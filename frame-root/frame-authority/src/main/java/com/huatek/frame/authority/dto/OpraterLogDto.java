package com.huatek.frame.authority.dto;

import java.util.Date;

public class OpraterLogDto {
    private Long actLogId;
    private Date actionTime;
    private String clientIp;
    private Long clientPort;
    private Long actionId;
    private Long acctId;
    private String acctName;
    private Long menuId;
    private String orgName;
    private String msg;
    private String userAgent;
    
    
    public Long getMenuId() {
		return menuId;
	}



	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	private String opretePath;
    
    /**
	 * 默认构造器
	 */
	public OpraterLogDto(){}
	
	
	
    public void setActLogId(Long actLogId){
        this.actLogId = actLogId;
    }
      
	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public Long getActLogId(){
        return this.actLogId;
    }
      
    public void setActionTime(Date actionTime){
        this.actionTime = actionTime;
    }
      
    public Date getActionTime(){
        return this.actionTime;
    }
      
    public void setClientIp(String clientIp){
        this.clientIp = clientIp;
    }
      
    public String getClientIp(){
        return this.clientIp;
    }
      
    public void setClientPort(Long clientPort){
        this.clientPort = clientPort;
    }
      
    public Long getClientPort(){
        return this.clientPort;
    }
      


	public String getOpretePath() {
		return opretePath;
	}

	public void setOpretePath(String opretePath) {
		this.opretePath = opretePath;
	}
	
	

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}



    public String getOrgName() {
        return orgName;
    }



    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public String getUserAgent() {
		return userAgent;
	}



	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}



 
}