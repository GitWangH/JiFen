package com.huatek.busi.dto.quality;

 /**
  * 二衬厚度检测修改日志Dto.
  * @ClassName: BusiQualitySecondLiningThicknessCheckModifyLog
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-29 21:37:37
  * @version: Windows 7
  */
public class BusiQualitySecondLiningThicknessCheckModifyLogDto{

	private static final long serialVersionUID = 1L;
	
 	private Long id;
    
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
    private String startCheckDate;
    
	/** @Fields endCheckDate : 结束检测时间 */
    private String endCheckDate;
    
	/** @Fields beginToEndStakeNo : 起讫桩号*/ 
    private String beginToEndStakeNo;
    
	/** @Fields liningType : 衬砌类型(字典表)*/ 
    private String liningType;
    
     /** @Fields designThickness : 设计厚度(cm)*/ 
    private String designThickness;
    
     /** @Fields actualMeasureAverageThickness : 实测平均厚度(cm)*/ 
    private String actualMeasureAverageThickness;
    
     /** @Fields oneLineQualifiedRate : 单线合格率*/ 
    private String oneLineQualifiedRate;
    
     /** @Fields threeLineQualifiedRate : 3线合格率*/ 
    private String threeLineQualifiedRate;
    
     /** @Fields fiveLineQualifiedRate : 5线合格率*/ 
    private String fiveLineQualifiedRate;
    
	/** @Fields checkUserId : 检测人员ID */
    private String checkUserId;
    
	/** @Fields checkUserName : 检测人员名称*/ 
    private String checkUserName;
    
	/** @Fields qualifiedStatus : 合格状态(字典表)*/ 
    private String qualifiedStatus;
    
	/** @Fields attachmentFile : 附件*/ 
    private String attachmentFile;
    
	/** @Fields creater : 创建人id */
    private String creater;
    
	/** @Fields createrName : 创建人姓名*/ 
    private String createrName;
    
	/** @Fields createTime : 创建时间 */
    private String createTime;
    
	/** @Fields modifer : 修改人id */
    private String modifer;
    
	/** @Fields modifierName : 修改人姓名*/ 
    private String modifierName;
    
	/** @Fields modifyTime : 修改时间 */
    private String modifyTime;
    
	/** @Fields tenantId : 租户id */
    private Long tenantId;
    
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getTendersBranchId() {
		return tendersBranchId;
	}

	public void setTendersBranchId(Long tendersBranchId) {
		this.tendersBranchId = tendersBranchId;
	}

	public String getTendersBranchName() {
		return tendersBranchName;
	}

	public void setTendersBranchName(String tendersBranchName) {
		this.tendersBranchName = tendersBranchName;
	}

	public String getTunnelName() {
		return tunnelName;
	}

	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}

	public String getStartCheckDate() {
		return startCheckDate;
	}

	public void setStartCheckDate(String startCheckDate) {
		this.startCheckDate = startCheckDate;
	}

	public String getEndCheckDate() {
		return endCheckDate;
	}

	public void setEndCheckDate(String endCheckDate) {
		this.endCheckDate = endCheckDate;
	}

	public String getBeginToEndStakeNo() {
		return beginToEndStakeNo;
	}

	public void setBeginToEndStakeNo(String beginToEndStakeNo) {
		this.beginToEndStakeNo = beginToEndStakeNo;
	}

	public String getLiningType() {
		return liningType;
	}

	public void setLiningType(String liningType) {
		this.liningType = liningType;
	}

	public String getDesignThickness() {
		return designThickness;
	}

	public void setDesignThickness(String designThickness) {
		this.designThickness = designThickness;
	}

	public String getActualMeasureAverageThickness() {
		return actualMeasureAverageThickness;
	}

	public void setActualMeasureAverageThickness(
			String actualMeasureAverageThickness) {
		this.actualMeasureAverageThickness = actualMeasureAverageThickness;
	}

	public String getOneLineQualifiedRate() {
		return oneLineQualifiedRate;
	}

	public void setOneLineQualifiedRate(String oneLineQualifiedRate) {
		this.oneLineQualifiedRate = oneLineQualifiedRate;
	}

	public String getThreeLineQualifiedRate() {
		return threeLineQualifiedRate;
	}

	public void setThreeLineQualifiedRate(String threeLineQualifiedRate) {
		this.threeLineQualifiedRate = threeLineQualifiedRate;
	}

	public String getFiveLineQualifiedRate() {
		return fiveLineQualifiedRate;
	}

	public void setFiveLineQualifiedRate(String fiveLineQualifiedRate) {
		this.fiveLineQualifiedRate = fiveLineQualifiedRate;
	}

	public String getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getQualifiedStatus() {
		return qualifiedStatus;
	}

	public void setQualifiedStatus(String qualifiedStatus) {
		this.qualifiedStatus = qualifiedStatus;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifer() {
		return modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
      
}
