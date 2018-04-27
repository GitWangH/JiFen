package com.huatek.frame.service.dto;

public class RoleGroupTreeDto {

	private String id;
	
	private String id_;
 
    
	/** @Fields name : 组名*/ 
    private String name;
    private String rolecode;
    
    
	/** @Fields parentId : 父级组 */
    private Long pId;
    
    
	/** @Fields remark : 备注*/ 
    private String remark;
    
    
	/** @Fields tenantId :  */
    private Long tenantId;
    
    private String child;
    
    public void setId(String id){
        this.id = id;
    }
      
    public String getId(){
        return this.id;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}
	
}
