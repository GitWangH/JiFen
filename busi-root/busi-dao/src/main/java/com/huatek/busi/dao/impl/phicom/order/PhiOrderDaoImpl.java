package com.huatek.busi.dao.impl.phicom.order;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.order.PhiOrderDao;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.model.phicom.winner.PhiWinners;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiOrderDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-21 16:27:35
  * @version: 1.0
  */

@Repository("PhiOrderDao")
public class  PhiOrderDaoImpl extends AbstractDao<Long,  PhiOrder> implements  PhiOrderDao {

    private Logger logger = LoggerFactory.getLogger(PhiOrderDaoImpl.class);

    @Override
    public PhiOrder findPhiOrderById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiOrder( PhiOrder entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiOrder(PhiOrder entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiOrder(PhiOrder entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiOrder> findAllPhiOrder() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiOrder findPhiOrderByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiOrder) criteria.uniqueResult();
    }

   /* @Override
    public DataPage<PhiOrder> getAllPhiOrder(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
*/
    public DataPage<PhiOrder> getAllPhiOrder(QueryPage queryPage) {
    	
    	StringBuilder strbu=new StringBuilder();
    	strbu.append(" select from PhiOrder");
        return super.queryPageData(queryPage);
    }

    
	@Override
	public PhiOrder findPhiOrderByOrderCode(String order_code) {
		//StringBuilder strbu=new StringBuilder("from PhiOrder t where t.orderCode = :order_code");
		String strbu= "from PhiOrder t where t.orderCode = :order_code";
		Query query = this.createQuery(strbu);
		query.setParameter("order_code", order_code);
		PhiOrder order=(PhiOrder)query.uniqueResult();
		return order;

	}

	/* 
	@Override
	public List<PhiOrder> findPhiOrderByMemberId(Long memberId) {
	
		String sql = "from PhiOrder t where   t.phiMember.id = order by t.id desc";
		List<PhiOrder> orderList= this.find(sql, memberId);
		Query query = this.createQuery(sql);
		query.setParameter("memberId", memberId);
		return orderList;
	}
*/
	@Override
	public List<PhiOrder> findPhiOrderByMemberId(Long memberId) {
	
		String sql = "from PhiOrder t where   t.phiMember.id = :memberId ";
		Query query = this.createQuery(sql);
		query.setParameter("memberId", memberId);
		List<PhiOrder> orderList= query.list();
		
		return orderList;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PhiOrder findPhiOrderByMemberIdAndProductId(Long productId,Long MemberId) {

		 String hql = "from PhiOrder t where t.phiMember.id =:MemberId and t.productId =:productId ";
		 Query query =  super.createQuery(hql);
		 query.setParameter("MemberId", MemberId);
		 query.setParameter("productId",productId);
		 List<PhiOrder> orderList= query.list();
		 PhiOrder phiOrder = orderList.get(0);
		 //PhiOrder phiOrder = (PhiOrder)query.uniqueResult();
		 return phiOrder;
		
	}

//	@Override
//	public DataPage<PhiWinners> getWinnerpageByproductId(QueryPage queryPage) {
//		 return super.queryPageData(queryPage);
//	}

}
