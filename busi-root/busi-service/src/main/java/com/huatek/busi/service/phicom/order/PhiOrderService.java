package com.huatek.busi.service.phicom.order;

import java.util.List;

import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.product.PhiProductDto;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiOrderService {
	
	/** 
	* @Title: savePhiOrderDto 
	* @Description: 保存PhiOrder信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiOrderDto(PhiOrderDto entityDto) ;

	
	/** 
	* @Title: deletePhiOrder 
	* @Description:  删除PhiOrder信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiOrder(Long id) ;

	
	/** 
	* @Title: getPhiOrderDtoById 
	* @Description: 获取PhiOrder的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiOrderDto getPhiOrderDtoById(Long id);

	
    /** 
	* @Title: updatePhiOrder 
	* @Description:  更新PhiOrder信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiOrder(Long id, PhiOrderDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiOrderPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiOrderDto>    
	*/ 
	DataPage<PhiOrderDto> getAllPhiOrderPage(QueryPage queryPage);
	 
	
	/** 
	* @Title:  getPhiOrderByStatusPage 
	* @Description: 分页查询 通过订单状态
	* @param    queryPage
	* @return  DataPage<PhiOrderDto>    
	*/ 
	DataPage<PhiOrderDto> getPhiOrderByStatusPage(QueryPage queryPage);
	
	
	/** 
	* @Title:  getAllPhiOrderDto 
	* @Description: 获取所有的PhiOrder
	* @param      
	* @return  List<PhiOrderDto>    
	* @throws 
	*/
	List<PhiOrderDto> getAllPhiOrderDto();

	/**
	 * @Description: 新生成一条订单保存
	 * @param phiOrderDto
	 * @param phiProductDto
	 */
	void saveNewOrderDto(PhiOrderDto phiOrderDto, PhiProductDto phiProductDto);
	

	/**
	 * @Description: 新生成一条订单保存
	 * @param phiOrderDto
	 * @param productId
	 */
	void saveNewOrderDto(PhiOrderDto phiOrderDto, Long productId);
	/**
	 * @Description: 计算会员兑换订单
	 *  @param memberId
	 * 
	 */
	
	int getPhiOrderByMemberId(Long memberId);
	
	/**
	 * @Description: 开奖后修改类型为中奖商品的订单相关信息（）
	 * @param id
	 */
	void editOrderWin(Long orderId,Long productId);

	/**
	 * @Description:下单
	 */
	String addOrder(Long productId,Long memberId);


	String addAddressOrder(Long productId, Long memberId, Long addressId);

	/**
	 * 支付失败订单
	 * @param id
	 * @return
	 */
	void  setPhiOrderStatusDto(Long id);

	/**
	 * 支付成功订单信息修改
	 * @param orderId
	 * @return
	 */
	void editOrderAfterPayById(Long orderId);

	/**
	 * 分页获取中奖名单
	 * @param id
	 * @return
	 */
//	DataPage<PhiWinnersListDto> getWinnerPageByMId(QueryPage queryPage);
	
	/**
	 * 设置订单状态
	 * @param orderNo
	 */
	public void setPhiOrderStatusDtoByOrderNo(String orderNo);
	
	/**
	 * 支付成功后编辑订单信息
	 * @param orderNo 订单号
	 * @param payType 支付方式
	 */
	public void editOrderAfterPayByOrderNo(String orderNo,String payType,String transactionId);
	
	/**
	 * 查询订单信息
	 * @param orderNo 订单号
	 * @return
	 */
	public PhiOrderDto findPhiOrderinfoByOrderNo(String orderNo);
	
	PhiOrder findPhiOrderById(Long id);
}
