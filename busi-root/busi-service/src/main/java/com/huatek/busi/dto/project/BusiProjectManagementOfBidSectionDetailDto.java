	package com.huatek.busi.dto.project;

import java.math.BigDecimal;

/**
 * 工程标段管理明细
 * @author eli_cui
 *
 */
public class BusiProjectManagementOfBidSectionDetailDto {
	/** 工程标段管理明细 id */
	private Long id;
	/** 单位工程名称 */
	private String nameOfUnitProject;
	/** 单位工程类型 */
	private String typeOfUnitProject;
	/** 起始桩号 */
	private String initialPileNumber;
	/** 结束桩号 */
	private String endPileNumber;
    /** @Fields longitude : 经度*/ 
    private BigDecimal longitude;
    /** @Fields latitude : 纬度*/ 
    private BigDecimal latitude;
    /** @Fields longKm : 长度*/ 
    private BigDecimal longKm;
    /** 操作 0 新增 1修改 2删除*/
    private Integer operation;
    /** isDelete = 0 未删除  isDelete = 1 已删除*/
    private Integer isDelete;
    /** 编号 */
    private String code;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameOfUnitProject() {
		return nameOfUnitProject;
	}
	public void setNameOfUnitProject(String nameOfUnitProject) {
		this.nameOfUnitProject = nameOfUnitProject;
	}
	public String getTypeOfUnitProject() {
		return typeOfUnitProject;
	}
	public void setTypeOfUnitProject(String typeOfUnitProject) {
		this.typeOfUnitProject = typeOfUnitProject;
	}
	public String getInitialPileNumber() {
		return initialPileNumber;
	}
	public void setInitialPileNumber(String initialPileNumber) {
		this.initialPileNumber = initialPileNumber;
	}
	public String getEndPileNumber() {
		return endPileNumber;
	}
	public void setEndPileNumber(String endPileNumber) {
		this.endPileNumber = endPileNumber;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongKm() {
		return longKm;
	}
	public void setLongKm(BigDecimal longKm) {
		this.longKm = longKm;
	}
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}