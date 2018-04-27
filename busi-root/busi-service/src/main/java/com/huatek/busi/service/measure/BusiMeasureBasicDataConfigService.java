package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureBasicDataConfigDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiMeasureBasicDataConfigService {
	
	/** 
	* @Title: saveBusiMeasureBasicDataConfigDto 
	* @Description: 保存BusiMeasureBasicDataConfig信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureBasicDataConfigDto(BusiMeasureBasicDataConfigDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureBasicDataConfig 
	* @Description:  删除BusiMeasureBasicDataConfig信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureBasicDataConfig(Long id) ;

	
	/** 
	* @Title: getBusiMeasureBasicDataConfigDtoById 
	* @Description: 获取BusiMeasureBasicDataConfig的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureBasicDataConfigDto getBusiMeasureBasicDataConfigDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureBasicDataConfig 
	* @Description:  更新BusiMeasureBasicDataConfig信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureBasicDataConfig(Long id, BusiMeasureBasicDataConfigDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureBasicDataConfigPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureBasicDataConfigDto>    
	*/ 
	DataPage<BusiMeasureBasicDataConfigDto> getAllBusiMeasureBasicDataConfigPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureBasicDataConfigDto 
	* @Description: 获取所有的BusiMeasureBasicDataConfig
	* @param      
	* @return  List<BusiMeasureBasicDataConfigDto>    
	* @throws 
	*/
	List<BusiMeasureBasicDataConfigDto> getAllBusiMeasureBasicDataConfigDto();

	/**
	* @Title: saveOrUpdate 
	* @Description: 保存或者更新 
	* @createDate: 2017年12月6日 下午2:12:18
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void saveOrUpdate(List<BusiMeasureBasicDataConfigDto> saveDatas);

	/**
	* @Title: setBasicConfig 
	* @Description: 设置标段基础数据为所选数据 
	* @createDate: 2017年12月6日 下午7:10:20
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void setBasicConfig(Long id, String tenders);
	
}
