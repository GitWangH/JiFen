package com.huatek.busi.service.phicom.member;

import java.util.List;

import com.huatek.busi.dto.phicom.member.PhiVirtualUserDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiVirtualUserService {
	
	/** 
	* @Title: savePhiVirtualUserDto 
	* @Description: 保存PhiVirtualUser信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiVirtualUserDto(PhiVirtualUserDto entityDto) ;

	
	/** 
	* @Title: deletePhiVirtualUser 
	* @Description:  删除PhiVirtualUser信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiVirtualUser(Long id) ;

	
	/** 
	* @Title: getPhiVirtualUserDtoById 
	* @Description: 获取PhiVirtualUser的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiVirtualUserDto getPhiVirtualUserDtoById(Long id);

	
    /** 
	* @Title: updatePhiVirtualUser 
	* @Description:  更新PhiVirtualUser信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiVirtualUser(Long id, PhiVirtualUserDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiVirtualUserPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiVirtualUserDto>    
	*/ 
	DataPage<PhiVirtualUserDto> getAllPhiVirtualUserPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiVirtualUserDto 
	* @Description: 获取所有的PhiVirtualUser
	* @param      
	* @return  List<PhiVirtualUserDto>    
	* @throws 
	*/
	List<PhiVirtualUserDto> getAllPhiVirtualUserDto();
	
}
