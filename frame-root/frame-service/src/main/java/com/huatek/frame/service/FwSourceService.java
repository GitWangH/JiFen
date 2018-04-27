package com.huatek.frame.service;

import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.SourceDto;

/**
 * @Description: 资源(菜单menu)服务接口定义
 * @author caojun1@hisense.com
 * @date 2016年1月13日 下午1:18:03
 * @version V1.0
 */
public interface FwSourceService {

	/**
	 * 获取所有菜单数据（树状结构）
	 * 
	 * @param isMenu 0-全部、1 仅菜单
	 * @return
	 */
	//List<SourceDto> getAllSource(int isMenu, Long userId);
	
	//List<SourceDto> getAllSource(Long userId, Long sysid);
	
	/**
	 * 获取所有菜单数据
	 * 
	 * @return
	 */
	public List<SourceDto> getAllMenu();
	
	List<SourceDto> getAllMenuByUser();
	
	/**
	 * 根据角色获取菜单信息
	 * @param roleCode
	 * @return
	 */
	public List<SourceDto> getAllSourceByRoleCode(List<String> roleCode);
	
	/**
	 * 保存菜单信息.
	 * 
	 * @param sourceDto
	 *            菜单提交的数据对象.
	 */
	void saveMenu(SourceDto sourceDto);

	/***
	 * 根据ID删除账号数据.
	 * 
	 * @param id
	 *            账号ID.
	 */
	void deleteMenu(Long id);

	/***
	 * 根据菜单ID查询菜单对象.
	 * 
	 * @param id
	 *            菜单ID.
	 * @return 返回菜单对象.
	 */
	SourceDto getMenuById(Long id);

	/***
	 * 更新菜单信息. 更新菜单信息必须输入ID值.
	 * 
	 * @param id
	 *            菜单ID.
	 * @param uerDto
	 *            菜单DTO对象.
	 */
	void updateMenu(Long id, SourceDto sourceDto);

	/***
	 * 获取查询条件的当前页面.
	 * 
	 * @param queryPage
	 *            查询页面.
	 * @return 返回当前页面的菜单数据.
	 */
	DataPage<SourceDto> getAllMenu(QueryPage queryPage);
	
	/**
	 * 查询所有资源
	 * @return
	 */
	//List<SourceDto> getAllSource();
	
	/**
	 * 根据Id获取对应资源
	 * @param fwSourceId
	 * @return
	 */
	SourceDto getFwSourceById(Long fwSourceId);

	
	
	SourceDto findMenuByUrl(String url);
	
	/**
	 * 
	* @Title: getAllSourceByRoleCode 
	* @Description: 查询角色资源 
	* @createDate: 2017年11月4日 上午10:19:21
	* @param   
	* @return  List<SourceDto> 
	* @author cloud_liu   
	* @throws
	 */
	public List<SourceDto> getAllSourceByRoleCode(List<String> roleCodes,
			Long tenantId);
	
	/**
	 * 
	* @Title: getAllSource 
	* @Description: 获取所有资源 
	* @createDate: 2017年11月7日 上午10:32:19
	* @param   
	* @return  List<SourceDto> 
	* @author cloud_liu   
	* @throws
	 */
	public List<SourceDto> getAllSource();

	/**
	 * 
	* @Title: getAllUserSource 
	* @Description: 所有用户资源
	* @createDate: 2017年11月7日 上午10:36:34
	* @param   
	* @return  List<SourceDto> 
	* @author cloud_liu   
	* @throws
	 */
	public List<SourceDto> getAllUserSource();
	
	/**
	 * 
	* @Title: getAll 
	* @Description: 获取所有 
	* @createDate: 2017年11月7日 下午2:44:48
	* @param   
	* @return  List<SourceDto> 
	* @author cloud_liu   
	* @throws
	 */
	public List<SourceDto> getAll();
}
