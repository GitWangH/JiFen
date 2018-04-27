package com.huatek.busi.dto.quality;

import java.util.Date;

public class BusiQualityUniversalMachineDto {

	private Long id;

	private String testPersion;// 试验人

	private String sampleNumber;// 样品编号

	private String testTime;// 试验时间

	private String testName;// 试验名称

	private String testType;// 试验类型

	private String basicModulusOfElasticity;// 基本弹性模量

	private String baseNonProportionalExtensionForce;// 基本规定非比例延伸力

	private String baseNonProportionalExtension;// 基本规定非比例延伸率

	private String basicProvisionsTotalElongation;// 基本规定总延伸力

	private String basicProvisionsTotalElongationRate;// 基本规定总延伸率

	private String yieldPointElongation;// 屈服点延伸

	private String upperYieldForce;// 上屈服力

	private String lowerYieldForce;// 下屈服力

	private String extensometerGaugeDistance;// 引伸计标距

	private String originalGauge;// 原始标距

	private String originalCrossSectionalArea;// 原始横截面积

	private String maximumForce;// 最大力

	private String maximumForceFmNonProportionalElongation;// 最大力(Fm)非比例伸长

	private String maximumForceFmTotalElongationDeltaLm;// 最大力(Fm)总延伸(ΔLm)

	private String elasticModulus;// 弹性模量(E)

	private String brokenTheGauge;// 断后标距(Lu)

	private String brokenElongation;// 断后伸长率(A)

	private String brokenDiameter;// 断后直径(du)

	private String brokenMinimumCrossSectionalArea;// 断后最小横截面积(Su)

	private String sectionShrinkage;// 断面收缩率(Z)

	private String prescribedNonProportionalExtensionForce;// 规定非比例延伸力

	private String prescribedNonProportionalExtensionStrength;// 规定非比例延伸强度(Rp)

	private String specifiedTotalElongation;// 规定总延伸力

	private String specifiedTotalElongationStrength;// 规定总延伸强度(Rt)

	private String tensileStrength;// 抗拉强度(Rm)

	private String yieldPointElongationAe;// 屈服点延伸率(Ae)

	private String upperYieldForce1;// 上屈服力

	private String upperYieldStrength;// 上屈服强度(ReH)

	private String lowerYieldForce1;// 下屈服力

	private String lowerYieldStrength;// 下屈服强度(ReL)

	private String maximumForceFm;// 最大力(Fm)

	private String maximumForceNonProportionalProlongationRate;// 最大力(Fm)非比例伸长率(Ag)

	private String maximumForceTotalElongationRate;// 最大力(Fm)总伸长率(Agt)

	private String maximumForceTotalElongation;// 最大力(Fm)总延伸(ΔLm)

	private String maximumForceNonProportionalElongation;// 最大力非比例伸长

	private String radius;// 半径

	private String radiusSquared;// 半径平方

	private String brokenRadius;// 断后半径

	private String brokenRadiusSquared;// 断后半径平方

	private String brokenPercentageElongation;// 断后伸长

	private String nominalDiameter;// 公称直径(d)

	private String prescribedNonProportionalElongation;// 规定非比例延伸率(εp)

	private String specifiedTotalElongation1;// 规定总延伸率(εt)

	private String extensometerGaugeDistance1;// 引伸计标距(Le)

	private String originalGaugeLo;// 原始标距(Lo)

	private String originalCrossSectionalAreaSo;// 原始横截面积(So)

	private String processTimeUpper;// 过程时间(多个用逗号隔开) ，上屈服力，以毫秒为单位

	private String processForceValueUpper;// 过程力值(多个用逗号隔开) ，上屈服力

	private String processTimeLower;// 过程时间(多个用逗号隔开) ，下屈服力，以毫秒为单位

	private String processForceValueLower;// 过程力值(多个用逗号隔开)，下屈服力

	private String thirdSetsOfData;// 第三组数据

	private String thirdSetsOfData1;// 第三组数据

	private String status;// 0:合格，1:不合格，2有效，:3:无效，4:其他

	private String typeCode;// 品种编码

	private String manufacturer;// 生产厂家

	private String ukey;// 唯一码

	private String descUrl;// 详细描述地址

	private String unqualifiedDescription;// 不合格描述

