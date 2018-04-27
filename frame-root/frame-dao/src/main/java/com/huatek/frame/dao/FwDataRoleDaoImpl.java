package com.huatek.frame.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwDataRole;
import com.huatek.frame.dao.model.FwSource;

@Transactional
@Repository("FwDataRole")
public class FwDataRoleDaoImpl extends AbstractDao<Long, FwDataRole> implements FwDataRoleDao {
	
	Logger logger =  LoggerFactory.getLogger(FwDataRoleDaoImpl.class);
	
	public DataPage<FwDataRole> getAllFwDataRole(QueryPage queryPage){
		return queryPageData(queryPage);
	}
	
	@SuppressWarnings("unchecked")
	public List<FwDataRole> findAllFwDataRole() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	/**
	 * 持久化保存
	 */
	@Override
	public void persistent(FwDataRole fwDataRole) {
		super.persistent(fwDataRole);
	}

	/**
	 * 根据Id找到数据角色
	 * @param id
	 * @return
	 */
	public FwDataRole findById(Long id) {
		return super.getByKey(id);
	}
	
	/**
	 * 删除数据角色信息
	 */
	public void deleteFwDataRole(FwDataRole fwDataRole) {
		super.delete(fwDataRole);
	}

	public FwDataRole getFwDataRoleByName(String name) {
		String hql = "from FwDataRole t where t.name=?";
		return (FwDataRole)super.createQuery(hql).setString(0, name).uniqueResult();
	}
	
	/**
	 * 查找被业务模块使用的菜单信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FwSource> findFwSourceInBusinessMap(QueryPage queryPage) {
		StringBuffer sql = new StringBuffer("from FwSource ");
		sql.append(" t where 1=1 and exits (select 1 from FwBusinessMap m where m.fwSourceObject.id = t.id) ");
		Query query = super.createQuery(sql.toString());
		List<FwSource> fwSourceList = query.list();
		return fwSourceList;
	}

}
