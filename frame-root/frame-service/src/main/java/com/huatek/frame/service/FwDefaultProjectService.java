package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dto.FwDefaultProjectDto;

public interface FwDefaultProjectService {
	
	/** 
	* @Title: saveFwDefaultProjectDto 
	* @Description: 保存FwDefaultProject信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveFwDefaultProjectDto(FwDefaultProjectDto entityDto) ;

	
	/** 
	* @Title: deleteFwDefaultProject 
	* @Description:  删除FwDefaultProject信息
	* @param    id
	* @return  void    
	*/ 
	void deleteFwDefaultProject(Long id) ;

	
	/** 
	* @Title: getFwDefaultProjectDtoById 
	* @Description: 获取FwDefaultProject的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	FwDefaultProjectDto getFwDefaultProjectDtoById(Long id);

	
    /** 
	* @Title: updateFwDefaultProject 
	* @Description:  更新FwDefaultProject信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateFwDefaultProject(Long id, FwDefaultProjectDto entityDto) ;

	 
	/** 
	* @Title:  getAllFwDefaultProjectPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<FwDefaultProjectDto>    
	*/ 
	DataPage<FwDefaultProjectDto> getAllFwDefaultProjectPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllFwDefaultProjectDto 
	* @Description: 获取所有的FwDefaultProject
	* @param      
	* @return  List<FwDefaultProjectDto>    
	* @throws 
	*/
	List<FwDefaultProjectDto> getAllFwDefaultProjectDto();

	/**
	* @Title: getFwDefaultProjectDtoByAcctId 
	* @Description: 根据用户ID获取默认 FwDefaultProject
	* @createDate: 2017年10月25日 下午3:00:04
	* @param   
	* @return  FwDefaultProjectDto 
	* @author cloud_liu   
	* @throws
	 */
	FwDefaultProjectDto getFwDefaultProjectDtoByAcctId(Long id);
	
}
