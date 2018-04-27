package com.huatek.busi.dto.phicom.order;

import java.math.BigDecimal;
import java.util.Date;

public class PhiPayInfoDto {
    private Long orderId;
    private Long id;
	private String orderCode;
    private Date payTime;
    private BigDecimal orderMoney;
    private String address;
    private String remark;
    private BigDecimal score;
    private BigDecimal logisticCost;
    
    /**
	 * 默认构造器
	 */
	public PhiPayInfoDto(){}
	
     /**
	 * 生成getter，setter 访问器
	 */
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
      
    public Long getOrderId(){
        return this.orderId;
    }
      
      
    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }
      
    public String getOrderCode(){
        return this.orderCode;
    }
      
    public void setPayTime(Date payTime){
        this.payTime = payTime;
    }
      
    public Date getPayTime(){
        return this.payTime;
    }
      
    public void setOrderMoney(BigDecimal orderMoney){
        this.orderMoney = orderMoney;
    }
      
    public BigDecimal getOrderMoney(){
        return this.orderMoney;
    }
      
    public void setAddress(String address){
        this.address = address;
    }
      
    public String getAddress(){
        return this.address;
    }
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    public void setScore(BigDecimal score){
        this.score = score;
    }
      
    public BigDecimal getScore(){
        return this.score;
    }
      
    public void setLogisticCost(BigDecimal logisticCost){
        this.logisticCost = logisticCost;
    }
      
    public BigDecimal getLogisticCost(){
        return this.logisticCost;
    }
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
      
    
}