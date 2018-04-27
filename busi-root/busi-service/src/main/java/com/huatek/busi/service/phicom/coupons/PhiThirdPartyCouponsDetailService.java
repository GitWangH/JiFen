package com.huatek.busi.service.phicom.coupons;

import java.util.List;

import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiThirdPartyCouponsDetailService {
	
	/** 
	* @Title: savePhiThirdPartyCouponsDetailDto 
	* @Description: 保存PhiThirdPartyCouponsDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiThirdPartyCouponsDetailDto(PhiThirdPartyCouponsDetailDto entityDto) ;

	
	/** 
	* @Title: deletePhiThirdPartyCouponsDetail 
	* @Description:  删除PhiThirdPartyCouponsDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiThirdPartyCouponsDetail(Long id) ;

	
	/** 
	* @Title: getPhiThirdPartyCouponsDetailDtoById 
	* @Description: 获取PhiThirdPartyCouponsDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiThirdPartyCouponsDetailDto getPhiThirdPartyCouponsDetailDtoById(Long id);

	
    /** 
	* @Title: updatePhiThirdPartyCouponsDetail 
	* @Description:  更新PhiThirdPartyCouponsDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiThirdPartyCouponsDetail(Long id, PhiThirdPartyCouponsDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiThirdPartyCouponsDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiThirdPartyCouponsDetailDto>    
	*/ 
	DataPage<PhiThirdPartyCouponsDetailDto> getAllPhiThirdPartyCouponsDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiThirdPartyCouponsDetailDto 
	* @Description: 获取所有的PhiThirdPartyCouponsDetail
	* @param      
	* @return  List<PhiThirdPartyCouponsDetailDto>    
	* @throws 
	*/
	List<PhiThirdPartyCouponsDetailDto> getAllPhiThirdPartyCouponsDetailDto();
	
	/** 
	* @Title:  getThirdPartyPhiCouponsDetailById 
	* @Description: 根据第三方优惠券id获取所有的PhiThirdPartyCouponsDetail
	* @param   cpnsId
	* @param   queryPage
	* @return  DataPage<PhiThirdPartyCouponsDetailDto>    
	* @throws 
	*/
	DataPage<PhiThirdPartyCouponsDetailDto> getThirdPartyPhiCouponsDetailById(String cpnsId,QueryPage queryPage);
	
}
