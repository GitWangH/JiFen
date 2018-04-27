package com.huatek.busi.model.quality;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 施工报检审批通过记录
  * @ClassName: BusiQualityConstrucationInspectionPassRecord
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-14 18:00:11
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_quality_construction_inspection_pass_record")
public class BusiQualityConstrucationInspectionPassRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "CONSTRUCTION_PASS_RECORD_ID", nullable = true )
 	private Long id;

	/** @Fields constructionInspectionId : 施工报检ID */
	@Column(name= "CONSTRUCTION_INSPECTION_ID", nullable = false)
    private Long constructionInspectionId;
    
	/** @Fields approverName : 审批人姓名*/ 
	@Column(name= "APPROVER_NAME", nullable = false)
    private String approverName;
    
	/** @Fields approverTime : 审批人时间 */
	@Column(name= "APPROVER_TIME", nullable = false, length=100 )
    private Date approverTime;
    
	/** @Fields approverId : 审批人ID */
	@Column(name= "APPROVER_ID", nullable = false)
    private Long approverId;
    
	/** @Fields flowStep : 流程步骤 */
	@Column(name= "FLOW_STEP", nullable = false)
    private String flowStep;
    
	/** @Fields flowStepName : 流程步骤名称*/ 
	@Column(name= "FLOW_STEP_NAME", nullable = false, length=100 )
    private String flowStepName;
    
	/** @Fields fileId : 附件UUID */
	@Column(name= "FILE_ID", nullable = false)
    private String passRecordFileId;
    
	/** @Fields flowResult : 审批结果 */
	@Column(name= "FLOW_RESULT", nullable = true)
    private Integer flowResult;
    
    /** @Fields flowMessage : 审批意见 */
    @Column(name= "FLOW_MESSAGE", nullable = true)
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

	public String getFlowStepName() {
		return flowStepName;
	}

	public void setFlowStepName(String flowStepName) {
		this.flowStepName = flowStepName;
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

	public Date getApproverTime() {
		return approverTime;
	}

	public void setApproverTime(Date approverTime) {
		this.approverTime = approverTime;
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

	public String getPassRecordFileId() {
		return passRecordFileId;
	}

	public void setPassRecordFileId(String passRecordFileId) {
		this.passRecordFileId = passRecordFileId;
	}

	public Integer getFlowResult() {
		return flowResult;
	}

	public void setFlowResult(Integer flowResult) {
		this.flowResult = flowResult;
	}
    
}
