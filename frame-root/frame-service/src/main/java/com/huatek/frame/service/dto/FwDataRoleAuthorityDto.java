package com.huatek.frame.service.dto;


public class FwDataRoleAuthorityDto implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 数据角色ID.
     */
    private Long id;
    
    /**
	 * 实例ID，如果是字典是编码
	 */
	private String dataId;
	
	/**
	 * 字段值
	 */
	private String fieldValue;
	
	/**
	 * 字段名称
	 */
	private String fieldName;
	
	private Long fwDataRoleId;
	
	private String fwDataRoleName;
	
	private Long fwSourceId;
	
	private String fwSourceName;
	
	private Long fwSourceEntityId;
	
	private String fwSourceEntityName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Long getFwDataRoleId() {
		return fwDataRoleId;
	}

	public void setFwDataRoleId(Long fwDataRoleId) {
		this.fwDataRoleId = fwDataRoleId;
	}

	public String getFwDataRoleName() {
		return fwDataRoleName;
	}

	public void setFwDataRoleName(String fwDataRoleName) {
		this.fwDataRoleName = fwDataRoleName;
	}

	public Long getFwSourceId() {
		return fwSourceId;
	}

	public void setFwSourceId(Long fwSourceId) {
		this.fwSourceId = fwSourceId;
	}

	public String getFwSourceName() {
		return fwSourceName;
	}

	public void setFwSourceName(String fwSourceName) {
		this.fwSourceName = fwSourceName;
	}

	public Long getFwSourceEntityId() {
		return fwSourceEntityId;
	}

	public void setFwSourceEntityId(Long fwSourceEntityId) {
		this.fwSourceEntityId = fwSourceEntityId;
	}

	public String getFwSourceEntityName() {
		return fwSourceEntityName;
	}

	public void setFwSourceEntityName(String fwSourceEntityName) {
		this.fwSourceEntityName = fwSourceEntityName;
	}

	/**
	 * 默认构造器
	 */
	public FwDataRoleAuthorityDto() {

	}
	
	
}
