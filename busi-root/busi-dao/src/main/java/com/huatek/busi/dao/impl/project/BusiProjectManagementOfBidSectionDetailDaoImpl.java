package com.huatek.busi.dao.impl.project;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.project.BusiProjectManagementOfBidSectionDetailDao;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


/**
 * 工程标段管理明细
 * @author eli_cui
 *
 */

@Repository("BusiProjectManagementOfBidSectionDetailDao")
public class  BusiProjectManagementOfBidSectionDetailDaoImpl extends AbstractDao<Long,  BusiProjectManagementOfBidSectionDetail> implements  BusiProjectManagementOfBidSectionDetailDao {

    private Logger logger = LoggerFactory.getLogger(BusiProjectManagementOfBidSectionDetailDaoImpl.class);

    @Override
    public BusiProjectManagementOfBidSectionDetail findBusiProjectManagementOfBidSectionDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiProjectManagementOfBidSectionDetail( BusiProjectManagementOfBidSectionDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiProjectManagementOfBidSectionDetail(BusiProjectManagementOfBidSectionDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiProjectManagementOfBidSectionDetail(BusiProjectManagementOfBidSectionDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiProjectManagementOfBidSectionDetail> findAllBusiProjectManagementOfBidSectionDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiProjectManagementOfBidSectionDetail findBusiProjectManagementOfBidSectionDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiProjectManagementOfBidSectionDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiProjectManagementOfBidSectionDetail> getAllBusiProjectManagementOfBidSectionDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public List<BusiProjectManagementOfBidSectionDetail> findBusiProjectManagementOfBidSectionDetailByIdList(List<Long> idList) {
    	String hql = "from BusiProjectManagementOfBidSectionDetail i where i.id in (:ids)";
    	Query query = super.createQuery(hql.toString());
    	query.setParameterList("ids", idList);
    	return query.list();
    }

}
