package com.huatek.busi.dao.phicom.member;
   
import java.util.List;

import com.huatek.busi.model.phicom.member.PhiMemberDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiMemberDetailDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-30 13:31:28
  * @version: 1.0
  */

public interface PhiMemberDetailDao {

    /** 
    * @Title: findPhiMemberDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-30 13:31:28
    * @param   id
    * @return  PhiMemberDetail    
    */ 
    PhiMemberDetail findPhiMemberDetailById(Long id);

    /** 
    * @Title: saveOrUpdatePhiMemberDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-30 13:31:28
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiMemberDetail(PhiMemberDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-30 13:31:28
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiMemberDetail(PhiMemberDetail entity);
    
    /** 
    * @Title: deletePhiMemberDetail 
    * @Description: 删除对象 
    * @createDate: 2017-12-30 13:31:28
    * @param   entity
    * @return  void    
    */ 
    void deletePhiMemberDetail(PhiMemberDetail entity);
    
    /** 
    * @Title: findAllPhiMemberDetail 
    * @Description:获取全部对象
    * @createDate:  2017-12-30 13:31:28
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiMemberDetail> findAllPhiMemberDetail();

    /** 
    * @Title: findPhiMemberDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-30 13:31:28
    * @param   condition
    * @return  PhiMemberDetail    
    */ 
    PhiMemberDetail findPhiMemberDetailByCondition(String condition);
    
    /** 
    * @Title: getAllPhiMemberDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-30 13:31:28
    * @param   queryPage
    * @return  DataPage<PhiMemberDetail>    
    */ 
    DataPage<PhiMemberDetail> getAllPhiMemberDetail(QueryPage queryPage);
    
}
