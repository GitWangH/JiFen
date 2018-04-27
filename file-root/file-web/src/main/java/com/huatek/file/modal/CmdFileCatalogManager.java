package com.huatek.file.modal;

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
  * @ClassName: CmdFileCatalog
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-07 10:02:42
  * @version: Windows 7
  */

@Entity
@Table(name = "cmd_file_catalog")
public class CmdFileCatalogManager extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields name : 路径名*/ 
	@Column(name= "NAME", nullable = false, length=255 )
    private String name;
    
    
	/** @Fields code : 代码*/ 
	@Column(name= "CODE", nullable = false, length=255 )
    private String code;
    
    
	/** @Fields path : 路径*/ 
	@Column(name= "PATH", nullable = false, length=255 )
    private String path;
    
    
	/** @Fields remark : 备注*/ 
	@Column(name= "REMARK", nullable = false, length=255 )
    private String remark;
    
    
	/** @Fields tenantId :  */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
	/** @Fields parentId : 父级目录ID */
	@ManyToOne
	@JoinColumn(name= "PARENT_ID")
    private CmdFileCatalogManager parent;
    
    
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
      
    public void setPath(String path){
        this.path = path;
    }
      
    public String getPath(){
        return this.path;
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

	public CmdFileCatalogManager getParent() {
		return parent;
	}

	public void setParent(CmdFileCatalogManager parent) {
		this.parent = parent;
	}
    
}
