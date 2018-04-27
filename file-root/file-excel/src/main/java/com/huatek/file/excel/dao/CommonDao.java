package com.huatek.file.excel.dao;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.model.BaseEntity;
@Repository("CommonDao")
public class CommonDao extends AbstractDao<Long, BaseEntity> {
	@Transactional
	public void save(List<BaseEntity> list){
		for(BaseEntity entity:list){
			super.persistent(entity);
		}
	}
	@Transactional
	public List<Object[]> findResultsByHql(String hql, Map<String, Object> params) {
		Query query = this.createQuery(hql);
		query.setProperties(params);
		return query.list();
	}
	
	@Transactional
	public List<Object[]> findResultsBySql(String hql, Map<String, Object> params) {
		Query query = this.createSQLQuery(hql);
		query.setProperties(params);
		//query.setMaxResults(100);
		return query.list();
	}
}
