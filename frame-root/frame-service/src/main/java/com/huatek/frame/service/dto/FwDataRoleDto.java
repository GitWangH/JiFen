package com.huatek.frame.service.dto;


public class FwDataRoleDto implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 数据角色ID.
     */
    private Long id;
    /**
     * 数据角色名称.
     */
    private String name;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 默认构造器
	 */
	public FwDataRoleDto() {

	}
	
	
	
	
	/**
	 * 分配功能权限时校验是否有全部勾选上
	 */
	private boolean ifChecked;

	public boolean isIfChecked() {
		return ifChecked;
	}

	public void setIfChecked(boolean ifChecked) {
		this.ifChecked = ifChecked;
	}
	
}
