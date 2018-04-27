package com.huatek.file.dao;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.file.modal.CmdFileCatalogManager;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: CmdFileCatalogDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-11-07 10:02:42
  * @version: Windows 7
  */

@Repository("CmdFileCatalogDao")
public class  CmdFileCatalogDaoImpl extends AbstractDao<Long,  CmdFileCatalogManager> implements  CmdFileCatalogDao {

    private Logger logger = LoggerFactory.getLogger(CmdFileCatalogDaoImpl.class);

    @Override
    public CmdFileCatalogManager findCmdFileCatalogById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateCmdFileCatalog( CmdFileCatalogManager entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentCmdFileCatalog(CmdFileCatalogManager entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteCmdFileCatalog(CmdFileCatalogManager entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CmdFileCatalogManager> findAllCmdFileCatalog() {
        return createEntityCriteria().list();
    }

    @Override
    public CmdFileCatalogManager findCmdFileCatalogByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (CmdFileCatalogManager) criteria.uniqueResult();
    }

    @Override
    public DataPage<CmdFileCatalogManager> getAllCmdFileCatalog(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<CmdFileCatalogManager> getAllUserCategory(Long tenantId) {
		StringBuffer hql = new StringBuffer(" from CmdFileCatalog where 1=1 ");
		if(null == tenantId){
			hql.append(" and tenantId is null ");
		}else {
			hql.append(" and (tenantId is null or tenantId =:tenantId)");
		}
		Query query = super.createQuery(hql.toString());
		if(null != tenantId){
			query.setLong("tenantId", tenantId);
		}
		return query.list();
	}

	@Override
	public CmdFileCatalogManager getCmdFileCatalogByConditionAndParent(String filed, String condition,
			Long parentId, Long tenantId, Long id) {
		Criteria criteria = createCriteriaUncheck();
		if (id!=null) {
			criteria.add(Restrictions.ne("id", id));
		}
		criteria.add(Restrictions.eq(filed, condition));
		if(null != parentId){
			criteria.add(Restrictions.eq("parent.id", parentId));
		}
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		return (CmdFileCatalogManager) criteria.uniqueResult() ;
	}

	@Override
	public List<CmdFileCatalogManager> getCmdFileCatalogLikePath(String oldPath,
			Long tenantId) {
		Criteria criteria = createCriteriaUncheck();
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		criteria.add(Restrictions.like("path", oldPath+"%"));
		return criteria.list();
	}

	@Override
	public List<CmdFileCatalogManager> getCmdFileCatalogs(Long[] ids) {
		Criteria criteria = createCriteriaUncheck();
		if(null != ids){
			criteria.add(Restrictions.in("id", ids));
		}else {
			return null;
		}
		return criteria.list();
	}

	@Override
	public List<CmdFileCatalogManager> getChildrens(String path, Long tenantId) {
		Criteria criteria = createCriteriaUncheck();
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		criteria.add(Restrictions.like("path", path+"%"));
		return criteria.list();
	}

	@Override
	public void batchDelete(List<CmdFileCatalogManager> categorys) {
		if(null != categorys && !categorys.isEmpty()){
			super.batchDelete(categorys, 50);
		}
		
	}

	@Override
	public List<CmdFileCatalogManager> getCmdFileCatalogByParent(Long parentId) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("parent.id", parentId));
		return criteria.list();
	}

	@Override
	public CmdFileCatalogManager getCmdFileCatalogByPath(String path, Long tenantId) {
		/*Criteria criteria = createCriteriaUncheck();
		if(null != tenantId){
			criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		criteria.add(Restrictions.eq("path", path));
		return (CmdFileCatalog) criteria.uniqueResult();*/
		StringBuffer hql = new StringBuffer(" from CmdFileCatalogManager t where 1=1 ");
		if(null != tenantId){
			hql.append(" and (t.tenantId =:tenantId or t.tenantId is null)");
		}
		hql.append(" and t.path=:path");
		Query query = super.createQuery(hql.toString());
		if(null != tenantId){
			query.setLong("tenantId", tenantId);
		}
		query.setString("path", path);
		return (CmdFileCatalogManager) query.uniqueResult();
	}

}
