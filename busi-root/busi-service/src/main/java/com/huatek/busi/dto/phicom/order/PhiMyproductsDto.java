package com.huatek.busi.dto.phicom.order;

import java.util.Date;

public class PhiMyproductsDto {
    private Long productId;
    private Long memberId;
    private Long id;
    private String productName;
    private String productCode;
    private String productType;
    private String productStatus;
    private Date validTime;
    private String productCount;
    private String productImage;
    private String remark;
    private String score;
    private String marketPrice;
    private String money;
    
    /**
	 * 默认构造器
	 */
	public PhiMyproductsDto(){}
	
	 
     /**
	 * 生成getter，setter 访问器
	 */
    public void setProductId(Long productId){
        this.productId = productId;
    }
      
    public Long getProductId(){
        return this.productId;
    }
      
    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }
      
    public Long getMemberId(){
        return this.memberId;
    }
      
      
   


	public void setProductName(String productName){
        this.productName = productName;
    }
      
    public String getProductName(){
        return this.productName;
    }
      
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
      
    public String getProductCode(){
        return this.productCode;
    }
      
    public void setProductType(String productType){
        this.productType = productType;
    }
      
    public String getProductType(){
        return this.productType;
    }
      
    public void setProductStatus(String productStatus){
        this.productStatus = productStatus;
    }
      
    public String getProductStatus(){
        return this.productStatus;
    }
      
    public void setValidTime(Date validTime){
        this.validTime = validTime;
    }
      
    public Date getValidTime(){
        return this.validTime;
    }
      
    public void setProductCount(String productCount){
        this.productCount = productCount;
    }
      
    public String getProductCount(){
        return this.productCount;
    }
      
    public void setProductImage(String productImage){
        this.productImage = productImage;
    }
      
    public String getProductImage(){
        return this.productImage;
    }
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    public void setScore(String score){
        this.score = score;
    }
      
    public String getScore(){
        return this.score;
    }
      
    public void setMarketPrice(String marketPrice){
        this.marketPrice = marketPrice;
    }
      
    public String getMarketPrice(){
        return this.marketPrice;
    }
      
    public void setMoney(String money){
        this.money = money;
    }
      
    public String getMoney(){
        return this.money;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
      
    
}