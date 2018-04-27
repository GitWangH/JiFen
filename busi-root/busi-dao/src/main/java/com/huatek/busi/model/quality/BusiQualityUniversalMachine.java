package com.huatek.busi.model.quality;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 万能机实体类.
 * 
 * @ClassName: BusiQualityUniversalMachine
 * @Description:万能机实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-10-30 14:16:43
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_universal_machine")
public class BusiQualityUniversalMachine extends BaseEntity {

	private static final long serialVersionUID = 1L;

//	/** @Fields busiQualityUniversalPressMachineParentId : 压力、万能机外键id */
//	@OneToOne
//	@JoinColumn(name = "UNIVERSAL_PRESS_MACHINE_PARENT_ID", nullable = false)
//	private BusiQualityUniversalPressMachineParent busiQualityUniversalPressMachineParent;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "UNIVERSAL_MACHINE_ID", nullable = true)
	private Long id;

	/** @Fields testPersion : 试验人 */
	@Column(name = "TEST_PERSION", nullable = false, length = 100)
	private String testPersion;

	/** @Fields sampleNumber : 样品编号 */
	@Column(name = "SAMPLE_NUMBER", nullable = false, length = 100)
	private String sampleNumber;

	/** @Fields testTime : 试验时间 */
	@Column(name = "TEST_TIME", nullable = false, length = 30)
	private String testTime;

	/** @Fields testName : 试验名称 */
	@Column(name = "TEST_NAME", nullable = false, length = 100)
	private String testName;

	/** @Fields testType : 试验类型 */
	@Column(name = "TEST_TYPE", nullable = false, length = 100)
	private String testType;

	/** @Fields basicModulusOfElasticity : 基本弹性模量 */
	@Column(name = "BASIC_MODULUS_OF_ELASTICITY", nullable = false, length = 100)
	private String basicModulusOfElasticity;

	/** @Fields baseNonProportionalExtensionForce : 基本规定非比例延伸力 */
	@Column(name = "BASE_NON_PROPORTIONAL_EXTENSION_FORCE", nullable = false, length = 100)
	private String baseNonProportionalExtensionForce;

	/** @Fields baseNonProportionalExtension : 基本规定非比例延伸率 */
	@Column(name = "BASE_NON_PROPORTIONAL_EXTENSION", nullable = false, length = 100)
	private String baseNonProportionalExtension;

	/** @Fields basicProvisionsTotalElongation : 基本规定总延伸力 */
	@Column(name = "BASIC_PROVISIONS_TOTAL_ELONGATION", nullable = false, length = 100)
	private String basicProvisionsTotalElongation;

	/** @Fields basicProvisionsTotalElongationRate : 基本规定总延伸率 */
	@Column(name = "BASIC_PROVISIONS_TOTAL_ELONGATION_RATE", nullable = false, length = 100)
	private String basicProvisionsTotalElongationRate;

	/** @Fields yieldPointElongation : 屈服点延伸 */
	@Column(name = "YIELD_POINT_ELONGATION", nullable = false, length = 100)
	private String yieldPointElongation;

	/** @Fields upperYieldForce : 上屈服力 */
	@Column(name = "UPPER_YIELD_FORCE", nullable = false, length = 100)
	private String upperYieldForce;

	/** @Fields lowerYieldForce : 下屈服力 */
	@Column(name = "LOWER_YIELD_FORCE", nullable = false, length = 100)
	private String lowerYieldForce;

	/** @Fields extensometerGaugeDistance : 引伸计标距 */
	@Column(name = "EXTENSOMETER_GAUGE_DISTANCE", nullable = false, length = 100)
	private String extensometerGaugeDistance;

	/** @Fields originalGauge : 原始标距 */
	@Column(name = "ORIGINAL_GAUGE", nullable = false, length = 100)
	private String originalGauge;

	/** @Fields originalCrossSectionalArea : 原始横截面积 */
	@Column(name = "ORIGINAL_CROSS_SECTIONAL_AREA", nullable = false, length = 100)
	private String originalCrossSectionalArea;

