package com.huatek.frame.dao.impl;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwDefaultProjectDao;
import com.huatek.frame.model.FwDefaultProject;


 /**
  * @ClassName: FwDefaultProjectDaoImpl
  * @Description: 
  * @author: cloud_liu
  * @Email : 
  * @date: 2017-10-25 14:43:49
  * @version: Windows 7
  */

@Repository("FwDefaultProjectDao")
public class  FwDefaultProjectDaoImpl extends AbstractDao<Long,  FwDefaultProject> implements  FwDefaultProjectDao {

    private Logger logger = LoggerFactory.getLogger(FwDefaultProjectDaoImpl.class);

    @Override
    public FwDefaultProject findFwDefaultProjectById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateFwDefaultProject( FwDefaultProject entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentFwDefaultProject(FwDefaultProject entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteFwDefaultProject(FwDefaultProject entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FwDefaultProject> findAllFwDefaultProject() {
        return createEntityCriteria().list();
    }

    @Override
    public FwDefaultProject findFwDefaultProjectByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (FwDefaultProject) criteria.uniqueResult();
    }

    @Override
    public DataPage<FwDefaultProject> getAllFwDefaultProject(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public FwDefaultProject getFwDefaultProjectByAcctId(Long id) {
		String hql = "from FwDefaultProject where acctId =:acctId ";
		return (FwDefaultProject) super.createQuery(hql).setLong("acctId", id).uniqueResult();
	}

}
