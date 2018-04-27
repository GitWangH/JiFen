package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.contract.BusiContractSupervisorContractDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiContractSupervisorContractService {
	
	/** 
	* @Title: saveBusiContractSupervisorContractDto 
	* @Description: 保存BusiContractSupervisorContract信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiContractSupervisorContractDto(BusiContractSupervisorContractDto entityDto) ;

	/** 
	* @Title: deleteBusiContractSupervisorContract 
	* @Description:  删除BusiContractSupervisorContract信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiContractSupervisorContract(Long id) ;

	/** 
	* @Title: getBusiContractSupervisorContractDtoById 
	* @Description: 获取BusiContractSupervisorContract的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiContractSupervisorContractDto getBusiContractSupervisorContractDtoById(Long id);

    /** 
	* @Title: updateBusiContractSupervisorContract 
	* @Description:  更新BusiContractSupervisorContract信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiContractSupervisorContract(Long id, BusiContractSupervisorContractDto entityDto, String[] ignoreTargetFields) ;

	/** 
	* @Title:  getAllBusiContractSupervisorContractPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractSupervisorContractDto>    
	*/ 
	DataPage<BusiContractSupervisorContractDto> getAllBusiContractSupervisorContractPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiContractSupervisorContractDto 
	* @Description: 获取所有的BusiContractSupervisorContract
	* @param      
	* @return  List<BusiContractSupervisorContractDto>    
	* @throws 
	*/
	List<BusiContractSupervisorContractDto> getAllBusiContractSupervisorContractDto();

	/**
	 * 流程申请
	 * @param id
	 */
	void applyBusiContractSupervisorContract(Long id);
	
}
