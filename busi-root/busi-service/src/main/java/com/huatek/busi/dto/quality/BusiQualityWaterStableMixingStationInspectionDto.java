package com.huatek.busi.dto.quality;


public class BusiQualityWaterStableMixingStationInspectionDto {
	private Long id;

	private BusiQualityWaterStableMixingStationExceedDto busiQualityWaterStableMixingStationExceed;

	private String actualAggregate1;// 实际骨料1(接口原字段：SJGL1)

	private String actualAggregate2;// 实际骨料2(接口原字段：SJGL2)

	private String actualAggregate3;// 实际骨料3(接口原字段：SJGL3)

	private String actualAggregate4;// 实际骨料4(接口原字段：SJGL4)

	private String actualAggregate5;// 实际骨料5(接口原字段：SJGL5)

	private String actualPowder1;// 实际粉料1(接口原字段：SJFL1)

	private String actualPowder2;// 实际粉料2(接口原字段：SJFL2)

	private String actualWater;// 实际水(接口原字段：SJSHUI)

	private String startDate;// 开始时间

	private String endDate;// 结束时间

	private String roughTotalOutput;// 粗总产量

	private String theoryAggregate1;// 理论骨料1

	private String theoryAggregate2;// 理论骨料2

	private String theoryAggregate3;// 理论骨料3

	private String theoryAggregate4;// 理论骨料4

	private String theoryAggregate5;// 理论骨料5

	private String theoryPowder1;// 理论粉料1

	private String theoryPowder2;// 理论粉料2

	private String theoryWater;// 理论水

	private String aggregateRatio1;// 骨料比率1

	private String aggregateRatio2;// 骨料比率2

	private String aggregateRatio3;// 骨料比率3

	private String aggregateRatio4;// 骨料比率4

	private String aggregateRatio5;// 骨料比率5

	private String powderRatio1;// 粉料比率1

	private String powderRatio2;// 粉料比率2

	private String waterRatio;// 水比率

	private String saveDate;// 保存时间

	private String gatherDate;// 采集时间

	private String isCalculatedGradation;// 是否计算级配

	private String actualAggregateSieving1;// 实际骨料1筛分

	private String actualAggregateSieving2;// 实际骨料2筛分

	private String actualAggregateSieving3;// 实际骨料3筛分

	private String actualAggregateSieving4;// 实际骨料4筛分

	private String actualAggregateSieving5;// 实际骨料5筛分

	private String actualPowderSieving1;// 实际粉料1筛分

	private String actualPowderSieving2;// 实际粉料2筛分

	private String recipeCode;// 配方号

	private String recipeName;// 配方名称

	private String backupField1;// 备用1

	private String backupField2;// 备用2

	private String backupField3;// 备用3

	private String ukey;// 水泥品种 （生产数据唯一码,和2.3接口中的ukey是对应的）

	private String orgId;// 被检测样品所属施工标段ID

	private String orgName;// 机构name

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传，2已传给质监局并做修改

	private String appKey;// 接口传数据标识符

	private String isExcessive;// 检测状态 0:未超标, 1：已超标

	private String inspectionCode;// 整改（快捷处理）编号
	
	private String rectificationUrgency;// 紧急程度
	private String checkNo;// 检查编号

	public BusiQualityWaterStableMixingStationExceedDto getBusiQualityWaterStableMixingStationExceed() {
		return busiQualityWaterStableMixingStationExceed;
	}

