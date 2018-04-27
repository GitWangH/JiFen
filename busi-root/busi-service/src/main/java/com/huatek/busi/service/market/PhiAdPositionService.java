package com.huatek.busi.service.market;

import java.util.List;

import com.huatek.busi.dto.market.PhiAdPositionDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiAdPositionService {
	
	/** 
	* @Title: savePhiAdPositionDto 
	* @Description: 保存PhiAdPosition信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiAdPositionDto(PhiAdPositionDto entityDto) ;

	
	/** 
	* @Title: deletePhiAdPosition 
	* @Description:  删除PhiAdPosition信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiAdPosition(Long id) ;

	
	/** 
	* @Title: getPhiAdPositionDtoById 
	* @Description: 获取PhiAdPosition的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiAdPositionDto getPhiAdPositionDtoById(Long id);

	
    /** 
	* @Title: updatePhiAdPosition 
	* @Description:  更新PhiAdPosition信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiAdPosition(Long id, PhiAdPositionDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiAdPositionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiAdPositionDto>    
	*/ 
	DataPage<PhiAdPositionDto> getAllPhiAdPositionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiAdPositionDto 
	* @Description: 获取所有的PhiAdPosition
	* @param      
	* @return  List<PhiAdPositionDto>    
	* @throws 
	*/
	List<PhiAdPositionDto> getAllPhiAdPositionDto();
	/*批量添加*/
	void batchAdd(List<PhiAdPositionDto> phiAdPositionDtoList);

	/*根据adCode获取广告位和图片信息 */
	List<PhiAdPositionDto> getAdPositionAndPhoInfoByAdCode(String adCode);
}
