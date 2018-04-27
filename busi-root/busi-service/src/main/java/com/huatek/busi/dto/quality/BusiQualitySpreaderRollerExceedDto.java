package com.huatek.busi.dto.quality;

import java.util.Date;

import javax.persistence.Column;

public class BusiQualitySpreaderRollerExceedDto {

	private Long spreaderRollerSpreaderParentId;// 铺摊机、压路机外键id

	private Long id;

	private Long originalId;// 原始id

	private String gatherDate;// 采集时间

	private String overproofGrade;// 超标等级

	private String overproofReason;// 超标原因

	private Integer disposeState;// 处理状态 0:未处理,1:已处理

	private String disposeUser;// 处置人

	private String disposeDate;// 处置时间

	private String disposeResult;// 处置结果

	private Integer dataType;// 分类（0：压路机，1：摊铺机）

	private String ukey;// 唯一码

	private String reportAddress;// 报告地址

	private Long orgId;// 机构id

	private String createTime;// 创建时间

	private Long tenantId;// 租户id

	private Integer inspectionId;// 快速处理或整改单的ID

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	public Long getSpreaderRollerSpreaderParentId() {
		return spreaderRollerSpreaderParentId;
	}

	public void setSpreaderRollerSpreaderParentId(
			Long spreaderRollerSpreaderParentId) {
		this.spreaderRollerSpreaderParentId = spreaderRollerSpreaderParentId;
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

	public String getGatherDate() {
		return gatherDate;
	}

	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
	}

	public String getOverproofGrade() {
		return overproofGrade;
	}

	public void setOverproofGrade(String overproofGrade) {
		this.overproofGrade = overproofGrade;
	}

	public String getOverproofReason() {
		return overproofReason;
	}

	public void setOverproofReason(String overproofReason) {
		this.overproofReason = overproofReason;
	}

	public Integer getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(Integer disposeState) {
		this.disposeState = disposeState;
	}

	public String getDisposeUser() {
		return disposeUser;
	}

	public void setDisposeUser(String disposeUser) {
		this.disposeUser = disposeUser;
	}

	public String getDisposeDate() {
		return disposeDate;
	}

	public void setDisposeDate(String disposeDate) {
		this.disposeDate = disposeDate;
	}

	public String getDisposeResult() {
		return disposeResult;
	}

	public void setDisposeResult(String disposeResult) {
		this.disposeResult = disposeResult;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getReportAddress() {
		return reportAddress;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
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

}
