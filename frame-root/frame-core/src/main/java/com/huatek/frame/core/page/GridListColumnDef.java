package com.huatek.frame.core.page;

public class GridListColumnDef {
	/**
	 * 列名称
	 */
	private String name;
	
	/**
	 * 字段名称
	 */
	private String field;
	
	/**
	 * 列宽
	 */
	private String width;
	
	/**
	 * 固定在左侧
	 */
	private boolean pinnedLeft = false;
	
	/**
	 * 样式class
	 */
	private String cellClass;

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
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
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
	
	
}
