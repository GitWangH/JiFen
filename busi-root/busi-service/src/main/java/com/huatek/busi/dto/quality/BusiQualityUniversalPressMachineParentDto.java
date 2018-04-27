package com.huatek.busi.dto.quality;

import javax.persistence.Column;

public class BusiQualityUniversalPressMachineParentDto {

	private BusiQualityPressMachineDto busiQualityPressMachine;// 压力机id

	private BusiQualityUniversalMachineDto busiQualityUniversalMachine;// 万能机id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long id;// 压力机、万能机id

	private String orgId;

	private String orgName;// 标段名称

	private Long tenantId;// 租户id

	private String createTime;// 创建时间

	private String disposeState;// 处理状态（数据字典）

	private String inspectionCode;// 整改编号

	private String sampleNumber;// 样品编号

	private String sampleName;// 样品名称

	private String testType;// 试验类型（0万能机，1压力机）

	private String testPersion;// 试验人

	private String testTime;// 试验时间

	private String descUrl;// 详细描述地址

	private String unqualifiedDescription;// 描述

	private String modifyTime;// 当前处理时间（回调维护）

	private String status;// 检测状态 0:合格，1:不合格，2有效，:3:无效，4:其他

	private String originalType;// 接口来源类型

	private Long inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0未删除， 1已删除
	private String rectificationUrgency;// 紧急程度
	private String checkNo;// 检查编号

	private Long factOrgId;// 被检测样品所属施工标段ID

	private String factOrgName;// 被检测样品所属施工标段名称

	public Long getFactOrgId() {
		return factOrgId;
	}

	public void setFactOrgId(Long factOrgId) {
		this.factOrgId = factOrgId;
	}

	public String getFactOrgName() {
		return factOrgName;
	}

	public void setFactOrgName(String factOrgName) {
		this.factOrgName = factOrgName;
	}

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}

	public BusiQualityPressMachineDto getBusiQualityPressMachine() {
		return busiQualityPressMachine;
	}

	public void setBusiQualityPressMachine(
			BusiQualityPressMachineDto busiQualityPressMachine) {
		this.busiQualityPressMachine = busiQualityPressMachine;
	}

	public BusiQualityUniversalMachineDto getBusiQualityUniversalMachine() {
		return busiQualityUniversalMachine;
	}

	public void setBusiQualityUniversalMachine(
			BusiQualityUniversalMachineDto busiQualityUniversalMachine) {
		this.busiQualityUniversalMachine = busiQualityUniversalMachine;
	}

	public Integer getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}

	public void setOriginalType(String originalType) {
		this.originalType = originalType;
	}

	/** @Fields modifer : 修改人ID */
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	private String modifierName;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestPersion() {
		return testPersion;
	}

	public void setTestPersion(String testPersion) {
		this.testPersion = testPersion;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getDescUrl() {
		return descUrl;
	}

	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}

	public String getUnqualifiedDescription() {
		return unqualifiedDescription;
	}

	public void setUnqualifiedDescription(String unqualifiedDescription) {
		this.unqualifiedDescription = unqualifiedDescription;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getOriginalType() {
		return originalType = "test_inspection";// 整改单专用（勿改）
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getRectificationUrgency() {
		return rectificationUrgency;
	}

	public void setRectificationUrgency(String rectificationUrgency) {
		this.rectificationUrgency = rectificationUrgency;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

}
