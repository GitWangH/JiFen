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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 巡检维护实体类.
 * 
 * @ClassName: BusiQualityRoutingInspection
 * @Description:巡检维护实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-06 20:50:23
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_routing_inspection")
public class BusiQualityRoutingInspection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "PATROL_MAINTENANCE_ID", nullable = true)
	private Long id;

	/** @Fields patrolType : 巡检类型（数据字典） */
	@Column(name = "PATROL_TYPE", nullable = false, length = 30)
	private String patrolType;

	/** @Fields checkNumber : 检查编号 */
	@Column(name = "CHECK_NUMBER", nullable = false, length = 100)
	private String checkNumber;

	/** @Fields checkTime : 检查时间 */
	@Column(name = "CHECK_TIME", nullable = false)
	private Date checkTime;

	/** @Fields checkResults : 检查结果（0 不合格 1 合格） */
	@Column(name = "CHECK_RESULTS", nullable = false, length = 30)
	private Integer checkResults;

	/** @Fields urgency : 紧急程度（数据字典） */
	@Column(name = "URGENCY", nullable = false, length = 30)
	private String urgency;

	/** @Fields rectificationPeriod : 整改期限 */
	@Column(name = "RECTIFICATION_PERIOD", nullable = false, length = 30)
	private String rectificationPeriod;

	/** @Fields personLiable : 责任人 */
	@Column(name = "PERSON_LIABLE", nullable = false, length = 100)
	private String personLiable;

	/** @Fields checkPerson : 检查人员 */
	@Column(name = "CHECK_PERSON", nullable = false, length = 100)
	private String checkPerson;

	/** @Fields checkContent : 检查内容 */
	@Column(name = "CHECK_CONTENT", nullable = false, length = 65535)
	private String checkContent;

	/** @Fields question : 存在问题 */
	@Column(name = "QUESTION", nullable = false, length = 65535)
	private String question;

	/** @Fields rectificationRequirements : 整改要求 */
	@Column(name = "RECTIFICATION_REQUIREMENTS", nullable = false, length = 65535)
	private String rectificationRequirements;

	/** @Fields violationPenalty : 违规处罚 */
	@Column(name = "VIOLATION_PENALTY", nullable = false, length = 65535)
	private String violationPenalty;

	/** @Fields punishmentSuggestion : 处罚建议 */
	@Column(name = "PUNISHMENT_SUGGESTION", nullable = false, length = 65535)
	private String punishmentSuggestion;

	/** @Fields enclosure : 附件 */
	@Column(name = "ENCLOSURE", nullable = false, length = 100)
	private String enclosure;

	/** @Fields orgId : 机构ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg org;

	/** @Fields creater : 创建人ID */
	@Column(name = "CREATER", nullable = false)
	private Long creater;

	/** @Fields createrName : 创建人姓名 */
	@Column(name = "CREATER_NAME", nullable = false, length = 100)
	private String createrName;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields modifer : 修改人ID */
	@Column(name = "MODIFER", nullable = false)
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	@Column(name = "MODIFIER_NAME", nullable = false, length = 100)
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;

	/** @Fields tenantId : 租户id */
	@Column(name = "TENANT_ID", nullable = false)
	private Long tenantId;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields disposeState : 处理状态 */
	@Column(name = "DISPOSE_STATE", nullable = false, length = 100)
	private String disposeState;

	/** @Fields disposeDate : 整改时间 */
	@Column(name = "DISPOSE_DATE", nullable = false)
	private Date disposeDate;

	/** @Fields inspectionType : 整改类型 0 快速处理 1 整改单 */
	@Column(name = "INSPECTION_TYPE", nullable = false)
	private Integer inspectionType;

	/** @Fields inspectionId : 快速处理或整改单的ID */
	@Column(name = "INSPECTION_ID", nullable = false)
	private Long inspectionId;

	/** @Fields inspectionCode : 整改（快捷处理）编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;
	

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

	public Date getDisposeDate() {
		return disposeDate;
	}

	public void setDisposeDate(Date disposeDate) {
		this.disposeDate = disposeDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setPatrolType(String patrolType) {
		this.patrolType = patrolType;
	}

	public String getPatrolType() {
		return this.patrolType;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public Integer getCheckResults() {
		return checkResults;
	}

	public void setCheckResults(Integer checkResults) {
		this.checkResults = checkResults;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getUrgency() {
		return this.urgency;
	}

	public void setRectificationPeriod(String rectificationPeriod) {
		this.rectificationPeriod = rectificationPeriod;
	}

	public String getRectificationPeriod() {
		return this.rectificationPeriod;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getPersonLiable() {
		return this.personLiable;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public String getCheckPerson() {
		return this.checkPerson;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getCheckContent() {
		return this.checkContent;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setRectificationRequirements(String rectificationRequirements) {
		this.rectificationRequirements = rectificationRequirements;
	}

	public String getRectificationRequirements() {
		return this.rectificationRequirements;
	}

	public void setViolationPenalty(String violationPenalty) {
		this.violationPenalty = violationPenalty;
	}

	public String getViolationPenalty() {
		return this.violationPenalty;
	}

	public void setPunishmentSuggestion(String punishmentSuggestion) {
		this.punishmentSuggestion = punishmentSuggestion;
	}

	public String getPunishmentSuggestion() {
		return this.punishmentSuggestion;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getEnclosure() {
		return this.enclosure;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Long getCreater() {
		return this.creater;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreaterName() {
		return this.createrName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public Long getModifer() {
		return this.modifer;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifierName() {
		return this.modifierName;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTenantId() {
		return this.tenantId;
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
