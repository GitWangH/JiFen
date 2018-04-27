package com.huatek.frame.handle;

public class TokenMessage{
	private String token;
	private String status;
	private String desc;
	public TokenMessage(String token,String status,String desc){
		this.token=token;
		this.status=status;
		this.desc=desc;
		
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}