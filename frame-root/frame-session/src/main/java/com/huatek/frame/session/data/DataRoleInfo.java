package com.huatek.frame.session.data;

public class DataRoleInfo implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 数据角色ID.
	 */
	private long id;
	/***
	 * 数据角色名称.
	 */
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
