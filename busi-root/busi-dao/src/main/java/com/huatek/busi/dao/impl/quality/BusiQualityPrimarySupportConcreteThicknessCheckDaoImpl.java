package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportConcreteThicknessCheckDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportConcreteThicknessCheck;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPrimarySupportConcreteThicknessCheckDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-07 15:57:43
  * @version: Windows 7
  */

@Repository("BusiQualityPrimarySupportConcreteThicknessCheckDao")
public class  BusiQualityPrimarySupportConcreteThicknessCheckDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportConcreteThicknessCheck> implements  BusiQualityPrimarySupportConcreteThicknessCheckDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportConcreteThicknessCheckDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportConcreteThicknessCheck findBusiQualityPrimarySupportConcreteThicknessCheckById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportConcreteThicknessCheck( BusiQualityPrimarySupportConcreteThicknessCheck entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportConcreteThicknessCheck(BusiQualityPrimarySupportConcreteThicknessCheck entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportConcreteThicknessCheck(BusiQualityPrimarySupportConcreteThicknessCheck entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportConcreteThicknessCheck> findAllBusiQualityPrimarySupportConcreteThicknessCheck() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportConcreteThicknessCheck findBusiQualityPrimarySupportConcreteThicknessCheckByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportConcreteThicknessCheck) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportConcreteThicknessCheck> getAllBusiQualityPrimarySupportConcreteThicknessCheck(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
