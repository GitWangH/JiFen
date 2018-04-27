package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityRollerInspectionDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityRollerInspectionService {
	
	/** 
	* @Title: saveBusiQualityRollerInspectionDto 
	* @Description: 保存BusiQualityRollerInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityRollerInspectionDto(BusiQualityRollerInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityRollerInspection 
	* @Description:  删除BusiQualityRollerInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityRollerInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualityRollerInspectionDtoById 
	* @Description: 获取BusiQualityRollerInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityRollerInspectionDto getBusiQualityRollerInspectionDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityRollerInspection 
	* @Description:  更新BusiQualityRollerInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityRollerInspection(Long id, BusiQualityRollerInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityRollerInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityRollerInspectionDto>    
	*/ 
	DataPage<BusiQualityRollerInspectionDto> getAllBusiQualityRollerInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityRollerInspectionDto 
	* @Description: 获取所有的BusiQualityRollerInspection
	* @param      
	* @return  List<BusiQualityRollerInspectionDto>    
	* @throws 
	*/
	List<BusiQualityRollerInspectionDto> getAllBusiQualityRollerInspectionDto();
	
}
