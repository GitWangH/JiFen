package com.huatek.busi.dao.impl.phicom.winners;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.winner.PhiWinnersDao;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.model.phicom.winner.PhiWinners;
import com.huatek.busi.model.phicom.winner.VirtualUser;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiWinnersDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 13:42:13
  * @version: 1.0
  */

@Repository("PhiWinnersDao")
public class  PhiWinnersDaoImpl extends AbstractDao<Long,  PhiWinners> implements  PhiWinnersDao {

    private Logger logger = LoggerFactory.getLogger(PhiWinnersDaoImpl.class);

    @Override
    public PhiWinners findPhiWinnersById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiWinners( PhiWinners entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiWinners(PhiWinners entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiWinners(PhiWinners entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiWinners> findAllPhiWinners() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiWinners findPhiWinnersByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiWinners) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiWinners> getAllPhiWinners(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void saveWinnerList(List<PhiWinners> winnerlist) {
	   super.batchSave(winnerlist, 20);		
	}

	@Override
	public List<PhiMember> getAllMemberByProductId(Long productId) {
		String strbu= " select distinct phiMember from PhiOrder t where t.productId =:productId ";
		Query query = this.createQuery(strbu);
		query.setParameter("productId", productId);
		List<PhiMember> orderlist =query.list();
		return orderlist;
		
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<VirtualUser> getAllVirtualUsers() {		
		 String hql= "from VirtualUser t ";
		 Query query = this.createQuery(hql);
		 List<VirtualUser> virtualList =query.list();
		 return  virtualList;
	}

	@Override
	public void cleanAllWinnersByProductId(Long id) {
		
	   String hql = "delete from PhiWinners t where t.productId.id = :productId";
	   Query query =  super.createQuery(hql);
	   query.setParameter("productId", id);
	   query.executeUpdate();

	}

	@Override
	public List<PhiWinners> getAllRandomMemberByProductId(Long id) {
		String hql = "from PhiWinners t where t.productId.id = :productId and t.userType = '0'" ;
		Query query =  super.createQuery(hql);
		query.setParameter("productId", id);
		List<PhiWinners> phiWinnersList = query.list();
		return phiWinnersList;
		 
	}


	@Override
	public List<PhiOrder> getRandomMemberByProductId(Long id) {
		String hql ="from PhiOrder t where t.productId =:productId and not exists (from PhiWinners rn where rn.memberId.id = t.phiMember.id and rn.productId.id = t.productId)" ;
		Query query =  super.createQuery(hql);
		query.setParameter("productId", id);
		List<PhiOrder> phiOrderList = query.list();
		return phiOrderList;
	}

	@Override
	public List<PhiWinners> getPhiWinnersByProductIdAndMemberId(Long Pid,
		  Long MId) {
		String hql = "from PhiWinners t where t.productId.id =:productId and t.memberId.id =:memberId" ;	
		Query query =  super.createQuery(hql);
		query.setParameter("productId", Pid);
		query.setParameter("memberId", MId);
		List<PhiWinners> phiWinnersList = query.list();
		return phiWinnersList;
	}


	@Override
	public List<PhiWinners> getAllPhiWinnersByproductId(Long productId) {
		String hql = "from PhiWinners t where t.productId.id = :productId " ;
		Query query =  super.createQuery(hql);
		query.setParameter("productId", productId);
		List<PhiWinners> phiWinnersList = query.list();
		return phiWinnersList;
	}

	@Override
	public List<PhiOrder> getAllOrderByProductId(Long ProductId) {
		String strbu= "from PhiOrder t where t.productId =:productId ";
		Query query = this.createQuery(strbu);
		query.setParameter("productId", ProductId);
		List<PhiOrder> orderlist =query.list();
		return orderlist;
	}


}
