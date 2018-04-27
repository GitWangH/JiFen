package com.huatek.busi.dto.quality;

public class BusiQualityRoutingInspectionDto {
	private Long id;

	private String orgName;// 被通报单位名称

	private Long orgId;// 被通报单位ORG_ID

	private String patrolType;// 巡检类型（数据字典）

	private String checkNumber;// 检查编号

	private String checkTime;// 检查时间

	private String checkResults;// 检查结果（0 不合格 1 合格）

	private String urgency;// 紧急程度（数据字典）

	private String rectificationPeriod;// 整改期限

	private String personLiable;// 责任人

	private String checkPerson;// 检查人员

	private String checkContent;// 检查内容

	private String question;// 存在问题

	private String rectificationRequirements;// 整改要求

	private String violationPenalty;// 违规处罚

	private String punishmentSuggestion;// 处罚建议

	private String enclosure;// 附件

	private Long creater;// 创建人ID

	private String createrName;// 创建人姓名

	private String createTime;// 创建时间

	private Long modifer;// 修改人ID

	private String modifierName;// 修改人姓名

	private String modifyTime;// 修改时间

	private Long tenantId;// 租户id

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private String isQualitySupervisionBureau;// 是否已传给质监局，0未传，1已传

	private String disposeState;// 处理状态

	private String disposeDate;// 整改时间

	private String inspectionType;// 整改类型 0 快速处理 1 整改单

	private Long inspectionId;// 快速处理或整改单的ID

	private String inspectionCode;// 整改（快捷处理）编号

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public String getDisposeState() {
		return disposeState;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getDisposeDate() {
		return disposeDate;
	}

	public void setDisposeDate(String disposeDate) {
		this.disposeDate = disposeDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPatrolType() {
		return patrolType;
	}

	public void setPatrolType(String patrolType) {
		this.patrolType = patrolType;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getCheckResults() {
		return checkResults;
	}

	public void setCheckResults(String checkResults) {
		this.checkResults = checkResults;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getRectificationPeriod() {
		return rectificationPeriod;
	}

	public void setRectificationPeriod(String rectificationPeriod) {
		this.rectificationPeriod = rectificationPeriod;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRectificationRequirements() {
		return rectificationRequirements;
	}

	public void setRectificationRequirements(String rectificationRequirements) {
		this.rectificationRequirements = rectificationRequirements;
	}

	public String getViolationPenalty() {
		return violationPenalty;
	}

	public void setViolationPenalty(String violationPenalty) {
		this.violationPenalty = violationPenalty;
	}

	public String getPunishmentSuggestion() {
		return punishmentSuggestion;
	}

	public void setPunishmentSuggestion(String punishmentSuggestion) {
		this.punishmentSuggestion = punishmentSuggestion;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsQualitySupervisionBureau() {
		return isQualitySupervisionBureau;
	}

	public void setIsQualitySupervisionBureau(String isQualitySupervisionBureau) {
		this.isQualitySupervisionBureau = isQualitySupervisionBureau;
	}

	public String getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}

}
