package com.huatek.busi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.huatek.frame.core.model.BaseEntity;

@MappedSuperclass
public abstract class BusiTreeGridEntity extends BaseEntity {

	/** @Fields order : 顺序 */
	@Column(name = "ORDER_NUMBER", nullable = false)
	private Integer orderNumber;

	/** @Fields orgId : 组织机构ID */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg busiFwOrg;// 关联Org对象获取机构信息

	/** @Fields parentId : 上级ID */
	@Column(name = "PARENT_ID", nullable = false)
	private String parentId;

	/** @Fields creater : 创建人ID */
	@Column(name = "CREATER", nullable = false)
	private Long creater;

	/** @Fields createrName : 创建人姓名 */
	@Column(name = "CREATER_NAME", nullable = false, length = 100)
	private String createrName;

	/** @Fields createTime : 创建时间 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/** @Fields modifer : 修改人ID */
	@Column(name = "MODIFER", nullable = false)
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	@Column(name = "MODIFIER_NAME", nullable = false, length = 100)
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;

	/** @Fields level1 : 是否一级组织（1-是，0-不是） */
	@Column(name = "LEVEL_1", nullable = false)
	private String level1;

	/** @Fields level2 : 是否二级组织（1-是，0-不是） */
	@Column(name = "LEVEL_2", nullable = false)
	private String level2;

	/** @Fields level3 : 是否三级组织（1-是，0-不是） */
	@Column(name = "LEVEL_3", nullable = false)
	private String level3;

	/** @Fields level4 : 是否四级组织（1-是，0-不是） */
	@Column(name = "LEVEL_4", nullable = false)
	private String level4;

	/** @Fields level5 : 是否五级组织（1-是，0-不是） */
	@Column(name = "LEVEL_5", nullable = false)
	private String level5;

	/** @Fields level6 : 是否六级组织（1-是，0-不是） */
	@Column(name = "LEVEL_6", nullable = false)
	private String level6;

	/** @Fields level7 : 是否七级组织（1-是，0-不是） */
	@Column(name = "LEVEL_7", nullable = false)
	private String level7;

	/** @Fields level8 : 是否八级组织（1-是，0-不是） */
	@Column(name = "LEVEL_8", nullable = false)
	private String level8;

	/** @Fields level9 : 是否九级组织（1-是，0-不是） */
	@Column(name = "LEVEL_9", nullable = false)
	private String level9;

	/** @Fields level10 : 是否十级组织（1-是，0-不是） */
	@Column(name = "LEVEL_10", nullable = false)
	private String level10;

	/** @Fields groupLevel : 组织级别 */
	@Column(name = "GROUP_LEVEL", nullable = false)
	private Long groupLevel;

	/** @Fields tenantId : 租户id */
	@Column(name = "TENANT_ID", nullable = false)
	private Long tenantId;

	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	@Column(name = "IS_DELETE", nullable = false)
	private Integer isDelete;
	
	/** @Fields isLeaf : 是否叶子节点 0 不是 1 是*/
	@Column(name = "IS_LEAF", nullable = false)
	private Integer isLeaf;
	
	@Column(name = "UUID", nullable = false)
	private String UUID;

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BusiFwOrg getBusiFwOrg() {
		return busiFwOrg;
	}

	public void setBusiFwOrg(BusiFwOrg busiFwOrg) {
		this.busiFwOrg = busiFwOrg;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String UUID) {
		this.UUID = UUID;
	}
}
