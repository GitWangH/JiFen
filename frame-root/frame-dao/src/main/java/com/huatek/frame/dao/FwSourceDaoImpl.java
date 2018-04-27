package com.huatek.frame.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwSource;

/**
 * @Description: 资源(菜单menu)dao实现类
 * @author caojun1@hisense.com
 * @date 2016年1月13日 下午1:18:50
 * @version V1.0
 */
@Repository("fwSourceDao")
@Transactional
public class FwSourceDaoImpl extends AbstractDao<Long, FwSource> implements FwSourceDao {
	Logger logger = LoggerFactory.getLogger(FwSourceDaoImpl.class);

	public FwSource findById(Long id) {
		return super.getByKey(id);
	}

	public void persistent(FwSource fwMenu) {
		super.persistent(fwMenu);
		logger.debug("fwMenu id is @" + fwMenu.getId());
	}

	public void deleteMenu(FwSource fwMenu) {
		super.delete(fwMenu);
	}
	/***
	 * 删除当前菜单以及所有下级菜单.
	 * @param menuId 菜单Id.
	 */
	public void deleteMenuAndSubMenu(long menuId){
		Query query = super.createQuery("delete from FwSource t where t.id=? ");
		query.setLong(0, menuId);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<FwSource> getSystemSource(Long systemId, Long userId){
		String hsql = "select s from FwSource s, FwRoleSource t, FwAccountRole r"+
				" where s.id=t.fwSource.id and t.fwRole = r.fwRole and r.fwAccount.id = ? and s.system.id = ?";
		Query query = super.createQuery(hsql);
		query.setLong(0, userId);
		query.setLong(1, systemId);
		return query.list();
	}
	/***
	 * 获取用户菜单.
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FwSource> getAllSource(int isMenu, Long userId){
		String hsql = "select  DISTINCT s from FwSource s, FwRoleSource t, FwAccountRole r"+
				" where s.id=t.fwSource.id and t.fwRole = r.fwRole ";
		if(isMenu == 1){
			hsql += " and s.isMenu=1";
		}
		if(userId != null){
			hsql +=" and r.fwAccount.id = ?";
		}
		Query query = super.createQuery(hsql+" order by s.displayOrder ");
		if(userId != null){
			query.setLong(0, userId);
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<FwSource> getAllSourceByRoleCode(List<String> roleCode){
		String hsql = "select  DISTINCT s from FwSource s, FwRoleSource t, FwRole r"+
				" where s.id=t.fwSource.id and t.fwRole = r and  r.rolecode in (:rolecode) ";
		Query query = super.createQueryUncheck(hsql+" order by s.displayOrder asc");
		if(roleCode != null){
			query.setParameterList("rolecode", roleCode);
		}
		return query.list();
	}
	/***
	 * 获取指定系统的用户菜单.
	 * @param systemId 系统Id
	 * @param userId 用户Id
	 * @return
	 */
	
	
	

	@SuppressWarnings("unchecked")
	public List<FwSource> findAllSource() {
		
		Criteria criteria = createEntityCriteria();

		criteria.addOrder(Order.asc("orgLevel"));
		criteria.addOrder(Order.asc("level1"));
		criteria.addOrder(Order.asc("level2"));
		criteria.addOrder(Order.asc("level3"));
		criteria.addOrder(Order.asc("level4"));
		criteria.addOrder(Order.asc("level5"));
		criteria.addOrder(Order.asc("level6"));
		criteria.addOrder(Order.asc("level7"));
		criteria.addOrder(Order.asc("level8"));
		criteria.addOrder(Order.asc("level9"));
		criteria.addOrder(Order.asc("level10"));
		return criteria.list();
	}
	
	public DataPage<FwSource> getAllMenu(QueryPage queryPage) {
		return queryPageData(queryPage);
	}
	public List<FwSource> findMenuByUrl(String url) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("sourceUrl", url));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<FwSource> findAllMenu() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("isMenu", 1));
		
		criteria.addOrder(Order.asc("displayOrder"));
		
		return criteria.list();
	}
	
	/**
	 * 查找被业务模块使用的菜单信息
	 * @return
	 */
	public DataPage<FwSource> findFwSourceInBusinessMap(QueryPage queryPage) {
		queryPage.setSqlCondition(" exists (select 1 from FwBusinessMap m where m.fwSourceObject.id = t.id) ");
		return queryPageDataForT(queryPage);
	}

	@Override
	public List<FwSource> getAllSourceByRoleCode(List<String> roleCodes,
			Long tenantId) {
		String hsql = "";
		if(null != tenantId){
			hsql = "select  DISTINCT s from FwSource s, FwRoleSource t, FwRole r"+
					" where s.id=t.fwSource.id and t.fwRole = r and  r.rolecode in (:rolecode) and r.tenantId = :tenantId ";
		}else {
			hsql = "select  DISTINCT s from FwSource s, FwRoleSource t, FwRole r"+
					" where s.id=t.fwSource.id and t.fwRole = r and  r.rolecode in (:rolecode) ";
		}
		Query query = super.createQueryUncheck(hsql+" order by s.displayOrder asc");
		if(roleCodes != null){
			query.setParameterList("rolecode", roleCodes);
		}
		if(null != tenantId){
			query.setLong("tenantId", tenantId);
		}
		return query.list();
	}

	@Override
	public List<FwSource> getAll() {
		return super.find("from FwSource ");
	}

}