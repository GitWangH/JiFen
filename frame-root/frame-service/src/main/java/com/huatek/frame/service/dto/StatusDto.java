package com.huatek.frame.service.dto;

public class StatusDto implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code;
	String name;
	String value;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
