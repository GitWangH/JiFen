package com.huatek.busi.service.impl.measure;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.measure.BusiMeasureMiddlePayCertificateTemplateDao;
import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateTemplateDto;
import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificateTemplate;
import com.huatek.busi.service.measure.BusiMeasureMiddlePayCertificateTemplateService;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiMeasureMiddlePayCertificateTemplateServiceImpl")
@Transactional
public class BusiMeasureMiddlePayCertificateTemplateServiceImpl implements BusiMeasureMiddlePayCertificateTemplateService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiMeasureMiddlePayCertificateTemplateServiceImpl.class);
	
	@Autowired
	BusiMeasureMiddlePayCertificateTemplateDao busiMeasureMiddlePayCertificateTemplateDao;
	
	@Override
	public void saveBusiMeasureMiddlePayCertificateTemplateDto(BusiMeasureMiddlePayCertificateTemplateDto entityDto)  {
		log.debug("save busiMeasureMiddlePayCertificateTemplateDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiMeasureMiddlePayCertificateTemplate entity = DTOUtils.map(entityDto, BusiMeasureMiddlePayCertificateTemplate.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddlePayCertificateTemplateDao.persistentBusiMeasureMiddlePayCertificateTemplate(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiMeasureMiddlePayCertificateTemplateDto getBusiMeasureMiddlePayCertificateTemplateDtoById(Long id) {
		log.debug("get busiMeasureMiddlePayCertificateTemplate by id@" + id);
		BusiMeasureMiddlePayCertificateTemplate entity = busiMeasureMiddlePayCertificateTemplateDao.findBusiMeasureMiddlePayCertificateTemplateById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureMiddlePayCertificateTemplateDto entityDto = DTOUtils.map(entity, BusiMeasureMiddlePayCertificateTemplateDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureMiddlePayCertificateTemplate(Long id, BusiMeasureMiddlePayCertificateTemplateDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiMeasureMiddlePayCertificateTemplate entity = busiMeasureMiddlePayCertificateTemplateDao.findBusiMeasureMiddlePayCertificateTemplateById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiMeasureMiddlePayCertificateTemplateDao.persistentBusiMeasureMiddlePayCertificateTemplate(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureMiddlePayCertificateTemplate(Long id) {
		log.debug("delete busiMeasureMiddlePayCertificateTemplate by id@" + id);
		beforeRemove(id);
		BusiMeasureMiddlePayCertificateTemplate entity = busiMeasureMiddlePayCertificateTemplateDao.findBusiMeasureMiddlePayCertificateTemplateById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiMeasureMiddlePayCertificateTemplateDao.deleteBusiMeasureMiddlePayCertificateTemplate(entity);
	}
	
	@Override
	public DataPage<BusiMeasureMiddlePayCertificateTemplateDto> getAllBusiMeasureMiddlePayCertificateTemplatePage(QueryPage queryPage) {
		DataPage<BusiMeasureMiddlePayCertificateTemplate> dataPage = busiMeasureMiddlePayCertificateTemplateDao.getAllBusiMeasureMiddlePayCertificateTemplate(queryPage);
		DataPage<BusiMeasureMiddlePayCertificateTemplateDto> datPageDto = DTOUtils.mapPage(dataPage, BusiMeasureMiddlePayCertificateTemplateDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureMiddlePayCertificateTemplateDto> getAllBusiMeasureMiddlePayCertificateTemplateDto(Long orgId) {
		List<BusiMeasureMiddlePayCertificateTemplate> entityList = busiMeasureMiddlePayCertificateTemplateDao.findAllBusiMeasureMiddlePayCertificateTemplate(orgId);
		List<BusiMeasureMiddlePayCertificateTemplateDto> dtos = DTOUtils.mapList(entityList, BusiMeasureMiddlePayCertificateTemplateDto.class);
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
	* @param    busiMeasureMiddlePayCertificateTemplateDto
	* @param    busiMeasureMiddlePayCertificateTemplate
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureMiddlePayCertificateTemplateDto entityDto, BusiMeasureMiddlePayCertificateTemplate entity) {

	}
}
