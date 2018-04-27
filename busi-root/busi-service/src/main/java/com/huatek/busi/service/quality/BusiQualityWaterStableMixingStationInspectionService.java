package com.huatek.busi.service.quality;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityWaterStableMixingStationInspectionDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityWaterStableMixingStationInspectionService {
	
	/** 
	* @Title: saveBusiQualityWaterStableMixingStationInspectionDto 
	* @Description: 保存BusiQualityWaterStableMixingStationInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityWaterStableMixingStationInspectionDto(BusiQualityWaterStableMixingStationInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityWaterStableMixingStationInspection 
	* @Description:  删除BusiQualityWaterStableMixingStationInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityWaterStableMixingStationInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualityWaterStableMixingStationInspectionDtoById 
	* @Description: 获取BusiQualityWaterStableMixingStationInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityWaterStableMixingStationInspectionDto getBusiQualityWaterStableMixingStationInspectionDtoById(Long id);
	
	/** 
	* @Title: getBusiQualityWaterStableMixingStationInspectionDtoByIds 
	* @Description: 获取BusiQualityWaterStableMixingStationInspection的Dto 
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityWaterStableMixingStationInspectionDto> getBusiQualityWaterStableMixingStationInspectionDtoByIds(Long[] ids);


	
    /** 
	* @Title: updateBusiQualityWaterStableMixingStationInspection 
	* @Description:  更新BusiQualityWaterStableMixingStationInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityWaterStableMixingStationInspection(Long id, BusiQualityWaterStableMixingStationInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityWaterStableMixingStationInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityWaterStableMixingStationInspectionDto>    
	*/ 
	DataPage<BusiQualityWaterStableMixingStationInspectionDto> getAllBusiQualityWaterStableMixingStationInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityWaterStableMixingStationInspectionDto 
	* @Description: 获取所有的BusiQualityWaterStableMixingStationInspection
	* @param      
	* @return  List<BusiQualityWaterStableMixingStationInspectionDto>    
	* @throws 
	*/
	List<BusiQualityWaterStableMixingStationInspectionDto> getAllBusiQualityWaterStableMixingStationInspectionDto();
	
	/**
	 * 生成整改单
	 * @param ids 页面获取需要整改的数据编号
	 * @param dto 保存对象
	 */
	void generateBusiQualityRectificate(BusiQualityRectificationDto rectification,Long... ids);
	
	/**
	 * 生成快捷处理
	 * @param ids 页面获取需要整改的数据编号
	 * @param dto 保存对象
	 */
	void generateBusiQualityQuickProcess(BusiQualityQuickProcessingDto dto,Long... ids);
	
	/**
	 * 流程结束后更改水稳拌合站相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeWaterStableMixingStationStatus(String inspectionCode,int result);
	
	/**
	 * 通过整改编号查询水稳拌合站数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityWaterStableMixingStationInspectionDto> getBusiQualityWaterStableMixingStationByReCode(String inspectionCode);
	
}
