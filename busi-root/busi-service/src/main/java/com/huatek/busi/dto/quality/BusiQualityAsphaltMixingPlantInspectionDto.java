package com.huatek.busi.dto.quality;


public class BusiQualityAsphaltMixingPlantInspectionDto {
	private Long id;

	private BusiQualityAsphaltMixingPlantExceedDto busiQualityAsphaltMixingPlantExceed;

	private Long originalId;// 原始id

	private String mixingDate;// 搅拌时间

	private String user;// 用户

	private String formula;// 配方

	private String formulaName;// 配方名称

	private String asphaltTemperature;// 沥青温度

	private String aggregateTemperature;// 骨料温度

	private String totalOutput;// 总产量

	private String actualWhetstoneRatio;// 实际油石比

	private String actualAggregate1;// 实际骨料1(接口原字段：SJGL1)

	private String actualAggregate2;// 实际骨料2(接口原字段：SJGL2)

	private String actualAggregate3;// 实际骨料3(接口原字段：SJGL3)

	private String actualAggregate4;// 实际骨料4(接口原字段：SJGL4)

	private String actualAggregate5;// 实际骨料5(接口原字段：SJGL5)

	private String actualAggregate6;// 实际骨料6

	private String actualAggregate7;// 实际骨料7

	private String actualPowder1;// 实际粉料1

	private String actualPowder2;// 实际粉料2

	private String actualAsphalt;// 实际沥青

	private String actualAdditive;// 实际添加剂

	private String theoryWhetstoneRatio;// 理论油石比

	private String theoryAggregate1;// 理论骨料1

	private String theoryAggregate2;// 理论骨料2

	private String theoryAggregate3;// 理论骨料3

	private String theoryAggregate4;// 理论骨料4

	private String theoryAggregate5;// 理论骨料5

	private String theoryAggregate6;// 理论骨料6

	private String theoryAggregate7;// 理论骨料7

	private String theoryPowder1;// 理论粉料1

	private String theoryPowder2;// 理论粉料2

	private String theoryAsphalt;// 理论沥青

	private String theoryAdditive;// 理论添加剂

	private String dischargeTemperature;// 出料温度

	private String customerName;// 客户名称

	private String engineeringName;// 工程名称

	private String constructionPosition;// 施工部位

	private String cumulativeAmount;// 累计数量

	private String backupField1;// 备用1

	private String backupField2;// 备用2

	private String backupField3;// 备用3

	private String saveDate;// 保存时间

	private String gatherDate;// 采集时间

	private String ukey;// 生产数据唯一码与2.5接口中的ukey对应

	private Long orgId;// 机构id

	private String orgName;// 机构name

	private String isUpdate;// 0:新增 1：更新

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	private String isExcessive;// 0:未超标, 1：已超标

	private String inspectionCode;// 整改（快捷处理）编号
	
