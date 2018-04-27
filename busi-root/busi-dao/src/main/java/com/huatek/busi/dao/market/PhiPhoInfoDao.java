package com.huatek.busi.dao.market;
   
import java.util.List;

import com.huatek.busi.model.market.PhiPhoInfo;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPhoInfoDao
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-19 13:43:48
  * @version: Windows 7
  */

public interface PhiPhoInfoDao {

    /** 
    * @Title: findPhiPhoInfoById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-19 13:43:48
    * @param   id
    * @return  PhiPhoInfo    
    */ 
    PhiPhoInfo findPhiPhoInfoById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPhoInfo 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-19 13:43:48
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPhoInfo(PhiPhoInfo entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-19 13:43:48
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPhoInfo(PhiPhoInfo entity);
    
    /** 
    * @Title: deletePhiPhoInfo 
    * @Description: 删除对象 
    * @createDate: 2018-01-19 13:43:48
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPhoInfo(PhiPhoInfo entity);
    
    /** 
    * @Title: findAllPhiPhoInfo 
    * @Description:获取全部对象
    * @createDate:  2018-01-19 13:43:48
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPhoInfo> findAllPhiPhoInfo();

    /** 
    * @Title: findPhiPhoInfoByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-19 13:43:48
    * @param   condition
    * @return  PhiPhoInfo    
    */ 
    PhiPhoInfo findPhiPhoInfoByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPhoInfo 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-19 13:43:48
    * @param   queryPage
    * @return  DataPage<PhiPhoInfo>    
    */ 
    DataPage<PhiPhoInfo> getAllPhiPhoInfo(QueryPage queryPage);

    /*批量添加*/
	void batchAdd(List<PhiPhoInfo> entityList);
	
	/*批量删除*/
	void batchDelete(List<PhiPhoInfo> entityList);
	
	
	/*根据adCode获取广告位和图片信息 */
	List<PhiPhoInfo> getAdPositionAndPhoInfoByAdCode(String adCode);
	/*根据adCode获取图片信息*/
	List<PhiPhoInfo> getPhiPhoInfoByAdCode(String adCode);
	
}
