package com.huatek.frame.authority.util;

import java.io.Serializable;

import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.data.UserInfo;

public class ClientInfoBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long menuId;

	private String hostIp;
	private CloudMember cloudMember;
	public CloudMember getCloudMember() {
		return cloudMember;
	}
	public void setCloudMember(CloudMember cloudMember) {
		this.cloudMember = cloudMember;
	}
	private Boolean isTransaction = false;
	public Boolean isTransaction() {
		return isTransaction;
	}
	public void setTransaction(Boolean isTransaction) {
		this.isTransaction = isTransaction;
	}
	/***
	 * 远程调用的请求ID.
	 * 用于保存当前请求ID的事务数据的关系.
	 */
	private String requestId;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	/***
	 * 事务状态值.
	 * 0为初始操作,
	 * 1为成功操作,
	 * -1为失败操作.
	 */
	private int transtatus;
	
	public int getTranstatus() {
		return transtatus;
	}
	public void setTranstatus(int transtatus) {
		this.transtatus = transtatus;
	}
	private UserInfo operator;



	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public UserInfo getOperator() {
		return operator;
	}

	public void setOperator(UserInfo operator) {
		this.operator = operator;
	}
	
	

}
