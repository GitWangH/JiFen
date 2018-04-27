package com.huatek.busi.service.impl.quality;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.busi.service.quality.BusiQualityRectificationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.dto.FwOrgDto;

@Service("busiQualityRectificationServiceImpl")
@Transactional
public class BusiQualityRectificationServiceImpl implements BusiQualityRectificationService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityRectificationServiceImpl.class);
	
	@Autowired
	BusiQualityRectificationDao busiQualityRectificationDao;
	@Autowired
	private FwOrgService fwOrgService;
	
	@Override
	public void saveBusiQualityRectificationDto(BusiQualityRectificationDto entityDto)  {
		log.debug("save busiQualityRectificationDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityRectification entity = DTOUtils.map(entityDto, BusiQualityRectification.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityRectificationDao.persistentBusiQualityRectification(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityRectificationDto getBusiQualityRectificationDtoById(Long id) {
		log.debug("get busiQualityRectification by id@" + id);
		BusiQualityRectification entity = busiQualityRectificationDao.findBusiQualityRectificationById(id);
		if (null == entity) {
			 return null;
		}
		FwOrgDto org = fwOrgService.getOrgById(entity.getOrg().getId());
		BusiQualityRectificationDto entityDto = BeanCopy.getInstance()//
					.addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(entity, BusiQualityRectificationDto.class);
		entityDto.setOrgName(org.getName());
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityRectification(Long id, BusiQualityRectificationDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityRectification entity = busiQualityRectificationDao.findBusiQualityRectificationById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiQualityRectificationDao.persistentBusiQualityRectification(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityRectification(Long id) {
		log.debug("delete busiQualityRectification by id@" + id);
		beforeRemove(id);
		BusiQualityRectification entity = busiQualityRectificationDao.findBusiQualityRectificationById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityRectificationDao.deleteBusiQualityRectification(entity);
	}
	
	@Override
	public DataPage<BusiQualityRectificationDto> getAllBusiQualityRectificationPage(QueryPage queryPage) {
		DataPage<BusiQualityRectification> dataPage = busiQualityRectificationDao.getAllBusiQualityRectification(queryPage);
		DataPage<BusiQualityRectificationDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName").addFieldMap("flowResult", "flowResult").convertPage(dataPage, BusiQualityRectificationDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityRectificationDto> getAllBusiQualityRectificationDto() {
		List<BusiQualityRectification> entityList = busiQualityRectificationDao.findAllBusiQualityRectification();
		List<BusiQualityRectificationDto> dtos = DTOUtils.mapList(entityList, BusiQualityRectificationDto.class);
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
	* @param    busiQualityRectificationDto
	* @param    busiQualityRectification
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityRectificationDto entityDto, BusiQualityRectification entity) {

	}


	@Override
	public BusiQualityRectificationDto getBusiQualityRectificationDtoByCode(String rectificationCode) {
		BusiQualityRectification rectification = busiQualityRectificationDao.findBusiQualityRectificationByCondition(rectificationCode);
		//获取标段名称通过标段id
		BusiQualityRectificationDto qualityrectificateDto = BeanCopy.getInstance()//
				.addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(rectification, BusiQualityRectificationDto.class);
		if(null != rectification){
			FwOrgDto org = fwOrgService.getOrgById(rectification.getOrg().getId());
			qualityrectificateDto.setOrgName(org.getName());
		}
		return qualityrectificateDto;
	}

}
