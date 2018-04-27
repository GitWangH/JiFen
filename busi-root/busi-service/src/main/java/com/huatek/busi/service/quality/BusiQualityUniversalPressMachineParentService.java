package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerSpreaderParentDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityUniversalPressMachineParentService {
	
	/** 
	* @Title: saveBusiQualityUniversalPressMachineParentDto 
	* @Description: 保存BusiQualityUniversalPressMachineParent信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityUniversalPressMachineParentDto(BusiQualityUniversalPressMachineParentDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityUniversalPressMachineParent 
	* @Description:  删除BusiQualityUniversalPressMachineParent信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityUniversalPressMachineParent(Long id) ;

	
	/** 
	* @Title: getBusiQualityUniversalPressMachineParentDtoById 
	* @Description: 获取BusiQualityUniversalPressMachineParent的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityUniversalPressMachineParentDto getBusiQualityUniversalPressMachineParentDtoById(Long id);
	
	/** 
	* @Title: getBusiQualityUniversalPressMachineParentDtoByIds 
	* @Description: 获取BusiQualityUniversalPressMachineParentDto 
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualityUniversalPressMachineParentDto> getBusiQualityUniversalPressMachineParentDtoByIds(Long[] ids);

	
    /** 
	* @Title: updateBusiQualityUniversalPressMachineParent 
	* @Description:  更新BusiQualityUniversalPressMachineParent信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityUniversalPressMachineParent(Long id, BusiQualityUniversalPressMachineParentDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityUniversalPressMachineParentPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityUniversalPressMachineParentDto>    
	*/ 
	DataPage<BusiQualityUniversalPressMachineParentDto> getAllBusiQualityUniversalPressMachineParentPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityUniversalPressMachineParentDto 
	* @Description: 获取所有的BusiQualityUniversalPressMachineParent
	* @param      
	* @return  List<BusiQualityUniversalPressMachineParentDto>    
	* @throws 
	*/
	List<BusiQualityUniversalPressMachineParentDto> getAllBusiQualityUniversalPressMachineParentDto();
	
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
	 * 通过整改编号查询压力、万能机数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualityUniversalPressMachineParentDto> getBusiQualityUniversalPressMachineParentByReCode(String inspectionCode);

	/**
	 * 流程结束后更改试验检测相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeUniversalPressMachineParentStatus(String inspectionCode,int result);
	
}
