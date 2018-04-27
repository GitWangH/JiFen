package com.huatek.busi.service.phicom.member;

import java.util.List;

import com.huatek.busi.dto.phicom.plusmember.PhiPlusMemberOrderDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 会员plus订单信息ServiceImpl
 * 
 * @ClassName: PhiPlusMemberOrderService
 * @Description: TODO
 * @author martin_ju
 * @e_mail martin_ju@huatek.com
 * @date 2018年1月24日
 *
 */
public interface PhiPlusMemberOrderService {

	/**
	 * 查询订单信息
	 * @param orderNo 订单号
	 * @return
	 */
	PhiPlusMemberOrderDto getphiPlusMemberOrder(String orderNo);
	
	/**
	 * 保存
	 * @param model 
	 */
	void saveOrUpdatePhiPlusMemberOrder(PhiPlusMemberOrderDto model);
	
	/** 
	* @Title: savePhiPlusMemberOrderDto 
	* @Description: 保存PhiPlusMemberOrder信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPlusMemberOrderDto(PhiPlusMemberOrderDto entityDto) ;

	
	/** 
	* @Title: deletePhiPlusMemberOrder 
	* @Description:  删除PhiPlusMemberOrder信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusMemberOrder(Long id) ;

	
	/** 
	* @Title: getPhiPlusMemberOrderDtoById 
	* @Description: 获取PhiPlusMemberOrder的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusMemberOrderDto getPhiPlusMemberOrderDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusMemberOrder 
	* @Description:  更新PhiPlusMemberOrder信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusMemberOrder(Long id, PhiPlusMemberOrderDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusMemberOrderPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusMemberOrderDto>    
	*/ 
	DataPage<PhiPlusMemberOrderDto> getAllPhiPlusMemberOrderPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusMemberOrderDto 
	* @Description: 获取所有的PhiPlusMemberOrder
	* @param      
	* @return  List<PhiPlusMemberOrderDto>    
	* @throws 
	*/
	List<PhiPlusMemberOrderDto> getAllPhiPlusMemberOrderDto();

	/**
	 * 保存备注
	 * @param id
	 * @param remark
	 */
	void saveRemark(Long id, String remark);
}
