package com.huatek.busi.dto.phicom.plusmember;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

/**
 * 会员plus订单信息ServiceImpl
 * 
 * @ClassName: PhiPlusMemberOrderDto
 * @Description: TODO
 * @author martin_ju
 * @e_mail martin_ju@huatek.com
 * @date 2018年1月24日
 *
 */
public class PhiPlusMemberOrderDto {
	private Long id;

	/** plus开通订单号 */
	private String orderNo;

	/** @Fields transaction_id :微信支付订单号 */
	private String transactionId;

	/** @Fields plusCode :plusCode */
	private String plusCode;

	/** @Fields create_time :创建时间 */
	private Date createTime;

	/** @Fields pay_money :金额 */
	private String payMoney;

	/** @Fields pay_money :支付时间 */
	private Date payTime;

	/** @Fields is_pay :是否支付 1 已支付 0 未支付 */
	private String isPay;
	/** @Fields member_Id 会员ID */
	private Long memberId;
	
	/** @Fields pay_money :卡金额 */
	private String cardMoney;
	
	
	public String getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(String cardMoney) {
		this.cardMoney = cardMoney;
	}

	/** @Fields plus_code :支付方式 */
	private String payType;
	
	/** @Fields remark 备注 */
	private String remark;
	
	/** @Fields platform 支付平台 */
	private String platForm;
	
	/**@Fields count 总数 */
	private int count; 
	
	/** @Fields count_money 优惠金额 */
	private BigDecimal countMoney;
	
	/** @Fields real_money 实际到帐金额  实际到账金额=支付优惠金额+实际支付金额 */
	private BigDecimal realMoney;
	
	private String telNumber;
	
	
	private String platFormName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getPlusCode() {
		return plusCode;
	}

	public void setPlusCode(String plusCode) {
		this.plusCode = plusCode;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public String getPlatForm() {
		return platForm;
	}

	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public BigDecimal getCountMoney() {
		return countMoney;
	}

	public void setCountMoney(BigDecimal countMoney) {
		this.countMoney = countMoney;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getPlatFormName() {
		return platFormName;
	}

	public void setPlatFormName(String platFormName) {
		this.platFormName = platFormName;
	}

}
