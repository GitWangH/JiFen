package com.huatek.busi.dao.phicom.member;
   
import java.util.List;

import com.huatek.busi.model.phicom.member.PhiMemberPrivilege;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiMemberPrivilegeDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2018-01-22 21:00:56
  * @version: Windows 7
  */

public interface PhiMemberPrivilegeDao {

    /** 
    * @Title: findPhiMemberPrivilegeById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-22 21:00:56
    * @param   id
    * @return  PhiMemberPrivilege    
    */ 
    PhiMemberPrivilege findPhiMemberPrivilegeById(Long id);
    PhiMemberPrivilege findPhiMemberPrivilegeByMemberGradeId(Long gradeId);
    PhiMemberPrivilege findPhiMemberBirthdayPrivilege();

    /** 
    * @Title: saveOrUpdatePhiMemberPrivilege 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-22 21:00:56
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiMemberPrivilege(PhiMemberPrivilege entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-22 21:00:56
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiMemberPrivilege(PhiMemberPrivilege entity);
    
    /** 
    * @Title: deletePhiMemberPrivilege 
    * @Description: 删除对象 
    * @createDate: 2018-01-22 21:00:56
    * @param   entity
    * @return  void    
    */ 
    void deletePhiMemberPrivilege(PhiMemberPrivilege entity);
    
    /** 
    * @Title: findAllPhiMemberPrivilege 
    * @Description:获取全部对象
    * @createDate:  2018-01-22 21:00:56
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiMemberPrivilege> findAllPhiMemberPrivilege();

    /** 
    * @Title: findPhiMemberPrivilegeByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-22 21:00:56
    * @param   condition
    * @return  PhiMemberPrivilege    
    */ 
    PhiMemberPrivilege findPhiMemberPrivilegeByCondition(String condition);
    
    /** 
    * @Title: getAllPhiMemberPrivilege 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-22 21:00:56
    * @param   queryPage
    * @return  DataPage<PhiMemberPrivilege>    
    */ 
    DataPage<PhiMemberPrivilege> getAllPhiMemberPrivilege(QueryPage queryPage);
    
}