	/** @Fields maximumForce : 最大力 */
	@Column(name = "MAXIMUM_FORCE", nullable = false, length = 100)
	private String maximumForce;

	/** @Fields maximumForceFmNonProportionalElongation : 最大力(Fm)非比例伸长 */
	@Column(name = "MAXIMUM_FORCE_FM_NON_PROPORTIONAL_ELONGATION", nullable = false, length = 100)
	private String maximumForceFmNonProportionalElongation;

	/** @Fields maximumForceFmTotalElongationDeltaLm : 最大力(Fm)总延伸(ΔLm) */
	@Column(name = "MAXIMUM_FORCE_FM_TOTAL_ELONGATION_DELTA_LM", nullable = false, length = 100)
	private String maximumForceFmTotalElongationDeltaLm;

	/** @Fields elasticModulus : 弹性模量(E) */
	@Column(name = "ELASTIC_MODULUS", nullable = false, length = 100)
	private String elasticModulus;

	/** @Fields brokenTheGauge : 断后标距(Lu) */
	@Column(name = "BROKEN_THE_GAUGE", nullable = false, length = 100)
	private String brokenTheGauge;

	/** @Fields brokenElongation : 断后伸长率(A) */
	@Column(name = "BROKEN_ELONGATION", nullable = false, length = 100)
	private String brokenElongation;

	/** @Fields brokenDiameter : 断后直径(du) */
	@Column(name = "BROKEN_DIAMETER", nullable = false, length = 100)
	private String brokenDiameter;

	/** @Fields brokenMinimumCrossSectionalArea : 断后最小横截面积(Su) */
	@Column(name = "BROKEN_MINIMUM_CROSS_SECTIONAL_AREA", nullable = false, length = 100)
	private String brokenMinimumCrossSectionalArea;

	/** @Fields sectionShrinkage : 断面收缩率(Z) */
	@Column(name = "SECTION_SHRINKAGE", nullable = false, length = 100)
	private String sectionShrinkage;

	/** @Fields prescribedNonProportionalExtensionForce : 规定非比例延伸力 */
	@Column(name = "PRESCRIBED_NON_PROPORTIONAL_EXTENSION_FORCE", nullable = false, length = 100)
	private String prescribedNonProportionalExtensionForce;

	/** @Fields prescribedNonProportionalExtensionStrength : 规定非比例延伸强度(Rp) */
	@Column(name = "PRESCRIBED_NON_PROPORTIONAL_EXTENSION_STRENGTH", nullable = false, length = 100)
	private String prescribedNonProportionalExtensionStrength;

	/** @Fields specifiedTotalElongation : 规定总延伸力 */
	@Column(name = "SPECIFIED_TOTAL_ELONGATION", nullable = false, length = 100)
	private String specifiedTotalElongation;

	/** @Fields specifiedTotalElongationStrength : 规定总延伸强度(Rt) */
	@Column(name = "SPECIFIED_TOTAL_ELONGATION_STRENGTH", nullable = false, length = 100)
	private String specifiedTotalElongationStrength;

	/** @Fields tensileStrength : 抗拉强度(Rm) */
	@Column(name = "TENSILE_STRENGTH", nullable = false, length = 100)
	private String tensileStrength;

	/** @Fields yieldPointElongationAe : 屈服点延伸率(Ae) */
	@Column(name = "YIELD_POINT_ELONGATION_AE", nullable = false, length = 100)
	private String yieldPointElongationAe;

	/** @Fields upperYieldForce1 : 上屈服力 */
	@Column(name = "UPPER_YIELD_FORCE_1", nullable = false, length = 100)
	private String upperYieldForce1;

	/** @Fields upperYieldStrength : 上屈服强度(ReH) */
	@Column(name = "UPPER_YIELD_STRENGTH", nullable = false, length = 100)
	private String upperYieldStrength;

	/** @Fields lowerYieldForce1 : 下屈服力 */
	@Column(name = "LOWER_YIELD_FORCE_1", nullable = false, length = 100)
	private String lowerYieldForce1;

