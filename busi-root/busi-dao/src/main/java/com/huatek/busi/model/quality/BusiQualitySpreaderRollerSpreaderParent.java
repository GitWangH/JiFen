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
 * 路面摊压检测实体类.
 * 
 * @ClassName: BusiQualitySpreaderRollerSpreaderParent
 * @Description:路面摊压检测实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-03 09:29:53
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_spreader_roller_spreader_parent")
public class BusiQualitySpreaderRollerSpreaderParent extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// 压路机id
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "ROLLER_INSPECTION_ID")
	private BusiQualityRollerInspection busiQualityRollerInspection;
	// 铺摊机id
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "SPREADER_INSPECTION_ID")
	private BusiQualitySpreaderInspection busiQualitySpreaderInspection;

	// 铺摊、压路机超标id
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "SPREADER_ROLLER_EXCEED_ID")
	private BusiQualitySpreaderRollerExceed busiQualitySpreaderRollerExceed;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "SPREADER_ROLLER_SPREADER_PARENT_ID", nullable = true)
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

	/** @Fields operationSurface : 作业层面 */
	@Column(name = "OPERATION_SURFACE", nullable = true, length = 100)
	private String operationSurface;

	/** @Fields gatherDate : 采集时间 */
	@Column(name = "GATHER_DATE", nullable = true)
	private Date gatherDate;

	/** @Fields type : 作业类型( 压路机0， 铺摊机 1) */
	@Column(name = "TYPE", nullable = false)
	private Integer type;

	/** @Fields overproofReason : 超标原因 */
	@Column(name = "OVERPROOF_REASON", nullable = true, length = 200)
	private String overproofReason;

	/** @Fields reportAddress : 检测地址 */
	@Column(name = "REPORT_ADDRESS", nullable = true, length = 100)
	private String reportAddress;

	/** @Fields modifyTime : 处理时间 */
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;

	/** @Fields overproofGrade : 超标等级 */
	@Column(name = "OVERPROOF_GRADE", nullable = true, length = 100)
	private String overproofGrade;

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

	/** @Fields dataType : 0:合格，1:不合格，2：有效，3:无效，4:其他 */
	@Column(name = "DATA_TYPE", nullable = true)
	private Integer dataType;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
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

	public BusiQualitySpreaderRollerExceed getBusiQualitySpreaderRollerExceed() {
		return busiQualitySpreaderRollerExceed;
	}

	public void setBusiQualitySpreaderRollerExceed(
			BusiQualitySpreaderRollerExceed busiQualitySpreaderRollerExceed) {
		this.busiQualitySpreaderRollerExceed = busiQualitySpreaderRollerExceed;
	}

	public String getOverproofGrade() {
		return overproofGrade;
	}

	public void setOverproofGrade(String overproofGrade) {
		this.overproofGrade = overproofGrade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public BusiQualityRollerInspection getBusiQualityRollerInspection() {
		return busiQualityRollerInspection;
	}

	public void setBusiQualityRollerInspection(
			BusiQualityRollerInspection busiQualityRollerInspection) {
		this.busiQualityRollerInspection = busiQualityRollerInspection;
	}

	public BusiQualitySpreaderInspection getBusiQualitySpreaderInspection() {
		return busiQualitySpreaderInspection;
	}

	public void setBusiQualitySpreaderInspection(
			BusiQualitySpreaderInspection busiQualitySpreaderInspection) {
		this.busiQualitySpreaderInspection = busiQualitySpreaderInspection;
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

	public void setOperationSurface(String operationSurface) {
		this.operationSurface = operationSurface;
	}

	public String getOperationSurface() {
		return this.operationSurface;
	}

	public void setGatherDate(Date gatherDate) {
		this.gatherDate = gatherDate;
	}

	public Date getGatherDate() {
		return this.gatherDate;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public void setOverproofReason(String overproofReason) {
		this.overproofReason = overproofReason;
	}

	public String getOverproofReason() {
		return this.overproofReason;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getReportAddress() {
		return this.reportAddress;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

}
