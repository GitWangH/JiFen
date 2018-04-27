package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiMeasureMiddleMeasureDetailService
 * @Description: 中间计量明细Service接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:46:45
 * @version: 1.0
 */
public interface BusiMeasureMiddleMeasureDetailService {
	
	/** 
	* @Title: saveBusiMeasureMiddleMeasureDetailDto 
	* @Description: 保存BusiMeasureMiddleMeasureDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureMiddleMeasureDetailDto(BusiMeasureMiddleMeasureDetailDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureMiddleMeasureDetail 
	* @Description:  删除BusiMeasureMiddleMeasureDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureMiddleMeasureDetail(Long id) ;

	
	/** 
	* @Title: getBusiMeasureMiddleMeasureDetailDtoById 
	* @Description: 获取BusiMeasureMiddleMeasureDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureMiddleMeasureDetailDto getBusiMeasureMiddleMeasureDetailDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureMiddleMeasureDetail 
	* @Description:  更新BusiMeasureMiddleMeasureDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureMiddleMeasureDetail(Long id, BusiMeasureMiddleMeasureDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureMiddleMeasureDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureMiddleMeasureDetailDto>    
	*/ 
	DataPage<BusiMeasureMiddleMeasureDetailDto> getAllBusiMeasureMiddleMeasureDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureMiddleMeasureDetailDto 
	* @Description: 获取所有的BusiMeasureMiddleMeasureDetail
	* @param      
	* @return  List<BusiMeasureMiddleMeasureDetailDto>    
	* @throws 
	*/
	List<BusiMeasureMiddleMeasureDetailDto> getAllBusiMeasureMiddleMeasureDetailDto();
	
}
