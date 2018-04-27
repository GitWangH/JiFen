package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDetailBranchDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiMeasureMiddleMeasureDetailBranchDetailService
 * @Description: 中间计量分部分项Service接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:46:45
 * @version: 1.0
 */
public interface BusiMeasureMiddleMeasureDetailBranchDetailService {
	
	/** 
	* @Title: saveBusiMeasureMiddleMeasureDetailBranchDetailDto 
	* @Description: 保存BusiMeasureMiddleMeasureDetailBranchDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureMiddleMeasureDetailBranchDetailDto(BusiMeasureMiddleMeasureDetailBranchDetailDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureMiddleMeasureDetailBranchDetail 
	* @Description:  删除BusiMeasureMiddleMeasureDetailBranchDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureMiddleMeasureDetailBranchDetail(Long id) ;

	
	/** 
	* @Title: getBusiMeasureMiddleMeasureDetailBranchDetailDtoById 
	* @Description: 获取BusiMeasureMiddleMeasureDetailBranchDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureMiddleMeasureDetailBranchDetailDto getBusiMeasureMiddleMeasureDetailBranchDetailDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureMiddleMeasureDetailBranchDetail 
	* @Description:  更新BusiMeasureMiddleMeasureDetailBranchDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureMiddleMeasureDetailBranchDetail(Long id, BusiMeasureMiddleMeasureDetailBranchDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureMiddleMeasureDetailBranchDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureMiddleMeasureDetailBranchDetailDto>    
	*/ 
	DataPage<BusiMeasureMiddleMeasureDetailBranchDetailDto> getAllBusiMeasureMiddleMeasureDetailBranchDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureMiddleMeasureDetailBranchDetailDto 
	* @Description: 获取所有的BusiMeasureMiddleMeasureDetailBranchDetail
	* @param      
	* @return  List<BusiMeasureMiddleMeasureDetailBranchDetailDto>    
	* @throws 
	*/
	List<BusiMeasureMiddleMeasureDetailBranchDetailDto> getAllBusiMeasureMiddleMeasureDetailBranchDetailDto();
	
}
