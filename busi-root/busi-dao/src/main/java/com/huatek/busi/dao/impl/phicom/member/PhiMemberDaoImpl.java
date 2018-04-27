package com.huatek.busi.dao.impl.phicom.member;
   
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiMemberDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-18 15:33:14
  * @version: 1.0
  */
@Repository("PhiMemberDao")
public class  PhiMemberDaoImpl extends AbstractDao<Long,  PhiMember> implements  PhiMemberDao {

    private Logger logger = LoggerFactory.getLogger(PhiMemberDaoImpl.class);

    @Override
    public PhiMember findPhiMemberById(Long id) {
        return super.getByKey(id);
    }
    @Override
    public PhiMember findPhiMemberByIdLock(Long id) {
        return super.lockData(id);
    }
    @Override
    public void saveOrUpdatePhiMember( PhiMember entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiMember(PhiMember entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiMember(PhiMember entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiMember> findAllPhiMember() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiMember findPhiMemberByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        
        return (PhiMember) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiMember> getAllPhiMember(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@SuppressWarnings("unchecked")
	@Override
	public PhiMember findPhiMemberByUid(int UId) {
		
		String sql = "from PhiMember t where   t.UId = :UId ";
		Query query = this.createQuery(sql);
		query.setParameter("UId", UId);
//		PhiMember member  = new PhiMember();
		PhiMember member  = null;
		List<PhiMember> memberList= query.list();
		if(memberList != null && memberList.size()>0 ){
			member = memberList.get(0);
		}
		return member;
	}

	@Override
	public List<PhiMember> getRandomWinners(Long productId, List<Long> idsList,int count) {
		String sql = "select rm.member_id as id,rm.tel as tel,rm.user_name as userName from phi_member rm where rm.member_id in (select rn.member_id from phi_order rn where rn.product_id = :productId) ";
		if(idsList.size() > 0){
			sql += " and rm.member_id not in :idsList ORDER BY RAND() LIMIT :count";
		}else{
			sql += " ORDER BY RAND() LIMIT :count";
		}
		Query query = this.createSQLQuery(sql).addScalar("id",StandardBasicTypes.LONG).addScalar("tel",StandardBasicTypes.STRING).addScalar("userName",StandardBasicTypes.STRING);
		query.setLong("productId", productId);
		if(idsList.size() > 0){
			query.setParameterList("idsList", idsList);
		}
		query.setInteger("count", count);
		List<PhiMember> memberList = query.setResultTransformer(Transformers.aliasToBean(PhiMember.class)).list();
		return memberList;
	}

	@Override
	public List<PhiMember> findAllPlusPhiMember(String currentDate1) {
		SimpleDateFormat beforeMonthSdf = new SimpleDateFormat("yyyy-MM-dd  23:59:59");
		SimpleDateFormat currentDateSdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
		
		String beforeMonth = beforeMonthSdf.format(calendar.getTime());//当前日期的上月
		String currentDate = currentDateSdf.format(new Date());//当前日期月的第一天
//		String hql = "from PhiMember t where   t.isplusMember = 1 and date_format(t.sendTime,'%Y-%m-%d') =:currentDate ";
		String hql = "from PhiMember t where   t.isplusMember = 1 and t.UId is not null  and t.plusOpenDate <= '"+beforeMonth+"' and t.validTime >='"+currentDate+"'";
		Query query = createQuery(hql);
//				     .setParameter("beforeMonth", beforeMonth)
//					 .setParameter("currentDate", currentDate);
		List<PhiMember>  memberList = query.list();
		if(memberList == null  || memberList.size() < 1 ){
			return null;
		}
		return memberList;
	}
	
	@Override
	public PhiMember findPhiMemberByTel(String acctName) {
		String hql = "from PhiMember t where   t.tel = :acctName ";
		//Query query = createQuery(hql).setParameter("acctName", acctName);
		Query query = this.createQuery(hql);
		query.setParameter("acctName", acctName);
		PhiMember member=(PhiMember)query.uniqueResult();
		return member;
	}
	
	@Override
	public List<PhiMember> findPlusPhiMember() {
		String hql = "from PhiMember t where   t.isplusMember = 1 ";
		Query query = createQuery(hql);
		List<PhiMember>  memberList = query.list();
		if(memberList == null  || memberList.size() < 0 ){
			return null;
		}
		return memberList;
	}
	
	@Override
	public PhiMember findPlusPhiMemberById(Long memberId) {
		String hql = "from PhiMember t where t.isplusMember = 1 and  t.id =:memberId ";
		//Query query = createQuery(hql).setParameter("acctName", acctName);
		Query query = this.createQuery(hql);
		query.setParameter("memberId", memberId);
		PhiMember member=(PhiMember)query.uniqueResult();
		return member;
	}
	@Override
	public BigDecimal findCountExchange(Long productId, Long memberId) {
		String hql = "select count(1) from PhiOrder t where t.phiMember.id =:memberId and  t.productId =:productId  "
				+ "and status <> '5' and isdelete = '0'";
		//Query query = createQuery(hql).setParameter("acctName", acctName);
		Query query = this.createQuery(hql);
		query.setParameter("memberId", memberId);
		query.setParameter("productId", productId);
		Long ExchangeCount = (Long) query.list().get(0);
		BigDecimal countBigDecimal = new BigDecimal(ExchangeCount);
		return countBigDecimal;
	}

	public static void main(String[] agrs){
		SimpleDateFormat beforeMonthSdf = new SimpleDateFormat("yyyy-MM-dd  23:59:59");
		SimpleDateFormat currentDateSdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
		
		String beforeMonth = beforeMonthSdf.format(calendar.getTime());
		String currentDate = currentDateSdf.format(new Date());
		
		System.out.println(beforeMonth);
		System.out.println(currentDate);
	}
	
	/**
	 * 根据分页查到的会员数据查询会员信息
	 * @param memberIdList
	 * @return
	 */
	@Override
	public Map<Long, PhiMember> findPlusPhiMemberByIds(List<Long> memberIdList){
		Map<Long,PhiMember> phiMemberMap = null;
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.in("id", memberIdList));
        List<PhiMember> phiMemberList = criteria.list();
        if(null != phiMemberList && phiMemberList.size() > 0){
        	phiMemberMap = new HashMap<Long,PhiMember>();
        	for(PhiMember phiMember:phiMemberList){
        		phiMemberMap.put(phiMember.getId(), phiMember);
        	}
        }
        return phiMemberMap;
	}

}
