package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityWaterStableMixingStationExceedDao;
import com.huatek.busi.dto.quality.BusiQualityWaterStableMixingStationExceedDto;
import com.huatek.busi.model.quality.BusiQualityWaterStableMixingStationExceed;
import com.huatek.busi.service.quality.BusiQualityWaterStableMixingStationExceedService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityWaterStableMixingStationExceedServiceImpl")
@Transactional
public class BusiQualityWaterStableMixingStationExceedServiceImpl implements BusiQualityWaterStableMixingStationExceedService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityWaterStableMixingStationExceedServiceImpl.class);
	
	@Autowired
	BusiQualityWaterStableMixingStationExceedDao busiQualityWaterStableMixingStationExceedDao;
	
	@Override
	public void saveBusiQualityWaterStableMixingStationExceedDto(BusiQualityWaterStableMixingStationExceedDto entityDto)  {
		log.debug("save busiQualityWaterStableMixingStationExceedDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityWaterStableMixingStationExceed entity = BeanCopy.getInstance().convert(entityDto, BusiQualityWaterStableMixingStationExceed.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityWaterStableMixingStationExceedDao.persistentBusiQualityWaterStableMixingStationExceed(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityWaterStableMixingStationExceedDto getBusiQualityWaterStableMixingStationExceedDtoById(Long id) {
		log.debug("get busiQualityWaterStableMixingStationExceed by id@" + id);
		BusiQualityWaterStableMixingStationExceed entity = busiQualityWaterStableMixingStationExceedDao.findBusiQualityWaterStableMixingStationExceedById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityWaterStableMixingStationExceedDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityWaterStableMixingStationExceedDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityWaterStableMixingStationExceed(Long id, BusiQualityWaterStableMixingStationExceedDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityWaterStableMixingStationExceed entity = busiQualityWaterStableMixingStationExceedDao.findBusiQualityWaterStableMixingStationExceedById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityWaterStableMixingStationExceedDao.persistentBusiQualityWaterStableMixingStationExceed(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityWaterStableMixingStationExceed(Long id) {
		log.debug("delete busiQualityWaterStableMixingStationExceed by id@" + id);
		beforeRemove(id);
		BusiQualityWaterStableMixingStationExceed entity = busiQualityWaterStableMixingStationExceedDao.findBusiQualityWaterStableMixingStationExceedById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityWaterStableMixingStationExceedDao.deleteBusiQualityWaterStableMixingStationExceed(entity);
	}
	
	@Override
	public DataPage<BusiQualityWaterStableMixingStationExceedDto> getAllBusiQualityWaterStableMixingStationExceedPage(QueryPage queryPage) {
		DataPage<BusiQualityWaterStableMixingStationExceed> dataPage = busiQualityWaterStableMixingStationExceedDao.getAllBusiQualityWaterStableMixingStationExceed(queryPage);
		DataPage<BusiQualityWaterStableMixingStationExceedDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualityWaterStableMixingStationExceedDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityWaterStableMixingStationExceedDto> getAllBusiQualityWaterStableMixingStationExceedDto() {
		List<BusiQualityWaterStableMixingStationExceed> entityList = busiQualityWaterStableMixingStationExceedDao.findAllBusiQualityWaterStableMixingStationExceed();
		List<BusiQualityWaterStableMixingStationExceedDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityWaterStableMixingStationExceedDto.class);
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
	* @param    busiQualityWaterStableMixingStationExceedDto
	* @param    busiQualityWaterStableMixingStationExceed
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityWaterStableMixingStationExceedDto entityDto, BusiQualityWaterStableMixingStationExceed entity) {

	}
}
