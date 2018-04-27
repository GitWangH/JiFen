package com.huatek.busi.model.base;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 形象清单和分部分项挂接表
  * @ClassName: BusiBaseImageListSubConnectionTable
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:25:13
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_base_image_list_sub_connection_table")
public class BusiBaseImageListSubConnectionTable extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields subEngineeringId : 分部分项主键ID */
	@Column(name= "SUB_ENGINEERING_ID", nullable = false)
    private Long subEngineeringId;
    
    
	/** @Fields imageListId : 形象清单主键ID */
	@Column(name= "IMAGE_LIST_ID", nullable = false)
    private Long imageListId;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setSubEngineeringId(Long subEngineeringId){
        this.subEngineeringId = subEngineeringId;
    }
      
    public Long getSubEngineeringId(){
        return this.subEngineeringId;
    }
      
    public void setImageListId(Long imageListId){
        this.imageListId = imageListId;
    }
      
    public Long getImageListId(){
        return this.imageListId;
    }
      

}
