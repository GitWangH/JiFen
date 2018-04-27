package com.huatek.file.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huatek.file.modal.CmdFile;
import com.huatek.frame.core.page.DataPage;

public class CmdFileDto {
	private Long id;
    private String fileName;
    private String filePath;
    private String suffixName;  
    private String uploadUser;    
    private Date uploadTime;    
    private String fileSize;       
    private String businessId; 
    private Long order;    
    private String viewPath; //文件查看地址
    private String compressPath;
    private Long catalogId;
    private String filePathCh;
    private Long tenantId;
    private Long orgId;
    

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	private String businessType;
    
    
    /**
	 * 默认构造器
	 */
	public CmdFileDto(){}
	
	/**
	 * 使用model数据库模型，构造DTO
	 * 
	 * @param model
	 */
	public CmdFileDto(CmdFile model) {
			this.filePath = model.getFilePath();
			this.suffixName = model.getSuffixName();
	}
	 
     /**
	 * 生成getter，setter 访问器
	 */
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
      
    
    /**
	 * 
	 * @param 构造分页
	 * @return
	 */
	public static DataPage<CmdFileDto> transToDtoPage(DataPage<CmdFile> dataPage) {
		DataPage<CmdFileDto> dtoPage = new DataPage<CmdFileDto>();
		List<CmdFileDto> dtos = new ArrayList<CmdFileDto>();
		if (dataPage != null && dataPage.getContent() != null
				&& dataPage.getContent().size() > 0) {
			for (CmdFile m : dataPage.getContent()) {
				dtos.add(new CmdFileDto(m));
			}
			dtoPage.setContent(dtos);
			dtoPage.setPage(dataPage.getPage());
			dtoPage.setPageSize(dataPage.getPageSize());
			dtoPage.setTotalPage(dataPage.getTotalPage());
			dtoPage.setTotalRows(dataPage.getTotalRows());
		}
		return dtoPage;
	}
	
	/**
	 * 
	 * 将 model 集合转为 dto 集合
	 * @param 
	 * @return
	 */
	public static List<CmdFileDto> transToDtoList(List<CmdFile> datas) {
		List<CmdFileDto> dtos =new ArrayList<CmdFileDto>();
		for(CmdFile newDatas : datas){
			CmdFileDto dto = new CmdFileDto(newDatas);
			dtos.add(dto);
		}
		return dtos;
	}

	public String getCompressPath() {
		return compressPath;
	}

	public void setCompressPath(String compressPath) {
		this.compressPath = compressPath;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public String getFilePathCh() {
		return filePathCh;
	}

	public void setFilePathCh(String filePathCh) {
		this.filePathCh = filePathCh;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
}