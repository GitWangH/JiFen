package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 业务模块应用配置的service
 * @author yu_tang
 *
 */
public interface FwApplyScopeService {
	
	/***
	 * 获取查询条件的当前页面.
	 * @param queryPage 查询页面.
	 * @return 返回当前页面的用户数据.
	 */
	DataPage<ApplyScopeDto> getAllFwApplyScopePage(QueryPage queryPage);
	
	/**
	 * 获取所有的业务模块应用配置
	 * 
	 * @return
	 */
	List<ApplyScopeDto> getAllApplyScopeDto();
	
	


	/**
	 * 保存业务模块应用配置
	 * @param fwOrgDao
	 */
	void saveFwApplyScope(ApplyScopeDto applyScopeDto);
	
	
	/**
	 * 获取业务模块应用配置的Dto
	 * 
	 * @param id
	 * @return
	 */
	ApplyScopeDto getFwApplyScopeById(Long id);

	/**
	 * 单条更新业务模块应用配置
	 * @param id
	 * @param applyScopeDto
	 */
	void updateFwApplyScope(Long id, ApplyScopeDto applyScopeDto);

	/**
	 * 删除业务模块应用配置
	 * @param id
	 */
	void deleteFwApplyScope(Long id);
	
	

}
