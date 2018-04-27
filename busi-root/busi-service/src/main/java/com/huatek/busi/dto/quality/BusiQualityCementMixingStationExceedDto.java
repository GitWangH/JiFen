package com.huatek.busi.dto.quality;


public class BusiQualityCementMixingStationExceedDto {
	private Long id;

	private String isExcessive;// 0:未超标, 1：已超标

	private String excessiveGrade;// 超标等级(1:一级,2：二级, 3:三级)

	private String disposeState;// 处理状态（数据字典）

	private String excessiveReason;// 超标原因

	private String disposePerson;// 处理人

	private String disposeTime;// 处理时间

	private String disposeImage;// 处理图片(多张图片时用英文的逗号分隔,byte数组)

	private String disposeContent;// 处理内容

	private String disposeResult;// 处理结果

	private String examinePerson;// 审核人

	private String examineDatetime;// 审核时间

	private String examineImage;// 审核图片（多张图片时用英文的逗号分隔,byte数组）

	private String examineContent;// 审核内容

	private String examineResult;// 审核结果

	private String approvalPerson;// 审批人

	private String approvalDatetime;// 审批时间

	private String approvalImage;// 审批图片（多张图片时用英文的逗号分隔,byte数组）

	private String approvalContent;// 审批内容

	private String approvalResult;// 审批结果

	private String remarks;// 备注

	private String desContent;// 描述

	private String reportAddress;// 报告地址

	private String ukey;// 唯一码

	private Long orgId;// 机构id

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	private String appKey;// 接口传数据标识符

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsExcessive() {
		return isExcessive;
	}

	public void setIsExcessive(String isExcessive) {
		this.isExcessive = isExcessive;
	}

	public String getExcessiveGrade() {
		return excessiveGrade;
	}

	public void setExcessiveGrade(String excessiveGrade) {
		this.excessiveGrade = excessiveGrade;
	}

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getExcessiveReason() {
		return excessiveReason;
	}

	public void setExcessiveReason(String excessiveReason) {
		this.excessiveReason = excessiveReason;
	}

	public String getDisposePerson() {
		return disposePerson;
	}

	public void setDisposePerson(String disposePerson) {
		this.disposePerson = disposePerson;
	}

	public String getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}

	public String getDisposeImage() {
		return disposeImage;
	}

	public void setDisposeImage(String disposeImage) {
		this.disposeImage = disposeImage;
	}

	public String getDisposeContent() {
		return disposeContent;
	}

	public void setDisposeContent(String disposeContent) {
		this.disposeContent = disposeContent;
	}

	public String getDisposeResult() {
		return disposeResult;
	}

	public void setDisposeResult(String disposeResult) {
		this.disposeResult = disposeResult;
	}

	public String getExaminePerson() {
		return examinePerson;
	}

	public void setExaminePerson(String examinePerson) {
		this.examinePerson = examinePerson;
	}

	public String getExamineDatetime() {
		return examineDatetime;
	}

	public void setExamineDatetime(String examineDatetime) {
		this.examineDatetime = examineDatetime;
	}

	public String getExamineImage() {
		return examineImage;
	}

	public void setExamineImage(String examineImage) {
		this.examineImage = examineImage;
	}

	public String getExamineContent() {
		return examineContent;
	}

	public void setExamineContent(String examineContent) {
		this.examineContent = examineContent;
	}

	public String getExamineResult() {
		return examineResult;
	}

	public void setExamineResult(String examineResult) {
		this.examineResult = examineResult;
	}

	public String getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

	public String getApprovalDatetime() {
		return approvalDatetime;
	}

	public void setApprovalDatetime(String approvalDatetime) {
		this.approvalDatetime = approvalDatetime;
	}

	public String getApprovalImage() {
		return approvalImage;
	}

	public void setApprovalImage(String approvalImage) {
		this.approvalImage = approvalImage;
	}

	public String getApprovalContent() {
		return approvalContent;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDesContent() {
		return desContent;
	}

	public void setDesContent(String desContent) {
		this.desContent = desContent;
	}

	public String getReportAddress() {
		return reportAddress;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
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

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}


}
