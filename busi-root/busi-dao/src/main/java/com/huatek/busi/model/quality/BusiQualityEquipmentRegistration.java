package com.huatek.busi.model.quality;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: BusiQualityEquipmentRegistration
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:52
  * @version: Windows 7
  */

@Entity
@Table(name = "busi_quality_equipment_registration")
public class BusiQualityEquipmentRegistration extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "EQUIPMENT_REGISTRATION_ID", nullable = true )
 	private Long id;
 
    
	/** @Fields platformMechanism : 平台机构*/ 
	@Column(name= "PLATFORM_MECHANISM", nullable = false, length=100 )
    private String platformMechanism;
    
    
	/** @Fields manufacturerName : 厂商名称*/ 
	@Column(name= "MANUFACTURER_NAME", nullable = false, length=100 )
    private String manufacturerName;
    
    
	/** @Fields installationPosition : 安装位置*/ 
	@Column(name= "INSTALLATION_POSITION", nullable = false, length=100 )
    private String installationPosition;
    
    
	/** @Fields onSubitem : 所在分部分项*/ 
	@Column(name= "ON_SUBITEM", nullable = false, length=100 )
    private String onSubitem;
    
    
	/** @Fields machineType : 机器类型(数据字典)*/ 
	@Column(name= "MACHINE_TYPE", nullable = false, length=100 )
    private String machineType;
    
    
	/** @Fields machineName : 机器名称*/ 
	@Column(name= "MACHINE_NAME", nullable = false, length=100 )
    private String machineName;
    
    
	/** @Fields machineCode : 机器编号*/ 
	@Column(name= "MACHINE_CODE", nullable = false, length=100 )
    private String machineCode;
    
    
	/** @Fields appKey : 注册码(AppKey)*/ 
	@Column(name= "APP_KEY", nullable = false, length=50 )
    private String appKey;
    
    
	/** @Fields orgId : 组织机构ID*/ 
	@Column(name= "ORG_ID", nullable = false, length=10 )
    private String orgId;
    
    
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
    
    
	/** @Fields column19 : */ 
	@Column(name= "Column_19", nullable = false, length=10 )
    private String column19;
    
    
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setPlatformMechanism(String platformMechanism){
        this.platformMechanism = platformMechanism;
    }
      
    public String getPlatformMechanism(){
        return this.platformMechanism;
    }
      
    public void setManufacturerName(String manufacturerName){
        this.manufacturerName = manufacturerName;
    }
      
    public String getManufacturerName(){
        return this.manufacturerName;
    }
      
    public void setInstallationPosition(String installationPosition){
        this.installationPosition = installationPosition;
    }
      
    public String getInstallationPosition(){
        return this.installationPosition;
    }
      
    public void setOnSubitem(String onSubitem){
        this.onSubitem = onSubitem;
    }
      
    public String getOnSubitem(){
        return this.onSubitem;
    }
      
    public void setMachineType(String machineType){
        this.machineType = machineType;
    }
      
    public String getMachineType(){
        return this.machineType;
    }
      
    public void setMachineName(String machineName){
        this.machineName = machineName;
    }
      
    public String getMachineName(){
        return this.machineName;
    }
      
    public void setMachineCode(String machineCode){
        this.machineCode = machineCode;
    }
      
    public String getMachineCode(){
        return this.machineCode;
    }
      
    public void setAppKey(String appKey){
        this.appKey = appKey;
    }
      
    public String getAppKey(){
        return this.appKey;
    }
      
    public void setOrgId(String orgId){
        this.orgId = orgId;
    }
      
    public String getOrgId(){
        return this.orgId;
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
      
    public void setColumn19(String column19){
        this.column19 = column19;
    }
      
    public String getColumn19(){
        return this.column19;
    }
      

}
