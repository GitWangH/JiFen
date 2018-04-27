package com.huatek.busi.service.impl.base;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.base.BusiBaseSubEngineeringDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseSubEngineeringDto;
import com.huatek.busi.model.base.BusiBaseSubEngineering;
import com.huatek.busi.service.base.BusiBaseSubEngineeringService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

@Service("busiBaseSubEngineeringServiceImpl")
@Transactional
public class BusiBaseSubEngineeringServiceImpl implements BusiBaseSubEngineeringService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiBaseSubEngineeringServiceImpl.class);
	
	@Autowired
	BusiBaseSubEngineeringDao busiBaseSubEngineeringDao;
	
	@Override
	public List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId, List<BusiBaseSubEngineeringDto> dtoList) {
		List<BusiBaseSubEngineeringDto> addList = null;
		List<BusiBaseSubEngineeringDto> updateList = null;
		List<BusiBaseSubEngineeringDto> deleteList = null;
		List<BusiBaseSubEngineering> addEntityList = null;
		if(null != dtoList && dtoList.size() > 0){
			addList = new ArrayList<BusiBaseSubEngineeringDto>();
			updateList = new ArrayList<BusiBaseSubEngineeringDto>();
			deleteList = new ArrayList<BusiBaseSubEngineeringDto>();
			for(BusiBaseSubEngineeringDto entity : dtoList){
				entity.setOrgId(orgId);
				switch (entity.getOperation()) {
				case Constant.OPERATION_TYPE_ADD :
					addList.add(entity);
					break;
				case Constant.OPERATION_TYPE_UPDATE :
					updateList.add(entity);
					break;
				case Constant.OPERATION_TYPE_DELETE :
					beforeRemove(entity);
					deleteList.add(entity);
					break;
				default:
					throw new BusinessRuntimeException("BusiBaseSubEngineeringServiceImpl.dataManipulation, 增加 修改 删除key错误", "-1");
				}
			}
			/*批量增加*/
			if(null != addList && addList.size() > 0){
				addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(addList, BusiBaseSubEngineering.class);
				busiBaseSubEngineeringDao.batchSave(addEntityList, addList.size());
			}
			/*批量修改*/
			if(null != updateList && updateList.size() > 0){
				List<BusiBaseSubEngineering> updateEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(updateList, BusiBaseSubEngineering.class);
				busiBaseSubEngineeringDao.batchSave(updateEntityList, updateList.size());
			}
			/*批量删除*/
			if(null != deleteList && deleteList.size() > 0){
				List<BusiBaseSubEngineering> deleteEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(deleteList, BusiBaseSubEngineering.class);
				busiBaseSubEngineeringDao.batchSave(deleteEntityList, deleteList.size());
			}
		}
		if(addEntityList != null){
			List<TreeGridAddIdAndUUIDDto> list = new ArrayList<TreeGridAddIdAndUUIDDto>();
			for(BusiBaseSubEngineering entity : addEntityList){
				TreeGridAddIdAndUUIDDto dto = new TreeGridAddIdAndUUIDDto();
				dto.setId(entity.getId());
				dto.setUuid(entity.getUUID());
				list.add(dto);
			}
			return list;
		} else {
			return null;
		}
	}
	
	@Override
	public List<BusiBaseSubEngineeringDto> getChildqNodesByParentPK(Long id, QueryPage queryPage) {
		BusiBaseSubEngineering entity = busiBaseSubEngineeringDao.findBusiBaseSubEngineeringById(id);
		return BeanCopy.getInstance().convertList(busiBaseSubEngineeringDao.getChildqNodesByParentUUIDAndOrgId(entity.getUUID(), Long.valueOf(queryPage.getQueryParamList().get(0).getValue())), BusiBaseSubEngineeringDto.class);
	}
	
	@Override
	public List<BusiBaseSubEngineeringDto> getTopLevelData(QueryPage queryPage) {
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
  	    List<BusiBaseSubEngineeringDto> result = BeanCopy.getInstance().convertList(busiBaseSubEngineeringDao.getAllBusiBaseSubEngineering(queryPage).getContent(), BusiBaseSubEngineeringDto.class);
		return result;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(BusiBaseSubEngineeringDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_DELETED);
	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @return  void    
	* @
	*/
	private void beforeSave(BusiBaseSubEngineeringDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
	}
}
