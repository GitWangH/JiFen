package com.huatek.busi.dao.phicom.winner;
   
import java.util.List;

import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.model.phicom.winner.PhiWinners;
import com.huatek.busi.model.phicom.winner.VirtualUser;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiWinnersDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 13:42:13
  * @version: 1.0
  */

public interface PhiWinnersDao {

    /** 
    * @Title: findPhiWinnersById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-10 13:42:13
    * @param   id
    * @return  PhiWinners    
    */ 
    PhiWinners findPhiWinnersById(Long id);

    /** 
    * @Title: saveOrUpdatePhiWinners 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-10 13:42:13
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiWinners(PhiWinners entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-10 13:42:13
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiWinners(PhiWinners entity);
    
    /** 
    * @Title: deletePhiWinners 
    * @Description: 删除对象 
    * @createDate: 2018-01-10 13:42:13
    * @param   entity
    * @return  void    
    */ 
    void deletePhiWinners(PhiWinners entity);
    
    /** 
    * @Title: findAllPhiWinners 
    * @Description:获取全部对象
    * @createDate:  2018-01-10 13:42:13
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiWinners> findAllPhiWinners();

    /** 
    * @Title: findPhiWinnersByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-10 13:42:13
    * @param   condition
    * @return  PhiWinners    
    */ 
    PhiWinners findPhiWinnersByCondition(String condition);
    
    /** 
    * @Title: getAllPhiWinners 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-10 13:42:13
    * @param   queryPage
    * @return  DataPage<PhiWinners>    
    */ 
    DataPage<PhiWinners> getAllPhiWinners(QueryPage queryPage);
    
    
    void  saveWinnerList(List<PhiWinners> winnerlist);
    
    
    
    List<PhiMember> getAllMemberByProductId(Long ProductId);
    
    List<PhiOrder> getAllOrderByProductId(Long ProductId);
    //获得所有的虚拟用户
    List<VirtualUser> getAllVirtualUsers();
    
    
    void cleanAllWinnersByProductId(Long id);
    //获取所有的随机中奖的用户
    List<PhiWinners> getAllRandomMemberByProductId(Long id);

    //从order用户找到所有的随机中奖用户排除手动中奖
    List<PhiOrder> getRandomMemberByProductId(Long id);


    /**
     * 通过商品id找到获奖名单
     * @param productId
     * @return
     */
	List<PhiWinners> getAllPhiWinnersByproductId(Long productId);

    
    List<PhiWinners> getPhiWinnersByProductIdAndMemberId(Long Pid,Long MId);
    
    
}
