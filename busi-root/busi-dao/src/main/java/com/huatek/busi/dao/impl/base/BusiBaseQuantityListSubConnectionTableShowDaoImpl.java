package com.huatek.busi.dao.impl.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseQuantityListSubConnectionTableShowDao;
import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTableShow;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 分部分项与工程量清单挂接 展示 查询
 * @author eli_cui
 *
 */
@Repository("busiBaseQuantityListSubConnectionTableShowDao")
public class BusiBaseQuantityListSubConnectionTableShowDaoImpl extends AbstractDao<Long,  BusiBaseQuantityListSubConnectionTableShow>  implements BusiBaseQuantityListSubConnectionTableShowDao{
	private Logger logger = LoggerFactory.getLogger(BusiBaseQuantityListSubConnectionTableShowDaoImpl.class);
	@Override
	public DataPage<BusiBaseQuantityListSubConnectionTableShow> getAllBusiBaseQuantityListSubConnectionTableShow(QueryPage queryPage, Long subId) {
		queryPage.setOrderBy(" b.id desc ");
		//查询sql
		StringBuilder querySql = new StringBuilder(" select new com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTableShow(b.id, a.number, a.name, c.number,c.name) ");
		//CountSql
		StringBuilder countSql = new StringBuilder(" select count(1) ");
		StringBuilder fromSql = new StringBuilder(" from BusiBaseSubEngineering a, BusiBaseQuantityListSubConnectionTable b, BusiBaseEngineeringQuantityList c ");
		fromSql.append(" where a.id = b.subEngineeringId and b.engineeringQuantityId = c.id and b.subEngineeringId = ");
		fromSql.append(subId);
		String fromSqlStr = fromSql.toString();
		DataPage<BusiBaseQuantityListSubConnectionTableShow> result = queryPageData(querySql.append(fromSqlStr).toString(), countSql.append(fromSql).toString(), queryPage);
		return result;
	}
}
