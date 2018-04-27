package com.huatek.busi.dao.impl.phicom.plusmember;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightDetailsDao;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightDetails;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiPlusRightDetailsDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 14:45:06
  * @version: 1.0
  */

@Repository("PhiPlusRightDetailsDao")
public class  PhiPlusRightDetailsDaoImpl extends AbstractDao<Long,  PhiPlusRightDetails> implements  PhiPlusRightDetailsDao {

    private Logger logger = LoggerFactory.getLogger(PhiPlusRightDetailsDaoImpl.class);

    @Override
    public PhiPlusRightDetails findPhiPlusRightDetailsById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPlusRightDetails( PhiPlusRightDetails entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPlusRightDetails(PhiPlusRightDetails entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPlusRightDetails(PhiPlusRightDetails entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPlusRightDetails> findAllPhiPlusRightDetails() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPlusRightDetails findPhiPlusRightDetailsByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPlusRightDetails) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPlusRightDetails> getAllPhiPlusRightDetails(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
