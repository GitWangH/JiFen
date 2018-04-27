package com.huatek.frame.service.dto;


public class FwRoleGroupDto {

 	private Long id;
 
    
	/** @Fields name : 组名*/ 
    private String name;
    
    
	/** @Fields parentId : 父级组 */
    private Long parentId;
    private Long parentName;
    
    
	/** @Fields remark : 备注*/ 
    private String remark;
    
    
	/** @Fields tenantId :  */
    private Long tenantId;
    
    private String type;
    private String text;
    
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
      
    public Long getParentId(){
        return this.parentId;
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

	public Long getParentName() {
		return parentName;
	}

	public void setParentName(Long parentName) {
		this.parentName = parentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
    
}
