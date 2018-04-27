package com.huatek.busi.dto.phicom.product;


import java.util.List;

import com.huatek.busi.dto.phicom.winner.PhiWinnersListDto;



public class PhiProductDto {
	
	private Long id;
    //商品名称
    private String productName;
    //商品编码
    private String productCode;
    //商品大类
    private String productClass;
    //商品审核状态
    private String productStatus;
    //金额
    private String money;
    //有效期
    private String validTime;
    //上架时间
    private String upTime;
    //下架时间
    private String downTime;
    //创建时间
    private String createTime;
    //市场价
    private String marketPrice;
    //商品描述
    private String remark;
    //创建人id
    private Long creatorId;
    //库存
    private String productStock;
    //优惠券方案id   
    private Long coupWayId;
    
    private String thirdId;
    
    //兑换上限
    private String exchangSuperLimit;     
    //积分
    private String score;
    //是否推荐
    private Boolean isRecommend;
    //商品主图
    private String 	productImageHead;
    //商品压缩主图
    private String 	productCompressImageHead;
    

	//商品详情图(pc端)
	private String productImagePc;
	//商品详情图(App端)
    private String productImageApp;
    //数量
    private String quantity;      
    //是否上架
    private String isShop;
    //开奖时间
    private String winnerTime;
    //商品类型id
    private Long phiProductType;
    //需要等级
    private String needGrade;
    //物流费用
    private String logisticsCost;
    //中奖状态(是否已中奖)
    private String winnerStatus;
    //开奖总人数
    private String  winnerCounts;
    //手动开奖
    private String  manualCounts;
    //随机开奖
    private String  randomCounts;
    //虚拟开奖
    private String  fakeCounts;
    //是否可兑换
    private String isused;
    //商品的主图返回给App;
    private List<String> productImageHeadsList;
    //商品的详情图返回给App;
    private List<String> procuctDetailImageList; 
    //商品的详情图返回给Pc;
    private List<String> procuctDetailPcImageList;
    //商品主图压缩图片
    private List<String> procuctCompressPathImageList;   
   //商品详情图压缩图片App
    private List<String> procuctCompressDetailAppImageList;
    //今日是否开奖;
    private String todayisOpen;
    
    private String orderCount;
    
    private boolean downRemind;
       

	public boolean getDownRemind() {
		return downRemind;
	}

	public void setDownRemind(boolean downRemind) {
		this.downRemind = downRemind;
	}

	public String getProductCompressImageHead() {
		return productCompressImageHead;
	}

	public void setProductCompressImageHead(String productCompressImageHead) {
		this.productCompressImageHead = productCompressImageHead;
	}

	public List<String> getProcuctCompressPathImageList() {
		return procuctCompressPathImageList;
	}

	public void setProcuctCompressPathImageList(
			List<String> procuctCompressPathImageList) {
		this.procuctCompressPathImageList = procuctCompressPathImageList;
	}

	public List<String> getProcuctCompressDetailAppImageList() {
		return procuctCompressDetailAppImageList;
	}

	public void setProcuctCompressDetailAppImageList(
			List<String> procuctCompressDetailAppImageList) {
		this.procuctCompressDetailAppImageList = procuctCompressDetailAppImageList;
	}

	public String getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public List<String> getProcuctDetailPcImageList() {
		return procuctDetailPcImageList;
	}

	public void setProcuctDetailPcImageList(List<String> procuctDetailPcImageList) {
		this.procuctDetailPcImageList = procuctDetailPcImageList;
	}

	public String getTodayisOpen() {
		return todayisOpen;
	}

	public void setTodayisOpen(String todayisOpen) {
		this.todayisOpen = todayisOpen;
	}

	public List<String> getProductImageHeadsList() {
		return productImageHeadsList;
	}

	public void setProductImageHeadsList(List<String> productImageHeadsList) {
		this.productImageHeadsList = productImageHeadsList;
	}

	public List<String> getProcuctDetailImageList() {
		return procuctDetailImageList;
	}

	public void setProcuctDetailImageList(List<String> procuctDetailImageList) {
		this.procuctDetailImageList = procuctDetailImageList;
	}

	private List<PhiWinnersListDto> phiWinnersListDtoList;
    
    public List<PhiWinnersListDto> getPhiWinnersListDtoList() {
		return phiWinnersListDtoList;
	}

	public void setPhiWinnersListDtoList(
			List<PhiWinnersListDto> phiWinnersListDtoList) {
		this.phiWinnersListDtoList = phiWinnersListDtoList;
	}

	public String getWinnerCounts() {
		return winnerCounts;
	}

	public void setWinnerCounts(String winnerCounts) {
		this.winnerCounts = winnerCounts;
	}

	


	public String getManualCounts() {
		return manualCounts;
	}

	public void setManualCounts(String manualCounts) {
		this.manualCounts = manualCounts;
	}

	public String getRandomCounts() {
		return randomCounts;
	}

	public void setRandomCounts(String randomCounts) {
		this.randomCounts = randomCounts;
	}

	public String getFakeCounts() {
		return fakeCounts;
	}

	public void setFakeCounts(String fakeCounts) {
		this.fakeCounts = fakeCounts;
	}



    public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public String getProductImageHead() {

		return productImageHead;
	}

	public void setProductImageHead(String productImageHead) {
		this.productImageHead = productImageHead;
	}

    
	public String getLogisticsCost() {
		return logisticsCost;
	}

	public void setLogisticsCost(String logisticsCost) {
		this.logisticsCost = logisticsCost;
	}

	public Long getCoupWayId() {
		return coupWayId;
	}

	public void setCoupWayId(Long coupWayId) {
		this.coupWayId = coupWayId;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
    
	public String getQuantity() {
		return quantity;
	}
    
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
    
    public String getWinnerTime() {
		return winnerTime;
	}

	public void setWinnerTime(String winnerTime) {
		this.winnerTime = winnerTime;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
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

	public String getProductStock() {
		return productStock;
	}

	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}

	public String getExchangSuperLimit() {
		return exchangSuperLimit;
	}

	public void setExchangSuperLimit(String exchangSuperLimit) {
		this.exchangSuperLimit = exchangSuperLimit;
	}


	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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
      
  
    public void setProductStatus(String productStatus){
        this.productStatus = productStatus;
    }
      
    public String getProductStatus(){
        return this.productStatus;
    }
      
    public void setMoney(String money){
        this.money = money;
    }
      
    public String getMoney(){
        return this.money;
    }

      
    public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public void setUpTime(String upTime){
        this.upTime = upTime;
    }
      
    public String getUpTime(){
        return this.upTime;
    }
      
    public void setDownTime(String downTime){
        this.downTime = downTime;
    }
      
    public String getDownTime(){
        return this.downTime;
    }
      
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
      
    public String getCreateTime(){
        return this.createTime;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsShop() {
		return isShop;
	}

	public void setIsShop(String isShop) {
		this.isShop = isShop;
	}

	public String getWinnerStatus() {
		return winnerStatus;
	}

	public void setWinnerStatus(String winnerStatus) {
		this.winnerStatus = winnerStatus;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	


	public Long getPhiProductType() {
		return phiProductType;
	}

	public void setPhiProductType(Long phiProductType) {
		this.phiProductType = phiProductType;
	}

	public String getNeedGrade() {
		return needGrade;
	}

	public void setNeedGrade(String needGrade) {
		this.needGrade = needGrade;
	}





	

	

	
	
    

	
}