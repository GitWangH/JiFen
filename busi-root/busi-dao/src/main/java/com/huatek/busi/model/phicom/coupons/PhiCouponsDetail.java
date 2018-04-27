package com.huatek.busi.model.phicom.coupons;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.model.BaseEntity;
@Entity
@Table(name = "phi_coupons_detail")
public class PhiCouponsDetail extends BaseEntity {
private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "coup_id", nullable = true )
	private Long cpnsId;
	
	
	@Column(name = "coup_way_id",nullable = true, length=100 )
	private Long coupWayId ;
	
	
	@Column(name = "coup_code",nullable = true, length=100 )
	private String coupCode;

	

	@Column(name = "exchange_status",nullable = true, length=100 )
	private String exchangeStatus;
	
	@Column(name = "coup_status",nullable = true, length=100 )

	
	private String coupStatus;
	
	@ManyToOne(cascade = {CascadeType.ALL }, optional = true)
	@JoinColumn(name = "member_id", nullable = true)
	private PhiMember memberId;
	
	@Column(name = "start_time",nullable = true, length=100 )
	private Date startTime;
	
	@Column(name = "end_time",nullable = true, length=100 )
	private Date endTime;
	
	@Column(name = "coup_uid",nullable = true, length=100 )
	private Long coupUid;

	@Version
	private int version;

	
	public Long getCpnsId() {
		return cpnsId;
	}

	public void setCpnsId(Long cpnsId) {
		this.cpnsId = cpnsId;
	}

	public Long getCoupWayId() {
		return coupWayId;
	}

	public void setCoupWayId(Long coupWayId) {
		this.coupWayId = coupWayId;
	}

	public String getCoupCode() {
		return coupCode;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}


	public String getExchangeStatus() {
		return exchangeStatus;
	}

	public void setExchangeStatus(String exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

	public String getCoupStatus() {
		return coupStatus;
	}

	public void setCoupStatus(String coupStatus) {
		this.coupStatus = coupStatus;
	}

	

	public PhiMember getMemberId() {
		return memberId;
	}

	public void setMemberId(PhiMember memberId) {
		this.memberId = memberId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getCoupUid() {
		return coupUid;
	}

	public void setCoupUid(Long coupUid) {
		this.coupUid = coupUid;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
