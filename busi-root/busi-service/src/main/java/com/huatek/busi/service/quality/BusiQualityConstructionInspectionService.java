package com.huatek.busi.service.quality;

import java.util.List;
import java.util.Map;

import com.huatek.busi.dto.quality.BusiQualityConstructionInspectionDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiQualityConstructionInspectionService {
	
	/** 
	* @Title: saveBusiQualityConstructionInspectionDto 
	* @Description: 保存BusiQualityConstructionInspection信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiQualityConstructionInspectionDto(BusiQualityConstructionInspectionDto entityDto) ;

	
	/** 
	* @Title: deleteBusiQualityConstructionInspection 
	* @Description:  删除BusiQualityConstructionInspection信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiQualityConstructionInspection(Long id) ;

	
	/** 
	* @Title: getBusiQualityConstructionInspectionDtoById 
	* @Description: 获取BusiQualityConstructionInspection的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiQualityConstructionInspectionDto getBusiQualityConstructionInspectionDtoById(Long id);

	
    /** 
	* @Title: updateBusiQualityConstructionInspection 
	* @Description:  更新BusiQualityConstructionInspection信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiQualityConstructionInspection(Long id, BusiQualityConstructionInspectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiQualityConstructionInspectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiQualityConstructionInspectionDto>    
	*/ 
	DataPage<BusiQualityConstructionInspectionDto> getAllBusiQualityConstructionInspectionPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiQualityConstructionInspectionDto 
	* @Description: 获取所有的BusiQualityConstructionInspection
	* @param      
	* @return  List<BusiQualityConstructionInspectionDto>    
	* @throws 
	*/
	List<BusiQualityConstructionInspectionDto> getAllBusiQualityConstructionInspectionDto();


	/**
	 * 流程发起申请
	 * @param id
	 */
	void reportQualityConstructionInspection(Long id);
	
	/**
	 * 施工报检审批
	 * @param taskId
	 * @param busiId
	 * @param map
	 */
	void constructionInspectionApprove(String taskId,Long busiId,Map<String,Object> map);

	/**
     * 通过整改编号查询施工报检信息
     * @param inspectionCode
     * @return
     */
	List<BusiQualityConstructionInspectionDto> getConInspectionByInsCode(String inspectionCode);
	
	/**
	 * 整改审批成功后回填状态
	 * @param inspectionCode 
	 * @param result
	 */
	void afterFlowEndChangeConstrctionInspectionStatus(String inspectionCode, int result);
	
	/**
	 * 根据分部分项id判断是否有数据存在
	 * @param id 分部分项id
	 * @return Boolean 如果有数据返回true,没有数据返回false;
	 */
	Boolean isOrNotTenderBranchId(Long tid);
}
