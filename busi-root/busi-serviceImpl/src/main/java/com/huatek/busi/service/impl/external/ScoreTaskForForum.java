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
* @ClassName: ScoreTaskForForum 
* @Description: 社区接口调用获取积分
* @author eden_sun
* @date Feb 9, 2018 9:57:04 AM 
*
 */
@Service("scoreTaskForForum")
@Transactional
public class ScoreTaskForForum implements BusiQualityExternalForPiccomService{
	private Logger log = LoggerFactory
			.getLogger(ScoreTaskForForum.class);
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
		map.put("forPostedPoints", Constant.Points_Posted);
		map.put("forEssencePoints", Constant.Points_Essence);
		map.put("forReplyPoints", Constant.Points_Reply);
		map.put("forSurveyPoints", Constant.Points_Survey);
		return map;
	}

	@Override
	public ExternalWithDataResponse receiveData(String busiType, String appKey,
			String data, Date timestamp) {
		try{
		JsonObject jsonObject= null;
		log.error("社区接口调用："+data);
		try{
			jsonObject= new JsonParser().parse(data).getAsJsonObject();
		}catch(Exception e){
			e.printStackTrace();
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查请求data值是否符合json规范。");
		}
		
		PhiScoreTaskConfig pstc=scoreTaskService.findPhiScoreTaskConfigByCondition("forum");
		if(pstc==null){
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请将method值设置为正确的业务类别。");
		}
		StringBuffer sb=new StringBuffer();
		int uid=0;
		PhiMember member=new PhiMember();
		try{
			uid = jsonObject.get("UID").getAsInt();
			member = phiMemberService.findPhiMemberByUid(uid);
			}catch(Exception e){
				e.printStackTrace();
				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查接口是否包含参数 UID。");
			}
		
		String taskSwitch=pstc.getTaskSwitch();
		long score=0;
		//积分流水
		PhiScoreFlow entity=new PhiScoreFlow();
	
		//任务开启时执行积分获取操作
		if(taskSwitch.equalsIgnoreCase("on")){
			sb.append("{");
			int requestTime=TimeStampUtil.getIntTimeStamp(new Date());
			Set<PhiScoreConfigRule> scoreConfigSet=pstc.getScoreConfigRule();
			Iterator<PhiScoreConfigRule> pscrIterator=scoreConfigSet.iterator();
			String noralScore="";
			//遍历积分获取任务规则
			while(pscrIterator.hasNext()){
				PhiScoreConfigRule scoreConfigRule=pscrIterator.next();
				
				int startTime=0;
				int endTime=0;
				
			  if(scoreConfigRule.getExtensing().equals(busiType)){
					int ruleType= scoreConfigRule.getRuleTimeType();
					if(ruleType!=1){
						startTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getStartTime());
						endTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getEndTime());
					}
					String orderCode="";
					String modelName = "";
					//判断获取积分的时间是否符合任务规则项时间范围，ruleType为1永久，2为时间设置
					if((ruleType==2&&(requestTime>startTime&&requestTime<endTime))||ruleType==1){
						//奖励积分
						int gainScore=scoreConfigRule.getScoreValue1();
						//当日积分上限
						int maxScore=scoreConfigRule.getMaxScoreValue();
						
						//获取接口中用户当日以获取的总积分
						int totalscore = jsonObject.get("daily_totalscore").getAsInt();
						//本次积分获取后应该当日积分；
						int dailyScore=gainScore+totalscore;
						
						//奖励回复前几名
						int rank=0;
						if(busiType.equals(Constant.Points_Reply)){
							modelName = "论坛回复积分获取|";
							entity.setScoreAction("论坛回复积分获取");
							rank=scoreConfigRule.getScoreValue1Quantity();
							//获取当前回复人员的名次
							int currentRank = jsonObject.get("rank").getAsInt();
							if(currentRank<rank||currentRank==rank){
								if(dailyScore<maxScore||dailyScore==maxScore){
									score=gainScore;
								}else{
									return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "当日获取积分值已达设置上限");
								}
							}else{
								return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "回复名次超过设置范围");
							}
						}else{
							switch(busiType){
							case Constant.Points_Posted:
								modelName = "论坛发帖积分获取|";
								entity.setScoreAction("论坛发帖积分获取");
								break;
							case Constant.Points_Essence:
								modelName = "论坛加精积分获取|";
								entity.setScoreAction("论坛加精积分获取");
								break;
							case Constant.Points_Survey:
								modelName = "论坛问卷调研积分获取|";
								entity.setScoreAction("论坛问卷调研积分获取");
								break;
							}
							
							if(dailyScore<maxScore||dailyScore==maxScore){
								score=gainScore;
							}else{
								return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "当日获取积分值已达设置上限");
							}
						}
						
						
						sb.append("'value':'"+score+"'");
						
						if(member.getEnableScore()==null){
							member.setEnableScore(new BigDecimal(score).add(new BigDecimal(0)));	
						}else{
							member.setEnableScore(new BigDecimal(score).add(member.getEnableScore()));	
						}
						if(member.getAllScore()==null){
							member.setAllScore(new BigDecimal(score).add(new BigDecimal(0))); 
						}else{
							member.setAllScore(new BigDecimal(score).add(member.getAllScore())); 
						}
						
						phiMemberService.saveOrUpdatePhiMember(member);
						//签到、商城支付、评论、完善个人资料、分享 积分发生变化，给商城推送会员信息
						phiMemberService.pullMemberInfoToChenShang(member,modelName);
						
						sb.append("}");
						entity.setCreateTime(new Date());
						entity.setMemberId(member.getId());
						entity.setOrderCode(orderCode);
						entity.setScoreType("gain");
						entity.setSourcePlatform("phicomm_mall");
						entity.setScore(new BigDecimal(score));
						entity.setTaskId(pstc.getId());
						entity.setTaskName(pstc.getTaskName());
						try{
							phiScoreFlowService.savePhiScoreFlow(entity);
						}
						catch(Exception e){
							e.printStackTrace();
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分获取失败");
						}
					}else{
						return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "不符合积分规则设置的时间段");
					}
			  }
			}
			
		}else{
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "该积分规则已关闭");
		}
		return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "积分获取成功",sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @author eden  
	 * @date Jan 10, 2018 8:21:39 PM
	 * @desc 生成两数字之间的随机数
	 * @param: @param min
	 * @param: @param max
	 * @param: @return  
	 * @return: int      
	 * @throws
	 */
	public static int getRandomNum(int min,int max){  
	     Random rdm = new Random();  
	     return rdm.nextInt(max-min+1)+min;  
	} 
	
}
