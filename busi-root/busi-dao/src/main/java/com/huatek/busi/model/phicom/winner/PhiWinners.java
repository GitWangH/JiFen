package com.huatek.busi.model.phicom.winner;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.frame.core.model.BaseEntity;
@Entity
@Table(name = "phi_winners")
public class PhiWinners extends BaseEntity{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "winner_id", nullable = true )
 	private Long id;
	
	@ManyToOne //JPA注释： 一对一 关系
	@JoinColumn(name="product_id",referencedColumnName = "product_id" )//
	private PhiProduct  productId;

	
	@Column(name = "user_type",nullable = true, length=100 )
	private String userType;

	@Column(name = "user_name",nullable = true, length=100 )
	private String userName;

	@Column(name = "mobile",nullable = true, length=100 )
	private String  mobile;
	
	@Column(name = "winners_type",nullable = true, length=100 )
	private String  winnersType;
	
	
	@OneToOne(optional = false, cascade = CascadeType.REFRESH) //JPA注释： 一对一 关系
	@JoinColumn(name="member_id",referencedColumnName = "member_id", unique = true )//
	private PhiMember  memberId;
	

	public PhiProduct getProductId() {
		return productId;
	}

	public void setProductId(PhiProduct productId) {
		this.productId = productId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	public String getWinnersType() {
		return winnersType;
	}

	public void setWinnersType(String winnersType) {
		this.winnersType = winnersType;
	}

	

	public PhiMember getMemberId() {
		return memberId;
	}

	public void setMemberId(PhiMember memberId) {
		this.memberId = memberId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
