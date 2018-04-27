package com.huatek.busi.model.measure;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiMeasureMiddlePayCertificateDetail
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

@Entity
@Table(name = "busi_measure_middle_pay_certificate_detail")
public class BusiMeasureMiddlePayCertificateDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields certificateId : 证书主键 */
	@Column(name= "CERTIFICATE_ID", nullable = false)
    private Long certificateId;
    
    
	/** @Fields itemType : 类型(小计，合计，支付，其他)*/ 
	@Column(name= "ITEM_TYPE", nullable = false, length=50 )
    private String itemType;
    
    
	/** @Fields itemKey : 支付项目key*/ 
	@Column(name= "ITEM_KEY", nullable = false, length=255 )
    private String itemKey;
    
    
	/** @Fields itemName : 支付项目抿成*/ 
	@Column(name= "ITEM_NAME", nullable = false, length=255 )
    private String itemName;
	@Column(name= "SIGN", nullable = false)
    private String sign;
    
     /** @Fields oldPrice : 原合同价*/ 
    @Column(name= "OLD_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal oldPrice;
    
    
     /** @Fields reviewPrice : 复合合同价*/ 
    @Column(name= "REVIEW_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal reviewPrice;
    
    
     /** @Fields changePrice : 变更合同价*/ 
    @Column(name= "CHANGE_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal changePrice;
    
    
     /** @Fields price : 合同价*/ 
    @Column(name= "PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal price;
    
    
     /** @Fields preThePeriodAmount : 到上期末完成金额*/ 
    @Column(name= "PRE_THE_PERIOD_AMOUNT", nullable = false , precision=18 , scale=4)
    private BigDecimal preThePeriodAmount;
    
    
     /** @Fields andThePeriodAmount : 到本期末完成金额*/ 
    @Column(name= "AND_THE_PERIOD_AMOUNT", nullable = false , precision=18 , scale=4)
    private BigDecimal andThePeriodAmount;
    
    
     /** @Fields thePeriodAmount : 本期完成金额*/ 
    @Column(name= "THE_PERIOD_AMOUNT", nullable = false , precision=18 , scale=4)
    private BigDecimal thePeriodAmount;
    
    
	/** @Fields creater : 创建人id */
	@Column(name= "CREATER", nullable = false)
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
	@Column(name= "CREATER_NAME", nullable = false, length=100 )
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
	@Column(name= "CREATE_TIME", nullable = false)
    private Date createTime;
    
    
	/** @Fields modifer : 修改人id */
	@Column(name= "MODIFER", nullable = false)
    private Long modifer;
    
    
	/** @Fields modifierName : 修改人姓名*/ 
	@Column(name= "MODIFIER_NAME", nullable = false, length=100 )
    private String modifierName;
    
    
	/** @Fields modifyTime : 修改时间 */
	@Column(name= "MODIFY_TIME", nullable = false)
    private Date modifyTime;
    
    
	/** @Fields tenantId : 租户id */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setCertificateId(Long certificateId){
        this.certificateId = certificateId;
    }
      
    public Long getCertificateId(){
        return this.certificateId;
    }
      
    public void setItemType(String itemType){
        this.itemType = itemType;
    }
      
    public String getItemType(){
        return this.itemType;
    }
      
    public void setItemKey(String itemKey){
        this.itemKey = itemKey;
    }
      
    public String getItemKey(){
        return this.itemKey;
    }
      
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
      
    public String getItemName(){
        return this.itemName;
    }
      
    public void setOldPrice(BigDecimal oldPrice){
        this.oldPrice = oldPrice;
    }
      
    public BigDecimal getOldPrice(){
        return this.oldPrice;
    }
      
    public void setReviewPrice(BigDecimal reviewPrice){
        this.reviewPrice = reviewPrice;
    }
      
    public BigDecimal getReviewPrice(){
        return this.reviewPrice;
    }
      
    public void setChangePrice(BigDecimal changePrice){
        this.changePrice = changePrice;
    }
      
    public BigDecimal getChangePrice(){
        return this.changePrice;
    }
      
    public void setPrice(BigDecimal price){
        this.price = price;
    }
      
    public BigDecimal getPrice(){
        return this.price;
    }
      
    public void setPreThePeriodAmount(BigDecimal preThePeriodAmount){
        this.preThePeriodAmount = preThePeriodAmount;
    }
      
    public BigDecimal getPreThePeriodAmount(){
        return this.preThePeriodAmount;
    }
      
    public void setAndThePeriodAmount(BigDecimal andThePeriodAmount){
        this.andThePeriodAmount = andThePeriodAmount;
    }
      
    public BigDecimal getAndThePeriodAmount(){
        return this.andThePeriodAmount;
    }
      
    public void setThePeriodAmount(BigDecimal thePeriodAmount){
        this.thePeriodAmount = thePeriodAmount;
    }
      
    public BigDecimal getThePeriodAmount(){
        return this.thePeriodAmount;
    }
      
    public void setCreater(Long creater){
        this.creater = creater;
    }
      
    public Long getCreater(){
        return this.creater;
    }
      
    public void setCreaterName(String createrName){
        this.createrName = createrName;
    }
      
    public String getCreaterName(){
        return this.createrName;
    }
      
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
      
    public Date getCreateTime(){
        return this.createTime;
    }
      
    public void setModifer(Long modifer){
        this.modifer = modifer;
    }
      
    public Long getModifer(){
        return this.modifer;
    }
      
    public void setModifierName(String modifierName){
        this.modifierName = modifierName;
    }
      
    public String getModifierName(){
        return this.modifierName;
    }
      
    public void setModifyTime(Date modifyTime){
        this.modifyTime = modifyTime;
    }
      
    public Date getModifyTime(){
        return this.modifyTime;
    }
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
      

}
