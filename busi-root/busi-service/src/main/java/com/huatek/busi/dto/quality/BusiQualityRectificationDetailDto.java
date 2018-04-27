package com.huatek.busi.dto.quality;

/**
 * 整改单明细
 * @author rocky_wei
 *
 */
public class BusiQualityRectificationDetailDto {

//	private Long id;
    
	/** @Fields rectificationId : 外键ID*/ 
    private String rectificationId;
    
	/** @Fields backupField1 : 预留1*/ 
    private String backupField1;
    
	/** @Fields backupField2 : 预留2*/ 
    private String backupField2;
    
	/** @Fields backupField3 : 预留3*/ 
    private String backupField3;
    
	/** @Fields backupField4 : 预留4*/ 
    private String backupField4;
    
	/** @Fields backupField5 : 预留5*/ 
    private String backupField5;
    
	/** @Fields backupField6 : 预留6*/ 
    private String backupField6;
    
	/** @Fields orgId : 组织机构ID */
    private Long orgId;
    
	/** @Fields creater : 创建人ID */
    private Long creater;
    
	/** @Fields createrName : 创建人姓名*/ 
    private String createrName;
    
	/** @Fields createTime : 创建时间 */
    private String createTime;
    
	/** @Fields modifer : 修改人ID */
    private Long modifer;
    
	/** @Fields modifierName : 修改人姓名*/ 
    private String modifierName;
    
	/** @Fields modifyTime : 修改时间 */
    private String modifyTime;
    
	/** @Fields tenantId : 租户id */
    private Long tenantId;

    /** @Fields rectificationDescription : 整改描述 */
    private String rectificationDescription;
    
    /** @Fields fileId : 附件编码 */
    private String detailFileId;
    
    /** @Fields detailType : 明细类型 */
    private String detailType;
    
    /** 复查结果 */
    private Boolean detailFlowResult;
    
    /** 流程节点名称 */
    private String taskId;
    
    /** 数量索引 */
    private int index;
    
    /** @Fields flowStep : 流程步骤 */
    private String flowStep;
    
    /** @Fields flowStepName :流程步骤名称*/
    private String flowStepName;
    
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getRectificationId() {
		return rectificationId;
	}

	public void setRectificationId(String rectificationId) {
		this.rectificationId = rectificationId;
	}

	public String getBackupField1() {
		return backupField1;
	}

	public void setBackupField1(String backupField1) {
		this.backupField1 = backupField1;
	}

	public String getBackupField2() {
		return backupField2;
	}

	public void setBackupField2(String backupField2) {
		this.backupField2 = backupField2;
	}

	public String getBackupField3() {
		return backupField3;
	}

	public void setBackupField3(String backupField3) {
		this.backupField3 = backupField3;
	}

	public String getBackupField4() {
		return backupField4;
	}

	public void setBackupField4(String backupField4) {
		this.backupField4 = backupField4;
	}

	public String getBackupField5() {
		return backupField5;
	}

	public void setBackupField5(String backupField5) {
		this.backupField5 = backupField5;
	}

	public String getBackupField6() {
		return backupField6;
	}

	public void setBackupField6(String backupField6) {
		this.backupField6 = backupField6;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getRectificationDescription() {
		return rectificationDescription;
	}

	public void setRectificationDescription(String rectificationDescription) {
		this.rectificationDescription = rectificationDescription;
	}

	public String getDetailFileId() {
		return detailFileId;
	}

	public void setDetailFileId(String detailFileId) {
		this.detailFileId = detailFileId;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public Boolean getDetailFlowResult() {
		return detailFlowResult; 
	}

	public void setDetailFlowResult(Boolean detailFlowResult) {
		this.detailFlowResult = detailFlowResult;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getFlowStep() {
		return flowStep;
	}

	public void setFlowStep(String flowStep) {
		this.flowStep = flowStep;
	}

	public String getFlowStepName() {
		return flowStepName;
	}

	public void setFlowStepName(String flowStepName) {
		this.flowStepName = flowStepName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
