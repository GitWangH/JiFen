package com.huatek.busi.dto.phicom.score;

import java.util.Date;

public class PhiScoreFlowDto {
	private Long scoreFlowId;
	private Long memberId;
	private String memberName;
	private String score;
	private String sourcePlatform;
	private String scoreAction;
	private String scoreType;
	private Date createTime;
	private String orderCode;
	private String taskName;// 任务项名称
	private Long taskId;
	//积分是否可用 1表示可用，0表示冻结（不可用）
	public int isEnable;
	//订单是否退款，0表示未退款，1表示已退款
	public int isRefund;
	//积分可用时间，目前只针对消费积分10天后积分可用情况
	public Date enableTime;
	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public int getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(int isRefund) {
		this.isRefund = isRefund;
	}

	public Date getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}

	/**
	 * 默认构造器
	 */
	public PhiScoreFlowDto() {
	}

	/**
	 * 生成getter，setter 访问器
	 */
	public void setScoreFlowId(Long scoreFlowId) {
		this.scoreFlowId = scoreFlowId;
	}

	public Long getScoreFlowId() {
		return this.scoreFlowId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScore() {
		return this.score;
	}

	public void setSourcePlatform(String sourcePlatform) {
		this.sourcePlatform = sourcePlatform;
	}

	public String getSourcePlatform() {
		return this.sourcePlatform;
	}

	public void setScoreAction(String scoreAction) {
		this.scoreAction = scoreAction;
	}

	public String getScoreAction() {
		return this.scoreAction;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	public String getScoreType() {
		return this.scoreType;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

}