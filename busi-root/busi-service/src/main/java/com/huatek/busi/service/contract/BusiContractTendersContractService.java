package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.contract.BusiContractTendersContractDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractDto
 * @Description: 标段合同表 (施工合同)服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 13:29:35
 * @version: 1.0
 */
public interface BusiContractTendersContractService {
	
	/** 
	* @Title: saveBusiContractTendersContractDto 
	* @Description: 保存 标段合同表 (施工合同) 数据信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiContractTendersContractDto(BusiContractTendersContractDto entityDto) ;

	/** 
	* @Title: deleteBusiContractTendersContract 
	* @Description:  删除标段合同表 (施工合同) 数据信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiContractTendersContract(Long id) ;

	
	/** 
	* @Title: getBusiContractTendersContractDtoById 
	* @Description: 根据ID获取标段合同表 (施工合同) 数据的Dto对象 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiContractTendersContractDto getBusiContractTendersContractDtoById(Long id);

	
    /** 
	* @Title: updateBusiContractTendersContract 
	* @Description:  更新标段合同表 (施工合同) 数据信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiContractTendersContract(Long id, BusiContractTendersContractDto entityDto, String[] ignoreTargetFields) ;

	 
	/** 
	* @Title:  getAllBusiContractTendersContractPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractTendersContractDto>    
	*/ 
	DataPage<BusiContractTendersContractDto> getAllBusiContractTendersContractPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiContractTendersContractDto 
	* @Description: 获取所有的标段合同表 (施工合同) 数据
	* @param      
	* @return  List<BusiContractTendersContractDto>    
	* @throws 
	*/
	List<BusiContractTendersContractDto> getAllBusiContractTendersContractDto();

	/**
	 * 流程申请
	 * @param id
	 */
	void applyBusiContractTendersContract(Long id);
	
}
