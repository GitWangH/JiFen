package com.huatek.file.excel.imp.validate;

public class ValidateResult {
	private boolean result;
	private String message;
	public ValidateResult(){
		result=true;
	}
	public ValidateResult(boolean result,String message){
		this.result=result;
		this.message=message;
	}
	public boolean isOk() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
