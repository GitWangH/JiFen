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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiTreeGridEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractTendersBranch
 * @Description: 标段分部分项
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:11:29
 * @version: 1.0
 */
@Entity
@Table(name = "busi_contract_tenders_branch")
public class BusiContractTendersBranch extends BusiTreeGridEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "TENDERS_BRANCH_ID", nullable = true)
	private Long id;

	/** @Fields busiContractTendersContract : 合同主键ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "TENDERS_CONTRACT_ID")
	private BusiContractTendersContract busiContractTendersContract;

	/** @Fields tendersBranchName : 名称 */
	@Column(name = "TENDERS_BRANCH_NAME", nullable = false, length = 100)
	private String tendersBranchName;

	/** @Fields tendersBranchCode : 编码 */
	@Column(name = "TENDERS_BRANCH_CODE", nullable = false, length = 100)
	private String tendersBranchCode;

	/** @Fields stakeNoType : 桩号类型(字典表) */
	@Column(name = "STAKE_NO_TYPE", nullable = false, length = 30)
	private String stakeNoType;

	/** @Fields startStakeNo : 起始桩号 */
	@Column(name = "START_STAKE_NO", nullable = false, length = 100)
	private String startStakeNo;

	/** @Fields simplifyStartStakeNo : 简化的起始桩号 */
	@Column(name = "SIMPLIFY_START_STAKE_NO", nullable = false, length = 100)
	private BigDecimal simplifyStartStakeNo;

	/** @Fields endStakeNo : 结束桩号 */
	@Column(name = "END_STAKE_NO", nullable = false, length = 100)
	private String endStakeNo;

	/** @Fields simplifyEndStakeNo : 简化的结束桩号 */
	@Column(name = "SIMPLIFY_END_STAKE_NO", nullable = false, length = 100)
	private BigDecimal simplifyEndStakeNo;

	/** @Fields contractFigureNo : 合同图号 */
	@Column(name = "CONTRACT_FIGURE_NO", nullable = false, length = 100)
	private String contractFigureNo;

	/** @Fields gradeHigh : 程高 */
	@Column(name = "GRADE_HIGH", nullable = false, length = 100)
	private String gradeHigh;

	/** @Fields checkBaseLibrary : 核对基础库（集团级别的分部分项主键） */
	@Column(name = "CHECK_BASE_LIBRARY", nullable = false)
	private Long checkBaseLibrary;

	/** @Fields checkStatus : 复核状态(字典表：已核对、未核对) */
	@Column(name = "CHECK_STATUS", nullable = false, length = 30)
	private String checkStatus;

	/** @Fields subEngineeringId : 基础数据-项目分部分项ID */
	@Column(name = "SUB_ENGINEERING_ID", nullable = false, length = 100)
	private Long subEngineeringId;

	/** @Fields managementOfBidSectionDetailId : 工程标段管理明细数据ID */
	@Column(name = "MANAGEMENT_OF_BID_SECTION_DETAIL_ID", nullable = false, length = 100)
	private Long managementOfBidSectionDetailId;

	/** @Fields busicontractcontractchangedetailSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "busiContractTendersBranch")
	private Set<BusiContractContractChangeDetail> busiContractContractChangeDetailSet;

	/** @Fields busicontracttendersbranchdetailSet : */
	// mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "busiContractTendersBranch")
	private Set<BusiContractTendersBranchDetail> busiContractTendersBranchDetailSet;

	/** @Fields checkMessage : 校验提示信息 */
	@Column(name = "CHECK_MESSAGE", nullable = false)
	private String checkMessage;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBusiContractTendersContract(
			BusiContractTendersContract busiContractTendersContract) {
		this.busiContractTendersContract = busiContractTendersContract;
	}

	public BusiContractTendersContract getBusiContractTendersContract() {
		return this.busiContractTendersContract;
	}

	public void setTendersBranchName(String tendersBranchName) {
		this.tendersBranchName = tendersBranchName;
	}

	public String getTendersBranchName() {
		return this.tendersBranchName;
	}

	public void setTendersBranchCode(String tendersBranchCode) {
		this.tendersBranchCode = tendersBranchCode;
	}

	public String getTendersBranchCode() {
		return this.tendersBranchCode;
	}

	public void setStakeNoType(String stakeNoType) {
		this.stakeNoType = stakeNoType;
	}

	public String getStakeNoType() {
		return this.stakeNoType;
	}

	public void setStartStakeNo(String startStakeNo) {
		this.startStakeNo = startStakeNo;
	}

	public String getStartStakeNo() {
		return this.startStakeNo;
	}

	public void setEndStakeNo(String endStakeNo) {
		this.endStakeNo = endStakeNo;
	}

	public String getEndStakeNo() {
		return this.endStakeNo;
	}

	public void setContractFigureNo(String contractFigureNo) {
		this.contractFigureNo = contractFigureNo;
	}

	public String getContractFigureNo() {
		return this.contractFigureNo;
	}

	public void setGradeHigh(String gradeHigh) {
		this.gradeHigh = gradeHigh;
	}

	public String getGradeHigh() {
		return this.gradeHigh;
	}

	public void setCheckBaseLibrary(Long checkBaseLibrary) {
		this.checkBaseLibrary = checkBaseLibrary;
	}

	public Long getCheckBaseLibrary() {
		return this.checkBaseLibrary;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckStatus() {
		return this.checkStatus;
	}

	public Set<BusiContractContractChangeDetail> getBusiContractContractChangeDetailSet() {
		return busiContractContractChangeDetailSet;
	}

	public void setBusiContractContractChangeDetailSet(
			Set<BusiContractContractChangeDetail> busiContractContractChangeDetailSet) {
		this.busiContractContractChangeDetailSet = busiContractContractChangeDetailSet;
	}

	public Set<BusiContractTendersBranchDetail> getBusiContractTendersBranchDetailSet() {
		return busiContractTendersBranchDetailSet;
	}

	public void setBusiContractTendersBranchDetailSet(
			Set<BusiContractTendersBranchDetail> busiContractTendersBranchDetailSet) {
		this.busiContractTendersBranchDetailSet = busiContractTendersBranchDetailSet;
	}

	public Long getSubEngineeringId() {
		return subEngineeringId;
	}

	public void setSubEngineeringId(Long subEngineeringId) {
		this.subEngineeringId = subEngineeringId;
	}

	public Long getManagementOfBidSectionDetailId() {
		return managementOfBidSectionDetailId;
	}

	public void setManagementOfBidSectionDetailId(
			Long managementOfBidSectionDetailId) {
		this.managementOfBidSectionDetailId = managementOfBidSectionDetailId;
	}

	public BigDecimal getSimplifyStartStakeNo() {
		return simplifyStartStakeNo;
	}

	public void setSimplifyStartStakeNo(BigDecimal simplifyStartStakeNo) {
		this.simplifyStartStakeNo = simplifyStartStakeNo;
	}

	public BigDecimal getSimplifyEndStakeNo() {
		return simplifyEndStakeNo;
	}

	public void setSimplifyEndStakeNo(BigDecimal simplifyEndStakeNo) {
		this.simplifyEndStakeNo = simplifyEndStakeNo;
	}

	public String getCheckMessage() {
		return checkMessage;
	}

	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}

}
