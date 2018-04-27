package com.huatek.busi.service.phicom.coupons;

import java.util.List;

import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiCouponsDetailService {
	
	/** 
	* @Title: savePhiCouponsDetailDto 
	* @Description: 保存PhiCouponsDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiCouponsDetailDto(PhiCouponsDetailDto entityDto) ;

	
	
	/** 
	* @Title: deletePhiCouponsDetail 
	* @Description:  删除PhiCouponsDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiCouponsDetail(Long id) ;

	
	/** 
	* @Title: getPhiCouponsDetailDtoById 
	* @Description: 获取PhiCouponsDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiCouponsDetailDto getPhiCouponsDetailDtoById(Long id);

	
    /** 
	* @Title: updatePhiCouponsDetail 
	* @Description:  更新PhiCouponsDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiCouponsDetail(Long id, PhiCouponsDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiCouponsDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiCouponsDetailDto>    
	*/ 
	DataPage<PhiCouponsDetailDto> getAllPhiCouponsDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiCouponsDetailDto 
	* @Description: 获取所有的PhiCouponsDetail
	* @param      
	* @return  List<PhiCouponsDetailDto>    
	* @throws 
	*/
	List<PhiCouponsDetailDto> getAllPhiCouponsDetailDto();
	
	
	DataPage<PhiCouponsDetailDto> getPhiCouponsDetailDtoByfatharId(QueryPage queryPage);
	/**
	 * @Title: getPhiCouponsDetailById
	 * @Description: 根据wayId查询优惠券明细
	 * @param wayId
	 * @param queryPage
	 * @return  DataPage<PhiCouponsDetailDto>
	 */
	DataPage<PhiCouponsDetailDto> getPhiCouponsDetailById(Long wayId,QueryPage queryPage);


	/**
	 * 根据方案ID查询优惠劵
	 * @param coupWayId
	 * @return
	 */
	PhiCouponsDetail getPhiCouponsDetailDtoByCoupWayId(Long coupWayId);



	/**
	 * PLUS会员开通专用
	 * @param newCouponsDetial
	 */
	void updatePhiCouponsDetailForOpenPlus(PhiCouponsDetail newCouponsDetial);
	
	
}
