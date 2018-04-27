package com.huatek.busi.model.quality;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 压力机实体类.
 * 
 * @ClassName: BusiQualityPressMachine
 * @Description:压力机实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-10-30 14:16:42
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_press_machine")
public class BusiQualityPressMachine extends BaseEntity {

	private static final long serialVersionUID = 1L;

//	/** @Fields busiQualityUniversalPressMachineParentId : 压力、万能机外键id */
//	@OneToOne
//	@JoinColumn(name = "UNIVERSAL_PRESS_MACHINE_PARENT_ID", nullable = false)
//	private BusiQualityUniversalPressMachineParent busiQualityUniversalPressMachineParent;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "PRESS_MACHINE_ID", nullable = true)
	private Long id;

	/** @Fields sampleNumber : 样品编号 */
	@Column(name = "SAMPLE_NUMBER", nullable = false, length = 100)
	private String sampleNumber;

	/** @Fields entrustedCompany : 委托单位 */
	@Column(name = "ENTRUSTED_COMPANY", nullable = false, length = 100)
	private String entrustedCompany;

	/** @Fields commissionDate : 委托日期 */
	@Column(name = "COMMISSION_DATE", nullable = false, length = 30)
	private String commissionDate;

	/** @Fields experimentalDate : 实验日期(对方提供说明：试验类型编号，错误说明) */
	@Column(name = "EXPERIMENTAL_DATE", nullable = false, length = 30)
	private String experimentalDate;

	/** @Fields engineeringName : 工程名称 */
	@Column(name = "ENGINEERING_NAME", nullable = false, length = 100)
	private String engineeringName;

	/** @Fields constructionSite : 施工部位 */
	@Column(name = "CONSTRUCTION_SITE", nullable = false, length = 100)
	private String constructionSite;

	/** @Fields sampleSource : 样品来源 */
	@Column(name = "SAMPLE_SOURCE", nullable = false, length = 100)
	private String sampleSource;

	/** @Fields sampleName : 样品名称 */
	@Column(name = "SAMPLE_NAME", nullable = false, length = 100)
	private String sampleName;

	/** @Fields approver : 批准人 */
	@Column(name = "APPROVER", nullable = false, length = 100)
	private String approver;

	/** @Fields verifier : 核验人 */
	@Column(name = "VERIFIER", nullable = false, length = 100)
	private String verifier;

	/** @Fields tester : 检定人 */
	@Column(name = "TESTER", nullable = false, length = 100)
	private String tester;

	/** @Fields testType : 试验类型 */
	@Column(name = "TEST_TYPE", nullable = false, length = 100)
	private String testType;

	/** @Fields testType1 : 试验类型 */
	@Column(name = "TEST_TYPE_1", nullable = false, length = 100)
	private String testType1;

	/** @Fields sampleType : 样品类型 */
	@Column(name = "SAMPLE_TYPE", nullable = false, length = 100)
	private String sampleType;

	/** @Fields pressureArea : 承压面积 */
	@Column(name = "PRESSURE_AREA", nullable = false, length = 100)
	private String pressureArea;

	/** @Fields maintenanceMethod : 养护方式 */
	@Column(name = "MAINTENANCE_METHOD", nullable = false, length = 100)
	private String maintenanceMethod;

	/** @Fields age : 龄期 */
	@Column(name = "AGE", nullable = false, length = 100)
	private String age;

	/** @Fields load1 : 荷载1 */
	@Column(name = "LOAD1", nullable = false, length = 100)
	private String load1;

	/** @Fields load2 : 荷载2 */
	@Column(name = "LOAD2", nullable = false, length = 100)
	private String load2;

	/** @Fields load3 : 荷载3 */
	@Column(name = "LOAD3", nullable = false, length = 100)
	private String load3;

	/** @Fields load4 : 荷载4 */
	@Column(name = "LOAD4", nullable = false, length = 100)
	private String load4;

	/** @Fields load5 : 荷载5 */
	@Column(name = "LOAD5", nullable = false, length = 100)
	private String load5;

	/** @Fields load6 : 荷载6 */
	@Column(name = "LOAD6", nullable = false, length = 100)
	private String load6;

	/** @Fields strengthRepresentativeValue : 强度代表值 */
	@Column(name = "STRENGTH_REPRESENTATIVE_VALUE", nullable = false, length = 100)
	private String strengthRepresentativeValue;

	/** @Fields designStrength : 设计强度 */
	@Column(name = "DESIGN_STRENGTH", nullable = false, length = 100)
	private String designStrength;

	/** @Fields processTimeLoad1 : 过程时间(多个用逗号隔开) ，以毫秒为单位，荷载1的 */
	@Column(name = "PROCESS_TIME_LOAD1", nullable = false, length = 30)
	private String processTimeLoad1;

	/** @Fields processForceValueload1 : 过程力值(多个用逗号隔开) ，荷载1的 */
	@Column(name = "PROCESS_FORCE_VALUELOAD1", nullable = false, length = 100)
	private String processForceValueload1;

	/** @Fields processTimeLoad2 : 过程时间(多个用逗号隔开) ，以毫秒为单位，，荷载2的 */
	@Column(name = "PROCESS_TIME_LOAD2", nullable = false, length = 30)
	private String processTimeLoad2;

	/** @Fields processForceValueload2 : 过程力值(多个用逗号隔开)，荷载2的 */
	@Column(name = "PROCESS_FORCE_VALUELOAD2", nullable = false, length = 100)
	private String processForceValueload2;

	/** @Fields processTimeLoad3 : 过程时间(多个用逗号隔开) ，以毫秒为单位，，荷载3的 */
	@Column(name = "PROCESS_TIME_LOAD3", nullable = false, length = 30)
	private String processTimeLoad3;

