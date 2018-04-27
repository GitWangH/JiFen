package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureMiddlePayCertificateDao;
import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificate;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureMiddlePayCertificateDaoImpl
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

@Repository("BusiMeasureMiddlePayCertificateDao")
public class  BusiMeasureMiddlePayCertificateDaoImpl extends AbstractDao<Long,  BusiMeasureMiddlePayCertificate> implements  BusiMeasureMiddlePayCertificateDao {

    private Logger logger = LoggerFactory.getLogger(BusiMeasureMiddlePayCertificateDaoImpl.class);

    @Override
    public BusiMeasureMiddlePayCertificate findBusiMeasureMiddlePayCertificateById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureMiddlePayCertificate( BusiMeasureMiddlePayCertificate entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureMiddlePayCertificate(BusiMeasureMiddlePayCertificate entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureMiddlePayCertificate(BusiMeasureMiddlePayCertificate entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureMiddlePayCertificate> findAllBusiMeasureMiddlePayCertificate() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureMiddlePayCertificate findBusiMeasureMiddlePayCertificateByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureMiddlePayCertificate) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureMiddlePayCertificate> getAllBusiMeasureMiddlePayCertificate(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
