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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

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
@Table(name = "phi_pay_info")
public class PhiPayInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	/** @Fields orderDetailId :订单详情主键*/ 
	@Column(name= "order_detail_id", nullable = true, length=100 )
    private Long id;
	
	/**一个订单对应一个支付信息*/
	
	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", unique = true)
    private PhiOrder phiOrder;
    
	/** @Fields orderCode : 订单编号*/ 
	@Column(name= "order_code", nullable = true, length=100 )
    private String orderCode;
    
    
    
	/** @Fields payTime : 支付时间*/ 
	@Column(name= "pay_time", nullable = false)
    private Date payTime;
    
    
	/** @Fields orderMoney : 支付金额*/ 
	@Column(name= "order_money", nullable = true, length=100 )
    private BigDecimal orderMoney;
    
   
	/** @Fields address : 订单地址*/ 
	@Column(name= "address", nullable = true, length=100 )
    private String address;
    
    
	/** @Fields remark : 订单描述*/ 
	@Column(name= "remark", nullable = true )
    private String remark;
    
    
	/** @Fields score : 消耗积分*/
	@Column(name= "score")
    private BigDecimal score;
    
    
	
	/** @Fields logisticCost : 物流费用*/ 
	@Column(name= "logistic_cost",  length=100 )
    private BigDecimal logisticCost;



	public String getOrderCode() {
		return orderCode;
	}



	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}



	public Date getPayTime() {
		return payTime;
	}



	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}



	public BigDecimal getOrderMoney() {
		return orderMoney;
	}



	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public BigDecimal getScore() {
		return score;
	}



	public void setScore(BigDecimal score) {
		this.score = score;
	}



	public BigDecimal getLogisticCost() {
		return logisticCost;
	}



	public void setLogisticCost(BigDecimal logisticCost) {
		this.logisticCost = logisticCost;
	}

	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public PhiOrder getPhiOrder() {
		return phiOrder;
	}



	public void setPhiOrder(PhiOrder phiOrder) {
		this.phiOrder = phiOrder;
	}



	public DataPage<PhiPayInfo> getAllPhiPayInfo(QueryPage queryPage) {
		// TODO Auto-generated method stub
		return null;
	}


}
