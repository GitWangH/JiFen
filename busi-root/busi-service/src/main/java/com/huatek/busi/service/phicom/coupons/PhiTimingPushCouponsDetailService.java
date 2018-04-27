package com.huatek.busi.service.phicom.coupons;

import java.util.List;

import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.coupons.PhiTimingPushCouponsDetail;

public interface PhiTimingPushCouponsDetailService {
	
public void saveOrUpdatePhiTimingPushCouponsDetail(PhiTimingPushCouponsDetail entity);
	
	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByNo(int number);

	public void savePhiTimingPushCouponsDetailList(List<PhiCouponsDetail> bindingCouponsDetailList);

	public void updatePhiTimingPushCouponsDetailList(List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList);

	/**
	 * 获取手动推送优惠劵数据
	 * @return
	 */
	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByByManual();

}
