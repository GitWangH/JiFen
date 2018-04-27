package com.huatek.busi.dao.phicom.scoreData;
   
import java.util.List;

import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 数据管理
  * @ClassName: PhiScoreFlowDao
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-08 13:35:12
  * @version: 1.0
  */

public interface PhiScoreDataDao {

    /**
    *  统计积分流水--获得和消耗
    * @param type 0 当前  1上一年
    * @return
    */
    List<Object> getPhiScoreFlowTotalByScoreType(String type);
    
    /**
     * 统计积分流水--任务项的获得和消耗
     * @param type 0 当前  1 上一年
     * @return
     */
    List<Object> getPhiScoreFlowTotalByScoreTypeAndTaskId(String type);
    
     DataPage<Object> getAllPhiScoreCoups(QueryPage queryPage);
    
}
