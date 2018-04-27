package com.huatek.busi.service.base;

import java.util.List;

import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableDto;
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableShowDto;
import com.huatek.busi.dto.base.paramEntity.BusiBaseImageListSubConnectionTableParamEntity;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 形象清单和分部分项挂接
 * @author eli_cui
 *
 */
public interface BusiBaseImageListSubConnectionTableService {
	
	/** 
	* @Title: saveBusiBaseImageListSubConnectionTableDto 
	* @Description: 保存BusiBaseImageListSubConnectionTable信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiBaseImageListSubConnectionTableDto(BusiBaseImageListSubConnectionTableDto entityDto) ;

	
	/** 
	* @Title: deleteBusiBaseImageListSubConnectionTable 
	* @Description:  删除用户所选中的数据
	* @param    selectedIdList 用户所选择要删除的数据id
	* @return  void    
	*/ 
	void deleteBusiBaseImageListSubConnectionTable(List<Long> selectedIdList) ;

	
	/** 
	* @Title: getBusiBaseImageListSubConnectionTableDtoById 
	* @Description: 获取BusiBaseImageListSubConnectionTable的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiBaseImageListSubConnectionTableDto getBusiBaseImageListSubConnectionTableDtoById(Long id);

	
    /** 
	* @Title: updateBusiBaseImageListSubConnectionTable 
	* @Description:  更新BusiBaseImageListSubConnectionTable信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiBaseImageListSubConnectionTable(Long id, BusiBaseImageListSubConnectionTableDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiBaseImageListSubConnectionTablePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiBaseImageListSubConnectionTableDto>    
	*/ 
	DataPage<BusiBaseImageListSubConnectionTableShowDto> getAllBusiBaseImageListSubConnectionTablePage(QueryPage queryPage, Long imageId);
	 
	/** 
	* @Title:  getAllBusiBaseImageListSubConnectionTableDto 
	* @Description: 获取所有的BusiBaseImageListSubConnectionTable
	* @param      
	* @return  List<BusiBaseImageListSubConnectionTableDto>    
	* @throws 
	*/
	List<BusiBaseImageListSubConnectionTableDto> getAllBusiBaseImageListSubConnectionTableDto();
	
	
	/**
	 * 保存形象清单与分部分项数据
	 * @param entity
	 */
	void saveBusiBaseImageListSubConnectionTable(BusiBaseImageListSubConnectionTableParamEntity entity);
}
