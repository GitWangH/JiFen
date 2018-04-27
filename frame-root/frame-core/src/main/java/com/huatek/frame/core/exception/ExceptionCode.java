package com.huatek.frame.core.exception;

public interface ExceptionCode {
	/***
	 * 模块名称.
	 */
	String getModuleName();
	/**
	 * 返回异常码
	 */
	String getErrorCode();

	/**
	 * 返回异常描述
	 * 
	 * @param args
	 *            异常描述所用到的占位符值数组
	 */
	String getErrorMesage();
}
