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
import com.huatek.busi.dao.contract.BusiContractTendersContractDao;
import com.huatek.busi.dto.contract.BusiContractTendersContractDto;
import com.huatek.busi.model.contract.BusiContractTendersContract;
import com.huatek.busi.service.contract.BusiContractTendersContractService;
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
 * @ClassName: BusiContractTendersContractDto
 * @Description: 标段合同表 (施工合同)服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 13:32:35
 * @version: 1.0
 */
@Service("busiContractTendersContractServiceImpl")
@Transactional
public class BusiContractTendersContractServiceImpl implements BusiContractTendersContractService,IProcessListener {
	
	private static final Logger log = LoggerFactory.getLogger(BusiContractTendersContractServiceImpl.class);
	
	@Autowired
	private BusiContractTendersContractDao busiContractTendersContractDao;
	
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
	
	/** 
	* @Title: saveBusiContractTendersContractDto 
	* @Description: 保存 标段合同表 (施工合同) 数据信息
	* @param   busiContractTendersContractDto
	* @return  void    
	*/ 
	@Override
	public void saveBusiContractTendersContractDto(BusiContractTendersContractDto busiContractTendersContractDto)  {
		log.debug("save busiContractTendersContractDto @" + busiContractTendersContractDto);
		//根据页面传进来的值设置保存的值信息  
		BusiContractTendersContract busiContractTendersContract = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg")//这里map的key要写model里面的子对象名，不能写子对象的id
																						/**建设单位 */
																						.addFieldMap("constructionCompanyCode", "constructionOrg")
																						/**监理单位**/
																						.addFieldMap("supervisionCompanyCode", "supervisionOrg")
																						/**施工单位*/
																						.addFieldMap("buildCompanyCode", "buildOrg")
																						.convert(busiContractTendersContractDto, BusiContractTendersContract.class);
		//保存之前操作
		beforeSave(busiContractTendersContractDto, busiContractTendersContract);
		//进行持久化保存
		busiContractTendersContractDao.persistentBusiContractTendersContract(busiContractTendersContract);
		log.debug("saved entityDto id is @" + busiContractTendersContract.getId());
	}
	
