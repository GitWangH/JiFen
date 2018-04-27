package com.huatek.busi.service.impl.quality;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualitySecondLiningClearanceModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceModifyLogDto;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceSectionSizeCheckDto;
import com.huatek.busi.model.quality.BusiQualitySecondLiningClearanceModifyLog;
import com.huatek.busi.service.quality.BusiQualitySecondLiningClearanceModifyLogService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualitySecondLiningClearanceModifyLogServiceImpl")
@Transactional
public class BusiQualitySecondLiningClearanceModifyLogServiceImpl implements BusiQualitySecondLiningClearanceModifyLogService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySecondLiningClearanceModifyLogServiceImpl.class);
	
	@Autowired
	BusiQualitySecondLiningClearanceModifyLogDao busiQualitySecondLiningClearanceModifyLogDao;
	
	@Override
	public void saveBusiQualitySecondLiningClearanceModifyLogDto(BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiQualitySecondLiningClearanceModifyLog entity = BeanCopy.getInstance().addFieldMap("orgId", "org").addFieldMap("id", "secondLiningThicknessSizeCheckId").convert(entityDto, BusiQualitySecondLiningClearanceModifyLog.class);
		//保存之前操作
		//进行持久化保存
		entity.setId(null);
		busiQualitySecondLiningClearanceModifyLogDao.persistentBusiQualitySecondLiningClearanceModifyLog(entity);
	}
	
	
	@Override
	public BusiQualitySecondLiningClearanceModifyLogDto getBusiQualitySecondLiningClearanceModifyLogDtoById(Long id) {
		BusiQualitySecondLiningClearanceModifyLog entity = busiQualitySecondLiningClearanceModifyLogDao.findBusiQualitySecondLiningClearanceModifyLogById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualitySecondLiningClearanceModifyLogDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualitySecondLiningClearanceModifyLogDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySecondLiningClearanceModifyLog(Long id, BusiQualitySecondLiningClearanceModifyLogDto entityDto) {
		BusiQualitySecondLiningClearanceModifyLog entity = busiQualitySecondLiningClearanceModifyLogDao.findBusiQualitySecondLiningClearanceModifyLogById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualitySecondLiningClearanceModifyLogDao.persistentBusiQualitySecondLiningClearanceModifyLog(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualitySecondLiningClearanceModifyLog(Long id) {
		beforeRemove(id);
		BusiQualitySecondLiningClearanceModifyLog entity = busiQualitySecondLiningClearanceModifyLogDao.findBusiQualitySecondLiningClearanceModifyLogById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySecondLiningClearanceModifyLogDao.deleteBusiQualitySecondLiningClearanceModifyLog(entity);
	}
	
	@Override
	public DataPage<BusiQualitySecondLiningClearanceModifyLogDto> getAllBusiQualitySecondLiningClearanceModifyLogPage(QueryPage queryPage) {
		DataPage<BusiQualitySecondLiningClearanceModifyLog> dataPage = busiQualitySecondLiningClearanceModifyLogDao.getAllBusiQualitySecondLiningClearanceModifyLog(queryPage);
		DataPage<BusiQualitySecondLiningClearanceModifyLogDto> datPageDto = BeanCopy.getInstance().addFieldMap("org.name", "orgName").convertPage(dataPage, BusiQualitySecondLiningClearanceModifyLogDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualitySecondLiningClearanceModifyLogDto> getAllBusiQualitySecondLiningClearanceModifyLogDto() {
		List<BusiQualitySecondLiningClearanceModifyLog> entityList = busiQualitySecondLiningClearanceModifyLogDao.findAllBusiQualitySecondLiningClearanceModifyLog();
		List<BusiQualitySecondLiningClearanceModifyLogDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualitySecondLiningClearanceModifyLogDto.class);
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
	* @param    busiQualitySecondLiningClearanceModifyLogDto
	* @param    busiQualitySecondLiningClearanceModifyLog
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySecondLiningClearanceModifyLogDto entityDto, BusiQualitySecondLiningClearanceModifyLog entity) {

	}
}
