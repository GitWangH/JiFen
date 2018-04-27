package com.huatek.busi.service.project;

import java.util.List;

import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDetailDto;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDto;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionShowDto;
import com.huatek.busi.dto.project.port.BusiProjectManagementOfBidSectionPortDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 工程标段管理
 * @author eli_cui
 *
 */
public interface BusiProjectManagementOfBidSectionService {

	
    /** 
	* @Title: updateBusiProjectManagementOfBidSection 
	* @Description:  更新BusiProjectManagementOfBidSection信息 
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSectionDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiProjectManagementOfBidSectionPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiProjectManagementOfBidSectionDto>    
	*/ 
	DataPage<BusiProjectManagementOfBidSectionShowDto> getAllBusiProjectManagementOfBidSectionPage(QueryPage queryPage);
	 
	
	
	/**
	 * 根据orgId 获取工程标段信息 此接口给 【施工合同管理】模块使用
	 * @param orgId
	 * @return
	 */
	BusiProjectManagementOfBidSectionPortDto getInfoForTheContractByOrgId(Long orgId);
	
	/**
	 * 根据 获取工程标段信息 获取明细信息
	 * @param parentId 获取工程标段信息主键id
	 * @return
	 */
	DataPage<BusiProjectManagementOfBidSectionDetailDto> getDetailByParentId(Long parentId);
	
	/**
	 * 根据idList 判断用户所选数据是否可以删除
	 * @param id
	 * @return 返回 "1" 表示可以删除 else 返回不能删除的基本信息。
	 */
	String getBusiProjectManagementOfBidSectionDetailListById(List<Long> idList);
	
}
