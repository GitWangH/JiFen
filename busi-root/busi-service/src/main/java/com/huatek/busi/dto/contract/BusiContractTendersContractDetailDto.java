package com.huatek.busi.dto.contract;

/**
 * @ClassName: BusiContractTendersContractDetailDto
 * @Description: 标段合同清单(复合清单)Dto
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-01 15:12:35
 * @version: 1.0
 */
public class BusiContractTendersContractDetailDto {

	private Long id;
	private String parentId;// 父级ID
	private String contractDetailName;// 合同清单名称
	private String contractDetailCode;// 合同清单编号
	private String countUnit;// 合同清单管理-合同清单-单位
	private String unitPrice;// 合同单价（元）
	private String quantity;// 合同数量
	private String totalPrice;// 合同明细总价(元)
	private String reviewUnitPrice;// 合同明细复核单价（元）
	private String reviewQuantity;// 合同明细复核数量
	private String reviewTotalPrice;// 合同明细复核总价(元)
	private String detaileType;// 明细类型(原清单-0、新增变更清单未审核-1,新增变更清单已审核-2， 数据字典)
	private Integer orderNumber;// 排序编号
	private String workabilityQuantity;// 可使用量
	private String usedQuantity;// 已使用量

	private String detailApplyTime;// 清单申请时间
	private Long detailApplyUserId;// 清单申请人ID
	private String detailApplyUserName;// 清单申请人名称
	private String detailApprovalStatus;// 清单审批状态
	private String detailApprovalTime;// 清单审批完成时间
	private Long detailApprovalUserId;// 清单审批人ID
	private String detailApprovalUserName;// 清单审批人名称
	private Long detailFlowInstanceId;// 清单流程实例ID
	private String detailFlowResult;// 清单审批结果
	private String detailFlowMessage;// 清单审批意见
	private String checkApplyTime;// 复核申请时间
	private Long checkApplyUserId;// 复核申请人ID
	private String checkApplyUserName;// 复核申请人名称
	private String checkApprovalStatus;// 复核审批状态
	private String checkApprovalTime;// 复核审批完成时间
	private Long checkApprovalUserId;// 复核审批人ID
	private String checkApprovalUserName;// 复核审批人名称
	private Long checkFlowInstanceId;// 复核流程实例ID
	private String checkFlowResult;// 复核审批结果
	private String checkFlowMessage;// 复核审批意见

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
	private Long creater;// 创建人id
	private String createrName;// 创建人姓名
	private String createTime;// 创建时间
	private Long modifer;// 修改人id
	private String modifierName;// 修改人姓名
	private String modifyTime;// 修改时间
	private Long tenantId;// 租户id
	private Long orgId;// 组织机构ID
	private String orgName;// 组织机构名称

	/** @Fields isLeaf : 是否叶子节点 0 不是 1 是 */
	private Integer isLeaf;
	private String UUID;
	private String operation;// 操作类型
	private boolean launch = false;
	private boolean isShow = true;
	/** @Fields isDelete : 是否删除 0 未删除的正常数据 1 已删除的数据 */
	private Integer isDelete;

	private String approvalStatusName;// 审批状态名称

	/*** 导入数据编号 ***/
	private String firstLevelCode;// 第一级编号
	private String secondLevelCode;// 第二级编号
	private String thirdLevelCode;// 第三极编号
	private String fourthLevelCode;// 第四极编号

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

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getReviewUnitPrice() {
		return reviewUnitPrice;
	}

	public void setReviewUnitPrice(String reviewUnitPrice) {
		this.reviewUnitPrice = reviewUnitPrice;
	}

	public String getReviewQuantity() {
		return reviewQuantity;
	}

	public void setReviewQuantity(String reviewQuantity) {
		this.reviewQuantity = reviewQuantity;
	}

	public String getReviewTotalPrice() {
		return reviewTotalPrice;
	}

	public void setReviewTotalPrice(String reviewTotalPrice) {
		this.reviewTotalPrice = reviewTotalPrice;
	}

	public String getDetaileType() {
		return detaileType;
	}

