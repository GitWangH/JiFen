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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 砂浆检测实体类.
 * 
 * @ClassName: BusiQualityMortar
 * @Description:砂浆检测实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-06 09:28:04
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_mortar")
public class BusiQualityMortar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "MORTAR_ID", nullable = true)
	private Long id;

	/** @Fields originalId : 原始id */
	@Column(name = "ORIGINAL_ID", nullable = false)
	private Long originalId;

	/** @Fields testUser : 试验人 */
	@Column(name = "TEST_USER", nullable = false, length = 100)
	private String testUser;

	/** @Fields sampleCode : 样品编号 */
	@Column(name = "SAMPLE_CODE", nullable = false, length = 100)
	private String sampleCode;

	/** @Fields testDate : 试验时间 */
	@Column(name = "TEST_DATE", nullable = false, length = 30)
	private String testDate;

	/** @Fields sampleName : 样品名称 */
	@Column(name = "SAMPLE_NAME", nullable = false, length = 100)
	private String sampleName;

	/** @Fields testType : 试验类型 */
	@Column(name = "TEST_TYPE", nullable = false, length = 100)
	private String testType;

	/** @Fields destroyPressure1 : 破坏压力1(Nu) */
	@Column(name = "DESTROY_PRESSURE_1", nullable = false, length = 100)
	private String destroyPressure1;

	/** @Fields destroyPressure2 : 破坏压力2(Nu) */
	@Column(name = "DESTROY_PRESSURE_2", nullable = false, length = 100)
	private String destroyPressure2;

	/** @Fields destroyPressure3 : 破坏压力3(Nu) */
	@Column(name = "DESTROY_PRESSURE_3", nullable = false, length = 100)
	private String destroyPressure3;

	/** @Fields destroyPressure4 : 破坏压力4(Nu) */
	@Column(name = "DESTROY_PRESSURE_4", nullable = false, length = 100)
	private String destroyPressure4;

	/** @Fields destroyPressure5 : 破坏压力5(Nu) */
	@Column(name = "DESTROY_PRESSURE_5", nullable = false, length = 100)
	private String destroyPressure5;

	/** @Fields destroyPressure6 : 破坏压力6(Nu) */
	@Column(name = "DESTROY_PRESSURE_6", nullable = false, length = 100)
	private String destroyPressure6;

	/** @Fields pressureArea : 承压面积(A) */
	@Column(name = "PRESSURE_AREA", nullable = false, length = 100)
	private String pressureArea;

	/** @Fields compressiveStrength1 : 抗压强度1(fm,cu) */
	@Column(name = "COMPRESSIVE_STRENGTH_1", nullable = false, length = 100)
	private String compressiveStrength1;

	/** @Fields compressiveStrength2 : 抗压强度2(fm,cu) */
	@Column(name = "COMPRESSIVE_STRENGTH_2", nullable = false, length = 100)
	private String compressiveStrength2;

	/** @Fields compressiveStrength3 : 抗压强度3(fm,cu) */
	@Column(name = "COMPRESSIVE_STRENGTH_3", nullable = false, length = 100)
	private String compressiveStrength3;

	/** @Fields compressiveStrength4 : 抗压强度4(fm,cu) */
	@Column(name = "COMPRESSIVE_STRENGTH_4", nullable = false, length = 100)
	private String compressiveStrength4;

	/** @Fields compressiveStrength5 : 抗压强度5(fm,cu) */
	@Column(name = "COMPRESSIVE_STRENGTH_5", nullable = false, length = 100)
	private String compressiveStrength5;

	/** @Fields compressiveStrength6 : 抗压强度6(fm,cu) */
	@Column(name = "COMPRESSIVE_STRENGTH_6", nullable = false, length = 100)
	private String compressiveStrength6;

	/** @Fields pressureAverage : 压力平均值(Nu) */
	@Column(name = "PRESSURE_AVERAGE", nullable = false, length = 100)
	private String pressureAverage;

	/** @Fields strengthAverage : 强度平均值(fm,cu) */
	@Column(name = "STRENGTH_AVERAGE", nullable = false, length = 100)
	private String strengthAverage;

	/** @Fields processTime1 : 过程时间1(多个用逗号隔开) ，以毫秒为单位 */
	@Column(name = "PROCESS_TIME_1", nullable = false, length = 30)
	private String processTime1;

	/** @Fields processForceValue1 : 过程力值1(多个用逗号隔开) */
	@Column(name = "PROCESS_FORCE_VALUE_1", nullable = false, length = 100)
	private String processForceValue1;

	/** @Fields processTime2 : 过程时间2(多个用逗号隔开) ，以毫秒为单位 */
	@Column(name = "PROCESS_TIME_2", nullable = false, length = 30)
	private String processTime2;

	/** @Fields processForceValue2 : 过程力值2(多个用逗号隔开) */
	@Column(name = "PROCESS_FORCE_VALUE_2", nullable = false, length = 100)
	private String processForceValue2;

	/** @Fields processTime3 : 过程时间3(多个用逗号隔开) ，以毫秒为单位 */
	@Column(name = "PROCESS_TIME_3", nullable = false, length = 30)
	private String processTime3;

	/** @Fields processForceValue3 : 过程力值3(多个用逗号隔开) */
	@Column(name = "PROCESS_FORCE_VALUE_3", nullable = false, length = 100)
	private String processForceValue3;

	/** @Fields isQualified : 0:合格，1:不合格，2有效，:3:无效，4:其他 */
	@Column(name = "IS_QUALIFIED", nullable = false)
	private Integer isQualified;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = false, length = 100)
	private String ukey;

	/** @Fields descriptionUrl : 详细描述地址 */
	@Column(name = "DESCRIPTION_URL", nullable = false, length = 100)
	private String descriptionUrl;

	/** @Fields description : 不合格描述 */
	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;

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

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields inspectionCode : 整改（快捷处理）编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;

	/** @Fields disposeTime : 处理时间 */
	@Column(name = "DISPOSE_TIME", nullable = false)
	private Date disposeTime;

	/** @Fields disposeState : 处理状态（数据字典） */
	@Column(name = "DISPOSE_STATE", nullable = true, length = 30)
	private String disposeState;

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public Date getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(Date disposeTime) {
		this.disposeTime = disposeTime;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
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

	public void setTestUser(String testUser) {
		this.testUser = testUser;
	}

	public String getTestUser() {
		return this.testUser;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getSampleCode() {
		return this.sampleCode;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getTestDate() {
		return this.testDate;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestType() {
		return this.testType;
	}

	public void setDestroyPressure1(String destroyPressure1) {
		this.destroyPressure1 = destroyPressure1;
	}

	public String getDestroyPressure1() {
		return this.destroyPressure1;
	}

	public void setDestroyPressure2(String destroyPressure2) {
		this.destroyPressure2 = destroyPressure2;
	}

	public String getDestroyPressure2() {
		return this.destroyPressure2;
	}

	public void setDestroyPressure3(String destroyPressure3) {
		this.destroyPressure3 = destroyPressure3;
	}

	public String getDestroyPressure3() {
		return this.destroyPressure3;
	}

	public void setDestroyPressure4(String destroyPressure4) {
		this.destroyPressure4 = destroyPressure4;
	}

	public String getDestroyPressure4() {
		return this.destroyPressure4;
	}

	public void setDestroyPressure5(String destroyPressure5) {
		this.destroyPressure5 = destroyPressure5;
	}

	public String getDestroyPressure5() {
		return this.destroyPressure5;
	}

	public void setDestroyPressure6(String destroyPressure6) {
		this.destroyPressure6 = destroyPressure6;
	}

	public String getDestroyPressure6() {
		return this.destroyPressure6;
	}

	public void setPressureArea(String pressureArea) {
		this.pressureArea = pressureArea;
	}

	public String getPressureArea() {
		return this.pressureArea;
	}

	public void setCompressiveStrength1(String compressiveStrength1) {
		this.compressiveStrength1 = compressiveStrength1;
	}

	public String getCompressiveStrength1() {
		return this.compressiveStrength1;
	}

	public void setCompressiveStrength2(String compressiveStrength2) {
		this.compressiveStrength2 = compressiveStrength2;
	}

	public String getCompressiveStrength2() {
		return this.compressiveStrength2;
	}

	public void setCompressiveStrength3(String compressiveStrength3) {
		this.compressiveStrength3 = compressiveStrength3;
	}

	public String getCompressiveStrength3() {
		return this.compressiveStrength3;
	}

	public void setCompressiveStrength4(String compressiveStrength4) {
		this.compressiveStrength4 = compressiveStrength4;
	}

	public String getCompressiveStrength4() {
		return this.compressiveStrength4;
	}

	public void setCompressiveStrength5(String compressiveStrength5) {
		this.compressiveStrength5 = compressiveStrength5;
	}

	public String getCompressiveStrength5() {
		return this.compressiveStrength5;
	}

	public void setCompressiveStrength6(String compressiveStrength6) {
		this.compressiveStrength6 = compressiveStrength6;
	}

	public String getCompressiveStrength6() {
		return this.compressiveStrength6;
	}

	public void setPressureAverage(String pressureAverage) {
		this.pressureAverage = pressureAverage;
	}

	public String getPressureAverage() {
		return this.pressureAverage;
	}

	public void setStrengthAverage(String strengthAverage) {
		this.strengthAverage = strengthAverage;
	}

	public String getStrengthAverage() {
		return this.strengthAverage;
	}

	public void setProcessTime1(String processTime1) {
		this.processTime1 = processTime1;
	}

	public String getProcessTime1() {
		return this.processTime1;
	}

	public void setProcessForceValue1(String processForceValue1) {
		this.processForceValue1 = processForceValue1;
	}

	public String getProcessForceValue1() {
		return this.processForceValue1;
	}

	public void setProcessTime2(String processTime2) {
		this.processTime2 = processTime2;
	}

	public String getProcessTime2() {
		return this.processTime2;
	}

	public void setProcessForceValue2(String processForceValue2) {
		this.processForceValue2 = processForceValue2;
	}

	public String getProcessForceValue2() {
		return this.processForceValue2;
	}

	public void setProcessTime3(String processTime3) {
		this.processTime3 = processTime3;
	}

	public String getProcessTime3() {
		return this.processTime3;
	}

	public void setProcessForceValue3(String processForceValue3) {
		this.processForceValue3 = processForceValue3;
	}

	public String getProcessForceValue3() {
		return this.processForceValue3;
	}

	public void setIsQualified(Integer isQualified) {
		this.isQualified = isQualified;
	}

	public Integer getIsQualified() {
		return this.isQualified;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	}

	public String getDescriptionUrl() {
		return this.descriptionUrl;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
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
