package com.huatek.busi.service.phicom.plusmember;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDetailsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusRightGiftBagDetailsService {
	
	/** 
	* @Title: savePhiPlusRightGiftBagDetailsDto 
	* @Description: 保存PhiPlusRightGiftBagDetails信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPlusRightGiftBagDetailsDto(PhiPlusRightGiftBagDetailsDto entityDto) ;

	
	/** 
	* @Title: deletePhiPlusRightGiftBagDetails 
	* @Description:  删除PhiPlusRightGiftBagDetails信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusRightGiftBagDetails(Long id) ;

	
	/** 
	* @Title: getPhiPlusRightGiftBagDetailsDtoById 
	* @Description: 获取PhiPlusRightGiftBagDetails的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusRightGiftBagDetailsDto getPhiPlusRightGiftBagDetailsDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusRightGiftBagDetails 
	* @Description:  更新PhiPlusRightGiftBagDetails信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusRightGiftBagDetails(Long id, PhiPlusRightGiftBagDetailsDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusRightGiftBagDetailsPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusRightGiftBagDetailsDto>    
	*/ 
	DataPage<PhiPlusRightGiftBagDetailsDto> getAllPhiPlusRightGiftBagDetailsPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusRightGiftBagDetailsDto 
	* @Description: 获取所有的PhiPlusRightGiftBagDetails
	* @param      
	* @return  List<PhiPlusRightGiftBagDetailsDto>    
	* @throws 
	*/
	List<PhiPlusRightGiftBagDetailsDto> getAllPhiPlusRightGiftBagDetailsDto();
	
}
