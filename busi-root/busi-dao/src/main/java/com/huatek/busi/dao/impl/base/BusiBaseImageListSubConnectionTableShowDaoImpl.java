package com.huatek.busi.dao.impl.base;

import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseImageListSubConnectionTableShowDao;
import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTableShow;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 形象清单与分部分项挂接展示查询
 * @author eli_cui
 *
 */
@Repository("busiBaseImageListSubConnectionTableShowDao")
public class BusiBaseImageListSubConnectionTableShowDaoImpl extends AbstractDao<Long, BusiBaseImageListSubConnectionTableShow> implements BusiBaseImageListSubConnectionTableShowDao{
	
	@Override
    public DataPage<BusiBaseImageListSubConnectionTableShow> getAllBusiBaseImageListSubConnectionTable(QueryPage queryPage, Long imageId) {
		queryPage.setOrderBy(" b.id desc ");
    	StringBuilder querySql = new StringBuilder(" select new com.huatek.busi.model.base.BusiBaseImageListSubConnectionTableShow(b.id, a.number, a.name, c.number, c.name)  ");
    	StringBuilder countSql = new StringBuilder(" select count(1) ");
    	StringBuilder fromSql = new StringBuilder(" FROM BusiBaseImageList a, BusiBaseImageListSubConnectionTable b, BusiBaseSubEngineering c ");
    	fromSql.append(" where a.id = b.imageListId and b.subEngineeringId = c.id and b.imageListId = ");
    	fromSql.append(imageId);
    	String fromSqlStr = fromSql.toString();
    	DataPage<BusiBaseImageListSubConnectionTableShow> result = queryPageData(querySql.append(fromSqlStr).toString(), countSql.append(fromSql).toString(), queryPage);
		return result;
    }

}
