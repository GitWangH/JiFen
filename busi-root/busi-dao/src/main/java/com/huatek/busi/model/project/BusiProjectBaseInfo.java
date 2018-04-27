package com.huatek.busi.model.project;

import javax.persistence.*;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 项目基本信息
  * @ClassName: BusiProjectBaseInfo
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-27 14:03:17
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_project_base_info")
public class BusiProjectBaseInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "BUSI_PROJECT_BASE_INFO_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields projectFullName : 项目全称*/ 
	@Column(name= "PROJECT_FULL_NAME", nullable = true, length=100 )
    private String projectFullName;
    
    
	/** @Fields projectAbbreviation : 项目简称*/ 
	@Column(name= "PROJECT_ABBREVIATION", nullable = true, length=100 )
    private String projectAbbreviation;
    
    
	/** @Fields projectNumber : 项目编号*/ 
	@Column(name= "PROJECT_NUMBER", nullable = true, length=100 )
    private String projectNumber;
    
    
	/** @Fields projectSponsor : 立项人*/ 
	@Column(name= "PROJECT_SPONSOR", nullable = false, length=100 )
    private String projectSponsor;
    
    
	/** @Fields projectStatus : 项目状态（数据字典）*/ 
	@Column(name= "PROJECT_STATUS", nullable = true, length=30 )
    private String projectStatus;
    
    
	/** @Fields buildCompany : 建设单位*/ 
	//@Formula("(select a.name From BusiFwOrg a where a.id = orgId)")
	@Transient
    private String buildCompany;
    
    
	/** @Fields projectOfficeAddress : 项目办地址*/ 
	@Column(name= "PROJECT_OFFICE_ADDRESS", nullable = true, length=255 )
    private String projectOfficeAddress;
    
    
	/** @Fields projectDate : 立项日期 */
	@Column(name= "PROJECT_DATE", nullable = true)
    private Date projectDate;
    
    
	/** @Fields contactInformation : 联系方式*/ 
	@Column(name= "CONTACT_INFORMATION", nullable = false, length=100 )
    private String contactInformation;
    
    
     /** @Fields projectBudgetEstimate : 项目概算（万元）*/ 
    @Column(name= "PROJECT_BUDGET_ESTIMATE", nullable = false , precision=18 , scale=4)
    private BigDecimal projectBudgetEstimate;
    
    
     /** @Fields finalAccountsOfCompletedProject : 竣工决算（万元）*/ 
    @Column(name= "FINAL_ACCOUNTS_OF_COMPLETED_PROJECT", nullable = false , precision=18 , scale=4)
    private BigDecimal finalAccountsOfCompletedProject;
    
    
	/** @Fields commencementDate : 开工日期 */
	@Column(name= "COMMENCEMENT_DATE", nullable = true)
    private Date commencementDate;
    
    
	/** @Fields completionDate : 竣工日期 */
	@Column(name= "COMPLETION_DATE", nullable = true)
    private Date completionDate;
    
    
	/** @Fields projectDescription : 项目描述*/ 
	@Column(name= "PROJECT_DESCRIPTION", nullable = false, length=65535 )
    private String projectDescription;
    
    
	/** @Fields remark : 备注*/ 
	@Column(name= "REMARK", nullable = false, length=65535 )
    private String remark;
    
    
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
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
	
	/** @Fields orgId : 机构ID */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORG_ID")
	//@Column(name= "ORG_ID", nullable = true)
    private BusiFwOrg orgId;
    
    @Transient
    private Long orgIdForShow;
    
	
	
	public BusiProjectBaseInfo(){}
	
	public BusiProjectBaseInfo(Long id, String projectFullName,
			String projectAbbreviation, String buildCompany,
			String projectStatus, BigDecimal projectBudgetEstimate,
			Date commencementDate, Date completionDate, Date createTime, Long orgId) {
		this.id = id;
		this.projectFullName = projectFullName;
		this.projectAbbreviation = projectAbbreviation;
		this.buildCompany = buildCompany;
		this.projectStatus = projectStatus;
		this.projectBudgetEstimate = projectBudgetEstimate;
		this.commencementDate = commencementDate;
		this.completionDate = completionDate;
		this.createTime = createTime;
		this.orgIdForShow = orgId;
	}
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setProjectFullName(String projectFullName){
        this.projectFullName = projectFullName;
    }
      
    public String getProjectFullName(){
        return this.projectFullName;
    }
      
    public void setProjectAbbreviation(String projectAbbreviation){
        this.projectAbbreviation = projectAbbreviation;
    }
      
    public String getProjectAbbreviation(){
        return this.projectAbbreviation;
    }
      
    public void setProjectNumber(String projectNumber){
        this.projectNumber = projectNumber;
    }
      
    public String getProjectNumber(){
        return this.projectNumber;
    }
      
    public void setProjectSponsor(String projectSponsor){
        this.projectSponsor = projectSponsor;
    }
      
    public String getProjectSponsor(){
        return this.projectSponsor;
    }
      
    public void setProjectStatus(String projectStatus){
        this.projectStatus = projectStatus;
    }
      
    public String getProjectStatus(){
        return this.projectStatus;
    }
    
    @Transient
    public void setBuildCompany(String buildCompany){
        this.buildCompany = buildCompany;
    }
    
    @Transient
    public String getBuildCompany(){
        return this.buildCompany;
    }
      
    public void setProjectOfficeAddress(String projectOfficeAddress){
        this.projectOfficeAddress = projectOfficeAddress;
    }
      
    public String getProjectOfficeAddress(){
        return this.projectOfficeAddress;
    }
      
    public void setProjectDate(Date projectDate){
        this.projectDate = projectDate;
    }
      
    public Date getProjectDate(){
        return this.projectDate;
    }
      
    public void setContactInformation(String contactInformation){
        this.contactInformation = contactInformation;
    }
      
    public String getContactInformation(){
        return this.contactInformation;
    }
      
    public void setProjectBudgetEstimate(BigDecimal projectBudgetEstimate){
        this.projectBudgetEstimate = projectBudgetEstimate;
    }
      
    public BigDecimal getProjectBudgetEstimate(){
        return this.projectBudgetEstimate;
    }
      
    public void setFinalAccountsOfCompletedProject(BigDecimal finalAccountsOfCompletedProject){
        this.finalAccountsOfCompletedProject = finalAccountsOfCompletedProject;
    }
      
    public BigDecimal getFinalAccountsOfCompletedProject(){
        return this.finalAccountsOfCompletedProject;
    }
      
    public void setCommencementDate(Date commencementDate){
        this.commencementDate = commencementDate;
    }
      
    public Date getCommencementDate(){
        return this.commencementDate;
    }
      
    public void setCompletionDate(Date completionDate){
        this.completionDate = completionDate;
    }
      
    public Date getCompletionDate(){
        return this.completionDate;
    }
      
    public void setProjectDescription(String projectDescription){
        this.projectDescription = projectDescription;
    }
      
    public String getProjectDescription(){
        return this.projectDescription;
    }
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
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

	public BusiFwOrg getOrgId() {
		return orgId;
	}

	public void setOrgId(BusiFwOrg orgId) {
		this.orgId = orgId;
	}
	
	@Transient
	public Long getOrgIdForShow() {
		return orgIdForShow;
	}
	@Transient
	public void setOrgIdForShow(Long orgIdForShow) {
		this.orgIdForShow = orgIdForShow;
	}
}
