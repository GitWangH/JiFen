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
import com.huatek.busi.dao.quality.BusiQualitySpreaderRollerSpreaderParentDao;
import com.huatek.busi.dto.quality.BusiQualityPrestressedTensionMainDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerSpreaderParentDto;
import com.huatek.busi.model.quality.BusiQualityPrestressedTensionMain;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.busi.model.quality.BusiQualitySpreaderRollerSpreaderParent;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualitySpreaderRollerSpreaderParentService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("busiQualitySpreaderRollerSpreaderParentServiceImpl")
@Transactional
public class BusiQualitySpreaderRollerSpreaderParentServiceImpl implements BusiQualitySpreaderRollerSpreaderParentService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySpreaderRollerSpreaderParentServiceImpl.class);
	
	@Autowired
	BusiQualitySpreaderRollerSpreaderParentDao busiQualitySpreaderRollerSpreaderParentDao;
	
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	
	@Override
	public void saveBusiQualitySpreaderRollerSpreaderParentDto(BusiQualitySpreaderRollerSpreaderParentDto entityDto)  {
		log.debug("save busiQualitySpreaderRollerSpreaderParentDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualitySpreaderRollerSpreaderParent entity = BeanCopy.getInstance().convert(entityDto, BusiQualitySpreaderRollerSpreaderParent.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualitySpreaderRollerSpreaderParentDao.persistentBusiQualitySpreaderRollerSpreaderParent(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualitySpreaderRollerSpreaderParentDto getBusiQualitySpreaderRollerSpreaderParentDtoById(Long id) {
		log.debug("get busiQualitySpreaderRollerSpreaderParent by id@" + id);
		BusiQualitySpreaderRollerSpreaderParent entity = busiQualitySpreaderRollerSpreaderParentDao.findBusiQualitySpreaderRollerSpreaderParentById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		
		BusiQualitySpreaderRollerSpreaderParentDto entityDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
										.addFieldMap("org.id", "orgId").addFieldMap("org.name", "orgName")//
										.convert(entity, BusiQualitySpreaderRollerSpreaderParentDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySpreaderRollerSpreaderParent(Long id, BusiQualitySpreaderRollerSpreaderParentDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualitySpreaderRollerSpreaderParent entity = busiQualitySpreaderRollerSpreaderParentDao.findBusiQualitySpreaderRollerSpreaderParentById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiQualitySpreaderRollerSpreaderParentDao.persistentBusiQualitySpreaderRollerSpreaderParent(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualitySpreaderRollerSpreaderParent(Long id) {
		log.debug("delete busiQualitySpreaderRollerSpreaderParent by id@" + id);
		beforeRemove(id);
		BusiQualitySpreaderRollerSpreaderParent entity = busiQualitySpreaderRollerSpreaderParentDao.findBusiQualitySpreaderRollerSpreaderParentById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySpreaderRollerSpreaderParentDao.deleteBusiQualitySpreaderRollerSpreaderParent(entity);
	}
	
	@Override
	public DataPage<BusiQualitySpreaderRollerSpreaderParentDto> getAllBusiQualitySpreaderRollerSpreaderParentPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualitySpreaderRollerSpreaderParent> dataPage = busiQualitySpreaderRollerSpreaderParentDao.getAllBusiQualitySpreaderRollerSpreaderParent(queryPage);
		DataPage<BusiQualitySpreaderRollerSpreaderParentDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.id", "orgId")//
				.addFieldMap("org.name", "orgName")
				.convertPage(dataPage, BusiQualitySpreaderRollerSpreaderParentDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualitySpreaderRollerSpreaderParentDto> getAllBusiQualitySpreaderRollerSpreaderParentDto() {
		List<BusiQualitySpreaderRollerSpreaderParent> entityList = busiQualitySpreaderRollerSpreaderParentDao.findAllBusiQualitySpreaderRollerSpreaderParent();
		List<BusiQualitySpreaderRollerSpreaderParentDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualitySpreaderRollerSpreaderParentDto.class);
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
	* @param    busiQualitySpreaderRollerSpreaderParentDto
	* @param    busiQualitySpreaderRollerSpreaderParent
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySpreaderRollerSpreaderParentDto entityDto, BusiQualitySpreaderRollerSpreaderParent entity) {

	}


	@Override
	public List<BusiQualitySpreaderRollerSpreaderParentDto> getBusiQualitySpreaderRollerSpreaderParentDtoByIds(Long[] ids) {
		List<BusiQualitySpreaderRollerSpreaderParent> entityList = busiQualitySpreaderRollerSpreaderParentDao.findBusiQualitySpreaderRollerSpreaderParentByIds(ids);
		
		 
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name","orgName").convertList(entityList, BusiQualitySpreaderRollerSpreaderParentDto.class);
	}


	@Override
	public void generateBusiQualityRectificate(BusiQualityRectificationDto dto, Long... ids) {
		dto.setDataSource("spreader_roller");//set 接口处理类型
		dto = baseQualityRectificationService.saveQualityRectification(dto);
		//修改原接口表中的相关字段
		List<BusiQualitySpreaderRollerSpreaderParent> list = new ArrayList<BusiQualitySpreaderRollerSpreaderParent>();
		for(Long id : ids){
			BusiQualitySpreaderRollerSpreaderParent obj = busiQualitySpreaderRollerSpreaderParentDao.findBusiQualitySpreaderRollerSpreaderParentById(id);
			obj.setInspectionType(1);
			obj.setInspectionCode(dto.getRectificationCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			obj.setInspectionId(dto.getId());
			list.add(obj);
		}
		busiQualitySpreaderRollerSpreaderParentDao.batchUpdate(list);
	
		
	}


	@Override
	public void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto, Long... ids) {
		dto.setDataSource("spreader_roller");//set 接口处理类型
		//从页面传入的快捷处理信息
		dto = baseQualityRectificationService.saveQualityQuickProcess(dto);
		//修改原接口表中的相关字段
		List<BusiQualitySpreaderRollerSpreaderParent> list = new ArrayList<BusiQualitySpreaderRollerSpreaderParent>();
		for(Long id : ids){
			BusiQualitySpreaderRollerSpreaderParent obj = busiQualitySpreaderRollerSpreaderParentDao.findBusiQualitySpreaderRollerSpreaderParentById(id);
			obj.setInspectionType(0);
			obj.setInspectionCode(dto.getQuickProcessCode());
			obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			obj.setInspectionId(dto.getId());
			obj.setInspectionId(dto.getId());
			if(!"7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
				obj.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
				obj.setModifyTime(new Date());
			}
			list.add(obj);
		}
		busiQualitySpreaderRollerSpreaderParentDao.batchUpdate(list);
		
	}


	@Override
	public List<BusiQualitySpreaderRollerSpreaderParentDto> getBusiQualitySpreaderRollerSpreaderByReCode(String inspectionCode) {
		List<BusiQualitySpreaderRollerSpreaderParent> spreaderRollerSpreaderParents = busiQualitySpreaderRollerSpreaderParentDao//
				.findBusiQualitySpreaderRollerSpreaderParentByCondition(inspectionCode,"inspectionCode");
        return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
		.addFieldMap("org.name", "orgName")//
		.convertList(spreaderRollerSpreaderParents, BusiQualitySpreaderRollerSpreaderParentDto.class);
	}


	@Override
	public void afterFlowEndChangeSpreaderRollerSpreaderStatus(String inspectionCode, int result) {
		List<BusiQualitySpreaderRollerSpreaderParent> spreaderRollerSpreaderParents = busiQualitySpreaderRollerSpreaderParentDao//
				.findBusiQualitySpreaderRollerSpreaderParentByCondition(inspectionCode,"inspectionCode");
		if(spreaderRollerSpreaderParents!=null){
			for(BusiQualitySpreaderRollerSpreaderParent spreaderRollerSpreaderParent : spreaderRollerSpreaderParents){
				/*if(result){
					if(0==rawMaterialInspection.getInspectionType().intValue()){//快捷处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_DEAL_SUCCESS));
					}else{//整改处理
						rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
					}
				}else{
					rawMaterialInspection.setDisposeState(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_SUCCESS));
				}*/
				spreaderRollerSpreaderParent.setDisposeState(String.valueOf(result));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				spreaderRollerSpreaderParent.setModifierName(sdf.format(new Date()));
				busiQualitySpreaderRollerSpreaderParentDao.saveOrUpdateBusiQualitySpreaderRollerSpreaderParent(spreaderRollerSpreaderParent);
			}
		}
		
	}
}
