package com.huatek.frame.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.model.FwRoleGroup;

/**
 * 系统功能角色.
 * 
 * @author winner pan.
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "FW_ROLE")
public class FwRole extends BaseEntity {

    /**
	 *
	 */
    private static final long serialVersionUID = -8474025844522178714L;
    @Id
    @Column(name = "role_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    private Long id;

    /**
     * 角色描述.
     */
    @Column(name = "comments", nullable = true)
    private String comments;
    /***
     * 角色名称.
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 角色编码.
     */
    @Column(name = "role_code", nullable = false)
    private String rolecode;
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;
    
    /**
     * 组织类型
     */
    @Column(name = "ORG_TYPE", nullable = false)
    private Integer orgType;
    
    /**
     * 角色组
     */
    @ManyToOne()
    @JoinColumn(name = "GROUP_ID")
    private FwRoleGroup fwRoleGroup;
  
    /**
     * @return the rolecode
     */
    public String getRolecode() {
	return rolecode;
    }

    /**
     * @param rolecode
     */
    public void setRolecode(String rolecode) {
	this.rolecode = rolecode;
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

    /*
     * public FwOrg getFwGroup() { return fwGroup; }
     * 
     * public void setFwGroup(FwOrg fwGroup) { this.fwGroup = fwGroup; }
     */
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

	public FwRoleGroup getFwRoleGroup() {
		return fwRoleGroup;
	}

	public void setFwRoleGroup(FwRoleGroup fwRoleGroup) {
		this.fwRoleGroup = fwRoleGroup;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	
}
