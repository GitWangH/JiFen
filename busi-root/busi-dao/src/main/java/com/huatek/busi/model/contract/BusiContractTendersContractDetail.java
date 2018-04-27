package com.huatek.busi.model.contract;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiTreeGridEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractTendersContractDetail
 * @Description: 标段合同清单(复合清单)
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:02:52
 * @version: 1.0
 */
@Entity
@Table(name = "busi_contract_tenders_contract_detail")
public class BusiContractTendersContractDetail extends BusiTreeGridEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "TENDERS_CONTRACT_DETAIL_ID", nullable = true)
	private Long id;

	/** @Fields contractDetailName : 合同清单名称 */
	@Column(name = "CONTRACT_DETAIL_NAME", nullable = false, length = 50)
	private String contractDetailName;

	/** @Fields contractDetailCode : 合同清单编号 */
	@Column(name = "CONTRACT_DETAIL_CODE", nullable = false, length = 100)
	private String contractDetailCode;

	/** @Fields countUnit : 合同清单管理-合同清单-单位 */
	@Column(name = "COUNT_UNIT", nullable = false, length = 10)
	private String countUnit;

	/** @Fields unitPrice : 合同单价（元） */
	@Column(name = "UNIT_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal unitPrice;

	/** @Fields quantity : 合同数量 */
	@Column(name = "QUANTITY", nullable = false, precision = 18, scale = 2)
	private BigDecimal quantity;

	/** @Fields totalPrice : 合同明细总价(元) */
	@Column(name = "TOTAL_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal totalPrice;

	/** @Fields reviewUnitPrice : 合同明细复核单价（元） */
	@Column(name = "REVIEW_UNIT_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal reviewUnitPrice;

	/** @Fields reviewQuantity : 合同明细复核数量 */
	@Column(name = "REVIEW_QUANTITY", nullable = false, precision = 18, scale = 2)
	private BigDecimal reviewQuantity;

	/** @Fields reviewTotalPrice : 合同明细复核总价(元) */
	@Column(name = "REVIEW_TOTAL_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal reviewTotalPrice;

	/** @Fields detaileType : 明细类型(原清单-0、新增变更清单未审核-1,新增变更清单已审核-2， 数据字典) */
	@Column(name = "DETAILE_TYPE", nullable = false, length = 30)
	private String detaileType;

	/** @Fields workabilityQuantity : 可使用量 */
	@Column(name = "WORKABILITY_QUANTITY", nullable = false, precision = 18, scale = 2)
	private BigDecimal workabilityQuantity;

	/** @Fields usedQuantity : 已使用量 */
	@Column(name = "USED_QUANTITY", nullable = false, precision = 18, scale = 2)
	private BigDecimal usedQuantity;

	/** @Fields busiContractTendersContract : 标段合同ID */
	/*@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "TENDERS_CONTRACT_ID")
	private BusiContractTendersContract busiContractTendersContract;*/

	/** @Fields orgId : 流程审批表 */
	/*@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "APPROVE_FLOW_INFO_ID")
	private BusiContractTendersContractDetailApproveFlowInfo contractDetailApproveFlowInfo;// 标段合同清单(复合清单)审批信息表
*/
	/** @Fields busicontractcontractchangedetailSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "busiContractTendersContractDetail")
	private Set<BusiContractContractChangeDetail> busicontractcontractchangedetailSet;

	/** @Fields busicontracttendersbranchdetailSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "busiContractTendersContractDetail")
	private Set<BusiContractTendersBranchDetail> busicontracttendersbranchdetailSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractDetailName() {
		return contractDetailName;
	}

	public void setContractDetailName(String contractDetailName) {
		this.contractDetailName = contractDetailName;
	}

	public String getContractDetailCode() {
		return contractDetailCode;
	}

	public void setContractDetailCode(String contractDetailCode) {
		this.contractDetailCode = contractDetailCode;
	}

	public String getCountUnit() {
		return countUnit;
	}

	public void setCountUnit(String countUnit) {
		this.countUnit = countUnit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getReviewUnitPrice() {
		return reviewUnitPrice;
	}

	public void setReviewUnitPrice(BigDecimal reviewUnitPrice) {
		this.reviewUnitPrice = reviewUnitPrice;
	}

	public BigDecimal getReviewQuantity() {
		return reviewQuantity;
	}

	public void setReviewQuantity(BigDecimal reviewQuantity) {
		this.reviewQuantity = reviewQuantity;
	}

	public BigDecimal getReviewTotalPrice() {
		return reviewTotalPrice;
	}

	public void setReviewTotalPrice(BigDecimal reviewTotalPrice) {
		this.reviewTotalPrice = reviewTotalPrice;
	}

	public String getDetaileType() {
		return detaileType;
	}

	public void setDetaileType(String detaileType) {
		this.detaileType = detaileType;
	}

	public BigDecimal getWorkabilityQuantity() {
		return workabilityQuantity;
	}

	public void setWorkabilityQuantity(BigDecimal workabilityQuantity) {
		this.workabilityQuantity = workabilityQuantity;
	}

	public BigDecimal getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(BigDecimal usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	/*public BusiContractTendersContract getBusiContractTendersContract() {
		return busiContractTendersContract;
	}

	public void setBusiContractTendersContract(
			BusiContractTendersContract busiContractTendersContract) {
		this.busiContractTendersContract = busiContractTendersContract;
	}*/

	/*public BusiContractTendersContractDetailApproveFlowInfo getContractDetailApproveFlowInfo() {
		return contractDetailApproveFlowInfo;
	}

	public void setContractDetailApproveFlowInfo(
			BusiContractTendersContractDetailApproveFlowInfo contractDetailApproveFlowInfo) {
		this.contractDetailApproveFlowInfo = contractDetailApproveFlowInfo;
	}*/

	public Set<BusiContractContractChangeDetail> getBusicontractcontractchangedetailSet() {
		return busicontractcontractchangedetailSet;
	}

	public void setBusicontractcontractchangedetailSet(
			Set<BusiContractContractChangeDetail> busicontractcontractchangedetailSet) {
		this.busicontractcontractchangedetailSet = busicontractcontractchangedetailSet;
	}

	public Set<BusiContractTendersBranchDetail> getBusicontracttendersbranchdetailSet() {
		return busicontracttendersbranchdetailSet;
	}

	public void setBusicontracttendersbranchdetailSet(
			Set<BusiContractTendersBranchDetail> busicontracttendersbranchdetailSet) {
		this.busicontracttendersbranchdetailSet = busicontracttendersbranchdetailSet;
	}

}
