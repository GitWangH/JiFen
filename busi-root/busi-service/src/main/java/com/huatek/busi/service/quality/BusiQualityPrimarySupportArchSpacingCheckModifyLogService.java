package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportArchSpacingCheckModifyLogDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 初期支护拱架间距检测修改记录service接口
 * @author rocky_wei
 *
 */
public interface BusiQualityPrimarySupportArchSpacingCheckModifyLogService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportArchSpacingCheckModifyLogDto 
	* @Description: 保存BusiQualityPrimarySupportArchSpacingCheckModifyLog信息
	* @param   entityDto 初期支护拱架间距检测Dto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportArchSpacingCheckModifyLogDto(BusiQualityPrimarySupportArchSpacingCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportArchSpacingCheckModifyLog 
	* @Description:  删除BusiQualityPrimarySupportArchSpacingCheckModifyLog信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportArchSpacingCheckModifyLog(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportArchSpacingCheckModifyLogDtoById 
	* @Description: 获取BusiQualityPrimarySupportArchSpacingCheckModifyLog的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportArchSpacingCheckModifyLogDto getBusiQualityPrimarySupportArchSpacingCheckModifyLogDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportArchSpacingCheckModifyLog 
	* @Description:  更新BusiQualityPrimarySupportArchSpacingCheckModifyLog信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportArchSpacingCheckModifyLog(Long id, BusiQualityPrimarySupportArchSpacingCheckModifyLogDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogDto 
	* @Description: 获取所有的BusiQualityPrimarySupportArchSpacingCheckModifyLog
	* @param      
	* @return  List<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportArchSpacingCheckModifyLogDto> getAllBusiQualityPrimarySupportArchSpacingCheckModifyLogDto();
	
}
