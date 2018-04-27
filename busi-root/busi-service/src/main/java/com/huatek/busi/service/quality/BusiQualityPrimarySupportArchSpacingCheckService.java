package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPrimarySupportArchSpacingCheckService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportArchSpacingCheckDto 
	* @Description: 保存BusiQualityPrimarySupportArchSpacingCheck信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportArchSpacingCheckDto(BusiQualityPrimarySupportArchSpacingCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportArchSpacingCheck 
	* @Description:  删除BusiQualityPrimarySupportArchSpacingCheck信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportArchSpacingCheck(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportArchSpacingCheckDtoById 
	* @Description: 获取BusiQualityPrimarySupportArchSpacingCheck的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportArchSpacingCheckDto getBusiQualityPrimarySupportArchSpacingCheckDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportArchSpacingCheck 
	* @Description:  更新BusiQualityPrimarySupportArchSpacingCheck信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportArchSpacingCheck(Long id, BusiQualityPrimarySupportArchSpacingCheckDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportArchSpacingCheckPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportArchSpacingCheckDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportArchSpacingCheckDto> getAllBusiQualityPrimarySupportArchSpacingCheckPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportArchSpacingCheckDto 
	* @Description: 获取所有的BusiQualityPrimarySupportArchSpacingCheck
	* @param      
	* @return  List<BusiQualityPrimarySupportArchSpacingCheckDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportArchSpacingCheckDto> getAllBusiQualityPrimarySupportArchSpacingCheckDto();
	
}
