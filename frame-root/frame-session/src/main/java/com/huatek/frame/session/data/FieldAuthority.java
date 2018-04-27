package com.huatek.frame.session.data;


/***
 * 应用于数据权限应用.
 * 本类用于记录需要检查权限的字段所对应的权限数据操作列表.
 * @author winner pan.
 *
 */
public class FieldAuthority implements java.io.Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FieldAuthority(){

	}
	public FieldAuthority(String fieldName){
		this.fieldName = fieldName;
	}
	public FieldAuthority(String fieldName, String authorityDatas){
		this.fieldName = fieldName;
		this.authorityDatas = authorityDatas;
	}
	public FieldAuthority(String entityName,String fieldName, String authorityDatas){
		this.entityName = entityName;
		this.fieldName = fieldName;
		this.authorityDatas = authorityDatas;
	}
	/**
	 * 实体名称.
	 */
	public String entityName;
	/***
	 * 字段名称
	 */
	public String fieldName;
	/**
	 * 数据库字段
	 */
	public String columnName;
	/***
	 * 实体对应的权限列表.
	 */
	public String authorityDatas;
	
	/***
	 * 不在过滤范围的数据.
	 * 不能设置作为权限数据设置.
	 * 表现为：_prefix.字段名=某个数据   and _prefix.字段名=某个数据
	 */
	public String queryParam;
	/***
	 * 是否必填.
	 * 1必填，0非必填.
	 */
	public Integer notNull;

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getAuthorityDatas() {
		return authorityDatas;
	}

	public void setAuthorityDatas(String authorityDatas) {
		this.authorityDatas = authorityDatas;
	}
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getQueryParam() {
		return queryParam;
	}
	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}
	public Integer getNotNull() {
		return notNull;
	}
	public void setNotNull(Integer notNull) {
		this.notNull = notNull;
	}


}
