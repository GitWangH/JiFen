package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityQuickProcessingDaoImpl
  * @Description: 快捷处理service实现
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-10-26 09:36:43
  * @version: Windows 7
  */

@Repository("BusiQualityQuickProcessingDao")
public class  BusiQualityQuickProcessingDaoImpl extends AbstractDao<Long,  BusiQualityQuickProcessing> implements  BusiQualityQuickProcessingDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityQuickProcessingDaoImpl.class);

    @Override
    public BusiQualityQuickProcessing findBusiQualityQuickProcessingById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityQuickProcessing( BusiQualityQuickProcessing entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityQuickProcessing(BusiQualityQuickProcessing entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityQuickProcessing(BusiQualityQuickProcessing entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityQuickProcessing> findAllBusiQualityQuickProcessing() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityQuickProcessing findBusiQualityQuickProcessingByCondition(String field,String condition) {
        Criteria criteria = createEntityCriteria();
        if("flowInstanceId".equals(field)){
        	criteria.add(Restrictions.eq(field, Long.valueOf(condition)));
        }else{
        	criteria.add(Restrictions.eq(field, condition));
        }
        return (BusiQualityQuickProcessing) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityQuickProcessing> getAllBusiQualityQuickProcessing(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
