package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityMonthlyReportManagementDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 施工报检service接口
 * @author rocky_wei
 *
 */
public interface BusiQualityMonthlyReportManagementService {
	
	/** 
	* @Title: saveBusiQualityMonthlyReportManagementDto 
	* @Description: 保存BusiQualityMonthlyReportManagement信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityMonthlyReportManagementDto(BusiQualityMonthlyReportManagementDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityMonthlyReportManagement 
	* @Description:  删除BusiQualityMonthlyReportManagement信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityMonthlyReportManagement(Long id) ;

	
	/** 
	* @Title: getBusiQualityMonthlyReportManagementDtoById 
	* @Description: 获取BusiQualityMonthlyReportManagement的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityMonthlyReportManagementDto getBusiQualityMonthlyReportManagementDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityMonthlyReportManagement 
	* @Description:  更新BusiQualityMonthlyReportManagement信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityMonthlyReportManagement(Long id, BusiQualityMonthlyReportManagementDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityMonthlyReportManagementPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityMonthlyReportManagementDto>    
	*/ 
	DataPage<BusiQualityMonthlyReportManagementDto> getAllBusiQualityMonthlyReportManagementPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityMonthlyReportManagementDto 
	* @Description: 获取所有的BusiQualityMonthlyReportManagement
	* @param      
	* @return  List<BusiQualityMonthlyReportManagementDto>    
	* @throws 
	*/
	List<BusiQualityMonthlyReportManagementDto> getAllBusiQualityMonthlyReportManagementDto();

	/**
	 * 上报月报
	 * @param id 编号
	 */
	void monthReportFlowStart(Long id);
	
}
