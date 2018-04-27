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
import com.huatek.busi.dao.contract.BusiContractContractChangeDao;
import com.huatek.busi.dto.contract.BusiContractContractChangeDto;
import com.huatek.busi.model.contract.BusiContractContractChange;
import com.huatek.busi.service.contract.BusiContractContractChangeService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.workflow.service.IProcessListener;
import com.huatek.workflow.service.WorkFlowRpcService;

@Service("busiContractContractChangeServiceImpl")
@Transactional
public class BusiContractContractChangeServiceImpl implements BusiContractContractChangeService,IProcessListener {
	
	@Autowired
	private BusiContractContractChangeDao busiContractContractChangeDao;
	
	@Autowired
    private RpcProxy rpcProxy;
	
	@Autowired
    private FwOrgService fwOrgService;//机构服务类
	
	/**
	 * 机构对象的机构ID、机构名称转换至Dto对象的数据常量
	 */
	public static final Map<String,String> entityToFields = new HashMap<String,String>();
	static {
		entityToFields.put("busiFwOrg.name", "orgName");
		entityToFields.put("busiFwOrg.id", "orgId");
	}
	
	@Override
	public void saveBusiContractContractChangeDto(BusiContractContractChangeDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiContractContractChange entity = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg")//这里map的key要写model里面的子对象名，不能写子对象的id
																  .convert(entityDto, BusiContractContractChange.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiContractContractChangeDao.persistentBusiContractContractChange(entity);
	}
	
	
	@Override
	public BusiContractContractChangeDto getBusiContractContractChangeDtoById(Long id) {
		BusiContractContractChange entity = busiContractContractChangeDao.findBusiContractContractChangeById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		BusiContractContractChangeDto entityDto = BeanCopy.getInstance().addFieldMaps(entityToFields)/**组织机构**/
																		.convert(entity, BusiContractContractChangeDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiContractContractChange(Long id, BusiContractContractChangeDto entityDto) {
		BusiContractContractChange entity = busiContractContractChangeDao.findBusiContractContractChangeById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiContractContractChangeDao.persistentBusiContractContractChange(entity);
	}
	
	
	@Override
	public void deleteBusiContractContractChange(Long id) {
		beforeRemove(id);
		BusiContractContractChange entity = busiContractContractChangeDao.findBusiContractContractChangeById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiContractContractChangeDao.deleteBusiContractContractChange(entity);
	}
	
	@Override
	public DataPage<BusiContractContractChangeDto> getAllBusiContractContractChangePage(QueryPage queryPage) {
		queryPage.setSqlCondition(" busiFwOrg.level3 ="+UserUtil.getUser().getCurrProId()+" ");
		DataPage<BusiContractContractChange> dataPage = busiContractContractChangeDao.getAllBusiContractContractChange(queryPage);
		DataPage<BusiContractContractChangeDto> datPageDto = BeanCopy.getInstance().addFieldMaps(entityToFields).convertPage(dataPage, BusiContractContractChangeDto.class);
		return datPageDto;
	}
	
	@Override
	public List<BusiContractContractChangeDto> getAllBusiContractContractChangeDto() {
		List<BusiContractContractChange> entityList = busiContractContractChangeDao.findAllBusiContractContractChange();
		List<BusiContractContractChangeDto> dtos = BeanCopy.getInstance().addFieldMaps(entityToFields).convertList(entityList, BusiContractContractChangeDto.class);
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
	* @param    busiContractContractChangeDto
	* @param    busiContractContractChange
	* @return  void    
	* @
	*/
	private void beforeSave(BusiContractContractChangeDto entityDto, BusiContractContractChange entity) {
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		entity.setApprovalStatus(Constant.FLOW_STATUS_UNAPPROVED);// 审批状态-未审批
		entity.setCreater(currentUser.getId());// 创建人id
		entity.setCreaterName(currentUser.getUserName());// 创建人姓名
		entity.setCreateTime(new Date());// 创建时间
		entity.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
	}

	/**
	 * 流程申请
	 */
	@Override
	public void applyBusiContractContractChange(Long id) {
		BusiContractContractChange busiContractContractChange = busiContractContractChangeDao.findBusiContractContractChangeById(id);
		//调用工作流RPC
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
		Map<String,Object> variables = new HashMap<String, Object>();
		variables.put("title",  "合同变更审批："+busiContractContractChange.getBusiFwOrg().getName());
		String flowInstanceId = workFlowRpcService.startProcessInstanceByKey("busi_contract_contract_change",id, variables);
		if(!StringUtils.isEmpty(flowInstanceId)){
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractContractChange.setApplyTime(new Date());// 申请时间
			busiContractContractChange.setApplyUserId(currentUser.getId());// 申请人ID
			busiContractContractChange.setApplyUserName(currentUser.getUserName());// 申请人名称
			busiContractContractChange.setApprovalStatus(Constant.FLOW_STATUS_INAPPROVAL);// 审批状态-审批中
			busiContractContractChange.setFlowInstanceId(Long.valueOf(flowInstanceId));// 流程实例ID
			//进行持久化保存
			busiContractContractChangeDao.persistentBusiContractContractChange(busiContractContractChange);
		}
	}
	
	/**
	 * 工作流审批结束
	 */
	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		BusiContractContractChange busiContractContractChange = busiContractContractChangeDao.findBusiContractContractChangeByProcessInstanceId(processInstanceId);
		if(null == busiContractContractChange){
			throw new BusinessRuntimeException("合同变更审批:根据流程ID:"+processInstanceId+",查询合同变更审批业务数据数据出错", "-1");
		}else{
			if (variables.get("result").equals(true)) {
				busiContractContractChange.setFlowResult(Constant.FLOW_STATUS_PASSED);// //审批状态-已通过
			}else{
				busiContractContractChange.setFlowResult(Constant.FLOW_STATUS_REBUT);//审批状态-已驳回
			}
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractContractChange.setApprovalStatus(busiContractContractChange.getFlowResult());//审批状态-已审批
			busiContractContractChange.setApprovalTime(new Date());// 审批完成时间
			busiContractContractChange.setApprovalUserId(currentUser.getId());// 审批人ID
			busiContractContractChange.setApprovalUserName(currentUser.getUserName());// 审批人名称
			busiContractContractChange.setFlowMessage((String) variables.get("resultMessage"));// 审批意见
		}
		return null;
	}
}
