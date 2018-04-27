package com.huatek.busi.dto.project;


public class BusiRemoteMonitorDto {

 	private Long id;
 
    
	/** @Fields tendersId : 标段 */
    private Long tendersId;
    
    private String tendersName;
    
    private String tendersCode;
    
    
	/** @Fields monitorType : 监控类型*/ 
    private String monitorType;
    
    
	/** @Fields remoteAddress : 远程地址*/ 
    private String remoteAddress;
    
    
	/** @Fields acctName : 用户名*/ 
    private String acctName;
    
    /** @Fields firmCompany : 厂商名称*/ 
    private String firmCompany;
    
    
	/** @Fields acctPass : 密码*/ 
    private String acctPass;
    
    
	/** @Fields md5 : MD5码*/ 
    private String md5;
    
    
	/** @Fields base64 : BASE64加密*/ 
    private String base64;
    
    
	/** @Fields 3des : 3DES加密*/ 
    private String threeDes;
    
    private Long tenantId;
    
    private String remoteAddressPhone;
    
    private String remark;
    
    private String title;
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setTendersId(Long tendersId){
        this.tendersId = tendersId;
    }
      
    public Long getTendersId(){
        return this.tendersId;
    }
      
    public void setMonitorType(String monitorType){
        this.monitorType = monitorType;
    }
      
    public String getMonitorType(){
        return this.monitorType;
    }
      
    public void setRemoteAddress(String remoteAddress){
        this.remoteAddress = remoteAddress;
    }
      
    public String getRemoteAddress(){
        return this.remoteAddress;
    }
      
    public void setAcctName(String acctName){
        this.acctName = acctName;
    }
      
    public String getAcctName(){
        return this.acctName;
    }
      
    public void setAcctPass(String acctPass){
        this.acctPass = acctPass;
    }
      
    public String getAcctPass(){
        return this.acctPass;
    }
      
    public void setMd5(String md5){
        this.md5 = md5;
    }
      
    public String getMd5(){
        return this.md5;
    }
      
    public void setBase64(String base64){
        this.base64 = base64;
    }
      
    public String getBase64(){
        return this.base64;
    }

	public String getThreeDes() {
		return threeDes;
	}

	public void setThreeDes(String threeDes) {
		this.threeDes = threeDes;
	}

	public String getTendersName() {
		return tendersName;
	}

	public void setTendersName(String tendersName) {
		this.tendersName = tendersName;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTendersCode() {
		return tendersCode;
	}

	public void setTendersCode(String tendersCode) {
		this.tendersCode = tendersCode;
	}

	public String getRemoteAddressPhone() {
		return remoteAddressPhone;
	}

	public void setRemoteAddressPhone(String remoteAddressPhone) {
		this.remoteAddressPhone = remoteAddressPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFirmCompany() {
		return firmCompany;
	}

	public void setFirmCompany(String firmCompany) {
		this.firmCompany = firmCompany;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
