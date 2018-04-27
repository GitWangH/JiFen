package com.huatek.file.dto;


public class CmdFileCatalogDto  {

 	private Long id;
 
    
	/** @Fields name : 路径名*/ 
    private String name;
    
    
	/** @Fields code : 代码*/ 
    private String code;
    
    
	/** @Fields path : 路径*/ 
    private String path;
    
    
	/** @Fields remark : 备注*/ 
    private String remark;
    
    
	/** @Fields tenantId :  */
    private Long tenantId;
    
    
	/** @Fields parentId : 父级目录ID */
    private Long parentId;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getTenantId() {
		return tenantId;
	}


	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
    
}
