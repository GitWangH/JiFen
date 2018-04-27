package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dto.FwTenantDto;

public interface FwTenantService {
	
	/** 
	* @Title: saveFwTenantDto 
	* @Description: 保存FwTenant信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveFwTenantDto(FwTenantDto entityDto) ;

	
	/** 
	* @Title: deleteFwTenant 
	* @Description:  删除FwTenant信息
	* @param    id
	* @return  void    
	*/ 
	void deleteFwTenant(Long id) ;

	
	/** 
	* @Title: getFwTenantDtoById 
	* @Description: 获取FwTenant的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	FwTenantDto getFwTenantDtoById(Long id);

	
    /** 
	* @Title: updateFwTenant 
	* @Description:  更新FwTenant信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateFwTenant(Long id, FwTenantDto entityDto) ;
	void changeDate(Long id, FwTenantDto fwTenantDto);
	void changeStatus(Long id, int status) ;
	void initData(Long id) ;
	
	/** 
	* @Title:  getAllFwTenantPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<FwTenantDto>    
	*/ 
	DataPage<FwTenantDto> getAllFwTenantPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllFwTenantDto 
	* @Description: 获取所有的FwTenant
	* @param      
	* @return  List<FwTenantDto>    
	* @throws 
	*/
	List<FwTenantDto> getAllFwTenantDto();
	
}
