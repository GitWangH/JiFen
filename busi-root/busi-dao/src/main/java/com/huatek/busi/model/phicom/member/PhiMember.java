package com.huatek.busi.model.phicom.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 代码自动生成model类.
 * 
 * @ClassName: PhiMember
 * @Description:
 * @author: Ken Bai
 * @Email : Ken_Bai@huatek.com
 * @date: 2017-12-18 15:33:14
 * @version: 1.0
 */

@Entity
@Table(name = "phi_member")
public class PhiMember extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "member_id", nullable = false)
	private Long memberId;

	/** 多个会员对应一个等级 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "member_grade_id")
	private PhiMemberGrade phiMembergrade;

	/** 一个会员对应多个订单 */
	/*
	 * @OneToMany(cascade = {CascadeType.REFRESH,
	 * CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "phiMember") private
	 * Set<PhiOrder> phiOrderList;
	 */

	/** @Fields orderCount : 兑换订单数量 */
	@Column(name = "order_count", nullable = true, length = 100)
	private int orderCount = 0;

	/** @Fields plusCode : plus会员 Code */
	@Column(name = "plus_code", nullable = true, length = 100)
	private String plusCode;

	/** @Fields memberGradeCode : 普通会员code */
	@Column(name = "member_grade_code", nullable = true, length = 100)
	private String memberGradeCode;

	/** @Fields enableScore : 可用积分 */
	@Column(name = "enable_score", nullable = false, precision = 18, scale = 4)
	private BigDecimal enableScore;

	/** @Fields enableScore : 冻结积分 */
	@Column(name = "disableScore", nullable = false, precision = 18, scale = 4)
	private BigDecimal disableScore = BigDecimal.ZERO;

	public BigDecimal getDisableScore() {
		return disableScore;
	}

	public void setDisableScore(BigDecimal disableScore) {
		this.disableScore = disableScore;
	}

	/** @Fields allScore : 累计积分 */
	@Column(name = "all_score", nullable = false, precision = 18, scale = 4)
	private BigDecimal allScore = new BigDecimal("0");

	/** @Fields createTime : 创建时间 */
	@Column(name = "create_time", nullable = true)
	private Date createTime;

	/** @Fields state : 状态(是否启用) */
	@Column(name = "state", nullable = true, length = 100)
	private String state;

	/** @Fields payCode : 支付密码 */
	@Column(name = "pay_code", nullable = true, length = 100)
	private String payCode;

	/** @Fields password : 登录密码 */
	@Column(name = "password", nullable = true, length = 100)
	private String password;

	/** @Fields userName :会员用户名称 */
	@Column(name = "user_name", nullable = true, length = 100)
	private String userName;

	/** @Fields realName :会员真实名称 */
	@Column(name = "real_name", nullable = true, length = 100)
	private String realName;

	/** @Fields sex : 性别 */
	@Column(name = "sex", nullable = false, length = 100)
	private String sex;

	/** @Fields portrait : 头像 */
	@Column(name = "portrait", length = 100)
	private String portrait;

	/** @Fields birthday : 生日 */
	@Column(name = "birthday", nullable = true)
	private Date birthday;

	/** @Fields tel : 手机号 */
	@Column(name = "tel", nullable = false, length = 100)
	private String tel;

	/** @Fields blacklist : 是否黑名单(1,是;0,否) */
	@Column(name = "blacklist", nullable = false, length = 100)
	private String blacklist;

	/** @Fields isplusMember : 是否是plus会员:( 1:是;0否 ) */
	@Column(name = "is_plusMember", nullable = true, length = 100)
	private String isplusMember = "0";

	/** @Fields validTime: plus会员有效期 */
	@Column(name = "valid_time", nullable = true, length = 100)
	private Date validTime;

	/** @Fields desc: 说明 */
	@Column(name = "desc_info", nullable = true, length = 100)
	private String descInfo;

	/** @Fields UId: 斐讯云账户id */
	@Column(name = "uid", nullable = true, length = 100)
	private Integer UId;

	/** @Fields checkin_day: 签到天数 */
	@Column(name = "checkin_day", nullable = true, length = 2)
	private Integer checkInDay;

	/** @Fields plusOpenDate: plus会员开通时间 */
	@Column(name = "plusMember_open_date", nullable = true, length = 2)
	private Date plusOpenDate;

	/** @Fields sendTime :发放时间 */
	@Column(name = "send_time", nullable = true, length = 20)
	private Date sendTime;

	/** @Fields last_check_time :最后签到日期 */
	@Column(name = "last_check_time", nullable = true, length = 20)
	private Date lastCheckTime;

	/** @Fields isDraw :是否抽奖 */
	@Column(name = "isDraw", nullable = true, length = 5)
	private String isDraw;

	/** @Fields drawTimesJ :九宫格抽奖次数 */
	@Column(name = "draw_times_j", nullable = true, length = 5)
	private Integer drawTimesJ;

	/** @Fields drawTimesD :大转盘抽奖次数 */
	@Column(name = "draw_times_d", nullable = true, length = 5)
	private Integer drawTimesD;

	/** @Fields isDraw :是否完成用户信息任务 */
	@Column(name = "is_finish_infotask", nullable = false, length = 11)
	private int isFinishInfoTask;

	/** @Fields plusYears :plus已经开通时间 */
	@Column(name = "plus_years", nullable = true, length = 11)
	private int plusYears;

	/** @Fields plusOpenType :plus会员开通类型：firstOpen:首次开通；renewOpen:续费开通 */
	@Column(name = "plus_open_type", nullable = true, length = 5)
	private String plusOpenType;

	/** @Fields plusRenewDate: plus会员续费时间 */
	@Column(name = "plus_renew_open_date", nullable = true, length = 100)
	private Date plusRenewDate;

	@OneToMany(mappedBy = "memberId", cascade = (CascadeType.ALL))
	private Set<PhiCouponsDetail> couponseDetailSet = new HashSet<PhiCouponsDetail>();

	@Transient
	private String keyNum;

	public Set<PhiCouponsDetail> getCouponseDetailSet() {
		return couponseDetailSet;
	}

	public void setCouponseDetailSet(Set<PhiCouponsDetail> couponseDetailSet) {
		this.couponseDetailSet = couponseDetailSet;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getPlusCode() {
		return plusCode;
	}

	public void setPlusCode(String plusCode) {
		this.plusCode = plusCode;
	}

	public Integer getDrawTimesJ() {
		return drawTimesJ;
	}

	public void setDrawTimesJ(Integer drawTimesJ) {
		this.drawTimesJ = drawTimesJ;
	}

	public Integer getDrawTimesD() {
		return drawTimesD;
	}

	public void setDrawTimesD(Integer drawTimesD) {
		this.drawTimesD = drawTimesD;
	}

	public String getMemberGradeCode() {
		return memberGradeCode;
	}

	public void setMemberGradeCode(String memberGradeCode) {
		this.memberGradeCode = memberGradeCode;
	}

	public BigDecimal getEnableScore() {
		return enableScore;
	}

	public void setEnableScore(BigDecimal enableScore) {
		this.enableScore = enableScore;
	}

	public BigDecimal getAllScore() {
		return allScore;
	}

	public void setAllScore(BigDecimal allScore) {
		this.allScore = allScore;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public Integer getCheckInDay() {
		return checkInDay;
	}

	public void setCheckInDay(Integer checkInDay) {
		this.checkInDay = checkInDay;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}

	/*
	 * public Set<PhiOrder> getPhiOrderList() { return phiOrderList; }
	 * 
	 * public void setPhiOrderList(Set<PhiOrder> phiOrderList) {
	 * this.phiOrderList = phiOrderList; }
	 */

	public PhiMemberGrade getPhiMembergrade() {
		return phiMembergrade;
	}

	public void setPhiMembergrade(PhiMemberGrade phiMembergrade) {
		this.phiMembergrade = phiMembergrade;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public String getIsplusMember() {
		return isplusMember;
	}

	public void setIsplusMember(String isplusMember) {
		this.isplusMember = isplusMember;
	}

	public Integer getUId() {
		return UId;
	}

	public void setUId(Integer uId) {
		UId = uId;
	}

	public String getDescInfo() {
		return descInfo;
	}

	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}

	public Date getPlusOpenDate() {
		return plusOpenDate;
	}

	public void setPlusOpenDate(Date plusOpenDate) {
		this.plusOpenDate = plusOpenDate;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public String getIsDraw() {
		return isDraw;
	}

	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}

	public int getIsFinishInfoTask() {
		return isFinishInfoTask;
	}

	public void setIsFinishInfoTask(int isFinishInfoTask) {
		this.isFinishInfoTask = isFinishInfoTask;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return memberId;
	}

	@Override
	public void setId(Long memberId) {
		this.memberId = memberId;
	}

	public int getPlusYears() {
		return plusYears;
	}

	public void setPlusYears(int plusYears) {
		this.plusYears = plusYears;
	}

	public String getPlusOpenType() {
		return plusOpenType;
	}

	public void setPlusOpenType(String plusOpenType) {
		this.plusOpenType = plusOpenType;
	}

	public Date getPlusRenewDate() {
		return plusRenewDate;
	}

	public void setPlusRenewDate(Date plusRenewDate) {
		this.plusRenewDate = plusRenewDate;
	}

	public String getKeyNum() {
		return keyNum;
	}

	public void setKeyNum(String keyNum) {
		this.keyNum = keyNum;
	}

}
