package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualitySecondLiningThicknessCheckService {
	
	/** 
	* @Title: saveBusiQualitySecondLiningThicknessCheckDto 
	* @Description: 保存BusiQualitySecondLiningThicknessCheck信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySecondLiningThicknessCheckDto(BusiQualitySecondLiningThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySecondLiningThicknessCheck 
	* @Description:  删除BusiQualitySecondLiningThicknessCheck信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySecondLiningThicknessCheck(Long id) ;

	
	/** 
	* @Title: getBusiQualitySecondLiningThicknessCheckDtoById 
	* @Description: 获取BusiQualitySecondLiningThicknessCheck的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySecondLiningThicknessCheckDto getBusiQualitySecondLiningThicknessCheckDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualitySecondLiningThicknessCheck 
	* @Description:  更新BusiQualitySecondLiningThicknessCheck信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySecondLiningThicknessCheck(Long id, BusiQualitySecondLiningThicknessCheckDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningThicknessCheckPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySecondLiningThicknessCheckDto>    
	*/ 
	DataPage<BusiQualitySecondLiningThicknessCheckDto> getAllBusiQualitySecondLiningThicknessCheckPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningThicknessCheckDto 
	* @Description: 获取所有的BusiQualitySecondLiningThicknessCheck
	* @param      
	* @return  List<BusiQualitySecondLiningThicknessCheckDto>    
	* @throws 
	*/
	List<BusiQualitySecondLiningThicknessCheckDto> getAllBusiQualitySecondLiningThicknessCheckDto();
	
}
