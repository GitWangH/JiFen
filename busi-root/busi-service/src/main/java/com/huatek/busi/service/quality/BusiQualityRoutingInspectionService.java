package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityRoutingInspectionDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityRoutingInspectionService {
	
	/** 
	* @Title: saveBusiQualityRoutingInspectionDto 
	* @Description: 保存BusiQualityRoutingInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityRoutingInspectionDto(BusiQualityRoutingInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityRoutingInspection 
	* @Description:  删除BusiQualityRoutingInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityRoutingInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualityRoutingInspectionDtoById 
	* @Description: 获取BusiQualityRoutingInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityRoutingInspectionDto getBusiQualityRoutingInspectionDtoById(Long id);
	
	/** 
	* @Title: getBusiQualityRoutingInspectionDtoByIds 
	* @Description: 获取BusiQualityRoutingInspectionDto
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityRoutingInspectionDto> getBusiQualityRoutingInspectionDtoByIds(Long[] ids);

	
    /** 
	* @Title: updateBusiQualityRoutingInspection 
	* @Description:  更新BusiQualityRoutingInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityRoutingInspection(Long id, BusiQualityRoutingInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityRoutingInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityRoutingInspectionDto>    
	*/ 
	DataPage<BusiQualityRoutingInspectionDto> getAllBusiQualityRoutingInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityRoutingInspectionDto 
	* @Description: 获取所有的BusiQualityRoutingInspection
	* @param      
	* @return  List<BusiQualityRoutingInspectionDto>    
	* @throws 
	*/
	List<BusiQualityRoutingInspectionDto> getAllBusiQualityRoutingInspectionDto();
	
	/**
	 * 获取该整改类型下的所有质量巡检数据
	 * @param inspectionType 整改类型 0 快速处理 1 整改单
	 * @return 
	 */
	List<BusiQualityRoutingInspectionDto> getQualityAllRectificateRoutingInspectionInspection(String inspectionType);
	
	/**
	 * 生成整改单
	 * @param ids 页面获取需要整改的数据编号
	 * @param dto 保存对象
	 */
	void generateBusiQualityRectificate(BusiQualityRectificationDto rectification,Long... ids);
	
	/**
	 * 下发整改单
	 * @param ids 页面获取需要整改的数据编号
	 * @param 
	 */
	void rectificateReport(Long... ids);
	
	
	
	/**
	 * 通过整改编号查询质量巡检数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityRoutingInspectionDto> getRoutingInspectionByReCode(String inspectionCode);

	/**
	 * 流程结束后更改质量巡检相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeRoutingInspectionStatus(String inspectionCode,int result);

	/**
	 * 通过整改单id获取对应的质量巡检数据
	 * @param rid
	 * @return
	 */
	BusiQualityRoutingInspectionDto getRoutingByRectificationId(Long rid);
	
}
