package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLog;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPrimarySupportClearanceSectionCheckModifyLogServiceImpl")
@Transactional
public class BusiQualityPrimarySupportClearanceSectionCheckModifyLogServiceImpl implements BusiQualityPrimarySupportClearanceSectionCheckModifyLogService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportClearanceSectionCheckModifyLogServiceImpl.class);
	
	@Autowired
	BusiQualityPrimarySupportClearanceSectionCheckModifyLogDao busiQualityPrimarySupportClearanceSectionCheckModifyLogDao;
	@Autowired
	private OperationService operationService;
	
	@Override
	public void saveBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto(BusiQualityPrimarySupportClearanceSectionCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportClearanceSectionCheckModifyLogDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addIgnoreField("id").addFieldMap("orgId", "org").addFieldMap("id", "busiQualityPrimarySupportClearanceSectionCheck")//
				.convert(entityDto, BusiQualityPrimarySupportClearanceSectionCheckModifyLog.class);
		//进行持久化保存
		busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.persistentBusiQualityPrimarySupportClearanceSectionCheckModifyLog(entity);
		operationService.logOperation("初期支护净空断面检测修改记录数据添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto getBusiQualityPrimarySupportClearanceSectionCheckModifyLogDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportClearanceSectionCheckModifyLog by id@" + id);
		BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity = busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.findBusiQualityPrimarySupportClearanceSectionCheckModifyLogById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportClearanceSectionCheckModifyLog(Long id, BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity = busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.findBusiQualityPrimarySupportClearanceSectionCheckModifyLogById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.persistentBusiQualityPrimarySupportClearanceSectionCheckModifyLog(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportClearanceSectionCheckModifyLog(Long id) {
		log.debug("delete busiQualityPrimarySupportClearanceSectionCheckModifyLog by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity = busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.findBusiQualityPrimarySupportClearanceSectionCheckModifyLogById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.deleteBusiQualityPrimarySupportClearanceSectionCheckModifyLog(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto> getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogPage(QueryPage queryPage) {
		DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLog> dataPage = busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog(queryPage);
		DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto> datPageDto = BeanCopy.getInstance()//
				.addConvertParam(ConvertParam.dateFormatPatten,"yyyy-MM-dd HH:mm:ss")//
				.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")//
				.convertPage(dataPage, BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto> getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto() {
		List<BusiQualityPrimarySupportClearanceSectionCheckModifyLog> entityList = busiQualityPrimarySupportClearanceSectionCheckModifyLogDao.findAllBusiQualityPrimarySupportClearanceSectionCheckModifyLog();
		List<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto.class);
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
	* @param    busiQualityPrimarySupportClearanceSectionCheckModifyLogDto
	* @param    busiQualityPrimarySupportClearanceSectionCheckModifyLog
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto entityDto, BusiQualityPrimarySupportClearanceSectionCheckModifyLog entity) {

	}

}
