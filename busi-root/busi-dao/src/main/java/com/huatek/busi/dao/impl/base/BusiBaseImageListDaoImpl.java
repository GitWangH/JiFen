package com.huatek.busi.dao.impl.base;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseImageListDao;
import com.huatek.busi.model.base.BusiBaseImageList;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * 形象清单
  * @ClassName: BusiBaseImageListDaoImpl
  * @Description: 
  * @author: eli_cui
  * @Email : 
  * @date: 2017-10-24 13:23:28
  * @version: Windows 7
  */

@Repository("BusiBaseImageListDao")
public class  BusiBaseImageListDaoImpl extends AbstractDao<Long,  BusiBaseImageList> implements  BusiBaseImageListDao {

    private Logger logger = LoggerFactory.getLogger(BusiBaseImageListDaoImpl.class);

    @Override
    public BusiBaseImageList findBusiBaseImageListById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiBaseImageList( BusiBaseImageList entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiBaseImageList(BusiBaseImageList entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiBaseImageList(BusiBaseImageList entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiBaseImageList> findAllBusiBaseImageList() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiBaseImageList findBusiBaseImageListByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiBaseImageList) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiBaseImageList> getAllBusiBaseImageList(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    @Override
    public List<BusiBaseImageList> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", uuid));
        criteria.add(Restrictions.eq("busiFwOrg.id", orgId));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        criteria.addOrder(Order.asc("orderNumber"));
        return criteria.list();
    }
}
