package com.huatek.frame.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.dao.model.FwDepartment;
import com.huatek.frame.dao.model.FwOrg;

 /**
  * 代码自动生成model类.
  * @ClassName: FwStation
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 15:31:57
  * @version: Windows 7
  */

@Entity
@Table(name = "fw_station")
public class FwStation extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields name : 名称*/ 
	@Column(name= "NAME", nullable = false, length=255 )
    private String name;
    
    
	/** @Fields code : 编码*/ 
	@Column(name= "CODE", nullable = false, length=255 )
    private String code;
    
    
	/** @Fields orgId : 组织机构ID */
//	@Column(name= "ORG_ID", nullable = false)
//    private Long orgId;
	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	private FwOrg fwOrg;
    
    
	/** @Fields departmentId :  */
//	@Column(name= "DEPARTMENT_ID", nullable = false)
//    private Long departmentId;
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private FwDepartment fwDepartment;
    
    
	/** @Fields remark : */ 
	@Column(name= "REMARK", nullable = false, length=65535 )
    private String remark;
    
    
	/** @Fields tenantId :  */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
	/** @Fields fwstationaccountSet :  */
	//mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy= "fwStation")
    private Set<FwStationAccount> fwStationAccountSet;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public void setCode(String code){
        this.code = code;
    }
      
    public String getCode(){
        return this.code;
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

	public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }

	public Set<FwStationAccount> getFwStationAccountSet() {
		return fwStationAccountSet;
	}

	public void setFwStationAccountSet(Set<FwStationAccount> fwStationAccountSet) {
		this.fwStationAccountSet = fwStationAccountSet;
	}
      
}
