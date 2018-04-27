package com.huatek.busi.dao.impl.base;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseImageListSubConnectionTableDao;
import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTable;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * 形象清单和分部分项挂接表
  * @ClassName: BusiBaseImageListSubConnectionTableDaoImpl
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:25:13
  * @version: Windows 7
  */

@Repository("BusiBaseImageListSubConnectionTableDao")
public class  BusiBaseImageListSubConnectionTableDaoImpl extends AbstractDao<Long,  BusiBaseImageListSubConnectionTable> implements  BusiBaseImageListSubConnectionTableDao {

    private Logger logger = LoggerFactory.getLogger(BusiBaseImageListSubConnectionTableDaoImpl.class);

    @Override
    public BusiBaseImageListSubConnectionTable findBusiBaseImageListSubConnectionTableById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiBaseImageListSubConnectionTable( BusiBaseImageListSubConnectionTable entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiBaseImageListSubConnectionTable(BusiBaseImageListSubConnectionTable entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiBaseImageListSubConnectionTable(BusiBaseImageListSubConnectionTable entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiBaseImageListSubConnectionTable> findAllBusiBaseImageListSubConnectionTable() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiBaseImageListSubConnectionTable findBusiBaseImageListSubConnectionTableByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiBaseImageListSubConnectionTable) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiBaseImageListSubConnectionTable> getAllBusiBaseImageListSubConnectionTable(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public List<BusiBaseImageListSubConnectionTable> getBusiBaseImageListSubConnectionTableBySelectedId(List<Long> selectedIdList) {
    	StringBuilder hql = new StringBuilder(" FROM BusiBaseImageListSubConnectionTable i where 1 = 1 ");
    	hql.append(" AND i.id in (:ids) ");
    	Query query = super.createQuery(hql.toString());
    	query.setParameterList("ids", selectedIdList);
    	return query.list();
    }
    
    @Override
    public void batchDelete(List<BusiBaseImageListSubConnectionTable> entityList, int count) {
    	super.batchDelete(entityList, count);
    }
}
