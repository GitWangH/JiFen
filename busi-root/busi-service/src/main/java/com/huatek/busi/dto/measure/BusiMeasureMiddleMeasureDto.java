package com.huatek.busi.dto.measure;


/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiMeasureMiddleMeasure
 * @Description: 中间计量
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:46:44
 * @version: 1.0
 */
public class BusiMeasureMiddleMeasureDto {

	private Long id;
	private Long orgId;// 标段机构对象ID
	private String measureIssueNumber;// 计量期号
	private String measureTotalMoney;// 计量总额(元)
	private String isMeasureAdvance;// 是否只计量动员预付款(N、Y)
	private String remarks;// 备注
	private String measureFile;// 附件
	private String applyTime;// 申请时间
	private Long applyUserId;// 申请人ID
	private String applyUserName;// 申请人名称
	private String approvalStatus;// 审批状态
	private String approvalTime;// 审批完成时间
	private Long approvalUserId;// 审批人ID
	private String approvalUserName;// 审批人名称
	private Long flowInstanceId;// 流程实例ID
	private String flowResult;// 审批结果
	private String flowMessage;// 审批意见
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户id
	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据
	private Long middleMeasureDetailId;// 中间计量明细ID

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return this.orgId;
	}

	public void setMeasureIssueNumber(String measureIssueNumber) {
		this.measureIssueNumber = measureIssueNumber;
	}

	public String getMeasureIssueNumber() {
		return this.measureIssueNumber;
	}

	public void setMeasureTotalMoney(String measureTotalMoney) {
		this.measureTotalMoney = measureTotalMoney;
	}

	public String getMeasureTotalMoney() {
		return this.measureTotalMoney;
	}

	public void setIsMeasureAdvance(String isMeasureAdvance) {
		this.isMeasureAdvance = isMeasureAdvance;
	}

	public String getIsMeasureAdvance() {
		return this.isMeasureAdvance;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setMeasureFile(String measureFile) {
		this.measureFile = measureFile;
	}

	public String getMeasureFile() {
		return this.measureFile;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyTime() {
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

	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getApprovalTime() {
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

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
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

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyTime() {
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

	public Long getMiddleMeasureDetailId() {
		return middleMeasureDetailId;
	}

	public void setMiddleMeasureDetailId(Long middleMeasureDetailId) {
		this.middleMeasureDetailId = middleMeasureDetailId;
	}

}
