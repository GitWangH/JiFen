package com.huatek.busi.dao.phicom.score;
   
import java.util.List;
import java.util.Map;

import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: PhiScoreFlowDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-08 13:35:12
  * @version: 1.0
  */

public interface PhiScoreFlowDao {

    /** 
    * @Title: findPhiScoreFlowById 
    * @Description: 根据ID获取对象 
    * @createDate:  2018-01-08 13:35:12
    * @param   id
    * @return  PhiScoreFlow    
    */ 
    PhiScoreFlow findPhiScoreFlowById(Long id);

    /** 
    * @Title: saveOrUpdatePhiScoreFlow 
    * @Description: 保存或者修改对象  
    * @createDate:  2018-01-08 13:35:12
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdatePhiScoreFlow(PhiScoreFlow entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2018-01-08 13:35:12
    * @param   entity
    * @return  void    
    */ 
	void persistentPhiScoreFlow(PhiScoreFlow entity);
    
    /** 
    * @Title: deletePhiScoreFlow 
    * @Description: 删除对象 
    * @createDate: 2018-01-08 13:35:12
    * @param   entity
    * @return  void    
    */ 
    void deletePhiScoreFlow(PhiScoreFlow entity);
    
    /** 
    * @Title: findAllPhiScoreFlow 
    * @Description:获取全部对象
    * @createDate:  2018-01-08 13:35:12
    * @param   
    * @return  List<bean.className>    
    */ 
    List<PhiScoreFlow> findAllPhiScoreFlow();

    /** 
    * @Title: findPhiScoreFlowByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2018-01-08 13:35:12
    * @param   condition
    * @return  PhiScoreFlow    
    */ 
    PhiScoreFlow findPhiScoreFlowByCondition(String condition);
    
    /** 
    * @Title: getAllPhiScoreFlow 
    * @Description:获取对象翻页信息
    * @createDate: 2018-01-08 13:35:12
    * @param   queryPage
    * @return  DataPage<PhiScoreFlow>    
    */ 
    DataPage<PhiScoreFlow> getAllPhiScoreFlow(QueryPage queryPage);
    

    void expireLastYearScore();
    int getSoonFallDueScore(Long memberId);
    
    /***
     * 统计经验积分（从当前时间往后推365天）
     * @param memberId
     * @param currenDate
     * @return
     */
   Object getPhiScoreFlowAllScore(Long memberId );

   public int getRemainScore(Long memberId);
   
   public PhiScoreFlow findPhiScoreFlowByCondition(Long memberId,String orderCode);
   
   public List<PhiScoreFlow> findPhiScoreFlowByCondition(Long memberId);

   public PhiScoreFlow findPhiScoreFlowByRCondition(long memberId, String refundCode);

}
