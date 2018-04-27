package com.huatek.busi.dao.impl.phicom.member;
   
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.member.PhiMemberAddressDao;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.member.PhiMemberAddress;
import com.huatek.busi.model.phicom.member.PhiMemberGrade;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiMemberAddressDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-30 13:37:51
  * @version: 1.0
  */

@Repository("PhiMemberAddressDao")
public class  PhiMemberAddressDaoImpl extends AbstractDao<Long,  PhiMemberAddress> implements  PhiMemberAddressDao {

    private Logger logger = LoggerFactory.getLogger(PhiMemberAddressDaoImpl.class);

    @Override
    public PhiMemberAddress findPhiMemberAddressById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiMemberAddress( PhiMemberAddress entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiMemberAddress(PhiMemberAddress entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiMemberAddress(PhiMemberAddress entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiMemberAddress> findAllPhiMemberAddress() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiMemberAddress findPhiMemberAddressByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiMemberAddress) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiMemberAddress> getAllPhiMemberAddress(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<PhiMemberAddress> findPhiMemberAddressByMemberId(Long memberId) {
		String sql = "from PhiMemberAddress t where   t.memberId =:memberId  order by t.id desc";
		Query query = createQuery(sql).setParameter("memberId", memberId);
		List<PhiMemberAddress> memberAddressList = query.list();
		if(memberAddressList == null  || memberAddressList.size() < 0 ){
			return null;
		}
		return memberAddressList;
	}
	
	
	@Override
	public List<PhiMemberAddress> findPhiMemberAddressByUId(int UId) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("UId", UId));
		return criteria.list();
		
		/*String sql = "from PhiMemberAddress t where   t.UId= :UId  order by t.id desc";
		Query query = this.createQuery(sql);
		query.setParameter("UId", UId);
		PhiMemberAddress memberAddress=(PhiMemberAddress)query.uniqueResult();
		return null;*/
	}

	@Override
	public PhiMemberAddress fingDefaultAddressByMemberId(Long memberId) {
		String hql = "from PhiMemberAddress t where t.memberId =:memberId and t.defaultState ='1'";
		Query query = this.createQuery(hql);
		query.setParameter("memberId", memberId);
		PhiMemberAddress memberAddress=(PhiMemberAddress)query.uniqueResult();
		return memberAddress;
	}

	@Override
	public DataPage<PhiMemberAddress> getAllPhiMemberAddressByMemberId(QueryPage queryPage, Long id) {
	/*	String hql = "from PhiMemberAddress where memberId =:memberId";
		Query query = this.createQuery(hql);
		query.setParameter("memberId", memberId);
		PhiMemberAddress memberAddress=(PhiMemberAddress)query.uniqueResult();
		return memberAddress;*/
		return null;
	}

	/**
     * 批量保存地址信息
     * @author eden  
     * @date Jan 31, 2018 8:37:13 PM
     * @desc TODO(用一句话描述本方法的作用)  
     * @param: @param addressList  
     * @return: void      
     * @throws
     */
	@Override
	public void bacthSaveAddress(List<PhiMemberAddress> addressList){
		super.batchSave(addressList, 100);
	}

	@Override
	public void batchDeleteAddress(List<PhiMemberAddress> addressList) {
		super.batchDelete(addressList, 10);
		
	}
	
	
	
	
}
