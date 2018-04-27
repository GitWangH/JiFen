package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityUniversalMachineDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityUniversalMachineService {
	
	/** 
	* @Title: saveBusiQualityUniversalMachineDto 
	* @Description: 保存BusiQualityUniversalMachine信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityUniversalMachineDto(BusiQualityUniversalMachineDto entityDto, BusiQualityUniversalPressMachineParentDto parentEntityDto) ;

	
	/** 
	* @Title: deleteBusiQualityUniversalMachine 
	* @Description:  删除BusiQualityUniversalMachine信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityUniversalMachine(Long id) ;

	
	/** 
	* @Title: getBusiQualityUniversalMachineDtoById 
	* @Description: 获取BusiQualityUniversalMachine的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityUniversalMachineDto getBusiQualityUniversalMachineDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityUniversalMachine 
	* @Description:  更新BusiQualityUniversalMachine信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityUniversalMachine(Long id, BusiQualityUniversalMachineDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityUniversalMachinePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityUniversalMachineDto>    
	*/ 
	DataPage<BusiQualityUniversalMachineDto> getAllBusiQualityUniversalMachinePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityUniversalMachineDto 
	* @Description: 获取所有的BusiQualityUniversalMachine
	* @param      
	* @return  List<BusiQualityUniversalMachineDto>    
	* @throws 
	*/
	List<BusiQualityUniversalMachineDto> getAllBusiQualityUniversalMachineDto();
	
}
