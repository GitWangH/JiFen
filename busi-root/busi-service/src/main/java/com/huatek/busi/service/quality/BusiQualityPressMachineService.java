package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPressMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPressMachineService {
	
	/** 
	* @Title: saveBusiQualityPressMachineDto 
	* @Description: 保存BusiQualityPressMachine信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPressMachineDto(BusiQualityPressMachineDto entityDto, BusiQualityUniversalPressMachineParentDto parentEntityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPressMachine 
	* @Description:  删除BusiQualityPressMachine信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPressMachine(Long id) ;

	
	/** 
	* @Title: getBusiQualityPressMachineDtoById 
	* @Description: 获取BusiQualityPressMachine的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPressMachineDto getBusiQualityPressMachineDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPressMachine 
	* @Description:  更新BusiQualityPressMachine信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPressMachine(Long id, BusiQualityPressMachineDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPressMachinePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPressMachineDto>    
	*/ 
	DataPage<BusiQualityPressMachineDto> getAllBusiQualityPressMachinePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPressMachineDto 
	* @Description: 获取所有的BusiQualityPressMachine
	* @param      
	* @return  List<BusiQualityPressMachineDto>    
	* @throws 
	*/
	List<BusiQualityPressMachineDto> getAllBusiQualityPressMachineDto();
	
}
