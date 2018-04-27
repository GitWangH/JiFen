package com.huatek.busi.service.impl.progress;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.base.BusiBaseImageListDao;
import com.huatek.busi.dao.progress.BusiProgressImageDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseImageListDto;
import com.huatek.busi.dto.progress.BusiProgressImageDto;
import com.huatek.busi.model.base.BusiBaseImageList;
import com.huatek.busi.model.progress.BusiProgressImage;
import com.huatek.busi.service.progress.BusiProgressImageService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiProgressImageServiceImpl")
@Transactional
public class BusiProgressImageServiceImpl implements BusiProgressImageService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiProgressImageServiceImpl.class);
	
	@Autowired
	BusiProgressImageDao busiProgressImageDao;
	
	
	@Override
	public List<BusiProgressImageDto> getTopLevelData(QueryPage queryPage) {
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
  	    List<BusiProgressImageDto> result = BeanCopy.getInstance().convertList(busiProgressImageDao.getAllBusiProgressImage(queryPage).getContent(), BusiProgressImageDto.class);
		return result;
	}
	
	@Override
	public List<BusiProgressImageDto> getChildqNodesByParentPK(Long id, QueryPage queryPage) {
		BusiProgressImage entity = busiProgressImageDao.findBusiProgressImageById(id);
		return BeanCopy.getInstance().convertList(busiProgressImageDao.getChildqNodesByParentUUIDAndOrgId(entity.getUuid(), Long.valueOf(queryPage.getQueryParamList().get(0).getValue())), BusiProgressImageDto.class);
	}
	
	@Override
	public List<BusiProgressImageDto> getChildqNodesAndDesignQuantityByParentPK(Long id, QueryPage queryPage) {
		BusiProgressImage entity = busiProgressImageDao.findBusiProgressImageById(id);
		List<BusiProgressImageDto> imageDtoList = BeanCopy.getInstance().convertList(busiProgressImageDao.getChildqNodesByParentUUIDAndOrgId(entity.getUuid(), Long.valueOf(queryPage.getQueryParamList().get(0).getValue())), BusiProgressImageDto.class);
		List<BusiProgressImage> designQuantityList = busiProgressImageDao.getDesignQuantityByImageId(id);
		if(designQuantityList != null){
			Map<Long, BigDecimal> map = getDesignQuantityMap(designQuantityList);
			for(BusiProgressImageDto dto : imageDtoList){
				if(map.containsKey(dto.getId())){
					dto.setDesignAmount(map.get(dto.getId()));
					dto.setChangeAmount(map.get(dto.getId()));
				}
			}
		}
		// TODO Auto-generated method stub
		return imageDtoList;
	}
	
	@Override
	public List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId,List<BusiProgressImageDto> dtoList) {
		List<BusiProgressImageDto> addList = null;
		List<BusiProgressImageDto> updateList = null;
		List<BusiProgressImageDto> deleteList = null;
		List<BusiProgressImage> addEntityList = null;
		if(null != dtoList && dtoList.size() > 0){
			addList = new ArrayList<BusiProgressImageDto>();
			updateList = new ArrayList<BusiProgressImageDto>();
			deleteList = new ArrayList<BusiProgressImageDto>();
			for(BusiProgressImageDto entity : dtoList){
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
					throw new BusinessRuntimeException("BusiProgressImageServiceImpl.dataManipulation, 增加 修改 删除key错误", "-1");
				}
			}
			/*批量增加*/
			if(null != addList && addList.size() > 0){
				addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(addList, BusiProgressImage.class);
				busiProgressImageDao.batchSave(addEntityList, addList.size());
			}
			/*批量修改*/
			if(null != updateList && updateList.size() > 0){
				List<BusiProgressImage> updateEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(updateList, BusiProgressImage.class);
				busiProgressImageDao.batchSave(updateEntityList, updateList.size());
			}
			/*批量删除*/
			if(null != deleteList && deleteList.size() > 0){
				List<BusiProgressImage> deleteEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(deleteList, BusiProgressImage.class);
				busiProgressImageDao.batchSave(deleteEntityList, deleteList.size());
			}
		}
		if(addEntityList != null){
			List<TreeGridAddIdAndUUIDDto> list = new ArrayList<TreeGridAddIdAndUUIDDto>();
			for(BusiProgressImage entity : addEntityList){
				TreeGridAddIdAndUUIDDto dto = new TreeGridAddIdAndUUIDDto();
				dto.setId(entity.getId());
				dto.setUuid(entity.getUuid());
				list.add(dto);
			}
			return list;
		} else {
			return null;
		}
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(BusiProgressImageDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_DELETED);

	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiProgressImageDto
	* @param    busiProgressImage
	* @return  void    
	* @
	*/
	private void beforeSave(BusiProgressImageDto entityDto) {
		entityDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
	}

	@Override
	public void createProgressImage(Long orgId) {
		busiProgressImageDao.initProgressImage(orgId);
	}
	
	/**
	 * 根据查出来的 designQuantityList 获取 一个 key是 imageId value 是designQuantity的Map
	 * @param list
	 * @return
	 */
	private Map<Long, BigDecimal> getDesignQuantityMap(List<BusiProgressImage> designQuantityList){
		Map<Long, BigDecimal> map = new HashMap<Long, BigDecimal>();
		for(BusiProgressImage entity : designQuantityList){
			map.put(entity.getImageId(), entity.getSum());
		}
		return map;
	}
}
