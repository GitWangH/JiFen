package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceModifyLogDto;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningClearanceSectionSizeCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualitySecondLiningClearanceModifyLogService {
	
	/** 
	* @Title: saveBusiQualitySecondLiningClearanceModifyLogDto 
	* @Description: 保存BusiQualitySecondLiningClearanceModifyLog信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySecondLiningClearanceModifyLogDto(BusiQualitySecondLiningClearanceSectionSizeCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySecondLiningClearanceModifyLog 
	* @Description:  删除BusiQualitySecondLiningClearanceModifyLog信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySecondLiningClearanceModifyLog(Long id) ;

	
	/** 
	* @Title: getBusiQualitySecondLiningClearanceModifyLogDtoById 
	* @Description: 获取BusiQualitySecondLiningClearanceModifyLog的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySecondLiningClearanceModifyLogDto getBusiQualitySecondLiningClearanceModifyLogDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualitySecondLiningClearanceModifyLog 
	* @Description:  更新BusiQualitySecondLiningClearanceModifyLog信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySecondLiningClearanceModifyLog(Long id, BusiQualitySecondLiningClearanceModifyLogDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningClearanceModifyLogPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySecondLiningClearanceModifyLogDto>    
	*/ 
	DataPage<BusiQualitySecondLiningClearanceModifyLogDto> getAllBusiQualitySecondLiningClearanceModifyLogPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningClearanceModifyLogDto 
	* @Description: 获取所有的BusiQualitySecondLiningClearanceModifyLog
	* @param      
	* @return  List<BusiQualitySecondLiningClearanceModifyLogDto>    
	* @throws 
	*/
	List<BusiQualitySecondLiningClearanceModifyLogDto> getAllBusiQualitySecondLiningClearanceModifyLogDto();
	
}
