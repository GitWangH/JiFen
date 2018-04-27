package com.huatek.frame.core.exception;

/***
 * 如果需要中断某个业务操作，请抛出本异常.
 * @author 潘仁胜
 *
 */
public class BusinessRuntimeException extends RuntimeException implements HandlerException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Throwable rootCause;
	
	private String module;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public BusinessRuntimeException(String message, String code) {
		super(message);
		this.errorCode = code;
		rootCause = this;
	}
	
	public BusinessRuntimeException(ExceptionCode exceptionCode){
		super(exceptionCode.getErrorMesage());
		this.errorCode = exceptionCode.getErrorCode();
		this.module = exceptionCode.getModuleName();
	}

	public BusinessRuntimeException() {
		super();
	}

	public BusinessRuntimeException(String message, String code,  Throwable e) {
		this(message, code);
		if (e instanceof BusinessRuntimeException) {
			rootCause = ((BusinessRuntimeException) e).rootCause;
		} else {
			rootCause = e;
		}
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
