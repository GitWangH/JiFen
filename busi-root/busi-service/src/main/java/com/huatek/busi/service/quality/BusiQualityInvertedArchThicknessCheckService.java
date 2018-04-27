package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityInvertedArchThicknessCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityInvertedArchThicknessCheckService {
	
	/** 
	* @Title: saveBusiQualityInvertedArchThicknessCheckDto 
	* @Description: 保存BusiQualityInvertedArchThicknessCheck信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityInvertedArchThicknessCheckDto(BusiQualityInvertedArchThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityInvertedArchThicknessCheck 
	* @Description:  删除BusiQualityInvertedArchThicknessCheck信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityInvertedArchThicknessCheck(Long id) ;

	
	/** 
	* @Title: getBusiQualityInvertedArchThicknessCheckDtoById 
	* @Description: 获取BusiQualityInvertedArchThicknessCheck的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityInvertedArchThicknessCheckDto getBusiQualityInvertedArchThicknessCheckDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityInvertedArchThicknessCheck 
	* @Description:  更新BusiQualityInvertedArchThicknessCheck信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityInvertedArchThicknessCheck(Long id, BusiQualityInvertedArchThicknessCheckDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityInvertedArchThicknessCheckPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityInvertedArchThicknessCheckDto>    
	*/ 
	DataPage<BusiQualityInvertedArchThicknessCheckDto> getAllBusiQualityInvertedArchThicknessCheckPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityInvertedArchThicknessCheckDto 
	* @Description: 获取所有的BusiQualityInvertedArchThicknessCheck
	* @param      
	* @return  List<BusiQualityInvertedArchThicknessCheckDto>    
	* @throws 
	*/
	List<BusiQualityInvertedArchThicknessCheckDto> getAllBusiQualityInvertedArchThicknessCheckDto();
	
}
