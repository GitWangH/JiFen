package com.huatek.frame.core.model;

public abstract class BaseEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * 实体主键.
	 * @return 主键ID.
	 */
	public abstract Long getId(); 
	
	public abstract void setId(Long id);
}
