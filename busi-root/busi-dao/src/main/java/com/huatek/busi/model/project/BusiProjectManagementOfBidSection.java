package com.huatek.busi.model.project;

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

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

/**
 * 工程标段管理
 * @author eli_cui
 *
 */

@Entity
@Table(name = "busi_project_management_of_bid_section")
public class BusiProjectManagementOfBidSection extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "MANAGEMENT_OF_BID_SECTION_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields orgId : 机构ID */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORG_ID")
	//@Column(name= "ORG_ID", nullable = true)
    private BusiFwOrg orgId;
    
    
	/** @Fields initialPileNumber : 起始桩号*/ 
	@Column(name= "INITIAL_PILE_NUMBER", nullable = false, length=50 )
    private String initialPileNumber;
    
    
	/** @Fields endPileNumber : 结束桩号*/ 
	@Column(name= "END_PILE_NUMBER", nullable = false, length=50 )
    private String endPileNumber;
    
    
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
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
	
	@Column(name= "UUID", nullable = false)
	private String UUID;
    
    
	/** @Fields busiprojectmanagementofbidsectiondetailSet :  */
	//mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy= "busiProjectManagementOfBidSection")
    private Set<BusiProjectManagementOfBidSectionDetail> busiprojectmanagementofbidsectiondetailSet;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setOrgEntity(BusiFwOrg orgId){
        this.orgId = orgId;
    }
      
    public BusiFwOrg getOrgEntity(){
        return this.orgId;
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
      
    public void setBusiprojectmanagementofbidsectiondetailSet(Set<BusiProjectManagementOfBidSectionDetail> busiprojectmanagementofbidsectiondetailSet){
        this.busiprojectmanagementofbidsectiondetailSet = busiprojectmanagementofbidsectiondetailSet;
    }
      
    public Set<BusiProjectManagementOfBidSectionDetail> getBusiprojectmanagementofbidsectiondetailSet(){
        return this.busiprojectmanagementofbidsectiondetailSet;
    }

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String UUID) {
		this.UUID = UUID;
	}

	
}
