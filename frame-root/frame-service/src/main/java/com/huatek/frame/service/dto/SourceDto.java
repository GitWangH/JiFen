package com.huatek.frame.service.dto;


/**   
 * @Description: 资源(菜单menu)dto 
 * @author caojun1@hisense.com  
 * @date 2016年1月13日 下午1:18:40 
 * @version V1.0   
 */
public class SourceDto implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String label;
	private String cssClass;
	private String icon;
	private String url;
	private int isMenu;
	private String isMenuStr;
	private Integer level;
	private String id;
	private Long parentId;
	private String parentName;
	private String view;
	private String controller;
	private String params;
	private int isShow;
	private String isShowStr;
	private int displayOrder;
	private Integer width;
	private Integer height;
	
	public String getIsShowStr() {
		return isShowStr;
	}

	public void setIsShowStr(String isShowStr) {
		this.isShowStr = isShowStr;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public SourceDto() {

	}


	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIsMenuStr() {
		return isMenuStr;
	}

	public void setIsMenuStr(String isMenuStr) {
		this.isMenuStr = isMenuStr;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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


}
