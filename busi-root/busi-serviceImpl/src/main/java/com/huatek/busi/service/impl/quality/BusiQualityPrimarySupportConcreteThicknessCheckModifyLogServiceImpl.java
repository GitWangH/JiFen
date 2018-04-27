package com.huatek.busi.service.impl.quality;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLog;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLog;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPrimarySupportConcreteThicknessCheckModifyLogServiceImpl")
@Transactional
public class BusiQualityPrimarySupportConcreteThicknessCheckModifyLogServiceImpl implements BusiQualityPrimarySupportConcreteThicknessCheckModifyLogService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportConcreteThicknessCheckModifyLogServiceImpl.class);
	
	@Autowired
	BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDao busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao;
	@Autowired
	private OperationService operationService;
	
	@Override
	public void saveBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto(BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportConcreteThicknessCheckModifyLogDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addIgnoreField("id").addFieldMap("orgId", "org").addFieldMap("id", "busiQualityPrimarySupportConcreteThicknessCheck")//
				.convert(entityDto, BusiQualityPrimarySupportConcreteThicknessCheckModifyLog.class);
		
		//进行持久化保存
		busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.persistentBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(entity);
		operationService.logOperation("初期支护混凝土厚度检测修改记录数据添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto getBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportConcreteThicknessCheckModifyLog by id@" + id);
		BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity = busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto entityDto = DTOUtils.map(entity, BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(Long id, BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity = busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.persistentBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(Long id) {
		log.debug("delete busiQualityPrimarySupportConcreteThicknessCheckModifyLog by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity = busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.findBusiQualityPrimarySupportConcreteThicknessCheckModifyLogById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.deleteBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogPage(QueryPage queryPage) {
		DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLog> dataPage = busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(queryPage);
		DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto> datPageDto = BeanCopy.getInstance()//
				.addConvertParam(ConvertParam.dateFormatPatten,"yyyy-MM-dd HH:mm:ss")//
				.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")////
				.convertPage(dataPage, BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto() {
		List<BusiQualityPrimarySupportConcreteThicknessCheckModifyLog> entityList = busiQualityPrimarySupportConcreteThicknessCheckModifyLogDao.findAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLog();
		List<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto> dtos = DTOUtils.mapList(entityList, BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto.class);
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
	* @param    busiQualityPrimarySupportConcreteThicknessCheckModifyLogDto
	* @param    busiQualityPrimarySupportConcreteThicknessCheckModifyLog
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto entityDto, BusiQualityPrimarySupportConcreteThicknessCheckModifyLog entity) {

	}


}
