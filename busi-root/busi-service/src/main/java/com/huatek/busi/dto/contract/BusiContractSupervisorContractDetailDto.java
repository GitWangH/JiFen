package com.huatek.busi.dto.contract;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractSupervisorContractDetailDto
 * @Description: 监理合同清单（安全计量设置）Dto
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 20:07:57
 * @version: 1.0
 */
public class BusiContractSupervisorContractDetailDto {

	private Long id;

	/** @Fields parentId : 上级ID */
	private String parentId;

	/** @Fields order : 排序 */
	private Integer orderNumber;

	/** @Fields number : 编号 */
	private String contractDetailCode;

	/** @Fields name : 名称 */
	private String contractDetailName;

	/** @Fields amount : 金额 */
	private String amount;

	/** @Fields level1 : 是否一级组织（1-是，0-不是） */
	private String level1;

	/** @Fields level2 : 是否二级组织（1-是，0-不是） */
	private String level2;

	/** @Fields level3 : 是否三级组织（1-是，0-不是） */
	private String level3;

	/** @Fields level4 : 是否四级组织（1-是，0-不是） */
	private String level4;

	/** @Fields level5 : 是否五级组织（1-是，0-不是） */
	private String level5;

	/** @Fields level6 : 是否六级组织（1-是，0-不是） */
	private String level6;

	/** @Fields level7 : 是否七级组织（1-是，0-不是） */
	private String level7;

	/** @Fields level8 : 是否八级组织（1-是，0-不是） */
	private String level8;

	/** @Fields level9 : 是否九级组织（1-是，0-不是） */
	private String level9;

	/** @Fields level10 : 是否十级组织（1-是，0-不是） */
	private String level10;

	/** @Fields groupLevel : 组织级别 */
	private Long groupLevel;

	/** @Fields creater : 创建人ID */
	private Long creater;

	/** @Fields createrName : 创建人姓名 */
	private String createrName;

	/** @Fields createTime : 创建时间 */
	private String createTime;

	/** @Fields modifer : 修改人ID */
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	private String modifyTime;

	/** @Fields tenantId : 租户id */
	private Long tenantId;

	/** @Fields orgId : 组织机构ID， ORG_ID为1时， 是系统标准库。 */
	private Long orgId;

	/** @Fields orgName : 组织机构名称 */
	private String orgName;
	
	/** @Fields isLeaf : 是否叶子节点 0 不是 1 是 */
	private Integer isLeaf;

	private String UUID;

	private String operation;// 操作类型

	private boolean launch = false;

	private boolean isShow = true;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	private Integer isDelete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	public String getLevel3() {
		return level3;
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	}

	public String getLevel4() {
		return level4;
	}

	public void setLevel4(String level4) {
		this.level4 = level4;
	}

	public String getLevel5() {
		return level5;
	}

	public void setLevel5(String level5) {
		this.level5 = level5;
	}

	public String getLevel6() {
		return level6;
	}

	public void setLevel6(String level6) {
		this.level6 = level6;
	}

	public String getLevel7() {
		return level7;
	}

	public void setLevel7(String level7) {
		this.level7 = level7;
	}

	public String getLevel8() {
		return level8;
	}

	public void setLevel8(String level8) {
		this.level8 = level8;
	}

	public String getLevel9() {
		return level9;
	}

	public void setLevel9(String level9) {
		this.level9 = level9;
	}

	public String getLevel10() {
		return level10;
	}

	public void setLevel10(String level10) {
		this.level10 = level10;
	}

	public Long getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(Long groupLevel) {
		this.groupLevel = groupLevel;
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

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public boolean isLaunch() {
		return launch;
	}

	public void setLaunch(boolean launch) {
		this.launch = launch;
	}

	public boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(boolean isShow) {
		this.isShow = isShow;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
