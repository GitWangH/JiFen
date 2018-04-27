package com.huatek.frame.authority.dto;


public class BusinessMapDto {

    /**
     * 业务模块ID.
     */
    protected Long id;
    /**
     * 业务模块名称.
     */
    protected String name;
    
    /***
     * 菜单Id.
     */
    protected Long fwSourceId;
    
    /**
     * 菜单名称
     */
    protected String fwSourceId_;
    
    
    /**
     * 实体名称
     */
    protected String entityId_;
    
    /**
     *  实体ID
     */
    protected Long entityId;
    
    protected Long fwSystemId;
    
    protected String fwSystemId_;
    
    
	public Long getFwSystemId() {
		return fwSystemId;
	}

	public void setFwSystemId(Long fwSystemId) {
		this.fwSystemId = fwSystemId;
	}

	public String getFwSystemId_() {
		return fwSystemId_;
	}

	public void setFwSystemId_(String fwSystemId_) {
		this.fwSystemId_ = fwSystemId_;
	}

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

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	/**
	 * 默认构造器
	 */
	public BusinessMapDto() {

	}

	public Long getFwSourceId() {
		return fwSourceId;
	}

	public void setFwSourceId(Long fwSourceId) {
		this.fwSourceId = fwSourceId;
	}

	public String getFwSourceId_() {
		return fwSourceId_;
	}

	public void setFwSourceId_(String fwSourceId_) {
		this.fwSourceId_ = fwSourceId_;
	}

	public String getEntityId_() {
		return entityId_;
	}

	public void setEntityId_(String entityId_) {
		this.entityId_ = entityId_;
	}

	
}
