package com.huatek.busi.model.quality;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 压路机实体类.
 * 
 * @ClassName: BusiQualityRollerInspection
 * @Description:压路机实体类.
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-03 09:29:52
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_roller_inspection")
public class BusiQualityRollerInspection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "ROLLER_INSPECTION_ID", nullable = true)
	private Long id;

	/** @Fields originalId : 原始id */
	@Column(name = "ORIGINAL_ID", nullable = false)
	private Long originalId;

	/** @Fields operationSurface : 作业层面 */
	@Column(name = "OPERATION_SURFACE", nullable = true, length = 100)
	private String operationSurface;

	/** @Fields gatherDate : 采集时间 */
	@Column(name = "GATHER_DATE", nullable = true, length = 100)
	private String gatherDate;

	/** @Fields rollingTemperature : 碾压温度 */
	@Column(name = "ROLLING_TEMPERATURE", nullable = true, length = 20)
	private String rollingTemperature;

	/** @Fields rollingSpeed : 碾压速度 */
	@Column(name = "ROLLING_SPEED", nullable = true, length = 20)
	private String rollingSpeed;

	/** @Fields rollingNumber : 碾压次数 */
	@Column(name = "ROLLING_NUMBER", nullable = true)
	private Integer rollingNumber;

	/** @Fields dataType : 0:合格，1:不合格，2：有效，3:无效，4:其他 */
	@Column(name = "DATA_TYPE", nullable = true)
	private Integer dataType;

	/** @Fields orgId : */
	@Column(name = "ORG_ID", nullable = false)
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

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields spreaderRollerSpreaderParentId : 铺摊机、压路机外键id */
	@OneToOne
	@JoinColumn(name = "SPREADER_ROLLER_SPREADER_PARENT_ID", nullable = false)
	private BusiQualitySpreaderRollerSpreaderParent busiQualitySpreaderRollerSpreaderParent;

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

	public void setOperationSurface(String operationSurface) {
		this.operationSurface = operationSurface;
	}

	public String getOperationSurface() {
		return this.operationSurface;
	}

	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
	}

	public String getGatherDate() {
		return this.gatherDate;
	}

	public void setRollingTemperature(String rollingTemperature) {
		this.rollingTemperature = rollingTemperature;
	}

	public String getRollingTemperature() {
		return this.rollingTemperature;
	}

	public void setRollingSpeed(String rollingSpeed) {
		this.rollingSpeed = rollingSpeed;
	}

	public String getRollingSpeed() {
		return this.rollingSpeed;
	}

	public void setRollingNumber(Integer rollingNumber) {
		this.rollingNumber = rollingNumber;
	}

	public Integer getRollingNumber() {
		return this.rollingNumber;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getDataType() {
		return this.dataType;
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

	public BusiQualitySpreaderRollerSpreaderParent getBusiQualitySpreaderRollerSpreaderParent() {
		return busiQualitySpreaderRollerSpreaderParent;
	}

	public void setBusiQualitySpreaderRollerSpreaderParent(
			BusiQualitySpreaderRollerSpreaderParent busiQualitySpreaderRollerSpreaderParent) {
		this.busiQualitySpreaderRollerSpreaderParent = busiQualitySpreaderRollerSpreaderParent;
	}

}
