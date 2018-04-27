package com.huatek.busi.dto.progress;

/**
 * 标段形象清单划分实体类.
 * 
 * @ClassName: BusiProgressImage
 * @Description:
 * @author: jordan_li
 * @Email :
 * @date: 2017-12-06 10:58:13
 * @version: Windows 7
 */
public class BusiProgressImageToBranchConnectDto {

	private Long id;

	private Long imageId;// 形象清单id
	
	private String imageCode;//形象清单编号
	
	private String imageName;// 形象清单名称

	private Long branchId;// 分部分项id
	
	private String branchCode;//分部分项编号
	
	private String branchName;//分部分项名称
	
	public BusiProgressImageToBranchConnectDto() {
		super();
	}

	public BusiProgressImageToBranchConnectDto(Long imageId, Long branchId) {
		super();
		this.imageId = imageId;
		this.branchId = branchId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
}
