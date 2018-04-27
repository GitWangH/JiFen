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
import com.huatek.busi.dao.base.BusiBaseEngineeringQuantityListDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseEngineeringQuantityListDto;
import com.huatek.busi.model.base.BusiBaseEngineeringQuantityList;
import com.huatek.busi.service.base.BusiBaseEngineeringQuantityListService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

/**
 * 工程量清单
 * @author eli_cui
 *
 */
@Service("busiBaseEngineeringQuantityListServiceImpl")
@Transactional
public class BusiBaseEngineeringQuantityListServiceImpl implements BusiBaseEngineeringQuantityListService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiBaseEngineeringQuantityListServiceImpl.class);
	
	@Autowired
	BusiBaseEngineeringQuantityListDao busiBaseEngineeringQuantityListDao;
	
	@Override
	public List<BusiBaseEngineeringQuantityListDto> getTopLevelData(QueryPage queryPage) {
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
  	    List<BusiBaseEngineeringQuantityListDto> resultData = BeanCopy.getInstance().convertList(busiBaseEngineeringQuantityListDao.getAllBusiBaseEngineeringQuantityList(queryPage).getContent(), BusiBaseEngineeringQuantityListDto.class);
		return resultData;
	}
	
	@Override
	public List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId, List<BusiBaseEngineeringQuantityListDto> dtoList) {
		List<BusiBaseEngineeringQuantityListDto> addList = null;
		List<BusiBaseEngineeringQuantityListDto> updateList = null;
		List<BusiBaseEngineeringQuantityListDto> deleteList = null;
		List<BusiBaseEngineeringQuantityList> addEntityList = null;
		if(null != dtoList && dtoList.size() > 0){
			addList = new ArrayList<BusiBaseEngineeringQuantityListDto>();
			updateList = new ArrayList<BusiBaseEngineeringQuantityListDto>();
			deleteList = new ArrayList<BusiBaseEngineeringQuantityListDto>();
			for(BusiBaseEngineeringQuantityListDto entity : dtoList){
				entity.setOrgId(orgId);
				switch (entity.getOperation()) {
				case Constant.OPERATION_TYPE_ADD :
					beforeSave(entity);
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
					throw new BusinessRuntimeException("BusiBaseEngineeringQuantityListServiceImpl.dataManipulation, 增加 修改 删除key错误", "-1");
				}
			}
			/*批量增加*/
			if(null != addList && addList.size() > 0){
				addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(addList, BusiBaseEngineeringQuantityList.class);
				busiBaseEngineeringQuantityListDao.batchSave(addEntityList, addList.size());
			}
			/*批量修改*/
			if(null != updateList && updateList.size() > 0){
				List<BusiBaseEngineeringQuantityList> updateEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(updateList, BusiBaseEngineeringQuantityList.class);
				busiBaseEngineeringQuantityListDao.batchSave(updateEntityList, updateList.size());
			}
			/*批量删除*/
			if(null != deleteList && deleteList.size() > 0){
				List<BusiBaseEngineeringQuantityList> deleteEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(deleteList, BusiBaseEngineeringQuantityList.class);
				busiBaseEngineeringQuantityListDao.batchSave(deleteEntityList, deleteList.size());
			}
		}
		if(addEntityList != null){
			List<TreeGridAddIdAndUUIDDto> list = new ArrayList<TreeGridAddIdAndUUIDDto>();
			for(BusiBaseEngineeringQuantityList entity : addEntityList){
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
	public List<BusiBaseEngineeringQuantityListDto> getChildqNodesByParentPK(Long id, QueryPage queryPage) {
		//List<BusiBaseEngineeringQuantityList> entityList = busiBaseEngineeringQuantityListDao.getAllBusiBaseEngineeringQuantityList(queryPage).getContent();
		BusiBaseEngineeringQuantityList entity = busiBaseEngineeringQuantityListDao.findBusiBaseEngineeringQuantityListById(id);
		return BeanCopy.getInstance().convertList(busiBaseEngineeringQuantityListDao.getChildqNodesByParentUUIDAndOrgId(entity.getUUID(), Long.valueOf(queryPage.getQueryParamList().get(0).getValue())), BusiBaseEngineeringQuantityListDto.class);
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(BusiBaseEngineeringQuantityListDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_DELETED);
	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiBaseEngineeringQuantityListDto
	* @param    busiBaseEngineeringQuantityList
	* @return  void    
	* @
	*/
	private void beforeSave(BusiBaseEngineeringQuantityListDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
	}
	

}
