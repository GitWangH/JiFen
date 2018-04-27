package com.huatek.busi.dto.base;

public class BusiBaseImageListSubConnectionTableDto {
	
	/**分部分项与工程量清单挂接表 主键id*/
	private Long id;
	/** 分部分项编号 */
	private String subEngineeringNumber;
	/** 分部分项名称 */
	private String subEngineeringName;
	/** 形象清单编号 */
	private String engineeringQuantityNumber;
	/** 形象清单名称 */
	private String engineeringQuantityName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubEngineeringNumber() {
		return subEngineeringNumber;
	}
	public void setSubEngineeringNumber(String subEngineeringNumber) {
		this.subEngineeringNumber = subEngineeringNumber;
	}
	public String getSubEngineeringName() {
		return subEngineeringName;
	}
	public void setSubEngineeringName(String subEngineeringName) {
		this.subEngineeringName = subEngineeringName;
	}
	public String getEngineeringQuantityNumber() {
		return engineeringQuantityNumber;
	}
	public void setEngineeringQuantityNumber(String engineeringQuantityNumber) {
		this.engineeringQuantityNumber = engineeringQuantityNumber;
	}
	public String getEngineeringQuantityName() {
		return engineeringQuantityName;
	}
	public void setEngineeringQuantityName(String engineeringQuantityName) {
		this.engineeringQuantityName = engineeringQuantityName;
	}
	

}