	public void setDetaileType(String detaileType) {
		this.detaileType = detaileType;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getWorkabilityQuantity() {
		return workabilityQuantity;
	}

	public void setWorkabilityQuantity(String workabilityQuantity) {
		this.workabilityQuantity = workabilityQuantity;
	}

	public String getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(String usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	public String getDetailApplyTime() {
		return detailApplyTime;
	}

	public void setDetailApplyTime(String detailApplyTime) {
		this.detailApplyTime = detailApplyTime;
	}

	public Long getDetailApplyUserId() {
		return detailApplyUserId;
	}

	public void setDetailApplyUserId(Long detailApplyUserId) {
		this.detailApplyUserId = detailApplyUserId;
	}

	public String getDetailApplyUserName() {
		return detailApplyUserName;
	}

	public void setDetailApplyUserName(String detailApplyUserName) {
		this.detailApplyUserName = detailApplyUserName;
	}

	public String getDetailApprovalStatus() {
		return detailApprovalStatus;
	}

	public void setDetailApprovalStatus(String detailApprovalStatus) {
		this.detailApprovalStatus = detailApprovalStatus;
	}

	public String getDetailApprovalTime() {
		return detailApprovalTime;
	}

	public void setDetailApprovalTime(String detailApprovalTime) {
		this.detailApprovalTime = detailApprovalTime;
	}

	public Long getDetailApprovalUserId() {
		return detailApprovalUserId;
	}

	public void setDetailApprovalUserId(Long detailApprovalUserId) {
		this.detailApprovalUserId = detailApprovalUserId;
	}

	public String getDetailApprovalUserName() {
		return detailApprovalUserName;
	}

	public void setDetailApprovalUserName(String detailApprovalUserName) {
		this.detailApprovalUserName = detailApprovalUserName;
	}

	public Long getDetailFlowInstanceId() {
		return detailFlowInstanceId;
	}

	public void setDetailFlowInstanceId(Long detailFlowInstanceId) {
		this.detailFlowInstanceId = detailFlowInstanceId;
	}

	public String getDetailFlowResult() {
		return detailFlowResult;
	}

	public void setDetailFlowResult(String detailFlowResult) {
		this.detailFlowResult = detailFlowResult;
	}

	public String getDetailFlowMessage() {
		return detailFlowMessage;
	}

	public void setDetailFlowMessage(String detailFlowMessage) {
		this.detailFlowMessage = detailFlowMessage;
	}

	public String getCheckApplyTime() {
		return checkApplyTime;
	}

	public void setCheckApplyTime(String checkApplyTime) {
		this.checkApplyTime = checkApplyTime;
	}

	public Long getCheckApplyUserId() {
		return checkApplyUserId;
	}

	public void setCheckApplyUserId(Long checkApplyUserId) {
		this.checkApplyUserId = checkApplyUserId;
	}

	public String getCheckApplyUserName() {
		return checkApplyUserName;
	}

	public void setCheckApplyUserName(String checkApplyUserName) {
		this.checkApplyUserName = checkApplyUserName;
	}

	public String getCheckApprovalStatus() {
		return checkApprovalStatus;
	}

	public void setCheckApprovalStatus(String checkApprovalStatus) {
		this.checkApprovalStatus = checkApprovalStatus;
	}

	public String getCheckApprovalTime() {
		return checkApprovalTime;
	}

	public void setCheckApprovalTime(String checkApprovalTime) {
		this.checkApprovalTime = checkApprovalTime;
	}

	public Long getCheckApprovalUserId() {
		return checkApprovalUserId;
	}

	public void setCheckApprovalUserId(Long checkApprovalUserId) {
		this.checkApprovalUserId = checkApprovalUserId;
	}

	public String getCheckApprovalUserName() {
		return checkApprovalUserName;
	}

	public void setCheckApprovalUserName(String checkApprovalUserName) {
		this.checkApprovalUserName = checkApprovalUserName;
	}

	public Long getCheckFlowInstanceId() {
		return checkFlowInstanceId;
	}

	public void setCheckFlowInstanceId(Long checkFlowInstanceId) {
		this.checkFlowInstanceId = checkFlowInstanceId;
	}

	public String getCheckFlowResult() {
		return checkFlowResult;
	}

	public void setCheckFlowResult(String checkFlowResult) {
		this.checkFlowResult = checkFlowResult;
	}

	public String getCheckFlowMessage() {
		return checkFlowMessage;
	}

	public void setCheckFlowMessage(String checkFlowMessage) {
		this.checkFlowMessage = checkFlowMessage;
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

	public String getApprovalStatusName() {
		return approvalStatusName;
	}

	public void setApprovalStatusName(String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
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

	public String getFirstLevelCode() {
		return firstLevelCode;
	}

	public void setFirstLevelCode(String firstLevelCode) {
		this.firstLevelCode = firstLevelCode;
	}

	public String getSecondLevelCode() {
		return secondLevelCode;
	}

	public void setSecondLevelCode(String secondLevelCode) {
		this.secondLevelCode = secondLevelCode;
	}

	public String getThirdLevelCode() {
		return thirdLevelCode;
	}

	public void setThirdLevelCode(String thirdLevelCode) {
		this.thirdLevelCode = thirdLevelCode;
	}

	public String getFourthLevelCode() {
		return fourthLevelCode;
	}

	public void setFourthLevelCode(String fourthLevelCode) {
		this.fourthLevelCode = fourthLevelCode;
	}

}
