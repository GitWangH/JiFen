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

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 沥青拌合站检测实体类.
 * 
 * @ClassName: BusiQualityAsphaltMixingPlantInspection
 * @Description:沥青拌合站检测实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-04 14:03:49
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_asphalt_mixing_plant_inspection")
public class BusiQualityAsphaltMixingPlantInspection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "ASPHALT_MIXING_PLANT_EXCEED_ID")
	private BusiQualityAsphaltMixingPlantExceed busiQualityAsphaltMixingPlantExceed;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "ASPHALT_MIXING_PLANT_INSPECTION_ID", nullable = true)
	private Long id;

	/** @Fields originalId : 原始id */
	@Column(name = "ORIGINAL_ID", nullable = false)
	private Long originalId;

	/** @Fields mixingDate : 搅拌时间 */
	@Column(name = "MIXING_DATE", nullable = false)
	private Date mixingDate;

	/** @Fields user : 用户 */
	@Column(name = "USER", nullable = false, length = 100)
	private String user;

	/** @Fields formula : 配方 */
	@Column(name = "FORMULA", nullable = false, length = 100)
	private String formula;

	/** @Fields formulaName : 配方名称 */
	@Column(name = "FORMULA_NAME", nullable = false, length = 100)
	private String formulaName;

	/** @Fields asphaltTemperature : 沥青温度 */
	@Column(name = "ASPHALT_TEMPERATURE", nullable = false, length = 100)
	private String asphaltTemperature;

	/** @Fields aggregateTemperature : 骨料温度 */
	@Column(name = "AGGREGATE_TEMPERATURE", nullable = false, length = 100)
	private String aggregateTemperature;

	/** @Fields totalOutput : 总产量 */
	@Column(name = "TOTAL_OUTPUT", nullable = false, length = 100)
	private String totalOutput;

	/** @Fields actualWhetstoneRatio : 实际油石比 */
	@Column(name = "ACTUAL_WHETSTONE_RATIO", nullable = false, length = 100)
	private String actualWhetstoneRatio;

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

	/** @Fields actualAggregate6 : 实际骨料6 */
	@Column(name = "ACTUAL_AGGREGATE_6", nullable = false, length = 100)
	private String actualAggregate6;

	/** @Fields actualAggregate7 : 实际骨料7 */
	@Column(name = "ACTUAL_AGGREGATE_7", nullable = false, length = 100)
	private String actualAggregate7;

	/** @Fields actualPowder1 : 实际粉料1 */
	@Column(name = "ACTUAL_POWDER_1", nullable = false, length = 100)
	private String actualPowder1;

	/** @Fields actualPowder2 : 实际粉料2 */
	@Column(name = "ACTUAL_POWDER_2", nullable = false, length = 100)
	private String actualPowder2;

	/** @Fields actualAsphalt : 实际沥青 */
	@Column(name = "ACTUAL_ASPHALT", nullable = false, length = 100)
	private String actualAsphalt;

	/** @Fields actualAdditive : 实际添加剂 */
	@Column(name = "ACTUAL_ADDITIVE", nullable = false, length = 100)
	private String actualAdditive;

	/** @Fields theoryWhetstoneRatio : 理论油石比 */
	@Column(name = "THEORY_WHETSTONE_RATIO", nullable = false, length = 100)
	private String theoryWhetstoneRatio;

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

	/** @Fields theoryAggregate6 : 理论骨料6 */
	@Column(name = "THEORY_AGGREGATE_6", nullable = false, length = 100)
	private String theoryAggregate6;

	/** @Fields theoryAggregate7 : 理论骨料7 */
	@Column(name = "THEORY_AGGREGATE_7", nullable = false, length = 100)
	private String theoryAggregate7;

	/** @Fields theoryPowder1 : 理论粉料1 */
	@Column(name = "THEORY_POWDER_1", nullable = true, length = 50)
	private String theoryPowder1;

	/** @Fields theoryPowder2 : 理论粉料2 */
	@Column(name = "THEORY_POWDER_2", nullable = true, length = 50)
	private String theoryPowder2;

	/** @Fields theoryAsphalt : 理论沥青 */
	@Column(name = "THEORY_ASPHALT", nullable = false, length = 100)
	private String theoryAsphalt;

	/** @Fields theoryAdditive : 理论添加剂 */
	@Column(name = "THEORY_ADDITIVE", nullable = false, length = 100)
	private String theoryAdditive;

	/** @Fields dischargeTemperature : 出料温度 */
	@Column(name = "DISCHARGE_TEMPERATURE", nullable = false, length = 100)
	private String dischargeTemperature;

	/** @Fields customerName : 客户名称 */
	@Column(name = "CUSTOMER_NAME", nullable = false, length = 100)
	private String customerName;

	/** @Fields engineeringName : 工程名称 */
	@Column(name = "ENGINEERING_NAME", nullable = false, length = 100)
	private String engineeringName;

	/** @Fields constructionPosition : 施工部位 */
	@Column(name = "CONSTRUCTION_POSITION", nullable = false, length = 100)
	private String constructionPosition;

	/** @Fields cumulativeAmount : 累计数量 */
	@Column(name = "CUMULATIVE_AMOUNT", nullable = false, length = 100)
	private String cumulativeAmount;

	/** @Fields backupField1 : 备用1 */
	@Column(name = "BACKUP_FIELD_1", nullable = true, length = 50)
	private String backupField1;

	/** @Fields backupField2 : 备用2 */
	@Column(name = "BACKUP_FIELD_2", nullable = true, length = 50)
	private String backupField2;

	/** @Fields backupField3 : 备用3 */
	@Column(name = "BACKUP_FIELD_3", nullable = true, length = 50)
	private String backupField3;

	/** @Fields saveDate : 保存时间 */
	@Column(name = "SAVE_DATE", nullable = true)
	private Date saveDate;

	/** @Fields gatherDate : 采集时间 */
	@Column(name = "GATHER_DATE", nullable = true)
	private Date gatherDate;

	/** @Fields ukey : 生产数据唯一码与2.5接口中的ukey对应 */
	@Column(name = "UKEY", nullable = false, length = 100)
	private String ukey;

	/** @Fields orgId : */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg org;

	/** @Fields isUpdate : 0:新增 1：更新 */
	@Column(name = "IS_UPDATE", nullable = false, length = 1)
	private String isUpdate;

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

	public BusiQualityAsphaltMixingPlantExceed getBusiQualityAsphaltMixingPlantExceed() {
		return busiQualityAsphaltMixingPlantExceed;
	}

	public void setBusiQualityAsphaltMixingPlantExceed(
			BusiQualityAsphaltMixingPlantExceed busiQualityAsphaltMixingPlantExceed) {
		this.busiQualityAsphaltMixingPlantExceed = busiQualityAsphaltMixingPlantExceed;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
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

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public Long getOriginalId() {
		return this.originalId;
	}

	public void setMixingDate(Date mixingDate) {
		this.mixingDate = mixingDate;
	}

	public Date getMixingDate() {
		return this.mixingDate;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getFormulaName() {
		return this.formulaName;
	}

	public void setAsphaltTemperature(String asphaltTemperature) {
		this.asphaltTemperature = asphaltTemperature;
	}

	public String getAsphaltTemperature() {
		return this.asphaltTemperature;
	}

	public void setAggregateTemperature(String aggregateTemperature) {
		this.aggregateTemperature = aggregateTemperature;
	}

	public String getAggregateTemperature() {
		return this.aggregateTemperature;
	}

	public void setTotalOutput(String totalOutput) {
		this.totalOutput = totalOutput;
	}

	public String getTotalOutput() {
		return this.totalOutput;
	}

	public void setActualWhetstoneRatio(String actualWhetstoneRatio) {
		this.actualWhetstoneRatio = actualWhetstoneRatio;
	}

	public String getActualWhetstoneRatio() {
		return this.actualWhetstoneRatio;
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

	public void setActualAggregate6(String actualAggregate6) {
		this.actualAggregate6 = actualAggregate6;
	}

	public String getActualAggregate6() {
		return this.actualAggregate6;
	}

	public void setActualAggregate7(String actualAggregate7) {
		this.actualAggregate7 = actualAggregate7;
	}

	public String getActualAggregate7() {
		return this.actualAggregate7;
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

	public void setActualAsphalt(String actualAsphalt) {
		this.actualAsphalt = actualAsphalt;
	}

	public String getActualAsphalt() {
		return this.actualAsphalt;
	}

	public void setActualAdditive(String actualAdditive) {
		this.actualAdditive = actualAdditive;
	}

	public String getActualAdditive() {
		return this.actualAdditive;
	}

	public void setTheoryWhetstoneRatio(String theoryWhetstoneRatio) {
		this.theoryWhetstoneRatio = theoryWhetstoneRatio;
	}

	public String getTheoryWhetstoneRatio() {
		return this.theoryWhetstoneRatio;
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

	public void setTheoryAggregate6(String theoryAggregate6) {
		this.theoryAggregate6 = theoryAggregate6;
	}

	public String getTheoryAggregate6() {
		return this.theoryAggregate6;
	}

	public void setTheoryAggregate7(String theoryAggregate7) {
		this.theoryAggregate7 = theoryAggregate7;
	}

	public String getTheoryAggregate7() {
		return this.theoryAggregate7;
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

	public void setTheoryAsphalt(String theoryAsphalt) {
		this.theoryAsphalt = theoryAsphalt;
	}

	public String getTheoryAsphalt() {
		return this.theoryAsphalt;
	}

	public void setTheoryAdditive(String theoryAdditive) {
		this.theoryAdditive = theoryAdditive;
	}

	public String getTheoryAdditive() {
		return this.theoryAdditive;
	}

	public void setDischargeTemperature(String dischargeTemperature) {
		this.dischargeTemperature = dischargeTemperature;
	}

	public String getDischargeTemperature() {
		return this.dischargeTemperature;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getEngineeringName() {
		return this.engineeringName;
	}

	public void setConstructionPosition(String constructionPosition) {
		this.constructionPosition = constructionPosition;
	}

	public String getConstructionPosition() {
		return this.constructionPosition;
	}

	public void setCumulativeAmount(String cumulativeAmount) {
		this.cumulativeAmount = cumulativeAmount;
	}

	public String getCumulativeAmount() {
		return this.cumulativeAmount;
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

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsUpdate() {
		return this.isUpdate;
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

}
