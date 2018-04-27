package com.huatek.busi.dao.impl.phicom.coupons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.coupons.PhiTimingPushCouponsDetailDao;
import com.huatek.busi.model.phicom.coupons.PhiTimingPushCouponsDetail;
import com.huatek.frame.core.dao.AbstractDao;

@Repository("PhiTimingPushCouponsDetailDao")
public class PhiTimingPushCouponsDetailImpl extends AbstractDao<Long, PhiTimingPushCouponsDetail> implements PhiTimingPushCouponsDetailDao {
	
	@Override
    public void saveOrUpdatePhiTimingPushCouponsDetail(PhiTimingPushCouponsDetail entity) {
        super.saveOrUpdate(entity);
    }
	
	@Override
	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByNo(int number){
		StringBuffer sbHql = new StringBuffer();
//		System.out.println("===================== 1、定时任务查询优惠劵数据 Start ======================");
		//第一种：只支持一次取一个会员数据-测试通过
		sbHql.append("FROM PhiTimingPushCouponsDetail c")
		 	 .append(" WHERE c.phiMember.id IN (")
		 	 .append("       SELECT")
		 	 .append("             MAX(t.phiMember.id)")
		 	 .append("         FROM PhiTimingPushCouponsDetail t ")
		 	 .append("        WHERE t.pushStatus = :pushStatus")
		 	 .append("                 )")
		 	 .append("    AND c.version = 0");
		//第二种：用HQL语句支持多个会员-加锁测试未通过
		/*sbHql.append("FROM PhiTimingPushCouponsDetail c")
		 .append(" WHERE EXISTS (")
		 .append("       SELECT 1 ")
		 .append("           FROM (SELECT")
		 .append("                        t.phiMember.id AS memberId")
		 .append("         			 FROM PhiTimingPushCouponsDetail t ")
		 .append("        			WHERE t.pushStatus = :pushStatus")
		 .append("                   GROUP BY t.phiMember.id")
		 .append("                   ORDER BY t.phiMember.id DESC")
		 .append("                 LIMIT )").append(number).append(") v")
		 .append("          WHERE v.memberId = c.phiMember.id ")
		 .append("      )");*/
		System.out.println("======= 2、定时任务查询优惠劵数据SQL:"+sbHql.toString());
		Query query = super.createQuery(sbHql.toString())
						   .setParameter("pushStatus", Long.valueOf(0))
	    		   		   .setLockMode( "c", LockMode.UPGRADE);//加锁;
		//第三种：用sql支持多个，加锁不成功
		/*sbHql.append("SELECT *")
		 	 .append("  FROM phi_timing_push_coupons_detail c")
		 	 .append(" WHERE EXISTS (")
		 	 .append("       SELECT 1 ")
		 	 .append("           FROM (SELECT")
		 	 .append("                        t.member_id")
		 	 .append("         			 FROM phi_timing_push_coupons_detail t ")
		 	 .append("        			WHERE t.push_status = 0")
		 	 .append("                   GROUP BY t.member_id")
		 	 .append("                   ORDER BY t.member_id DESC")
		 	 .append("                 LIMIT ").append(number).append(") v")
		 	 .append("          WHERE v.member_id = c.member_id ")
		 	 .append("      ) for update");
		 	 
		System.out.println("======= 2、定时任务查询优惠劵数据SQL:"+sbHql.toString());
		List<PhiTimingPushCouponsDetail> list = super.queryEntityListBySql(sbHql.toString(), null, null);
				*/
		List<PhiTimingPushCouponsDetail>  list  = query.list();   
//		System.out.println("===================== 3、定时任务查询优惠劵数据 End ======================"+list.size());
		return list;
	}
	
	@Override
    public void batchSaveForMergePhiTimingPushCouponsDetailList(List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList) {
        super.batchSaveForMerge(phiTimingPushCouponsDetailList, 10);;
    }

	@Override
	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByByManual() {
		StringBuffer sbHql = new StringBuffer();
//		System.out.println("===================== 1、定时任务查询优惠劵数据 Start ======================");
		SimpleDateFormat currentDateSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = currentDateSdf.format(new Date());//当前日期
		
		sbHql.append("FROM PhiTimingPushCouponsDetail c")
		 	 .append(" WHERE c.pushStatus = :pushStatus")
		 	 .append("   AND c.version = 0")
		 	 .append("   AND c.createTime < '").append(currentDate).append("'");
		System.out.println("======= 定时任务查询优惠劵数据SQL:"+sbHql.toString());
		Query query = super.createQuery(sbHql.toString())
						   .setParameter("pushStatus", Long.valueOf(0))
	    		   		   .setLockMode( "c", LockMode.UPGRADE);//加锁;
		List<PhiTimingPushCouponsDetail>  list  = query.list();   
//		System.out.println("===================== 3、定时任务查询优惠劵数据 End ======================"+list.size());
		return list;
	}
}
