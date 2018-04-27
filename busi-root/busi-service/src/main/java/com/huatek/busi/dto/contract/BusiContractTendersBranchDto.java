package com.huatek.busi.dto.contract;

import java.util.List;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiContractTendersBranchDto
 * @Description: 标段分部分项Dto
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-28 14:21:29
 * @version: 1.0
 */
public class BusiContractTendersBranchDto {

	private Long id;
	private Long tendersBranchParentId;// 父级ID
	private String tendersBranchName;// 标段分部分项名称
	private String tendersBranchCode;// 标段分部分项编码
	private String stakeNoType;// 桩号类型(字典表)
	private String startStakeNo;// 起始桩号
	private String endStakeNo;// 结束桩号
	private String simplifyStartStakeNo;// 简化的起始桩号
	private String simplifyEndStakeNo;// 简化的结束桩号
	private String contractFigureNo;// 合同图号
	private String gradeHigh;// 程高
	private Long checkBaseLibrary;// 核对基础库（集团级别的分部分项主键）
	private String checkStatus;// 复核状态(字典表：已核对、未核对)
	private String orderNumber;// 排序编号
	private String applyTime;// 申请时间
	private Long applyUserId;// 申请人ID
	private String applyUserName;// 申请人名称
	private String approvalStatus;// 审批状态
	private String approvalStatusName;// 审批状态名称
	private String approvalTime;// 审批完成时间
	private Long approvalUserId;// 审批人ID
	private String approvalUserName;// 审批人名称
	private Long flowInstanceId;// 流程实例ID
	private String flowResult;// 审批结果
	private String flowMessage;// 审批意见
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
	private String parentId;// 父级UUID
	private Long tenantId;// 租户id
	private Long orgId;// 组织机构ID
	private String orgName;// 组织机构名称
	private Integer isDelete;// 是否删除 0 未删除的正常数据 1 已删除的数据

	private Integer isLeaf;// 是否叶子节点 0 不是 1 是
	private String UUID;
	private String operation;// 操作类型
	private boolean launch = false;
	private boolean isShow = true;

	private String subEngineeringName;// 分部工程
	private String bidSectionName;// 单位工程
	private String designQuantity;// 设计量
	private String designTotalPrice;// 设计金额
	
	private String checkMessage;//校验提示信息

	private List<BusiContractTendersBranchDetailDto> busiContractTendersBranchDetailDtoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTendersBranchParentId() {
		return tendersBranchParentId;
	}

	public void setTendersBranchParentId(Long tendersBranchParentId) {
		this.tendersBranchParentId = tendersBranchParentId;
	}

	public String getTendersBranchName() {
		return tendersBranchName;
	}

	public void setTendersBranchName(String tendersBranchName) {
		this.tendersBranchName = tendersBranchName;
	}

	public String getTendersBranchCode() {
		return tendersBranchCode;
	}

	public void setTendersBranchCode(String tendersBranchCode) {
		this.tendersBranchCode = tendersBranchCode;
	}

	public String getStakeNoType() {
		return stakeNoType;
	}

	public void setStakeNoType(String stakeNoType) {
		this.stakeNoType = stakeNoType;
	}

	public String getStartStakeNo() {
		return startStakeNo;
	}

	public void setStartStakeNo(String startStakeNo) {
		this.startStakeNo = startStakeNo;
	}

	public String getEndStakeNo() {
		return endStakeNo;
	}

	public void setEndStakeNo(String endStakeNo) {
		this.endStakeNo = endStakeNo;
	}

	public String getContractFigureNo() {
		return contractFigureNo;
	}

	public void setContractFigureNo(String contractFigureNo) {
		this.contractFigureNo = contractFigureNo;
	}

	public String getGradeHigh() {
		return gradeHigh;
	}

	public void setGradeHigh(String gradeHigh) {
		this.gradeHigh = gradeHigh;
	}

	public Long getCheckBaseLibrary() {
		return checkBaseLibrary;
	}

	public void setCheckBaseLibrary(Long checkBaseLibrary) {
		this.checkBaseLibrary = checkBaseLibrary;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public Long getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Long getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(Long approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public String getApprovalUserName() {
		return approvalUserName;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public Long getFlowInstanceId() {
		return flowInstanceId;
	}

	public void setFlowInstanceId(Long flowInstanceId) {
		this.flowInstanceId = flowInstanceId;
	}

	public String getFlowResult() {
		return flowResult;
	}

	public void setFlowResult(String flowResult) {
		this.flowResult = flowResult;
	}

	public String getFlowMessage() {
		return flowMessage;
	}

	public void setFlowMessage(String flowMessage) {
		this.flowMessage = flowMessage;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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

	public String getApprovalStatusName() {
		return approvalStatusName;
	}

	public void setApprovalStatusName(String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
	}

	public List<BusiContractTendersBranchDetailDto> getBusiContractTendersBranchDetailDtoList() {
		return busiContractTendersBranchDetailDtoList;
	}

	public void setBusiContractTendersBranchDetailDtoList(
			List<BusiContractTendersBranchDetailDto> busiContractTendersBranchDetailDtoList) {
		this.busiContractTendersBranchDetailDtoList = busiContractTendersBranchDetailDtoList;
	}

	public String getSimplifyStartStakeNo() {
		return simplifyStartStakeNo;
	}

	public void setSimplifyStartStakeNo(String simplifyStartStakeNo) {
		this.simplifyStartStakeNo = simplifyStartStakeNo;
	}

	public String getSimplifyEndStakeNo() {
		return simplifyEndStakeNo;
	}

	public void setSimplifyEndStakeNo(String simplifyEndStakeNo) {
		this.simplifyEndStakeNo = simplifyEndStakeNo;
	}

	public String getSubEngineeringName() {
		return subEngineeringName;
	}

	public void setSubEngineeringName(String subEngineeringName) {
		this.subEngineeringName = subEngineeringName;
	}

	public String getBidSectionName() {
		return bidSectionName;
	}

	public void setBidSectionName(String bidSectionName) {
		this.bidSectionName = bidSectionName;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getCheckMessage() {
		return checkMessage;
	}

	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}

}