	private String rectificationUrgency;// 紧急程度
	private String checkNo;// 检查编号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BusiQualityAsphaltMixingPlantExceedDto getBusiQualityAsphaltMixingPlantExceed() {
		return busiQualityAsphaltMixingPlantExceed;
	}

	public void setBusiQualityAsphaltMixingPlantExceed(
			BusiQualityAsphaltMixingPlantExceedDto busiQualityAsphaltMixingPlantExceed) {
		this.busiQualityAsphaltMixingPlantExceed = busiQualityAsphaltMixingPlantExceed;
	}

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getAsphaltTemperature() {
		return asphaltTemperature;
	}

	public void setAsphaltTemperature(String asphaltTemperature) {
		this.asphaltTemperature = asphaltTemperature;
	}

	public String getAggregateTemperature() {
		return aggregateTemperature;
	}

	public void setAggregateTemperature(String aggregateTemperature) {
		this.aggregateTemperature = aggregateTemperature;
	}

	public String getTotalOutput() {
		return totalOutput;
	}

	public void setTotalOutput(String totalOutput) {
		this.totalOutput = totalOutput;
	}

	public String getActualWhetstoneRatio() {
		return actualWhetstoneRatio;
	}

	public void setActualWhetstoneRatio(String actualWhetstoneRatio) {
		this.actualWhetstoneRatio = actualWhetstoneRatio;
	}

	public String getActualAggregate1() {
		return actualAggregate1;
	}

	public void setActualAggregate1(String actualAggregate1) {
		this.actualAggregate1 = actualAggregate1;
	}

	public String getActualAggregate2() {
		return actualAggregate2;
	}

	public void setActualAggregate2(String actualAggregate2) {
		this.actualAggregate2 = actualAggregate2;
	}

	public String getActualAggregate3() {
		return actualAggregate3;
	}

	public void setActualAggregate3(String actualAggregate3) {
		this.actualAggregate3 = actualAggregate3;
	}

	public String getActualAggregate4() {
		return actualAggregate4;
	}

	public void setActualAggregate4(String actualAggregate4) {
		this.actualAggregate4 = actualAggregate4;
	}

	public String getActualAggregate5() {
		return actualAggregate5;
	}

	public void setActualAggregate5(String actualAggregate5) {
		this.actualAggregate5 = actualAggregate5;
	}

	public String getActualAggregate6() {
		return actualAggregate6;
	}

	public void setActualAggregate6(String actualAggregate6) {
		this.actualAggregate6 = actualAggregate6;
	}

	public String getActualAggregate7() {
		return actualAggregate7;
	}

	public void setActualAggregate7(String actualAggregate7) {
		this.actualAggregate7 = actualAggregate7;
	}

	public String getActualPowder1() {
		return actualPowder1;
	}

	public void setActualPowder1(String actualPowder1) {
		this.actualPowder1 = actualPowder1;
	}

	public String getActualPowder2() {
		return actualPowder2;
	}

	public void setActualPowder2(String actualPowder2) {
		this.actualPowder2 = actualPowder2;
	}

	public String getActualAsphalt() {
		return actualAsphalt;
	}

	public void setActualAsphalt(String actualAsphalt) {
		this.actualAsphalt = actualAsphalt;
	}

	public String getActualAdditive() {
		return actualAdditive;
	}

	public void setActualAdditive(String actualAdditive) {
		this.actualAdditive = actualAdditive;
	}

	public String getTheoryWhetstoneRatio() {
		return theoryWhetstoneRatio;
	}

	public void setTheoryWhetstoneRatio(String theoryWhetstoneRatio) {
		this.theoryWhetstoneRatio = theoryWhetstoneRatio;
	}

	public String getTheoryAggregate1() {
		return theoryAggregate1;
	}

	public void setTheoryAggregate1(String theoryAggregate1) {
		this.theoryAggregate1 = theoryAggregate1;
	}

	public String getTheoryAggregate2() {
		return theoryAggregate2;
	}

	public void setTheoryAggregate2(String theoryAggregate2) {
		this.theoryAggregate2 = theoryAggregate2;
	}

	public String getTheoryAggregate3() {
		return theoryAggregate3;
	}

	public void setTheoryAggregate3(String theoryAggregate3) {
		this.theoryAggregate3 = theoryAggregate3;
	}

	public String getTheoryAggregate4() {
		return theoryAggregate4;
	}

	public void setTheoryAggregate4(String theoryAggregate4) {
		this.theoryAggregate4 = theoryAggregate4;
	}

	public String getTheoryAggregate5() {
		return theoryAggregate5;
	}

	public void setTheoryAggregate5(String theoryAggregate5) {
		this.theoryAggregate5 = theoryAggregate5;
	}

	public String getTheoryAggregate6() {
		return theoryAggregate6;
	}

	public void setTheoryAggregate6(String theoryAggregate6) {
		this.theoryAggregate6 = theoryAggregate6;
	}

	public String getTheoryAggregate7() {
		return theoryAggregate7;
	}

	public void setTheoryAggregate7(String theoryAggregate7) {
		this.theoryAggregate7 = theoryAggregate7;
	}

	public String getTheoryPowder1() {
		return theoryPowder1;
	}

	public void setTheoryPowder1(String theoryPowder1) {
		this.theoryPowder1 = theoryPowder1;
	}

	public String getTheoryPowder2() {
		return theoryPowder2;
	}

	public void setTheoryPowder2(String theoryPowder2) {
		this.theoryPowder2 = theoryPowder2;
	}

	public String getTheoryAsphalt() {
		return theoryAsphalt;
	}

	public void setTheoryAsphalt(String theoryAsphalt) {
		this.theoryAsphalt = theoryAsphalt;
	}

	public String getTheoryAdditive() {
		return theoryAdditive;
	}

	public void setTheoryAdditive(String theoryAdditive) {
		this.theoryAdditive = theoryAdditive;
	}

	public String getDischargeTemperature() {
		return dischargeTemperature;
	}

	public void setDischargeTemperature(String dischargeTemperature) {
		this.dischargeTemperature = dischargeTemperature;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getConstructionPosition() {
		return constructionPosition;
	}

	public void setConstructionPosition(String constructionPosition) {
		this.constructionPosition = constructionPosition;
	}

	public String getCumulativeAmount() {
		return cumulativeAmount;
	}

	public void setCumulativeAmount(String cumulativeAmount) {
		this.cumulativeAmount = cumulativeAmount;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getMixingDate() {
		return mixingDate;
	}

	public void setMixingDate(String mixingDate) {
		this.mixingDate = mixingDate;
	}

	public String getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(String saveDate) {
		this.saveDate = saveDate;
	}

	public String getGatherDate() {
		return gatherDate;
	}

	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
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

	public String getIsExcessive() {
		return isExcessive;
	}

	public void setIsExcessive(String isExcessive) {
		this.isExcessive = isExcessive;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
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
