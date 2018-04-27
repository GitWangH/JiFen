package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityEquipmentRegistrationDao;
import com.huatek.busi.model.quality.BusiQualityEquipmentRegistration;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityEquipmentRegistrationDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:52
  * @version: Windows 7
  */

@Repository("BusiQualityEquipmentRegistrationDao")
public class  BusiQualityEquipmentRegistrationDaoImpl extends AbstractDao<Long,  BusiQualityEquipmentRegistration> implements  BusiQualityEquipmentRegistrationDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityEquipmentRegistrationDaoImpl.class);

    @Override
    public BusiQualityEquipmentRegistration findBusiQualityEquipmentRegistrationById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityEquipmentRegistration( BusiQualityEquipmentRegistration entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityEquipmentRegistration(BusiQualityEquipmentRegistration entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityEquipmentRegistration(BusiQualityEquipmentRegistration entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityEquipmentRegistration> findAllBusiQualityEquipmentRegistration() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityEquipmentRegistration findBusiQualityEquipmentRegistrationByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityEquipmentRegistration) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityEquipmentRegistration> getAllBusiQualityEquipmentRegistration(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
