package com.huatek.busi.service.impl.measure;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.measure.BusiMeasureMiddlePayCertificateDao;
import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateDto;
import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificate;
import com.huatek.busi.service.measure.BusiMeasureMiddlePayCertificateService;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiMeasureMiddlePayCertificateServiceImpl")
@Transactional
public class BusiMeasureMiddlePayCertificateServiceImpl implements BusiMeasureMiddlePayCertificateService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiMeasureMiddlePayCertificateServiceImpl.class);
	
	@Autowired
	BusiMeasureMiddlePayCertificateDao busiMeasureMiddlePayCertificateDao;
	
	@Override
	public void saveBusiMeasureMiddlePayCertificateDto(BusiMeasureMiddlePayCertificateDto entityDto)  {
		log.debug("save busiMeasureMiddlePayCertificateDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiMeasureMiddlePayCertificate entity = DTOUtils.map(entityDto, BusiMeasureMiddlePayCertificate.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddlePayCertificateDao.persistentBusiMeasureMiddlePayCertificate(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiMeasureMiddlePayCertificateDto getBusiMeasureMiddlePayCertificateDtoById(Long id) {
		log.debug("get busiMeasureMiddlePayCertificate by id@" + id);
		BusiMeasureMiddlePayCertificate entity = busiMeasureMiddlePayCertificateDao.findBusiMeasureMiddlePayCertificateById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureMiddlePayCertificateDto entityDto = DTOUtils.map(entity, BusiMeasureMiddlePayCertificateDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureMiddlePayCertificate(Long id, BusiMeasureMiddlePayCertificateDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiMeasureMiddlePayCertificate entity = busiMeasureMiddlePayCertificateDao.findBusiMeasureMiddlePayCertificateById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiMeasureMiddlePayCertificateDao.persistentBusiMeasureMiddlePayCertificate(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureMiddlePayCertificate(Long id) {
		log.debug("delete busiMeasureMiddlePayCertificate by id@" + id);
		beforeRemove(id);
		BusiMeasureMiddlePayCertificate entity = busiMeasureMiddlePayCertificateDao.findBusiMeasureMiddlePayCertificateById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiMeasureMiddlePayCertificateDao.deleteBusiMeasureMiddlePayCertificate(entity);
	}
	
	@Override
	public DataPage<BusiMeasureMiddlePayCertificateDto> getAllBusiMeasureMiddlePayCertificatePage(QueryPage queryPage) {
		DataPage<BusiMeasureMiddlePayCertificate> dataPage = busiMeasureMiddlePayCertificateDao.getAllBusiMeasureMiddlePayCertificate(queryPage);
		DataPage<BusiMeasureMiddlePayCertificateDto> datPageDto = DTOUtils.mapPage(dataPage, BusiMeasureMiddlePayCertificateDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureMiddlePayCertificateDto> getAllBusiMeasureMiddlePayCertificateDto() {
		List<BusiMeasureMiddlePayCertificate> entityList = busiMeasureMiddlePayCertificateDao.findAllBusiMeasureMiddlePayCertificate();
		List<BusiMeasureMiddlePayCertificateDto> dtos = DTOUtils.mapList(entityList, BusiMeasureMiddlePayCertificateDto.class);
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
	* @param    busiMeasureMiddlePayCertificateDto
	* @param    busiMeasureMiddlePayCertificate
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureMiddlePayCertificateDto entityDto, BusiMeasureMiddlePayCertificate entity) {

	}
}
