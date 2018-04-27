package com.huatek.busi.service.impl.phicom.coupons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.coupons.PhiTimingPushCouponsDetail;
import com.huatek.busi.service.phicom.coupons.PhiTimingPushCouponsDetailService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.frame.service.ExceptionLogService;
import com.huatek.task.service.AbstractJob;
import com.huatek.task.service.TaskContext;

/**
 * 推送优惠劵定时任务
 * @author mickey_meng
 *
 */
@Service("PhiTimingPushCouponsDetailTaskService")
@Transactional(rollbackFor=Exception.class)
public class PhiTimingPushCouponsDetailTaskService extends AbstractJob {
	
	private static final Logger log = LoggerFactory.getLogger(PhiTimingPushCouponsDetailTaskService.class);

	@Autowired
	private PhiTimingPushCouponsDetailService phiTimingPushCouponsDetailService;
	
	@Autowired
    private ExceptionLogService exceptionLogService;//异常日志服务类
	
	@Autowired
	private InterfaceApiService interfaceApiService;
	
	@Override
	public void excute(TaskContext context) {
		log.info("[推送优惠劵]定时任务开始执行...");
		//获取待推送优惠劵数据
//		List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList = phiTimingPushCouponsDetailService.findNotPushPhiTimingPushCouponsDetailsByNo(2);//根据几个会员获取几批优惠劵数据
		List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList = phiTimingPushCouponsDetailService.findNotPushPhiTimingPushCouponsDetailsByByManual();//根据几个会员获取几批优惠劵数据
		
		if(null != phiTimingPushCouponsDetailList && phiTimingPushCouponsDetailList.size() > 0){
			List<PhiCouponsDetail> bindingCouponsDetailList = new ArrayList<PhiCouponsDetail>();
			for(PhiTimingPushCouponsDetail phiTimingPushCouponsDetail: phiTimingPushCouponsDetailList){
				bindingCouponsDetailList.add(this.convertPhiCouponsDetail(phiTimingPushCouponsDetail));
				phiTimingPushCouponsDetail.setPushStatus(Long.valueOf(1));
				phiTimingPushCouponsDetail.setPushTime(new Date());
			}
			try{
				interfaceApiService.taskPushAndBindingSupplyPhiCouponsToChengShang(bindingCouponsDetailList);
				phiTimingPushCouponsDetailService.updatePhiTimingPushCouponsDetailList(phiTimingPushCouponsDetailList);//更新定时任务数据
			} catch(Exception e){
				log.error("定时任务推送优惠劵失败!");
				e.printStackTrace();
			}	
		}
		log.info("[推送优惠劵]定时任务执行完成!");
	}

	private PhiCouponsDetail convertPhiCouponsDetail(PhiTimingPushCouponsDetail phiTimingPushCouponsDetail) {
		PhiCouponsDetail bindingCouponsDetail = new PhiCouponsDetail();
		bindingCouponsDetail.setCpnsId(phiTimingPushCouponsDetail.getCpnsId());
		bindingCouponsDetail.setCoupCode(phiTimingPushCouponsDetail.getCoupCode());
		bindingCouponsDetail.setCoupWayId(phiTimingPushCouponsDetail.getCoupWayId());
		bindingCouponsDetail.setMemberId(phiTimingPushCouponsDetail.getPhiMember());
		return bindingCouponsDetail;
	}
	
	public void pushTimingPushCouponsDetailByManual(){
		log.info("[手动-推送优惠劵]定时任务开始执行...");
		//获取待推送优惠劵数据
		List<PhiTimingPushCouponsDetail> phiTimingPushCouponsDetailList = phiTimingPushCouponsDetailService.findNotPushPhiTimingPushCouponsDetailsByByManual();//根据几个会员获取几批优惠劵数据
		if(null != phiTimingPushCouponsDetailList && phiTimingPushCouponsDetailList.size() > 0){
			List<PhiCouponsDetail> bindingCouponsDetailList = new ArrayList<PhiCouponsDetail>();
			for(PhiTimingPushCouponsDetail phiTimingPushCouponsDetail: phiTimingPushCouponsDetailList){
				bindingCouponsDetailList.add(this.convertPhiCouponsDetail(phiTimingPushCouponsDetail));
				phiTimingPushCouponsDetail.setPushStatus(Long.valueOf(1));
				phiTimingPushCouponsDetail.setPushTime(new Date());
			}
			try{
				log.error("手动-定时任务推送优惠劵:"+bindingCouponsDetailList.size());
				interfaceApiService.taskPushAndBindingSupplyPhiCouponsToChengShang(bindingCouponsDetailList);
				phiTimingPushCouponsDetailService.updatePhiTimingPushCouponsDetailList(phiTimingPushCouponsDetailList);//更新定时任务数据
			} catch(Exception e){
				log.error("手动-定时任务推送优惠劵失败!");
				e.printStackTrace();
			}	
		}
		log.info("[手动-推送优惠劵]定时任务执行完成!");
	}

}
