package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.contract.BusiContractContractChangeDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiContractContractChangeService {
	
	/** 
	* @Title: saveBusiContractContractChangeDto 
	* @Description: 保存BusiContractContractChange信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiContractContractChangeDto(BusiContractContractChangeDto entityDto) ;

	
	/** 
	* @Title: deleteBusiContractContractChange 
	* @Description:  删除BusiContractContractChange信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiContractContractChange(Long id) ;

	
	/** 
	* @Title: getBusiContractContractChangeDtoById 
	* @Description: 获取BusiContractContractChange的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiContractContractChangeDto getBusiContractContractChangeDtoById(Long id);

	
    /** 
	* @Title: updateBusiContractContractChange 
	* @Description:  更新BusiContractContractChange信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiContractContractChange(Long id, BusiContractContractChangeDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiContractContractChangePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractContractChangeDto>    
	*/ 
	DataPage<BusiContractContractChangeDto> getAllBusiContractContractChangePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiContractContractChangeDto 
	* @Description: 获取所有的BusiContractContractChange
	* @param      
	* @return  List<BusiContractContractChangeDto>    
	* @throws 
	*/
	List<BusiContractContractChangeDto> getAllBusiContractContractChangeDto();


	void applyBusiContractContractChange(Long id);
	
}
