package com.huatek.frame.core.dto;

import java.util.List;

/**
 * @Description: 参数字典dto
 * @author caojun1@hisense.com
 * @date 2016年1月13日 下午1:18:15
 * @version V1.0
 * 
 */
public class ParamsDto {

	private String fieldName = "";
	private String displayName = "";
	private String defaultValue = "";
	private String isRequired = "";
	private boolean isShow;
	private int index;
	private List<ParamDto> params;


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<ParamDto> getParams() {
		return params;
	}

	public void setParams(List<ParamDto> params) {
		this.params = params;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
