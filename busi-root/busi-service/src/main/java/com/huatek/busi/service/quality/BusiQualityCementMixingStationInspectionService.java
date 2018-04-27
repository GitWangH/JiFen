package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityCementMixingStationInspectionService {
	
	/** 
	* @Title: saveBusiQualityCementMixingStationInspectionDto 
	* @Description: 保存BusiQualityCementMixingStationInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityCementMixingStationInspectionDto(BusiQualityCementMixingStationInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityCementMixingStationInspection 
	* @Description:  删除BusiQualityCementMixingStationInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityCementMixingStationInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualityCementMixingStationInspectionDtoById 
	* @Description: 获取BusiQualityCementMixingStationInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityCementMixingStationInspectionDto getBusiQualityCementMixingStationInspectionDtoById(Long id);
	
	/** 
	* @Title: getBusiQualityCementMixingStationInspectionDtoByIds 
	* @Description: 获取BusiQualityCementMixingStationInspection的Dto 
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityCementMixingStationInspectionDto> getBusiQualityCementMixingStationInspectionDtoByIds(Long[] ids);

	
    /** 
	* @Title: updateBusiQualityCementMixingStationInspection 
	* @Description:  更新BusiQualityCementMixingStationInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityCementMixingStationInspection(Long id, BusiQualityCementMixingStationInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityCementMixingStationInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityCementMixingStationInspectionDto>    
	*/ 
	DataPage<BusiQualityCementMixingStationInspectionDto> getAllBusiQualityCementMixingStationInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityCementMixingStationInspectionDto 
	* @Description: 获取所有的BusiQualityCementMixingStationInspection
	* @param      
	* @return  List<BusiQualityCementMixingStationInspectionDto>    
	* @throws 
	*/
	List<BusiQualityCementMixingStationInspectionDto> getAllBusiQualityCementMixingStationInspectionDto();
	
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
	 * 流程结束后更改水泥拌合站相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeCementMixingStationStatus(String inspectionCode,int result);
	
	/**
	 * 通过整改编号查询水泥拌合站数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityCementMixingStationInspectionDto> getBusiQualityCementMixingStationByReCode(String inspectionCode);
	
}
