package com.huatek.busi.model.phicom.member;

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

import com.huatek.busi.model.phicom.plusmember.PhiPlusGrade;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 会员特权管理实体类
 * 
 * @ClassName: PhiMemberPrivilege
 * @Description:
 * @author: jordan_li
 * @Email :
 * @date: 2018-01-22 21:00:55
 * @version: Windows 7
 */

@Entity
@Table(name = "phi_member_privilege")
public class PhiMemberPrivilege extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "privilege_id", nullable = true)
	private Long id;

	/** @Fields phiMemberGrade : 会员等级id */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "member_grade_id")
	private PhiMemberGrade phiMemberGrade;

	/** @Fields phiPlusGrade : PLUS会员编码ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "plus_id")
	private PhiPlusGrade phiPlusGrade;

	/** @Fields rightCode : 权限编码(全局唯一) */
	@Column(name = "right_code", nullable = false, length = 40)
	private String rightCode;

	/** @Fields rightName : 权限名称 */
	@Column(name = "right_name", nullable = false, length = 40)
	private String rightName;

	/** @Fields rightExplain : 权限说明 */
	@Column(name = "right_explain", nullable = false, length = 100)
	private String rightExplain;

	/** @Fields rightDeadline : 权限时间(0：永久(默认） 1：设置时间段） */
	@Column(name = "right_deadline", nullable = false, length = 100)
	private String rightDeadline;

	/** @Fields state : 是否开启( 0 : 否 ， 1 : 是 ) */
	@Column(name = "state", nullable = false)
	private Integer state;

	/** @Fields checkIn : 签到(0：否 ，1：是） */
	@Column(name = "check_in", nullable = false)
	private Integer checkIn;

	/** @Fields pay : 商城消费（0：否，1：是） */
	@Column(name = "pay", nullable = false)
	private Integer pay;

	/** @Fields comment : 评论（0：否 ，1：是） */
	@Column(name = "comment", nullable = false)
	private Integer comment;

	/** @Fields forum : 论坛（0：否，1：是） */
	@Column(name = "forum", nullable = false)
	private Integer forum;

	/** @Fields privilegeType : 权限类型（1: 消费返积分特权 , 2：生日特权） */
	@Column(name = "privilege_type", nullable = false, length = 256)
	private String privilegeType;

	/** @Fields doubleSet : 翻倍设置（大于1的数字，保留一位小数） */
	@Column(name = "double_set", nullable = false, precision = 18, scale = 1)
	private BigDecimal doubleSet;

	/** @Fields extraAdd : 额外增加积分数（大于1的整数） */
	@Column(name = "extra_add", nullable = false)
	private Integer extraAdd;

	/** @Fields scoreOrMutiply : 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分） */
	@Column(name = "score_or_mutiply", nullable = false)
	private Integer scoreOrMutiply;

	/** @Fields startTime : 开始时间 */
	@Column(name = "start_time", nullable = false)
	private Date startTime;

	/** @Fields endTime : 结束时间 */
	@Column(name = "end_time", nullable = false)
	private Date endTime;

	/** @Fields modifer : 修改人ID */
	@Column(name = "modifer", nullable = false)
	private Long modifer;

	/** @Fields modifierName : 修改人姓名 */
	@Column(name = "modifier_name", nullable = false, length = 100)
	private String modifierName;

	/** @Fields modifyTime : 修改时间 */
	@Column(name = "modify_time", nullable = false)
	private Date modifyTime;

	/** @Fields payScoreMultiple : 消费返积分倍数 */
	@Column(name = "pay_score_multiple", nullable = false, precision = 18, scale = 1)
	private BigDecimal payScoreMultiple;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PhiMemberGrade getPhiMemberGrade() {
		return phiMemberGrade;
	}

	public void setPhiMemberGrade(PhiMemberGrade phiMemberGrade) {
		this.phiMemberGrade = phiMemberGrade;
	}

	public PhiPlusGrade getPhiPlusGrade() {
		return phiPlusGrade;
	}

	public void setPhiPlusGrade(PhiPlusGrade phiPlusGrade) {
		this.phiPlusGrade = phiPlusGrade;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightExplain() {
		return rightExplain;
	}

	public void setRightExplain(String rightExplain) {
		this.rightExplain = rightExplain;
	}

	public String getRightDeadline() {
		return rightDeadline;
	}

	public void setRightDeadline(String rightDeadline) {
		this.rightDeadline = rightDeadline;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public BigDecimal getDoubleSet() {
		return doubleSet;
	}

	public void setDoubleSet(BigDecimal doubleSet) {
		this.doubleSet = doubleSet;
	}

	public Integer getExtraAdd() {
		return extraAdd;
	}

	public void setExtraAdd(Integer extraAdd) {
		this.extraAdd = extraAdd;
	}

	public Integer getScoreOrMutiply() {
		return scoreOrMutiply;
	}

	public void setScoreOrMutiply(Integer scoreOrMutiply) {
		this.scoreOrMutiply = scoreOrMutiply;
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

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Integer checkIn) {
		this.checkIn = checkIn;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getForum() {
		return forum;
	}

	public void setForum(Integer forum) {
		this.forum = forum;
	}

	public BigDecimal getPayScoreMultiple() {
		return payScoreMultiple;
	}

	public void setPayScoreMultiple(BigDecimal payScoreMultiple) {
		this.payScoreMultiple = payScoreMultiple;
	}

}
