package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiMeasureMiddleMeasureService
 * @Description: 中间计量Service接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:46:45
 * @version: 1.0
 */
public interface BusiMeasureMiddleMeasureService {
	
	/** 
	* @Title: saveBusiMeasureMiddleMeasureDto 
	* @Description: 保存BusiMeasureMiddleMeasure信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureMiddleMeasureDto(BusiMeasureMiddleMeasureDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureMiddleMeasure 
	* @Description:  删除BusiMeasureMiddleMeasure信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureMiddleMeasure(Long id) ;

	
	/** 
	* @Title: getBusiMeasureMiddleMeasureDtoById 
	* @Description: 获取BusiMeasureMiddleMeasure的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureMiddleMeasureDto getBusiMeasureMiddleMeasureDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureMiddleMeasure 
	* @Description:  更新BusiMeasureMiddleMeasure信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureMiddleMeasure(Long id, BusiMeasureMiddleMeasureDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureMiddleMeasurePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureMiddleMeasureDto>    
	*/ 
	DataPage<BusiMeasureMiddleMeasureDto> getAllBusiMeasureMiddleMeasurePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureMiddleMeasureDto 
	* @Description: 获取所有的BusiMeasureMiddleMeasure
	* @param      
	* @return  List<BusiMeasureMiddleMeasureDto>    
	* @throws 
	*/
	List<BusiMeasureMiddleMeasureDto> getAllBusiMeasureMiddleMeasureDto();
	
}
