package com.huatek.busi.service.impl.contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.contract.BusiContractTendersBranchApproveFlowInfoDao;
import com.huatek.busi.dao.contract.BusiContractTendersBranchDao;
import com.huatek.busi.dao.contract.BusiContractTendersBranchDetailDao;
import com.huatek.busi.dao.contract.BusiContractTendersBranchImportDao;
import com.huatek.busi.dao.contract.BusiContractTendersContractDetailDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractTendersBranchDetailDto;
import com.huatek.busi.dto.contract.BusiContractTendersBranchDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.contract.BusiContractTendersBranch;
import com.huatek.busi.model.contract.BusiContractTendersBranchApproveFlowInfo;
import com.huatek.busi.model.contract.BusiContractTendersBranchDetail;
import com.huatek.busi.model.contract.BusiContractTendersBranchImport;
import com.huatek.busi.model.contract.BusiContractTendersContractDetail;
import com.huatek.busi.service.contract.BusiContractTendersBranchService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.util.DateUtil;
import com.huatek.workflow.service.IProcessListener;
import com.huatek.workflow.service.WorkFlowRpcService;

/**
 * @ClassName: BusiContractTendersBranchServiceImpl
 * @Description: 分部分项维护服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-08 13:35:35
 * @version: 1.0
 */
@Service("busiContractTendersBranchServiceImpl")
@Transactional
public class BusiContractTendersBranchServiceImpl implements BusiContractTendersBranchService,IProcessListener {
	
	@Autowired
	private BusiContractTendersBranchDao busiContractTendersBranchDao;
	
	@Autowired
	private BusiContractTendersBranchDetailDao busiContractTendersBranchDetailDao;
	@Autowired
	private BusiContractTendersBranchApproveFlowInfoDao busiContractTendersBranchApproveFlowInfoDao;
	
	@Autowired
    private RpcProxy rpcProxy;
	
	@Autowired
    private FwOrgService fwOrgService;//机构服务类
	
	@Autowired
	private BusiContractTendersContractDetailDao busiContractTendersContractDetailDao;
	
	@Autowired
	private BusiContractTendersBranchImportDao busiContractTendersBranchImportDao;
	
	/**
	 * 机构对象的机构ID、机构名称转换至Dto对象的数据常量
	 */
	public static final Map<String,String> entityToFields = new HashMap<String,String>();
	static {
		entityToFields.put("busiFwOrg.name", "orgName");
		entityToFields.put("busiFwOrg.id", "orgId");
	}
	
	/**
	 * 查询顶级节点列表数据
	 * @param queryPage
	 * @return
	 */
	@Override
	public List<BusiContractTendersBranchDto> getAllTopLevelBusiContractTendersBranchDto(QueryPage queryPage){
		queryPage.setSqlCondition(" busiFwOrg.level3 ="+  UserUtil.getUser().getCurrProId()+" ");
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
  	    List<BusiContractTendersBranch> busiContractTendersBranchList = busiContractTendersBranchDao.findAllBusiContractTendersBranch(queryPage);
		return afreshCopyTendersBranchDtoList(busiContractTendersBranchList);
	}

	/**
	 * 重新封装返回的Dto数据
	 * @param busiContractTendersBranchList
	 * @return
	 */
	private List<BusiContractTendersBranchDto> afreshCopyTendersBranchDtoList(List<BusiContractTendersBranch> busiContractTendersBranchList) {
		List<BusiContractTendersBranchDto> busiContractTendersBranchDtoList = new ArrayList<BusiContractTendersBranchDto>();
		//获取分部分项主表model数据
  	    
		BeanCopy beanCopy = BeanCopy.getInstance().addFieldMaps(entityToFields);
		BeanCopy subBeanCopy = BeanCopy.getInstance().addFieldMap("busiContractTendersBranch.id","tendersBranchId")
													 .addFieldMap("busiContractTendersContractDetail.id","contractDetailId")
													 .addFieldMap("busiContractTendersContractDetail.contractDetailName","contractDetailName")
													 .addFieldMap("busiContractTendersContractDetail.contractDetailCode","contractDetailCode")
													 .addFieldMap("busiContractTendersContractDetail.countUnit","countUnit")
													 .addFieldMap("busiContractTendersContractDetail.workabilityQuantity","workabilityQuantity");;
		for(BusiContractTendersBranch busiContractTendersBranch:busiContractTendersBranchList){
			BusiContractTendersBranchDto busiContractTendersBranchDto = beanCopy.convert(busiContractTendersBranch, BusiContractTendersBranchDto.class);
			//Set集合转List集合
			List<BusiContractTendersBranchDetail> busiContractTendersBranchDetailList = new ArrayList<BusiContractTendersBranchDetail>(busiContractTendersBranch.getBusiContractTendersBranchDetailSet());
			busiContractTendersBranchDto.setBusiContractTendersBranchDetailDtoList(subBeanCopy.addFieldMaps(entityToFields).convertList(busiContractTendersBranchDetailList, BusiContractTendersBranchDetailDto.class));
			busiContractTendersBranchDtoList.add(busiContractTendersBranchDto);
		}
		return busiContractTendersBranchDtoList;
	}
	
	/**
	 * 根据父级ID查询自己节点数据
	 * @param parentUUID
	 * @return
	 */
	@Override
	public List<BusiContractTendersBranchDto> getChildBusiContractTendersBranchDtoByParentUUID(String parentUUID){
		List<BusiContractTendersBranch> busiContractTendersBranchList = busiContractTendersBranchDao.findChildBusiContractTendersBranchByParentUUID(parentUUID);
		return afreshCopyTendersBranchDtoList(busiContractTendersBranchList);
	}
	
	
	/**
	 * 桩号转化为米(按照紫光李登科 提供的代码翻译过来)
	 * 备用正则(暂未使用)：
	 * (正则1：获取最后一组数字[^.*((?<!\\d)\\d+).*$]  正则2：获取最后一个字母[(.*)([a-zA-Z])[^a-zA-Z]*])
	 * @param stakeNo
	 * @return
	 */
	public static BigDecimal getSimplifyStakeNo(String stakeNo){
		try {
			if (StringUtils.isNotEmpty(stakeNo)) {
				int lastKOfIndex = stakeNo.toUpperCase().lastIndexOf("K");
				String newStakeNo = stakeNo.substring(lastKOfIndex + 1);
				String[] newStakeNoArr = newStakeNo.split("\\+");
				Double simplifyStakeNo = Double.valueOf(newStakeNoArr[0]) * 1000 + Double.valueOf(newStakeNoArr[1]);
				return BigDecimal.valueOf(simplifyStakeNo);
			} else {
				return BigDecimal.valueOf(0);
			}
		} catch (Exception e) {
			return BigDecimal.valueOf(0);
		}
	}
	
