package com.huatek.busi.service.phicom.coupons;

import java.util.List;

import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiThirdPartyCouponsService {
	
	/** 
	* @Title: savePhiThirdPartyCouponsDto 
	* @Description: 保存PhiThirdPartyCoupons信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiThirdPartyCouponsDto(PhiThirdPartyCouponsDto entityDto) ;

	
	/** 
	* @Title: deletePhiThirdPartyCoupons 
	* @Description:  删除PhiThirdPartyCoupons信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiThirdPartyCoupons(Long id) ;

	
	/** 
	* @Title: getPhiThirdPartyCouponsDtoById 
	* @Description: 获取PhiThirdPartyCoupons的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiThirdPartyCouponsDto getPhiThirdPartyCouponsDtoById(Long id);

	
    /** 
	* @Title: updatePhiThirdPartyCoupons 
	* @Description:  更新PhiThirdPartyCoupons信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiThirdPartyCoupons(Long id, PhiThirdPartyCouponsDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiThirdPartyCouponsPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiThirdPartyCouponsDto>    
	*/ 
	DataPage<PhiThirdPartyCouponsDto> getAllPhiThirdPartyCouponsPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiThirdPartyCouponsDto 
	* @Description: 获取所有的PhiThirdPartyCoupons
	* @param      
	* @return  List<PhiThirdPartyCouponsDto>    
	* @throws 
	*/
	List<PhiThirdPartyCouponsDto> getAllPhiThirdPartyCouponsDto();
	
}
