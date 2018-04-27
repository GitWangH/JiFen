package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.frame.core.exception.BusinessCheckException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
/**
 * 快捷处理service服务
 * @author rocky_wei
 *
 */
public interface BusiQualityQuickProcessService {
	
	/** 
	* @Title:  getAllBusiQualityRectificationPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityRectificationDto>    
	*/ 
	DataPage<BusiQualityQuickProcessingDto> getAllBusiQualityQuickProcessPage(QueryPage queryPage) ;

	
	/** 
	* @Title: deleteBusiQualityRectification 
	* @Description:  删除BusiQualityRectification信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityRectification(Long id) ;

	
	/** 
	* @Title: getBusiQualityQuickProcessingDtoById 
	* @Description: 获取BusiQualityQuickProcessingDto 
	* @param    id
	* @return  BusiQualityQuickProcessingDto    
	*/
	BusiQualityQuickProcessingDto getBusiQualityQuickProcessingDtoById(Long id) throws BusinessCheckException ;

	
    /** 
	* @Title: updateBusiQualityRectification 
	* @Description:  更新BusiQualityRectification信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityRectification(Long id, BusiQualityQuickProcessingDto entityDto) ;

	/** 
	* @Title: saveBusiQualityQuickProcessDto 
	* @Description: 保存BusiQualityQuickProcess信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityQuickProcessDto(BusiQualityQuickProcessingDto entityDto);
	 
	/** 
	* @Title:  getAllBusiQualityQuickProcessDto 
	* @Description: 获取所有的BusiQualityRectification
	* @param      
	* @return  List<BusiQualityRectificationDto>    
	* @throws 
	*/
	List<BusiQualityRectificationDto> getAllBusiQualityQuickProcessDto();

	/**
	 * 获取快捷处理单通过编号
	 * @param rectificationCode
	 * @return
	 */
	BusiQualityQuickProcessingDto getBusiQualityQuickProcessByCode(String rectificationCode) throws BusinessCheckException ;
}
