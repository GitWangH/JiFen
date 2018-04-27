package com.huatek.busi.service.phicom.product;

import java.util.List;
import java.util.Map;

import com.huatek.busi.dto.phicom.product.PhiProductDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiProductService {
	
	void submitProductCertification(Long id);
	
	
	void updateProductStauts(Long id,String status);
	
	/** 
	* @Title: savePhiProductDto 
	* @Description: 保存PhiProduct信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiProductDto(PhiProductDto entityDto) ;

	
	/** 
	* @Title: deletePhiProduct 
	* @Description:  删除PhiProduct信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiProduct(Long id) ;

	
	/** 
	* @Title: getPhiProductDtoById 
	* @Description: 获取PhiProduct的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiProductDto getPhiProductDtoById(Long id);

	
    /** 
	* @Title: updatePhiProduct 
	* @Description:  更新PhiProduct信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiProduct(Long id, PhiProductDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiProductPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiProductDto>    
	*/ 
	DataPage<PhiProductDto> getAllPhiProductPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiProductDto 
	* @Description: 获取所有的PhiProduct
	* @param      
	* @return  List<PhiProductDto>    
	* @throws 
	*/
	List<PhiProductDto> getAllPhiProductDto();
	
	
	void updatePhiProductStatus(String val,Long... ids);
	
	/**
	 * 获取所有的商品类型
	 * @return
	 */
	List<Map<Long,String>> getAllTypeName();
	
	/**
	 * 获取所有的优惠券id类型
	 * @return
	 */
	List<Long> getAllCouponsId();
	//获取所有第三方劵的id
	
	List<String> getAllThirdPartyId();
	
	String  getProductValidDateById(Long id);
	//通过第三方id找到优惠券的有效期
	String  getThirdPartyValidDateById(String  id);
	
	void productAutoUptoShop();

	/**
	 * 查看商品详情
	 * @param id
	 * @param memberId
	 * @return
	 */
	PhiProductDto getPhiProductDetialDtoById(Long id, Long memberId);
	
	PhiProductDto getProductDetailForApp(Long id,Long memberId);

	List<PhiProductDto> getRecommendProductForApp();
	
	
	
}
