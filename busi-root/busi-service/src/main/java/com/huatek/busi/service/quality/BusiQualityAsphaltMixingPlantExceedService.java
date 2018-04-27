package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityAsphaltMixingPlantExceedDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityAsphaltMixingPlantExceedService {
	
	/** 
	* @Title: saveBusiQualityAsphaltMixingPlantExceedDto 
	* @Description: 保存BusiQualityAsphaltMixingPlantExceed信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityAsphaltMixingPlantExceedDto(BusiQualityAsphaltMixingPlantExceedDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityAsphaltMixingPlantExceed 
	* @Description:  删除BusiQualityAsphaltMixingPlantExceed信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityAsphaltMixingPlantExceed(Long id) ;

	
	/** 
	* @Title: getBusiQualityAsphaltMixingPlantExceedDtoById 
	* @Description: 获取BusiQualityAsphaltMixingPlantExceed的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityAsphaltMixingPlantExceedDto getBusiQualityAsphaltMixingPlantExceedDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityAsphaltMixingPlantExceed 
	* @Description:  更新BusiQualityAsphaltMixingPlantExceed信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityAsphaltMixingPlantExceed(Long id, BusiQualityAsphaltMixingPlantExceedDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityAsphaltMixingPlantExceedPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityAsphaltMixingPlantExceedDto>    
	*/ 
	DataPage<BusiQualityAsphaltMixingPlantExceedDto> getAllBusiQualityAsphaltMixingPlantExceedPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityAsphaltMixingPlantExceedDto 
	* @Description: 获取所有的BusiQualityAsphaltMixingPlantExceed
	* @param      
	* @return  List<BusiQualityAsphaltMixingPlantExceedDto>    
	* @throws 
	*/
	List<BusiQualityAsphaltMixingPlantExceedDto> getAllBusiQualityAsphaltMixingPlantExceedDto();
	
}
