package com.huatek.busi.service.contract;

import java.util.List;

import com.huatek.busi.dto.contract.BusiContractTendersBranchDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersBranchDetailService
 * @Description: 分部分项维护服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-08 16:44:00
 * @version: 1.0
 */
public interface BusiContractTendersBranchDetailService {
	
	/** 
	* @Title:  getAllBusiContractTendersBranchDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractTendersBranchDetailDto>    
	*/ 
	DataPage<BusiContractTendersBranchDetailDto> getAllBusiContractTendersBranchDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiContractTendersBranchDetailDto 
	* @Description: 获取所有的BusiContractTendersBranchDetail
	* @param      
	* @return  List<BusiContractTendersBranchDetailDto>    
	* @throws 
	*/
	List<BusiContractTendersBranchDetailDto> getAllBusiContractTendersBranchDetailDto();
	
}
