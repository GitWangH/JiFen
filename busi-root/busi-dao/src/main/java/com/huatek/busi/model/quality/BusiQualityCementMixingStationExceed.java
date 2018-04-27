package com.huatek.busi.model.quality;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 水泥拌合站超标实体类.
 * 
 * @ClassName: BusiQualityCementMixingStationExceed
 * @Description:水泥拌合站超标实体类
 * @author: jordan_li
 * @Email :
 * @date: 2017-10-30 14:18:25
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_cement_mixing_station_exceed")
public class BusiQualityCementMixingStationExceed extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade={CascadeType.ALL})  
    @JoinColumn(name="CEMENT_MIXING_STATION_INSPECTION_ID")
	private BusiQualityCementMixingStationInspection busiQualityCementMixingStationInspection;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "CEMENT_MIXING_STATION_EXCEED_ID", nullable = true)
	private Long id;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = false, length = 200)
	private String ukey;

	/** @Fields isExcessive : 0:未超标, 1：已超标 */
	@Column(name = "IS_EXCESSIVE", nullable = false, length = 100)
	private String isExcessive;

	/** @Fields excessiveGrade : 超标等级(1:一级,2：二级, 3:三级) */
	@Column(name = "EXCESSIVE_GRADE", nullable = false, length = 100)
	private String excessiveGrade;

	/** @Fields excessiveReason : 超标原因 */
	@Column(name = "EXCESSIVE_REASON", nullable = false, length = 65535)
	private String excessiveReason;

	/** @Fields disposeState : 是否处理　0:未处理 */
	@Column(name = "DISPOSE_STATE", nullable = false, length = 100)
	private String disposeState;

	/** @Fields disposePerson : 处理人 */
	@Column(name = "DISPOSE_PERSON", nullable = false, length = 100)
	private String disposePerson;

	/** @Fields disposeTime : 处理时间 */
	@Column(name = "DISPOSE_TIME", nullable = false, length = 50)
	private String disposeTime;

	/** @Fields disposeImage : 处理图片(多张图片时用英文的逗号分隔,byte数组) */
	@Column(name = "DISPOSE_IMAGE", nullable = false, length = 65535)
	private String disposeImage;

	/** @Fields disposeContent : 处理内容 */
	@Column(name = "DISPOSE_CONTENT", nullable = false, length = 65535)
	private String disposeContent;

	/** @Fields disposeResult : 处理结果 */
	@Column(name = "DISPOSE_RESULT", nullable = false, length = 65535)
	private String disposeResult;

	/** @Fields examinePerson : 审核人 */
	@Column(name = "EXAMINE_PERSON", nullable = false, length = 500)
	private String examinePerson;

	/** @Fields examineDatetime : 审核时间 */
	@Column(name = "EXAMINE_DATETIME", nullable = false, length = 100)
	private String examineDatetime;

	/** @Fields examineImage : 审核图片（多张图片时用英文的逗号分隔,byte数组） */
	@Column(name = "EXAMINE_IMAGE", nullable = false, length = 65535)
	private String examineImage;

	/** @Fields examineContent : 审核内容 */
	@Column(name = "EXAMINE_CONTENT", nullable = false, length = 65535)
	private String examineContent;

	/** @Fields examineResult : 审核结果 */
	@Column(name = "EXAMINE_RESULT", nullable = false, length = 65535)
	private String examineResult;

	/** @Fields approvalPerson : 审批人 */
	@Column(name = "APPROVAL_PERSON", nullable = false, length = 200)
	private String approvalPerson;

	/** @Fields approvalDatetime : 审批时间 */
	@Column(name = "APPROVAL_DATETIME", nullable = false, length = 100)
	private String approvalDatetime;

	/** @Fields approvalImage : 审批图片（多张图片时用英文的逗号分隔,byte数组） */
	@Column(name = "APPROVAL_IMAGE", nullable = false, length = 65535)
	private String approvalImage;

	/** @Fields approvalContent : 审批内容 */
	@Column(name = "APPROVAL_CONTENT", nullable = false, length = 65535)
	private String approvalContent;

	/** @Fields approvalResult : 审批结果 */
	@Column(name = "APPROVAL_RESULT", nullable = false, length = 65535)
	private String approvalResult;

	/** @Fields remarks : 备注 */
	@Column(name = "REMARKS", nullable = false, length = 500)
	private String remarks;

	/** @Fields desContent : 描述 */
	@Column(name = "DES_CONTENT", nullable = false, length = 500)
	private String desContent;

	/** @Fields reportAddress : 报告地址 */
	@Column(name = "REPORT_ADDRESS", nullable = false, length = 100)
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

	/** @Fields appKey : 接口传数据标识符 */
	@Column(name = "APP_KEY", nullable = false, length = 100)
	private String appKey;

	public BusiQualityCementMixingStationInspection getBusiQualityCementMixingStationInspection() {
		return busiQualityCementMixingStationInspection;
	}

	public void setBusiQualityCementMixingStationInspection(
			BusiQualityCementMixingStationInspection busiQualityCementMixingStationInspection) {
		this.busiQualityCementMixingStationInspection = busiQualityCementMixingStationInspection;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setIsExcessive(String isExcessive) {
		this.isExcessive = isExcessive;
	}

	public String getIsExcessive() {
		return this.isExcessive;
	}

	public void setExcessiveGrade(String excessiveGrade) {
		this.excessiveGrade = excessiveGrade;
	}

	public String getExcessiveGrade() {
		return this.excessiveGrade;
	}

	public void setExcessiveReason(String excessiveReason) {
		this.excessiveReason = excessiveReason;
	}

	public String getExcessiveReason() {
		return this.excessiveReason;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getDisposeState() {
		return this.disposeState;
	}

	public void setDisposePerson(String disposePerson) {
		this.disposePerson = disposePerson;
	}

	public String getDisposePerson() {
		return this.disposePerson;
	}

	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}

	public String getDisposeTime() {
		return this.disposeTime;
	}

	public void setDisposeImage(String disposeImage) {
		this.disposeImage = disposeImage;
	}

	public String getDisposeImage() {
		return this.disposeImage;
	}

	public void setDisposeContent(String disposeContent) {
		this.disposeContent = disposeContent;
	}

	public String getDisposeContent() {
		return this.disposeContent;
	}

	public void setDisposeResult(String disposeResult) {
		this.disposeResult = disposeResult;
	}

	public String getDisposeResult() {
		return this.disposeResult;
	}

	public void setExaminePerson(String examinePerson) {
		this.examinePerson = examinePerson;
	}

	public String getExaminePerson() {
		return this.examinePerson;
	}

	public void setExamineDatetime(String examineDatetime) {
		this.examineDatetime = examineDatetime;
	}

	public String getExamineDatetime() {
		return this.examineDatetime;
	}

	public void setExamineImage(String examineImage) {
		this.examineImage = examineImage;
	}

	public String getExamineImage() {
		return this.examineImage;
	}

	public void setExamineContent(String examineContent) {
		this.examineContent = examineContent;
	}

	public String getExamineContent() {
		return this.examineContent;
	}

	public void setExamineResult(String examineResult) {
		this.examineResult = examineResult;
	}

	public String getExamineResult() {
		return this.examineResult;
	}

	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

	public String getApprovalPerson() {
		return this.approvalPerson;
	}

	public void setApprovalDatetime(String approvalDatetime) {
		this.approvalDatetime = approvalDatetime;
	}

	public String getApprovalDatetime() {
		return this.approvalDatetime;
	}

	public void setApprovalImage(String approvalImage) {
		this.approvalImage = approvalImage;
	}

	public String getApprovalImage() {
		return this.approvalImage;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	public String getApprovalContent() {
		return this.approvalContent;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getApprovalResult() {
		return this.approvalResult;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setDesContent(String desContent) {
		this.desContent = desContent;
	}

	public String getDesContent() {
		return this.desContent;
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

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppKey() {
		return this.appKey;
	}

}
