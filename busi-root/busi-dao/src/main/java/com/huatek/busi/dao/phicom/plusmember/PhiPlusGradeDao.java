package com.huatek.busi.dao.phicom.plusmember;
   
import java.util.List;




import com.huatek.busi.model.phicom.plusmember.PhiPlusGrade;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiPlusGradeDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-03 19:59:33
  * @version: 1.0
  */

public interface PhiPlusGradeDao {

    /** 
    * @Title: findPhiPlusGradeById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-03 19:59:33
    * @param   id
    * @return  PhiPlusGrade    
    */ 
    PhiPlusGrade findPhiPlusGradeById(Long id);

    /** 
    * @Title: saveOrUpdatePhiPlusGrade 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-03 19:59:33
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiPlusGrade(PhiPlusGrade entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-03 19:59:33
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiPlusGrade(PhiPlusGrade entity);
    
    /** 
    * @Title: deletePhiPlusGrade 
    * @Description: 删除对象 
    * @createDate: 2018-01-03 19:59:33
    * @param   entity
    * @return  void    
    */ 
    void deletePhiPlusGrade(PhiPlusGrade entity);
    
    /** 
    * @Title: findAllPhiPlusGrade 
    * @Description:获取全部对象
    * @createDate:  2018-01-03 19:59:33
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiPlusGrade> findAllPhiPlusGrade();

    /** 
    * @Title: findPhiPlusGradeByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-03 19:59:33
    * @param   condition
    * @return  PhiPlusGrade    
    */ 
    PhiPlusGrade findPhiPlusGradeByCondition(String condition);
    
    /** 
    * @Title: getAllPhiPlusGrade 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-03 19:59:33
    * @param   queryPage
    * @return  DataPage<PhiPlusGrade>    
    */ 
    DataPage<PhiPlusGrade> getAllPhiPlusGrade(QueryPage queryPage);
    
    /** 
    * @Title: findPhiPlusGradeByPlusgrade 
    * @Description: 根据plus会员等级获取对象 
    * @createDate: 2018-01-04 13:09:33
    * @param   plusGrade
    * @return  PhiPlusGrade    
    */ 
    PhiPlusGrade findPhiPlusGradeByPlusgrade(String plusGrade);
    
    public PhiPlusGrade findPhiPlusGradeByPlusCode(String plusCode);
}
