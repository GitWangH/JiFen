package com.huatek.busi.model.quality;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 铺摊压路超标实体类.
 * 
 * @ClassName: BusiQualitySpreaderRollerExceed
 * @Description:铺摊压路超标实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-03 09:29:53
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_spreader_roller_exceed")
public class BusiQualitySpreaderRollerExceed extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** @Fields spreaderRollerSpreaderParentId : 铺摊机、压路机外键id */
	@OneToOne
	@JoinColumn(name = "SPREADER_ROLLER_SPREADER_PARENT_ID", nullable = false)
	private BusiQualitySpreaderRollerSpreaderParent busiQualitySpreaderRollerSpreaderParent;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "SPREADER_ROLLER_EXCEED_ID", nullable = true)
	private Long id;

	/** @Fields originalId : 原始id */
	@Column(name = "ORIGINAL_ID", nullable = false)
	private Long originalId;

	/** @Fields gatherDate : 采集时间 */
	@Column(name = "GATHER_DATE", nullable = true)
	private Date gatherDate;

	/** @Fields overproofGrade : 超标等级 */
	@Column(name = "OVERPROOF_GRADE", nullable = true, length = 100)
	private String overproofGrade;

	/** @Fields overproofReason : 超标原因 */
	@Column(name = "OVERPROOF_REASON", nullable = true, length = 200)
	private String overproofReason;

	/** @Fields disposeState : 0:未处理,1:已处理 */
	@Column(name = "DISPOSE_STATE", nullable = true)
	private Integer disposeState;

	/** @Fields disposeUser : 处置人 */
	@Column(name = "DISPOSE_USER", nullable = true, length = 100)
	private String disposeUser;

	/** @Fields disposeDate : 处置时间 */
	@Column(name = "DISPOSE_DATE", nullable = true, length = 100)
	private String disposeDate;

	/** @Fields disposeResult : 处置结果 */
	@Column(name = "DISPOSE_RESULT", nullable = true, length = 100)
	private String disposeResult;

	/** @Fields dataType : 分类（0：压路机，1：摊铺机） */
	@Column(name = "DATA_TYPE", nullable = true)
	private Integer dataType;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = true, length = 100)
	private String ukey;

	/** @Fields reportAddress : 报告地址 */
	@Column(name = "REPORT_ADDRESS", nullable = true, length = 100)
	private String reportAddress;

	/** @Fields orgId : */
	@Column(name = "ORG_ID", nullable = false)
	private Long orgId;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields tenantId : 租户id */
	@Column(name = "TENANT_ID", nullable = false)
	private Long tenantId;

	/** @Fields inspectionId : 快速处理或整改单的ID */
	@Column(name = "INSPECTION_ID", nullable = false)
	private Integer inspectionId;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	public BusiQualitySpreaderRollerSpreaderParent getBusiQualitySpreaderRollerSpreaderParent() {
		return busiQualitySpreaderRollerSpreaderParent;
	}

	public void setBusiQualitySpreaderRollerSpreaderParent(
			BusiQualitySpreaderRollerSpreaderParent busiQualitySpreaderRollerSpreaderParent) {
		this.busiQualitySpreaderRollerSpreaderParent = busiQualitySpreaderRollerSpreaderParent;
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

	public void setGatherDate(Date gatherDate) {
		this.gatherDate = gatherDate;
	}

	public Date getGatherDate() {
		return this.gatherDate;
	}

	public void setOverproofGrade(String overproofGrade) {
		this.overproofGrade = overproofGrade;
	}

	public String getOverproofGrade() {
		return this.overproofGrade;
	}

	public void setOverproofReason(String overproofReason) {
		this.overproofReason = overproofReason;
	}

	public String getOverproofReason() {
		return this.overproofReason;
	}

	public void setDisposeState(Integer disposeState) {
		this.disposeState = disposeState;
	}

	public Integer getDisposeState() {
		return this.disposeState;
	}

	public void setDisposeUser(String disposeUser) {
		this.disposeUser = disposeUser;
	}

	public String getDisposeUser() {
		return this.disposeUser;
	}

	public void setDisposeDate(String disposeDate) {
		this.disposeDate = disposeDate;
	}

	public String getDisposeDate() {
		return this.disposeDate;
	}

	public void setDisposeResult(String disposeResult) {
		this.disposeResult = disposeResult;
	}

	public String getDisposeResult() {
		return this.disposeResult;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getDataType() {
		return this.dataType;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getReportAddress() {
		return this.reportAddress;
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

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public Integer getInspectionId() {
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
