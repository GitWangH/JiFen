package com.huatek.frame.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: FwAccount
  * @Description: 
  * @author: arno
  * @date: 2016-04-06 16:16:09
  * @version: 1.0
  */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "fw_account")
public class FwAccount extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name= "ACCT_ID", nullable = true )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
 	private Long id;
 
    
	/** @Fields acctName : 职员姓名*/ 
	@Column(name= "ACCT_NAME", nullable = true, length=50 )
    private String acctName;
    
    
	/** @Fields userName : 职员代码*/ 
	@Column(name= "USER_NAME", nullable = true, length=50 )
    private String userName;
    
    
	/** @Fields acctPwd : 密码*/ 
	@Column(name= "ACCT_PWD", nullable = true, length=50 )
    private String acctPwd;
    
    
	/** @Fields status : 状态*/ 
	@Column(name= "STATUS", nullable = false, length=2 )
    private String status;
    
    
//	/** @Fields lastLoginTime : 上次登录时间 */
//	@Column(name= "LAST_LOGIN_TIME", nullable = false)
//    private Date lastLoginTime;
    
    
	/** @Fields isLocked : 是否锁定 */
	@Column(name= "IS_LOCKED", nullable = false)
    private Long isLocked;
    
    
	/** @Fields lockedTime : 锁定时间 */
	@Column(name= "LOCKED_TIME", nullable = false)
    private Date lockedTime;
	
	@NotNull(message="机构不能为空")
	@ManyToOne
	@JoinColumn(name = "org_id")
	private FwOrg fwOrg;
    
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private FwDepartment fwDepartment;
	/** @Fields isLocked : 是否锁定 */
	@Column(name= "IDENTITY_CARD_NO", nullable = false)
    private String identityCardNo;
	/** @Fields email : 邮箱 */
	@Column(name= "email", nullable = false)
    private String email;
	/** @Fields phone : 电话 */
	@Column(name= "phone", nullable = false)
    private String phone;
	
	@Column(name= "tenant_id", nullable = false)
    private Long tenantId;
	/** @Fields sex : 性别 */
	@Column(name= "SEX", nullable = false)
    private Integer sex;
	/** @Fields isManager : 是否管理员 */
	@Column(name= "IS_MANAGER", nullable = false)
	private Integer isManager;
	
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setAcctName(String acctName){
        this.acctName = acctName;
    }
      
    public String getAcctName(){
        return this.acctName;
    }
      
    public void setUserName(String userName){
        this.userName = userName;
    }
      
    public String getUserName(){
        return this.userName;
    }
      
    public void setAcctPwd(String acctPwd){
        this.acctPwd = acctPwd;
    }
      
    public String getAcctPwd(){
        return this.acctPwd;
    }
      
    public void setStatus(String status){
        this.status = status;
    }
      
    public String getStatus(){
        return this.status;
    }
      
//    public void setLastLoginTime(Date lastLoginTime){
//        this.lastLoginTime = lastLoginTime;
//    }
//      
//    public Date getLastLoginTime(){
//        return this.lastLoginTime;
//    }
      
    public void setIsLocked(Long isLocked){
        this.isLocked = isLocked;
    }
      
    public Long getIsLocked(){
        return this.isLocked;
    }
      
    public void setLockedTime(Date lockedTime){
        this.lockedTime = lockedTime;
    }
      
    public Date getLockedTime(){
        return this.lockedTime;
    }
   

	

	public FwOrg getFwOrg() {
		return fwOrg;
	}

	public void setFwOrg(FwOrg fwOrg) {
		this.fwOrg = fwOrg;
	}

	public FwDepartment getFwDepartment() {
		return fwDepartment;
	}

	public void setFwDepartment(FwDepartment fwDepartment) {
		this.fwDepartment = fwDepartment;
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
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

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}
	
}
