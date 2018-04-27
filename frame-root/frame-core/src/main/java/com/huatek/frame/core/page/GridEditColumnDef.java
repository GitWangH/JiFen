package com.huatek.frame.core.page;

/**
 * AngularJs ui-grid可编辑表格列定义对象
 * @author Maybe
 *
 */
public class GridEditColumnDef {
	
	/**
	 * 字段名称
	 */
	private String name;
	
	/**
	 * 显示名称
	 */
	private String displayName;
	
	/**
	 * 列宽
	 */
	private String width;
	
	/**
	 * 编辑作用域
	 */
	private String editModelField;
	
	/**
	 * 样式模板
	 */
	private String editableCellTemplate;
	
	/**
	 * 数据源获取的url
	 */
	private String dataUrl;
	
	/**
	 * 是否允许编辑
	 */
	private boolean enableCellEdit = true;
	
	/**
	 * 固定在左侧
	 */
	private boolean pinnedLeft = false;
	
	/**
	 * 显示列操作菜单
	 */
	private boolean enableColumnMenu = true;
	
	/**
	 * 数据类型  string、number、date、object、boolean
	 */
	private String type = "string";
	
	/**
	 * 单元格样式class
	 */
	private String cellClass;
	
	/**
	 * 表头单元格样式
	 */
	private String headerCellClass;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the editModelField
	 */
	public String getEditModelField() {
		return editModelField;
	}

	/**
	 * @param editModelField the editModelField to set
	 */
	public void setEditModelField(String editModelField) {
		this.editModelField = editModelField;
	}

	/**
	 * @return the editableCellTemplate
	 */
	public String getEditableCellTemplate() {
		return editableCellTemplate;
	}

	/**
	 * @param editableCellTemplate the editableCellTemplate to set
	 */
	public void setEditableCellTemplate(String editableCellTemplate) {
		this.editableCellTemplate = editableCellTemplate;
	}

	/**
	 * @return the dataUrl
	 */
	public String getDataUrl() {
		return dataUrl;
	}

	/**
	 * @param dataUrl the dataUrl to set
	 */
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	/**
	 * @return the enableCellEdit
	 */
	public boolean isEnableCellEdit() {
		return enableCellEdit;
	}

	/**
	 * @param enableCellEdit the enableCellEdit to set
	 */
	public void setEnableCellEdit(boolean enableCellEdit) {
		this.enableCellEdit = enableCellEdit;
	}

	/**
	 * @return the pinnedLeft
	 */
	public boolean isPinnedLeft() {
		return pinnedLeft;
	}

	/**
	 * @param pinnedLeft the pinnedLeft to set
	 */
	public void setPinnedLeft(boolean pinnedLeft) {
		this.pinnedLeft = pinnedLeft;
	}

	/**
	 * @return the enableColumnMenu
	 */
	public boolean isEnableColumnMenu() {
		return enableColumnMenu;
	}

	/**
	 * @param enableColumnMenu the enableColumnMenu to set
	 */
	public void setEnableColumnMenu(boolean enableColumnMenu) {
		this.enableColumnMenu = enableColumnMenu;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the cellClass
	 */
	public String getCellClass() {
		return cellClass;
	}

	/**
	 * @param cellClass the cellClass to set
	 */
	public void setCellClass(String cellClass) {
		this.cellClass = cellClass;
	}

	/**
	 * @return the headerCellClass
	 */
	public String getHeaderCellClass() {
		return headerCellClass;
	}

	/**
	 * @param headerCellClass the headerCellClass to set
	 */
	public void setHeaderCellClass(String headerCellClass) {
		this.headerCellClass = headerCellClass;
	}

}
