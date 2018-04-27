package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.FwDepartmentDto;

public interface FwDepartmentService {

    /**
     * @Title: saveFwDepartmentDto
     * @Description: 保存FwDepartment信息
     * @param entityDto
     * @return void
     */
	FwDepartmentDto saveFwDepartmentDto(FwDepartmentDto entityDto);

    /**
     * @Title: deleteFwDepartment
     * @Description: 删除FwDepartment信息
     * @param id
     * @return void
     */
    void deleteFwDepartment(Long id);

    /**
     * @Title: getFwDepartmentDtoById
     * @Description: 获取FwDepartment的Dto
     * @param id
     * @return MdmLineBaseInfoDto
     */
    FwDepartmentDto getFwDepartmentDtoById(Long id);
    List<FwDepartmentDto> getSubAllDepartment(Long id);
    

    /**
     * @Title: updateFwDepartment
     * @Description: 更新FwDepartment信息
     * @param id
     * @param entityDto
     * @return void
     */
    void updateFwDepartment(Long id, FwDepartmentDto entityDto);

    /**
     * @Title: getAllFwDepartmentPage
     * @Description: 分页查询
     * @param queryPage
     * @return DataPage<FwDepartmentDto>
     */
    DataPage<FwDepartmentDto> getAllFwDepartmentPage(QueryPage queryPage);

    /**
     * @Title: getAllFwDepartmentDto
     * @Description: 获取所有的FwDepartment
     * @param
     * @return List<FwDepartmentDto>
     * @throws
     */
    List<FwDepartmentDto> getAllFwDepartmentDto();

    /**
     * 
     * @Description: TODO
     * @param @param condition 名称/编码
     * @param @param type 1 2
     * @param @return
     * @return List<FwDepartment>
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月23日
     */
    List<FwDepartmentDto> getFwDepartmentByNameAndCode(String condition,
	    String type);

    /**
     * 根据组织机构下的部门
     * 
     * @Description: TODO
     * @param @param orgId
     * @param @return
     * @return List<FwDepartmentDto>
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月23日
     */
    List<FwDepartmentDto> getFwDepartmentByOrgId(Long orgId);

    /**
     * 判断是否有父部门存在
     * 
     * @Description: TODO
     * @param @param id
     * @param @return
     * @return Boolean
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月24日
     */
    Boolean isDepartmentParent(Long id);

    /**
     * 判断名称和编码是否存在
     * 
     * @Description: TODO
     * @param @param condition
     * @param @param type 0 名称 1 编码
     * @param @return
     * @return Boolean
     * @throws
     * @author martin_ju
     * @e_mail martin_ju@huatek.com
     * @date 2016年6月25日
     */
    Boolean isDepartmentByNameAndCode(String condition, String type, Long id);
    
    /**
     * 根据名称获取部门(当前所在机构以及下部门)
    * @Title: getParamDtoByName 
    * @Description: TODO 
    * @createDate: 2017年10月27日 下午3:01:47
    * @param   
    * @return  List<ParamDto> 
    * @author cloud_liu   
    * @throws
     */
	List<ParamDto> getParamDtoByName(String name, Long tenantId, Long orgId);
	
	/**
	 * 
	* @Title: getFwDepartmentByNameAndCodeNew 
	* @Description: 根据部门名称或部门编码获取部门信息 
	* @createDate: 2017年10月30日 下午5:26:05
	* @param   
	* @return  List<FwDepartmentDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwDepartmentDto> getFwDepartmentByNameAndCodeNew(String name,
			String type, Long tenantId, Long orgId);
	/**
	 * 
	* @Title: isDepartmentByNextStation 
	* @Description: 部门是否与岗位关联 
	* @createDate: 2017年10月30日 下午6:33:55
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isDepartmentByNextStation(Long id);
	
	/**
	 * 
	* @Title: getFwDepartmentsByIds 
	* @Description: 根据ids获取部门 
	* @createDate: 2017年10月30日 下午6:48:37
	* @param   
	* @return  List<FwDepartmentDto> 
	* @author cloud_liu   
	* @throws
	 */
	List<FwDepartmentDto> getFwDepartmentsByIds(List<Long> ids);
	
	/**
	 * 
	* @Title: batchDelate 
	* @Description: 批量删除部门 
	* @createDate: 2017年10月30日 下午7:04:51
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelate(List<FwDepartmentDto> departmentList, Integer i);
	
	/**
	 * 
	* @Title: isDepartmentByAcct 
	* @Description: 部门是否与人员关联 
	* @createDate: 2017年10月30日 下午8:50:14
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isDepartmentByAcct(Long id);
	
	/**
	 * 
	* @Title: isExistByParentId 
	* @Description: 判断部门名称在同一父级内是否存在 
	* @createDate: 2017年11月6日 上午10:21:04
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isExistByParentId(Long id, String deptName, Long parentId, Long orgId, Long tenantId);
	
	/**
	 * 
	* @Title: isDepartmentByNameAndCode 
	* @Description: 部门编码是否存在 
	* @createDate: 2017年11月6日 上午10:40:49
	* @param   
	* @return  boolean 
	* @author cloud_liu   
	* @throws
	 */
	boolean isDepartmentByNameAndCode(String deptCode, Long id,
			Long tenantId);
	

}
