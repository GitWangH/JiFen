package com.huatek.busi.model.quality;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 水泥拌合站检测实体类.
 * 
 * @ClassName: BusiQualityCementMixingStationInspection
 * @Description:水泥拌合站检测实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-10-30 14:16:42
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_cement_mixing_station_inspection")
public class BusiQualityCementMixingStationInspection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "CEMENT_MIXING_STATION_INSPECTION_ID", nullable = true)
	private Long id;

	/** @Fields jobNumber : 工单号 */
	@Column(name = "JOB_NUMBER", nullable = false, length = 100)
	private String jobNumber;

	/** @Fields operator : 操作者 */
	@Column(name = "OPERATOR", nullable = false, length = 100)
	private String operator;

	/** @Fields designSquare : 设计方量 */
	@Column(name = "DESIGN_SQUARE", nullable = false, length = 100)
	private String designSquare;

	/** @Fields actualSquare : 实际方量 */
	@Column(name = "ACTUAL_SQUARE", nullable = false, length = 100)
	private String actualSquare;

	/** @Fields fineAggregate1 : 细骨料1 */
	@Column(name = "FINE_AGGREGATE_1", nullable = false, length = 100)
	private String fineAggregate1;

	/** @Fields fineAggregate1Proportioning : 细骨料1配比 */
	@Column(name = "FINE_AGGREGATE_1_PROPORTIONING", nullable = false, length = 100)
	private String fineAggregate1Proportioning;

	/** @Fields fineAggregate2 : 细骨料2 */
	@Column(name = "FINE_AGGREGATE_2", nullable = false, length = 100)
	private String fineAggregate2;

	/** @Fields fineAggregate2Proportioning : 细骨料2配比 */
	@Column(name = "FINE_AGGREGATE_2_PROPORTIONING", nullable = false, length = 100)
	private String fineAggregate2Proportioning;

	/** @Fields coarseAggregate1 : 粗骨料1 */
	@Column(name = "COARSE_AGGREGATE_1", nullable = false, length = 100)
	private String coarseAggregate1;

	/** @Fields coarseAggregate1Proportioning : 粗骨料1配比 */
	@Column(name = "COARSE_AGGREGATE_1_PROPORTIONING", nullable = false, length = 100)
	private String coarseAggregate1Proportioning;

	/** @Fields coarseAggregate2 : 粗骨料2 */
	@Column(name = "COARSE_AGGREGATE_2", nullable = false, length = 100)
	private String coarseAggregate2;

	/** @Fields coarseAggregate2Proportioning : 粗骨料2配比 */
	@Column(name = "COARSE_AGGREGATE_2_PROPORTIONING", nullable = false, length = 100)
	private String coarseAggregate2Proportioning;

	/** @Fields coarseAggregate3 : 粗骨料3 */
	@Column(name = "COARSE_AGGREGATE_3", nullable = false, length = 100)
	private String coarseAggregate3;

	/** @Fields coarseAggregate3Proportioning : 粗骨料3配比 */
	@Column(name = "COARSE_AGGREGATE_3_PROPORTIONING", nullable = false, length = 100)
	private String coarseAggregate3Proportioning;

	/** @Fields cement1 : 水泥1 */
	@Column(name = "CEMENT_1", nullable = false, length = 100)
	private String cement1;

	/** @Fields cement1Proportioning : 水泥1配比 */
	@Column(name = "CEMENT_1_PROPORTIONING", nullable = false, length = 100)
	private String cement1Proportioning;

	/** @Fields cement2 : 水泥2 */
	@Column(name = "CEMENT_2", nullable = false, length = 100)
	private String cement2;

	/** @Fields cement2Proportioning : 水泥2配比 */
	@Column(name = "CEMENT_2_PROPORTIONING", nullable = false, length = 100)
	private String cement2Proportioning;

	/** @Fields orePowder : 矿粉 */
	@Column(name = "ORE_POWDER", nullable = false, length = 100)
	private String orePowder;

	/** @Fields orePowderProportioning : 矿粉配比 */
	@Column(name = "ORE_POWDER_PROPORTIONING", nullable = false, length = 100)
	private String orePowderProportioning;

	/** @Fields flyash : 粉煤灰 */
	@Column(name = "FLYASH", nullable = false, length = 100)
	private String flyash;

	/** @Fields flyashProportioning : 粉煤灰配比 */
	@Column(name = "FLYASH_PROPORTIONING", nullable = false, length = 100)
	private String flyashProportioning;

	/** @Fields powder5 : 粉料5 */
	@Column(name = "POWDER_5", nullable = false, length = 100)
	private String powder5;

	/** @Fields powder5Proportioning : 粉料5配比 */
	@Column(name = "POWDER_5_PROPORTIONING", nullable = false, length = 100)
	private String powder5Proportioning;

	/** @Fields powder6 : 粉料6 */
	@Column(name = "POWDER_6", nullable = false, length = 100)
	private String powder6;

	/** @Fields powder6Proportioning : 粉料6配比 */
	@Column(name = "POWDER_6_PROPORTIONING", nullable = false, length = 100)
	private String powder6Proportioning;

	/** @Fields water1 : 水1 */
	@Column(name = "WATER_1", nullable = false, length = 100)
	private String water1;

	/** @Fields water1Proportioning : 水1配比 */
	@Column(name = "WATER_1_PROPORTIONING", nullable = false, length = 100)
	private String water1Proportioning;

	/** @Fields water2 : 水2 */
	@Column(name = "WATER_2", nullable = false, length = 100)
	private String water2;

	/** @Fields water2Proportioning : 水2配比 */
	@Column(name = "WATER_2_PROPORTIONING", nullable = false, length = 100)
	private String water2Proportioning;

	/** @Fields admixture1 : 外加剂1 */
	@Column(name = "ADMIXTURE_1", nullable = false, length = 100)
	private String admixture1;

	/** @Fields admixture1Proportioning : 外加剂1配比 */
	@Column(name = "ADMIXTURE_1_PROPORTIONING", nullable = false, length = 100)
	private String admixture1Proportioning;

	/** @Fields admixture2 : 外加剂2 */
	@Column(name = "ADMIXTURE_2", nullable = false, length = 100)
	private String admixture2;

	/** @Fields admixture2Proportioning : 外加剂2配比 */
	@Column(name = "ADMIXTURE_2_PROPORTIONING", nullable = false, length = 100)
	private String admixture2Proportioning;

	/** @Fields admixture3 : 外加剂3 */
	@Column(name = "ADMIXTURE_3", nullable = false, length = 100)
	private String admixture3;

	/** @Fields admixture3Proportioning : 外加剂3配比 */
	@Column(name = "ADMIXTURE_3_PROPORTIONING", nullable = false, length = 100)
	private String admixture3Proportioning;

	/** @Fields admixture4 : 外加剂4 */
	@Column(name = "ADMIXTURE_4", nullable = false, length = 100)
	private String admixture4;

	/** @Fields admixture4Proportioning : 外加剂4配比 */
	@Column(name = "ADMIXTURE_4_PROPORTIONING", nullable = false, length = 100)
	private String admixture4Proportioning;

	/** @Fields dischargeDate : 出料时间 */
	@Column(name = "DISCHARGE_DATE", nullable = false, length = 100)
	private String dischargeDate;

	/** @Fields engineeringName : 工程名称 */
	@Column(name = "ENGINEERING_NAME", nullable = false, length = 100)
	private String engineeringName;

	/** @Fields jobLocation : 施工地点 */
	@Column(name = "JOB_LOCATION", nullable = false, length = 100)
	private String jobLocation;

	/** @Fields pouringPosition : 浇注部位 */
	@Column(name = "POURING_POSITION", nullable = false, length = 100)
	private String pouringPosition;

	/** @Fields cementType : 水泥品种 */
	@Column(name = "CEMENT_TYPE", nullable = false, length = 100)
	private String cementType;

	/** @Fields recipeNumber : 配方号 */
	@Column(name = "RECIPE_NUMBER", nullable = false, length = 100)
	private String recipeNumber;

	/** @Fields strengthGrade : 强度等级 */
	@Column(name = "STRENGTH_GRADE", nullable = false, length = 100)
	private String strengthGrade;

	/** @Fields mixingDuration : 搅拌时长 */
	@Column(name = "MIXING_DURATION", nullable = false, length = 100)
	private String mixingDuration;

	/** @Fields saveDatetime : 保存时间 */
	@Column(name = "SAVE_DATETIME", nullable = false)
	private Date saveDatetime;

	/** @Fields clientCode : 客户端编号(本拌合机数据唯一编号) */
	@Column(name = "CLIENT_CODE", nullable = false, length = 100)
	private String clientCode;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = true, length = 100)
	private String ukey;

	/** @Fields orgId : */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg org;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields tenantId : 租户id */
	@Column(name = "TENANT_ID", nullable = false)
	private Long tenantId;

	/** @Fields inspectionType : 整改类型 0 快速处理 1 整改单 */
	@Column(name = "INSPECTION_TYPE", nullable = false)
	private Integer inspectionType;

	/** @Fields inspectionId : 快速处理或整改单的ID */
	@Column(name = "INSPECTION_ID", nullable = false)
	private Long inspectionId;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields appKey : 接口传数据标识符 */
	@Column(name = "APP_KEY", nullable = false, length = 100)
	private String appKey;

	/** @Fields inspectionCode : 整改（快捷处理）编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;

	/** @Fields orgName : 机构名称 */
	@Transient
	private String orgName;

	/** @Fields isExcessive : 0:未超标, 1：已超标 */
	@Column(name = "IS_EXCESSIVE", nullable = false, length = 100)
	private String isExcessive;

	@OneToOne
	@JoinColumn(name="CEMENT_MIXING_STATION_EXCEED_ID")
	private BusiQualityCementMixingStationExceed busiQualityCementMixingStationExceed;

	

	public BusiQualityCementMixingStationExceed getBusiQualityCementMixingStationExceed() {
		return busiQualityCementMixingStationExceed;
	}

	public void setBusiQualityCementMixingStationExceed(
			BusiQualityCementMixingStationExceed busiQualityCementMixingStationExceed) {
		this.busiQualityCementMixingStationExceed = busiQualityCementMixingStationExceed;
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


	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setDesignSquare(String designSquare) {
		this.designSquare = designSquare;
	}

	public String getDesignSquare() {
		return this.designSquare;
	}

	public void setActualSquare(String actualSquare) {
		this.actualSquare = actualSquare;
	}

	public String getActualSquare() {
		return this.actualSquare;
	}

	public void setFineAggregate1(String fineAggregate1) {
		this.fineAggregate1 = fineAggregate1;
	}

	public String getFineAggregate1() {
		return this.fineAggregate1;
	}

	public void setFineAggregate1Proportioning(
			String fineAggregate1Proportioning) {
		this.fineAggregate1Proportioning = fineAggregate1Proportioning;
	}

	public String getFineAggregate1Proportioning() {
		return this.fineAggregate1Proportioning;
	}

	public void setFineAggregate2(String fineAggregate2) {
		this.fineAggregate2 = fineAggregate2;
	}

	public String getFineAggregate2() {
		return this.fineAggregate2;
	}

	public void setFineAggregate2Proportioning(
			String fineAggregate2Proportioning) {
		this.fineAggregate2Proportioning = fineAggregate2Proportioning;
	}

	public String getFineAggregate2Proportioning() {
		return this.fineAggregate2Proportioning;
	}

	public void setCoarseAggregate1(String coarseAggregate1) {
		this.coarseAggregate1 = coarseAggregate1;
	}

	public String getCoarseAggregate1() {
		return this.coarseAggregate1;
	}

	public void setCoarseAggregate1Proportioning(
			String coarseAggregate1Proportioning) {
		this.coarseAggregate1Proportioning = coarseAggregate1Proportioning;
	}

	public String getCoarseAggregate1Proportioning() {
		return this.coarseAggregate1Proportioning;
	}

	public void setCoarseAggregate2(String coarseAggregate2) {
		this.coarseAggregate2 = coarseAggregate2;
	}

	public String getCoarseAggregate2() {
		return this.coarseAggregate2;
	}

	public void setCoarseAggregate2Proportioning(
			String coarseAggregate2Proportioning) {
		this.coarseAggregate2Proportioning = coarseAggregate2Proportioning;
	}

	public String getCoarseAggregate2Proportioning() {
		return this.coarseAggregate2Proportioning;
	}

	public void setCoarseAggregate3(String coarseAggregate3) {
		this.coarseAggregate3 = coarseAggregate3;
	}

	public String getCoarseAggregate3() {
		return this.coarseAggregate3;
	}

	public void setCoarseAggregate3Proportioning(
			String coarseAggregate3Proportioning) {
		this.coarseAggregate3Proportioning = coarseAggregate3Proportioning;
	}

	public String getCoarseAggregate3Proportioning() {
		return this.coarseAggregate3Proportioning;
	}

	public void setCement1(String cement1) {
		this.cement1 = cement1;
	}

	public String getCement1() {
		return this.cement1;
	}

	public void setCement1Proportioning(String cement1Proportioning) {
		this.cement1Proportioning = cement1Proportioning;
	}

	public String getCement1Proportioning() {
		return this.cement1Proportioning;
	}

	public void setCement2(String cement2) {
		this.cement2 = cement2;
	}

	public String getCement2() {
		return this.cement2;
	}

	public void setCement2Proportioning(String cement2Proportioning) {
		this.cement2Proportioning = cement2Proportioning;
	}

	public String getCement2Proportioning() {
		return this.cement2Proportioning;
	}

	public void setOrePowder(String orePowder) {
		this.orePowder = orePowder;
	}

	public String getOrePowder() {
		return this.orePowder;
	}

	public void setOrePowderProportioning(String orePowderProportioning) {
		this.orePowderProportioning = orePowderProportioning;
	}

	public String getOrePowderProportioning() {
		return this.orePowderProportioning;
	}

	public void setFlyash(String flyash) {
		this.flyash = flyash;
	}

	public String getFlyash() {
		return this.flyash;
	}

	public void setFlyashProportioning(String flyashProportioning) {
		this.flyashProportioning = flyashProportioning;
	}

	public String getFlyashProportioning() {
		return this.flyashProportioning;
	}

	public void setPowder5(String powder5) {
		this.powder5 = powder5;
	}

	public String getPowder5() {
		return this.powder5;
	}

	public void setPowder5Proportioning(String powder5Proportioning) {
		this.powder5Proportioning = powder5Proportioning;
	}

	public String getPowder5Proportioning() {
		return this.powder5Proportioning;
	}

	public void setPowder6(String powder6) {
		this.powder6 = powder6;
	}

	public String getPowder6() {
		return this.powder6;
	}

	public void setPowder6Proportioning(String powder6Proportioning) {
		this.powder6Proportioning = powder6Proportioning;
	}

	public String getPowder6Proportioning() {
		return this.powder6Proportioning;
	}

	public void setWater1(String water1) {
		this.water1 = water1;
	}

	public String getWater1() {
		return this.water1;
	}

	public void setWater1Proportioning(String water1Proportioning) {
		this.water1Proportioning = water1Proportioning;
	}

	public String getWater1Proportioning() {
		return this.water1Proportioning;
	}

	public void setWater2(String water2) {
		this.water2 = water2;
	}

	public String getWater2() {
		return this.water2;
	}

	public void setWater2Proportioning(String water2Proportioning) {
		this.water2Proportioning = water2Proportioning;
	}

	public String getWater2Proportioning() {
		return this.water2Proportioning;
	}

	public void setAdmixture1(String admixture1) {
		this.admixture1 = admixture1;
	}

	public String getAdmixture1() {
		return this.admixture1;
	}

	public void setAdmixture1Proportioning(String admixture1Proportioning) {
		this.admixture1Proportioning = admixture1Proportioning;
	}

	public String getAdmixture1Proportioning() {
		return this.admixture1Proportioning;
	}

	public void setAdmixture2(String admixture2) {
		this.admixture2 = admixture2;
	}

	public String getAdmixture2() {
		return this.admixture2;
	}

	public void setAdmixture2Proportioning(String admixture2Proportioning) {
		this.admixture2Proportioning = admixture2Proportioning;
	}

	public String getAdmixture2Proportioning() {
		return this.admixture2Proportioning;
	}

	public void setAdmixture3(String admixture3) {
		this.admixture3 = admixture3;
	}

	public String getAdmixture3() {
		return this.admixture3;
	}

	public void setAdmixture3Proportioning(String admixture3Proportioning) {
		this.admixture3Proportioning = admixture3Proportioning;
	}

	public String getAdmixture3Proportioning() {
		return this.admixture3Proportioning;
	}

	public void setAdmixture4(String admixture4) {
		this.admixture4 = admixture4;
	}

	public String getAdmixture4() {
		return this.admixture4;
	}

	public void setAdmixture4Proportioning(String admixture4Proportioning) {
		this.admixture4Proportioning = admixture4Proportioning;
	}

	public String getAdmixture4Proportioning() {
		return this.admixture4Proportioning;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getDischargeDate() {
		return this.dischargeDate;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getEngineeringName() {
		return this.engineeringName;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getJobLocation() {
		return this.jobLocation;
	}

	public void setPouringPosition(String pouringPosition) {
		this.pouringPosition = pouringPosition;
	}

	public String getPouringPosition() {
		return this.pouringPosition;
	}

	public void setCementType(String cementType) {
		this.cementType = cementType;
	}

	public String getCementType() {
		return this.cementType;
	}

	public void setRecipeNumber(String recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public String getRecipeNumber() {
		return this.recipeNumber;
	}

	public void setStrengthGrade(String strengthGrade) {
		this.strengthGrade = strengthGrade;
	}

	public String getStrengthGrade() {
		return this.strengthGrade;
	}

	public void setMixingDuration(String mixingDuration) {
		this.mixingDuration = mixingDuration;
	}

	public String getMixingDuration() {
		return this.mixingDuration;
	}

	public void setSaveDatetime(Date saveDatetime) {
		this.saveDatetime = saveDatetime;
	}

	public Date getSaveDatetime() {
		return this.saveDatetime;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientCode() {
		return this.clientCode;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTenantId() {
		return this.tenantId;
	}

	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}

	public Integer getInspectionType() {
		return this.inspectionType;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}

	public Long getInspectionId() {
		return this.inspectionId;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsQualitySupervisionBureau(Integer isQualitySupervisionBureau) {
		this.isQualitySupervisionBureau = isQualitySupervisionBureau;
	}

	public Integer getIsQualitySupervisionBureau() {
		return this.isQualitySupervisionBureau;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

}
