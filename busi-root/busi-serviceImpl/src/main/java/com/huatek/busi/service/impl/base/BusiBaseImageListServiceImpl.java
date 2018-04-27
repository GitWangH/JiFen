package com.huatek.busi.service.impl.base;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.base.BusiBaseImageListDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseImageListDto;
import com.huatek.busi.model.base.BusiBaseEngineeringQuantityList;
import com.huatek.busi.model.base.BusiBaseImageList;
import com.huatek.busi.service.base.BusiBaseImageListService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

/**
 * 形象清单
 * @author eli_cui
 *
 */
@Service("busiBaseImageListServiceImpl")
@Transactional
public class BusiBaseImageListServiceImpl implements BusiBaseImageListService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiBaseImageListServiceImpl.class);
	
	@Autowired
	BusiBaseImageListDao busiBaseImageListDao;
	
	@Override
	public List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId, List<BusiBaseImageListDto> dtoList) {
		List<BusiBaseImageListDto> addList = null;
		List<BusiBaseImageListDto> updateList = null;
		List<BusiBaseImageListDto> deleteList = null;
		List<BusiBaseImageList> addEntityList = null;
		if(null != dtoList && dtoList.size() > 0){
			addList = new ArrayList<BusiBaseImageListDto>();
			updateList = new ArrayList<BusiBaseImageListDto>();
			deleteList = new ArrayList<BusiBaseImageListDto>();
			for(BusiBaseImageListDto entity : dtoList){
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
					throw new BusinessRuntimeException("BusiBaseImageListServiceImpl.dataManipulation, 增加 修改 删除key错误", "-1");
				}
			}
			/*批量增加*/
			if(null != addList && addList.size() > 0){
				addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(addList, BusiBaseImageList.class);
				busiBaseImageListDao.batchSave(addEntityList, addList.size());
			}
			/*批量修改*/
			if(null != updateList && updateList.size() > 0){
				List<BusiBaseImageList> updateEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(updateList, BusiBaseImageList.class);
				busiBaseImageListDao.batchSave(updateEntityList, updateList.size());
			}
			/*批量删除*/
			if(null != deleteList && deleteList.size() > 0){
				List<BusiBaseImageList> deleteEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(deleteList, BusiBaseImageList.class);
				busiBaseImageListDao.batchSave(deleteEntityList, deleteList.size());
			}
		}
		if(addEntityList != null){
			List<TreeGridAddIdAndUUIDDto> list = new ArrayList<TreeGridAddIdAndUUIDDto>();
			for(BusiBaseImageList entity : addEntityList){
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
	public List<BusiBaseImageListDto> getChildqNodesByParentPK(Long id, QueryPage queryPage) {
		BusiBaseImageList entity = busiBaseImageListDao.findBusiBaseImageListById(id);
		return BeanCopy.getInstance().convertList(busiBaseImageListDao.getChildqNodesByParentUUIDAndOrgId(entity.getUUID(), Long.valueOf(queryPage.getQueryParamList().get(0).getValue())), BusiBaseImageListDto.class);
	}
	
	@Override
	public List<BusiBaseImageListDto> getTopLevelData(QueryPage queryPage) {
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
  	    List<BusiBaseImageListDto> result = BeanCopy.getInstance().convertList(busiBaseImageListDao.getAllBusiBaseImageList(queryPage).getContent(), BusiBaseImageListDto.class);
		return result;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(BusiBaseImageListDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_DELETED);
	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @return  void    
	* @
	*/
	private void beforeSave(BusiBaseImageListDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
	}
}
