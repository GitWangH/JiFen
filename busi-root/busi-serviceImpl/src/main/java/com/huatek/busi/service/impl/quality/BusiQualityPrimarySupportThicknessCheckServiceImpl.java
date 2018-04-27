package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportThicknessCheckDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportThicknessCheck;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportThicknessCheckService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPrimarySupportThicknessCheckServiceImpl")
@Transactional
public class BusiQualityPrimarySupportThicknessCheckServiceImpl implements BusiQualityPrimarySupportThicknessCheckService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportThicknessCheckServiceImpl.class);
	
	@Autowired
	BusiQualityPrimarySupportThicknessCheckDao busiQualityPrimarySupportThicknessCheckDao;
	
	@Autowired
	private OperationService oprationService;
	
	@Autowired
	private BusiQualityPrimarySupportThicknessCheckModifyLogService primarySupportArchSpacingCheckModifyLogService;
	
	@Override
	public void saveBusiQualityPrimarySupportThicknessCheckDto(BusiQualityPrimarySupportThicknessCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportThicknessCheckDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrimarySupportThicknessCheck entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("orgId", "org").convert(entityDto, BusiQualityPrimarySupportThicknessCheck.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		entity.setIsEdited("N");
		//进行持久化保存
		busiQualityPrimarySupportThicknessCheckDao.persistentBusiQualityPrimarySupportThicknessCheck(entity);
		entityDto.setId(entity.getId());
		//修改日志表中保存原始数据
		primarySupportArchSpacingCheckModifyLogService.saveBusiQualityPrimarySupportThicknessCheckModifyLogDto(entityDto);
		oprationService.logOperation("初期支护厚度检测添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportThicknessCheckDto getBusiQualityPrimarySupportThicknessCheckDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportThicknessCheck by id@" + id);
		BusiQualityPrimarySupportThicknessCheck entity = busiQualityPrimarySupportThicknessCheckDao.findBusiQualityPrimarySupportThicknessCheckById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiQualityPrimarySupportThicknessCheckDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.convert(entity,BusiQualityPrimarySupportThicknessCheckDto.class);		 
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportThicknessCheck(Long id, BusiQualityPrimarySupportThicknessCheckDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		
		BusiQualityPrimarySupportThicknessCheck entity = busiQualityPrimarySupportThicknessCheckDao.findBusiQualityPrimarySupportThicknessCheckById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		beforeSave(entityDto, entity);
		entity.setIsEdited("Y");
		//进行持久化保存
		busiQualityPrimarySupportThicknessCheckDao.persistentBusiQualityPrimarySupportThicknessCheck(entity);
		oprationService.logOperation("初期支护厚度检测修改");
		entityDto.setId(id);
		//保存修改后的数据
		primarySupportArchSpacingCheckModifyLogService.saveBusiQualityPrimarySupportThicknessCheckModifyLogDto(entityDto);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportThicknessCheck(Long id) {
		log.debug("delete busiQualityPrimarySupportThicknessCheck by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportThicknessCheck entity = busiQualityPrimarySupportThicknessCheckDao.findBusiQualityPrimarySupportThicknessCheckById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportThicknessCheckDao.deleteBusiQualityPrimarySupportThicknessCheck(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportThicknessCheckDto> getAllBusiQualityPrimarySupportThicknessCheckPage(QueryPage queryPage) {
		/*QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);*/
		DataPage<BusiQualityPrimarySupportThicknessCheck> dataPage = busiQualityPrimarySupportThicknessCheckDao.getAllBusiQualityPrimarySupportThicknessCheck(queryPage);
		DataPage<BusiQualityPrimarySupportThicknessCheckDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
																	.addFieldMap("org.name", "orgName")//
																	.convertPage(dataPage, BusiQualityPrimarySupportThicknessCheckDto.class);
		
		return datPageDto;
	
	}
	
	@Override
	public List<BusiQualityPrimarySupportThicknessCheckDto> getAllBusiQualityPrimarySupportThicknessCheckDto() {
		List<BusiQualityPrimarySupportThicknessCheck> entityList = busiQualityPrimarySupportThicknessCheckDao.findAllBusiQualityPrimarySupportThicknessCheck();
		List<BusiQualityPrimarySupportThicknessCheckDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityPrimarySupportThicknessCheckDto.class);
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
	* @param    busiQualityPrimarySupportThicknessCheckDto
	* @param    busiQualityPrimarySupportThicknessCheck
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportThicknessCheckDto entityDto, BusiQualityPrimarySupportThicknessCheck entity) {

	}
}
