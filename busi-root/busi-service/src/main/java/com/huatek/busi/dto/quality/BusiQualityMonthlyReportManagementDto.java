package com.huatek.busi.dto.quality;


 /**
  * 月报管理数据转化对象
  */
public class BusiQualityMonthlyReportManagementDto{

 	private Long id;
	/** @Fields orgId : 标段名称(机构ID，根据机构ID查询名称) */
    private Long orgId;
    /** @Fields orgName : 标段名称 */
    private String orgName;
	/** @Fields writeReportTime : 填报时间 */
    private String writeReportTime;
	/** @Fields writeReportUserId : 填报人ID */
    private String writeReportUserId;
	/** @Fields writeReportUserName : 填报人名称*/ 
    private String writeReportUserName;
	/** @Fields monthlyReportName : 文件名称*/ 
    private String monthlyReportName;
	/** @Fields monthlyReportCode : 文件编号*/ 
    private String monthlyReportCode;
	/** @Fields monthlyReportType : 月报类型(字典表：施工月报等)*/ 
    private String monthlyReportType;
    private String monthlyReportType_; /** 页面显示 */
	/** @Fields content : 正文*/ 
    private String content;
	/** @Fields attachmentFile : 附件*/ 
    private String attachmentFile;
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
    private String flowMessage_;/** 页面显示 */
	/** @Fields creater : 创建人id */
    private Long creater;
	/** @Fields createrName : 创建人姓名*/ 
    private String createrName;
	/** @Fields createTime : 创建时间 */
    private String createTime;
	/** @Fields modifer : 修改人id */
    private Long modifer;
	/** @Fields modifierName : 修改人姓名*/ 
    private String modifierName;
	/** @Fields modifyTime : 修改时间 */
    private String modifyTime;
	/** @Fields tenantId : 租户id */
    private Long tenantId;
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
    private Integer isDelete;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getWriteReportTime() {
		return writeReportTime;
	}
	public void setWriteReportTime(String writeReportTime) {
		this.writeReportTime = writeReportTime;
	}
	public String getWriteReportUserId() {
		return writeReportUserId;
	}
	public void setWriteReportUserId(String writeReportUserId) {
		this.writeReportUserId = writeReportUserId;
	}
	public String getWriteReportUserName() {
		return writeReportUserName;
	}
	public void setWriteReportUserName(String writeReportUserName) {
		this.writeReportUserName = writeReportUserName;
	}
	public String getMonthlyReportName() {
		return monthlyReportName;
	}
	public void setMonthlyReportName(String monthlyReportName) {
		this.monthlyReportName = monthlyReportName;
	}
	public String getMonthlyReportCode() {
		return monthlyReportCode;
	}
	public void setMonthlyReportCode(String monthlyReportCode) {
		this.monthlyReportCode = monthlyReportCode;
	}
	public String getMonthlyReportType() {
		return monthlyReportType;
	}
	public void setMonthlyReportType(String monthlyReportType) {
		this.monthlyReportType = monthlyReportType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
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
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getMonthlyReportType_() {
		return monthlyReportType_;
	}
	public void setMonthlyReportType_(String monthlyReportType_) {
		this.monthlyReportType_ = monthlyReportType_;
	}
	public String getFlowMessage_() {
		return flowMessage_;
	}
	public void setFlowMessage_(String flowMessage_) {
		this.flowMessage_ = flowMessage_;
	}
	
}
