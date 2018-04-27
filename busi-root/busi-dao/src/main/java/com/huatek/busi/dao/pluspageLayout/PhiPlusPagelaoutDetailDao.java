package com.huatek.busi.dao.pluspageLayout;
   
import java.util.List;

import com.huatek.busi.model.pluspageLayout.PhiPlusPagelaoutDetail;
import com.huatek.busi.model.pluspageLayout.PhiPlusPagelayout;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPlusPagelaoutDetail
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-25 13:13:39
  * @version: 1.0
  */

public interface PhiPlusPagelaoutDetailDao {

    /** 
    * @Title: findPhiPlusPagelaoutDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-25 13:13:39
    * @param   id
    * @return  PhiPluPagelaoutDetail    
    */ 
    PhiPlusPagelaoutDetail findPhiPlusPagelaoutDetailById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPlusPagelaoutDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-25 13:13:39
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPlusPagelaoutDetail(PhiPlusPagelaoutDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-25 13:13:39
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPlusPagelaoutDetail(PhiPlusPagelaoutDetail entity);
    
    /** 
    * @Title: deletePhiPluPagelaoutDetail 
    * @Description: 删除对象 
    * @createDate: 2018-01-25 13:13:39
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPlusPagelaoutDetail(PhiPlusPagelaoutDetail entity);
    
    /** 
    * @Title: findAllPhiPluPagelaoutDetail 
    * @Description:获取全部对象
    * @createDate:  2018-01-25 13:13:39
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPlusPagelaoutDetail> findAllPhiPlusPagelaoutDetail();

    /** 
    * @Title: findPhiPluPagelaoutDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-25 13:13:39
    * @param   condition
    * @return  PhiPluPagelaoutDetail    
    */ 
    PhiPlusPagelaoutDetail findPhiPlusPagelaoutDetailByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPluPagelaoutDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-25 13:13:39
    * @param   queryPage
    * @return  DataPage<PhiPluPagelaoutDetail>    
    */ 
    DataPage<PhiPlusPagelaoutDetail> getAllPhiPlusPagelaoutDetail(QueryPage queryPage);
    
    
    /**批量添加*/
	void batchUpdate(List<PhiPlusPagelaoutDetail> entityList);
	
	void merge(PhiPlusPagelaoutDetail entityList);
	
	List<PhiPlusPagelaoutDetail> getByParentId(Long parentId);
	
	void reset(Long id);
	
	//List<PhiPlusPagelaoutDetail> getBypageCode(String code);
}
