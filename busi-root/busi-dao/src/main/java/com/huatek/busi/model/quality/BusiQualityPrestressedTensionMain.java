package com.huatek.busi.model.quality;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 预应力张拉、压浆检测实体类.
 * 
 * @ClassName: BusiQualityPrestressedTensionMain
 * @Description:预应力张拉、压浆检测实体类.
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-06 10:38:54
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_prestressed_tension_main")
public class BusiQualityPrestressedTensionMain extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "TENSION_MAIN_ID", nullable = true)
	private Long id;

	/** @Fields engineeringName : 工程名称 */
	@Column(name = "ENGINEERING_NAME", nullable = false, length = 50)
	private String engineeringName;

	/** @Fields bridgeName : 桥梁名称 */
	@Column(name = "BRIDGE_NAME", nullable = false, length = 50)
	private String bridgeName;

	/** @Fields beamNumber : 梁号 */
	@Column(name = "BEAM_NUMBER", nullable = false, length = 50)
	private String beamNumber;

	/** @Fields channelNumber : 孔道号 */
	@Column(name = "CHANNEL_NUMBER", nullable = false, length = 50)
	private String channelNumber;

	/** @Fields startTime : 开始时间 */
	@Column(name = "START_TIME", nullable = false)
	private Date startTime;

	/** @Fields recordTime : 记录时间 */
	@Column(name = "RECORD_TIME", nullable = false)
	private Date recordTime;

	/** @Fields tensionAmount : 张拉次数 */
	@Column(name = "TENSION_AMOUNT", nullable = false, precision = 18, scale = 2)
	private BigDecimal tensionAmount;

	/** @Fields gradedTension : 分级张拉0：表示不进 行分级张拉1：表示进行分级张拉 */
	@Column(name = "GRADED_TENSION", nullable = false)
	private Integer gradedTension;

	/** @Fields theoryElongation : 理论伸长量 */
	@Column(name = "THEORY_ELONGATION", nullable = false, precision = 18, scale = 2)
	private BigDecimal theoryElongation;

	/** @Fields actualElongation : 实际伸长量 */
	@Column(name = "ACTUAL_ELONGATION", nullable = false, precision = 18, scale = 2)
	private BigDecimal actualElongation;

	/** @Fields deviationElongation : 误差伸长量 */
	@Column(name = "DEVIATION_ELONGATION", nullable = false, precision = 18, scale = 2)
	private BigDecimal deviationElongation;

	/** @Fields deviationElongationRate : 误差率 */
	@Column(name = "DEVIATION_ELONGATION_RATE", nullable = false, precision = 18, scale = 2)
	private BigDecimal deviationElongationRate;

	/** @Fields elongationState : 伸长量状态是否合格(0:不合格；1:合格) */
	@Column(name = "ELONGATION_STATE", nullable = false, length = 30)
	private String elongationState;

	/** @Fields theoryTension : 理论张拉力 */
	@Column(name = "THEORY_TENSION", nullable = false, length = 100)
	private String theoryTension;

	/** @Fields actualTension : 实际张拉力 */
	@Column(name = "ACTUAL_TENSION", nullable = false, length = 100)
	private String actualTension;

	/** @Fields deviationTension : 张拉力误差 */
	@Column(name = "DEVIATION_TENSION", nullable = false, length = 100)
	private String deviationTension;

	/** @Fields deviationTensionRate : 超长率 */
	@Column(name = "DEVIATION_TENSION_RATE", nullable = false, length = 100)
	private String deviationTensionRate;

	/** @Fields tensionState : 张拉力状态是否合格(0:不合格；1:合格) */
	@Column(name = "TENSION_STATE", nullable = false, length = 100)
	private String tensionState;

	/** @Fields theoryStress : 理论应力 */
	@Column(name = "THEORY_STRESS", nullable = false, length = 100)
	private String theoryStress;

	/** @Fields actualStress : 实际应力 */
	@Column(name = "ACTUAL_STRESS", nullable = false, length = 100)
	private String actualStress;

	/** @Fields deviationStress : 应力误差 */
	@Column(name = "DEVIATION_STRESS", nullable = false, length = 100)
	private String deviationStress;

	/** @Fields deviationStressRate : 超长率 */
	@Column(name = "DEVIATION_STRESS_RATE", nullable = false, length = 100)
	private String deviationStressRate;

	/** @Fields stressState : 应力状态是否合格(0:不合格；1:合格) */
	@Column(name = "STRESS_STATE", nullable = false, length = 100)
	private String stressState;

	/** @Fields jackNo1 : 1号千斤顶编号 */
	@Column(name = "JACK_NO_1", nullable = false, length = 100)
	private String jackNo1;

	/** @Fields hydraulicPumpNo1 : 1号油压泵编号 */
	@Column(name = "HYDRAULIC_PUMP_NO_1", nullable = false, length = 100)
	private String hydraulicPumpNo1;

	/** @Fields oilPressureGaugeNo1 : 1号油压表编号 */
	@Column(name = "OIL_PRESSURE_GAUGE_NO_1", nullable = false, length = 100)
	private String oilPressureGaugeNo1;

	/** @Fields jackNo2 : 2号千斤顶编号 */
	@Column(name = "JACK_NO_2", nullable = false, length = 100)
	private String jackNo2;

	/** @Fields hydraulicPumpNo2 : 2号油压泵编号 */
	@Column(name = "HYDRAULIC_PUMP_NO_2", nullable = false, length = 100)
	private String hydraulicPumpNo2;

	/** @Fields oilPressureGaugeNo2 : 2号油压表编号 */
	@Column(name = "OIL_PRESSURE_GAUGE_NO_2", nullable = false, length = 100)
	private String oilPressureGaugeNo2;

	/** @Fields coefficientA1 : 1号系数A */
	@Column(name = "COEFFICIENT_A_1", nullable = false, length = 100)
	private String coefficientA1;

	/** @Fields coefficientB1 : 1号系数B */
	@Column(name = "COEFFICIENT_B_1", nullable = false, length = 100)
	private String coefficientB1;

	/** @Fields coefficientA2 : 2号系数A */
	@Column(name = "COEFFICIENT_A_2", nullable = false, length = 100)
	private String coefficientA2;

	/** @Fields coefficientB2 : 2号系数B */
	@Column(name = "COEFFICIENT_B_2", nullable = false, length = 100)
	private String coefficientB2;

	/** @Fields steelStrandLength : 钢绞线长度 */
	@Column(name = "STEEL_STRAND_LENGTH", nullable = false, length = 100)
	private String steelStrandLength;

	/** @Fields overstepTensionPercentage : 超张拉力百分比0~5,0表示无超张拉 */
	@Column(name = "OVERSTEP_TENSION_PERCENTAGE", nullable = false, length = 100)
	private String overstepTensionPercentage;

	/** @Fields tensionType : 构件名称(张拉方式) */
	@Column(name = "TENSION_TYPE", nullable = false, length = 100)
	private String tensionType;

	/** @Fields steelBeamNo : 钢束编号1 */
	@Column(name = "STEEL_BEAM_NO", nullable = false, length = 100)
	private String steelBeamNo;

	/** @Fields tensionOrder : 张拉顺序1 无符号字符型：~99. */
	@Column(name = "TENSION_ORDER", nullable = false, length = 100)
	private String tensionOrder;

	/** @Fields concreteDesignStrength : 砼设计强度2 无符号整形，定点两位小数XX.xx Mpa */
	@Column(name = "CONCRETE_DESIGN_STRENGTH", nullable = false, length = 100)
	private String concreteDesignStrength;

	/** @Fields concreteStrength : 砼强度2 无符号整形，定点两位小数XX.xx Mpa */
	@Column(name = "CONCRETE_STRENGTH", nullable = false, length = 100)
	private String concreteStrength;

	/** @Fields dataPackageCount : 数据包数目测试记录的数据包数目~ */
	@Column(name = "DATA_PACKAGE_COUNT", nullable = false, length = 100)
	private String dataPackageCount;

	/** @Fields elongationCorrectCoefficient1 : 1号伸长量修正系数 */
	@Column(name = "ELONGATION_CORRECT_COEFFICIENT_1", nullable = false, length = 100)
	private String elongationCorrectCoefficient1;

	/** @Fields elongationCorrectCoefficient2 : 2号伸长量修正系数 */
	@Column(name = "ELONGATION_CORRECT_COEFFICIENT_2", nullable = false, length = 100)
	private String elongationCorrectCoefficient2;

	/** @Fields shrinkage1 : 1号回缩量 */
	@Column(name = "SHRINKAGE_1", nullable = false, length = 100)
	private String shrinkage1;

	/** @Fields shrinkage2 : 2号回缩量 */
	@Column(name = "SHRINKAGE_2", nullable = false, length = 100)
	private String shrinkage2;

	/** @Fields recordCount : 记录的点数 */
	@Column(name = "RECORD_COUNT", nullable = false)
	private Integer recordCount;

	/** @Fields tensionTotalCount : 张拉结果次数 */
	@Column(name = "TENSION_TOTAL_COUNT", nullable = false)
	private Integer tensionTotalCount;

	/** @Fields elongation1 : 1号伸长量 */
	@Column(name = "ELONGATION_1", nullable = false, length = 100)
	private String elongation1;

	/** @Fields elongation2 : 2号伸长量 */
	@Column(name = "ELONGATION_2", nullable = false, length = 100)
	private String elongation2;

	/** @Fields oilPressureGaugePressure1 : 1号油表压力 */
	@Column(name = "OIL_PRESSURE_GAUGE_PRESSURE_1", nullable = false, length = 100)
	private String oilPressureGaugePressure1;

	/** @Fields oilPressureGaugePressure2 : 2号油表压力 */
	@Column(name = "OIL_PRESSURE_GAUGE_PRESSURE_2", nullable = false, length = 100)
	private String oilPressureGaugePressure2;

	/** @Fields topTension1 : 1号顶张拉力 */
	@Column(name = "TOP_TENSION_1", nullable = false, length = 100)
	private String topTension1;

	/** @Fields topTension2 : 2号顶张拉力 */
	@Column(name = "TOP_TENSION_2", nullable = false, length = 100)
	private String topTension2;

	/** @Fields holdingTime : 持荷时间 */
	@Column(name = "HOLDING_TIME", nullable = false, length = 100)
	private String holdingTime;

	/** @Fields smsStatusCode : 短信状态码 */
	@Column(name = "SMS_STATUS_CODE", nullable = false, length = 100)
	private String smsStatusCode;

	/** @Fields gatherTime : 采集时间 */
	@Column(name = "GATHER_TIME", nullable = false)
	private Date gatherTime;

	/** @Fields remarks : 备注 */
	@Column(name = "REMARKS", nullable = false, length = 100)
	private String remarks;

	/** @Fields backupField1 : 备用字段1 */
	@Column(name = "BACKUP_FIELD_1", nullable = false, length = 100)
	private String backupField1;

	/** @Fields backupField2 : 备用字段2 */
	@Column(name = "BACKUP_FIELD_2", nullable = false, length = 100)
	private String backupField2;

	/** @Fields backupField3 : 备用字段3 */
	@Column(name = "BACKUP_FIELD_3", nullable = false, length = 100)
	private String backupField3;

	/** @Fields backupField4 : 备用字段4 */
	@Column(name = "BACKUP_FIELD_4", nullable = false, length = 100)
	private String backupField4;

	/** @Fields backupField5 : 备用字段5 */
	@Column(name = "BACKUP_FIELD_5", nullable = false, length = 100)
	private String backupField5;

	/** @Fields backupField6 : 备用字段6 */
	@Column(name = "BACKUP_FIELD_6", nullable = false, length = 100)
	private String backupField6;

	/** @Fields backupField7 : 备用字段7 */
	@Column(name = "BACKUP_FIELD_7", nullable = false, length = 100)
	private String backupField7;

	/** @Fields backupField8 : 备用字段8 */
	@Column(name = "BACKUP_FIELD_8", nullable = false, length = 100)
	private String backupField8;

	/** @Fields reportAddress : 报告地址 */
	@Column(name = "REPORT_ADDRESS", nullable = false, length = 100)
	private String reportAddress;

	/** @Fields unqualifiedDescription : 不合格描述 */
	@Column(name = "UNQUALIFIED_DESCRIPTION", nullable = false, length = 100)
	private String unqualifiedDescription;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = false, length = 100)
	private String ukey;

	/** @Fields disposeTime : 处理时间 */
	@Column(name = "DISPOSE_TIME", nullable = false)
	private Date disposeTime;

	/** @Fields disposeStatus : 处理状态 */
	@Column(name = "DISPOSE_STATUS", nullable = false, length = 30)
	private String disposeStatus;

	/** @Fields orgId : 机构ID */
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

	/** @Fields originalId : 原始id */
	@Column(name = "ORIGINAL_ID", nullable = false)
	private Long originalId;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields inspectionCode : 整改（快捷处理）编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;

	/** @Fields isQualified : 0:合格，1:不合格，2有效，:3:无效，4:其他 */
	@Column(name = "IS_QUALIFIED", nullable = false)
	private Integer isQualified;

	/** @Fields modifer : 修改人ID */
	@Column(name= "MODIFER", nullable = false)
    private Long modifer;
    
    
	/** @Fields modifierName : 修改人姓名*/ 
	@Column(name= "MODIFIER_NAME", nullable = false, length=100 )
    private String modifierName;
    
    
	/** @Fields modifyTime : 修改时间 */
	@Column(name= "MODIFY_TIME", nullable = false)
    private Date modifyTime;
	
	public Integer getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Integer isQualified) {
		this.isQualified = isQualified;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getEngineeringName() {
		return this.engineeringName;
	}

	public void setBridgeName(String bridgeName) {
		this.bridgeName = bridgeName;
	}

	public String getBridgeName() {
		return this.bridgeName;
	}

	public void setBeamNumber(String beamNumber) {
		this.beamNumber = beamNumber;
	}

	public String getBeamNumber() {
		return this.beamNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getChannelNumber() {
		return this.channelNumber;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setTensionAmount(BigDecimal tensionAmount) {
		this.tensionAmount = tensionAmount;
	}

	public BigDecimal getTensionAmount() {
		return this.tensionAmount;
	}

	public void setGradedTension(Integer gradedTension) {
		this.gradedTension = gradedTension;
	}

	public Integer getGradedTension() {
		return this.gradedTension;
	}

	public void setTheoryElongation(BigDecimal theoryElongation) {
		this.theoryElongation = theoryElongation;
	}

	public BigDecimal getTheoryElongation() {
		return this.theoryElongation;
	}

	public void setActualElongation(BigDecimal actualElongation) {
		this.actualElongation = actualElongation;
	}

	public BigDecimal getActualElongation() {
		return this.actualElongation;
	}

	public void setDeviationElongation(BigDecimal deviationElongation) {
		this.deviationElongation = deviationElongation;
	}

	public BigDecimal getDeviationElongation() {
		return this.deviationElongation;
	}

	public void setDeviationElongationRate(BigDecimal deviationElongationRate) {
		this.deviationElongationRate = deviationElongationRate;
	}

	public BigDecimal getDeviationElongationRate() {
		return this.deviationElongationRate;
	}

	public void setElongationState(String elongationState) {
		this.elongationState = elongationState;
	}

	public String getElongationState() {
		return this.elongationState;
	}

	public void setTheoryTension(String theoryTension) {
		this.theoryTension = theoryTension;
	}

	public String getTheoryTension() {
		return this.theoryTension;
	}

	public void setActualTension(String actualTension) {
		this.actualTension = actualTension;
	}

	public String getActualTension() {
		return this.actualTension;
	}

	public void setDeviationTension(String deviationTension) {
		this.deviationTension = deviationTension;
	}

	public String getDeviationTension() {
		return this.deviationTension;
	}

	public void setDeviationTensionRate(String deviationTensionRate) {
		this.deviationTensionRate = deviationTensionRate;
	}

	public String getDeviationTensionRate() {
		return this.deviationTensionRate;
	}

	public void setTensionState(String tensionState) {
		this.tensionState = tensionState;
	}

	public String getTensionState() {
		return this.tensionState;
	}

	public void setTheoryStress(String theoryStress) {
		this.theoryStress = theoryStress;
	}

	public String getTheoryStress() {
		return this.theoryStress;
	}

	public void setActualStress(String actualStress) {
		this.actualStress = actualStress;
	}

	public String getActualStress() {
		return this.actualStress;
	}

	public void setDeviationStress(String deviationStress) {
		this.deviationStress = deviationStress;
	}

	public String getDeviationStress() {
		return this.deviationStress;
	}

	public void setDeviationStressRate(String deviationStressRate) {
		this.deviationStressRate = deviationStressRate;
	}

	public String getDeviationStressRate() {
		return this.deviationStressRate;
	}

	public void setStressState(String stressState) {
		this.stressState = stressState;
	}

	public String getStressState() {
		return this.stressState;
	}

	public void setJackNo1(String jackNo1) {
		this.jackNo1 = jackNo1;
	}

	public String getJackNo1() {
		return this.jackNo1;
	}

	public void setHydraulicPumpNo1(String hydraulicPumpNo1) {
		this.hydraulicPumpNo1 = hydraulicPumpNo1;
	}

	public String getHydraulicPumpNo1() {
		return this.hydraulicPumpNo1;
	}

	public void setOilPressureGaugeNo1(String oilPressureGaugeNo1) {
		this.oilPressureGaugeNo1 = oilPressureGaugeNo1;
	}

	public String getOilPressureGaugeNo1() {
		return this.oilPressureGaugeNo1;
	}

	public void setJackNo2(String jackNo2) {
		this.jackNo2 = jackNo2;
	}

	public String getJackNo2() {
		return this.jackNo2;
	}

	public void setHydraulicPumpNo2(String hydraulicPumpNo2) {
		this.hydraulicPumpNo2 = hydraulicPumpNo2;
	}

	public String getHydraulicPumpNo2() {
		return this.hydraulicPumpNo2;
	}

	public void setOilPressureGaugeNo2(String oilPressureGaugeNo2) {
		this.oilPressureGaugeNo2 = oilPressureGaugeNo2;
	}

	public String getOilPressureGaugeNo2() {
		return this.oilPressureGaugeNo2;
	}

	public void setCoefficientA1(String coefficientA1) {
		this.coefficientA1 = coefficientA1;
	}

	public String getCoefficientA1() {
		return this.coefficientA1;
	}

	public void setCoefficientB1(String coefficientB1) {
		this.coefficientB1 = coefficientB1;
	}

	public String getCoefficientB1() {
		return this.coefficientB1;
	}

	public void setCoefficientA2(String coefficientA2) {
		this.coefficientA2 = coefficientA2;
	}

	public String getCoefficientA2() {
		return this.coefficientA2;
	}

	public void setCoefficientB2(String coefficientB2) {
		this.coefficientB2 = coefficientB2;
	}

	public String getCoefficientB2() {
		return this.coefficientB2;
	}

	public void setSteelStrandLength(String steelStrandLength) {
		this.steelStrandLength = steelStrandLength;
	}

	public String getSteelStrandLength() {
		return this.steelStrandLength;
	}

	public void setOverstepTensionPercentage(String overstepTensionPercentage) {
		this.overstepTensionPercentage = overstepTensionPercentage;
	}

	public String getOverstepTensionPercentage() {
		return this.overstepTensionPercentage;
	}

	public void setTensionType(String tensionType) {
		this.tensionType = tensionType;
	}

	public String getTensionType() {
		return this.tensionType;
	}

	public void setSteelBeamNo(String steelBeamNo) {
		this.steelBeamNo = steelBeamNo;
	}

	public String getSteelBeamNo() {
		return this.steelBeamNo;
	}

	public void setTensionOrder(String tensionOrder) {
		this.tensionOrder = tensionOrder;
	}

	public String getTensionOrder() {
		return this.tensionOrder;
	}

	public void setConcreteDesignStrength(String concreteDesignStrength) {
		this.concreteDesignStrength = concreteDesignStrength;
	}

	public String getConcreteDesignStrength() {
		return this.concreteDesignStrength;
	}

	public void setConcreteStrength(String concreteStrength) {
		this.concreteStrength = concreteStrength;
	}

	public String getConcreteStrength() {
		return this.concreteStrength;
	}

	public void setDataPackageCount(String dataPackageCount) {
		this.dataPackageCount = dataPackageCount;
	}

	public String getDataPackageCount() {
		return this.dataPackageCount;
	}

	public void setElongationCorrectCoefficient1(
			String elongationCorrectCoefficient1) {
		this.elongationCorrectCoefficient1 = elongationCorrectCoefficient1;
	}

	public String getElongationCorrectCoefficient1() {
		return this.elongationCorrectCoefficient1;
	}

	public void setElongationCorrectCoefficient2(
			String elongationCorrectCoefficient2) {
		this.elongationCorrectCoefficient2 = elongationCorrectCoefficient2;
	}

	public String getElongationCorrectCoefficient2() {
		return this.elongationCorrectCoefficient2;
	}

	public void setShrinkage1(String shrinkage1) {
		this.shrinkage1 = shrinkage1;
	}

	public String getShrinkage1() {
		return this.shrinkage1;
	}

	public void setShrinkage2(String shrinkage2) {
		this.shrinkage2 = shrinkage2;
	}

	public String getShrinkage2() {
		return this.shrinkage2;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getRecordCount() {
		return this.recordCount;
	}

	public void setTensionTotalCount(Integer tensionTotalCount) {
		this.tensionTotalCount = tensionTotalCount;
	}

	public Integer getTensionTotalCount() {
		return this.tensionTotalCount;
	}

	public void setElongation1(String elongation1) {
		this.elongation1 = elongation1;
	}

	public String getElongation1() {
		return this.elongation1;
	}

	public void setElongation2(String elongation2) {
		this.elongation2 = elongation2;
	}

	public String getElongation2() {
		return this.elongation2;
	}

	public void setOilPressureGaugePressure1(String oilPressureGaugePressure1) {
		this.oilPressureGaugePressure1 = oilPressureGaugePressure1;
	}

	public String getOilPressureGaugePressure1() {
		return this.oilPressureGaugePressure1;
	}

	public void setOilPressureGaugePressure2(String oilPressureGaugePressure2) {
		this.oilPressureGaugePressure2 = oilPressureGaugePressure2;
	}

	public String getOilPressureGaugePressure2() {
		return this.oilPressureGaugePressure2;
	}

	public void setTopTension1(String topTension1) {
		this.topTension1 = topTension1;
	}

	public String getTopTension1() {
		return this.topTension1;
	}

	public void setTopTension2(String topTension2) {
		this.topTension2 = topTension2;
	}

	public String getTopTension2() {
		return this.topTension2;
	}

	public void setHoldingTime(String holdingTime) {
		this.holdingTime = holdingTime;
	}

	public String getHoldingTime() {
		return this.holdingTime;
	}

	public void setSmsStatusCode(String smsStatusCode) {
		this.smsStatusCode = smsStatusCode;
	}

	public String getSmsStatusCode() {
		return this.smsStatusCode;
	}

	public void setGatherTime(Date gatherTime) {
		this.gatherTime = gatherTime;
	}

	public Date getGatherTime() {
		return this.gatherTime;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
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

	public void setBackupField4(String backupField4) {
		this.backupField4 = backupField4;
	}

	public String getBackupField4() {
		return this.backupField4;
	}

	public void setBackupField5(String backupField5) {
		this.backupField5 = backupField5;
	}

	public String getBackupField5() {
		return this.backupField5;
	}

	public void setBackupField6(String backupField6) {
		this.backupField6 = backupField6;
	}

	public String getBackupField6() {
		return this.backupField6;
	}

	public void setBackupField7(String backupField7) {
		this.backupField7 = backupField7;
	}

	public String getBackupField7() {
		return this.backupField7;
	}

	public void setBackupField8(String backupField8) {
		this.backupField8 = backupField8;
	}

	public String getBackupField8() {
		return this.backupField8;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getReportAddress() {
		return this.reportAddress;
	}

	public void setUnqualifiedDescription(String unqualifiedDescription) {
		this.unqualifiedDescription = unqualifiedDescription;
	}

	public String getUnqualifiedDescription() {
		return this.unqualifiedDescription;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setDisposeTime(Date disposeTime) {
		this.disposeTime = disposeTime;
	}

	public Date getDisposeTime() {
		return this.disposeTime;
	}

	public void setDisposeStatus(String disposeStatus) {
		this.disposeStatus = disposeStatus;
	}

	public String getDisposeStatus() {
		return this.disposeStatus;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
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

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public Long getOriginalId() {
		return this.originalId;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
