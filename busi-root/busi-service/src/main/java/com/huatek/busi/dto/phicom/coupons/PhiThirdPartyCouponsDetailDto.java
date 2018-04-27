package com.huatek.busi.dto.phicom.coupons;

/**
 * 第三方券明细Dto类
 * 
 * @author jordan_li
 */

public class PhiThirdPartyCouponsDetailDto {

	private Long id;

	private String coupId;// 优惠券id

	private String coupCode;// 劵码

	private String exchangeStatus;// 兑换状态(1表示已兑换，2表示未兑换)

	private String coupStatus;// 券状态（1表示已使用，2表示未使用，3表示已过期）

	private Long memberId;// 会员id

	private String memberName;// 绑定会员

	private String startTime;// 开始时间

	private String endTime;// 结束时间

	private String cpnsValid;// 券有效期（绝对有效期——展示具体到期日，相对有效期——展示有效天数）

	private Long coupUid;// 用户的UID

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoupId() {
		return coupId;
	}

	public void setCoupId(String coupId) {
		this.coupId = coupId;
	}

	public String getCoupCode() {
		return coupCode;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}

	public String getExchangeStatus() {
		return exchangeStatus;
	}

	public void setExchangeStatus(String exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

	public String getCoupStatus() {
		return coupStatus;
	}

	public void setCoupStatus(String coupStatus) {
		this.coupStatus = coupStatus;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public String getCpnsValid() {
		return cpnsValid;
	}

	public void setCpnsValid(String cpnsValid) {
		this.cpnsValid = cpnsValid;
	}

	public Long getCoupUid() {
		return coupUid;
	}

	public void setCoupUid(Long coupUid) {
		this.coupUid = coupUid;
	}

}
