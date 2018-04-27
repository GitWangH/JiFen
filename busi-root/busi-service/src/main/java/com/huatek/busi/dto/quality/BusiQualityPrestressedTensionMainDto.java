package com.huatek.busi.dto.quality;

public class BusiQualityPrestressedTensionMainDto {
	private Long id;

	private String engineeringName;// 工程名称

	private String orgId;// 标段id-接口传的是机构ID

	private String orgName;// 机构名称

	private String bridgeName;// 桥梁名称

	private String beamNumber;// 梁号

	private String channelNumber;// 孔道号

	private String startTime;// 开始时间

	private String recordTime;// 记录时间

	private String tensionAmount;// 张拉次数

	private String gradedTension;// 分级张拉0：表示不进 行分级张拉1：表示进行分级张拉

	private String theoryElongation;// 理论伸长量

	private String actualElongation;// 实际伸长量

	private String deviationElongation;// 误差伸长量

	private String deviationElongationRate;// 误差率

	private String elongationState;// 伸长量状态是否合格(0:不合格；1:合格)

	private String theoryTension;// 理论张拉力

	private String actualTension;// 实际张拉力

	private String deviationTension;// 张拉力误差

	private String deviationTensionRate;// 超长率

	private String tensionState;// 张拉力状态是否合格(0:不合格；1:合格)

	private String theoryStress;// 理论应力

	private String actualStress;// 实际应力

	private String deviationStress;// 应力误差

	private String deviationStressRate;// 超长率

	private String stressState;// 应力状态是否合格(0:不合格；1:合格)

	private String jackNo1;// 1号千斤顶编号

	private String hydraulicPumpNo1;// 1号油压泵编号

	private String oilPressureGaugeNo1;// 1号油压表编号

	private String jackNo2;// 2号千斤顶编号

	private String hydraulicPumpNo2;// 2号油压泵编号

	private String oilPressureGaugeNo2;// 2号油压表编号

	private String coefficientA1;// 1号系数A

	private String coefficientB1;// 1号系数B

	private String coefficientA2;// 2号系数A

	private String coefficientB2;// 2号系数B

	private String steelStrandLength;// 钢绞线长度

	private String overstepTensionPercentage;// 超张拉力百分比0~5,0表示无超张拉

	private String tensionType;// 构件名称(张拉方式)

	private String steelBeamNo;// 钢束编号1

	private String tensionOrder;// 张拉顺序1 无符号字符型：~99.

	private String concreteDesignStrength;// 砼设计强度2 无符号整形，定点两位小数XX.xx Mpa

	private String concreteStrength;// 砼强度2 无符号整形，定点两位小数XX.xx Mpa

	private String dataPackageCount;// 数据包数目测试记录的数据包数目~

	private String elongationCorrectCoefficient1;// 1号伸长量修正系数

	private String elongationCorrectCoefficient2;// 2号伸长量修正系数

	private String shrinkage1;// 1号回缩量

	private String shrinkage2;// 2号回缩量

	private Integer recordCount;// 记录的点数

	private Integer tensionTotalCount;// 张拉结果次数

	private String elongation1;// 1号伸长量

	private String elongation2;// 2号伸长量

	private String oilPressureGaugePressure1;// 1号油表压力

	private String oilPressureGaugePressure2;// 2号油表压力

	private String topTension1;// 1号顶张拉力

	private String topTension2;// 2号顶张拉力

	private String holdingTime;// 持荷时间

	private String smsStatusCode;// 短信状态码

	private String gatherTime;// 采集时间

	private String remarks;// 备注

	private String backupField1;// 备用字段1

	private String backupField2;// 备用字段2

	private String backupField3;// 备用字段3

	private String backupField4;// 备用字段4

	private String backupField5;// 备用字段5

	private String backupField6;// 备用字段6

	private String backupField7;// 备用字段7

	private String backupField8;// 备用字段8

	private String reportAddress;// 报告地址

	private String unqualifiedDescription;// 不合格描述

	private String ukey;// 唯一码

	private String disposeTime;// 处理时间

	private String disposeStatus;// 处理状态

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private Long originalId;// 原始id

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	private String inspectionCode;// 整改单、快捷处理编号

	private Integer isQualified;// 0:合格，1:不合格，2有效，:3:无效，4:其他

	/** @Fields modifer : 修改人ID */
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	private String modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getBridgeName() {
		return bridgeName;
	}

	public void setBridgeName(String bridgeName) {
		this.bridgeName = bridgeName;
	}

	public String getBeamNumber() {
		return beamNumber;
	}

