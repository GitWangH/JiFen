package com.huatek.busi.model.quality;

import java.util.Date;

import javax.persistence.CascadeType;
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
  * 月报管理实体类.
  * @ClassName: BusiQualityMonthlyReportManagement
  * @Description: 月报管理实体类
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:52
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_quality_monthly_report_management")
public class BusiQualityMonthlyReportManagement extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "MONTHLY_REPORT_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields orgId : 标段名称(机构ID，根据机构ID查询名称) */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "ORG_ID")
	private BusiFwOrg org;
    
	/** @Fields writeReportTime : 填报时间 */
	@Column(name= "WRITE_REPORT_TIME", nullable = false)
    private Date writeReportTime;
    
    
	/** @Fields writeReportUserId : 填报人ID */
	@Column(name= "WRITE_REPORT_USER_ID", nullable = false)
    private Long writeReportUserId;
    
    
	/** @Fields writeReportUserName : 填报人名称*/ 
	@Column(name= "WRITE_REPORT_USER_NAME", nullable = false, length=100 )
    private String writeReportUserName;
    
    
	/** @Fields monthlyReportName : 文件名称*/ 
	@Column(name= "MONTHLY_REPORT_NAME", nullable = false, length=100 )
    private String monthlyReportName;
    
    
	/** @Fields monthlyReportCode : 文件编号*/ 
	@Column(name= "MONTHLY_REPORT_CODE", nullable = false, length=50 )
    private String monthlyReportCode;
    
    
	/** @Fields monthlyReportType : 月报类型(字典表：施工月报等)*/ 
	@Column(name= "MONTHLY_REPORT_TYPE", nullable = false, length=30 )
    private String monthlyReportType;
    
    
	/** @Fields content : 正文*/ 
	@Column(name= "CONTENT", nullable = false, length=65535 )
    private String content;
    
    
	/** @Fields attachmentFile : 附件*/ 
	@Column(name= "ATTACHMENT_FILE", nullable = false, length=32 )
    private String attachmentFile;
    
    
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
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

	public void setWriteReportTime(Date writeReportTime){
        this.writeReportTime = writeReportTime;
    }
      
    public Date getWriteReportTime(){
        return this.writeReportTime;
    }
      
    public void setWriteReportUserId(Long writeReportUserId){
        this.writeReportUserId = writeReportUserId;
    }
      
    public Long getWriteReportUserId(){
        return this.writeReportUserId;
    }
      
    public void setWriteReportUserName(String writeReportUserName){
        this.writeReportUserName = writeReportUserName;
    }
      
    public String getWriteReportUserName(){
        return this.writeReportUserName;
    }
      
    public void setMonthlyReportName(String monthlyReportName){
        this.monthlyReportName = monthlyReportName;
    }
      
    public String getMonthlyReportName(){
        return this.monthlyReportName;
    }
      
    public void setMonthlyReportCode(String monthlyReportCode){
        this.monthlyReportCode = monthlyReportCode;
    }
      
    public String getMonthlyReportCode(){
        return this.monthlyReportCode;
    }
      
    public void setMonthlyReportType(String monthlyReportType){
        this.monthlyReportType = monthlyReportType;
    }
      
    public String getMonthlyReportType(){
        return this.monthlyReportType;
    }
      
    public void setContent(String content){
        this.content = content;
    }
      
    public String getContent(){
        return this.content;
    }
      
    public void setAttachmentFile(String attachmentFile){
        this.attachmentFile = attachmentFile;
    }
      
    public String getAttachmentFile(){
        return this.attachmentFile;
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
      

}
