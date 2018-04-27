package com.huatek.frame.core.beancopy;

public enum ConvertParam {
	/**
	 * 默认的时间格式化
	 * */
	dateFormatPatten("yyyy-MM-dd HH:mm:ss"),
	/**
	 * 默认的true变量toString的结果
	 * */
	trueValue("1"),
	/**
	 * 默认的false变量toString的结果
	 * */
	falseValue("0");
	
	public final String value;

	ConvertParam(String value) {
		this.value = value;
	}
}
