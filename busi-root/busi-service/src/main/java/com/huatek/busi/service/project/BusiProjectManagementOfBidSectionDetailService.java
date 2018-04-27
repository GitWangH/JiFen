package com.huatek.busi.service.project;

import java.util.List;

import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 工程标段管理明细
 * @author eli_cui
 *
 */
public interface BusiProjectManagementOfBidSectionDetailService {
	
	/** 
	* @Title: saveBusiProjectManagementOfBidSectionDetailDto 
	* @Description: 保存BusiProjectManagementOfBidSectionDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiProjectManagementOfBidSectionDetailDto(BusiProjectManagementOfBidSectionDetailDto entityDto) ;

	
	/** 
	* @Title: deleteBusiProjectManagementOfBidSectionDetail 
	* @Description:  删除BusiProjectManagementOfBidSectionDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiProjectManagementOfBidSectionDetail(Long id) ;

	
	/** 
	* @Title: getBusiProjectManagementOfBidSectionDetailDtoById 
	* @Description: 获取BusiProjectManagementOfBidSectionDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiProjectManagementOfBidSectionDetailDto getBusiProjectManagementOfBidSectionDetailDtoById(Long id);

	
    /** 
	* @Title: updateBusiProjectManagementOfBidSectionDetail 
	* @Description:  更新BusiProjectManagementOfBidSectionDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiProjectManagementOfBidSectionDetail(Long id, BusiProjectManagementOfBidSectionDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiProjectManagementOfBidSectionDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiProjectManagementOfBidSectionDetailDto>    
	*/ 
	DataPage<BusiProjectManagementOfBidSectionDetailDto> getAllBusiProjectManagementOfBidSectionDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiProjectManagementOfBidSectionDetailDto 
	* @Description: 获取所有的BusiProjectManagementOfBidSectionDetail
	* @param      
	* @return  List<BusiProjectManagementOfBidSectionDetailDto>    
	* @throws 
	*/
	List<BusiProjectManagementOfBidSectionDetailDto> getAllBusiProjectManagementOfBidSectionDetailDto();
	
}
