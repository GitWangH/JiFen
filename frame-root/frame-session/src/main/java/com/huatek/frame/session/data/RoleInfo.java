package com.huatek.frame.session.data;

/***
 * 功能角色数据.
 * 
 * @author winner pan.
 *
 */
public class RoleInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * 角色ID.
	 */
	long id;
	/***
	 * 角色名称.
	 */
	String name;
	String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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
