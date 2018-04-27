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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 原材料检测实体类.
 * 
 * @ClassName: BusiQualityRawMaterialInspection
 * @Description:原材料检测实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-10-24 18:10:25
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_raw_material_inspection")
public class BusiQualityRawMaterialInspection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "RAW_MATERIAL_INSPECTION_ID", nullable = true)
	private Long id;

	/** @Fields disposeState : 处理状态（数据字典） */
	@Column(name = "DISPOSE_STATE", nullable = true, length = 30)
	private String disposeState;

	/**
	 * @Fields tpId : 东方星数据表唯一主键Id 陕西公众数据库表唯一主键Id 上海同岩数据库表唯一主键Id
	 */
	// @Column(name= "TP_ID", nullable = true, length=64 )
	// private String tpId;

	/** @Fields entrustSampleBillNo : 委托取样单编号 */
	@Column(name = "ENTRUST_SAMPLE_BILL_NO", nullable = true, length = 100)
	private String entrustSampleBillNo;

	/** @Fields testDate : 试验日期(格式：yyyy-MM-dd HH:mm:ss) */
	@Column(name = "TEST_DATE", nullable = true)
	private Date testDate;

	/** @Fields reportAddress : 报告页面地址 */
	@Column(name = "REPORT_ADDRESS", nullable = true, length = 100)
	private String reportAddress;

	/** @Fields reportConclusion : 结论(数据验证必须是【字典表-合格/不合格】) */
	@Column(name = "REPORT_CONCLUSION", nullable = true, length = 30)
	private String reportConclusion;

	/** @Fields reportDate : 报告时间 */
	@Column(name = "REPORT_DATE", nullable = true)
	private Date reportDate;

	/** @Fields reportName : 报告名称 */
	@Column(name = "REPORT_NAME", nullable = true, length = 100)
	private String reportName;

	/** @Fields reportCode : 报告编号 */
	@Column(name = "REPORT_CODE", nullable = true, length = 50)
	private String reportCode;

	/** @Fields reportResult : 报告结果(数据验证必须是【检查合格/检查不合格】), 不合格描述 */
	@Column(name = "REPORT_RESULT", nullable = true, length = 30)
	private String reportResult;

	/** @Fields entrustSampleBillDate : 委托取样日期(格式：yyyy-MM-dd HH:mm:ss) */
	@Column(name = "ENTRUST_SAMPLE_BILL_DATE", nullable = true)
	private Date entrustSampleBillDate;

	/** @Fields measureUnit : 计量单位 */
	@Column(name = "MEASURE_UNIT", nullable = true, length = 32)
	private String measureUnit;

	/** @Fields sampleName : 样品名称 */
	@Column(name = "SAMPLE_NAME", nullable = true, length = 100)
	private String sampleName;

	/** @Fields sampleCode : 样品编号 */
	@Column(name = "SAMPLE_CODE", nullable = true, length = 50)
	private String sampleCode;

	/** @Fields batch : 批次 */
	@Column(name = "BATCH", nullable = true, length = 50)
	private String batch;

	/** @Fields quantity : 数量 */
	@Column(name = "QUANTITY", nullable = true, precision = 18, scale = 2)
	private BigDecimal quantity;

	/** @Fields supplier : 供货商 */
	@Column(name = "SUPPLIER", nullable = true, length = 100)
	private String supplier;

	/** @Fields checkDate : 检查时间 */
	@Column(name = "CHECK_DATE", nullable = true)
	private Date checkDate;

	/** @Fields checkCode : 检查编号 */
	@Column(name = "CHECK_CODE", nullable = true, length = 50)
	private String checkCode;

	/** @Fields checkQuantity : 检查数量 */
	@Column(name = "CHECK_QUANTITY", nullable = true, precision = 18, scale = 2)
	private BigDecimal checkQuantity;

	/** @Fields checkUserName : 检查人名称 */
	@Column(name = "CHECK_USER_NAME", nullable = true, length = 100)
	private String checkUserName;

	/** @Fields checkResult : 检查结果 */
	@Column(name = "CHECK_RESULT", nullable = true, length = 100)
	private String checkResult;

	/** @Fields sampleUnit : 样品单位 */
	@Column(name = "SAMPLE_UNIT", nullable = true, length = 30)
	private String sampleUnit;

	/** @Fields checkType : 检测类型（数据验证必须是【1：自检；2：监理抽检；3：中心试验室抽检】） */
	@Column(name = "CHECK_TYPE", nullable = true, length = 30)
	private String checkType;

	/** @Fields machineNo : 机器编号 */
	@Column(name = "MACHINE_NO", nullable = true, length = 50)
	private String machineNo;

	/** @Fields orgName : 机构名称 */
	@Transient
	private String orgName;

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

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传，2已传给质监局并做修改 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields appKey : 接口传数据标识符 */
	@Column(name = "APP_KEY", nullable = false)
	private String appKey;

	/** @Fields inspectionCode : 整改（快捷处理）编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;

	/** @Fields factOrgId : 被检测样品所属施工标段ID */
	@Column(name = "FACT_ORG_ID", nullable = false)
	private Long factOrgId;

	/** @Fields disposeTime : 处理时间 */
	@Column(name = "DISPOSE_TIME", nullable = false)
	private Date disposeTime;
	
	/** @Fields UKEY : 唯一码 */
	@Column(name = "UKEY")
	private String ukey;

	public Date getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(Date disposeTime) {
		this.disposeTime = disposeTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getDisposeState() {
		return this.disposeState;
	}

	public void setEntrustSampleBillNo(String entrustSampleBillNo) {
		this.entrustSampleBillNo = entrustSampleBillNo;
	}

	public String getEntrustSampleBillNo() {
		return this.entrustSampleBillNo;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Date getTestDate() {
		return this.testDate;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getReportAddress() {
		return this.reportAddress;
	}

	public void setReportConclusion(String reportConclusion) {
		this.reportConclusion = reportConclusion;
	}

	public String getReportConclusion() {
		return this.reportConclusion;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportName() {
		return this.reportName;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getReportCode() {
		return this.reportCode;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

	public String getReportResult() {
		return this.reportResult;
	}

	public void setEntrustSampleBillDate(Date entrustSampleBillDate) {
		this.entrustSampleBillDate = entrustSampleBillDate;
	}

	public Date getEntrustSampleBillDate() {
		return this.entrustSampleBillDate;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public String getMeasureUnit() {
		return this.measureUnit;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getSampleCode() {
		return this.sampleCode;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getBatch() {
		return this.batch;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getCheckCode() {
		return this.checkCode;
	}

	public void setCheckQuantity(BigDecimal checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	public BigDecimal getCheckQuantity() {
		return this.checkQuantity;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getCheckUserName() {
		return this.checkUserName;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckResult() {
		return this.checkResult;
	}

	public void setSampleUnit(String sampleUnit) {
		this.sampleUnit = sampleUnit;
	}

	public String getSampleUnit() {
		return this.sampleUnit;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCheckType() {
		return this.checkType;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getMachineNo() {
		return this.machineNo;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgName() {
		return this.orgName;
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

	public Long getFactOrgId() {
		return factOrgId;
	}

	public void setFactOrgId(Long factOrgId) {
		this.factOrgId = factOrgId;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

}
