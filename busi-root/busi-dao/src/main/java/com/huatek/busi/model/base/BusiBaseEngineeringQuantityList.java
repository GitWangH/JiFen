package com.huatek.busi.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiTreeGridEntity;
import com.huatek.frame.core.model.BaseEntity;
import com.huatek.frame.core.model.BaseEntityForTree;


 /**
  * 工程量清单
  * 代码自动生成model类.
  * @ClassName: BusiBaseEngineeringQuantityList
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:21:41
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_base_engineering_quantity_list")
public class BusiBaseEngineeringQuantityList extends BusiTreeGridEntity { 

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ENGINEERING_QUANTITY_ID", nullable = true )
 	private Long id;
	
	/** @Fields number : 编号*/ 
	@Column(name= "NUMBER", nullable = false, length=30 )
    private String number;
    
	/** @Fields name : 名字*/ 
	@Column(name= "NAME", nullable = false, length=100 )
    private String name;
    
	/** @Fields unit : 单位*/ 
	@Column(name= "UNIT", nullable = false, length=30 )
    private String unit;
    
	/** @Fields describe : 描述*/ 
	@Column(name= "REMARK", nullable = false, length=255 )
    private String remark;
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setNumber(String number){
        this.number = number;
    }
      
    public String getNumber(){
        return this.number;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public void setUnit(String unit){
        this.unit = unit;
    }
      
    public String getUnit(){
        return this.unit;
    }
      
    public void setRemark(String describe){
        this.remark = describe;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
    

}
