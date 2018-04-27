package com.huatek.busi.service.impl.quality;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLog;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;
/**
 * 初期支护拱架间距检测修改记录service接口实现
 * @author rocky_wei
 *
 */
@Service("busiQualityPrimarySupportArchSpacingCheckModifyLogServiceImpl")
@Transactional
public class BusiQualityPrimarySupportArchSpacingCheckModifyLogServiceImpl implements BusiQualityPrimarySupportArchSpacingCheckModifyLogService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportArchSpacingCheckModifyLogServiceImpl.class);
	
	@Autowired
	private BusiQualityPrimarySupportArchSpacingCheckModifyLogDao busiQualityPrimarySupportArchSpacingCheckModifyLogDao;
	@Autowired
	private OperationService operationService;
	
	@Override
	public void saveBusiQualityPrimarySupportArchSpacingCheckModifyLogDto(BusiQualityPrimarySupportArchSpacingCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportArchSpacingCheckModifyLogDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrimarySupportArchSpacingCheckModifyLog entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
							.addIgnoreField("id").addFieldMap("orgId", "org").addFieldMap("id", "primarySupportClearanceSectionCheck")//
							.convert(entityDto, BusiQualityPrimarySupportArchSpacingCheckModifyLog.class);
		//进行持久化保存
		busiQualityPrimarySupportArchSpacingCheckModifyLogDao.persistentBusiQualityPrimarySupportArchSpacingCheckModifyLog(entity);
		operationService.logOperation("初期支护拱架间距检测修改记录数据添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportArchSpacingCheckModifyLogDto getBusiQualityPrimarySupportArchSpacingCheckModifyLogDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportArchSpacingCheckModifyLog by id@" + id);
		BusiQualityPrimarySupportArchSpacingCheckModifyLog entity = busiQualityPrimarySupportArchSpacingCheckModifyLogDao.findBusiQualityPrimarySupportArchSpacingCheckModifyLogById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityPrimarySupportArchSpacingCheckModifyLogDto entityDto = DTOUtils.map(entity, BusiQualityPrimarySupportArchSpacingCheckModifyLogDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportArchSpacingCheckModifyLog(Long id, BusiQualityPrimarySupportArchSpacingCheckModifyLogDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPrimarySupportArchSpacingCheckModifyLog entity = busiQualityPrimarySupportArchSpacingCheckModifyLogDao.findBusiQualityPrimarySupportArchSpacingCheckModifyLogById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiQualityPrimarySupportArchSpacingCheckModifyLogDao.persistentBusiQualityPrimarySupportArchSpacingCheckModifyLog(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportArchSpacingCheckModifyLog(Long id) {
		log.debug("delete busiQualityPrimarySupportArchSpacingCheckModifyLog by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportArchSpacingCheckModifyLog entity = busiQualityPrimarySupportArchSpacingCheckModifyLogDao.findBusiQualityPrimarySupportArchSpacingCheckModifyLogById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportArchSpacingCheckModifyLogDao.deleteBusiQualityPrimarySupportArchSpacingCheckModifyLog(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogPage(QueryPage queryPage) {
		
		DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLog> dataPage = busiQualityPrimarySupportArchSpacingCheckModifyLogDao.getAllBusiQualityPrimarySupportArchSpacingCheckModifyLog(queryPage);
		DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto> datPageDto = BeanCopy.getInstance()//
						.addConvertParam(ConvertParam.dateFormatPatten,"yyyy-MM-dd HH:mm:ss")//
						.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")////
						.convertPage(dataPage, BusiQualityPrimarySupportArchSpacingCheckModifyLogDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogDto() {
		List<BusiQualityPrimarySupportArchSpacingCheckModifyLog> entityList = busiQualityPrimarySupportArchSpacingCheckModifyLogDao.findAllBusiQualityPrimarySupportArchSpacingCheckModifyLog();
		List<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto> dtos = DTOUtils.mapList(entityList, BusiQualityPrimarySupportArchSpacingCheckModifyLogDto.class);
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
	* @param    busiQualityPrimarySupportArchSpacingCheckModifyLogDto
	* @param    busiQualityPrimarySupportArchSpacingCheckModifyLog
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportArchSpacingCheckModifyLogDto entityDto, BusiQualityPrimarySupportArchSpacingCheckModifyLog entity) {

	}
}
