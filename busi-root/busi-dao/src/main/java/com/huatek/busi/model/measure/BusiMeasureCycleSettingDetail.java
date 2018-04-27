package com.huatek.busi.model.measure;

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

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiMeasureCycleSettingDetail
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-12-07 13:35:04
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_measure_cycle_setting_detail")
public class BusiMeasureCycleSettingDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "CYCLE_SETTING_DETAIL_ID", nullable = true )
 	private Long id;
 
    
    /** @Fields busiMeasureCycleSetting : 计量周期主表 */
	@ManyToOne
    @JoinColumn(name = "CYCLE_SETTING_ID")
    private BusiMeasureCycleSetting busiMeasureCycleSetting;
    
    
	/** @Fields serialNumber : 序号 */
	@Column(name= "SERIAL_NUMBER", nullable = false)
    private Integer serialNumber;
    
    
	/** @Fields issueNumber : 期号*/ 
	@Column(name= "ISSUE_NUMBER", nullable = false, length=50 )
    private String issueNumber;
    
    
	/** @Fields startDate : 开始日期 */
	@Column(name= "START_DATE", nullable = false)
    private Date startDate;
    
    
	/** @Fields endDate : 结束日期 */
	@Column(name= "END_DATE", nullable = false)
    private Date endDate;
    
    
	/** @Fields year : 年份 */
	@Column(name= "YEAR", nullable = false)
    private Integer year;
    
    
	/** @Fields month : 月份 */
	@Column(name= "MONTH", nullable = false)
    private Integer month;
    
    
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
      
    public void setBusiMeasureCycleSetting(BusiMeasureCycleSetting busiMeasureCycleSetting){
        this.busiMeasureCycleSetting = busiMeasureCycleSetting;
    }
      
    public BusiMeasureCycleSetting getBusiMeasureCycleSetting(){
        return this.busiMeasureCycleSetting;
    }
      
    public void setSerialNumber(Integer serialNumber){
        this.serialNumber = serialNumber;
    }
      
    public Integer getSerialNumber(){
        return this.serialNumber;
    }
      
    public void setIssueNumber(String issueNumber){
        this.issueNumber = issueNumber;
    }
      
    public String getIssueNumber(){
        return this.issueNumber;
    }
      
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
      
    public Date getStartDate(){
        return this.startDate;
    }
      
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
      
    public Date getEndDate(){
        return this.endDate;
    }
      
    public void setYear(Integer year){
        this.year = year;
    }
      
    public Integer getYear(){
        return this.year;
    }
      
    public void setMonth(Integer month){
        this.month = month;
    }
      
    public Integer getMonth(){
        return this.month;
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
