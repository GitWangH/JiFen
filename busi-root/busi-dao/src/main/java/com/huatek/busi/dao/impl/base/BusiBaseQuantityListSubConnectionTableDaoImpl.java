package com.huatek.busi.dao.impl.base;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseQuantityListSubConnectionTableDao;
import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTable;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiBaseQuantityListSubConnectionTableDaoImpl
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:25:14
  * @version: Windows 7
  */

@Repository("BusiBaseQuantityListSubConnectionTableDao")
public class  BusiBaseQuantityListSubConnectionTableDaoImpl extends AbstractDao<Long,  BusiBaseQuantityListSubConnectionTable> implements  BusiBaseQuantityListSubConnectionTableDao {

    private Logger logger = LoggerFactory.getLogger(BusiBaseQuantityListSubConnectionTableDaoImpl.class);

    @Override
    public BusiBaseQuantityListSubConnectionTable findBusiBaseQuantityListSubConnectionTableById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiBaseQuantityListSubConnectionTable( BusiBaseQuantityListSubConnectionTable entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiBaseQuantityListSubConnectionTable(BusiBaseQuantityListSubConnectionTable entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiBaseQuantityListSubConnectionTable(BusiBaseQuantityListSubConnectionTable entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiBaseQuantityListSubConnectionTable> findAllBusiBaseQuantityListSubConnectionTable() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiBaseQuantityListSubConnectionTable findBusiBaseQuantityListSubConnectionTableByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiBaseQuantityListSubConnectionTable) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiBaseQuantityListSubConnectionTable> getAllBusiBaseQuantityListSubConnectionTable(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public void batchDelete(List<BusiBaseQuantityListSubConnectionTable> entityList, int count) {
    	// TODO Auto-generated method stub
    	super.batchDelete(entityList, count);
    }
    
    @Override
    public List<BusiBaseQuantityListSubConnectionTable> getBusiBaseQuantityListSubConnectionTableListBySelectedId(List<Long> selectedIdList) {
    	StringBuilder hql = new StringBuilder(" FROM BusiBaseQuantityListSubConnectionTable i where 1 = 1 ");
    	hql.append(" AND i.id in (:ids) ");
    	Query query = super.createQuery(hql.toString());
    	query.setParameterList("ids", selectedIdList);
    	return query.list();
    }

}
