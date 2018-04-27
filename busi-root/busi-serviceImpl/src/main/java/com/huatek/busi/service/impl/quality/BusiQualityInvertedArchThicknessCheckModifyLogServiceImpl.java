package com.huatek.busi.service.impl.quality;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityInvertedArchThicknessCheckModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckModifyLogDto;
import com.huatek.busi.model.quality.BusiQualityInvertedArchThicknessCheckModifyLog;
import com.huatek.busi.service.BusiQualityInvertedArchThicknessCheckModifyLogService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityInvertedArchThicknessCheckModifyLogServiceImpl")
@Transactional
public class BusiQualityInvertedArchThicknessCheckModifyLogServiceImpl implements BusiQualityInvertedArchThicknessCheckModifyLogService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityInvertedArchThicknessCheckModifyLogServiceImpl.class);
	
	@Autowired
	BusiQualityInvertedArchThicknessCheckModifyLogDao busiQualityInvertedArchThicknessCheckModifyLogDao;
	@Autowired
	private OperationService operationService;
	
	@Override
	public void saveBusiQualityInvertedArchThicknessCheckModifyLogDto(BusiQualityInvertedArchThicknessCheckDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiQualityInvertedArchThicknessCheckModifyLog entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")
				.addIgnoreField("id").addFieldMap("orgId", "org").addFieldMap("id", "invertedArchThicknessCheckId")
				.convert(entityDto, BusiQualityInvertedArchThicknessCheckModifyLog.class);
		//保存之前操作
		operationService.logOperation("仰拱厚度检测修改记录数据添加");
		//进行持久化保存
		busiQualityInvertedArchThicknessCheckModifyLogDao.persistentBusiQualityInvertedArchThicknessCheckModifyLog(entity);
	}
	
	
	@Override
	public BusiQualityInvertedArchThicknessCheckModifyLogDto getBusiQualityInvertedArchThicknessCheckModifyLogDtoById(Long id) {
		BusiQualityInvertedArchThicknessCheckModifyLog entity = busiQualityInvertedArchThicknessCheckModifyLogDao.findBusiQualityInvertedArchThicknessCheckModifyLogById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityInvertedArchThicknessCheckModifyLogDto entityDto = BeanCopy.getInstance().convert(entity, BusiQualityInvertedArchThicknessCheckModifyLogDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityInvertedArchThicknessCheckModifyLog(Long id, BusiQualityInvertedArchThicknessCheckModifyLogDto entityDto) {
		BusiQualityInvertedArchThicknessCheckModifyLog entity = busiQualityInvertedArchThicknessCheckModifyLogDao.findBusiQualityInvertedArchThicknessCheckModifyLogById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityInvertedArchThicknessCheckModifyLogDao.persistentBusiQualityInvertedArchThicknessCheckModifyLog(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityInvertedArchThicknessCheckModifyLog(Long id) {
		beforeRemove(id);
		BusiQualityInvertedArchThicknessCheckModifyLog entity = busiQualityInvertedArchThicknessCheckModifyLogDao.findBusiQualityInvertedArchThicknessCheckModifyLogById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityInvertedArchThicknessCheckModifyLogDao.deleteBusiQualityInvertedArchThicknessCheckModifyLog(entity);
	}
	
	@Override
	public DataPage<BusiQualityInvertedArchThicknessCheckModifyLogDto> getAllBusiQualityInvertedArchThicknessCheckModifyLogPage(QueryPage queryPage) {
		DataPage<BusiQualityInvertedArchThicknessCheckModifyLog> dataPage = busiQualityInvertedArchThicknessCheckModifyLogDao.getAllBusiQualityInvertedArchThicknessCheckModifyLog(queryPage);
		DataPage<BusiQualityInvertedArchThicknessCheckModifyLogDto> datPageDto = BeanCopy.getInstance().addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId").convertPage(dataPage, BusiQualityInvertedArchThicknessCheckModifyLogDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityInvertedArchThicknessCheckModifyLogDto> getAllBusiQualityInvertedArchThicknessCheckModifyLogDto() {
		List<BusiQualityInvertedArchThicknessCheckModifyLog> entityList = busiQualityInvertedArchThicknessCheckModifyLogDao.findAllBusiQualityInvertedArchThicknessCheckModifyLog();
		List<BusiQualityInvertedArchThicknessCheckModifyLogDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityInvertedArchThicknessCheckModifyLogDto.class);
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
	* @param    busiQualityInvertedArchThicknessCheckModifyLogDto
	* @param    busiQualityInvertedArchThicknessCheckModifyLog
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityInvertedArchThicknessCheckModifyLogDto entityDto, BusiQualityInvertedArchThicknessCheckModifyLog entity) {

	}
}
