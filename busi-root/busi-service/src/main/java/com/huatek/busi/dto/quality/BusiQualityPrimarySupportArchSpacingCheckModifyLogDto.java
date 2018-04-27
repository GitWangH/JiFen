package com.huatek.busi.dto.quality;

 /**
  * 初期支护拱架间距检测修改记录数据转化类
  * @ClassName: BusiQualityPrimarySupportArchSpacingCheckModifyLog
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @String: 2017-11-24 16:32:12
  * @version: Windows 7
  */
public class BusiQualityPrimarySupportArchSpacingCheckModifyLogDto {

 	private Long id;
 
	/** @Fields priSupCleSecCheckId : 初期支护拱架间距检测 id*/
	private Long priSupCleSecCheckId;
	
	/** @Fields orgId : 标段ID */
    private Long orgId;
    
    /** @Fields orgName : 标段名称(机构ID，根据机构ID查询名称) */
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
    
    
	/** @Fields checkParagraph : 检测段落*/ 
    private String checkParagraph;
    
    
	/** @Fields checkUserId : 检测人员ID */
    private Long checkUserId;
    
    
	/** @Fields checkUserName : 检测人员名称*/ 
    private String checkUserName;
    
    
	/** @Fields liningType : 衬砌类型(字典表)*/ 
    private String liningType;
    
    
     /** @Fields intervalLength : 区间长度(m)*/ 
    private String intervalLength;
    
    
     /** @Fields designIntervalLength : 设计间距(cm)*/ 
    private String designIntervalLength;
    
    
     /** @Fields actualMeasureAverageSpacing : 实测平均间距(cm)*/ 
    private String actualMeasureAverageSpacing;
    
    
     /** @Fields actualMeasureRoofTrussesQuantity : 实测榀数*/ 
    private String actualMeasureRoofTrussesQuantity;
    
    
     /** @Fields designMeasureRoofTrussesQuantity : 设计榀数*/ 
    private String designMeasureRoofTrussesQuantity;
    
    
	/** @Fields qualifiedStatus : 合格状态(字典表)*/ 
    private String qualifiedStatus;
    
    
	/** @Fields attachmentFile : 附件*/ 
    private String attachmentFile;
    
    
	/** @Fields creater : 创建人id */
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
    private String createTime;
    
    
	/** @Fields modifer : 修改人id */
    private Long modifer;
    
    
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

	public void setCheckParagraph(String checkParagraph){
        this.checkParagraph = checkParagraph;
    }
      
    public String getCheckParagraph(){
        return this.checkParagraph;
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
      
    public void setIntervalLength(String intervalLength){
        this.intervalLength = intervalLength;
    }
      
    public String getIntervalLength(){
        return this.intervalLength;
    }
      
    public void setDesignIntervalLength(String designIntervalLength){
        this.designIntervalLength = designIntervalLength;
    }
      
    public String getDesignIntervalLength(){
        return this.designIntervalLength;
    }
      
    public void setActualMeasureAverageSpacing(String actualMeasureAverageSpacing){
        this.actualMeasureAverageSpacing = actualMeasureAverageSpacing;
    }
      
    public String getActualMeasureAverageSpacing(){
        return this.actualMeasureAverageSpacing;
    }
      
    public void setActualMeasureRoofTrussesQuantity(String actualMeasureRoofTrussesQuantity){
        this.actualMeasureRoofTrussesQuantity = actualMeasureRoofTrussesQuantity;
    }
      
    public String getActualMeasureRoofTrussesQuantity(){
        return this.actualMeasureRoofTrussesQuantity;
    }
      
    public void setDesignMeasureRoofTrussesQuantity(String designMeasureRoofTrussesQuantity){
        this.designMeasureRoofTrussesQuantity = designMeasureRoofTrussesQuantity;
    }
      
    public String getDesignMeasureRoofTrussesQuantity(){
        return this.designMeasureRoofTrussesQuantity;
    }
      
    public void setQualifiedStatus(String qualifiedStatus){
        this.qualifiedStatus = qualifiedStatus;
    }
      
    public String getQualifiedStatus(){
        return this.qualifiedStatus;
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
      
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
      
    public String getCreateTime(){
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
      
    public void setModifyTime(String modifyTime){
        this.modifyTime = modifyTime;
    }
      
    public String getModifyTime(){
        return this.modifyTime;
    }
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }

	public Long getPriSupCleSecCheckId() {
		return priSupCleSecCheckId;
	}

	public void setPriSupCleSecCheckId(Long priSupCleSecCheckId) {
		this.priSupCleSecCheckId = priSupCleSecCheckId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
