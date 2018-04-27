package com.huatek.busi.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiTreeGridEntity;

 /**
  * 项目分部分项
  * @ClassName: BusiBaseSubEngineering
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:23:13
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_base_sub_engineering")
public class BusiBaseSubEngineering extends BusiTreeGridEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "SUB_ENGINEERING_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields number : 编号*/ 
	@Column(name= "NUMBER", nullable = false, length=30 )
    private String number;
    
	@Column(name= "UNIT", nullable = false, length=30 )
	private String unit;
    
	/** @Fields name : 名称*/ 
	@Column(name= "NAME", nullable = false, length=100 )
    private String name;
    
    
	/** @Fields remark : 备注*/ 
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
      
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
    }
      
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
    
}
