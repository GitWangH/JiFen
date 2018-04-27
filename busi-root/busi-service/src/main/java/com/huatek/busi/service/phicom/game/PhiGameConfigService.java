package com.huatek.busi.service.phicom.game;

import java.util.List;

import com.huatek.busi.dto.phicom.game.PhiGameConfigDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiGameConfigService {
	
	/** 
	* @Title: savePhiGameConfigDto 
	* @Description: 保存PhiGameConfig信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiGameConfigDto(PhiGameConfigDto entityDto) ;

	
	/** 
	* @Title: deletePhiGameConfig 
	* @Description:  删除PhiGameConfig信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiGameConfig(Long id) ;

	
	/** 
	* @Title: getPhiGameConfigDtoById 
	* @Description: 获取PhiGameConfig的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiGameConfigDto getPhiGameConfigDtoById(Long id);

	
    /** 
	* @Title: updatePhiGameConfig 
	* @Description:  更新PhiGameConfig信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiGameConfig(Long id, PhiGameConfigDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiGameConfigPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiGameConfigDto>    
	*/ 
	DataPage<PhiGameConfigDto> getAllPhiGameConfigPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiGameConfigDto 
	* @Description: 获取所有的PhiGameConfig
	* @param      
	* @return  List<PhiGameConfigDto>    
	* @throws 
	*/
	List<PhiGameConfigDto> getAllPhiGameConfigDto();
	
	
}
