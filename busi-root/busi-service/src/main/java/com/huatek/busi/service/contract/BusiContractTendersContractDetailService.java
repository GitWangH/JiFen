package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractTendersContractDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractDetailService
 * @Description: 标段合同表 (施工合同)清单管理服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-01 15:12:35
 * @version: 1.0
 */
public interface BusiContractTendersContractDetailService {
	
	/** 
	* @Title:  getAllBusiContractTendersContractDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractTendersContractDetailDto>    
	*/ 
	DataPage<BusiContractTendersContractDetailDto> getAllBusiContractTendersContractDetailPage(QueryPage queryPage);
	
	/**
	 * 查询合同清单列表树数据
	 * @param queryPage
	 * @return
	 */
	public List<BusiContractTendersContractDetailDto> getAllTopLevelBusiContractTendersContractDetailDto(QueryPage queryPage);
	 
	/**
	 * 保存标段合同表 (施工合同)清单列表数据
	 * @param busiContractTendersContractDetailDtoList
	 */
	List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId, List<BusiContractTendersContractDetailDto> busiContractTendersContractDetailDtoList,String detaileType);

	/**
	 * 保存所选的工程量清单数据
	 * @param orgId
	 * @param busiContractTendersContractDetailDtoList
	 */
	List<TreeGridAddIdAndUUIDDto> saveSelecteTreeGridData(Long orgId, List<BusiContractTendersContractDetailDto> busiContractTendersContractDetailDtoList);
	
	/**
	 * 流程申请
	 * @param id
	 */
	public void applyBusiContractTendersContractDetailByOrgId(Long orgId);

	/**
	 * 根据结果ID获取流程信息
	 * @param orgId
	 * @return
	 */
	BusiContractTendersContractDetailDto getBusiContractTendersContractDetailDtoByOrgId(Long orgId);

	/**
	 * 根据父节点查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	List<BusiContractTendersContractDetailDto> getChildBusiContractTendersContractDetailDtoByParentUUID(String parentUUID);
}
