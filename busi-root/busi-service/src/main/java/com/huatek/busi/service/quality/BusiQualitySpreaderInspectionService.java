package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualitySpreaderInspectionDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualitySpreaderInspectionService {
	
	/** 
	* @Title: saveBusiQualitySpreaderInspectionDto 
	* @Description: 保存BusiQualitySpreaderInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySpreaderInspectionDto(BusiQualitySpreaderInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySpreaderInspection 
	* @Description:  删除BusiQualitySpreaderInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySpreaderInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualitySpreaderInspectionDtoById 
	* @Description: 获取BusiQualitySpreaderInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySpreaderInspectionDto getBusiQualitySpreaderInspectionDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualitySpreaderInspection 
	* @Description:  更新BusiQualitySpreaderInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySpreaderInspection(Long id, BusiQualitySpreaderInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySpreaderInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySpreaderInspectionDto>    
	*/ 
	DataPage<BusiQualitySpreaderInspectionDto> getAllBusiQualitySpreaderInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySpreaderInspectionDto 
	* @Description: 获取所有的BusiQualitySpreaderInspection
	* @param      
	* @return  List<BusiQualitySpreaderInspectionDto>    
	* @throws 
	*/
	List<BusiQualitySpreaderInspectionDto> getAllBusiQualitySpreaderInspectionDto();
	
}
