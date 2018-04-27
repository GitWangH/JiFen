package com.huatek.busi.dao.phicom.member;
   
import com.huatek.busi.model.phicom.plusmember.ViewPhiPlusMemberOrder;
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

public interface ViewPhiPlusMemberOrderDao {

    /** 
    * @Title: getAllPhiMember 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-18 15:33:14
    * @param   queryPage
    * @return  DataPage<PhiMember>    
    */ 
    DataPage<ViewPhiPlusMemberOrder> getAllViewPhiPlusMemberOrder(QueryPage queryPage);
    
}
