package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySecondLiningThicknessCheckDao;
import com.huatek.busi.model.quality.BusiQualitySecondLiningThicknessCheck;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualitySecondLiningThicknessCheckDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 16:53:04
  * @version: Windows 7
  */

@Repository("BusiQualitySecondLiningThicknessCheckDao")
public class  BusiQualitySecondLiningThicknessCheckDaoImpl extends AbstractDao<Long,  BusiQualitySecondLiningThicknessCheck> implements  BusiQualitySecondLiningThicknessCheckDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySecondLiningThicknessCheckDaoImpl.class);

    @Override
    public BusiQualitySecondLiningThicknessCheck findBusiQualitySecondLiningThicknessCheckById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySecondLiningThicknessCheck( BusiQualitySecondLiningThicknessCheck entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySecondLiningThicknessCheck(BusiQualitySecondLiningThicknessCheck entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySecondLiningThicknessCheck(BusiQualitySecondLiningThicknessCheck entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySecondLiningThicknessCheck> findAllBusiQualitySecondLiningThicknessCheck() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualitySecondLiningThicknessCheck findBusiQualitySecondLiningThicknessCheckByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualitySecondLiningThicknessCheck) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualitySecondLiningThicknessCheck> getAllBusiQualitySecondLiningThicknessCheck(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
