package com.huatek.busi.dto.phicom.coupons;

/**
 * 优惠券明细Dto
 * 
 * @author jordan_li
 *
 */

public class PhiCouponsDetailDto {

	private Long cpnsId;

	private Long coupWayId;// 优惠券方案id

	private String coupCode;// 劵码

	private String exchangeStatus;// 兑换状态(1表示已兑换，2表示未兑换)

	private String coupStatus;// 券状态（1表示已使用，2表示未使用，3表示已过期）

	private Long memberId;// 会员id

	private String memberName;// 绑定会员名

	private String startTime;// 开始时间

	private String endTime;// 结束时间

	private Long coupUid;// 用户的UID

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getCpnsId() {
		return cpnsId;
	}

	public void setCpnsId(Long cpnsId) {
		this.cpnsId = cpnsId;
	}

	public Long getCoupWayId() {
		return coupWayId;
	}

	public void setCoupWayId(Long coupWayId) {
		this.coupWayId = coupWayId;
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

	public Long getCoupUid() {
		return coupUid;
	}

	public void setCoupUid(Long coupUid) {
		this.coupUid = coupUid;
	}

}
