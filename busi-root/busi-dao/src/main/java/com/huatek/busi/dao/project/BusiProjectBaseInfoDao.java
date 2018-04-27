package com.huatek.busi.dao.project;
   
import java.util.List;

import com.huatek.busi.model.project.BusiProjectBaseInfo;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 项目基本信息
 * @author eli_cui
 *
 */

public interface BusiProjectBaseInfoDao {

    /** 
    * @Title: findBusiProjectBaseInfoById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-27 14:03:17
    * @param   id
    * @return  BusiProjectBaseInfo    
    */ 
    BusiProjectBaseInfo findBusiProjectBaseInfoById(Long id);

    /** 
    * @Title: saveOrUpdateBusiProjectBaseInfo 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-27 14:03:17
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiProjectBaseInfo(BusiProjectBaseInfo entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-27 14:03:17
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiProjectBaseInfo(BusiProjectBaseInfo entity);
    
    /** 
    * @Title: deleteBusiProjectBaseInfo 
    * @Description: 删除对象 
    * @createDate: 2017-10-27 14:03:17
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiProjectBaseInfo(BusiProjectBaseInfo entity);
    
    /** 
    * @Title: findAllBusiProjectBaseInfo 
    * @Description:获取全部对象
    * @createDate:  2017-10-27 14:03:17
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiProjectBaseInfo> findAllBusiProjectBaseInfo();

    /** 
    * @Title: findBusiProjectBaseInfoByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-27 14:03:17
    * @param   condition
    * @return  BusiProjectBaseInfo    
    */ 
    BusiProjectBaseInfo findBusiProjectBaseInfoByCondition(String condition);
    
    /**
     * @Description:获取对象翻页信息
     * @param orgId	当前登陆人组织机构id
     * @param currProId	当前登陆人选择id
     * @param queryPage	查询条件
     * @return
     */
    DataPage<BusiProjectBaseInfo> getAllBusiProjectBaseInfo(Long orgId, Long currProId, QueryPage queryPage);
    
}
