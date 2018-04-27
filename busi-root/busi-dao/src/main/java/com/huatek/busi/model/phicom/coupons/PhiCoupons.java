package com.huatek.busi.model.phicom.coupons;

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

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.model.BaseEntity;

@Entity
@Table(name = "phi_coupons")
public class PhiCoupons extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "")
    @Column(name= "cpns_way_id", nullable = true )
	private Long cpnsWayId;
	
	
	@Column(name = "cpns_name",nullable = true, length=100 )
	private String cpnsName;
	
	
	@Column(name = "cpns_quantity",nullable = true, length=100 )
	private BigDecimal cpnsQuantity;
	
	@Column(name = "cpns_type",nullable = true, length=100 )
	private String cpnsType;
	
	@Column(name = "cpns_desc",nullable = true, length=100 )
	private String cpnsDesc;
	
	@Column(name = "cpns_way",nullable = true, length=100 )
	private String cponWay;
	
	@Column(name = "cpns_valid",nullable = true, length=100 )
	private Date cpnsValid;
	
	@Column(name = "cpns_money",nullable = true, length=100 )
	private int cpnsMoney;


	public int getCpnsMoney() {
		return cpnsMoney;
	}

	public void setCpnsMoney(int cpnsMoney) {
		this.cpnsMoney = cpnsMoney;
	}

	public Date getCpnsValid() {
		return cpnsValid;
	}

	public void setCpnsValid(Date cpnsValid) {
		this.cpnsValid = cpnsValid;
	}

	public Long getCpnsWayId() {
		return cpnsWayId;
	} 

	public void setCpnsWayId(Long cpnsWayId) {
		this.cpnsWayId = cpnsWayId;
	}

	public String getCpnsName() {
		return cpnsName;
	}

	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}

	public BigDecimal getCpnsQuantity() {
		return cpnsQuantity;
	}

	public void setCpnsQuantity(BigDecimal cpnsQuantity) {
		this.cpnsQuantity = cpnsQuantity;
	}

	public String getCpnsType() {
		return cpnsType;
	}

	public void setCpnsType(String cpnsType) {
		this.cpnsType = cpnsType;
	}

	public String getCpnsDesc() {
		return cpnsDesc;
	}

	public void setCpnsDesc(String cpnsDesc) {
		this.cpnsDesc = cpnsDesc;
	}

	public String getCponWay() {
		return cponWay;
	}

	public void setCponWay(String cponWay) {
		this.cponWay = cponWay;
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

}
