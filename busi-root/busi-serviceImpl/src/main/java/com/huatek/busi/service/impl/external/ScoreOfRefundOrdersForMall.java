package com.huatek.busi.service.impl.external;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.phicom.member.PhiMemberPrivilegeDao;
import com.huatek.busi.dao.phicom.score.PhiScoreFlowDao;
import com.huatek.busi.dto.external.ExternalWithDataResponse;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.member.PhiMemberPrivilege;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRight;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightDetails;
import com.huatek.busi.model.phicom.score.PhiScoreConfigRule;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.busi.service.external.BusiQualityExternalForPiccomService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightService;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.busi.service.phicom.score.ScoreTaskService;
import com.huatek.frame.core.util.TimeStampUtil;
import com.huatek.frame.handle.LoginCheckFilter;

/**
 * 
* @ClassName: ScoreOfRefundOrdersForMall 
* @Description: 退款订单积分处理
* @author eden_sun
* @date Mar 14, 2018 1:48:39 PM 
*
 */
@Service("scoreOfRefundOrdersForMall")
@Transactional
public class ScoreOfRefundOrdersForMall implements BusiQualityExternalForPiccomService{
	private Logger log = LoggerFactory
			.getLogger(ScoreOfRefundOrdersForMall.class);
	@Autowired
	ScoreTaskService scoreTaskService;
	@Autowired
	PhiScoreFlowDao phiScoreFlowDao;
	@Autowired
	PhiScoreFlowService phiScoreFlowService;
	@Autowired
    PhiMemberService phiMemberService;
	@Autowired
	PhiPlusRightService phiPlusRightService;
	@Autowired
	PhiMemberPrivilegeDao phiMemberPrivilegeDao;
	
	@Override
	public Map<String, String> getType() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("forRefundPoints","forRefundPoints");
		return map;
	}

	@SuppressWarnings("unused")
	@Override
	public ExternalWithDataResponse receiveData(String busiType, String appKey,
			String data, Date timestamp) {
		JsonObject jsonObject= new JsonObject();
		JsonObject jsonObjectToPhi= new JsonObject();
		int uid=0;
		String orderCode="";
		String action="";
		String refundCode="" ;
		String orderPrice="";
		String refundMoney="";
		PhiScoreFlow entity=new PhiScoreFlow();
		 PhiMember member=new PhiMember();
		log.error("退款订单积分处理-接口调用："+data);
		try{
			jsonObject= new JsonParser().parse(data).getAsJsonObject();
		}catch(Exception e){
			e.printStackTrace();
			log.debug("ScoreOfRefundOrdersForMall:json is error");
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查请求data值是否符合json规范。");
		}
		try{
			uid=jsonObject.get("UID").getAsInt();
			orderCode=jsonObject.get("orderCode").getAsString();
			action=jsonObject.get("action").getAsString();
			orderPrice=jsonObject.get("orderPrice").getAsString();
			//退款单号
			if(jsonObject.get("refundCode").isJsonNull()){
				refundCode = null;
			}else{
				refundCode = jsonObject.get("refundCode").getAsString();
			}
			//退款金额
			if(jsonObject.get("refundPrice").isJsonNull()){
				refundMoney = null;
			}else{
				refundMoney = jsonObject.get("refundPrice").getAsString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.debug("ScoreOfRefundOrdersForMall:UID and orderCode error");
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "UID and orderCode error！请检查UID、action and orderCode是否符合规范。");
		}
		
		member = phiMemberService.findPhiMemberByUid(uid);
		long memberId=member.getId();
		PhiScoreFlow pscoreflow=phiScoreFlowService.findPhiScoreFlowByCondition(memberId,orderCode);
		if(pscoreflow==null){
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "未找到该用户订单积分获取记录");
		}
