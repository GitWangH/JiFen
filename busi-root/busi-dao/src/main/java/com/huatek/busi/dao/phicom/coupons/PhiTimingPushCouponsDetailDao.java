package com.huatek.busi.dao.phicom.coupons;

import java.util.List;

import com.huatek.busi.model.phicom.coupons.PhiTimingPushCouponsDetail;

/**
 * 定时任务推送优惠劵Dao
 * @author mickey_meng
 *
 */
public interface PhiTimingPushCouponsDetailDao {
	
	public void saveOrUpdatePhiTimingPushCouponsDetail(PhiTimingPushCouponsDetail entity);
	
	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByNo(int number);

	public void batchSaveForMergePhiTimingPushCouponsDetailList(List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList);

	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByByManual();
}
