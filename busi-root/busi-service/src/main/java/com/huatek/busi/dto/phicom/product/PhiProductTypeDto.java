package com.huatek.busi.dto.phicom.product;


public class PhiProductTypeDto {
	
	
	private Long id;
    private String typeName;
    private String typeIcon;
    private String showOrder;
    private String handleMan;
    private String lastTime;
    private String isRecomment;
    private String remark;
	
	/**typeName
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 */
	 
     /**
	 * 生成getter，setter 访问器
	 */
    
    public void setTypeName(String typeName){
        this.typeName = typeName;
    }
    

	public String getTypeName(){
        return this.typeName;
    }
      
    public void setTypeIcon(String typeIcon){
        this.typeIcon = typeIcon;
    }
      
    public String getTypeIcon(){
        return this.typeIcon;
    }
      
    public void setShowOrder(String showOrder){
        this.showOrder = showOrder;
    }
      
    public String getShowOrder(){
        return this.showOrder;
    }
      
    public void setHandleMan(String handleMan){
        this.handleMan = handleMan;
    }
      
    public String getHandleMan(){
        return this.handleMan;
    }
      
    public void setLastTime(String lastTime){
        this.lastTime = lastTime;
    }
      
    public String getLastTime(){
        return this.lastTime;
    }
      
    public void setIsRecomment(String isRecomment){
        this.isRecomment = isRecomment;
    }
      
    public String getIsRecomment(){
        return this.isRecomment;
    }


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	
    
  
	
}