//		if(pscoreflow.getIsRefund() == 1){
//			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分扣除失败:该订单已经退过款，并扣除过消费获得积分");
//		}
		//查询是否该单是否退过款
		if(null != pscoreflow.getIsRefund() && pscoreflow.getIsRefund() == 1){
			if(null!=refundCode&&!"".equals(refundCode)){
				PhiScoreFlow rscoreflow=phiScoreFlowService.findPhiScoreFlowByRCondition(memberId,refundCode);
				if(null!=rscoreflow){
					return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "积分扣除失败:该商品已经退过款，并扣除过消费获得积分");
				}
			}
		}
		
		Date enabelTime=pscoreflow.getEnableTime();
		if(action.equals("apply")){
			Calendar c=Calendar.getInstance();
			c.setTime(enabelTime);
			c.add(Calendar.DAY_OF_MONTH, 15);
			pscoreflow.setEnableTime(c.getTime());
			phiScoreFlowService.savePhiScoreFlow(pscoreflow);
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "积分解冻时间已经顺延15天,解冻时间为:"+c.getTime());
		}else{
			if(null==refundCode&&"".equals(refundCode)){
				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "确认退款时退款单号不能为空！");
			}
			if(null==refundMoney&&"".equals(refundMoney)){
				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "确认退款退款金额不能为空！");
			}
			Date date=new Date();
			int compareV=enabelTime.compareTo(date);
			if(compareV>0||compareV==0){
				BigDecimal score = refundConsumScore(refundMoney, pscoreflow, orderPrice);//获得扣除积分数
				entity.setScoreAction("商城消费退款");
				entity.setOrderCode(orderCode);
				entity.setIsEnable(0);
				entity.setIsRefund(1);
				entity.setEnableTime(enabelTime);
				entity.setCreateTime(date);
				entity.setMemberId(memberId);
				entity.setRefundCode(refundCode);
				entity.setScoreType("consume");
				entity.setSourcePlatform("phicomm_mall");
				entity.setTaskId(pscoreflow.getTaskId());
				//退款相关信息
				entity.setScore(score);
				entity.setRefundCode(refundCode);
				entity.setRefundMoney(new BigDecimal(refundMoney));
				entity.setBirthdayType(pscoreflow.getBirthdayType());
				entity.setBirthdayValue(pscoreflow.getBirthdayValue());
				entity.setMemberPowerType(pscoreflow.getMemberPowerType());
				entity.setMemberPowerValue(pscoreflow.getMemberPowerValue());
				entity.setPlusPowerType(pscoreflow.getPlusPowerType());
				entity.setPlusPowerValue(pscoreflow.getPlusPowerValue());
				entity.setTaskName("退款扣除积分");
				member.setDisableScore(member.getDisableScore().subtract(score));
				try{
					phiScoreFlowService.savePhiScoreFlow(entity);
					
					pscoreflow.setIsRefund(1);
					phiScoreFlowService.savePhiScoreFlow(pscoreflow);
					
				}catch(Exception e){
					e.printStackTrace();
					log.debug("ScoreOfRefundOrdersForMall:savePhiScoreFlow error"+e.getMessage());
					return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分扣除失败");
				}
				jsonObjectToPhi.addProperty("value",score);
				phiMemberService.pullMemberInfoToChenShang(member,"退款同步积分|");
				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "积分扣除成功",jsonObjectToPhi.toString());
			}else{
				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分扣除失败:该订单已超过冻结周期，不能扣除积分");
			}
		//如果解冻积分时间大于当前时间，才可以退款
