package com.huatek.busi.model.contract;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractOtherContract
 * @Description: 其它合同管理
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-27 11:07:01
 * @version: 1.0
 */
@Entity
@Table(name = "busi_contract_other_contract")
public class BusiContractOtherContract extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** @Fields otherContractId : 其它合同ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "OTHER_CONTRACT_ID", nullable = false)
	private Long id;

	/** @Fields orgId : 组织机构ID */
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg busiFwOrg;// 关联Org对象获取机构信息

	/** @Fields contractCnName : 合同名称 */
	@Column(name = "CONTRACT_NAME", nullable = false, length = 100)
	private String contractName;

	/** @Fields signatureData : 签订日期 */
	@Column(name = "SIGNATURE_DATA", nullable = false)
	private Date signatureData;

	/** @Fields contractDescription : 描述 */
	@Column(name = "CONTRACT_DESCRIPTION", nullable = false, length = 255)
	private String contractDescription;

	/** @Fields contractFile : 合同附件 */
	@Column(name = "CONTRACT_FILE", nullable = false, length = 32)
	private String contractFile;

	/** @Fields creater : 创建人id */
	@Column(name = "CREATER", nullable = false)
	private Long creater;

	/** @Fields createrName : 创建人姓名 */
	@Column(name = "CREATER_NAME", nullable = false, length = 100)
	private String createrName;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields modifer : 修改人id */
	@Column(name = "MODIFER", nullable = false)
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	@Column(name = "MODIFIER_NAME", nullable = false, length = 100)
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;

	/** @Fields tenantId : 租户id */
	@Column(name = "TENANT_ID", nullable = false)
	private Long tenantId;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields busicontractothercontractdetailSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "busiContractOtherContract")
	private Set<BusiContractOtherContractDetail> busicontractothercontractdetailSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BusiFwOrg getBusiFwOrg() {
		return busiFwOrg;
	}

	public void setBusiFwOrg(BusiFwOrg busiFwOrg) {
		this.busiFwOrg = busiFwOrg;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setSignatureData(Date signatureData) {
		this.signatureData = signatureData;
	}

	public Date getSignatureData() {
		return this.signatureData;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public String getContractDescription() {
		return this.contractDescription;
	}

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}

	public String getContractFile() {
		return this.contractFile;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Long getCreater() {
		return this.creater;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreaterName() {
		return this.createrName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public Long getModifer() {
		return this.modifer;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifierName() {
		return this.modifierName;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTenantId() {
		return this.tenantId;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setBusicontractothercontractdetailSet(
			Set<BusiContractOtherContractDetail> busicontractothercontractdetailSet) {
		this.busicontractothercontractdetailSet = busicontractothercontractdetailSet;
	}

	public Set<BusiContractOtherContractDetail> getBusicontractothercontractdetailSet() {
		return this.busicontractothercontractdetailSet;
	}

}
