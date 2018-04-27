package com.huatek.file.excel.imp;

import java.util.List;

public class ImportConfig {
	private Long id;

	private String busiType;
	
	private String busiName;

	private String excelPath;

	private String busiCode;
	private Integer sheet;
	private Integer startRow;

	private Integer startCol;

	private String persisType;

	private String persisImpl;
	private String validateService; 
	private String transformService; 
	public String getValidateService() {
		return validateService;
	}
	public void setValidateService(String validateService) {
		this.validateService = validateService;
	}
	public String getTransformService() {
		return transformService;
	}
	public void setTransformService(String transformService) {
		this.transformService = transformService;
	}
	private List<ImportFieldConfig>  fieldsList;
	public Long getId() {
		return id;
	}
	public Integer getSheet() {
		return sheet;
	}
	public void setSheet(Integer sheet) {
		this.sheet = sheet;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getBusiName() {
		return busiName;
	}
	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}
	public String getExcelPath() {
		return excelPath;
	}
	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getStartCol() {
		return startCol;
	}
	public void setStartCol(Integer startCol) {
		this.startCol = startCol;
	}
	public String getPersisType() {
		return persisType;
	}
	public void setPersisType(String persisType) {
		this.persisType = persisType;
	}
	public String getPersisImpl() {
		return persisImpl;
	}
	public void setPersisImpl(String persisImpl) {
		this.persisImpl = persisImpl;
	}
	public List<ImportFieldConfig> getFieldsList() {
		return fieldsList;
	}
	public void setFieldsList(List<ImportFieldConfig> fieldsList) {
		this.fieldsList = fieldsList;
	}
}
