package com.huatek.busi.dto.contract;


/**
 * @ClassName: BusiContractTendersContractDto
 * @Description: 标段合同表 (施工合同)Dto
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 13:18:35
 * @version: 1.0
 */
public class BusiContractTendersContractDto {

	private Long id;// 主键ID
	private String contractCode;// 合同编号
	private String contractCnName;// 合同名称（中）
	private String contractEnName;// 合同名称（英）
	private String startStakeNo;// 起始桩号
	private String endStakeNo;// 结束桩号
	private String technicalLevel;// 技术等级
	private String overallLength;// 全长（KM）
	private String beginDate;// 开工日期
	private String planCompleteData;// 计划完工日期
	private String supervisionCompanyName;// 监理单位
	private Long supervisionCompanyCode;// 监理单位id
	private String constructionCompanyName;// 建设单位名称
	private Long constructionCompanyCode;// 建设单位id
	private String buildCompanyName;// 施工单位id
	private Long buildCompanyCode;// 施工单位名称
	private String mobilizeAdvanceAmount;// 动员预付款总额（元）
	private String deductedMobilizeAdvanceRatio;// 扣回动员预付款比例（%）
	private String mobilizeAdvanceDeductedMoney;// 动员预付款起扣金额（元）
	private String mobilizeAdvanceAmountDeductedLimit;// 动员预付款全额扣回限额（元）
	private String detainRetentionMoneyRatio;// 暂扣保留金额比例（%）
	private String retentionDeductedMoney;// 保留金起扣金额（元）
	private String cumulativeDetainRetentionMoneyLimit;// 累计暂扣保留金限额（元）
	private String materialDeductMoneyRatio;// 材料扣回款比例（%）
	private String materialAdvanceMoneyRatio;// 材料预付款比例（%）
	private String materialAdvanceMoneyLimit;// 材料预付款限额（元）
	private String latePaymentDailyInterest;// 迟付款日利息（‰）
	private String latePaymentInterestMoneyLimit;// 迟付款利息限额（元）
	private String liquidatedDamagesLimit;// 违约金限额（元）
	private String claimMoneyLimit;// 索赔金额限额（元）
	private String detailedListMoney;// 清单金额（元）
	private String dayworkMoney;// 计日工金额（元）
	private String provisionalMoney;// 暂定金金额（元）
	private String deductionMigrantWorkersSalaryDeposit;// 扣农民工工资保证金（元）
	private String incrementDutyRatio;// 增值税比例（%）
	private String contractTotalPrice;// 合同总价（元）
	private String reviewTotalPrice;// 复核总价（元）
	private String contractDescription;// 合同描述
	private String contractFile;// 合同附件
	private String applyTime;// 申请时间
	private Long applyUserId;// 申请人ID
	private String applyUserName;// 申请人名称
	private String approvalStatus;// 审批状态
	private String approvalTime;// 审批完成时间
	private Long approvalUserId;// 审批人ID
	private String approvalUserName;// 审批人名称
	private Long flowInstanceId;// 流程实例ID
	private String flowResult;// 审批结果
	private String flowMessage;// 审批意见
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户id
	private Long orgId;// 组织机构ID
	private String orgName;// 组织机构名称

	public BusiContractTendersContractDto() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractCnName() {
		return contractCnName;
	}

	public void setContractCnName(String contractCnName) {
		this.contractCnName = contractCnName;
	}

	public String getContractEnName() {
		return contractEnName;
	}

	public void setContractEnName(String contractEnName) {
		this.contractEnName = contractEnName;
	}

	public String getStartStakeNo() {
		return startStakeNo;
	}

	public void setStartStakeNo(String startStakeNo) {
		this.startStakeNo = startStakeNo;
	}

	public String getEndStakeNo() {
		return endStakeNo;
	}

	public void setEndStakeNo(String endStakeNo) {
		this.endStakeNo = endStakeNo;
	}

	public String getTechnicalLevel() {
		return technicalLevel;
	}

	public void setTechnicalLevel(String technicalLevel) {
		this.technicalLevel = technicalLevel;
	}

	public String getOverallLength() {
		return overallLength;
	}

