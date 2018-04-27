package com.huatek.busi.model.contract;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiTreeGridEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractSupervisorContractDetail
 * @Description: 监理合同清单（安全计量设置）
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:10:57
 * @version: 1.0
 */
@Entity
@Table(name = "busi_contract_supervisor_contract_detail")
public class BusiContractSupervisorContractDetail extends BusiTreeGridEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "SUPERVISOR_CONTRACT_DETAIL_ID", nullable = true)
	private Long id;

	/** @Fields number : 编号 */
	@Column(name = "CONTRACT_DETAIL_CODE", nullable = false, length = 100)
	private String contractDetailCode;

	/** @Fields name : 名称 */
	@Column(name = "CONTRACT_DETAIL_NAME", nullable = false, length = 100)
	private String contractDetailName;

	/** @Fields amount : 金额 */
	@Column(name = "AMOUNT", nullable = false, precision = 18, scale = 4)
	private BigDecimal amount;
	
	/** @Fields busiContractSupervisorContract : 监理合同主键ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "SUPERVISOR_CONTRACT_ID")
	private BusiContractSupervisorContract busiContractSupervisorContract;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractDetailCode() {
		return contractDetailCode;
	}

	public void setContractDetailCode(String contractDetailCode) {
		this.contractDetailCode = contractDetailCode;
	}

	public String getContractDetailName() {
		return contractDetailName;
	}

	public void setContractDetailName(String contractDetailName) {
		this.contractDetailName = contractDetailName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BusiContractSupervisorContract getBusiContractSupervisorContract() {
		return busiContractSupervisorContract;
	}

	public void setBusiContractSupervisorContract(
			BusiContractSupervisorContract busiContractSupervisorContract) {
		this.busiContractSupervisorContract = busiContractSupervisorContract;
	}

}
