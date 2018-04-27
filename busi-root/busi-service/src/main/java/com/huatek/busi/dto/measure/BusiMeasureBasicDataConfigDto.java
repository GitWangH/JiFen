package com.huatek.busi.dto.measure;

import java.math.BigDecimal;
import java.util.Date;
public class BusiMeasureBasicDataConfigDto {

	
 	private Long id;
 
    
	/** @Fields orgId : 标段机构对象ID */
    private Long orgId;
    
    private String orgName;
    
    
     /** @Fields upperLimitRatio : 计量上限比例（%）*/ 
    private BigDecimal upperLimitRatio;
    
    
     /** @Fields measurePaySet : 计量支付设置*/ 
    private BigDecimal measurePaySet;
    
    
     /** @Fields mobilizeAdvancePayRatio : 动员预付款付款比例（%）*/ 
    private BigDecimal mobilizeAdvancePayRatio;
    
    
     /** @Fields monthDeductedMobilizeAdvanceRatio : 月扣回动员预付款比例（%）*/ 
    private BigDecimal monthDeductedMobilizeAdvanceRatio;
    
    
     /** @Fields mobilizeAdvanceDeductedRatio : 动员预付款起扣比例（%）*/ 
    private BigDecimal mobilizeAdvanceDeductedRatio;
    
    
     /** @Fields detainRetentionMoneyRatio : 暂扣保留金额比例（%）*/ 
    private BigDecimal detainRetentionMoneyRatio;
    
    
     /** @Fields cumulativeDetainRetentionMoneyLimit : 累计暂扣保留金限额（%）*/ 
    private BigDecimal cumulativeDetainRetentionMoneyLimit;
    
    
	/** @Fields remarks : 备注*/ 
    private String remarks;
    
    
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
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
    private Integer isDelete;
    
    /** 是否修改1是0否*/
    private Integer isEdit;
    
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
	public void setUpperLimitRatio(BigDecimal upperLimitRatio){
        this.upperLimitRatio = upperLimitRatio;
    }
      
    public BigDecimal getUpperLimitRatio(){
        return this.upperLimitRatio;
    }
      
    public void setMeasurePaySet(BigDecimal measurePaySet){
        this.measurePaySet = measurePaySet;
    }
      
    public BigDecimal getMeasurePaySet(){
        return this.measurePaySet;
    }
      
    public void setMobilizeAdvancePayRatio(BigDecimal mobilizeAdvancePayRatio){
        this.mobilizeAdvancePayRatio = mobilizeAdvancePayRatio;
    }
      
    public BigDecimal getMobilizeAdvancePayRatio(){
        return this.mobilizeAdvancePayRatio;
    }
      
    public void setMonthDeductedMobilizeAdvanceRatio(BigDecimal monthDeductedMobilizeAdvanceRatio){
        this.monthDeductedMobilizeAdvanceRatio = monthDeductedMobilizeAdvanceRatio;
    }
      
    public BigDecimal getMonthDeductedMobilizeAdvanceRatio(){
        return this.monthDeductedMobilizeAdvanceRatio;
    }
      
    public void setMobilizeAdvanceDeductedRatio(BigDecimal mobilizeAdvanceDeductedRatio){
        this.mobilizeAdvanceDeductedRatio = mobilizeAdvanceDeductedRatio;
    }
      
    public BigDecimal getMobilizeAdvanceDeductedRatio(){
        return this.mobilizeAdvanceDeductedRatio;
    }
      
    public void setDetainRetentionMoneyRatio(BigDecimal detainRetentionMoneyRatio){
        this.detainRetentionMoneyRatio = detainRetentionMoneyRatio;
    }
      
    public BigDecimal getDetainRetentionMoneyRatio(){
        return this.detainRetentionMoneyRatio;
    }
      
    public void setCumulativeDetainRetentionMoneyLimit(BigDecimal cumulativeDetainRetentionMoneyLimit){
        this.cumulativeDetainRetentionMoneyLimit = cumulativeDetainRetentionMoneyLimit;
    }
      
    public BigDecimal getCumulativeDetainRetentionMoneyLimit(){
        return this.cumulativeDetainRetentionMoneyLimit;
    }
      
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
      
    public String getRemarks(){
        return this.remarks;
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

	public Integer getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}
	
}
