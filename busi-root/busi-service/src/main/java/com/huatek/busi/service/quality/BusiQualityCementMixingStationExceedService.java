package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityCementMixingStationExceedDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityCementMixingStationExceedService {
	
	/** 
	* @Title: saveBusiQualityCementMixingStationExceedDto 
	* @Description: 保存BusiQualityCementMixingStationExceed信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityCementMixingStationExceedDto(BusiQualityCementMixingStationExceedDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityCementMixingStationExceed 
	* @Description:  删除BusiQualityCementMixingStationExceed信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityCementMixingStationExceed(Long id) ;

	
	/** 
	* @Title: getBusiQualityCementMixingStationExceedDtoById 
	* @Description: 获取BusiQualityCementMixingStationExceed的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityCementMixingStationExceedDto getBusiQualityCementMixingStationExceedDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityCementMixingStationExceed 
	* @Description:  更新BusiQualityCementMixingStationExceed信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityCementMixingStationExceed(Long id, BusiQualityCementMixingStationExceedDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityCementMixingStationExceedPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityCementMixingStationExceedDto>    
	*/ 
	DataPage<BusiQualityCementMixingStationExceedDto> getAllBusiQualityCementMixingStationExceedPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityCementMixingStationExceedDto 
	* @Description: 获取所有的BusiQualityCementMixingStationExceed
	* @param      
	* @return  List<BusiQualityCementMixingStationExceedDto>    
	* @throws 
	*/
	List<BusiQualityCementMixingStationExceedDto> getAllBusiQualityCementMixingStationExceedDto();

	/**
	 * 根据UKEY获取超标数据
	 * @param ukey
	 * @return
	 */
	BusiQualityCementMixingStationExceedDto getBusiQualityCementMixingStationExceedByUkey(String uKey);
	
}
