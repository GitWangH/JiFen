package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 组织的service
 * @author yu_tang
 *
 */
public interface FwSourceEntityService {
	
	/***
	 * 获取查询条件的当前页面.
	 * @param queryPage 查询页面.
	 * @return 返回当前页面的用户数据.
	 */
	DataPage<SourceEntityDto> getAllFwSourceEntityPage(QueryPage queryPage);
	
	/**
	 * 获取所有的数据角色
	 * 
	 * @return
	 */
	List<SourceEntityDto> getAllSourceEntityDto();
	
	


	/**
	 * 保存数据角色
	 * @param fwOrgDao
	 */
	void saveFwSourceEntity(SourceEntityDto sourceEntityDto);
	
	
	/**
	 * 获取角色的Dto
	 * 
	 * @param id
	 * @return
	 */
	SourceEntityDto getFwSourceEntityById(Long id);

	/**
	 * 单条更新数据角色
	 * @param id
	 * @param fwOrgDto
	 */
	void updateFwSourceEntity(Long id, SourceEntityDto sourceEntityDto);

	/**
	 * 删除数据角色
	 * @param id
	 */
	void deleteFwSourceEntity(Long id);
	
	List<ParamDto> getAllFwSourceEntityParamDto();

}
