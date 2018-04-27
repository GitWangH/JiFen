package com.huatek.frame.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwOrg;

@Repository("FwOrgDao")
@SuppressWarnings("all")
public class FwOrgDaoImpl extends AbstractDao<Long, FwOrg> implements FwOrgDao {

	Logger logger = LoggerFactory.getLogger(FwOrgDaoImpl.class);

	public DataPage<FwOrg> getAllOrg(QueryPage queryPage) {
		return queryPageData(queryPage);
	}

	@SuppressWarnings("unchecked")
	public List<FwOrg> findAllOrg() {
		String hsql = " from FwOrg  order by id ";

		return this.createQuery(hsql).list();
	}

	/**
	 * 持久化保存
	 */
	@Override
	public void persistent(FwOrg fwOrg) {
		super.persistent(fwOrg);
		logger.debug("fwOrg id is @" + fwOrg.getId());
	}

	@Override
	public void batchSaveOrUpdate(List<FwOrg> listEntity) {
		super.batchSave(listEntity, 50);

	}

	@Override
	public void saveOrUpdate(FwOrg fwOrg) {
		super.saveOrUpdate(fwOrg);

	}

	/**
	 * 根据Id找到组织
	 * 
	 * @param id
	 * @return
	 */
	public FwOrg findById(Long id) {
		return super.getByKey(id);
	}

	/**
	 * 删除组织信息
	 */
	public void deleteFwOrg(FwOrg fwOrg) {
		super.delete(fwOrg);
	}

	@Override
	public List<FwOrg> findOrgLikeName(String name, Long tenantId,
			String orgStatus, Long orgId, Long currProId) {
		StringBuffer sql = new StringBuffer(" from FwOrg t where 1=1");
		if (name != null && !name.isEmpty()) {
			sql.append(" and t.name like :name ");
		}
		if (tenantId != null) {
			sql.append(" and t.tenantId =:tenantId ");
		}
		if (StringUtils.isNotBlank(orgStatus)) {
			sql.append(" and t.orgStatus =:orgStatus ");
		}
		if (null != orgId) {
			sql.append(" and (t.level1=:orgId or t.level2 = :orgId or t.level3 = :orgId or t.level4 = :orgId or t.level5 = :orgId or t.level6 = :orgId or t.level7 = :orgId) ");
		}
		if (null != currProId) {
			sql.append(" and t.level3 =:currProId ");
		}
		Query query = super.createQuery(sql.toString());
		if (name != null && !name.isEmpty()) {
			query.setString("name", "%" + name + "%");
		}
		if (tenantId != null) {
			query.setLong("tenantId", tenantId);
		}
		if (StringUtils.isNotBlank(orgStatus)) {
			query.setString("orgStatus", orgStatus);
		}
		if (null != orgId) {
			query.setLong("orgId", orgId);
		}
		if (null != currProId) {
			query.setLong("currProId", currProId);
		}
		query.setMaxResults(50);
		List<FwOrg> fwOrgList = query.list();
		return fwOrgList;
	}

	public List<FwOrg> findChildOrgByParentIdList(List<Long> parentIdList) {
		StringBuffer sql = new StringBuffer(" from FwOrg ");
		sql.append(" t where t.parent.id in  (:pList) ");
		Query query = super.createQuery(sql.toString());
		query.setParameterList("pList", parentIdList);
		List<FwOrg> fwOrgList = query.list();
		return fwOrgList;
	}

	@Override
	public List<Long> getOrgIdsByHsql(String hsql) {
		return this.createQueryUncheck(hsql).list();
	}

	@Override
	public List<FwOrg> findNextAndCurrentOrgLikeName(String name, Long orgId) {
		FwOrg org = this.findById(orgId);
		StringBuffer sql = new StringBuffer(" from FwOrg t where 1=1 ");
		if (org == null) {
			logger.error("组织不存在  id=" + orgId);
		} else {
			sql.append(" and t.level" + org.getOrgLevel() + "=?");
		}
		if (name != null && !name.isEmpty()) {
			sql.append(" and t.name like ? ");
		}
		Query query = super.createQuery(sql.toString());
		query.setLong(0, orgId);
		if (name != null && !name.isEmpty()) {
			query.setString(1, "%" + name + "%");
		}
		query.setMaxResults(50);
		List<FwOrg> fwOrgList = query.list();
		return fwOrgList;
	}

	@Override
	public List<FwOrg> getSubOrgLikeNameAndType(String name, String types,
			Long orgId, String orgStatus) {
		String sql = "from FwOrg  where  (level1=:orgId or level2 = :orgId or level3 = :orgId or level4 = :orgId or level5 = :orgId or level6 = :orgId or level7 = :orgId) ";
		sql += " and name like concat('%',:name,'%') and orgType in(:types) and orgStatus =:orgStatus";
		return this.createQuery(sql).setLong("orgId", orgId)
				.setString("name", name).setString("orgStatus", orgStatus)
				.setParameterList("types", types.split("\\|")).list();
	}

	@Override
	public List<FwOrg> getFwOrgByLevel3(Long orgId) {
		String hql = "from FwOrg where level3 =:orgId ";
		return this.createQuery(hql).setLong("orgId", orgId).list();
	}

