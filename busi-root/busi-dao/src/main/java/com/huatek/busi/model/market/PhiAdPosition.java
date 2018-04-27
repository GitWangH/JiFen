package com.huatek.busi.model.market;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: PhiAdPosition
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-19 13:43:47
  * @version: Windows 7
  * 广告位的model
  */

@Entity
@Table(name = "phi_ad_position")
public class PhiAdPosition extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "AD_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields adAddress : 配置位置*/ 
	@Column(name= "AD_ADDRESS", nullable = false, length=40 )
    private String adAddress;
    
    
	/** @Fields client : 位置的客户端*/ 
	@Column(name= "CLIENT", nullable = false, length=200 )
    private String client;
    
    
	/** @Fields rule : 位置规则*/ 
	@Column(name= "RULE", nullable = false, length=40 )
    private String rule;
    
    
	/** @Fields operatingPerson : 操作人*/ 
	@Column(name= "OPERATING_PERSON", nullable = false, length=40 )
    private String operatingPerson;
    
    
	/** @Fields adTitle : 标题*/ 
	@Column(name= "AD_TITLE", nullable = false, length=100 )
    private String adTitle;
    
    
	/** @Fields adSubheading : 副标题*/ 
	@Column(name= "AD_SUBHEADING", nullable = false, length=100 )
    private String adSubheading;
    
    
	/** @Fields endoperatingtime : 最后操作时间 */
	@Column(name= "ENDOPERATINGTIME", nullable = false)
    private Date endoperatingtime;
    
    
	/** @Fields showrule : 位置的展示规则*/ 
	@Column(name= "SHOWRULE", nullable = false, length=0 )
    private String showrule;
    
    
	/** @Fields adCode : 广告位CODE*/ 
	@Column(name= "AD_CODE", nullable = false, length=40 )
    private String adCode;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setAdAddress(String adAddress){
        this.adAddress = adAddress;
    }
      
    public String getAdAddress(){
        return this.adAddress;
    }
      
    public void setClient(String client){
        this.client = client;
    }
      
    public String getClient(){
        return this.client;
    }
      
    public void setRule(String rule){
        this.rule = rule;
    }
      
    public String getRule(){
        return this.rule;
    }
      
    public void setOperatingPerson(String operatingPerson){
        this.operatingPerson = operatingPerson;
    }
      
    public String getOperatingPerson(){
        return this.operatingPerson;
    }
      
    public void setAdTitle(String adTitle){
        this.adTitle = adTitle;
    }
      
    public String getAdTitle(){
        return this.adTitle;
    }
      
    public void setAdSubheading(String adSubheading){
        this.adSubheading = adSubheading;
    }
      
    public String getAdSubheading(){
        return this.adSubheading;
    }
      
    public void setEndoperatingtime(Date endoperatingtime){
        this.endoperatingtime = endoperatingtime;
    }
      
    public Date getEndoperatingtime(){
        return this.endoperatingtime;
    }
      
    public void setShowrule(String showrule){
        this.showrule = showrule;
    }
      
    public String getShowrule(){
        return this.showrule;
    }
      
    public void setAdCode(String adCode){
        this.adCode = adCode;
    }
      
    public String getAdCode(){
        return this.adCode;
    }
      

}
