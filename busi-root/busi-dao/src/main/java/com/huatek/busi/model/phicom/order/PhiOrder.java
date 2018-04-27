package com.huatek.busi.model.phicom.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: PhiOrder
 * @Description:
 * @author: Ken Bai
 * @Email : Ken_Bai@huatek.com
 * @date: 2017-12-21 16:27:35
 * @version: 1.0
 */

@Entity
@Table(name = "phi_order")
public class PhiOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	/** @Fields orderId :订单id*/
	@Column(name = "order_id", nullable = false)
	private Long id;

	@Column(name = "uid", nullable = false)
	private Long uid;
	/** 一个订单对应一条商品记录（订单维护商品） */
	// @OneToOne(optional = false, cascade = CascadeType.REFRESH)
	// @JoinColumn(name = "product_id", referencedColumnName = "product_id",
	// unique = true)
	// public PhiMyproducts phiMyproducts;

	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	// JPA注释： 一对一 关系
	@JoinColumn(name = "orderInfo_id", referencedColumnName = "id", unique = true)
	// 在PhiOrder中，添加一个外键 "orderInfo_id"
	public PhiOrderinfo phiOrderInfo;

	@OneToOne(optional = true, cascade = CascadeType.REFRESH)
	// JPA注释： 一对一 关系
	@JoinColumn(name = "logistic_id", referencedColumnName = "id", unique = true)
	// 在pepole中，添加一个外键 "pet_fk"
	public PhiLogistic phiLogistic;

	// /** @Fields orderInfo_id :订单id*/
	// @Column(name= "orderInfo_id", nullable = true)
	// private Long orderInfoId;

	// /** @Fields logistic_id :订单id*/
	// @Column(name= "logistic_id", nullable = true)
	// private Long logisticId;

	/** 多个订单对应一个会员 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "member_id")
	private PhiMember phiMember;

	/** @Fields orderCode :商品id */
	@Column(name = "product_id", nullable = true)
	private Long productId;

	/** @Fields orderCode :订单编号 */
	@Column(name = "order_code", nullable = true, length = 40)
	private String orderCode;

	/** @Fields createTime :开始时间 */
	@Column(name = "create_time", nullable = true)
	private Date createTime;

	/** @Fields endTime :结束时间 */
	@Column(name = "end_time", nullable = true)
	private Date endTime;

	/** @Fields status :订单状态 */
	@Column(name = "status", nullable = true, length = 255)
	private String status;

	/** @Fields status :是否结算 */
	@Column(name = "isclose", nullable = true, length = 10)
	private String isclose;

	/** @Fields status :商品类型（0券码类 1中奖类 2实物类） */
	@Column(name = "product_class", nullable = true, length = 10)
	private String productClass;

	/** @Fields status :物流编号 */
	@Column(name = "logistic_code", nullable = true, length = 10)
	private String logisticCode;

	/** @Fields isdistribut :是否配送 */
	@Column(name = "isdistribut", nullable = true, length = 10)
	private String isdistribut;

	/** @Fields number :购买商品数量 */
	@Column(name = "number", nullable = true)
	private BigDecimal number;

	/** @Fields status :支付方式 */
	@Column(name = "pay_type", nullable = true, length = 10)
	private String payType;

	/** @Fields is_delete :是否删除 */
	@Column(name = "is_delete", nullable = true, length = 10)
	private String isdelete;

	/** @Fields transaction_id :交易流水号 */
	@Column(name = "transaction_id")
	private String transactionId;

	/** @Fields pay_money :支付时间 */
	@Column(name = "pay_time")
	private Date payTime;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public String getIsdistribut() {
		return isdistribut;
	}

	public void setIsdistribut(String isdistribut) {
		this.isdistribut = isdistribut;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsclose() {
		return isclose;
	}

	public void setIsclose(String isclose) {
		this.isclose = isclose;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public PhiMember getPhiMember() {
		return phiMember;
	}

	public void setPhiMember(PhiMember phiMember) {
		this.phiMember = phiMember;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PhiLogistic getPhiLogistic() {
		return phiLogistic;
	}

	public void setPhiLogistic(PhiLogistic phiLogistic) {
		this.phiLogistic = phiLogistic;
	}

	public PhiOrderinfo getPhiOrderInfo() {
		return phiOrderInfo;
	}

	public void setPhiOrderInfo(PhiOrderinfo phiOrderInfo) {
		this.phiOrderInfo = phiOrderInfo;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
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

	// public Long getCpnsWayId() {
	// return cpnsWayId;
	// }
	//
	// public void setCpnsWayId(Long cpnsWayId) {
	// this.cpnsWayId = cpnsWayId;
	// }

	// public Long getOrderInfoId() {
	// return orderInfoId;
	// }
	//
	// public void setOrderInfoId(Long orderInfoId) {
	// this.orderInfoId = orderInfoId;
	// }

	// public Long getLogisticId() {
	// return logisticId;
	// }
	//
	// public void setLogisticId(Long logisticId) {
	// this.logisticId = logisticId;
	// }

	/** @Fields phipayinfoSet : */
	/*
	 * //mappedBy通过维护端的属性关联
	 * 
	 * @OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy=
	 * "phiOrder") private Set<PhiPayInfo> phipayinfoSet;
	 * 
	 * 
	 * 
	 * public void setPhipayinfoSet(Set<PhiPayInfo> phipayinfoSet){
	 * this.phipayinfoSet = phipayinfoSet; }
	 * 
	 * public Set<PhiPayInfo> getPhipayinfoSet(){ return this.phipayinfoSet; }
	 */

	// order表的字段

}
