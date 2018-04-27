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
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dao.quality.BusiQualityUniversalPressMachineParentDao;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.model.quality.BusiQualityUniversalPressMachineParent;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityUniversalPressMachineParentService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualityUniversalPressMachineParentServiceImpl")
@Transactional
public class BusiQualityUniversalPressMachineParentServiceImpl implements BusiQualityUniversalPressMachineParentService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityUniversalPressMachineParentServiceImpl.class);
	
	@Autowired
	BusiQualityUniversalPressMachineParentDao busiQualityUniversalPressMachineParentDao;
	
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualityUniversalPressMachineParentDto(BusiQualityUniversalPressMachineParentDto entityDto)  {
		log.debug("save busiQualityUniversalPressMachineParentDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityUniversalPressMachineParent entity = BeanCopy.getInstance().convert(entityDto, BusiQualityUniversalPressMachineParent.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityUniversalPressMachineParentDao.persistentBusiQualityUniversalPressMachineParent(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityUniversalPressMachineParentDto getBusiQualityUniversalPressMachineParentDtoById(Long id) {
		log.debug("get busiQualityUniversalPressMachineParent by id@" + id);
		BusiQualityUniversalPressMachineParent entity = busiQualityUniversalPressMachineParentDao.findBusiQualityUniversalPressMachineParentById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiQualityUniversalPressMachineParentDto entityDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
				.addFieldMap("factOrg.id", "factOrgId").addFieldMap("factOrg.name", "factOrgName")//
				.convert(entity, BusiQualityUniversalPressMachineParentDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityUniversalPressMachineParent(Long id, BusiQualityUniversalPressMachineParentDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityUniversalPressMachineParent entity = busiQualityUniversalPressMachineParentDao.findBusiQualityUniversalPressMachineParentById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualityUniversalPressMachineParentDao.persistentBusiQualityUniversalPressMachineParent(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityUniversalPressMachineParent(Long id) {
		log.debug("delete busiQualityUniversalPressMachineParent by id@" + id);
		beforeRemove(id);
		BusiQualityUniversalPressMachineParent entity = busiQualityUniversalPressMachineParentDao.findBusiQualityUniversalPressMachineParentById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityUniversalPressMachineParentDao.deleteBusiQualityUniversalPressMachineParent(entity);
	}
	
	@Override
	public DataPage<BusiQualityUniversalPressMachineParentDto> getAllBusiQualityUniversalPressMachineParentPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityUniversalPressMachineParent> dataPage = busiQualityUniversalPressMachineParentDao.getAllBusiQualityUniversalPressMachineParent(queryPage);
		DataPage<BusiQualityUniversalPressMachineParentDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId")//
				.addFieldMap("org.name", "orgName")
				.addFieldMap("factOrg.id", "factOrgId")//
				.addFieldMap("factOrg.name", "factOrgName")
				.convertPage(dataPage, BusiQualityUniversalPressMachineParentDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityUniversalPressMachineParentDto> getAllBusiQualityUniversalPressMachineParentDto() {
		List<BusiQualityUniversalPressMachineParent> entityList = busiQualityUniversalPressMachineParentDao.findAllBusiQualityUniversalPressMachineParent();
		List<BusiQualityUniversalPressMachineParentDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityUniversalPressMachineParentDto.class);
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
	* @param    busiQualityUniversalPressMachineParentDto
	* @param    busiQualityUniversalPressMachineParent
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityUniversalPressMachineParentDto entityDto, BusiQualityUniversalPressMachineParent entity) {

	}


	@Override
	public List<BusiQualityUniversalPressMachineParentDto> getBusiQualityUniversalPressMachineParentDtoByIds(Long[] ids) {
		List<BusiQualityUniversalPressMachineParent> entityList = busiQualityUniversalPressMachineParentDao.findBusiQualityUniversalPressMachineParentByIds(ids);
		
		 
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").addFieldMap("factOrg.name","factOrgName").convertList(entityList, BusiQualityUniversalPressMachineParentDto.class);
	}


	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto, Long... ids) {
		dto.setDataSource("test_inspection");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualityUniversalPressMachineParent> list = new ArrayList<BusiQualityUniversalPressMachineParent>();
		for(Long id : ids){
			BusiQualityUniversalPressMachineParent obj = busiQualityUniversalPressMachineParentDao.findBusiQualityUniversalPressMachineParentById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			list.add(obj);
		}
		busiQualityUniversalPressMachineParentDao.batchUpdate(list);
		
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto, Long... ids) {
		dto.setDataSource("test_inspection");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualityUniversalPressMachineParent> list = new ArrayList<BusiQualityUniversalPressMachineParent>();
		for(Long id : ids){
			BusiQualityUniversalPressMachineParent obj = busiQualityUniversalPressMachineParentDao.findBusiQualityUniversalPressMachineParentById(id);
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setInspectionId(dto.getId());
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				obj.setModifyTime(new Date());
			}
			list.add(obj);
		}
		busiQualityUniversalPressMachineParentDao.batchUpdate(list);
		
	}


	@Override
	public List<BusiQualityUniversalPressMachineParentDto> getBusiQualityUniversalPressMachineParentByReCode(String inspectionCode) {
		List<BusiQualityUniversalPressMachineParent> universalPressMachineParents = busiQualityUniversalPressMachineParentDao//
				.findBusiQualityUniversalPressMachineParentByCondition(inspectionCode,"inspectionCode");
        return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.addFieldMap("factOrg.name", "factOrgName")//
		.convertList(universalPressMachineParents, BusiQualityUniversalPressMachineParentDto.class);
	}


	@Override
	public void afterFlowEndChangeUniversalPressMachineParentStatus(String inspectionCode, int result) {
		List<BusiQualityUniversalPressMachineParent> universalPressMachineParents = busiQualityUniversalPressMachineParentDao//
				.findBusiQualityUniversalPressMachineParentByCondition(inspectionCode,"inspectionCode");
		if(universalPressMachineParents!=null){
			for(BusiQualityUniversalPressMachineParent universalPressMachineParent : universalPressMachineParents){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				universalPressMachineParent.setDisposeState(String.valueOf(result));
				universalPressMachineParent.setModifyTime(new Date());
				busiQualityUniversalPressMachineParentDao.saveOrUpdateBusiQualityUniversalPressMachineParent(universalPressMachineParent);
			}
		}
		
	}
}
