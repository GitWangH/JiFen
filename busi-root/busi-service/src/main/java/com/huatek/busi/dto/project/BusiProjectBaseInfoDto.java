package com.huatek.busi.dto.project;

import java.math.BigDecimal;

/**
 * 项目基本信息
 * @author eli_cui
 *
 */
public class BusiProjectBaseInfoDto {
    private Long id;
    private String projectFullName;
    private String projectAbbreviation;
    private String projectNumber;
    private String projectSponsor;
    private String projectStatus;
    private String buildCompany;
    private String projectOfficeAddress;
    private String projectDate;
    private String contactInformation;
    private BigDecimal projectBudgetEstimate;
    private BigDecimal finalAccountsOfCompletedProject;
    private String commencementDate;
    private String completionDate;
    private String projectDescription;
    private String remark;
    private Long orgIdForShow;
    private String createTime;
    
   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectFullName() {
		return projectFullName;
	}

	public void setProjectFullName(String projectFullName) {
		this.projectFullName = projectFullName;
	}

	public String getProjectAbbreviation() {
		return projectAbbreviation;
	}

	public void setProjectAbbreviation(String projectAbbreviation) {
		this.projectAbbreviation = projectAbbreviation;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectSponsor() {
		return projectSponsor;
	}

	public void setProjectSponsor(String projectSponsor) {
		this.projectSponsor = projectSponsor;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getBuildCompany() {
		return buildCompany;
	}

	public void setBuildCompany(String buildCompany) {
		this.buildCompany = buildCompany;
	}

	public String getProjectOfficeAddress() {
		return projectOfficeAddress;
	}

	public void setProjectOfficeAddress(String projectOfficeAddress) {
		this.projectOfficeAddress = projectOfficeAddress;
	}

	public String getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(String projectDate) {
		this.projectDate = projectDate;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public BigDecimal getProjectBudgetEstimate() {
		return projectBudgetEstimate;
	}

	public void setProjectBudgetEstimate(BigDecimal projectBudgetEstimate) {
		this.projectBudgetEstimate = projectBudgetEstimate;
	}

	public BigDecimal getFinalAccountsOfCompletedProject() {
		return finalAccountsOfCompletedProject;
	}

	public void setFinalAccountsOfCompletedProject(
			BigDecimal finalAccountsOfCompletedProject) {
		this.finalAccountsOfCompletedProject = finalAccountsOfCompletedProject;
	}

	public String getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(String commencementDate) {
		this.commencementDate = commencementDate;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getOrgIdForShow() {
		return orgIdForShow;
	}

	public void setOrgIdForShow(Long orgIdForShow) {
		this.orgIdForShow = orgIdForShow;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}