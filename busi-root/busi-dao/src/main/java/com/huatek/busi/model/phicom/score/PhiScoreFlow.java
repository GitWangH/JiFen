package com.huatek.busi.model.phicom.score;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: PhiScoreFlow
 * @Description:
 * @author: Ken Bai
 * @Email : Ken_Bai@huatek.com
 * @date: 2018-01-08 13:35:12
 * @version: 1.0
 */

@Entity
@Table(name = "phi_score_flow")
public class PhiScoreFlow extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "score_flow_id", nullable = false)
	public long id;

	@Column(name = "member_id", nullable = false)
	public long memberId;

	@Column(name = "member_name", nullable = false)
	public String memberName;

	@Column(name = "score", nullable = false)
	public BigDecimal score;

	@Column(name = "source_platform", nullable = false)
	public String sourcePlatform;

	@Column(name = "score_action", nullable = false)
	public String scoreAction;

	@Column(name = "score_type", nullable = false)
	public String scoreType;

	@Column(name = "create_time", nullable = false)
	public Date createTime;
	//订单编号
	@Column(name = "order_code", nullable = false)
	public String orderCode;

	@Column(name = "task_name", nullable = false)
	public String taskName;

	@Column(name = "task_id", nullable = false)
	public Long taskId;
	//积分是否可用 1表示可用，0表示冻结（不可用）
	@Column(name = "isEnable", nullable = false)
	public Integer isEnable=1;

	//订单是否退款，0表示未退款，1表示已退款
	@Column(name = "isRefund", nullable = false)
	public Integer isRefund=0;
	
	//积分可用时间，目前只针对消费积分10天后积分可用情况
	@Column(name = "enableTime", nullable = false)
	public Date enableTime;
	
	//1元=？积分值
	@Column(name = "proportion", nullable = false)
	public Integer proportion;
	
	//会员特权类型（0翻倍 1额外）
	@Column(name = "memberPowerType", nullable = false)
	public String memberPowerType;
	
	//会员特权设定值
	@Column(name = "memberPowerValue", nullable = false, length = 50)
	public String memberPowerValue;
	
	
	//plus会员特权类型（0翻倍 1额外）
	@Column(name = "plusPowerType", nullable = false)
	public String plusPowerType;
	
		
	//plus会员特权设定值
	@Column(name = "plusPowerValue", nullable = false, length = 50)
	public String plusPowerValue;
	
	//生日会员特权类型（0翻倍 1额外）
	@Column(name = "birthdayType", nullable = false)
	public String birthdayType;
	
	//生日会员特权设定值
	@Column(name = "birthdayValue", nullable = false,length = 50)
	public String birthdayValue;
	
	//退款单号
	@Column(name = "refundCode", nullable = false,length = 50)
	public String refundCode;
	
	//退款金额
	@Column(name = "refundMoney", nullable = false,length = 50)
	public BigDecimal refundMoney;
	
	
	
	
	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getSourcePlatform() {
		return sourcePlatform;
	}

	public void setSourcePlatform(String sourcePlatform) {
		this.sourcePlatform = sourcePlatform;
	}

	public String getScoreAction() {
		return scoreAction;
	}

	public void setScoreAction(String scoreAction) {
		this.scoreAction = scoreAction;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}



	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Date getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(Integer isRefund) {
		this.isRefund = isRefund;
	}

	public Integer getProportion() {
		return proportion;
	}

	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}

	public String getMemberPowerType() {
		return memberPowerType;
	}

	public void setMemberPowerType(String memberPowerType) {
		this.memberPowerType = memberPowerType;
	}

	public String getMemberPowerValue() {
		return memberPowerValue;
	}

	public void setMemberPowerValue(String memberPowerValue) {
		this.memberPowerValue = memberPowerValue;
	}

	public String getPlusPowerType() {
		return plusPowerType;
	}

	public void setPlusPowerType(String plusPowerType) {
		this.plusPowerType = plusPowerType;
	}

	public String getPlusPowerValue() {
		return plusPowerValue;
	}

	public void setPlusPowerValue(String plusPowerValue) {
		this.plusPowerValue = plusPowerValue;
	}

	public String getBirthdayType() {
		return birthdayType;
	}

	public void setBirthdayType(String birthdayType) {
		this.birthdayType = birthdayType;
	}

	public String getBirthdayValue() {
		return birthdayValue;
	}

	public void setBirthdayValue(String birthdayValue) {
		this.birthdayValue = birthdayValue;
	}

	public String getRefundCode() {
		return refundCode;
	}

	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	
	
}
