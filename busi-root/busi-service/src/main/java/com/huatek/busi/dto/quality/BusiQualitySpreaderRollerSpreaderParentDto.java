package com.huatek.busi.dto.quality;

import java.util.Date;

import javax.persistence.Column;

public class BusiQualitySpreaderRollerSpreaderParentDto {

	private BusiQualityRollerInspectionDto busiQualityRollerInspection;// 压路机对象

	private BusiQualitySpreaderInspectionDto busiQualitySpreaderInspection;// 铺摊机对象

	private BusiQualitySpreaderRollerExceedDto busiQualitySpreaderRollerExceed;// 铺摊、压路超标对象

	private Long id;

	private String orgId;// 机构id

	private String orgName;// 机构名称

	private Long tenantId;// 租户id

	private String createTime;// 创建时间

	private String disposeState;// 处理状态（数据字典）

	private String inspectionCode;// 整改编号

	private String operationSurface;// 作业层面

	private String gatherDate;// 采集时间

	private String type;// 作业类型( 压路机0， 铺摊机 1)

	private String overproofReason;// 超标原因

	private String reportAddress;// 检测地址

	private String modifyTime;// 处理时间

	private String overproofGrade;// 超标等级

	private Long modifer;// 修改人ID

	private String modifierName;// 修改人姓名

	private Integer inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private Integer dataType;// 0:合格，1:不合格，2：有效，3:无效，4:其他

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

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

	public BusiQualityRollerInspectionDto getBusiQualityRollerInspection() {
		return busiQualityRollerInspection;
	}

	public void setBusiQualityRollerInspection(
			BusiQualityRollerInspectionDto busiQualityRollerInspection) {
		this.busiQualityRollerInspection = busiQualityRollerInspection;
	}

	public BusiQualitySpreaderInspectionDto getBusiQualitySpreaderInspection() {
		return busiQualitySpreaderInspection;
	}

	public void setBusiQualitySpreaderInspection(
			BusiQualitySpreaderInspectionDto busiQualitySpreaderInspection) {
		this.busiQualitySpreaderInspection = busiQualitySpreaderInspection;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOverproofGrade() {
		return overproofGrade;
	}

	public void setOverproofGrade(String overproofGrade) {
		this.overproofGrade = overproofGrade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public String getOperationSurface() {
		return operationSurface;
	}

	public void setOperationSurface(String operationSurface) {
		this.operationSurface = operationSurface;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOverproofReason() {
		return overproofReason;
	}

	public void setOverproofReason(String overproofReason) {
		this.overproofReason = overproofReason;
	}

	public String getReportAddress() {
		return reportAddress;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public BusiQualitySpreaderRollerExceedDto getBusiQualitySpreaderRollerExceed() {
		return busiQualitySpreaderRollerExceed;
	}

	public void setBusiQualitySpreaderRollerExceed(
			BusiQualitySpreaderRollerExceedDto busiQualitySpreaderRollerExceed) {
		this.busiQualitySpreaderRollerExceed = busiQualitySpreaderRollerExceed;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getGatherDate() {
		return gatherDate;
	}

	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
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

}
