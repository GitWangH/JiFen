package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportClearanceSectionCheckDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportClearanceSectionCheck;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportClearanceSectionCheckService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPrimarySupportClearanceSectionCheckServiceImpl")
@Transactional
public class BusiQualityPrimarySupportClearanceSectionCheckServiceImpl implements BusiQualityPrimarySupportClearanceSectionCheckService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportClearanceSectionCheckServiceImpl.class);
	
	@Autowired
	BusiQualityPrimarySupportClearanceSectionCheckDao busiQualityPrimarySupportClearanceSectionCheckDao;
	
	@Autowired
	private OperationService oprationService;
	
	@Autowired
	private BusiQualityPrimarySupportClearanceSectionCheckModifyLogService busiQualityPrimarySupportClearanceSectionCheckModifyLogService;
	
	
	@Override
	public void saveBusiQualityPrimarySupportClearanceSectionCheckDto(BusiQualityPrimarySupportClearanceSectionCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportClearanceSectionCheckDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrimarySupportClearanceSectionCheck entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss")//
				.addFieldMap("orgId", "org").convert(entityDto, BusiQualityPrimarySupportClearanceSectionCheck.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		entity.setIsEdited("N");
			//进行持久化保存
			busiQualityPrimarySupportClearanceSectionCheckDao.persistentBusiQualityPrimarySupportClearanceSectionCheck(entity);
			entityDto.setId(entity.getId());
			//修改日志表中保存原始数据
			busiQualityPrimarySupportClearanceSectionCheckModifyLogService.saveBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto(entityDto);
			oprationService.logOperation("初期支护净空断面检测添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportClearanceSectionCheckDto getBusiQualityPrimarySupportClearanceSectionCheckDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportClearanceSectionCheck by id@" + id);
		BusiQualityPrimarySupportClearanceSectionCheck entity = busiQualityPrimarySupportClearanceSectionCheckDao.findBusiQualityPrimarySupportClearanceSectionCheckById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualityPrimarySupportClearanceSectionCheckDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.convert(entity,BusiQualityPrimarySupportClearanceSectionCheckDto.class);
				 
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportClearanceSectionCheck(Long id, BusiQualityPrimarySupportClearanceSectionCheckDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		
		BusiQualityPrimarySupportClearanceSectionCheck entity = busiQualityPrimarySupportClearanceSectionCheckDao.findBusiQualityPrimarySupportClearanceSectionCheckById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss").addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		beforeSave(entityDto, entity);
			//进行持久化保存
		entity.setIsEdited("Y");
			busiQualityPrimarySupportClearanceSectionCheckDao.persistentBusiQualityPrimarySupportClearanceSectionCheck(entity);
			oprationService.logOperation("初期支护拱架间距检测修改");
			entityDto.setId(id);
			//保存修改后的数据
			busiQualityPrimarySupportClearanceSectionCheckModifyLogService.saveBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto(entityDto);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportClearanceSectionCheck(Long id) {
		log.debug("delete busiQualityPrimarySupportClearanceSectionCheck by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportClearanceSectionCheck entity = busiQualityPrimarySupportClearanceSectionCheckDao.findBusiQualityPrimarySupportClearanceSectionCheckById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportClearanceSectionCheckDao.deleteBusiQualityPrimarySupportClearanceSectionCheck(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportClearanceSectionCheckDto> getAllBusiQualityPrimarySupportClearanceSectionCheckPage(QueryPage queryPage) {
		/*QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);*/
		DataPage<BusiQualityPrimarySupportClearanceSectionCheck> dataPage = busiQualityPrimarySupportClearanceSectionCheckDao.getAllBusiQualityPrimarySupportClearanceSectionCheck(queryPage);
		DataPage<BusiQualityPrimarySupportClearanceSectionCheckDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
																	.addFieldMap("org.name", "orgName")//
																	.convertPage(dataPage, BusiQualityPrimarySupportClearanceSectionCheckDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityPrimarySupportClearanceSectionCheckDto> getAllBusiQualityPrimarySupportClearanceSectionCheckDto() {
		List<BusiQualityPrimarySupportClearanceSectionCheck> entityList = busiQualityPrimarySupportClearanceSectionCheckDao.findAllBusiQualityPrimarySupportClearanceSectionCheck();
		List<BusiQualityPrimarySupportClearanceSectionCheckDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityPrimarySupportClearanceSectionCheckDto.class);
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
	* @param    busiQualityPrimarySupportClearanceSectionCheckDto
	* @param    busiQualityPrimarySupportClearanceSectionCheck
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportClearanceSectionCheckDto entityDto, BusiQualityPrimarySupportClearanceSectionCheck entity) {

	}
}
