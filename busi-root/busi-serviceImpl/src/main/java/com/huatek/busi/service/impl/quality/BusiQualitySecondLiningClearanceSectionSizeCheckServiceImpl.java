package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualitySecondLiningClearanceSectionSizeCheckDao;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceSectionSizeCheckDto;
import com.huatek.busi.model.quality.BusiQualitySecondLiningClearanceSectionSizeCheck;
import com.huatek.busi.service.quality.BusiQualitySecondLiningClearanceModifyLogService;
import com.huatek.busi.service.quality.BusiQualitySecondLiningClearanceSectionSizeCheckService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualitySecondLiningClearanceSectionSizeCheckServiceImpl")
@Transactional
public class BusiQualitySecondLiningClearanceSectionSizeCheckServiceImpl implements BusiQualitySecondLiningClearanceSectionSizeCheckService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySecondLiningClearanceSectionSizeCheckServiceImpl.class);
	
	@Autowired
	BusiQualitySecondLiningClearanceSectionSizeCheckDao busiQualitySecondLiningClearanceSectionSizeCheckDao;
	@Autowired
	BusiQualitySecondLiningClearanceModifyLogService busiQualitySecondLiningClearanceModifyLogService;
	
	
	@Autowired
	private OperationService oprationService;
	
	@Override
	public void saveBusiQualitySecondLiningClearanceSectionSizeCheckDto(BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto)  {
		log.debug("save busiQualitySecondLiningClearanceSectionSizeCheckDto @" + entityDto);
		
		//根据页面传进来的值设置保存的值信息
		BusiQualitySecondLiningClearanceSectionSizeCheck entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("orgId", "org").convert(entityDto, BusiQualitySecondLiningClearanceSectionSizeCheck.class);
						//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		entity.setIsEdited("N");
		busiQualitySecondLiningClearanceSectionSizeCheckDao.persistentBusiQualitySecondLiningClearanceSectionSizeCheck(entity);;
		oprationService.logOperation("二衬净空断面尺寸检测添加");
		//	添加日志
		entityDto.setId(entity.getId());
		busiQualitySecondLiningClearanceModifyLogService.saveBusiQualitySecondLiningClearanceModifyLogDto(entityDto);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualitySecondLiningClearanceSectionSizeCheckDto getBusiQualitySecondLiningClearanceSectionSizeCheckDtoById(Long id) {
		log.debug("get busiQualitySecondLiningClearanceSectionSizeCheck by id@" + id);
		BusiQualitySecondLiningClearanceSectionSizeCheck entity = busiQualitySecondLiningClearanceSectionSizeCheckDao.findBusiQualitySecondLiningClearanceSectionSizeCheckById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.convert(entity,BusiQualitySecondLiningClearanceSectionSizeCheckDto.class);	
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySecondLiningClearanceSectionSizeCheck(Long id, BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		
		BusiQualitySecondLiningClearanceSectionSizeCheck entity = busiQualitySecondLiningClearanceSectionSizeCheckDao.findBusiQualitySecondLiningClearanceSectionSizeCheckById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		//进行持久化保存
		entity.setIsEdited("Y");
		busiQualitySecondLiningClearanceSectionSizeCheckDao.persistentBusiQualitySecondLiningClearanceSectionSizeCheck(entity);
		oprationService.logOperation("二衬净空断面尺寸检测修改");
		//	添加修改日志
		entityDto.setId(entity.getId());
		busiQualitySecondLiningClearanceModifyLogService.saveBusiQualitySecondLiningClearanceModifyLogDto(entityDto);
		
	}
	
	
	
	@Override
	public void deleteBusiQualitySecondLiningClearanceSectionSizeCheck(Long id) {
		log.debug("delete busiQualitySecondLiningClearanceSectionSizeCheck by id@" + id);
		beforeRemove(id);
		BusiQualitySecondLiningClearanceSectionSizeCheck entity = busiQualitySecondLiningClearanceSectionSizeCheckDao.findBusiQualitySecondLiningClearanceSectionSizeCheckById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySecondLiningClearanceSectionSizeCheckDao.deleteBusiQualitySecondLiningClearanceSectionSizeCheck(entity);
	}
	
	@Override
	public DataPage<BusiQualitySecondLiningClearanceSectionSizeCheckDto> getAllBusiQualitySecondLiningClearanceSectionSizeCheckPage(QueryPage queryPage) {
		/*QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);*/
		DataPage<BusiQualitySecondLiningClearanceSectionSizeCheck> dataPage = busiQualitySecondLiningClearanceSectionSizeCheckDao.getAllBusiQualitySecondLiningClearanceSectionSizeCheck(queryPage);
		DataPage<BusiQualitySecondLiningClearanceSectionSizeCheckDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
																	.addFieldMap("org.name", "orgName")//
																	.convertPage(dataPage, BusiQualitySecondLiningClearanceSectionSizeCheckDto.class);
		
		return datPageDto;
		
	}
	
	@Override
	public List<BusiQualitySecondLiningClearanceSectionSizeCheckDto> getAllBusiQualitySecondLiningClearanceSectionSizeCheckDto() {
		List<BusiQualitySecondLiningClearanceSectionSizeCheck> entityList = busiQualitySecondLiningClearanceSectionSizeCheckDao.findAllBusiQualitySecondLiningClearanceSectionSizeCheck();
		List<BusiQualitySecondLiningClearanceSectionSizeCheckDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualitySecondLiningClearanceSectionSizeCheckDto.class);
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
	* @param    busiQualitySecondLiningClearanceSectionSizeCheckDto
	* @param    busiQualitySecondLiningClearanceSectionSizeCheck
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto, BusiQualitySecondLiningClearanceSectionSizeCheck entity) {

	}
}
