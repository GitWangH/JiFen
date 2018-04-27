package com.huatek.busi.dao.impl.phicom.order;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.order.PhiMyproductsDao;
import com.huatek.busi.model.phicom.order.PhiMyproducts;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiMyproductsDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-27 13:37:19
  * @version: 1.0
  */

@Repository("PhiMyproductsDao")
public class  PhiMyproductsDaoImpl extends AbstractDao<Long,  PhiMyproducts> implements  PhiMyproductsDao {

    private Logger logger = LoggerFactory.getLogger(PhiMyproductsDaoImpl.class);

    @Override
    public PhiMyproducts findPhiMyproductsById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiMyproducts( PhiMyproducts entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiMyproducts(PhiMyproducts entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiMyproducts(PhiMyproducts entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiMyproducts> findAllPhiMyproducts() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiMyproducts findPhiMyproductsByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiMyproducts) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiMyproducts> getAllPhiMyproducts(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
