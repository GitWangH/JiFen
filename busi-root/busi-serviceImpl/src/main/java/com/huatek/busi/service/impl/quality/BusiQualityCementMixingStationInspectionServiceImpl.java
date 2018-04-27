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
import com.huatek.busi.dao.quality.BusiQualityCementMixingStationInspectionDao;
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationExceed;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationExceedService;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationInspectionService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityCementMixingStationInspectionServiceImpl")
@Transactional
public class BusiQualityCementMixingStationInspectionServiceImpl implements BusiQualityCementMixingStationInspectionService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityCementMixingStationInspectionServiceImpl.class);
	
	@Autowired
	BusiQualityCementMixingStationInspectionDao busiQualityCementMixingStationInspectionDao;
	
	@Autowired
	BusiQualityCementMixingStationExceedService busiQualityCementMixingStationExceedService;
	
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	BusiQualityCementMixingStationExceedDao busiQualityCementMixingStationExceedDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	
	@Override
	public void saveBusiQualityCementMixingStationInspectionDto(BusiQualityCementMixingStationInspectionDto entityDto)  {
		log.debug("save busiQualityCementMixingStationInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityCementMixingStationInspection entity = BeanCopy.getInstance().convert(entityDto, BusiQualityCementMixingStationInspection.class);
		BusiFwOrg org = new BusiFwOrg();
		org.setId(entityDto.getOrgId());
		entity.setOrg(org);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityCementMixingStationInspectionDao.persistentBusiQualityCementMixingStationInspection(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityCementMixingStationInspectionDto getBusiQualityCementMixingStationInspectionDtoById(Long id) {
		log.debug("get busiQualityCementMixingStationInspection by id@" + id);
		BusiQualityCementMixingStationInspection entity = busiQualityCementMixingStationInspectionDao.findBusiQualityCementMixingStationInspectionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualityCementMixingStationInspectionDto entityDto = BeanCopy.getInstance()//
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.addFieldMap("busiQualityCementMixingStationExceed", "busiQualityCementMixingStationExceed")//
				.convert(entity, BusiQualityCementMixingStationInspectionDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityCementMixingStationInspection(Long id, BusiQualityCementMixingStationInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityCementMixingStationInspection entity = busiQualityCementMixingStationInspectionDao.findBusiQualityCementMixingStationInspectionById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityCementMixingStationInspectionDao.persistentBusiQualityCementMixingStationInspection(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityCementMixingStationInspection(Long id) {
		log.debug("delete busiQualityCementMixingStationInspection by id@" + id);
		beforeRemove(id);
		BusiQualityCementMixingStationInspection entity = busiQualityCementMixingStationInspectionDao.findBusiQualityCementMixingStationInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityCementMixingStationInspectionDao.deleteBusiQualityCementMixingStationInspection(entity);
	}
	
	@Override
	public DataPage<BusiQualityCementMixingStationInspectionDto> getAllBusiQualityCementMixingStationInspectionPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityCementMixingStationInspection> dataPage = busiQualityCementMixingStationInspectionDao.getAllBusiQualityCementMixingStationInspection(queryPage);
		return BeanCopy.getInstance().addFieldMap("busiQualityCementMixingStationExceed", "busiQualityCementMixingStationExceed").addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName").convertPage(dataPage, BusiQualityCementMixingStationInspectionDto.class);
		
		
	}
	
	@Override
	public List<BusiQualityCementMixingStationInspectionDto> getAllBusiQualityCementMixingStationInspectionDto() {
		List<BusiQualityCementMixingStationInspection> entityList = busiQualityCementMixingStationInspectionDao.findAllBusiQualityCementMixingStationInspection();
		List<BusiQualityCementMixingStationInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityCementMixingStationInspectionDto.class);
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
	* @param    busiQualityCementMixingStationInspectionDto
	* @param    busiQualityCementMixingStationInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityCementMixingStationInspectionDto entityDto, BusiQualityCementMixingStationInspection entity) {

	}

	
	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto,Long... ids) {
		dto.setDataSource("cement_mixing_station_inspection");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityCementMixingStationInspection> list = new ArrayList<BusiQualityCementMixingStationInspection>();
		for(Long id : ids){
			BusiQualityCementMixingStationInspection obj = busiQualityCementMixingStationInspectionDao.findBusiQualityCementMixingStationInspectionById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
//			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			//超标数据
		    BusiQualityCementMixingStationExceed cb = obj.getBusiQualityCementMixingStationExceed();
			cb.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			cb.setBusiQualityCementMixingStationInspection(obj);
			obj.setBusiQualityCementMixingStationExceed(cb);
			list.add(obj);
		}
		busiQualityCementMixingStationInspectionDao.batchUpdate(list);
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto, Long... ids) {
		dto.setDataSource("cement_mixing_station_inspection");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualityCementMixingStationInspection> list = new ArrayList<BusiQualityCementMixingStationInspection>();
		for(Long id : ids){
			BusiQualityCementMixingStationInspection obj = busiQualityCementMixingStationInspectionDao.findBusiQualityCementMixingStationInspectionById(id);
			
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
//			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setInspectionId(dto.getId());
			//	超标数据
			BusiQualityCementMixingStationExceed cb = obj.getBusiQualityCementMixingStationExceed();
			cb.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			cb.setBusiQualityCementMixingStationInspection(obj);
			obj.setBusiQualityCementMixingStationExceed(cb);
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.getBusiQualityCementMixingStationExceed().setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				obj.getBusiQualityCementMixingStationExceed().setDisposeTime(sdf.format(new Date()));
			}
			list.add(obj);
			
		}
		busiQualityCementMixingStationInspectionDao.batchUpdate(list);
	
		
	}


	@Override
	public List<BusiQualityCementMixingStationInspectionDto> getBusiQualityCementMixingStationInspectionDtoByIds(Long[] ids) {
		log.debug("get busiQualityCementMixingStationInspection by ids@" + ids);
		List<BusiQualityCementMixingStationInspection> entityList = busiQualityCementMixingStationInspectionDao.findBusiQualityCementMixingStationInspectionByIds(ids);
		
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convertList(entityList, BusiQualityCementMixingStationInspectionDto.class);
		
	}


	@Override
	public void afterFlowEndChangeCementMixingStationStatus(String inspectionCode, int result) {
		List<BusiQualityCementMixingStationInspection> cementMixingStationInspections = busiQualityCementMixingStationInspectionDao//
				.findBusiQualityCementMixingStationInspectionByCondition(inspectionCode,"inspectionCode");
		if(cementMixingStationInspections!=null){
			for(BusiQualityCementMixingStationInspection cementMixingStationInspection : cementMixingStationInspections){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				cementMixingStationInspection.getBusiQualityCementMixingStationExceed().setDisposeState(String.valueOf(result));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cementMixingStationInspection.getBusiQualityCementMixingStationExceed().setDisposeTime(sdf.format(new Date()));
				busiQualityCementMixingStationInspectionDao.saveOrUpdateBusiQualityCementMixingStationInspection(cementMixingStationInspection);
			}
		}
		
	}


	@Override
	public List<BusiQualityCementMixingStationInspectionDto> getBusiQualityCementMixingStationByReCode(String inspectionCode) {
		List<BusiQualityCementMixingStationInspection> cementMixingStations = busiQualityCementMixingStationInspectionDao//
				.findBusiQualityCementMixingStationInspectionByCondition(inspectionCode,"inspectionCode");
        return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.convertList(cementMixingStations, BusiQualityCementMixingStationInspectionDto.class);
	}

}
