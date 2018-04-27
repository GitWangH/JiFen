package com.huatek.busi.dao.pluspageLayout;
   
import java.util.List;

import com.huatek.busi.model.pluspageLayout.PhiPlusPagelayout;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPlusPagelayoutDao
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-23 15:00:36
  * @version: Windows 7
  */

public interface PhiPlusPagelayoutDao {

    /** 
    * @Title: findPhiPlusPagelayoutById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-23 15:00:36
    * @param   id
    * @return  PhiPlusPagelayout    
    */ 
    PhiPlusPagelayout findPhiPlusPagelayoutById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPlusPagelayout 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-23 15:00:36
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPlusPagelayout(PhiPlusPagelayout entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-23 15:00:36
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPlusPagelayout(PhiPlusPagelayout entity);
    
    /** 
    * @Title: deletePhiPlusPagelayout 
    * @Description: 删除对象 
    * @createDate: 2018-01-23 15:00:36
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPlusPagelayout(PhiPlusPagelayout entity);
    
    /** 
    * @Title: findAllPhiPlusPagelayout 
    * @Description:获取全部对象
    * @createDate:  2018-01-23 15:00:36
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPlusPagelayout> findAllPhiPlusPagelayout();

    /** 
    * @Title: findPhiPlusPagelayoutByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-23 15:00:36
    * @param   condition
    * @return  PhiPlusPagelayout    
    */ 
    PhiPlusPagelayout findPhiPlusPagelayoutByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPlusPagelayout 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-23 15:00:36
    * @param   queryPage
    * @return  DataPage<PhiPlusPagelayout>    
    */ 
    DataPage<PhiPlusPagelayout> getAllPhiPlusPagelayout(QueryPage queryPage);

    /**根据id查询广告的个数*/
	List<PhiPlusPagelayout> getCountByParentId(Long id);
	/**批量添加*/
	void batchAdd(List<PhiPlusPagelayout> entityList);
    
	/***
	 * 根据code查询plus页面配置
	 * @param code
	 * @return
	 */
	PhiPlusPagelayout findPhiPlusPagelayoutByCode(String code,String clientType);
	
	
	
}
