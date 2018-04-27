package com.huatek.busi.dto.base.paramEntity;

import java.util.List;

public class BusiBaseImageListSubConnectionTableParamEntity {
	/**形象清单id*/
	private Long imageId;
	/**分部分项id列表*/
	private List<Long> subEngineeringIdList;
	
	
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public List<Long> getSubEngineeringIdList() {
		return subEngineeringIdList;
	}
	public void setSubEngineeringIdList(List<Long> subEngineeringIdList) {
		this.subEngineeringIdList = subEngineeringIdList;
	}
}
