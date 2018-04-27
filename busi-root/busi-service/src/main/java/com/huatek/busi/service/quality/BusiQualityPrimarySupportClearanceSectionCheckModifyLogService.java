package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPrimarySupportClearanceSectionCheckModifyLogService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto 
	* @Description: 保存BusiQualityPrimarySupportClearanceSectionCheckModifyLog信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto(BusiQualityPrimarySupportClearanceSectionCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportClearanceSectionCheckModifyLog 
	* @Description:  删除BusiQualityPrimarySupportClearanceSectionCheckModifyLog信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportClearanceSectionCheckModifyLog(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportClearanceSectionCheckModifyLogDtoById 
	* @Description: 获取BusiQualityPrimarySupportClearanceSectionCheckModifyLog的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto getBusiQualityPrimarySupportClearanceSectionCheckModifyLogDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportClearanceSectionCheckModifyLog 
	* @Description:  更新BusiQualityPrimarySupportClearanceSectionCheckModifyLog信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportClearanceSectionCheckModifyLog(Long id, BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto> getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto 
	* @Description: 获取所有的BusiQualityPrimarySupportClearanceSectionCheckModifyLog
	* @param      
	* @return  List<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportClearanceSectionCheckModifyLogDto> getAllBusiQualityPrimarySupportClearanceSectionCheckModifyLogDto();
	
}
