package com.huatek.busi.model.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiContractContractChange
  * @Description: 合同变更
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:12:22
  * @version: 1.0
  */
@Entity
@Table(name = "busi_contract_contract_change")
public class BusiContractContractChange extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "CONTRACT_CHANGE_ID", nullable = true )
 	private Long id;
 
    
    /** @Fields busiContractTendersContract : 合同主键ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "TENDERS_CONTRACT_ID")
    private BusiContractTendersContract busiContractTendersContract;
    
    
	/** @Fields orgId : 组织机构ID */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ORG_ID")
	private BusiFwOrg busiFwOrg;// 关联Org对象获取机构信息
    
    
	/** @Fields changeLevel : 变更等级(字典表：一般、重大)*/ 
	@Column(name= "CHANGE_LEVEL", nullable = false, length=30 )
    private String changeLevel;
    
    
	/** @Fields changeData : 变更日期 */
	@Column(name= "CHANGE_DATA", nullable = false)
    private Date changeData;
    
    
	/** @Fields changeType : 变更类型(字典表：新增、取消、纠错、位置变更)*/ 
	@Column(name= "CHANGE_TYPE", nullable = false, length=30 )
    private String changeType;
    
    
	/** @Fields changeProjectName : 变更项目*/ 
	@Column(name= "CHANGE_PROJECT_NAME", nullable = false, length=100 )
    private String changeProjectName;
    
    
	/** @Fields changeOrderNo : 变更令号*/ 
	@Column(name= "CHANGE_ORDER_NO", nullable = false, length=50 )
    private String changeOrderNo;
    
    
	/** @Fields contractDrawings : 合同图纸*/ 
	@Column(name= "CONTRACT_DRAWINGS", nullable = false, length=100 )
    private String contractDrawings;
    
    
	/** @Fields contractChangedDrawings : 变更后图纸*/ 
	@Column(name= "CONTRACT_CHANGED_DRAWINGS", nullable = false, length=100 )
    private String contractChangedDrawings;
    
    
	/** @Fields stakeNo : 桩号*/ 
	@Column(name= "STAKE_NO", nullable = false, length=100 )
    private String stakeNo;
    
    
	/** @Fields delaySchedule : 变更延长工期*/ 
	@Column(name= "DELAY_SCHEDULE", nullable = false, length=100 )
    private String delaySchedule;
    
    
	/** @Fields replyChangeStatus : 未批复变更（是否批复：未批复、已批复）*/ 
	@Column(name= "REPLY_CHANGE_STATUS", nullable = false, length=30 )
    private String replyChangeStatus;
    
    
     /** @Fields changeMoney : 变更金额*/ 
    @Column(name= "CHANGE_MONEY", nullable = false , precision=18 , scale=4)
    private BigDecimal changeMoney;
    
    
	/** @Fields changeContent : 变更内容*/ 
	@Column(name= "CHANGE_CONTENT", nullable = false, length=255 )
    private String changeContent;
    
    
	/** @Fields changeReason : 变更原因*/ 
	@Column(name= "CHANGE_REASON", nullable = false, length=255 )
    private String changeReason;
    
    
	/** @Fields changeFile : */ 
	@Column(name= "CHANGE_FILE", nullable = false, length=32 )
    private String changeFile;
    
    
	/** @Fields meetingSummaryFile : 会议纪要*/ 
	@Column(name= "MEETING_SUMMARY_FILE", nullable = false, length=32 )
    private String meetingSummaryFile;
    
    
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
    
    
	/** @Fields creater : 创建人id */
	@Column(name= "CREATER", nullable = false)
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
	@Column(name= "CREATER_NAME", nullable = false, length=100 )
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
	@Column(name= "CREATE_TIME", nullable = false)
    private Date createTime;
    
    
	/** @Fields modifer : 修改人id */
	@Column(name= "MODIFER", nullable = false)
    private Long modifer;
    
    
	/** @Fields modifierName : 修改人姓名*/ 
	@Column(name= "MODIFIER_NAME", nullable = false, length=100 )
    private String modifierName;
    
    
	/** @Fields modifyTime : 修改时间 */
	@Column(name= "MODIFY_TIME", nullable = false)
    private Date modifyTime;
    
    
	/** @Fields tenantId : 租户id */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
    
    
	/** @Fields busicontractcontractchangedetailSet :  */
	//mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy= "busiContractContractChange")
    private Set<BusiContractContractChangeDetail> busicontractcontractchangedetailSet;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setBusiContractTendersContract(BusiContractTendersContract busiContractTendersContract){
        this.busiContractTendersContract = busiContractTendersContract;
    }
      
    public BusiContractTendersContract getBusiContractTendersContract(){
        return this.busiContractTendersContract;
    }
      
    public BusiFwOrg getBusiFwOrg() {
		return busiFwOrg;
	}

	public void setBusiFwOrg(BusiFwOrg busiFwOrg) {
		this.busiFwOrg = busiFwOrg;
	}
      
    public void setChangeLevel(String changeLevel){
        this.changeLevel = changeLevel;
    }
      
    public String getChangeLevel(){
        return this.changeLevel;
    }
      
    public void setChangeData(Date changeData){
        this.changeData = changeData;
    }
      
    public Date getChangeData(){
        return this.changeData;
    }
      
    public void setChangeType(String changeType){
        this.changeType = changeType;
    }
      
    public String getChangeType(){
        return this.changeType;
    }
      
    public void setChangeProjectName(String changeProjectName){
        this.changeProjectName = changeProjectName;
    }
      
    public String getChangeProjectName(){
        return this.changeProjectName;
    }
      
    public void setChangeOrderNo(String changeOrderNo){
        this.changeOrderNo = changeOrderNo;
    }
      
    public String getChangeOrderNo(){
        return this.changeOrderNo;
    }
      
    public void setContractDrawings(String contractDrawings){
        this.contractDrawings = contractDrawings;
    }
      
    public String getContractDrawings(){
        return this.contractDrawings;
    }
      
    public void setContractChangedDrawings(String contractChangedDrawings){
        this.contractChangedDrawings = contractChangedDrawings;
    }
      
    public String getContractChangedDrawings(){
        return this.contractChangedDrawings;
    }
      
    public void setStakeNo(String stakeNo){
        this.stakeNo = stakeNo;
    }
      
    public String getStakeNo(){
        return this.stakeNo;
    }
      
    public void setDelaySchedule(String delaySchedule){
        this.delaySchedule = delaySchedule;
    }
      
    public String getDelaySchedule(){
        return this.delaySchedule;
    }
      
    public void setReplyChangeStatus(String replyChangeStatus){
        this.replyChangeStatus = replyChangeStatus;
    }
      
    public String getReplyChangeStatus(){
        return this.replyChangeStatus;
    }
      
    public void setChangeMoney(BigDecimal changeMoney){
        this.changeMoney = changeMoney;
    }
      
    public BigDecimal getChangeMoney(){
        return this.changeMoney;
    }
      
    public void setChangeContent(String changeContent){
        this.changeContent = changeContent;
    }
      
    public String getChangeContent(){
        return this.changeContent;
    }
      
    public void setChangeReason(String changeReason){
        this.changeReason = changeReason;
    }
      
    public String getChangeReason(){
        return this.changeReason;
    }
      
    public void setChangeFile(String changeFile){
        this.changeFile = changeFile;
    }
      
    public String getChangeFile(){
        return this.changeFile;
    }
      
    public void setMeetingSummaryFile(String meetingSummaryFile){
        this.meetingSummaryFile = meetingSummaryFile;
    }
      
    public String getMeetingSummaryFile(){
        return this.meetingSummaryFile;
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
      
    public void setBusicontractcontractchangedetailSet(Set<BusiContractContractChangeDetail> busicontractcontractchangedetailSet){
        this.busicontractcontractchangedetailSet = busicontractcontractchangedetailSet;
    }
      
    public Set<BusiContractContractChangeDetail> getBusicontractcontractchangedetailSet(){
        return this.busicontractcontractchangedetailSet;
    }
      

}
