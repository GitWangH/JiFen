package com.huatek.busi.dto.contract;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractContractChangeDetail
 * @Description: 合同变更明细
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-25 15:12:40
 * @version: 1.0
 */
public class BusiContractContractChangeDetailDto {

	private Long id;
	private Long contractChangeId;// 合同变更主表ID
	private Long contractDetailId;// 合同清单主键ID
	private Long tendersBranchId;// 标段分部分项ID
	private String changeType;// 数据字典维护，包括数量变更、单价变更（对原清单）、单价变更（对增减量）
	private String changeBeforeUnitPrice;// 变更前单价(元)
	private String changeBeforeQuantity;// 变更前数量
	private String changeBeforeTotalPrice;// 变更前金额(元)
	private String changeUnitPrice;// 变更单价(元)
	private String changeQuantity;// 变更数量
	private String changeAfterUnitPrice;// 变更后单价(元)
	private String changeAfterQuantity;// 变更后数量
	private String changeAfterTotalPrice;// 变更后金额(元)
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户id
	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据
	private Long orgId;// 组织机构ID
	private Long orgName;// 组织机构名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContractChangeId() {
		return contractChangeId;
	}

	public void setContractChangeId(Long contractChangeId) {
		this.contractChangeId = contractChangeId;
	}

	public Long getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(Long contractDetailId) {
		this.contractDetailId = contractDetailId;
	}

	public Long getTendersBranchId() {
		return tendersBranchId;
	}

	public void setTendersBranchId(Long tendersBranchId) {
		this.tendersBranchId = tendersBranchId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeBeforeUnitPrice() {
		return changeBeforeUnitPrice;
	}

	public void setChangeBeforeUnitPrice(String changeBeforeUnitPrice) {
		this.changeBeforeUnitPrice = changeBeforeUnitPrice;
	}

	public String getChangeBeforeQuantity() {
		return changeBeforeQuantity;
	}

	public void setChangeBeforeQuantity(String changeBeforeQuantity) {
		this.changeBeforeQuantity = changeBeforeQuantity;
	}

	public String getChangeBeforeTotalPrice() {
		return changeBeforeTotalPrice;
	}

	public void setChangeBeforeTotalPrice(String changeBeforeTotalPrice) {
		this.changeBeforeTotalPrice = changeBeforeTotalPrice;
	}

	public String getChangeUnitPrice() {
		return changeUnitPrice;
	}

	public void setChangeUnitPrice(String changeUnitPrice) {
		this.changeUnitPrice = changeUnitPrice;
	}

	public String getChangeQuantity() {
		return changeQuantity;
	}

	public void setChangeQuantity(String changeQuantity) {
		this.changeQuantity = changeQuantity;
	}

	public String getChangeAfterUnitPrice() {
		return changeAfterUnitPrice;
	}

	public void setChangeAfterUnitPrice(String changeAfterUnitPrice) {
		this.changeAfterUnitPrice = changeAfterUnitPrice;
	}

	public String getChangeAfterQuantity() {
		return changeAfterQuantity;
	}

	public void setChangeAfterQuantity(String changeAfterQuantity) {
		this.changeAfterQuantity = changeAfterQuantity;
	}

	public String getChangeAfterTotalPrice() {
		return changeAfterTotalPrice;
	}

	public void setChangeAfterTotalPrice(String changeAfterTotalPrice) {
		this.changeAfterTotalPrice = changeAfterTotalPrice;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgName() {
		return orgName;
	}

	public void setOrgName(Long orgName) {
		this.orgName = orgName;
	}

}
