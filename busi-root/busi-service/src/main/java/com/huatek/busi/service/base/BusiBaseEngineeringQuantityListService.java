package com.huatek.busi.service.base;

import java.util.List;



import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.base.BusiBaseEngineeringQuantityListDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
/**
 * 工程量清单
 * @author eli_cui
 *
 */
public interface BusiBaseEngineeringQuantityListService {
	
	/**
	 * 
	 * @param queryPage
	 * @return
	 */
	List<BusiBaseEngineeringQuantityListDto> getTopLevelData(QueryPage queryPage);
	
	/**
	 * 数据操作 包括 保存 修改 删除
	 * @param orgId
	 * @param dtoList
	 */
	List<TreeGridAddIdAndUUIDDto> dataManipulation(Long orgId, List<BusiBaseEngineeringQuantityListDto> dtoList);
	
	/**
	 * 根据父节点主键获取子节点
	 * @param uuid
	 * @return
	 */
	//List<BusiBaseEngineeringQuantityListDto> getChildqNodesByParentUUIDAndOrgId(QueryPage queryPage);

	List<BusiBaseEngineeringQuantityListDto> getChildqNodesByParentPK(Long id, QueryPage queryPage);
	
}
