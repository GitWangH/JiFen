package com.huatek.busi.model.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractTendersContract
 * @Description: 标段合同表 (施工合同)
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:02:33
 * @version: 1.0
 */
@Entity
@Table(name = "busi_contract_tenders_contract")
public class BusiContractTendersContract extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "TENDERS_CONTRACT_ID", nullable = true)
	private Long id;

	/** @Fields contractCode : 合同编号 */
	@Column(name = "CONTRACT_CODE", nullable = true, length = 50)
	private String contractCode;

	/** @Fields contractCnName : 合同名称（中） */
	@Column(name = "CONTRACT_CN_NAME", nullable = false, length = 100)
	private String contractCnName;

	/** @Fields contractEnName : 合同名称（英） */
	@Column(name = "CONTRACT_EN_NAME", nullable = false, length = 100)
	private String contractEnName;

	/** @Fields startStakeNo : 起始桩号 */
	@Column(name = "START_STAKE_NO", nullable = false, length = 100)
	private String startStakeNo;

	/** @Fields endStakeNo : 结束桩号 */
	@Column(name = "END_STAKE_NO", nullable = false, length = 100)
	private String endStakeNo;

	/** @Fields technicalLevel : 技术等级 */
	@Column(name = "TECHNICAL_LEVEL", nullable = false, length = 30)
	private String technicalLevel;

	/** @Fields overallLength : 全长（KM） */
	@Column(name = "OVERALL_LENGTH", nullable = false, precision = 18, scale = 2)
	private BigDecimal overallLength;

	/** @Fields beginDate : 开工日期 */
	@Column(name = "BEGIN_DATE", nullable = false)
	private Date beginDate;

	/** @Fields planCompleteData : 计划完工日期 */
	@Column(name = "PLAN_COMPLETE_DATA", nullable = false)
	private Date planCompleteData;

	/** @Fields constructionCompanyCode : 建设单位 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONSTRUCTION_COMPANY_CODE")
	@NotFound(action = NotFoundAction.IGNORE)// 处理这个异常：No row with the given identifier exists
	private BusiFwOrg constructionOrg;// 关联Org对象获取机构信息

	/** @Fields supervisionCompanyCode : 监理单位 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SUPERVISION_COMPANY_CODE")
	@NotFound(action = NotFoundAction.IGNORE)// 处理这个异常：No row with the given identifier exists
	private BusiFwOrg supervisionOrg;// 关联Org对象获取机构信息

	/** @Fields buildCompanyCode : 施工单位 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "BUILD_COMPANY_CODE")
	@NotFound(action = NotFoundAction.IGNORE)// 处理这个异常：No row with the given identifier exists
	private BusiFwOrg buildOrg;// 关联Org对象获取机构信息

	/** @Fields mobilizeAdvanceAmount : 动员预付款总额（元） */
	@Column(name = "MOBILIZE_ADVANCE_AMOUNT", nullable = false, precision = 18, scale = 4)
	private BigDecimal mobilizeAdvanceAmount;

	/** @Fields deductedMobilizeAdvanceRatio : 扣回动员预付款比例（%） */
	@Column(name = "DEDUCTED_MOBILIZE_ADVANCE_RATIO", nullable = false, precision = 6, scale = 2)
	private BigDecimal deductedMobilizeAdvanceRatio;

	/** @Fields mobilizeAdvanceDeductedMoney : 动员预付款起扣金额（元） */
	@Column(name = "MOBILIZE_ADVANCE_DEDUCTED_MONEY", nullable = false, precision = 18, scale = 4)
	private BigDecimal mobilizeAdvanceDeductedMoney;

	/** @Fields mobilizeAdvanceAmountDeductedLimit : 动员预付款全额扣回限额（元） */
	@Column(name = "MOBILIZE_ADVANCE_AMOUNT_DEDUCTED_LIMIT", nullable = false, precision = 18, scale = 4)
	private BigDecimal mobilizeAdvanceAmountDeductedLimit;

	/** @Fields detainRetentionMoneyRatio : 暂扣保留金额比例（%） */
	@Column(name = "DETAIN_RETENTION_MONEY_RATIO", nullable = false, precision = 6, scale = 2)
	private BigDecimal detainRetentionMoneyRatio;

	/** @Fields retentionDeductedMoney : 保留金起扣金额（元） */
	@Column(name = "RETENTION_DEDUCTED_MONEY", nullable = false, precision = 18, scale = 4)
	private BigDecimal retentionDeductedMoney;

	/** @Fields cumulativeDetainRetentionMoneyLimit : 累计暂扣保留金限额（元） */
	@Column(name = "CUMULATIVE_DETAIN_RETENTION_MONEY_LIMIT", nullable = false, precision = 18, scale = 4)
	private BigDecimal cumulativeDetainRetentionMoneyLimit;

	/** @Fields materialDeductMoneyRatio : 材料扣回款比例（%） */
	@Column(name = "MATERIAL_DEDUCT_MONEY_RATIO", nullable = false, precision = 6, scale = 2)
	private BigDecimal materialDeductMoneyRatio;

	/** @Fields materialAdvanceMoneyRatio : 材料预付款比例（%） */
	@Column(name = "MATERIAL_ADVANCE_MONEY_RATIO", nullable = false, precision = 6, scale = 2)
	private BigDecimal materialAdvanceMoneyRatio;

	/** @Fields materialAdvanceMoneyLimit : 材料预付款限额（元） */
	@Column(name = "MATERIAL_ADVANCE_MONEY_LIMIT", nullable = false, precision = 18, scale = 4)
	private BigDecimal materialAdvanceMoneyLimit;

	/** @Fields latePaymentDailyInterest : 迟付款日利息（‰） */
	@Column(name = "LATE_PAYMENT_DAILY_INTEREST", nullable = false, precision = 6, scale = 2)
	private BigDecimal latePaymentDailyInterest;

	/** @Fields latePaymentInterestMoneyLimit : 迟付款利息限额（元） */
	@Column(name = "LATE_PAYMENT_INTEREST_MONEY_LIMIT", nullable = false, precision = 18, scale = 4)
	private BigDecimal latePaymentInterestMoneyLimit;

	/** @Fields liquidatedDamagesLimit : 违约金限额（元） */
	@Column(name = "LIQUIDATED_DAMAGES_LIMIT", nullable = false, precision = 18, scale = 4)
	private BigDecimal liquidatedDamagesLimit;

	/** @Fields claimMoneyLimit : 索赔金额限额（元） */
	@Column(name = "CLAIM_MONEY_LIMIT", nullable = false, precision = 18, scale = 4)
	private BigDecimal claimMoneyLimit;

	/** @Fields detailedListMoney : 清单金额（元） */
	@Column(name = "DETAILED_LIST_MONEY", nullable = false, precision = 18, scale = 4)
	private BigDecimal detailedListMoney;

	/** @Fields dayworkMoney : 计日工金额（元） */
	@Column(name = "DAYWORK_MONEY", nullable = false, precision = 18, scale = 4)
	private BigDecimal dayworkMoney;

	/** @Fields provisionalMoney : 暂定金金额（元） */
	@Column(name = "PROVISIONAL_MONEY", nullable = false, precision = 18, scale = 4)
	private BigDecimal provisionalMoney;

	/** @Fields deductionMigrantWorkersSalaryDeposit : 扣农民工工资保证金（元） */
	@Column(name = "DEDUCTION_MIGRANT_WORKERS_SALARY_DEPOSIT", nullable = false, precision = 18, scale = 4)
	private BigDecimal deductionMigrantWorkersSalaryDeposit;

	/** @Fields incrementDutyRatio : 增值税比例（%） */
	@Column(name = "INCREMENT_DUTY_RATIO", nullable = false, precision = 6, scale = 2)
	private BigDecimal incrementDutyRatio;

	/** @Fields contractTotalPrice : 合同总价（元） */
	@Column(name = "CONTRACT_TOTAL_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal contractTotalPrice;

	/** @Fields reviewTotalPrice : 复核总价（元） */
	@Column(name = "REVIEW_TOTAL_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal reviewTotalPrice;

	/** @Fields contractDescription : 合同描述 */
	@Column(name = "CONTRACT_DESCRIPTION", nullable = false, length = 255)
	private String contractDescription;

	/** @Fields contractFile : 合同附件 */
	@Column(name = "CONTRACT_FILE", nullable = false, length = 32)
	private String contractFile;

	/** @Fields applyTime : 申请时间 */
	@Column(name = "APPLY_TIME", nullable = false)
	private Date applyTime;

	/** @Fields applyUserId : 申请人ID */
	@Column(name = "APPLY_USER_ID", nullable = false)
	private Long applyUserId;

	/** @Fields applyUserName : 申请人名称 */
	@Column(name = "APPLY_USER_NAME", nullable = false, length = 100)
	private String applyUserName;

	/** @Fields approvalStatus : 审批状态 */
	@Column(name = "APPROVAL_STATUS", nullable = false, length = 30)
	private String approvalStatus;

	/** @Fields approvalTime : 审批完成时间 */
	@Column(name = "APPROVAL_TIME", nullable = false)
	private Date approvalTime;

	/** @Fields approvalUserId : 审批人ID */
	@Column(name = "APPROVAL_USER_ID", nullable = false)
	private Long approvalUserId;

	/** @Fields approvalUserName : 审批人名称 */
	@Column(name = "APPROVAL_USER_NAME", nullable = false, length = 100)
	private String approvalUserName;

	/** @Fields flowInstanceId : 流程实例ID */
	@Column(name = "FLOW_INSTANCE_ID", nullable = false)
	private Long flowInstanceId;

	/** @Fields flowResult : 审批结果 */
	@Column(name = "FLOW_RESULT", nullable = false, length = 30)
	private String flowResult;

	/** @Fields flowMessage : 审批意见 */
	@Column(name = "FLOW_MESSAGE", nullable = false, length = 100)
	private String flowMessage;

	/** @Fields creater : 创建人id */
	@Column(name = "CREATER", nullable = false)
	private Long creater;

	/** @Fields createrName : 创建人姓名 */
	@Column(name = "CREATER_NAME", nullable = false, length = 100)
	private String createrName;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields modifer : 修改人id */
	@Column(name = "MODIFER", nullable = false)
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	@Column(name = "MODIFIER_NAME", nullable = false, length = 100)
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;

	/** @Fields tenantId : 租户id */
	@Column(name = "TENANT_ID", nullable = false)
	private Long tenantId;

	/** @Fields orgId : 组织机构ID */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg busiFwOrg;// 关联Org对象获取机构信息

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields busicontractcontractchangeSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "busiContractTendersContract")
	private Set<BusiContractContractChange> busicontractcontractchangeSet;

	/** @Fields busicontracttendersbranchSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "busiContractTendersContract")
	private Set<BusiContractTendersBranch> busicontracttendersbranchSet;

	/** @Fields busicontracttenderscontractdetailSet : */
	/*// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "busiContractTendersContract")
	private Set<BusiContractTendersContractDetail> busicontracttenderscontractdetailSet;*/

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

	public BigDecimal getOverallLength() {
		return overallLength;
	}

	public void setOverallLength(BigDecimal overallLength) {
		this.overallLength = overallLength;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getPlanCompleteData() {
		return planCompleteData;
	}

	public void setPlanCompleteData(Date planCompleteData) {
		this.planCompleteData = planCompleteData;
	}

	public BusiFwOrg getConstructionOrg() {
		return constructionOrg;
	}

	public void setConstructionOrg(BusiFwOrg constructionOrg) {
		this.constructionOrg = constructionOrg;
	}

	public BusiFwOrg getSupervisionOrg() {
		return supervisionOrg;
	}

	public void setSupervisionOrg(BusiFwOrg supervisionOrg) {
		this.supervisionOrg = supervisionOrg;
	}

	public BusiFwOrg getBuildOrg() {
		return buildOrg;
	}

	public void setBuildOrg(BusiFwOrg buildOrg) {
		this.buildOrg = buildOrg;
	}

	public BigDecimal getMobilizeAdvanceAmount() {
		return mobilizeAdvanceAmount;
	}

	public void setMobilizeAdvanceAmount(BigDecimal mobilizeAdvanceAmount) {
		this.mobilizeAdvanceAmount = mobilizeAdvanceAmount;
	}

	public BigDecimal getDeductedMobilizeAdvanceRatio() {
		return deductedMobilizeAdvanceRatio;
	}

	public void setDeductedMobilizeAdvanceRatio(
			BigDecimal deductedMobilizeAdvanceRatio) {
		this.deductedMobilizeAdvanceRatio = deductedMobilizeAdvanceRatio;
	}

	public BigDecimal getMobilizeAdvanceDeductedMoney() {
		return mobilizeAdvanceDeductedMoney;
	}

	public void setMobilizeAdvanceDeductedMoney(
			BigDecimal mobilizeAdvanceDeductedMoney) {
		this.mobilizeAdvanceDeductedMoney = mobilizeAdvanceDeductedMoney;
	}

	public BigDecimal getMobilizeAdvanceAmountDeductedLimit() {
		return mobilizeAdvanceAmountDeductedLimit;
	}

	public void setMobilizeAdvanceAmountDeductedLimit(
			BigDecimal mobilizeAdvanceAmountDeductedLimit) {
		this.mobilizeAdvanceAmountDeductedLimit = mobilizeAdvanceAmountDeductedLimit;
	}

	public BigDecimal getDetainRetentionMoneyRatio() {
		return detainRetentionMoneyRatio;
	}

	public void setDetainRetentionMoneyRatio(
			BigDecimal detainRetentionMoneyRatio) {
		this.detainRetentionMoneyRatio = detainRetentionMoneyRatio;
	}

	public BigDecimal getRetentionDeductedMoney() {
		return retentionDeductedMoney;
	}

	public void setRetentionDeductedMoney(BigDecimal retentionDeductedMoney) {
		this.retentionDeductedMoney = retentionDeductedMoney;
	}

	public BigDecimal getCumulativeDetainRetentionMoneyLimit() {
		return cumulativeDetainRetentionMoneyLimit;
	}

	public void setCumulativeDetainRetentionMoneyLimit(
			BigDecimal cumulativeDetainRetentionMoneyLimit) {
		this.cumulativeDetainRetentionMoneyLimit = cumulativeDetainRetentionMoneyLimit;
	}

	public BigDecimal getMaterialDeductMoneyRatio() {
		return materialDeductMoneyRatio;
	}

	public void setMaterialDeductMoneyRatio(BigDecimal materialDeductMoneyRatio) {
		this.materialDeductMoneyRatio = materialDeductMoneyRatio;
	}

	public BigDecimal getMaterialAdvanceMoneyRatio() {
		return materialAdvanceMoneyRatio;
	}

	public void setMaterialAdvanceMoneyRatio(
			BigDecimal materialAdvanceMoneyRatio) {
		this.materialAdvanceMoneyRatio = materialAdvanceMoneyRatio;
	}

	public BigDecimal getMaterialAdvanceMoneyLimit() {
		return materialAdvanceMoneyLimit;
	}

	public void setMaterialAdvanceMoneyLimit(
			BigDecimal materialAdvanceMoneyLimit) {
		this.materialAdvanceMoneyLimit = materialAdvanceMoneyLimit;
	}

	public BigDecimal getLatePaymentDailyInterest() {
		return latePaymentDailyInterest;
	}

	public void setLatePaymentDailyInterest(BigDecimal latePaymentDailyInterest) {
		this.latePaymentDailyInterest = latePaymentDailyInterest;
	}

	public BigDecimal getLatePaymentInterestMoneyLimit() {
		return latePaymentInterestMoneyLimit;
	}

	public void setLatePaymentInterestMoneyLimit(
			BigDecimal latePaymentInterestMoneyLimit) {
		this.latePaymentInterestMoneyLimit = latePaymentInterestMoneyLimit;
	}

	public BigDecimal getLiquidatedDamagesLimit() {
		return liquidatedDamagesLimit;
	}

	public void setLiquidatedDamagesLimit(BigDecimal liquidatedDamagesLimit) {
		this.liquidatedDamagesLimit = liquidatedDamagesLimit;
	}

	public BigDecimal getClaimMoneyLimit() {
		return claimMoneyLimit;
	}

	public void setClaimMoneyLimit(BigDecimal claimMoneyLimit) {
		this.claimMoneyLimit = claimMoneyLimit;
	}

	public BigDecimal getDetailedListMoney() {
		return detailedListMoney;
	}

	public void setDetailedListMoney(BigDecimal detailedListMoney) {
		this.detailedListMoney = detailedListMoney;
	}

	public BigDecimal getDayworkMoney() {
		return dayworkMoney;
	}

	public void setDayworkMoney(BigDecimal dayworkMoney) {
		this.dayworkMoney = dayworkMoney;
	}

	public BigDecimal getProvisionalMoney() {
		return provisionalMoney;
	}

	public void setProvisionalMoney(BigDecimal provisionalMoney) {
		this.provisionalMoney = provisionalMoney;
	}

	public BigDecimal getDeductionMigrantWorkersSalaryDeposit() {
		return deductionMigrantWorkersSalaryDeposit;
	}

	public void setDeductionMigrantWorkersSalaryDeposit(
			BigDecimal deductionMigrantWorkersSalaryDeposit) {
		this.deductionMigrantWorkersSalaryDeposit = deductionMigrantWorkersSalaryDeposit;
	}

	public BigDecimal getIncrementDutyRatio() {
		return incrementDutyRatio;
	}

	public void setIncrementDutyRatio(BigDecimal incrementDutyRatio) {
		this.incrementDutyRatio = incrementDutyRatio;
	}

	public BigDecimal getContractTotalPrice() {
		return contractTotalPrice;
	}

	public void setContractTotalPrice(BigDecimal contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}

	public BigDecimal getReviewTotalPrice() {
		return reviewTotalPrice;
	}

	public void setReviewTotalPrice(BigDecimal reviewTotalPrice) {
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

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
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

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public BusiFwOrg getBusiFwOrg() {
		return busiFwOrg;
	}

	public void setBusiFwOrg(BusiFwOrg busiFwOrg) {
		this.busiFwOrg = busiFwOrg;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Set<BusiContractContractChange> getBusicontractcontractchangeSet() {
		return busicontractcontractchangeSet;
	}

	public void setBusicontractcontractchangeSet(
			Set<BusiContractContractChange> busicontractcontractchangeSet) {
		this.busicontractcontractchangeSet = busicontractcontractchangeSet;
	}

	public Set<BusiContractTendersBranch> getBusicontracttendersbranchSet() {
		return busicontracttendersbranchSet;
	}

	public void setBusicontracttendersbranchSet(
			Set<BusiContractTendersBranch> busicontracttendersbranchSet) {
		this.busicontracttendersbranchSet = busicontracttendersbranchSet;
	}

	/*public Set<BusiContractTendersContractDetail> getBusicontracttenderscontractdetailSet() {
		return busicontracttenderscontractdetailSet;
	}

	public void setBusicontracttenderscontractdetailSet(
			Set<BusiContractTendersContractDetail> busicontracttenderscontractdetailSet) {
		this.busicontracttenderscontractdetailSet = busicontracttenderscontractdetailSet;
	}*/

}
