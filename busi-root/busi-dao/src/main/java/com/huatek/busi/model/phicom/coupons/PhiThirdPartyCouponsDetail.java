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
import com.huatek.frame.core.model.BaseEntity;

/**
 * 第三方券明细实体类.
 * 
 * @ClassName: PhiThirdPartyCouponsDetail
 * @Description:
 * @author: jordan_li
 * @Email :
 * @date: 2018-01-20 10:48:05
 * @version: Windows 7
 */

@Entity
@Table(name = "phi_third_party_coupons_detail")
public class PhiThirdPartyCouponsDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "coup_detail_id", nullable = true)
	private Long id;

	/** @Fields coupId : 优惠券id */
	@Column(name = "coup_id", nullable = false, length = 100)
	private String coupId;

	/** @Fields coupCode : 劵码 */
	@Column(name = "coup_code", nullable = false, length = 100)
	private String coupCode;

	/** @Fields exchangeStatus : 兑换状态(1表示已兑换，2表示未兑换) */
	@Column(name = "exchange_status", nullable = false, length = 20)
	private String exchangeStatus;

	/** @Fields coupStatus : 券状态（1表示已使用，2表示未使用，3表示已过期） */
	@Column(name = "coup_status", nullable = false, length = 20)
	private String coupStatus;

	/** @Fields phiMember : 会员id */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "member_id")
	private PhiMember phiMember;

	/** @Fields startTime : 开始时间 */
	@Column(name = "start_time", nullable = false)
	private Date startTime;

	/** @Fields endTime : 结束时间 */
	@Column(name = "end_time", nullable = false)
	private Date endTime;

	/** @Fields coupUid : 用户的UID */
	@Column(name = "coup_uid", nullable = false)
	private Long coupUid;

	@Version
	private int version;


	public void setCoupId(String coupId) {
		this.coupId = coupId;
	}

	public String getCoupId() {
		return this.coupId;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}

	public String getCoupCode() {
		return this.coupCode;
	}

	public void setExchangeStatus(String exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

	public String getExchangeStatus() {
		return this.exchangeStatus;
	}

	public void setCoupStatus(String coupStatus) {
		this.coupStatus = coupStatus;
	}

	public String getCoupStatus() {
		return this.coupStatus;
	}

	public void setPhiMember(PhiMember phiMember) {
		this.phiMember = phiMember;
	}

	public PhiMember getPhiMember() {
		return this.phiMember;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setCoupUid(Long coupUid) {
		this.coupUid = coupUid;
	}

	public Long getCoupUid() {
		return this.coupUid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	

}
