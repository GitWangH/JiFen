package com.huatek.busi.service.impl.quality;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huatek.busi.dao.quality.BusiQualitySecondLiningThicknessCheckModifyLogDao;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckModifyLogDto;
import com.huatek.busi.model.quality.BusiQualitySecondLiningThicknessCheckModifyLog;
import com.huatek.busi.service.quality.BusiQualitySecondLiningThicknessCheckModifyLogService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualitySecondLiningThicknessCheckModifyLogServiceImpl")
@Transactional
public class BusiQualitySecondLiningThicknessCheckModifyLogServiceImpl implements BusiQualitySecondLiningThicknessCheckModifyLogService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySecondLiningThicknessCheckModifyLogServiceImpl.class);
	
	@Autowired
	BusiQualitySecondLiningThicknessCheckModifyLogDao busiQualitySecondLiningThicknessCheckModifyLogDao;
	@Autowired
	private OperationService operationService;
	
	@Override
	public void saveBusiQualitySecondLiningThicknessCheckModifyLogDto(BusiQualitySecondLiningThicknessCheckDto entityDto)  {
		log.debug("save busiQualitySecondLiningThicknessCheckModifyLogDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualitySecondLiningThicknessCheckModifyLog entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
							.addIgnoreField("id").addFieldMap("orgId", "org").addFieldMap("id", "secondLiningThicknessCheck")//
							.convert(entityDto, BusiQualitySecondLiningThicknessCheckModifyLog.class);
		//进行持久化保存
		busiQualitySecondLiningThicknessCheckModifyLogDao.persistentBusiQualitySecondLiningThicknessCheckModifyLog(entity);
		operationService.logOperation("二衬厚度检测检测修改记录数据添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualitySecondLiningThicknessCheckModifyLogDto getBusiQualitySecondLiningThicknessCheckModifyLogDtoById(Long id) {
		log.debug("get busiQualitySecondLiningThicknessCheckModifyLog by id@" + id);
		BusiQualitySecondLiningThicknessCheckModifyLog entity = busiQualitySecondLiningThicknessCheckModifyLogDao.findBusiQualitySecondLiningThicknessCheckModifyLogById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualitySecondLiningThicknessCheckModifyLogDto entityDto = DTOUtils.map(entity, BusiQualitySecondLiningThicknessCheckModifyLogDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySecondLiningThicknessCheckModifyLog(Long id, BusiQualitySecondLiningThicknessCheckModifyLogDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualitySecondLiningThicknessCheckModifyLog entity = busiQualitySecondLiningThicknessCheckModifyLogDao.findBusiQualitySecondLiningThicknessCheckModifyLogById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiQualitySecondLiningThicknessCheckModifyLogDao.persistentBusiQualitySecondLiningThicknessCheckModifyLog(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualitySecondLiningThicknessCheckModifyLog(Long id) {
		log.debug("delete busiQualitySecondLiningThicknessCheckModifyLog by id@" + id);
		beforeRemove(id);
		BusiQualitySecondLiningThicknessCheckModifyLog entity = busiQualitySecondLiningThicknessCheckModifyLogDao.findBusiQualitySecondLiningThicknessCheckModifyLogById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySecondLiningThicknessCheckModifyLogDao.deleteBusiQualitySecondLiningThicknessCheckModifyLog(entity);
	}
	
	@Override
	public DataPage<BusiQualitySecondLiningThicknessCheckModifyLogDto> getAllBusiQualitySecondLiningThicknessCheckModifyLogPage(QueryPage queryPage) {
		DataPage<BusiQualitySecondLiningThicknessCheckModifyLog> dataPage = busiQualitySecondLiningThicknessCheckModifyLogDao.getAllBusiQualitySecondLiningThicknessCheckModifyLog(queryPage);
		DataPage<BusiQualitySecondLiningThicknessCheckModifyLogDto> datPageDto = BeanCopy.getInstance()//
						.addConvertParam(ConvertParam.dateFormatPatten,"yyyy-MM-dd HH:mm:ss")//
						.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")////
						.convertPage(dataPage, BusiQualitySecondLiningThicknessCheckModifyLogDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualitySecondLiningThicknessCheckModifyLogDto> getAllBusiQualitySecondLiningThicknessCheckModifyLogDto() {
		List<BusiQualitySecondLiningThicknessCheckModifyLog> entityList = busiQualitySecondLiningThicknessCheckModifyLogDao.findAllBusiQualitySecondLiningThicknessCheckModifyLog();
		List<BusiQualitySecondLiningThicknessCheckModifyLogDto> dtos = DTOUtils.mapList(entityList, BusiQualitySecondLiningThicknessCheckModifyLogDto.class);
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
	* @param    busiQualitySecondLiningThicknessCheckModifyLogDto
	* @param    busiQualitySecondLiningThicknessCheckModifyLog
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySecondLiningThicknessCheckModifyLogDto entityDto, BusiQualitySecondLiningThicknessCheckModifyLog entity) {

	}
}
