package com.huatek.busi.service.impl.quality;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.contract.BusiContractTendersBranchDao;
import com.huatek.busi.dao.quality.BusiQualityConstructionInspectionDao;
import com.huatek.busi.dto.quality.BusiQualityConstrucationInspectionPassRecordDto;
import com.huatek.busi.dto.quality.BusiQualityConstructionInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.contract.BusiContractTendersBranch;
import com.huatek.busi.model.quality.BusiQualityConstructionInspection;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.busi.service.quality.BusiQualityConstrucationInspectionPassRecordService;
import com.huatek.busi.service.quality.BusiQualityConstructionInspectionService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.workflow.service.IProcessListener;
import com.huatek.workflow.service.WorkFlowRpcService;

/**
 * 施工报检service实现
 * @author rocky_wei
 *
 */
@Service("busiQualityConstructionInspectionServiceImpl")
@Transactional
public class BusiQualityConstructionInspectionServiceImpl implements BusiQualityConstructionInspectionService,IProcessListener {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityConstructionInspectionServiceImpl.class);
	
	@Autowired
	private BusiQualityConstructionInspectionDao busiQualityConstructionInspectionDao;
	@Autowired
	private OperationService oprationService;
	@Autowired
	private BusiContractTendersBranchDao contractTendersBranchDao;
	@Autowired
	private RpcProxy rpcProxy;
	@Autowired
	private BaseQualityRectificationService baseQualityRectificationService;
	@Autowired
	private BusiQualityConstrucationInspectionPassRecordService busiQualityConstrucationInspectionPassRecordService;
	
	@Override
	public void saveBusiQualityConstructionInspectionDto(BusiQualityConstructionInspectionDto entityDto)  {
		log.debug("save busiQualityConstructionInspectionDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityConstructionInspection entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten,"yyyy-MM-dd")//
				.addFieldMap("orgId", "org")//
				.addFieldMap("tendersBranchId", "tendersBranch")//
				.convert(entityDto, BusiQualityConstructionInspection.class);
		entity.setIsDelete(0);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityConstructionInspectionDao.persistentBusiQualityConstructionInspection(entity);
		log.debug("saved entityDto id is @" + entity.getId());
		oprationService.logOperation("施工报检添加成功");
	}
	
	
	@Override
	public BusiQualityConstructionInspectionDto getBusiQualityConstructionInspectionDtoById(Long id) {
		log.debug("get busiQualityConstructionInspection by id@" + id);
		BusiQualityConstructionInspection entity = busiQualityConstructionInspectionDao.findBusiQualityConstructionInspectionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiQualityConstructionInspectionDto entityDto = BeanCopy.getInstance()//
								.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")//
//								.addFieldMap("tendersBranch.tendersBranchName", "tendersBranchName")
								.addFieldMap("tendersBranch.id", "tendersBranchId")//
								.convert(entity, BusiQualityConstructionInspectionDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityConstructionInspection(Long id, BusiQualityConstructionInspectionDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityConstructionInspection entity = busiQualityConstructionInspectionDao.findBusiQualityConstructionInspectionById(id);
		BeanCopy.getInstance().addFieldMap("orgId", "org").addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.mapIgnoreNull(entityDto, entity);
		//进行持久化保存
		busiQualityConstructionInspectionDao.persistentBusiQualityConstructionInspection(entity);
		oprationService.logOperation("施工报检修改");
	}
	
	
	
	@Override
	public void deleteBusiQualityConstructionInspection(Long id) {
		log.debug("delete busiQualityConstructionInspection by id@" + id);
		beforeRemove(id);
		BusiQualityConstructionInspection entity = busiQualityConstructionInspectionDao.findBusiQualityConstructionInspectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
//		busiQualityConstructionInspectionDao.deleteBusiQualityConstructionInspection(entity);
		entity.setIsDelete(1);
		busiQualityConstructionInspectionDao.persistentBusiQualityConstructionInspection(entity);
		oprationService.logOperation("施工报检删除");
	}
	
	@Override
	public DataPage<BusiQualityConstructionInspectionDto> getAllBusiQualityConstructionInspectionPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityConstructionInspection> dataPage = busiQualityConstructionInspectionDao.getAllBusiQualityConstructionInspection(queryPage);
		DataPage<BusiQualityConstructionInspectionDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten,"yyyy-MM-dd")//
					.addConvertParam(ConvertParam.trueValue, "true").addConvertParam(ConvertParam.falseValue, "false")//
					.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")//
//					.addFieldMap("tendersBranch.tendersBranchName", "tendersBranchName")//
					.convertPage(dataPage, BusiQualityConstructionInspectionDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityConstructionInspectionDto> getAllBusiQualityConstructionInspectionDto() {
		List<BusiQualityConstructionInspection> entityList = busiQualityConstructionInspectionDao.findAllBusiQualityConstructionInspection();
		List<BusiQualityConstructionInspectionDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiQualityConstructionInspectionDto.class);
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
	* @param    busiQualityConstructionInspectionDto
	* @param    busiQualityConstructionInspection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityConstructionInspectionDto entityDto, BusiQualityConstructionInspection entity) {
		//添加填报人信息
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		if(currentUser==null){
			throw new BusinessRuntimeException("请重新登录", "-1");
		}
		if(entityDto.getId()==null){
//			entity.setWriteReportUserId(currentUser.getId());
//			entity.setWriteReportUserName(currentUser.getUserName());
//			entity.setWriteReportTime(new Date());
			entity.setQualityStatus(Constant.Quality.CONSTRUCION_STATUS_3);//未确定
			entity.setApprovalStatus(String.valueOf(Constant.Quality.STATUS_DEAL_NO));
			entity.setTenantId(currentUser.getTenantId());
			
			BusiContractTendersBranch tendersBranch = contractTendersBranchDao.findBusiContractTendersBranchById(entityDto.getTendersBranchId());
			entity.setTendersBranch(tendersBranch);
		}
	}

	

	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		List<BusiQualityConstructionInspection> constructionInspectionList = busiQualityConstructionInspectionDao//
						.findBusiQualityConstructionInspectionByCondition("flowInstanceId",processInstanceId.toString());
		if(constructionInspectionList!=null && constructionInspectionList.size()>0){
			BusiQualityConstructionInspection constructionInspection = constructionInspectionList.get(0);
			constructionInspection.setApprovalTime(new Date());
			constructionInspection.setApprovalUserId(ThreadLocalClient.get().getOperator().getId());
			constructionInspection.setApprovalUserName(ThreadLocalClient.get().getOperator().getUserName());
			constructionInspection.setFlowMessage(variables.get("resultMessage").toString());
			Boolean result = new Boolean(variables.get("result").toString());
			if(result){//true
				constructionInspection.setApprovalStatus(String.valueOf(Constant.Quality.STATUS_APPROVE_PASS));//通过
				constructionInspection.setFlowResult(Constant.FLOW_STATUS_PASSED);
				constructionInspection.setQualityStatus(Constant.Quality.CONSTRUCION_STATUS_2);
			}else{//false
				constructionInspection.setFlowResult(Constant.FLOW_STATUS_REBUT);
				constructionInspection.setApprovalStatus(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
				constructionInspection.setQualityStatus(Constant.Quality.CONSTRUCION_STATUS_1);
			}
			busiQualityConstructionInspectionDao.saveOrUpdateBusiQualityConstructionInspection(constructionInspection);
		}
		return null;
	}
	
	@Override
	public void reportQualityConstructionInspection(Long id) {
		BusiQualityConstructionInspection construcionInspection = busiQualityConstructionInspectionDao.findBusiQualityConstructionInspectionById(id);
		if(construcionInspection!=null){
			WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("title","施工报检单："+construcionInspection.getCheckParagraph()+"上报");
			String flowInstanceId = workFlowRpcService.startProcessInstanceByKey("busi_quality_construction_inspection", construcionInspection.getId(), variables);
			
			construcionInspection.setApplyTime(new Date());
			construcionInspection.setApplyUserId(ThreadLocalClient.get().getOperator().getId());
			construcionInspection.setApplyUserName(ThreadLocalClient.get().getOperator().getUserName());
			construcionInspection.setFlowInstanceId(Long.valueOf(flowInstanceId));
			construcionInspection.setApprovalStatus(String.valueOf(Constant.Quality.STATUS_NOt_APPROVING));//审批中
			busiQualityConstructionInspectionDao.saveOrUpdateBusiQualityConstructionInspection(construcionInspection);
			oprationService.logOperation("施工报检流程发起");
		}
	}


	@Override
	public void constructionInspectionApprove(String taskId, Long busiId,Map<String, Object> map) {
		
		Boolean result=Boolean.valueOf(map.get("result").toString());
		String  resultMessage=map.get("resultMessage")==null? "整改已下发" : map.get("resultMessage").toString();
		
		 Map<String,Object> variables=new HashMap<>();
		 variables.put("result", result);
		 variables.put("resultMessage", resultMessage);
		 
		 WorkFlowRpcService workflowService = rpcProxy.create(WorkFlowRpcService.class);
		 workflowService.complete(taskId, variables);
		 /*
		  * 如果result为true，保存合格审批信息(含附件)；如果result为false，监理或项目办开始整改下发流程。
		  */
		 if(result){//通过
			 BusiQualityConstrucationInspectionPassRecordDto construcationInspectionPassRecordDto = BeanCopy.getInstance()//
					 .convert(map, BusiQualityConstrucationInspectionPassRecordDto.class);
			 construcationInspectionPassRecordDto.setConstructionInspectionId(busiId);
			 construcationInspectionPassRecordDto.setFlowResult(result ? "1" : "0");
			 construcationInspectionPassRecordDto.setFlowMessage(resultMessage);
			 busiQualityConstrucationInspectionPassRecordService.addConstrucationInspectionPassRecord(construcationInspectionPassRecordDto);
		 }else{//不通过
			 BusiQualityConstrucationInspectionPassRecordDto construcationInspectionPassRecordDto = BeanCopy.getInstance()//
					 .convert(map, BusiQualityConstrucationInspectionPassRecordDto.class);
			 construcationInspectionPassRecordDto.setConstructionInspectionId(busiId);
			 construcationInspectionPassRecordDto.setFlowResult(result ? "1" : "0");
			 construcationInspectionPassRecordDto.setFlowMessage(resultMessage);
			 busiQualityConstrucationInspectionPassRecordService.addConstrucationInspectionPassRecord(construcationInspectionPassRecordDto);
			 
			 BusiQualityConstructionInspection constructionInspection = busiQualityConstructionInspectionDao.findBusiQualityConstructionInspectionById(busiId);
			 BusiQualityRectificationDto rectificationDto = BeanCopy.getInstance().convert(map, BusiQualityRectificationDto.class);
			 rectificationDto.setDataSource("construction_inspection");
			 rectificationDto = baseQualityRectificationService.saveQualityRectification(rectificationDto);
			 constructionInspection.setInspectionCode(rectificationDto.getRectificationCode());
			 busiQualityConstructionInspectionDao.saveOrUpdateBusiQualityConstructionInspection(constructionInspection);
		 }
	}


	@Override
	public List<BusiQualityConstructionInspectionDto> getConInspectionByInsCode(String inspectionCode) {
		List<BusiQualityConstructionInspection> constructionInspections = busiQualityConstructionInspectionDao//
								.findBusiQualityConstructionInspectionByCondition("inspectionCode",inspectionCode);
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("org.name", "orgName").addFieldMap("org.id", "orgId")//
				.addFieldMap("tendersBranch.tendersBranchName", "tendersBranchName")//
				.convertList(constructionInspections, BusiQualityConstructionInspectionDto.class);
	}
	
	@Override
	public void afterFlowEndChangeConstrctionInspectionStatus(String inspectionCode, int result) {
		List<BusiQualityConstructionInspection> constructionInspections = busiQualityConstructionInspectionDao//
				.findBusiQualityConstructionInspectionByCondition("inspectionCode",inspectionCode);
		if(constructionInspections!=null){
			for(BusiQualityConstructionInspection entity : constructionInspections){
				entity.setApprovalStatus(String.valueOf(result));
				busiQualityConstructionInspectionDao.saveOrUpdateBusiQualityConstructionInspection(entity);
			}
		}
		
	}


	@Override
	public Boolean isOrNotTenderBranchId(Long tid) {
		Boolean boo = true;
		List<BusiQualityConstructionInspection> constructionInspections = busiQualityConstructionInspectionDao//
							.findQualityConstructionInspectionByTid(tid);
		if(constructionInspections==null || constructionInspections.size()==0){
			boo = false;
		}
		return boo;
	}
}
