package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualitySecondLiningThicknessCheckDao;
import com.huatek.busi.dao.quality.BusiQualitySecondLiningThicknessCheckModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckDto;
import com.huatek.busi.model.quality.BusiQualitySecondLiningThicknessCheck;
import com.huatek.busi.service.quality.BusiQualitySecondLiningThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualitySecondLiningThicknessCheckService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualitySecondLiningThicknessCheckServiceImpl")
@Transactional
public class BusiQualitySecondLiningThicknessCheckServiceImpl implements BusiQualitySecondLiningThicknessCheckService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySecondLiningThicknessCheckServiceImpl.class);
	
	@Autowired
	private BusiQualitySecondLiningThicknessCheckDao busiQualitySecondLiningThicknessCheckDao;
	@Autowired
	private BusiQualitySecondLiningThicknessCheckModifyLogService secondLiningThicknessCheckModifyLogService;
	@Autowired
	private OperationService oprationService;
	
	@Override
	public void saveBusiQualitySecondLiningThicknessCheckDto(BusiQualitySecondLiningThicknessCheckDto entityDto)  {
		log.debug("save busiQualitySecondLiningThicknessCheckDto @" + entityDto);
		
		//根据页面传进来的值设置保存的值信息
		BusiQualitySecondLiningThicknessCheck entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("orgId", "org").convert(entityDto, BusiQualitySecondLiningThicknessCheck.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		entity.setIsEdited("N");
		//进行持久化保存
		busiQualitySecondLiningThicknessCheckDao.persistentBusiQualitySecondLiningThicknessCheck(entity);;
		oprationService.logOperation("二衬厚度检测添加");
		//进行持久化保存
		entityDto.setId(entity.getId());
		//修改日志表中保存原始数据
		secondLiningThicknessCheckModifyLogService.saveBusiQualitySecondLiningThicknessCheckModifyLogDto(entityDto);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualitySecondLiningThicknessCheckDto getBusiQualitySecondLiningThicknessCheckDtoById(Long id) {
		log.debug("get busiQualitySecondLiningThicknessCheck by id@" + id);
		BusiQualitySecondLiningThicknessCheck entity = busiQualitySecondLiningThicknessCheckDao.findBusiQualitySecondLiningThicknessCheckById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualitySecondLiningThicknessCheckDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.convert(entity,BusiQualitySecondLiningThicknessCheckDto.class);	
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySecondLiningThicknessCheck(Long id, BusiQualitySecondLiningThicknessCheckDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		
		BusiQualitySecondLiningThicknessCheck entity = busiQualitySecondLiningThicknessCheckDao.findBusiQualitySecondLiningThicknessCheckById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		beforeSave(entityDto, entity);
		//进行持久化保存
		entity.setIsEdited("Y");
		busiQualitySecondLiningThicknessCheckDao.persistentBusiQualitySecondLiningThicknessCheck(entity);
		entityDto.setId(id);
		oprationService.logOperation("二衬厚度检测修改");
		//保存修改后的数据
		secondLiningThicknessCheckModifyLogService.saveBusiQualitySecondLiningThicknessCheckModifyLogDto(entityDto);
	}
	
	
	
	@Override
	public void deleteBusiQualitySecondLiningThicknessCheck(Long id) {
		log.debug("delete busiQualitySecondLiningThicknessCheck by id@" + id);
		beforeRemove(id);
		BusiQualitySecondLiningThicknessCheck entity = busiQualitySecondLiningThicknessCheckDao.findBusiQualitySecondLiningThicknessCheckById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySecondLiningThicknessCheckDao.deleteBusiQualitySecondLiningThicknessCheck(entity);
	}
	
	@Override
	public DataPage<BusiQualitySecondLiningThicknessCheckDto> getAllBusiQualitySecondLiningThicknessCheckPage(QueryPage queryPage) {
		/*QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);*/
		DataPage<BusiQualitySecondLiningThicknessCheck> dataPage = busiQualitySecondLiningThicknessCheckDao.getAllBusiQualitySecondLiningThicknessCheck(queryPage);
		DataPage<BusiQualitySecondLiningThicknessCheckDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
																	.addFieldMap("org.name", "orgName")//
																	.convertPage(dataPage, BusiQualitySecondLiningThicknessCheckDto.class);
		
		return datPageDto;
		
	}
	
	@Override
	public List<BusiQualitySecondLiningThicknessCheckDto> getAllBusiQualitySecondLiningThicknessCheckDto() {
		List<BusiQualitySecondLiningThicknessCheck> entityList = busiQualitySecondLiningThicknessCheckDao.findAllBusiQualitySecondLiningThicknessCheck();
		List<BusiQualitySecondLiningThicknessCheckDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualitySecondLiningThicknessCheckDto.class);
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
	* @param    busiQualitySecondLiningThicknessCheckDto
	* @param    busiQualitySecondLiningThicknessCheck
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySecondLiningThicknessCheckDto entityDto, BusiQualitySecondLiningThicknessCheck entity) {

	}
}
