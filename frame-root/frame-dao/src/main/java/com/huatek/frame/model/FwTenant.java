package com.huatek.frame.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: FwTenant
  * @Description: 
  * @author: Kaka Xiao
  * @Email : 
  * @date: 2017-10-16 17:42:36
  * @version: Windows 10
  */

@Entity
@Table(name = "fw_tenant")
public class FwTenant extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields code : */ 
	@Column(name= "CODE", nullable = false, length=20 )
    private String code;
    
    
	/** @Fields name : 会员全称*/ 
	@Column(name= "NAME", nullable = false, length=255 )
    private String name;
    
    
	/** @Fields shortName : 会员简称*/ 
	@Column(name= "SHORT_NAME", nullable = false, length=255 )
    private String shortName;
    
    
	/** @Fields addr : 详细地址*/ 
	@Column(name= "ADDR", nullable = false, length=255 )
    private String addr;
    
    
	/** @Fields endTime : 到期时间 */
	@Column(name= "END_TIME", nullable = false)
    private Date endTime;
    
    
	/** @Fields status : 状态(1 启用，0禁用) */
	@Column(name= "STATUS", nullable = false)
    private Integer status;
    
    
	/** @Fields contactPerson : 联系人*/ 
	@Column(name= "CONTACT_PERSON", nullable = false, length=50 )
    private String contactPerson;
    
    
	/** @Fields contactTel : 联系人电话*/ 
	@Column(name= "CONTACT_TEL", nullable = false, length=15 )
    private String contactTel;
    
    
	/** @Fields contactMail : 联系人邮箱*/ 
	@Column(name= "CONTACT_MAIL", nullable = false, length=100 )
    private String contactMail;
    
    
	/** @Fields contactPhone : 联系人手机*/ 
	@Column(name= "CONTACT_PHONE", nullable = false, length=15 )
    private String contactPhone;
    
    
	/** @Fields creater : 创建人 */
	@Column(name= "CREATER", nullable = false)
    private Long creater;
    
    
	/** @Fields createrName : 创建人名称*/ 
	@Column(name= "CREATER_NAME", nullable = false, length=20 )
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
	@Column(name= "CREATE_TIME", nullable = false)
    private Date createTime;
    
    
	/** @Fields modifier : 修改人 */
	@Column(name= "MODIFIER", nullable = false)
    private Long modifier;
    
    
	/** @Fields modifierName : 修改人姓名*/ 
	@Column(name= "MODIFIER_NAME", nullable = false, length=20 )
    private String modifierName;
    
    
	/** @Fields modifyTime : 修改时间 */
	@Column(name= "MODIFY_TIME", nullable = false)
    private Date modifyTime;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setCode(String code){
        this.code = code;
    }
      
    public String getCode(){
        return this.code;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public void setShortName(String shortName){
        this.shortName = shortName;
    }
      
    public String getShortName(){
        return this.shortName;
    }
      
    public void setAddr(String addr){
        this.addr = addr;
    }
      
    public String getAddr(){
        return this.addr;
    }
      
    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }
      
    public Date getEndTime(){
        return this.endTime;
    }
      
    public void setStatus(Integer status){
        this.status = status;
    }
      
    public Integer getStatus(){
        return this.status;
    }
      
    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }
      
    public String getContactPerson(){
        return this.contactPerson;
    }
      
    public void setContactTel(String contactTel){
        this.contactTel = contactTel;
    }
      
    public String getContactTel(){
        return this.contactTel;
    }
      
    public void setContactMail(String contactMail){
        this.contactMail = contactMail;
    }
      
    public String getContactMail(){
        return this.contactMail;
    }
      
    public void setContactPhone(String contactPhone){
        this.contactPhone = contactPhone;
    }
      
    public String getContactPhone(){
        return this.contactPhone;
    }
      
    public void setCreater(Long creater){
        this.creater = creater;
    }
      
    public Long getCreater(){
        return this.creater;
    }
      
    public void setCreaterName(String createrName){
        this.createrName = createrName;
    }
      
    public String getCreaterName(){
        return this.createrName;
    }
      
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
      
    public Date getCreateTime(){
        return this.createTime;
    }
      
    public void setModifier(Long modifier){
        this.modifier = modifier;
    }
      
    public Long getModifier(){
        return this.modifier;
    }
      
    public void setModifierName(String modifierName){
        this.modifierName = modifierName;
    }
      
    public String getModifierName(){
        return this.modifierName;
    }
      
    public void setModifyTime(Date modifyTime){
        this.modifyTime = modifyTime;
    }
      
    public Date getModifyTime(){
        return this.modifyTime;
    }
      

}
