package com.huatek.busi.dto.quality;

/**
 * 整改单列表页面公共字段
 * @author rocky_wei
 *
 */
public class BusiQualityCommonRectificationDto {
	private Long id;
	private String orgId; /* 标段编号 */
	private String orgName; /* 标段名称 */
	private String inspectionCode;/* 整改编号 */
	private String sampleName;/*名称*/
	private String originalType;/*类型*/
	private String checkDate;/*检测时间*/
	private String checkUserName;/*下发人*/
	private String applyTime;/*下发人*/
	private String disposeState;/*处理状态*/
	private String rectificationUrgency;/*紧急程度*/
	private Long inspectionId;//	更改单ID
	private String checkNo;//	检查单编号
	
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getInspectionCode() {
		return inspectionCode;
	}
	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getOriginalType() {
		return originalType;
	}
	public void setOriginalType(String originalType) {
		this.originalType = originalType;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public String getDisposeState() {
		return disposeState;
	}
	public void setDisposeState(String disposeState) {
		this.disposeState = disposeState;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getRectificationUrgency() {
		return rectificationUrgency;
	}
	public void setRectificationUrgency(String rectificationUrgency) {
		this.rectificationUrgency = rectificationUrgency;
	}
	public Long getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	
}
