package com.huatek.frame.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/***
 * 记录给角色分配的资源实体.
 * @author winner pan.
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="FW_ROLE_SOURCE")
public class FwRoleSource extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -4612188363497692194L;
	@Id
	@Column(name="role_action_id", nullable=false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "source_id")
	private FwSource fwSource;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "role_id")
	private FwRole fwRole;
	@Column(name = "tenant_id")
	private Long tenantId;
	public FwRoleSource() {
	}

	
	public FwSource getFwSource() {
		return fwSource;
	}
	public void setFwSource(FwSource fwSource) {
		this.fwSource = fwSource;
	}
	public FwRole getFwRole() {
		return fwRole;
	}
	public void setFwRole(FwRole fwRole) {
		this.fwRole = fwRole;
	}
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	


}
