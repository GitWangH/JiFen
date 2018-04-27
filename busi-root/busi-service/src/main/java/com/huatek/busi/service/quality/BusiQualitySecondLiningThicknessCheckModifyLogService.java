package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckDto;
import com.huatek.busi.dto.quality.BusiQualitySecondLiningThicknessCheckModifyLogDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 二衬厚度检测修改日志service接口.
 * @author rocky_wei
 *
 */
public interface BusiQualitySecondLiningThicknessCheckModifyLogService {
	
	/** 
	* @Title: saveBusiQualitySecondLiningThicknessCheckModifyLogDto 
	* @Description: 保存BusiQualitySecondLiningThicknessCheckModifyLog信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySecondLiningThicknessCheckModifyLogDto(BusiQualitySecondLiningThicknessCheckDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySecondLiningThicknessCheckModifyLog 
	* @Description:  删除BusiQualitySecondLiningThicknessCheckModifyLog信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySecondLiningThicknessCheckModifyLog(Long id) ;

	
	/** 
	* @Title: getBusiQualitySecondLiningThicknessCheckModifyLogDtoById 
	* @Description: 获取BusiQualitySecondLiningThicknessCheckModifyLog的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySecondLiningThicknessCheckModifyLogDto getBusiQualitySecondLiningThicknessCheckModifyLogDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualitySecondLiningThicknessCheckModifyLog 
	* @Description:  更新BusiQualitySecondLiningThicknessCheckModifyLog信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySecondLiningThicknessCheckModifyLog(Long id, BusiQualitySecondLiningThicknessCheckModifyLogDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningThicknessCheckModifyLogPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySecondLiningThicknessCheckModifyLogDto>    
	*/ 
	DataPage<BusiQualitySecondLiningThicknessCheckModifyLogDto> getAllBusiQualitySecondLiningThicknessCheckModifyLogPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySecondLiningThicknessCheckModifyLogDto 
	* @Description: 获取所有的BusiQualitySecondLiningThicknessCheckModifyLog
	* @param      
	* @return  List<BusiQualitySecondLiningThicknessCheckModifyLogDto>    
	* @throws 
	*/
	List<BusiQualitySecondLiningThicknessCheckModifyLogDto> getAllBusiQualitySecondLiningThicknessCheckModifyLogDto();
	
}
