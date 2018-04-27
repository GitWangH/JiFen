package com.huatek.busi.service.phicom.product;

import java.util.List;
import java.util.Map;

import com.huatek.busi.dto.phicom.product.PhiProductTypeDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiProductTypeService {
	
	/** 
	* @Title: savePhiProductTypeDto 
	* @Description: 保存PhiProductType信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiProductTypeDto(PhiProductTypeDto entityDto) ;

	
	/** 
	* @Title: deletePhiProductType 
	* @Description:  删除PhiProductType信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiProductType(Long id) ;

	
	/** 
	* @Title: getPhiProductTypeDtoById 
	* @Description: 获取PhiProductType的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiProductTypeDto getPhiProductTypeDtoById(Long id);

	
    /** 
	* @Title: updatePhiProductType 
	* @Description:  更新PhiProductType信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiProductType(Long id, PhiProductTypeDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiProductTypePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiProductTypeDto>    
	*/ 
	DataPage<PhiProductTypeDto> getAllPhiProductTypePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiProductTypeDto 
	* @Description: 获取所有的PhiProductType
	* @param      
	* @return  List<PhiProductTypeDto>    
	* @throws 
	*/
	List<PhiProductTypeDto> getAllPhiProductTypeDto();
	
    List<PhiProductTypeDto> getAllProductTypeRecommendForApp();
	
}
