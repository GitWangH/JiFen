package com.huatek.busi.model.base;

public class BusiBaseImageListSubConnectionTableShow {
	/**形象清单与分部分项挂接表主键id*/
	private Long id;
	/**形象清单编号*/
	private String imageNumber;
	/**形象清单名称*/
	private String imageName;
	/**分部分项编号*/
	private String subEngineeringNumber;
	/**分部分项名称*/
	private String subEngineeringName;
	
	public BusiBaseImageListSubConnectionTableShow(){}
	
	
	public BusiBaseImageListSubConnectionTableShow(Long id, String imageNumber,
			String imageName, String subEngineeringNumber,
			String subEngineeringName) {
		super();
		this.id = id;
		this.imageNumber = imageNumber;
		this.imageName = imageName;
		this.subEngineeringNumber = subEngineeringNumber;
		this.subEngineeringName = subEngineeringName;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(String imageNumber) {
		this.imageNumber = imageNumber;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getSubEngineeringNumber() {
		return subEngineeringNumber;
	}
	public void setSubEngineeringNumber(String subEngineeringNumber) {
		this.subEngineeringNumber = subEngineeringNumber;
	}
	public String getSubEngineeringName() {
		return subEngineeringName;
	}
	public void setSubEngineeringName(String subEngineeringName) {
		this.subEngineeringName = subEngineeringName;
	}
}
