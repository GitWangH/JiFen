package com.huatek.busi.model.quality;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 水稳拌合站超标检测实体类.
 * 
 * @ClassName: BusiQualityWaterStableMixingStationExceed
 * @Description:水稳拌合站超标检测实体类.
 * @author: jordan_li
 * @Email :
 * @date: 2017-11-01 21:03:18
 * @version: Windows 7
 */

@Entity
@Table(name = "busi_quality_water_stable_mixing_station_exceed")
public class BusiQualityWaterStableMixingStationExceed extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade={CascadeType.ALL})  
    @JoinColumn(name="WATER_STABLE_MIXING_STATION_INSPECTION_ID")
	private BusiQualityWaterStableMixingStationInspection busiQualityWaterStableMixingStationInspection;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "WATER_STABLE_MIXING_STATION_EXCEED_ID", nullable = true)
	private Long id;

	/** @Fields resultDate : 结果时间 */
	@Column(name = "RESULT_DATE", nullable = true)
	private Date resultDate;

	/** @Fields aggregate1 : 骨料1 */
	@Column(name = "AGGREGATE_1", nullable = true, length = 50)
	private String aggregate1;

	/** @Fields aggregate2 : 骨料2 */
	@Column(name = "AGGREGATE_2", nullable = true, length = 50)
	private String aggregate2;

	/** @Fields aggregate3 : 骨料3 */
	@Column(name = "AGGREGATE_3", nullable = true, length = 50)
	private String aggregate3;

	/** @Fields aggregate4 : 骨料4 */
	@Column(name = "AGGREGATE_4", nullable = true, length = 50)
	private String aggregate4;

	/** @Fields aggregate5 : 骨料5 */
	@Column(name = "AGGREGATE_5", nullable = true, length = 50)
	private String aggregate5;

	/** @Fields powder1 : 粉料1 */
	@Column(name = "POWDER_1", nullable = true, length = 50)
	private String powder1;

	/** @Fields powder2 : 粉料2 */
	@Column(name = "POWDER_2", nullable = true, length = 50)
	private String powder2;

	/** @Fields waterStabilityInfoCode : 水稳信息编号 */
	@Column(name = "WATER_STABILITY_INFO_CODE", nullable = true, length = 50)
	private String waterStabilityInfoCode;

	/** @Fields type : 类型 */
	@Column(name = "TYPE", nullable = true, length = 50)
	private String type;

	/** @Fields ukey : 唯一码 */
	@Column(name = "UKEY", nullable = true, length = 200)
	private String ukey;

	/** @Fields overproofGrade : 超标等级 1:一级,2：二级, 3:三级 */
	@Column(name = "OVERPROOF_GRADE", nullable = true, length = 50)
	private String overproofGrade;

	/** @Fields overproofReason : 超标原因 */
	@Column(name = "OVERPROOF_REASON", nullable = true, length = 65535)
	private String overproofReason;

	/** @Fields disposeState : 处理状态 0:未处理,1:已处理 */
	@Column(name = "DISPOSE_STATE", nullable = true, length = 50)
	private String disposeState;

	/** @Fields disposeUser : 处理人名称 */
	@Column(name = "DISPOSE_USER", nullable = true, length = 100)
	private String disposeUser;

	/** @Fields disposeDate : 处理时间 */
	@Column(name = "DISPOSE_DATE", nullable = true)
	private Date disposeDate;

	/** @Fields disposeImage : 处理图片 */
	@Column(name = "DISPOSE_IMAGE", nullable = true, length = 65535)
	private String disposeImage;

	/** @Fields disposeContent : 处理内容 */
	@Column(name = "DISPOSE_CONTENT", nullable = true, length = 65535)
	private String disposeContent;

	/** @Fields disposeResult : 处理结果 */
	@Column(name = "DISPOSE_RESULT", nullable = true, length = 65535)
	private String disposeResult;

	/** @Fields examineUser : 审核人 */
	@Column(name = "EXAMINE_USER", nullable = true, length = 100)
	private String examineUser;

	/** @Fields examineDate : 审核时间 */
	@Column(name = "EXAMINE_DATE", nullable = true)
	private Date examineDate;

	/** @Fields examineImage : 审核图片 */
	@Column(name = "EXAMINE_IMAGE", nullable = true, length = 65535)
	private String examineImage;

	/** @Fields examineContent : 审核内容 */
	@Column(name = "EXAMINE_CONTENT", nullable = true, length = 65535)
	private String examineContent;

	/** @Fields examineResult : 审核结果 */
	@Column(name = "EXAMINE_RESULT", nullable = true, length = 65535)
	private String examineResult;

	/** @Fields approvalUser : 审批人 */
	@Column(name = "APPROVAL_USER", nullable = true, length = 100)
	private String approvalUser;

	/** @Fields approvalDate : 审批时间 */
	@Column(name = "APPROVAL_DATE", nullable = true)
	private Date approvalDate;

	/** @Fields approvalImage : 审批图片 */
	@Column(name = "APPROVAL_IMAGE", nullable = true, length = 65535)
	private String approvalImage;

	/** @Fields approvalContent : 审批内容 */
	@Column(name = "APPROVAL_CONTENT", nullable = true, length = 65535)
	private String approvalContent;

	/** @Fields approvalResult : 审批结果 */
	@Column(name = "APPROVAL_RESULT", nullable = true, length = 65535)
	private String approvalResult;

	/** @Fields remarks : 备注 */
	@Column(name = "REMARKS", nullable = true, length = 255)
	private String remarks;

	/** @Fields reportAddress : 报告地址 */
	@Column(name = "REPORT_ADDRESS", nullable = true, length = 255)
	private String reportAddress;

	/** @Fields orgId : 被检测样品所属施工标段ID */
	@Column(name = "ORG_ID", nullable = false, length = 32)
	private String orgId;

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

	/** @Fields isQualitySupervisionBureau : 是否已传给质监局，0未传，1已传，2已传给质监局并做修改 */
	@Column(name = "IS_QUALITY_SUPERVISION_BUREAU", nullable = false)
	private Integer isQualitySupervisionBureau;

	/** @Fields appKey : 接口传数据标识符 */
	@Column(name = "APP_KEY", nullable = false, length = 100)
	private String appKey;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public Date getResultDate() {
		return this.resultDate;
	}

	public void setAggregate1(String aggregate1) {
		this.aggregate1 = aggregate1;
	}

	public String getAggregate1() {
		return this.aggregate1;
	}

	public void setAggregate2(String aggregate2) {
		this.aggregate2 = aggregate2;
	}

	public String getAggregate2() {
		return this.aggregate2;
	}

	public void setAggregate3(String aggregate3) {
		this.aggregate3 = aggregate3;
	}

	public String getAggregate3() {
		return this.aggregate3;
	}

	public void setAggregate4(String aggregate4) {
		this.aggregate4 = aggregate4;
	}

	public String getAggregate4() {
		return this.aggregate4;
	}

	public void setAggregate5(String aggregate5) {
		this.aggregate5 = aggregate5;
	}

	public String getAggregate5() {
		return this.aggregate5;
	}

	public void setPowder1(String powder1) {
		this.powder1 = powder1;
	}

	public String getPowder1() {
		return this.powder1;
	}

	public void setPowder2(String powder2) {
		this.powder2 = powder2;
	}

	public String getPowder2() {
		return this.powder2;
	}

	public void setWaterStabilityInfoCode(String waterStabilityInfoCode) {
		this.waterStabilityInfoCode = waterStabilityInfoCode;
	}

	public String getWaterStabilityInfoCode() {
		return this.waterStabilityInfoCode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setOverproofGrade(String overproofGrade) {
		this.overproofGrade = overproofGrade;
	}

	public String getOverproofGrade() {
		return this.overproofGrade;
	}

	public void setOverproofReason(String overproofReason) {
		this.overproofReason = overproofReason;
	}

	public String getOverproofReason() {
		return this.overproofReason;
	}

	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}

	public String getDisposeState() {
		return this.disposeState;
	}

	public void setDisposeUser(String disposeUser) {
		this.disposeUser = disposeUser;
	}

	public String getDisposeUser() {
		return this.disposeUser;
	}

	public void setDisposeDate(Date disposeDate) {
		this.disposeDate = disposeDate;
	}

	public Date getDisposeDate() {
		return this.disposeDate;
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

	public void setExamineUser(String examineUser) {
		this.examineUser = examineUser;
	}

	public String getExamineUser() {
		return this.examineUser;
	}

	public void setExamineDate(Date examineDate) {
		this.examineDate = examineDate;
	}

	public Date getExamineDate() {
		return this.examineDate;
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

	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}

	public String getApprovalUser() {
		return this.approvalUser;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Date getApprovalDate() {
		return this.approvalDate;
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

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getReportAddress() {
		return this.reportAddress;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId() {
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
	
	public BusiQualityWaterStableMixingStationInspection getBusiQualityWaterStableMixingStationInspection() {
		return busiQualityWaterStableMixingStationInspection;
	}

	public void setBusiQualityWaterStableMixingStationInspection(
			BusiQualityWaterStableMixingStationInspection busiQualityWaterStableMixingStationInspection) {
		this.busiQualityWaterStableMixingStationInspection = busiQualityWaterStableMixingStationInspection;
	}

}
