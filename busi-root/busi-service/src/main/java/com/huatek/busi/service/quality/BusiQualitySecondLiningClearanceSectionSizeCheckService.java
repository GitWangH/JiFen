package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceSectionSizeCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualitySecondLiningClearanceSectionSizeCheckService {
	
	/** 
	* @Title: saveBusiQualitySecondLiningClearanceSectionSizeCheckDto 
	* @Description: 保存BusiQualitySecondLiningClearanceSectionSizeCheck信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySecondLiningClearanceSectionSizeCheckDto(BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySecondLiningClearanceSectionSizeCheck 
	* @Description:  删除BusiQualitySecondLiningClearanceSectionSizeCheck信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySecondLiningClearanceSectionSizeCheck(Long id) ;

	
	/** 
	* @Title: getBusiQualitySecondLiningClearanceSectionSizeCheckDtoById 
	* @Description: 获取BusiQualitySecondLiningClearanceSectionSizeCheck的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySecondLiningClearanceSectionSizeCheckDto getBusiQualitySecondLiningClearanceSectionSizeCheckDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualitySecondLiningClearanceSectionSizeCheck 
	* @Description:  更新BusiQualitySecondLiningClearanceSectionSizeCheck信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySecondLiningClearanceSectionSizeCheck(Long id, BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningClearanceSectionSizeCheckPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySecondLiningClearanceSectionSizeCheckDto>    
	*/ 
	DataPage<BusiQualitySecondLiningClearanceSectionSizeCheckDto> getAllBusiQualitySecondLiningClearanceSectionSizeCheckPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningClearanceSectionSizeCheckDto 
	* @Description: 获取所有的BusiQualitySecondLiningClearanceSectionSizeCheck
	* @param      
	* @return  List<BusiQualitySecondLiningClearanceSectionSizeCheckDto>    
	* @throws 
	*/
	List<BusiQualitySecondLiningClearanceSectionSizeCheckDto> getAllBusiQualitySecondLiningClearanceSectionSizeCheckDto();
	
}
