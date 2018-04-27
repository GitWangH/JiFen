package com.huatek.busi.service.phicom.game;

import java.util.List;

import com.huatek.busi.dto.phicom.game.PhiGameUserDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiGameUserService {
	
	/** 
	* @Title: savePhiGameUserDto 
	* @Description: 保存PhiGameUser信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiGameUserDto(PhiGameUserDto entityDto) ;

	
	/** 
	* @Title: deletePhiGameUser 
	* @Description:  删除PhiGameUser信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiGameUser(Long id) ;

	
	/** 
	* @Title: getPhiGameUserDtoById 
	* @Description: 获取PhiGameUser的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiGameUserDto getPhiGameUserDtoById(Long id);

	
    /** 
	* @Title: updatePhiGameUser 
	* @Description:  更新PhiGameUser信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiGameUser(Long id, PhiGameUserDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiGameUserPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiGameUserDto>    
	*/ 
	DataPage<PhiGameUserDto> getAllPhiGameUserPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiGameUserDto 
	* @Description: 获取所有的PhiGameUser
	* @param      
	* @return  List<PhiGameUserDto>    
	* @throws 
	*/
	List<PhiGameUserDto> getAllPhiGameUserDto();
	
}
