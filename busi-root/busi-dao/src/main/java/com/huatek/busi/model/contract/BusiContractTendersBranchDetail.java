package com.huatek.busi.model.contract;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractTendersBranchDetail
 * @Description: 标段分部分项清单
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:11:54
 * @version: 1.0
 */
@Entity
@Table(name = "busi_contract_tenders_branch_detail")
public class BusiContractTendersBranchDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "TENDERS_BRANCH_DETAIL_ID", nullable = true)
	private Long id;

	/** @Fields busiContractTendersBranch : 标段分部分项ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "TENDERS_BRANCH_ID")
	private BusiContractTendersBranch busiContractTendersBranch;

	/** @Fields busiContractTendersContractDetail : 合同清单主键ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "TENDERS_CONTRACT_DETAIL_ID")
	private BusiContractTendersContractDetail busiContractTendersContractDetail;

	/** @Fields dissolubleQuantity : 可分解数量 */
	@Column(name = "DISSOLUBLE_QUANTITY", nullable = false, precision = 18, scale = 2)
	private BigDecimal dissolubleQuantity;

	/** @Fields designUnitPrice : 设计单价（元） */
	@Column(name = "DESIGN_UNIT_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal designUnitPrice;

	/** @Fields designQuantity : 设计数量 */
	@Column(name = "DESIGN_QUANTITY", nullable = false, precision = 18, scale = 2)
	private BigDecimal designQuantity;

	/** @Fields designTotalPrice : 设计金额（元） */
	@Column(name = "DESIGN_TOTAL_PRICE", nullable = false, precision = 18, scale = 4)
	private BigDecimal designTotalPrice;

	/** @Fields cumulativeQuantity : 累计计量 */
	@Column(name = "CUMULATIVE_QUANTITY", nullable = false, precision = 18, scale = 2)
	private BigDecimal cumulativeQuantity;

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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBusiContractTendersBranch(
			BusiContractTendersBranch busiContractTendersBranch) {
		this.busiContractTendersBranch = busiContractTendersBranch;
	}

	public BusiContractTendersBranch getBusiContractTendersBranch() {
		return this.busiContractTendersBranch;
	}

	public void setBusiContractTendersContractDetail(
			BusiContractTendersContractDetail busiContractTendersContractDetail) {
		this.busiContractTendersContractDetail = busiContractTendersContractDetail;
	}

	public BusiContractTendersContractDetail getBusiContractTendersContractDetail() {
		return this.busiContractTendersContractDetail;
	}

	public void setDissolubleQuantity(BigDecimal dissolubleQuantity) {
		this.dissolubleQuantity = dissolubleQuantity;
	}

	public BigDecimal getDissolubleQuantity() {
		return this.dissolubleQuantity;
	}

	public void setDesignUnitPrice(BigDecimal designUnitPrice) {
		this.designUnitPrice = designUnitPrice;
	}

	public BigDecimal getDesignUnitPrice() {
		return this.designUnitPrice;
	}

	public void setDesignQuantity(BigDecimal designQuantity) {
		this.designQuantity = designQuantity;
	}

	public BigDecimal getDesignQuantity() {
		return this.designQuantity;
	}

	public void setDesignTotalPrice(BigDecimal designTotalPrice) {
		this.designTotalPrice = designTotalPrice;
	}

	public BigDecimal getDesignTotalPrice() {
		return this.designTotalPrice;
	}

	public void setCumulativeQuantity(BigDecimal cumulativeQuantity) {
		this.cumulativeQuantity = cumulativeQuantity;
	}

	public BigDecimal getCumulativeQuantity() {
		return this.cumulativeQuantity;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
