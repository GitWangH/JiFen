package com.huatek.busi.dto.phicom.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.huatek.busi.dto.phicom.member.PhiMemberDto;

public class PhiOrderDto {
	private Long memberId;
	private Long uid;
	private Long productId;;
	private Long id;
	private String orderCode;
	private Date createTime;
	private Date endTime;
	private String status;
	private String isclose;
	private String logisticType;
	private String logisticCode;
	private String isdistribut;
	private String number;
	private String payType;
	private String userName;
	private String isdelete;
	//序号
	private Long code;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	// 要显示
	private String productCode;
	private String productName;
	private String productClass;
	private BigDecimal marketPrice;
	private BigDecimal money;
	private BigDecimal productCount;
	private BigDecimal score;
	private String productImg;
	private String province;
	private String city;
	private String area;
	private String street;
	private String addressDetail;
	private String receiverName;
	private String tel;
	private String productType;
	private String isPlus;
	private String nu;
	// 是否开奖
	private String winnerStatus;

	/** @Fields transaction_id :交易流水号 */
	private String transactionId;

	/** @Fields pay_money :支付时间 */
	private Date payTime;
	/*
	 * public String getUserName() { return userName; }
	 * 
	 * public void setUserName(PhiMemberDto phiMember) { this.userName =
	 * phiMember.getUserName(); }
	 */

	public PhiOrderinfoDto phiOrderInfo;
	private PhiMemberDto phiMember;
	private PhiLogisticDto phiLogistic;

	
	public String getIsPlus() {
		return isPlus;
	}

	public void setIsPlus(String isPlus) {
		this.isPlus = isPlus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 默认构造器
	 */
	public PhiOrderDto() {
	}

	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 */
	/*
	 * 此方法生成有误（不需要） public PhiOrderDto(PhiOrder model) { this.memberId =
	 * model.getMemberId(); this.productDetailId = model.getProductDetailId();
	 * this.orderId = model.getOrderId(); this.orderCode = model.getOrderCode();
	 * this.createTime = model.getCreateTime(); this.endTime =
	 * model.getEndTime(); this.status = model.getStatus(); this.isclose =
	 * model.getIsclose(); this.logisticType = model.getLogisticType();
	 * this.logisticCode = model.getLogisticCode(); }
	 */
	/**
	 * 生成getter，setter 访问器
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	/*
	 * public Date getPayTime() { return payTime; }
	 * 
	 * public void setPayTime(Date payTime) { this.payTime = payTime; }
	 */

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setIsclose(String isclose) {
		this.isclose = isclose;
	}

	public String getIsclose() {
		return this.isclose;
	}

	public void setLogisticType(String logisticType) {
		this.logisticType = logisticType;
	}

	public String getLogisticType() {
		return this.logisticType;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public String getLogisticCode() {
		return this.logisticCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIsdistribut() {
		return isdistribut;
	}

	public void setIsdistribut(String isdistribut) {
		this.isdistribut = isdistribut;
	}

	public PhiMemberDto getPhiMember() {
		return phiMember;
	}

	public void setPhiMember(PhiMemberDto phiMember) {
		this.phiMember = phiMember;
	}

	public PhiOrderinfoDto getPhiOrderInfo() {
		return phiOrderInfo;
	}

	public void setPhiOrderInfo(PhiOrderinfoDto phiOrderInfo) {
		this.phiOrderInfo = phiOrderInfo;
	}

	public PhiLogisticDto getPhiLogistic() {
		return phiLogistic;
	}

	public void setPhiLogistic(PhiLogisticDto phiLogistic) {
		this.phiLogistic = phiLogistic;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal bigDecimal) {
		this.money = bigDecimal;
	}

	public BigDecimal getProductCount() {
		return productCount;
	}

	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal bigDecimal) {
		this.score = bigDecimal;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getWinnerStatus() {
		return winnerStatus;
	}

	public void setWinnerStatus(String winnerStatus) {
		this.winnerStatus = winnerStatus;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}


}