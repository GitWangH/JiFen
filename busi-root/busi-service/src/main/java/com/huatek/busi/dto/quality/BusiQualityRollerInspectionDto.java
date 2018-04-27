package com.huatek.busi.dto.quality;

import java.util.Date;

import javax.persistence.Column;

public class BusiQualityRollerInspectionDto {

	private Long id;

	private Long originalId;// 原始id

	private String operationSurface;// 作业层面

	private String gatherDate;// 采集时间

	private String rollingTemperature;// 碾压温度

	private String rollingSpeed;// 碾压速度

	private Integer rollingNumber;// 碾压次数

	private Integer dataType;// 0:合格，1:不合格，2：有效，3:无效，4:其他

	private Long orgId;// 机构id

	private Long orgName;// 机构名称

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Integer inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	private Long spreaderRollerSpreaderParentId;// 铺摊机、压路机外键id


	public Long getOrgName() {
		return orgName;
	}

	public void setOrgName(Long orgName) {
		this.orgName = orgName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public String getOperationSurface() {
		return operationSurface;
	}

	public void setOperationSurface(String operationSurface) {
		this.operationSurface = operationSurface;
	}

	public String getGatherDate() {
		return gatherDate;
	}

	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
	}

	public String getRollingTemperature() {
		return rollingTemperature;
	}

	public void setRollingTemperature(String rollingTemperature) {
		this.rollingTemperature = rollingTemperature;
	}

	public String getRollingSpeed() {
		return rollingSpeed;
	}

	public void setRollingSpeed(String rollingSpeed) {
		this.rollingSpeed = rollingSpeed;
	}

	public Integer getRollingNumber() {
		return rollingNumber;
	}

	public void setRollingNumber(Integer rollingNumber) {
		this.rollingNumber = rollingNumber;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}

	public Integer getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsQualitySupervisionBureau() {
		return isQualitySupervisionBureau;
	}

	public void setIsQualitySupervisionBureau(Integer isQualitySupervisionBureau) {
		this.isQualitySupervisionBureau = isQualitySupervisionBureau;
	}

	public Long getSpreaderRollerSpreaderParentId() {
		return spreaderRollerSpreaderParentId;
	}

	public void setSpreaderRollerSpreaderParentId(
			Long spreaderRollerSpreaderParentId) {
		this.spreaderRollerSpreaderParentId = spreaderRollerSpreaderParentId;
	}

}
