package com.huatek.busi.model.quality;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 水稳拌合站检测实体类.
 * 
 * @ClassName: BusiQualityWaterStableMixingStationInspection
 * @Description:水稳拌合站检测实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-04 09:27:30
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_water_stable_mixing_station_inspection")
public class BusiQualityWaterStableMixingStationInspection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "WATER_STABLE_MIXING_STATION_INSPECTION_ID", nullable = true)
	private Long id;

	/** @Fields actualAggregate1 : 实际骨料1(接口原字段：SJGL1) */
	@Column(name = "ACTUAL_AGGREGATE_1", nullable = true, length = 50)
	private String actualAggregate1;

	/** @Fields actualAggregate2 : 实际骨料2(接口原字段：SJGL2) */
	@Column(name = "ACTUAL_AGGREGATE_2", nullable = true, length = 50)
	private String actualAggregate2;

	/** @Fields actualAggregate3 : 实际骨料3(接口原字段：SJGL3) */
	@Column(name = "ACTUAL_AGGREGATE_3", nullable = true, length = 50)
	private String actualAggregate3;

	/** @Fields actualAggregate4 : 实际骨料4(接口原字段：SJGL4) */
	@Column(name = "ACTUAL_AGGREGATE_4", nullable = true, length = 50)
	private String actualAggregate4;

	/** @Fields actualAggregate5 : 实际骨料5(接口原字段：SJGL5) */
	@Column(name = "ACTUAL_AGGREGATE_5", nullable = true, length = 50)
	private String actualAggregate5;

	/** @Fields actualPowder1 : 实际粉料1(接口原字段：SJFL1) */
	@Column(name = "ACTUAL_POWDER_1", nullable = true, length = 50)
	private String actualPowder1;

	/** @Fields actualPowder2 : 实际粉料2(接口原字段：SJFL2) */
	@Column(name = "ACTUAL_POWDER_2", nullable = true, length = 50)
	private String actualPowder2;

	/** @Fields actualWater : 实际水(接口原字段：SJSHUI) */
	@Column(name = "ACTUAL_WATER", nullable = true, length = 50)
	private String actualWater;

	/** @Fields startDate : 开始时间 */
	@Column(name = "START_DATE", nullable = true)
	private Date startDate;

	/** @Fields endDate : 结束时间 */
	@Column(name = "END_DATE", nullable = true)
	private Date endDate;

	/** @Fields roughTotalOutput : 粗总产量 */
	@Column(name = "ROUGH_TOTAL_OUTPUT", nullable = true, length = 50)
	private String roughTotalOutput;

	/** @Fields theoryAggregate1 : 理论骨料1 */
	@Column(name = "THEORY_AGGREGATE_1", nullable = true, length = 50)
	private String theoryAggregate1;

	/** @Fields theoryAggregate2 : 理论骨料2 */
	@Column(name = "THEORY_AGGREGATE_2", nullable = true, length = 50)
	private String theoryAggregate2;

	/** @Fields theoryAggregate3 : 理论骨料3 */
	@Column(name = "THEORY_AGGREGATE_3", nullable = true, length = 50)
	private String theoryAggregate3;

	/** @Fields theoryAggregate4 : 理论骨料4 */
	@Column(name = "THEORY_AGGREGATE_4", nullable = true, length = 50)
	private String theoryAggregate4;

	/** @Fields theoryAggregate5 : 理论骨料5 */
	@Column(name = "THEORY_AGGREGATE_5", nullable = true, length = 50)
	private String theoryAggregate5;

	/** @Fields theoryPowder1 : 理论粉料1 */
	@Column(name = "THEORY_POWDER_1", nullable = true, length = 50)
	private String theoryPowder1;

	/** @Fields theoryPowder2 : 理论粉料2 */
	@Column(name = "THEORY_POWDER_2", nullable = true, length = 50)
	private String theoryPowder2;

	/** @Fields theoryWater : 理论水 */
	@Column(name = "THEORY_WATER", nullable = true, length = 50)
	private String theoryWater;

	/** @Fields aggregateRatio1 : 骨料比率1 */
	@Column(name = "AGGREGATE_RATIO_1", nullable = true, length = 50)
	private String aggregateRatio1;

	/** @Fields aggregateRatio2 : 骨料比率2 */
	@Column(name = "AGGREGATE_RATIO_2", nullable = true, length = 50)
	private String aggregateRatio2;

	/** @Fields aggregateRatio3 : 骨料比率3 */
	@Column(name = "AGGREGATE_RATIO_3", nullable = true, length = 50)
	private String aggregateRatio3;

	/** @Fields aggregateRatio4 : 骨料比率4 */
	@Column(name = "AGGREGATE_RATIO_4", nullable = true, length = 50)
	private String aggregateRatio4;

	/** @Fields aggregateRatio5 : 骨料比率5 */
	@Column(name = "AGGREGATE_RATIO_5", nullable = true, length = 50)
	private String aggregateRatio5;

	/** @Fields powderRatio1 : 粉料比率1 */
	@Column(name = "POWDER_RATIO_1", nullable = true, length = 50)
	private String powderRatio1;

	/** @Fields powderRatio2 : 粉料比率2 */
	@Column(name = "POWDER_RATIO_2", nullable = true, length = 50)
	private String powderRatio2;

	/** @Fields waterRatio : 水比率 */
	@Column(name = "WATER_RATIO", nullable = true, length = 50)
	private String waterRatio;

	/** @Fields saveDate : 保存时间 */
	@Column(name = "SAVE_DATE", nullable = true)
	private Date saveDate;

	/** @Fields gatherDate : 采集时间 */
	@Column(name = "GATHER_DATE", nullable = true)
	private Date gatherDate;

	/** @Fields isCalculatedGradation : 是否计算级配 */
	@Column(name = "IS_CALCULATED_GRADATION", nullable = true, length = 30)
	private String isCalculatedGradation;

	/** @Fields actualAggregateSieving1 : 实际骨料1筛分 */
	@Column(name = "ACTUAL_AGGREGATE_SIEVING_1", nullable = true, length = 50)
	private String actualAggregateSieving1;

	/** @Fields actualAggregateSieving2 : 实际骨料2筛分 */
	@Column(name = "ACTUAL_AGGREGATE_SIEVING_2", nullable = true, length = 50)
	private String actualAggregateSieving2;

	/** @Fields actualAggregateSieving3 : 实际骨料3筛分 */
	@Column(name = "ACTUAL_AGGREGATE_SIEVING_3", nullable = true, length = 50)
	private String actualAggregateSieving3;

	/** @Fields actualAggregateSieving4 : 实际骨料4筛分 */
	@Column(name = "ACTUAL_AGGREGATE_SIEVING_4", nullable = true, length = 50)
	private String actualAggregateSieving4;

	/** @Fields actualAggregateSieving5 : 实际骨料5筛分 */
	@Column(name = "ACTUAL_AGGREGATE_SIEVING_5", nullable = true, length = 50)
	private String actualAggregateSieving5;

	/** @Fields actualPowderSieving1 : 实际粉料1筛分 */
	@Column(name = "ACTUAL_POWDER_SIEVING_1", nullable = true, length = 50)
	private String actualPowderSieving1;

	/** @Fields actualPowderSieving2 : 实际粉料2筛分 */
	@Column(name = "ACTUAL_POWDER_SIEVING_2", nullable = true, length = 50)
	private String actualPowderSieving2;

	/** @Fields recipeCode : 配方号 */
	@Column(name = "RECIPE_CODE", nullable = true, length = 50)
	private String recipeCode;

	/** @Fields recipeName : 配方名称 */
	@Column(name = "RECIPE_NAME", nullable = true, length = 50)
	private String recipeName;

	/** @Fields backupField1 : 备用1 */
	@Column(name = "BACKUP_FIELD_1", nullable = true, length = 50)
	private String backupField1;

	/** @Fields backupField2 : 备用2 */
	@Column(name = "BACKUP_FIELD_2", nullable = true, length = 50)
	private String backupField2;

	/** @Fields backupField3 : 备用3 */
	@Column(name = "BACKUP_FIELD_3", nullable = true, length = 50)
	private String backupField3;

	/** @Fields ukey : 水泥品种 （生产数据唯一码,和2.3接口中的ukey是对应的） */
	@Column(name = "UKEY", nullable = true, length = 50)
	private String ukey;

	/** @Fields orgId : 被检测样品所属施工标段ID */
	@ManyToOne
	@JoinColumn(name="ORG_ID")
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

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传，2已传给质监局并做修改 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields appKey : 接口传数据标识符 */
	@Column(name = "APP_KEY", nullable = false, length = 100)
	private String appKey;

	/** @Fields isExcessive : 0:未超标, 1：已超标 */
	@Column(name = "IS_EXCESSIVE", nullable = false, length = 100)
	private String isExcessive;

	/** @Fields inspectionCode : 整改（快捷处理）编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	@OneToOne
	@JoinColumn(name = "WATER_STABLE_MIXING_STATION_EXCEED_ID")
	private BusiQualityWaterStableMixingStationExceed busiQualityWaterStableMixingStationExceed;

	public BusiQualityWaterStableMixingStationExceed getBusiQualityWaterStableMixingStationExceed() {
		return busiQualityWaterStableMixingStationExceed;
	}

	public void setBusiQualityWaterStableMixingStationExceed(
			BusiQualityWaterStableMixingStationExceed busiQualityWaterStableMixingStationExceed) {
		this.busiQualityWaterStableMixingStationExceed = busiQualityWaterStableMixingStationExceed;
	}

	public String getIsExcessive() {
		return isExcessive;
	}

	public void setIsExcessive(String isExcessive) {
		this.isExcessive = isExcessive;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setActualAggregate1(String actualAggregate1) {
		this.actualAggregate1 = actualAggregate1;
	}

	public String getActualAggregate1() {
		return this.actualAggregate1;
	}

	public void setActualAggregate2(String actualAggregate2) {
		this.actualAggregate2 = actualAggregate2;
	}

	public String getActualAggregate2() {
		return this.actualAggregate2;
	}

	public void setActualAggregate3(String actualAggregate3) {
		this.actualAggregate3 = actualAggregate3;
	}

	public String getActualAggregate3() {
		return this.actualAggregate3;
	}

	public void setActualAggregate4(String actualAggregate4) {
		this.actualAggregate4 = actualAggregate4;
	}

	public String getActualAggregate4() {
		return this.actualAggregate4;
	}

	public void setActualAggregate5(String actualAggregate5) {
		this.actualAggregate5 = actualAggregate5;
	}

	public String getActualAggregate5() {
		return this.actualAggregate5;
	}

	public void setActualPowder1(String actualPowder1) {
		this.actualPowder1 = actualPowder1;
	}

	public String getActualPowder1() {
		return this.actualPowder1;
	}

	public void setActualPowder2(String actualPowder2) {
		this.actualPowder2 = actualPowder2;
	}

	public String getActualPowder2() {
		return this.actualPowder2;
	}

	public void setActualWater(String actualWater) {
		this.actualWater = actualWater;
	}

	public String getActualWater() {
		return this.actualWater;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setRoughTotalOutput(String roughTotalOutput) {
		this.roughTotalOutput = roughTotalOutput;
	}

	public String getRoughTotalOutput() {
		return this.roughTotalOutput;
	}

	public void setTheoryAggregate1(String theoryAggregate1) {
		this.theoryAggregate1 = theoryAggregate1;
	}

	public String getTheoryAggregate1() {
		return this.theoryAggregate1;
	}

	public void setTheoryAggregate2(String theoryAggregate2) {
		this.theoryAggregate2 = theoryAggregate2;
	}

	public String getTheoryAggregate2() {
		return this.theoryAggregate2;
	}

	public void setTheoryAggregate3(String theoryAggregate3) {
		this.theoryAggregate3 = theoryAggregate3;
	}

	public String getTheoryAggregate3() {
		return this.theoryAggregate3;
	}

	public void setTheoryAggregate4(String theoryAggregate4) {
		this.theoryAggregate4 = theoryAggregate4;
	}

	public String getTheoryAggregate4() {
		return this.theoryAggregate4;
	}

	public void setTheoryAggregate5(String theoryAggregate5) {
		this.theoryAggregate5 = theoryAggregate5;
	}

	public String getTheoryAggregate5() {
		return this.theoryAggregate5;
	}

	public void setTheoryPowder1(String theoryPowder1) {
		this.theoryPowder1 = theoryPowder1;
	}

	public String getTheoryPowder1() {
		return this.theoryPowder1;
	}

	public void setTheoryPowder2(String theoryPowder2) {
		this.theoryPowder2 = theoryPowder2;
	}

	public String getTheoryPowder2() {
		return this.theoryPowder2;
	}

	public void setTheoryWater(String theoryWater) {
		this.theoryWater = theoryWater;
	}

	public String getTheoryWater() {
		return this.theoryWater;
	}

	public void setAggregateRatio1(String aggregateRatio1) {
		this.aggregateRatio1 = aggregateRatio1;
	}

	public String getAggregateRatio1() {
		return this.aggregateRatio1;
	}

	public void setAggregateRatio2(String aggregateRatio2) {
		this.aggregateRatio2 = aggregateRatio2;
	}

	public String getAggregateRatio2() {
		return this.aggregateRatio2;
	}

	public void setAggregateRatio3(String aggregateRatio3) {
		this.aggregateRatio3 = aggregateRatio3;
	}

	public String getAggregateRatio3() {
		return this.aggregateRatio3;
	}

	public void setAggregateRatio4(String aggregateRatio4) {
		this.aggregateRatio4 = aggregateRatio4;
	}

	public String getAggregateRatio4() {
		return this.aggregateRatio4;
	}

	public void setAggregateRatio5(String aggregateRatio5) {
		this.aggregateRatio5 = aggregateRatio5;
	}

	public String getAggregateRatio5() {
		return this.aggregateRatio5;
	}

	public void setPowderRatio1(String powderRatio1) {
		this.powderRatio1 = powderRatio1;
	}

	public String getPowderRatio1() {
		return this.powderRatio1;
	}

	public void setPowderRatio2(String powderRatio2) {
		this.powderRatio2 = powderRatio2;
	}

	public String getPowderRatio2() {
		return this.powderRatio2;
	}

	public void setWaterRatio(String waterRatio) {
		this.waterRatio = waterRatio;
	}

	public String getWaterRatio() {
		return this.waterRatio;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public Date getSaveDate() {
		return this.saveDate;
	}

	public void setGatherDate(Date gatherDate) {
		this.gatherDate = gatherDate;
	}

	public Date getGatherDate() {
		return this.gatherDate;
	}

	public void setIsCalculatedGradation(String isCalculatedGradation) {
		this.isCalculatedGradation = isCalculatedGradation;
	}

	public String getIsCalculatedGradation() {
		return this.isCalculatedGradation;
	}

	public void setActualAggregateSieving1(String actualAggregateSieving1) {
		this.actualAggregateSieving1 = actualAggregateSieving1;
	}

	public String getActualAggregateSieving1() {
		return this.actualAggregateSieving1;
	}

	public void setActualAggregateSieving2(String actualAggregateSieving2) {
		this.actualAggregateSieving2 = actualAggregateSieving2;
	}

	public String getActualAggregateSieving2() {
		return this.actualAggregateSieving2;
	}

	public void setActualAggregateSieving3(String actualAggregateSieving3) {
		this.actualAggregateSieving3 = actualAggregateSieving3;
	}

	public String getActualAggregateSieving3() {
		return this.actualAggregateSieving3;
	}

	public void setActualAggregateSieving4(String actualAggregateSieving4) {
		this.actualAggregateSieving4 = actualAggregateSieving4;
	}

	public String getActualAggregateSieving4() {
		return this.actualAggregateSieving4;
	}

	public void setActualAggregateSieving5(String actualAggregateSieving5) {
		this.actualAggregateSieving5 = actualAggregateSieving5;
	}

	public String getActualAggregateSieving5() {
		return this.actualAggregateSieving5;
	}

	public void setActualPowderSieving1(String actualPowderSieving1) {
		this.actualPowderSieving1 = actualPowderSieving1;
	}

	public String getActualPowderSieving1() {
		return this.actualPowderSieving1;
	}

	public void setActualPowderSieving2(String actualPowderSieving2) {
		this.actualPowderSieving2 = actualPowderSieving2;
	}

	public String getActualPowderSieving2() {
		return this.actualPowderSieving2;
	}

	public void setRecipeCode(String recipeCode) {
		this.recipeCode = recipeCode;
	}

	public String getRecipeCode() {
		return this.recipeCode;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeName() {
		return this.recipeName;
	}

	public void setBackupField1(String backupField1) {
		this.backupField1 = backupField1;
	}

	public String getBackupField1() {
		return this.backupField1;
	}

	public void setBackupField2(String backupField2) {
		this.backupField2 = backupField2;
	}

	public String getBackupField2() {
		return this.backupField2;
	}

	public void setBackupField3(String backupField3) {
		this.backupField3 = backupField3;
	}

	public String getBackupField3() {
		return this.backupField3;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}
	
	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
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

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
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

}
