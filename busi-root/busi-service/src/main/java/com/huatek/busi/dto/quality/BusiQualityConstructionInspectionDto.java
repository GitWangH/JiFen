package com.huatek.busi.dto.quality;


public class BusiQualityConstructionInspectionDto {
	private Long id;

	private Long orgId;// 机构ID

	private String orgName;// 机构名称
	

	private String inspectionCompanyName;// 报检单名称

	private String beginConstructionDate;// 开工日期

	private Long tendersBranchId;// 分部分项(分部分项表ID)
	private String tendersBranchName;// 分部分项页面展示

	private Boolean isCompleted;// 是否已完成(Y、N)

	private String inspectionDate;// 报检日期

	private Long inspectionUserId;// 报检人员ID

	private String inspectionUserName;// 报检人员名称

	private String checkParagraph;// 检测段落

	private String inspectionContent;// 报检内容

	private String attachmentFile;// 附件

	private String qualityStatus;// 质量状态(字典表维护)
	private String qualityStatus_;// 质量状态页面显示

	private String applyTime;// 申请时间

	private Long applyUserId;// 申请人ID

	private String applyUserName;// 申请人名称

	private String approvalStatus;// 审批状态
	private String approvalStatus_;// 审批状态页面显示

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

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传
	
	private String inspectionCode;

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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getInspectionCompanyName() {
		return inspectionCompanyName;
	}

	public void setInspectionCompanyName(String inspectionCompanyName) {
		this.inspectionCompanyName = inspectionCompanyName;
	}

	public String getBeginConstructionDate() {
		return beginConstructionDate;
	}

	public void setBeginConstructionDate(String beginConstructionDate) {
		this.beginConstructionDate = beginConstructionDate;
	}

	public Long getTendersBranchId() {
		return tendersBranchId;
	}

	public void setTendersBranchId(Long tendersBranchId) {
		this.tendersBranchId = tendersBranchId;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public Long getInspectionUserId() {
		return inspectionUserId;
	}

	public void setInspectionUserId(Long inspectionUserId) {
		this.inspectionUserId = inspectionUserId;
	}

	public String getInspectionUserName() {
		return inspectionUserName;
	}

	public void setInspectionUserName(String inspectionUserName) {
		this.inspectionUserName = inspectionUserName;
	}

	public String getCheckParagraph() {
		return checkParagraph;
	}

	public void setCheckParagraph(String checkParagraph) {
		this.checkParagraph = checkParagraph;
	}

	public String getInspectionContent() {
		return inspectionContent;
	}

	public void setInspectionContent(String inspectionContent) {
		this.inspectionContent = inspectionContent;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getQualityStatus() {
		return qualityStatus;
	}

	public void setQualityStatus(String qualityStatus) {
		this.qualityStatus = qualityStatus;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsQualitySupervisionBureau() {
		return isQualitySupervisionBureau;
	}

	public void setIsQualitySupervisionBureau(Integer isQualitySupervisionBureau) {
		this.isQualitySupervisionBureau = isQualitySupervisionBureau;
	}

	public String getTendersBranchName() {
		return tendersBranchName;
	}

	public void setTendersBranchName(String tendersBranchName) {
		this.tendersBranchName = tendersBranchName;
	}

	public String getQualityStatus_() {
		return qualityStatus_;
	}

	public void setQualityStatus_(String qualityStatus_) {
		this.qualityStatus_ = qualityStatus_;
	}

	

	public String getApprovalStatus_() {
		return approvalStatus_;
	}

	public void setApprovalStatus_(String approvalStatus_) {
		this.approvalStatus_ = approvalStatus_;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}
	
}
