package com.huatek.busi.service.progress;

import java.util.List;

import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseImageListDto;
import com.huatek.busi.dto.progress.BusiProgressImageDto;
import com.huatek.frame.core.page.QueryPage;

public interface BusiProgressImageService {
	
	/**
	 * 获取顶级节点
	 * @param queryPage
	 * @return
	 */
	List<BusiProgressImageDto> getTopLevelData(QueryPage queryPage);
	
	
	/**
	 * 根据父节点主键获取子节点
	 * @param uuid
	 * @return
	 */
	List<BusiProgressImageDto> getChildqNodesByParentPK(Long id, QueryPage queryPage);
	
	/**
	 * 根据父节点主键获取子节点数据 包括设计金额
	 * @param id
	 * @param queryPage
	 * @return
	 */
	List<BusiProgressImageDto> getChildqNodesAndDesignQuantityByParentPK(Long id, QueryPage queryPage);
	
	
	/**
	 * 数据操作 包括 保存 修改 删除
	 * @param orgId
	 * @param dtoList
	 */
	List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId, List<BusiProgressImageDto> dtoList);
	
	/**
	 * 生成形象清单
	 * @param orgId
	 */
	void createProgressImage(Long orgId);
	
	
}
