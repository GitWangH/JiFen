package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityMortarDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityMortarService {
	
	/** 
	* @Title: saveBusiQualityMortarDto 
	* @Description: 保存BusiQualityMortar信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityMortarDto(BusiQualityMortarDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityMortar 
	* @Description:  删除BusiQualityMortar信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityMortar(Long id) ;

	
	/** 
	* @Title: getBusiQualityMortarDtoById 
	* @Description: 获取BusiQualityMortar的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityMortarDto getBusiQualityMortarDtoById(Long id);
	
	/** 
	* @Title: getBusiQualityMortarDtoByIds 
	* @Description: 获取BusiQualityMortar的Dto 
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityMortarDto> getBusiQualityMortarDtoByIds(Long[] ids);

	
    /** 
	* @Title: updateBusiQualityMortar 
	* @Description:  更新BusiQualityMortar信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityMortar(Long id, BusiQualityMortarDto entityDto) ;
	
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
	* @Title:  getAllBusiQualityMortarPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityMortarDto>    
	*/ 
	DataPage<BusiQualityMortarDto> getAllBusiQualityMortarPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityMortarDto 
	* @Description: 获取所有的BusiQualityMortar
	* @param      
	* @return  List<BusiQualityMortarDto>    
	* @throws 
	*/
	List<BusiQualityMortarDto> getAllBusiQualityMortarDto();
	
	/**
	 * 通过整改编号查询砂浆数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityMortarDto> getBusiQualityMortarByReCode(String inspectionCode);

	/**
	 * 流程结束后更改砂浆相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeMortarStatus(String inspectionCode,int result);
	
}
