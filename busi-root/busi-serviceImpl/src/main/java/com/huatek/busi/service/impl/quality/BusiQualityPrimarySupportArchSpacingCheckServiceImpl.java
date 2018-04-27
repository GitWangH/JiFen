package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportArchSpacingCheckDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheck;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportArchSpacingCheckService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPrimarySupportArchSpacingCheckServiceImpl")
@Transactional
public class BusiQualityPrimarySupportArchSpacingCheckServiceImpl implements BusiQualityPrimarySupportArchSpacingCheckService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportArchSpacingCheckServiceImpl.class);
	
	@Autowired
	BusiQualityPrimarySupportArchSpacingCheckDao busiQualityPrimarySupportArchSpacingCheckDao;
	@Autowired
	private OperationService oprationService;
	@Autowired
	private BusiQualityPrimarySupportArchSpacingCheckModifyLogService primarySupportArchSpacingCheckModifyLogService;
	
	
	@Override
	public void saveBusiQualityPrimarySupportArchSpacingCheckDto(BusiQualityPrimarySupportArchSpacingCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportArchSpacingCheckDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		 BusiQualityPrimarySupportArchSpacingCheck entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss")//
				 .addFieldMap("orgId", "org").convert(entityDto, BusiQualityPrimarySupportArchSpacingCheck.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		entity.setIsEdited("N");
		//进行持久化保存
		busiQualityPrimarySupportArchSpacingCheckDao.persistentBusiQualityPrimarySupportArchSpacingCheck(entity);
		entityDto.setId(entity.getId());
		//修改日志表中保存原始数据
		primarySupportArchSpacingCheckModifyLogService.saveBusiQualityPrimarySupportArchSpacingCheckModifyLogDto(entityDto);
		oprationService.logOperation("初期支护拱架间距检测添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportArchSpacingCheckDto getBusiQualityPrimarySupportArchSpacingCheckDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportArchSpacingCheck by id@" + id);
		BusiQualityPrimarySupportArchSpacingCheck entity = busiQualityPrimarySupportArchSpacingCheckDao.findBusiQualityPrimarySupportArchSpacingCheckById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}

		BusiQualityPrimarySupportArchSpacingCheckDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.convert(entity,BusiQualityPrimarySupportArchSpacingCheckDto.class);
	
				 
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportArchSpacingCheck(Long id, BusiQualityPrimarySupportArchSpacingCheckDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPrimarySupportArchSpacingCheck entity = busiQualityPrimarySupportArchSpacingCheckDao.findBusiQualityPrimarySupportArchSpacingCheckById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss").addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		beforeSave(entityDto, entity);
		//进行持久化保存
		entity.setIsEdited("Y");
		busiQualityPrimarySupportArchSpacingCheckDao.persistentBusiQualityPrimarySupportArchSpacingCheck(entity);
		oprationService.logOperation("初期支护拱架间距检测修改");
		entityDto.setId(id);
		//保存修改后的数据
		primarySupportArchSpacingCheckModifyLogService.saveBusiQualityPrimarySupportArchSpacingCheckModifyLogDto(entityDto);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportArchSpacingCheck(Long id) {
		log.debug("delete busiQualityPrimarySupportArchSpacingCheck by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportArchSpacingCheck entity = busiQualityPrimarySupportArchSpacingCheckDao.findBusiQualityPrimarySupportArchSpacingCheckById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportArchSpacingCheckDao.deleteBusiQualityPrimarySupportArchSpacingCheck(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportArchSpacingCheckDto> getAllBusiQualityPrimarySupportArchSpacingCheckPage(QueryPage queryPage) {
	/*	QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);*/
		DataPage<BusiQualityPrimarySupportArchSpacingCheck> dataPage = busiQualityPrimarySupportArchSpacingCheckDao.getAllBusiQualityPrimarySupportArchSpacingCheck(queryPage);
		DataPage<BusiQualityPrimarySupportArchSpacingCheckDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss")//
																	.addFieldMap("org.name", "orgName")//
																	.convertPage(dataPage, BusiQualityPrimarySupportArchSpacingCheckDto.class);
		
		return datPageDto;
	
	}
	
	@Override
	public List<BusiQualityPrimarySupportArchSpacingCheckDto> getAllBusiQualityPrimarySupportArchSpacingCheckDto() {
		List<BusiQualityPrimarySupportArchSpacingCheck> entityList = busiQualityPrimarySupportArchSpacingCheckDao.findAllBusiQualityPrimarySupportArchSpacingCheck();
		List<BusiQualityPrimarySupportArchSpacingCheckDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityPrimarySupportArchSpacingCheckDto.class);
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
	* @param    busiQualityPrimarySupportArchSpacingCheckDto
	* @param    busiQualityPrimarySupportArchSpacingCheck
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportArchSpacingCheckDto entityDto, BusiQualityPrimarySupportArchSpacingCheck entity) {

	}
}
