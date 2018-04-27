package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwRoleGroupDto;

public interface FwRoleGroupService {
	
	/** 
	* @Title: saveFwRoleGroupDto 
	* @Description: 保存FwRoleGroup信息
	* @param   entityDto
	* @return  void    
	*/ 
	FwRoleGroupDto saveFwRoleGroupDto(FwRoleGroupDto entityDto) ;

	
	/** 
	* @Title: deleteFwRoleGroup 
	* @Description:  删除FwRoleGroup信息
	* @param    id
	* @return  void    
	*/ 
	void deleteFwRoleGroup(Long id) ;

	
	/** 
	* @Title: getFwRoleGroupDtoById 
	* @Description: 获取FwRoleGroup的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	FwRoleGroupDto getFwRoleGroupDtoById(Long id);

	
    /** 
	* @Title: updateFwRoleGroup 
	* @Description:  更新FwRoleGroup信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateFwRoleGroup(Long id, FwRoleGroupDto entityDto) ;

	 
	/** 
	* @Title:  getAllFwRoleGroupPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<FwRoleGroupDto>    
	*/ 
	DataPage<FwRoleGroupDto> getAllFwRoleGroupPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllFwRoleGroupDto 
	* @Description: 获取所有的FwRoleGroup
	* @param      
	* @return  List<FwRoleGroupDto>    
	* @throws 
	*/
	List<FwRoleGroupDto> getAllFwRoleGroupDto();

	/**
	 * 
	* @Title: isNextRoleGroup 
	* @Description: 是否有子角色组 
	* @createDate: 2017年11月2日 下午6:28:18
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isNextRoleGroup(Long id, Long tenantId);

}
