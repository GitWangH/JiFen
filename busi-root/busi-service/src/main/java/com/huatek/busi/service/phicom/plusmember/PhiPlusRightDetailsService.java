package com.huatek.busi.service.phicom.plusmember;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDetailsDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusRightDetailsService {
	
	/** 
	* @Title: savePhiPlusRightDetailsDto 
	* @Description: 保存PhiPlusRightDetails信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPlusRightDetailsDto(PhiPlusRightDetailsDto entityDto) ;

	
	/** 
	* @Title: deletePhiPlusRightDetails 
	* @Description:  删除PhiPlusRightDetails信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusRightDetails(Long id) ;

	
	/** 
	* @Title: getPhiPlusRightDetailsDtoById 
	* @Description: 获取PhiPlusRightDetails的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusRightDetailsDto getPhiPlusRightDetailsDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusRightDetails 
	* @Description:  更新PhiPlusRightDetails信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusRightDetails(Long id, PhiPlusRightDetailsDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusRightDetailsPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusRightDetailsDto>    
	*/ 
	DataPage<PhiPlusRightDetailsDto> getAllPhiPlusRightDetailsPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusRightDetailsDto 
	* @Description: 获取所有的PhiPlusRightDetails
	* @param      
	* @return  List<PhiPlusRightDetailsDto>    
	* @throws 
	*/
	List<PhiPlusRightDetailsDto> getAllPhiPlusRightDetailsDto();
	
}
