package com.huatek.busi.service.phicom.order;

import java.util.List;

import com.huatek.busi.dto.phicom.order.PhiOrderinfoDto;
import com.huatek.busi.model.phicom.order.PhiOrderinfo;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiOrderinfoService {
	
	/** 
	* @Title: savePhiOrderinfoDto 
	* @Description: 保存PhiOrderinfo信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiOrderinfoDto(PhiOrderinfoDto entityDto) ;

	
	/** 
	* @Title: deletePhiOrderinfo 
	* @Description:  删除PhiOrderinfo信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiOrderinfo(Long id) ;
	
	
	/** 
	* @Title: getPhiOrderinfoDtoById 
	* @Description: 获取PhiOrderinfo的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiOrderinfoDto getPhiOrderinfoDtoById(Long id);

	
    /** 
	* @Title: updatePhiOrderinfo 
	* @Description:  更新PhiOrderinfo信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiOrderinfo(Long id, PhiOrderinfoDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiOrderinfoPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiOrderinfoDto>    
	*/ 
	DataPage<PhiOrderinfoDto> getAllPhiOrderinfoPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiOrderinfoDto 
	* @Description: 获取所有的PhiOrderinfo
	* @param      
	* @return  List<PhiOrderinfoDto>    
	* @throws 
	*/
	List<PhiOrderinfoDto> getAllPhiOrderinfoDto();


	PhiOrderinfo findPhiOrderById(Long valueOf);
	
}
