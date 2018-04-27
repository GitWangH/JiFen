package com.huatek.frame.service;


import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwAccountRoleDto;
import com.huatek.frame.service.dto.FwRoleDto;

public interface FwAccountRoleService {
	
	/** 
	* @Title:  getAllFwAccountRolePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<FwAccountRoleDto>    
	*/ 
	DataPage<FwAccountRoleDto> getAllFwAccountRolePage(QueryPage queryPage);
	
	/**
	 * 
	* @Title: RoleAccts 
	* @Description: 分页获取用于分配人员 
	* @createDate: 2017年11月3日 下午2:55:28
	* @param   
	* @return  DataPage<FwAccountDto> 
	* @author cloud_liu   
	* @throws
	 */
	DataPage<FwAccountRoleDto> RoleAccts(QueryPage queryPage);
	
	/**
	 * 
	* @Title: getAccountRoleByAcct 
	* @Description: 获取用户已有角色 
	* @createDate: 2017年11月4日 下午3:27:49
	* @param   
	* @return  List<FwRoleDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwRoleDto> getAccountRoleByAcct(Long acctId);
	 
}
