package com.huatek.busi.model.phicom.member;



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
  * @ClassName: PhiMemberAddress
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-30 13:37:51
  * @version: 1.0
  */

@Entity
@Table(name = "phi_member_address")
public class PhiMemberAddress extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "address_id", nullable = false)
	private Long id;
	
	/*
	/**一个会员有多个收货地址*//*
    @ManyToOne(optional = true)
	@JoinColumn(name = "member_id")
	private PhiMember phiMember;*/
	
	@Column(name = "member_id", nullable = false)
	private Long memberId;
	
    /** @Fields country : 国家 */
	@Column(name = "country", nullable = true, length = 50)
    private String country;
    
	/** @Fields province : 省 */
	@Column(name = "province", nullable = true, length = 50)
    private String province;
    
	/** @Fields city : 市 */
	@Column(name = "city", nullable = true, length = 50)
    private String city;
    
	/** @Fields area : 区 */
	@Column(name = "area", nullable = true, length = 50)
    private String area;
    
	/** @Fields street : 街道 */
	@Column(name = "street", nullable = true, length = 200)
    private String street;
    
	/** @Fields addressDetail : 详细地址 */
	@Column(name = "address_detail", nullable = true, length = 200)
    private String addressDetail;
    
	/** @Fields tel : 电话 */
	@Column(name = "tel", nullable = true, length = 50)
    private String tel;
    
	/** @Fields name : 联系人姓名 */
	@Column(name = "name", nullable = true, length = 50)
    private String name;
	
	/** @Fields zipCode : 邮编 */
	@Column(name = "zip_code", nullable = true, length = 50)
    private String zipCode;
    
	
	/** @Fields defaultState : 是否默认(1，默认。0不默认)*/
	@Column(name = "default_state", nullable = true, length = 50)
    private String defaultState;
    
	/** @Fields day : 上门日期 */
	@Column(name = "day", nullable = true, length = 100)
    private String day;
    
	/** @Fields time : 上门时间 */
	@Column(name = "time", nullable = true, length = 100)
    private String time;
    
	/** @Fields time : 上门时间 */
	@Column(name = "created_time", nullable = true, length = 100)
    private Date createTime;
	
	@Column(name = "uid", nullable = true, length = 100)
	private Integer UId;

	public Integer getUId() {
		return UId;
	}

	public void setUId(Integer uId) {
		UId = uId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	/*	public PhiMember getPhiMember() {
		return phiMember;
	}

	public void setPhiMember(PhiMember phiMember) {
		this.phiMember = phiMember;
	}
*/
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDefaultState() {
		return defaultState;
	}

	public void setDefaultState(String defaultState) {
		this.defaultState = defaultState;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	} 
     
	

}
