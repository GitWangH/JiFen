package com.huatek.frame.service.dto;

public class FwRoleDto implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    /**
     * 分配功能权限时校验是否有全部勾选上
     */
    private boolean ifChecked;

    private String rolecode;

    private String comments;
    
    /**
     * 角色组ID
     */
    private Long groupId;
    
    /**
     * 租户ID
     */
    private Long tenantId;
    
    /**
     * 角色组名称
     */
    private String groupName;
    
    /**
     * 角色类型(用于显示)
     */
    private String type;
    
    private Long parentId;
    
    private Integer orgType;

    public String getRolecode() {
	return rolecode;
    }

    public void setRolecode(String rolecode) {
	this.rolecode = rolecode;
    }

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

   

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    public boolean isIfChecked() {
	return ifChecked;
    }

    public void setIfChecked(boolean ifChecked) {
	this.ifChecked = ifChecked;
    }

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getType() {
		return this.tenantId == null?"系统角色":"用户角色";
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	
}
