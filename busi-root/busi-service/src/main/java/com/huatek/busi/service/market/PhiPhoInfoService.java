package com.huatek.busi.service.market;

import java.util.List;

import com.huatek.busi.dto.market.PhiAdPositionPhoInfoCommonDto;
import com.huatek.busi.dto.market.PhiAdPositionPhoInfoDto;
import com.huatek.busi.dto.market.PhiPhoInfoDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPhoInfoService {
	
	/** 
	* @Title: savePhiPhoInfoDto 
	* @Description: 保存PhiPhoInfo信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPhoInfoDto(PhiPhoInfoDto entityDto) ;

	
	/** 
	* @Title: deletePhiPhoInfo 
	* @Description:  删除PhiPhoInfo信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPhoInfo(Long id) ;

	
	/** 
	* @Title: getPhiPhoInfoDtoById 
	* @Description: 获取PhiPhoInfo的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPhoInfoDto getPhiPhoInfoDtoById(Long id);

	
    /** 
	* @Title: updatePhiPhoInfo 
	* @Description:  更新PhiPhoInfo信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPhoInfo(Long id, PhiPhoInfoDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPhoInfoPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPhoInfoDto>    
	*/ 
	DataPage<PhiPhoInfoDto> getAllPhiPhoInfoPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPhoInfoDto 
	* @Description: 获取所有的PhiPhoInfo
	* @param      
	* @return  List<PhiPhoInfoDto>    
	* @throws 
	*/
	List<PhiPhoInfoDto> getAllPhiPhoInfoDto();
	
	/*批量添加*/
	void batchAdd(List<PhiPhoInfoDto> phiPhoInfoDtoList);
	
	/*批量删除*/
	void batchDelete(List<PhiPhoInfoDto> phiPhoInfoDtoList);

	/*根据adCode获取广告位和图片信息 */
	PhiAdPositionPhoInfoDto getAdPositionAndPhoInfoByAdCode(String adCode);

	/*根据adCode获取图片信息 */
	List<PhiPhoInfoDto> getPhiPhoInfoByAdCode(String string);

	/*修改信息*/
	void batchUpdate(List<PhiPhoInfoDto> phiPhoInfoDtoList);
	
	
    void saveOrUpdatePhiPhoInfo(PhiPhoInfoDto entityDto);
    
    /** 
	* @Title:  getAdInfobyAdCode 
	* @Description: 根据adCode获取广告位和图片信息
	* @param   adCode   
	* @return  List<PhiAdPositionPhoInfoCommonDto>    
	* @throws 
	*/
	List<PhiAdPositionPhoInfoCommonDto> getAdInfobyAdCode(String adCode);
}
