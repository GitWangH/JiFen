package com.huatek.busi.model.phicom.member;

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
  * @ClassName: PhiVirtualUser
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-22 12:58:58
  * @version: 1.0
  */

@Entity
@Table(name = "phi_virtual_user")
public class PhiVirtualUser extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -509616727489683526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "virtual_user_id", nullable = false)
	private Long id;
	
	@Column(name = "user_name", nullable = true, length = 100)
    private String userName;
	
	@Column(name = "mobile", nullable = true, length = 100)
    private String mobile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
      

}
