package com.huatek.busi.dto.phicom.score;


public class PhiScoreConfigRuleDto {
	private Long id;
	
	//任务项
	private Long scoreTaskConfig;
	
	//积分值1
	private String scoreValue1;
	
	//积分值2
	private String scoreValue2;
	
	//积分值3
	private String scoreValue3;
	
	//积分值4
	private String scoreValue4;
	
	//积分值5
	private String scoreValue5;
	
	//积分值6
	private String scoreValue6;
	
	//积分值7
	private String scoreValue7;
	
	//最小积分值
	private String minScoreValue;
	
	//最大积分值
	private String maxScoreValue;
	
	//时间1永久 2设置时间段
	private int ruleTimeType;
	
	//开始时间
	private String startTime;
	
	//结束时间
	private String endTime;
	
	//积分值1奖励前几人
	private String  scoreValue1Quantity;
	
	//积分值2奖励前几人
	private String  scoreValue2Quantity;
	
	//积分值3奖励前几人
	private String  scoreValue3Quantity;
	
	//积分值4奖励前几人
	private String  scoreValue4Quantity;
	
	//积分值5奖励前几人
	private String  scoreValue5Quantity;
	
	//积分值6奖励前几人
	private String  scoreValue6Quantity;
	
	//积分值7奖励前几人
	private String  scoreValue7Quantity;
	
	/**
	 * 扩展项
	 * 完善资料类为：头像(icon)、昵称(nickname)、性别(gender)、生日(birthday),出现多选时各类中间用|分割，如:icon|nickname
	 * 实名认证项为：身份证(identity)
	 * 绑定第三方账号为：微信(weChat)、微博(microblog)、QQ(qq)
	 * 
	 */
	private String  extensing;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScoreTaskConfig() {
		return scoreTaskConfig;
	}

	public void setScoreTaskConfig(Long scoreTaskConfig) {
		this.scoreTaskConfig = scoreTaskConfig;
	}

	public String getScoreValue1() {
		return scoreValue1;
	}

	public void setScoreValue1(String scoreValue1) {
		this.scoreValue1 = scoreValue1;
	}

	public String getScoreValue2() {
		return scoreValue2;
	}

	public void setScoreValue2(String scoreValue2) {
		this.scoreValue2 = scoreValue2;
	}

	public String getScoreValue3() {
		return scoreValue3;
	}

	public void setScoreValue3(String scoreValue3) {
		this.scoreValue3 = scoreValue3;
	}

	public String getScoreValue4() {
		return scoreValue4;
	}

	public void setScoreValue4(String scoreValue4) {
		this.scoreValue4 = scoreValue4;
	}

	public String getScoreValue5() {
		return scoreValue5;
	}

	public void setScoreValue5(String scoreValue5) {
		this.scoreValue5 = scoreValue5;
	}

	public String getScoreValue6() {
		return scoreValue6;
	}

	public void setScoreValue6(String scoreValue6) {
		this.scoreValue6 = scoreValue6;
	}

	public String getScoreValue7() {
		return scoreValue7;
	}

	public void setScoreValue7(String scoreValue7) {
		this.scoreValue7 = scoreValue7;
	}

	public String getMinScoreValue() {
		return minScoreValue;
	}

	public void setMinScoreValue(String minScoreValue) {
		this.minScoreValue = minScoreValue;
	}

	public String getMaxScoreValue() {
		return maxScoreValue;
	}

	public void setMaxScoreValue(String maxScoreValue) {
		this.maxScoreValue = maxScoreValue;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getScoreValue1Quantity() {
		return scoreValue1Quantity;
	}

	public void setScoreValue1Quantity(String scoreValue1Quantity) {
		this.scoreValue1Quantity = scoreValue1Quantity;
	}

	public String getScoreValue2Quantity() {
		return scoreValue2Quantity;
	}

	public void setScoreValue2Quantity(String scoreValue2Quantity) {
		this.scoreValue2Quantity = scoreValue2Quantity;
	}

	public String getScoreValue3Quantity() {
		return scoreValue3Quantity;
	}

	public void setScoreValue3Quantity(String scoreValue3Quantity) {
		this.scoreValue3Quantity = scoreValue3Quantity;
	}

	public String getScoreValue4Quantity() {
		return scoreValue4Quantity;
	}

	public void setScoreValue4Quantity(String scoreValue4Quantity) {
		this.scoreValue4Quantity = scoreValue4Quantity;
	}

	public String getScoreValue5Quantity() {
		return scoreValue5Quantity;
	}

	public void setScoreValue5Quantity(String scoreValue5Quantity) {
		this.scoreValue5Quantity = scoreValue5Quantity;
	}

	public String getScoreValue6Quantity() {
		return scoreValue6Quantity;
	}

	public void setScoreValue6Quantity(String scoreValue6Quantity) {
		this.scoreValue6Quantity = scoreValue6Quantity;
	}

	public String getScoreValue7Quantity() {
		return scoreValue7Quantity;
	}

	public void setScoreValue7Quantity(String scoreValue7Quantity) {
		this.scoreValue7Quantity = scoreValue7Quantity;
	}

	public String getExtensing() {
		return extensing;
	}

	public void setExtensing(String extensing) {
		this.extensing = extensing;
	}

	public int getRuleTimeType() {
		return ruleTimeType;
	}

	public void setRuleTimeType(int ruleTimeType) {
		this.ruleTimeType = ruleTimeType;
	}
	
	
      
}