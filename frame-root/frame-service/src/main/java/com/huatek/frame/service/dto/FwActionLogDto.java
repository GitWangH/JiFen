package com.huatek.frame.service.dto;

import java.util.Date;

public class FwActionLogDto implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 用户ID.
	 */
	private FwAccountDto fwAccount;
	
	/**
	 * 操作path
	 */
	private String opretePath;
	
	/**
	 * 操作时间.
	 */
	private Date actionTime;
	
	/**
	 * 客户端IP.
	 */
	private String clientIp;
	
	/**
	 * 端口.
	 */
	private int clientPort;

	public FwActionLogDto() {
	}

	public FwActionLogDto(Long id, FwAccountDto fwAccount,
			String opretePath, Date actionTime) {
		this.id = id;
		this.fwAccount = fwAccount;
		this.opretePath = opretePath;
		this.actionTime = actionTime;
	}

	public FwActionLogDto(Long id, FwAccountDto fwAccount,
			String opretePath, Date actionTime, String clientIp,
			int clientPort) {
		this.id = id;
		this.fwAccount = fwAccount;
		this.opretePath = opretePath;
		this.actionTime = actionTime;
		this.clientIp = clientIp;
		this.clientPort = clientPort;
	}

	/**  
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public FwAccountDto getFwAccount() {
		return fwAccount;
	}

	public void setFwAccount(FwAccountDto fwAccount) {
		this.fwAccount = fwAccount;
	}

	public String getOpretePath() {
		return opretePath;
	}

	public void setOpretePath(String opretePath) {
		this.opretePath = opretePath;
	}

	/**  
	 * 取得 操作时间.
	 */
	public Date getActionTime() {
		return this.actionTime;
	}

	/**
	 * 设置 操作时间.
	 */
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	/**  
	 * 取得 客户端IP.
	 */
	public String getClientIp() {
		return this.clientIp;
	}

	/**
	 * 设置 客户端IP.
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**  
	 * 取得 端口.
	 */
	public int getClientPort() {
		return this.clientPort;
	}

	/**
	 * 设置 端口.
	 */
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

}
