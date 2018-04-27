package com.huatek.rest.util;

public class HttpClientResponseResult {
	private int statusCode;	//httpClient请求返回的状态码。0：失败；1：成功
	private String responseContent;	//httpClient请求返回成功或失败的内容
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

}
