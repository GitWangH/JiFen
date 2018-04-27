package com.huatek.frame.session.data;

import java.math.BigDecimal;
import java.util.Date;

public class MemberInfo {
	

	/** id  @Fields memberId : 会员id*/
	private Long id;
	/** @Fields plusCode : plus会员 Code */
	private String plusCode;
	/** @Fields memberGradeCode : 普通会员code */
	private String memberGradeCode;
	/** @Fields enableScore : 可用积分 */
	private BigDecimal enableScore = new BigDecimal("0");
	/** @Fields allScore : 累计积分 */
	private BigDecimal allScore = new BigDecimal("0");
	/** @Fields createTime : 创建时间 */
	private String createTime;
	/** @Fields state : 状态(是否启用) */
	private String state;
	/** @Fields payCode : 支付密码 */
	private String payCode;
	/** @Fields password : 登录密码 */
	private String password;
	/** @Fields userName :会员用户名称 */
	private String userName;
	/** @Fields realName :会员真实名称 */
	private String realName;
	/** @Fields sex : 性别 */
	private String sex;
	/** @Fields portrait : 头像 */
	private String portrait;
	/** @Fields birthday : 生日 */
	private Date birthday;
	/** @Fields tel : 手机号 */
	private String tel;
	/** @Fields blacklist : 是否黑名单 */
	private String blacklist;
	
	/** @Fields IsplusMember : 是否是plus会员:( 1:是;0否 )*/
	private String IsplusMember = "0";
	
	/**@Fields  validTime:  plus会员有效期 */
	private String  validTime;
	
	/**@Fields  UId:  斐讯云账户id */
	private int  UId;
	
	/**@Fields  desc_info:  说明*/
	private String   descInfo;
	
	/**@Fields  plusOpenDate:  plus会员开通时间*/
	private String plusOpenDate;
	
	public Object[] getCnee_info() {
		return cnee_info;
	}

	public void setCnee_info(Object[] cnee_info) {
		this.cnee_info = cnee_info;
	}

	private Object[] cnee_info;
	
	private String memberGrade;
	
	private Integer orderCount;
	
	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlusCode() {
		return plusCode;
	}

	public void setPlusCode(String plusCode) {
		this.plusCode = plusCode;
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


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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



	public String getTel() {
		return tel;
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

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public String getIsplusMember() {
		return IsplusMember;
	}

	public void setIsplusMember(String isplusMember) {
		IsplusMember = isplusMember;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public int getUId() {
		return UId;
	}

	public void setUId(int uId) {
		UId = uId;
	}

	public String getDescInfo() {
		return descInfo;
	}

	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPlusOpenDate() {
		return plusOpenDate;
	}

	public void setPlusOpenDate(String plusOpenDate) {
		this.plusOpenDate = plusOpenDate;
	}
}