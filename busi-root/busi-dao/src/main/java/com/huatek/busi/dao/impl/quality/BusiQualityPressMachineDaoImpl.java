package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityPressMachineDao;
import com.huatek.busi.model.quality.BusiQualityPressMachine;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityPressMachineDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:16:43
  * @version: Windows 7
  */

@Repository("BusiQualityPressMachineDao")
public class  BusiQualityPressMachineDaoImpl extends AbstractDao<Long,  BusiQualityPressMachine> implements  BusiQualityPressMachineDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityPressMachineDaoImpl.class);

    @Override
    public BusiQualityPressMachine findBusiQualityPressMachineById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityPressMachine( BusiQualityPressMachine entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityPressMachine(BusiQualityPressMachine entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityPressMachine(BusiQualityPressMachine entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityPressMachine> findAllBusiQualityPressMachine() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityPressMachine findBusiQualityPressMachineByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityPressMachine) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityPressMachine> getAllBusiQualityPressMachine(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public BusiQualityPressMachine findBusiQualityPressMachineByUkey(String ukey) {
    	String hql = "from BusiQualityPressMachine i where i.ukey = :ukey";
    	Query query = super.createQuery(hql);
    	query.setString("ukey", ukey);
    	BusiQualityPressMachine entity = (BusiQualityPressMachine)query.uniqueResult();
    	return entity;
    }

}
