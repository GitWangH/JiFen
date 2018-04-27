package com.huatek.frame.session.data;

import java.util.Map;

/***
 * 本类用于保存到memcache. 所以数据结构尽量简单,其次不宜放置经常变化的数据，以免要更新和保存数据..
 * 
 * @author winner pan.
 *
 */
public class UserInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * sessionId 登录会话ID.
	 */
	private String sessionId;

	/***
	 * 组织ID.
	 */
	private Long orgId;

	/***
	 * 机构名称.
	 */
	private String orgName;

	/***
	 * 机构代码.
	 */
	private String orgCode;




	/**
	 * 账户类别
	 */
	private String acctType;



	/**
	 * 组织类型 集团，项目，项目办，中心实验室，监理，标段
	 */
	public String orgType;
/*集团id*/
	
	private  Long groupId;
	/*集团名称*/
	private String groupName;
	
	/*组织简称*/
	private String orgShortName;
	
		
	
	
	/***
	 * 用户账号.
	 */
	private String acctName;

	/***
	 * 用户姓名.
	 */
	private String userName;
	/***
	 * 用户Id.
	 */
	private Long id;

	/**
	 * 部门Id
	 */
	private  Long deptId;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 身份证号
	 */
	private String idNumber;

	/***
	 * 角色列表.
	 */
	private RoleInfo[] roleInfos;
	
	/***
	 * 多租户id.
	 */
	private Long tenantId;

	/***
	 * 数据角色ID列表.
	 */
	private DataRoleInfo[] dataRoleInfo;

	/***
	 * 数据权限列表.
	 */
	Map<String, Map<String, Map<String, FieldAuthority>>> userAuthorityData;
	
	/**
	 * 当前所选项目ID
	 */
	private Long currProId;
	
	/**
	 * 当前所选项目
	 */
	private String currProName;
	
	private String email;
	
	private String phone;
	
	private Integer sex;
	private Boolean fromApp=false;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public RoleInfo[] getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(RoleInfo[] roleInfos) {
		this.roleInfos = roleInfos;
	}

	public DataRoleInfo[] getDataRoleInfo() {
		return dataRoleInfo;
	}

	public void setDataRoleInfo(DataRoleInfo[] dataRoleInfo) {
		this.dataRoleInfo = dataRoleInfo;
	}

	public Map<String, Map<String, Map<String, FieldAuthority>>> getUserAuthorityData() {
		return userAuthorityData;
	}

	public void setUserAuthorityData(
			Map<String, Map<String, Map<String, FieldAuthority>>> userAuthorityData) {
		this.userAuthorityData = userAuthorityData;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getCurrProId() {
		return currProId;
	}

	public void setCurrProId(Long currProId) {
		this.currProId = currProId;
	}

	public String getCurrProName() {
		return currProName;
	}

	public void setCurrProName(String currProName) {
		this.currProName = currProName;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Boolean getFromApp() {
		return fromApp;
	}

	public void setFromApp(Boolean fromApp) {
		this.fromApp = fromApp;
	}

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	
}
