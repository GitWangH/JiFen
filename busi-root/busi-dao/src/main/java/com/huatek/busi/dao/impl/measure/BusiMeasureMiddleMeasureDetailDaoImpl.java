package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureMiddleMeasureDetailDao;
import com.huatek.busi.model.measure.BusiMeasureMiddleMeasureDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureMiddleMeasureDetailDaoImpl
  * @Description: 中间计量明细Dao接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:47:13
  * @version: 1.0
  */
@Repository("BusiMeasureMiddleMeasureDetailDao")
public class  BusiMeasureMiddleMeasureDetailDaoImpl extends AbstractDao<Long,  BusiMeasureMiddleMeasureDetail> implements  BusiMeasureMiddleMeasureDetailDao {

    @Override
    public BusiMeasureMiddleMeasureDetail findBusiMeasureMiddleMeasureDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureMiddleMeasureDetail( BusiMeasureMiddleMeasureDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureMiddleMeasureDetail(BusiMeasureMiddleMeasureDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureMiddleMeasureDetail(BusiMeasureMiddleMeasureDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureMiddleMeasureDetail> findAllBusiMeasureMiddleMeasureDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureMiddleMeasureDetail findBusiMeasureMiddleMeasureDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureMiddleMeasureDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureMiddleMeasureDetail> getAllBusiMeasureMiddleMeasureDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
