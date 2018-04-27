package com.huatek.busi.dto.quality;

/**
 * 初期支护净空断面检测修改日志类
 * 
 * @ClassName: BusiQualityPrimarySupportClearanceSectionCheckModifyLog
 * @Description:
 * @author: jordan_li
 * @Email : jordan_li@huatek.com
 * @date: Nov 25, 2017 3:49:31 PM
 * @version: 1.0
 */
public class BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto {
	private Long id;

	private Long priSupCleSecCheckId;// 初期支护净空断面检测id

	private Long orgId;// 标段id

	private String orgName;// 标段名称

	private Long tendersBranchId;// 分部分项(分部分项表ID)

	private String tendersBranchName;// 分部分项名称(父级拼接)

	private String tunnelName;// 隧道名称

	private String startCheckDate;// 开始检测时间

	private String endCheckDate;// 结束检测时间

	private String sectionStakeNo;// 断面桩号

	private Long checkUserId;// 检测人员id

	private String checkUserName;// 检测人员名称

	private String liningType;// 衬砌类型(字典表)

	private String invasionLineStatus;// 侵线状态(字典表)

	private String invasionLineMaxLength;// 侵线最大值(cm)

	private String invasionLinePosition;// 侵线位置

	private String attachmentFile;// 附件

	private Long creater;// 创建人id

	private String createrName;// 创建人姓名

	private String createTime;// 创建时间

	private Long modifer;// 修改人id

	private String modifierName;// 修改人姓名

	private String modifyTime;// 修改时间

	private Long tenantId;// 租户id

	private String qualifiedStatus;// 合格状态(字典表)

	public String getQualifiedStatus() {
		return qualifiedStatus;
	}

	public void setQualifiedStatus(String qualifiedStatus) {
		this.qualifiedStatus = qualifiedStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPriSupCleSecCheckId() {
		return priSupCleSecCheckId;
	}

	public void setPriSupCleSecCheckId(Long priSupCleSecCheckId) {
		this.priSupCleSecCheckId = priSupCleSecCheckId;
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

	public String getTendersBranchName() {
		return tendersBranchName;
	}

	public void setTendersBranchName(String tendersBranchName) {
		this.tendersBranchName = tendersBranchName;
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

	public String getSectionStakeNo() {
		return sectionStakeNo;
	}

	public void setSectionStakeNo(String sectionStakeNo) {
		this.sectionStakeNo = sectionStakeNo;
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

	public String getInvasionLineStatus() {
		return invasionLineStatus;
	}

	public void setInvasionLineStatus(String invasionLineStatus) {
		this.invasionLineStatus = invasionLineStatus;
	}

	public String getInvasionLineMaxLength() {
		return invasionLineMaxLength;
	}

	public void setInvasionLineMaxLength(String invasionLineMaxLength) {
		this.invasionLineMaxLength = invasionLineMaxLength;
	}

	public String getInvasionLinePosition() {
		return invasionLinePosition;
	}

	public void setInvasionLinePosition(String invasionLinePosition) {
		this.invasionLinePosition = invasionLinePosition;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

}
