package com.huatek.frame.dto;



public class FwSourceDto  {

	private Long id;

	private String params;
	private Integer width;
	private Integer height;
	
	private String sourceName;
	
	private String controller;

	private String cssClass;
	private String icon;
	private int isMenu;

//	private String sourceTemplate;
	
//	private String sourceUrl;

	private FwSourceDto parent;
	
	private String url;
	
	private String view;

	private int isShow;
	private int displayOrder;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}
	public FwSourceDto getParent() {
		return parent;
	}
	public void setParent(FwSourceDto parent) {
		this.parent = parent;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	
}
