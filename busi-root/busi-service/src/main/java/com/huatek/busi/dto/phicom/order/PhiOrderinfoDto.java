package com.huatek.busi.dto.phicom.order;

import java.math.BigDecimal;
import java.util.Date;


public class PhiOrderinfoDto {
    private Long id;
    private Long orderId;
    private Long myProductid;
    private Long productId;
    private String orderStatus;
    private String productCode;
    private String productName;
    private String productType;
    private String productClass;
    private String productStatus;
    private String productImg;
    private Long validTime;
    private String isWin;
    private String remark;
    private String receiverName;
    private String province;
    private String city;
    private String area;
    private String street;
    private String addressDetail;
    private String zipCode;
    private String tel;
    private String isReceive;
    private String Isdelete;
    private BigDecimal marketPrice;
    private BigDecimal money;
    private BigDecimal productCount;
    private BigDecimal score;
    private Date winnerTime;
    private String winnerStatus;
    private String coupCode;
    private String coupName;
    private String coupType;
    private Long cpnsWayId;
    private String reciverAddress;
    
    /**
	 * 默认构造器
	 */
	public PhiOrderinfoDto(){}
	
	 
     /**
	 * 生成getter，setter 访问器
	 */
	
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
      
//    public Long getOrderInfoid() {
//		return orderInfoid;
//	}
//
//
//	public void setOrderInfoid(Long orderInfoid) {
//		this.orderInfoid = orderInfoid;
//	}


	public Long getOrderId(){
        return this.orderId;
    }
      
    public void setMyProductid(Long myProductid){
        this.myProductid = myProductid;
    }
      
    public Long getMyProductid(){
        return this.myProductid;
    }
      
    public void setProductId(Long productId){
        this.productId = productId;
    }
      
    public Long getProductId(){
        return this.productId;
    }
      
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }
      
    public String getOrderStatus(){
        return this.orderStatus;
    }
      
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
      
    public String getProductCode(){
        return this.productCode;
    }
      
    public void setProductName(String productName){
        this.productName = productName;
    }
      
    public String getProductName(){
        return this.productName;
    }
      
    public void setProductType(String productType){
        this.productType = productType;
    }
      
    public String getProductType(){
        return this.productType;
    }
      
    public void setProductClass(String productClass){
        this.productClass = productClass;
    }
      
    public String getProductClass(){
        return this.productClass;
    }
      
    public void setProductStatus(String productStatus){
        this.productStatus = productStatus;
    }
      
    public String getProductStatus(){
        return this.productStatus;
    }
      
      
    public void setProductImg(String productImg){
        this.productImg = productImg;
    }
      
    public String getProductImg(){
        return this.productImg;
    }
      
    public void setValidTime(Long validTime){
        this.validTime = validTime;
    }
      
    public Long getValidTime(){
        return this.validTime;
    }
      
    public void setIsWin(String isWin){
        this.isWin = isWin;
    }
      
    public String getIsWin(){
        return this.isWin;
    }
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    public void setReceiverName(String receiverName){
        this.receiverName = receiverName;
    }
      
    public String getReceiverName(){
        return this.receiverName;
    }
      
    public void setProvince(String province){
        this.province = province;
    }
      
    public String getProvince(){
        return this.province;
    }
      
    public void setCity(String city){
        this.city = city;
    }
      
    public String getCity(){
        return this.city;
    }
      
    public void setArea(String area){
        this.area = area;
    }
      
    public String getArea(){
        return this.area;
    }
      
    public void setStreet(String street){
        this.street = street;
    }
      
    public String getStreet(){
        return this.street;
    }
      
    public void setAddressDetail(String addressDetail){
        this.addressDetail = addressDetail;
    }
      
    public String getAddressDetail(){
        return this.addressDetail;
    }
      
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
      
    public String getZipCode(){
        return this.zipCode;
    }
      
    public void setTel(String tel){
        this.tel = tel;
    }
      
    public String getTel(){
        return this.tel;
    }
      
    public void setIsReceive(String isReceive){
        this.isReceive = isReceive;
    }
      
    public String getIsReceive(){
        return this.isReceive;
    }


	public String getIsdelete() {
		return Isdelete;
	}


	public void setIsdelete(String isdelete) {
		Isdelete = isdelete;
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


	public BigDecimal getScore() {
		return score;
	}


	public void setScore(BigDecimal score) {
		this.score = score;
	}




	public Date getWinnerTime() {
		return winnerTime;
	}


	public void setWinnerTime(Date winnerTime) {
		this.winnerTime = winnerTime;
	}


	public String getWinnerStatus() {
		return winnerStatus;
	}


	public void setWinnerStatus(String winnerStatus) {
		this.winnerStatus = winnerStatus;
	}


	public String getCoupCode() {
		return coupCode;
	}


	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}


	public String getCoupName() {
		return coupName;
	}


	public void setCoupName(String coupName) {
		this.coupName = coupName;
	}


	public String getCoupType() {
		return coupType;
	}


	public void setCoupType(String coupType) {
		this.coupType = coupType;
	}


	public Long getCpnsWayId() {
		return cpnsWayId;
	}


	public void setCpnsWayId(Long cpnsWayId) {
		this.cpnsWayId = cpnsWayId;
	}


	public String getReciverAddress() {
		return reciverAddress;
	}


	public void setReciverAddress(String reciverAddress) {
		this.reciverAddress = reciverAddress;
	}
      
      
    
	
	/**
	 * 
	 * 将 model 集合转为 dto 集合
	 * @param 
	 * @return
	 */
	/*public static List<PhiOrderinfoDto> transToDtoList(List<PhiOrderinfo> datas) {
		List<PhiOrderinfoDto> dtos =new ArrayList<PhiOrderinfoDto>();
		for(PhiOrderinfo newDatas : datas){
			PhiOrderinfoDto dto = new PhiOrderinfoDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}*/
}