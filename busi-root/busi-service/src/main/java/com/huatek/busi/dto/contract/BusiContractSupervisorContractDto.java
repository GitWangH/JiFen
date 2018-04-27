package com.huatek.busi.dto.contract;



/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractSupervisorContract
 * @Description: 监理合同
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 11:23:41
 * @version: 1.0
 */
public class BusiContractSupervisorContractDto {

	private Long id;
	private String contractName;// 合同名称
	private String contractCode;// 合同编号
	private String signatureData;// 签订日期
	private String contractTotalPrice;// 合同金额（元）
	private String provisionalMoney;// 暂定金（元）
	private String contractDescription;// 描述
	private String contractFile;// 合同附件
	private String applyTime;// 申请时间
	private Long applyUserId;// 申请人ID
	private String applyUserName;// 申请人名称
	private String approvalStatus;// 审批状态
	private String approvalTime;// 审批完成时间
	private Long approvalUserId;// 审批人ID
	private String approvalUserName;// 审批人名称
	private Long flowInstanceId;// 流程实例ID
	private String flowResult;// 审批结
	private String flowMessage;// 审批意见
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户id
	private Long orgId;// 组织机构ID
	private String orgName;// 单位名称

//	private Set<BusiContractSupervisorContractDetaileListDto> busicontractsupervisorcontractdetailelistDtoSet;

	public BusiContractSupervisorContractDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSignatureData() {
		return signatureData;
	}

	public void setSignatureData(String signatureData) {
		this.signatureData = signatureData;
	}

	public String getContractTotalPrice() {
		return contractTotalPrice;
	}

	public void setContractTotalPrice(String contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}

	public String getProvisionalMoney() {
		return provisionalMoney;
	}

	public void setProvisionalMoney(String provisionalMoney) {
		this.provisionalMoney = provisionalMoney;
	}

	public String getContractDescription() {
		return contractDescription;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public String getContractFile() {
		return contractFile;
	}

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public Long getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Long getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(Long approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public String getApprovalUserName() {
		return approvalUserName;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public Long getFlowInstanceId() {
		return flowInstanceId;
	}

	public void setFlowInstanceId(Long flowInstanceId) {
		this.flowInstanceId = flowInstanceId;
	}

	public String getFlowResult() {
		return flowResult;
	}

	public void setFlowResult(String flowResult) {
		this.flowResult = flowResult;
	}

	public String getFlowMessage() {
		return flowMessage;
	}

	public void setFlowMessage(String flowMessage) {
		this.flowMessage = flowMessage;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/*public Set<BusiContractSupervisorContractDetaileListDto> getBusicontractsupervisorcontractdetailelistDtoSet() {
		return busicontractsupervisorcontractdetailelistDtoSet;
	}

	public void setBusicontractsupervisorcontractdetailelistDtoSet(
			Set<BusiContractSupervisorContractDetaileListDto> busicontractsupervisorcontractdetailelistDtoSet) {
		this.busicontractsupervisorcontractdetailelistDtoSet = busicontractsupervisorcontractdetailelistDtoSet;
	}*/

}
