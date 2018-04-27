package com.huatek.busi.service.impl.quality;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityMortarDao;
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dto.quality.BusiQualityMortarDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.quality.BusiQualityMortar;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityMortarService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityMortarServiceImpl")
@Transactional
public class BusiQualityMortarServiceImpl implements BusiQualityMortarService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityMortarServiceImpl.class);
	
	@Autowired
	BusiQualityMortarDao busiQualityMortarDao;
	
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualityMortarDto(BusiQualityMortarDto entityDto)  {
		log.debug("save busiQualityMortarDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityMortar entity = BeanCopy.getInstance().convert(entityDto, BusiQualityMortar.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityMortarDao.persistentBusiQualityMortar(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityMortarDto getBusiQualityMortarDtoById(Long id) {
		log.debug("get busiQualityMortar by id@" + id);
		BusiQualityMortar entity = busiQualityMortarDao.findBusiQualityMortarById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiQualityMortarDto entityDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId")//
				.addFieldMap("org.name", "orgName").convert(entity, BusiQualityMortarDto.class);		 
		return entityDto;
	}
	
	@Override
	public List<BusiQualityMortarDto> getBusiQualityMortarDtoByIds(Long[] ids) {
		log.debug("get busiQualityRawMaterialInspection by ids@" + ids);
		List<BusiQualityMortar> entityList = busiQualityMortarDao.findBusiQualityMortarByIds(ids);
		
				 
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convertList(entityList, BusiQualityMortarDto.class);
		
	}
	
	@Override
	public void updateBusiQualityMortar(Long id, BusiQualityMortarDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityMortar entity = busiQualityMortarDao.findBusiQualityMortarById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityMortarDao.persistentBusiQualityMortar(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityMortar(Long id) {
		log.debug("delete busiQualityMortar by id@" + id);
		beforeRemove(id);
		BusiQualityMortar entity = busiQualityMortarDao.findBusiQualityMortarById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityMortarDao.deleteBusiQualityMortar(entity);
	}
	
	@Override
	public DataPage<BusiQualityMortarDto> getAllBusiQualityMortarPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityMortar> dataPage = busiQualityMortarDao.getAllBusiQualityMortar(queryPage);
		DataPage<BusiQualityMortarDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId")//
				.addFieldMap("org.name", "orgName")
				.convertPage(dataPage, BusiQualityMortarDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityMortarDto> getAllBusiQualityMortarDto() {
		List<BusiQualityMortar> entityList = busiQualityMortarDao.findAllBusiQualityMortar();
		List<BusiQualityMortarDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityMortarDto.class);
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
	* @param    busiQualityMortarDto
	* @param    busiQualityMortar
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityMortarDto entityDto, BusiQualityMortar entity) {

	}


	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto, Long... ids) {

		dto.setDataSource("mortar_inspection");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityMortar> list = new ArrayList<BusiQualityMortar>();
		for(Long id : ids){
			BusiQualityMortar obj = busiQualityMortarDao.findBusiQualityMortarById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			list.add(obj);
		}
		busiQualityMortarDao.batchUpdate(list);
	
		
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto, Long... ids) {
		dto.setDataSource("mortar_inspection");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualityMortar> list = new ArrayList<BusiQualityMortar>();
		for(Long id : ids){
			BusiQualityMortar obj = busiQualityMortarDao.findBusiQualityMortarById(id);
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setInspectionId(dto.getId());
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				obj.setDisposeTime(new Date());
			}
			list.add(obj);
		}
		busiQualityMortarDao.batchUpdate(list);
	}


	@Override
	public List<BusiQualityMortarDto> getBusiQualityMortarByReCode(String inspectionCode) {
		List<BusiQualityMortar> rawMaterialInspections = busiQualityMortarDao//
				.findBusiQualityMortarByCondition(inspectionCode,"inspectionCode");
        return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.convertList(rawMaterialInspections, BusiQualityMortarDto.class);
}


	@Override
	public void afterFlowEndChangeMortarStatus(String inspectionCode, int result) {
		List<BusiQualityMortar> mortarInspections = busiQualityMortarDao//
				.findBusiQualityMortarByCondition(inspectionCode,"inspectionCode");
		if(mortarInspections!=null){
			for(BusiQualityMortar mortarInspection : mortarInspections){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				mortarInspection.setDisposeState(String.valueOf(result));
				mortarInspection.setDisposeTime(new Date());
				busiQualityMortarDao.saveOrUpdateBusiQualityMortar(mortarInspection);;
			}
		}
		
	}


}
