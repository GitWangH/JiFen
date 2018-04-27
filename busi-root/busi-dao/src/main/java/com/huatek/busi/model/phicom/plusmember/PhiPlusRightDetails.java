package com.huatek.busi.model.phicom.plusmember;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiPlusRightDetails
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:45:06
  * @version: 1.0
  */

@Entity
@Table(name = "phi_plus_right_details")
public class PhiPlusRightDetails extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "plus_right_details_id", nullable = false)
	private Long id;
	
	/** @Fields maxscorevalue :最大积分值*/ 
	@Column(name= "maxScoreValue", nullable = true, length=20)
    private String maxscorevalue;
	
	/** @Fields minscorevalue :最小积分值*/ 
	@Column(name= "minScoreValue", nullable = true, length=20)
    private String minscorevalue;
    
	/** @Fields scoreValue1 :积分值1(额外积分数值)*/ 
	@Column(name= "scoreValue1", nullable = true, length=20)
	private int scoreValue1;
	
	/** @Fields scoreValue2 :倍数（翻倍时）*/ 
	@Column(name= "scoreValue2", nullable = true, length=20)
	private int scoreValue2;
	
	//积分值3
	@Column(name= "scoreValue3", nullable = true, length=20)
	private int scoreValue3;
	
	//积分值4
	@Column(name= "scoreValue4", nullable = true, length=20)
	private int scoreValue4;
	
	//积分值5
	@Column(name= "scoreValue5", nullable = true, length=20)
	private int scoreValue5;
	
	//积分值6
	@Column(name= "scoreValue6", nullable = true, length=20)
	private int scoreValue6;
	
	//积分值7
	@Column(name= "scoreValue7", nullable = true, length=20)
	private int scoreValue7;

	
	//积分值1奖励前几人
	@Column(name= "scoreValue1Quantity", nullable = true, length=30)
	private BigDecimal  scoreValue1Quantity;
	
	//积分值2奖励前几人
	@Column(name= "scoreValue2Quantity", nullable = true, length=30)
	private BigDecimal  scoreValue2Quantity;
	
	//积分值3奖励前几人
	@Column(name= "scoreValue3Quantity", nullable = true, length=10)
	private BigDecimal  scoreValue3Quantity;
	
	//积分值4奖励前几人
	@Column(name= "scoreValue4Quantity", nullable = true, length=10)
	private BigDecimal  scoreValue4Quantity;
	
	//积分值5奖励前几人
	@Column(name= "scoreValue5Quantity", nullable = true, length=10)
	private BigDecimal  scoreValue5Quantity;
	
	//积分值6奖励前几人
	@Column(name= "scoreValue6Quantity", nullable = true, length=10)
	private BigDecimal  scoreValue6Quantity;
	
	//积分值7奖励前几人
	@Column(name= "scoreValue7Quantity", nullable = true, length=10)
	private BigDecimal  scoreValue7Quantity;
    
	/** @Fields starttime : 开始时间 */ 
	@Column(name= "startTime", nullable = true, length=20)
    private Date starttime;
    
	/** @Fields scoreOrMutiply : 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分） */ 
	@Column(name= "score_or_mutiply", nullable = true, length=20)
    private int scoreOrMutiply;
    
	/** @Fields plusRightId : PLUS会员积分特权id */ 
	@OneToOne
	@JoinColumn(name= "plus_right_id")
    private PhiPlusRight phiPlusRight;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaxscorevalue() {
		return maxscorevalue;
	}
	public void setMaxscorevalue(String maxscorevalue) {
		this.maxscorevalue = maxscorevalue;
	}
	public String getMinscorevalue() {
		return minscorevalue;
	}
	public void setMinscorevalue(String minscorevalue) {
		this.minscorevalue = minscorevalue;
	}
	
	public int getScoreValue1() {
		return scoreValue1;
	}
	public void setScoreValue1(int scoreValue1) {
		this.scoreValue1 = scoreValue1;
	}
	public int getScoreValue2() {
		return scoreValue2;
	}
	public void setScoreValue2(int scoreValue2) {
		this.scoreValue2 = scoreValue2;
	}
	public int getScoreValue3() {
		return scoreValue3;
	}
	public void setScoreValue3(int scoreValue3) {
		this.scoreValue3 = scoreValue3;
	}
	public int getScoreValue4() {
		return scoreValue4;
	}
	public void setScoreValue4(int scoreValue4) {
		this.scoreValue4 = scoreValue4;
	}
	public int getScoreValue5() {
		return scoreValue5;
	}
	public void setScoreValue5(int scoreValue5) {
		this.scoreValue5 = scoreValue5;
	}
	public int getScoreValue6() {
		return scoreValue6;
	}
	public void setScoreValue6(int scoreValue6) {
		this.scoreValue6 = scoreValue6;
	}
	public int getScoreValue7() {
		return scoreValue7;
	}
	public void setScoreValue7(int scoreValue7) {
		this.scoreValue7 = scoreValue7;
	}

	public BigDecimal getScoreValue1Quantity() {
		return scoreValue1Quantity;
	}
	public void setScoreValue1Quantity(BigDecimal scoreValue1Quantity) {
		this.scoreValue1Quantity = scoreValue1Quantity;
	}
	public BigDecimal getScoreValue2Quantity() {
		return scoreValue2Quantity;
	}
	public void setScoreValue2Quantity(BigDecimal scoreValue2Quantity) {
		this.scoreValue2Quantity = scoreValue2Quantity;
	}
	public BigDecimal getScoreValue3Quantity() {
		return scoreValue3Quantity;
	}
	public void setScoreValue3Quantity(BigDecimal scoreValue3Quantity) {
		this.scoreValue3Quantity = scoreValue3Quantity;
	}
	public BigDecimal getScoreValue4Quantity() {
		return scoreValue4Quantity;
	}
	public void setScoreValue4Quantity(BigDecimal scoreValue4Quantity) {
		this.scoreValue4Quantity = scoreValue4Quantity;
	}
	public BigDecimal getScoreValue5Quantity() {
		return scoreValue5Quantity;
	}
	public void setScoreValue5Quantity(BigDecimal scoreValue5Quantity) {
		this.scoreValue5Quantity = scoreValue5Quantity;
	}
	public BigDecimal getScoreValue6Quantity() {
		return scoreValue6Quantity;
	}
	public void setScoreValue6Quantity(BigDecimal scoreValue6Quantity) {
		this.scoreValue6Quantity = scoreValue6Quantity;
	}
	public BigDecimal getScoreValue7Quantity() {
		return scoreValue7Quantity;
	}
	public void setScoreValue7Quantity(BigDecimal scoreValue7Quantity) {
		this.scoreValue7Quantity = scoreValue7Quantity;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	public PhiPlusRight getPhiPlusRight() {
		return phiPlusRight;
	}
	public void setPhiPlusRight(PhiPlusRight phiPlusRight) {
		this.phiPlusRight = phiPlusRight;
	}
	public int getScoreOrMutiply() {
		return scoreOrMutiply;
	}
	public void setScoreOrMutiply(int scoreOrMutiply) {
		this.scoreOrMutiply = scoreOrMutiply;
	}
      
    

}
