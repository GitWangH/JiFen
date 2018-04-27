package com.huatek.busi.dto.progress;

import java.math.BigDecimal;

/**
 * 标段形象清单划分实体类.
 * 
 * @ClassName: BusiProgressImage
 * @Description:
 * @author: jordan_li
 * @Email :
 * @date: 2017-12-06 10:58:13
 * @version: Windows 7
 */
public class BusiProgressImageDto {
	private Long id;

	private Long orgId;// 标段id

	private String orgName;// 标段名称

	private String code;// 编号

	private String name;// 名称

	private String unit;// 单位

	private String keyProject;// 重点工程(0不是，1是)

	private BigDecimal designQuantity;// 设计工程量

	private String designUnitPrice;// 设计单价

	private BigDecimal designAmount;// 设计金额

	private String changeQuantity;// 变更后工程量

	private String changeUnitPrice;// 变更后单价

	private BigDecimal changeAmount;// 变更后金额

	private String remark;// 备注

	private Long creater;// 创建人id

	private String createrName;// 创建人姓名

	private String createTime;// 创建时间

	private Long modifer;// 修改人id

	private String modifierName;// 修改人姓名

	private String modifyTime;// 修改时间

	private Long tenantId;// 租户id

	private Long baseImageId;// 基础库形象清单id

	private Integer orderNumber;// 顺序

	private String level1;// 是否一级组织（1-是，0-不是）

	private String level2;// 是否二级组织（1-是，0-不是）

	private String level3;// 是否三级组织（1-是，0-不是）

	private String level4;// 是否四级组织（1-是，0-不是）

	private String level5;// 是否五级组织（1-是，0-不是）

	private String level6;// 是否六级组织（1-是，0-不是）

	private String level7;// 是否七级组织（1-是，0-不是）

	private String level8;// 是否八级组织（1-是，0-不是）

	private String level9;// 是否九级组织（1-是，0-不是）

	private String level10;// 是否十级组织（1-是，0-不是）

	private Long groupLevel;// 组织级别

	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isLeaf;// 是否叶子节点 0 不是 1 是

	private String uuid;// 前台传输唯一键
	
	private String parentId;//上级uuid
	
    /**操作类型 add 新增  update修改  delete删除*/
    private String operation;
    
	private boolean launch = false;

	private boolean isShow = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getKeyProject() {
		return keyProject;
	}

	public void setKeyProject(String keyProject) {
		this.keyProject = keyProject;
	}

	public BigDecimal getDesignQuantity() {
		return designQuantity;
	}

	public void setDesignQuantity(BigDecimal designQuantity) {
		this.designQuantity = designQuantity;
	}

	public String getDesignUnitPrice() {
		return designUnitPrice;
	}

	public void setDesignUnitPrice(String designUnitPrice) {
		this.designUnitPrice = designUnitPrice;
	}

	public BigDecimal getDesignAmount() {
		return designAmount;
	}

	public void setDesignAmount(BigDecimal designAmount) {
		this.designAmount = designAmount;
	}

	public String getChangeQuantity() {
		return changeQuantity;
	}

	public void setChangeQuantity(String changeQuantity) {
		this.changeQuantity = changeQuantity;
	}

	public String getChangeUnitPrice() {
		return changeUnitPrice;
	}

	public void setChangeUnitPrice(String changeUnitPrice) {
		this.changeUnitPrice = changeUnitPrice;
	}

	public BigDecimal getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getBaseImageId() {
		return baseImageId;
	}

	public void setBaseImageId(Long baseImageId) {
		this.baseImageId = baseImageId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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
}
