package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dto.FwStationDto;
import com.huatek.frame.service.dto.FwAccountDto;

public interface FwStationService {
	
	/** 
	* @Title: saveFwStationDto 
	* @Description: 保存FwStation信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveFwStationDto(FwStationDto entityDto) ;

	
	/** 
	* @Title: deleteFwStation 
	* @Description:  删除FwStation信息
	* @param    id
	* @return  void    
	*/ 
	void deleteFwStation(Long id) ;

	
	/** 
	* @Title: getFwStationDtoById 
	* @Description: 获取FwStation的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	FwStationDto getFwStationDtoById(Long id);

	
    /** 
	* @Title: updateFwStation 
	* @Description:  更新FwStation信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateFwStation(Long id, FwStationDto entityDto) ;

	 
	/** 
	* @Title:  getAllFwStationPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<FwStationDto>    
	*/ 
	DataPage<FwStationDto> getAllFwStationPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllFwStationDto 
	* @Description: 获取所有的FwStation
	* @param      
	* @return  List<FwStationDto>    
	* @throws 
	*/
	List<FwStationDto> getAllFwStationDto();

	/**
	 * 
	* @Title: getFwStationDtoByAcctId 
	* @Description: 获取用户任职岗位 
	* @createDate: 2017年10月25日 下午4:04:55
	* @param   
	* @return  List<FwStationDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwStationDto> getFwStationDtoByAcctId(Long id, Long tenantId);

	/**
	 * 
	* @Title: getFwStationDtoByIds 
	* @Description: 根据ids获取岗位 
	* @createDate: 2017年10月30日 下午7:54:41
	* @param   
	* @return  List<FwStationDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwStationDto> getFwStationDtoByIds(List<Long> ids);

	/**
	 * 
	* @Title: deleteFwStation 
	* @Description: 批量删除岗位 
	* @createDate: 2017年10月30日 下午7:59:50
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelete(List<FwStationDto> fwStationDtos);

	/**
	 * 
	* @Title: isStationByNextAcct 
	* @Description: 是否用户关联 
	* @createDate: 2017年10月30日 下午8:06:31
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isStationByNextAcct(Long id, Long teanatnId);

	/**
	 * 
	* @Title: getFwAccountByStation 
	* @Description: 获取岗位下所有人员 
	* @createDate: 2017年11月1日 下午3:19:33
	* @param   
	* @return  List<FwAccountDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwAccountDto> getFwAccountByStation(Long stationId, Long tenantId);

	/**
	 * 
	* @Title: isExistByParent 
	* @Description: 判断部门编码是否存在 
	* @createDate: 2017年11月6日 上午11:10:12
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isExistCodeByParent(Long id, String code, Long tenantId);

	/**
	 * 
	* @Title: isExistNameByParent 
	* @Description: 岗位名称是否在同一父级存在 
	* @createDate: 2017年11月6日 上午11:16:26
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isExistNameByParent(Long id, String departmentName,
			Long orgId, Long departmentId, Long tenantId);
	
}
