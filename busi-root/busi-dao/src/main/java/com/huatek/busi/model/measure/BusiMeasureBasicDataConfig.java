package com.huatek.busi.model.measure;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiMeasureBasicDataConfig
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-06 10:18:10
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_measure_basic_data_config")
public class BusiMeasureBasicDataConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "BASIC_DATA_CONFIG_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields orgId : 标段机构对象ID */
	@ManyToOne
	@JoinColumn(name= "ORG_ID", nullable = false)
    private BusiFwOrg org;
    
    
     /** @Fields upperLimitRatio : 计量上限比例（%）*/ 
    @Column(name= "UPPER_LIMIT_RATIO", nullable = false , precision=18 , scale=2)
    private BigDecimal upperLimitRatio;
    
    
     /** @Fields measurePaySet : 计量支付设置*/ 
    @Column(name= "MEASURE_PAY_SET", nullable = false , precision=18 , scale=2)
    private BigDecimal measurePaySet;
    
    
     /** @Fields mobilizeAdvancePayRatio : 动员预付款付款比例（%）*/ 
    @Column(name= "MOBILIZE_ADVANCE_PAY_RATIO", nullable = false , precision=18 , scale=2)
    private BigDecimal mobilizeAdvancePayRatio;
    
    
     /** @Fields monthDeductedMobilizeAdvanceRatio : 月扣回动员预付款比例（%）*/ 
    @Column(name= "MONTH_DEDUCTED_MOBILIZE_ADVANCE_RATIO", nullable = false , precision=18 , scale=2)
    private BigDecimal monthDeductedMobilizeAdvanceRatio;
    
    
     /** @Fields mobilizeAdvanceDeductedRatio : 动员预付款起扣比例（%）*/ 
    @Column(name= "MOBILIZE_ADVANCE_DEDUCTED_RATIO", nullable = false , precision=18 , scale=2)
    private BigDecimal mobilizeAdvanceDeductedRatio;
    
    
     /** @Fields detainRetentionMoneyRatio : 暂扣保留金额比例（%）*/ 
    @Column(name= "DETAIN_RETENTION_MONEY_RATIO", nullable = false , precision=18 , scale=2)
    private BigDecimal detainRetentionMoneyRatio;
    
    
     /** @Fields cumulativeDetainRetentionMoneyLimit : 累计暂扣保留金限额（%）*/ 
    @Column(name= "CUMULATIVE_DETAIN_RETENTION_MONEY_LIMIT", nullable = false , precision=18 , scale=2)
    private BigDecimal cumulativeDetainRetentionMoneyLimit;
    
    
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
      
    public BusiFwOrg getOrg() {
		return org;
	}

	public void setOrg(BusiFwOrg org) {
		this.org = org;
	}

	public void setUpperLimitRatio(BigDecimal upperLimitRatio){
        this.upperLimitRatio = upperLimitRatio;
    }
      
    public BigDecimal getUpperLimitRatio(){
        return this.upperLimitRatio;
    }
      
    public void setMeasurePaySet(BigDecimal measurePaySet){
        this.measurePaySet = measurePaySet;
    }
      
    public BigDecimal getMeasurePaySet(){
        return this.measurePaySet;
    }
      
    public void setMobilizeAdvancePayRatio(BigDecimal mobilizeAdvancePayRatio){
        this.mobilizeAdvancePayRatio = mobilizeAdvancePayRatio;
    }
      
    public BigDecimal getMobilizeAdvancePayRatio(){
        return this.mobilizeAdvancePayRatio;
    }
      
    public void setMonthDeductedMobilizeAdvanceRatio(BigDecimal monthDeductedMobilizeAdvanceRatio){
        this.monthDeductedMobilizeAdvanceRatio = monthDeductedMobilizeAdvanceRatio;
    }
      
    public BigDecimal getMonthDeductedMobilizeAdvanceRatio(){
        return this.monthDeductedMobilizeAdvanceRatio;
    }
      
    public void setMobilizeAdvanceDeductedRatio(BigDecimal mobilizeAdvanceDeductedRatio){
        this.mobilizeAdvanceDeductedRatio = mobilizeAdvanceDeductedRatio;
    }
      
    public BigDecimal getMobilizeAdvanceDeductedRatio(){
        return this.mobilizeAdvanceDeductedRatio;
    }
      
    public void setDetainRetentionMoneyRatio(BigDecimal detainRetentionMoneyRatio){
        this.detainRetentionMoneyRatio = detainRetentionMoneyRatio;
    }
      
    public BigDecimal getDetainRetentionMoneyRatio(){
        return this.detainRetentionMoneyRatio;
    }
      
    public void setCumulativeDetainRetentionMoneyLimit(BigDecimal cumulativeDetainRetentionMoneyLimit){
        this.cumulativeDetainRetentionMoneyLimit = cumulativeDetainRetentionMoneyLimit;
    }
      
    public BigDecimal getCumulativeDetainRetentionMoneyLimit(){
        return this.cumulativeDetainRetentionMoneyLimit;
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
      

}
