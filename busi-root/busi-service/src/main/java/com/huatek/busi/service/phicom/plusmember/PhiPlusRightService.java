package com.huatek.busi.service.phicom.plusmember;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRight;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusRightService {
	
	/** 
	* @Title: savePhiPlusRightDto 
	* @Description: 保存PhiPlusRight信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPlusRightDto(PhiPlusRightDto entityDto) ;

	
	/** 
	* @Title: deletePhiPlusRight 
	* @Description:  删除PhiPlusRight信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusRight(Long id) ;

	
	/** 
	* @Title: getPhiPlusRightDtoById 
	* @Description: 获取PhiPlusRight的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusRightDto getPhiPlusRightDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusRight 
	* @Description:  更新PhiPlusRight信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusRight(Long id, PhiPlusRightDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusRightPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusRightDto>    
	*/ 
	DataPage<PhiPlusRightDto> getAllPhiPlusRightPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusRightDto 
	* @Description: 获取所有的PhiPlusRight
	* @param      
	* @return  List<PhiPlusRightDto>    
	* @throws 
	*/
	List<PhiPlusRightDto> getAllPhiPlusRightDto();
	
   PhiPlusRight findPhiPlusRightByCondition(String condition);
	
}
