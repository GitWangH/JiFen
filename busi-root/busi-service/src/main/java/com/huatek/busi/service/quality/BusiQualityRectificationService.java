package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityRectificationService {
	
	/** 
	* @Title: saveBusiQualityRectificationDto 
	* @Description: 保存BusiQualityRectification信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityRectificationDto(BusiQualityRectificationDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityRectification 
	* @Description:  删除BusiQualityRectification信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityRectification(Long id) ;

	
	/** 
	* @Title: getBusiQualityRectificationDtoById 
	* @Description: 获取BusiQualityRectification的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityRectificationDto getBusiQualityRectificationDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityRectification 
	* @Description:  更新BusiQualityRectification信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityRectification(Long id, BusiQualityRectificationDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityRectificationPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityRectificationDto>    
	*/ 
	DataPage<BusiQualityRectificationDto> getAllBusiQualityRectificationPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityRectificationDto 
	* @Description: 获取所有的BusiQualityRectification
	* @param      
	* @return  List<BusiQualityRectificationDto>    
	* @throws 
	*/
	List<BusiQualityRectificationDto> getAllBusiQualityRectificationDto();

	/**
	 * 获取整改单通过整改单编号
	 * @param rectificationCode
	 * @return
	 */
	BusiQualityRectificationDto getBusiQualityRectificationDtoByCode(String rectificationCode);
	
}