	/** @Fields lowerYieldStrength : 下屈服强度(ReL) */
	@Column(name = "LOWER_YIELD_STRENGTH", nullable = false, length = 100)
	private String lowerYieldStrength;

	/** @Fields maximumForceFm : 最大力(Fm) */
	@Column(name = "MAXIMUM_FORCE_FM", nullable = false, length = 100)
	private String maximumForceFm;

	/** @Fields maximumForceNonProportionalProlongationRate : 最大力(Fm)非比例伸长率(Ag) */
	@Column(name = "MAXIMUM_FORCE_NON_PROPORTIONAL_PROLONGATION_RATE", nullable = false, length = 100)
	private String maximumForceNonProportionalProlongationRate;

	/** @Fields maximumForceTotalElongationRate : 最大力(Fm)总伸长率(Agt) */
	@Column(name = "MAXIMUM_FORCE_TOTAL_ELONGATION_RATE", nullable = false, length = 100)
	private String maximumForceTotalElongationRate;

	/** @Fields maximumForceTotalElongation : 最大力(Fm)总延伸(ΔLm) */
	@Column(name = "MAXIMUM_FORCE_TOTAL_ELONGATION", nullable = false, length = 100)
	private String maximumForceTotalElongation;

	/** @Fields maximumForceNonProportionalElongation : 最大力非比例伸长 */
	@Column(name = "MAXIMUM_FORCE_NON_PROPORTIONAL_ELONGATION", nullable = false, length = 100)
	private String maximumForceNonProportionalElongation;

	/** @Fields radius : 半径 */
	@Column(name = "RADIUS", nullable = false, length = 100)
	private String radius;

	/** @Fields radiusSquared : 半径平方 */
	@Column(name = "RADIUS_SQUARED", nullable = false, length = 100)
	private String radiusSquared;

	/** @Fields brokenRadius : 断后半径 */
	@Column(name = "BROKEN_RADIUS", nullable = false, length = 100)
	private String brokenRadius;

	/** @Fields brokenRadiusSquared : 断后半径平方 */
	@Column(name = "BROKEN_RADIUS_SQUARED", nullable = false, length = 100)
	private String brokenRadiusSquared;

	/** @Fields brokenPercentageElongation : 断后伸长 */
	@Column(name = "BROKEN_PERCENTAGE_ELONGATION", nullable = false, length = 100)
	private String brokenPercentageElongation;

	/** @Fields nominalDiameter : 公称直径(d) */
	@Column(name = "NOMINAL_DIAMETER", nullable = false, length = 100)
	private String nominalDiameter;

	/** @Fields prescribedNonProportionalElongation : 规定非比例延伸率(εp) */
	@Column(name = "PRESCRIBED_NON_PROPORTIONAL_ELONGATION", nullable = false, length = 100)
	private String prescribedNonProportionalElongation;

	/** @Fields specifiedTotalElongation1 : 规定总延伸率(εt) */
	@Column(name = "SPECIFIED_TOTAL_ELONGATION_1", nullable = false, length = 100)
	private String specifiedTotalElongation1;

	/** @Fields extensometerGaugeDistance1 : 引伸计标距(Le) */
	@Column(name = "EXTENSOMETER_GAUGE_DISTANCE_1", nullable = false, length = 100)
	private String extensometerGaugeDistance1;

	/** @Fields originalGaugeLo : 原始标距(Lo) */
	@Column(name = "ORIGINAL_GAUGE_LO", nullable = false, length = 100)
	private String originalGaugeLo;

	/** @Fields originalCrossSectionalAreaSo : 原始横截面积(So) */
	@Column(name = "ORIGINAL_CROSS_SECTIONAL_AREA_SO", nullable = false, length = 100)
	private String originalCrossSectionalAreaSo;

	/** @Fields processTimeUpper : 过程时间(多个用逗号隔开) ，上屈服力，以毫秒为单位 */
	@Column(name = "PROCESS_TIME_UPPER", nullable = false, length = 30)
	private String processTimeUpper;