//		Date date=new Date();
//		int compareV=enabelTime.compareTo(date);
//		if(compareV>0||compareV==0){
//			entity.setScoreAction("商城消费退款");
//			entity.setOrderCode(orderCode);
//			entity.setIsEnable(0);
//			entity.setIsRefund(1);
//			entity.setEnableTime(enabelTime);
//			entity.setCreateTime(date);
//			entity.setMemberId(memberId);
//			entity.setOrderCode(orderCode);
//			entity.setScoreType("consume");
//			entity.setSourcePlatform("phicomm_mall");
//			entity.setScore(pscoreflow.getScore());
//			entity.setTaskId(pscoreflow.getTaskId());
//			entity.setTaskName("退款扣除积分");
//			member.setDisableScore(member.getDisableScore().subtract(pscoreflow.getScore()));
//			try{
//				phiScoreFlowService.savePhiScoreFlow(entity);
//				
//				pscoreflow.setIsRefund(1);
//				phiScoreFlowService.savePhiScoreFlow(pscoreflow);
//				
//			}catch(Exception e){
//				e.printStackTrace();
//				log.debug("ScoreOfRefundOrdersForMall:savePhiScoreFlow error"+e.getMessage());
//				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分扣除失败");
//			}
//			jsonObjectToPhi.addProperty("value",pscoreflow.getScore());
//			phiMemberService.pullMemberInfoToChenShang(member,"退款同步积分|");
//			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "积分扣除成功",jsonObjectToPhi.toString());
//		}else{
//			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分扣除失败:该订单已超过冻结周期，不能扣除积分");
//		}
		}
		
	}
	public BigDecimal refundConsumScore(String refundMoney,PhiScoreFlow pscoreflow,String orderPrice){
		int  proportion = pscoreflow.getProportion();//1元=？积分值
		String memberPowerType = pscoreflow.getMemberPowerType();//会员特权类型（0翻倍 1额外）
		String memberPowerValue = pscoreflow.getMemberPowerValue();//会员特权设定值
		String plusPowerType = pscoreflow.getPlusPowerType();//plus会员特权类型（0翻倍 1额外）
		String plusPowerValue = pscoreflow.getPlusPowerValue();//plus会员特权设定值
		String birthdayType = pscoreflow.getBirthdayType();//生日会员特权类型（0翻倍 1额外）
		String birthdayValue = pscoreflow.getBirthdayValue();//生日会员特权设定值
		//涉及的积分值
		BigDecimal score = new BigDecimal(0);
		BigDecimal memberScore = new BigDecimal(0);//会员特权积分
		BigDecimal plusScore = new BigDecimal(0);//plus特权积分
		BigDecimal birthScore = new BigDecimal(0);//生日特权积分
		//1计算基数值
		BigDecimal brefundMoney = new BigDecimal(refundMoney);//退款金额
		BigDecimal bproportion = new BigDecimal(proportion);//兑换比例
		BigDecimal baseScore = brefundMoney.multiply(bproportion);//推荐基础积分 = 推荐金额 * 兑换比例
		//会员特权
		if(StringUtils.isNotEmpty(memberPowerType)){
			//0额外1翻倍
			if(null!=memberPowerValue&&memberPowerValue.length()>0){
				//1翻倍
				if("1".equals(memberPowerType.trim())){
					memberScore = doubleScore(memberPowerValue,baseScore);
				}else{
					memberScore = extraScore(refundMoney,memberPowerValue,orderPrice,baseScore);
				}
				
			}
		}
		//plus会员特权
		if(StringUtils.isNotEmpty(plusPowerType)){
			//0额外1翻倍
			if(null!=plusPowerValue&&plusPowerValue.length()>0){
				//1翻倍
				if("1".equals(plusPowerType.trim())){
					plusScore = doubleScore(plusPowerValue,baseScore);
				}else{
					plusScore = extraScore(refundMoney,plusPowerValue,orderPrice,baseScore);
				}
				
			}
		}
		//取会员和plus会员中的高值
		if(memberScore.compareTo(plusScore)>0){
			score=memberScore;
		}else{
			score=plusScore;
		}
		
		//生日值
		if(StringUtils.isNotEmpty(birthdayType)){
			//0额外1翻倍
			if(null!=birthdayValue&&birthdayValue.length()>0){
				//1翻倍
				if("1".equals(birthdayType.trim())){
					birthScore = doubleScore(birthdayValue,baseScore);
				}else{
					birthScore = extraScore(refundMoney,birthdayValue,orderPrice,baseScore);
				}
				
			}
		}
		//计算完的分数
		score.add(birthScore);
		return score;
		
	}
	//用于计算翻倍类型的分数
	public BigDecimal doubleScore(String value,BigDecimal baseScore){
		BigDecimal douleValue = new BigDecimal(value);
		BigDecimal moneyScore = baseScore.multiply(douleValue);
		return moneyScore.setScale(0,BigDecimal.ROUND_HALF_UP);
	}
	
	//用于计算额外类型的分数
	public BigDecimal extraScore(String refundMoney,String value,String orderPrice,BigDecimal baseScore){
		BigDecimal extraValue = new BigDecimal(value);
		BigDecimal money = new BigDecimal(refundMoney);
		BigDecimal borderPrice = new BigDecimal(orderPrice);
		BigDecimal moneyScore = money.divide(borderPrice,BigDecimal.ROUND_HALF_UP).multiply(extraValue).add(baseScore);
		return moneyScore.setScale(0,BigDecimal.ROUND_HALF_UP);
	}
	
	public static void main(String[] agrs){
		ScoreOfRefundOrdersForMall t = new ScoreOfRefundOrdersForMall();
		//{"UID":"1232389","orderCode":"3117170749477","action":"confirm","orderPrice":"0.030","refundCode":"2031181624052","refundPrice":"0.020"}
		BigDecimal r = t.extraScore("0.020", "11", "0.030", new BigDecimal("20"));
		System.out.println(r.toString());
		BigDecimal money = new BigDecimal("0.020");
		BigDecimal borderPrice = new BigDecimal("0.030");
		BigDecimal r2 = money.divide(borderPrice,BigDecimal.ROUND_HALF_UP);
		System.out.println(r2.toString());
	}
}
