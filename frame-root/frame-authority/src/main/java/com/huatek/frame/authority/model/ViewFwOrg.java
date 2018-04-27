package com.huatek.frame.authority.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.TreeEntity;

/***
 * 部门组织的父类. 一用于系统的权限管理. 业务部门管理应该继承该实体进行管理.
 * 
 * @author winner pan.
 *
 */
@Entity
@Table(name = "V_FW_ORG")
public class ViewFwOrg extends TreeEntity implements java.io.Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1155807352468031510L;
    @Id
    @Column(name = "org_id", nullable = false)
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Size(max = 60)
    @Column(name = "ORG_NAME", nullable = false)
    private String name;

    /***
     * 上级节点.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ViewFwOrg parent;
    
    @Column(name = "ORG_CODE")
    private String orgCode;
    @Column(name = "ORG_TYPE")
    private String orgType;
    
    @Column(name = "ORG_STATUS")
    private Integer orgStatus;
    
    /**
     * 生成时间.
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /** */
    /**
     * 更新时间.
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ViewFwOrg getParent() {
        return parent;
    }

    public void setParent(ViewFwOrg parent) {
        this.parent = parent;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}
}