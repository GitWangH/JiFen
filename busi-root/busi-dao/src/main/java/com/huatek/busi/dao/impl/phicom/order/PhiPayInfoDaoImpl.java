package com.huatek.busi.dao.impl.phicom.order;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.order.PhiPayInfoDao;
import com.huatek.busi.model.phicom.order.PhiPayInfo;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiPayInfoDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-26 11:09:32
  * @version: 1.0
  */

@Repository("PhiPayInfoDao")
public class  PhiPayInfoDaoImpl extends AbstractDao<Long,  PhiPayInfo> implements  PhiPayInfoDao {

    private Logger logger = LoggerFactory.getLogger(PhiPayInfoDaoImpl.class);

    @Override
    public PhiPayInfo findPhiPayInfoById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPayInfo( PhiPayInfo entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPayInfo(PhiPayInfo entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPayInfo(PhiPayInfo entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPayInfo> findAllPhiPayInfo() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPayInfo findPhiPayInfoByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPayInfo) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPayInfo> getAllPhiPayInfo(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
