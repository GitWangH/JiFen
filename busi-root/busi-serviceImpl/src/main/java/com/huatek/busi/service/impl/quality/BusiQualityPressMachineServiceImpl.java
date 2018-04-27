package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityPressMachineDao;
import com.huatek.busi.dao.quality.BusiQualityUniversalPressMachineParentDao;
import com.huatek.busi.dto.quality.BusiQualityPressMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.quality.BusiQualityPressMachine;
import com.huatek.busi.model.quality.BusiQualityUniversalPressMachineParent;
import com.huatek.busi.service.quality.BusiQualityPressMachineService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPressMachineServiceImpl")
@Transactional
public class BusiQualityPressMachineServiceImpl implements BusiQualityPressMachineService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPressMachineServiceImpl.class);
	
	@Autowired
	BusiQualityPressMachineDao busiQualityPressMachineDao;
	
	@Autowired
	BusiQualityUniversalPressMachineParentDao parentDao;
	
	@Override
	public void saveBusiQualityPressMachineDto(BusiQualityPressMachineDto entityDto, BusiQualityUniversalPressMachineParentDto parentEntityDto)  {
		log.debug("save busiQualityPressMachineDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPressMachine entity = BeanCopy.getInstance().convert(entityDto, BusiQualityPressMachine.class);
		//进行持久化保存
		busiQualityPressMachineDao.persistentBusiQualityPressMachine(entity);
		BusiFwOrg org = new BusiFwOrg();
		org.setId(entityDto.getOrgId());
		//抽象父表 entity
		BusiQualityUniversalPressMachineParent parentEntity = BeanCopy.getInstance().convert(parentEntityDto, BusiQualityUniversalPressMachineParent.class);
		parentEntity.setOrg(org);
		parentEntity.setBusiQualityPressMachine(entity);
		parentEntity.setTenantId(entity.getTenantId());
		parentEntity.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
		BusiFwOrg factOrg = new BusiFwOrg();
		factOrg.setId(entity.getFactOrgId());
		parentEntity.setFactOrg(factOrg);
		parentDao.persistentBusiQualityUniversalPressMachineParent(parentEntity);
	}
	
	
	@Override
	public BusiQualityPressMachineDto getBusiQualityPressMachineDtoById(Long id) {
		log.debug("get busiQualityPressMachine by id@" + id);
		BusiQualityPressMachine entity = busiQualityPressMachineDao.findBusiQualityPressMachineById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityPressMachineDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityPressMachineDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPressMachine(Long id, BusiQualityPressMachineDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPressMachine entity = busiQualityPressMachineDao.findBusiQualityPressMachineById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityPressMachineDao.persistentBusiQualityPressMachine(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityPressMachine(Long id) {
		log.debug("delete busiQualityPressMachine by id@" + id);
		beforeRemove(id);
		BusiQualityPressMachine entity = busiQualityPressMachineDao.findBusiQualityPressMachineById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPressMachineDao.deleteBusiQualityPressMachine(entity);
	}
	
	@Override
	public DataPage<BusiQualityPressMachineDto> getAllBusiQualityPressMachinePage(QueryPage queryPage) {
		DataPage<BusiQualityPressMachine> dataPage = busiQualityPressMachineDao.getAllBusiQualityPressMachine(queryPage);
		DataPage<BusiQualityPressMachineDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualityPressMachineDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityPressMachineDto> getAllBusiQualityPressMachineDto() {
		List<BusiQualityPressMachine> entityList = busiQualityPressMachineDao.findAllBusiQualityPressMachine();
		List<BusiQualityPressMachineDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityPressMachineDto.class);
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
	* @param    busiQualityPressMachineDto
	* @param    busiQualityPressMachine
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPressMachineDto entityDto, BusiQualityPressMachine entity) {

	}
}
