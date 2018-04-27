package com.huatek.frame.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 * 代码自动生成model类.
 * 
 * @ClassName: FwDepartment
 * @Description:
 * @author: Arno
 * @Email : Arno_Fu@huatek.com
 * @date: 2016-06-23 14:16:21
 * @version: 1.0
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "fw_department")
public class FwDepartment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** @Fields id : */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    private Long id;

    /** @Fields deptName : */
    @Column(name = "DEPT_NAME", nullable = false)
    private String deptName;

    /** @Fields deptCode : */
    @Column(name = "DEPT_CODE", nullable = false)
    private String deptCode;
    /** @Fields deptCode : */
    @ManyToOne
	@JoinColumn(name = "org_id")
	private FwOrg fwOrg;
    /** @Fields parentId : */
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private FwDepartment parent;

    /** @Fields level1 : */
    @Column(name = "LEVEL1", nullable = false)
    private Long level1;

    /** @Fields level2 : */
    @Column(name = "LEVEL2", nullable = false)
    private Long level2;

    /** @Fields level3 : */
    @Column(name = "LEVEL3", nullable = false)
    private Long level3;

    /** @Fields level4 : */
    @Column(name = "LEVEL4", nullable = false)
    private Long level4;

    /** @Fields level5 : */
    @Column(name = "LEVEL5", nullable = false)
    private Long level5;

    /** @Fields groupLevel : */
    @Column(name = "GROUP_LEVEL", nullable = false)
    private Integer groupLevel;

    /** @Fields createId : */
    @Column(name = "CREATE_ID", nullable = false)
    private Long createId;

    /** @Fields createTime : */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /** @Fields createTime : */
    @Column(name = "REMARK", nullable = false)
    private String remark;

    /** @Fields tenantId : 租户架构*/
    @Column(name = "TENANT_ID", nullable = false)
    private Long tenantId;

    public void setId(Long id) {
	this.id = id;
    }

    public Long getId() {
	return this.id;
    }

    public void setDeptName(String deptName) {
	this.deptName = deptName;
    }

    public String getDeptName() {
	return this.deptName;
    }

    public void setDeptCode(String deptCode) {
	this.deptCode = deptCode;
    }

    public String getDeptCode() {
	return this.deptCode;
    }

    public FwDepartment getParent() {
	return parent;
    }

    public void setParent(FwDepartment parent) {
	this.parent = parent;
    }

    public Long getLevel1() {
	return level1;
    }

    public void setLevel1(Long level1) {
	this.level1 = level1;
    }

    public Long getLevel2() {
	return level2;
    }

    public void setLevel2(Long level2) {
	this.level2 = level2;
    }

    public Long getLevel3() {
	return level3;
    }

    public void setLevel3(Long level3) {
	this.level3 = level3;
    }

    public Long getLevel4() {
	return level4;
    }

    public void setLevel4(Long level4) {
	this.level4 = level4;
    }

    public Long getLevel5() {
	return level5;
    }

    public void setLevel5(Long level5) {
	this.level5 = level5;
    }

    public void setGroupLevel(Integer groupLevel) {
	this.groupLevel = groupLevel;
    }

    public Integer getGroupLevel() {
	return this.groupLevel;
    }

    public void setCreateId(Long createId) {
	this.createId = createId;
    }

    public Long getCreateId() {
	return this.createId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public Date getCreateTime() {
	return this.createTime;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

	public FwOrg getFwOrg() {
		return fwOrg;
	}

	public void setFwOrg(FwOrg fwOrg) {
		this.fwOrg = fwOrg;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	
}
