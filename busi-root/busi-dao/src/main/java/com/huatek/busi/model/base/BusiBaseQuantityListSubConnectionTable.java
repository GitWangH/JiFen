package com.huatek.busi.model.base;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 分部分项与工程量清单 挂接表
  * @ClassName: BusiBaseQuantityListSubConnectionTable
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:25:13
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_base_quantity_list_sub_connection_table")
public class BusiBaseQuantityListSubConnectionTable extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields engineeringQuantityId : 工程量清单ID */
	@Column(name= "ENGINEERING_QUANTITY_ID", nullable = false)
    private Long engineeringQuantityId;
    
    
	/** @Fields subEngineeringId : 分部分项ID */
	@Column(name= "SUB_ENGINEERING_ID", nullable = false)
    private Long subEngineeringId;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setEngineeringQuantityId(Long engineeringQuantityId){
        this.engineeringQuantityId = engineeringQuantityId;
    }
      
    public Long getEngineeringQuantityId(){
        return this.engineeringQuantityId;
    }
      
    public void setSubEngineeringId(Long subEngineeringId){
        this.subEngineeringId = subEngineeringId;
    }
      
    public Long getSubEngineeringId(){
        return this.subEngineeringId;
    }
      

}
