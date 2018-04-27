package com.huatek.frame.authority.dto;


public class SourceEntityDto {

    /**
     * 组织ID.
     */
    protected Long id;

	protected String entityName;
	
	protected String entityClass;
	
	protected String entityField;
	
	protected String entityColumn;
	
	//protected String queryUrl;
	/***
	 * 数据过滤条件.
	 */
	protected String queryParam;
	/***
	 * 实体别名.
	 */
	protected String entityAlias;
	/**
	 * 输出数据名称.
	 */
	protected String outputKey;
	/***
	 * 该显示字段已经废弃
	 */
	//protected String outputTitle;
	/***
	 * 用去查询数据的类名.
	 */
	protected String outputClass;
	/**
	 * 数据值字段.
	 */
	protected String outputValue;
	/**
	 * 可否为空
	 */
	protected int notNull;
	
	protected String notNull_;
	
	
	public String getNotNull_() {
		return notNull_;
	}

	public void setNotNull_(String notNull_) {
		this.notNull_ = notNull_;
	}

	protected String systemCode;
	
	
	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityField() {
		return entityField;
	}

	public void setEntityField(String entityField) {
		this.entityField = entityField;
	}
	
	public String getEntityColumn() {
		return entityColumn;
	}

	public void setEntityColumn(String entityColumn) {
		this.entityColumn = entityColumn;
	}

	/*public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}*/

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public String getEntityAlias() {
		return entityAlias;
	}

	public void setEntityAlias(String entityAlias) {
		this.entityAlias = entityAlias;
	}

	public String getOutputKey() {
		return outputKey;
	}

	public void setOutputKey(String outputKey) {
		this.outputKey = outputKey;
	}

	/*public String getOutputTitle() {
		return outputTitle;
	}

	public void setOutputTitle(String outputTitle) {
		this.outputTitle = outputTitle;
	}*/

	public String getOutputClass() {
		return outputClass;
	}

	public void setOutputClass(String outputClass) {
		this.outputClass = outputClass;
	}

	public String getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(String outputValue) {
		this.outputValue = outputValue;
	}

	public int getNotNull() {
		return notNull;
	}

	public void setNotNull(int notNull) {
		this.notNull = notNull;
	}

	/**
	 * 默认构造器
	 */
	public SourceEntityDto() {

	}
	
	
}
