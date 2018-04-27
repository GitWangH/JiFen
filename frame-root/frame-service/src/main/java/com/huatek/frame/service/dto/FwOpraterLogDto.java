package com.huatek.frame.service.dto;



public class FwOpraterLogDto implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long actLogId;
    private String actionTime;
    private String clientIp;
    private Long clientPort;
    private Long actionId;
    private Long acctId;
    private String acctName;
    private String userName;
    private SourceDto fwSource;
    private String opretePath;
    private String userAgent;
    private String orgName;
    private String parentOrgName;
    private String msg;
    public SourceDto getFwSource() {
		return fwSource;
	}



	public void setFwSource(SourceDto fwSource) {
		this.fwSource = fwSource;
	}



	/**
	 * 默认构造器
	 */
	public FwOpraterLogDto(){}
	
	
	
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
      
    public void setActionTime(String actionTime){
        this.actionTime = actionTime;
    }
      
    public String getActionTime(){
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



	public String getUserAgent() {
		return userAgent;
	}



	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}



    public String getUserName() {
        return userName;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getOrgName() {
        return orgName;
    }



    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }



    public String getParentOrgName() {
        return parentOrgName;
    }



    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}




    
	
}