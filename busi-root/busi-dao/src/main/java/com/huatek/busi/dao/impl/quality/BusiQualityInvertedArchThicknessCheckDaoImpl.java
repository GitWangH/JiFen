package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityInvertedArchThicknessCheckDao;
import com.huatek.busi.model.quality.BusiQualityInvertedArchThicknessCheck;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityInvertedArchThicknessCheckDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 19:28:08
  * @version: Windows 7
  */

@Repository("BusiQualityInvertedArchThicknessCheckDao")
public class  BusiQualityInvertedArchThicknessCheckDaoImpl extends AbstractDao<Long,  BusiQualityInvertedArchThicknessCheck> implements  BusiQualityInvertedArchThicknessCheckDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityInvertedArchThicknessCheckDaoImpl.class);

    @Override
    public BusiQualityInvertedArchThicknessCheck findBusiQualityInvertedArchThicknessCheckById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityInvertedArchThicknessCheck( BusiQualityInvertedArchThicknessCheck entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityInvertedArchThicknessCheck(BusiQualityInvertedArchThicknessCheck entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityInvertedArchThicknessCheck(BusiQualityInvertedArchThicknessCheck entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityInvertedArchThicknessCheck> findAllBusiQualityInvertedArchThicknessCheck() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityInvertedArchThicknessCheck findBusiQualityInvertedArchThicknessCheckByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityInvertedArchThicknessCheck) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityInvertedArchThicknessCheck> getAllBusiQualityInvertedArchThicknessCheck(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
