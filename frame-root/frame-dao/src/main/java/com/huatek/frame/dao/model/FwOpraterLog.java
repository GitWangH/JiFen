package com.huatek.frame.dao.model;

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

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: FwOpraterLog
  * @Description: 
  * @author: arno
  * @date: 2016-04-11 18:32:57
  * @version: 1.0
  */

@Entity
@Table(name = "fw_action_log")
public class FwOpraterLog extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ACT_LOG_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields actionTime : 操作时间 */
	@Column(name= "ACTION_TIME", nullable = true)
    private Date actionTime;
    
    
	/** @Fields clientIp : 客户端IP*/ 
	@Column(name= "CLIENT_IP", nullable = false, length=40 )
    private String clientIp;
    
    
	/** @Fields clientPort : 端口 */
	@Column(name= "CLIENT_PORT", nullable = false)
    private Long clientPort;
    
    
    /** @Fields fwSource : 菜单ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "ACTION_ID")
    private FwSource fwSource;
    
    
    /** @Fields fwAccount : 用户ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "ACCT_ID")
    private FwAccount fwAccount;
	/** @Fields clientPort : 端口 */
	@Column(name= "USER_AGENT", nullable = false)
    private String userAgent;
	
	/** @Fields ORG_NAME : 所在机构名称*/
    @Column(name= "ORG_NAME", nullable = false)
    private String orgName;
    
    /** @Fields clientPort : 上级机构名称 */
    @Column(name= "PARENTORG_NAME", nullable = false)
    private String parentOrgName;
    /** @Fields clientPort : 上级机构名称 */
    @Column(name= "REMARK", nullable = false)
    private String msg;
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setActionTime(Date actionTime){
        this.actionTime = actionTime;
    }
      
    public Date getActionTime(){
        return this.actionTime;
    }
      
    public void setClientIp(String clientIp){
        this.clientIp = clientIp;
    }
      
    public String getClientIp(){
        return this.clientIp;
    }
      
    public void setClientPort(Long clientPort){
        this.clientPort = clientPort;
    }
      
    public Long getClientPort(){
        return this.clientPort;
    }
      
    public void setFwSource(FwSource fwSource){
        this.fwSource = fwSource;
    }
      
    public FwSource getFwSource(){
        return this.fwSource;
    }
      
    public void setFwAccount(FwAccount fwAccount){
        this.fwAccount = fwAccount;
    }
      
    public FwAccount getFwAccount(){
        return this.fwAccount;
    }

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
      

}
