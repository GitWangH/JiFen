package com.huatek.busi.service.impl.contract;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.contract.BusiContractSupervisorContractDao;
import com.huatek.busi.dto.contract.BusiContractSupervisorContractDto;
import com.huatek.busi.model.contract.BusiContractSupervisorContract;
import com.huatek.busi.service.contract.BusiContractSupervisorContractService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.workflow.service.IProcessListener;
import com.huatek.workflow.service.WorkFlowRpcService;

/**
 * @ClassName: BusiContractSupervisorContractServiceImpl
 * @Description: 监理合同
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 17:25:35
 * @version: 1.0
 */
@Service("busiContractSupervisorContractServiceImpl")
@Transactional
public class BusiContractSupervisorContractServiceImpl implements BusiContractSupervisorContractService,IProcessListener {
	
	private static final Logger log = LoggerFactory.getLogger(BusiContractSupervisorContractServiceImpl.class);
	
	@Autowired
	private BusiContractSupervisorContractDao busiContractSupervisorContractDao;
	
	@Autowired
    private RpcProxy rpcProxy;
	
	/**
	 * 机构对象的机构ID、机构名称转换至Dto对象的数据常量
	 */
	public static final Map<String,String> entityToFields = new HashMap<String,String>();
	static {
		entityToFields.put("busiFwOrg.name", "orgName");
		entityToFields.put("busiFwOrg.id", "orgId");
	}
	
