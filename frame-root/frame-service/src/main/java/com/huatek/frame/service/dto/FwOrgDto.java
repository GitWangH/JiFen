package com.huatek.frame.service.dto;

import java.util.Date;


public class FwOrgDto implements java.io.Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 组织ID.
     */
    private Long id;
    /**
     * 组织名称.
     */
    private String name;
    
    private String orgCode;
    
    /**
     * 机构简称
     */
    private String shortName;
    
    /**
     * 单位名称
     */
    private String companyName;
    
    /**
     * 机构状态
     */
    private Integer orgStatus;
    
    /**
     * 机构类型
     */
    private String orgType;
    
    
	/**
     * 组织级别.
     */
    private Integer orgLevel;
    /**
     * 上级组织ID.
     */
    private Long parentId;
    private String parentName;
    private String parentId_;
    /**
     * 是否一级组织（1-是，0-不是）.
     */
    private long level_1;
    /**
     * 是否二级组织（1-是，0-不是）.
     */
    private long level_2;
    /**
     * 是否三级组织（1-是，0-不是）.
     */
    private long level_3;
    /**
     * 是否四级组织（1-是，0-不是）.
     */
    private long level_4;
    /**
     * 是否五级组织（1-是，0-不是）.
     */
    private long level_5;
    /**
     * 是否六级组织（1-是，0-不是）.
     */
    private long level_6;
    /** */
    /**
     * 是否七级组织（1-是，0-不是）.
     */
    private long level_7;
    /** */
    /**
     * 是否八级组织（1-是，0-不是）.
     */
    private long level_8;
    /** */
    /**
     * 是否九级组织（1-是，0-不是）.
     */
    private long level_9;
    /** */
    /**
     * 是否十级组织（1-是，0-不是）.
     */
    private long level_10;
    /** */
    /**
     * 生成时间.
     */
    private Date createTime;
    /**
     * 更新时间.
     */
    private Date updateTime;
    
    /**
     * 租户ID
     */
    private Long tenantId;
    
    private String remark;
    
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
	public FwOrgDto() {

	}
	
	public FwOrgDto(Long id,String name,String orgCode,String orgType,Long parentId) {
		this.id = id;
		this.name = name;
		this.orgCode = orgCode;
		this.orgType = orgType;
		this.parentId = parentId;
	}
    

    public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
	public Integer getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public long getLevel_1() {
		return level_1;
	}
	public void setLevel_1(long level_1) {
		this.level_1 = level_1;
	}
	public long getLevel_2() {
		return level_2;
	}
	public void setLevel_2(long level_2) {
		this.level_2 = level_2;
	}
	public long getLevel_3() {
		return level_3;
	}
	public void setLevel_3(long level_3) {
		this.level_3 = level_3;
	}
	public long getLevel_4() {
		return level_4;
	}
	public void setLevel_4(long level_4) {
		this.level_4 = level_4;
	}
	public long getLevel_5() {
		return level_5;
	}
	public void setLevel_5(long level_5) {
		this.level_5 = level_5;
	}
	public long getLevel_6() {
		return level_6;
	}
	public void setLevel_6(long level_6) {
		this.level_6 = level_6;
	}
	public long getLevel_7() {
		return level_7;
	}
	public void setLevel_7(long level_7) {
		this.level_7 = level_7;
	}
	public long getLevel_8() {
		return level_8;
	}
	public void setLevel_8(long level_8) {
		this.level_8 = level_8;
	}
	public long getLevel_9() {
		return level_9;
	}
	public void setLevel_9(long level_9) {
		this.level_9 = level_9;
	}
	public long getLevel_10() {
		return level_10;
	}
	public void setLevel_10(long level_10) {
		this.level_10 = level_10;
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
	
	public String getParentId_() {
		return parentId_;
	}
	public void setParentId_(String parentId_) {
		this.parentId_ = parentId_;
	}
	
	public Integer getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FwOrgDto){
			FwOrgDto orgDto = (FwOrgDto) obj;
			return orgDto.getId().longValue() == this.id?true:false;
		}
		return super.equals(obj);
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
