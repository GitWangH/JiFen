package com.huatek.busi.service.phicom.support;

import java.util.List;

import com.google.gson.JsonObject;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;

/**
 * 第三方接口Api服务类
 * @author mickey_meng
 */
public interface InterfaceApiService {
	
	/**
	 * 推送会员信息给商城
	 * @param phiMember
	 */
	public void pushPlusPhiMemberToChengShang(PhiMember phiMember);

	/**
	 * 给辰商推送已绑定的优惠劵和plus会员信息
	 * @param bindingCouponsDetailList
	 */
	void pushBindingPhiCouponsAndPlusPhiMemberToChengShang(Long memberId,List<PhiCouponsDetail> bindingCouponsDetailList);

	/**
	 * 手动补发和推送优惠劵
	 * @param couponsDetial
	 */
	void pushAndBindingSupplyPhiCouponsToChengShang(PhiCouponsDetail couponsDetial);
	
	
	JsonObject toCheckThePlusCodeForChengShang(String code,String uid);
	
	/**
	 * 定时任务自动推送优惠劵
	 * @param couponsDetial
	 */
	public void taskPushAndBindingSupplyPhiCouponsToChengShang(List<PhiCouponsDetail> couponsDetialList);

}
