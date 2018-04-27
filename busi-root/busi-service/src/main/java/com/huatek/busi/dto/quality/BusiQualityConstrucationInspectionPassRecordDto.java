package com.huatek.busi.dto.quality;

 /**
  * 施工报检审批通过记录DTO
  * @ClassName: BusiQualityConstrucationInspectionPassRecordDTO
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-14 18:00:11
  * @version: Windows 7
  */
public class BusiQualityConstrucationInspectionPassRecordDto{

	private static final long serialVersionUID = 1L;
	
 	private Long id;

	/** @Fields constructionInspectionId : 施工报检ID */
    private Long constructionInspectionId;
    
	/** @Fields approverName : 审批人姓名*/ 
    private String approverName;
    
	/** @Fields approverTime : 审批人时间 */
    private String approverTime;
    
	/** @Fields approverId : 审批人ID */
    private Long approverId;
    
	/** @Fields flowStep : 流程步骤 */
    private String flowStep;
    
	/** @Fields flowStepName : 流程步骤名称*/ 
    private String flowStepName;
    
	/** @Fields fileId : 附件UUID */
    private String passRecordFileId;
    
	/** @Fields flowResult : 审批结果 */
    private String flowResult;
    
    /** @Fields flowMessage : 审批意见 */
    private String flowMessage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConstructionInspectionId() {
		return constructionInspectionId;
	}

	public void setConstructionInspectionId(Long constructionInspectionId) {
		this.constructionInspectionId = constructionInspectionId;
	}

	public String getApproverTime() {
		return approverTime;
	}

	public void setApproverTime(String approverTime) {
		this.approverTime = approverTime;
	}

	public String getFlowStepName() {
		return flowStepName;
	}

	public void setFlowStepName(String flowStepName) {
		this.flowStepName = flowStepName;
	}

	public String getPassRecordFileId() {
		return passRecordFileId;
	}

	public void setPassRecordFileId(String passRecordFileId) {
		this.passRecordFileId = passRecordFileId;
	}

	public String getFlowMessage() {
		return flowMessage;
	}

	public void setFlowMessage(String flowMessage) {
		this.flowMessage = flowMessage;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public String getFlowStep() {
		return flowStep;
	}

	public void setFlowStep(String flowStep) {
		this.flowStep = flowStep;
	}

	public String getFlowResult() {
		return flowResult;
	}

	public void setFlowResult(String flowResult) {
		this.flowResult = flowResult;
	}
    
}
