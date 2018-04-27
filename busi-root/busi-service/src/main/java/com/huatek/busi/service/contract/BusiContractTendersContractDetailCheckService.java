package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.contract.BusiContractTendersContractDetailDto;
import com.huatek.frame.core.page.QueryPage;


/**
 * @ClassName: BusiContractTendersContractDetailCheckService
 * @Description: 标段合同表 (施工合同)复核清单管理服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-01 15:12:35
 * @version: 1.0
 */
public interface BusiContractTendersContractDetailCheckService {
	
	/**
	 * 流程申请
	 * @param id
	 */
	public void applyBusiContractTendersContractDetailCheckByOrgId(Long orgId);

	/**
	 * 查询复核清单列表树数据
	 * @param queryPage
	 * @return
	 */
	public List<BusiContractTendersContractDetailDto> getAllTopLevelBusiContractTendersContractDetailDto(QueryPage queryPage);
}
