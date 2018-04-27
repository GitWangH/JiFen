package com.huatek.busi.service.phicom.order;

import java.util.List;

import com.huatek.busi.dto.phicom.order.PhiPayInfoDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPayInfoService {
	
	/** 
	* @Title: savePhiPayInfoDto 
	* @Description: 保存PhiPayInfo信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPayInfoDto(PhiPayInfoDto entityDto) ;

	
	/** 
	* @Title: deletePhiPayInfo 
	* @Description:  删除PhiPayInfo信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPayInfo(Long id) ;

	
	/** 
	* @Title: getPhiPayInfoDtoById 
	* @Description: 获取PhiPayInfo的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPayInfoDto getPhiPayInfoDtoById(Long id);

	
    /** 
	* @Title: updatePhiPayInfo 
	* @Description:  更新PhiPayInfo信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPayInfo(Long id, PhiPayInfoDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPayInfoPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPayInfoDto>    
	*/ 
	DataPage<PhiPayInfoDto> getAllPhiPayInfoPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPayInfoDto 
	* @Description: 获取所有的PhiPayInfo
	* @param      
	* @return  List<PhiPayInfoDto>    
	* @throws 
	*/
	List<PhiPayInfoDto> getAllPhiPayInfoDto();
	
}
