package com.huatek.busi.model.base;

/***
 * 分部分项与工程量清单 挂接模块显示实体类
 * @author eli_cui
 *
 */
public class BusiBaseQuantityListSubConnectionTableShow {
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
	
	
	public BusiBaseQuantityListSubConnectionTableShow(){
		
	}
	
	public BusiBaseQuantityListSubConnectionTableShow(Long id,String subEngineeringNumber, String subEngineeringName,String engineeringQuantityNumber, String engineeringQuantityName) {
		this.id = id;
		this.subEngineeringNumber = subEngineeringNumber;
		this.subEngineeringName = subEngineeringName;
		this.engineeringQuantityNumber = engineeringQuantityNumber;
		this.engineeringQuantityName = engineeringQuantityName;
	}
	
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
