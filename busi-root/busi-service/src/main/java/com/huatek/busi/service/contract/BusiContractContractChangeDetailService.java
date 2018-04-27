package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.contract.BusiContractContractChangeDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiContractContractChangeDetailService {
	
	/** 
	* @Title: saveBusiContractContractChangeDetailDto 
	* @Description: 保存BusiContractContractChangeDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiContractContractChangeDetailDto(BusiContractContractChangeDetailDto entityDto) ;

	
	/** 
	* @Title: deleteBusiContractContractChangeDetail 
	* @Description:  删除BusiContractContractChangeDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiContractContractChangeDetail(Long id) ;

	
	/** 
	* @Title: getBusiContractContractChangeDetailDtoById 
	* @Description: 获取BusiContractContractChangeDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiContractContractChangeDetailDto getBusiContractContractChangeDetailDtoById(Long id);

	
    /** 
	* @Title: updateBusiContractContractChangeDetail 
	* @Description:  更新BusiContractContractChangeDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiContractContractChangeDetail(Long id, BusiContractContractChangeDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiContractContractChangeDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractContractChangeDetailDto>    
	*/ 
	DataPage<BusiContractContractChangeDetailDto> getAllBusiContractContractChangeDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiContractContractChangeDetailDto 
	* @Description: 获取所有的BusiContractContractChangeDetail
	* @param      
	* @return  List<BusiContractContractChangeDetailDto>    
	* @throws 
	*/
	List<BusiContractContractChangeDetailDto> getAllBusiContractContractChangeDetailDto();
	
}
