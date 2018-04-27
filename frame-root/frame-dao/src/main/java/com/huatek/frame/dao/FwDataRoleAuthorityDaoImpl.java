package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwBusinessMap;
import com.huatek.frame.dao.model.FwDataRole;
import com.huatek.frame.dao.model.FwDataRoleAuthority;
import com.huatek.frame.dao.model.FwSource;

@Repository("FwDataRoleAuthority")
public class FwDataRoleAuthorityDaoImpl extends AbstractDao<Long, FwDataRoleAuthority> implements FwDataRoleAuthorityDao {
	
	Logger logger =  LoggerFactory.getLogger(FwDataRoleAuthorityDaoImpl.class);
	
	public DataPage<FwDataRoleAuthority> getAllFwDataRoleAuthority(QueryPage queryPage){
		return queryPageData(queryPage);
	}
	
	@SuppressWarnings("unchecked")
	public List<FwDataRoleAuthority> findAllFwDataRoleAuthority() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	/**
	 * 持久化保存
	 */
	@Override
	public void persistent(FwDataRoleAuthority fwDataRoleAuthority) {
		super.persistent(fwDataRoleAuthority);
	}

	/**
	 * 根据Id找到数据角色
	 * @param id
	 * @return
	 */
	public FwDataRoleAuthority findById(Long id) {
		return super.getByKey(id);
	}
	
	/**
	 * 删除数据角色信息
	 */
	public void deleteFwDataRoleAuthority(FwDataRoleAuthority FwDataRoleAuthority) {
		super.delete(FwDataRoleAuthority);
	}

	public FwDataRoleAuthority getFwDataRoleAuthorityByName(String name) {
		String hql = "from FwDataRoleAuthority t where t.name=?";
		return (FwDataRoleAuthority)super.createQuery(hql).setString(0, name).uniqueResult();
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

	
	/**
	 * 获取已经关联的数据信息
	 * @param fwProperty
	 * @param fwBusinessMap
	 * @param fwDataRole
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FwDataRoleAuthority> getFwDataRoleAuthorityWithSomeId(
			Long fwPropertyId, FwBusinessMap fwBusinessMap,
			FwDataRole fwDataRole) {
		StringBuffer sql = new StringBuffer("from FwDataRoleAuthority ");
		sql.append(" t where 1=1 and t.fwDataRole.id = ? and t.fwSource.id = ? and t.fwSourceEntity.id = ? and t.dataId = ? ");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, fwDataRole.getId());
		query.setLong(1, fwBusinessMap.getFwSource().getId());
		query.setLong(2, fwBusinessMap.getFwSourceEntity().getId());
		query.setString(3, fwPropertyId.toString());
		List<FwDataRoleAuthority> fwDataRoleAuthorityList = query.list();
		return fwDataRoleAuthorityList;
	}

	public void deleteAuthorityByBusinessMap(FwBusinessMap busMap) {
		QueryPage queryPage = new QueryPage();
		StringBuffer sqlCondition = new StringBuffer();
		sqlCondition.append("fwSource=").append(busMap.getFwSource().getId()) ;
		sqlCondition.append("and fwSourceEntity=").append(busMap.getFwSourceEntity().getId()) ;
		queryPage.setSqlCondition(sqlCondition.toString());
		super.deleteListData(queryPage);
	}
	/***
	 * 获取用户的数据权限.
	 */
	@SuppressWarnings("unchecked")
	// @Cacheable("userCache")
	public List<FwDataRoleAuthority> getFwDataRoleAuthorityByUserId(Long userId){
		// 获取数据权限列表
		Query query = super.createQuery(" from FwDataRoleAuthority t where exists (select 1 "
				+ " from FwUserDataRole m where m.fwDataRole.id = t.fwDataRole.id and m.fwAccount.id=? ) ");
		query.setLong(0, userId);
		return query.list();
	}
	/**
	 * 获取当前用户未配置数据权限的业务模块
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FwBusinessMap> getUnAnthorityBusMapByUserId(Long userId){
		StringBuffer sql = new StringBuffer("from FwBusinessMap t where not exists (");
		sql.append("select 1 from FwDataRoleAuthority d,FwUserDataRole m ");
		sql.append("where t.fwSourceObject.id = d.fwSource.id ");
		sql.append("and t.fwSourceEntity.id = d.fwSourceEntity.id ");
		sql.append("and m.fwDataRole.id = d.fwDataRole.id ");
		sql.append("and m.fwAccount.id=? )");
		Query query = super.createQuery(sql.toString());
		query.setLong(0, userId);
		return query.list();
	}
			
	
}
