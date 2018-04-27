package com.huatek.busi.dto.external;


/**
 * 
* @ClassName: ExternalWithDataResponse 
* @Description: 带返回data的响应实体
* @author eden_sun
* @date Jan 3, 2018 1:43:36 PM 
*
 */
public class ExternalWithDataResponse {
	private int statusCode;
	private String responseContent;
	private Object data;
	
	public ExternalWithDataResponse(int result, String msg,Object jsonData) {
		super();
		this.statusCode = result;
		this.responseContent = msg;
		this.data=jsonData;
	}
	
	public ExternalWithDataResponse(int result, String msg,String jsonData) {
		super();
		this.statusCode = result;
		this.responseContent = msg;
		this.data=jsonData;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
