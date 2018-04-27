package com.huatek.busi.service.phicom.order;

import java.util.List;

import com.huatek.busi.dto.phicom.order.PhiMyproductsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiMyproductsService {
	
	/** 
	* @Title: savePhiMyproductsDto 
	* @Description: 保存PhiMyproducts信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiMyproductsDto(PhiMyproductsDto entityDto) ;

	
	/** 
	* @Title: deletePhiMyproducts 
	* @Description:  删除PhiMyproducts信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiMyproducts(Long id) ;

	
	/** 
	* @Title: getPhiMyproductsDtoById 
	* @Description: 获取PhiMyproducts的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiMyproductsDto getPhiMyproductsDtoById(Long id);

	
    /** 
	* @Title: updatePhiMyproducts 
	* @Description:  更新PhiMyproducts信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiMyproducts(Long id, PhiMyproductsDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiMyproductsPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiMyproductsDto>    
	*/ 
	DataPage<PhiMyproductsDto> getAllPhiMyproductsPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiMyproductsDto 
	* @Description: 获取所有的PhiMyproducts
	* @param      
	* @return  List<PhiMyproductsDto>    
	* @throws 
	*/
	List<PhiMyproductsDto> getAllPhiMyproductsDto();
	
}
