package com.huatek.busi.dao.phicom.member;


   
import java.math.BigDecimal;
import java.util.List;

import com.huatek.busi.model.phicom.member.PhiMemberGrade;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiMemberGradeDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-25 17:52:35
  * @version: 1.0
  */

public interface PhiMemberGradeDao {

    /** 
    * @Title: findPhiMemberGradeById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-25 17:52:35
    * @param   id
    * @return  PhiMemberGrade    
    */ 
    PhiMemberGrade findPhiMemberGradeById(Long id);

    /** 
    * @Title: saveOrUpdatePhiMemberGrade 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-25 17:52:35
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiMemberGrade(PhiMemberGrade entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-25 17:52:35
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiMemberGrade(PhiMemberGrade entity);
    
    /** 
    * @Title: deletePhiMemberGrade 
    * @Description: 删除对象 
    * @createDate: 2017-12-25 17:52:35
    * @param   entity
    * @return  void    
    */ 
    void deletePhiMemberGrade(PhiMemberGrade entity);
    
    /** 
    * @Title: findAllPhiMemberGrade 
    * @Description:获取全部对象
    * @createDate:  2017-12-25 17:52:35
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiMemberGrade> findAllPhiMemberGrade();

    /** 
    * @Title: findPhiMemberGradeByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-25 17:52:35
    * @param   condition
    * @return  PhiMemberGrade    
    */ 
    PhiMemberGrade findPhiMemberGradeByCondition(String condition);
    
    /** 
    * @Title: getAllPhiMemberGrade 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-25 17:52:35
    * @param   queryPage
    * @return  DataPage<PhiMemberGrade>    
    */ 
    DataPage<PhiMemberGrade> getAllPhiMemberGrade(QueryPage queryPage);
    
    /**
     * @Title: getMemberGradeList 
     * @Description:获取在积分上限、积分下限之间的数据
     */
    PhiMemberGrade getMemberGrade(BigDecimal allscore);
    
    /**
     * @Title: findPhiMemberGradeBygrade 
     * @param memberGrade
     *  @Description:根据等级名称查会员等级数据
     * @return
     */
   List<PhiMemberGrade> findPhiMemberGradeBygrade(String memberGrade);
}
