package com.huatek.busi.service.impl.quality;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dao.quality.BusiQualityRoutingInspectionDao;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityRoutingInspectionDto;
import com.huatek.busi.model.quality.BusiQualityRoutingInspection;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityRoutingInspectionService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityRoutingInspectionServiceImpl")
@Transactional
public class BusiQualityRoutingInspectionServiceImpl implements BusiQualityRoutingInspectionService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityRoutingInspectionServiceImpl.class);
	
	@Autowired
	BusiQualityRoutingInspectionDao busiQualityRoutingInspectionDao;
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	@Autowired
	private RpcProxy rpcProxy;
	@Autowired
	private OperationService oprationService;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualityRoutingInspectionDto(BusiQualityRoutingInspectionDto entityDto)  {
		log.debug("save busiQualityRoutingInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityRoutingInspection entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("orgId", "org").convert(entityDto, BusiQualityRoutingInspection.class);
		entity.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_NO));
		entity.setIsDelete(0);
		//保存之前操作
		beforeSave(entityDto, entity);
//		entity.setCheckResults(checkResults);
		//进行持久化保存
		busiQualityRoutingInspectionDao.persistentBusiQualityRoutingInspection(entity);
		oprationService.logOperation("巡检维护添加");
		
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityRoutingInspectionDto getBusiQualityRoutingInspectionDtoById(Long id) {
		log.debug("get busiQualityRoutingInspection by id@" + id);
		BusiQualityRoutingInspection entity = busiQualityRoutingInspectionDao.findBusiQualityRoutingInspectionById(id);
		
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
			 
		BusiQualityRoutingInspectionDto entityDto = BeanCopy.getInstance()
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName").convert(entity, BusiQualityRoutingInspectionDto.class);
		return entityDto;
	}
	
	@Override
	public List<BusiQualityRoutingInspectionDto> getBusiQualityRoutingInspectionDtoByIds(Long[] ids) {
		log.debug("get busiQualityRoutingInspection by ids@" + ids);
		List<BusiQualityRoutingInspection> entityList = busiQualityRoutingInspectionDao.findBusiQualityRoutingInspectionByIds(ids);
		
				 
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId").convertList(entityList, BusiQualityRoutingInspectionDto.class);
		
	}
	
	@Override
	public void updateBusiQualityRoutingInspection(Long id, BusiQualityRoutingInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityRoutingInspection entity = busiQualityRoutingInspectionDao.findBusiQualityRoutingInspectionById(id);
		entityDto.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_NO));	
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("orgId", "org").mapIgnoreNull(entityDto, entity);
		//进行持久化保存
		busiQualityRoutingInspectionDao.persistentBusiQualityRoutingInspection(entity);
		oprationService.logOperation("巡检维护修改");
		
	}
	
	
	
	@Override
	public void deleteBusiQualityRoutingInspection(Long id) {
		log.debug("delete busiQualityRoutingInspection by id@" + id);
		beforeRemove(id);
		BusiQualityRoutingInspection entity = busiQualityRoutingInspectionDao.findBusiQualityRoutingInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		entity.setIsDelete(1);
		busiQualityRoutingInspectionDao.persistentBusiQualityRoutingInspection(entity);
//		busiQualityRoutingInspectionDao.deleteBusiQualityRoutingInspection(entity);
		oprationService.logOperation("巡检维护删除");
	}
	
	@Override
	public DataPage<BusiQualityRoutingInspectionDto> getAllBusiQualityRoutingInspectionPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityRoutingInspection> dataPage = busiQualityRoutingInspectionDao.getAllBusiQualityRoutingInspection(queryPage);
		
		DataPage<BusiQualityRoutingInspectionDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name", "orgName")
				.addFieldMap("org.id", "orgId")
				.convertPage(dataPage, BusiQualityRoutingInspectionDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityRoutingInspectionDto> getAllBusiQualityRoutingInspectionDto() {
		List<BusiQualityRoutingInspection> entityList = busiQualityRoutingInspectionDao.findAllBusiQualityRoutingInspection();
		List<BusiQualityRoutingInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityRoutingInspectionDto.class);
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
	* @param    busiQualityRoutingInspectionDto
	* @param    busiQualityRoutingInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityRoutingInspectionDto entityDto, BusiQualityRoutingInspection entity) {

	}


	@Override
	public List<BusiQualityRoutingInspectionDto> getQualityAllRectificateRoutingInspectionInspection(String inspectionType) {
		List<BusiQualityRoutingInspection> routingInspection = busiQualityRoutingInspectionDao//
				.findBusiQualityRoutingInspectionByCondition(Integer.valueOf(inspectionType),"inspectionType");
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
					.convertList(routingInspection, BusiQualityRoutingInspectionDto.class);
	}


	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto, Long... ids) {
		dto.setDataSource("routing_inspection");//set 接口处理类型
		baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityRoutingInspection> list = new ArrayList<BusiQualityRoutingInspection>();
		for(Long id : ids){
			BusiQualityRoutingInspection obj = busiQualityRoutingInspectionDao.findBusiQualityRoutingInspectionById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			list.add(obj);
		}
		busiQualityRoutingInspectionDao.batchUpdate(list);
		
	}

	/**
	 * 通过整改编号查询质量巡检数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	@Override
	public List<BusiQualityRoutingInspectionDto> getRoutingInspectionByReCode(String inspectionCode) {
		List<BusiQualityRoutingInspection> routingInspections = busiQualityRoutingInspectionDao//
				.findBusiQualityRoutingInspectionByCondition(inspectionCode,"inspectionCode");
    return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.convertList(routingInspections, BusiQualityRoutingInspectionDto.class);
	}


	@Override
	public void afterFlowEndChangeRoutingInspectionStatus(String inspectionCode, int result) {
		List<BusiQualityRoutingInspection> routingInspections = busiQualityRoutingInspectionDao//
				.findBusiQualityRoutingInspectionByCondition(inspectionCode,"inspectionCode");
		if(routingInspections!=null){
			for(BusiQualityRoutingInspection routingInspection : routingInspections){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				routingInspection.setDisposeState(String.valueOf(result));
				busiQualityRoutingInspectionDao.saveOrUpdateBusiQualityRoutingInspection(routingInspection);
			}
		}
		
	} 


	@Override
	public void rectificateReport(Long... ids) {
		for(Long id :ids){
			BusiQualityRoutingInspection routingInspection = busiQualityRoutingInspectionDao.findBusiQualityRoutingInspectionById(id);
			routingInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			BusiQualityRectificationDto dto = new BusiQualityRectificationDto();
			dto.setOrgId(String.valueOf(routingInspection.getOrg().getId()));
			dto.setCheckNo(routingInspection.getCheckNumber());
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dto.setRectificationCheckTime(sdf.format(routingInspection.getCheckTime()));
			dto.setRectificationPersonLiable(routingInspection.getPersonLiable());
			dto.setRectificationUrgency(routingInspection.getUrgency());
			dto.setRectificationPeriod(routingInspection.getRectificationPeriod());
			dto.setRectificationPerson(routingInspection.getCheckPerson());
			dto.setRectificationCheckContent(routingInspection.getCheckContent());
			dto.setRectificationExistingProblems(routingInspection.getQuestion());
			dto.setRectificationRequirements(routingInspection.getRectificationRequirements());
			dto.setRectificationPunishmentSuggestion(routingInspection.getPunishmentSuggestion());
			dto.setDataSource("routing_inspection");//set 接口处理类型
			dto = baseQualityRectificationService.saveQualityRectification(dto);
			routingInspection.setInspectionCode(dto.getRectificationCode());
			routingInspection.setDisposeDate(new Date());
			routingInspection.setInspectionId(dto.getId());
			routingInspection.setInspectionType(1);
			busiQualityRoutingInspectionDao.persistentBusiQualityRoutingInspection(routingInspection);
			oprationService.logOperation("巡检维护整改下发"+dto.getRectificationCode());
		}
		
	}


	@Override
	public BusiQualityRoutingInspectionDto getRoutingByRectificationId(Long rid) {
		BusiQualityRoutingInspection qualityRoutingInspection = busiQualityRoutingInspectionDao.findBusiQualityRoutingInspectionByRid(rid);
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name", "orgName")//
				.convert(qualityRoutingInspection, BusiQualityRoutingInspectionDto.class);
	}
}
