package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrimarySupportConcreteThicknessCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPrimarySupportConcreteThicknessCheckService {
	
	/** 
	* @Title: saveBusiQualityPrimarySupportConcreteThicknessCheckDto 
	* @Description: 保存BusiQualityPrimarySupportConcreteThicknessCheck信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrimarySupportConcreteThicknessCheckDto(BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrimarySupportConcreteThicknessCheck 
	* @Description:  删除BusiQualityPrimarySupportConcreteThicknessCheck信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrimarySupportConcreteThicknessCheck(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrimarySupportConcreteThicknessCheckDtoById 
	* @Description: 获取BusiQualityPrimarySupportConcreteThicknessCheck的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrimarySupportConcreteThicknessCheckDto getBusiQualityPrimarySupportConcreteThicknessCheckDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityPrimarySupportConcreteThicknessCheck 
	* @Description:  更新BusiQualityPrimarySupportConcreteThicknessCheck信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrimarySupportConcreteThicknessCheck(Long id, BusiQualityPrimarySupportConcreteThicknessCheckDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportConcreteThicknessCheckPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrimarySupportConcreteThicknessCheckDto>    
	*/ 
	DataPage<BusiQualityPrimarySupportConcreteThicknessCheckDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrimarySupportConcreteThicknessCheckDto 
	* @Description: 获取所有的BusiQualityPrimarySupportConcreteThicknessCheck
	* @param      
	* @return  List<BusiQualityPrimarySupportConcreteThicknessCheckDto>    
	* @throws 
	*/
	List<BusiQualityPrimarySupportConcreteThicknessCheckDto> getAllBusiQualityPrimarySupportConcreteThicknessCheckDto();
	
}
