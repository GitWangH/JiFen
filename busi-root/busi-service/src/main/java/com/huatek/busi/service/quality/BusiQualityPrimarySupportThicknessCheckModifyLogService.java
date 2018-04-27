package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckModifyLogDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 初期支护厚度检测修改日志service服务.
 * @author rocky_wei
 *
 */
public interface BusiQualityPrimarySupportThicknessCheckModifyLogService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportThicknessCheckModifyLogDto 
	* @Description: 保存BusiQualityPrimarySupportThicknessCheckModifyLog信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportThicknessCheckModifyLogDto(BusiQualityPrimarySupportThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportThicknessCheckModifyLog 
	* @Description:  删除BusiQualityPrimarySupportThicknessCheckModifyLog信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportThicknessCheckModifyLog(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportThicknessCheckModifyLogDtoById 
	* @Description: 获取BusiQualityPrimarySupportThicknessCheckModifyLog的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportThicknessCheckModifyLogDto getBusiQualityPrimarySupportThicknessCheckModifyLogDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportThicknessCheckModifyLog 
	* @Description:  更新BusiQualityPrimarySupportThicknessCheckModifyLog信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportThicknessCheckModifyLog(Long id, BusiQualityPrimarySupportThicknessCheckModifyLogDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportThicknessCheckModifyLogPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportThicknessCheckModifyLogDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportThicknessCheckModifyLogPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportThicknessCheckModifyLogDto 
	* @Description: 获取所有的BusiQualityPrimarySupportThicknessCheckModifyLog
	* @param      
	* @return  List<BusiQualityPrimarySupportThicknessCheckModifyLogDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportThicknessCheckModifyLogDto> getAllBusiQualityPrimarySupportThicknessCheckModifyLogDto();
	
}
