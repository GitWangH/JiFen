package com.huatek.frame.service.dto;

import java.io.Serializable;
import java.util.Date;

public class ExceptionLogDto implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long acctId;
	
	private String acctName;
	private String userName;

	private Long sourceId;
	
	private String sourceName;
	
	private String ecptModule;
	
	private String ecptCode;
	
	private String ecptMessage;
	private String ecptStack;
	
	private String createTime;

	public ExceptionLogDto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcctName() {
		return this.acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getEcptModule() {
		return this.ecptModule;
	}

	public void setEcptModule(String ecptModule) {
		this.ecptModule = ecptModule;
	}

	public String getEcptMessage() {
		return this.ecptMessage;
	}

	public void setEcptMessage(String ecptMessage) {
		this.ecptMessage = ecptMessage;
	}

	

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEcptCode() {
		return ecptCode;
	}

	public void setEcptCode(String ecptCode) {
		this.ecptCode = ecptCode;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEcptStack() {
		return ecptStack;
	}

	public void setEcptStack(String ecptStack) {
		this.ecptStack = ecptStack;
	}

}
