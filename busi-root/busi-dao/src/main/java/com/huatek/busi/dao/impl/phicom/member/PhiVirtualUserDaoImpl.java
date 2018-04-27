package com.huatek.busi.dao.impl.phicom.member;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.member.PhiVirtualUserDao;
import com.huatek.busi.model.phicom.member.PhiVirtualUser;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiVirtualUserDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-22 12:58:58
  * @version: 1.0
  */

@Repository("PhiVirtualUserDao")
public class  PhiVirtualUserDaoImpl extends AbstractDao<Long,  PhiVirtualUser> implements  PhiVirtualUserDao {

    private Logger logger = LoggerFactory.getLogger(PhiVirtualUserDaoImpl.class);

    @Override
    public PhiVirtualUser findPhiVirtualUserById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiVirtualUser( PhiVirtualUser entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiVirtualUser(PhiVirtualUser entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiVirtualUser(PhiVirtualUser entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiVirtualUser> findAllPhiVirtualUser() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiVirtualUser findPhiVirtualUserByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiVirtualUser) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiVirtualUser> getAllPhiVirtualUser(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
