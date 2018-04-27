package com.huatek.busi.model.quality;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 整改单明细实体类.
  * @ClassName: BusiQualityRectificationDetail
  * @Description: 整改单明细实体类
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-10-25 18:00:11
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_quality_rectification_detail")
public class BusiQualityRectificationDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "RECTIFICATION_DETAIL_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields rectificationId : 外键ID*/ 
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "RECTIFICATION_ID")
    private BusiQualityRectification rectification;
    
    
	/** @Fields backupField1 : 预留1*/ 
	@Column(name= "BACKUP_FIELD_1", nullable = false, length=10 )
    private String backupField1;
    
    
	/** @Fields backupField2 : 预留2*/ 
	@Column(name= "BACKUP_FIELD_2", nullable = false, length=10 )
    private String backupField2;
    
    
	/** @Fields backupField3 : 预留3*/ 
	@Column(name= "BACKUP_FIELD_3", nullable = false, length=10 )
    private String backupField3;
    
    
	/** @Fields backupField4 : 预留4*/ 
	@Column(name= "BACKUP_FIELD_4", nullable = false, length=10 )
    private String backupField4;
    
    
	/** @Fields backupField5 : 预留5*/ 
	@Column(name= "BACKUP_FIELD_5", nullable = false, length=10 )
    private String backupField5;
    
    
	/** @Fields backupField6 : 预留6*/ 
	@Column(name= "BACKUP_FIELD_6", nullable = false, length=10 )
    private String backupField6;
    
    
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
    
	/** @Fields rectificationDescription : 整改描述 */
	@Column(name= "RECTIFICATION_DESCRIPTION", nullable = true)
    private String rectificationDescription;
    
    /** @Fields fileId : 附件编码 */
    @Column(name= "FILE_ID", nullable = true)
    private String detailFileId;
    
    /** @Fields detailType : 明细类型 */
    @Column(name= "DETAIL_TYPE", nullable = true)
    private Integer detailType;
    
    /** @Fields detailType : 复查结果 */
    @Column(name= "FLOW_RESULT", nullable = true)
    private String detailFlowResult;
    
    /** @Fields flowStep : 流程步骤 */
    @Column(name= "FLOW_STEP", nullable = true)
    private String flowStep;
    
    /** @Fields flowStepName :流程步骤名称*/
    @Column(name= "FLOW_STEP_NAME", nullable = true)
    private String flowStepName;
    
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
	public BusiQualityRectification getRectification() {
		return rectification;
	}

	public void setRectification(BusiQualityRectification rectification) {
		this.rectification = rectification;
	}

	public void setBackupField1(String backupField1){
        this.backupField1 = backupField1;
    }
      
    public String getBackupField1(){
        return this.backupField1;
    }
      
    public void setBackupField2(String backupField2){
        this.backupField2 = backupField2;
    }
      
    public String getBackupField2(){
        return this.backupField2;
    }
      
    public void setBackupField3(String backupField3){
        this.backupField3 = backupField3;
    }
      
    public String getBackupField3(){
        return this.backupField3;
    }
      
    public void setBackupField4(String backupField4){
        this.backupField4 = backupField4;
    }
      
    public String getBackupField4(){
        return this.backupField4;
    }
      
    public void setBackupField5(String backupField5){
        this.backupField5 = backupField5;
    }
      
    public String getBackupField5(){
        return this.backupField5;
    }
      
    public void setBackupField6(String backupField6){
        this.backupField6 = backupField6;
    }
      
    public String getBackupField6(){
        return this.backupField6;
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

	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}

	public String getDetailFlowResult() {
		return detailFlowResult;
	}

	public void setDetailFlowResult(String detailFlowResult) {
		this.detailFlowResult = detailFlowResult;
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
    
}
