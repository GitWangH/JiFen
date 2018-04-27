package com.huatek.frame.core.dto;
/**
 * 给前端传送下拉选择数据的VO类.
 * @author winner pan.
 *
 */
public class ParamDto implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private String value;/*用传输id等*/
	private String remark; //备用字段
	private String category;
	private String type;/*用于机构类型*/
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ParamDto(){
		
	}
	public ParamDto(String name, String code){
		this.name = name;
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
