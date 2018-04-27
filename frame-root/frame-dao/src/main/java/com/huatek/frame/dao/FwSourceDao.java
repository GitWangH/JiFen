package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.type.Type;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwSource;

/**
 * @Description: 资源(菜单menu)dao接口定义
 * @author caojun1@hisense.com
 * @date 2016年1月13日 下午1:19:02
 * @version V1.0
 */
public interface FwSourceDao extends CommonDao<Long, FwSource>{

	public FwSource findById(Long id);

	public void persistent(FwSource fwMenu);

	public void deleteMenu(FwSource fwMenu);

	public List<FwSource> findAllSource();
	
	public List<FwSource> findAllMenu();

	public DataPage<FwSource> getAllMenu(QueryPage queryPage);

	//public int getNextValue(String sqName);

	public List queryBySql(String sql, Object[] params, Type[] paramTypes);

	public List<FwSource> findMenuByUrl(String url);
	
	/**
	 * 查找被业务模块使用的菜单信息
	 * @return
	 */
	DataPage<FwSource> findFwSourceInBusinessMap(QueryPage queryPage);
	
	/***
	 * 删除当前菜单以及所有下级菜单.
	 * @deprecated 这个方法可能引起其他问题。
	 * @param menuId 菜单Id.
	 */
	void deleteMenuAndSubMenu(long menuId);
	/***
	 * 获取用户菜单信息.
	 * @param userId 用户Id
	 * @return 返回资料员列表
	 */
	List<FwSource> getAllSource(int isMenu, Long userId);
	
	/***
	 * 获取角色信息菜单信息.
	 * @param roleCode 角色code
	 * @return 返回资料员列表
	 */
	List<FwSource> getAllSourceByRoleCode(List<String> roleCode);
	
	/**
	 * 
	* @Title: getAllSourceByRoleCode 
	* @Description: 获取角色资源
	* @createDate: 2017年11月4日 上午10:20:10
	* @param   
	* @return  List<FwSource> 
	* @author cloud_liu   
	* @throws
	 */
	public List<FwSource> getAllSourceByRoleCode(List<String> roleCodes,
			Long tenantId);

	/**
	 * 
	* @Title: getAll 
	* @Description: 获取所有 
	* @createDate: 2017年11月7日 下午2:45:31
	* @param   
	* @return  List<FwSource> 
	* @author cloud_liu   
	* @throws
	 */
	public List<FwSource> getAll();
	
}
