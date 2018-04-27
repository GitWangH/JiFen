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
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRawMaterialInspectionDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.busi.model.quality.BusiQualityRawMaterialInspection;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityRawMaterialInspectionService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityRawMaterialInspectionServiceImpl")
@Transactional
public class BusiQualityRawMaterialInspectionServiceImpl implements BusiQualityRawMaterialInspectionService{
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityRawMaterialInspectionServiceImpl.class);
	
	@Autowired
	private BusiQualityRawMaterialInspectionDao busiQualityRawMaterialInspectionDao;
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	@Autowired
	private RpcProxy rpcProxy;
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualityRawMaterialInspectionDto(BusiQualityRawMaterialInspectionDto entityDto)  {
		log.debug("save busiQualityRawMaterialInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityRawMaterialInspection entity = BeanCopy.getInstance().convert(entityDto, BusiQualityRawMaterialInspection.class);
		BusiFwOrg org = new BusiFwOrg();
		org.setId(entityDto.getOrgId());
		entity.setOrg(org);
		entity.setCreateTime(new Date());
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityRawMaterialInspectionDao.persistentBusiQualityRawMaterialInspection(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityRawMaterialInspectionDto getBusiQualityRawMaterialInspectionDtoById(Long id) {
		log.debug("get busiQualityRawMaterialInspection by id@" + id);
		BusiQualityRawMaterialInspection entity = busiQualityRawMaterialInspectionDao.findBusiQualityRawMaterialInspectionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityRawMaterialInspectionDto entityDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convert(entity, BusiQualityRawMaterialInspectionDto.class);
		return entityDto;
	}
	
	
	@Override
	public List<BusiQualityRawMaterialInspectionDto> getBusiQualityRawMaterialInspectionDtoByIds(Long[] ids) {
		log.debug("get busiQualityRawMaterialInspection by ids@" + ids);
		List<BusiQualityRawMaterialInspection> entityList = busiQualityRawMaterialInspectionDao.findBusiQualityRawMaterialInspectionByIds(ids);
		
				 
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convertList(entityList, BusiQualityRawMaterialInspectionDto.class);
		
	}
	
	@Override
	public void updateBusiQualityRawMaterialInspection(Long id, BusiQualityRawMaterialInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityRawMaterialInspection entity = busiQualityRawMaterialInspectionDao.findBusiQualityRawMaterialInspectionById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityRawMaterialInspectionDao.persistentBusiQualityRawMaterialInspection(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityRawMaterialInspection(Long id) {
		log.debug("delete busiQualityRawMaterialInspection by id@" + id);
		beforeRemove(id);
		BusiQualityRawMaterialInspection entity = busiQualityRawMaterialInspectionDao.findBusiQualityRawMaterialInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityRawMaterialInspectionDao.deleteBusiQualityRawMaterialInspection(entity);
	}
	
	@Override
	public DataPage<BusiQualityRawMaterialInspectionDto> getAllBusiQualityRawMaterialInspectionPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityRawMaterialInspection> dataPage = busiQualityRawMaterialInspectionDao.getAllBusiQualityRawMaterialInspection(queryPage);
	
		DataPage<BusiQualityRawMaterialInspectionDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId")//
				.addFieldMap("org.name", "orgName")
				.convertPage(dataPage, BusiQualityRawMaterialInspectionDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityRawMaterialInspectionDto> getAllBusiQualityRawMaterialInspectionDto() {
		List<BusiQualityRawMaterialInspection> entityList = busiQualityRawMaterialInspectionDao.findAllBusiQualityRawMaterialInspection();
		List<BusiQualityRawMaterialInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityRawMaterialInspectionDto.class);
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
	* @param    busiQualityRawMaterialInspectionDto
	* @param    busiQualityRawMaterialInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityRawMaterialInspectionDto entityDto, BusiQualityRawMaterialInspection entity) {

	}



	@Override
	public List<BusiQualityRawMaterialInspectionDto> getQualityAllRectificateRawMaterialInspection(String inspectionType) {
		List<BusiQualityRawMaterialInspection> qualityRawMaterialInspection = busiQualityRawMaterialInspectionDao//
				.findRectificateRawMaterialByCondition(Integer.valueOf(inspectionType),"inspectionType");
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
					.convertList(qualityRawMaterialInspection, BusiQualityRawMaterialInspectionDto.class);
	}

	@Override
	public List<BusiQualityRawMaterialInspectionDto> getAllRawMaterialInspectionByInspectionTypeAndInspectionId(
			Integer inspectionType, Long inspectionId) {
		List<BusiQualityRawMaterialInspection> qualityRawMaterialInspection = busiQualityRawMaterialInspectionDao
				.findRectificateRawMaterialByCondition(Integer.valueOf(inspectionType),"inspectionType");
		return null;
	}
	
	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto,Long... ids) {
		dto.setDataSource("raw_material_inspection");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityRawMaterialInspection> list = new ArrayList<BusiQualityRawMaterialInspection>();
		for(Long id : ids){
			BusiQualityRawMaterialInspection obj = busiQualityRawMaterialInspectionDao.findBusiQualityRawMaterialInspectionById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setDisposeTime(new Date());
			obj.setInspectionId(dto.getId());
			list.add(obj);
		}
		busiQualityRawMaterialInspectionDao.batchUpdate(list);
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto,Long... ids) {
		dto.setDataSource("raw_material_inspection");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualityRawMaterialInspection> list = new ArrayList<BusiQualityRawMaterialInspection>();
		for(Long id : ids){
			BusiQualityRawMaterialInspection obj = busiQualityRawMaterialInspectionDao.findBusiQualityRawMaterialInspectionById(id);
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setDisposeTime(new Date());
			obj.setInspectionId(dto.getId());
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				obj.setDisposeTime(new Date());
			}
			list.add(obj);
		}
		busiQualityRawMaterialInspectionDao.batchUpdate(list);
	}

	/**
	 * 通过整改编号查询原材料数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	@Override
	public List<BusiQualityRawMaterialInspectionDto> getRawMaterialByReCode(String inspectionCode) {
		List<BusiQualityRawMaterialInspection> rawMaterialInspections = busiQualityRawMaterialInspectionDao//
							.findBusiQualityRawMaterialInspectionByCondition(inspectionCode,"inspectionCode");
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
					.addFieldMap("org.name", "orgName")//
					.convertList(rawMaterialInspections, BusiQualityRawMaterialInspectionDto.class);
	}


	@Override
	public void afterFlowEndChangeRawMaterialStatus(String inspectionCode,int result) {
		List<BusiQualityRawMaterialInspection> rawMaterialInspections = busiQualityRawMaterialInspectionDao//
				.findBusiQualityRawMaterialInspectionByCondition(inspectionCode,"inspectionCode");
		if(rawMaterialInspections!=null){
			for(BusiQualityRawMaterialInspection rawMaterialInspection : rawMaterialInspections){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				rawMaterialInspection.setDisposeState(String.valueOf(result));
				rawMaterialInspection.setDisposeTime(new Date());
				busiQualityRawMaterialInspectionDao.saveOrUpdateBusiQualityRawMaterialInspection(rawMaterialInspection);
			}
		}
		
	}

}
