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
  * @ClassName: PhiMyproducts
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-27 13:37:18
  * @version: 1.0
  */

@Entity
@Table(name = "phi_myproducts")
public class PhiMyproducts extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "product_id", nullable = true )
 	private Long id;
 
	/** @Fields member_id : 会员id*/ 
	@Column(name= "member_id", nullable = true)
    private Long member_id;
	
	 /**一条我的物品对应一个订单在订单方进行维护*/
//    @OneToOne(mappedBy="phiMyproducts")
//    public PhiOrder phiOrder;
    

	/** @Fields order_id : 订单id*/ 
	@Column(name= "order_id", nullable = true)
    private Long order_id;
	

	/** @Fields productName : 产品名称*/ 
	@Column(name= "product_name", nullable = true, length=100 )
    private String productName;
    
    
	/** @Fields productCode : 产品编号*/ 
	@Column(name= "product_code", nullable = true, length=40)
    private String productCode;
    
    
    
	/** @Fields productType : 产品类型*/ 
	@Column(name= "product_type", nullable = false, length=10 )
    private String productType;
    
   
	/** @Fields money : 金额*/ 
	@Column(name= "money", nullable = true )
    private BigDecimal money;
	
	
	/** @Fields marketPrice: 市场价格 */
	@Column(name= "market_price", nullable = true)
    private BigDecimal marketPrice;
    
    
	/** @Fields validTime : 有效期*/ 
	@Column(name= "valid_time", nullable = true )
    private Date validTime;
    
	
	/** @Fields productImage : 产品图片*/ 
	@Column(name= "product_image",  length=200 )
    private String productImage;
    
    
	/** @Fields product_status : 产品状态*/ 
	@Column(name= "product_status",  length=10 )
    private String product_status;
	
	/** @Fields product_status : 购买产品数量*/ 
	@Column(name= "product_count",  length=10 )
	 private BigDecimal productCount;
	
    
	/** @Fields score : 消耗积分 */
	@Column(name= "score", nullable = true)
    private BigDecimal score;
    
    
	/** @Fields remark : 商品描述 */
	@Column(name= "remark", nullable = true,length=400)
    private String remark;
	


	public BigDecimal getProductCount() {
		return productCount;
	}


	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getMember_id() {
		return member_id;
	}


	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}


	public Long getOrder_id() {
		return order_id;
	}


	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
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



	public BigDecimal getMoney() {
		return money;
	}


	public void setMoney(BigDecimal money) {
		this.money = money;
	}


	public BigDecimal getMarketPrice() {
		return marketPrice;
	}


	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}


	public Date getValidTime() {
		return validTime;
	}


	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public String getProduct_status() {
		return product_status;
	}


	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}



	public BigDecimal getScore() {
		return score;
	}


	public void setScore(BigDecimal score) {
		this.score = score;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


    
      

}
