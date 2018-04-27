package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.authority.dto.PropertyDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwDataRoleDto;
import com.huatek.frame.service.dto.SourceDto;
import com.huatek.frame.service.dto.SoureZtreeDto;

/**
 * 组织的service
 * @author yu_tang
 *
 */
public interface FwDataRoleService {
	
	/***
	 * 获取查询条件的当前页面.
	 * @param queryPage 查询页面.
	 * @return 返回当前页面的用户数据.
	 */
	DataPage<FwDataRoleDto> getAllFwDataRolePage(QueryPage queryPage);
	
	/**
	 * 获取所有的数据角色
	 * 
	 * @return
	 */
	List<FwDataRoleDto> getAllFwDataRoleDto();
	
	


	/**
	 * 保存数据角色
	 * @param fwOrgDao
	 */
	void saveFwDataRole(FwDataRoleDto fwDataRoleDto);
	
	/**
	 * 获取角色的Dto
	 * 
	 * @param id
	 * @return
	 */
	FwDataRoleDto getFwDataRoleById(Long id);

	/**
	 * 单条更新数据角色
	 * @param id
	 * @param fwOrgDto
	 */
	void updateFwDataRole(Long id, FwDataRoleDto fwDataRoleDto);

	/**
	 * 删除数据角色
	 * @param id
	 */
	void deleteFwDataRole(Long id);
	
	/**
	 * 分页查询被使用的菜单数据
	 */
	DataPage<SourceDto> getFwSourceInBusinessMapPage(QueryPage queryPage);
	
	/**
	 * 获取当前角色下的菜单Id和实体信息
	 * 
	 * @param sourceId
	 * @param dataRoleId
	 * @return
	 */
	List<SoureZtreeDto> getSourceEntityInfo(Long sourceId, Long dataRoleId) throws ClassNotFoundException;
	
	DataPage<PropertyDto> getEntityObject(Long roleId,Long id,QueryPage queryPage);
	
	/**
	 * 保存数据角色中间关联表
	 * 
	 * @param dataId
	 * @param businessMapId
	 * @param roleId
	 */
	void saveDataRoleAndEntity(Long dataId, Long businessMapId,Long roleId,String checkedOfAll,String entityField);
	
	/**
	 * 分页查询
	 */
	DataPage<FwDataRoleDto> getAllFwDataRolePageUserAssgin(QueryPage queryPage, Long fwAccountId);
	

}
