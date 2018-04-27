package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualitySpreaderInspectionDao;
import com.huatek.busi.dto.quality.BusiQualitySpreaderInspectionDto;
import com.huatek.busi.model.quality.BusiQualitySpreaderInspection;
import com.huatek.busi.service.quality.BusiQualitySpreaderInspectionService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualitySpreaderInspectionServiceImpl")
@Transactional
public class BusiQualitySpreaderInspectionServiceImpl implements BusiQualitySpreaderInspectionService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySpreaderInspectionServiceImpl.class);
	
	@Autowired
	BusiQualitySpreaderInspectionDao busiQualitySpreaderInspectionDao;
	
	@Override
	public void saveBusiQualitySpreaderInspectionDto(BusiQualitySpreaderInspectionDto entityDto)  {
		log.debug("save busiQualitySpreaderInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualitySpreaderInspection entity = BeanCopy.getInstance().convert(entityDto, BusiQualitySpreaderInspection.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualitySpreaderInspectionDao.persistentBusiQualitySpreaderInspection(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualitySpreaderInspectionDto getBusiQualitySpreaderInspectionDtoById(Long id) {
		log.debug("get busiQualitySpreaderInspection by id@" + id);
		BusiQualitySpreaderInspection entity = busiQualitySpreaderInspectionDao.findBusiQualitySpreaderInspectionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualitySpreaderInspectionDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualitySpreaderInspectionDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySpreaderInspection(Long id, BusiQualitySpreaderInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualitySpreaderInspection entity = busiQualitySpreaderInspectionDao.findBusiQualitySpreaderInspectionById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualitySpreaderInspectionDao.persistentBusiQualitySpreaderInspection(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualitySpreaderInspection(Long id) {
		log.debug("delete busiQualitySpreaderInspection by id@" + id);
		beforeRemove(id);
		BusiQualitySpreaderInspection entity = busiQualitySpreaderInspectionDao.findBusiQualitySpreaderInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySpreaderInspectionDao.deleteBusiQualitySpreaderInspection(entity);
	}
	
	@Override
	public DataPage<BusiQualitySpreaderInspectionDto> getAllBusiQualitySpreaderInspectionPage(QueryPage queryPage) {
		DataPage<BusiQualitySpreaderInspection> dataPage = busiQualitySpreaderInspectionDao.getAllBusiQualitySpreaderInspection(queryPage);
		DataPage<BusiQualitySpreaderInspectionDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiQualitySpreaderInspectionDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualitySpreaderInspectionDto> getAllBusiQualitySpreaderInspectionDto() {
		List<BusiQualitySpreaderInspection> entityList = busiQualitySpreaderInspectionDao.findAllBusiQualitySpreaderInspection();
		List<BusiQualitySpreaderInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualitySpreaderInspectionDto.class);
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
	* @param    busiQualitySpreaderInspectionDto
	* @param    busiQualitySpreaderInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySpreaderInspectionDto entityDto, BusiQualitySpreaderInspection entity) {

	}
}