	/** 
	* @Title: getBusiContractTendersContractDtoById 
	* @Description: 根据ID获取标段合同表 (施工合同) 数据的Dto对象 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	@Override
	public BusiContractTendersContractDto getBusiContractTendersContractDtoById(Long id) {
		log.debug("get busiContractTendersContract by id@" + id);
		BusiContractTendersContract busiContractTendersContract = busiContractTendersContractDao.findBusiContractTendersContractById(id);
		return BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields)/**组织机构**/
									/**建设单位 */
									.addFieldMap("constructionOrg.id", "constructionCompanyCode")
									.addFieldMap("constructionOrg.name", "constructionCompanyName")
									/**监理单位**/
									.addFieldMap("supervisionOrg.id", "supervisionCompanyCode")
									.addFieldMap("supervisionOrg.name", "supervisionCompanyName")
									/**施工单位*/
									.addFieldMap("buildOrg.id", "buildCompanyCode")
									.addFieldMap("buildOrg.name", "buildCompanyName")
									.convert(busiContractTendersContract, BusiContractTendersContractDto.class);
	}
	
	/** 
	* @Title: updateBusiContractTendersContract 
	* @Description:  更新标段合同表 (施工合同) 数据信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	@Override
	public void updateBusiContractTendersContract(Long id, BusiContractTendersContractDto busiContractTendersContractDto, String[] ignoreTargetFields) {
		log.debug("upadte entityDto by id@" + id);
		BusiContractTendersContract busiContractTendersContract = busiContractTendersContractDao.findBusiContractTendersContractById(id);
		//进行持久化保存
		BeanCopy.getInstance().addIgnoreFields(ignoreTargetFields).mapIgnoreId(busiContractTendersContractDto, busiContractTendersContract);
		busiContractTendersContract.setApprovalStatus(Constant.FLOW_STATUS_UNAPPROVED);// 审批状态-未审批
		busiContractTendersContractDao.persistentBusiContractTendersContract(busiContractTendersContract);
	}
	
	/** 
	* @Title: deleteBusiContractTendersContract 
	* @Description:  删除标段合同表 (施工合同) 数据信息
	* @param    id
	* @return  void    
	*/
	@Override
	public void deleteBusiContractTendersContract(Long id) {
		log.debug("delete busiContractTendersContract by id@" + id);
		beforeRemove(id);
		BusiContractTendersContract busiContractTendersContract = busiContractTendersContractDao.findBusiContractTendersContractById(id);
		if (null == busiContractTendersContract) {
			throw new ResourceNotFoundException(id);
		}else{
			busiContractTendersContract.setIsDelete(Constant.DELETE_STATUS_DELETED);//已软删除
			busiContractTendersContractDao.persistentBusiContractTendersContract(busiContractTendersContract);//执行更新操作，不执行物理删除
			//busiContractTendersContractDao.deleteBusiContractTendersContract(busiContractTendersContract);
		}
	}
	
	/** 
	* @Title:  getAllBusiContractTendersContractPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractTendersContractDto>    
	*/ 
	@Override
	public DataPage<BusiContractTendersContractDto> getAllBusiContractTendersContractPage(QueryPage queryPage) {
		queryPage.setSqlCondition(" busiFwOrg.level3 ="+UserUtil.getUser().getCurrProId()+" ");
		DataPage<BusiContractTendersContract> busiContractTendersContractDataPage = busiContractTendersContractDao.getAllBusiContractTendersContract(queryPage);
		return BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertPage(busiContractTendersContractDataPage, BusiContractTendersContractDto.class);
	}
	
	/** 
	* @Title:  getAllBusiContractTendersContractDto 
	* @Description: 获取所有的标段合同表 (施工合同) 数据
	* @param      
	* @return  List<BusiContractTendersContractDto>    
	* @throws 
	*/
	@Override
	public List<BusiContractTendersContractDto> getAllBusiContractTendersContractDto() {
		List<BusiContractTendersContract> busiContractTendersContractList = busiContractTendersContractDao.findAllBusiContractTendersContract();
		return BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertList(busiContractTendersContractList, BusiContractTendersContractDto.class);
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
	* @param    busiContractTendersContractDto
	* @param    busiContractTendersContract
	* @return  void    
	* @
	*/
	private void beforeSave(BusiContractTendersContractDto entityDto, BusiContractTendersContract busiContractTendersContract) {
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		busiContractTendersContract.setApprovalStatus(Constant.FLOW_STATUS_UNAPPROVED);// 审批状态-未审批
		busiContractTendersContract.setCreater(currentUser.getId());// 创建人id
		busiContractTendersContract.setCreaterName(currentUser.getUserName());// 创建人姓名
		busiContractTendersContract.setCreateTime(new Date());// 创建时间
		busiContractTendersContract.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
	}
	
	/**
	 * 流程申请
	 * @param id
	 */
	@Override
	public void applyBusiContractTendersContract(Long id){
		BusiContractTendersContract busiContractTendersContract = busiContractTendersContractDao.findBusiContractTendersContractById(id);
		//调用工作流RPC
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
		Map<String,Object> variables = new HashMap<String, Object>();
		variables.put("title",  "合同审批："+busiContractTendersContract.getContractCnName());
		String flowInstanceId = workFlowRpcService.startProcessInstanceByKey("busi_contract_tenders_contract",id, variables);
		if(!StringUtils.isEmpty(flowInstanceId)){
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractTendersContract.setApplyTime(new Date());// 申请时间
			busiContractTendersContract.setApplyUserId(currentUser.getId());// 申请人ID
			busiContractTendersContract.setApplyUserName(currentUser.getUserName());// 申请人名称
			busiContractTendersContract.setApprovalStatus(Constant.FLOW_STATUS_INAPPROVAL);// 审批状态-审批中
			busiContractTendersContract.setFlowInstanceId(Long.valueOf(flowInstanceId));// 流程实例ID
			//进行持久化保存
			busiContractTendersContractDao.persistentBusiContractTendersContract(busiContractTendersContract);
		}
	}
	
	/**
	 * 审批流程处理
	 * @param taskId
	 * @param id
	 * @param busiQumClaimWaybillDetailDto
	 */
	public void workflowSubmit(String taskId, Long id,BusiContractTendersContractDto busiContractTendersContractDto){
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
        Map<String,Object> variables= new HashMap <String, Object>();
        variables.put("result", busiContractTendersContractDto.getFlowResult());
        variables.put("resultMessage", busiContractTendersContractDto.getFlowMessage());
        //流程向前推进
        workFlowRpcService.complete(taskId, variables);
	}

	/**
	 * 流程结束处理
	 */
	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		BusiContractTendersContract busiContractTendersContract = busiContractTendersContractDao.findBusiContractTendersContractByProcessInstanceId(processInstanceId);
		if(null == busiContractTendersContract){
			throw new BusinessRuntimeException("合同审批:根据流程ID:"+processInstanceId+",查询合同业务数据数据出错", "-1");
		}else{
			if (variables.get("result").equals(true)) {
				busiContractTendersContract.setFlowResult(Constant.FLOW_STATUS_PASSED);// //审批状态-已通过
			}else{
				busiContractTendersContract.setFlowResult(Constant.FLOW_STATUS_REBUT);//审批状态-已驳回
			}
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractTendersContract.setApprovalStatus(busiContractTendersContract.getFlowResult());//审批状态-已审批
			busiContractTendersContract.setApprovalTime(new Date());// 审批完成时间
			busiContractTendersContract.setApprovalUserId(currentUser.getId());// 审批人ID
			busiContractTendersContract.setApprovalUserName(currentUser.getUserName());// 审批人名称
			busiContractTendersContract.setFlowMessage((String) variables.get("resultMessage"));// 审批意见
		}
		return null;
	}
}
