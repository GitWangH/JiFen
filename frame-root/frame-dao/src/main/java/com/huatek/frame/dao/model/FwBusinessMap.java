package com.huatek.frame.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;
/***
 *数据权限业务模块管理
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
@Table(name="FW_BUSINESS_MAP")
public class FwBusinessMap extends BaseEntity {
	
	private static final long serialVersionUID = 1671410093926527437L;

	@Id
	@Column(name="BUSINESS_MAP_ID", nullable=false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Size(min=5, max=50)
	@Column(name = "BUSINESS_MAP_NAME", nullable = false)
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_ENTITY_ID")
	private FwSourceEntity fwSourceEntity;
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_ID")
	private FwSource fwSource;
	public FwSourceEntity getFwSourceEntity() {
		return fwSourceEntity;
	}
	public void setFwSourceEntity(FwSourceEntity fwSourceEntity) {
		this.fwSourceEntity = fwSourceEntity;
	}
	
	public FwSource getFwSource() {
		return fwSource;
	}
	public void setFwSource(FwSource fwSource) {
		this.fwSource = fwSource;
	}

	
}