package com.huatek.busi.model.base;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.busi.model.BusiTreeGridEntity;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 形象清单
  * 代码自动生成model类.
  * @ClassName: BusiBaseImageList
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:23:28
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_base_image_list")
public class BusiBaseImageList extends BusiTreeGridEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "IMAGE_LIST_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields number : 编号*/ 
	@Column(name= "NUMBER", nullable = false, length=30 )
    private String number;
    
    
	/** @Fields name : 名称*/ 
	@Column(name= "NAME", nullable = false, length=100 )
    private String name;
    
    
	/** @Fields unit : 单位*/ 
	@Column(name= "UNIT", nullable = false, length=30 )
    private String unit;
    
	/** @Fields order : 备注 */
	@Column(name= "REMARK", nullable = false)
	private String remark;
    
    
	/** @Fields type : 类型*/ 
	@Column(name= "TYPE", nullable = false, length=30 )
    private String type;
    

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
      
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setType(String type){
        this.type = type;
    }
      
    public String getType(){
        return this.type;
    }
      
}
