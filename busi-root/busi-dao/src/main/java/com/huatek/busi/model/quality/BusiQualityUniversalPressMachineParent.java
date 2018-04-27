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
 * 试验检测（压力机&万能机）实体类.
 * 
 * @ClassName: BusiQualityUniversalPressMachineParent
 * @Description:试验检测（压力机&万能机）实体类.
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-03 09:14:10
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_universal_press_machine_parent")
public class BusiQualityUniversalPressMachineParent extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// 压力机id
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "PRESS_MACHINE_ID")
	private BusiQualityPressMachine busiQualityPressMachine;

	// 万能机id
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "UNIVERSAL_MACHINE_ID")
	private BusiQualityUniversalMachine busiQualityUniversalMachine;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "UNIVERSAL_PRESS_MACHINE_PARENT_ID", nullable = true)
	private Long id;

	/** @Fields orgId : */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg org;

	/** @Fields tenantId : 租户id */
	@Column(name = "TENANT_ID", nullable = false)
	private Long tenantId;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields disposeState : 处理状态（数据字典） */
	@Column(name = "DISPOSE_STATE", nullable = false, length = 30)
	private String disposeState;

	/** @Fields inspectionCode : 整改编号 */
	@Column(name = "INSPECTION_CODE", nullable = false, length = 30)
	private String inspectionCode;

	/** @Fields sampleNumber : 样品编号 */
	@Column(name = "SAMPLE_NUMBER", nullable = false, length = 100)
	private String sampleNumber;

	/** @Fields sampleName : 样品名称 */
	@Column(name = "SAMPLE_NAME", nullable = false, length = 100)
	private String sampleName;

	/** @Fields testType : 试验类型 */
	@Column(name = "TEST_TYPE", nullable = false, length = 100)
	private String testType;

	/** @Fields testPersion : 试验人 */
	@Column(name = "TEST_PERSION", nullable = false, length = 100)
	private String testPersion;

	/** @Fields testTime : 试验时间 */
	@Column(name = "TEST_TIME", nullable = false, length = 30)
	private String testTime;

	/** @Fields descUrl : 详细描述地址 */
	@Column(name = "DESC_URL", nullable = false, length = 255)
	private String descUrl;

	/** @Fields unqualifiedDescription : 描述 */
	@Column(name = "UNQUALIFIED_DESCRIPTION", nullable = false, length = 100)
	private String unqualifiedDescription;

	/** @Fields modifyTime : 当前处理时间（回调维护） */
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;

	/** @Fields status : 0:合格，1:不合格，2有效，:3:无效，4:其他 */
	@Column(name = "STATUS", nullable = false, length = 100)
	private String status;

	/** @Fields modifer : 修改人ID */
	@Column(name = "MODIFER", nullable = false)
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	@Column(name = "MODIFIER_NAME", nullable = false, length = 100)
	private String modifierName;

	/** @Fields inspectionType : 整改类型 0 快速处理 1 整改单 */
	@Column(name = "INSPECTION_TYPE", nullable = false)
	private Integer inspectionType;

	/** @Fields inspectionId : 快速处理或整改单的ID */
	@Column(name = "INSPECTION_ID", nullable = false)
	private Long inspectionId;

	/** @Fields isDelete : 是否删除 0未删除， 1已删除 */
	@Column(name = "IS_DELETE")
	private Integer isDelete;

	/** @Fields factOrgId : 被检测样品所属施工标段ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "FACT_ORG_ID")
	private BusiFwOrg factOrg;

	public BusiFwOrg getFactOrg() {
		return factOrg;
	}

	public void setFactOrg(BusiFwOrg factOrg) {
		this.factOrg = factOrg;
	}

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}

	public BusiQualityPressMachine getBusiQualityPressMachine() {
		return busiQualityPressMachine;
	}

	public void setBusiQualityPressMachine(
			BusiQualityPressMachine busiQualityPressMachine) {
		this.busiQualityPressMachine = busiQualityPressMachine;
	}

	public BusiQualityUniversalMachine getBusiQualityUniversalMachine() {
		return busiQualityUniversalMachine;
	}

	public void setBusiQualityUniversalMachine(
			BusiQualityUniversalMachine busiQualityUniversalMachine) {
		this.busiQualityUniversalMachine = busiQualityUniversalMachine;
	}

	public Integer getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTenantId() {
		return this.tenantId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getDisposeState() {
		return this.disposeState;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public String getInspectionCode() {
		return this.inspectionCode;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getSampleNumber() {
		return this.sampleNumber;
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

	public void setTestPersion(String testPersion) {
		this.testPersion = testPersion;
	}

	public String getTestPersion() {
		return this.testPersion;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getTestTime() {
		return this.testTime;
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

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
