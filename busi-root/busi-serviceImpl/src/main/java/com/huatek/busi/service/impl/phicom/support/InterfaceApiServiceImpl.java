package com.huatek.busi.service.impl.phicom.support;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.coupons.PhiCouponsDetailService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.service.ExceptionLogService;
import com.huatek.frame.service.dto.ExceptionLogDto;
import com.huatek.frame.util.DateUtil;

/**
 * 第三方接口Api服务类
 * @author mickey_meng
 */
@Service("interfaceApiService")
public class InterfaceApiServiceImpl implements InterfaceApiService {
	
	private static final Logger log = LoggerFactory.getLogger(InterfaceApiServiceImpl.class);
	
	@Autowired
	private PhiMemberService phiMemberService; 
	
	@Autowired
	private CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	
	@Autowired
    private ExceptionLogService exceptionLogService;//异常日志服务类
	
	@Autowired
    private PhiCouponsDetailService phiCouponsDetailService;//优惠劵明细服务类

	/**
	 * 推送会员信息给商城
	 * @param phiMember
	 */
	public void pushPlusPhiMemberToChengShang(PhiMember phiMember) {
		/*********1.将开通plus会员信息同步给辰商 Start**********/
//		String data="{\"uid\":1231515,\"integral\":102,\"member_lv_code\":\"zc\",\"desc\":\"说明33\",\"plus\":\"0\"}";//必须为转义
		JsonObject jo = new JsonObject();
		jo.addProperty("uid", phiMember.getUId());
		jo.addProperty("integral", phiMember.getEnableScore());//可用积分
		jo.addProperty("member_lv_code", phiMember.getMemberGradeCode());
		jo.addProperty("desc", phiMember.getDescInfo());
		jo.addProperty("plus", phiMember.getIsplusMember());
		JsonObject sss = null;
		try {
			sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "member_info_modify", jo.toString());
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
			/**************开通plus会员信息同步给辰商成功****************/
			CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
			entityDto.setBusiTime(new Date());
			entityDto.setCode("memberinfomodify");
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(jo.toString());
			entityDto.setResult(isOk.equals("true")?200:0);
			entityDto.setMsg(dataJson.getString("message"));
			entityDto.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
		} catch (Exception e) {
			log.error(phiMember.getTel() + "开通plus会员推送参数解析失败(接口名：bindcoupon)memberinfomodify失败!)");
			e.printStackTrace();
		}
	}
	
	@Override
	public void pushBindingPhiCouponsAndPlusPhiMemberToChengShang(Long memberId, List<PhiCouponsDetail> bindingCouponsDetailList) {
		/*********1.将开通plus会员信息同步给辰商 Start**********/
//		String data="{\"uid\":1231515,\"integral\":102,\"member_lv_code\":\"zc\",\"desc\":\"说明33\",\"plus\":\"0\"}";//必须为转义
		PhiMember phiMember = phiMemberService.findPhiMemberById(memberId);
		JsonObject jo = new JsonObject();
		jo.addProperty("uid", phiMember.getUId());
		jo.addProperty("integral", phiMember.getEnableScore());//可用积分
		jo.addProperty("member_lv_code", phiMember.getMemberGradeCode());
		jo.addProperty("desc", phiMember.getDescInfo());
		jo.addProperty("plus", phiMember.getIsplusMember());
		JsonObject sss = null;
		try {
			sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "member_info_modify", jo.toString());
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
			/**************开通plus会员信息同步给辰商成功****************/
			CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
			entityDto.setBusiTime(new Date());
			entityDto.setCode("memberinfomodify");
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(jo.toString());
			entityDto.setResult(isOk.equals("true")?200:0);
			entityDto.setMsg(dataJson.getString("message"));
			entityDto.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
		} catch (Exception e) {
			log.error(phiMember.getTel() + "开通plus会员推送参数解析失败(接口名：bindcoupon)memberinfomodify失败!)");
			log.error(e.toString());
		} finally{
			//推送优惠劵
			if(null != bindingCouponsDetailList && bindingCouponsDetailList.size() > 0){
				try {
					Thread.sleep(3000);
					this.postHasPhiCouponsDetailToChengShang(bindingCouponsDetailList);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 推送已兑换的优惠卷给辰商
	 * @author eden  
	 * @date Jan 31, 2018 3:09:56 PM
	 * @desc 推送已兑换的优惠卷给辰商
	 * @param: @param couponsDetial
	 * @param: @return  
	 * @return: JsonObject      
	 * @throws
	 */
    private void postHasPhiCouponsDetailToChengShang(List<PhiCouponsDetail> couponsDetialList) { 
    	log.error(couponsDetialList.size()+"|推送优惠劵开始.....................)");
    	if(null != couponsDetialList && couponsDetialList.size() > 0){
    		for(PhiCouponsDetail couponsDetial:couponsDetialList){
//        		log.error(couponsDetial.toString());
//        		log.error(couponsDetial.getMemberId().getTel() + "推送优惠劵开始.....................)");
        		JsonObject sss =  null;
           	 	try{
    	       	 	JsonObject jo = new JsonObject();
    	       	 	jo.addProperty("uid", couponsDetial.getMemberId().getUId());
    	       	 	jo.addProperty("cpns_id", couponsDetial.getCoupWayId());//fangan id
    	       	 	jo.addProperty("code", couponsDetial.getCoupCode());
    	       	 	sss =  PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"bindcoupon", "bindcoupon",new Gson().toJson(jo));
    	       	 	
    	       	 	JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
    				String status = dataJson.getString("status");
           	 		/********绑定已赠送plus会员优惠卷成功*********/
    				CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
    				entityDto.setBusiTime(new Date());
    				entityDto.setCode("bindcoupon");
    				entityDto.setCreateTime(new Date());
    				entityDto.setRequestBody(jo.toString());
    				entityDto.setResult(status.equals("true")?200:0);
    				entityDto.setMsg(dataJson.getString("message"));
    				entityDto.setResponseBody(new Gson().toJson(sss.toString()));
    				cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
    				//如果推送失败，则取新的优惠劵再次推送
    				if(status.equals("false") && "优惠券码已被绑定".equals(dataJson.getString("message").trim())){
    					this.pushAndBindingNewPhiCouponsToChengShang(couponsDetial, true);
    				}
    			}catch(Exception e){
    				log.error(couponsDetial.getMemberId().getTel() + "推送优惠劵失败(接口名：bindcoupon)bindcoupon失败!)");
    				e.printStackTrace();
    			}
        	}
    	}
	}

    
	private void pushAndBindingNewPhiCouponsToChengShang(PhiCouponsDetail couponsDetial, boolean flag) {
		int count = 1;
		while(flag){
			PhiCouponsDetail newCouponsDetial = phiCouponsDetailService.getPhiCouponsDetailDtoByCoupWayId(couponsDetial.getCoupWayId());//根据方案ID查询优惠劵
			if(null != newCouponsDetial){
				newCouponsDetial.setMemberId(couponsDetial.getMemberId());
				newCouponsDetial.setExchangeStatus("1");
				phiCouponsDetailService.updatePhiCouponsDetailForOpenPlus(newCouponsDetial);
				JsonObject sss = null;
				try{
					JsonObject jo = new JsonObject();
					jo.addProperty("tel_number", newCouponsDetial.getMemberId().getTel());
		       	 	jo.addProperty("uid", newCouponsDetial.getMemberId().getUId());
		       	 	jo.addProperty("cpns_id", newCouponsDetial.getCoupWayId());//fangan id
		       	 	jo.addProperty("code", newCouponsDetial.getCoupCode());
		       	     //调用斐讯的接口得到jsonObject字符串
		       	 	sss =  PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"bindcoupon", "bindcoupon",new Gson().toJson(jo));		       	 	
		       	 	JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
					String status = dataJson.getString("status");
	       	 		/********绑定已赠送plus会员优惠卷成功*********/
					CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
					entityDto.setBusiTime(new Date());
					entityDto.setCode("bindcoupon");
					entityDto.setCreateTime(new Date());
					entityDto.setRequestBody(jo.toString());
					entityDto.setResult(status.equals("true")?200:0);
					entityDto.setMsg(dataJson.getString("message"));
					entityDto.setResponseBody(new Gson().toJson(sss.toString()));
					cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
					//如果推送失败，则取新的优惠劵再次推送
					log.error("推送是否成功："+status + "   循环次数：" + (count++));
					if(status.equals("false") && "优惠券码已被绑定".equals(dataJson.getString("message").trim())){
						pushAndBindingNewPhiCouponsToChengShang(newCouponsDetial,flag);
					}else{
						flag = false;
						return;
					}
				}catch(Exception e){
					log.error(couponsDetial.getMemberId().getTel() + "补推送优惠劵失败(接口名：bindcoupon)bindcoupon失败!)");
					e.printStackTrace();
				}
			}else{
				throw new BusinessRuntimeException("无可用优惠劵", "-1");
			}
		}
	}

	/**
	 * 手动补发和推送优惠劵
	 * @param couponsDetial
	 */
	@Override
	public void pushAndBindingSupplyPhiCouponsToChengShang(PhiCouponsDetail couponsDetial) {
		this.pushAndBindingNewPhiCouponsToChengShang(couponsDetial, true);
	}
    /**
     * 校验优惠券码
     */
	@Override
	public JsonObject toCheckThePlusCodeForChengShang(String code,String uid) {
		JsonObject sss = null;
		try {
			JsonObject jo = new JsonObject();
			jo.addProperty("pluscode", code);
			//现在和辰商的接口还没有调通暂时用这个代替
			Map<String, String> ss = new HashMap<>();
			ss.put("uid", uid);
			ss.put("coupon_code", code);
			sss =  PhiCommApiClient.requestFeiXunCommApi(PropertyUtil.getConfigValue("CHECKCODE_URL"),ss);				
			JSONObject dataJson = JSONObject.fromObject(sss.toString());	
			String status = dataJson.getString("status");
   	 		/********调用辰商的接口校验会员码是否成功*********/
			CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
			entityDto.setBusiTime(new Date());
			entityDto.setCode("checkpluscode");
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(jo.toString());
			entityDto.setResult(status.equals("true")?200:0);
			entityDto.setMsg(dataJson.getString("message"));
			entityDto.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
		} catch (Exception e) {
			ExceptionLogDto exceptionLogDto = new ExceptionLogDto();
			exceptionLogDto.setEcptMessage("会员码"+code);
			exceptionLogDto.setEcptModule("校验会员码");
			exceptionLogDto.setCreateTime(DateUtil.timeFormat.format(new Date()));
			exceptionLogDto.setAcctName(code);
			exceptionLogDto.setEcptCode("plus_code");
			exceptionLogDto.setEcptStack(e.toString());
//			exceptionLogService.saveExceptionLog(exceptionLogDto);
		}
		
		return sss;
	}
	
	/**
	 * 定时任务自动推送优惠劵
	 * @param couponsDetial
	 */
	public void taskPushAndBindingSupplyPhiCouponsToChengShang(List<PhiCouponsDetail> couponsDetialList) {
		this.postHasPhiCouponsDetailToChengShang(couponsDetialList);
	}
	
	
}
