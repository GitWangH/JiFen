package com.huatek.frame.dto;

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

public class FwDefaultProjectDto {

 	private Long id;
 
	/** @Fields acctId : 用户id*/ 
    private Long acctId;
    
	/** @Fields orgId : 项目机构id*/ 
    private Long orgId;
    
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setAcctId(Long acctId){
        this.acctId = acctId;
    }
      
    public Long getAcctId(){
        return this.acctId;
    }
      
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
      
    public Long getOrgId(){
        return this.orgId;
    }
      
}
