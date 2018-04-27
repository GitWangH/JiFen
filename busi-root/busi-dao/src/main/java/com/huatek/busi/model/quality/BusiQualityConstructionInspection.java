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
import com.huatek.busi.model.contract.BusiContractTendersBranch;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 施工报检实体类.
 * 
 * @ClassName: BusiQualityConstructionInspection
 * @Description:施工报检实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-10 20:43:56
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_construction_inspection")
public class BusiQualityConstructionInspection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "CONSTRUCTION_INSPECTION_ID", nullable = true)
	private Long id;

	/** @Fields orgId : 机构ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg org;

	/** @Fields inspectionCompanyName : 报检单名称 */
	@Column(name = "INSPECTION_COMPANY_NAME", nullable = false, length = 100)
	private String inspectionCompanyName;

	/** @Fields beginConstructionDate : 开工日期 */
	@Column(name = "BEGIN_CONSTRUCTION_DATE", nullable = false)
	private Date beginConstructionDate;

	/** @Fields tendersBranch : 分部分项(分部分项表ID) */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "TENDERS_BRANCH_ID")
	private BusiContractTendersBranch tendersBranch;

	/** @Fields tendersBranchName : 分部分项名称(父级拼接)*/
	@Column(name = "TENDERS_BRANCH_NAME", nullable = false)
	private String tendersBranchName;
	
	/** @Fields isCompleted : 是否已完成(0否、1是) */
	@Column(name = "IS_COMPLETED", nullable = false, length = 2)
	private Integer isCompleted;

	/** @Fields inspectionDate : 报检日期 */
	@Column(name = "INSPECTION_DATE", nullable = false)
	private Date inspectionDate;

	/** @Fields inspectionUserId : 报检人员ID */
	@Column(name = "INSPECTION_USER_ID", nullable = false)
	private Long inspectionUserId;

	/** @Fields inspectionUserName : 报检人员名称 */
	@Column(name = "INSPECTION_USER_NAME", nullable = false, length = 100)
	private String inspectionUserName;

	/** @Fields checkParagraph : 检测段落 */
	@Column(name = "CHECK_PARAGRAPH", nullable = false, length = 100)
	private String checkParagraph;

	/** @Fields inspectionContent : 报检内容 */
	@Column(name = "INSPECTION_CONTENT", nullable = false, length = 255)
	private String inspectionContent;

	/** @Fields attachmentFile : 附件 */
	@Column(name = "ATTACHMENT_FILE", nullable = false, length = 32)
	private String attachmentFile;

	/** @Fields qualityStatus : 质量状态(字典表维护) */
	@Column(name = "QUALITY_STATUS", nullable = false, length = 30)
	private String qualityStatus;

	/** @Fields applyTime : 申请时间 */
	@Column(name = "APPLY_TIME", nullable = false)
	private Date applyTime;

	/** @Fields applyUserId : 申请人ID */
	@Column(name = "APPLY_USER_ID", nullable = false)
	private Long applyUserId;

	/** @Fields applyUserName : 申请人名称 */
	@Column(name = "APPLY_USER_NAME", nullable = false, length = 100)
	private String applyUserName;

	/** @Fields approvalStatus : 审批状态 */
	@Column(name = "APPROVAL_STATUS", nullable = false, length = 30)
	private String approvalStatus;

	/** @Fields approvalTime : 审批完成时间 */
	@Column(name = "APPROVAL_TIME", nullable = false)
	private Date approvalTime;

	/** @Fields approvalUserId : 审批人ID */
	@Column(name = "APPROVAL_USER_ID", nullable = false)
	private Long approvalUserId;

	/** @Fields approvalUserName : 审批人名称 */
	@Column(name = "APPROVAL_USER_NAME", nullable = false, length = 100)
	private String approvalUserName;

	/** @Fields flowInstanceId : 流程实例ID */
	@Column(name = "FLOW_INSTANCE_ID", nullable = false)
	private Long flowInstanceId;

	/** @Fields flowResult : 审批结果 */
	@Column(name = "FLOW_RESULT", nullable = false, length = 30)
	private String flowResult;

	/** @Fields flowMessage : 审批意见 */
	@Column(name = "FLOW_MESSAGE", nullable = false, length = 100)
	private String flowMessage;

	/** @Fields creater : 创建人id */
	@Column(name = "CREATER", nullable = false)
	private Long creater;

	/** @Fields createrName : 创建人姓名 */
	@Column(name = "CREATER_NAME", nullable = false, length = 100)
	private String createrName;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields modifer : 修改人id */
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

	/** @Fields inspectionCode : 整改编号 */
	@Column(name = "INSPECTION_CODE", nullable = false)
	private String inspectionCode;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

	public void setInspectionCompanyName(String inspectionCompanyName) {
		this.inspectionCompanyName = inspectionCompanyName;
	}

	public String getInspectionCompanyName() {
		return this.inspectionCompanyName;
	}

	public void setBeginConstructionDate(Date beginConstructionDate) {
		this.beginConstructionDate = beginConstructionDate;
	}

	public Date getBeginConstructionDate() {
		return this.beginConstructionDate;
	}

	public BusiContractTendersBranch getTendersBranch() {
		return tendersBranch;
	}

	public void setTendersBranch(BusiContractTendersBranch tendersBranch) {
		this.tendersBranch = tendersBranch;
	}

	public Integer getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public Date getInspectionDate() {
		return this.inspectionDate;
	}

	public void setInspectionUserId(Long inspectionUserId) {
		this.inspectionUserId = inspectionUserId;
	}

	public Long getInspectionUserId() {
		return this.inspectionUserId;
	}

	public void setInspectionUserName(String inspectionUserName) {
		this.inspectionUserName = inspectionUserName;
	}

	public String getInspectionUserName() {
		return this.inspectionUserName;
	}

	public void setCheckParagraph(String checkParagraph) {
		this.checkParagraph = checkParagraph;
	}

	public String getCheckParagraph() {
		return this.checkParagraph;
	}

	public void setInspectionContent(String inspectionContent) {
		this.inspectionContent = inspectionContent;
	}

	public String getInspectionContent() {
		return this.inspectionContent;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getAttachmentFile() {
		return this.attachmentFile;
	}

	public void setQualityStatus(String qualityStatus) {
		this.qualityStatus = qualityStatus;
	}

	public String getQualityStatus() {
		return this.qualityStatus;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Long getApplyUserId() {
		return this.applyUserId;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyUserName() {
		return this.applyUserName;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Date getApprovalTime() {
		return this.approvalTime;
	}

	public void setApprovalUserId(Long approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public Long getApprovalUserId() {
		return this.approvalUserId;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public String getApprovalUserName() {
		return this.approvalUserName;
	}

	public void setFlowInstanceId(Long flowInstanceId) {
		this.flowInstanceId = flowInstanceId;
	}

	public Long getFlowInstanceId() {
		return this.flowInstanceId;
	}

	public void setFlowResult(String flowResult) {
		this.flowResult = flowResult;
	}

	public String getFlowResult() {
		return this.flowResult;
	}

	public void setFlowMessage(String flowMessage) {
		this.flowMessage = flowMessage;
	}

	public String getFlowMessage() {
		return this.flowMessage;
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

	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public String getTendersBranchName() {
		return tendersBranchName;
	}

	public void setTendersBranchName(String tendersBranchName) {
		this.tendersBranchName = tendersBranchName;
	}
	
}
