package com.huatek.busi.dto.common;

/**
 * 公用模糊检索DTO
 * @author eli_cui
 *
 */
public class CommonFuzzyRetrievalDto {
	private String value;
	private Long id;
	
	public CommonFuzzyRetrievalDto(){}
	
	public CommonFuzzyRetrievalDto(String value, Long id) {
		this.value = value;
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
