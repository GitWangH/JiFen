package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerExceedDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualitySpreaderRollerExceedService {
	
	/** 
	* @Title: saveBusiQualitySpreaderRollerExceedDto 
	* @Description: 保存BusiQualitySpreaderRollerExceed信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualitySpreaderRollerExceedDto(BusiQualitySpreaderRollerExceedDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualitySpreaderRollerExceed 
	* @Description:  删除BusiQualitySpreaderRollerExceed信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualitySpreaderRollerExceed(Long id) ;

	
	/** 
	* @Title: getBusiQualitySpreaderRollerExceedDtoById 
	* @Description: 获取BusiQualitySpreaderRollerExceed的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualitySpreaderRollerExceedDto getBusiQualitySpreaderRollerExceedDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualitySpreaderRollerExceed 
	* @Description:  更新BusiQualitySpreaderRollerExceed信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualitySpreaderRollerExceed(Long id, BusiQualitySpreaderRollerExceedDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualitySpreaderRollerExceedPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualitySpreaderRollerExceedDto>    
	*/ 
	DataPage<BusiQualitySpreaderRollerExceedDto> getAllBusiQualitySpreaderRollerExceedPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualitySpreaderRollerExceedDto 
	* @Description: 获取所有的BusiQualitySpreaderRollerExceed
	* @param      
	* @return  List<BusiQualitySpreaderRollerExceedDto>    
	* @throws 
	*/
	List<BusiQualitySpreaderRollerExceedDto> getAllBusiQualitySpreaderRollerExceedDto();
	
}
