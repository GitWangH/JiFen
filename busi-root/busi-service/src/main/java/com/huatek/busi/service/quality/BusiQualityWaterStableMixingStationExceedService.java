package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityWaterStableMixingStationExceedDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityWaterStableMixingStationExceedService {
	
	/** 
	* @Title: saveBusiQualityWaterStableMixingStationExceedDto 
	* @Description: 保存BusiQualityWaterStableMixingStationExceed信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityWaterStableMixingStationExceedDto(BusiQualityWaterStableMixingStationExceedDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityWaterStableMixingStationExceed 
	* @Description:  删除BusiQualityWaterStableMixingStationExceed信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityWaterStableMixingStationExceed(Long id) ;

	
	/** 
	* @Title: getBusiQualityWaterStableMixingStationExceedDtoById 
	* @Description: 获取BusiQualityWaterStableMixingStationExceed的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityWaterStableMixingStationExceedDto getBusiQualityWaterStableMixingStationExceedDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityWaterStableMixingStationExceed 
	* @Description:  更新BusiQualityWaterStableMixingStationExceed信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityWaterStableMixingStationExceed(Long id, BusiQualityWaterStableMixingStationExceedDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityWaterStableMixingStationExceedPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityWaterStableMixingStationExceedDto>    
	*/ 
	DataPage<BusiQualityWaterStableMixingStationExceedDto> getAllBusiQualityWaterStableMixingStationExceedPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityWaterStableMixingStationExceedDto 
	* @Description: 获取所有的BusiQualityWaterStableMixingStationExceed
	* @param      
	* @return  List<BusiQualityWaterStableMixingStationExceedDto>    
	* @throws 
	*/
	List<BusiQualityWaterStableMixingStationExceedDto> getAllBusiQualityWaterStableMixingStationExceedDto();
	
}
