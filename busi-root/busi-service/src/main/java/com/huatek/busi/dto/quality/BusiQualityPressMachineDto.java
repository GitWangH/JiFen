package com.huatek.busi.dto.quality;

import java.util.Date;

public class BusiQualityPressMachineDto {
	private Long id;

	private String sampleNumber;// 样品编号

	private String entrustedCompany;// 委托单位

	private String commissionDate;// 委托日期

	private String experimentalDate;// 实验日期(对方提供说明：试验类型编号，错误说明)

	private String engineeringName;// 工程名称

	private String constructionSite;// 施工部位

	private String sampleSource;// 样品来源

	private String sampleName;// 样品来源

	private String approver;// 批准人

	private String verifier;// 核验人

	private String tester;// 检定人

	private String testType;// 试验类型

	private String testType1;// 试验类型

	private String sampleType;// 样品类型

	private String pressureArea;// 承压面积

	private String maintenanceMethod;// 养护方式

	private String age;// 龄期

	private String load1;// 荷载1

	private String load2;// 荷载2

	private String load3;// 荷载3

	private String load4;// 荷载4

	private String load5;// 荷载5

	private String load6;// 荷载6

	private String strengthRepresentativeValue;// 强度代表值

	private String designStrength;// 设计强度

	private String processTimeLoad1;// 过程时间(多个用逗号隔开) ，以毫秒为单位，荷载1的

	private String processForceValueload1;// 过程力值(多个用逗号隔开) ，荷载1的

	private String processTimeLoad2;// 过程时间(多个用逗号隔开) ，以毫秒为单位，，荷载2的

	private String processForceValueload2;// 过程力值(多个用逗号隔开)，荷载2的

	private String processTimeLoad3;// 过程时间(多个用逗号隔开) ，以毫秒为单位，，荷载3的

	private String processForceValueload3;// 过程力值(多个用逗号隔开)，荷载3的

	private String status;// 0:合格，1:不合格，2有效，:3:无效，4:其他

	private String ukey;// 唯一码

	private String descUrl;// 详细描述地址

	private String description;// 不合格描述

	private Long factOrgId;// 被检测样品所属施工标段ID

	private Long orgId;// 机构ID

	private String orgName;// 机构名称

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传，2已传给质监局并做修改

	private String appKey;// 接口传数据标识符

	private String inspectionCode;// 整改（快捷处理）编号

	private String disposeState;// 处理状态（数据字典）

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getEntrustedCompany() {
		return entrustedCompany;
	}

	public void setEntrustedCompany(String entrustedCompany) {
		this.entrustedCompany = entrustedCompany;
	}

	public String getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getExperimentalDate() {
		return experimentalDate;
	}

	public void setExperimentalDate(String experimentalDate) {
		this.experimentalDate = experimentalDate;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getConstructionSite() {
		return constructionSite;
	}

	public void setConstructionSite(String constructionSite) {
		this.constructionSite = constructionSite;
	}

	public String getSampleSource() {
		return sampleSource;
	}

	public void setSampleSource(String sampleSource) {
		this.sampleSource = sampleSource;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestType1() {
		return testType1;
	}

	public void setTestType1(String testType1) {
		this.testType1 = testType1;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getPressureArea() {
		return pressureArea;
	}

	public void setPressureArea(String pressureArea) {
		this.pressureArea = pressureArea;
	}

	public String getMaintenanceMethod() {
		return maintenanceMethod;
	}

	public void setMaintenanceMethod(String maintenanceMethod) {
		this.maintenanceMethod = maintenanceMethod;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLoad1() {
		return load1;
	}

	public void setLoad1(String load1) {
		this.load1 = load1;
	}

	public String getLoad2() {
		return load2;
	}

	public void setLoad2(String load2) {
		this.load2 = load2;
	}

	public String getLoad3() {
		return load3;
	}

	public void setLoad3(String load3) {
		this.load3 = load3;
	}

	public String getLoad4() {
		return load4;
	}

	public void setLoad4(String load4) {
		this.load4 = load4;
	}

	public String getLoad5() {
		return load5;
	}

	public void setLoad5(String load5) {
		this.load5 = load5;
	}

	public String getLoad6() {
		return load6;
	}

	public void setLoad6(String load6) {
		this.load6 = load6;
	}

	public String getStrengthRepresentativeValue() {
		return strengthRepresentativeValue;
	}

	public void setStrengthRepresentativeValue(
			String strengthRepresentativeValue) {
		this.strengthRepresentativeValue = strengthRepresentativeValue;
	}

	public String getDesignStrength() {
		return designStrength;
	}

	public void setDesignStrength(String designStrength) {
		this.designStrength = designStrength;
	}

	public String getProcessTimeLoad1() {
		return processTimeLoad1;
	}

	public void setProcessTimeLoad1(String processTimeLoad1) {
		this.processTimeLoad1 = processTimeLoad1;
	}

	public String getProcessForceValueload1() {
		return processForceValueload1;
	}

	public void setProcessForceValueload1(String processForceValueload1) {
		this.processForceValueload1 = processForceValueload1;
	}

	public String getProcessTimeLoad2() {
		return processTimeLoad2;
	}

	public void setProcessTimeLoad2(String processTimeLoad2) {
		this.processTimeLoad2 = processTimeLoad2;
	}

	public String getProcessForceValueload2() {
		return processForceValueload2;
	}

	public void setProcessForceValueload2(String processForceValueload2) {
		this.processForceValueload2 = processForceValueload2;
	}

	public String getProcessTimeLoad3() {
		return processTimeLoad3;
	}

	public void setProcessTimeLoad3(String processTimeLoad3) {
		this.processTimeLoad3 = processTimeLoad3;
	}

	public String getProcessForceValueload3() {
		return processForceValueload3;
	}

	public void setProcessForceValueload3(String processForceValueload3) {
		this.processForceValueload3 = processForceValueload3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getDescUrl() {
		return descUrl;
	}

	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getFactOrgId() {
		return factOrgId;
	}

	public void setFactOrgId(Long factOrgId) {
		this.factOrgId = factOrgId;
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

	public Integer getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