	public void setBeamNumber(String beamNumber) {
		this.beamNumber = beamNumber;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getGradedTension() {
		return gradedTension;
	}

	public void setGradedTension(String gradedTension) {
		this.gradedTension = gradedTension;
	}

	public String getElongationState() {
		return elongationState;
	}

	public void setElongationState(String elongationState) {
		this.elongationState = elongationState;
	}

	public String getTheoryTension() {
		return theoryTension;
	}

	public void setTheoryTension(String theoryTension) {
		this.theoryTension = theoryTension;
	}

	public String getActualTension() {
		return actualTension;
	}

	public String getTensionAmount() {
		return tensionAmount;
	}

	public void setTensionAmount(String tensionAmount) {
		this.tensionAmount = tensionAmount;
	}

	public String getTheoryElongation() {
		return theoryElongation;
	}

	public void setTheoryElongation(String theoryElongation) {
		this.theoryElongation = theoryElongation;
	}

	public String getActualElongation() {
		return actualElongation;
	}

	public void setActualElongation(String actualElongation) {
		this.actualElongation = actualElongation;
	}

	public String getDeviationElongation() {
		return deviationElongation;
	}

	public void setDeviationElongation(String deviationElongation) {
		this.deviationElongation = deviationElongation;
	}

	public String getDeviationElongationRate() {
		return deviationElongationRate;
	}

	public void setDeviationElongationRate(String deviationElongationRate) {
		this.deviationElongationRate = deviationElongationRate;
	}

	public void setActualTension(String actualTension) {
		this.actualTension = actualTension;
	}

	public String getDeviationTension() {
		return deviationTension;
	}

	public void setDeviationTension(String deviationTension) {
		this.deviationTension = deviationTension;
	}

	public String getDeviationTensionRate() {
		return deviationTensionRate;
	}

	public void setDeviationTensionRate(String deviationTensionRate) {
		this.deviationTensionRate = deviationTensionRate;
	}

	public String getTensionState() {
		return tensionState;
	}

	public void setTensionState(String tensionState) {
		this.tensionState = tensionState;
	}

	public String getTheoryStress() {
		return theoryStress;
	}

	public void setTheoryStress(String theoryStress) {
		this.theoryStress = theoryStress;
	}

	public String getActualStress() {
		return actualStress;
	}

	public void setActualStress(String actualStress) {
		this.actualStress = actualStress;
	}

	public String getDeviationStress() {
		return deviationStress;
	}

	public void setDeviationStress(String deviationStress) {
		this.deviationStress = deviationStress;
	}

	public String getDeviationStressRate() {
		return deviationStressRate;
	}

	public void setDeviationStressRate(String deviationStressRate) {
		this.deviationStressRate = deviationStressRate;
	}

	public String getStressState() {
		return stressState;
	}

	public void setStressState(String stressState) {
		this.stressState = stressState;
	}

	public String getJackNo1() {
		return jackNo1;
	}

	public void setJackNo1(String jackNo1) {
		this.jackNo1 = jackNo1;
	}

	public String getHydraulicPumpNo1() {
		return hydraulicPumpNo1;
	}

	public void setHydraulicPumpNo1(String hydraulicPumpNo1) {
		this.hydraulicPumpNo1 = hydraulicPumpNo1;
	}

	public String getOilPressureGaugeNo1() {
		return oilPressureGaugeNo1;
	}

	public void setOilPressureGaugeNo1(String oilPressureGaugeNo1) {
		this.oilPressureGaugeNo1 = oilPressureGaugeNo1;
	}

	public String getJackNo2() {
		return jackNo2;
	}

	public void setJackNo2(String jackNo2) {
		this.jackNo2 = jackNo2;
	}

	public String getHydraulicPumpNo2() {
		return hydraulicPumpNo2;
	}

	public void setHydraulicPumpNo2(String hydraulicPumpNo2) {
		this.hydraulicPumpNo2 = hydraulicPumpNo2;
	}

	public String getOilPressureGaugeNo2() {
		return oilPressureGaugeNo2;
	}

	public void setOilPressureGaugeNo2(String oilPressureGaugeNo2) {
		this.oilPressureGaugeNo2 = oilPressureGaugeNo2;
	}

	public String getCoefficientA1() {
		return coefficientA1;
	}

	public void setCoefficientA1(String coefficientA1) {
		this.coefficientA1 = coefficientA1;
	}

	public String getCoefficientB1() {
		return coefficientB1;
	}

	public void setCoefficientB1(String coefficientB1) {
		this.coefficientB1 = coefficientB1;
	}

	public String getCoefficientA2() {
		return coefficientA2;
	}

	public void setCoefficientA2(String coefficientA2) {
		this.coefficientA2 = coefficientA2;
	}

	public String getCoefficientB2() {
		return coefficientB2;
	}

	public void setCoefficientB2(String coefficientB2) {
		this.coefficientB2 = coefficientB2;
	}

	public String getSteelStrandLength() {
		return steelStrandLength;
	}

	public void setSteelStrandLength(String steelStrandLength) {
		this.steelStrandLength = steelStrandLength;
	}

	public String getOverstepTensionPercentage() {
		return overstepTensionPercentage;
	}

	public void setOverstepTensionPercentage(String overstepTensionPercentage) {
		this.overstepTensionPercentage = overstepTensionPercentage;
	}

	public String getTensionType() {
		return tensionType;
	}

	public void setTensionType(String tensionType) {
		this.tensionType = tensionType;
	}

	public String getSteelBeamNo() {
		return steelBeamNo;
	}

	public void setSteelBeamNo(String steelBeamNo) {
		this.steelBeamNo = steelBeamNo;
	}

	public String getTensionOrder() {
		return tensionOrder;
	}

	public void setTensionOrder(String tensionOrder) {
		this.tensionOrder = tensionOrder;
	}

	public String getConcreteDesignStrength() {
		return concreteDesignStrength;
	}

	public void setConcreteDesignStrength(String concreteDesignStrength) {
		this.concreteDesignStrength = concreteDesignStrength;
	}

	public String getConcreteStrength() {
		return concreteStrength;
	}

	public void setConcreteStrength(String concreteStrength) {
		this.concreteStrength = concreteStrength;
	}

	public String getDataPackageCount() {
		return dataPackageCount;
	}

	public void setDataPackageCount(String dataPackageCount) {
		this.dataPackageCount = dataPackageCount;
	}

	public String getElongationCorrectCoefficient1() {
		return elongationCorrectCoefficient1;
	}

	public void setElongationCorrectCoefficient1(
			String elongationCorrectCoefficient1) {
		this.elongationCorrectCoefficient1 = elongationCorrectCoefficient1;
	}

	public String getElongationCorrectCoefficient2() {
		return elongationCorrectCoefficient2;
	}

	public void setElongationCorrectCoefficient2(
			String elongationCorrectCoefficient2) {
		this.elongationCorrectCoefficient2 = elongationCorrectCoefficient2;
	}

	public String getShrinkage1() {
		return shrinkage1;
	}

	public void setShrinkage1(String shrinkage1) {
		this.shrinkage1 = shrinkage1;
	}

	public String getShrinkage2() {
		return shrinkage2;
	}

	public void setShrinkage2(String shrinkage2) {
		this.shrinkage2 = shrinkage2;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getTensionTotalCount() {
		return tensionTotalCount;
	}

	public void setTensionTotalCount(Integer tensionTotalCount) {
		this.tensionTotalCount = tensionTotalCount;
	}

	public String getElongation1() {
		return elongation1;
	}

	public void setElongation1(String elongation1) {
		this.elongation1 = elongation1;
	}

	public String getElongation2() {
		return elongation2;
	}

	public void setElongation2(String elongation2) {
		this.elongation2 = elongation2;
	}

	public String getOilPressureGaugePressure1() {
		return oilPressureGaugePressure1;
	}

	public void setOilPressureGaugePressure1(String oilPressureGaugePressure1) {
		this.oilPressureGaugePressure1 = oilPressureGaugePressure1;
	}

	public String getOilPressureGaugePressure2() {
		return oilPressureGaugePressure2;
	}

	public void setOilPressureGaugePressure2(String oilPressureGaugePressure2) {
		this.oilPressureGaugePressure2 = oilPressureGaugePressure2;
	}

	public String getTopTension1() {
		return topTension1;
	}

	public void setTopTension1(String topTension1) {
		this.topTension1 = topTension1;
	}

	public String getTopTension2() {
		return topTension2;
	}

	public void setTopTension2(String topTension2) {
		this.topTension2 = topTension2;
	}

	public String getHoldingTime() {
		return holdingTime;
	}

	public void setHoldingTime(String holdingTime) {
		this.holdingTime = holdingTime;
	}

	public String getSmsStatusCode() {
		return smsStatusCode;
	}

	public void setSmsStatusCode(String smsStatusCode) {
		this.smsStatusCode = smsStatusCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBackupField1() {
		return backupField1;
	}

	public void setBackupField1(String backupField1) {
		this.backupField1 = backupField1;
	}

	public String getBackupField2() {
		return backupField2;
	}

	public void setBackupField2(String backupField2) {
		this.backupField2 = backupField2;
	}

	public String getBackupField3() {
		return backupField3;
	}

	public void setBackupField3(String backupField3) {
		this.backupField3 = backupField3;
	}

	public String getBackupField4() {
		return backupField4;
	}

	public void setBackupField4(String backupField4) {
		this.backupField4 = backupField4;
	}

	public String getBackupField5() {
		return backupField5;
	}

	public void setBackupField5(String backupField5) {
		this.backupField5 = backupField5;
	}

	public String getBackupField6() {
		return backupField6;
	}

	public void setBackupField6(String backupField6) {
		this.backupField6 = backupField6;
	}

	public String getBackupField7() {
		return backupField7;
	}

	public void setBackupField7(String backupField7) {
		this.backupField7 = backupField7;
	}

	public String getBackupField8() {
		return backupField8;
	}

	public void setBackupField8(String backupField8) {
		this.backupField8 = backupField8;
	}

	public String getReportAddress() {
		return reportAddress;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getUnqualifiedDescription() {
		return unqualifiedDescription;
	}

	public void setUnqualifiedDescription(String unqualifiedDescription) {
		this.unqualifiedDescription = unqualifiedDescription;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getDisposeStatus() {
		return disposeStatus;
	}

	public void setDisposeStatus(String disposeStatus) {
		this.disposeStatus = disposeStatus;
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

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getGatherTime() {
		return gatherTime;
	}

	public void setGatherTime(String gatherTime) {
		this.gatherTime = gatherTime;
	}

	public String getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
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

	public Integer getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Integer isQualified) {
		this.isQualified = isQualified;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	
}
