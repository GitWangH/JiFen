package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureCycleSettingDetailDto;
import com.huatek.busi.dto.measure.BusiMeasureCycleSettingDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiMeasureCycleSettingService {
	
	/** 
	* @Title: saveBusiMeasureCycleSettingDto 
	* @Description: 保存BusiMeasureCycleSetting信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureCycleSettingDto(BusiMeasureCycleSettingDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureCycleSetting 
	* @Description:  删除BusiMeasureCycleSetting信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureCycleSetting(Long id) ;

	
	/** 
	* @Title: getBusiMeasureCycleSettingDtoById 
	* @Description: 获取BusiMeasureCycleSetting的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureCycleSettingDto getBusiMeasureCycleSettingDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureCycleSetting 
	* @Description:  更新BusiMeasureCycleSetting信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureCycleSetting(Long id, BusiMeasureCycleSettingDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureCycleSettingPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureCycleSettingDto>    
	*/ 
	DataPage<BusiMeasureCycleSettingDto> getAllBusiMeasureCycleSettingPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureCycleSettingDto 
	* @Description: 获取所有的BusiMeasureCycleSetting
	* @param      
	* @return  List<BusiMeasureCycleSettingDto>    
	* @throws 
	*/
	List<BusiMeasureCycleSettingDto> getAllBusiMeasureCycleSettingDto();


	DataPage<BusiMeasureCycleSettingDetailDto> getAllBusiMeasureCycleSettingDetailPage(
			QueryPage queryPage);

	/**
	 * 
	* @Title: saveOrUpdateSettingDetail 
	* @Description: 保存计量周期明细数据 
	* @createDate: 2017年12月7日 下午8:53:40
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void saveOrUpdateSettingDetail(
			List<BusiMeasureCycleSettingDetailDto> saveDatas);

	/**
	 * 
	* @Title: deleteBusiMeasureCycleSettingDetial 
	* @Description: 删除计量周期明细 
	* @createDate: 2017年12月8日 上午9:36:44
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void deleteBusiMeasureCycleSettingDetial(Long id);
	
}
