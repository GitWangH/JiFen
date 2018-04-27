package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportClearanceSectionCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPrimarySupportClearanceSectionCheckService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportClearanceSectionCheckDto 
	* @Description: 保存BusiQualityPrimarySupportClearanceSectionCheck信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportClearanceSectionCheckDto(BusiQualityPrimarySupportClearanceSectionCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportClearanceSectionCheck 
	* @Description:  删除BusiQualityPrimarySupportClearanceSectionCheck信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportClearanceSectionCheck(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportClearanceSectionCheckDtoById 
	* @Description: 获取BusiQualityPrimarySupportClearanceSectionCheck的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportClearanceSectionCheckDto getBusiQualityPrimarySupportClearanceSectionCheckDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportClearanceSectionCheck 
	* @Description:  更新BusiQualityPrimarySupportClearanceSectionCheck信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportClearanceSectionCheck(Long id, BusiQualityPrimarySupportClearanceSectionCheckDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportClearanceSectionCheckPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportClearanceSectionCheckDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportClearanceSectionCheckDto> getAllBusiQualityPrimarySupportClearanceSectionCheckPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportClearanceSectionCheckDto 
	* @Description: 获取所有的BusiQualityPrimarySupportClearanceSectionCheck
	* @param      
	* @return  List<BusiQualityPrimarySupportClearanceSectionCheckDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportClearanceSectionCheckDto> getAllBusiQualityPrimarySupportClearanceSectionCheckDto();
	
}
