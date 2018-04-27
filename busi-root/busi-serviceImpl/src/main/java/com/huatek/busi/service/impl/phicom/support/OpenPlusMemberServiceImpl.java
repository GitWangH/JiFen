package com.huatek.busi.service.impl.phicom.support;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.coupons.PhiCouponsService;
import com.huatek.busi.service.phicom.coupons.PhiTimingPushCouponsDetailService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.busi.service.phicom.support.OpenPlusMemberService;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.ExceptionLogService;

@Service("openPlusMemberService")
public class OpenPlusMemberServiceImpl implements OpenPlusMemberService {
	
	private static final Logger log = LoggerFactory.getLogger(OpenPlusMemberServiceImpl.class);

	@Autowired
	private PhiMemberService phiMemberService; 
	
	@Autowired
    private ExceptionLogService exceptionLogService;//异常日志服务类
	
	@Autowired
	private PhiCouponsService phiCouponsService;//优惠劵服务类
	
	@Autowired
	private InterfaceApiService interfaceApiService;//第三方接口Api服务类
	
	@Autowired
	private PhiTimingPushCouponsDetailService phiTimingPushCouponsDetailService;
	
	@Override
	public void openPlusMember(Long memberId, String plusCode) {
		PhiMember phiMember = phiMemberService.findPhiMemberById(memberId);
		if (phiMember != null) {
			/*********************** 1、开通plus会员 *********************************/
			//plusCode已存在 plus会员续费 isplusMember = "0"
			if("0".equals(phiMember.getIsplusMember())&& //非plus
					StringUtils.isNotEmpty(phiMember.getPlusCode()) && 
								plusCode.equals(phiMember.getPlusCode())){
				//plus开通类型：续费
				phiMember.setPlusOpenType("renewOpen");
				phiMember.setPlusRenewDate(new Date());
				phiMember = this.setPhiMemberInfo(phiMember);//设置会员信息
				phiMemberService.saveOrUpdatePhiMember(phiMember);
				//首次开通plus会员-普通会员（isplusMember = "0"），plusCode为null,
			}else if("0".equals(phiMember.getIsplusMember())&& //非plus
					StringUtils.isEmpty(phiMember.getPlusCode())){
				//plus首次开通时间
			    phiMember.setPlusOpenDate(new Date());
			    phiMember.setPlusOpenType("firstOpen");
			    phiMember.setPlusCode(plusCode);
			    phiMember = this.setPhiMemberInfo(phiMember);//设置会员信息
			    phiMemberService.saveOrUpdatePhiMember(phiMember);
			    List<PhiCouponsDetail> bindingCouponsDetailList = null;
		        try {
		        	/********************** 2、 绑定优惠劵 *********************************/
		        	bindingCouponsDetailList = phiCouponsService.bindingCouponsDetailOfPlusPhiMember(phiMember);//PLUS会员绑定优惠劵
		        	 /********************** 3、 推送已绑定的优惠劵 & 推送会员信息给辰商 *********************************/
//			        interfaceApiService.pushBindingPhiCouponsAndPlusPhiMemberToChengShang(memberId,bindingCouponsDetailList);
			        interfaceApiService.pushPlusPhiMemberToChengShang(phiMember);//只推送会员信息-优惠劵使用定时推送 2018-03-28 Edit By Mickey
		        }catch (Exception e) {
		        	log.error(phiMember.getTel() + "开通plus会员绑定优惠劵失败(PhiMemberServiceImpl_862)失败!");
		        	e.printStackTrace();
		        }
		        phiTimingPushCouponsDetailService.savePhiTimingPushCouponsDetailList(bindingCouponsDetailList);
			}
		}else{
			throw new ResourceNotFoundException(memberId);
		}
	}
	
	public void openPlusMember_old(Long memberId, String plusCode) {
		PhiMember phiMember = phiMemberService.findPhiMemberById(memberId);
		if (phiMember != null) {
			/*********************** 1、开通plus会员 *********************************/
			//plusCode已存在 plus会员续费 isplusMember = "0"
			if("0".equals(phiMember.getIsplusMember())&& //非plus
					StringUtils.isNotEmpty(phiMember.getPlusCode()) && 
								plusCode.equals(phiMember.getPlusCode())){
				phiMember = this.setPhiMemberInfo(phiMember);//设置会员信息
				phiMemberService.saveOrUpdatePhiMember(phiMember);
				//首次开通plus会员-普通会员（isplusMember = "0"），plusCode为null,
			}else if("0".equals(phiMember.getIsplusMember())&& //非plus
					StringUtils.isEmpty(phiMember.getPlusCode())){
				//plus首次开通时间
			    phiMember.setPlusOpenDate(new Date());
			    phiMember.setPlusCode(plusCode);
			    phiMember = this.setPhiMemberInfo(phiMember);//设置会员信息
			    phiMemberService.saveOrUpdatePhiMember(phiMember);
			    List<PhiCouponsDetail> bindingCouponsDetailList = null;
		        try {
		        	/********************** 2、 绑定优惠劵 *********************************/
		        	bindingCouponsDetailList = phiCouponsService.bindingCouponsDetailOfPlusPhiMember(phiMember);//PLUS会员绑定优惠劵
		        }catch (Exception e) {
					log.error(phiMember.getTel() + "开通plus会员绑定优惠劵失败(PhiMemberServiceImpl_862)失败!");
					e.printStackTrace();
		        }
		        /********************** 3、 推送已绑定的优惠劵 & 推送会员信息给辰商 *********************************/
		        interfaceApiService.pushBindingPhiCouponsAndPlusPhiMemberToChengShang(memberId,bindingCouponsDetailList);
			}
		}else{
			throw new ResourceNotFoundException(memberId);
		}
	}
	
	/**
	 * 设置会员信息
	 * @param phiMember
	 * @return
	 */
	private PhiMember setPhiMemberInfo(PhiMember phiMember){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH,1);//计算1个月后的时间  每月1日凌晨3点开始发放，从开通成功后的次月开始
		c.set(Calendar.DAY_OF_MONTH,1);
		c.set(Calendar.HOUR_OF_DAY,3);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);		
		phiMember.setSendTime(c.getTime());
		phiMember.setIsplusMember("1");
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.YEAR, 1);
	    //plus会员有效期
	    phiMember.setValidTime(cal.getTime());
		return phiMember;
	}

}
