package com.huatek.busi.dto.quality;

/**
 * 初期支护混凝土厚度修改记录数据转化类
 * 
 * @ClassName: BusiQualityPrimarySupportConcreteThicknessCheckModifyLog
 * @Description:
 * @author: jordan_li
 * @Email :
 * @String: 2017-11-30 16:32:12
 * @version: Windows 7
 */

public class BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto {
	private Long id;

	/** @Fields supportConcreteThicknessCheckId : 初期支护混凝土厚度检测 id */
	private Long supportConcreteThicknessCheckId;

	/** @Fields orgId : 标段ID */
	private Long orgId;

	/** @Fields orgName : 标段名称(机构ID，根据机构ID查询名称) */
	private String orgName;

	private Long tendersBranchId;// 分部分项(分部分项表ID)

	private String tunnelName;// 隧道名称

	private String startCheckDate;// 开始检测时间

	private String endCheckDate;// 结束检测时间

	private String beginToEndStakeNo;// 起讫桩号

	private Long checkUserId;// 检测人员ID

	private String checkUserName;// 检测人员名称

	private String liningType;// 衬砌类型

	private String intervalLength;// 区间长度(m)

	private String designIntervalLength;// 设计值(cm)

	private String actualMeasureAverageSpacing;// 实测平均值(cm)

	private String actualMeasureMinLength;// 实测最小值

	private String standardDeviation;// 标准差

	private String qualifiedStatus;// 合格状态(字典表)

	private String attachmentFile;// 附件

	private Long creater;// 创建人id

	private String createrName;// 创建人姓名

	private String createTime;// 创建时间

	private Long modifer;// 修改人id

	private String modifierName;// 修改人姓名

	private String modifyTime;// 修改时间

	private Long tenantId;// 租户id

	private String remark;// 备注

	private Long testPoints;// 检测点数

	private Long qualifiedPoints;// 合格点数

	public Long getSupportConcreteThicknessCheckId() {
		return supportConcreteThicknessCheckId;
	}

	public void setSupportConcreteThicknessCheckId(
			Long supportConcreteThicknessCheckId) {
		this.supportConcreteThicknessCheckId = supportConcreteThicknessCheckId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getTendersBranchId() {
		return tendersBranchId;
	}

	public void setTendersBranchId(Long tendersBranchId) {
		this.tendersBranchId = tendersBranchId;
	}

	public String getTunnelName() {
		return tunnelName;
	}

	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}

	public String getStartCheckDate() {
		return startCheckDate;
	}

	public void setStartCheckDate(String startCheckDate) {
		this.startCheckDate = startCheckDate;
	}

	public String getEndCheckDate() {
		return endCheckDate;
	}

	public void setEndCheckDate(String endCheckDate) {
		this.endCheckDate = endCheckDate;
	}

	public String getBeginToEndStakeNo() {
		return beginToEndStakeNo;
	}

	public void setBeginToEndStakeNo(String beginToEndStakeNo) {
		this.beginToEndStakeNo = beginToEndStakeNo;
	}

	public Long getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getLiningType() {
		return liningType;
	}

	public void setLiningType(String liningType) {
		this.liningType = liningType;
	}

	public String getQualifiedStatus() {
		return qualifiedStatus;
	}

	public void setQualifiedStatus(String qualifiedStatus) {
		this.qualifiedStatus = qualifiedStatus;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
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

	public String getIntervalLength() {
		return intervalLength;
	}

	public void setIntervalLength(String intervalLength) {
		this.intervalLength = intervalLength;
	}

	public String getDesignIntervalLength() {
		return designIntervalLength;
	}

	public void setDesignIntervalLength(String designIntervalLength) {
		this.designIntervalLength = designIntervalLength;
	}

	public String getActualMeasureAverageSpacing() {
		return actualMeasureAverageSpacing;
	}

	public void setActualMeasureAverageSpacing(
			String actualMeasureAverageSpacing) {
		this.actualMeasureAverageSpacing = actualMeasureAverageSpacing;
	}

	public String getActualMeasureMinLength() {
		return actualMeasureMinLength;
	}

	public void setActualMeasureMinLength(String actualMeasureMinLength) {
		this.actualMeasureMinLength = actualMeasureMinLength;
	}

	public String getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(String standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getTestPoints() {
		return testPoints;
	}

	public void setTestPoints(Long testPoints) {
		this.testPoints = testPoints;
	}

	public Long getQualifiedPoints() {
		return qualifiedPoints;
	}

	public void setQualifiedPoints(Long qualifiedPoints) {
		this.qualifiedPoints = qualifiedPoints;
	}

}
