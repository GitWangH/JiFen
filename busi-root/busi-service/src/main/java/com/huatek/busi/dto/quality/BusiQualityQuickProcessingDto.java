package com.huatek.busi.dto.quality;

import javax.persistence.Column;
/**
 * 快捷处理Dto
 * @author rocky_wei
 *
 */
public class BusiQualityQuickProcessingDto {

	private Long id;
    
	/** @Fields dataSource : 快速处理的类型，例：水泥拌合站，水稳拌合站等.(数据字典)*/ 
    private String dataSource;
    
	/** @Fields orgId : 组织机构ID */
    private String orgId;
    private String orgName;
    
	/** @Fields creater : 创建人ID */
    private Long creater;
    
	/** @Fields createrName : 创建人姓名*/ 
    private String createrName;
    
	/** @Fields createTime : 创建时间 */
    private String createTime;
    
	/** @Fields modifer : 修改人ID */
    private String modifer;
    
	/** @Fields modifierName : 修改人姓名*/ 
    private String modifierName;
    
	/** @Fields modifyTime : 修改时间 */
    private String modifyTime;
    
	/** @Fields tenantId : 租户id */
    private String tenantId;
    
	/** @Fields applyTime : 申请时间 */
    private String applyTime;
    
	/** @Fields applyUserId : 申请人ID */
    private String applyUserId;
    
	/** @Fields applyUserName : 申请人名称*/ 
    private String applyUserName;
    
	/** @Fields approvalStatus : 审批状态*/ 
    private String approvalStatus;
    
	/** @Fields approvalTime : 审批完成时间 */
    private String approvalTime;
    
	/** @Fields approvalUserId : 审批人ID */
    private String approvalUserId;
    
	/** @Fields approvalUserName : 审批人名称*/ 
    private String approvalUserName;
    
	/** @Fields flowInstanceId : 流程实例ID */
    private String flowInstanceId;
    
	/** @Fields flowResult : 审批结果*/ 
    private String flowResult;
    
	/** @Fields flowMessage : 审批意见*/ 
    private String flowMessage;
    
	/** @Fields quickReason : 快速处理中的原因（数据字典）*/ 
    private String quickReason;
    
	/** @Fields quickExplain : 快速处理中的说明*/ 
	private String quickExplain;
    
	/** @Fields quickProcessingTime : 快速处理中的处理时间 */
    private String quickProcessingTime;
    
	/** @Fields quickUserName : 快速处理人*/ 
	private String quickUserName;
	
	/** @Fields quickUserId : 快速处理人ID*/
    private String quickUserId;
    
	/** @Fields enclosure : 附件*/ 
    private String enclosure;
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
    private String isDelete;
    
    /** @Fields quickProcessCode : 快捷处理编码 */
	private String quickProcessCode;

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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getQuickReason() {
		return quickReason;
	}

	public void setQuickReason(String quickReason) {
		this.quickReason = quickReason;
	}

	public String getQuickExplain() {
		return quickExplain;
	}

	public void setQuickExplain(String quickExplain) {
		this.quickExplain = quickExplain;
	}

	public String getQuickProcessingTime() {
		return quickProcessingTime;
	}

	public void setQuickProcessingTime(String quickProcessingTime) {
		this.quickProcessingTime = quickProcessingTime;
	}

	public String getQuickUserName() {
		return quickUserName;
	}

	public void setQuickUserName(String quickUserName) {
		this.quickUserName = quickUserName;
	}

	public String getQuickUserId() {
		return quickUserId;
	}

	public void setQuickUserId(String quickUserId) {
		this.quickUserId = quickUserId;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getQuickProcessCode() {
		return quickProcessCode;
	}

	public void setQuickProcessCode(String quickProcessCode) {
		this.quickProcessCode = quickProcessCode;
	}
    
}
