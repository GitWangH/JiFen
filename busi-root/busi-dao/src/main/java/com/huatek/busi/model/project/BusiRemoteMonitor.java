package com.huatek.busi.model.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiRemoteMonitor
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-16 13:37:43
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_remote_monitor")
public class BusiRemoteMonitor extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields tendersId : 标段 */
	@ManyToOne
	@JoinColumn(name= "TENDERS_ID", nullable = false)
    private BusiFwOrg tenders;
    
    
	/** @Fields monitorType : 监控类型*/ 
	@Column(name= "MONITOR_TYPE", nullable = false, length=50 )
    private String monitorType;
    
    
	/** @Fields remoteAddress : 远程地址*/ 
	@Column(name= "REMOTE_ADDRESS", nullable = false, length=255 )
    private String remoteAddress;
	
	/** @Fields remoteAddress : 手机访问IP*/ 
	@Column(name= "REMOTE_ADDRESS_PHONE", nullable = false, length=255 )
	private String remoteAddressPhone;
    
    
	/** @Fields acctName : 用户名*/ 
	@Column(name= "ACCT_NAME", nullable = false, length=255 )
    private String acctName;
	
	/** @Fields firmCompany : 厂商名称*/ 
	@Column(name= "FIRM_COMPANY", nullable = false, length=30 )
	private String firmCompany;
    
    
	/** @Fields acctPass : 密码*/ 
	@Column(name= "ACCT_PASS", nullable = false, length=255 )
    private String acctPass;
    
    
	/** @Fields md5 : MD5码*/ 
	@Column(name= "MD5", nullable = false, length=255 )
    private String md5;
    
    
	/** @Fields base64 : BASE64加密*/ 
	@Column(name= "BASE64", nullable = false, length=255 )
    private String base64;
    
    
	/** @Fields 3des : 3DES加密*/ 
	@Column(name= "3DES", nullable = false, length=255 )
    private String threeDes;
	
	/** @Fields tenantId : 租户ID*/ 
	@Column(name= "TENANT_ID", nullable = false, length=255 )
	private Long tenantId;
	
	/** @Fields creator : 创建人*/ 
	@Column(name= "CREATOR", nullable = false, length=255 )
	private Long creator;
	
	/** @Fields createDate : 创建时间*/ 
	@Column(name= "CREATE_TIME", nullable = false, length=255 )
	private Date createDate;
	
	/** @Fields createDate : 创建时间*/ 
	@Column(name= "REMARK", nullable = false, length=255 )
	private String remark;
	
	/** @Fields title : 标题*/ 
	@Column(name= "TITLE", nullable = false, length=255 )
	private String title;
    
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public BusiFwOrg getTenders() {
		return tenders;
	}

	public void setTenders(BusiFwOrg tenders) {
		this.tenders = tenders;
	}

	public void setMonitorType(String monitorType){
        this.monitorType = monitorType;
    }
      
    public String getMonitorType(){
        return this.monitorType;
    }
      
    public void setRemoteAddress(String remoteAddress){
        this.remoteAddress = remoteAddress;
    }
      
    public String getRemoteAddress(){
        return this.remoteAddress;
    }
      
    public void setAcctName(String acctName){
        this.acctName = acctName;
    }
      
    public String getAcctName(){
        return this.acctName;
    }
      
    public void setAcctPass(String acctPass){
        this.acctPass = acctPass;
    }
      
    public String getAcctPass(){
        return this.acctPass;
    }
      
    public void setMd5(String md5){
        this.md5 = md5;
    }
      
    public String getMd5(){
        return this.md5;
    }
      
    public void setBase64(String base64){
        this.base64 = base64;
    }
      
    public String getBase64(){
        return this.base64;
    }

	public String getThreeDes() {
		return threeDes;
	}

	public void setThreeDes(String threeDes) {
		this.threeDes = threeDes;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemoteAddressPhone() {
		return remoteAddressPhone;
	}

	public void setRemoteAddressPhone(String remoteAddressPhone) {
		this.remoteAddressPhone = remoteAddressPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFirmCompany() {
		return firmCompany;
	}

	public void setFirmCompany(String firmCompany) {
		this.firmCompany = firmCompany;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
