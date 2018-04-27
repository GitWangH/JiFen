package com.huatek.frame.service.dto;

import java.io.Serializable;

public class OrgZtreeDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private Long id_;
	
	private String pId;
	
	private String name;
	
	private String shortName;
	
	private boolean open;
	
	private String icon;
	
	private String isDept;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getId_() {
		return id_;
	}

	public void setId_(Long id_) {
		this.id_ = id_;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getIsDept() {
		return isDept;
	}

	public void setIsDept(String isDept) {
		this.isDept = isDept;
	}
	
}
