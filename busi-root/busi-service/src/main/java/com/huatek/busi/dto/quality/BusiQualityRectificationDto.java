package com.huatek.busi.dto.quality;


/**
 * 整改单数据转化对象
 * 
 * @author rocky_wei
 * @email rocky_wei@huatek.com
 */
public class BusiQualityRectificationDto {
	private Long id;

	/** @Fields dataSource : 被整改处理的类型，例：水泥拌合站，水稳拌合站等.(数据字典) */
	private String dataSource;
	/** @Fields applyTime : 申请时间 */
	private String applyTime;

	/** @Fields applyUserId : 申请人ID */
	private String applyUserId;

	/** @Fields applyUserName : 申请人名称 */
	private String applyUserName;

	/** @Fields approvalStatus : 审批状态 */
	private String approvalStatus;

	/** @Fields approvalTime : 审批完成时间 */
	private String approvalTime;

	/** @Fields approvalUserId : 审批人ID */
	private String approvalUserId;

	/** @Fields approvalUserName : 审批人名称 */
	private String approvalUserName;

	/** @Fields flowInstanceId : 流程实例ID */
	private String flowInstanceId;

	/** @Fields flowResult : 审批结果 */
	private Boolean flowResult;

	/** @Fields flowMessage : 审批意见 */
	private String flowMessage;

	/** @Fields rectificationCheckTime : 整改中的检查时间 */
	private String rectificationCheckTime;

	/** @Fields rectificationCheckContent : 整改中的检查内容 */
	private String rectificationCheckContent;

	/** @Fields rectificationUrgency : 整改中的紧急程度（数据字典） */
	private String rectificationUrgency;

	/** @Fields rectificationPeriod : 整改期限 */
	private String rectificationPeriod;

	/** @Fields rectificationPersonLiable : 相关责任人 */
	private String rectificationPersonLiable;

	/** @Fields rectificationPerson : 检查人员 */
	private String rectificationPerson;

	/** @Fields rectificationExistingProblems : 整改中存在问题 */
	private String rectificationExistingProblems;

	/** @Fields rectificationRequirements : 整改要求 */
	private String rectificationRequirements;

	/** @Fields rectificationPunishmentSuggestion : 处罚建议 */
	private String rectificationPunishmentSuggestion;

	/** @Fields orgId : 组织机构ID */
	private String orgId;
	private String orgName;
	/** @Fields creater : 创建人ID */
	private String creater;

	/** @Fields createrName : 创建人姓名 */
	private String createrName;

	/** @Fields createTime : 创建时间 */
	private String createTime;

	/** @Fields modifer : 修改人ID */
	private String modifer;

	/** @Fields modifierName : 修改人姓名 */
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	private String modifyTime;

	/** @Fields tenantId : 租户id */
	private String tenantId;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	private String isDelete;

	/** @Fields rectificationCode : 整改编码 */
	private String rectificationCode;

	/** @Fields fileId : 上传文件编码 */
	private String fileId;

	/** @Fields checkNo : 检查编号 */
	private String checkNo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
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

	public String getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public String getApprovalUserName() {
		return approvalUserName;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public String getFlowInstanceId() {
		return flowInstanceId;
	}

	public void setFlowInstanceId(String flowInstanceId) {
		this.flowInstanceId = flowInstanceId;
	}

	public Boolean getFlowResult() {
		return flowResult;
	}

	public void setFlowResult(Boolean flowResult) {
		this.flowResult = flowResult;
	}

	public String getFlowMessage() {
		return flowMessage;
	}

	public void setFlowMessage(String flowMessage) {
		this.flowMessage = flowMessage;
	}

	public String getRectificationCheckTime() {
		return rectificationCheckTime;
	}

	public void setRectificationCheckTime(String rectificationCheckTime) {
		this.rectificationCheckTime = rectificationCheckTime;
	}

	public String getRectificationCheckContent() {
		return rectificationCheckContent;
	}

	public void setRectificationCheckContent(String rectificationCheckContent) {
		this.rectificationCheckContent = rectificationCheckContent;
	}

	public String getRectificationUrgency() {
		return rectificationUrgency;
	}

	public void setRectificationUrgency(String rectificationUrgency) {
		this.rectificationUrgency = rectificationUrgency;
	}

	public String getRectificationPeriod() {
		return rectificationPeriod;
	}

	public void setRectificationPeriod(String rectificationPeriod) {
		this.rectificationPeriod = rectificationPeriod;
	}

	public String getRectificationPersonLiable() {
		return rectificationPersonLiable;
	}

	public void setRectificationPersonLiable(String rectificationPersonLiable) {
		this.rectificationPersonLiable = rectificationPersonLiable;
	}

	public String getRectificationPerson() {
		return rectificationPerson;
	}

	public void setRectificationPerson(String rectificationPerson) {
		this.rectificationPerson = rectificationPerson;
	}

	public String getRectificationExistingProblems() {
		return rectificationExistingProblems;
	}

	public void setRectificationExistingProblems(
			String rectificationExistingProblems) {
		this.rectificationExistingProblems = rectificationExistingProblems;
	}

	public String getRectificationRequirements() {
		return rectificationRequirements;
	}

	public void setRectificationRequirements(String rectificationRequirements) {
		this.rectificationRequirements = rectificationRequirements;
	}

	public String getRectificationPunishmentSuggestion() {
		return rectificationPunishmentSuggestion;
	}

	public void setRectificationPunishmentSuggestion(
			String rectificationPunishmentSuggestion) {
		this.rectificationPunishmentSuggestion = rectificationPunishmentSuggestion;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
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

	public String getModifer() {
		return modifer;
	}

	public void setModifer(String modifer) {
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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getRectificationCode() {
		return rectificationCode;
	}

	public void setRectificationCode(String rectificationCode) {
		this.rectificationCode = rectificationCode;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
}
