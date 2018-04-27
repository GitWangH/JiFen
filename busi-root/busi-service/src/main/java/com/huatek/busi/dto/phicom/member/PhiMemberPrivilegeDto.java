package com.huatek.busi.dto.phicom.member;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 会员特权管理Dto类
 * 
 * @author jordan_li
 *
 */
public class PhiMemberPrivilegeDto {
	private Long id;

	private String memberGradeId;// 会员等级id

	private String memberGradeName;// 会员等级名称

	private String plusId;// PLUS会员编码ID

	private String rightCode;// 权限编码(全局唯一)

	private String rightName;// 权限名称

	private String rightExplain;// 权限说明

	private String rightDeadline;// 权限时间(0：永久(默认） 1：设置时间段）

	private Integer state;// 是否开启( 0 : 否 ， 1 : 是 )

	private boolean checkIn;// 签到(0：否 ，1：是）

	private boolean pay;// 商城消费（0：否，1：是）

	private boolean comment;// 评论（0：否 ，1：是）

	private boolean forum;// 论坛（0：否，1：是）

	private String privilegeType;// 权限类型（1: 消费返积分特权 , 2：生日特权）

	private String doubleSet;// 翻倍设置（大于1的数字，保留一位小数）

	private Integer extraAdd;// 额外增加积分数（大于1的整数）

	private Integer scoreOrMutiply;// 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分）

	private String startTime;// 开始时间

	private String endTime;// 结束时间

	private Long modifer;// 修改人ID

	private String modifierName;// 修改人姓名

	private String modifyTime;// 修改时间

	private String memberGrade;// 会员等级

	private String payScoreMultiple;// 消费返积分倍数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberGradeId() {
		return memberGradeId;
	}

	public void setMemberGradeId(String memberGradeId) {
		this.memberGradeId = memberGradeId;
	}

	public String getMemberGradeName() {
		return memberGradeName;
	}

	public void setMemberGradeName(String memberGradeName) {
		this.memberGradeName = memberGradeName;
	}

	public String getPlusId() {
		return plusId;
	}

	public void setPlusId(String plusId) {
		this.plusId = plusId;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightExplain() {
		return rightExplain;
	}

	public void setRightExplain(String rightExplain) {
		this.rightExplain = rightExplain;
	}

	public String getRightDeadline() {
		return rightDeadline;
	}

	public void setRightDeadline(String rightDeadline) {
		this.rightDeadline = rightDeadline;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	public boolean isPay() {
		return pay;
	}

	public void setPay(boolean pay) {
		this.pay = pay;
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public boolean isForum() {
		return forum;
	}

	public void setForum(boolean forum) {
		this.forum = forum;
	}

	public String getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public String getDoubleSet() {
		return doubleSet;
	}

	public void setDoubleSet(String doubleSet) {
		this.doubleSet = doubleSet;
	}

	public Integer getExtraAdd() {
		return extraAdd;
	}

	public void setExtraAdd(Integer extraAdd) {
		this.extraAdd = extraAdd;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public Integer getScoreOrMutiply() {
		return scoreOrMutiply;
	}

	public void setScoreOrMutiply(Integer scoreOrMutiply) {
		this.scoreOrMutiply = scoreOrMutiply;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public String getPayScoreMultiple() {
		return payScoreMultiple;
	}

	public void setPayScoreMultiple(String payScoreMultiple) {
		this.payScoreMultiple = payScoreMultiple;
	}

}