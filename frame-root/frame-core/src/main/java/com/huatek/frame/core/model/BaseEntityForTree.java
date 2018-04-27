package com.huatek.frame.core.model;

public abstract class BaseEntityForTree implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * 实体主键.
	 * @return 主键ID.
	 */
	public abstract String getId(); 
	
	public abstract void setId(String id);
}
