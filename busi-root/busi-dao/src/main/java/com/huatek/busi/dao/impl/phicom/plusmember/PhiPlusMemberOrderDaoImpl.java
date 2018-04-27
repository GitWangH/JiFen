package com.huatek.busi.dao.impl.phicom.plusmember;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.plusmember.PhiPlusMemberOrderDao;
import com.huatek.busi.model.phicom.plusmember.PhiPlusMemberOrder;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 
 * 会员plus订单信息DaoImpl
 * 
 * @ClassName: PhiPlusMemberOrderDaoImpl
 * @Description: TODO
 * @author martin_ju
 * @e_mail martin_ju@huatek.com
 * @date 2018年1月24日
 *
 */
@Repository("PhiPlusMemberOrderDao")
public class PhiPlusMemberOrderDaoImpl extends
		AbstractDao<Long, PhiPlusMemberOrder> implements PhiPlusMemberOrderDao {

	@Override
	public PhiPlusMemberOrder getphiPlusMemberOrder(String orderNo) {
		 Criteria criteria = createEntityCriteria();
		 criteria.add(Restrictions.eq("orderNo", orderNo));
	     List<PhiPlusMemberOrder> list=criteria.list();
	     if(list!=null&&list.size()>0){
	    	 return list.get(0);
	     }
	     return null;

	}

	@Override
	public void saveOrUpdatePhiPlusMemberOrder(PhiPlusMemberOrder model) {
		super.saveOrUpdate(model);
	}
	
	@Override
    public PhiPlusMemberOrder findPhiPlusMemberOrderById(Long id) {
        return super.getByKey(id);
    }


    @Override
    public void persistentPhiPlusMemberOrder(PhiPlusMemberOrder entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPlusMemberOrder(PhiPlusMemberOrder entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPlusMemberOrder> findAllPhiPlusMemberOrder() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPlusMemberOrder findPhiPlusMemberOrderByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPlusMemberOrder) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPlusMemberOrder> getAllPhiPlusMemberOrder(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }


}
