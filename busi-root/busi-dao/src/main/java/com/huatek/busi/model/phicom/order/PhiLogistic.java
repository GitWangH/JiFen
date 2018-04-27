package com.huatek.busi.model.phicom.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiLogistic
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-29 17:41:49
  * @version: 1.0
  */

@Entity
@Table(name = "phi_logistic")
public class PhiLogistic extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	/** @Fields id :物流id*/ 
	@Column(name= "id", nullable = false, length=20)
	private Long id;
	
	
	/**物流和订单单项维护（物流删除了订单还在，所以）*/
//	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "order_id", referencedColumnName = "order_id", unique = true)
//    private PhiOrder phiOrder;
	
	
	/** @Fields orderId :订单id*/ 
	@Column(name= "order_id", nullable = false, length=20)
	private Long orderId;
	
	
	/** @Fields orderInfo_id :订单详情id*/ 
	@Column(name= "orderInfo_id", nullable = false, length=20)
	private Long orderInfoId;
	
	
	/** @Fields 会员id :会员id*/ 
	@Column(name= "member_id", nullable = false, length=20)
	private Long memberId;
	
	
	/** @Fields comname :快递公司*/ 
	@Column(name= "comname", nullable = true, length=255)
	private String comname;
	
	
	/** @Fields com :快递公司编码*/ 
	@Column(name= "com", nullable = true, length=255)
	private String com;
	
	
	/** @Fields com :快递单号*/ 
	@Column(name= "nu", nullable = true, length=255)
	private String nu;
	
	
	/** @Fields sender :发货人*/ 
	@Column(name= "sender", nullable = true, length=255)
	private String sender;
	
	/** @Fields send_phone :发货电话*/ 
	@Column(name= "send_phone", nullable = true, length=255)
	private String sendPhone;
	
	/** @Fields sender :发货时间*/ 
	@Column(name= "send_time", nullable = true)
	private Date sendTime;
	
	/** @Fields remark :物流详情*/ 
	@Column(name= "remark", nullable = true, length=400)
	private String remark;
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getComname() {
		return comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendPhone() {
		return sendPhone;
	}

	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	

}
