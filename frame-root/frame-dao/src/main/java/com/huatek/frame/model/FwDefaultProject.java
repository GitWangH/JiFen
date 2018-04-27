package com.huatek.frame.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: FwDefaultProject
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 14:43:48
  * @version: Windows 7
  */

@Entity
@Table(name = "fw_default_project")
public class FwDefaultProject extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields acctId : 用户id*/ 
	@Column(name= "ACCT_ID", nullable = true, length=255 )
    private String acctId;
    
    
	/** @Fields orgId : 项目机构id*/ 
	@Column(name= "ORG_ID", nullable = true, length=255 )
    private String orgId;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setAcctId(String acctId){
        this.acctId = acctId;
    }
      
    public String getAcctId(){
        return this.acctId;
    }
      
    public void setOrgId(String orgId){
        this.orgId = orgId;
    }
      
    public String getOrgId(){
        return this.orgId;
    }
      

}
