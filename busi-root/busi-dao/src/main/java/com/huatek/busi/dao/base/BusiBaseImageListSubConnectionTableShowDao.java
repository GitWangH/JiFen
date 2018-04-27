package com.huatek.busi.dao.base;

import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTableShow;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 形象清单与分部分项展示dao
 * @author eli_cui
 *
 */
public interface BusiBaseImageListSubConnectionTableShowDao {
	
	/**
	 * 获取形象清单与分部分项挂接的数据
	 * @param queryPage
	 * @return
	 */
	DataPage<BusiBaseImageListSubConnectionTableShow> getAllBusiBaseImageListSubConnectionTable(QueryPage queryPage, Long imageId);
}
