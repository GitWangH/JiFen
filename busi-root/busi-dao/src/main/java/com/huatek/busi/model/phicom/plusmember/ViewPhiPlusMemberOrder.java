package com.huatek.busi.model.phicom.plusmember;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * plus会员订单管理
 * 
 * @author mickey_meng
 *
 */
@Entity
@Table(name = "v_phi_plus_member_order")
public class ViewPhiPlusMemberOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "plus_order_id", nullable = false)
	private Long id;

	/** plus开通订单号 */
	@Column(name = "order_no")
	private String orderNo;

	/** @Fields transaction_id :微信支付订单号 */
	@Column(name = "transaction_id")
	private String transactionId;

	/** @Fields create_time :创建时间 */
	@Column(name = "create_time")
	private Date createTime;

	/** @Fields pay_money :金额 */
	@Column(name = "pay_money")
	private String payMoney;

	/** @Fields pay_money :支付时间 */
	@Column(name = "pay_time")
	private Date payTime;

	/** @Fields is_pay :是否支付 1 已支付 0 未支付 */
	@Column(name = "is_pay")
	private String isPay;

	/** @Fields plus_code :plus会员Code */
	@Column(name = "plus_code")
	private String plusCode;

	/** @Fields plus_code :支付方式 */
	@Column(name = "payt_ype")
	private String payType;

	/** @Fields member_Id 会员ID */
	@Column(name = "member_Id")
	private Long memberId;

	@Column(name = "card_money")
	private String cardMoney;

	/** @Fields remark 备注 */
	@Column(name = "remark")
	private String remark;

	/** @Fields platform 支付平台 */
	@Column(name = "plat_form")
	private String platForm;

	/** @Fields count_money 优惠金额 */
	@Column(name = "count_money")
	private BigDecimal countMoney;

	/** @Fields real_money 实际到帐金额 实际到账金额=支付优惠金额+实际支付金额 */
	@Column(name = "real_money")
	private BigDecimal realMoney;

	/** @Fields count 总数 */
	@Column(name = "count")
	private BigDecimal count;

	@Column(name = "tel_number")
	private String telNumber;

	@Column(name = "platform_name")
	private String platFormName;
	

	
	public String getPlatFormName() {
		return platFormName;
	}

	public void setPlatFormName(String platFormName) {
		this.platFormName = platFormName;
	}

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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(String cardMoney) {
		this.cardMoney = cardMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}



	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getPlatForm() {
		return platForm;
	}

	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}

}
