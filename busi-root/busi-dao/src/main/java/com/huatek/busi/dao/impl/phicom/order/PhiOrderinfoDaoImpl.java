package com.huatek.busi.dao.impl.phicom.order;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.order.PhiOrderinfoDao;
import com.huatek.busi.model.phicom.order.PhiOrderinfo;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiOrderinfoDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-29 17:33:32
  * @version: 1.0
  */

@Repository("PhiOrderinfoDao")
public class  PhiOrderinfoDaoImpl extends AbstractDao<Long,  PhiOrderinfo> implements  PhiOrderinfoDao {

    private Logger logger = LoggerFactory.getLogger(PhiOrderinfoDaoImpl.class);

    @Override
    public PhiOrderinfo findPhiOrderinfoById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiOrderinfo( PhiOrderinfo entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiOrderinfo(PhiOrderinfo entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiOrderinfo(PhiOrderinfo entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiOrderinfo> findAllPhiOrderinfo() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiOrderinfo findPhiOrderinfoByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiOrderinfo) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiOrderinfo> getAllPhiOrderinfo(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
