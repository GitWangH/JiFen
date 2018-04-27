package com.huatek.busi.service.phicom.product;

import java.util.List;

import com.huatek.busi.dto.phicom.product.PhiProductDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiProductDetailService {
	
	/** 
	* @Title: savePhiProductDetailDto 
	* @Description: 保存PhiProduct信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiProductDetielDto(PhiProductDetailDto entityDto) ;

	
	/** 
	* @Title: deletePhiProduct 
	* @Description:  删除PhiProduct信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiProductDetail(Long id) ;

	
	/** 
	* @Title: getPhiProductDtoById 
	* @Description: 获取PhiProduct的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiProductDetailDto getPhiProductDtoDetailById(Long id);

	
    /** 
	* @Title: updatePhiProduct 
	* @Description:  更新PhiProduct信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiProductDetail(Long id, PhiProductDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiProductPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiProductDto>    
	*/ 
	DataPage<PhiProductDetailDto> getAllPhiProductDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiProductDto 
	* @Description: 获取所有的PhiProduct
	* @param      
	* @return  List<PhiProductDto>    
	* @throws 
	*/
	List<PhiProductDetailDto> getAllPhiProductDetailDto();
	
	
	
	
	
}
