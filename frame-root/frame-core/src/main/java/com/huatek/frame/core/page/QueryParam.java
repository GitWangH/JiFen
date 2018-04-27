package com.huatek.frame.core.page;

public class QueryParam implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 查询字段.
	 */
	String field;
	/***
	 * 操作符号.
	 */
	String logic;
	/**
	 * 查询值.
	 */
	String value;
	/***
	 * 字段类型.
	 */
	String type;
	
	String[] items;

	
	/**
	 * @author caojun1@hisense.com 20160305
	 */
	public QueryParam() {

	}

	public QueryParam(String field, String logic, String value, String type,
			String[] items) {
		super();
		this.field = field;
		this.logic = logic;
		this.value = value;
		this.type = type;
		this.items = items;
	}



	/**
	 * @param field
	 * @param type
	 * @param logic
	 * @param value
	 * @author @author caojun1@hisense.com 20160305
	 */
	public QueryParam(String field, String type, String logic, String value) {
		this.setField(field);
		this.setLogic(logic);
		this.setType(type);
		this.setValue(value);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
}