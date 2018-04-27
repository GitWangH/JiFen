package com.huatek.busi.service.phicom.region;

import java.util.List;

import com.huatek.busi.dto.phicom.region.PhiRegionsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiRegionsService {
	
	/** 
	* @Title: savePhiRegionsDto 
	* @Description: 保存PhiRegions信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiRegionsDto(PhiRegionsDto entityDto) ;

	
	/** 
	* @Title: deletePhiRegions 
	* @Description:  删除PhiRegions信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiRegions(Long id) ;

	
	/** 
	* @Title: getPhiRegionsDtoById 
	* @Description: 获取PhiRegions的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiRegionsDto getPhiRegionsDtoById(Long id);

	
    /** 
	* @Title: updatePhiRegions 
	* @Description:  更新PhiRegions信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiRegions(Long id, PhiRegionsDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiRegionsPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiRegionsDto>    
	*/ 
	DataPage<PhiRegionsDto> getAllPhiRegionsPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiRegionsDto 
	* @Description: 获取所有的PhiRegions
	* @param      
	* @return  List<PhiRegionsDto>    
	* @throws 
	*/
	List<PhiRegionsDto> getAllPhiRegionsDto();
	
    void mysave();
	
}