	/** @Fields processForceValueload3 : 过程力值(多个用逗号隔开)，荷载3的 */
	@Column(name = "PROCESS_FORCE_VALUELOAD3", nullable = false, length = 100)
	private String processForceValueload3;

	/** @Fields status : 0:合格，1:不合格，2有效，:3:无效，4:其他 */
	@Column(name = "STATUS", nullable = false, length = 100)
	private String status;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = false, length = 100)
	private String ukey;

	/** @Fields descurl : 详细描述地址 */
	@Column(name = "DESC_URL", nullable = false, length = 100)
	private String descUrl;

	/** @Fields description : 不合格描述 */
	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	private String description;

	/** @Fields factOrgId : 被检测样品所属施工标段ID */
	@Column(name = "FACT_ORG_ID", nullable = true)
	private Long factOrgId;

	/** @Fields orgId : 机构ID */
	@Column(name = "ORG_ID", nullable = false, length = 32)
	private String orgId;

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

	/** @Fields orgName : 机构名称 */
	@Transient
	private String orgName;

//	public BusiQualityUniversalPressMachineParent getBusiQualityUniversalPressMachineParent() {
//		return busiQualityUniversalPressMachineParent;
//	}

//	public void setBusiQualityUniversalPressMachineParent(
//			BusiQualityUniversalPressMachineParent busiQualityUniversalPressMachineParent) {
//		this.busiQualityUniversalPressMachineParent = busiQualityUniversalPressMachineParent;
//	}

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

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getSampleNumber() {
		return this.sampleNumber;
	}

	public void setEntrustedCompany(String entrustedCompany) {
		this.entrustedCompany = entrustedCompany;
	}

	public String getEntrustedCompany() {
		return this.entrustedCompany;
	}

	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getCommissionDate() {
		return this.commissionDate;
	}

	public void setExperimentalDate(String experimentalDate) {
		this.experimentalDate = experimentalDate;
	}

	public String getExperimentalDate() {
		return this.experimentalDate;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getEngineeringName() {
		return this.engineeringName;
	}

	public void setConstructionSite(String constructionSite) {
		this.constructionSite = constructionSite;
	}

	public String getConstructionSite() {
		return this.constructionSite;
	}

	public void setSampleSource(String sampleSource) {
		this.sampleSource = sampleSource;
	}

	public String getSampleSource() {
		return this.sampleSource;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApprover() {
		return this.approver;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public String getVerifier() {
		return this.verifier;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getTester() {
		return this.tester;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestType() {
		return this.testType;
	}

	public void setTestType1(String testType1) {
		this.testType1 = testType1;
	}

	public String getTestType1() {
		return this.testType1;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getSampleType() {
		return this.sampleType;
	}

	public void setPressureArea(String pressureArea) {
		this.pressureArea = pressureArea;
	}

	public String getPressureArea() {
		return this.pressureArea;
	}

	public void setMaintenanceMethod(String maintenanceMethod) {
		this.maintenanceMethod = maintenanceMethod;
	}

	public String getMaintenanceMethod() {
		return this.maintenanceMethod;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return this.age;
	}

	public void setLoad1(String load1) {
		this.load1 = load1;
	}

	public String getLoad1() {
		return this.load1;
	}

	public void setLoad2(String load2) {
		this.load2 = load2;
	}

	public String getLoad2() {
		return this.load2;
	}

	public void setLoad3(String load3) {
		this.load3 = load3;
	}

	public String getLoad3() {
		return this.load3;
	}

	public void setLoad4(String load4) {
		this.load4 = load4;
	}

	public String getLoad4() {
		return this.load4;
	}

	public void setLoad5(String load5) {
		this.load5 = load5;
	}

	public String getLoad5() {
		return this.load5;
	}

	public void setLoad6(String load6) {
		this.load6 = load6;
	}

	public String getLoad6() {
		return this.load6;
	}

	public void setStrengthRepresentativeValue(
			String strengthRepresentativeValue) {
		this.strengthRepresentativeValue = strengthRepresentativeValue;
	}

	public String getStrengthRepresentativeValue() {
		return this.strengthRepresentativeValue;
	}

	public void setDesignStrength(String designStrength) {
		this.designStrength = designStrength;
	}

	public String getDesignStrength() {
		return this.designStrength;
	}

	public void setProcessTimeLoad1(String processTimeLoad1) {
		this.processTimeLoad1 = processTimeLoad1;
	}

	public String getProcessTimeLoad1() {
		return this.processTimeLoad1;
	}

	public void setProcessForceValueload1(String processForceValueload1) {
		this.processForceValueload1 = processForceValueload1;
	}

	public String getProcessForceValueload1() {
		return this.processForceValueload1;
	}

	public void setProcessTimeLoad2(String processTimeLoad2) {
		this.processTimeLoad2 = processTimeLoad2;
	}

	public String getProcessTimeLoad2() {
		return this.processTimeLoad2;
	}

	public void setProcessForceValueload2(String processForceValueload2) {
		this.processForceValueload2 = processForceValueload2;
	}

	public String getProcessForceValueload2() {
		return this.processForceValueload2;
	}

	public void setProcessTimeLoad3(String processTimeLoad3) {
		this.processTimeLoad3 = processTimeLoad3;
	}

	public String getProcessTimeLoad3() {
		return this.processTimeLoad3;
	}

	public void setProcessForceValueload3(String processForceValueload3) {
		this.processForceValueload3 = processForceValueload3;
	}

	public String getProcessForceValueload3() {
		return this.processForceValueload3;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setFactOrgId(Long factOrgId) {
		this.factOrgId = factOrgId;
	}

	public Long getFactOrgId() {
		return this.factOrgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId() {
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
