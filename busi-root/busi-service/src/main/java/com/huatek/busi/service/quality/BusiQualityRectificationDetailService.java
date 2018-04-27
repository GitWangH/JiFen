package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityRectificationDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityRectificationDetailService {
	
	/** 
	* @Title: saveBusiQualityRectificationDetailDto 
	* @Description: 保存BusiQualityRectificationDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityRectificationDetailDto(BusiQualityRectificationDetailDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityRectificationDetail 
	* @Description:  删除BusiQualityRectificationDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityRectificationDetail(Long id) ;

	
	/** 
	* @Title: getBusiQualityRectificationDetailDtoById 
	* @Description: 获取BusiQualityRectificationDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityRectificationDetailDto getBusiQualityRectificationDetailDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityRectificationDetail 
	* @Description:  更新BusiQualityRectificationDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityRectificationDetail(Long id, BusiQualityRectificationDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityRectificationDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityRectificationDetailDto>    
	*/ 
	DataPage<BusiQualityRectificationDetailDto> getAllBusiQualityRectificationDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityRectificationDetailDto 
	* @Description: 获取所有的BusiQualityRectificationDetail
	* @param      
	* @return  List<BusiQualityRectificationDetailDto>    
	* @throws 
	*/
	List<BusiQualityRectificationDetailDto> getAllBusiQualityRectificationDetailDto();

	/**
	 * 获取整改单明细通过整改单编码
	 * @param rid
	 * @return
	 */
	List<BusiQualityRectificationDetailDto> getBusiQualityDetailRecordByRectId(Long rid);
	
}
