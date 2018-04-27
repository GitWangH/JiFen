package com.huatek.busi.service.impl.quality;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.busi.model.quality.BusiQualityRectification;
import com.huatek.busi.service.quality.BaseQualityRectificationService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.service.FwOrgRpcService;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.workflow.service.WorkFlowRpcService;

/**
 * 生成整改单的公共方法
 * @author rocky_wei
 *
 */
@Service("baseQualityRectificationServiceImpl")
@Transactional
public class BaseQualityRectificationServiceImpl implements BaseQualityRectificationService{
	
	private static WebApplicationContext webContext = ContextLoader .getCurrentWebApplicationContext();
	@Autowired
	private BusiQualityRectificationDao busiQualityRectificationDao;
	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	@Autowired
	private RpcProxy rpcProxy;
	
	/**
	 * 保存整改单并上报
	 * @param rectificationDto 页面获取的整改信息
	 * @param orgId 机构编码
	 * @return rectification 整改单对象
	 */
	public BusiQualityRectificationDto saveQualityRectification(BusiQualityRectificationDto rectificationDto){
		BusiQualityRectification rectification = new BusiQualityRectification();
		if(rectificationDto!=null){
			rectification = BeanCopy.getInstance()//
					.addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(rectificationDto, BusiQualityRectification.class);
		}
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		//保存整改单
		if(rectification!=null){
			rectification.setDataSource(rectificationDto.getDataSource());
//			rectification.setRectificationCode("YP"+sdf.format(new Date()));
			rectification.setRectificationCode("R"+UserUtil.getUser().getId()+System.currentTimeMillis());
			rectification.setApplyUserId(ThreadLocalClient.get().getOperator().getId());
			rectification.setApplyUserName(ThreadLocalClient.get().getOperator().getAcctName());
			rectification.setApplyTime(new Date());
			FwOrgRpcService orgRpcService = rpcProxy.create(FwOrgRpcService.class);
			FwOrgDto org = orgRpcService.getOrgById(Long.valueOf(rectificationDto.getOrgId()));
			rectification.setOrg(BeanCopy.getInstance().convert(org, BusiFwOrg.class));
			rectification.setTenantId(ThreadLocalClient.get().getOperator().getTenantId());
			//	添加整改中状态
			rectification.setApprovalStatus(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
			busiQualityRectificationDao.saveOrUpdateBusiQualityRectification(rectification);
		}
		
		//启动工作流
		WorkFlowRpcService workflowService = rpcProxy.create(WorkFlowRpcService.class);
		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("title","整改单："+rectification.getCheckNo());
		variable.put("inspectionType","1");//整改单
		variable.put("applyOrgId",Long.valueOf(rectificationDto.getOrgId()));
		variable.put("operaterOrgType", ThreadLocalClient.get().getOperator().orgType);//用于流程中判断下发人是项目办还是监理
		String processInstanceIdString=workflowService.startProcessInstanceByKey("busi_quality_rectification_"+rectificationDto.getDataSource(), rectification.getId(), variable);
		rectification.setFlowInstanceId(Long.valueOf(processInstanceIdString));
		rectification.setFlowResult(String.valueOf(Constant.Quality.STATUS_RECTIFICATE_ING));
		
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
					.convert(rectification, BusiQualityRectificationDto.class);
	}
	
	/**
	 * 保存快捷处理
	 * @param dto 
	 * @return
	 */
	public BusiQualityQuickProcessingDto saveQualityQuickProcess(BusiQualityQuickProcessingDto quickProcessingDto){
		BusiQualityQuickProcessing quickProcess = new BusiQualityQuickProcessing();
		if(quickProcessingDto!=null){
			quickProcess = BeanCopy.getInstance()//
					.addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convert(quickProcessingDto,  BusiQualityQuickProcessing.class);
		}
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		//保存整改单
		if(quickProcess!=null){
			quickProcess.setDataSource(quickProcessingDto.getDataSource());
//			quickProcess.setQuickProcessCode("YP"+sdf.format(new Date()));
			quickProcess.setQuickProcessCode("Q"+UserUtil.getUser().getId()+System.currentTimeMillis());
			quickProcess.setQuickUserId(ThreadLocalClient.get().getOperator().getId());
			quickProcess.setApplyUserId(ThreadLocalClient.get().getOperator().getId());
			quickProcess.setApplyUserName(ThreadLocalClient.get().getOperator().getAcctName());
			quickProcess.setApplyTime(new Date());
			quickProcess.setOrgId(Long.valueOf(quickProcessingDto.getOrgId()));	
			quickProcess.setTenantId(ThreadLocalClient.get().getOperator().getTenantId());
//			添加处理中状态
			quickProcess.setApprovalStatus(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
			busiQualityQuickProcessingDao.saveOrUpdateBusiQualityQuickProcessing(quickProcess);
		}
		
		/*
		 * 快捷处理：如果处理人机构为标段，则需要进行流程审批；若处理人机构为监理和项目办，则不要流程审批。
		 */
		if("7".equals(ThreadLocalClient.get().getOperator().getOrgType())){
			WorkFlowRpcService workflowService = rpcProxy.create(WorkFlowRpcService.class);
			Map<String, Object> variable = new HashMap<String, Object>();
			variable.put("title","快捷处理："+quickProcess.getQuickUserName()+"_"+quickProcessingDto.getQuickProcessingTime());
			variable.put("inspectionType","0");//快捷处理
			variable.put("applyOrgId",Long.valueOf(quickProcess.getOrgId()));
			variable.put("operaterOrgType", ThreadLocalClient.get().getOperator().orgType);
			String processInstanceIdString=workflowService.startProcessInstanceByKey("busi_quality_quick_processing_"+quickProcessingDto.getDataSource(), quickProcess.getId(), variable);
			quickProcess.setFlowInstanceId(Long.valueOf(processInstanceIdString));
		}
		quickProcess.setFlowResult(String.valueOf(Constant.Quality.STATUS_DEAL_ING));
		
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.convert(quickProcess, BusiQualityQuickProcessingDto.class);
	}
	
}
