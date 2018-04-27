package com.huatek.busi.dto.measure;

import java.util.Date;

public class BusiMeasureCycleSettingDetailDto  {

	
 	private Long id;
 
    
    /** @Fields busiMeasureCycleSetting : 计量周期主表 */
    private Long busiMeasureCycleSettingId;
    
    
	/** @Fields serialNumber : 序号 */
    private Integer serialNumber;
    
    
	/** @Fields issueNumber : 期号*/ 
    private String issueNumber;
    
    
	/** @Fields startDate : 开始日期 */
    private String startDate;
    
    
	/** @Fields endDate : 结束日期 */
    private String endDate;
    
    
	/** @Fields year : 年份 */
    private Integer year;
    
    
	/** @Fields month : 月份 */
    private Integer month;
    
    
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
    private Integer isEdit;
    /** 用于标识列表为行为新增还是编辑*/
    private Boolean isNewRow;
    
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setSerialNumber(Integer serialNumber){
        this.serialNumber = serialNumber;
    }
      
    public Integer getSerialNumber(){
        return this.serialNumber;
    }
      
    public void setIssueNumber(String issueNumber){
        this.issueNumber = issueNumber;
    }
      
    public String getIssueNumber(){
        return this.issueNumber;
    }
      
    public void setYear(Integer year){
        this.year = year;
    }
      
    public Integer getYear(){
        return this.year;
    }
      
    public void setMonth(Integer month){
        this.month = month;
    }
      
    public Integer getMonth(){
        return this.month;
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

	public Long getBusiMeasureCycleSettingId() {
		return busiMeasureCycleSettingId;
	}

	public void setBusiMeasureCycleSettingId(Long busiMeasureCycleSettingId) {
		this.busiMeasureCycleSettingId = busiMeasureCycleSettingId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getIsEdit() {
		if(null != this.isEdit){
			return isEdit;
		}
		return 0;
	}

	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}

	public Boolean getIsNewRow() {
		return true;
	}
	
}
