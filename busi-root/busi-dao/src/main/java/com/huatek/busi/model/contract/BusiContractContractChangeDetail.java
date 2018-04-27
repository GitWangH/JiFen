package com.huatek.busi.model.contract;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiContractContractChangeDetail
  * @Description: 合同变更明细
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:12:40
  * @version: 1.0
  */
@Entity
@Table(name = "busi_contract_contract_change_detail")
public class BusiContractContractChangeDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "CONTRACT_CHANGE_DETAIL_ID", nullable = true )
 	private Long id;
 
    
    /** @Fields busiContractContractChange : 合同变更主表ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "CONTRACT_CHANGE_ID")
    private BusiContractContractChange busiContractContractChange;
    
    
    /** @Fields busiContractTendersContractDetail : 合同清单主键ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "TENDERS_CONTRACT_DETAIL_ID")
    private BusiContractTendersContractDetail busiContractTendersContractDetail;
    
    
    /** @Fields busiContractTendersBranch : 标段分部分项ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "TENDERS_BRANCH_ID")
    private BusiContractTendersBranch busiContractTendersBranch;
    
    
	/** @Fields changeType : 数据字典维护，包括数量变更、单价变更（对原清单）、单价变更（对增减量）*/ 
	@Column(name= "CHANGE_TYPE", nullable = false, length=30 )
    private String changeType;
    
    
     /** @Fields changeBeforeUnitPrice : 变更前单价(元)*/ 
    @Column(name= "CHANGE_BEFORE_UNIT_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal changeBeforeUnitPrice;
    
    
     /** @Fields changeBeforeQuantity : 变更前数量*/ 
    @Column(name= "CHANGE_BEFORE_QUANTITY", nullable = false , precision=18 , scale=2)
    private BigDecimal changeBeforeQuantity;
    
    
     /** @Fields changeBeforeTotalPrice : 变更前金额(元)*/ 
    @Column(name= "CHANGE_BEFORE_TOTAL_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal changeBeforeTotalPrice;
    
    
     /** @Fields changeUnitPrice : 变更单价(元)*/ 
    @Column(name= "CHANGE_UNIT_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal changeUnitPrice;
    
    
     /** @Fields changeQuantity : 变更数量*/ 
    @Column(name= "CHANGE_QUANTITY", nullable = false , precision=18 , scale=2)
    private BigDecimal changeQuantity;
    
    
     /** @Fields changeAfterUnitPrice : 变更后单价(元)*/ 
    @Column(name= "CHANGE_AFTER_UNIT_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal changeAfterUnitPrice;
    
    
     /** @Fields changeAfterQuantity : 变更后数量*/ 
    @Column(name= "CHANGE_AFTER_QUANTITY", nullable = false , precision=18 , scale=2)
    private BigDecimal changeAfterQuantity;
    
    
     /** @Fields changeAfterTotalPrice : 变更后金额(元)*/ 
    @Column(name= "CHANGE_AFTER_TOTAL_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal changeAfterTotalPrice;
    
    
	/** @Fields creater : 创建人id */
	@Column(name= "CREATER", nullable = false)
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
	@Column(name= "CREATER_NAME", nullable = false, length=100 )
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
	@Column(name= "CREATE_TIME", nullable = false)
    private Date createTime;
    
    
	/** @Fields tenantId : 租户id */
	@Column(name= "TENANT_ID", nullable = false)
    private Long tenantId;
    
    
	/** @Fields orgId : 组织机构ID */
	@Column(name= "ORG_ID", nullable = false)
    private Long orgId;
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setBusiContractContractChange(BusiContractContractChange busiContractContractChange){
        this.busiContractContractChange = busiContractContractChange;
    }
      
    public BusiContractContractChange getBusiContractContractChange(){
        return this.busiContractContractChange;
    }
      
    public void setBusiContractTendersContractDetail(BusiContractTendersContractDetail busiContractTendersContractDetail){
        this.busiContractTendersContractDetail = busiContractTendersContractDetail;
    }
      
    public BusiContractTendersContractDetail getBusiContractTendersContractDetail(){
        return this.busiContractTendersContractDetail;
    }
      
    public void setBusiContractTendersBranch(BusiContractTendersBranch busiContractTendersBranch){
        this.busiContractTendersBranch = busiContractTendersBranch;
    }
      
    public BusiContractTendersBranch getBusiContractTendersBranch(){
        return this.busiContractTendersBranch;
    }
      
    public void setChangeType(String changeType){
        this.changeType = changeType;
    }
      
    public String getChangeType(){
        return this.changeType;
    }
      
    public void setChangeBeforeUnitPrice(BigDecimal changeBeforeUnitPrice){
        this.changeBeforeUnitPrice = changeBeforeUnitPrice;
    }
      
    public BigDecimal getChangeBeforeUnitPrice(){
        return this.changeBeforeUnitPrice;
    }
      
    public void setChangeBeforeQuantity(BigDecimal changeBeforeQuantity){
        this.changeBeforeQuantity = changeBeforeQuantity;
    }
      
    public BigDecimal getChangeBeforeQuantity(){
        return this.changeBeforeQuantity;
    }
      
    public void setChangeBeforeTotalPrice(BigDecimal changeBeforeTotalPrice){
        this.changeBeforeTotalPrice = changeBeforeTotalPrice;
    }
      
    public BigDecimal getChangeBeforeTotalPrice(){
        return this.changeBeforeTotalPrice;
    }
      
    public void setChangeUnitPrice(BigDecimal changeUnitPrice){
        this.changeUnitPrice = changeUnitPrice;
    }
      
    public BigDecimal getChangeUnitPrice(){
        return this.changeUnitPrice;
    }
      
    public void setChangeQuantity(BigDecimal changeQuantity){
        this.changeQuantity = changeQuantity;
    }
      
    public BigDecimal getChangeQuantity(){
        return this.changeQuantity;
    }
      
    public void setChangeAfterUnitPrice(BigDecimal changeAfterUnitPrice){
        this.changeAfterUnitPrice = changeAfterUnitPrice;
    }
      
    public BigDecimal getChangeAfterUnitPrice(){
        return this.changeAfterUnitPrice;
    }
      
    public void setChangeAfterQuantity(BigDecimal changeAfterQuantity){
        this.changeAfterQuantity = changeAfterQuantity;
    }
      
    public BigDecimal getChangeAfterQuantity(){
        return this.changeAfterQuantity;
    }
      
    public void setChangeAfterTotalPrice(BigDecimal changeAfterTotalPrice){
        this.changeAfterTotalPrice = changeAfterTotalPrice;
    }
      
    public BigDecimal getChangeAfterTotalPrice(){
        return this.changeAfterTotalPrice;
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
      
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }
      
    public Long getTenantId(){
        return this.tenantId;
    }
      
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
      
    public Long getOrgId(){
        return this.orgId;
    }
      
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
      
    public Integer getIsDelete(){
        return this.isDelete;
    }
      

}
