package com.huatek.busi.model.phicom.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiOrderinfo
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-29 17:33:31
  * @version: 1.0
  */

@Entity
@Table(name = "phi_orderinfo")
public class PhiOrderinfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name= "id", nullable = false, length=20)
	private Long id;
	
	@OneToOne(mappedBy="phiOrderInfo")
    private PhiOrder phiOrder;
	
	/** @Fields orderId :订单id*/ 
	@Column(name= "order_id", nullable = false, length=20)
	private Long orderId;
	
	
	/** @Fields my_productid :我的商品id*/ 
	@Column(name= "my_productid", nullable = false, length=20)
	private Long myProductid;
	
	
	/** @Fields product_id :商品id*/ 
	@Column(name= "product_id", nullable = false, length=20)
	private Long productId;
	
	/** @Fields order_status :订单状态*/ 
	@Column(name= "order_status", nullable = false, length=20)
	private Long orderStatus;
	
	/** @Fields product_name : 商品名称*/ 
	@Column(name= "product_name", nullable = true, length=10 )
    private String productName;
	
	/** @Fields productCode : 商品编号*/ 
	@Column(name= "product_code", nullable = true, length=10 )
    private String productCode;
	
	/** @Fields productType : 商品分类*/ 
	@Column(name= "product_type", nullable = false, length=10 )
    private String productType;
	
	/** @Fields productType : 商品类型*/ 
	@Column(name= "product_class", nullable = false, length=10 )
    private String productClass;
    
    
	/** @Fields productStatus : 商品状态*/ 
	@Column(name= "product_status", nullable = true, length=10 )
    private String productStatus;
	
	
	/** @Fields score : 积分数 */
	@Column(name= "score", nullable = true)
    private BigDecimal score;
	
	
	/** @Fields marketPrice : 市场价 */
	@Column(name= "market_price", nullable = true)
    private BigDecimal marketPrice;
	
	
	/** @Fields money : 加购价*/ 
	@Column(name= "money", nullable = true )
    private BigDecimal money;
   
	
	/** @Fields product_count : 购买数量 */
	@Column(name= "product_count", nullable = true)
    private BigDecimal productCount;
	
	
	/** @Fields productImage : 产品图片*/ 
	@Column(name= "product_img",  length=200 )
    private String productImageHead;
   
    
	/** @Fields validTime : 有效期*/ 
	@Column(name= "valid_time", nullable = true )
    private Date validTime;
    
	
	/** @Fields is_win : 是否中奖*/ 
	@Column(name= "is_win", nullable = true, length=10 )
    private String isWin;
	
	

	/** @Fields remark : 备注*/ 
	@Column(name= "remark", nullable = true, length=400 )
    private String remark;
	
	/** @Fields remark : 收货人*/ 
	@Column(name= "receiver_name", nullable = true, length=40)
    private String receiverName;
	
	/** @Fields province : 省*/ 
	@Column(name= "province", nullable = true, length=40)
    private String province;
	
	
	/** @Fields city : 市*/ 
	@Column(name= "city", nullable = true, length=40)
    private String city;
	

	/** @Fields area : 区*/ 
	@Column(name= "area", nullable = true, length=40)
    private String area;
	
	
	/** @Fields street : 街道*/ 
	@Column(name= "street", nullable = true, length=200)
    private String street;
	
	
	/** @Fields addressDetail : 详细地址*/ 
	@Column(name= "address_detail", nullable = true, length=200)
    private String addressDetail;
	
	
	/** @Fields addressDetail :邮编*/ 
	@Column(name= "zip_code", nullable = true, length=40)
    private String zipCode;
	
	
	/** @Fields tel : 收货人手机号*/ 
	@Column(name= "tel", nullable = true, length=10)
    private String tel;
	
	
	/** @Fields is_receive : 是否收获*/ 
	@Column(name= "is_receive", nullable = true, length=10)
    private String isReceive;
	
	
	/** @Fields status : 状态（停用启用）*/ 
	@Column(name= "status", nullable = true, length=10)
    private String Isdelete;
	
	
	/** @Fields status : 支付时间*/ 
	@Column(name= "paytime", nullable = true, length=10)
    private Date paytime;

	/** @Fields status : 物流费用*/ 
	@Column(name= "logisticMoney", nullable = true, length=10)
    private BigDecimal logisticMoney;
	
	
	/** @Fields status : 开奖时间*/ 
	@Column(name= "prizeTime", nullable = true, length=10)
    private Date winnerTime;
	
	
	/** @Fields status : 是否开奖*/ 
	@Column(name= "winner_status", nullable = true, length=32)
    private String winnerStatus;
	
	/** @Fields coupCode : 券码*/ 
	@Column(name= "coupCode", nullable = true, length=32)
	private String coupCode;
	
