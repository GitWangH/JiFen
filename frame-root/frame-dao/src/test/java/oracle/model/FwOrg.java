package oracle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.TreeEntity;
/***
 * 部门组织的父类.
 * 一用于系统的权限管理.
 * 业务部门管理应该继承该实体进行管理.
 * @author winner pan.
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
@Table(name="FW_ORG")
public class FwOrg extends TreeEntity implements java.io.Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1155807352468031510L;
	@Id
	@Column(name="org_id", nullable=false)	
	@SequenceGenerator(name="my_seq", sequenceName="S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Size(min=5, max=50)
	@Column(name = "ORG_NAME", nullable = false)
	private String name;
	
	/***
	 * 上级节点.
	 */
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private FwOrg parent;
	public FwOrg getParent() {
		return parent;
	}
	public void setParent(FwOrg parent) {
		this.parent = parent;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FwOrg(){

	}
	
	/**
     * 生成时间.
     */
	@Column(name = "CREATE_TIME")
    private Date createTime;
    /** */
    /**
     * 更新时间.
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;


    /**  
     * 取得 生成时间.
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 生成时间.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**  
     * 取得 更新时间.
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置 更新时间.
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
     *  机构简称
     */
    @Column(name = "SHORT_NAME")
    private String shortName;
    /**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
    
    /**
     * 所属营销中心
     */
    @Column(name = "COMPANY_NAME")
    private String companyName;
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name = "ORG_CODE")
	String orgCode;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	
}