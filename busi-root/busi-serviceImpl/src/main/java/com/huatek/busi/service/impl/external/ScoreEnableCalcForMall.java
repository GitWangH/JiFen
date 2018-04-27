package com.huatek.busi.service.impl.external;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
@Service("scoreEnableCalcForMall")
@Transactional
public class ScoreEnableCalcForMall implements BusiQualityExternalForPiccomService{
	private Logger log = LoggerFactory
			.getLogger(ScoreEnableCalcForMall.class);
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
		map.put("calcEnableScore","calcEnableScore");
		return map;
	}

	@Override
	public ExternalWithDataResponse receiveData(String busiType, String appKey,
			String data, Date timestamp) {
		JsonObject jsonObject= new JsonObject();
		JsonObject jsonObjectToPhi=new JsonObject();
		int uid=0;
		 PhiMember member=new PhiMember();
		log.error("接口调用："+data);
		try{
			jsonObject= new JsonParser().parse(data).getAsJsonObject();
		}catch(Exception e){
			e.printStackTrace();
			log.debug("ScoreOfRefundOrdersForMall:json is error");
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查请求data值是否符合json规范。");
		}
		try{
			uid=jsonObject.get("UID").getAsInt();
		}catch(Exception e){
			e.printStackTrace();
			log.debug("ScoreOfRefundOrdersForMall:UID error");
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "UID and orderCode error！请检查UID 是否符合规范。");
		}
		
		member = phiMemberService.findPhiMemberByUid(uid);
		long memberId=member.getId();
		List<PhiScoreFlow> pscoreflow = phiScoreFlowService.findPhiScoreFlowByCondition(memberId);
		
		if(pscoreflow.size()>0){
			 Iterator<PhiScoreFlow>  it= pscoreflow.iterator();
			 BigDecimal score= BigDecimal.ZERO;
			while(it.hasNext()){
				PhiScoreFlow psf=it.next();
				psf.setIsEnable(1);
				score=score.add(psf.getScore());
				phiScoreFlowService.savePhiScoreFlow(psf);
			}
			
			BigDecimal enableScore= new BigDecimal(0);
			BigDecimal allScore= new BigDecimal(0);
			if(member.getEnableScore()==null){
				enableScore= enableScore.add(score);
			}else{
				enableScore= member.getEnableScore().add(score);
			}
			member.setDisableScore(member.getDisableScore().subtract(score));
			if(member.getAllScore()==null){
				allScore= allScore.add(score);
			}else{
				allScore= member.getAllScore().add(score);
			}
			
			member.setEnableScore(enableScore);
			member.setAllScore(allScore);
			phiMemberService.saveOrUpdatePhiMember(member);
			jsonObjectToPhi.addProperty("value", enableScore.add(member.getDisableScore()));
			phiMemberService.pullMemberInfoToChenShang(member,"冻结积分计算|");
		}else{
			jsonObjectToPhi.addProperty("value", member.getEnableScore().add(member.getDisableScore()));
		}
		phiMemberService.pullMemberInfoToChenShang(member,"冻结积分计算|");
		return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "计算完成",jsonObjectToPhi.toString());
		
	}
	
	
	
}
