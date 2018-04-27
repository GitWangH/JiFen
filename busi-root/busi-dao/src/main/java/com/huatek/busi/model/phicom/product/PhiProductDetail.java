package com.huatek.busi.model.phicom.product;

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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

@Entity
@Table(name = "phi_product_detail")
public class PhiProductDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "product_detail_id", nullable = true )
 	private Long detialId;
	
	/** @Fields busiContractContractChange : 主表ID *//*
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "product_id")
	private PhiProduct phiProduct;*/
	
	/** @Fields creatorId : 创建人ID */
    @Column(name= "creator_id", nullable = true)
	private Long creatorId;
	    
    /** @Fields createTime: 创建时间*/ 
	@Column(name= "create_time", nullable = true, length=65535 )
    private Date createTime;
      

	/** @Fields money : 市场价格*/ 
	@Column(name= "money", nullable = true, length=100 )
    private BigDecimal money;
	

	/** @Fields score : 获得积分 */
	@Column(name= "score", nullable = true)
    private BigDecimal score;
	
	
	   
	/** @Fields exchangSuperLimit : 创建人姓名*/ 
	@Column(name= "exchang_super_limit", nullable = true, length=100 )
	private String exchangSuperLimit;
	
	
	 
	/** @Fields marketPrice: 市场价格 */
	@Column(name= "market_price", nullable = true)
	private BigDecimal marketPrice;
	
	
	/** @Fields discount : 打折率 */
	@Column(name= "discount", nullable = true)
    private Long discount;
	    
	

	/** @Fields code : 编码*/
	@Column(name= "code", nullable = true)
    private String code;

	
	/** @Fields isUsed : 是否使用 */
	@Column(name= "isUsed", nullable = true)
    private Long isUsed;

	
	
	

	public Long getDetialId() {
		return detialId;
	}

	public void setDetialId(Long detialId) {
		this.detialId = detialId;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getExchangSuperLimit() {
		return exchangSuperLimit;
	}

	public void setExchangSuperLimit(String exchangSuperLimit) {
		this.exchangSuperLimit = exchangSuperLimit;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Long isUsed) {
		this.isUsed = isUsed;
	}


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	/*public PhiProduct getPhiProduct() {
		return phiProduct;
	}

	public void setPhiProduct(PhiProduct phiProduct) {
		this.phiProduct = phiProduct;
	}*/

	

}