	/** @Fields processForceValueUpper : 过程力值(多个用逗号隔开) ，上屈服力 */
	@Column(name = "PROCESS_FORCE_VALUE_UPPER", nullable = false, length = 100)
	private String processForceValueUpper;

	/** @Fields processTimeLower : 过程时间(多个用逗号隔开) ，下屈服力，以毫秒为单位 */
	@Column(name = "PROCESS_TIME_LOWER", nullable = false, length = 30)
	private String processTimeLower;

	/** @Fields processForceValueLower : 过程力值(多个用逗号隔开)，下屈服力 */
	@Column(name = "PROCESS_FORCE_VALUE_LOWER", nullable = false, length = 100)
	private String processForceValueLower;

	/** @Fields thirdSetsOfData : 第三组数据 */
	@Column(name = "THIRD_SETS_OF_DATA", nullable = false, length = 30)
	private String thirdSetsOfData;

	/** @Fields thirdSetsOfData1 : 第三组数据 */
	@Column(name = "THIRD_SETS_OF_DATA_1", nullable = false, length = 100)
	private String thirdSetsOfData1;

	/** @Fields status : 0:合格，1:不合格，2有效，:3:无效，4:其他 */
	@Column(name = "STATUS", nullable = false, length = 100)
	private String status;

	/** @Fields typeCode : 品种编码 */
	@Column(name = "TYPE_CODE", nullable = false, length = 100)
	private String typeCode;

	/** @Fields manufacturer : 生产厂家 */
	@Column(name = "MANUFACTURER", nullable = false, length = 100)
	private String manufacturer;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = false, length = 100)
	private String ukey;

	/** @Fields descUrl : 详细描述地址 */
	@Column(name = "DESC_URL", nullable = false, length = 100)
	private String descUrl;

	/** @Fields unqualifiedDescription : 不合格描述 */
	@Column(name = "UNQUALIFIED_DESCRIPTION", nullable = false, length = 100)
	private String unqualifiedDescription;

	/** @Fields factOrgId : 被检测样品所属施工标段ID */
	@Column(name = "FACT_ORG_ID", nullable = true)
	private Long factOrgId;

	/** @Fields orgId : 机构ID */
	@Column(name = "ORG_ID", nullable = true, length = 32)
	private Long orgId;

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

	/** @Fields inspectionCode : 整改（快捷处理）编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;

	/** @Fields disposeState : 处理状态（数据字典） */
	@Column(name = "DISPOSE_STATE", nullable = true, length = 30)
	private String disposeState;

