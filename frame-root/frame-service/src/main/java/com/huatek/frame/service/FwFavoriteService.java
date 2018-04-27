package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dto.FwFavoriteDto;

public interface FwFavoriteService {
	
	/** 
	* @Title: saveFwFavoriteDto 
	* @Description: 保存FwFavorite信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveFwFavoriteDto(FwFavoriteDto entityDto) ;

	
	/** 
	* @Title: deleteFwFavorite 
	* @Description:  删除FwFavorite信息
	* @param    id
	* @return  void    
	*/ 
	void deleteFwFavorite(Long id) ;

	
	/** 
	* @Title: getFwFavoriteDtoById 
	* @Description: 获取FwFavorite的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	FwFavoriteDto getFwFavoriteDtoById(Long id);

	
    /** 
	* @Title: updateFwFavorite 
	* @Description:  更新FwFavorite信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateFwFavorite(Long id, FwFavoriteDto entityDto) ;

	 
	/** 
	* @Title:  getAllFwFavoritePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<FwFavoriteDto>    
	*/ 
	DataPage<FwFavoriteDto> getAllFwFavoritePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllFwFavoriteDto 
	* @Description: 获取所有的FwFavorite
	* @param      
	* @return  List<FwFavoriteDto>    
	* @throws 
	*/
	List<FwFavoriteDto> getAllFwFavoriteDto();

	/**
	 * 
	* @Title: getUserFavourite 
	* @Description: 获取用户常用功能
	* @createDate: 2017年11月10日 下午3:37:58
	* @param   
	* @return  List<FwFavoriteDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwFavoriteDto> getUserFavourite(Long id);

	/**
	 * 
	* @Title: saveFavouriteSource 
	* @Description: 保存用户添加的已有功能 
	* @createDate: 2017年11月11日 下午2:34:56
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void saveFavouriteSource(Long acctId, String[] dataArr);
	
}
