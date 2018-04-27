package com.huatek.file.dao;
   
import java.util.List;

import com.huatek.file.modal.CmdFileCatalogManager;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: CmdFileCatalogDao
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-07 10:02:42
  * @version: Windows 7
  */

public interface CmdFileCatalogDao {

    /** 
    * @Title: findCmdFileCatalogById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-07 10:02:42
    * @param   id
    * @return  CmdFileCatalog    
    */ 
    CmdFileCatalogManager findCmdFileCatalogById(Long id);

    /** 
    * @Title: saveOrUpdateCmdFileCatalog 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-07 10:02:42
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateCmdFileCatalog(CmdFileCatalogManager entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-07 10:02:42
    * @param   entity
    * @return  void    
    */ 
	void persistentCmdFileCatalog(CmdFileCatalogManager entity);
    
    /** 
    * @Title: deleteCmdFileCatalog 
    * @Description: 删除对象 
    * @createDate: 2017-11-07 10:02:42
    * @param   entity
    * @return  void    
    */ 
    void deleteCmdFileCatalog(CmdFileCatalogManager entity);
    
    /** 
    * @Title: findAllCmdFileCatalog 
    * @Description:获取全部对象
    * @createDate:  2017-11-07 10:02:42
    * @param   
    * @return  List<bean.className>    
    */ 
    List<CmdFileCatalogManager> findAllCmdFileCatalog();

    /** 
    * @Title: findCmdFileCatalogByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-07 10:02:42
    * @param   condition
    * @return  CmdFileCatalog    
    */ 
    CmdFileCatalogManager findCmdFileCatalogByCondition(String condition);
    
    /** 
    * @Title: getAllCmdFileCatalog 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-07 10:02:42
    * @param   queryPage
    * @return  DataPage<CmdFileCatalog>    
    */ 
    DataPage<CmdFileCatalogManager> getAllCmdFileCatalog(QueryPage queryPage);
    
    /**
     * 
    * @Title: getAllUserCategory 
    * @Description: 获取用户目录 
    * @createDate: 2017年11月7日 下午2:08:29
    * @param   
    * @return  List<CmdFileCatalog> 
    * @author cloud_liu   
    * @throws
     */
	List<CmdFileCatalogManager> getAllUserCategory(Long tenantId);

	/**
	 * 
	* @Title: getCmdFileCategoryByCodeAndParent 
	* @Description: 根据code和parent获取Category 
	* @createDate: 2017年11月7日 下午3:24:24
	* @param   
	* @return  CmdFileCatalog 
	* @author cloud_liu   
	* @throws
	 */
	CmdFileCatalogManager getCmdFileCatalogByConditionAndParent(String filed, String code,
			Long parentId, Long tenantId, Long id);
	
	/**
	 * 
	* @Title: getCmdFileCategoryLikePath 
	* @Description: 根据path获取所有目录 
	* @createDate: 2017年11月7日 下午4:54:39
	* @param   
	* @return  List<CmdFileCatalog> 
	* @author cloud_liu   
	* @throws
	 */
	List<CmdFileCatalogManager> getCmdFileCatalogLikePath(String oldPath,
			Long tenantId);
	
	/**
	 * 
	* @Title: getCmdFileCatalogs 
	* @Description: 获取目录 
	* @createDate: 2017年11月8日 上午10:44:48
	* @param   
	* @return  List<CmdFileCatalog> 
	* @author cloud_liu   
	* @throws
	 */
	List<CmdFileCatalogManager> getCmdFileCatalogs(Long[] ids);
	
	/**
	 * 
	* @Title: getChildrens 
	* @Description: 根据路径获取所有子集 
	* @createDate: 2017年11月8日 上午11:03:09
	* @param   
	* @return  List<CmdFileCatalog> 
	* @author cloud_liu   
	* @throws
	 */
	List<CmdFileCatalogManager> getChildrens(String path, Long tenantId);
	
	/**
	 * 
	* @Title: batchDelete 
	* @Description: 批量删除 
	* @createDate: 2017年11月8日 上午11:16:27
	* @param   
	* @return  void 
	* @author cloud_liu   
	* @throws
	 */
	void batchDelete(List<CmdFileCatalogManager> categorys);
	
	/**
	 * 
	* @Title: getCmdFileCatalogByParent 
	* @Description: 根据父级获取直接下级 
	* @createDate: 2017年11月8日 上午11:24:59
	* @param   
	* @return  List<CmdFileCatalog> 
	* @author cloud_liu   
	* @throws
	 */
	List<CmdFileCatalogManager> getCmdFileCatalogByParent(Long id);
	
	/**
	 * 
	* @Title: getCmdFileCatalogByPath 
	* @Description: 根据path获取 
	* @createDate: 2017年11月8日 下午8:37:00
	* @param   
	* @return  CmdFileCatalog 
	* @author cloud_liu   
	* @throws
	 */
	CmdFileCatalogManager getCmdFileCatalogByPath(String path, Long tenantId);
	
    
}