//	public BusiQualityUniversalPressMachineParent getBusiQualityUniversalPressMachineParent() {
//		return busiQualityUniversalPressMachineParent;
//	}
//
//	public void setBusiQualityUniversalPressMachineParent(
//			BusiQualityUniversalPressMachineParent busiQualityUniversalPressMachineParent) {
//		this.busiQualityUniversalPressMachineParent = busiQualityUniversalPressMachineParent;
//	}

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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setTestPersion(String testPersion) {
		this.testPersion = testPersion;
	}

	public String getTestPersion() {
		return this.testPersion;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getSampleNumber() {
		return this.sampleNumber;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getTestTime() {
		return this.testTime;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestType() {
		return this.testType;
	}

	public void setBasicModulusOfElasticity(String basicModulusOfElasticity) {
		this.basicModulusOfElasticity = basicModulusOfElasticity;
	}

	public String getBasicModulusOfElasticity() {
		return this.basicModulusOfElasticity;
	}

	public void setBaseNonProportionalExtensionForce(
			String baseNonProportionalExtensionForce) {
		this.baseNonProportionalExtensionForce = baseNonProportionalExtensionForce;
	}

	public String getBaseNonProportionalExtensionForce() {
		return this.baseNonProportionalExtensionForce;
	}

	public void setBaseNonProportionalExtension(
			String baseNonProportionalExtension) {
		this.baseNonProportionalExtension = baseNonProportionalExtension;
	}

	public String getBaseNonProportionalExtension() {
		return this.baseNonProportionalExtension;
	}

	public void setBasicProvisionsTotalElongation(
			String basicProvisionsTotalElongation) {
		this.basicProvisionsTotalElongation = basicProvisionsTotalElongation;
	}

	public String getBasicProvisionsTotalElongation() {
		return this.basicProvisionsTotalElongation;
	}

	public void setBasicProvisionsTotalElongationRate(
			String basicProvisionsTotalElongationRate) {
		this.basicProvisionsTotalElongationRate = basicProvisionsTotalElongationRate;
	}

	public String getBasicProvisionsTotalElongationRate() {
		return this.basicProvisionsTotalElongationRate;
	}

	public void setYieldPointElongation(String yieldPointElongation) {
		this.yieldPointElongation = yieldPointElongation;
	}

	public String getYieldPointElongation() {
		return this.yieldPointElongation;
	}

	public void setUpperYieldForce(String upperYieldForce) {
		this.upperYieldForce = upperYieldForce;
	}

	public String getUpperYieldForce() {
		return this.upperYieldForce;
	}

	public void setLowerYieldForce(String lowerYieldForce) {
		this.lowerYieldForce = lowerYieldForce;
	}

	public String getLowerYieldForce() {
		return this.lowerYieldForce;
	}

	public void setExtensometerGaugeDistance(String extensometerGaugeDistance) {
		this.extensometerGaugeDistance = extensometerGaugeDistance;
	}

	public String getExtensometerGaugeDistance() {
		return this.extensometerGaugeDistance;
	}

	public void setOriginalGauge(String originalGauge) {
		this.originalGauge = originalGauge;
	}

	public String getOriginalGauge() {
		return this.originalGauge;
	}

	public void setOriginalCrossSectionalArea(String originalCrossSectionalArea) {
		this.originalCrossSectionalArea = originalCrossSectionalArea;
	}

	public String getOriginalCrossSectionalArea() {
		return this.originalCrossSectionalArea;
	}

	public void setMaximumForce(String maximumForce) {
		this.maximumForce = maximumForce;
	}

	public String getMaximumForce() {
		return this.maximumForce;
	}

	public void setMaximumForceFmNonProportionalElongation(
			String maximumForceFmNonProportionalElongation) {
		this.maximumForceFmNonProportionalElongation = maximumForceFmNonProportionalElongation;
	}

	public String getMaximumForceFmNonProportionalElongation() {
		return this.maximumForceFmNonProportionalElongation;
	}

	public void setMaximumForceFmTotalElongationDeltaLm(
			String maximumForceFmTotalElongationDeltaLm) {
		this.maximumForceFmTotalElongationDeltaLm = maximumForceFmTotalElongationDeltaLm;
	}

	public String getMaximumForceFmTotalElongationDeltaLm() {
		return this.maximumForceFmTotalElongationDeltaLm;
	}

	public void setElasticModulus(String elasticModulus) {
		this.elasticModulus = elasticModulus;
	}

	public String getElasticModulus() {
		return this.elasticModulus;
	}

	public void setBrokenTheGauge(String brokenTheGauge) {
		this.brokenTheGauge = brokenTheGauge;
	}

	public String getBrokenTheGauge() {
		return this.brokenTheGauge;
	}

	public void setBrokenElongation(String brokenElongation) {
		this.brokenElongation = brokenElongation;
	}

	public String getBrokenElongation() {
		return this.brokenElongation;
	}

	public void setBrokenDiameter(String brokenDiameter) {
		this.brokenDiameter = brokenDiameter;
	}

	public String getBrokenDiameter() {
		return this.brokenDiameter;
	}

	public void setBrokenMinimumCrossSectionalArea(
			String brokenMinimumCrossSectionalArea) {
		this.brokenMinimumCrossSectionalArea = brokenMinimumCrossSectionalArea;
	}

	public String getBrokenMinimumCrossSectionalArea() {
		return this.brokenMinimumCrossSectionalArea;
	}

	public void setSectionShrinkage(String sectionShrinkage) {
		this.sectionShrinkage = sectionShrinkage;
	}

	public String getSectionShrinkage() {
		return this.sectionShrinkage;
	}

	public void setPrescribedNonProportionalExtensionForce(
			String prescribedNonProportionalExtensionForce) {
		this.prescribedNonProportionalExtensionForce = prescribedNonProportionalExtensionForce;
	}

	public String getPrescribedNonProportionalExtensionForce() {
		return this.prescribedNonProportionalExtensionForce;
	}

	public void setPrescribedNonProportionalExtensionStrength(
			String prescribedNonProportionalExtensionStrength) {
		this.prescribedNonProportionalExtensionStrength = prescribedNonProportionalExtensionStrength;
	}

	public String getPrescribedNonProportionalExtensionStrength() {
		return this.prescribedNonProportionalExtensionStrength;
	}

	public void setSpecifiedTotalElongation(String specifiedTotalElongation) {
		this.specifiedTotalElongation = specifiedTotalElongation;
	}

	public String getSpecifiedTotalElongation() {
		return this.specifiedTotalElongation;
	}

	public void setSpecifiedTotalElongationStrength(
			String specifiedTotalElongationStrength) {
		this.specifiedTotalElongationStrength = specifiedTotalElongationStrength;
	}

	public String getSpecifiedTotalElongationStrength() {
		return this.specifiedTotalElongationStrength;
	}

	public void setTensileStrength(String tensileStrength) {
		this.tensileStrength = tensileStrength;
	}

	public String getTensileStrength() {
		return this.tensileStrength;
	}

	public void setYieldPointElongationAe(String yieldPointElongationAe) {
		this.yieldPointElongationAe = yieldPointElongationAe;
	}

	public String getYieldPointElongationAe() {
		return this.yieldPointElongationAe;
	}

	public void setUpperYieldForce1(String upperYieldForce1) {
		this.upperYieldForce1 = upperYieldForce1;
	}

	public String getUpperYieldForce1() {
		return this.upperYieldForce1;
	}

	public void setUpperYieldStrength(String upperYieldStrength) {
		this.upperYieldStrength = upperYieldStrength;
	}

	public String getUpperYieldStrength() {
		return this.upperYieldStrength;
	}

	public void setLowerYieldForce1(String lowerYieldForce1) {
		this.lowerYieldForce1 = lowerYieldForce1;
	}

	public String getLowerYieldForce1() {
		return this.lowerYieldForce1;
	}

	public void setLowerYieldStrength(String lowerYieldStrength) {
		this.lowerYieldStrength = lowerYieldStrength;
	}

	public String getLowerYieldStrength() {
		return this.lowerYieldStrength;
	}

	public void setMaximumForceFm(String maximumForceFm) {
		this.maximumForceFm = maximumForceFm;
	}

	public String getMaximumForceFm() {
		return this.maximumForceFm;
	}

	public void setMaximumForceNonProportionalProlongationRate(
			String maximumForceNonProportionalProlongationRate) {
		this.maximumForceNonProportionalProlongationRate = maximumForceNonProportionalProlongationRate;
	}

	public String getMaximumForceNonProportionalProlongationRate() {
		return this.maximumForceNonProportionalProlongationRate;
	}

	public void setMaximumForceTotalElongationRate(
			String maximumForceTotalElongationRate) {
		this.maximumForceTotalElongationRate = maximumForceTotalElongationRate;
	}

	public String getMaximumForceTotalElongationRate() {
		return this.maximumForceTotalElongationRate;
	}

	public void setMaximumForceTotalElongation(
			String maximumForceTotalElongation) {
		this.maximumForceTotalElongation = maximumForceTotalElongation;
	}

	public String getMaximumForceTotalElongation() {
		return this.maximumForceTotalElongation;
	}

	public void setMaximumForceNonProportionalElongation(
			String maximumForceNonProportionalElongation) {
		this.maximumForceNonProportionalElongation = maximumForceNonProportionalElongation;
	}

	public String getMaximumForceNonProportionalElongation() {
		return this.maximumForceNonProportionalElongation;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getRadius() {
		return this.radius;
	}

	public void setRadiusSquared(String radiusSquared) {
		this.radiusSquared = radiusSquared;
	}

	public String getRadiusSquared() {
		return this.radiusSquared;
	}

	public void setBrokenRadius(String brokenRadius) {
		this.brokenRadius = brokenRadius;
	}

	public String getBrokenRadius() {
		return this.brokenRadius;
	}

	public void setBrokenRadiusSquared(String brokenRadiusSquared) {
		this.brokenRadiusSquared = brokenRadiusSquared;
	}

	public String getBrokenRadiusSquared() {
		return this.brokenRadiusSquared;
	}

	public void setBrokenPercentageElongation(String brokenPercentageElongation) {
		this.brokenPercentageElongation = brokenPercentageElongation;
	}

	public String getBrokenPercentageElongation() {
		return this.brokenPercentageElongation;
	}

	public void setNominalDiameter(String nominalDiameter) {
		this.nominalDiameter = nominalDiameter;
	}

	public String getNominalDiameter() {
		return this.nominalDiameter;
	}

	public void setPrescribedNonProportionalElongation(
			String prescribedNonProportionalElongation) {
		this.prescribedNonProportionalElongation = prescribedNonProportionalElongation;
	}

	public String getPrescribedNonProportionalElongation() {
		return this.prescribedNonProportionalElongation;
	}

	public void setSpecifiedTotalElongation1(String specifiedTotalElongation1) {
		this.specifiedTotalElongation1 = specifiedTotalElongation1;
	}

	public String getSpecifiedTotalElongation1() {
		return this.specifiedTotalElongation1;
	}

	public void setExtensometerGaugeDistance1(String extensometerGaugeDistance1) {
		this.extensometerGaugeDistance1 = extensometerGaugeDistance1;
	}

	public String getExtensometerGaugeDistance1() {
		return this.extensometerGaugeDistance1;
	}

	public void setOriginalGaugeLo(String originalGaugeLo) {
		this.originalGaugeLo = originalGaugeLo;
	}

	public String getOriginalGaugeLo() {
		return this.originalGaugeLo;
	}

	public void setOriginalCrossSectionalAreaSo(
			String originalCrossSectionalAreaSo) {
		this.originalCrossSectionalAreaSo = originalCrossSectionalAreaSo;
	}

	public String getOriginalCrossSectionalAreaSo() {
		return this.originalCrossSectionalAreaSo;
	}

	public void setProcessTimeUpper(String processTimeUpper) {
		this.processTimeUpper = processTimeUpper;
	}

	public String getProcessTimeUpper() {
		return this.processTimeUpper;
	}

	public void setProcessForceValueUpper(String processForceValueUpper) {
		this.processForceValueUpper = processForceValueUpper;
	}

	public String getProcessForceValueUpper() {
		return this.processForceValueUpper;
	}

	public void setProcessTimeLower(String processTimeLower) {
		this.processTimeLower = processTimeLower;
	}

	public String getProcessTimeLower() {
		return this.processTimeLower;
	}

	public void setProcessForceValueLower(String processForceValueLower) {
		this.processForceValueLower = processForceValueLower;
	}

	public String getProcessForceValueLower() {
		return this.processForceValueLower;
	}

	public void setThirdSetsOfData(String thirdSetsOfData) {
		this.thirdSetsOfData = thirdSetsOfData;
	}

	public String getThirdSetsOfData() {
		return this.thirdSetsOfData;
	}

	public void setThirdSetsOfData1(String thirdSetsOfData1) {
		this.thirdSetsOfData1 = thirdSetsOfData1;
	}

	public String getThirdSetsOfData1() {
		return this.thirdSetsOfData1;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}

	public String getDescUrl() {
		return this.descUrl;
	}

	public void setUnqualifiedDescription(String unqualifiedDescription) {
		this.unqualifiedDescription = unqualifiedDescription;
	}

	public String getUnqualifiedDescription() {
		return this.unqualifiedDescription;
	}

	public void setFactOrgId(Long factOrgId) {
		this.factOrgId = factOrgId;
	}

	public Long getFactOrgId() {
		return this.factOrgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return this.orgId;
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

}