	/**
	 * 保存标段分部分项列表数据
	 * @param orgId
	 * @param busiContractTendersBranchDtoList
	 */
	@Override
	public List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId,List<BusiContractTendersBranchDto> busiContractTendersBranchDtoList){
		List<BusiContractTendersBranch> entityList = new ArrayList<BusiContractTendersBranch>();
		//根据页面传进来的值设置保存的值信息
		if(null != busiContractTendersBranchDtoList && busiContractTendersBranchDtoList.size() > 0){
			List<BusiContractTendersBranch> deleteEntityList = new ArrayList<BusiContractTendersBranch>();
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			for(BusiContractTendersBranchDto busiContractTendersBranchDto:busiContractTendersBranchDtoList){
				busiContractTendersBranchDto.setOrgId(orgId);
				BusiContractTendersBranch busiContractTendersBranch = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convert(busiContractTendersBranchDto, BusiContractTendersBranch.class);
				busiContractTendersBranch.setSimplifyStartStakeNo(getSimplifyStakeNo(busiContractTendersBranch.getStartStakeNo()));//简化开始桩号
				busiContractTendersBranch.setSimplifyEndStakeNo(getSimplifyStakeNo(busiContractTendersBranch.getEndStakeNo()));//简化结束桩号
				if(busiContractTendersBranchDto.getOperation().equals(Constant.OPERATION_TYPE_ADD)){
					//新增
					busiContractTendersBranch.setCreater(currentUser.getId());// 创建人id
					busiContractTendersBranch.setCreaterName(currentUser.getUserName());// 创建人姓名
					busiContractTendersBranch.setCreateTime(new Date());// 创建时间
					busiContractTendersBranch.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
					busiContractTendersBranchDao.persistentBusiContractTendersBranch(busiContractTendersBranch);
					entityList.add(busiContractTendersBranch);
					this.persistentBusiContractTendersBranchDetail(currentUser, busiContractTendersBranchDto,	busiContractTendersBranch);
				}else if(busiContractTendersBranchDto.getOperation().equals(Constant.OPERATION_TYPE_UPDATE)){
					//更新
					busiContractTendersBranch.setModifer(currentUser.getId());// 修改人id
					busiContractTendersBranch.setModifierName(currentUser.getUserName());// 修改人姓名
					busiContractTendersBranch.setModifyTime(new Date());// 修改时间
					busiContractTendersBranchDao.saveOrUpdateBusiContractTendersBranch(busiContractTendersBranch);
					//busiContractTendersBranchDetailDao.deleteBusiContractTendersBranchDetailByTendersBranchId(busiContractTendersBranch.getId());
					this.persistentBusiContractTendersBranchDetail(currentUser, busiContractTendersBranchDto,	busiContractTendersBranch);
				}else if(busiContractTendersBranchDto.getOperation().equals(Constant.OPERATION_TYPE_DELETE)){
					//删除
					deleteEntityList.add(busiContractTendersBranch);
					busiContractTendersBranchDto.setIsDelete(Constant.DELETE_STATUS_DELETED);//已删除
					busiContractTendersBranchDetailDao.deleteBusiContractTendersBranchDetailByTendersBranchId(busiContractTendersBranch.getId());
					busiContractTendersBranchDao.deleteBusiContractTendersBranch(busiContractTendersBranch);
				}
			}
			//还原的合同清单数据
			if(null != deleteEntityList && deleteEntityList.size() > 0){
				//预还原的合同清单数据
				List<BusiContractTendersContractDetail> updateBusiContractTendersContractDetailList = getRestoreBusiContractTendersContractDetail(deleteEntityList);//批量更新的(修改)合同清单数据
				if(null != updateBusiContractTendersContractDetailList && updateBusiContractTendersContractDetailList.size() > 0){
					busiContractTendersContractDetailDao.batchUpdateBusiContractTendersContractDetailList(updateBusiContractTendersContractDetailList);
				}
			}
		}
		//前台返回新增的数据集合
		List<TreeGridAddIdAndUUIDDto> treeGridAddIdAndUUIDDtoList = new ArrayList<TreeGridAddIdAndUUIDDto>();
		if(null != entityList && entityList.size() > 0){
			for(BusiContractTendersBranch entity : entityList){
				TreeGridAddIdAndUUIDDto treeGridAddIdAndUUIDDto = new TreeGridAddIdAndUUIDDto();
				treeGridAddIdAndUUIDDto.setId(entity.getId());
				treeGridAddIdAndUUIDDto.setUuid(entity.getUUID());
				treeGridAddIdAndUUIDDtoList.add(treeGridAddIdAndUUIDDto);
			}
			
		} 
		return treeGridAddIdAndUUIDDtoList;
	}
	

	/**
	 * 持久化分部分项明细数据
	 * @param currentUser
	 * @param busiContractTendersBranchDto
	 * @param busiContractTendersBranch
	 */
	private void persistentBusiContractTendersBranchDetail(UserInfo currentUser,	BusiContractTendersBranchDto busiContractTendersBranchDto,
														BusiContractTendersBranch busiContractTendersBranch) {
		if(busiContractTendersBranchDto.getBusiContractTendersBranchDetailDtoList() != null && busiContractTendersBranchDto.getBusiContractTendersBranchDetailDtoList().size() > 0){
			for(BusiContractTendersBranchDetailDto busiContractTendersBranchDetailDto:busiContractTendersBranchDto.getBusiContractTendersBranchDetailDtoList()){
				BusiContractTendersBranchDetail	 busiContractTendersBranchDetail = null;
				if(null != busiContractTendersBranchDetailDto.getId()){
					//这里查询后再beanCopy，否则会抛 detached entity passed to persist
					busiContractTendersBranchDetail = busiContractTendersBranchDetailDao.findBusiContractTendersBranchDetailById(busiContractTendersBranchDetailDto.getId());
					BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").addFieldMap("contractDetailId", "busiContractTendersContractDetail")//合同明细表
										  .map(busiContractTendersBranchDetailDto, busiContractTendersBranchDetail);
				}else{
					busiContractTendersBranchDetail = BeanCopy.getInstance()
							.addFieldMap("orgId", "busiFwOrg")
							.addFieldMap("contractDetailId", "busiContractTendersContractDetail")//合同明细表
					.convert(busiContractTendersBranchDetailDto, BusiContractTendersBranchDetail.class);
				}
				busiContractTendersBranchDetail.setBusiContractTendersBranch(busiContractTendersBranch);
				busiContractTendersBranchDetail.setCreater(currentUser.getId());// 创建人id
				busiContractTendersBranchDetail.setCreaterName(currentUser.getUserName());// 创建人姓名
				busiContractTendersBranchDetail.setCreateTime(new Date());// 创建时间
				busiContractTendersBranchDetail.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
				
				/** 计算 合同清单已使用量、合同清单可使用量、可分解数量、设计单价、设计总额  **/
				BusiContractTendersContractDetail busiContractTendersContractDetail = null;
				BigDecimal usedQuantity = BigDecimal.ZERO;
				BigDecimal workabilityQuantity = BigDecimal.ZERO;
				if(null != busiContractTendersBranchDetail.getBusiContractTendersContractDetail()){
					busiContractTendersContractDetail= busiContractTendersContractDetailDao.findBusiContractTendersContractDetailById(busiContractTendersBranchDetail.getBusiContractTendersContractDetail().getId());
				    usedQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getUsedQuantity()).add(convertBigDecimalNull(busiContractTendersBranchDetail.getDesignQuantity()));//合同清单已使用量 = 原已使用量 + 分部分项清单本次设计数量
					workabilityQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getQuantity()).subtract(usedQuantity);//合同清单可使用量 = 合同清单合同数量 - 合同清单已使用量
//					busiContractTendersBranchDetail.setDissolubleQuantity(workabilityQuantity);//可分解数量 = 合同清单可使用量
					busiContractTendersBranchDetail.setDesignUnitPrice(convertBigDecimalNull(busiContractTendersContractDetail.getUnitPrice()));//设计单价 = 合同单价
				}
				BigDecimal designTotalPrice = convertBigDecimalNull(busiContractTendersBranchDetail.getDesignQuantity()).multiply(convertBigDecimalNull(busiContractTendersBranchDetail.getDesignUnitPrice()));//设计总额 = 设计数量 * 设计单价
				busiContractTendersBranchDetail.setDesignTotalPrice(designTotalPrice);//设计总额
				if(busiContractTendersBranchDto.getOperation().equals(Constant.OPERATION_TYPE_ADD)){
					busiContractTendersBranchDetailDao.persistentBusiContractTendersBranchDetail(busiContractTendersBranchDetail);
				}else if(busiContractTendersBranchDto.getOperation().equals(Constant.OPERATION_TYPE_UPDATE)){
					//明细删除
					if(Constant.OPERATION_TYPE_DELETE.equals(busiContractTendersBranchDetailDto.getOpration())){
						//如果是删除，则将之前分解的数量加回至原清单
						if(null != busiContractTendersContractDetail){
							usedQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getUsedQuantity()).subtract(convertBigDecimalNull(busiContractTendersBranchDetail.getDesignQuantity()));//合同清单已使用量 = 原已使用量 - 分部分项清单本次设计数量
							workabilityQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getQuantity()).subtract(usedQuantity);//合同清单可使用量 = 合同清单合同数量 - 合同清单已使用量
						}
						busiContractTendersBranchDetail.setIsDelete(Constant.DELETE_STATUS_DELETED);//已删除
						busiContractTendersBranchDetailDao.deleteBusiContractTendersBranchDetail(busiContractTendersBranchDetail);
					}else{
						busiContractTendersBranchDetailDao.saveOrUpdateBusiContractTendersBranchDetail(busiContractTendersBranchDetail);
					}
				}
				if(null != busiContractTendersContractDetail){
					busiContractTendersContractDetail.setUsedQuantity(usedQuantity);//合同清单已使用量
					busiContractTendersContractDetail.setWorkabilityQuantity(workabilityQuantity);//合同清单可使用量
					busiContractTendersContractDetailDao.persistentBusiContractTendersContractDetail(busiContractTendersContractDetail);
				}
			}
		}
	}
	
	private static BigDecimal convertBigDecimalNull(BigDecimal bigDecimalVal){
		if(null == bigDecimalVal){
			bigDecimalVal = bigDecimalVal.ZERO;
		}
		return bigDecimalVal;
	}
	
	/**
	 * 查询分页数据
	 */
	@Override
	public DataPage<BusiContractTendersBranchDto> getAllBusiContractTendersBranchPage(QueryPage queryPage) {
		DataPage<BusiContractTendersBranch> dataPage = busiContractTendersBranchDao.getAllBusiContractTendersBranch(queryPage);
		DataPage<BusiContractTendersBranchDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiContractTendersBranchDto.class);
		return datPageDto;
	}
	
	/**
	 * 分部分项流程申请
	 */
	@Override
	public void applyBusiContractTendersBranchByOrgId(Long orgId) {
		List<BusiContractTendersBranch> busiContractTendersBranchList = busiContractTendersBranchDao.findBusiContractTendersBranchListByOrgId(orgId);
		String checkMessage = "";
		if(null != busiContractTendersBranchList && busiContractTendersBranchList.size() > 0){
			for(BusiContractTendersBranch busiContractTendersBranch:busiContractTendersBranchList){
				if(StringUtils.isNotEmpty(busiContractTendersBranch.getCheckMessage())){
					checkMessage = busiContractTendersBranch.getCheckMessage();
				}
			}
		}
		if(StringUtils.isNotEmpty(checkMessage)){
			throw new BusinessRuntimeException("提交的分部分项数据包含["+checkMessage+"]，不可提交申请!", "-1");
		} else {
			//调用工作流RPC
			WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
			Map<String,Object> variables = new HashMap<String, Object>();
			FwOrgDto fwOrgDto = fwOrgService.getOrgById(orgId);
			variables.put("title",  "分部分项审批：[标段机构:"+fwOrgDto.getName()+"("+fwOrgDto.getOrgCode()+")]");
			String flowInstanceId = workFlowRpcService.startProcessInstanceByKey("busi_contract_tenders_branch_approve_flow_info",orgId, variables);
			if(!StringUtils.isEmpty(flowInstanceId)){
				BusiContractTendersBranchApproveFlowInfo busiContractTendersBranchApproveFlowInfo = busiContractTendersBranchApproveFlowInfoDao.findBusiContractTendersBranchApproveFlowInfoByOrgId(orgId);
				if(null == busiContractTendersBranchApproveFlowInfo){
					busiContractTendersBranchApproveFlowInfo = new BusiContractTendersBranchApproveFlowInfo();
				}
				UserInfo currentUser = ThreadLocalClient.get().getOperator();
				busiContractTendersBranchApproveFlowInfo.setOrgId(orgId);
				busiContractTendersBranchApproveFlowInfo.setApplyTime(new Date());// 申请时间
				busiContractTendersBranchApproveFlowInfo.setApplyUserId(currentUser.getId());// 申请人ID
				busiContractTendersBranchApproveFlowInfo.setApplyUserName(currentUser.getUserName());// 申请人名称
				busiContractTendersBranchApproveFlowInfo.setApprovalStatus(Constant.FLOW_STATUS_INAPPROVAL);// 审批状态-审批中
				busiContractTendersBranchApproveFlowInfo.setFlowInstanceId(Long.valueOf(flowInstanceId));// 流程实例ID
				
				busiContractTendersBranchApproveFlowInfo.setCreater(currentUser.getId());// 创建人id
				busiContractTendersBranchApproveFlowInfo.setCreaterName(currentUser.getUserName());// 创建人姓名
				busiContractTendersBranchApproveFlowInfo.setCreateTime(new Date());// 创建时间
				//进行持久化保存
				busiContractTendersBranchApproveFlowInfoDao.persistentBusiContractTendersBranchApproveFlowInfo(busiContractTendersBranchApproveFlowInfo);
			}
		}
	}

	/**
	 * 根据机构ID查询审批状态 
	 */
	@Override
	public BusiContractTendersBranchDto getBusiContractTendersBranchDtoByOrgId(Long orgId) {
		BusiContractTendersBranchApproveFlowInfo busiContractTendersBranchApproveFlowInfo = busiContractTendersBranchApproveFlowInfoDao.findBusiContractTendersBranchApproveFlowInfoByOrgId(orgId);
		return BeanCopy.getInstance().convert(busiContractTendersBranchApproveFlowInfo, BusiContractTendersBranchDto.class);
	}

	/**
	 * 审批流程处理
	 * @param taskId
	 * @param id
	 * @param busiQumClaimWaybillDetailDto
	 */
	public void workflowSubmit(String taskId, Long id,BusiContractTendersBranchApproveFlowInfo busiContractTendersBranchApproveFlowInfo){
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
        Map<String,Object> variables= new HashMap <String, Object>();
        variables.put("result", busiContractTendersBranchApproveFlowInfo.getFlowResult());
        variables.put("resultMessage", busiContractTendersBranchApproveFlowInfo.getFlowMessage());
        //流程向前推进
        workFlowRpcService.complete(taskId, variables);
	}
	
	/**
	 * 工作流审批结束
	 */
	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		BusiContractTendersBranchApproveFlowInfo busiContractTendersBranchApproveFlowInfo = busiContractTendersBranchApproveFlowInfoDao.findBusiContractTendersBranchApproveFlowInfoByProcessInstanceId(Long.valueOf(processInstanceId));
		if(null == busiContractTendersBranchApproveFlowInfo){
			throw new BusinessRuntimeException("分部分项审批:根据流程ID:"+processInstanceId+",查询分部分项业务数据数据出错", "-1");
		}else{
			if (variables.get("result").equals(true)) {
				busiContractTendersBranchApproveFlowInfo.setFlowResult(Constant.FLOW_STATUS_PASSED);// //审批状态-已通过
			}else{
				busiContractTendersBranchApproveFlowInfo.setFlowResult(Constant.FLOW_STATUS_REBUT);//审批状态-已驳回
			}
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractTendersBranchApproveFlowInfo.setApprovalStatus(busiContractTendersBranchApproveFlowInfo.getFlowResult());//审批状态-已审批
			busiContractTendersBranchApproveFlowInfo.setApprovalTime(new Date());// 审批完成时间
			busiContractTendersBranchApproveFlowInfo.setApprovalUserId(currentUser.getId());// 审批人ID
			busiContractTendersBranchApproveFlowInfo.setApprovalUserName(currentUser.getUserName());// 审批人名称
			busiContractTendersBranchApproveFlowInfo.setFlowMessage((String) variables.get("resultMessage"));// 审批意见
		}
		return null;
	}

	/**
	 * 生成分部分项[生成规则：根据机构编号、单位工程类型 获取工程标段管理明细数据和 基础数据-项目分部分项 相匹配的数据]
	 */
	@Override
	public void createTendersBranchDataByOrgId(Long orgId) {
		//清除orgid对应的原始明细数据
		this.clearOldTendersBranchDataByOrgId(orgId);
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		busiContractTendersBranchDao.createTendersBranchDataByOrgId(orgId,currentUser.getTenantId(), currentUser.getId(), currentUser.getUserName());
	}

	/**
	 * 查询已挂接分部分项列表数据
	 * @param tendersContractDetailId
	 * @return
	 */
	@Override
	public List<BusiContractTendersBranchDto> getAllRelationBranchTreeDataListByContractDetailId(Long tendersContractDetailId) {
		List<Map<String, Object>> resultDataMapList =  busiContractTendersBranchDao.findAllRelationBranchTreeListBusiContractTendersContractDetailDtoByContractDetailId(tendersContractDetailId);
		List<BusiContractTendersBranchDto> busiContractTendersBranchDtoList = new ArrayList<BusiContractTendersBranchDto>();
		if (resultDataMapList != null && resultDataMapList.size() > 0) {
			for(Map<String, Object> resultDataMap:resultDataMapList){
				BusiContractTendersBranchDto busiContractTendersBranchDto = new BusiContractTendersBranchDto();
				busiContractTendersBranchDto.setTendersBranchCode(conventNull(resultDataMap.get("TENDERS_BRANCH_CODE")));/*编号*/
				busiContractTendersBranchDto.setTendersBranchName(conventNull(resultDataMap.get("TENDERS_BRANCH_NAME")));
				busiContractTendersBranchDto.setSubEngineeringName(conventNull(resultDataMap.get("SUB_ENGINEERING_NAME")));//分部工程
				busiContractTendersBranchDto.setBidSectionName(conventNull(resultDataMap.get("BID_SECTION_NAME")));//单位工程
				busiContractTendersBranchDto.setDesignQuantity(conventNull(resultDataMap.get("DESIGN_QUANTITY")));//设计量
				busiContractTendersBranchDto.setDesignTotalPrice(conventNull(resultDataMap.get("DESIGN_TOTAL_PRICE")));//设计金额
				busiContractTendersBranchDto.setStakeNoType(conventNull(resultDataMap.get("STAKE_NO_TYPE")));/*桩号类型*/
				busiContractTendersBranchDto.setStartStakeNo(conventNull(resultDataMap.get("START_STAKE_NO")));/*起始桩号*/
				busiContractTendersBranchDto.setEndStakeNo(conventNull(resultDataMap.get("END_STAKE_NO")));/*结束桩号*/
				busiContractTendersBranchDto.setContractFigureNo(conventNull(resultDataMap.get("CONTRACT_FIGURE_NO")));/*合同图号*/
				busiContractTendersBranchDto.setGradeHigh(conventNull(resultDataMap.get("GRADE_HIGH")));/*程高*/
				busiContractTendersBranchDtoList.add(busiContractTendersBranchDto);
			}
		}
		return busiContractTendersBranchDtoList;
	}
	
	private static String conventNull(Object object){
		return object == null? null: object.toString();
	}

	/**
	 * 根据
	 * @throws KettleException 
	 */
	@Override
	public Map<String, String> importDataByEtl(String filePath, Long orgId) throws Exception {
		long startTime = System.currentTimeMillis();
		
		Map<String, String> resultMap = new HashMap<String, String>();
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		String importBatchNo = UUID.randomUUID().toString().toUpperCase();//批次号
        KettleEnvironment.init();// 初始化 
        EnvUtil.environmentInit(); 
        TransMeta transMeta = new TransMeta(BusiContractTendersBranchServiceImpl.class.getResource("/etl/tendersBranch.ktr").getPath()); 
        Trans trans = new Trans(transMeta); 
        trans.setParameterValue("importBatchNo", importBatchNo);
        trans.setParameterValue("creater", currentUser.getUserName()+"("+currentUser.getId()+")");
        trans.setParameterValue("importDate", DateUtil.timeFormat.format(new Date()));
        trans.setParameterValue("filePath", filePath);
        trans.execute(null); 
        trans.waitUntilFinished();//导入完成
        if (trans.getErrors() > 0) { 
        	resultMap.put("error", "导入发生错误");
        	resultMap.put("message", "导入发生错误，请检查excel,是否有空行，空值，合计值等");
        	return resultMap;
        } 
        long insertTempTable = System.currentTimeMillis();
        System.out.println("插入临时表时长：" + (insertTempTable - startTime));
        this.clearOldTendersBranchDataByOrgId(orgId);//清除原标段下的数据
        long clearOldTable = System.currentTimeMillis();
        System.out.println("清除原标段下的数据时长：" + (clearOldTable - insertTempTable));
        this.handlePersistImportDataByBatchNo(orgId, currentUser, importBatchNo);//处理导入临时表中的数据
        System.out.println("入业务库时长：" + (System.currentTimeMillis() - clearOldTable));
        
        System.out.println("总处理时长：" + (System.currentTimeMillis() - startTime));
        resultMap.put("message", "导入成功！");
        
        return resultMap;
	}

	
	/**
	 * 清除原标段下的数据
	 * @param orgId
	 */
	private void clearOldTendersBranchDataByOrgId(Long orgId) {
		List<BusiContractTendersBranch> busiContractTendersBranchList = busiContractTendersBranchDao.findBusiContractTendersBranchListByOrgId(orgId);
		//预还原的合同清单数据
		List<BusiContractTendersContractDetail> updateBusiContractTendersContractDetailList = getRestoreBusiContractTendersContractDetail(busiContractTendersBranchList);//批量更新的(修改)合同清单数据
		if(null != updateBusiContractTendersContractDetailList && updateBusiContractTendersContractDetailList.size() > 0){
			busiContractTendersContractDetailDao.batchUpdateBusiContractTendersContractDetailList(updateBusiContractTendersContractDetailList);
		}
		busiContractTendersBranchDao.deleteBusiContractTendersBranchListByOrgId(orgId);//删除分部分项数据以及明细数据
	}
	
	/**
	 * 根据分部分项数据还原其挂接的合同清单可使用量
	 * @param busiContractTendersBranch
	 */
	private List<BusiContractTendersContractDetail> getRestoreBusiContractTendersContractDetail(List<BusiContractTendersBranch> busiContractTendersBranchList){
		List<BusiContractTendersContractDetail> updateBusiContractTendersContractDetailList = null;//批量更新的(修改)合同清单数据
		if(null != busiContractTendersBranchList && busiContractTendersBranchList.size() > 0){
			//分部分项主表
			for(BusiContractTendersBranch busiContractTendersBranch:busiContractTendersBranchList){
				if(null != busiContractTendersBranch.getBusiContractTendersBranchDetailSet() && busiContractTendersBranch.getBusiContractTendersBranchDetailSet().size() > 0){
					updateBusiContractTendersContractDetailList = new ArrayList<BusiContractTendersContractDetail>();
					//分部分项明细
					for(BusiContractTendersBranchDetail busiContractTendersBranchDetail : busiContractTendersBranch.getBusiContractTendersBranchDetailSet()){
						BusiContractTendersContractDetail busiContractTendersContractDetail = busiContractTendersBranchDetail.getBusiContractTendersContractDetail();
						if(null != busiContractTendersContractDetail){
							//如果是删除，则将之前分解的数量加回至原清单
							BigDecimal usedQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getUsedQuantity()).subtract(convertBigDecimalNull(busiContractTendersBranchDetail.getDesignQuantity()));//合同清单已使用量 = 原已使用量 - 分部分项清单本次设计数量
							BigDecimal workabilityQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getQuantity()).subtract(usedQuantity);//合同清单可使用量 = 合同清单合同数量 - 合同清单已使用量
							busiContractTendersContractDetail.setUsedQuantity(usedQuantity);//合同清单已使用量
							busiContractTendersContractDetail.setWorkabilityQuantity(workabilityQuantity);//合同清单可使用量
							updateBusiContractTendersContractDetailList.add(busiContractTendersContractDetail);
						}
					}
				}
			}
		}
		return updateBusiContractTendersContractDetailList;
	}

	/**
	 * 处理导入临时表中的数据
	 * @param orgId
	 * @param currentUser
	 * @param importBatchNo
	 */
	private void handlePersistImportDataByBatchNo(Long orgId, UserInfo currentUser, String importBatchNo) {
		//获取本次导入的数据
		List<BusiContractTendersBranchImport> busiContractTendersBranchImportList = busiContractTendersBranchImportDao.findAllBusiContractTendersBranchImportByBatchNo(importBatchNo);
		List<BusiContractTendersBranch> persistBusiContractTendersBranchList = null;//待持久化的分部分项数据
		List<BusiContractTendersBranchDetail> persistBusiContractTendersBranchDetailList = null;//待持久化的分部分项明细数据
		List<BusiContractTendersContractDetail> persistBusiContractTendersContractDetailList = null;//待持久化(修改)的合同清单数据
		Map<String, BusiContractTendersBranch> uniqueImportRowDataMap = new HashMap<String, BusiContractTendersBranch>();
		if(null != busiContractTendersBranchImportList && busiContractTendersBranchImportList.size() > 0){
			persistBusiContractTendersBranchList = new ArrayList<BusiContractTendersBranch>();
			persistBusiContractTendersBranchDetailList = new ArrayList<BusiContractTendersBranchDetail>();
			persistBusiContractTendersContractDetailList = new ArrayList<BusiContractTendersContractDetail>();
			//根据机构ID获取
			Map<String, BusiContractTendersContractDetail> uniqueContractDetailMap = busiContractTendersContractDetailDao.findCheckPassedBusiContractTendersContractDetailListByOrgId(orgId);
			Map<String, Map<String, Double>> importContractDetailDesignInfoMap = this.getImportContractDetailDesignInfoMap(busiContractTendersBranchImportList);
			Map<String,Double> importContractDetailDesignQuantityMap = importContractDetailDesignInfoMap.get("importContractDetailDesignQuantityMap");//合同清单数量校验Map
			Map<String,Double> importContractDetailDesignTotalPriceMap = importContractDetailDesignInfoMap.get("importContractDetailDesignTotalPriceMap");//合同清单金额校验Map
			for(BusiContractTendersBranchImport busiContractTendersBranchImport : busiContractTendersBranchImportList){
				//校验当前行数据是否已经在Map中存在
				String firstLevelSameBusiContractTendersBranchKey = getGroupLevelSameBusiContractTendersBranch(busiContractTendersBranchImport, 1);
				String secondLevelSameBusiContractTendersBranchKey = getGroupLevelSameBusiContractTendersBranch(busiContractTendersBranchImport, 2);
				String thirdLevelSameBusiContractTendersBranchKey = getGroupLevelSameBusiContractTendersBranch(busiContractTendersBranchImport, 3);
				String fourthLevelSameBusiContractTendersBranchKey = getGroupLevelSameBusiContractTendersBranch(busiContractTendersBranchImport, 4);
				BusiContractTendersBranch firstBusiContractTendersBranch = null;//顶级节点
				BusiContractTendersBranch secondBusiContractTendersBranch = null;//二级节点
				BusiContractTendersBranch thirdBusiContractTendersBranch = null;//三级节点
				BusiContractTendersBranch fourthBusiContractTendersBranch = null;//四级节点
				//封装分部分项数据
				if(!uniqueImportRowDataMap.containsKey(firstLevelSameBusiContractTendersBranchKey)){
					firstBusiContractTendersBranch = this.packageBusiContractTendersBranch(orgId, currentUser, "0", busiContractTendersBranchImport, 1);
		    		persistBusiContractTendersBranchList.add(firstBusiContractTendersBranch);
		    		uniqueImportRowDataMap.put(firstLevelSameBusiContractTendersBranchKey, firstBusiContractTendersBranch);
				}else{
					firstBusiContractTendersBranch = uniqueImportRowDataMap.get(firstLevelSameBusiContractTendersBranchKey);
				}	
	    		//second：二级节点
				if(!uniqueImportRowDataMap.containsKey(secondLevelSameBusiContractTendersBranchKey)){
		    		secondBusiContractTendersBranch = this.packageBusiContractTendersBranch(orgId, currentUser, firstBusiContractTendersBranch.getUUID(), busiContractTendersBranchImport, 2);
		    		persistBusiContractTendersBranchList.add(secondBusiContractTendersBranch);
		    		uniqueImportRowDataMap.put(secondLevelSameBusiContractTendersBranchKey, secondBusiContractTendersBranch);
				}else{
					secondBusiContractTendersBranch = uniqueImportRowDataMap.get(secondLevelSameBusiContractTendersBranchKey);
				}
				//third：三级节点
				if(!uniqueImportRowDataMap.containsKey(thirdLevelSameBusiContractTendersBranchKey)){
		    		thirdBusiContractTendersBranch = this.packageBusiContractTendersBranch(orgId, currentUser, secondBusiContractTendersBranch.getUUID(), busiContractTendersBranchImport, 3);
		    		persistBusiContractTendersBranchList.add(thirdBusiContractTendersBranch);
		    		uniqueImportRowDataMap.put(thirdLevelSameBusiContractTendersBranchKey, thirdBusiContractTendersBranch);
				}else{
					thirdBusiContractTendersBranch = uniqueImportRowDataMap.get(thirdLevelSameBusiContractTendersBranchKey);
				}
				//fourth：四级节点
				if(!uniqueImportRowDataMap.containsKey(fourthLevelSameBusiContractTendersBranchKey)){
		    		fourthBusiContractTendersBranch = this.packageBusiContractTendersBranch(orgId, currentUser, thirdBusiContractTendersBranch.getUUID(), busiContractTendersBranchImport, 4);
		    		persistBusiContractTendersBranchList.add(fourthBusiContractTendersBranch);
		    		uniqueImportRowDataMap.put(fourthLevelSameBusiContractTendersBranchKey, fourthBusiContractTendersBranch);
				}else{
					fourthBusiContractTendersBranch = uniqueImportRowDataMap.get(fourthLevelSameBusiContractTendersBranchKey);
				}
				//封装明细
				BusiContractTendersBranchDetail busiContractTendersBranchDetail = new BusiContractTendersBranchDetail();
				busiContractTendersBranchDetail.setBusiContractTendersBranch(fourthBusiContractTendersBranch);////挂接的分部分项对象-标段分部分项ID
				BusiContractTendersContractDetail busiContractTendersContractDetail = null;
				if(uniqueContractDetailMap.containsKey(busiContractTendersBranchImport.getContractDetailCode())){
					busiContractTendersContractDetail = uniqueContractDetailMap.get(busiContractTendersBranchImport.getContractDetailCode());
					Double contractDetailDesignQuantity = importContractDetailDesignQuantityMap.get(busiContractTendersBranchImport.getContractDetailCode());
					//合同清单的可使用量 < 导入数据合同的设计数量之和，则不允许申请(需求：每条被分解的清单的设计量之和要小于等于该清单的总数量)
					if(convertBigDecimalNull(busiContractTendersContractDetail.getWorkabilityQuantity()).doubleValue() < contractDetailDesignQuantity){
						fourthBusiContractTendersBranch.setCheckMessage(fourthBusiContractTendersBranch.getCheckMessage() + "该分部分项挂接的合同清单的可使用量小于被分解的清单的设计量之和;\n");
					}
					//设计金额之和要小于等于清单金额总量
					Double contractDetailDesignTotalPrice = importContractDetailDesignTotalPriceMap.get(busiContractTendersBranchImport.getContractDetailCode());
					if(convertBigDecimalNull(busiContractTendersContractDetail.getTotalPrice()).doubleValue() < contractDetailDesignTotalPrice){
						fourthBusiContractTendersBranch.setCheckMessage(fourthBusiContractTendersBranch.getCheckMessage() + "该分部分项挂接的合同清单的金额总量小于被分解的清单的设计金额之和;\n");
					}
				} else {
					fourthBusiContractTendersBranch.setCheckMessage(fourthBusiContractTendersBranch.getCheckMessage() + "该分部分项挂接的合同清单在合同清单复核管理中不存在;\n");
				}
				/** 计算 合同清单已使用量、合同清单可使用量、可分解数量、设计单价、设计总额  **/
				BigDecimal usedQuantity = new BigDecimal("0");//合同清单已使用量 
				BigDecimal workabilityQuantity = new BigDecimal("0");//合同清单可使用量
				if(null != busiContractTendersContractDetail){
					//合同清单已使用量 = 原已使用量 + 分部分项清单本次设计数量
					usedQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getUsedQuantity()).add(convertBigDecimalNull(new BigDecimal(StringUtils.defaultString(busiContractTendersBranchImport.getContractQuantity(),"0"))));
					busiContractTendersContractDetail.setUsedQuantity(usedQuantity);//合同清单已使用量
					//合同清单可使用量 = 合同清单合同数量 - 合同清单已使用量
					workabilityQuantity = convertBigDecimalNull(busiContractTendersContractDetail.getQuantity()).subtract(usedQuantity);
					busiContractTendersContractDetail.setWorkabilityQuantity(workabilityQuantity);//合同清单可使用量
					persistBusiContractTendersContractDetailList.add(busiContractTendersContractDetail);
					
					//如果合同清单系统中有
					busiContractTendersBranchDetail.setBusiContractTendersContractDetail(busiContractTendersContractDetail);//合同清单主键ID
					busiContractTendersBranchDetail.setDissolubleQuantity(workabilityQuantity);//可分解数量 = 合同清单可使用量
					busiContractTendersBranchDetail.setDesignUnitPrice(busiContractTendersContractDetail.getUnitPrice());
					
				}else{
					busiContractTendersBranchDetail.setDissolubleQuantity(convertBigDecimalNull(new BigDecimal(StringUtils.defaultString(busiContractTendersBranchImport.getContractQuantity(),"0"))));//可分解数量=导入的数量
					busiContractTendersBranchDetail.setDesignUnitPrice(convertBigDecimalNull(new BigDecimal(StringUtils.defaultString(busiContractTendersBranchImport.getContractUnitPrice(),"0"))));//设计单价=导入的单价
				}
				//能关联到：设计数量=导入的计量数量，设计单价=合同单价；关联不到：设计数量=导入的计量数量，设计单价=导入的单价  》》金额=设计数量 * 设计单价
				busiContractTendersBranchDetail.setDesignQuantity(convertBigDecimalNull(new BigDecimal(StringUtils.defaultString(busiContractTendersBranchImport.getMeterageQuantity(),"0"))));//设计数量=导入的计量数量
				BigDecimal designTotalPrice = convertBigDecimalNull(busiContractTendersBranchDetail.getDesignQuantity()).multiply(convertBigDecimalNull(busiContractTendersBranchDetail.getDesignUnitPrice()));//设计总额 = 设计数量 * 设计单价
				busiContractTendersBranchDetail.setDesignTotalPrice(designTotalPrice);//设计总额
				
				busiContractTendersBranchDetail.setCumulativeQuantity(new BigDecimal(0));//累计计量(待处理)
				busiContractTendersBranchDetail.setBusiFwOrg(fourthBusiContractTendersBranch.getBusiFwOrg());//机构信息
				busiContractTendersBranchDetail.setCreater(currentUser.getId());//创建人ID
				busiContractTendersBranchDetail.setCreaterName(currentUser.getUserName());//创建人姓名
				busiContractTendersBranchDetail.setCreateTime(new Date());//创建时间
				busiContractTendersBranchDetail.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
				persistBusiContractTendersBranchDetailList.add(busiContractTendersBranchDetail);
			}
			//持久化数据-批量新增分部分项主数据
			if(null != persistBusiContractTendersBranchList && persistBusiContractTendersBranchList.size() > 0){
				busiContractTendersBranchDao.batchSaveBusiContractTendersBranchList(persistBusiContractTendersBranchList);
			}
			//持久化数据-批量新增分部分项明细数据
			if(null != persistBusiContractTendersBranchDetailList && persistBusiContractTendersBranchDetailList.size() > 0){
				busiContractTendersBranchDetailDao.batchSaveBusiContractTendersBranchDetailList(persistBusiContractTendersBranchDetailList);
			}
			//持久化数据-批量更新合同清单数据
			if(null != persistBusiContractTendersContractDetailList && persistBusiContractTendersContractDetailList.size() > 0){
				busiContractTendersContractDetailDao.batchUpdateBusiContractTendersContractDetailList(persistBusiContractTendersContractDetailList);
			}
		}
	}

	/**
	 * 封装导入数据合同清单的设计量之和
	 * @param busiContractTendersBranchImportList
	 * @return
	 */
	private Map<String,Map<String,Double>> getImportContractDetailDesignInfoMap(List<BusiContractTendersBranchImport> busiContractTendersBranchImportList){
		Map<String,Map<String,Double>> resultMap = new HashMap<String,Map<String,Double>>();
		Map<String,Double> importContractDetailDesignQuantityMap = new HashMap<String,Double>();//数量
		Map<String,Double> importContractDetailDesignTotalPriceMap = new HashMap<String,Double>();//金额
		if(null != busiContractTendersBranchImportList && busiContractTendersBranchImportList.size() > 0){
			for(BusiContractTendersBranchImport busiContractTendersBranchImport : busiContractTendersBranchImportList){
				Double contractDetailDesignQuantity = Double.valueOf(StringUtils.defaultString(busiContractTendersBranchImport.getContractQuantity(), "0"));//导入的设计数量
				Double contractDetailDesignTotalPrice = contractDetailDesignQuantity * Double.valueOf(StringUtils.defaultString(busiContractTendersBranchImport.getContractUnitPrice(), "0"));//导入的设计金额(需求确认：金额=数量*单价，不取导入的金额列，以算的为准 2017-11-29)
				if(StringUtils.isNotEmpty(busiContractTendersBranchImport.getContractDetailCode())){
					if(importContractDetailDesignQuantityMap.containsKey(busiContractTendersBranchImport.getContractDetailCode())){
						contractDetailDesignQuantity += importContractDetailDesignQuantityMap.get(StringUtils.defaultString(busiContractTendersBranchImport.getContractDetailCode(), ""));
						contractDetailDesignTotalPrice += importContractDetailDesignTotalPriceMap.get(StringUtils.defaultString(busiContractTendersBranchImport.getContractDetailCode(), ""));
					}
					importContractDetailDesignQuantityMap.put(busiContractTendersBranchImport.getContractDetailCode(), contractDetailDesignQuantity);
					importContractDetailDesignTotalPriceMap.put(busiContractTendersBranchImport.getContractDetailCode(), contractDetailDesignTotalPrice);
				}
			}
		}
		resultMap.put("importContractDetailDesignQuantityMap", importContractDetailDesignQuantityMap);
		resultMap.put("importContractDetailDesignTotalPriceMap", importContractDetailDesignTotalPriceMap);
		return  resultMap;
	}
	/**
	 * 根据级别封装分部分项对象
	 * @param orgId
	 * @param currentUser
	 * @param busiContractTendersBranchImport
	 * @param groupLevel
	 * @return
	 */
	private BusiContractTendersBranch packageBusiContractTendersBranch(Long orgId, UserInfo currentUser,String parentId,BusiContractTendersBranchImport busiContractTendersBranchImport, int groupLevel) {
		BusiContractTendersBranch currentLevelBusiContractTendersBranch = new BusiContractTendersBranch();
		BusiFwOrg busiFwOrg = new BusiFwOrg();// 关联Org对象获取机构信息
		busiFwOrg.setId(orgId);
		
		Map<String,String> importTendersBranchInfoMap = getImportTendersBranchInfoByGroupLevel(busiContractTendersBranchImport, groupLevel);
		String tendersBranchCode = importTendersBranchInfoMap.get("tendersBranchCode");//编号
		String tendersBranchName = importTendersBranchInfoMap.get("tendersBranchName");//名称
		String startStakeNo = importTendersBranchInfoMap.get("startStakeNo");//起始桩号
		String endStakeNo = importTendersBranchInfoMap.get("endStakeNo");//结束桩号
		
		//first：顶级节点
		currentLevelBusiContractTendersBranch.setOrderNumber(busiContractTendersBranchImport.getId().intValue());
		currentLevelBusiContractTendersBranch.setTendersBranchName(tendersBranchName);//名称
		currentLevelBusiContractTendersBranch.setTendersBranchCode(tendersBranchCode);//编码
		currentLevelBusiContractTendersBranch.setStartStakeNo(startStakeNo);//起始桩号
		currentLevelBusiContractTendersBranch.setSimplifyStartStakeNo(getSimplifyStakeNo(startStakeNo));//简化的起始桩号
		currentLevelBusiContractTendersBranch.setEndStakeNo(endStakeNo);//结束桩号
		currentLevelBusiContractTendersBranch.setSimplifyEndStakeNo(getSimplifyStakeNo(endStakeNo));//简化的结束桩号
		currentLevelBusiContractTendersBranch.setStakeNoType(null);//桩号类型(字典表)
		currentLevelBusiContractTendersBranch.setContractFigureNo(null);//合同图号
		currentLevelBusiContractTendersBranch.setGradeHigh(null);//程高
		currentLevelBusiContractTendersBranch.setCheckStatus(Constant.CHECK_STATUS_NOT_CHECKED);//复核状态(字典表：已核对、未核对) -默认未核对
		currentLevelBusiContractTendersBranch.setGroupLevel(Long.valueOf(groupLevel));//第N级
		currentLevelBusiContractTendersBranch.setUUID(UUID.randomUUID().toString().toUpperCase());//父级UUID
		currentLevelBusiContractTendersBranch.setIsLeaf(groupLevel == 4 ?Integer.valueOf(1):Integer.valueOf(0));//是否叶子节点
		currentLevelBusiContractTendersBranch.setParentId(parentId);
		currentLevelBusiContractTendersBranch.setBusiFwOrg(busiFwOrg);
		currentLevelBusiContractTendersBranch.setCreater(currentUser.getId());//创建人ID
		currentLevelBusiContractTendersBranch.setCreaterName(currentUser.getUserName());//创建人姓名
		currentLevelBusiContractTendersBranch.setCreateTime(new Date());//创建时间
		currentLevelBusiContractTendersBranch.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
		currentLevelBusiContractTendersBranch.setCheckMessage(this.getCheckBusiContractTendersBranch(busiContractTendersBranchImport, groupLevel));//校验信息
		return currentLevelBusiContractTendersBranch;
	}
	
	/**
	 * 根据导入的当前行数据获取对应级别的信息
	 * @param busiContractTendersBranchImport
	 * @param groupLevel
	 * @return
	 */
	private Map<String,String> getImportTendersBranchInfoByGroupLevel(BusiContractTendersBranchImport busiContractTendersBranchImport,int groupLevel){
		Map<String,String> importTendersBranchInfoMap = new HashMap<String,String>();
		String tendersBranchCode = "";//编号
		String tendersBranchName = "";//名称
		String startStakeNo = "";//起始桩号
		String endStakeNo = "";//结束桩号
		String groupType = "单位工程";
		switch(groupLevel){
			case 1:
				groupType = "单位工程";
				tendersBranchCode = StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectCode());//单位工程编号
				tendersBranchName = StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectName());//单位工程名称
				startStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectStartStakeNo());//单位工程起始桩号
				endStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectEndStakeNo());//单位工程结束桩号
				break;
			case 2:
				groupType = "分部工程";
				tendersBranchCode = StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectCode());//分部工程编号
				tendersBranchName = StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectName());//分部工程名称
				startStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectStartStakeNo());//分部工程起始桩号
				endStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectEndStakeNo());//分部工程终止桩号
				break;
			case 3:
				groupType = "分项工程";
				tendersBranchCode = StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectCode());//分项工程编号            
				tendersBranchName = StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectName());//分项工程名称            
				startStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectStartStakeNo());//分项工程起始桩号  
				endStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectEndStakeNo());//分项工程终止桩号;  
				break;
			case 4:
				groupType = "部位";
				tendersBranchCode = StringUtils.trimToEmpty(busiContractTendersBranchImport.getPartCode());//部位编号                   
				tendersBranchName = StringUtils.trimToEmpty(busiContractTendersBranchImport.getConcreteParagraphOrPart());//具体段落或部位 
				startStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getPartStartStakeNo());//部位起始桩号         
				endStakeNo = StringUtils.trimToEmpty(busiContractTendersBranchImport.getPartEndStakeNo());//部位终止桩号 
				break;
		}
		importTendersBranchInfoMap.put("tendersBranchCode", tendersBranchCode);
		importTendersBranchInfoMap.put("tendersBranchName", tendersBranchName);
		importTendersBranchInfoMap.put("startStakeNo", startStakeNo);
		importTendersBranchInfoMap.put("endStakeNo", endStakeNo);
		importTendersBranchInfoMap.put("groupType", groupType);
		return importTendersBranchInfoMap;
	}
	
	/**
	 * 分部分项导入之后要验证的点：
	 *	1、编号、名称、结束桩号 必填
	 *	2、起始桩号和结束桩号的格式校验
	 *	3、清单编号必须在合同清单复核管理中
	 *	4、每条被分解的清单的设计量之和要小于等于该清单的总数量
	 *	5、设计金额之和要小于等于清单金额总量
	 * @param busiContractTendersBranchImport   导入的数据对象
	 * @param groupLevel						所属级别
	 * @return									校验信息
	 */
	private String getCheckBusiContractTendersBranch(BusiContractTendersBranchImport busiContractTendersBranchImport,int groupLevel){
		Map<String,String> importTendersBranchInfoMap = getImportTendersBranchInfoByGroupLevel(busiContractTendersBranchImport, groupLevel);
		String tendersBranchCode = importTendersBranchInfoMap.get("tendersBranchCode");//编号
		String tendersBranchName = importTendersBranchInfoMap.get("tendersBranchName");//名称
		String startStakeNo = importTendersBranchInfoMap.get("startStakeNo");//起始桩号
		String endStakeNo = importTendersBranchInfoMap.get("endStakeNo");//结束桩号
		String groupType = importTendersBranchInfoMap.get("groupType");
		//开始校验逻辑
		StringBuffer checkMessageBuffer = new StringBuffer();//校验信息
		//校验[编号]是否为空
		if(StringUtils.isEmpty(tendersBranchCode)){
			checkMessageBuffer.append(groupType).append("编号为空;\n");
		}
		//校验[名称]是否为空
		if(StringUtils.isEmpty(tendersBranchName)){
			checkMessageBuffer.append(groupType).append("名称为空;\n");
		}
		//校验[起始桩号]是否合法
		if(StringUtils.isNotEmpty(startStakeNo)){
			 if(!checkStakeNo(startStakeNo)){
				 checkMessageBuffer.append(groupType).append("起始桩号格式不正确;\n");
			 }
		}
		//校验[结束桩号]是否为空
		if(StringUtils.isEmpty(endStakeNo)){
			checkMessageBuffer.append(groupType).append("结束桩号为空;\n");
		} else{
			if(!checkStakeNo(endStakeNo)){
				 checkMessageBuffer.append(groupType).append("结束桩号格式不正确;\n");
			 }
		}
		return checkMessageBuffer != null? checkMessageBuffer.toString():"";
	}
	
	/**
	 * 校验桩号是否合法(待完善)
	 * @param stakeNo
	 * @return
	 */
	public static boolean checkStakeNo(String stakeNo) { 
		String regex = ""; //未确定具体规则
//        return Pattern.matches(regex, stakeNo); 
		return true;
    } 
	
	/**
	 * Map集合中获取分部分项数据相同的四级对象
	 * @return
	 */
	private static String getGroupLevelSameBusiContractTendersBranch(BusiContractTendersBranchImport busiContractTendersBranchImport,int groupLevel){
		StringBuffer sb = new StringBuffer();
		if(groupLevel == 1){
			sb.append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectCode()))//单位工程编号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectName()))//单位工程名称
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectStartStakeNo()))//单位工程起始桩号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getUnitProjectEndStakeNo()));//单位工程终止桩号
		}else if(groupLevel == 2){
			sb.append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectCode()))//分部工程编号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectName()))//分部工程名称
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectStartStakeNo()))//分部工程起始桩号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getBranchProjectEndStakeNo()));//分部工程终止桩号
		}else if(groupLevel == 3){
			sb.append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectCode()))//分项工程编号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectName()))//分项工程名称
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectStartStakeNo()))//分项工程起始桩号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getSubitemProjectEndStakeNo()));//分项工程终止桩号;
		}else if(groupLevel == 4){
			sb.append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getPartCode()))//部位编号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getConcreteParagraphOrPart()))//具体段落或部位
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getPartStartStakeNo()))//部位起始桩号
			  .append(StringUtils.trimToEmpty(busiContractTendersBranchImport.getPartEndStakeNo()));//部位终止桩号
		}
	    return sb.toString();
	}
	
	
	@Override
	public List<BusiContractTendersBranchDto> getBusiContractTendersBranchDtoByTendersBranchName(String tendersBranchName, Long orgId) {
		List<BusiContractTendersBranchDto> dtoList = null;
		QueryPage queryPage = new QueryPage();
		List<QueryParam> queryParamList = new ArrayList<QueryParam>();
		QueryParam name = new QueryParam();
		name.setField("tendersBranchName");
		name.setLogic("alllike");
		name.setValue(tendersBranchName);
		//name.setValue(tendersBranchName);
		name.setType("string");
		QueryParam org = new QueryParam();
		org.setField("busiFwOrg.id");
		org.setLogic("=");
		org.setValue(orgId.toString());
		org.setType("string");
		queryParamList.add(name);
		queryParamList.add(org);
		queryPage.setQueryParamList(queryParamList);
		
		List<BusiContractTendersBranch> entityList = busiContractTendersBranchDao.findAllBusiContractTendersBranch(queryPage);
		
		if(entityList != null && !entityList.isEmpty()){
			dtoList = BeanCopy.getInstance().convertList(entityList, BusiContractTendersBranchDto.class);
			return dtoList;
		} else {
			return null;
		}
	}
	
	@Override
	public List<BusiContractTendersBranchDto> getBusiContractTendersBranchDtoByLevel(Map<String, List<String>> conditionLevelMap, Long orgId) {
		List<BusiContractTendersBranchDto> dtoList = null;
		
		QueryPage queryPage = new QueryPage();
		List<QueryParam> queryParamList = new ArrayList<QueryParam>();
		queryPage.setGroupBy(" level1,level2,level3,level4,level5,level6,level7,level8,level9,level10 ");
		StringBuilder sb = new StringBuilder(" ( ");
		for(String key : conditionLevelMap.keySet()){
			if(!conditionLevelMap.get(key).isEmpty()){
				sb.append(key);
				sb.append(" in ( ");
				sb.append(listToString(conditionLevelMap.get(key), ","));
				sb.append(" ) or ");
			}
		}
		StringBuilder s = new StringBuilder(sb.toString().substring(0, sb.toString().length() - 3));
		s.append(" ) ");
		queryPage.setSqlCondition(s.toString());
		QueryParam org = new QueryParam();
		org.setField("busiFwOrg.id");
		org.setLogic("=");
		org.setValue(orgId.toString());
		org.setType("string");
		queryParamList.add(org);
		queryPage.setQueryParamList(queryParamList);
		
		List<BusiContractTendersBranch> entityList = busiContractTendersBranchDao.findAllBusiContractTendersBranch(queryPage);
		
		if(entityList != null && !entityList.isEmpty()){
			dtoList = BeanCopy.getInstance().convertList(entityList, BusiContractTendersBranchDto.class);
			return dtoList;
		} else {
			return null;
		}
	}
	
	private String listToString(List<String> list, String separator) {
		StringBuilder sb = new StringBuilder("'");
		for(String s : list){
			sb.append(s);
			sb.append("',");
		}
		return sb.toString().substring(0, sb.length()  - 1);
		//return StringUtils.join(list.toArray(), separator);
	}

	/**
	 * 根据orgID和修改时间查询所有修改的分部分项数据(for App)
	 */
	@Override
	public List<BusiContractTendersBranchDto> getAllLevelOfEditBusiContractTendersBranchDto(QueryPage queryPage) {
//		if(!UserUtil.getUser().getFromApp()){
//			queryPage.setSqlCondition(" busiFwOrg.level3 ="+  UserUtil.getUser().getCurrProId()+" ");
//		}
		queryPage.setSqlCondition(" busiFwOrg.level3 ="+  UserUtil.getUser().getCurrProId()+" ");
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
  	    List<BusiContractTendersBranch> busiContractTendersBranchList = busiContractTendersBranchDao.findAllBusiContractTendersBranch(queryPage);
		return afreshCopyTendersBranchDtoList(busiContractTendersBranchList);
	}
	
}