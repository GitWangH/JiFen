package com.huatek.busi.model.contract;

import java.math.BigDecimal;

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

import com.huatek.busi.model.BusiTreeGridEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiContractOtherContractDetail
  * @Description: 其它合同明细管理
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-27 11:08:20
  * @version: 1.0
  */
@Entity
@Table(name = "busi_contract_other_contract_detail")
public class BusiContractOtherContractDetail extends BusiTreeGridEntity {

	private static final long serialVersionUID = 1L;
	
	/** @Fields otherContractDetailId : 其它合同清单ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name= "OTHER_CONTRACT_DETAIL_ID", nullable = false)
    private Long id;
    
	/** @Fields contractDetailCode : 其它合同清单编号*/ 
	@Column(name= "CONTRACT_DETAIL_CODE", nullable = false, length=50 )
    private String contractDetailCode;
    
	/** @Fields contractDetailName : 其它合同清单名称*/ 
	@Column(name= "CONTRACT_DETAIL_NAME", nullable = false, length=100 )
    private String contractDetailName;
    
	/** @Fields detailMoney : 金额（元） */
	@Column(name = "DETAIL_MONEY", nullable = false, precision = 18, scale = 4)
	private BigDecimal detailMoney;
	
	/** @Fields meteringType : 计量类型(字典表)*/ 
	@Column(name= "METERING_TYPE", nullable = false, length=10 )
    private String meteringType;
    
	/** @Fields busiContractOtherContract : 其它合同ID */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "OTHER_CONTRACT_ID")
    private BusiContractOtherContract busiContractOtherContract;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
      
    public BigDecimal getDetailMoney() {
		return detailMoney;
	}
    
    public void setDetailMoney(BigDecimal detailMoney) {
		this.detailMoney = detailMoney;
	}

    public void setMeteringType(String meteringType){
        this.meteringType = meteringType;
    }
      
    public String getMeteringType(){
        return this.meteringType;
    }
      
    public void setBusiContractOtherContract(BusiContractOtherContract busiContractOtherContract){
        this.busiContractOtherContract = busiContractOtherContract;
    }
      
    public BusiContractOtherContract getBusiContractOtherContract(){
        return this.busiContractOtherContract;
    }

}
