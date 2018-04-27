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
import com.huatek.busi.dao.quality.BusiQualityPrestressedTensionMainDao;
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dto.quality.BusiQualityMortarDto;
import com.huatek.busi.dto.quality.BusiQualityPrestressedTensionMainDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.quality.BusiQualityMortar;
import com.huatek.busi.model.quality.BusiQualityPrestressedTensionMain;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityPrestressedTensionMainService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityPrestressedTensionMainServiceImpl")
@Transactional
public class BusiQualityPrestressedTensionMainServiceImpl implements BusiQualityPrestressedTensionMainService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityPrestressedTensionMainServiceImpl.class);
	
	@Autowired
	BusiQualityPrestressedTensionMainDao busiQualityPrestressedTensionMainDao;
	
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualityPrestressedTensionMainDto(BusiQualityPrestressedTensionMainDto entityDto)  {
		log.debug("save busiQualityPrestressedTensionMainDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityPrestressedTensionMain entity = BeanCopy.getInstance().convert(entityDto, BusiQualityPrestressedTensionMain.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityPrestressedTensionMainDao.persistentBusiQualityPrestressedTensionMain(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityPrestressedTensionMainDto getBusiQualityPrestressedTensionMainDtoById(Long id) {
		log.debug("get busiQualityPrestressedTensionMain by id@" + id);
		BusiQualityPrestressedTensionMain entity = busiQualityPrestressedTensionMainDao.findBusiQualityPrestressedTensionMainById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiQualityPrestressedTensionMainDto entityDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId")//
				.addFieldMap("org.name", "orgName").convert(entity, BusiQualityPrestressedTensionMainDto.class);		 
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityPrestressedTensionMain(Long id, BusiQualityPrestressedTensionMainDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityPrestressedTensionMain entity = busiQualityPrestressedTensionMainDao.findBusiQualityPrestressedTensionMainById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityPrestressedTensionMainDao.persistentBusiQualityPrestressedTensionMain(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityPrestressedTensionMain(Long id) {
		log.debug("delete busiQualityPrestressedTensionMain by id@" + id);
		beforeRemove(id);
		BusiQualityPrestressedTensionMain entity = busiQualityPrestressedTensionMainDao.findBusiQualityPrestressedTensionMainById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityPrestressedTensionMainDao.deleteBusiQualityPrestressedTensionMain(entity);
	}
	
	@Override
	public DataPage<BusiQualityPrestressedTensionMainDto> getAllBusiQualityPrestressedTensionMainPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityPrestressedTensionMain> dataPage = busiQualityPrestressedTensionMainDao.getAllBusiQualityPrestressedTensionMain(queryPage);
		DataPage<BusiQualityPrestressedTensionMainDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId")//
				.addFieldMap("org.name", "orgName")
				.convertPage(dataPage, BusiQualityPrestressedTensionMainDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityPrestressedTensionMainDto> getAllBusiQualityPrestressedTensionMainDto() {
		List<BusiQualityPrestressedTensionMain> entityList = busiQualityPrestressedTensionMainDao.findAllBusiQualityPrestressedTensionMain();
		List<BusiQualityPrestressedTensionMainDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityPrestressedTensionMainDto.class);
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
	* @param    busiQualityPrestressedTensionMainDto
	* @param    busiQualityPrestressedTensionMain
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityPrestressedTensionMainDto entityDto, BusiQualityPrestressedTensionMain entity) {

	}


	@Override
	public List<BusiQualityPrestressedTensionMainDto> getBusiQualityPrestressedTensionMainDtoByIds(Long[] ids) {
		List<BusiQualityPrestressedTensionMain> entityList = busiQualityPrestressedTensionMainDao.findBusiQualityPrestressedTensionMainByIds(ids);
		
		 
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convertList(entityList, BusiQualityPrestressedTensionMainDto.class);
	}


	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto, Long... ids) {
		dto.setDataSource("prestressed_tension_main_inspection");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityPrestressedTensionMain> list = new ArrayList<BusiQualityPrestressedTensionMain>();
		for(Long id : ids){
			BusiQualityPrestressedTensionMain obj = busiQualityPrestressedTensionMainDao.findBusiQualityPrestressedTensionMainById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
			obj.setDisposeStatus(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			list.add(obj);
		}
		busiQualityPrestressedTensionMainDao.batchUpdate(list);
	
		
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto, Long... ids) {
		dto.setDataSource("prestressed_tension_main_inspection");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualityPrestressedTensionMain> list = new ArrayList<BusiQualityPrestressedTensionMain>();
		for(Long id : ids){
			BusiQualityPrestressedTensionMain obj = busiQualityPrestressedTensionMainDao.findBusiQualityPrestressedTensionMainById(id);
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
			obj.setDisposeStatus(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setInspectionId(dto.getId());
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.setDisposeStatus(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				obj.setDisposeTime(new Date());
			}
			list.add(obj);
		}
		busiQualityPrestressedTensionMainDao.batchUpdate(list);
		
	}


	@Override
	public List<BusiQualityPrestressedTensionMainDto> getBusiQualityPrestressedTensionMainByReCode(String inspectionCode) {

		List<BusiQualityPrestressedTensionMain> prestressedTensionMains = busiQualityPrestressedTensionMainDao//
				.findBusiQualityPrestressedTensionMainByCondition(inspectionCode,"inspectionCode");
        return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.convertList(prestressedTensionMains, BusiQualityPrestressedTensionMainDto.class);

	}


	@Override
	public void afterFlowEndChangePrestressedTensionMainStatus(String inspectionCode, int result) {
		List<BusiQualityPrestressedTensionMain> prestressedTensionMains = busiQualityPrestressedTensionMainDao//
				.findBusiQualityPrestressedTensionMainByCondition(inspectionCode,"inspectionCode");
		if(prestressedTensionMains!=null){
			for(BusiQualityPrestressedTensionMain prestressedTensionMain : prestressedTensionMains){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				prestressedTensionMain.setDisposeStatus(String.valueOf(result));
				prestressedTensionMain.setDisposeTime(new Date());
				busiQualityPrestressedTensionMainDao.saveOrUpdateBusiQualityPrestressedTensionMain(prestressedTensionMain);;;
			}
		}
		
	}
}
