package com.huatek.busi.dto.quality;

import java.util.List;

import com.huatek.frame.core.model.BaseEntity;

/**
 * 隐蔽工程文件上传数据转化类
 * 
 * @ClassName: BusiQualitySeclusionEngineerFile
 * @Description:
 * @author: rocky_wei
 * @Email :
 * @date: 2017-11-09 14:14:21
 * @version: Windows 7
 */
public class BusiQualitySeclusionEngineerFileDto extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	/** @Fields fileId : 附件uuid */
	private String fileId;
	
	/** @Fields fileIdList : 附件uuid */
	private List<BusiQualityFileDto> fileIdList;

	/** @Fields fileSuffix : 文件后缀名 */
	private String fileSuffix;

	/** @Fields tendersBranch : 项目分部分项id */
	private Long tendersBranchId;

	/** @Fields orgId : 标段id */
	private Long orgId;
	
	/** @Fields orgName : 标段名称 */
	private String orgName;
	
	/** @Fields fileName : 文件名称 */
	private String fileName;

	/** @Fields tendersBranchCode : 编码 */
	private String tendersBranchCode;
	
	/** @Fields tendersBranchName : 分部分项名称 */
	private String tendersBranchName;

	private String modifyTime;// 上传时间

	private Long modifer;// 上传人ID

	private String modifierName;// 上传人姓名
	
	private String remarks;//备注

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public List<BusiQualityFileDto> getFileIdList() {
		return fileIdList;
	}

	public void setFileIdList(List<BusiQualityFileDto> fileIdList) {
		this.fileIdList = fileIdList;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileSuffix() {
		return this.fileSuffix;
	}

	public Long getTendersBranchId() {
		return tendersBranchId;
	}

	public void setTendersBranchId(Long tendersBranchId) {
		this.tendersBranchId = tendersBranchId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTendersBranchCode() {
		return tendersBranchCode;
	}

	public void setTendersBranchCode(String tendersBranchCode) {
		this.tendersBranchCode = tendersBranchCode;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTendersBranchName() {
		return tendersBranchName;
	}

	public void setTendersBranchName(String tendersBranchName) {
		this.tendersBranchName = tendersBranchName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
}
