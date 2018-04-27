package com.huatek.busi.model.quality;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 整改单管理实体.
  * @ClassName: BusiQualityRectification
  * @Description: 整改单管理实体
  * @author: rocky_wei
  * @Email : rocky_wei@huatek.com
  * @date: 2017-10-25 16:28:12
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_quality_rectification")
public class BusiQualityRectification extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "RECTIFICATION_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields dataSource : 被整改处理的类型，例：水泥拌合站，水稳拌合站等.(数据字典)*/ 
	@Column(name= "DATA_SOURCE", nullable = true, length=30 )
    private String dataSource;
    
    
	/** @Fields applyTime : 申请时间 */
	@Column(name= "APPLY_TIME", nullable = false)
    private Date applyTime;
    
    
	/** @Fields applyUserId : 申请人ID */
	@Column(name= "APPLY_USER_ID", nullable = false)
    private Long applyUserId;
    
    
	/** @Fields applyUserName : 申请人名称*/ 
	@Column(name= "APPLY_USER_NAME", nullable = false, length=100 )
    private String applyUserName;
    
    
	/** @Fields approvalStatus : 审批状态*/ 
	@Column(name= "APPROVAL_STATUS", nullable = false, length=30 )
    private String approvalStatus;
    
    
	/** @Fields approvalTime : 审批完成时间 */
	@Column(name= "APPROVAL_TIME", nullable = false)
    private Date approvalTime;
    
    
	/** @Fields approvalUserId : 审批人ID */
	@Column(name= "APPROVAL_USER_ID", nullable = false)
    private Long approvalUserId;
    
    
	/** @Fields approvalUserName : 审批人名称*/ 
	@Column(name= "APPROVAL_USER_NAME", nullable = false, length=100 )
    private String approvalUserName;
    
    
	/** @Fields flowInstanceId : 流程实例ID */
	@Column(name= "FLOW_INSTANCE_ID", nullable = false)
    private Long flowInstanceId;
    
    
	/** @Fields flowResult : 审批结果*/ 
	@Column(name= "FLOW_RESULT", nullable = false, length=30 )
    private String flowResult;
    
    
	/** @Fields flowMessage : 审批意见*/ 
	@Column(name= "FLOW_MESSAGE", nullable = false, length=100 )
    private String flowMessage;
    
    
	/** @Fields rectificationCheckTime : 整改中的检查时间 */
	@Column(name= "RECTIFICATION_CHECK_TIME", nullable = false)
    private Date rectificationCheckTime;
    
    
	/** @Fields rectificationCheckContent : 整改中的检查内容*/ 
	@Column(name= "RECTIFICATION_CHECK_CONTENT", nullable = false, length=65535 )
    private String rectificationCheckContent;
    
    
	/** @Fields rectificationUrgency : 整改中的紧急程度（数据字典）*/ 
	@Column(name= "RECTIFICATION_URGENCY", nullable = false, length=30 )
    private String rectificationUrgency;
    
    
	/** @Fields rectificationPeriod : 整改期限*/ 
	@Column(name= "RECTIFICATION_PERIOD", nullable = false, length=10 )
    private String rectificationPeriod;
    
    
	/** @Fields rectificationPersonLiable : 相关责任人*/ 
	@Column(name= "RECTIFICATION_PERSON_LIABLE", nullable = false, length=100 )
    private String rectificationPersonLiable;
    
    
	/** @Fields rectificationPerson : 检查人员*/ 
	@Column(name= "RECTIFICATION_PERSON", nullable = false, length=100 )
    private String rectificationPerson;
    
    
	/** @Fields rectificationExistingProblems : 整改中存在问题*/ 
	@Column(name= "RECTIFICATION_EXISTING_PROBLEMS", nullable = false, length=65535 )
    private String rectificationExistingProblems;
    
    
	/** @Fields rectificationRequirements : 整改要求*/ 
	@Column(name= "RECTIFICATION_REQUIREMENTS", nullable = false, length=65535 )
    private String rectificationRequirements;
    
    
	/** @Fields rectificationPunishmentSuggestion : 处罚建议*/ 
	@Column(name= "RECTIFICATION_PUNISHMENT_SUGGESTION", nullable = false, length=65535 )
    private String rectificationPunishmentSuggestion;
    
    
	/** @Fields orgId : 组织机构ID */
	@ManyToOne
	@JoinColumn(name= "ORG_ID", nullable = false)
    private BusiFwOrg org;
    
    
	/** @Fields creater : 创建人ID */
	@Column(name= "CREATER", nullable = false)
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
	@Column(name= "CREATER_NAME", nullable = false, length=100 )
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
	@Column(name= "CREATE_TIME", nullable = false)
    private Date createTime;
    
    
	/** @Fields modifer : 修改人ID */
	@Column(name= "MODIFER", nullable = false)
    private Long modifer;
    
    
	/** @Fields modifierName : 修改人姓名*/ 
	@Column(name= "MODIFIER_NAME", nullable = false, length=100 )
    private String modifierName;
    
    
	/** @Fields modifyTime : 修改时间 */
	@Column(name= "MODIFY_TIME", nullable = false)
    private Date modifyTime;
    
    
	/** @Fields tenantId : 租户id */
	@Column(name= "TENANT_ID", nullable = true)
    private Long tenantId;
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
    
	/** @Fields rectificationCode : 整改编码 */
	@Column(name= "RECTIFICATION_CODE", nullable = false)
    private String rectificationCode;
      
	/** @Fields fileId : 上传文件编码 */
	@Column(name= "FILE_ID", nullable = false)
	private String fileId;
	
	/** @Fields checkNo : 检查编号 */
	@Column(name= "CHECK_NO", nullable = false)
	private String checkNo;
	
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setDataSource(String dataSource){
        this.dataSource = dataSource;
    }
      
    public String getDataSource(){
        return this.dataSource;
    }
      
    public void setApplyTime(Date applyTime){
        this.applyTime = applyTime;
    }
      
    public Date getApplyTime(){
        return this.applyTime;
    }
      
    public void setApplyUserId(Long applyUserId){
        this.applyUserId = applyUserId;
    }
      
    public Long getApplyUserId(){
        return this.applyUserId;
    }
      
    public void setApplyUserName(String applyUserName){
        this.applyUserName = applyUserName;
    }
      
    public String getApplyUserName(){
        return this.applyUserName;
    }
      
    public void setApprovalStatus(String approvalStatus){
        this.approvalStatus = approvalStatus;
    }
      
    public String getApprovalStatus(){
        return this.approvalStatus;
    }
      
    public void setApprovalTime(Date approvalTime){
        this.approvalTime = approvalTime;
    }
      
    public Date getApprovalTime(){
        return this.approvalTime;
    }
      
    public void setApprovalUserId(Long approvalUserId){
        this.approvalUserId = approvalUserId;
    }
      
    public Long getApprovalUserId(){
        return this.approvalUserId;
    }
      
    public void setApprovalUserName(String approvalUserName){
        this.approvalUserName = approvalUserName;
    }
      
    public String getApprovalUserName(){
        return this.approvalUserName;
    }
      
    public void setFlowInstanceId(Long flowInstanceId){
        this.flowInstanceId = flowInstanceId;
    }
      
    public Long getFlowInstanceId(){
        return this.flowInstanceId;
    }
      
    public void setFlowResult(String flowResult){
        this.flowResult = flowResult;
    }
      
    public String getFlowResult(){
        return this.flowResult;
    }
      
    public void setFlowMessage(String flowMessage){
        this.flowMessage = flowMessage;
    }
      
    public String getFlowMessage(){
        return this.flowMessage;
    }
      
    public void setRectificationCheckTime(Date rectificationCheckTime){
        this.rectificationCheckTime = rectificationCheckTime;
    }
      
    public Date getRectificationCheckTime(){
        return this.rectificationCheckTime;
    }
      
    public void setRectificationCheckContent(String rectificationCheckContent){
        this.rectificationCheckContent = rectificationCheckContent;
    }
      
    public String getRectificationCheckContent(){
        return this.rectificationCheckContent;
    }
      
    public void setRectificationUrgency(String rectificationUrgency){
        this.rectificationUrgency = rectificationUrgency;
    }
      
    public String getRectificationUrgency(){
        return this.rectificationUrgency;
    }
      
    public void setRectificationPeriod(String rectificationPeriod){
        this.rectificationPeriod = rectificationPeriod;
    }
      
    public String getRectificationPeriod(){
        return this.rectificationPeriod;
    }
      
    public void setRectificationPersonLiable(String rectificationPersonLiable){
        this.rectificationPersonLiable = rectificationPersonLiable;
    }
      
    public String getRectificationPersonLiable(){
        return this.rectificationPersonLiable;
    }
      
    public void setRectificationPerson(String rectificationPerson){
        this.rectificationPerson = rectificationPerson;
    }
      
    public String getRectificationPerson(){
        return this.rectificationPerson;
    }
      
    public void setRectificationExistingProblems(String rectificationExistingProblems){
        this.rectificationExistingProblems = rectificationExistingProblems;
    }
      
    public String getRectificationExistingProblems(){
        return this.rectificationExistingProblems;
    }
      
    public void setRectificationRequirements(String rectificationRequirements){
        this.rectificationRequirements = rectificationRequirements;
    }
      
    public String getRectificationRequirements(){
        return this.rectificationRequirements;
    }
      
    public void setRectificationPunishmentSuggestion(String rectificationPunishmentSuggestion){
        this.rectificationPunishmentSuggestion = rectificationPunishmentSuggestion;
    }
      
    public String getRectificationPunishmentSuggestion(){
        return this.rectificationPunishmentSuggestion;
    }
      
    public void setCreater(Long creater){
        this.creater = creater;
    }
      
    public Long getCreater(){
        return this.creater;
    }
      
    public void setCreaterName(String createrName){
        this.createrName = createrName;
    }
      
    public String getCreaterName(){
        return this.createrName;
    }
      
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
      
    public Date getCreateTime(){
        return this.createTime;
    }
      
    public void setModifer(Long modifer){
        this.modifer = modifer;
    }
      
    public Long getModifer(){
        return this.modifer;
    }
      
    public void setModifierName(String modifierName){
        this.modifierName = modifierName;
    }
      
    public String getModifierName(){
        return this.modifierName;
    }
      
    public void setModifyTime(Date modifyTime){
        this.modifyTime = modifyTime;
    }
      
    public Date getModifyTime(){
        return this.modifyTime;
    }
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }
      
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
      
    public Integer getIsDelete(){
        return this.isDelete;
    }

	public String getRectificationCode() {
		return rectificationCode;
	}

	public void setRectificationCode(String rectificationCode) {
		this.rectificationCode = rectificationCode;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}
	
}
