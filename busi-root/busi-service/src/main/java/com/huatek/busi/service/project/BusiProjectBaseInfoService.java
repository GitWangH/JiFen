package com.huatek.busi.service.project;

import java.util.List;

import com.huatek.busi.dto.project.BusiProjectBaseInfoDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 项目基本信息
 * @author eli_cui
 *
 */
public interface BusiProjectBaseInfoService {
	
	/** 
	* @Title: saveBusiProjectBaseInfoDto 
	* @Description: 保存BusiProjectBaseInfo信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiProjectBaseInfoDto(BusiProjectBaseInfoDto entityDto) ;

	
	/** 
	* @Title: deleteBusiProjectBaseInfo 
	* @Description:  删除BusiProjectBaseInfo信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiProjectBaseInfo(Long id) ;

	
	/** 
	* @Title: getBusiProjectBaseInfoDtoById 
	* @Description: 获取BusiProjectBaseInfo的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiProjectBaseInfoDto getBusiProjectBaseInfoDtoById(Long id);

	
    /** 
	* @Title: updateBusiProjectBaseInfo 
	* @Description:  更新BusiProjectBaseInfo信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiProjectBaseInfo(Long id, BusiProjectBaseInfoDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiProjectBaseInfoPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiProjectBaseInfoDto>    
	*/ 
	DataPage<BusiProjectBaseInfoDto> getAllBusiProjectBaseInfoPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiProjectBaseInfoDto 
	* @Description: 获取所有的BusiProjectBaseInfo
	* @param      
	* @return  List<BusiProjectBaseInfoDto>    
	* @throws 
	*/
	List<BusiProjectBaseInfoDto> getAllBusiProjectBaseInfoDto();
	
}
