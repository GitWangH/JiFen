package com.huatek.frame.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: FwRoleGroup
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-02 14:17:05
  * @version: Windows 7
  */

@Entity
@Table(name = "fw_role_group")
public class FwRoleGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields name : 组名*/ 
	@Column(name= "NAME", nullable = true, length=50 )
    private String name;
    
    
	/** @Fields parentId : 父级组 */
	@Column(name= "PARENT_ID", nullable = true)
    private Long parentId;
    
    
	/** @Fields remark : 备注*/ 
	@Column(name= "REMARK", nullable = false, length=100 )
    private String remark;
    
    
	/** @Fields tenantId :  */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
      
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
      
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
      
    public Long getParentId(){
        return this.parentId;
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
      

}
