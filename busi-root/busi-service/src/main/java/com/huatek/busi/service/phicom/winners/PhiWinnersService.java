package com.huatek.busi.service.phicom.winners;

import java.util.List;
import java.util.Map;

import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.winner.PhiVirtualUser;
import com.huatek.busi.dto.phicom.winner.PhiWinnersListDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiWinnersService {
	
	
	
	/**
	 * 通过产品id找到对应的会员id
	 * @param id
	 * @return
	 */
    List<PhiOrderDto> getAllOrderById(Long id);
	/** 
	* @Title: savePhiWinnersDto 
	* @Description: 保存PhiWinners信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiWinnersDto(PhiWinnersListDto entityDto) ;

	
	/** 
	* @Title: deletePhiWinners 
	* @Description:  删除PhiWinners信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiWinners(Long id) ;
	

	
	/** 
	* @Title: getPhiWinnersDtoById 
	* @Description: 获取PhiWinners的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiWinnersListDto getPhiWinnersDtoById(Long id);

	
    /** 
	* @Title: updatePhiWinners 
	* @Description:  更新PhiWinners信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiWinners(Long id, PhiWinnersListDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiWinnersPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiWinnersDto>    
	*/ 
	DataPage<PhiWinnersListDto> getAllPhiWinnersPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiWinnersDto 
	* @Description: 获取所有的PhiWinners
	* @param      
	* @return  List<PhiWinnersDto>    
	* @throws 
	*/
	List<PhiWinnersListDto> getAllPhiWinnersDto();
	
	void saveAllWinnersList(List<PhiWinnersListDto> list);
	
	List<Map<Long,String>> gerAllMemberByProductId(Long productId);
	
	List<PhiVirtualUser> getVirtualUsersByCount(int count);
	
	void deleteAllWinnersByProductId(Long id);
	
	int getOrderCountByProductId(Long id);
	
	void updateProductWinnersStatus(Long id);
	//通过商品id找到所有随机中奖的用户
	List<PhiWinnersListDto> getWinnersByProductId(Long id);
	

	List<PhiOrderDto> gerAllRandomMemberByProductId(Long productId);
	
	List<PhiWinnersListDto> getMemberByProductIdandMemberId(Long productId,Long MemberId);
	
	List<PhiWinnersListDto> getRandomWinners(Long productId,List<Long> idsList,int count);

	/**
	 * 通过商品id找到中奖名单
	 * @param productId
	 * @return
	 */
	List<PhiWinnersListDto> getAllPhiWinnersByproductIdDto(Long productId);
	
	void updateOrderStatus(Long productId,List<PhiWinnersListDto> winnersList);
	
	/**
	 * app前台商品中奖名单列表
	 * @param queryPage
	 * @return
	 */
	DataPage<PhiWinnersListDto> getAllPhiWinnersPageforApp(QueryPage queryPage);
	

}
