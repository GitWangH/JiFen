package com.huatek.busi.service.phicom.plusmember;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusRightGiftBagService {
	
	/** 
	* @Title: savePhiPlusRightGiftBagDto 
	* @Description: 保存PhiPlusRightGiftBag信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPlusRightGiftBagDto(PhiPlusRightGiftBagDto entityDto) ;

	
	/** 
	* @Title: deletePhiPlusRightGiftBag 
	* @Description:  删除PhiPlusRightGiftBag信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusRightGiftBag(Long id) ;

	
	/** 
	* @Title: getPhiPlusRightGiftBagDtoById 
	* @Description: 获取PhiPlusRightGiftBag的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusRightGiftBagDto getPhiPlusRightGiftBagDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusRightGiftBag 
	* @Description:  更新PhiPlusRightGiftBag信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusRightGiftBag(Long id, PhiPlusRightGiftBagDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusRightGiftBagPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusRightGiftBagDto>    
	*/ 
	DataPage<PhiPlusRightGiftBagDto> getAllPhiPlusRightGiftBagPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusRightGiftBagDto 
	* @Description: 获取所有的PhiPlusRightGiftBag
	* @param      
	* @return  List<PhiPlusRightGiftBagDto>    
	* @throws 
	*/
	List<PhiPlusRightGiftBagDto> getAllPhiPlusRightGiftBagDto();
	
	
}
