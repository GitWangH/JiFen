package com.huatek.busi.service.contract;

import java.util.List;


import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractSupervisorContractDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractSupervisorContractDetailService
 * @Description: 安全(监理)清单管理服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 20:15:35
 * @version: 1.0
 */
public interface BusiContractSupervisorContractDetailService {
	
	/**
	 * 查询安全清单列表树数据
	 * @param queryPage
	 * @return
	 */
	List<BusiContractSupervisorContractDetailDto> getAllTopLevelBusiContractSupervisorContractDetailDto(QueryPage queryPage);


	/**
	 * 根据父节点查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	List<BusiContractSupervisorContractDetailDto> getChildBusiContractSupervisorContractDetailDtoByParentUUID(String parentUUID);


	/**
	 * 保存安全清单列表数据
	 * @param orgId
	 * @param busiContractSupervisorContractDetailDtoList
	 */
	List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId, List<BusiContractSupervisorContractDetailDto> busiContractSupervisorContractDetailDtoList);
	 
	/** 
	* @Title:  getAllBusiContractSupervisorContractDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractSupervisorContractDetailDto>    
	*/ 
	DataPage<BusiContractSupervisorContractDetailDto> getAllBusiContractSupervisorContractDetailPage(QueryPage queryPage);

}
