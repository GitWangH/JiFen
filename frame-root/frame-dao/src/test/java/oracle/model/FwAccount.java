package oracle.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.BaseEntity;


/***
 * 系统用户表. 用于保存基本的用户信息. 该表不不会单独用于管理,除非是框架演示. 业务系统的用户管理应该继承于本实体.
 * 
 * @author winner pan.
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "FW_ACCOUNT")
public class FwAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ACCT_ID", nullable = false)
	@SequenceGenerator(name = "my_seq", sequenceName = "S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	private Long id;

	@Size(max = 50)
	@Column(name = "ACCT_NAME", nullable = false)
	private String acctName;

	@Size(max = 50)
	@Column(name = "USER_NAME", nullable = true)
	private String userName;

	// ACCT_PWD
	@Size(max = 50)
	@Column(name = "ACCT_PWD", nullable = false)
	private String acctPwd;
	// STATUS
	@Size(max = 2)
	@Column(name = "STATUS", nullable = false)
	private String status;

	// LAST_LOGIN_TIME
	@Column(name = "LAST_LOGIN_TIME")
	private Date lastLoginTime;

	// IS_LOCKED
	@Column(name = "IS_LOCKED")
	private Integer isLocked;

	// LOCKED_TIME
	@Column(name = "LOCKED_TIME")
	private Date lockedTime;

	// GENDER
	@Column(name = "GENDER")
	private Integer gender;

	// BIRTHDAY
	@Column(name = "BIRTHDAY")
	private Date birthday;

	// EMAIL
	@Column(name = "EMAIL")
	private String email;

	// PHONE
	@Column(name = "PHONE")
	private String phone;

	// CREATE_TIME
	@Column(name = "CREATE_TIME")
	private Date createTime;

	// UPDATE_TIME
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	

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
	@OneToMany(mappedBy = "fwAccount", fetch = FetchType.LAZY)
	private Set<FwAccountRole> fwAccountRoleSet;
	

	public Set<FwAccountRole> getFwAccountRoleSet() {
		return fwAccountRoleSet;
	}

	public void setFwAccountRoleSet(Set<FwAccountRole> fwAccountRoleSet) {
		this.fwAccountRoleSet = fwAccountRoleSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.intValue();
		result = prime * result
				+ ((acctName == null) ? 0 : acctName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FwAccount))
			return false;
		FwAccount other = (FwAccount) obj;
		if (!id.equals(other.id))
			return false;
		if (acctName == null) {
			if (other.acctName != null)
				return false;
		} else if (!acctName.equals(other.acctName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FW_ACCOUNT [id=" + id + ", name=" + this.acctName + "]";
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

	public String getAcctPwd() {
		return acctPwd;
	}

	public void setAcctPwd(String acctPwd) {
		this.acctPwd = acctPwd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public Date getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
