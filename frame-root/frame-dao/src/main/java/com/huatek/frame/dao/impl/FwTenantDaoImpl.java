package com.huatek.frame.dao.impl;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwTenantDao;
import com.huatek.frame.model.FwTenant;


 /**
  * @ClassName: FwTenantDaoImpl
  * @Description: 
  * @author: Kaka Xiao
  * @Email : 
  * @date: 2017-10-16 17:42:36
  * @version: Windows 10
  */

@Repository("FwTenantDao")
public class  FwTenantDaoImpl extends AbstractDao<Long,  FwTenant> implements  FwTenantDao {

    private Logger logger = LoggerFactory.getLogger(FwTenantDaoImpl.class);

    @Override
    public FwTenant findFwTenantById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateFwTenant( FwTenant entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentFwTenant(FwTenant entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteFwTenant(FwTenant entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FwTenant> findAllFwTenant() {
        return createEntityCriteria().list();
    }

    @Override
    public FwTenant findFwTenantByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (FwTenant) criteria.uniqueResult();
    }

    @Override
    public DataPage<FwTenant> getAllFwTenant(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
