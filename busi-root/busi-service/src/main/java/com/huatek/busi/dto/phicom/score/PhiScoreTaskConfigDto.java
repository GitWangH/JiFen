package com.huatek.busi.dto.phicom.score;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class PhiScoreTaskConfigDto {
    private Long id;
    private Date lastOperationTime;
    private String operationPerson;
    private String showName;
    private String taskName;
    private String taskRemark;
    private String taskSwitch;
    private String taskTimeType;
    private String taskType;
    private Set<PhiScoreConfigRuleDto> scoreConfigRule;
    private List<PhiScoreConfigRuleDto> scoreConfigRuleList;
    private List<String> thirdAccount;
    private String image;
    private String imageApp;
    private String src;
    private Long code;
    private String pcSrc;
    private String time;
    
//    private PhiScoreConfigRuleDto phiScoreRule;
    
    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 默认构造器
	 */
	public PhiScoreTaskConfigDto(){}
    
	public String getImageApp() {
		return imageApp;
	}

	public void setImageApp(String imageApp) {
		this.imageApp = imageApp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastOperationTime() {
		return lastOperationTime;
	}

	public void setLastOperationTime(Date lastOperationTime) {
		this.lastOperationTime = lastOperationTime;
	}

	public String getOperationPerson() {
		return operationPerson;
	}

	public void setOperationPerson(String operationPerson) {
		this.operationPerson = operationPerson;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskRemark() {
		return taskRemark;
	}

	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}

	public String getTaskSwitch() {
		return taskSwitch;
	}

	public void setTaskSwitch(String taskSwitch) {
		this.taskSwitch = taskSwitch;
	}

	public String getTaskTimeType() {
		return taskTimeType;
	}

	public void setTaskTimeType(String taskTimeType) {
		this.taskTimeType = taskTimeType;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public List<PhiScoreConfigRuleDto> getScoreConfigRuleList() {
		return scoreConfigRuleList;
	}

	public void setScoreConfigRuleList(
			List<PhiScoreConfigRuleDto> scoreConfigRuleList) {
		this.scoreConfigRuleList = scoreConfigRuleList;
	}

	/*public Set<PhiScoreConfigRuleDto> getScoreConfigRule() {
		return scoreConfigRule;
	}*/

	public void setScoreConfigRule(Set<PhiScoreConfigRuleDto> scoreConfigRule) {
		this.scoreConfigRule = scoreConfigRule;
	}

	public Set<PhiScoreConfigRuleDto> getScoreConfigRule() {
		return scoreConfigRule;
	}

	public List<String> getThirdAccount() {
		return thirdAccount;
	}

	public void setThirdAccount(List<String> thirdAccount) {
		this.thirdAccount = thirdAccount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getPcSrc() {
		return pcSrc;
	}

	public void setPcSrc(String pcSrc) {
		this.pcSrc = pcSrc;
	}
	 
	
}