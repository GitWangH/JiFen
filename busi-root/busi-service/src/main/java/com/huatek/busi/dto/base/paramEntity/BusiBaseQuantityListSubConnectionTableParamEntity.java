package com.huatek.busi.dto.base.paramEntity;

import java.util.List;

/**
 * 分部分项与工程量清单挂接
 * @author eli_cui
 *
 */
public class BusiBaseQuantityListSubConnectionTableParamEntity {
	/** 分部分项id */
	private Long subEngineeringId;
	/** 工程量清单id List*/
	private List<Long> engineeringQuantityIdList;

	public Long getSubEngineeringId() {
		return subEngineeringId;
	}

	public void setSubEngineeringId(Long subEngineeringId) {
		this.subEngineeringId = subEngineeringId;
	}

	public List<Long> getEngineeringQuantityIdList() {
		return engineeringQuantityIdList;
	}

	public void setEngineeringQuantityIdList(List<Long> engineeringQuantityIdList) {
		this.engineeringQuantityIdList = engineeringQuantityIdList;
	}
}
