package com.huatek.busi.service.impl.phicom.coupons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.coupons.PhiTimingPushCouponsDetailDao;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.coupons.PhiTimingPushCouponsDetail;
import com.huatek.busi.service.phicom.coupons.PhiTimingPushCouponsDetailService;


@Service("phiTimingPushCouponsDetailService")
@Transactional
public class PhiTimingPushCouponsDetailServiceImpl implements PhiTimingPushCouponsDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiTimingPushCouponsDetailServiceImpl.class);
	
	@Autowired
	private PhiTimingPushCouponsDetailDao phiTimingPushCouponsDetailDao;
	
	public void saveOrUpdatePhiTimingPushCouponsDetail(PhiTimingPushCouponsDetail entity){
		phiTimingPushCouponsDetailDao.saveOrUpdatePhiTimingPushCouponsDetail(entity);
	}
	
	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByNo(int number){
		return phiTimingPushCouponsDetailDao.findNotPushPhiTimingPushCouponsDetailsByNo(number);
	}

	/**
	 * 新增定时任务数据
	 */
	@Override
	public void savePhiTimingPushCouponsDetailList(List<PhiCouponsDetail> bindingCouponsDetailList) {
		if(null != bindingCouponsDetailList && bindingCouponsDetailList.size() > 0){
			List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList = new ArrayList<PhiTimingPushCouponsDetail>();
			for(PhiCouponsDetail phiCouponsDetail:bindingCouponsDetailList){
				PhiTimingPushCouponsDetail phiTimingPushCouponsDetail = new PhiTimingPushCouponsDetail();
				phiTimingPushCouponsDetail.setCpnsId(phiCouponsDetail.getCpnsId());
				phiTimingPushCouponsDetail.setCoupWayId(phiCouponsDetail.getCoupWayId());
				phiTimingPushCouponsDetail.setCoupCode(phiCouponsDetail.getCoupCode());
				phiTimingPushCouponsDetail.setPushStatus(Long.valueOf(0));
				phiTimingPushCouponsDetail.setCreateTime(new Date());
				phiTimingPushCouponsDetail.setPhiMember(phiCouponsDetail.getMemberId());
				phiTimingPushCouponsDetailList.add(phiTimingPushCouponsDetail);
			}
			if(null != phiTimingPushCouponsDetailList && phiTimingPushCouponsDetailList.size() > 0){
				phiTimingPushCouponsDetailDao.batchSaveForMergePhiTimingPushCouponsDetailList(phiTimingPushCouponsDetailList);
			}
		}
		
	}

	@Override
	public void updatePhiTimingPushCouponsDetailList(List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList) {
		if(null != phiTimingPushCouponsDetailList && phiTimingPushCouponsDetailList.size() > 0){
			phiTimingPushCouponsDetailDao.batchSaveForMergePhiTimingPushCouponsDetailList(phiTimingPushCouponsDetailList);;
		}
		
	}

	@Override
	public List<PhiTimingPushCouponsDetail> findNotPushPhiTimingPushCouponsDetailsByByManual() {
		List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList = phiTimingPushCouponsDetailDao.findNotPushPhiTimingPushCouponsDetailsByByManual();
		return phiTimingPushCouponsDetailList;
	}
	
}
