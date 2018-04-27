package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractOtherContractDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractOtherContractDetailService
 * @Description: 其它合同清单管理服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 17:07:35
 * @version: 1.0
 */
public interface BusiContractOtherContractDetailService {
	
	/** 
	* @Title:  getAllBusiContractOtherContractDetailDto 
	* @Description: 获取所有的BusiContractOtherContractDetail
	* @param      
	* @return  List<BusiContractOtherContractDetailDto>    
	* @throws 
	*/
	public DataPage<BusiContractOtherContractDetailDto> getAllBusiContractOtherContractDetailPage(QueryPage queryPage);

	/**
	 * 保存其它合同清单列表数据
	 * @param busiContractOtherContractDetailDtoList
	 */
	public List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId, List<BusiContractOtherContractDetailDto> busiContractOtherContractDetailDtoList);

	/**
	 * 查询所有顶级节点
	 * @param queryPage
	 * @return
	 */
	public List<BusiContractOtherContractDetailDto> getAllTopLevelBusiContractOtherContractDetailDto(QueryPage queryPage);

	/**
	 * 根据父节点的UUID查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	public List<BusiContractOtherContractDetailDto> getChildBusiContractOtherContractDetailDtoByParentUUID(String parentUUID);
	
}
