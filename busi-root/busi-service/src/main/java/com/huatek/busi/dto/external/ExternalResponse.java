package com.huatek.busi.dto.external;

public class ExternalResponse {
	private int statusCode;
	private String responseContent;
	
	public ExternalResponse(int result, String msg) {
		super();
		this.statusCode = result;
		this.responseContent = msg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int result) {
		this.statusCode = result;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String msg) {
		this.responseContent = msg;
	}

	
}
