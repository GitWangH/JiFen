package com.huatek.busi.dto.quality;


public class BusiQualityCementMixingStationInspectionDto {
	private Long id;

	private BusiQualityCementMixingStationExceedDto busiQualityCementMixingStationExceed;

	private String jobNumber;// 工单号

	private String operator;// 操作者

	private String designSquare;// 设计方量

	private String actualSquare;// 实际方量

	private String fineAggregate1;// 细骨料1

	private String fineAggregate1Proportioning;// 细骨料1配比

	private String fineAggregate2;// 细骨料2

	private String fineAggregate2Proportioning;// 细骨料2配比

	private String coarseAggregate1;// 粗骨料1

	private String coarseAggregate1Proportioning;// 粗骨料1配比

	private String coarseAggregate2;// 粗骨料2

	private String coarseAggregate2Proportioning;// 粗骨料2配比

	private String coarseAggregate3;// 粗骨料3

	private String coarseAggregate3Proportioning;// 粗骨料3配比

	private String cement1;// 水泥1

	private String cement1Proportioning;// 水泥1配比

	private String cement2;// 水泥2

	private String cement2Proportioning;// 水泥2配比

	private String orePowder;// 矿粉

	private String orePowderProportioning;// 矿粉配比

	private String flyash;// 粉煤灰

	private String flyashProportioning;// 粉煤灰配比

	private String powder5;// 粉料5

	private String powder5Proportioning;// 粉料5配比

	private String powder6;// 粉料6

	private String powder6Proportioning;// 粉料6配比

	private String water1;// 水1

	private String water1Proportioning;// 水1配比

	private String water2;// 水2

	private String water2Proportioning;// 水2配比

	private String admixture1;// 外加剂1

	private String admixture1Proportioning;// 外加剂1配比

	private String admixture2;// 外加剂2

	private String admixture2Proportioning;// 外加剂2配比

	private String admixture3;// 外加剂3

	private String admixture3Proportioning;// 外加剂3配比

	private String admixture4;// 外加剂4

	private String admixture4Proportioning;// 外加剂4配比

	private String dischargeDate;// 出料时间

	private String engineeringName;// 工程名称

	private String jobLocation;// 施工地点

	private String pouringPosition;// 浇注部位

	private String cementType;// 水泥品种

	private String recipeNumber;// 配方号

	private String strengthGrade;// 强度等级

	private String mixingDuration;// 搅拌时长

	private String saveDatetime;// 保存时间

	private String clientCode;// 客户端编号(本拌合机数据唯一编号)

	private String ukey;// 唯一码

	private Long orgId;// 机构id

	private String orgName;// 机构name

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	private String appKey;// 接口传数据标识符

	private String inspectionCode;// 整改（快捷处理）编号

	private String excessiveGrade;// 超标等级(1:一级,2：二级, 3:三级)

	private String excessiveReason;// 超标原因

	private String isExcessive;// 检测状态 0:未超标, 1：已超标
	
	private String rectificationUrgency;// 紧急程度

	public BusiQualityCementMixingStationExceedDto getBusiQualityCementMixingStationExceed() {
		return busiQualityCementMixingStationExceed;
	}

	public void setBusiQualityCementMixingStationExceed(
			BusiQualityCementMixingStationExceedDto busiQualityCementMixingStationExceed) {
		this.busiQualityCementMixingStationExceed = busiQualityCementMixingStationExceed;
	}

	private String reportAddress;// 报告地址

	private String desContent;// 描述

	private String disposeTime;// 处理时间

	private BusiQualityRectificationDto rectificationDto;// 获取整改单填写信息

	private BusiQualityQuickProcessingDto quickProcessingDto;// 获取快捷处理填写信息
	
	private String checkNo;

	public BusiQualityRectificationDto getRectificationDto() {
		return rectificationDto;
	}

	public void setRectificationDto(BusiQualityRectificationDto rectificationDto) {
		this.rectificationDto = rectificationDto;
	}

	public BusiQualityQuickProcessingDto getQuickProcessingDto() {
		return quickProcessingDto;
	}

	public void setQuickProcessingDto(
			BusiQualityQuickProcessingDto quickProcessingDto) {
		this.quickProcessingDto = quickProcessingDto;
	}

