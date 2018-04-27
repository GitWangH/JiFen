package com.huatek.busi.dto.phicom.game;

import java.util.List;

public class PhiGameDto {
	private Long id;
	private String gameType;
	private String gameName;
	private String lastOperationTime;
	private String taskSwitch;
	private String remark;
	private String freeTimesDay;
	private String oneTimeScore;
	private String operationPerson;
	private String drawMax;
	private int drawTimes;// 玩大转盘次数
	private int code;
	/** @Fields type : 类型(bigWheel大转盘 ninePlace九宫格) */
	private String type;
	private List<PhiGameConfigDto> phiGameConfigs;
	private int isEncough;

	private String enableScore;// 可用积分

	public int getIsEncough() {
		return isEncough;
	}

	public void setIsEncough(int isEncough) {
		this.isEncough = isEncough;
	}

	public int getDrawTimes() {
		return drawTimes;
	}

	public void setDrawTimes(int drawTimes) {
		this.drawTimes = drawTimes;
	}

	/**
	 * 默认构造器
	 */
	public PhiGameDto() {
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getGameType() {
		return this.gameType;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameName() {
		return this.gameName;
	}

	public void setTaskSwitch(String taskSwitch) {
		this.taskSwitch = taskSwitch;
	}

	public String getTaskSwitch() {
		return this.taskSwitch;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setFreeTimesDay(String freeTimesDay) {
		this.freeTimesDay = freeTimesDay;
	}

	public String getFreeTimesDay() {
		return this.freeTimesDay;
	}

	public void setOneTimeScore(String oneTimeScore) {
		this.oneTimeScore = oneTimeScore;
	}

	public String getOneTimeScore() {
		return this.oneTimeScore;
	}

	public void setDrawMax(String drawMax) {
		this.drawMax = drawMax;
	}

	public String getDrawMax() {
		return this.drawMax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperationPerson() {
		return operationPerson;
	}

	public void setOperationPerson(String operationPerson) {
		this.operationPerson = operationPerson;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getLastOperationTime() {
		return lastOperationTime;
	}

	public void setLastOperationTime(String lastOperationTime) {
		this.lastOperationTime = lastOperationTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PhiGameConfigDto> getPhiGameConfigs() {
		return phiGameConfigs;
	}

	public void setPhiGameConfigs(List<PhiGameConfigDto> phiGameConfigs) {
		this.phiGameConfigs = phiGameConfigs;
	}

	public String getEnableScore() {
		return enableScore;
	}

	public void setEnableScore(String enableScore) {
		this.enableScore = enableScore;
	}

}