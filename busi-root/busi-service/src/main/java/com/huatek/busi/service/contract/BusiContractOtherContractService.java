package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.contract.BusiContractOtherContractDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractOtherContractService
 * @Description: 其它合同服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-27 13:33:35
 * @version: 1.0
 */
public interface BusiContractOtherContractService {
	
	/** 
	* @Title: saveBusiContractOtherContractDto 
	* @Description: 保存BusiContractOtherContract信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiContractOtherContractDto(BusiContractOtherContractDto entityDto) ;

	
	/** 
	* @Title: deleteBusiContractOtherContract 
	* @Description:  删除BusiContractOtherContract信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiContractOtherContract(Long id) ;

	
	/** 
	* @Title: getBusiContractOtherContractDtoById 
	* @Description: 获取BusiContractOtherContract的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiContractOtherContractDto getBusiContractOtherContractDtoById(Long id);

	
    /** 
	* @Title: updateBusiContractOtherContract 
	* @Description:  更新BusiContractOtherContract信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiContractOtherContract(Long id, BusiContractOtherContractDto entityDto, String[] ignoreTargetFields) ;

	 
	/** 
	* @Title:  getAllBusiContractOtherContractPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractOtherContractDto>    
	*/ 
	DataPage<BusiContractOtherContractDto> getAllBusiContractOtherContractPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiContractOtherContractDto 
	* @Description: 获取所有的BusiContractOtherContract
	* @param      
	* @return  List<BusiContractOtherContractDto>    
	* @throws 
	*/
	List<BusiContractOtherContractDto> getAllBusiContractOtherContractDto();
	
}
