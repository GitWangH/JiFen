package com.huatek.frame.dao.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;
/***
 *数据权限管理
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
@Table(name="FW_DATA_ROLE_AUTHORITY")
public class FwDataRoleAuthority extends BaseEntity {
	
	private static final long serialVersionUID = 5166741522807732079L;

	@Id
	@Column(name="DA_ID", nullable=false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 数据角色
	 */
	@ManyToOne
	@JoinColumn(name = "DR_ID")
	private FwDataRole fwDataRole;
	
	/**
	 * 菜单ID
	 */
	@ManyToOne
	@JoinColumn(name = "SOURCE_ID")
	private FwSource fwSource;
	
	
	/**
	 * 实体ID
	 */
	@ManyToOne
	@JoinColumn(name = "ENTITY_ID")
	private FwSourceEntity fwSourceEntity;
	
	/**
	 * 实例ID，如果是字典是编码
	 */
	@Size(max=20)
	@Column(name = "DATA_ID", nullable = false)
	private String dataId;
	
	/**
	 * 字段值
	 */
	@Size(max=20)
	@Column(name = "FIELD_VALUE", nullable = false)
	private String fieldValue;
	
	/**
	 * 字段名称
	 */
	@Size(max=20)
	@Column(name = "FIELD_NAME", nullable = false)
	private String fieldName;
	
	public FwDataRole getFwDataRole() {
		return fwDataRole;
	}
	public void setFwDataRole(FwDataRole fwDataRole) {
		this.fwDataRole = fwDataRole;
	}
	public FwSource getFwSource() {
		return fwSource;
	}
	public void setFwSource(FwSource fwSource) {
		this.fwSource = fwSource;
	}
	public FwSourceEntity getFwSourceEntity() {
		return fwSourceEntity;
	}
	public void setFwSourceEntity(FwSourceEntity fwSourceEntity) {
		this.fwSourceEntity = fwSourceEntity;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
}