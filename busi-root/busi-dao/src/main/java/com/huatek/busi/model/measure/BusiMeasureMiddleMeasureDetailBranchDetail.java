package com.huatek.busi.model.measure;

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
  * @ClassName: BusiMeasureMiddleMeasureDetailBranchDetail
  * @Description: 中间计量分部分项明细
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:47:27
  * @version: 1.0
  */
@Entity
@Table(name = "busi_measure_middle_measure_detail_branch_detail")
public class BusiMeasureMiddleMeasureDetailBranchDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "MIDDLE_MEASURE_DETAIL_BRANCH_DETAIL_ID", nullable = true )
 	private Long id;
 
    
    /** @Fields busiMeasureMiddleMeasureDetail : 主键 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "MIDDLE_MEASURE_DETAIL_ID")
    private BusiMeasureMiddleMeasureDetail busiMeasureMiddleMeasureDetail;
    
    
	/** @Fields tendersContractDetailId : 合同清单ID */
	@Column(name= "TENDERS_CONTRACT_DETAIL_ID", nullable = false)
    private Long tendersContractDetailId;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setBusiMeasureMiddleMeasureDetail(BusiMeasureMiddleMeasureDetail busiMeasureMiddleMeasureDetail){
        this.busiMeasureMiddleMeasureDetail = busiMeasureMiddleMeasureDetail;
    }
      
    public BusiMeasureMiddleMeasureDetail getBusiMeasureMiddleMeasureDetail(){
        return this.busiMeasureMiddleMeasureDetail;
    }
      
    public void setTendersContractDetailId(Long tendersContractDetailId){
        this.tendersContractDetailId = tendersContractDetailId;
    }
      
    public Long getTendersContractDetailId(){
        return this.tendersContractDetailId;
    }
      

}
