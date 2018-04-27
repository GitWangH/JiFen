package com.huatek.frame.service;

import java.util.List;
import com.huatek.frame.service.dto.FwOrgDto;

/**
 * FwOrgRpcService
 * @Description: 机构 rpc 服务
 * @time:2017年8月2日 上午10:58:36	
 * @author rocky_wei
 * @email rocky_wei@huatek.com
 */
public interface FwOrgRpcService {
	
	/**
	 * 获取所有的组织
	 * 
	 * @return
	 */
	List<FwOrgDto> getAllOrg();
	
	FwOrgDto getOrgById(Long id);
}
