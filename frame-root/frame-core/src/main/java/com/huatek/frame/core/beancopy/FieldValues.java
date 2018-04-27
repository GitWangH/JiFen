package com.huatek.frame.core.beancopy;

import java.util.Map;

/***
 * 本类用于记录属于数据字典类的字段，以及对应的数据字典值.
 * @author winner pan.
 *
 */
public class FieldValues implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/***
	 * 字段名称.
	 */
	String fieldName;
	/***
	 * 对应的数据集合.
	 */
	Map<String, String> values; 
	
	
	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public Map<String, String> getValues() {
		return values;
	}


	public void setValues(Map<String, String> values) {
		this.values = values;
	}


	public FieldValues(String fieldName, Map<String, String> values){
		this.fieldName = fieldName;
		this.values = values;
	}
}
