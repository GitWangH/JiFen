package com.huatek.busi.service.impl.progress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.progress.BusiProgressImageToBranchConnectDao;
import com.huatek.busi.dto.base.paramEntity.BusiBaseImageListSubConnectionTableParamEntity;
import com.huatek.busi.dto.progress.BusiProgressImageToBranchConnectDto;
import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTable;
import com.huatek.busi.model.progress.BusiProgressImageToBranchConnect;
import com.huatek.busi.service.progress.BusiProgressImageToBranchConnectService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiProgressImageToBranchConnectServiceImpl")
@Transactional
public class BusiProgressImageToBranchConnectServiceImpl implements BusiProgressImageToBranchConnectService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiProgressImageToBranchConnectServiceImpl.class);
	
	@Autowired
	BusiProgressImageToBranchConnectDao busiProgressImageToBranchConnectDao;
	
	@Override
	public void saveBusiProgressImageToBranchConnectDto(BusiProgressImageToBranchConnectDto entityDto)  {
		log.debug("save busiProgressImageToBranchConnectDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiProgressImageToBranchConnect entity = DTOUtils.map(entityDto, BusiProgressImageToBranchConnect.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiProgressImageToBranchConnectDao.persistentBusiProgressImageToBranchConnect(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiProgressImageToBranchConnectDto getBusiProgressImageToBranchConnectDtoById(Long id) {
		log.debug("get busiProgressImageToBranchConnect by id@" + id);
		BusiProgressImageToBranchConnect entity = busiProgressImageToBranchConnectDao.findBusiProgressImageToBranchConnectById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiProgressImageToBranchConnectDto entityDto = DTOUtils.map(entity, BusiProgressImageToBranchConnectDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiProgressImageToBranchConnect(Long id, BusiProgressImageToBranchConnectDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiProgressImageToBranchConnect entity = busiProgressImageToBranchConnectDao.findBusiProgressImageToBranchConnectById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiProgressImageToBranchConnectDao.persistentBusiProgressImageToBranchConnect(entity);
	}
	
	@Override
	public void deleteBusiProgressImageToBranchConnect(Long[] ids) {
		log.debug("delete busiProgressImageToBranchConnect by id@" + ids);
		List<BusiProgressImageToBranchConnect> list = busiProgressImageToBranchConnectDao.findBusiProgressImageToBranchConnectByCondition(ids);
		busiProgressImageToBranchConnectDao.batchDeleteBusiProgressImageToBranchConnect(list,20);
	}
	
	@Override
	public DataPage<BusiProgressImageToBranchConnectDto> getAllBusiProgressImageToBranchConnectPage(QueryPage queryPage) {
		DataPage<BusiProgressImageToBranchConnect> dataPage = busiProgressImageToBranchConnectDao.getAllBusiProgressImageToBranchConnect(queryPage);
		DataPage<BusiProgressImageToBranchConnectDto> datPageDto = BeanCopy.getInstance().addFieldMap("progressImage.code", "imageCode")//
				.addFieldMap("progressImage.name", "imageName").addFieldMap("contractTendersBranch.tendersBranchCode", "branchCode")//
				.addFieldMap("contractTendersBranch.tendersBranchName", "branchName")//
				.convertPage(dataPage, BusiProgressImageToBranchConnectDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiProgressImageToBranchConnectDto> getAllBusiProgressImageToBranchConnectDto() {
		List<BusiProgressImageToBranchConnect> entityList = busiProgressImageToBranchConnectDao.findAllBusiProgressImageToBranchConnect();
		List<BusiProgressImageToBranchConnectDto> dtos = DTOUtils.mapList(entityList, BusiProgressImageToBranchConnectDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(Long id) {

	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiProgressImageToBranchConnectDto
	* @param    busiProgressImageToBranchConnect
	* @return  void    
	* @
	*/
	private void beforeSave(BusiProgressImageToBranchConnectDto entityDto, BusiProgressImageToBranchConnect entity) {

	}


	@Override
	public void saveBusiProgressImageListSubConnectionTable(BusiBaseImageListSubConnectionTableParamEntity entity) {
		/**形象清单id*/
		Long imageId = entity.getImageId();
		/**分部分项id列表*/
		List<Long> SubEngineeringIdList = entity.getSubEngineeringIdList();
		for(Long subEngineeringId : SubEngineeringIdList){
			BusiProgressImageToBranchConnectDto dto = new BusiProgressImageToBranchConnectDto(imageId,subEngineeringId);
			BusiProgressImageToBranchConnect busiProgressImageListSubConnectionTable = BeanCopy.getInstance().addFieldMap("imageId", "progressImage")//contractTendersBranch
					.addFieldMap("branchId", "contractTendersBranch").convert(dto, BusiProgressImageToBranchConnect.class);
			busiProgressImageToBranchConnectDao.persistentBusiProgressImageToBranchConnect(busiProgressImageListSubConnectionTable);
		}
		
	}
}
