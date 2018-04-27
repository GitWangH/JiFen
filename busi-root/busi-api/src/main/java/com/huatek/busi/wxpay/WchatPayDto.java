package com.huatek.busi.wxpay;

public class WchatPayDto {

	// 金额
	private String orderPrice;
	// 运单号
	private String wayBillNo;
	// 商品订单号
	private String orderNo;
	// 商品名称
	private String goodsName;
	// 会员ID
	private Long memberId;

	// plusCode
	private String plusCode;
	// 订单ID
	private Long orderId;
	// 调用的类型 1 plus 会员 2 积分+钱
	private String type;

	/** @Fields platform 支付平台 */
	private String platForm;

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getWayBillNo() {
		return wayBillNo;
	}

	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPlatForm() {
		return platForm;
	}

	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}

}
