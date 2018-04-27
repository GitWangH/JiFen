package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityPrestressedTensionMainDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerSpreaderParentDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualitySpreaderRollerSpreaderParentService {
	
	/** 
	* @Title: saveBusiQualitySpreaderRollerSpreaderParentDto 
	* @Description: 保存BusiQualitySpreaderRollerSpreaderParent信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySpreaderRollerSpreaderParentDto(BusiQualitySpreaderRollerSpreaderParentDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySpreaderRollerSpreaderParent 
	* @Description:  删除BusiQualitySpreaderRollerSpreaderParent信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySpreaderRollerSpreaderParent(Long id) ;

	
	/** 
	* @Title: getBusiQualitySpreaderRollerSpreaderParentDtoById 
	* @Description: 获取BusiQualitySpreaderRollerSpreaderParent的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySpreaderRollerSpreaderParentDto getBusiQualitySpreaderRollerSpreaderParentDtoById(Long id);
	
	/** 
	* @Title: getBusiQualitySpreaderRollerSpreaderParentDtoByIds 
	* @Description: 获取BusiQualitySpreaderRollerSpreaderParentDto 
	* @param    ids
	* @return  MdmLineBaseInfoDto    
	*/
	List<BusiQualitySpreaderRollerSpreaderParentDto> getBusiQualitySpreaderRollerSpreaderParentDtoByIds(Long[] ids);

	
    /** 
	* @Title: updateBusiQualitySpreaderRollerSpreaderParent 
	* @Description:  更新BusiQualitySpreaderRollerSpreaderParent信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySpreaderRollerSpreaderParent(Long id, BusiQualitySpreaderRollerSpreaderParentDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySpreaderRollerSpreaderParentPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySpreaderRollerSpreaderParentDto>    
	*/ 
	DataPage<BusiQualitySpreaderRollerSpreaderParentDto> getAllBusiQualitySpreaderRollerSpreaderParentPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySpreaderRollerSpreaderParentDto 
	* @Description: 获取所有的BusiQualitySpreaderRollerSpreaderParent
	* @param      
	* @return  List<BusiQualitySpreaderRollerSpreaderParentDto>    
	* @throws 
	*/
	List<BusiQualitySpreaderRollerSpreaderParentDto> getAllBusiQualitySpreaderRollerSpreaderParentDto();
	
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
	 * 通过整改编号查询铺摊、压路机数据
	 * @param inspectionCode 整改编号
	 * @return
	 */
	List<BusiQualitySpreaderRollerSpreaderParentDto> getBusiQualitySpreaderRollerSpreaderByReCode(String inspectionCode);

	/**
	 * 流程结束后更改砂浆相关信息
	 * @param inspectionCode 整改单编号
	 * @param result 处理结果
	 */
	void afterFlowEndChangeSpreaderRollerSpreaderStatus(String inspectionCode,int result);
	
}
