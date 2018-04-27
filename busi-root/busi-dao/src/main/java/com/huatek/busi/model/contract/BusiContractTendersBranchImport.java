package com.huatek.busi.model.contract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 分部分项导入数据临时表.
  * @ClassName: BusiContractTendersBranchImport
  * @Description: 
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-11-21 13:11:16
  * @version: 1.0
  */
@Entity
@Table(name = "busi_contract_tenders_branch_import")
public class BusiContractTendersBranchImport extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "TENDERS_BRANCH_IMPORT_ID", nullable = true )
 	private Long id;
    
	/** @Fields importBatchNo : 导入批次号*/ 
	@Column(name= "IMPORT_BATCH_NO", nullable = false, length=50 )
    private String importBatchNo;
    
    
	/** @Fields unitProjectCode : 单位工程编号*/ 
	@Column(name= "UNIT_PROJECT_CODE", nullable = false, length=100 )
    private String unitProjectCode;
    
    
	/** @Fields unitProjectName : 单位工程名称*/ 
	@Column(name= "UNIT_PROJECT_NAME", nullable = false, length=100 )
    private String unitProjectName;
    
    
	/** @Fields unitProjectStartStakeNo : 单位工程起始桩号*/ 
	@Column(name= "UNIT_PROJECT_START_STAKE_NO", nullable = false, length=100 )
    private String unitProjectStartStakeNo;
    
    
	/** @Fields unitProjectEndStakeNo : 单位工程终止桩号*/ 
	@Column(name= "UNIT_PROJECT_END_STAKE_NO", nullable = false, length=100 )
    private String unitProjectEndStakeNo;
    
    
	/** @Fields branchProjectCode : 分部工程编号*/ 
	@Column(name= "BRANCH_PROJECT_CODE", nullable = false, length=100 )
    private String branchProjectCode;
    
    
	/** @Fields branchProjectName : 分部工程名称*/ 
	@Column(name= "BRANCH_PROJECT_NAME", nullable = false, length=100 )
    private String branchProjectName;
    
    
	/** @Fields branchProjectStartStakeNo : 分部工程起始桩号*/ 
	@Column(name= "BRANCH_PROJECT_START_STAKE_NO", nullable = false, length=100 )
    private String branchProjectStartStakeNo;
    
    
	/** @Fields branchProjectEndStakeNo : 分部工程终止桩号*/ 
	@Column(name= "BRANCH_PROJECT_END_STAKE_NO", nullable = false, length=100 )
    private String branchProjectEndStakeNo;
    
    
	/** @Fields subitemProjectCode : 分项工程编号*/ 
	@Column(name= "SUBITEM_PROJECT_CODE", nullable = false, length=100 )
    private String subitemProjectCode;
    
    
	/** @Fields subitemProjectName : 分项工程名称*/ 
	@Column(name= "SUBITEM_PROJECT_NAME", nullable = false, length=100 )
    private String subitemProjectName;
    
    
	/** @Fields subitemProjectStartStakeNo : 分项工程起始桩号*/ 
	@Column(name= "SUBITEM_PROJECT_START_STAKE_NO", nullable = false, length=100 )
    private String subitemProjectStartStakeNo;
    
    
	/** @Fields subitemProjectEndStakeNo : 分项工程终止桩号*/ 
	@Column(name= "SUBITEM_PROJECT_END_STAKE_NO", nullable = false, length=100 )
    private String subitemProjectEndStakeNo;
    
    
	/** @Fields partCode : 部位编号*/ 
	@Column(name= "PART_CODE", nullable = false, length=100 )
    private String partCode;
    
    
	/** @Fields concreteParagraphOrPart : 具体段落或部位*/ 
	@Column(name= "CONCRETE_PARAGRAPH_OR_PART", nullable = false, length=100 )
    private String concreteParagraphOrPart;
    
    
	/** @Fields partStartStakeNo : 部位起始桩号*/ 
	@Column(name= "PART_START_STAKE_NO", nullable = false, length=100 )
    private String partStartStakeNo;
    
    
	/** @Fields partEndStakeNo : 部位终止桩号*/ 
	@Column(name= "PART_END_STAKE_NO", nullable = false, length=100 )
    private String partEndStakeNo;
    
    
	/** @Fields contractDetailCode : (合同)清单编号*/ 
	@Column(name= "CONTRACT_DETAIL_CODE", nullable = false, length=100,columnDefinition="VARCHAR default ''" )
    private String contractDetailCode;
    
    
	/** @Fields contractDetailName : (合同)清单名称*/ 
	@Column(name= "CONTRACT_DETAIL_NAME", nullable = false, length=100,columnDefinition="VARCHAR default ''" )
    private String contractDetailName;
    
    
	/** @Fields contractUnitPrice : (合同)单价*/ 
	@Column(name= "CONTRACT_UNIT_PRICE", nullable = false, length=100,columnDefinition="VARCHAR default '0'" )
    private String contractUnitPrice;
    
    
	/** @Fields contractQuantity : (合同)数量*/ 
	@Column(name= "CONTRACT_QUANTITY", nullable = false, length=100,columnDefinition="VARCHAR default '0'" )
    private String contractQuantity;
    
    
	/** @Fields contractTotalPrice : (合同)金额*/ 
	@Column(name= "CONTRACT_TOTAL_PRICE", nullable = false, length=100,columnDefinition="VARCHAR default '0'" )
    private String contractTotalPrice;
    
    
	/** @Fields meterageQuantity : 计量数量*/ 
	@Column(name= "METERAGE_QUANTITY", nullable = false, length=100,columnDefinition="VARCHAR default '0'" )
    private String meterageQuantity;
    
    
	/** @Fields creater : 操作人*/ 
	@Column(name= "CREATER", nullable = false, length=100 )
    private String creater;
    
    
	/** @Fields importDate : 导入时间*/ 
	@Column(name= "IMPORT_DATE", nullable = false, length=50 )
    private String importDate;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setImportBatchNo(String importBatchNo){
        this.importBatchNo = importBatchNo;
    }
      
    public String getImportBatchNo(){
        return this.importBatchNo;
    }
      
    public void setUnitProjectCode(String unitProjectCode){
        this.unitProjectCode = unitProjectCode;
    }
      
    public String getUnitProjectCode(){
        return this.unitProjectCode;
    }
      
    public void setUnitProjectName(String unitProjectName){
        this.unitProjectName = unitProjectName;
    }
      
    public String getUnitProjectName(){
        return this.unitProjectName;
    }
      
    public void setUnitProjectStartStakeNo(String unitProjectStartStakeNo){
        this.unitProjectStartStakeNo = unitProjectStartStakeNo;
    }
      
    public String getUnitProjectStartStakeNo(){
        return this.unitProjectStartStakeNo;
    }
      
    public void setUnitProjectEndStakeNo(String unitProjectEndStakeNo){
        this.unitProjectEndStakeNo = unitProjectEndStakeNo;
    }
      
    public String getUnitProjectEndStakeNo(){
        return this.unitProjectEndStakeNo;
    }
      
    public void setBranchProjectCode(String branchProjectCode){
        this.branchProjectCode = branchProjectCode;
    }
      
    public String getBranchProjectCode(){
        return this.branchProjectCode;
    }
      
    public void setBranchProjectName(String branchProjectName){
        this.branchProjectName = branchProjectName;
    }
      
    public String getBranchProjectName(){
        return this.branchProjectName;
    }
      
    public void setBranchProjectStartStakeNo(String branchProjectStartStakeNo){
        this.branchProjectStartStakeNo = branchProjectStartStakeNo;
    }
      
    public String getBranchProjectStartStakeNo(){
        return this.branchProjectStartStakeNo;
    }
      
    public void setBranchProjectEndStakeNo(String branchProjectEndStakeNo){
        this.branchProjectEndStakeNo = branchProjectEndStakeNo;
    }
      
    public String getBranchProjectEndStakeNo(){
        return this.branchProjectEndStakeNo;
    }
      
    public void setSubitemProjectCode(String subitemProjectCode){
        this.subitemProjectCode = subitemProjectCode;
    }
      
    public String getSubitemProjectCode(){
        return this.subitemProjectCode;
    }
      
    public void setSubitemProjectName(String subitemProjectName){
        this.subitemProjectName = subitemProjectName;
    }
      
    public String getSubitemProjectName(){
        return this.subitemProjectName;
    }
      
    public void setSubitemProjectStartStakeNo(String subitemProjectStartStakeNo){
        this.subitemProjectStartStakeNo = subitemProjectStartStakeNo;
    }
      
    public String getSubitemProjectStartStakeNo(){
        return this.subitemProjectStartStakeNo;
    }
      
    public void setSubitemProjectEndStakeNo(String subitemProjectEndStakeNo){
        this.subitemProjectEndStakeNo = subitemProjectEndStakeNo;
    }
      
    public String getSubitemProjectEndStakeNo(){
        return this.subitemProjectEndStakeNo;
    }
      
    public void setPartCode(String partCode){
        this.partCode = partCode;
    }
      
    public String getPartCode(){
        return this.partCode;
    }
      
    public void setConcreteParagraphOrPart(String concreteParagraphOrPart){
        this.concreteParagraphOrPart = concreteParagraphOrPart;
    }
      
    public String getConcreteParagraphOrPart(){
        return this.concreteParagraphOrPart;
    }
      
    public void setPartStartStakeNo(String partStartStakeNo){
        this.partStartStakeNo = partStartStakeNo;
    }
      
    public String getPartStartStakeNo(){
        return this.partStartStakeNo;
    }
      
    public void setPartEndStakeNo(String partEndStakeNo){
        this.partEndStakeNo = partEndStakeNo;
    }
      
    public String getPartEndStakeNo(){
        return this.partEndStakeNo;
    }
      
    public void setContractDetailCode(String contractDetailCode){
        this.contractDetailCode = contractDetailCode;
    }
      
    public String getContractDetailCode(){
        return this.contractDetailCode;
    }
      
    public void setContractDetailName(String contractDetailName){
        this.contractDetailName = contractDetailName;
    }
      
    public String getContractDetailName(){
        return this.contractDetailName;
    }
      
    public void setContractUnitPrice(String contractUnitPrice){
        this.contractUnitPrice = contractUnitPrice;
    }
      
    public String getContractUnitPrice(){
        return this.contractUnitPrice;
    }
      
    public void setContractQuantity(String contractQuantity){
        this.contractQuantity = contractQuantity;
    }
      
    public String getContractQuantity(){
        return this.contractQuantity;
    }
      
    public void setContractTotalPrice(String contractTotalPrice){
        this.contractTotalPrice = contractTotalPrice;
    }
      
    public String getContractTotalPrice(){
        return this.contractTotalPrice;
    }
      
    public void setMeterageQuantity(String meterageQuantity){
        this.meterageQuantity = meterageQuantity;
    }
      
    public String getMeterageQuantity(){
        return this.meterageQuantity;
    }
      
    public void setCreater(String creater){
        this.creater = creater;
    }
      
    public String getCreater(){
        return this.creater;
    }
      
    public void setImportDate(String importDate){
        this.importDate = importDate;
    }
      
    public String getImportDate(){
        return this.importDate;
    }
      

}
