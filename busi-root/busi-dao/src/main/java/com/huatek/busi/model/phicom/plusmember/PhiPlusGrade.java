package com.huatek.busi.model.phicom.plusmember;

import java.math.BigDecimal;
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
  * @ClassName: PhiPlusGrade
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-03 19:59:33
  * @version: 1.0
  */

@Entity
@Table(name = "phi_plus_grade")
public class PhiPlusGrade extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "plus_id", nullable = false)
	private Long id;
	
	/** @Fields plusCode :plus会员code*/ 
	@Column(name= "plus_code", nullable = false, length=20)
	private String plusCode;

	/** @Fields plusGrade :plus会员等级名称*/ 
	@Column(name= "plus_grade", nullable = false, length=20)
    private String plusGrade;
	
	/** @Fields creatorId :创建人id*/ 
	@Column(name= "creator_id", nullable = false, length=20)
    private Long creatorId;
	
	/** @Fields createTime :创建时间*/ 
	@Column(name= "create_time", nullable = false, length=20)
    private Date createTime;
	
	/** @Fields rechargeMoney :充值金额*/ 
	@Column(name= "recharge_money", nullable = false, length=20)
    private BigDecimal rechargeMoney;
	
	/** @Fields image :图标*/ 
	@Column(name= "image", nullable = false, length=20)
    private String image;
	
	/** @Fields remark :图标*/ 
	@Column(name= "remark", nullable = false, length=20)
    private String remark;
	
	/** @Fields validTime :有效期*/ 
	@Column(name= "valid_time", nullable = false, length=20)
    private Date validTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getPlusCode() {
		return plusCode;
	}

	public void setPlusCode(String plusCode) {
		this.plusCode = plusCode;
	}

	public String getPlusGrade() {
		return plusGrade;
	}

	public void setPlusGrade(String plusGrade) {
		this.plusGrade = plusGrade;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	
	
	/** @Fields phirightSet :  *//*
	//mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy= "phiPlusGrade")
    private Set<PhiRight> phirightSet;
    
    
      
    public void setPhirightSet(Set<PhiRight> phirightSet){
        this.phirightSet = phirightSet;
    }
      
    public Set<PhiRight> getPhirightSet(){
        return this.phirightSet;
    }
      */

}
