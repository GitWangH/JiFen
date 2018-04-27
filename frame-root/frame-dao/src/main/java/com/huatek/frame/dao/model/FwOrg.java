package com.huatek.frame.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.TreeEntity;

/***
 * 部门组织的父类. 一用于系统的权限管理. 业务部门管理应该继承该实体进行管理.
 * 
 * @author winner pan.
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "FW_ORG")
public class FwOrg extends TreeEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1155807352468031510L;
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "org_id", nullable = false )
    private Long id;

    @Size(max = 60)
    @Column(name = "ORG_NAME", nullable = false)
    private String name;
    
    // 上级节点.
    @OneToOne
    @JoinColumn(name = "parent_id")
    private FwOrg parent;

    @Column(name = "ORG_CODE")
    private String orgCode;

    @Column(name = "ORG_TYPE")
    private String orgType;

    @Column(name = "ORG_STATUS")
    private String orgStatus;
    
    /**
     *  机构简称
     */
    @Column(name = "SHORT_NAME")
    private String shortName;
    
    /**
     * 单位名称
     */
    @Column(name = "COMPANY_NAME")
    private String companyName;
    
     // 生成时间.
    @Column(name = "CREATE_TIME")
    private Date createTime;
     // 更新时间.
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /** 租户id */
    @Column(name = "TENANT_ID")
    private Long tenantId;
    
    @Column(name = "REMARK")
    private String remark;
    

    
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

	public FwOrg getParent() {
		return parent;
	}

	public void setParent(FwOrg parent) {
		this.parent = parent;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
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

	public String getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FwOrg){
			FwOrg orgDto = (FwOrg) obj;
			return orgDto.getId().longValue() == this.id?true:false;
		}
		return super.equals(obj);
	}
	
}