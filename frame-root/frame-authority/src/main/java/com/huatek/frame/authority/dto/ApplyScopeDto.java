package com.huatek.frame.authority.dto;


public class ApplyScopeDto {

    /**
     * 业务模块应用配置ID.
     */
    protected Long id;
    /**
     * 目标类.
     */
    protected String targetClass;
    
    /**
     * 目标字段
     */
    protected String targetField;
    
    /**
     * 是否应用
     */
    protected int isEnable;
    
    /**
     * 业务模块名称
     */
    protected String businessMapName;
    
    /**
     * 业务模块外键
     */
    protected Long businessMapId;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	public String getTargetField() {
		return targetField;
	}

	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}

	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public String getBusinessMapName() {
		return businessMapName;
	}

	public void setBusinessMapName(String businessMapName) {
		this.businessMapName = businessMapName;
	}

	public Long getBusinessMapId() {
		return businessMapId;
	}

	public void setBusinessMapId(Long businessMapId) {
		this.businessMapId = businessMapId;
	}

	/**
	 * 默认构造器
	 */
	public ApplyScopeDto() {

	}
	
	
}
