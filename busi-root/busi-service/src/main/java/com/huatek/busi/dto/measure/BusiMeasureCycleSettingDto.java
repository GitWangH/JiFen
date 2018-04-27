package com.huatek.busi.dto.measure;


public class BusiMeasureCycleSettingDto  {

 	private Long id;
    
	/** @Fields orgId : 标段机构对象ID */
    private Long orgId;
    private String orgName;
    
    
	/** @Fields measureType : 计量类型(字典表)*/ 
    private String measureType;
    
    
	/** @Fields remarks : 备注*/ 
    private String remarks;
    
    
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
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
    private Integer isDelete;
    
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
      
    public void setMeasureType(String measureType){
        this.measureType = measureType;
    }
      
    public String getMeasureType(){
        return this.measureType;
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
      
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
      
    public Integer getIsDelete(){
        return this.isDelete;
    }

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
