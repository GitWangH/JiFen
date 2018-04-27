package com.huatek.frame.authority.dto;

import java.io.Serializable;
import java.util.Date;

public class ExceptionLogDto {
	
	private Long id;
	
	private Long acctId;
	
	private String acctName;

	private Long sourceId;
	
	private String sourceName;
	private String sourcePath;
	private String ecptModule;
	
	private String ecptCode;
	
	private String ecptMessage;
	private String ecptStack;
	
	
	private Date createTime;

	public ExceptionLogDto() {
	}

	public ExceptionLogDto(Long id, Long acctId, String acctName,
			Long sourceId, String sourceName, String ecptModule, 
			String ecptCode, String ecptMessage, Date createTime) {
		this.id = id;
		this.acctName = acctName;
		this.sourceName = sourceName;
		this.ecptModule = ecptModule;
		this.ecptCode = ecptCode;
		this.ecptMessage = ecptMessage;
		this.createTime = createTime;
		this.acctId = acctId;
		this.sourceId = sourceId;
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

	public Serializable getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
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

	public String getEcptStack() {
		return ecptStack;
	}

	public void setEcptStack(String ecptStack) {
		this.ecptStack = ecptStack;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

}
