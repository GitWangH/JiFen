package com.huatek.busi.model.contract;

import java.math.BigDecimal;
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
 * @ClassName: BusiContractSupervisorContract
 * @Description: 监理合同
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:08:25
 * @version: 1.0
 */
@Entity
@Table(name = "busi_contract_supervisor_contract")
public class BusiContractSupervisorContract extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "SUPERVISOR_CONTRACT_ID", nullable = true)
	private Long id;

	/** @Fields contractName : 合同名称 */
	@Column(name = "CONTRACT_NAME", nullable = false, length = 100)
	private String contractName;

	/** @Fields contractCode : 合同编号 */
	@Column(name = "CONTRACT_CODE", nullable = false, length = 100)
	private String contractCode;

	/** @Fields signatureData : 签订日期 */
	@Column(name = "SIGNATURE_DATA", nullable = false)
	private Date signatureData;

	/** @Fields contractTotalPrice : 合同金额（元） */
	@Column(name = "CONTRACT_TOTAL_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal contractTotalPrice;

	/** @Fields provisionalMoney : 暂定金（元） */
	@Column(name = "PROVISIONAL_MONEY", nullable = false, precision = 18, scale = 4)
	private BigDecimal provisionalMoney;

	/** @Fields contractDescription : 描述 */
	@Column(name = "CONTRACT_DESCRIPTION", nullable = false, length = 255)
	private String contractDescription;

	/** @Fields contractFile : 合同附件 */
	@Column(name = "CONTRACT_FILE", nullable = false, length = 32)
	private String contractFile;

	/** @Fields applyTime : 申请时间 */
	@Column(name = "APPLY_TIME", nullable = false)
	private Date applyTime;

	/** @Fields applyUserId : 申请人ID */
	@Column(name = "APPLY_USER_ID", nullable = false)
	private Long applyUserId;

	/** @Fields applyUserName : 申请人名称 */
	@Column(name = "APPLY_USER_NAME", nullable = false, length = 100)
	private String applyUserName;

	/** @Fields approvalStatus : 审批状态 */
	@Column(name = "APPROVAL_STATUS", nullable = false, length = 30)
	private String approvalStatus;

	/** @Fields approvalTime : 审批完成时间 */
	@Column(name = "APPROVAL_TIME", nullable = false)
	private Date approvalTime;

	/** @Fields approvalUserId : 审批人ID */
	@Column(name = "APPROVAL_USER_ID", nullable = false)
	private Long approvalUserId;

	/** @Fields approvalUserName : 审批人名称 */
	@Column(name = "APPROVAL_USER_NAME", nullable = false, length = 100)
	private String approvalUserName;

	/** @Fields flowInstanceId : 流程实例ID */
	@Column(name = "FLOW_INSTANCE_ID", nullable = false)
	private Long flowInstanceId;

	/** @Fields flowResult : 审批结果 */
	@Column(name = "FLOW_RESULT", nullable = false, length = 30)
	private String flowResult;

	/** @Fields flowMessage : 审批意见 */
	@Column(name = "FLOW_MESSAGE", nullable = false, length = 100)
	private String flowMessage;

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

	/** @Fields orgId : 组织机构ID */
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg busiFwOrg;// 关联Org对象获取机构信息

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;

	/** @Fields busicontractsupervisorcontractdetailSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "busiContractSupervisorContract")
	private Set<BusiContractSupervisorContractDetail> busicontractsupervisorcontractdetailSet;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractCode() {
		return this.contractCode;
	}

	public void setSignatureData(Date signatureData) {
		this.signatureData = signatureData;
	}

	public Date getSignatureData() {
		return this.signatureData;
	}

	public void setContractTotalPrice(BigDecimal contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}

	public BigDecimal getContractTotalPrice() {
		return this.contractTotalPrice;
	}

	public void setProvisionalMoney(BigDecimal provisionalMoney) {
		this.provisionalMoney = provisionalMoney;
	}

	public BigDecimal getProvisionalMoney() {
		return this.provisionalMoney;
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

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Long getApplyUserId() {
		return this.applyUserId;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyUserName() {
		return this.applyUserName;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Date getApprovalTime() {
		return this.approvalTime;
	}

	public void setApprovalUserId(Long approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public Long getApprovalUserId() {
		return this.approvalUserId;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public String getApprovalUserName() {
		return this.approvalUserName;
	}

	public void setFlowInstanceId(Long flowInstanceId) {
		this.flowInstanceId = flowInstanceId;
	}

	public Long getFlowInstanceId() {
		return this.flowInstanceId;
	}

	public void setFlowResult(String flowResult) {
		this.flowResult = flowResult;
	}

	public String getFlowResult() {
		return this.flowResult;
	}

	public void setFlowMessage(String flowMessage) {
		this.flowMessage = flowMessage;
	}

	public String getFlowMessage() {
		return this.flowMessage;
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

	public BusiFwOrg getBusiFwOrg() {
		return busiFwOrg;
	}

	public void setBusiFwOrg(BusiFwOrg busiFwOrg) {
		this.busiFwOrg = busiFwOrg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setBusicontractsupervisorcontractdetailSet(
			Set<BusiContractSupervisorContractDetail> busicontractsupervisorcontractdetailSet) {
		this.busicontractsupervisorcontractdetailSet = busicontractsupervisorcontractdetailSet;
	}

	public Set<BusiContractSupervisorContractDetail> getBusicontractsupervisorcontractdetailSet() {
		return this.busicontractsupervisorcontractdetailSet;
	}

}
