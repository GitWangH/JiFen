package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 业务模块的service
 * @author yu_tang
 *
 */
public interface FwBusinessMapService {
	
	/***
	 * 获取查询条件的当前页面.
	 * @param queryPage 查询页面.
	 * @return 返回当前页面的用户数据.
	 */
	DataPage<BusinessMapDto> getAllFwBusinessMapPage(QueryPage queryPage);
	
	/**
	 * 获取所有的业务模块
	 * 
	 * @return
	 */
	List<BusinessMapDto> getAllFwBusinessMapDto();
	
	


	/**
	 * 保存业务模块
	 * @param fwOrgDao
	 */
	void saveFwBusinessMap(BusinessMapDto fwBusinessMapDto);
	
	/**
	 * 获取业务模块的Dto
	 * 
	 * @param id
	 * @return
	 */
	BusinessMapDto getFwBusinessMapById(Long id);

	/**
	 * 单条更新业务模块
	 * @param id
	 * @param fwOrgDto
	 */
	void updateFwBusinessMap(Long id, BusinessMapDto fwBusinessMapDto);

	/**
	 * 删除业务模块
	 * @param id
	 */
	void deleteFwBusinessMap(Long id);
	
	

}
