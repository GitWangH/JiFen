package com.huatek.busi.service.phicom.coupons;

import java.util.List;

import com.huatek.busi.dto.phicom.coupons.PhiCouponsDto;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiCouponsService {
	
	
	
	/** 
	* @Title: savePhiCouponsDto 
	* @Description: 保存PhiCoupons信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiCouponsDto(PhiCouponsDto entityDto) ;
	
	/** 
	* @Title: deletePhiCoupons 
	* @Description:  删除PhiCoupons信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiCoupons(Long id) ;

	
	/** 
	* @Title: getPhiCouponsDtoById 
	* @Description: 获取PhiCoupons的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiCouponsDto getPhiCouponsDtoById(Long id);

	
    /** 
	* @Title: updatePhiCoupons 
	* @Description:  更新PhiCoupons信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiCoupons(Long id, PhiCouponsDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiCouponsPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiCouponsDto>    
	*/ 
	DataPage<PhiCouponsDto> getAllPhiCouponsPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiCouponsDto 
	* @Description: 获取所有的PhiCoupons
	* @param      
	* @return  List<PhiCouponsDto>    
	* @throws 
	*/
	List<PhiCouponsDto> getAllPhiCouponsDto();
	
	/**
	 * @Title: getPhiCoupons
	 * @Description:根据面值获取优惠券
	 * @param cpnsMoney
	 * @return
	 */
	List<PhiCouponsDto> getPhiCoupons(int cpnsMoney);
	
	/**
	 * @Title: getPhiCouponsByCpnsName
	 * @Description:根据名称获取优惠券
	 * @param cpnsName
	 * @return
	 */
	List<PhiCouponsDto> getPhiCouponsByCpnsName(String  cpnsName);


	/**
	 * PLUS会员优惠劵绑定
	 * @param phiMember
	 */
	List<PhiCouponsDetail> bindingCouponsDetailOfPlusPhiMember(PhiMember phiMember);
}
