package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureMiddlePayCertificateTemplateDao;
import com.huatek.busi.model.measure.BusiMeasureMiddlePayCertificateTemplate;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureMiddlePayCertificateTemplateDaoImpl
  * @Description: 
  * @author: Administrator
  * @Email : 
  * @date: 2017-12-08 10:03:21
  * @version: Windows 10
  */

@Repository("BusiMeasureMiddlePayCertificateTemplateDao")
public class  BusiMeasureMiddlePayCertificateTemplateDaoImpl extends AbstractDao<Long,  BusiMeasureMiddlePayCertificateTemplate> implements  BusiMeasureMiddlePayCertificateTemplateDao {

    private Logger logger = LoggerFactory.getLogger(BusiMeasureMiddlePayCertificateTemplateDaoImpl.class);

    @Override
    public BusiMeasureMiddlePayCertificateTemplate findBusiMeasureMiddlePayCertificateTemplateById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureMiddlePayCertificateTemplate( BusiMeasureMiddlePayCertificateTemplate entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureMiddlePayCertificateTemplate(BusiMeasureMiddlePayCertificateTemplate entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureMiddlePayCertificateTemplate(BusiMeasureMiddlePayCertificateTemplate entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureMiddlePayCertificateTemplate> findAllBusiMeasureMiddlePayCertificateTemplate(Long orgId) {
    	Criteria criteria = createEntityCriteria();
    	criteria.add(Restrictions.eq("orgId", orgId));
    	return criteria.list();
    }

    @Override
    public BusiMeasureMiddlePayCertificateTemplate findBusiMeasureMiddlePayCertificateTemplateByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureMiddlePayCertificateTemplate) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureMiddlePayCertificateTemplate> getAllBusiMeasureMiddlePayCertificateTemplate(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
