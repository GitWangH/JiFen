package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualitySeclusionEngineerFileDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualitySeclusionEngineerFileService {
	
	/** 
	* @Title: saveBusiQualitySeclusionEngineerFileDto 
	* @Description: 保存BusiQualitySeclusionEngineerFile信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySeclusionEngineerFileDto(BusiQualitySeclusionEngineerFileDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySeclusionEngineerFile 
	* @Description:  删除BusiQualitySeclusionEngineerFile信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySeclusionEngineerFile(Long id) ;

	
	/** 
	* @Title: getBusiQualitySeclusionEngineerFileDtoById 
	* @Description: 获取BusiQualitySeclusionEngineerFile的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySeclusionEngineerFileDto getBusiQualitySeclusionEngineerFileDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualitySeclusionEngineerFile 
	* @Description:  更新BusiQualitySeclusionEngineerFile信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySeclusionEngineerFile(Long id, BusiQualitySeclusionEngineerFileDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySeclusionEngineerFilePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySeclusionEngineerFileDto>    
	*/ 
	DataPage<BusiQualitySeclusionEngineerFileDto> getAllBusiQualitySeclusionEngineerFilePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySeclusionEngineerFileDto 
	* @Description: 获取所有的BusiQualitySeclusionEngineerFile
	* @param      
	* @return  List<BusiQualitySeclusionEngineerFileDto>    
	* @throws 
	*/
	List<BusiQualitySeclusionEngineerFileDto> getAllBusiQualitySeclusionEngineerFileDto();

	 /** 
     * @Title:  getSeclEngListByTendersId
     * @Description: 根据分部分项编号id获取隐蔽工程附件
     * @param   tId 分部分项编号id
     */
	List<BusiQualitySeclusionEngineerFileDto> getSeclEngListByTendersId(Long tId);
	
}
