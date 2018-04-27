package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureMiddlePayCertificateDetailDao;
import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificateDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureMiddlePayCertificateDetailDaoImpl
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

@Repository("BusiMeasureMiddlePayCertificateDetailDao")
public class  BusiMeasureMiddlePayCertificateDetailDaoImpl extends AbstractDao<Long,  BusiMeasureMiddlePayCertificateDetail> implements  BusiMeasureMiddlePayCertificateDetailDao {

    private Logger logger = LoggerFactory.getLogger(BusiMeasureMiddlePayCertificateDetailDaoImpl.class);

    @Override
    public BusiMeasureMiddlePayCertificateDetail findBusiMeasureMiddlePayCertificateDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureMiddlePayCertificateDetail( BusiMeasureMiddlePayCertificateDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureMiddlePayCertificateDetail(BusiMeasureMiddlePayCertificateDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureMiddlePayCertificateDetail(BusiMeasureMiddlePayCertificateDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureMiddlePayCertificateDetail> findAllBusiMeasureMiddlePayCertificateDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureMiddlePayCertificateDetail findBusiMeasureMiddlePayCertificateDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureMiddlePayCertificateDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureMiddlePayCertificateDetail> getAllBusiMeasureMiddlePayCertificateDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
