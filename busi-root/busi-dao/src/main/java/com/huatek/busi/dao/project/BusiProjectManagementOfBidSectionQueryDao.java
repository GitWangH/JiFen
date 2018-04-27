package com.huatek.busi.dao.project;

import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionShow;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiProjectManagementOfBidSectionQueryDao {
   /** 
    * @Title: getAllBusiProjectManagementOfBidSectionShow 
    * @Description:获取对象翻页信息(页面显示)
    * @createDate: 2017-10-27 14:03:46
    * @param   orgId 当前登录人机构id
    * @param   currProId 当前登录人选择机构id
    * @param   queryPage 页面查询条件
    * @return  DataPage<BusiProjectManagementOfBidSection>    
    */ 
    DataPage<BusiProjectManagementOfBidSectionShow> getAllBusiProjectManagementOfBidSectionShow(Long orgId, Long currProId, QueryPage queryPage);
}
