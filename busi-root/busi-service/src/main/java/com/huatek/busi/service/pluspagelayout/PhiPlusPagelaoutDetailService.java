package com.huatek.busi.service.pluspagelayout;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelaoutDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusPagelaoutDetailService {
	
	/** 
	* @Title: savePhiPlusPagelaoutDetailDto 
	* @Description: 保存PhiPlusPagelaoutDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPlusPagelaoutDetailDto(PhiPlusPagelaoutDetailDto entityDto) ;

	
	/** 
	* @Title: deletePhiPlusPagelaoutDetail 
	* @Description:  删除PhiPlusPagelaoutDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusPagelaoutDetail(Long id) ;

	
	/** 
	* @Title: getPhiPlusPagelaoutDetailDtoById 
	* @Description: 获取PhiPlusPagelaoutDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusPagelaoutDetailDto getPhiPlusPagelaoutDetailDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusPagelaoutDetail 
	* @Description:  更新PhiPlusPagelaoutDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusPagelaoutDetail(Long id, PhiPlusPagelaoutDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusPagelaoutDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusPagelaoutDetailDto>    
	*/ 
	DataPage<PhiPlusPagelaoutDetailDto> getAllPhiPlusPagelaoutDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusPagelaoutDetailDto 
	* @Description: 获取所有的PhiPlusPagelaoutDetail
	* @param      
	* @return  List<PhiPlusPagelaoutDetailDto>    
	* @throws 
	*/
	List<PhiPlusPagelaoutDetailDto> getAllPhiPlusPagelaoutDetailDto();
	
}