	public void setBusiQualityWaterStableMixingStationExceed(
			BusiQualityWaterStableMixingStationExceedDto busiQualityWaterStableMixingStationExceed) {
		this.busiQualityWaterStableMixingStationExceed = busiQualityWaterStableMixingStationExceed;
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

	public String getIsExcessive() {
		return isExcessive;
	}

	public void setIsExcessive(String isExcessive) {
		this.isExcessive = isExcessive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getActualWater() {
		return actualWater;
	}

	public void setActualWater(String actualWater) {
		this.actualWater = actualWater;
	}

	public String getRoughTotalOutput() {
		return roughTotalOutput;
	}

	public void setRoughTotalOutput(String roughTotalOutput) {
		this.roughTotalOutput = roughTotalOutput;
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

	public String getTheoryWater() {
		return theoryWater;
	}

	public void setTheoryWater(String theoryWater) {
		this.theoryWater = theoryWater;
	}

	public String getAggregateRatio1() {
		return aggregateRatio1;
	}

	public void setAggregateRatio1(String aggregateRatio1) {
		this.aggregateRatio1 = aggregateRatio1;
	}

	public String getAggregateRatio2() {
		return aggregateRatio2;
	}

	public void setAggregateRatio2(String aggregateRatio2) {
		this.aggregateRatio2 = aggregateRatio2;
	}

	public String getAggregateRatio3() {
		return aggregateRatio3;
	}

	public void setAggregateRatio3(String aggregateRatio3) {
		this.aggregateRatio3 = aggregateRatio3;
	}

	public String getAggregateRatio4() {
		return aggregateRatio4;
	}

	public void setAggregateRatio4(String aggregateRatio4) {
		this.aggregateRatio4 = aggregateRatio4;
	}

	public String getAggregateRatio5() {
		return aggregateRatio5;
	}

	public void setAggregateRatio5(String aggregateRatio5) {
		this.aggregateRatio5 = aggregateRatio5;
	}

	public String getPowderRatio1() {
		return powderRatio1;
	}

	public void setPowderRatio1(String powderRatio1) {
		this.powderRatio1 = powderRatio1;
	}

	public String getPowderRatio2() {
		return powderRatio2;
	}

	public void setPowderRatio2(String powderRatio2) {
		this.powderRatio2 = powderRatio2;
	}

	public String getWaterRatio() {
		return waterRatio;
	}

	public void setWaterRatio(String waterRatio) {
		this.waterRatio = waterRatio;
	}

	public String getIsCalculatedGradation() {
		return isCalculatedGradation;
	}

	public void setIsCalculatedGradation(String isCalculatedGradation) {
		this.isCalculatedGradation = isCalculatedGradation;
	}

	public String getActualAggregateSieving1() {
		return actualAggregateSieving1;
	}

	public void setActualAggregateSieving1(String actualAggregateSieving1) {
		this.actualAggregateSieving1 = actualAggregateSieving1;
	}

	public String getActualAggregateSieving2() {
		return actualAggregateSieving2;
	}

	public void setActualAggregateSieving2(String actualAggregateSieving2) {
		this.actualAggregateSieving2 = actualAggregateSieving2;
	}

	public String getActualAggregateSieving3() {
		return actualAggregateSieving3;
	}

	public void setActualAggregateSieving3(String actualAggregateSieving3) {
		this.actualAggregateSieving3 = actualAggregateSieving3;
	}

	public String getActualAggregateSieving4() {
		return actualAggregateSieving4;
	}

	public void setActualAggregateSieving4(String actualAggregateSieving4) {
		this.actualAggregateSieving4 = actualAggregateSieving4;
	}

	public String getActualAggregateSieving5() {
		return actualAggregateSieving5;
	}

	public void setActualAggregateSieving5(String actualAggregateSieving5) {
		this.actualAggregateSieving5 = actualAggregateSieving5;
	}

	public String getActualPowderSieving1() {
		return actualPowderSieving1;
	}

	public void setActualPowderSieving1(String actualPowderSieving1) {
		this.actualPowderSieving1 = actualPowderSieving1;
	}

	public String getActualPowderSieving2() {
		return actualPowderSieving2;
	}

	public void setActualPowderSieving2(String actualPowderSieving2) {
		this.actualPowderSieving2 = actualPowderSieving2;
	}

	public String getRecipeCode() {
		return recipeCode;
	}

	public void setRecipeCode(String recipeCode) {
		this.recipeCode = recipeCode;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
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
