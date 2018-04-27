package com.huatek.busi.dto.project.port;

/**
 * 工程标段管理 接口传输DTO
 * 
 * @author eli_cui
 *
 */
public class BusiProjectManagementOfBidSectionPortDto {
	/** 监理单位 */
	private String supervisionCompanyName;
	/** 监理单位id */
	private Long supervisionCompanyCode;
	/** 建设单位名称 */
	private String constructionCompanyName;
	/** 建设单位id */
	private Long constructionCompanyCode;
	/** 施工单位id **/
	private Long buildCompanyCode;
	/** 施工单位Name **/
	private String buildCompanyName;

	/** 起始桩号 */
	private String startStakeNo;
	/** 结束桩号 */
	private String endStakeNo;
	/** 合同编号 */
	private String contractCode;

	public String getSupervisionCompanyName() {
		return supervisionCompanyName;
	}

	public void setSupervisionCompanyName(String supervisionCompanyName) {
		this.supervisionCompanyName = supervisionCompanyName;
	}

	public Long getSupervisionCompanyCode() {
		return supervisionCompanyCode;
	}

	public void setSupervisionCompanyCode(Long supervisionCompanyCode) {
		this.supervisionCompanyCode = supervisionCompanyCode;
	}

	public String getConstructionCompanyName() {
		return constructionCompanyName;
	}

	public void setConstructionCompanyName(String constructionCompanyName) {
		this.constructionCompanyName = constructionCompanyName;
	}

	public Long getConstructionCompanyCode() {
		return constructionCompanyCode;
	}

	public void setConstructionCompanyCode(Long constructionCompanyCode) {
		this.constructionCompanyCode = constructionCompanyCode;
	}

	public Long getBuildCompanyCode() {
		return buildCompanyCode;
	}

	public void setBuildCompanyCode(Long buildCompanyCode) {
		this.buildCompanyCode = buildCompanyCode;
	}

	public String getBuildCompanyName() {
		return buildCompanyName;
	}

	public void setBuildCompanyName(String buildCompanyName) {
		this.buildCompanyName = buildCompanyName;
	}

	public String getStartStakeNo() {
		return startStakeNo;
	}

	public void setStartStakeNo(String startStakeNo) {
		this.startStakeNo = startStakeNo;
	}

	public String getEndStakeNo() {
		return endStakeNo;
	}

	public void setEndStakeNo(String endStakeNo) {
		this.endStakeNo = endStakeNo;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

}
