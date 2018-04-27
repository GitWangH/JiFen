package com.huatek.busi.service.base;

import java.util.List;

import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseSubEngineeringDto;
import com.huatek.frame.core.page.QueryPage;

public interface BusiBaseSubEngineeringService {

	/**
	 * 获取所有顶级节点
	 * @param queryPage
	 * @return
	 */
	List<BusiBaseSubEngineeringDto> getTopLevelData(QueryPage queryPage);
	
	/**
	 * 数据操作 包括 保存 修改 删除
	 * @param orgId
	 * @param dtoList
	 */
	List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId, List<BusiBaseSubEngineeringDto> dtoList);
	
	/**
	 * 根据父节点主键获取子节点
	 * @param uuid
	 * @return
	 */
	List<BusiBaseSubEngineeringDto> getChildqNodesByParentPK(Long id, QueryPage queryPage);
	
}
