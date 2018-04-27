package oracle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.TreeEntity;


/***
 * 菜单记录实体.
 * 
 * @author winner pan
 *
 */
@Entity
@Table(name = "FW_SOURCE")
public class FwSource extends TreeEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -5175246456181078279L;
	@Id
	@Column(name = "source_id", nullable = false)
	@SequenceGenerator(name = "my_seq", sequenceName = "S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Size(min = 1, max = 50)
	@Column(name = "source_name", nullable = false)
	private String sourceName;

	@Size(min = 1, max = 50)
	@Column(name = "source_url", nullable = false)
	private String sourceUrl;

	@Column(name = "is_menu", nullable = false)
	private int isMenu;

	@Size(max = 200)
	@Column(name = "source_path", nullable = true)
	private String sourcePath;

	@Column(name = "display_order")
	private int displayOrder;
	
	@Column(name = "req_url", nullable = true)
	private String reqUrl;

	/***
	 * 菜单显示样式.
	 */
	@Size(max = 100)
	@Column(name = "css_class", nullable = true)
	private String cssClass;
	/***
	 * 菜单js控制器名称.
	 */
	@Size(max = 50)
	@Column(name = "controller", nullable = true)
	private String controller;

	/***
	 * 菜单页面模版名称.
	 */
	@Size(max = 50)
	@Column(name = "source_template", nullable = true)
	private String sourceTemplate;

	public String getSourceTemplate() {
		return sourceTemplate;
	}

	public void setSourceTemplate(String sourceTemplate) {
		this.sourceTemplate = sourceTemplate;
	}

	/***
	 * 上级节点.
	 */
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private FwSource parent;

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

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
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
	/***
	 * 是否显示在导航菜单上.
	 * 1：显示，其他不显示.
	 */
	@Column(name = "is_show", nullable = false)
	private int isShow;

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

	/**
	 * @return the reqUrl
	 */
	public String getReqUrl() {
		return reqUrl;
	}

	/**
	 * @param reqUrl the reqUrl to set
	 */
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	

}
