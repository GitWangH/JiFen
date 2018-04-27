package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureMiddleMeasureDao;
import com.huatek.busi.model.measure.BusiMeasureMiddleMeasure;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureMiddleMeasureDaoImpl
  * @Description: 中间计量Dao接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:46:45
  * @version: 1.0
  */
@Repository("BusiMeasureMiddleMeasureDao")
public class  BusiMeasureMiddleMeasureDaoImpl extends AbstractDao<Long,  BusiMeasureMiddleMeasure> implements  BusiMeasureMiddleMeasureDao {

    @Override
    public BusiMeasureMiddleMeasure findBusiMeasureMiddleMeasureById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureMiddleMeasure( BusiMeasureMiddleMeasure entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureMiddleMeasure(BusiMeasureMiddleMeasure entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureMiddleMeasure(BusiMeasureMiddleMeasure entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureMiddleMeasure> findAllBusiMeasureMiddleMeasure() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureMiddleMeasure findBusiMeasureMiddleMeasureByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureMiddleMeasure) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureMiddleMeasure> getAllBusiMeasureMiddleMeasure(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
