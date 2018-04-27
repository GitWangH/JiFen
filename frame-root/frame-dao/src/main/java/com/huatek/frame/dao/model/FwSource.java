package com.huatek.frame.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.TreeEntity;


/***
 * 菜单记录实体.
 * 
 * @author winner pan
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "FW_SOURCE")
public class FwSource extends TreeEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -5175246456181078279L;
	@Id
	@Column(name = "source_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	
	/**
	 * 用于记录前端angularjs路由的路径,含参数.
	 */
	@Column(name = "source_params", nullable = true)
	private String params;
	@Column(name = "WIDTH", nullable = true)
	private Integer width;
	@Column(name = "HEIGHT", nullable = true)
	private Integer height;
	
	@Column(name = "source_name", nullable = false)
	private String sourceName;
	
	/***
	 * 菜单js控制器名称.
	 */
	@Column(name = "controller", nullable = true)
	private String controller;

	/***
	 * 菜单显示样式.
	 */
	@Column(name = "css_class", nullable = true)
	private String cssClass;
	@Column(name = "icon", nullable = true)
	private String icon;
	@Column(name = "is_menu", nullable = false)
	private int isMenu;

	/***
	 * 菜单页面模版名称.
	 */
	@Column(name = "source_template", nullable = true)
	private String sourceTemplate;
	
	/***
	 * 用于记录api资源路径,在数据库中唯一.
	 */
	@Column(name = "source_url", nullable = false)
	private String sourceUrl;

	/***
	 * 上级节点.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private FwSource parent;


	/***
	 * 是否显示在导航菜单上.
	 * 1：显示，其他不显示.
	 */
	@Column(name = "is_show", nullable = false)
	private int isShow;

	
	@Column(name = "display_order")
	private int displayOrder;
	
	

	
	public String getSourceTemplate() {
		return sourceTemplate;
	}

	public void setSourceTemplate(String sourceTemplate) {
		this.sourceTemplate = sourceTemplate;
	}

	public FwSource getParent() {
		return parent;
	}

	public void setParent(FwSource parent) {
		this.parent = parent;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	/** default constructor */
	public FwSource() {
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	


	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
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
