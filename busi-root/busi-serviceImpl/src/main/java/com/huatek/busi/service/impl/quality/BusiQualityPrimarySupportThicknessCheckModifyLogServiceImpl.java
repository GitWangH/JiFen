package com.huatek.busi.service.impl.quality;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportThicknessCheckModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckModifyLogDto;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLog;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportThicknessCheckModifyLog;
import com.huatek.busi.service.quality.BusiQualityPrimarySupportThicknessCheckModifyLogService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * 初期支护厚度检测修改日志service服务实现.
 * @author rocky_wei
 *
 */
@Service("busiQualityPrimarySupportThicknessCheckModifyLogServiceImpl")
@Transactional
public class BusiQualityPrimarySupportThicknessCheckModifyLogServiceImpl implements BusiQualityPrimarySupportThicknessCheckModifyLogService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrimarySupportThicknessCheckModifyLogServiceImpl.class);
	
	@Autowired
	BusiQualityPrimarySupportThicknessCheckModifyLogDao busiQualityPrimarySupportThicknessCheckModifyLogDao;
	@Autowired
	private OperationService operationService;
	
	@Override
	public void saveBusiQualityPrimarySupportThicknessCheckModifyLogDto(BusiQualityPrimarySupportThicknessCheckDto entityDto)  {
		log.debug("save busiQualityPrimarySupportThicknessCheckModifyLogDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrimarySupportThicknessCheckModifyLog entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
							.addIgnoreField("id").addFieldMap("orgId", "org").addFieldMap("id", "primarySupportThicknessCheck")//
							.convert(entityDto, BusiQualityPrimarySupportThicknessCheckModifyLog.class);
		//进行持久化保存
		busiQualityPrimarySupportThicknessCheckModifyLogDao.persistentBusiQualityPrimarySupportThicknessCheckModifyLog(entity);
		operationService.logOperation("初期支护厚度检检测修改记录数据添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrimarySupportThicknessCheckModifyLogDto getBusiQualityPrimarySupportThicknessCheckModifyLogDtoById(Long id) {
		log.debug("get busiQualityPrimarySupportThicknessCheckModifyLog by id@" + id);
		BusiQualityPrimarySupportThicknessCheckModifyLog entity = busiQualityPrimarySupportThicknessCheckModifyLogDao.findBusiQualityPrimarySupportThicknessCheckModifyLogById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityPrimarySupportThicknessCheckModifyLogDto entityDto = DTOUtils.map(entity, BusiQualityPrimarySupportThicknessCheckModifyLogDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrimarySupportThicknessCheckModifyLog(Long id, BusiQualityPrimarySupportThicknessCheckModifyLogDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPrimarySupportThicknessCheckModifyLog entity = busiQualityPrimarySupportThicknessCheckModifyLogDao.findBusiQualityPrimarySupportThicknessCheckModifyLogById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiQualityPrimarySupportThicknessCheckModifyLogDao.persistentBusiQualityPrimarySupportThicknessCheckModifyLog(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrimarySupportThicknessCheckModifyLog(Long id) {
		log.debug("delete busiQualityPrimarySupportThicknessCheckModifyLog by id@" + id);
		beforeRemove(id);
		BusiQualityPrimarySupportThicknessCheckModifyLog entity = busiQualityPrimarySupportThicknessCheckModifyLogDao.findBusiQualityPrimarySupportThicknessCheckModifyLogById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrimarySupportThicknessCheckModifyLogDao.deleteBusiQualityPrimarySupportThicknessCheckModifyLog(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrimarySupportThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportThicknessCheckModifyLogPage(QueryPage queryPage) {
		DataPage<BusiQualityPrimarySupportThicknessCheckModifyLog> dataPage = busiQualityPrimarySupportThicknessCheckModifyLogDao.getAllBusiQualityPrimarySupportThicknessCheckModifyLog(queryPage);
		DataPage<BusiQualityPrimarySupportThicknessCheckModifyLogDto> datPageDto = BeanCopy.getInstance()//
						.addConvertParam(ConvertParam.dateFormatPatten,"yyyy-MM-dd HH:mm:ss")//
						.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")////
						.convertPage(dataPage, BusiQualityPrimarySupportThicknessCheckModifyLogDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityPrimarySupportThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportThicknessCheckModifyLogDto() {
		List<BusiQualityPrimarySupportThicknessCheckModifyLog> entityList = busiQualityPrimarySupportThicknessCheckModifyLogDao.findAllBusiQualityPrimarySupportThicknessCheckModifyLog();
		List<BusiQualityPrimarySupportThicknessCheckModifyLogDto> dtos = DTOUtils.mapList(entityList, BusiQualityPrimarySupportThicknessCheckModifyLogDto.class);
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
	* @param    busiQualityPrimarySupportThicknessCheckModifyLogDto
	* @param    busiQualityPrimarySupportThicknessCheckModifyLog
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrimarySupportThicknessCheckModifyLogDto entityDto, BusiQualityPrimarySupportThicknessCheckModifyLog entity) {

	}
}
