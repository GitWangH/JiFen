package com.huatek.busi.dto.project;

import java.util.List;

/**
 * 工程标段管理
 * @author eli_cui
 *
 */

public class BusiProjectManagementOfBidSectionDto {
	/** 工程标段管理 主键id */
	private Long id;
	/** 起始桩号 */
	private String initialPileNumber;
	/** 结束桩号 */
	private String endPileNumber;
	/**  */
	private Long orgId;
	/** 工程标段管理明细 */
	private List<BusiProjectManagementOfBidSectionDetailDto> busiProjectManagementOfBidSectionDetailDtoList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public List<BusiProjectManagementOfBidSectionDetailDto> getBusiProjectManagementOfBidSectionDetailDtoList() {
		return busiProjectManagementOfBidSectionDetailDtoList;
	}
	public void setBusiProjectManagementOfBidSectionDetailDtoList(
			List<BusiProjectManagementOfBidSectionDetailDto> busiProjectManagementOfBidSectionDetailDtoList) {
		this.busiProjectManagementOfBidSectionDetailDtoList = busiProjectManagementOfBidSectionDetailDtoList;
	}
}