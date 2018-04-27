package com.huatek.busi.dao.phicom.member;
   
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiMemberDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-18 15:33:14
  * @version: 1.0
  */

public interface PhiMemberDao {

    /** 
    * @Title: findPhiMemberById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-18 15:33:14
    * @param   id
    * @return  PhiMember    
    */ 
    PhiMember findPhiMemberById(Long id);
    PhiMember findPhiMemberByIdLock(Long id);
    /** 
    * @Title: saveOrUpdatePhiMember 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-18 15:33:14
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiMember(PhiMember entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-18 15:33:14
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiMember(PhiMember entity);
    
    /** 
    * @Title: deletePhiMember 
    * @Description: 删除对象 
    * @createDate: 2017-12-18 15:33:14
    * @param   entity
    * @return  void    
    */ 
    void deletePhiMember(PhiMember entity);
    
    /** 
    * @Title: findAllPhiMember 
    * @Description:获取全部对象
    * @createDate:  2017-12-18 15:33:14
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiMember> findAllPhiMember();

    /** 
    * @Title: findPhiMemberByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-18 15:33:14
    * @param   condition
    * @return  PhiMember    
    */ 
    PhiMember findPhiMemberByCondition(String condition);
    
    /** 
    * @Title: getAllPhiMember 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-18 15:33:14
    * @param   queryPage
    * @return  DataPage<PhiMember>    
    */ 
    DataPage<PhiMember> getAllPhiMember(QueryPage queryPage);
    
    /** 
    * @Title: findPhiMemberByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-18 15:33:14
    * @param   condition
    * @return  PhiMember    
    */ 
    PhiMember findPhiMemberByUid(int Uid);
    
    List<PhiMember> getRandomWinners(Long productId,List<Long> idsList,int count);
    
    
    /** 
     * @Title: findAllPlusPhiMember 
     * @Description:获取全部plus会员对象
     * @createDate:  2017-12-18 15:33:14
     * @param   
     * @return  List<bean.className>    
     */ 
     List<PhiMember> findAllPlusPhiMember(String currentDate);
     
     /***
      * 根据登录用户查找用户信息
      * @param acctName
      * @return
      */
     
 	public PhiMember findPhiMemberByTel(String acctName) ;
 	
	public List<PhiMember> findPlusPhiMember();
	
	PhiMember findPlusPhiMemberById(Long memberId);
	
	BigDecimal findCountExchange(Long productId,Long memberId);
	
	/**
	 * 根据分页查到的会员数据查询会员信息
	 * @param memberIdList
	 * @return
	 */
	Map<Long, PhiMember> findPlusPhiMemberByIds(List<Long> memberIdList);
}
