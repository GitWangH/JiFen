package com.huatek.frame.service.dto;

public class FwStationAccountDto {

	
 	private Long id;
 
    
    /** @Fields fwStation : 岗位Id */
    private Long stationId;
    
    /** @Fields fwStation : 岗位名称 */
    private String stationName;
    
    /** @Fields fwAccount : 账户ID */
    private Long fwAccountId;
    
    /** @Fields fwAccount : 账户名称 */
    private String fwAccountName;
    
	/** @Fields tenantId :  */
    private Long tenantId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Long getFwAccountId() {
		return fwAccountId;
	}

	public void setFwAccountId(Long fwAccountId) {
		this.fwAccountId = fwAccountId;
	}

	public String getFwAccountName() {
		return fwAccountName;
	}

	public void setFwAccountName(String fwAccountName) {
		this.fwAccountName = fwAccountName;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
    
}
