package com.huatek.busi.model.contract;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiContractTendersBranchApproveFlowInfo
  * @Description: 分部分项审批
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-11-08 17:13:40
  * @version: 1.0
  */
@Entity
@Table(name = "busi_contract_tenders_branch_approve_flow_info")
public class BusiContractTendersBranchApproveFlowInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "APPROVE_FLOW_INFO_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields orgId : 机构ID,标段信息 */
	@Column(name= "ORG_ID", nullable = false)
    private Long orgId;
    
    
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
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
      
    public Long getOrgId(){
        return this.orgId;
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
      

}
