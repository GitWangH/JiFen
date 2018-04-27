package com.huatek.busi.dao.base;

import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTable;
import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTableShow;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 分部分项 和 工程量清单 挂接 展现DAO
 * @author eli_cui
 *
 */
public interface BusiBaseQuantityListSubConnectionTableShowDao {
   /** 
    * @Title: getAllBusiBaseQuantityListSubConnectionTable 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-24 13:25:14
    * @param   queryPage
    * @return  DataPage<BusiBaseQuantityListSubConnectionTable>    
    */ 
	DataPage<BusiBaseQuantityListSubConnectionTableShow> getAllBusiBaseQuantityListSubConnectionTableShow(QueryPage queryPage, Long subId);
}
