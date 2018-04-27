package com.huatek.busi.model.measure;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiMeasureMiddlePayCertificateTemplate
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

@Entity
@Table(name = "busi_measure_middle_pay_certificate_template")
public class BusiMeasureMiddlePayCertificateTemplate extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "ID", nullable = true )
 	private Long id;
 
    
	/** @Fields orgId : 项目 */
	@Column(name= "ORG_ID", nullable = false)
    private Long orgId;
    
    
	/** @Fields type : 类型(小计项，合计项，支付项，其他项)*/ 
	@Column(name= "TYPE", nullable = false, length=10 )
    private String type;
    
    
	/** @Fields itemKey : 支付项KEY */
	@Column(name= "ITEM_KEY", nullable = false)
    private String itemKey;
    
    
	/** @Fields itemName : 支付项名称 */
	@Column(name= "ITEM_NAME", nullable = false)
    private String itemName;
	/** @Fields sign : 计算方向 */
	@Column(name= "SIGN", nullable = false)
    private String sign;
    
	/** @Fields remarks : 备注*/ 
	@Column(name= "REMARKS", nullable = false, length=10 )
    private String remarks;
    
    
	/** @Fields creater : 创建人id */
	@Column(name= "CREATER", nullable = false)
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
	@Column(name= "CREATER_NAME", nullable = false, length=100 )
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
	@Column(name= "CREATE_TIME", nullable = false)
    private Date createTime;
    
    
	/** @Fields modifer : 修改人id */
	@Column(name= "MODIFER", nullable = false)
    private Long modifer;
    
    
	/** @Fields modifierName : 修改人姓名*/ 
	@Column(name= "MODIFIER_NAME", nullable = false, length=100 )
    private String modifierName;
    
    
	/** @Fields modifyTime : 修改时间 */
	@Column(name= "MODIFY_TIME", nullable = false)
    private Date modifyTime;
    
    
	/** @Fields tenantId : 租户id */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
      
    public Long getOrgId(){
        return this.orgId;
    }
      
    public void setType(String type){
        this.type = type;
    }
      
    public String getType(){
        return this.type;
    }
      

      
    public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setRemarks(String remarks){
        this.remarks = remarks;
    }
      
    public String getRemarks(){
        return this.remarks;
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
      
    public void setModifer(Long modifer){
        this.modifer = modifer;
    }
      
    public Long getModifer(){
        return this.modifer;
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
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }
      
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
      
    public Integer getIsDelete(){
        return this.isDelete;
    }

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
      

}
