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
import com.huatek.frame.dao.model.FwApplyScope;

@Repository("FwApplyScope")
@Transactional
public class FwApplyScopeDaoImpl extends AbstractDao<Long, FwApplyScope> implements FwApplyScopeDao {
	
	Logger logger =  LoggerFactory.getLogger(FwApplyScopeDaoImpl.class);
	
	/**
	 * 翻页查找所有数据
	 */
	public DataPage<FwApplyScope> getAllFwApplyScope(QueryPage queryPage){
		return queryPageData(queryPage);
	}
	
	@SuppressWarnings("unchecked")
	public List<FwApplyScope> findAllFwApplyScope() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	/**
	 * 持久化保存
	 */
	@Override
	public void persistent(FwApplyScope fwApplyScope) {
		super.persistent(fwApplyScope);
	}

	/**
	 * 根据Id找到业务模块应用配置
	 * @param id
	 * @return
	 */
	public FwApplyScope findById(Long id) {
		return super.getByKey(id);
	}
	
	/**
	 * 删除业务模块应用配置信息
	 */
	public void deleteFwApplyScope(FwApplyScope fwApplyScope) {
		super.delete(fwApplyScope);
	}

	public FwApplyScope getFwApplyScopeByName(String name) {
		String hql = "from FwApplyScope t where t.name=?";
		return (FwApplyScope)super.createQuery(hql).setString(0, name).uniqueResult();
	}
	/***
	 * 获取机构配置范围，用于设置用户机构属性的权限.
	 * @return
	 */
	//// @Cacheable("systemCache")
	@SuppressWarnings("unchecked")
	public List<FwApplyScope> getViewFwOrgApplyScope(){
		// 获取数据权限列表
		Query query = super.createQuery("from FwApplyScope t left join fetch t.fwBusinessMap a left join fetch t.fwBusinessMap.fwSourceEntity b left join fetch t.fwBusinessMap.fwSource c   where t.fwBusinessMap.fwSourceEntity.entityClass='ViewFwOrg'");
		return query.list();
	}

}
