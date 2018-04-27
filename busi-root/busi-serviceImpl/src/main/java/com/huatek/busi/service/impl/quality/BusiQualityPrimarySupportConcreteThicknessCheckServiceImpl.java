package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportConcreteThicknessCheckDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportConcreteThicknessCheck;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportConcreteThicknessCheckService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPrimarySupportConcreteThicknessCheckServiceImpl")
@Transactional
public class BusiQualityPrimarySupportConcreteThicknessCheckServiceImpl implements BusiQualityPrimarySupportConcreteThicknessCheckService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportConcreteThicknessCheckServiceImpl.class);
	
	@Autowired
	BusiQualityPrimarySupportConcreteThicknessCheckDao busiQualityPrimarySupportConcreteThicknessCheckDao;
	
	@Autowired
	private OperationService oprationService;
	
	@Autowired
	private BusiQualityPrimarySupportConcreteThicknessCheckModifyLogService primarySupportConcreteThicknessCheckModifyLogService;
	
	@Override
	public void saveBusiQualityPrimarySupportConcreteThicknessCheckDto(BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportConcreteThicknessCheckDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrimarySupportConcreteThicknessCheck entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("orgId", "org").convert(entityDto, BusiQualityPrimarySupportConcreteThicknessCheck.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		entity.setIsEdited("N");
			//进行持久化保存
			busiQualityPrimarySupportConcreteThicknessCheckDao.persistentBusiQualityPrimarySupportConcreteThicknessCheck(entity);
			entityDto.setId(entity.getId());
			//修改日志表中保存原始数据
			primarySupportConcreteThicknessCheckModifyLogService.saveBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto(entityDto);
			oprationService.logOperation("初期支护混凝土厚度检测添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportConcreteThicknessCheckDto getBusiQualityPrimarySupportConcreteThicknessCheckDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportConcreteThicknessCheck by id@" + id);
		BusiQualityPrimarySupportConcreteThicknessCheck entity = busiQualityPrimarySupportConcreteThicknessCheckDao.findBusiQualityPrimarySupportConcreteThicknessCheckById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.convert(entity,BusiQualityPrimarySupportConcreteThicknessCheckDto.class);
	
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportConcreteThicknessCheck(Long id, BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPrimarySupportConcreteThicknessCheck entity = busiQualityPrimarySupportConcreteThicknessCheckDao.findBusiQualityPrimarySupportConcreteThicknessCheckById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		beforeSave(entityDto, entity);
			//进行持久化保存
		entity.setIsEdited("Y");
		busiQualityPrimarySupportConcreteThicknessCheckDao.persistentBusiQualityPrimarySupportConcreteThicknessCheck(entity);
		oprationService.logOperation("初期支护混凝土厚度检测修改");
		entityDto.setId(id);
		primarySupportConcreteThicknessCheckModifyLogService.saveBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto(entityDto);
		
		
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportConcreteThicknessCheck(Long id) {
		log.debug("delete busiQualityPrimarySupportConcreteThicknessCheck by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportConcreteThicknessCheck entity = busiQualityPrimarySupportConcreteThicknessCheckDao.findBusiQualityPrimarySupportConcreteThicknessCheckById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportConcreteThicknessCheckDao.deleteBusiQualityPrimarySupportConcreteThicknessCheck(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportConcreteThicknessCheckDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckPage(QueryPage queryPage) {
		/*QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);*/
		DataPage<BusiQualityPrimarySupportConcreteThicknessCheck> dataPage = busiQualityPrimarySupportConcreteThicknessCheckDao.getAllBusiQualityPrimarySupportConcreteThicknessCheck(queryPage);
		DataPage<BusiQualityPrimarySupportConcreteThicknessCheckDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
																	.addFieldMap("org.name", "orgName")//
																	.convertPage(dataPage, BusiQualityPrimarySupportConcreteThicknessCheckDto.class);
		
		return datPageDto;
	
	}
	
	@Override
	public List<BusiQualityPrimarySupportConcreteThicknessCheckDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckDto() {
		List<BusiQualityPrimarySupportConcreteThicknessCheck> entityList = busiQualityPrimarySupportConcreteThicknessCheckDao.findAllBusiQualityPrimarySupportConcreteThicknessCheck();
		List<BusiQualityPrimarySupportConcreteThicknessCheckDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityPrimarySupportConcreteThicknessCheckDto.class);
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
	* @param    busiQualityPrimarySupportConcreteThicknessCheckDto
	* @param    busiQualityPrimarySupportConcreteThicknessCheck
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto, BusiQualityPrimarySupportConcreteThicknessCheck entity) {

	}
}