	public void setOverallLength(String overallLength) {
		this.overallLength = overallLength;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getPlanCompleteData() {
		return planCompleteData;
	}

	public void setPlanCompleteData(String planCompleteData) {
		this.planCompleteData = planCompleteData;
	}

	public String getSupervisionCompanyName() {
		return supervisionCompanyName;
	}

	public void setSupervisionCompanyName(String supervisionCompanyName) {
		this.supervisionCompanyName = supervisionCompanyName;
	}

	public Long getSupervisionCompanyCode() {
		return supervisionCompanyCode;
	}

	public void setSupervisionCompanyCode(Long supervisionCompanyCode) {
		this.supervisionCompanyCode = supervisionCompanyCode;
	}

	public String getConstructionCompanyName() {
		return constructionCompanyName;
	}

	public void setConstructionCompanyName(String constructionCompanyName) {
		this.constructionCompanyName = constructionCompanyName;
	}

	public Long getConstructionCompanyCode() {
		return constructionCompanyCode;
	}

	public void setConstructionCompanyCode(Long constructionCompanyCode) {
		this.constructionCompanyCode = constructionCompanyCode;
	}

	public String getBuildCompanyName() {
		return buildCompanyName;
	}

	public void setBuildCompanyName(String buildCompanyName) {
		this.buildCompanyName = buildCompanyName;
	}

	public Long getBuildCompanyCode() {
		return buildCompanyCode;
	}

	public void setBuildCompanyCode(Long buildCompanyCode) {
		this.buildCompanyCode = buildCompanyCode;
	}

	public String getMobilizeAdvanceAmount() {
		return mobilizeAdvanceAmount;
	}

	public void setMobilizeAdvanceAmount(String mobilizeAdvanceAmount) {
		this.mobilizeAdvanceAmount = mobilizeAdvanceAmount;
	}

	public String getDeductedMobilizeAdvanceRatio() {
		return deductedMobilizeAdvanceRatio;
	}

	public void setDeductedMobilizeAdvanceRatio(
			String deductedMobilizeAdvanceRatio) {
		this.deductedMobilizeAdvanceRatio = deductedMobilizeAdvanceRatio;
	}

	public String getMobilizeAdvanceDeductedMoney() {
		return mobilizeAdvanceDeductedMoney;
	}

	public void setMobilizeAdvanceDeductedMoney(
			String mobilizeAdvanceDeductedMoney) {
		this.mobilizeAdvanceDeductedMoney = mobilizeAdvanceDeductedMoney;
	}

	public String getMobilizeAdvanceAmountDeductedLimit() {
		return mobilizeAdvanceAmountDeductedLimit;
	}

	public void setMobilizeAdvanceAmountDeductedLimit(
			String mobilizeAdvanceAmountDeductedLimit) {
		this.mobilizeAdvanceAmountDeductedLimit = mobilizeAdvanceAmountDeductedLimit;
	}

	public String getDetainRetentionMoneyRatio() {
		return detainRetentionMoneyRatio;
	}

	public void setDetainRetentionMoneyRatio(String detainRetentionMoneyRatio) {
		this.detainRetentionMoneyRatio = detainRetentionMoneyRatio;
	}

	public String getRetentionDeductedMoney() {
		return retentionDeductedMoney;
	}

	public void setRetentionDeductedMoney(String retentionDeductedMoney) {
		this.retentionDeductedMoney = retentionDeductedMoney;
	}

	public String getCumulativeDetainRetentionMoneyLimit() {
		return cumulativeDetainRetentionMoneyLimit;
	}

	public void setCumulativeDetainRetentionMoneyLimit(
			String cumulativeDetainRetentionMoneyLimit) {
		this.cumulativeDetainRetentionMoneyLimit = cumulativeDetainRetentionMoneyLimit;
	}

	public String getMaterialDeductMoneyRatio() {
		return materialDeductMoneyRatio;
	}

	public void setMaterialDeductMoneyRatio(String materialDeductMoneyRatio) {
		this.materialDeductMoneyRatio = materialDeductMoneyRatio;
	}

	public String getMaterialAdvanceMoneyRatio() {
		return materialAdvanceMoneyRatio;
	}

	public void setMaterialAdvanceMoneyRatio(String materialAdvanceMoneyRatio) {
		this.materialAdvanceMoneyRatio = materialAdvanceMoneyRatio;
	}

	public String getMaterialAdvanceMoneyLimit() {
		return materialAdvanceMoneyLimit;
	}

	public void setMaterialAdvanceMoneyLimit(String materialAdvanceMoneyLimit) {
		this.materialAdvanceMoneyLimit = materialAdvanceMoneyLimit;
	}

	public String getLatePaymentDailyInterest() {
		return latePaymentDailyInterest;
	}

	public void setLatePaymentDailyInterest(String latePaymentDailyInterest) {
		this.latePaymentDailyInterest = latePaymentDailyInterest;
	}

	public String getLatePaymentInterestMoneyLimit() {
		return latePaymentInterestMoneyLimit;
	}

	public void setLatePaymentInterestMoneyLimit(
			String latePaymentInterestMoneyLimit) {
		this.latePaymentInterestMoneyLimit = latePaymentInterestMoneyLimit;
	}

	public String getLiquidatedDamagesLimit() {
		return liquidatedDamagesLimit;
	}

	public void setLiquidatedDamagesLimit(String liquidatedDamagesLimit) {
		this.liquidatedDamagesLimit = liquidatedDamagesLimit;
	}

	public String getClaimMoneyLimit() {
		return claimMoneyLimit;
	}

	public void setClaimMoneyLimit(String claimMoneyLimit) {
		this.claimMoneyLimit = claimMoneyLimit;
	}

	public String getDetailedListMoney() {
		return detailedListMoney;
	}

	public void setDetailedListMoney(String detailedListMoney) {
		this.detailedListMoney = detailedListMoney;
	}

	public String getDayworkMoney() {
		return dayworkMoney;
	}

	public void setDayworkMoney(String dayworkMoney) {
		this.dayworkMoney = dayworkMoney;
	}

	public String getProvisionalMoney() {
		return provisionalMoney;
	}

	public void setProvisionalMoney(String provisionalMoney) {
		this.provisionalMoney = provisionalMoney;
	}

	public String getDeductionMigrantWorkersSalaryDeposit() {
		return deductionMigrantWorkersSalaryDeposit;
	}

	public void setDeductionMigrantWorkersSalaryDeposit(
			String deductionMigrantWorkersSalaryDeposit) {
		this.deductionMigrantWorkersSalaryDeposit = deductionMigrantWorkersSalaryDeposit;
	}

	public String getIncrementDutyRatio() {
		return incrementDutyRatio;
	}

	public void setIncrementDutyRatio(String incrementDutyRatio) {
		this.incrementDutyRatio = incrementDutyRatio;
	}

	public String getContractTotalPrice() {
		return contractTotalPrice;
	}

	public void setContractTotalPrice(String contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}

	public String getReviewTotalPrice() {
		return reviewTotalPrice;
	}

	public void setReviewTotalPrice(String reviewTotalPrice) {
		this.reviewTotalPrice = reviewTotalPrice;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