//	/** @Fields cpns_name : 券码名称*/ 
//	@Column(name= "cpns_name", nullable = true, length=32)
//	private String coupName;
	
	
	/** @Fields cpns_name : 券码类型*/ 
	@Column(name= "cpns_type", nullable = true, length=32)
	private String coupType;
	
	/** @Fields coupons_way_id :优惠券id*/ 
	@Column(name= "coupons_way_id", nullable = true)
	private Long cpnsWayId;
	
	/** @Fields third_id :第三方券id*/ 
	@Column(name= "third_id", nullable = true)
	private Long thirdId;
	
	
	/** @Fields address_id :收货地址id*/ 
	@Column(name= "address_id", nullable = false, length=20)
	private Long addressId;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCoupType() {
		return coupType;
	}


	public void setCoupType(String coupType) {
		this.coupType = coupType;
	}


	public String getWinnerStatus() {
		return winnerStatus;
	}


	public String getCoupCode() {
		return coupCode;
	}


	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}


	public void setWinnerStatus(String winnerStatus) {
		this.winnerStatus = winnerStatus;
	}




	public Date getWinnerTime() {
		return winnerTime;
	}


	public void setWinnerTime(Date winnerTime) {
		this.winnerTime = winnerTime;
	}


	public BigDecimal getLogisticMoney() {
		return logisticMoney;
	}


	public void setLogisticMoney(BigDecimal logisticMoney) {
		this.logisticMoney = logisticMoney;
	}

//	public Long getOrderInfoid() {
//		return orderInfoid;
//	}

	public Date getPaytime() {
		return paytime;
	}


	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}


//	public void setOrderInfoid(Long orderInfoid) {
//		this.orderInfoid = orderInfoid;
//	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Long getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Long orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getProductClass() {
		return productClass;
	}


	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}


	public String getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}


	public BigDecimal getScore() {
		return score;
	}


	public void setScore(BigDecimal score) {
		this.score = score;
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


	public void setMoney(BigDecimal money) {
		this.money = money;
	}


	public BigDecimal getProductCount() {
		return productCount;
	}


	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}


	public Date getValidTime() {
		return validTime;
	}


	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}


	public String getIsWin() {
		return isWin;
	}


	public void setIsWin(String isWin) {
		this.isWin = isWin;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getReceiverName() {
		return receiverName;
	}


	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getIsdelete() {
		return Isdelete;
	}


	public void setIsdelete(String isdelete) {
		Isdelete = isdelete;
	}


	public PhiOrder getPhiOrder() {
		return phiOrder;
	}


	public void setPhiOrder(PhiOrder phiOrder) {
		this.phiOrder = phiOrder;
	}


	public Long getMyProductid() {
		return myProductid;
	}


	public void setMyProductid(Long myProductid) {
		this.myProductid = myProductid;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getIsReceive() {
		return isReceive;
	}


	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}


	public Long getCpnsWayId() {
		return cpnsWayId;
	}


	public void setCpnsWayId(Long cpnsWayId) {
		this.cpnsWayId = cpnsWayId;
	}


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public String getProductImageHead() {
		return productImageHead;
	}


	public void setProductImageHead(String productImageHead) {
		this.productImageHead = productImageHead;
	}


	public Long getThirdId() {
		return thirdId;
	}


	public void setThirdId(Long thirdId) {
		this.thirdId = thirdId;
	}


}
