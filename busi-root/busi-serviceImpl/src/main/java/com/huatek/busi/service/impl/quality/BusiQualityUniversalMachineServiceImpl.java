package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityUniversalMachineDao;
import com.huatek.busi.dao.quality.BusiQualityUniversalPressMachineParentDao;
import com.huatek.busi.dto.quality.BusiQualityUniversalMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.quality.BusiQualityUniversalMachine;
import com.huatek.busi.model.quality.BusiQualityUniversalPressMachineParent;
import com.huatek.busi.service.quality.BusiQualityUniversalMachineService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityUniversalMachineServiceImpl")
@Transactional
public class BusiQualityUniversalMachineServiceImpl implements BusiQualityUniversalMachineService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityUniversalMachineServiceImpl.class);
	
	@Autowired
	BusiQualityUniversalMachineDao busiQualityUniversalMachineDao;
	
	@Autowired
	BusiQualityUniversalPressMachineParentDao parentDao;
	
	/**
	 * 接口保存方法
	 */
	@Override
	public void saveBusiQualityUniversalMachineDto(BusiQualityUniversalMachineDto entityDto, BusiQualityUniversalPressMachineParentDto parentEntityDto)  {
		log.debug("save busiQualityUniversalMachineDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityUniversalMachine entity = BeanCopy.getInstance().convert(entityDto, BusiQualityUniversalMachine.class);
		//进行持久化保存
		busiQualityUniversalMachineDao.persistentBusiQualityUniversalMachine(entity);
		BusiFwOrg org = new BusiFwOrg();
		org.setId(entityDto.getOrgId());
		//抽象父表 entity
		BusiQualityUniversalPressMachineParent parentEntity = BeanCopy.getInstance().convert(parentEntityDto, BusiQualityUniversalPressMachineParent.class);
		parentEntity.setOrg(org);
		parentEntity.setBusiQualityUniversalMachine(entity);
		parentEntity.setTenantId(entity.getTenantId());
		parentEntity.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
		BusiFwOrg factOrg = new BusiFwOrg();
		factOrg.setId(entity.getFactOrgId());
		parentEntity.setFactOrg(factOrg);
		parentDao.persistentBusiQualityUniversalPressMachineParent(parentEntity);

	}
	
	
	@Override
	public BusiQualityUniversalMachineDto getBusiQualityUniversalMachineDtoById(Long id) {
		log.debug("get busiQualityUniversalMachine by id@" + id);
		BusiQualityUniversalMachine entity = busiQualityUniversalMachineDao.findBusiQualityUniversalMachineById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityUniversalMachineDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityUniversalMachineDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityUniversalMachine(Long id, BusiQualityUniversalMachineDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityUniversalMachine entity = busiQualityUniversalMachineDao.findBusiQualityUniversalMachineById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityUniversalMachineDao.persistentBusiQualityUniversalMachine(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityUniversalMachine(Long id) {
		log.debug("delete busiQualityUniversalMachine by id@" + id);
		beforeRemove(id);
		BusiQualityUniversalMachine entity = busiQualityUniversalMachineDao.findBusiQualityUniversalMachineById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityUniversalMachineDao.deleteBusiQualityUniversalMachine(entity);
	}
	
	@Override
	public DataPage<BusiQualityUniversalMachineDto> getAllBusiQualityUniversalMachinePage(QueryPage queryPage) {
		DataPage<BusiQualityUniversalMachine> dataPage = busiQualityUniversalMachineDao.getAllBusiQualityUniversalMachine(queryPage);
		DataPage<BusiQualityUniversalMachineDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualityUniversalMachineDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityUniversalMachineDto> getAllBusiQualityUniversalMachineDto() {
		List<BusiQualityUniversalMachine> entityList = busiQualityUniversalMachineDao.findAllBusiQualityUniversalMachine();
		List<BusiQualityUniversalMachineDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityUniversalMachineDto.class);
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
	* @param    busiQualityUniversalMachineDto
	* @param    busiQualityUniversalMachine
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityUniversalMachineDto entityDto, BusiQualityUniversalMachine entity) {

	}
}
