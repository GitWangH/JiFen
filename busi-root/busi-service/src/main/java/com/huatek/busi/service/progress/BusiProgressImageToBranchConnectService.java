package com.huatek.busi.service.progress;

import java.util.List;

import com.huatek.busi.dto.base.paramEntity.BusiBaseImageListSubConnectionTableParamEntity;
import com.huatek.busi.dto.progress.BusiProgressImageToBranchConnectDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiProgressImageToBranchConnectService {
	
	/** 
	* @Title: saveBusiProgressImageToBranchConnectDto 
	* @Description: 保存BusiProgressImageToBranchConnect信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiProgressImageToBranchConnectDto(BusiProgressImageToBranchConnectDto entityDto) ;

	
	/** 
	* @Title: deleteBusiProgressImageToBranchConnect 
	* @Description:  删除BusiProgressImageToBranchConnect信息
	* @param    ids
	* @return  void    
	*/ 
	void deleteBusiProgressImageToBranchConnect(Long[] ids) ;

	
	/** 
	* @Title: getBusiProgressImageToBranchConnectDtoById 
	* @Description: 获取BusiProgressImageToBranchConnect的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiProgressImageToBranchConnectDto getBusiProgressImageToBranchConnectDtoById(Long id);

	
    /** 
	* @Title: updateBusiProgressImageToBranchConnect 
	* @Description:  更新BusiProgressImageToBranchConnect信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiProgressImageToBranchConnect(Long id, BusiProgressImageToBranchConnectDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiProgressImageToBranchConnectPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiProgressImageToBranchConnectDto>    
	*/ 
	DataPage<BusiProgressImageToBranchConnectDto> getAllBusiProgressImageToBranchConnectPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiProgressImageToBranchConnectDto 
	* @Description: 获取所有的BusiProgressImageToBranchConnect
	* @param      
	* @return  List<BusiProgressImageToBranchConnectDto>    
	* @throws 
	*/
	List<BusiProgressImageToBranchConnectDto> getAllBusiProgressImageToBranchConnectDto();


	void saveBusiProgressImageListSubConnectionTable(BusiBaseImageListSubConnectionTableParamEntity entity);
	
}
