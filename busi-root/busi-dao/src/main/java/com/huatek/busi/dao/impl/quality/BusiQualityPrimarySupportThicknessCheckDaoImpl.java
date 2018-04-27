package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPrimarySupportThicknessCheckDao;
import com.huatek.busi.model.quality.BusiQualityPrimarySupportThicknessCheck;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPrimarySupportThicknessCheckDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-06 20:25:47
  * @version: Windows 7
  */

@Repository("BusiQualityPrimarySupportThicknessCheckDao")
public class  BusiQualityPrimarySupportThicknessCheckDaoImpl extends AbstractDao<Long,  BusiQualityPrimarySupportThicknessCheck> implements  BusiQualityPrimarySupportThicknessCheckDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPrimarySupportThicknessCheckDaoImpl.class);

    @Override
    public BusiQualityPrimarySupportThicknessCheck findBusiQualityPrimarySupportThicknessCheckById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPrimarySupportThicknessCheck( BusiQualityPrimarySupportThicknessCheck entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPrimarySupportThicknessCheck(BusiQualityPrimarySupportThicknessCheck entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPrimarySupportThicknessCheck(BusiQualityPrimarySupportThicknessCheck entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPrimarySupportThicknessCheck> findAllBusiQualityPrimarySupportThicknessCheck() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPrimarySupportThicknessCheck findBusiQualityPrimarySupportThicknessCheckByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPrimarySupportThicknessCheck) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPrimarySupportThicknessCheck> getAllBusiQualityPrimarySupportThicknessCheck(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
