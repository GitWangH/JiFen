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
import com.huatek.busi.dao.quality.BusiQualityAsphaltMixingPlantExceedDao;
import com.huatek.busi.dao.quality.BusiQualityAsphaltMixingPlantInspectionDao;
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dto.quality.BusiQualityAsphaltMixingPlantInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.quality.BusiQualityAsphaltMixingPlantExceed;
import com.huatek.busi.model.quality.BusiQualityAsphaltMixingPlantInspection;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityAsphaltMixingPlantExceedService;
import com.huatek.busi.service.quality.BusiQualityAsphaltMixingPlantInspectionService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityAsphaltMixingPlantInspectionServiceImpl")
@Transactional
public class BusiQualityAsphaltMixingPlantInspectionServiceImpl implements BusiQualityAsphaltMixingPlantInspectionService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityAsphaltMixingPlantInspectionServiceImpl.class);
	
	@Autowired
	BusiQualityAsphaltMixingPlantInspectionDao busiQualityAsphaltMixingPlantInspectionDao;
	
	@Autowired
	BusiQualityAsphaltMixingPlantExceedService busiQualityAsphaltMixingPlantExceedService;
	
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	BusiQualityAsphaltMixingPlantExceedDao busiQualityAsphaltMixingPlantExceedDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualityAsphaltMixingPlantInspectionDto(BusiQualityAsphaltMixingPlantInspectionDto entityDto)  {
		log.debug("save busiQualityAsphaltMixingPlantInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityAsphaltMixingPlantInspection entity = BeanCopy.getInstance().convert(entityDto, BusiQualityAsphaltMixingPlantInspection.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityAsphaltMixingPlantInspectionDao.persistentBusiQualityAsphaltMixingPlantInspection(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityAsphaltMixingPlantInspectionDto getBusiQualityAsphaltMixingPlantInspectionDtoById(Long id) {
		log.debug("get busiQualityAsphaltMixingPlantInspection by id@" + id);
		BusiQualityAsphaltMixingPlantInspection entity = busiQualityAsphaltMixingPlantInspectionDao.findBusiQualityAsphaltMixingPlantInspectionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualityAsphaltMixingPlantInspectionDto entityDto = BeanCopy.getInstance()//
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.addFieldMap("busiQualityAsphaltMixingPlantExceed", "busiQualityAsphaltMixingPlantExceed")//
				.convert(entity, BusiQualityAsphaltMixingPlantInspectionDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityAsphaltMixingPlantInspection(Long id, BusiQualityAsphaltMixingPlantInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityAsphaltMixingPlantInspection entity = busiQualityAsphaltMixingPlantInspectionDao.findBusiQualityAsphaltMixingPlantInspectionById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityAsphaltMixingPlantInspectionDao.persistentBusiQualityAsphaltMixingPlantInspection(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityAsphaltMixingPlantInspection(Long id) {
		log.debug("delete busiQualityAsphaltMixingPlantInspection by id@" + id);
		beforeRemove(id);
		BusiQualityAsphaltMixingPlantInspection entity = busiQualityAsphaltMixingPlantInspectionDao.findBusiQualityAsphaltMixingPlantInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityAsphaltMixingPlantInspectionDao.deleteBusiQualityAsphaltMixingPlantInspection(entity);
	}
	
	@Override
	public DataPage<BusiQualityAsphaltMixingPlantInspectionDto> getAllBusiQualityAsphaltMixingPlantInspectionPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityAsphaltMixingPlantInspection> dataPage = busiQualityAsphaltMixingPlantInspectionDao.getAllBusiQualityAsphaltMixingPlantInspection(queryPage);
		return BeanCopy.getInstance().addFieldMap("busiQualityAsphaltMixingPlantExceed", "busiQualityAsphaltMixingPlantExceed").addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName").convertPage(dataPage, BusiQualityAsphaltMixingPlantInspectionDto.class);
	}
	
	@Override
	public List<BusiQualityAsphaltMixingPlantInspectionDto> getAllBusiQualityAsphaltMixingPlantInspectionDto() {
		List<BusiQualityAsphaltMixingPlantInspection> entityList = busiQualityAsphaltMixingPlantInspectionDao.findAllBusiQualityAsphaltMixingPlantInspection();
		List<BusiQualityAsphaltMixingPlantInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityAsphaltMixingPlantInspectionDto.class);
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
	* @param    busiQualityAsphaltMixingPlantInspectionDto
	* @param    busiQualityAsphaltMixingPlantInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityAsphaltMixingPlantInspectionDto entityDto, BusiQualityAsphaltMixingPlantInspection entity) {

	}


	@Override
	public List<BusiQualityAsphaltMixingPlantInspectionDto> getBusiQualityAsphaltMixingPlantInspectionDtoByIds(Long[] ids) {
		log.debug("get busiQualityAsphaltMixingPlantInspection by ids@" + ids);
		List<BusiQualityAsphaltMixingPlantInspection> entityList = busiQualityAsphaltMixingPlantInspectionDao.findBusiQualityAsphaltMixingPlantInspectionByIds(ids);
		
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convertList(entityList, BusiQualityAsphaltMixingPlantInspectionDto.class);
		
	}


	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto, Long... ids) {
		dto.setDataSource("asphalt_mixing_station");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityAsphaltMixingPlantInspection> list = new ArrayList<BusiQualityAsphaltMixingPlantInspection>();
		for(Long id : ids){
			BusiQualityAsphaltMixingPlantInspection obj = busiQualityAsphaltMixingPlantInspectionDao.findBusiQualityAsphaltMixingPlantInspectionById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
//			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			//超标数据
			BusiQualityAsphaltMixingPlantExceed cb = obj.getBusiQualityAsphaltMixingPlantExceed();
			cb.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			cb.setBusiQualityAsphaltMixingPlantInspection(obj);;
			obj.setBusiQualityAsphaltMixingPlantExceed(cb);;
			list.add(obj);
		}
		busiQualityAsphaltMixingPlantInspectionDao.batchUpdate(list);
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto, Long... ids) {
		dto.setDataSource("asphalt_mixing_station");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualityAsphaltMixingPlantInspection> list = new ArrayList<BusiQualityAsphaltMixingPlantInspection>();
		for(Long id : ids){
			BusiQualityAsphaltMixingPlantInspection obj = busiQualityAsphaltMixingPlantInspectionDao.findBusiQualityAsphaltMixingPlantInspectionById(id);
			
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
//			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setInspectionId(dto.getId());
			//	超标数据
			BusiQualityAsphaltMixingPlantExceed cb = obj.getBusiQualityAsphaltMixingPlantExceed();
			cb.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			cb.setBusiQualityAsphaltMixingPlantInspection(obj);
			obj.setBusiQualityAsphaltMixingPlantExceed(cb);
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.getBusiQualityAsphaltMixingPlantExceed().setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				obj.getBusiQualityAsphaltMixingPlantExceed().setDisposeDate(sdf.format(new Date()));
			}
			list.add(obj);
			
		}
		busiQualityAsphaltMixingPlantInspectionDao.batchUpdate(list);
	
		
	
		
	}


	@Override
	public void afterFlowEndChangeAsphaltMixingPlantStatus(String inspectionCode, int result) {
		List<BusiQualityAsphaltMixingPlantInspection> asphaltMixingPlantInspections = busiQualityAsphaltMixingPlantInspectionDao//
				.findBusiQualityAsphaltMixingPlantInspectionByCondition(inspectionCode,"inspectionCode");
		if(asphaltMixingPlantInspections!=null){
			for(BusiQualityAsphaltMixingPlantInspection asphaltMixingPlantInspection : asphaltMixingPlantInspections){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				asphaltMixingPlantInspection.getBusiQualityAsphaltMixingPlantExceed().setDisposeState(String.valueOf(result));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				asphaltMixingPlantInspection.getBusiQualityAsphaltMixingPlantExceed().setDisposeDate(sdf.format(new Date()));
				busiQualityAsphaltMixingPlantInspectionDao.saveOrUpdateBusiQualityAsphaltMixingPlantInspection(asphaltMixingPlantInspection);
			}
		}
		
	}


	@Override
	public List<BusiQualityAsphaltMixingPlantInspectionDto> getBusiQualityAsphaltMixingPlantByReCode(String inspectionCode) {
		List<BusiQualityAsphaltMixingPlantInspection> asphaltMixingPlants = busiQualityAsphaltMixingPlantInspectionDao//
				.findBusiQualityAsphaltMixingPlantInspectionByCondition(inspectionCode,"inspectionCode");
        return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.convertList(asphaltMixingPlants, BusiQualityAsphaltMixingPlantInspectionDto.class);
	}
}
