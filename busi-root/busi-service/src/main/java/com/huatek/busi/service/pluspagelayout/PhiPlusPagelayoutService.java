package com.huatek.busi.service.pluspagelayout;

import java.util.List;







import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelaoutDetailDto;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelayoutDto;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelayoutshowDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusPagelayoutService {
	
	/** 
	* @Title: savePhiPlusPagelayoutDto 
	* @Description: 保存PhiPlusPagelayout信息
	* @param   entityDto
	* @return  void    
	*/
	void savePhiPlusPagelayoutDto(PhiPlusPagelayoutDto entityDto);
	
	
	/** 
	* @Title: deletePhiPlusPagelayout 
	* @Description:  删除PhiPlusPagelayout信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusPagelayout(Long id) ;

	
	/** 
	* @Title: getPhiPlusPagelayoutDtoById 
	* @Description: 获取PhiPlusPagelayout的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	List<PhiPlusPagelaoutDetailDto> getPhiPlusPagelayoutDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusPagelayout 
	* @Description:  更新PhiPlusPagelayout信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusPagelayout(Long id, PhiPlusPagelayoutDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusPagelayoutPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusPagelayoutDto>    
	*/ 
	DataPage<PhiPlusPagelayoutDto> getAllPhiPlusPagelayoutPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusPagelayoutDto 
	* @Description: 获取所有的PhiPlusPagelayout
	* @param      
	* @return  List<PhiPlusPagelayoutDto>    
	* @throws 
	*/
	List<PhiPlusPagelayoutDto> getAllPhiPlusPagelayoutDto();

	/**根据parentid回去配置个数*/
	List<PhiPlusPagelayoutDto> getCountByParentId(Long id);
	
	void reset(Long id);
	
	
	List<PhiPlusPagelayoutshowDto> getAllPhiPlusPagelayoutshowDto();
	
	PhiPlusPagelayoutshowDto getAllPhiplusRightSummary(String code ,String clientType);
	
}
