package com.huatek.frame.dto;


public class FwFavoriteDto  {

 	private Long id;
 
    
	/** @Fields acctId : 用户id */
    private Long acctId;
    
    
	/** @Fields sourceId : 菜单id */
   private Long sourceId;
	private String sourceName;
	
	private String controller;

	private String cssClass;
	private String icon;
	private int isMenu;
	
	private String url;
	
	private String view;

	private int isShow;
	
	private String title;
	
	private String label;
    
    
	/** @Fields orderNo : 排序 */
    private Integer orderNo;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setAcctId(Long acctId){
        this.acctId = acctId;
    }
      
    public Long getAcctId(){
        return this.acctId;
    }

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
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

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public String getTitle() {
		return sourceName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return sourceName;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
