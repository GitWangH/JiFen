package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportThicknessCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPrimarySupportThicknessCheckService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportThicknessCheckDto 
	* @Description: 保存BusiQualityPrimarySupportThicknessCheck信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportThicknessCheckDto(BusiQualityPrimarySupportThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportThicknessCheck 
	* @Description:  删除BusiQualityPrimarySupportThicknessCheck信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportThicknessCheck(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportThicknessCheckDtoById 
	* @Description: 获取BusiQualityPrimarySupportThicknessCheck的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportThicknessCheckDto getBusiQualityPrimarySupportThicknessCheckDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportThicknessCheck 
	* @Description:  更新BusiQualityPrimarySupportThicknessCheck信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportThicknessCheck(Long id, BusiQualityPrimarySupportThicknessCheckDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportThicknessCheckPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportThicknessCheckDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportThicknessCheckDto> getAllBusiQualityPrimarySupportThicknessCheckPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportThicknessCheckDto 
	* @Description: 获取所有的BusiQualityPrimarySupportThicknessCheck
	* @param      
	* @return  List<BusiQualityPrimarySupportThicknessCheckDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportThicknessCheckDto> getAllBusiQualityPrimarySupportThicknessCheckDto();
	
}
