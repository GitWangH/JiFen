package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPrimarySupportConcreteThicknessCheckModifyLogService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto 
	* @Description: 保存BusiQualityPrimarySupportConcreteThicknessCheckModifyLog信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto(BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportConcreteThicknessCheckModifyLog 
	* @Description:  删除BusiQualityPrimarySupportConcreteThicknessCheckModifyLog信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDtoById 
	* @Description: 获取BusiQualityPrimarySupportConcreteThicknessCheckModifyLog的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto getBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportConcreteThicknessCheckModifyLog 
	* @Description:  更新BusiQualityPrimarySupportConcreteThicknessCheckModifyLog信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportConcreteThicknessCheckModifyLog(Long id, BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto 
	* @Description: 获取所有的BusiQualityPrimarySupportConcreteThicknessCheckModifyLog
	* @param      
	* @return  List<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckModifyLogDto();
	
}
