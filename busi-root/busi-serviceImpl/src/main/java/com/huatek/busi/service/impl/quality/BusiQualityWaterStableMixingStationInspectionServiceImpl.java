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
import com.huatek.busi.dao.quality.BusiQualityCementMixingStationExceedDao;
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dao.quality.BusiQualityWaterStableMixingStationExceedDao;
import com.huatek.busi.dao.quality.BusiQualityWaterStableMixingStationInspectionDao;
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityWaterStableMixingStationInspectionDto;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationExceed;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.busi.model.quality.BusiQualityWaterStableMixingStationExceed;
import com.huatek.busi.model.quality.BusiQualityWaterStableMixingStationInspection;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationExceedService;
import com.huatek.busi.service.quality.BusiQualityWaterStableMixingStationExceedService;
import com.huatek.busi.service.quality.BusiQualityWaterStableMixingStationInspectionService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityWaterStableMixingStationInspectionServiceImpl")
@Transactional
public class BusiQualityWaterStableMixingStationInspectionServiceImpl implements BusiQualityWaterStableMixingStationInspectionService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityWaterStableMixingStationInspectionServiceImpl.class);
	
	@Autowired
	BusiQualityWaterStableMixingStationInspectionDao busiQualityWaterStableMixingStationInspectionDao;
	
	@Autowired
	BusiQualityWaterStableMixingStationExceedService busiQualityWaterStableMixingStationExceedService;
	
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	BusiQualityWaterStableMixingStationExceedDao busiQualityWaterStableMixingStationExceedDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualityWaterStableMixingStationInspectionDto(BusiQualityWaterStableMixingStationInspectionDto entityDto)  {
		log.debug("save busiQualityWaterStableMixingStationInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityWaterStableMixingStationInspection entity = BeanCopy.getInstance().convert(entityDto, BusiQualityWaterStableMixingStationInspection.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityWaterStableMixingStationInspectionDao.persistentBusiQualityWaterStableMixingStationInspection(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityWaterStableMixingStationInspectionDto getBusiQualityWaterStableMixingStationInspectionDtoById(Long id) {
		log.debug("get busiQualityWaterStableMixingStationInspection by id@" + id);
		BusiQualityWaterStableMixingStationInspection entity = busiQualityWaterStableMixingStationInspectionDao.findBusiQualityWaterStableMixingStationInspectionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityWaterStableMixingStationInspectionDto entityDto = BeanCopy.getInstance().addFieldMap("busiQualityWaterStableMixingStationExceed", "busiQualityWaterStableMixingStationExceed")//
						.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
						.convert(entity, BusiQualityWaterStableMixingStationInspectionDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityWaterStableMixingStationInspection(Long id, BusiQualityWaterStableMixingStationInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityWaterStableMixingStationInspection entity = busiQualityWaterStableMixingStationInspectionDao.findBusiQualityWaterStableMixingStationInspectionById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityWaterStableMixingStationInspectionDao.persistentBusiQualityWaterStableMixingStationInspection(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityWaterStableMixingStationInspection(Long id) {
		log.debug("delete busiQualityWaterStableMixingStationInspection by id@" + id);
		beforeRemove(id);
		BusiQualityWaterStableMixingStationInspection entity = busiQualityWaterStableMixingStationInspectionDao.findBusiQualityWaterStableMixingStationInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityWaterStableMixingStationInspectionDao.deleteBusiQualityWaterStableMixingStationInspection(entity);
	}
	
	@Override
	public DataPage<BusiQualityWaterStableMixingStationInspectionDto> getAllBusiQualityWaterStableMixingStationInspectionPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityWaterStableMixingStationInspection> dataPage = busiQualityWaterStableMixingStationInspectionDao.getAllBusiQualityWaterStableMixingStationInspection(queryPage);

		return BeanCopy.getInstance().addFieldMap("busiQualityWaterStableMixingStationExceed", "busiQualityWaterStableMixingStationExceed").addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName").convertPage(dataPage, BusiQualityWaterStableMixingStationInspectionDto.class);
		
		
		
	}
	
	@Override
	public List<BusiQualityWaterStableMixingStationInspectionDto> getAllBusiQualityWaterStableMixingStationInspectionDto() {
		List<BusiQualityWaterStableMixingStationInspection> entityList = busiQualityWaterStableMixingStationInspectionDao.findAllBusiQualityWaterStableMixingStationInspection();
		List<BusiQualityWaterStableMixingStationInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityWaterStableMixingStationInspectionDto.class);
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
	* @param    busiQualityWaterStableMixingStationInspectionDto
	* @param    busiQualityWaterStableMixingStationInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityWaterStableMixingStationInspectionDto entityDto, BusiQualityWaterStableMixingStationInspection entity) {

	}


	@Override
	public List<BusiQualityWaterStableMixingStationInspectionDto> getBusiQualityWaterStableMixingStationInspectionDtoByIds(Long[] ids) {
		log.debug("get busiQualityWaterStableMixingStationInspection by ids@" + ids);
		List<BusiQualityWaterStableMixingStationInspection> entityList = busiQualityWaterStableMixingStationInspectionDao.findBusiQualityWaterStableMixingStationInspectionByIds(ids);
		
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convertList(entityList, BusiQualityWaterStableMixingStationInspectionDto.class);
	}


	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto, Long... ids) {
		dto.setDataSource("water_stable_mixing_station");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityWaterStableMixingStationInspection> list = new ArrayList<BusiQualityWaterStableMixingStationInspection>();
		for(Long id : ids){
			BusiQualityWaterStableMixingStationInspection obj = busiQualityWaterStableMixingStationInspectionDao.findBusiQualityWaterStableMixingStationInspectionById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
//			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			//超标数据
			BusiQualityWaterStableMixingStationExceed cb = obj.getBusiQualityWaterStableMixingStationExceed();
			cb.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			cb.setBusiQualityWaterStableMixingStationInspection(obj);;
			obj.setBusiQualityWaterStableMixingStationExceed(cb);;
			list.add(obj);
		}
		busiQualityWaterStableMixingStationInspectionDao.batchUpdate(list);
		
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto, Long... ids) {

		dto.setDataSource("water_stable_mixing_station");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualityWaterStableMixingStationInspection> list = new ArrayList<BusiQualityWaterStableMixingStationInspection>();
		for(Long id : ids){
			BusiQualityWaterStableMixingStationInspection obj = busiQualityWaterStableMixingStationInspectionDao.findBusiQualityWaterStableMixingStationInspectionById(id);
			
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
//			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setInspectionId(dto.getId());
			//	超标数据
			BusiQualityWaterStableMixingStationExceed cb = obj.getBusiQualityWaterStableMixingStationExceed();
			cb.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			cb.setBusiQualityWaterStableMixingStationInspection(obj);;
			obj.setBusiQualityWaterStableMixingStationExceed(cb);;
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.getBusiQualityWaterStableMixingStationExceed().setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				obj.getBusiQualityWaterStableMixingStationExceed().setDisposeDate(new Date());
			}
			list.add(obj);
			
		}
		busiQualityWaterStableMixingStationInspectionDao.batchUpdate(list);
	
		
	
		
	}


	@Override
	public void afterFlowEndChangeWaterStableMixingStationStatus(String inspectionCode, int result) {
		List<BusiQualityWaterStableMixingStationInspection> waterStableMixingStationInspections = busiQualityWaterStableMixingStationInspectionDao//
				.findBusiQualityWaterStableMixingStationInspectionByCondition(inspectionCode,"inspectionCode");
		if(waterStableMixingStationInspections!=null){
			for(BusiQualityWaterStableMixingStationInspection waterStableMixingStationInspection : waterStableMixingStationInspections){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/	
				waterStableMixingStationInspection.getBusiQualityWaterStableMixingStationExceed().setDisposeState(String.valueOf(result));
				waterStableMixingStationInspection.getBusiQualityWaterStableMixingStationExceed().setDisposeDate(new Date());
				busiQualityWaterStableMixingStationInspectionDao.saveOrUpdateBusiQualityWaterStableMixingStationInspection(waterStableMixingStationInspection);;
			}
		}
		
	}


	@Override
	public List<BusiQualityWaterStableMixingStationInspectionDto> getBusiQualityWaterStableMixingStationByReCode(String inspectionCode) {
		List<BusiQualityWaterStableMixingStationInspection> waterStableMixingStations = busiQualityWaterStableMixingStationInspectionDao//
				.findBusiQualityWaterStableMixingStationInspectionByCondition(inspectionCode,"inspectionCode");
        return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.convertList(waterStableMixingStations, BusiQualityWaterStableMixingStationInspectionDto.class);
	}
}
