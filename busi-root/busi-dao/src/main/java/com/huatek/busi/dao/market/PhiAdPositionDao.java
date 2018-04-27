package com.huatek.busi.dao.market;
   
import java.util.List;

import com.huatek.busi.model.market.PhiAdPosition;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiAdPositionDao
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-19 13:43:48
  * @version: Windows 7
  */

public interface PhiAdPositionDao {

    /** 
    * @Title: findPhiAdPositionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-19 13:43:48
    * @param   id
    * @return  PhiAdPosition    
    */ 
    PhiAdPosition findPhiAdPositionById(Long id);

    /** 
    * @Title: saveOrUpdatePhiAdPosition 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-19 13:43:48
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiAdPosition(PhiAdPosition entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-19 13:43:48
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiAdPosition(PhiAdPosition entity);
    
    /** 
    * @Title: deletePhiAdPosition 
    * @Description: 删除对象 
    * @createDate: 2018-01-19 13:43:48
    * @param   entity
    * @return  void    
    */ 
    void deletePhiAdPosition(PhiAdPosition entity);
    
    /** 
    * @Title: findAllPhiAdPosition 
    * @Description:获取全部对象
    * @createDate:  2018-01-19 13:43:48
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiAdPosition> findAllPhiAdPosition();

    /** 
    * @Title: findPhiAdPositionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-19 13:43:48
    * @param   condition
    * @return  PhiAdPosition    
    */ 
    PhiAdPosition findPhiAdPositionByCondition(String condition);
    
    /** 
    * @Title: getAllPhiAdPosition 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-19 13:43:48
    * @param   queryPage
    * @return  DataPage<PhiAdPosition>    
    */ 
    DataPage<PhiAdPosition> getAllPhiAdPosition(QueryPage queryPage);
    /*批量添加*/
    void batchAdd(List<PhiAdPosition> phiAdPositionList);
    /*根据adCode获取广告位和图片信息 */
	List<PhiAdPosition> getAdPositionAndPhoInfoByAdCode(String adCode);
}
