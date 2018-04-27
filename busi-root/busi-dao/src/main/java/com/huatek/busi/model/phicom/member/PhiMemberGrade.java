package com.huatek.busi.model.phicom.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: PhiMemberGrade
 * @Description:
 * @author: Ken Bai
 * @Email : Ken_Bai@huatek.com
 * @date: 2017-12-25 17:52:35
 * @version: 1.0
 */

@Entity
@Table(name = "phi_member_grade")
public class PhiMemberGrade extends BaseEntity {

	private static final long serialVersionUID = -3577960144275172687L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "member_grade_id", nullable = false)
	private Long id;

	/** 一个等级对应多个会员 *//*
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "phiMembergrade")
	private Set<PhiMember> phiMemberList;*/

	/** @Fields memberGradeCode : 会员等级Code */
	@Column(name = "member_grade_code", nullable = true, length = 100)
	private String memberGradeCode;

	/** @Fields memberGrade : 会员等级 */
	@Column(name = "member_grade", nullable = true, length = 100)
	private String memberGrade;

	/** @Fields creatorId : 创建人id */
	@Column(name = "creator_id", nullable = true, length = 100)
	private String creatorId;

	/** @Fields createTime: 创建时间 */
	@Column(name = "create_time", nullable = true, length = 65535)
	private Date createTime;

	/** @Fields remark : 备注 */
	@Column(name = "remark", nullable = true, length = 65535)
	private String remark;

	/** @Fields image : 图标 */
	@Column(name = "image", length = 100)
	private String image;

	/** @Fields scoreMax : 积分上限 */
	@Column(name = "score_max", nullable = true, precision = 18, scale = 4)
	private BigDecimal scoreMax;

	/** @Fields scoreMin : 积分下限 */
	@Column(name = "score_min", nullable = true, precision = 18, scale = 4)
	private BigDecimal scoreMin;

	/** @Fields validState : 是否有效(1,有效、0，无效) */
	@Column(name = "valid_state", nullable = true, length = 100)
	private String validState;

	/** @Fields conditions_meet : 满足条件 */
	@Column(name = "conditions_meet", nullable = false, length = 100)
	private String conditionsMeet = "<= 经验积分 <";

	/** @Fields scoreMultiple : 消费返积分倍数(会员特权模块维护) */
	@Column(name = "score_multiple", nullable = true, precision = 18, scale = 1)
	private BigDecimal scoreMultiple;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberGradeCode() {
		return memberGradeCode;
	}

	public void setMemberGradeCode(String memberGradeCode) {
		this.memberGradeCode = memberGradeCode;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(BigDecimal scoreMax) {
		this.scoreMax = scoreMax;
	}

	public BigDecimal getScoreMin() {
		return scoreMin;
	}

	public void setScoreMin(BigDecimal scoreMin) {
		this.scoreMin = scoreMin;
	}

	public String getValidState() {
		return validState;
	}

	public void setValidState(String validState) {
		this.validState = validState;
	}

	public String getConditionsMeet() {
		return conditionsMeet;
	}

	public void setConditionsMeet(String conditionsMeet) {
		this.conditionsMeet = conditionsMeet;
	}

	public BigDecimal getScoreMultiple() {
		return scoreMultiple;
	}

	public void setScoreMultiple(BigDecimal scoreMultiple) {
		this.scoreMultiple = scoreMultiple;
	}

}
