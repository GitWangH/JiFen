package com.huatek.file.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.huatek.frame.core.model.BaseEntity;

 /**
  * 代码自动生成model类.
  * @ClassName: CmdFile
  * @Description: 
  * @author: hobart
  * @date: 2016-05-05 06:27:01
  * @version: 1.0
  */

@Entity
@Table(name = "cmd_file")
public class CmdFile extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name= "FILE_PATH_ID", nullable = true )
 	private Long id;
	/**
	 * 上传顺序
	 */
	@Column(name= "FILE_ORDER", nullable = true )
 	private Long fileOrder;
	public Long getOrder() {
		return fileOrder;
	}
	
	

	/** @Fields fileName : 文件预览地址*/ 
	@Column(name= "VIEW_PATH", nullable = true, length=255 )
    private String viewPath;
	
	public Long getFileOrder() {
		return fileOrder;
	}

	public void setFileOrder(Long fileOrder) {
		this.fileOrder = fileOrder;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public void setOrder(Long fileOrder) {
		this.fileOrder = fileOrder;
	}

	/** @Fields fileName : 文件名*/ 
	@Column(name= "FILE_NAME", nullable = true, length=255 )
    private String fileName;
    
    
	/** @Fields filePath : 文件路径url*/ 
	@Column(name= "FILE_PATH", nullable = true, length=255 )
    private String filePath;
    
    
	/** @Fields suffixName : 后缀名*/ 
	@Column(name= "SUFFIX_NAME", nullable = true, length=255 )
    private String suffixName;
    
    
	/** @Fields uploadUser : 上传人*/ 
	@Column(name= "UPLOAD_USER", nullable = true, length=255 )
    private String uploadUser;
    
    
	/** @Fields uploadTime : 上传时间 */
	@Column(name= "UPLOAD_TIME", nullable = true)
    private Date uploadTime;
    
    
	/** @Fields fileSize : 文件大小*/ 
	@Column(name= "FILE_SIZE", nullable = true, length=255 )
    private String fileSize;
    
    
	/** @Fields businessId : 业务id */
	@Column(name= "BUSINESS_ID", nullable = true)
    private String  businessId;
    
 
    
	/** @Fields compressPath : 压缩图片路径*/ 
	@Column(name= "COMPRESS_PATH", nullable = true)
    private String compressPath;
   
	
	@Column(name= "TENANT_ID", nullable = true )
	private Long tenantId;
	
	@Column(name= "ORG_ID", nullable = true )
	private Long orgId;
	
	@Column(name= "FILE_PATH_CH", nullable = true )
	private String filePathCh;
	
	/** @Fields catalogId  目录 **/
	@Column(name= "CATALOG_ID", nullable = true)
	private Long catalogId;
      
    public void setId(Long id){
        this.id = id;
    }
      
    public Long getId(){
        return this.id;
    }
      
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
      
    public String getFileName(){
        return this.fileName;
    }
      
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
      
    public String getFilePath(){
        return this.filePath;
    }
      
    public void setSuffixName(String suffixName){
        this.suffixName = suffixName;
    }
      
    public String getSuffixName(){
        return this.suffixName;
    }
      
    public void setUploadUser(String uploadUser){
        this.uploadUser = uploadUser;
    }
      
    public String getUploadUser(){
        return this.uploadUser;
    }
      
    public void setUploadTime(Date uploadTime){
        this.uploadTime = uploadTime;
    }
      
    public Date getUploadTime(){
        return this.uploadTime;
    }
      
    public void setFileSize(String fileSize){
        this.fileSize = fileSize;
    }
      
    public String getFileSize(){
        return this.fileSize;
    }
      
    public void setBusinessId(String businessId){
        this.businessId = businessId;
    }
      
    public String getBusinessId(){
        return this.businessId;
    }
 

	public String getCompressPath() {
		return compressPath;
	}

	public void setCompressPath(String compressPath) {
		this.compressPath = compressPath;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getFilePathCh() {
		return filePathCh;
	}

	public void setFilePathCh(String filePathCh) {
		this.filePathCh = filePathCh;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
}
