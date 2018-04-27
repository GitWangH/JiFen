package com.huatek.busi.dto.quality;

import java.math.BigDecimal;
import java.util.Date;

public class BusiQualitySecondLiningClearanceModifyLogDto {

	
 	private Long id;
 
    
	/** @Fields secondLiningThicknessSizeCheckId : 主表ID */
    private Long secondLiningThicknessSizeCheckId;
    
    
	/** @Fields orgId : 标段名称(机构ID，根据机构ID查询名称) */
    private Long orgId;
    private String orgName;
    
    
	/** @Fields tendersBranchId : 分部分项(分部分项表ID) */
    private Long tendersBranchId;
    
    
	/** @Fields tendersBranchName : 分部分项名称(父级拼接)*/ 
    private String tendersBranchName;
    
    
	/** @Fields tunnelName : 隧道名称*/ 
    private String tunnelName;
    
    
	/** @Fields startCheckDate : 开始检测时间 */
    private Date startCheckDate;
    
    
	/** @Fields endCheckDate : 结束检测时间 */
    private Date endCheckDate;
    
    
	/** @Fields sectionStakeNo : 断面桩号*/ 
    private String sectionStakeNo;
    
    
	/** @Fields checkUserId : 检测人员ID */
    private Long checkUserId;
    
    
	/** @Fields checkUserName : 检测人员名称*/ 
    private String checkUserName;
    
    
	/** @Fields liningType : 衬砌类型(字典表)*/ 
    private String liningType;
    
    
	/** @Fields invasionLineStatus : 侵线状态(字典表)*/ 
    private String invasionLineStatus;
    
    
     /** @Fields invasionLineMaxLength : 侵线最大值(cm)*/ 
    private BigDecimal invasionLineMaxLength;
    
    
	/** @Fields invasionLinePosition : 侵线位置*/ 
    private String invasionLinePosition;
    
    
	/** @Fields attachmentFile : 附件*/ 
    private String attachmentFile;
    
    
	/** @Fields creater : 创建人id */
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
    private Date createTime;
    
    
	/** @Fields modifer : 修改人id */
    private Long modifer;
    
    
	/** @Fields modifierName : 修改人姓名*/ 
    private String modifierName;
    
    
	/** @Fields modifyTime : 修改时间 */
    private Date modifyTime;
    
    
	/** @Fields tenantId : 租户id */
    private Long tenantId;
    
    
	/** @Fields qualifiedStatus : 合格状态(字典表)*/ 
    private String qualifiedStatus;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setSecondLiningThicknessSizeCheckId(Long secondLiningThicknessSizeCheckId){
        this.secondLiningThicknessSizeCheckId = secondLiningThicknessSizeCheckId;
    }
      
    public Long getSecondLiningThicknessSizeCheckId(){
        return this.secondLiningThicknessSizeCheckId;
    }
      
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
      
    public Long getOrgId(){
        return this.orgId;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
}
