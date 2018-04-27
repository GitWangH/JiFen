package com.huatek.frame.service.dto;


public class FwAccountDto implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String acctName;
    private String userName;
    private String acctPwd;
    private Long id;
    private String status;
    private Long orgId;
    private String orgName;
    private String orgShortName;
    private String orgType;
    private String orgCode;
    private Long deptId;
    private String deptName;
    private String identityCardNo;
    private String email;
    private String phone;
    private String isExternal;
    private Long tenantId;
    private Integer sex;
    private Boolean isManager;
    
    /**
	 * 默认构造器
	 */
	public FwAccountDto(){}
	
	 
     public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 生成getter，setter 访问器
	 */
    public void setAcctName(String acctName){
        this.acctName = acctName;
    }
      
    public String getAcctName(){
        return this.acctName;
    }
      
    public void setUserName(String userName){
        this.userName = userName;
    }
      
    public String getUserName(){
        return this.userName;
    }
      
    public void setAcctPwd(String acctPwd){
        this.acctPwd = acctPwd;
    }
      
    public String getAcctPwd(){
        return this.acctPwd;
    }


	public Long getOrgId() {
		return orgId;
	}


	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public Long getDeptId() {
		return deptId;
	}


	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getIdentityCardNo() {
		return identityCardNo;
	}


	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getIsExternal() {
		return isExternal;
	}


	public void setIsExternal(String isExternal) {
		this.isExternal = isExternal;
	}


	public Long getTenantId() {
		return tenantId;
	}


	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}


	public String getOrgType() {
		return orgType;
	}


	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}


	public Integer getSex() {
		return sex;
	}


	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getOrgShortName() {
		return orgShortName;
	}


	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}


	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	
}