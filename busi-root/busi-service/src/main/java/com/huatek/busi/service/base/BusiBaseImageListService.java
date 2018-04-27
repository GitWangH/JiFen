package com.huatek.busi.service.base;

import java.util.List;

import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseEngineeringQuantityListDto;
import com.huatek.busi.dto.base.BusiBaseImageListDto;
import com.huatek.frame.core.page.QueryPage;

/**
 * 形象清单
 * @author eli_cui
 *
 */
public interface BusiBaseImageListService {
	
	/**
	 * 获取所有顶级节点
	 * @param queryPage
	 * @return
	 */
	List<BusiBaseImageListDto> getTopLevelData(QueryPage queryPage);
	
	/**
	 * 数据操作 包括 保存 修改 删除
	 * @param orgId
	 * @param dtoList
	 */
	List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId, List<BusiBaseImageListDto> dtoList);
	
	/**
	 * 根据父节点主键获取子节点
	 * @param uuid
	 * @return
	 */
	List<BusiBaseImageListDto> getChildqNodesByParentPK(Long id, QueryPage queryPage);
	
}
