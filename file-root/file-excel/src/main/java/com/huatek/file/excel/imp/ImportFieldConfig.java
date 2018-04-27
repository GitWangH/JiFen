package com.huatek.file.excel.imp;

import java.math.BigDecimal;

public class ImportFieldConfig {
	private Long id;

	private String name;

	private String code;
	private Integer col;
	private String type;

	private String patten;

	private Integer nullable;

	private Integer length;

	private BigDecimal max;

	private BigDecimal min;

	private String contentValidateType;

	private String contentValidateImp;

	private String contentTransformType;

	private String contentTransformImp;


	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNullable() {
		return nullable;
	}

	public void setNullable(Integer nullable) {
		this.nullable = nullable;
	}

	public String getPatten() {
		return patten;
	}

	public void setPatten(String patten) {
		this.patten = patten;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public String getContentValidateType() {
		return contentValidateType;
	}

	public void setContentValidateType(String contentValidateType) {
		this.contentValidateType = contentValidateType;
	}

	public String getContentValidateImp() {
		return contentValidateImp;
	}

	public void setContentValidateImp(String contentValidateImp) {
		this.contentValidateImp = contentValidateImp;
	}

	public String getContentTransformType() {
		return contentTransformType;
	}

	public void setContentTransformType(String contentTransformType) {
		this.contentTransformType = contentTransformType;
	}

	public String getContentTransformImp() {
		return contentTransformImp;
	}

	public void setContentTransformImp(String contentTransformImp) {
		this.contentTransformImp = contentTransformImp;
	}

}
