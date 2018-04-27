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
  * @ClassName: BusiContractTendersContractDetailApproveFlowInfo
  * @Description: 标段合同清单(复合清单)审批信息表
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-11-06 10:55:23
  * @version: 1.0
  */
@Entity
@Table(name = "busi_contract_tenders_contract_detail_approve_flow_info")
public class BusiContractTendersContractDetailApproveFlowInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** @Fields approveFlowInfoId : ID主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name= "APPROVE_FLOW_INFO_ID", nullable = false)
    private Long id;
    
    
	/** @Fields orgId : 机构ID,标段信息 */
	@Column(name= "ORG_ID", nullable = false)
    private Long orgId;
    
    
	/** @Fields detailApplyTime : 清单申请时间 */
	@Column(name= "DETAIL_APPLY_TIME", nullable = false)
    private Date detailApplyTime;
    
    
	/** @Fields detailApplyUserId : 清单申请人ID */
	@Column(name= "DETAIL_APPLY_USER_ID", nullable = false)
    private Long detailApplyUserId;
    
    
	/** @Fields detailApplyUserName : 清单申请人名称*/ 
	@Column(name= "DETAIL_APPLY_USER_NAME", nullable = false, length=100 )
    private String detailApplyUserName;
    
    
	/** @Fields detailApprovalStatus : 清单审批状态*/ 
	@Column(name= "DETAIL_APPROVAL_STATUS", nullable = false, length=30 )
    private String detailApprovalStatus;
    
    
	/** @Fields detailApprovalTime : 清单审批完成时间 */
	@Column(name= "DETAIL_APPROVAL_TIME", nullable = false)
    private Date detailApprovalTime;
    
    
	/** @Fields detailApprovalUserId : 清单审批人ID */
	@Column(name= "DETAIL_APPROVAL_USER_ID", nullable = false)
    private Long detailApprovalUserId;
    
    
	/** @Fields detailApprovalUserName : 清单审批人名称*/ 
	@Column(name= "DETAIL_APPROVAL_USER_NAME", nullable = false, length=100 )
    private String detailApprovalUserName;
    
    
	/** @Fields detailFlowInstanceId : 清单流程实例ID */
	@Column(name= "DETAIL_FLOW_INSTANCE_ID", nullable = false)
    private Long detailFlowInstanceId;
    
    
	/** @Fields detailFlowResult : 清单审批结果*/ 
	@Column(name= "DETAIL_FLOW_RESULT", nullable = false, length=30 )
    private String detailFlowResult;
    
    
	/** @Fields detailFlowMessage : 清单审批意见*/ 
	@Column(name= "DETAIL_FLOW_MESSAGE", nullable = false, length=100 )
    private String detailFlowMessage;
    
    
	/** @Fields checkApplyTime : 复核申请时间 */
	@Column(name= "CHECK_APPLY_TIME", nullable = false)
    private Date checkApplyTime;
    
    
	/** @Fields checkApplyUserId : 复核申请人ID */
	@Column(name= "CHECK_APPLY_USER_ID", nullable = false)
    private Long checkApplyUserId;
    
    
	/** @Fields checkApplyUserName : 复核申请人名称*/ 
	@Column(name= "CHECK_APPLY_USER_NAME", nullable = false, length=100 )
    private String checkApplyUserName;
    
    
	/** @Fields checkApprovalStatus : 复核审批状态*/ 
	@Column(name= "CHECK_APPROVAL_STATUS", nullable = false, length=30 )
    private String checkApprovalStatus;
    
    
	/** @Fields checkApprovalTime : 复核审批完成时间 */
	@Column(name= "CHECK_APPROVAL_TIME", nullable = false)
    private Date checkApprovalTime;
    
    
	/** @Fields checkApprovalUserId : 复核审批人ID */
	@Column(name= "CHECK_APPROVAL_USER_ID", nullable = false)
    private Long checkApprovalUserId;
    
    
	/** @Fields checkApprovalUserName : 复核审批人名称*/ 
	@Column(name= "CHECK_APPROVAL_USER_NAME", nullable = false, length=100 )
    private String checkApprovalUserName;
    
    
	/** @Fields checkFlowInstanceId : 复核流程实例ID */
	@Column(name= "CHECK_FLOW_INSTANCE_ID", nullable = false)
    private Long checkFlowInstanceId;
    
    
	/** @Fields checkFlowResult : 复核审批结果*/ 
	@Column(name= "CHECK_FLOW_RESULT", nullable = false, length=30 )
    private String checkFlowResult;
    
    
	/** @Fields checkFlowMessage : 复核审批意见*/ 
	@Column(name= "CHECK_FLOW_MESSAGE", nullable = false, length=100 )
    private String checkFlowMessage;
    
    
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
    
    
      
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}
	
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
      
    public Long getOrgId(){
        return this.orgId;
    }
      
    public void setDetailApplyTime(Date detailApplyTime){
        this.detailApplyTime = detailApplyTime;
    }
      
    public Date getDetailApplyTime(){
        return this.detailApplyTime;
    }
      
    public void setDetailApplyUserId(Long detailApplyUserId){
        this.detailApplyUserId = detailApplyUserId;
    }
      
    public Long getDetailApplyUserId(){
        return this.detailApplyUserId;
    }
      
    public void setDetailApplyUserName(String detailApplyUserName){
        this.detailApplyUserName = detailApplyUserName;
    }
      
    public String getDetailApplyUserName(){
        return this.detailApplyUserName;
    }
      
    public void setDetailApprovalStatus(String detailApprovalStatus){
        this.detailApprovalStatus = detailApprovalStatus;
    }
      
    public String getDetailApprovalStatus(){
        return this.detailApprovalStatus;
    }
      
    public void setDetailApprovalTime(Date detailApprovalTime){
        this.detailApprovalTime = detailApprovalTime;
    }
      
    public Date getDetailApprovalTime(){
        return this.detailApprovalTime;
    }
      
    public void setDetailApprovalUserId(Long detailApprovalUserId){
        this.detailApprovalUserId = detailApprovalUserId;
    }
      
    public Long getDetailApprovalUserId(){
        return this.detailApprovalUserId;
    }
      
    public void setDetailApprovalUserName(String detailApprovalUserName){
        this.detailApprovalUserName = detailApprovalUserName;
    }
      
    public String getDetailApprovalUserName(){
        return this.detailApprovalUserName;
    }
      
    public void setDetailFlowInstanceId(Long detailFlowInstanceId){
        this.detailFlowInstanceId = detailFlowInstanceId;
    }
      
    public Long getDetailFlowInstanceId(){
        return this.detailFlowInstanceId;
    }
      
    public void setDetailFlowResult(String detailFlowResult){
        this.detailFlowResult = detailFlowResult;
    }
      
    public String getDetailFlowResult(){
        return this.detailFlowResult;
    }
      
    public void setDetailFlowMessage(String detailFlowMessage){
        this.detailFlowMessage = detailFlowMessage;
    }
      
    public String getDetailFlowMessage(){
        return this.detailFlowMessage;
    }
      
    public void setCheckApplyTime(Date checkApplyTime){
        this.checkApplyTime = checkApplyTime;
    }
      
    public Date getCheckApplyTime(){
        return this.checkApplyTime;
    }
      
    public void setCheckApplyUserId(Long checkApplyUserId){
        this.checkApplyUserId = checkApplyUserId;
    }
      
    public Long getCheckApplyUserId(){
        return this.checkApplyUserId;
    }
      
    public void setCheckApplyUserName(String checkApplyUserName){
        this.checkApplyUserName = checkApplyUserName;
    }
      
    public String getCheckApplyUserName(){
        return this.checkApplyUserName;
    }
      
    public void setCheckApprovalStatus(String checkApprovalStatus){
        this.checkApprovalStatus = checkApprovalStatus;
    }
      
    public String getCheckApprovalStatus(){
        return this.checkApprovalStatus;
    }
      
    public void setCheckApprovalTime(Date checkApprovalTime){
        this.checkApprovalTime = checkApprovalTime;
    }
      
    public Date getCheckApprovalTime(){
        return this.checkApprovalTime;
    }
      
    public void setCheckApprovalUserId(Long checkApprovalUserId){
        this.checkApprovalUserId = checkApprovalUserId;
    }
      
    public Long getCheckApprovalUserId(){
        return this.checkApprovalUserId;
    }
      
    public void setCheckApprovalUserName(String checkApprovalUserName){
        this.checkApprovalUserName = checkApprovalUserName;
    }
      
    public String getCheckApprovalUserName(){
        return this.checkApprovalUserName;
    }
      
    public void setCheckFlowInstanceId(Long checkFlowInstanceId){
        this.checkFlowInstanceId = checkFlowInstanceId;
    }
      
    public Long getCheckFlowInstanceId(){
        return this.checkFlowInstanceId;
    }
      
    public void setCheckFlowResult(String checkFlowResult){
        this.checkFlowResult = checkFlowResult;
    }
      
    public String getCheckFlowResult(){
        return this.checkFlowResult;
    }
      
    public void setCheckFlowMessage(String checkFlowMessage){
        this.checkFlowMessage = checkFlowMessage;
    }
      
    public String getCheckFlowMessage(){
        return this.checkFlowMessage;
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
