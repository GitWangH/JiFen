package com.huatek.busi.model.phicom.score;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 
* @ClassName: ScoreConfigRule 
* @Description: 积分获取任务配置项规则
* @author eden_sun
* @date Jan 5, 2018 2:52:08 PM 
*
 */
@Entity
@Table(name = "phi_score_config_rule")
public class PhiScoreConfigRule extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	/** @Fields orderId :订单id*/ 
	@Column(name= "scr_id", nullable = false, length=20)
	private Long id;
	
	//任务项
//	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = false)
//	@JoinColumn(name = "stc_id")
	@Column(name = "stc_id", nullable = false, length=20)
	private Long scoreTaskConfig;
	
	//积分值1
	@Column(name= "scoreValue1", nullable = false, length=20)
	private Integer scoreValue1;
	
	//积分值2
	@Column(name= "scoreValue2", nullable = false, length=20)
	private Integer scoreValue2;
	
	//积分值3
	@Column(name= "scoreValue3", nullable = false, length=20)
	private Integer scoreValue3;
	
	//积分值4
	@Column(name= "scoreValue4", nullable = false, length=20)
	private Integer scoreValue4;
	
	//积分值5
	@Column(name= "scoreValue5", nullable = false, length=20)
	private Integer scoreValue5;
	
	//积分值6
	@Column(name= "scoreValue6", nullable = false, length=20)
	private Integer scoreValue6;
	
	//积分值7
	@Column(name= "scoreValue7", nullable = false, length=20)
	private Integer scoreValue7;
	
	//最小积分值
	@Column(name= "minScoreValue", nullable = false, length=20)
	private Integer minScoreValue;
	
	//任务时间类别,1为永久;2为带有起始时间
	@Column(name= "ruleTimeType", nullable = false,length=100)
	private int ruleTimeType;
	
	//最大积分值
	@Column(name= "maxScoreValue", length=20)
	private Integer maxScoreValue;
	
	//开始时间
	@Column(name= "startTime", nullable = false, length=30)
	private Date startTime;
	
	//结束时间
	@Column(name= "endTime", nullable = false, length=30)
	private Date endTime;
	
	//积分值1奖励前几人
	@Column(name= "scoreValue1Quantity", nullable = false, length=30)
	private Integer  scoreValue1Quantity;
	
	//积分值2奖励前几人
	@Column(name= "scoreValue2Quantity", nullable = false, length=30)
	private Integer  scoreValue2Quantity;
	
	//积分值3奖励前几人
	@Column(name= "scoreValue3Quantity", nullable = false, length=10)
	private Integer  scoreValue3Quantity;
	
	//积分值4奖励前几人
	@Column(name= "scoreValue4Quantity", nullable = false, length=10)
	private Integer  scoreValue4Quantity;
	
	//积分值5奖励前几人
	@Column(name= "scoreValue5Quantity", nullable = false, length=10)
	private Integer  scoreValue5Quantity;
	
	//积分值6奖励前几人
	@Column(name= "scoreValue6Quantity", nullable = false, length=10)
	private Integer  scoreValue6Quantity;
	
	//积分值7奖励前几人
	@Column(name= "scoreValue7Quantity", nullable = false, length=10)
	private Integer  scoreValue7Quantity;
	
	/**
	 * 扩展项
	 * 完善资料类为：头像(icon)、昵称(nickname)、性别(gender)、生日(birthday),出现多选时各类中间用|分割，如:icon|nickname
	 * 实名认证项为：身份证(identity)
	 * 绑定第三方账号为：微信(wechat)、微博(weibo)、QQ(qq)
	 * 
	 */
	@Column(name= "extensing", nullable = false, length=10)
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

	public Integer getScoreValue1() {
		return scoreValue1;
	}

	public void setScoreValue1(Integer scoreValue1) {
		this.scoreValue1 = scoreValue1;
	}

	public Integer getScoreValue2() {
		return scoreValue2;
	}

	public void setScoreValue2(Integer scoreValue2) {
		this.scoreValue2 = scoreValue2;
	}

	public Integer getScoreValue3() {
		return scoreValue3;
	}

	public void setScoreValue3(Integer scoreValue3) {
		this.scoreValue3 = scoreValue3;
	}

	public Integer getScoreValue4() {
		return scoreValue4;
	}

	public void setScoreValue4(Integer scoreValue4) {
		this.scoreValue4 = scoreValue4;
	}

	public Integer getScoreValue5() {
		return scoreValue5;
	}

	public void setScoreValue5(Integer scoreValue5) {
		this.scoreValue5 = scoreValue5;
	}

	public Integer getScoreValue6() {
		return scoreValue6;
	}

	public void setScoreValue6(Integer scoreValue6) {
		this.scoreValue6 = scoreValue6;
	}

	public Integer getScoreValue7() {
		return scoreValue7;
	}

	public void setScoreValue7(Integer scoreValue7) {
		this.scoreValue7 = scoreValue7;
	}

	public Integer getMinScoreValue() {
		return minScoreValue;
	}

	public void setMinScoreValue(Integer minScoreValue) {
		this.minScoreValue = minScoreValue;
	}

	public int getRuleTimeType() {
		return ruleTimeType;
	}

	public void setRuleTimeType(int ruleTimeType) {
		this.ruleTimeType = ruleTimeType;
	}

	public Integer getMaxScoreValue() {
		return maxScoreValue;
	}

	public void setMaxScoreValue(Integer maxScoreValue) {
		this.maxScoreValue = maxScoreValue;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getScoreValue1Quantity() {
		return scoreValue1Quantity;
	}

	public void setScoreValue1Quantity(Integer scoreValue1Quantity) {
		this.scoreValue1Quantity = scoreValue1Quantity;
	}

	public Integer getScoreValue2Quantity() {
		return scoreValue2Quantity;
	}

	public void setScoreValue2Quantity(Integer scoreValue2Quantity) {
		this.scoreValue2Quantity = scoreValue2Quantity;
	}

	public Integer getScoreValue3Quantity() {
		return scoreValue3Quantity;
	}

	public void setScoreValue3Quantity(Integer scoreValue3Quantity) {
		this.scoreValue3Quantity = scoreValue3Quantity;
	}

	public Integer getScoreValue4Quantity() {
		return scoreValue4Quantity;
	}

	public void setScoreValue4Quantity(Integer scoreValue4Quantity) {
		this.scoreValue4Quantity = scoreValue4Quantity;
	}

	public Integer getScoreValue5Quantity() {
		return scoreValue5Quantity;
	}

	public void setScoreValue5Quantity(Integer scoreValue5Quantity) {
		this.scoreValue5Quantity = scoreValue5Quantity;
	}

	public Integer getScoreValue6Quantity() {
		return scoreValue6Quantity;
	}

	public void setScoreValue6Quantity(Integer scoreValue6Quantity) {
		this.scoreValue6Quantity = scoreValue6Quantity;
	}

	public Integer getScoreValue7Quantity() {
		return scoreValue7Quantity;
	}

	public void setScoreValue7Quantity(Integer scoreValue7Quantity) {
		this.scoreValue7Quantity = scoreValue7Quantity;
	}

	public String getExtensing() {
		return extensing;
	}

	public void setExtensing(String extensing) {
		this.extensing = extensing;
	}
	
}
