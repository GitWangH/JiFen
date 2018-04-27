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
import com.huatek.busi.dao.quality.BusiQualityMonthlyReportManagementDao;
import com.huatek.busi.dto.quality.BusiQualityMonthlyReportManagementDto;
import com.huatek.busi.model.quality.BusiQualityMonthlyReportManagement;
import com.huatek.busi.service.quality.BusiQualityMonthlyReportManagementService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.workflow.service.IProcessListener;
import com.huatek.workflow.service.WorkFlowRpcService;

@Service("BusiQualityMonthlyReportManagementServiceImpl")
@Transactional
public class BusiQualityMonthlyReportManagementServiceImpl implements BusiQualityMonthlyReportManagementService ,IProcessListener{
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityMonthlyReportManagementServiceImpl.class);
	
	@Autowired
	private BusiQualityMonthlyReportManagementDao busiQualityMonthlyReportManagementDao;
	@Autowired
	private RpcProxy rpcProxy;
	@Autowired
	private OperationService oprationService;
	
	@Override
	public void saveBusiQualityMonthlyReportManagementDto(BusiQualityMonthlyReportManagementDto entityDto)  {
		log.debug("save busiQualityMonthlyReportManagementDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityMonthlyReportManagement entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.addFieldMap("orgId", "org")
				.convert(entityDto, BusiQualityMonthlyReportManagement.class);
		entity.setIsDelete(0);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityMonthlyReportManagementDao.persistentBusiQualityMonthlyReportManagement(entity);
		oprationService.logOperation("月报添加");
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualityMonthlyReportManagementDto getBusiQualityMonthlyReportManagementDtoById(Long id) {
		log.debug("get busiQualityMonthlyReportManagement by id@" + id);
		BusiQualityMonthlyReportManagement entity = busiQualityMonthlyReportManagementDao.findBusiQualityMonthlyReportManagementById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiQualityMonthlyReportManagementDto entityDto = null;
			entityDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
					.addFieldMap("org.id", "orgId")//
					.addFieldMap("org.name", "orgName")//
					.convert(entity,BusiQualityMonthlyReportManagementDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityMonthlyReportManagement(Long id, BusiQualityMonthlyReportManagementDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityMonthlyReportManagement entity = busiQualityMonthlyReportManagementDao.findBusiQualityMonthlyReportManagementById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").addFieldMap("orgId", "org").mapIgnoreNull(entityDto, entity);
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiQualityMonthlyReportManagementDao.persistentBusiQualityMonthlyReportManagement(entity);
		oprationService.logOperation("月报修改");
	}
	
	
	
	@Override
	public void deleteBusiQualityMonthlyReportManagement(Long id) {
		log.debug("delete busiQualityMonthlyReportManagement by id@" + id);
		beforeRemove(id);
		BusiQualityMonthlyReportManagement entity = busiQualityMonthlyReportManagementDao.findBusiQualityMonthlyReportManagementById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		entity.setIsDelete(1);
		busiQualityMonthlyReportManagementDao.persistentBusiQualityMonthlyReportManagement(entity);
//		busiQualityMonthlyReportManagementDao.deleteBusiQualityMonthlyReportManagement(entity);
		oprationService.logOperation("月报删除");
	}
	
	@Override
	public DataPage<BusiQualityMonthlyReportManagementDto> getAllBusiQualityMonthlyReportManagementPage(QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("isDelete");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
		DataPage<BusiQualityMonthlyReportManagement> dataPage = busiQualityMonthlyReportManagementDao.getAllBusiQualityMonthlyReportManagement(queryPage);
		DataPage<BusiQualityMonthlyReportManagementDto> datPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
															.addFieldMap("org.name", "orgName")//
															.convertPage(dataPage, BusiQualityMonthlyReportManagementDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityMonthlyReportManagementDto> getAllBusiQualityMonthlyReportManagementDto() {
		List<BusiQualityMonthlyReportManagement> entityList = busiQualityMonthlyReportManagementDao.findAllBusiQualityMonthlyReportManagement();
		List<BusiQualityMonthlyReportManagementDto> dtos = DTOUtils.mapList(entityList, BusiQualityMonthlyReportManagementDto.class);
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
	* @param    busiQualityMonthlyReportManagementDto
	* @param    busiQualityMonthlyReportManagement
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityMonthlyReportManagementDto entityDto, BusiQualityMonthlyReportManagement entity) {
		//添加填报人信息
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		if(currentUser==null){
			throw new BusinessRuntimeException("请重新登录", "-1");
		}
		if(entity.getId()==null){
			entity.setWriteReportUserId(currentUser.getId());
			entity.setWriteReportUserName(currentUser.getUserName());
			entity.setWriteReportTime(new Date());
			entity.setApprovalStatus(Constant.FLOW_STATUS_UNAPPROVED);
		}
	}

	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		BusiQualityMonthlyReportManagement monthReport = busiQualityMonthlyReportManagementDao//
				.findBusiQualityMonthlyReportManagementByCondition("flowInstanceId",processInstanceId);
		if(monthReport!=null){
			Boolean result = Boolean.valueOf(variables.get("result").toString());
			monthReport.setApprovalTime(new Date());
			monthReport.setApprovalUserId(ThreadLocalClient.get().getOperator().getId());
			monthReport.setApprovalUserName(ThreadLocalClient.get().getOperator().getUserName());
			monthReport.setFlowMessage(variables.get("resultMessage").toString());
			if(result){
				monthReport.setFlowResult(Constant.FLOW_STATUS_PASSED);
				monthReport.setApprovalStatus(Constant.FLOW_STATUS_PASSED);
				oprationService.logOperation("月报审批通过");
			}else{
				monthReport.setFlowResult(Constant.FLOW_STATUS_PASSED);
				monthReport.setApprovalStatus(Constant.FLOW_STATUS_REBUT);
				oprationService.logOperation("月报审批不通过");
			}
			busiQualityMonthlyReportManagementDao.saveOrUpdateBusiQualityMonthlyReportManagement(monthReport);
			
		}
		return null;
	}


	@Override
	public void monthReportFlowStart(Long id) {
		BusiQualityMonthlyReportManagement monthReport = busiQualityMonthlyReportManagementDao.findBusiQualityMonthlyReportManagementById(id);
		if(monthReport!=null){
			WorkFlowRpcService workflowService=rpcProxy.create(WorkFlowRpcService.class);
			Map<String, Object> variables=new HashMap<String,Object>();
			variables.put("title", "月报流程审批");
			variables.put("operaterOrgType", ThreadLocalClient.get().getOperator().orgType);//用于流程中判断下发人是项目办还是监理
			String processInstanceIdString=workflowService.startProcessInstanceByKey("busi_quality_monthly_report_management", +monthReport.getId(), variables);
			monthReport.setFlowInstanceId(Long.valueOf(processInstanceIdString));
			monthReport.setApplyTime(new Date());
			monthReport.setApplyUserId(ThreadLocalClient.get().getOperator().getId());
			monthReport.setApplyUserName(ThreadLocalClient.get().getOperator().getUserName());
			monthReport.setApprovalStatus(Constant.FLOW_STATUS_INAPPROVAL);
			busiQualityMonthlyReportManagementDao.saveOrUpdateBusiQualityMonthlyReportManagement(monthReport);
			oprationService.logOperation("上报月报");
		}
	}
}
