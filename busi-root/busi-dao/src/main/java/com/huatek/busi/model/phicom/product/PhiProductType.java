package com.huatek.busi.model.phicom.product;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

@Entity
@Table(name = "phi_product_type")
public class PhiProductType  extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "prouduct_type_id", nullable = true )
 	private Long id;
 
    
	/** @Fields typeName : 类型名称*/ 
	@Column(name= "type_name", nullable = true, length=100 )
    private String typeName;
    
    
	/** @Fields typeIcon : 类型图片*/ 
	@Column(name= "type_icon", nullable = true, length=100 )
    private String typeIcon;
    
    
	/** @Fields showOrder : 显示顺序*/ 
	@Column(name= "show_order", nullable = true, length=100 )
    private String showOrder;     
   
	/** @Fields handleMan : 操作人员*/ 
	@Column(name= "handle_man", nullable = true )
    private String handleMan;
    
    
	/** @Fields productStock : 创建人员id */
	@Column(name= "create_id")
    private BigDecimal createId;
  
	
	/** @Fields lastTime : 最后操作 */ 
    @Column(name= "last_time" ,nullable = true)
    private Date lastTime;
    
    
     /** @Fields isRecomment : 是否推荐*/ 
    @Column(name= "is_recomment", nullable = true)
    private String isRecomment;
         
    /** @Fields createTime: 创建时间*/ 
	@Column(name= "create_time", nullable = true)
    private Date createTime;
    
	
	/** @Fields remark : 备注*/ 
	@Column(name= "remark", nullable = true)
    private String remark;
	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeIcon() {
		return typeIcon;
	}

	public void setTypeIcon(String typeIcon) {
		this.typeIcon = typeIcon;
	}


	public String getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}

	

	public String getHandleMan() {
		return handleMan;
	}

	public void setHandleMan(String handleMan) {
		this.handleMan = handleMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getCreateId() {
		return createId;
	}

	public void setCreateId(BigDecimal createId) {
		this.createId = createId;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getIsRecomment() {
		return isRecomment;
	}

	public void setIsRecomment(String isRecomment) {
		this.isRecomment = isRecomment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
