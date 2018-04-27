package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityMortarDto;
import com.huatek.busi.dto.quality.BusiQualityPrestressedTensionMainDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityPrestressedTensionMainService {
	
	/** 
	* @Title: saveBusiQualityPrestressedTensionMainDto 
	* @Description: 保存BusiQualityPrestressedTensionMain信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityPrestressedTensionMainDto(BusiQualityPrestressedTensionMainDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityPrestressedTensionMain 
	* @Description:  删除BusiQualityPrestressedTensionMain信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityPrestressedTensionMain(Long id) ;

	
	/** 
	* @Title: getBusiQualityPrestressedTensionMainDtoById 
	* @Description: 获取BusiQualityPrestressedTensionMain的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityPrestressedTensionMainDto getBusiQualityPrestressedTensionMainDtoById(Long id);
	
	/** 
	* @Title: getBusiQualityPrestressedTensionMainDtoByIds 
	* @Description: 获取BusiQualityPrestressedTensionMainDto 
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityPrestressedTensionMainDto> getBusiQualityPrestressedTensionMainDtoByIds(Long[] ids);

	
    /** 
	* @Title: updateBusiQualityPrestressedTensionMain 
	* @Description:  更新BusiQualityPrestressedTensionMain信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityPrestressedTensionMain(Long id, BusiQualityPrestressedTensionMainDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityPrestressedTensionMainPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityPrestressedTensionMainDto>    
	*/ 
	DataPage<BusiQualityPrestressedTensionMainDto> getAllBusiQualityPrestressedTensionMainPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityPrestressedTensionMainDto 
	* @Description: 获取所有的BusiQualityPrestressedTensionMain
	* @param      
	* @return  List<BusiQualityPrestressedTensionMainDto>    
	* @throws 
	*/
	List<BusiQualityPrestressedTensionMainDto> getAllBusiQualityPrestressedTensionMainDto();
	
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
	 * 通过整改编号查询预应张力数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityPrestressedTensionMainDto> getBusiQualityPrestressedTensionMainByReCode(String inspectionCode);

	/**
	 * 流程结束后更改砂浆相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangePrestressedTensionMainStatus(String inspectionCode,int result);
	
}
