package com.huatek.busi.model.progress;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Persister;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.huatek.busi.model.BusiFwOrg;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 标段形象清单划分实体类.
  * @ClassName: BusiProgressImage
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-12-06 10:58:13
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_progress_image")
public class BusiProgressImage extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "IMAGE_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields orgId : 标段 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name= "ORG_ID", nullable = false)
    private BusiFwOrg busiFwOrg;
    
    
	/** @Fields code : 编号*/ 
	@Column(name= "CODE", nullable = false, length=25 )
    private String code;
    
    
	/** @Fields name : 名称*/ 
	@Column(name= "NAME", nullable = false, length=255 )
    private String name;
    
    
	/** @Fields unit : 单位*/ 
	@Column(name= "UNIT", nullable = false, length=15 )
    private String unit;
    
    
	/** @Fields keyProject : 重点工程(0不是，1是)*/ 
	@Column(name= "KEY_PROJECT", nullable = false, length=30 )
    private String keyProject;
    
    
     /** @Fields designQuantity : 设计工程量*/ 
    @Column(name= "DESIGN_QUANTITY", nullable = false , precision=18 , scale=4)
    private BigDecimal designQuantity;
    
    
     /** @Fields designUnitPrice : 设计单价*/ 
    @Column(name= "DESIGN_UNIT_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal designUnitPrice;
    
    
     /** @Fields designAmount : 设计金额*/ 
    @Column(name= "DESIGN_AMOUNT", nullable = false , precision=18 , scale=4)
    private BigDecimal designAmount;
    
    
     /** @Fields changeQuantity : 变更后工程量*/ 
    @Column(name= "CHANGE_QUANTITY", nullable = false , precision=18 , scale=4)
    private BigDecimal changeQuantity;
    
    
     /** @Fields changeUnitPrice : 变更后单价*/ 
    @Column(name= "CHANGE_UNIT_PRICE", nullable = false , precision=18 , scale=4)
    private BigDecimal changeUnitPrice;
    
    
     /** @Fields changeAmount : 变更后金额*/ 
    @Column(name= "CHANGE_AMOUNT", nullable = false , precision=18 , scale=4)
    private BigDecimal changeAmount;
    
    
	/** @Fields remark : 备注*/ 
	@Column(name= "REMARK", nullable = false, length=255 )
    private String remark;
    
    
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
    
    
	/** @Fields baseImageId : 基础库形象清单id */
	@Column(name= "BASE_IMAGE_ID", nullable = false)
    private Long baseImageId;
    
    
	/** @Fields orderNumber : 顺序 */
	@Column(name= "ORDER_NUMBER", nullable = false)
    private Integer orderNumber;
    
    
	/** @Fields level1 : 是否一级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_1", nullable = false, length=40 )
    private String level1;
    
    
	/** @Fields level2 : 是否二级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_2", nullable = false, length=40 )
    private String level2;
    
    
	/** @Fields level3 : 是否三级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_3", nullable = false, length=40 )
    private String level3;
    
    
	/** @Fields level4 : 是否四级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_4", nullable = false, length=40 )
    private String level4;
    
    
	/** @Fields level5 : 是否五级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_5", nullable = false, length=40 )
    private String level5;
    
    
	/** @Fields level6 : 是否六级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_6", nullable = false, length=40 )
    private String level6;
    
    
	/** @Fields level7 : 是否七级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_7", nullable = false, length=40 )
    private String level7;
    
    
	/** @Fields level8 : 是否八级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_8", nullable = false, length=40 )
    private String level8;
    
    
	/** @Fields level9 : 是否九级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_9", nullable = false, length=40 )
    private String level9;
    
    
	/** @Fields level10 : 是否十级组织（1-是，0-不是）*/ 
	@Column(name= "LEVEL_10", nullable = false, length=40 )
    private String level10;
    
    
	/** @Fields groupLevel : 组织级别 */
	@Column(name= "GROUP_LEVEL", nullable = false)
    private Long groupLevel;
    
    
	/** @Fields isDelete : 是否删除  0 未删除的正常数据 1 已删除的数据 */
	@Column(name= "IS_DELETE", nullable = false)
    private Integer isDelete;
    
    
	/** @Fields isLeaf : 是否叶子节点 0 不是 1 是 */
	@Column(name= "IS_LEAF", nullable = false)
    private Integer isLeaf;
    
    
	/** @Fields uuid : 前台传输唯一键*/ 
	@Column(name= "UUID", nullable = false, length=40 )
    private String uuid;
	
	/** @Fields uuid : 上级uuid*/ 
	@Column(name= "PARENT_ID", nullable = false, length=40 )
	private String parentId;
	
	@Transient
	private Long imageId;
	
	@Transient
	private Long branchId;
	
	@Transient
	private BigDecimal sum;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }

      
    public BusiFwOrg getBusiFwOrg() {
		return busiFwOrg;
	}

	public void setBusiFwOrg(BusiFwOrg busiFwOrg) {
		this.busiFwOrg = busiFwOrg;
	}

	public void setCode(String code){
        this.code = code;
    }
      
    public String getCode(){
        return this.code;
    }
      
    public void setName(String name){
        this.name = name;
    }
      
    public String getName(){
        return this.name;
    }
      
    public void setUnit(String unit){
        this.unit = unit;
    }
      
    public String getUnit(){
        return this.unit;
    }
      
    public void setKeyProject(String keyProject){
        this.keyProject = keyProject;
    }
      
    public String getKeyProject(){
        return this.keyProject;
    }
      
    public void setDesignQuantity(BigDecimal designQuantity){
        this.designQuantity = designQuantity;
    }
      
    public BigDecimal getDesignQuantity(){
        return this.designQuantity;
    }
      
    public void setDesignUnitPrice(BigDecimal designUnitPrice){
        this.designUnitPrice = designUnitPrice;
    }
      
    public BigDecimal getDesignUnitPrice(){
        return this.designUnitPrice;
    }
      
    public void setDesignAmount(BigDecimal designAmount){
        this.designAmount = designAmount;
    }
      
    public BigDecimal getDesignAmount(){
        return this.designAmount;
    }
      
    public void setChangeQuantity(BigDecimal changeQuantity){
        this.changeQuantity = changeQuantity;
    }
      
    public BigDecimal getChangeQuantity(){
        return this.changeQuantity;
    }
      
    public void setChangeUnitPrice(BigDecimal changeUnitPrice){
        this.changeUnitPrice = changeUnitPrice;
    }
      
    public BigDecimal getChangeUnitPrice(){
        return this.changeUnitPrice;
    }
      
    public void setChangeAmount(BigDecimal changeAmount){
        this.changeAmount = changeAmount;
    }
      
    public BigDecimal getChangeAmount(){
        return this.changeAmount;
    }
      
    public void setRemark(String remark){
        this.remark = remark;
    }
      
    public String getRemark(){
        return this.remark;
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
      
    public void setBaseImageId(Long baseImageId){
        this.baseImageId = baseImageId;
    }
      
    public Long getBaseImageId(){
        return this.baseImageId;
    }
      
    public void setOrderNumber(Integer orderNumber){
        this.orderNumber = orderNumber;
    }
      
    public Integer getOrderNumber(){
        return this.orderNumber;
    }
      
    public void setLevel1(String level1){
        this.level1 = level1;
    }
      
    public String getLevel1(){
        return this.level1;
    }
      
    public void setLevel2(String level2){
        this.level2 = level2;
    }
      
    public String getLevel2(){
        return this.level2;
    }
      
    public void setLevel3(String level3){
        this.level3 = level3;
    }
      
    public String getLevel3(){
        return this.level3;
    }
      
    public void setLevel4(String level4){
        this.level4 = level4;
    }
      
    public String getLevel4(){
        return this.level4;
    }
      
    public void setLevel5(String level5){
        this.level5 = level5;
    }
      
    public String getLevel5(){
        return this.level5;
    }
      
    public void setLevel6(String level6){
        this.level6 = level6;
    }
      
    public String getLevel6(){
        return this.level6;
    }
      
    public void setLevel7(String level7){
        this.level7 = level7;
    }
      
    public String getLevel7(){
        return this.level7;
    }
      
    public void setLevel8(String level8){
        this.level8 = level8;
    }
      
    public String getLevel8(){
        return this.level8;
    }
      
    public void setLevel9(String level9){
        this.level9 = level9;
    }
      
    public String getLevel9(){
        return this.level9;
    }
      
    public void setLevel10(String level10){
        this.level10 = level10;
    }
      
    public String getLevel10(){
        return this.level10;
    }
      
    public void setGroupLevel(Long groupLevel){
        this.groupLevel = groupLevel;
    }
      
    public Long getGroupLevel(){
        return this.groupLevel;
    }
      
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
      
    public Integer getIsDelete(){
        return this.isDelete;
    }
      
    public void setIsLeaf(Integer isLeaf){
        this.isLeaf = isLeaf;
    }
      
    public Integer getIsLeaf(){
        return this.isLeaf;
    }
      
    public void setUuid(String uuid){
        this.uuid = uuid;
    }
      
    public String getUuid(){
        return this.uuid;
    }

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = Long.valueOf(imageId);
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = Long.valueOf(branchId);
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
}
