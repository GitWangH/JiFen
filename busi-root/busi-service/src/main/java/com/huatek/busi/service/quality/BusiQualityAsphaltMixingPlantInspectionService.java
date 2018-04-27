package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityAsphaltMixingPlantInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityAsphaltMixingPlantInspectionService {
	
	/** 
	* @Title: saveBusiQualityAsphaltMixingPlantInspectionDto 
	* @Description: 保存BusiQualityAsphaltMixingPlantInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityAsphaltMixingPlantInspectionDto(BusiQualityAsphaltMixingPlantInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityAsphaltMixingPlantInspection 
	* @Description:  删除BusiQualityAsphaltMixingPlantInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityAsphaltMixingPlantInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualityAsphaltMixingPlantInspectionDtoById 
	* @Description: 获取BusiQualityAsphaltMixingPlantInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityAsphaltMixingPlantInspectionDto getBusiQualityAsphaltMixingPlantInspectionDtoById(Long id);
	
	/** 
	* @Title: getBusiQualityAsphaltMixingPlantInspectionDtoByIds 
	* @Description: 获取BusiQualityAsphaltMixingPlantInspectionDto
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityAsphaltMixingPlantInspectionDto> getBusiQualityAsphaltMixingPlantInspectionDtoByIds(Long[] ids);

	
    /** 
	* @Title: updateBusiQualityAsphaltMixingPlantInspection 
	* @Description:  更新BusiQualityAsphaltMixingPlantInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityAsphaltMixingPlantInspection(Long id, BusiQualityAsphaltMixingPlantInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityAsphaltMixingPlantInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityAsphaltMixingPlantInspectionDto>    
	*/ 
	DataPage<BusiQualityAsphaltMixingPlantInspectionDto> getAllBusiQualityAsphaltMixingPlantInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityAsphaltMixingPlantInspectionDto 
	* @Description: 获取所有的BusiQualityAsphaltMixingPlantInspection
	* @param      
	* @return  List<BusiQualityAsphaltMixingPlantInspectionDto>    
	* @throws 
	*/
	List<BusiQualityAsphaltMixingPlantInspectionDto> getAllBusiQualityAsphaltMixingPlantInspectionDto();
	
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
	 * 通过整改编号查询沥青拌合站数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityAsphaltMixingPlantInspectionDto> getBusiQualityAsphaltMixingPlantByReCode(String inspectionCode);
	
	/**
	 * 流程结束后更改沥青拌合站相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeAsphaltMixingPlantStatus(String inspectionCode,int result);
	
}
