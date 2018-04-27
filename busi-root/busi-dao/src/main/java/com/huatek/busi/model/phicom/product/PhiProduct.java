package com.huatek.busi.model.phicom.product;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiProduct
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-18 15:12:53
  * @version: 1.0
  */

@Entity
@Table(name = "phi_product")
public class PhiProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "product_id", nullable = true )
 	private Long id;
    //辰商的商品id
	@Column(name= "goods_id", nullable = true )
 	private Long goodsId;
	/**一个商品对应多个订单*//*
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "phiProduct")//商品详情
	private Set<PhiOrder> phiOrder;
	*/
	
	//多个商品对应一个类型
	@Column(name = "prouduct_type_id",nullable = true, length=100 )
	private Long phiProductType;
	
	

	public Long getGoodsId() {
		return goodsId;
	}



	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}


	@Column(name = "need_grade",nullable = true, length=100 )
	private String needGrade;
    
	/** @Fields worder: 权重*/ 
	@Column(name= "coupons_way_id", nullable = true, length=100 )
    private Long coupWayId;
		
	/** @Fields worder: 第三方优惠劵id*/ 
	@Column(name= "third_id", nullable = true, length=100 )
    private String thirdId;
	
	@Version
	private int version;

	


	/** @Fields worder: 权重*/ 
	@Column(name= "w_order", nullable = true, length=100 )
    private String worder;
	
	/** @Fields productName : 产品名称*/ 
	@Column(name= "product_name", nullable = true, length=100 )
    private String productName;
    
    
	/** @Fields productCode : 产品编号*/ 
	@Column(name= "product_code", nullable = true, length=100 )
    private String productCode;
    
    
    
	/** @Fields productClass : 产品类型*/ 
	@Column(name= "product_class", nullable = false, length=100 )
    private String productClass;
    
    
	/** @Fields productStatus : 产品状态*/ 
	@Column(name= "product_status", nullable = true, length=100 )
    private String productStatus;
      
    
    
	/** @Fields validTime : 有效期*/ 
	@Column(name= "valid_time", nullable = true )
    private Date validTime;
    
    
	/** @Fields productStock : 仓储数量 */
	@Column(name= "product_stock")
    private BigDecimal productStock;
    
    
	
	/** @Fields productImage : 产品图片*/ 
	@Column(name= "product_image_pc",  length=100 )
    private String productImagePc;  
 
	
	/** @Fields productImage : 产品图片*/ 
	@Column(name= "product_image_App",  length=100 )
    private String productImageApp; 


	/** @Fields productImage : 产品主图*/ 
	@Column(name= "product_image_head",  length=100 )
    private String productImageHead;
	
	/** @Fields productImage : 是否推荐*/ 
	@Column(name= "isRecommend",  length=100 )
    private String isRecommend;
	

	/** @Fields upTime : 上架时间*/ 
    @Column(name= "up_time" ,nullable = true)
    private Date upTime;
    
    /** @Fields upTime : 开奖时间*/ 
    @Column(name= "winner_time" ,nullable = true)
    private Date winnerTime;
      
    /** @Fields upTime : 开奖状态*/ 
    @Column(name= "winner_status" ,nullable = true)
    private String winnerStatus;


	/** @Fields downTime : 下架时间*/ 
    @Column(name= "down_time", nullable = true)
    private Date downTime;
         
    
	/** @Fields createTime: 创建时间*/ 
	@Column(name= "create_time", nullable = true)
    private Date createTime;
    
    
	/** @Fields remark : 备注*/ 
	@Column(name= "remark", nullable = true)
    private String remark;
    
    
	/** @Fields creatorId : 创建人ID */
	@Column(name= "creator_id", nullable = true)
    private Long creatorId;
       		
	/** @Fields quantity : 数量 */
	@Column(name= "quantity", nullable = true)
    private BigDecimal quantity;
	

	/** @Fields score : 积分数 */
	@Column(name= "score", nullable = true)
    private BigDecimal score;
	
	
	/** @Fields marketPrice : 市场价 */
	@Column(name= "market_price", nullable = true)
    private BigDecimal marketPrice;
	
	/** @Fields money : 金额 */
	@Column(name= "money", nullable = true)
    private BigDecimal money;
	
	/** @Fields money : 物流费*/
	@Column(name= "logistics_cost", nullable = true)
    private BigDecimal logisticsCost;
	

	/** @Fields exchangSuperLimit : 兑换上限 */
	@Column(name= "exchang_super_limit", nullable = true)
    private BigDecimal exchangSuperLimit;

	
	/** @Fields productName : 中奖人数*/ 
	@Column(name= "winner_counts", nullable = true, length=100 )
    private String winnerCounts;
	
	/** @Fields productName : 手动中奖人数*/ 
	@Column(name= "Manual_counts", nullable = true, length=100 )
    private String ManualCounts;
	
	/** @Fields productName : 随机中奖人数*/ 
	@Column(name= "Random_counts", nullable = true, length=100 )
    private String RandomCounts;
	
	/** @Fields productName : 虚拟人数*/ 
	@Column(name= "fake_counts", nullable = true, length=100 )
    private String fakeCounts;
	
	/** @Fields productName : 是否上架*/ 
	@Column(name= "isShop", nullable = true, length=100 )
    private String isShop;

	/** @Fields exchangQuantity : 商品兑换数量*/ 
	@Column(name= "exchang_quantity", nullable = true, length=100 )
    private int exchangQuantity;
	

	
	

	public int getExchangQuantity() {
		return exchangQuantity;
	}



	public void setExchangQuantity(int exchangQuantity) {
		this.exchangQuantity = exchangQuantity;
	}



	public  int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}



	public String getThirdId() {
		return thirdId;
	}



	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	

	public String getWinnerCounts() {
		return winnerCounts;
	}



	public void setWinnerCounts(String winnerCounts) {
		this.winnerCounts = winnerCounts;
	}



	public String getManualCounts() {
		return ManualCounts;
	}



	public void setManualCounts(String manualCounts) {
		ManualCounts = manualCounts;
	}



	public String getRandomCounts() {
		return RandomCounts;
	}



	public void setRandomCounts(String randomCounts) {
		RandomCounts = randomCounts;
	}



	public String getFakeCounts() {
		return fakeCounts;
	}



	public void setFakeCounts(String fakeCounts) {
		this.fakeCounts = fakeCounts;
	}



	public String getProductImageHead() {
		return productImageHead;
	}



	public void setProductImageHead(String productImageHead) {
		this.productImageHead = productImageHead;
	}
	

	public Long getCoupWayId() {
		return coupWayId;
	}



	public void setCoupWayId(Long coupWayId) {
		this.coupWayId = coupWayId;
	}
	 
		

	public String getIsRecommend() {
		return isRecommend;
	}



	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}



	public String getWinnerStatus() {
		return winnerStatus;
	}



	public void setWinnerStatus(String winnerStatus) {
		this.winnerStatus = winnerStatus;
	}



	public String getIsShop() {
		return isShop;
	}



	public String getNeedGrade() {
		return needGrade;
	}

     public Date getWinnerTime() {
		return winnerTime;
	 }



	public void setWinnerTime(Date winnerTime) {
		this.winnerTime = winnerTime;
	}



	public BigDecimal getLogisticsCost() {
		return logisticsCost;
	}



	public void setLogisticsCost(BigDecimal logisticsCost) {
		this.logisticsCost = logisticsCost;
	}



	public void setNeedGrade(String needGrade) {
		this.needGrade = needGrade;
	}



	public String getWorder() {
		return worder;
	}


	public void setWorder(String worder) {
		this.worder = worder;
	}


	public void setIsShop(String isShop) {
		this.isShop = isShop;
	}


	


	public String getProductImagePc() {
		return productImagePc;
	}



	public void setProductImagePc(String productImagePc) {
		this.productImagePc = productImagePc;
	}



	public String getProductImageApp() {
		return productImageApp;
	}



	public void setProductImageApp(String productImageApp) {
		this.productImageApp = productImageApp;
	}



	public BigDecimal getQuantity() {
		return quantity;
	}


	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public Long getId() {

		return id;
	}


	@Override
	public void setId(Long id) {
		this.id = id;
		
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


	public String getProductClass() {
		return productClass;
	}


	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}


	public BigDecimal getExchangSuperLimit() {
		return exchangSuperLimit;
	}


	public Long getPhiProductType() {
		return phiProductType;
	}



	public void setPhiProductType(Long phiProductType) {
		this.phiProductType = phiProductType;
	}



	public void setExchangSuperLimit(BigDecimal exchangSuperLimit) {
		this.exchangSuperLimit = exchangSuperLimit;
	}



	public String getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}




	public Date getValidTime() {
		return validTime;
	}


	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}


	public BigDecimal getProductStock() {
		return productStock;
	}


	public void setProductStock(BigDecimal productStock) {
		this.productStock = productStock;
	}



	public Date getUpTime() {
		return upTime;
	}


	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}


	public Date getDownTime() {
		return downTime;
	}


	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}




	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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


}