	@Override
	public void saveBusiContractSupervisorContractDto(BusiContractSupervisorContractDto entityDto)  {
		log.debug("save busiContractSupervisorContractDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiContractSupervisorContract entity = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg")//这里map的key要写model里面的子对象名，不能写子对象的id
														.convert(entityDto, BusiContractSupervisorContract.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiContractSupervisorContractDao.persistentBusiContractSupervisorContract(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiContractSupervisorContractDto getBusiContractSupervisorContractDtoById(Long id) {
		log.debug("get busiContractSupervisorContract by id@" + id);
		BusiContractSupervisorContract entity = busiContractSupervisorContractDao.findBusiContractSupervisorContractById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiContractSupervisorContractDto entityDto = BeanCopy.getInstance().addFieldMap("busiFwOrg.id", "orgId").convert(entity, BusiContractSupervisorContractDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiContractSupervisorContract(Long id, BusiContractSupervisorContractDto entityDto, String[] ignoreTargetFields) {
		log.debug("upadte entityDto by id@" + id);
		BusiContractSupervisorContract entity = busiContractSupervisorContractDao.findBusiContractSupervisorContractById(id);
		BeanCopy.getInstance().addIgnoreFields(ignoreTargetFields).mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiContractSupervisorContractDao.persistentBusiContractSupervisorContract(entity);
	}
	
	
	
	@Override
	public void deleteBusiContractSupervisorContract(Long id) {
		log.debug("delete busiContractSupervisorContract by id@" + id);
		beforeRemove(id);
		BusiContractSupervisorContract busiContractSupervisorContract = busiContractSupervisorContractDao.findBusiContractSupervisorContractById(id);
		if (null == busiContractSupervisorContract) {
			throw new ResourceNotFoundException(id);
		}else{
			busiContractSupervisorContract.setIsDelete(Constant.DELETE_STATUS_DELETED);//已软删除
//			busiContractSupervisorContractDao.deleteBusiContractSupervisorContract(busiContractSupervisorContract);
			busiContractSupervisorContractDao.persistentBusiContractSupervisorContract(busiContractSupervisorContract);
		}
	}
	
	@Override
	public DataPage<BusiContractSupervisorContractDto> getAllBusiContractSupervisorContractPage(QueryPage queryPage) {
		queryPage.setSqlCondition(" busiFwOrg.level3 ="+UserUtil.getUser().getCurrProId()+" ");
		DataPage<BusiContractSupervisorContract> dataPage = busiContractSupervisorContractDao.getAllBusiContractSupervisorContract(queryPage);
		DataPage<BusiContractSupervisorContractDto> datPageDto = BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertPage(dataPage, BusiContractSupervisorContractDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiContractSupervisorContractDto> getAllBusiContractSupervisorContractDto() {
		List<BusiContractSupervisorContract> entityList = busiContractSupervisorContractDao.findAllBusiContractSupervisorContract();
		List<BusiContractSupervisorContractDto> dtos = BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertList(entityList, BusiContractSupervisorContractDto.class);
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
	* @param    busiContractSupervisorContractDto
	* @param    busiContractSupervisorContract
	* @return  void    
	* @
	*/
	private void beforeSave(BusiContractSupervisorContractDto busiContractSupervisorContractDto, BusiContractSupervisorContract busiContractSupervisorContract) {
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		busiContractSupervisorContract.setApprovalStatus(Constant.FLOW_STATUS_UNAPPROVED);// 审批状态-未审批
		busiContractSupervisorContract.setCreater(currentUser.getId());// 创建人id
		busiContractSupervisorContract.setCreaterName(currentUser.getUserName());// 创建人姓名
		busiContractSupervisorContract.setCreateTime(new Date());// 创建时间
		busiContractSupervisorContract.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
	}
	
	/**
	 * 流程申请
	 * @param id
	 */
	@Override
	public void applyBusiContractSupervisorContract(Long id){
		BusiContractSupervisorContract busiContractSupervisorContract = busiContractSupervisorContractDao.findBusiContractSupervisorContractById(id);
		//调用工作流RPC
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
		Map<String,Object> variables = new HashMap<String, Object>();
		variables.put("title",  "监理合同审批："+busiContractSupervisorContract.getContractName());
		String flowInstanceId = workFlowRpcService.startProcessInstanceByKey("busi_contract_supervisor_contract",id, variables);
		if(!StringUtils.isEmpty(flowInstanceId)){
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractSupervisorContract.setApplyTime(new Date());// 申请时间
			busiContractSupervisorContract.setApplyUserId(currentUser.getId());// 申请人ID
			busiContractSupervisorContract.setApplyUserName(currentUser.getUserName());// 申请人名称
			busiContractSupervisorContract.setApprovalStatus(Constant.FLOW_STATUS_INAPPROVAL);// 审批状态-审批中
			busiContractSupervisorContract.setFlowInstanceId(Long.valueOf(flowInstanceId));// 流程实例ID
			//进行持久化保存
			busiContractSupervisorContractDao.persistentBusiContractSupervisorContract(busiContractSupervisorContract);;
		}
	}
	
	/**
	 * 审批流程处理
	 * @param taskId
	 * @param id
	 * @param busiQumClaimWaybillDetailDto
	 */
	public void workflowSubmit(String taskId, Long id,BusiContractSupervisorContractDto busiContractSupervisorContractDto){
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
        Map<String,Object> variables= new HashMap <String, Object>();
        variables.put("result", busiContractSupervisorContractDto.getFlowResult());
        variables.put("resultMessage", busiContractSupervisorContractDto.getFlowMessage());
        //流程向前推进
        workFlowRpcService.complete(taskId, variables);
	}

	/**
	 * 流程结束处理
	 */
	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		BusiContractSupervisorContract busiContractSupervisorContract = busiContractSupervisorContractDao.findBusiContractSupervisorContractByProcessInstanceId(processInstanceId);
		if(null == busiContractSupervisorContract){
			throw new BusinessRuntimeException("监理合同审批:根据流程ID:"+processInstanceId+",查询监理合同业务数据数据出错", "-1");
		}else{
			if (variables.get("result").equals(true)) {
				busiContractSupervisorContract.setFlowResult(Constant.FLOW_STATUS_PASSED);// //审批状态-已通过
			}else{
				busiContractSupervisorContract.setFlowResult(Constant.FLOW_STATUS_REBUT);//审批状态-已驳回
			}
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractSupervisorContract.setApprovalStatus(busiContractSupervisorContract.getFlowResult());//审批状态-已审批
			busiContractSupervisorContract.setApprovalTime(new Date());// 审批完成时间
			busiContractSupervisorContract.setApprovalUserId(currentUser.getId());// 审批人ID
			busiContractSupervisorContract.setApprovalUserName(currentUser.getUserName());// 审批人名称
			busiContractSupervisorContract.setFlowMessage((String) variables.get("resultMessage"));// 审批意见
		}
		return null;
	}
}
