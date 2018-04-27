package com.huatek.busi.model.phicom.coupons;

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
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.phicom.member.PhiMember;

@Entity
@Table(name = "phi_timing_push_coupons_detail")
public class PhiTimingPushCouponsDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "id", nullable = false)
	private Long id;// 定时任务明细id

	@Column(name = "coup_id", nullable = true)
	private Long cpnsId;// 优惠劵明细id

	@Column(name = "coup_way_id", nullable = true, length = 100)
	private Long coupWayId;// 方案id

	@Column(name = "coup_code", nullable = true, length = 100)
	private String coupCode;// 优惠劵编码

	@Column(name = "push_status", nullable = true, length = 100)
	private Long pushStatus;// 推送状态（1表示已推送，2表示未推送）

	@ManyToOne(cascade = {CascadeType.ALL }, optional = true)
	@JoinColumn(name = "member_id", nullable = true)
	private PhiMember phiMember;

	@Column(name = "create_time", nullable = true, length = 100)
	private Date createTime;// 创建时间

	@Column(name = "push_time", nullable = true, length = 100)
	private Date pushTime;// 推送时间

	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Long pushStatus) {
		this.pushStatus = pushStatus;
	}
	
	public PhiMember getPhiMember() {
		return phiMember;
	}

	public void setPhiMember(PhiMember phiMember) {
		this.phiMember = phiMember;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
