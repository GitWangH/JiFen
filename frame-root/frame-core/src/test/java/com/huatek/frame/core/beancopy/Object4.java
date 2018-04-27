package com.huatek.frame.core.beancopy;

import com.huatek.frame.core.model.BaseEntity;

public class Object4 extends BaseEntity{
	private Long id;
	private String value7;
	private String value8;
	@Override
	public String toString() {
		return "Object4 [value7=" + value7 + ", value8=" + value8 + "]";
	}
	public String getValue7() {
		return value7;
	}
	public void setValue7(String value7) {
		this.value7 = value7;
	}
	public String getValue8() {
		return value8;
	}
	public void setValue8(String value8) {
		this.value8 = value8;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
		
	}
	
}
