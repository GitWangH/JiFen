package com.huatek.busi.dao.impl.measure;
   
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.measure.BusiMeasureMiddleMeasureDetailBranchDetailDao;
import com.huatek.busi.model.measure.BusiMeasureMiddleMeasureDetailBranchDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiMeasureMiddleMeasureDetailBranchDetailDaoImpl
  * @Description: 中间计量分部分项明细Dao接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-12-05 10:47:27
  * @version: 1.0
  */
@Repository("BusiMeasureMiddleMeasureDetailBranchDetailDao")
public class  BusiMeasureMiddleMeasureDetailBranchDetailDaoImpl extends AbstractDao<Long,  BusiMeasureMiddleMeasureDetailBranchDetail> implements  BusiMeasureMiddleMeasureDetailBranchDetailDao {

    @Override
    public BusiMeasureMiddleMeasureDetailBranchDetail findBusiMeasureMiddleMeasureDetailBranchDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiMeasureMiddleMeasureDetailBranchDetail( BusiMeasureMiddleMeasureDetailBranchDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiMeasureMiddleMeasureDetailBranchDetail(BusiMeasureMiddleMeasureDetailBranchDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiMeasureMiddleMeasureDetailBranchDetail(BusiMeasureMiddleMeasureDetailBranchDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiMeasureMiddleMeasureDetailBranchDetail> findAllBusiMeasureMiddleMeasureDetailBranchDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiMeasureMiddleMeasureDetailBranchDetail findBusiMeasureMiddleMeasureDetailBranchDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiMeasureMiddleMeasureDetailBranchDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiMeasureMiddleMeasureDetailBranchDetail> getAllBusiMeasureMiddleMeasureDetailBranchDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