	public String getReportAddress() {
		return reportAddress;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getDesContent() {
		return desContent;
	}

	public void setDesContent(String desContent) {
		this.desContent = desContent;
	}

	public String getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
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

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getDesignSquare() {
		return designSquare;
	}

	public void setDesignSquare(String designSquare) {
		this.designSquare = designSquare;
	}

	public String getActualSquare() {
		return actualSquare;
	}

	public void setActualSquare(String actualSquare) {
		this.actualSquare = actualSquare;
	}

	public String getFineAggregate1() {
		return fineAggregate1;
	}

	public void setFineAggregate1(String fineAggregate1) {
		this.fineAggregate1 = fineAggregate1;
	}

	public String getFineAggregate1Proportioning() {
		return fineAggregate1Proportioning;
	}

	public void setFineAggregate1Proportioning(
			String fineAggregate1Proportioning) {
		this.fineAggregate1Proportioning = fineAggregate1Proportioning;
	}

	public String getFineAggregate2() {
		return fineAggregate2;
	}

	public void setFineAggregate2(String fineAggregate2) {
		this.fineAggregate2 = fineAggregate2;
	}

	public String getFineAggregate2Proportioning() {
		return fineAggregate2Proportioning;
	}

	public void setFineAggregate2Proportioning(
			String fineAggregate2Proportioning) {
		this.fineAggregate2Proportioning = fineAggregate2Proportioning;
	}

	public String getCoarseAggregate1() {
		return coarseAggregate1;
	}

	public void setCoarseAggregate1(String coarseAggregate1) {
		this.coarseAggregate1 = coarseAggregate1;
	}

	public String getCoarseAggregate1Proportioning() {
		return coarseAggregate1Proportioning;
	}

	public void setCoarseAggregate1Proportioning(
			String coarseAggregate1Proportioning) {
		this.coarseAggregate1Proportioning = coarseAggregate1Proportioning;
	}

	public String getCoarseAggregate2() {
		return coarseAggregate2;
	}

	public void setCoarseAggregate2(String coarseAggregate2) {
		this.coarseAggregate2 = coarseAggregate2;
	}

	public String getCoarseAggregate2Proportioning() {
		return coarseAggregate2Proportioning;
	}

	public void setCoarseAggregate2Proportioning(
			String coarseAggregate2Proportioning) {
		this.coarseAggregate2Proportioning = coarseAggregate2Proportioning;
	}

	public String getCoarseAggregate3() {
		return coarseAggregate3;
	}

	public void setCoarseAggregate3(String coarseAggregate3) {
		this.coarseAggregate3 = coarseAggregate3;
	}

	public String getCoarseAggregate3Proportioning() {
		return coarseAggregate3Proportioning;
	}

	public void setCoarseAggregate3Proportioning(
			String coarseAggregate3Proportioning) {
		this.coarseAggregate3Proportioning = coarseAggregate3Proportioning;
	}

	public String getCement1() {
		return cement1;
	}

	public void setCement1(String cement1) {
		this.cement1 = cement1;
	}

	public String getCement1Proportioning() {
		return cement1Proportioning;
	}

	public void setCement1Proportioning(String cement1Proportioning) {
		this.cement1Proportioning = cement1Proportioning;
	}

	public String getCement2() {
		return cement2;
	}

	public void setCement2(String cement2) {
		this.cement2 = cement2;
	}

	public String getCement2Proportioning() {
		return cement2Proportioning;
	}

	public void setCement2Proportioning(String cement2Proportioning) {
		this.cement2Proportioning = cement2Proportioning;
	}

	public String getOrePowder() {
		return orePowder;
	}

	public void setOrePowder(String orePowder) {
		this.orePowder = orePowder;
	}

	public String getOrePowderProportioning() {
		return orePowderProportioning;
	}

	public void setOrePowderProportioning(String orePowderProportioning) {
		this.orePowderProportioning = orePowderProportioning;
	}

	public String getFlyash() {
		return flyash;
	}

	public void setFlyash(String flyash) {
		this.flyash = flyash;
	}

	public String getFlyashProportioning() {
		return flyashProportioning;
	}

	public void setFlyashProportioning(String flyashProportioning) {
		this.flyashProportioning = flyashProportioning;
	}

	public String getPowder5() {
		return powder5;
	}

	public void setPowder5(String powder5) {
		this.powder5 = powder5;
	}

	public String getPowder5Proportioning() {
		return powder5Proportioning;
	}

	public void setPowder5Proportioning(String powder5Proportioning) {
		this.powder5Proportioning = powder5Proportioning;
	}

	public String getPowder6() {
		return powder6;
	}

	public void setPowder6(String powder6) {
		this.powder6 = powder6;
	}

	public String getPowder6Proportioning() {
		return powder6Proportioning;
	}

	public void setPowder6Proportioning(String powder6Proportioning) {
		this.powder6Proportioning = powder6Proportioning;
	}

	public String getWater1() {
		return water1;
	}

	public void setWater1(String water1) {
		this.water1 = water1;
	}

	public String getWater1Proportioning() {
		return water1Proportioning;
	}

	public void setWater1Proportioning(String water1Proportioning) {
		this.water1Proportioning = water1Proportioning;
	}

	public String getWater2() {
		return water2;
	}

	public void setWater2(String water2) {
		this.water2 = water2;
	}

	public String getWater2Proportioning() {
		return water2Proportioning;
	}

	public void setWater2Proportioning(String water2Proportioning) {
		this.water2Proportioning = water2Proportioning;
	}

	public String getAdmixture1() {
		return admixture1;
	}

	public void setAdmixture1(String admixture1) {
		this.admixture1 = admixture1;
	}

	public String getAdmixture1Proportioning() {
		return admixture1Proportioning;
	}

	public void setAdmixture1Proportioning(String admixture1Proportioning) {
		this.admixture1Proportioning = admixture1Proportioning;
	}

	public String getAdmixture2() {
		return admixture2;
	}

	public void setAdmixture2(String admixture2) {
		this.admixture2 = admixture2;
	}

	public String getAdmixture2Proportioning() {
		return admixture2Proportioning;
	}

	public void setAdmixture2Proportioning(String admixture2Proportioning) {
		this.admixture2Proportioning = admixture2Proportioning;
	}

	public String getAdmixture3() {
		return admixture3;
	}

	public void setAdmixture3(String admixture3) {
		this.admixture3 = admixture3;
	}

	public String getAdmixture3Proportioning() {
		return admixture3Proportioning;
	}

	public void setAdmixture3Proportioning(String admixture3Proportioning) {
		this.admixture3Proportioning = admixture3Proportioning;
	}

	public String getAdmixture4() {
		return admixture4;
	}

	public void setAdmixture4(String admixture4) {
		this.admixture4 = admixture4;
	}

	public String getAdmixture4Proportioning() {
		return admixture4Proportioning;
	}

	public void setAdmixture4Proportioning(String admixture4Proportioning) {
		this.admixture4Proportioning = admixture4Proportioning;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getPouringPosition() {
		return pouringPosition;
	}

	public void setPouringPosition(String pouringPosition) {
		this.pouringPosition = pouringPosition;
	}

	public String getCementType() {
		return cementType;
	}

	public void setCementType(String cementType) {
		this.cementType = cementType;
	}

	public String getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(String recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public String getStrengthGrade() {
		return strengthGrade;
	}

	public void setStrengthGrade(String strengthGrade) {
		this.strengthGrade = strengthGrade;
	}

	public String getMixingDuration() {
		return mixingDuration;
	}

	public void setMixingDuration(String mixingDuration) {
		this.mixingDuration = mixingDuration;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
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

	public String getSaveDatetime() {
		return saveDatetime;
	}

	public void setSaveDatetime(String saveDatetime) {
		this.saveDatetime = saveDatetime;
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

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public String getExcessiveGrade() {
		return excessiveGrade;
	}

	public void setExcessiveGrade(String excessiveGrade) {
		this.excessiveGrade = excessiveGrade;
	}

	public String getExcessiveReason() {
		return excessiveReason;
	}

	public void setExcessiveReason(String excessiveReason) {
		this.excessiveReason = excessiveReason;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
