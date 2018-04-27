package com.huatek.busi.dao.impl.base;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseSubEngineeringDao;
import com.huatek.busi.model.base.BusiBaseImageList;
import com.huatek.busi.model.base.BusiBaseSubEngineering;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * 项目分部分项
  * @ClassName: BusiBaseSubEngineeringDaoImpl
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:23:13
  * @version: Windows 7
  */

@Repository("BusiBaseSubEngineeringDao")
public class  BusiBaseSubEngineeringDaoImpl extends AbstractDao<Long,  BusiBaseSubEngineering> implements  BusiBaseSubEngineeringDao {

    private Logger logger = LoggerFactory.getLogger(BusiBaseSubEngineeringDaoImpl.class);

    @Override
    public BusiBaseSubEngineering findBusiBaseSubEngineeringById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiBaseSubEngineering( BusiBaseSubEngineering entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiBaseSubEngineering(BusiBaseSubEngineering entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiBaseSubEngineering(BusiBaseSubEngineering entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiBaseSubEngineering> findAllBusiBaseSubEngineering() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiBaseSubEngineering findBusiBaseSubEngineeringByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiBaseSubEngineering) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiBaseSubEngineering> getAllBusiBaseSubEngineering(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public List<BusiBaseSubEngineering> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", uuid));
        criteria.add(Restrictions.eq("busiFwOrg.id", orgId));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        criteria.addOrder(Order.asc("orderNumber"));
        return criteria.list();
    }


}
