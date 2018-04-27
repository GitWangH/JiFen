package com.huatek.frame.service.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典
 * @author TangWenKuo
 * @date 2016年1月7日 下午8:07:44
 */
public class FwPropertyDto implements Serializable{
	private static final long serialVersionUID = 7946276886344228101L;
	private Long id;
	private String propertyValue;
	private String propertyName;
	//private FwCategory fwCategory;
	private Long fwCategoryId;
	private Date createTime;
	private String createUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Long getFwCategoryId() {
		return fwCategoryId;
	}
	public void setFwCategoryId(Long fwCategoryId) {
		this.fwCategoryId = fwCategoryId;
	}
	
/*	public FwCategory getFwCategory() {
		return fwCategory;
	}
	public void setFwCategory(FwCategory fwCategory) {
		this.fwCategory = fwCategory;
	}*/
	
	/**
	 * 非持久化字段表明是否选中
	 */
	private boolean ifChecked;
	public boolean isIfChecked() {
		return ifChecked;
	}

	public void setIfChecked(boolean ifChecked) {
		this.ifChecked = ifChecked;
	}
}
