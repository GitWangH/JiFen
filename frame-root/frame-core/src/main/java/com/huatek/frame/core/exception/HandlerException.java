package com.huatek.frame.core.exception;

public interface HandlerException {
	
	public String getModule();
	
	public Throwable getRootCause();
	
	public String getErrorCode();
	
	public String getMessage();
}
