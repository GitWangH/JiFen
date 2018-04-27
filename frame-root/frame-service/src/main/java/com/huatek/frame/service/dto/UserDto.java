package com.huatek.frame.service.dto;

import java.util.Date;

public class UserDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String acctName;
	private String userName;
	private String status;
	private Date lastLoginTime;
	private Integer isLocked;
	private Integer gender;
	private String email;
	private String phone;
	private Date createTime;
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return "A".equals(this.status) ? "有效" : "D".equals(this.status) ? "无效" : "未配置";
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIsLocked() {
		return isLocked==null ? "":(1 == isLocked ? "已锁定" : -1 == isLocked ? "未锁定" : "未配置");
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public String getGender() {
		return gender == null? "":(0 == gender ? "男" : 1 == gender ? "女" : "未配置");
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
