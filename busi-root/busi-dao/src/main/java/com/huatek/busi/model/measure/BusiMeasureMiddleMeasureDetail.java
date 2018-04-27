package com.huatek.busi.model.measure;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiMeasureMiddleMeasureDetail
  * @Description: 中间计量明细
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:47:13
  * @version: 1.0
  */
@Entity
@Table(name = "busi_measure_middle_measure_detail")
public class BusiMeasureMiddleMeasureDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "MIDDLE_MEASURE_DETAIL_ID", nullable = true )
 	private Long id;
 
    
    /** @Fields busiMeasureMiddleMeasure : 中间计量主键 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "MIDDLE_MEASURE_ID")
    private BusiMeasureMiddleMeasure busiMeasureMiddleMeasure;
    
    
	/** @Fields tendersBranchId : 分部分项 */
	@Column(name= "TENDERS_BRANCH_ID", nullable = false)
    private Long tendersBranchId;
    
    
	/** @Fields voucherNumber : 凭证号*/ 
	@Column(name= "VOUCHER_NUMBER", nullable = false, length=100 )
    private String voucherNumber;
    
    
	/** @Fields businessDate : 业务日期 */
	@Column(name= "BUSINESS_DATE", nullable = false)
    private Date businessDate;
    
    
	/** @Fields drawingNumber : 图纸号*/ 
	@Column(name= "DRAWING_NUMBER", nullable = false, length=100 )
    private String drawingNumber;
    
    
	/** @Fields stakeNo : 桩号*/ 
	@Column(name= "STAKE_NO", nullable = false, length=100 )
    private String stakeNo;
    
    
	/** @Fields changeDrawingNumber2 : 图纸号*/ 
	@Column(name= "CHANGE_DRAWING_NUMBER2", nullable = false, length=100 )
    private String changeDrawingNumber2;
    
    
	/** @Fields interimCertificateNumber : 中间交工证书编号*/ 
	@Column(name= "INTERIM_CERTIFICATE_NUMBER", nullable = false, length=100 )
    private String interimCertificateNumber;
    
    
     /** @Fields measureTotalMoney : 计量总额(元)*/ 
    @Column(name= "MEASURE_TOTAL_MONEY", nullable = false , precision=18 , scale=4)
    private BigDecimal measureTotalMoney;
    
    
	/** @Fields region : 部位*/ 
	@Column(name= "REGION", nullable = false, length=100 )
    private String region;
    
    
	/** @Fields calculatedMode : 计算式*/ 
	@Column(name= "CALCULATED_MODE", nullable = false, length=100 )
    private String calculatedMode;
    
    
	/** @Fields sketchFile : 草图附件*/ 
	@Column(name= "SKETCH_FILE", nullable = false, length=40 )
    private String sketchFile;
    
    
	/** @Fields otherFile : 其他附件*/ 
	@Column(name= "OTHER_FILE", nullable = false, length=40 )
    private String otherFile;
    
    
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
    
    
	/** @Fields busimeasuremiddlemeasuredetailbranchdetailSet :  */
	//mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.REFRESH}, fetch = FetchType.LAZY,mappedBy= "busiMeasureMiddleMeasureDetail")
    private Set<BusiMeasureMiddleMeasureDetailBranchDetail> busimeasuremiddlemeasuredetailbranchdetailSet;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setBusiMeasureMiddleMeasure(BusiMeasureMiddleMeasure busiMeasureMiddleMeasure){
        this.busiMeasureMiddleMeasure = busiMeasureMiddleMeasure;
    }
      
    public BusiMeasureMiddleMeasure getBusiMeasureMiddleMeasure(){
        return this.busiMeasureMiddleMeasure;
    }
      
    public void setTendersBranchId(Long tendersBranchId){
        this.tendersBranchId = tendersBranchId;
    }
      
    public Long getTendersBranchId(){
        return this.tendersBranchId;
    }
      
    public void setVoucherNumber(String voucherNumber){
        this.voucherNumber = voucherNumber;
    }
      
    public String getVoucherNumber(){
        return this.voucherNumber;
    }
      
    public void setBusinessDate(Date businessDate){
        this.businessDate = businessDate;
    }
      
    public Date getBusinessDate(){
        return this.businessDate;
    }
      
    public void setDrawingNumber(String drawingNumber){
        this.drawingNumber = drawingNumber;
    }
      
    public String getDrawingNumber(){
        return this.drawingNumber;
    }
      
    public void setStakeNo(String stakeNo){
        this.stakeNo = stakeNo;
    }
      
    public String getStakeNo(){
        return this.stakeNo;
    }
      
    public void setChangeDrawingNumber2(String changeDrawingNumber2){
        this.changeDrawingNumber2 = changeDrawingNumber2;
    }
      
    public String getChangeDrawingNumber2(){
        return this.changeDrawingNumber2;
    }
      
    public void setInterimCertificateNumber(String interimCertificateNumber){
        this.interimCertificateNumber = interimCertificateNumber;
    }
      
    public String getInterimCertificateNumber(){
        return this.interimCertificateNumber;
    }
      
    public void setMeasureTotalMoney(BigDecimal measureTotalMoney){
        this.measureTotalMoney = measureTotalMoney;
    }
      
    public BigDecimal getMeasureTotalMoney(){
        return this.measureTotalMoney;
    }
      
    public void setRegion(String region){
        this.region = region;
    }
      
    public String getRegion(){
        return this.region;
    }
      
    public void setCalculatedMode(String calculatedMode){
        this.calculatedMode = calculatedMode;
    }
      
    public String getCalculatedMode(){
        return this.calculatedMode;
    }
      
    public void setSketchFile(String sketchFile){
        this.sketchFile = sketchFile;
    }
      
    public String getSketchFile(){
        return this.sketchFile;
    }
      
    public void setOtherFile(String otherFile){
        this.otherFile = otherFile;
    }
      
    public String getOtherFile(){
        return this.otherFile;
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
      
    public void setBusimeasuremiddlemeasuredetailbranchdetailSet(Set<BusiMeasureMiddleMeasureDetailBranchDetail> busimeasuremiddlemeasuredetailbranchdetailSet){
        this.busimeasuremiddlemeasuredetailbranchdetailSet = busimeasuremiddlemeasuredetailbranchdetailSet;
    }
      
    public Set<BusiMeasureMiddleMeasureDetailBranchDetail> getBusimeasuremiddlemeasuredetailbranchdetailSet(){
        return this.busimeasuremiddlemeasuredetailbranchdetailSet;
    }
      

}
