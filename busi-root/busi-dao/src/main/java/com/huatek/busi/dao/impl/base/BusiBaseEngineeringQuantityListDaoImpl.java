package com.huatek.busi.dao.impl.base;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseEngineeringQuantityListDao;
import com.huatek.busi.model.base.BusiBaseEngineeringQuantityList;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * 工程量清单
  * @ClassName: BusiBaseEngineeringQuantityListDaoImpl
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:21:41
  * @version: Windows 7
  */

@Repository("BusiBaseEngineeringQuantityListDao")
public class  BusiBaseEngineeringQuantityListDaoImpl extends AbstractDao<Long,  BusiBaseEngineeringQuantityList> implements  BusiBaseEngineeringQuantityListDao {

    private Logger logger = LoggerFactory.getLogger(BusiBaseEngineeringQuantityListDaoImpl.class);

    @Override
    public BusiBaseEngineeringQuantityList findBusiBaseEngineeringQuantityListById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiBaseEngineeringQuantityList( BusiBaseEngineeringQuantityList entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiBaseEngineeringQuantityList(BusiBaseEngineeringQuantityList entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiBaseEngineeringQuantityList(BusiBaseEngineeringQuantityList entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiBaseEngineeringQuantityList> findAllBusiBaseEngineeringQuantityList() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiBaseEngineeringQuantityList findBusiBaseEngineeringQuantityListByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiBaseEngineeringQuantityList) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiBaseEngineeringQuantityList> getAllBusiBaseEngineeringQuantityList(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public List<BusiBaseEngineeringQuantityList> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", uuid));
        criteria.add(Restrictions.eq("busiFwOrg.id", orgId));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        criteria.addOrder(Order.asc("orderNumber"));
        return criteria.list();
    }
}
