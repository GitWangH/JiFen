package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityUniversalMachineDao;
import com.huatek.busi.model.quality.BusiQualityUniversalMachine;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityUniversalMachineDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-10-30 14:16:43
  * @version: Windows 7
  */

@Repository("BusiQualityUniversalMachineDao")
public class  BusiQualityUniversalMachineDaoImpl extends AbstractDao<Long,  BusiQualityUniversalMachine> implements  BusiQualityUniversalMachineDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityUniversalMachineDaoImpl.class);

    @Override
    public BusiQualityUniversalMachine findBusiQualityUniversalMachineById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityUniversalMachine( BusiQualityUniversalMachine entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityUniversalMachine(BusiQualityUniversalMachine entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityUniversalMachine(BusiQualityUniversalMachine entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityUniversalMachine> findAllBusiQualityUniversalMachine() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityUniversalMachine findBusiQualityUniversalMachineByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityUniversalMachine) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityUniversalMachine> getAllBusiQualityUniversalMachine(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public BusiQualityUniversalMachine findBusiQualityUniversalMachineByUkey(String ukey) {
    	String hql = "from BusiQualityUniversalMachine i where i.ukey = :ukey";
    	Query query = super.createQuery(hql);
    	query.setString("ukey", ukey);
    	BusiQualityUniversalMachine entity = (BusiQualityUniversalMachine)query.uniqueResult();
    	return entity;
    }

}
