package com.huatek.busi.service.impl.quality;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityAsphaltMixingPlantExceedDao;
import com.huatek.busi.dto.quality.BusiQualityAsphaltMixingPlantExceedDto;
import com.huatek.busi.model.quality.BusiQualityAsphaltMixingPlantExceed;
import com.huatek.busi.service.quality.BusiQualityAsphaltMixingPlantExceedService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityAsphaltMixingPlantExceedServiceImpl")
@Transactional
public class BusiQualityAsphaltMixingPlantExceedServiceImpl implements BusiQualityAsphaltMixingPlantExceedService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityAsphaltMixingPlantExceedServiceImpl.class);
	
	@Autowired
	BusiQualityAsphaltMixingPlantExceedDao busiQualityAsphaltMixingPlantExceedDao;
	
	@Override
	public void saveBusiQualityAsphaltMixingPlantExceedDto(BusiQualityAsphaltMixingPlantExceedDto entityDto)  {
		log.debug("save busiQualityAsphaltMixingPlantExceedDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityAsphaltMixingPlantExceed entity = BeanCopy.getInstance().convert(entityDto, BusiQualityAsphaltMixingPlantExceed.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityAsphaltMixingPlantExceedDao.persistentBusiQualityAsphaltMixingPlantExceed(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityAsphaltMixingPlantExceedDto getBusiQualityAsphaltMixingPlantExceedDtoById(Long id) {
		log.debug("get busiQualityAsphaltMixingPlantExceed by id@" + id);
		BusiQualityAsphaltMixingPlantExceed entity = busiQualityAsphaltMixingPlantExceedDao.findBusiQualityAsphaltMixingPlantExceedById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityAsphaltMixingPlantExceedDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityAsphaltMixingPlantExceedDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityAsphaltMixingPlantExceed(Long id, BusiQualityAsphaltMixingPlantExceedDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityAsphaltMixingPlantExceed entity = busiQualityAsphaltMixingPlantExceedDao.findBusiQualityAsphaltMixingPlantExceedById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityAsphaltMixingPlantExceedDao.persistentBusiQualityAsphaltMixingPlantExceed(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityAsphaltMixingPlantExceed(Long id) {
		log.debug("delete busiQualityAsphaltMixingPlantExceed by id@" + id);
		beforeRemove(id);
		BusiQualityAsphaltMixingPlantExceed entity = busiQualityAsphaltMixingPlantExceedDao.findBusiQualityAsphaltMixingPlantExceedById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityAsphaltMixingPlantExceedDao.deleteBusiQualityAsphaltMixingPlantExceed(entity);
	}
	
	@Override
	public DataPage<BusiQualityAsphaltMixingPlantExceedDto> getAllBusiQualityAsphaltMixingPlantExceedPage(QueryPage queryPage) {
		DataPage<BusiQualityAsphaltMixingPlantExceed> dataPage = busiQualityAsphaltMixingPlantExceedDao.getAllBusiQualityAsphaltMixingPlantExceed(queryPage);
		DataPage<BusiQualityAsphaltMixingPlantExceedDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualityAsphaltMixingPlantExceedDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityAsphaltMixingPlantExceedDto> getAllBusiQualityAsphaltMixingPlantExceedDto() {
		List<BusiQualityAsphaltMixingPlantExceed> entityList = busiQualityAsphaltMixingPlantExceedDao.findAllBusiQualityAsphaltMixingPlantExceed();
		List<BusiQualityAsphaltMixingPlantExceedDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityAsphaltMixingPlantExceedDto.class);
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
	* @param    busiQualityAsphaltMixingPlantExceedDto
	* @param    busiQualityAsphaltMixingPlantExceed
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityAsphaltMixingPlantExceedDto entityDto, BusiQualityAsphaltMixingPlantExceed entity) {

	}
}
