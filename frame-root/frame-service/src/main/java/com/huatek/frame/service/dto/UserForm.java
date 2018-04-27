package com.huatek.frame.service.dto;



public class UserForm implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String acctName;
	String userName;
	String acctPwd;
	String newAcctPwd;
	String verifyCode;
	String status;
	
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
	public String getAcctPwd() {
		return acctPwd;
	}
	public void setAcctPwd(String acctPwd) {
		this.acctPwd = acctPwd;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getNewAcctPwd() {
		return newAcctPwd;
	}
	public void setNewAcctPwd(String newAcctPwd) {
		this.newAcctPwd = newAcctPwd;
	}
	
}
