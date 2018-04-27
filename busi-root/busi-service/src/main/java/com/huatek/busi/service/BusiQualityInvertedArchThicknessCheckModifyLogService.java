package com.huatek.busi.service;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckModifyLogDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityInvertedArchThicknessCheckModifyLogService {
	
	/** 
	* @Title: saveBusiQualityInvertedArchThicknessCheckModifyLogDto 
	* @Description: 保存BusiQualityInvertedArchThicknessCheckModifyLog信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityInvertedArchThicknessCheckModifyLogDto(BusiQualityInvertedArchThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityInvertedArchThicknessCheckModifyLog 
	* @Description:  删除BusiQualityInvertedArchThicknessCheckModifyLog信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityInvertedArchThicknessCheckModifyLog(Long id) ;

	
	/** 
	* @Title: getBusiQualityInvertedArchThicknessCheckModifyLogDtoById 
	* @Description: 获取BusiQualityInvertedArchThicknessCheckModifyLog的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityInvertedArchThicknessCheckModifyLogDto getBusiQualityInvertedArchThicknessCheckModifyLogDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityInvertedArchThicknessCheckModifyLog 
	* @Description:  更新BusiQualityInvertedArchThicknessCheckModifyLog信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityInvertedArchThicknessCheckModifyLog(Long id, BusiQualityInvertedArchThicknessCheckModifyLogDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityInvertedArchThicknessCheckModifyLogPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityInvertedArchThicknessCheckModifyLogDto>    
	*/ 
	DataPage<BusiQualityInvertedArchThicknessCheckModifyLogDto> getAllBusiQualityInvertedArchThicknessCheckModifyLogPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityInvertedArchThicknessCheckModifyLogDto 
	* @Description: 获取所有的BusiQualityInvertedArchThicknessCheckModifyLog
	* @param      
	* @return  List<BusiQualityInvertedArchThicknessCheckModifyLogDto>    
	* @throws 
	*/
	List<BusiQualityInvertedArchThicknessCheckModifyLogDto> getAllBusiQualityInvertedArchThicknessCheckModifyLogDto();
	
}
