package com.huatek.busi.dto.contract;


/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractContractChange
 * @Description: 合同变更
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:12:22
 * @version: 1.0
 */
public class BusiContractContractChangeDto {

	private Long id;
	private Long tendersContractId;// 合同主键ID
	private String changeLevel;// 变更等级(字典表：一般、重大)
	private String changeData;// 变更日期
	private String changeType;// 变更类型(字典表：新增、取消、纠错、位置变更)
	private String changeProjectName;// 变更项目
	private String changeOrderNo;// 变更令号
	private String contractDrawings;// 合同图纸
	private String contractChangedDrawings;// 变更后图纸
	private String stakeNo;// 桩号
	private String delaySchedule;// 变更延长工期
	private String replyChangeStatus;// 未批复变更（是否批复：未批复、已批复）
	private String changeMoney;// 变更金额
	private String changeContent;// 变更内容
	private String changeReason;// 变更原因
	private String changeFile;// 附件
	private String meetingSummaryFile;// 会议纪要
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
	private Long orgId;// 组织机构ID
	private String orgName;// 组织机构名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTendersContractId() {
		return tendersContractId;
	}

	public void setTendersContractId(Long tendersContractId) {
		this.tendersContractId = tendersContractId;
	}

	public String getChangeLevel() {
		return changeLevel;
	}

	public void setChangeLevel(String changeLevel) {
		this.changeLevel = changeLevel;
	}

	public String getChangeData() {
		return changeData;
	}

	public void setChangeData(String changeData) {
		this.changeData = changeData;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeProjectName() {
		return changeProjectName;
	}

	public void setChangeProjectName(String changeProjectName) {
		this.changeProjectName = changeProjectName;
	}

	public String getChangeOrderNo() {
		return changeOrderNo;
	}

	public void setChangeOrderNo(String changeOrderNo) {
		this.changeOrderNo = changeOrderNo;
	}

	public String getContractDrawings() {
		return contractDrawings;
	}

	public void setContractDrawings(String contractDrawings) {
		this.contractDrawings = contractDrawings;
	}

	public String getContractChangedDrawings() {
		return contractChangedDrawings;
	}

	public void setContractChangedDrawings(String contractChangedDrawings) {
		this.contractChangedDrawings = contractChangedDrawings;
	}

	public String getStakeNo() {
		return stakeNo;
	}

	public void setStakeNo(String stakeNo) {
		this.stakeNo = stakeNo;
	}

	public String getDelaySchedule() {
		return delaySchedule;
	}

	public void setDelaySchedule(String delaySchedule) {
		this.delaySchedule = delaySchedule;
	}

	public String getReplyChangeStatus() {
		return replyChangeStatus;
	}

	public void setReplyChangeStatus(String replyChangeStatus) {
		this.replyChangeStatus = replyChangeStatus;
	}

	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}

	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getChangeFile() {
		return changeFile;
	}

	public void setChangeFile(String changeFile) {
		this.changeFile = changeFile;
	}

	public String getMeetingSummaryFile() {
		return meetingSummaryFile;
	}

	public void setMeetingSummaryFile(String meetingSummaryFile) {
		this.meetingSummaryFile = meetingSummaryFile;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public Long getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Long getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(Long approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public String getApprovalUserName() {
		return approvalUserName;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public Long getFlowInstanceId() {
		return flowInstanceId;
	}

	public void setFlowInstanceId(Long flowInstanceId) {
		this.flowInstanceId = flowInstanceId;
	}

	public String getFlowResult() {
		return flowResult;
	}

	public void setFlowResult(String flowResult) {
		this.flowResult = flowResult;
	}

	public String getFlowMessage() {
		return flowMessage;
	}

	public void setFlowMessage(String flowMessage) {
		this.flowMessage = flowMessage;
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

}
