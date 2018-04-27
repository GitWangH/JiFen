package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityRollerInspectionDao;
import com.huatek.busi.dto.quality.BusiQualityRollerInspectionDto;
import com.huatek.busi.model.quality.BusiQualityRollerInspection;
import com.huatek.busi.service.quality.BusiQualityRollerInspectionService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityRollerInspectionServiceImpl")
@Transactional
public class BusiQualityRollerInspectionServiceImpl implements BusiQualityRollerInspectionService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityRollerInspectionServiceImpl.class);
	
	@Autowired
	BusiQualityRollerInspectionDao busiQualityRollerInspectionDao;
	
	@Override
	public void saveBusiQualityRollerInspectionDto(BusiQualityRollerInspectionDto entityDto)  {
		log.debug("save busiQualityRollerInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityRollerInspection entity = BeanCopy.getInstance().convert(entityDto, BusiQualityRollerInspection.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityRollerInspectionDao.persistentBusiQualityRollerInspection(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityRollerInspectionDto getBusiQualityRollerInspectionDtoById(Long id) {
		log.debug("get busiQualityRollerInspection by id@" + id);
		BusiQualityRollerInspection entity = busiQualityRollerInspectionDao.findBusiQualityRollerInspectionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityRollerInspectionDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityRollerInspectionDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityRollerInspection(Long id, BusiQualityRollerInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityRollerInspection entity = busiQualityRollerInspectionDao.findBusiQualityRollerInspectionById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityRollerInspectionDao.persistentBusiQualityRollerInspection(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityRollerInspection(Long id) {
		log.debug("delete busiQualityRollerInspection by id@" + id);
		beforeRemove(id);
		BusiQualityRollerInspection entity = busiQualityRollerInspectionDao.findBusiQualityRollerInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityRollerInspectionDao.deleteBusiQualityRollerInspection(entity);
	}
	
	@Override
	public DataPage<BusiQualityRollerInspectionDto> getAllBusiQualityRollerInspectionPage(QueryPage queryPage) {
		DataPage<BusiQualityRollerInspection> dataPage = busiQualityRollerInspectionDao.getAllBusiQualityRollerInspection(queryPage);
		DataPage<BusiQualityRollerInspectionDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualityRollerInspectionDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityRollerInspectionDto> getAllBusiQualityRollerInspectionDto() {
		List<BusiQualityRollerInspection> entityList = busiQualityRollerInspectionDao.findAllBusiQualityRollerInspection();
		List<BusiQualityRollerInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityRollerInspectionDto.class);
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
	* @param    busiQualityRollerInspectionDto
	* @param    busiQualityRollerInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityRollerInspectionDto entityDto, BusiQualityRollerInspection entity) {

	}
}
