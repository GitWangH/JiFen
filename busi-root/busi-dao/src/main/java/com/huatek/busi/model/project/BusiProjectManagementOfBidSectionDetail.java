package com.huatek.busi.model.project;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 工程标段管理明细
 * @author eli_cui
 *
 */
@Entity
@Table(name = "busi_project_management_of_bid_section_detail")
public class BusiProjectManagementOfBidSectionDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "MANAGEMENT_OF_BID_SECTION_DETAIL_ID", nullable = true )
 	private Long id;
 
    
    /** @Fields busiProjectManagementOfBidSection : 工程标段管理主键id */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "MANAGEMENT_OF_BID_SECTION_ID")
    private BusiProjectManagementOfBidSection busiProjectManagementOfBidSection;
    
    
	/** @Fields nameOfUnitProject : 单位工程名称*/ 
	@Column(name= "NAME_OF_UNIT_PROJECT", nullable = false, length=100 )
    private String nameOfUnitProject;
    
    
	/** @Fields typeOfUnitProject : 单位工程类型（数据字典）*/ 
	@Column(name= "TYPE_OF_UNIT_PROJECT", nullable = false, length=30 )
    private String typeOfUnitProject;
    
    
	/** @Fields initialPileNumber : 起始桩号*/ 
	@Column(name= "INITIAL_PILE_NUMBER", nullable = false, length=50 )
    private String initialPileNumber;
    
    
	/** @Fields endPileNumber : 结束桩号*/ 
	@Column(name= "END_PILE_NUMBER", nullable = false, length=50 )
    private String endPileNumber;
    
    
     /** @Fields longitude : 经度*/ 
    @Column(name= "LONGITUDE", nullable = false , precision=10 , scale=7)
    private BigDecimal longitude;
    
    
     /** @Fields latitude : 纬度*/ 
    @Column(name= "LATITUDE", nullable = false , precision=10 , scale=7)
    private BigDecimal latitude;
    
    
     /** @Fields longKm : 长度*/ 
    @Column(name= "LONG_KM", nullable = false , precision=10 , scale=4)
    private BigDecimal longKm;
    
    
	/** @Fields creater : 创建人ID */
	@Column(name= "CREATER", nullable = false)
    private Long creater;
    
    
	/** @Fields createrName : 创建人姓名*/ 
	@Column(name= "CREATER_NAME", nullable = false, length=100 )
    private String createrName;
    
    
	/** @Fields createTime : 创建时间 */
	@Column(name= "CREATE_TIME", nullable = false)
    private Date createTime;
    
    
	/** @Fields modifer : 修改人ID */
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
	
	/** @Fields isDelete : 是否删除状态 0 未删除 1 已删除 */
	@Column(name= "IS_DELETE", nullable = false)
	private Integer isDelete;
	
	/** @Fields code : 编号 */
	@Column(name= "CODE", nullable = false)
	private String code;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSection busiProjectManagementOfBidSection){
        this.busiProjectManagementOfBidSection = busiProjectManagementOfBidSection;
    }
      
    public BusiProjectManagementOfBidSection getBusiProjectManagementOfBidSection(){
        return this.busiProjectManagementOfBidSection;
    }
      
    public void setNameOfUnitProject(String nameOfUnitProject){
        this.nameOfUnitProject = nameOfUnitProject;
    }
      
    public String getNameOfUnitProject(){
        return this.nameOfUnitProject;
    }
      
    public void setTypeOfUnitProject(String typeOfUnitProject){
        this.typeOfUnitProject = typeOfUnitProject;
    }
      
    public String getTypeOfUnitProject(){
        return this.typeOfUnitProject;
    }
      
    public void setInitialPileNumber(String initialPileNumber){
        this.initialPileNumber = initialPileNumber;
    }
      
    public String getInitialPileNumber(){
        return this.initialPileNumber;
    }
      
    public void setEndPileNumber(String endPileNumber){
        this.endPileNumber = endPileNumber;
    }
      
    public String getEndPileNumber(){
        return this.endPileNumber;
    }
      
    public void setLongitude(BigDecimal longitude){
        this.longitude = longitude;
    }
      
    public BigDecimal getLongitude(){
        return this.longitude;
    }
      
    public void setLatitude(BigDecimal latitude){
        this.latitude = latitude;
    }
      
    public BigDecimal getLatitude(){
        return this.latitude;
    }
      
    public void setLongKm(BigDecimal longKm){
        this.longKm = longKm;
    }
      
    public BigDecimal getLongKm(){
        return this.longKm;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
