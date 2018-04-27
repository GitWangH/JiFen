package com.huatek.busi.service.project;

import java.util.List;

import com.huatek.busi.dto.project.BusiRemoteMonitorDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiRemoteMonitorService {
	
	/** 
	* @Title: saveBusiRemoteMonitorDto 
	* @Description: 保存BusiRemoteMonitor信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiRemoteMonitorDto(BusiRemoteMonitorDto entityDto) ;

	
	/** 
	* @Title: deleteBusiRemoteMonitor 
	* @Description:  删除BusiRemoteMonitor信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiRemoteMonitor(Long id) ;

	
	/** 
	* @Title: getBusiRemoteMonitorDtoById 
	* @Description: 获取BusiRemoteMonitor的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiRemoteMonitorDto getBusiRemoteMonitorDtoById(Long id);

	
    /** 
	* @Title: updateBusiRemoteMonitor 
	* @Description:  更新BusiRemoteMonitor信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiRemoteMonitor(Long id, BusiRemoteMonitorDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiRemoteMonitorPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiRemoteMonitorDto>    
	*/ 
	DataPage<BusiRemoteMonitorDto> getAllBusiRemoteMonitorPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiRemoteMonitorDto 
	* @Description: 获取所有的BusiRemoteMonitor
	* @param      
	* @return  List<BusiRemoteMonitorDto>    
	* @throws 
	*/
	List<BusiRemoteMonitorDto> getAllBusiRemoteMonitorDto();
	
	/**
	 * 
	* @Title: getAllUserRemoteMonitorByMotitorType 
	* @Description: 根据监控类型获取机构下监控数据 
	* @createDate: 2017年11月16日 下午4:36:38
	* @param   
	* @return  List<BusiRemoteMonitorDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<BusiRemoteMonitorDto> getAllUserRemoteMonitorByMotitorType(
			String type, Long orgId, Long currProId);
	
}
