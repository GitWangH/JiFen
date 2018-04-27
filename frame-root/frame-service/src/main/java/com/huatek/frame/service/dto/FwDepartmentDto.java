package com.huatek.frame.service.dto;

import java.util.Date;

/**
 * 代码自动生成dto类.
 * 
 * @ClassName: FwDepartment
 * @Description:
 * @author: martin_ju
 * @Email : martin_ju@huatek.com
 * @date: 2016-06-23 14:16:21
 * @version: 1.0
 */
public class FwDepartmentDto implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String deptName;
    private String deptCode;
    /**
     * 上级部门
     */
    private Long parentId;
    private String parentName;
    /**
     * 组织机构
     */
    private Long orgId;
    private String orgName;
    private String remark;

    /**
     * 部门等级
     */
    private Integer groupLevel;
    /**
     * 是否一级部门（1-是，0-不是）
     */
    private Integer level1;
    /**
     * 是否二级部门（1-是，0-不是）
     */
    private Integer level2;
    /**
     * 是否三级部门（1-是，0-不是）
     */
    private Integer level3;
    /**
     * 是否四级部门（1-是，0-不是）
     */
    private Integer level4;
    /**
     * 是否五级部门（1-是，0-不是）
     */
    private Integer level5;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 租户ID
     */
    private Long tenantId;
    
    /**
     * 返回类型
     */
    private String type;
    
    /**
     * 返回信息
     */
    private String text;

    /**
     * 默认构造器
     */
    public FwDepartmentDto() {
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

    public Long getParentId() {
	return parentId;
    }

    public void setParentId(Long parentId) {
	this.parentId = parentId;
    }

    public String getParentName() {
	return parentName;
    }

    public void setParentName(String parentName) {
	this.parentName = parentName;
    }

    public Integer getGroupLevel() {
	return groupLevel;
    }

    public void setGroupLevel(Integer groupLevel) {
	this.groupLevel = groupLevel;
    }

    public Integer getLevel1() {
	return level1;
    }

    public void setLevel1(Integer level1) {
	this.level1 = level1;
    }

    public Integer getLevel2() {
	return level2;
    }

    public void setLevel2(Integer level2) {
	this.level2 = level2;
    }

    public Integer getLevel3() {
	return level3;
    }

    public void setLevel3(Integer level3) {
	this.level3 = level3;
    }

    public Integer getLevel4() {
	return level4;
    }

    public void setLevel4(Integer level4) {
	this.level4 = level4;
    }

    public Integer getLevel5() {
	return level5;
    }

    public void setLevel5(Integer level5) {
	this.level5 = level5;
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

    public String getCreator() {
	return creator;
    }

    public void setCreator(String creator) {
	this.creator = creator;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
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