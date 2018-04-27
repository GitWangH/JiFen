package com.huatek.busi.dto.phicom.region;

import java.util.List;


public class PhiRegionsDto {
    private Long id;
    private String code;
    private String name;
    private Long parentCode;
    private Long level;
    private String ordernume;
    private String disable;
    List<PhiRegionsDto> childs;
    
    
    /**
	 * 默认构造器
	 */
	public PhiRegionsDto(){}
	
	 
     /**
	 * 生成getter，setter 访问器
	 */
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setCode(String code){
        this.code = code;
    }
      
    public String getCode(){
        return this.code;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public Long getParentCode() {
		return parentCode;
	}


	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}


	public void setLevel(Long level){
        this.level = level;
    }
      
    public Long getLevel(){
        return this.level;
    }
      
    public void setOrdernume(String ordernume){
        this.ordernume = ordernume;
    }
      
    public String getOrdernume(){
        return this.ordernume;
    }
      
    public void setDisable(String disable){
        this.disable = disable;
    }
      
    public String getDisable(){
        return this.disable;
    }


	public List<PhiRegionsDto> getChilds() {
		return childs;
	}


	public void setChilds(List<PhiRegionsDto> childs) {
		this.childs = childs;
	}
      
    
}