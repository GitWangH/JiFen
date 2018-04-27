package com.huatek.busi.service.impl.contract;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.contract.BusiContractTendersContractDetailApproveFlowInfoDao;
import com.huatek.busi.dao.contract.BusiContractTendersContractDetailDao;
import com.huatek.busi.dto.contract.BusiContractTendersContractDetailDto;
import com.huatek.busi.model.contract.BusiContractTendersContractDetail;
import com.huatek.busi.model.contract.BusiContractTendersContractDetailApproveFlowInfo;
import com.huatek.busi.service.contract.BusiContractTendersContractDetailCheckService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.workflow.service.IProcessListener;
import com.huatek.workflow.service.WorkFlowRpcService;

/**
 * @ClassName: BusiContractTendersContractDetailServiceImpl
 * @Description: 标段合同表 (施工合同)复核清单管理服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-01 15:12:35
 * @version: 1.0
 */
@Service("busiContractTendersContractDetailCheckServiceImpl")
@Transactional
public class BusiContractTendersContractDetailCheckServiceImpl implements BusiContractTendersContractDetailCheckService,IProcessListener {
	
	@Autowired
	private BusiContractTendersContractDetailDao busiContractTendersContractDetailDao;
	
	@Autowired
	private BusiContractTendersContractDetailApproveFlowInfoDao busiContractTendersContractDetailApproveFlowInfoDao;
	
	@Autowired
    private RpcProxy rpcProxy;
	
	@Autowired
    private FwOrgService fwOrgService;//机构服务类
	
	
	/**
	 * 查询复核清单列表树数据(目前与合同清单列表查询一样)
	 * @param queryPage
	 * @return
	 */
	@Override
	public List<BusiContractTendersContractDetailDto> getAllTopLevelBusiContractTendersContractDetailDto(QueryPage queryPage){
		/*String hql = " FROM BusiContractTendersContractDetail cd"
				   + "  WHERE groupLevel = 1"
				   + "    AND isDelete = " + Constant.DELETE_STATUS_NOT_DELETED
				   +"     AND EXISTS("
				   +"           SELECT 1 FROM BusiContractTendersContractDetailApproveFlowInfo cf"
				   +"                   WHERE cf.orgId = cd.busiFwOrg.id"
				   +"                     AND cf.detailApprovalStatus = 'flow_passed'";*/
		List<QueryParam> queryParamList = queryPage.getQueryParamList();  
		String orgId = "";
		if(null != queryParamList && queryParamList.size() > 0){
			for(QueryParam queryParam:queryParamList){
				if("busiFwOrg.id".equals(queryParam.getField())){
					orgId = queryParam.getValue();
				}
			}
		}
		//复核模块只能看到合同清单已审批的数据
		queryParamList.add(new QueryParam("groupLevel", "long", "=", "1"));
		queryParamList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
		if(StringUtils.isNotEmpty(orgId)){
			queryPage.setSqlCondition(" EXISTS(SELECT 1 FROM BusiContractTendersContractDetailApproveFlowInfo  WHERE orgId = " + orgId + "  AND detailApprovalStatus = 'flow_passed')");
		}
		List<BusiContractTendersContractDetail> busiContractTendersContractDetailList = busiContractTendersContractDetailDao.findAllBusiContractTendersContractDetail(queryPage);
		return BeanCopy.getInstance().convertList(busiContractTendersContractDetailList, BusiContractTendersContractDetailDto.class);
	}
	
	
	/**
	 * 流程申请
	 * @param id
	 */
	@Override
	public void applyBusiContractTendersContractDetailCheckByOrgId(Long orgId){
		//调用工作流RPC
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
		Map<String,Object> variables = new HashMap<String, Object>();
		FwOrgDto fwOrgDto = fwOrgService.getOrgById(orgId);
		variables.put("title",  "合同清单复核审批：[标段机构:"+fwOrgDto.getName()+"("+fwOrgDto.getOrgCode()+")]");
		String flowInstanceId = workFlowRpcService.startProcessInstanceByKey("busi_contract_tenders_contract_detail_check",orgId, variables);
		if(!StringUtils.isEmpty(flowInstanceId)){
			BusiContractTendersContractDetailApproveFlowInfo busiContractTendersContractDetailApproveFlowInfo = busiContractTendersContractDetailApproveFlowInfoDao.findBusiContractTendersContractDetailApproveFlowInfoByOrgId(orgId);
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractTendersContractDetailApproveFlowInfo.setCheckApplyTime(new Date());// 申请时间
			busiContractTendersContractDetailApproveFlowInfo.setCheckApplyUserId(currentUser.getId());// 申请人ID
			busiContractTendersContractDetailApproveFlowInfo.setCheckApplyUserName(currentUser.getUserName());// 申请人名称
			busiContractTendersContractDetailApproveFlowInfo.setCheckApprovalStatus(Constant.FLOW_STATUS_INAPPROVAL);// 审批状态-审批中
			busiContractTendersContractDetailApproveFlowInfo.setCheckFlowInstanceId(Long.valueOf(flowInstanceId));// 流程实例ID
			
			busiContractTendersContractDetailApproveFlowInfo.setModifer(currentUser.getId());// 创建人id
			busiContractTendersContractDetailApproveFlowInfo.setModifierName(currentUser.getUserName());// 创建人姓名
			busiContractTendersContractDetailApproveFlowInfo.setModifyTime(new Date());// 创建时间
			//进行持久化保存
			busiContractTendersContractDetailApproveFlowInfoDao.persistentBusiContractTendersContractDetailApproveFlowInfo(busiContractTendersContractDetailApproveFlowInfo);
		}
	}
	
