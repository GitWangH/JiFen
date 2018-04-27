package com.huatek.busi.service.base;

import java.util.List;

import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableDto;
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableShowDto;
import com.huatek.busi.dto.base.BusiBaseQuantityListSubConnectionTableDto;
import com.huatek.busi.dto.base.paramEntity.BusiBaseQuantityListSubConnectionTableParamEntity;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 工程量清单和分部分项挂接
 * @author eli_cui
 *
 */
public interface BusiBaseQuantityListSubConnectionTableService {
	
	/** 
	* @Title: saveBusiBaseQuantityListSubConnectionTableDto 
	* @Description: 保存BusiBaseQuantityListSubConnectionTable信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiBaseQuantityListSubConnectionTableDto(BusiBaseQuantityListSubConnectionTableDto entityDto) ;

	
	/** 
	* @Title: deleteBusiBaseQuantityListSubConnectionTable 
	* @Description:  删除BusiBaseQuantityListSubConnectionTable信息
	* @param    List<Long> selectedIdList 所选中的要删除的id
	* @return  void    
	*/ 
	void deleteBusiBaseQuantityListSubConnectionTable(List<Long> selectedIdList) ;

	
	/** 
	* @Title: getBusiBaseQuantityListSubConnectionTableDtoById 
	* @Description: 获取BusiBaseQuantityListSubConnectionTable的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiBaseQuantityListSubConnectionTableDto getBusiBaseQuantityListSubConnectionTableDtoById(Long id);

	
    /** 
	* @Title: updateBusiBaseQuantityListSubConnectionTable 
	* @Description:  更新BusiBaseQuantityListSubConnectionTable信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiBaseQuantityListSubConnectionTable(Long id, BusiBaseQuantityListSubConnectionTableDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiBaseQuantityListSubConnectionTablePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiBaseQuantityListSubConnectionTableDto>    
	*/ 
	DataPage<BusiBaseImageListSubConnectionTableDto> getAllBusiBaseQuantityListSubConnectionTablePage(QueryPage queryPage, Long id);
	 
	/** 
	* @Title:  getAllBusiBaseQuantityListSubConnectionTableDto 
	* @Description: 获取所有的BusiBaseQuantityListSubConnectionTable
	* @param      
	* @return  List<BusiBaseQuantityListSubConnectionTableDto>    
	* @throws 
	*/
	List<BusiBaseQuantityListSubConnectionTableDto> getAllBusiBaseQuantityListSubConnectionTableDto();
	
	/**
	 * 保存分部分项与形象清单挂接。 分部分项与形象清单的关系是 一对多。
	 * @param subEngineeringId 分部分项id
	 * @param engineeringQuantityIdList 形象清单id List
	 */
	void saveBusiBaseQuantityListSubConnectionTable(BusiBaseQuantityListSubConnectionTableParamEntity entity);
	
}
