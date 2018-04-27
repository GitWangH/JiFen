package com.huatek.frame.dto;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.service.dto.FwOrgDto;

 /**
  * @ClassName: FwStationDto
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 15:31:57
  * @version: Windows 7
  */

public class FwStationDto  {

	
 	private Long id;
 
    
	/** @Fields name : 名称*/ 
    private String name;
    
    
	/** @Fields code : 编码*/ 
    private String code;
    
    
	/** @Fields orgId : 组织机构ID */
    private Long orgId;
    
    /** @Fields orgId : 组织机构名称 */
    private String orgName;
    
    
	/** @Fields departmentId :  */
    private Long departmentId;
    
    /** @Fields departmentId :  */
    private String departmentName;
    
    
	/** @Fields remark : */ 
    private String remark;
    
    
	/** @Fields tenantId :  */
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
      
    public void setCode(String code){
        this.code = code;
    }
      
    public String getCode(){
        return this.code;
    }
      
    public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setDepartmentId(Long departmentId){
        this.departmentId = departmentId;
    }
      
    public Long getDepartmentId(){
        return this.departmentId;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
    
}