	/**
	 * 审批流程处理
	 * @param taskId
	 * @param id
	 * @param busiQumClaimWaybillDetailDto
	 */
	public void workflowSubmit(String taskId, Long id,BusiContractTendersContractDetailApproveFlowInfo busiContractTendersContractDetailApproveFlowInfo){
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
        Map<String,Object> variables= new HashMap <String, Object>();
        variables.put("result", busiContractTendersContractDetailApproveFlowInfo.getDetailFlowResult());
        variables.put("resultMessage", busiContractTendersContractDetailApproveFlowInfo.getDetailFlowMessage());
        //流程向前推进
        workFlowRpcService.complete(taskId, variables);
	}
	
	/**
	 * 工作流审批结束
	 */
	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		BusiContractTendersContractDetailApproveFlowInfo busiContractTendersContractDetailApproveFlowInfo = busiContractTendersContractDetailApproveFlowInfoDao.findBusiContractTendersContractDetailApproveFlowInfoByProcessInstanceIdAndType(Long.valueOf(processInstanceId), "check");
		if(null == busiContractTendersContractDetailApproveFlowInfo){
			throw new BusinessRuntimeException("合同清单复核审批:根据流程ID:"+processInstanceId+",查询合同业务数据数据出错", "-1");
		}else{
			if (variables.get("result").equals(true)) {
				busiContractTendersContractDetailApproveFlowInfo.setCheckFlowResult(Constant.FLOW_STATUS_PASSED);// //审批状态-已通过
			}else{
				busiContractTendersContractDetailApproveFlowInfo.setCheckFlowResult(Constant.FLOW_STATUS_REBUT);//审批状态-已驳回
			}
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractTendersContractDetailApproveFlowInfo.setCheckApprovalStatus(busiContractTendersContractDetailApproveFlowInfo.getCheckFlowResult());//审批状态-已审批
			busiContractTendersContractDetailApproveFlowInfo.setCheckApprovalTime(new Date());// 审批完成时间
			busiContractTendersContractDetailApproveFlowInfo.setCheckApprovalUserId(currentUser.getId());// 审批人ID
			busiContractTendersContractDetailApproveFlowInfo.setCheckApprovalUserName(currentUser.getUserName());// 审批人名称
			busiContractTendersContractDetailApproveFlowInfo.setCheckFlowMessage((String) variables.get("resultMessage"));// 审批意见
		}
		return null;
	}
	
}
