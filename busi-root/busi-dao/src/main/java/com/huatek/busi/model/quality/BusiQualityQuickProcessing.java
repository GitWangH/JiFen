package com.huatek.busi.model.quality;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 快捷处理实体类.
  * @ClassName: BusiQualityQuickProcessing
  * @Description: 快捷处理实体
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-10-26 09:36:42
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_quality_quick_processing")
public class BusiQualityQuickProcessing extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "QUICK_PROCESSING_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields dataSource : 快速处理的类型，例：水泥拌合站，水稳拌合站等.(数据字典)*/ 
	@Column(name= "DATA_SOURCE", nullable = true, length=30 )
    private String dataSource;
    
    
	/** @Fields orgId : 组织机构ID */
	@Column(name= "ORG_ID", nullable = false)
    private Long orgId;
    
    
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
    
    
	/** @Fields quickReason : 快速处理中的原因（数据字典）*/ 
	@Column(name= "QUICK_REASON", nullable = false, length=30 )
    private String quickReason;
    
    
	/** @Fields quickExplain : 快速处理中的说明*/ 
	@Column(name= "QUICK_EXPLAIN", nullable = false, length=65535 )
    private String quickExplain;
    
    
	/** @Fields quickProcessingTime : 快速处理中的处理时间 */
	@Column(name= "QUICK_PROCESSING_TIME", nullable = false)
    private Date quickProcessingTime;
    
    
	/** @Fields quickUserName : 快速处理人*/ 
	@Column(name= "QUICK_USER_NAME", nullable = false, length=100 )
    private String quickUserName;
    
    
	/** @Fields quickUserId : 快速处理人ID
             */
	@Column(name= "QUICK_USER_ID", nullable = false)
    private Long quickUserId;
    
    
	/** @Fields enclosure : 附件*/ 
	@Column(name= "ENCLOSURE", nullable = false, length=32 )
    private String enclosure;
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
      
	/** @Fields quickProcessCode : 快捷处理编码 */
	@Column(name= "QUICK_PROCESS_CODE", nullable = false)
	private String quickProcessCode;
	
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
      
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
      
    public Long getOrgId(){
        return this.orgId;
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
      
    public void setQuickReason(String quickReason){
        this.quickReason = quickReason;
    }
      
    public String getQuickReason(){
        return this.quickReason;
    }
      
    public void setQuickExplain(String quickExplain){
        this.quickExplain = quickExplain;
    }
      
    public String getQuickExplain(){
        return this.quickExplain;
    }
      
    public void setQuickProcessingTime(Date quickProcessingTime){
        this.quickProcessingTime = quickProcessingTime;
    }
      
    public Date getQuickProcessingTime(){
        return this.quickProcessingTime;
    }
      
    public void setQuickUserName(String quickUserName){
        this.quickUserName = quickUserName;
    }
      
    public String getQuickUserName(){
        return this.quickUserName;
    }
      
    public void setQuickUserId(Long quickUserId){
        this.quickUserId = quickUserId;
    }
      
    public Long getQuickUserId(){
        return this.quickUserId;
    }
      
    public void setEnclosure(String enclosure){
        this.enclosure = enclosure;
    }
      
    public String getEnclosure(){
        return this.enclosure;
    }
      
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
      
    public Integer getIsDelete(){
        return this.isDelete;
    }

	public String getQuickProcessCode() {
		return quickProcessCode;
	}

	public void setQuickProcessCode(String quickProcessCode) {
		this.quickProcessCode = quickProcessCode;
	}
      
}