	private Long factOrgId;// 被检测样品所属施工标段ID

	private Long orgId;// 机构ID

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

	public String getTestPersion() {
		return testPersion;
	}

	public void setTestPersion(String testPersion) {
		this.testPersion = testPersion;
	}

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getBasicModulusOfElasticity() {
		return basicModulusOfElasticity;
	}

	public void setBasicModulusOfElasticity(String basicModulusOfElasticity) {
		this.basicModulusOfElasticity = basicModulusOfElasticity;
	}

	public String getBaseNonProportionalExtensionForce() {
		return baseNonProportionalExtensionForce;
	}

	public void setBaseNonProportionalExtensionForce(
			String baseNonProportionalExtensionForce) {
		this.baseNonProportionalExtensionForce = baseNonProportionalExtensionForce;
	}

	public String getBaseNonProportionalExtension() {
		return baseNonProportionalExtension;
	}

	public void setBaseNonProportionalExtension(
			String baseNonProportionalExtension) {
		this.baseNonProportionalExtension = baseNonProportionalExtension;
	}

	public String getBasicProvisionsTotalElongation() {
		return basicProvisionsTotalElongation;
	}

	public void setBasicProvisionsTotalElongation(
			String basicProvisionsTotalElongation) {
		this.basicProvisionsTotalElongation = basicProvisionsTotalElongation;
	}

	public String getBasicProvisionsTotalElongationRate() {
		return basicProvisionsTotalElongationRate;
	}

	public void setBasicProvisionsTotalElongationRate(
			String basicProvisionsTotalElongationRate) {
		this.basicProvisionsTotalElongationRate = basicProvisionsTotalElongationRate;
	}

	public String getYieldPointElongation() {
		return yieldPointElongation;
	}

	public void setYieldPointElongation(String yieldPointElongation) {
		this.yieldPointElongation = yieldPointElongation;
	}

	public String getUpperYieldForce() {
		return upperYieldForce;
	}

	public void setUpperYieldForce(String upperYieldForce) {
		this.upperYieldForce = upperYieldForce;
	}

	public String getLowerYieldForce() {
		return lowerYieldForce;
	}

	public void setLowerYieldForce(String lowerYieldForce) {
		this.lowerYieldForce = lowerYieldForce;
	}

	public String getExtensometerGaugeDistance() {
		return extensometerGaugeDistance;
	}

	public void setExtensometerGaugeDistance(String extensometerGaugeDistance) {
		this.extensometerGaugeDistance = extensometerGaugeDistance;
	}

	public String getOriginalGauge() {
		return originalGauge;
	}

	public void setOriginalGauge(String originalGauge) {
		this.originalGauge = originalGauge;
	}

	public String getOriginalCrossSectionalArea() {
		return originalCrossSectionalArea;
	}

	public void setOriginalCrossSectionalArea(String originalCrossSectionalArea) {
		this.originalCrossSectionalArea = originalCrossSectionalArea;
	}

	public String getMaximumForce() {
		return maximumForce;
	}

	public void setMaximumForce(String maximumForce) {
		this.maximumForce = maximumForce;
	}

	public String getMaximumForceFmNonProportionalElongation() {
		return maximumForceFmNonProportionalElongation;
	}

	public void setMaximumForceFmNonProportionalElongation(
			String maximumForceFmNonProportionalElongation) {
		this.maximumForceFmNonProportionalElongation = maximumForceFmNonProportionalElongation;
	}

	public String getMaximumForceFmTotalElongationDeltaLm() {
		return maximumForceFmTotalElongationDeltaLm;
	}

	public void setMaximumForceFmTotalElongationDeltaLm(
			String maximumForceFmTotalElongationDeltaLm) {
		this.maximumForceFmTotalElongationDeltaLm = maximumForceFmTotalElongationDeltaLm;
	}

	public String getElasticModulus() {
		return elasticModulus;
	}

	public void setElasticModulus(String elasticModulus) {
		this.elasticModulus = elasticModulus;
	}

	public String getBrokenTheGauge() {
		return brokenTheGauge;
	}

	public void setBrokenTheGauge(String brokenTheGauge) {
		this.brokenTheGauge = brokenTheGauge;
	}

	public String getBrokenElongation() {
		return brokenElongation;
	}

