package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityCementMixingStationExceedDao;
import com.huatek.busi.dao.quality.BusiQualityCementMixingStationInspectionDao;
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationExceedDto;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationExceed;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationExceedService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityCementMixingStationExceedServiceImpl")
@Transactional
public class BusiQualityCementMixingStationExceedServiceImpl implements BusiQualityCementMixingStationExceedService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityCementMixingStationExceedServiceImpl.class);
	
	@Autowired
	BusiQualityCementMixingStationExceedDao busiQualityCementMixingStationExceedDao;
	
	@Autowired
	private BusiQualityCementMixingStationInspectionDao stationDao;
	
	
	
	@Override
	public void saveBusiQualityCementMixingStationExceedDto(BusiQualityCementMixingStationExceedDto entityDto)  {
		log.debug("save busiQualityCementMixingStationExceedDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityCementMixingStationExceed entity = BeanCopy.getInstance().convert(entityDto, BusiQualityCementMixingStationExceed.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		BusiQualityCementMixingStationInspection busiQualityCementMixingStationInspection = stationDao.findBusiQualityCementMixingStationInspectionByUkey(entityDto.getUkey());
		//进行持久化保存
		busiQualityCementMixingStationExceedDao.persistentBusiQualityCementMixingStationExceed(entity);
		//保存水泥拌合站主体数据
		busiQualityCementMixingStationInspection.setBusiQualityCementMixingStationExceed(entity);
		stationDao.saveOrUpdateBusiQualityCementMixingStationInspection(busiQualityCementMixingStationInspection); 
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityCementMixingStationExceedDto getBusiQualityCementMixingStationExceedDtoById(Long id) {
		log.debug("get busiQualityCementMixingStationExceed by id@" + id);
		BusiQualityCementMixingStationExceed entity = busiQualityCementMixingStationExceedDao.findBusiQualityCementMixingStationExceedById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityCementMixingStationExceedDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityCementMixingStationExceedDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityCementMixingStationExceed(Long id, BusiQualityCementMixingStationExceedDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityCementMixingStationExceed entity = busiQualityCementMixingStationExceedDao.findBusiQualityCementMixingStationExceedById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityCementMixingStationExceedDao.persistentBusiQualityCementMixingStationExceed(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityCementMixingStationExceed(Long id) {
		log.debug("delete busiQualityCementMixingStationExceed by id@" + id);
		beforeRemove(id);
		BusiQualityCementMixingStationExceed entity = busiQualityCementMixingStationExceedDao.findBusiQualityCementMixingStationExceedById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityCementMixingStationExceedDao.deleteBusiQualityCementMixingStationExceed(entity);
	}
	
	@Override
	public DataPage<BusiQualityCementMixingStationExceedDto> getAllBusiQualityCementMixingStationExceedPage(QueryPage queryPage) {
		DataPage<BusiQualityCementMixingStationExceed> dataPage = busiQualityCementMixingStationExceedDao.getAllBusiQualityCementMixingStationExceed(queryPage);
		DataPage<BusiQualityCementMixingStationExceedDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualityCementMixingStationExceedDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityCementMixingStationExceedDto> getAllBusiQualityCementMixingStationExceedDto() {
		List<BusiQualityCementMixingStationExceed> entityList = busiQualityCementMixingStationExceedDao.findAllBusiQualityCementMixingStationExceed();
		List<BusiQualityCementMixingStationExceedDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityCementMixingStationExceedDto.class);
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
	* @param    busiQualityCementMixingStationExceedDto
	* @param    busiQualityCementMixingStationExceed
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityCementMixingStationExceedDto entityDto, BusiQualityCementMixingStationExceed entity) {

	}


	@Override
	public BusiQualityCementMixingStationExceedDto getBusiQualityCementMixingStationExceedByUkey(String uKey) {
		
		BusiQualityCementMixingStationExceed entity = busiQualityCementMixingStationExceedDao.findBusiQualityCementMixingStationExceedByUkey(uKey);
		return BeanCopy.getInstance().convert(entity,BusiQualityCementMixingStationExceedDto.class);
	}


}
