package com.huatek.busi.dto.quality;

import java.util.Date;

public class BusiQualityWaterStableMixingStationExceedDto {
	private Long id;

	private String resultDate;// 结果时间

	private String aggregate1;// 骨料1

	private String aggregate2;// 骨料2

	private String aggregate3;// 骨料3

	private String aggregate4;// 骨料4

	private String aggregate5;// 骨料5

	private String powder1;// 粉料1

	private String powder2;// 粉料2

	private String waterStabilityInfoCode;// 水稳信息编号

	private String type;// 类型

	private String ukey;// 唯一码

	private String overproofGrade;// 超标等级 1:一级,2：二级, 3:三级

	private String overproofReason;// 超标原因

	private String disposeState;// 处理状态 0:未处理,1:已处理

	private String disposeUser;// 处理人名称

	private String disposeDate;// 处理时间

	private String disposeImage;// 处理图片

	private String disposeContent;// 处理内容

	private String disposeResult;// 处理结果

	private String examineUser;// 审核人

	private String examineDate;// 审核时间

	private String examineImage;// 审核图片

	private String examineContent;// 审核内容

	private String examineResult;// 审核结果

	private String approvalUser;// 审批人

	private String approvalDate;// 审批时间

	private String approvalImage;// 审批图片

	private String approvalContent;// 审批内容

	private String approvalResult;// 审批结果

	private String remarks;// 备注

	private String reportAddress;// 报告地址

	private String orgId;// 被检测样品所属施工标段ID

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传，2已传给质监局并做修改

	private String appKey;// 接口传数据标识符

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAggregate1() {
		return aggregate1;
	}

	public void setAggregate1(String aggregate1) {
		this.aggregate1 = aggregate1;
	}

	public String getAggregate2() {
		return aggregate2;
	}

	public void setAggregate2(String aggregate2) {
		this.aggregate2 = aggregate2;
	}

	public String getAggregate3() {
		return aggregate3;
	}

	public void setAggregate3(String aggregate3) {
		this.aggregate3 = aggregate3;
	}

	public String getAggregate4() {
		return aggregate4;
	}

	public void setAggregate4(String aggregate4) {
		this.aggregate4 = aggregate4;
	}

	public String getAggregate5() {
		return aggregate5;
	}

	public void setAggregate5(String aggregate5) {
		this.aggregate5 = aggregate5;
	}

	public String getPowder1() {
		return powder1;
	}

	public void setPowder1(String powder1) {
		this.powder1 = powder1;
	}

	public String getPowder2() {
		return powder2;
	}

	public void setPowder2(String powder2) {
		this.powder2 = powder2;
	}

	public String getWaterStabilityInfoCode() {
		return waterStabilityInfoCode;
	}

	public void setWaterStabilityInfoCode(String waterStabilityInfoCode) {
		this.waterStabilityInfoCode = waterStabilityInfoCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getResultDate() {
		return resultDate;
	}

	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}

	public String getDisposeDate() {
		return disposeDate;
	}

	public void setDisposeDate(String disposeDate) {
		this.disposeDate = disposeDate;
	}

	public String getOverproofGrade() {
		return overproofGrade;
	}

	public void setOverproofGrade(String overproofGrade) {
		this.overproofGrade = overproofGrade;
	}

	public String getOverproofReason() {
		return overproofReason;
	}

	public void setOverproofReason(String overproofReason) {
		this.overproofReason = overproofReason;
	}

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getDisposeUser() {
		return disposeUser;
	}

	public void setDisposeUser(String disposeUser) {
		this.disposeUser = disposeUser;
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

	public String getExamineUser() {
		return examineUser;
	}

	public void setExamineUser(String examineUser) {
		this.examineUser = examineUser;
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

	public String getApprovalUser() {
		return approvalUser;
	}

	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
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

	public String getReportAddress() {
		return reportAddress;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getExamineDate() {
		return examineDate;
	}

	public void setExamineDate(String examineDate) {
		this.examineDate = examineDate;
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
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