	public void setBrokenElongation(String brokenElongation) {
		this.brokenElongation = brokenElongation;
	}

	public String getBrokenDiameter() {
		return brokenDiameter;
	}

	public void setBrokenDiameter(String brokenDiameter) {
		this.brokenDiameter = brokenDiameter;
	}

	public String getBrokenMinimumCrossSectionalArea() {
		return brokenMinimumCrossSectionalArea;
	}

	public void setBrokenMinimumCrossSectionalArea(
			String brokenMinimumCrossSectionalArea) {
		this.brokenMinimumCrossSectionalArea = brokenMinimumCrossSectionalArea;
	}

	public String getSectionShrinkage() {
		return sectionShrinkage;
	}

	public void setSectionShrinkage(String sectionShrinkage) {
		this.sectionShrinkage = sectionShrinkage;
	}

	public String getPrescribedNonProportionalExtensionForce() {
		return prescribedNonProportionalExtensionForce;
	}

	public void setPrescribedNonProportionalExtensionForce(
			String prescribedNonProportionalExtensionForce) {
		this.prescribedNonProportionalExtensionForce = prescribedNonProportionalExtensionForce;
	}

	public String getPrescribedNonProportionalExtensionStrength() {
		return prescribedNonProportionalExtensionStrength;
	}

	public void setPrescribedNonProportionalExtensionStrength(
			String prescribedNonProportionalExtensionStrength) {
		this.prescribedNonProportionalExtensionStrength = prescribedNonProportionalExtensionStrength;
	}

	public String getSpecifiedTotalElongation() {
		return specifiedTotalElongation;
	}

	public void setSpecifiedTotalElongation(String specifiedTotalElongation) {
		this.specifiedTotalElongation = specifiedTotalElongation;
	}

	public String getSpecifiedTotalElongationStrength() {
		return specifiedTotalElongationStrength;
	}

	public void setSpecifiedTotalElongationStrength(
			String specifiedTotalElongationStrength) {
		this.specifiedTotalElongationStrength = specifiedTotalElongationStrength;
	}

	public String getTensileStrength() {
		return tensileStrength;
	}

	public void setTensileStrength(String tensileStrength) {
		this.tensileStrength = tensileStrength;
	}

	public String getYieldPointElongationAe() {
		return yieldPointElongationAe;
	}

	public void setYieldPointElongationAe(String yieldPointElongationAe) {
		this.yieldPointElongationAe = yieldPointElongationAe;
	}

	public String getUpperYieldForce1() {
		return upperYieldForce1;
	}

	public void setUpperYieldForce1(String upperYieldForce1) {
		this.upperYieldForce1 = upperYieldForce1;
	}

	public String getUpperYieldStrength() {
		return upperYieldStrength;
	}

	public void setUpperYieldStrength(String upperYieldStrength) {
		this.upperYieldStrength = upperYieldStrength;
	}

	public String getLowerYieldForce1() {
		return lowerYieldForce1;
	}

	public void setLowerYieldForce1(String lowerYieldForce1) {
		this.lowerYieldForce1 = lowerYieldForce1;
	}

	public String getLowerYieldStrength() {
		return lowerYieldStrength;
	}

	public void setLowerYieldStrength(String lowerYieldStrength) {
		this.lowerYieldStrength = lowerYieldStrength;
	}

	public String getMaximumForceFm() {
		return maximumForceFm;
	}

	public void setMaximumForceFm(String maximumForceFm) {
		this.maximumForceFm = maximumForceFm;
	}

	public String getMaximumForceNonProportionalProlongationRate() {
		return maximumForceNonProportionalProlongationRate;
	}

	public void setMaximumForceNonProportionalProlongationRate(
			String maximumForceNonProportionalProlongationRate) {
		this.maximumForceNonProportionalProlongationRate = maximumForceNonProportionalProlongationRate;
	}

	public String getMaximumForceTotalElongationRate() {
		return maximumForceTotalElongationRate;
	}

	public void setMaximumForceTotalElongationRate(
			String maximumForceTotalElongationRate) {
		this.maximumForceTotalElongationRate = maximumForceTotalElongationRate;
	}

	public String getMaximumForceTotalElongation() {
		return maximumForceTotalElongation;
	}

