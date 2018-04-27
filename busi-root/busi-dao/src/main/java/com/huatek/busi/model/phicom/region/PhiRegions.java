package com.huatek.busi.model.phicom.region;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiRegions
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-13 11:06:38
  * @version: 1.0
  */

@Entity
@Table(name = "phi_regions")
public class PhiRegions extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "id", nullable = true )
 	private Long id;
 
	@Column(name = "code",nullable = true, length=50)
	private String code;
    
	@Column(name = "name",nullable = true, length=32)
	private String name;
	
//	@Column(name = "parentCode",nullable = true, length=50)
//	private String parentCode; 
	
	@Column(name = "level",nullable = true)
	private int level; 
	
	//排序
	@Column(name = "ordernume",nullable = true)
	private int ordernume; 
	
	@Column(name = "disable",nullable = true, length=25)
	private String disable;
	
	@Column(name = "parentCode",nullable = true, length=25)
	private String parentCode;

//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="parentCode")
//	private PhiRegions parentCode; 
//	
//	@OneToMany(mappedBy="parentCode", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	private Set<PhiRegions> children = new HashSet<PhiRegions>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getOrdernume() {
		return ordernume;
	}

	public void setOrdernume(int ordernume) {
		this.ordernume = ordernume;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

//	public Set<PhiRegions> getChildren() {
//		return children;
//	}
//
//	public void setChildren(Set<PhiRegions> children) {
//		this.children = children;
//	}

	
	

}
