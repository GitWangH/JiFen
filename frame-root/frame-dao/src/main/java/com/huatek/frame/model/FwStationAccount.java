package com.huatek.frame.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.dao.model.FwAccount;

 /**
  * 代码自动生成model类.
  * @ClassName: FwStationAccount
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 15:31:57
  * @version: Windows 7
  */

@Entity
@Table(name = "fw_station_account")
public class FwStationAccount extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
    /** @Fields fwStation : 岗位名称 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "STATION_ID")
    private FwStation fwStation;
    
    
    /** @Fields fwAccount : 岗位编码 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "ACCT_ID")
    private FwAccount fwAccount;
    
    
	/** @Fields tenantId :  */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setFwStation(FwStation fwStation){
        this.fwStation = fwStation;
    }
      
    public FwStation getFwStation(){
        return this.fwStation;
    }
      
    public void setFwAccount(FwAccount fwAccount){
        this.fwAccount = fwAccount;
    }
      
    public FwAccount getFwAccount(){
        return this.fwAccount;
    }
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }
      

}