	public void setMaximumForceTotalElongation(
			String maximumForceTotalElongation) {
		this.maximumForceTotalElongation = maximumForceTotalElongation;
	}

	public String getMaximumForceNonProportionalElongation() {
		return maximumForceNonProportionalElongation;
	}

	public void setMaximumForceNonProportionalElongation(
			String maximumForceNonProportionalElongation) {
		this.maximumForceNonProportionalElongation = maximumForceNonProportionalElongation;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getRadiusSquared() {
		return radiusSquared;
	}

	public void setRadiusSquared(String radiusSquared) {
		this.radiusSquared = radiusSquared;
	}

	public String getBrokenRadius() {
		return brokenRadius;
	}

	public void setBrokenRadius(String brokenRadius) {
		this.brokenRadius = brokenRadius;
	}

	public String getBrokenRadiusSquared() {
		return brokenRadiusSquared;
	}

	public void setBrokenRadiusSquared(String brokenRadiusSquared) {
		this.brokenRadiusSquared = brokenRadiusSquared;
	}

	public String getBrokenPercentageElongation() {
		return brokenPercentageElongation;
	}

	public void setBrokenPercentageElongation(String brokenPercentageElongation) {
		this.brokenPercentageElongation = brokenPercentageElongation;
	}

	public String getNominalDiameter() {
		return nominalDiameter;
	}

	public void setNominalDiameter(String nominalDiameter) {
		this.nominalDiameter = nominalDiameter;
	}

	public String getPrescribedNonProportionalElongation() {
		return prescribedNonProportionalElongation;
	}

	public void setPrescribedNonProportionalElongation(
			String prescribedNonProportionalElongation) {
		this.prescribedNonProportionalElongation = prescribedNonProportionalElongation;
	}

	public String getSpecifiedTotalElongation1() {
		return specifiedTotalElongation1;
	}

	public void setSpecifiedTotalElongation1(String specifiedTotalElongation1) {
		this.specifiedTotalElongation1 = specifiedTotalElongation1;
	}

	public String getExtensometerGaugeDistance1() {
		return extensometerGaugeDistance1;
	}

	public void setExtensometerGaugeDistance1(String extensometerGaugeDistance1) {
		this.extensometerGaugeDistance1 = extensometerGaugeDistance1;
	}

	public String getOriginalGaugeLo() {
		return originalGaugeLo;
	}

	public void setOriginalGaugeLo(String originalGaugeLo) {
		this.originalGaugeLo = originalGaugeLo;
	}

	public String getOriginalCrossSectionalAreaSo() {
		return originalCrossSectionalAreaSo;
	}

	public void setOriginalCrossSectionalAreaSo(
			String originalCrossSectionalAreaSo) {
		this.originalCrossSectionalAreaSo = originalCrossSectionalAreaSo;
	}

	public String getProcessTimeUpper() {
		return processTimeUpper;
	}

	public void setProcessTimeUpper(String processTimeUpper) {
		this.processTimeUpper = processTimeUpper;
	}

	public String getProcessForceValueUpper() {
		return processForceValueUpper;
	}

	public void setProcessForceValueUpper(String processForceValueUpper) {
		this.processForceValueUpper = processForceValueUpper;
	}

	public String getProcessTimeLower() {
		return processTimeLower;
	}

	public void setProcessTimeLower(String processTimeLower) {
		this.processTimeLower = processTimeLower;
	}

	public String getProcessForceValueLower() {
		return processForceValueLower;
	}

	public void setProcessForceValueLower(String processForceValueLower) {
		this.processForceValueLower = processForceValueLower;
	}

	public String getThirdSetsOfData() {
		return thirdSetsOfData;
	}

	public void setThirdSetsOfData(String thirdSetsOfData) {
		this.thirdSetsOfData = thirdSetsOfData;
	}

	public String getThirdSetsOfData1() {
		return thirdSetsOfData1;
	}

	public void setThirdSetsOfData1(String thirdSetsOfData1) {
		this.thirdSetsOfData1 = thirdSetsOfData1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public String getUnqualifiedDescription() {
		return unqualifiedDescription;
	}

	public void setUnqualifiedDescription(String unqualifiedDescription) {
		this.unqualifiedDescription = unqualifiedDescription;
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

}
