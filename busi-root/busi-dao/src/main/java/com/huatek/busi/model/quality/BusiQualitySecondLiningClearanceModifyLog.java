package com.huatek.busi.model.quality;

import java.math.BigDecimal;
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
  * 代码自动生成model类.
  * @ClassName: BusiQualitySecondLiningClearanceModifyLog
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-30 18:47:44
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_quality_second_lining_clearance_modify_log")
public class BusiQualitySecondLiningClearanceModifyLog extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "SECOND_LINING_THICKNESS_SIZE_CHECK_MODIFY_LOG_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields secondLiningThicknessSizeCheckId : 主表ID */
	@ManyToOne
	@JoinColumn(name= "SECOND_LINING_THICKNESS_SIZE_CHECK_ID", nullable = false)
    private BusiQualitySecondLiningClearanceSectionSizeCheck secondLiningThicknessSizeCheckId;
    
    
	/** @Fields orgId : 标段名称(机构ID，根据机构ID查询名称) */
	@ManyToOne
	@JoinColumn(name= "ORG_ID", nullable = false)
    private BusiFwOrg org;
    
    
	/** @Fields tendersBranchId : 分部分项(分部分项表ID) */
	@Column(name= "TENDERS_BRANCH_ID", nullable = false)
    private Long tendersBranchId;
    
    
	/** @Fields tendersBranchName : 分部分项名称(父级拼接)*/ 
	@Column(name= "TENDERS_BRANCH_NAME", nullable = false, length=500 )
    private String tendersBranchName;
    
    
	/** @Fields tunnelName : 隧道名称*/ 
	@Column(name= "TUNNEL_NAME", nullable = false, length=100 )
    private String tunnelName;
    
    
	/** @Fields startCheckDate : 开始检测时间 */
	@Column(name= "START_CHECK_DATE", nullable = false)
    private Date startCheckDate;
    
    
	/** @Fields endCheckDate : 结束检测时间 */
	@Column(name= "END_CHECK_DATE", nullable = false)
    private Date endCheckDate;
    
    
	/** @Fields sectionStakeNo : 断面桩号*/ 
	@Column(name= "SECTION_STAKE_NO", nullable = false, length=100 )
    private String sectionStakeNo;
    
    
	/** @Fields checkUserId : 检测人员ID */
	@Column(name= "CHECK_USER_ID", nullable = false)
    private Long checkUserId;
    
    
	/** @Fields checkUserName : 检测人员名称*/ 
	@Column(name= "CHECK_USER_NAME", nullable = false, length=100 )
    private String checkUserName;
    
    
	/** @Fields liningType : 衬砌类型(字典表)*/ 
	@Column(name= "LINING_TYPE", nullable = false, length=30 )
    private String liningType;
    
    
	/** @Fields invasionLineStatus : 侵线状态(字典表)*/ 
	@Column(name= "INVASION_LINE_STATUS", nullable = false, length=30 )
    private String invasionLineStatus;
    
    
     /** @Fields invasionLineMaxLength : 侵线最大值(cm)*/ 
    @Column(name= "INVASION_LINE_MAX_LENGTH", nullable = false , precision=18 , scale=2)
    private BigDecimal invasionLineMaxLength;
    
    
	/** @Fields invasionLinePosition : 侵线位置*/ 
	@Column(name= "INVASION_LINE_POSITION", nullable = false, length=100 )
    private String invasionLinePosition;
    
    
	/** @Fields attachmentFile : 附件*/ 
	@Column(name= "ATTACHMENT_FILE", nullable = false, length=100 )
    private String attachmentFile;
    
    
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
    
    
	/** @Fields qualifiedStatus : 合格状态(字典表)*/ 
	@Column(name= "QUALIFIED_STATUS", nullable = false, length=30 )
    private String qualifiedStatus;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public BusiQualitySecondLiningClearanceSectionSizeCheck getSecondLiningThicknessSizeCheckId() {
		return secondLiningThicknessSizeCheckId;
	}

	public void setSecondLiningThicknessSizeCheckId(
			BusiQualitySecondLiningClearanceSectionSizeCheck secondLiningThicknessSizeCheckId) {
		this.secondLiningThicknessSizeCheckId = secondLiningThicknessSizeCheckId;
	}

	public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

	public void setTendersBranchId(Long tendersBranchId){
        this.tendersBranchId = tendersBranchId;
    }
      
    public Long getTendersBranchId(){
        return this.tendersBranchId;
    }
      
    public void setTendersBranchName(String tendersBranchName){
        this.tendersBranchName = tendersBranchName;
    }
      
    public String getTendersBranchName(){
        return this.tendersBranchName;
    }
      
    public void setTunnelName(String tunnelName){
        this.tunnelName = tunnelName;
    }
      
    public String getTunnelName(){
        return this.tunnelName;
    }
      
    public void setStartCheckDate(Date startCheckDate){
        this.startCheckDate = startCheckDate;
    }
      
    public Date getStartCheckDate(){
        return this.startCheckDate;
    }
      
    public void setEndCheckDate(Date endCheckDate){
        this.endCheckDate = endCheckDate;
    }
      
    public Date getEndCheckDate(){
        return this.endCheckDate;
    }
      
    public void setSectionStakeNo(String sectionStakeNo){
        this.sectionStakeNo = sectionStakeNo;
    }
      
    public String getSectionStakeNo(){
        return this.sectionStakeNo;
    }
      
    public void setCheckUserId(Long checkUserId){
        this.checkUserId = checkUserId;
    }
      
    public Long getCheckUserId(){
        return this.checkUserId;
    }
      
    public void setCheckUserName(String checkUserName){
        this.checkUserName = checkUserName;
    }
      
    public String getCheckUserName(){
        return this.checkUserName;
    }
      
    public void setLiningType(String liningType){
        this.liningType = liningType;
    }
      
    public String getLiningType(){
        return this.liningType;
    }
      
    public void setInvasionLineStatus(String invasionLineStatus){
        this.invasionLineStatus = invasionLineStatus;
    }
      
    public String getInvasionLineStatus(){
        return this.invasionLineStatus;
    }
      
    public void setInvasionLineMaxLength(BigDecimal invasionLineMaxLength){
        this.invasionLineMaxLength = invasionLineMaxLength;
    }
      
    public BigDecimal getInvasionLineMaxLength(){
        return this.invasionLineMaxLength;
    }
      
    public void setInvasionLinePosition(String invasionLinePosition){
        this.invasionLinePosition = invasionLinePosition;
    }
      
    public String getInvasionLinePosition(){
        return this.invasionLinePosition;
    }
      
    public void setAttachmentFile(String attachmentFile){
        this.attachmentFile = attachmentFile;
    }
      
    public String getAttachmentFile(){
        return this.attachmentFile;
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
      
    public void setQualifiedStatus(String qualifiedStatus){
        this.qualifiedStatus = qualifiedStatus;
    }
      
    public String getQualifiedStatus(){
        return this.qualifiedStatus;
    }
      

}
