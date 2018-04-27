package com.huatek.busi.dto.contract;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractTendersBranchDetail
 * @Description: 标段分部分项清单Dto
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-08 15:03:20
 * @version: 1.0
 */
public class BusiContractTendersBranchDetailDto {

	private Long id;
	private Long tendersBranchId;// 标段分部分项ID
	private Long contractDetailId;// 合同清单主键ID
	private String contractDetailName;// 合同清单名称
	private String contractDetailCode;// 合同清单编号
	private String countUnit;// 合同清单管理-合同清单-单位
	private String workabilityQuantity;// 合同清单可使用数量
	private String dissolubleQuantity;// 可分解数量
	private String designUnitPrice;// 设计单价（元）
	private String designQuantity;// 设计数量
	private String designTotalPrice;// 设计金额（元）
	private String cumulativeQuantity;// 累计计量
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户id
	private Long orgId;// 组织机构ID
	private String orgName;// 组织机构名称
	private String opration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTendersBranchId() {
		return tendersBranchId;
	}

	public void setTendersBranchId(Long tendersBranchId) {
		this.tendersBranchId = tendersBranchId;
	}

	public Long getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(Long contractDetailId) {
		this.contractDetailId = contractDetailId;
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

	public String getDissolubleQuantity() {
		return dissolubleQuantity;
	}

	public void setDissolubleQuantity(String dissolubleQuantity) {
		this.dissolubleQuantity = dissolubleQuantity;
	}

	public String getDesignUnitPrice() {
		return designUnitPrice;
	}

	public void setDesignUnitPrice(String designUnitPrice) {
		this.designUnitPrice = designUnitPrice;
	}

	public String getDesignQuantity() {
		return designQuantity;
	}

	public void setDesignQuantity(String designQuantity) {
		this.designQuantity = designQuantity;
	}

	public String getDesignTotalPrice() {
		return designTotalPrice;
	}

	public void setDesignTotalPrice(String designTotalPrice) {
		this.designTotalPrice = designTotalPrice;
	}

	public String getCumulativeQuantity() {
		return cumulativeQuantity;
	}

	public void setCumulativeQuantity(String cumulativeQuantity) {
		this.cumulativeQuantity = cumulativeQuantity;
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

	public String getOpration() {
		return opration;
	}

	public void setOpration(String opration) {
		this.opration = opration;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getWorkabilityQuantity() {
		return workabilityQuantity;
	}

	public void setWorkabilityQuantity(String workabilityQuantity) {
		this.workabilityQuantity = workabilityQuantity;
	}

}
