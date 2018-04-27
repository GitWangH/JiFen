package com.huatek.busi.model.pluspageLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiPlusPagelayout
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-23 15:00:36
  * @version: Windows 7
  */

@Entity
@Table(name = "phi_plus_pagelayout")
public class PhiPlusPagelayout extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "PLUS_PAGELAYOUT_ID", nullable = true )
 	private Long id;
 
	
	 /** @Fields code :编号*/ 
	@Column(name= "CODE", nullable = false, length=200 )
	private String code;
	
	/** @Fields configureaddr : 配置位置*/ 
	@Column(name= "CONFIGUREADDR", nullable = false, length=200 )
    private String configureaddr;
    
    
	/** @Fields client : 客户端*/ 
	@Column(name= "CLIENT", nullable = false, length=200 )
    private String client;
    
    
	/** @Fields rule : 规则*/ 
	@Column(name= "RULE", nullable = false, length=200 )
    private String rule;
    
    
	/** @Fields operationperson : 操作人员*/ 
	@Column(name= "OPERATIONPERSON", nullable = false, length=100 )
    private String operationperson;
    
    
	/** @Fields endoperationtime : 末次操作时间 */
	@Column(name= "ENDOPERATIONTIME", nullable = false)
    private Date endoperationtime;
    
    
	/** @Fields nowconut : 当前配置数 */
	@Column(name= "NOWCONUT", nullable = false)
    private Long nowconut;
    
    
	/** @Fields maxcount : 最大配置数 */
	@Column(name= "MAXCOUNT", nullable = false)
    private Long maxcount;
    
    
	/** @Fields title : 版块标题*/ 
	@Column(name= "TITLE", nullable = false, length=200 )
    private String title;
    
    
	/** @Fields morelink : 更多链接*/ 
	@Column(name= "MORELINK", nullable = false, length=200 )
    private String morelink;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getConfigureaddr() {
		return configureaddr;
	}


	public void setConfigureaddr(String configureaddr) {
		this.configureaddr = configureaddr;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getRule() {
		return rule;
	}


	public void setRule(String rule) {
		this.rule = rule;
	}


	public String getOperationperson() {
		return operationperson;
	}


	public void setOperationperson(String operationperson) {
		this.operationperson = operationperson;
	}


	public Date getEndoperationtime() {
		return endoperationtime;
	}


	public void setEndoperationtime(Date endoperationtime) {
		this.endoperationtime = endoperationtime;
	}


	public Long getNowconut() {
		return nowconut;
	}


	public void setNowconut(Long nowconut) {
		this.nowconut = nowconut;
	}


	public Long getMaxcount() {
		return maxcount;
	}


	public void setMaxcount(Long maxcount) {
		this.maxcount = maxcount;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getMorelink() {
		return morelink;
	}


	public void setMorelink(String morelink) {
		this.morelink = morelink;
	}
    
}