	@Override
	public List<FwOrg> getFwOrgByOrgId(Long tenantId, Long orgId,
			String orgStatus) {
		FwOrg org = this.findById(orgId);
		StringBuffer sql = new StringBuffer(" from FwOrg t where 1=1 ");
		if (null == org) {
			logger.error("组织不存在  id=" + orgId);
		} else {
			sql.append(" and t.level" + org.getOrgLevel() + " =:orgId");
		}
		if (null != tenantId) {
			sql.append(" and t.tenantId =:tenantId");
		}
		if (StringUtils.isNotBlank(orgStatus)) {
			sql.append(" and t.orgStatus =:orgStatus");
		}
		Query query = super.createQuery(sql.toString());
		if (null != orgId) {
			query.setLong("orgId", orgId);
		}
		if (null != tenantId) {
			query.setLong("tenantId", tenantId);
		}
		if (StringUtils.isNotBlank(orgStatus)) {
			query.setString("orgStatus", orgStatus);
		}
		List<FwOrg> fwOrgList = query.list();
		return fwOrgList;
	}

	@Override
	public List<FwOrg> getFwOrgByNameOrShortName(Long id, String shortName,
			String name, Long parentId, Long tenantId) {
		StringBuffer hql = new StringBuffer(" from FwOrg t where 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and t.name =:name");
		}
		if (StringUtils.isNotBlank(shortName)) {
			hql.append(" and t.shortName =:shortName");
		}
		if (null != parentId) {
			hql.append(" and t.parent.id =:parentId");
		}
		if (null != tenantId) {
			hql.append(" and t.tenantId =:tenantId");
		}
		if (null != id) {
			hql.append(" and t.id <>:id");
		}
		Query query = super.createQuery(hql.toString());
		if (StringUtils.isNotBlank(name)) {
			query.setString("name", name);
		}
		if (StringUtils.isNotBlank(shortName)) {
			query.setString("shortName", shortName);
		}
		if (null != parentId) {
			query.setLong("parentId", parentId);
		}
		if (null != tenantId) {
			query.setLong("tenantId", tenantId);
		}
		if (null != id) {
			query.setLong("id", id);
		}
		return query.list();
	}

	@Override
	public List<FwOrg> findCurrAndChildOrgByParentId(Long orgId) {
		StringBuffer hql = new StringBuffer(
				" from FwOrg t where 1=1 and (level1=:orgId or level2 = :orgId or level3 = :orgId or level4 = :orgId or level5 = :orgId or level6 = :orgId or level7 = :orgId)");
		return this.createQueryUncheck(hql.toString()).setLong("orgId", orgId).list();
	}

	@Override
	public FwOrg getIsExistFwOrgByCode(Long id, String orgCode, Long tenantId) {
		Criteria criteria = createEntityCriteria();
		if (null != id) {
			criteria.add(Restrictions.ne("id", id));
		}
		if (StringUtils.isNotBlank(orgCode)) {
			criteria.add(Restrictions.eq("orgCode", orgCode));
		}
		if (null != tenantId) {
			criteria.add(Restrictions.eq("tenantId", tenantId));
		} else {
			return null;
		}

		return (FwOrg) criteria.uniqueResult();
	}

	@Override
	public List<FwOrg> getFwOrgByLevelAndOrgType(Long orgId, Integer orgLevel,
			Integer orgType) {
		StringBuffer hql = new StringBuffer(
				" from FwOrg t where t.orgType =:orgType ");
		hql.append(" and t.level" + orgLevel + " =:orgId and t.orgStatus ='2'");
		Query query = super.createQuery(hql.toString());
		query.setInteger("orgType", orgType);
		query.setLong("orgId", orgId);
		return query.list();
	}

	@Override
	public List<FwOrg> getAllOrgByTenantId(Long tenantId, String orgStatus) {
		if (null == tenantId) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" from FwOrg t where t.tenantId =:tenantId and t.orgStatus =:orgStatus");
		Query query = super.createQuery(hql.toString());
		query.setLong("tenantId", tenantId);
		query.setString("orgStatus", orgStatus);
		return query.list();
	}

	@Override
	public List<FwOrg> getAllOrg(Long tenantId) {
		StringBuffer hql = new StringBuffer(" from FwOrg t where 1=1 ");
		if (null != tenantId) {
			hql.append(" and t.tenantId =:tenantId");
		}
		Query query = super.createQuery(hql.toString());
		if (null != tenantId) {
			query.setLong("tenantId", tenantId);
		}
		return query.list();
	}

	@Override
	public List<FwOrg> getAllOrgByStatus(Long tenantId, String orgStatus) {
		StringBuffer hql = new StringBuffer(" from FwOrg t where 1=1 ");
		if (null != tenantId) {
			hql.append(" and t.tenantId =:tenantId");
		}
		if (StringUtils.isNotBlank(orgStatus)) {
			hql.append(" and t.orgStatus =:orgStatus");
		}
		Query query = super.createQuery(hql.toString());
		if (null != tenantId) {
			query.setLong("tenantId", tenantId);
		}
		if (StringUtils.isNotBlank(orgStatus)) {
			query.setString("orgStatus", orgStatus);
		}
		return query.list();
	}

	@Override
	public List<FwOrg> getSubOrgByParentId(Long parentId) {
		Criteria criteria = createEntityCriteria();
		if (null != parentId) {
			criteria.add(Restrictions.eq("parent.id", parentId));
		}
		return criteria.list();
	}

	@Override
	public List<FwOrg> getOrgByType(Long tenantId, String orgType) {
		Criteria criteria = createEntityCriteria();
		if (null != tenantId) {
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		criteria.add(Restrictions.eq("orgType", orgType));
		return criteria.list();
	}

}
