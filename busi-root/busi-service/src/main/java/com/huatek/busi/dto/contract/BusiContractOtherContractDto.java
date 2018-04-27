package com.huatek.busi.dto.contract;



/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractOtherContract
 * @Description: 其它合同管理DTO
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @String: 2017-10-27 11:27:01
 * @version: 1.0
 */
public class BusiContractOtherContractDto {

	private Long id;// 其它合同ID
	private Long orgId;// 机构ID
	private String orgName;// 单位名称
	private String contractName;// 合同名称
	private String signatureData;// 签订日期
	private String contractDescription;// 描述
	private String contractFile;// 合同附件
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户i

	// private Set<BusiContractOtherContractDetailDto>
	// busicontractothercontractdetailSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return this.orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setSignatureData(String signatureData) {
		this.signatureData = signatureData;
	}

	public String getSignatureData() {
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

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
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

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTenantId() {
		return this.tenantId;
	}

}
