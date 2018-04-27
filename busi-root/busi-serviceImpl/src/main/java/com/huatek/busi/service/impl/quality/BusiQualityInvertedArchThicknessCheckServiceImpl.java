package com.huatek.busi.service.impl.quality;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityInvertedArchThicknessCheckDao;
import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.quality.BusiQualityInvertedArchThicknessCheck;
import com.huatek.busi.service.BusiQualityInvertedArchThicknessCheckModifyLogService;
import com.huatek.busi.service.quality.BusiQualityInvertedArchThicknessCheckService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityInvertedArchThicknessCheckServiceImpl")
@Transactional
public class BusiQualityInvertedArchThicknessCheckServiceImpl implements BusiQualityInvertedArchThicknessCheckService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityInvertedArchThicknessCheckServiceImpl.class);
	
	@Autowired
	BusiQualityInvertedArchThicknessCheckDao busiQualityInvertedArchThicknessCheckDao;
	
	@Autowired
	BusiQualityInvertedArchThicknessCheckModifyLogService busiQualityInvertedArchThicknessCheckModifyLogService;
	
	@Autowired
	private OperationService oprationService;
	
	@Override
	public void saveBusiQualityInvertedArchThicknessCheckDto(BusiQualityInvertedArchThicknessCheckDto entityDto)  {
		log.debug("save busiQualityInvertedArchThicknessCheckDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityInvertedArchThicknessCheck entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
						.convert(entityDto, BusiQualityInvertedArchThicknessCheck.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		BusiFwOrg org=new BusiFwOrg();
		org.setId(entityDto.getOrgId());
		entity.setOrg(org);
		entity.setIsEdited("N");
		busiQualityInvertedArchThicknessCheckDao.persistentBusiQualityInvertedArchThicknessCheck(entity);;
		oprationService.logOperation("仰拱厚度检测添加");
		entityDto.setId(entity.getId());
		busiQualityInvertedArchThicknessCheckDao.persistentBusiQualityInvertedArchThicknessCheck(entity);
		//	添加修改日志
		busiQualityInvertedArchThicknessCheckModifyLogService.saveBusiQualityInvertedArchThicknessCheckModifyLogDto(entityDto);
		
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityInvertedArchThicknessCheckDto getBusiQualityInvertedArchThicknessCheckDtoById(Long id) {
		log.debug("get busiQualityInvertedArchThicknessCheck by id@" + id);
		BusiQualityInvertedArchThicknessCheck entity = busiQualityInvertedArchThicknessCheckDao.findBusiQualityInvertedArchThicknessCheckById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualityInvertedArchThicknessCheckDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.convert(entity,BusiQualityInvertedArchThicknessCheckDto.class);		
				 
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityInvertedArchThicknessCheck(Long id, BusiQualityInvertedArchThicknessCheckDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		
		BusiQualityInvertedArchThicknessCheck entity = busiQualityInvertedArchThicknessCheckDao.findBusiQualityInvertedArchThicknessCheckById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("orgId", "org").mapIgnoreId(entityDto, entity);
		//进行持久化保存
		entity.setIsEdited("Y");
		entityDto.setId(id);
		busiQualityInvertedArchThicknessCheckDao.persistentBusiQualityInvertedArchThicknessCheck(entity);
		//	添加修改日志
		busiQualityInvertedArchThicknessCheckModifyLogService.saveBusiQualityInvertedArchThicknessCheckModifyLogDto(entityDto);
		oprationService.logOperation("仰拱厚度检测修改");
		
	}
	
	
	
	@Override
	public void deleteBusiQualityInvertedArchThicknessCheck(Long id) {
		log.debug("delete busiQualityInvertedArchThicknessCheck by id@" + id);
		beforeRemove(id);
		BusiQualityInvertedArchThicknessCheck entity = busiQualityInvertedArchThicknessCheckDao.findBusiQualityInvertedArchThicknessCheckById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityInvertedArchThicknessCheckDao.deleteBusiQualityInvertedArchThicknessCheck(entity);
	}
	
	@Override
	public DataPage<BusiQualityInvertedArchThicknessCheckDto> getAllBusiQualityInvertedArchThicknessCheckPage(QueryPage queryPage) {
		/*QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);*/
		DataPage<BusiQualityInvertedArchThicknessCheck> dataPage = busiQualityInvertedArchThicknessCheckDao.getAllBusiQualityInvertedArchThicknessCheck(queryPage);
		DataPage<BusiQualityInvertedArchThicknessCheckDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
																	.addFieldMap("org.name", "orgName")//
																	.convertPage(dataPage, BusiQualityInvertedArchThicknessCheckDto.class);
		
		return datPageDto;
	
	}
	
	@Override
	public List<BusiQualityInvertedArchThicknessCheckDto> getAllBusiQualityInvertedArchThicknessCheckDto() {
		List<BusiQualityInvertedArchThicknessCheck> entityList = busiQualityInvertedArchThicknessCheckDao.findAllBusiQualityInvertedArchThicknessCheck();
		List<BusiQualityInvertedArchThicknessCheckDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityInvertedArchThicknessCheckDto.class);
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
	* @param    busiQualityInvertedArchThicknessCheckDto
	* @param    busiQualityInvertedArchThicknessCheck
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityInvertedArchThicknessCheckDto entityDto, BusiQualityInvertedArchThicknessCheck entity) {

	}
}
