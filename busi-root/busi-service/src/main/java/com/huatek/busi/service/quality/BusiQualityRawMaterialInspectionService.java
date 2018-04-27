package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityRawMaterialInspectionService {
	
	/** 
	* @Title: saveBusiQualityRawMaterialInspectionDto 
	* @Description: 保存BusiQualityRawMaterialInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityRawMaterialInspectionDto(BusiQualityRawMaterialInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityRawMaterialInspection 
	* @Description:  删除BusiQualityRawMaterialInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityRawMaterialInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualityRawMaterialInspectionDtoById 
	* @Description: 获取BusiQualityRawMaterialInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityRawMaterialInspectionDto getBusiQualityRawMaterialInspectionDtoById(Long id);

	/** 
	* @Title: getBusiQualityRawMaterialInspectionDtoByIds 
	* @Description: 获取BusiQualityRawMaterialInspection的Dto 
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityRawMaterialInspectionDto> getBusiQualityRawMaterialInspectionDtoByIds(Long[] ids);
	
    /** 
	* @Title: updateBusiQualityRawMaterialInspection 
	* @Description:  更新BusiQualityRawMaterialInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityRawMaterialInspection(Long id, BusiQualityRawMaterialInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityRawMaterialInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityRawMaterialInspectionDto>    
	*/ 
	DataPage<BusiQualityRawMaterialInspectionDto> getAllBusiQualityRawMaterialInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityRawMaterialInspectionDto 
	* @Description: 获取所有的BusiQualityRawMaterialInspection
	* @param      
	* @return  List<BusiQualityRawMaterialInspectionDto>    
	* @throws 
	*/
	List<BusiQualityRawMaterialInspectionDto> getAllBusiQualityRawMaterialInspectionDto();
	
	/**
	 * 获取该整改类型下的所有原材料检测数据
	 * @param inspectionType 整改类型 0 快速处理 1 整改单
	 * @return 
	 */
	List<BusiQualityRawMaterialInspectionDto> getQualityAllRectificateRawMaterialInspection(String inspectionType);
	
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
	 * 获取同一整改类型和快捷处理的所有原材料检测数据
	 * @param inspectionType 整改类型 0 快速处理 1 整改单  inspectionId 快速处理或整改单的ID
	 * @return 
	 */
	List<BusiQualityRawMaterialInspectionDto> getAllRawMaterialInspectionByInspectionTypeAndInspectionId(Integer inspectionType,Long inspectionId);

	/**
	 * 通过整改编号查询原材料数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityRawMaterialInspectionDto> getRawMaterialByReCode(String inspectionCode);

	/**
	 * 流程结束后更改原材料相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeRawMaterialStatus(String inspectionCode,int result);
}
