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

@Service("scoreTaskForMall")
@Transactional
public class ScoreTaskForMall implements BusiQualityExternalForPiccomService{
	private Logger log = LoggerFactory.getLogger(ScoreTaskForMall.class);
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
		map.put("forPayPoints", Constant.Points_Pay);
		map.put("forAppraisePoints", Constant.Points_Appraise);
		map.put("forCheckinPoints", Constant.Points_Checkin);
		map.put("forSharePoints", Constant.Points_Share);
		map.put("forMInfoPoints", Constant.Points_MInfo);
		map.put("forAuthPoints", Constant.Points_Auth);
		map.put("forInviteePoints", Constant.Points_Invitee);
		map.put("forBindPoints", Constant.Points_Bind);
		map.put("register", Constant.Points_register);
		return map;
	}

	@Override
	public ExternalWithDataResponse receiveData(String busiType, String appKey,
			String data, Date timestamp) {
		try{
		JsonObject jsonObject= null;
		log.error("scoreTaskForMall-接口调用："+data);
		try{
			jsonObject= new JsonParser().parse(data).getAsJsonObject();
		}catch(Exception e){
			e.printStackTrace();
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查请求data值是否符合json规范。");
		}
		
		PhiScoreTaskConfig pstc=scoreTaskService.findPhiScoreTaskConfigByCondition(busiType);
		if(pstc==null){
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请将method值设置为正确的业务类别。");
		}
		StringBuffer sb=new StringBuffer();
		int uid=0;
		boolean isPlus=false;
		boolean isBirthday=false;
		PhiMemberPrivilege birthdayPrivilege=null;
		PhiMemberPrivilege gradePrivilege=null;
		Date date=new Date();
		 PhiMember member=new PhiMember();
		try{
			uid = jsonObject.get("UID").getAsInt();
			 member = phiMemberService.findPhiMemberByUid(uid);
			String isplusMember= member.getIsplusMember();
			if(isplusMember.equals("1")){
				isPlus=true;
			}
			if(member.getPhiMembergrade()!=null){
				gradePrivilege=phiMemberPrivilegeDao.findPhiMemberPrivilegeByMemberGradeId(member.getPhiMembergrade().getId());
			}
			if(member.getBirthday()!=null){
				Calendar birthday=Calendar.getInstance();
				birthday.setTime(member.getBirthday());
				Calendar today=Calendar.getInstance();
				if(today.get(Calendar.MONTH)==birthday.get(Calendar.MONTH)&&today.get(Calendar.DATE)==birthday.get(Calendar.DATE)){
					isBirthday=true;
					birthdayPrivilege=phiMemberPrivilegeDao.findPhiMemberBirthdayPrivilege();
				}
			}
			
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
				int ruleType= scoreConfigRule.getRuleTimeType();
				if(ruleType!=1){
					startTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getStartTime());
					endTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getEndTime());
				}
				String orderCode="";
				String modelName = "";
				//判断获取积分的时间是否符合任务规则项时间范围，ruleType为1永久，2为时间设置
				if((ruleType==2&&(requestTime>startTime&&requestTime<endTime))||ruleType==1){
					switch (busiType) {
					case Constant.Points_Pay :     //支付积分获取
						modelName = "支付积分获取|";
						JsonObject orderInfo=null;
						Double payTotal=0.0;
						try{
//							String payType = jsonObject.get("paypoint_type").getAsString();
							orderInfo = jsonObject.get("order_info").getAsJsonObject();
							payTotal=orderInfo.get("price_total").getAsDouble();
//							JsonArray addOptions = jsonObject.get("additional_options").getAsJsonArray();	
						}catch(Exception e){
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查支付获取积分接口是否包含参数 paypoint_type ,order_info,additional_options。");
						}
						
						Long basescore=Math.round(scoreConfigRule.getScoreValue1()*payTotal);
						//存消费规则
						entity.setProportion(scoreConfigRule.getScoreValue1());
						score=basescore;
						noralScore+="{'name':'普通积分','value':'"+basescore+"'}";
						entity.setScoreAction("支付积分获取");
						long gradeScore=0;
						String gradeMessage=""; 
						if(gradePrivilege!=null){
							if(gradePrivilege.getDoubleSet()!=null&&gradePrivilege.getDoubleSet().doubleValue()>1){
								gradeScore=Math.round(basescore*gradePrivilege.getDoubleSet().doubleValue());
								gradeMessage="{'name':'会员特权','value:'积分翻倍"+gradePrivilege.getDoubleSet()+"'}";
								//存储会员特权规则(//0额外1翻倍)
								String gradeStr = ""+gradePrivilege.getDoubleSet();
								entity.setMemberPowerType("1");
								entity.setMemberPowerValue(gradeStr);
							}else{
								if(null != gradePrivilege.getExtraAdd()){
									gradeScore=basescore+gradePrivilege.getExtraAdd().longValue();
									gradeMessage="{'name':'会员特权','value:'额外获得"+gradePrivilege.getExtraAdd()+"'}";
									//存储会员特权规则(//0额外1翻倍)
									String extra = gradePrivilege.getExtraAdd()+"";
									entity.setMemberPowerType("0");
									entity.setMemberPowerValue(extra);
								}
							}
						}
						//无特权规则，则普通会员取 购买商品计算的积分 2018-01-31 修改 ---------start
						else{
							gradeScore = basescore;
						};
						//--------------end------------------
						long plusScore=0;
						String plusMessage=""; 
						if(isPlus){
							PhiPlusRight plusMember = null;
							try{
							plusMember = phiPlusRightService.findPhiPlusRightByCondition(busiType);
							}catch(Exception e){
								return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "phiPlusRightService.findPhiPlusRightByCondition(busiType)查询出错!");	
							}
							if(plusMember.getTaskswitch().equals("on")){
								PhiPlusRightDetails pprd=plusMember.getPlusRightDetail();
								int scoreOrMutiply=pprd.getScoreOrMutiply();
								if(scoreOrMutiply==0){
									int mutiply=pprd.getScoreValue2();
									plusScore=basescore*mutiply;
									plusMessage="{'name':'plus特权','value:'积分翻倍"+mutiply+"'}";
									//存储会员特权规则(//0额外1翻倍)
									String mutiplystr = ""+mutiply;
									entity.setPlusPowerType("1");
									entity.setPlusPowerValue(mutiplystr);
								}else{
									int scoreV=pprd.getScoreValue1();
									plusScore=basescore+scoreV;	
									plusMessage="{'name':'plus特权','value:'额外获得"+scoreV+"'}";
									//存储会员特权规则(//0额外1翻倍)
									String scoreVstr = ""+scoreV;
									entity.setPlusPowerType("0");
									entity.setPlusPowerValue(scoreVstr);
								}
							}
						
							
						}
						//普通会员和plus会员那个高去哪个
//						if(gradeScore>0&&plusScore>0){
							if(gradeScore>plusScore){
								score=gradeScore;
								noralScore+=","+gradeMessage;
								
							}else{
								score=plusScore;
								noralScore+=","+plusMessage;
							}
//						}
						//生日特权叠加
						if(isBirthday&&birthdayPrivilege!=null&&birthdayPrivilege.getPay()==1){
							if(birthdayPrivilege.getDoubleSet()!=null&&birthdayPrivilege.getDoubleSet().doubleValue()>1){
								score+=Math.round(basescore*birthdayPrivilege.getDoubleSet().doubleValue());
								noralScore="{'name':'生日特权','value:'积分翻倍"+birthdayPrivilege.getDoubleSet()+"'}";
								//存储生日特权规则(//0额外1翻倍)
								String birthValueStr = ""+birthdayPrivilege.getDoubleSet();
								entity.setBirthdayType("1");
								entity.setPlusPowerValue(birthValueStr);
							}else{
								score+=basescore+birthdayPrivilege.getExtraAdd().longValue();
								noralScore="{'name':'生日特权','value:'额外获得"+birthdayPrivilege.getExtraAdd().longValue()+"'}";
								//存储生日特权规则(//0额外1翻倍)
								String birExtr = ""+birthdayPrivilege.getExtraAdd();
								entity.setBirthdayType("0");
								entity.setPlusPowerValue(birExtr);
							}
						}
						if(orderInfo!=null){
							orderCode=orderInfo.get("ordercode").getAsString();	
						}else{
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！缺失ordercode。");
						}
						Calendar ca=Calendar.getInstance();
						ca.setTime(date);
						ca.add(Calendar.DAY_OF_MONTH, 10);
						entity.setOrderCode(orderCode);
						entity.setIsEnable(0);
						entity.setIsRefund(0);
						entity.setEnableTime(ca.getTime());
						sb.append("'value':'"+score+"','detail':["+noralScore+"]");
						break;
					case Constant.Points_Appraise:    //评论积分获取
						modelName = "评论积分获取|";
						log.error("===================开始评论获取积分=====================");
						String appraiseType=null;
						int rank=0;
						try{
							//评论类型，分为只评论(only_appraisepoint)、评论+晒图（showImage_appraisepoint）
							appraiseType=jsonObject.get("appraisepoint_type").getAsString();
							//商城传入的评论名次
							rank=jsonObject.get("rank").getAsInt();
							log.error("====================评论类型===================="+appraiseType);
							log.error("===================商城传入的评论名次===================="+rank);
						}catch(Exception e){
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查支付获取积分接口包含参数appraisepoint_type ,rank。");
						}
						
						if(appraiseType.equals("only_appraisepoint")){
							//设置积分1获取的名次
							int quantity=scoreConfigRule.getScoreValue1Quantity();
							log.error("+++++++++++++++++后台设置评论次数+++++++++++++++++"+quantity);
							//评论名次小于设置的评论人数则奖励积分
							if(rank<quantity||rank==quantity&&rank>0){						   
							score=scoreConfigRule.getScoreValue1();	
							log.error("------------------评论获取积分-------------------"+score);
							}else{
								return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "获取失败！评论名次超过设置的前"+quantity+"范围");
							}
						}else if(appraiseType.equals("showImage_appraisepoint")){
							int quantity=scoreConfigRule.getScoreValue2Quantity();
							if((rank<quantity||rank==quantity)&&rank>0){
								score=scoreConfigRule.getScoreValue2();		
							}else{
								return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "获取失败！评论名次超过设置的前"+quantity+"范围");
							}
						}else{
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错!请检查传入的评论类型（only_appraisepoint，showImage_appraisepoint）是否正确！");
						}
						noralScore="{'name':'普通积分','value':'"+score+"'}";
						//生日特权叠加
//						if(isBirthday&&birthdayPrivilege!=null&&birthdayPrivilege.getComment()==1){
//							if(birthdayPrivilege.getDoubleSet()!=null&&birthdayPrivilege.getDoubleSet().doubleValue()>1){
//								score=Math.round(score*birthdayPrivilege.getDoubleSet().doubleValue());
//								noralScore+="{'name':'生日特权','value:'积分翻倍"+birthdayPrivilege.getDoubleSet()+"'}";
//							}else{
//								score=score+birthdayPrivilege.getExtraAdd().longValue();
//								noralScore+="{'name':'生日特权','value:'额外获得"+birthdayPrivilege.getExtraAdd().longValue()+"'}";
//							}
//						}
						entity.setScoreAction("评论积分获取");
						sb.append("'value':'"+score+"','detail':["+noralScore+"]");
						break;
					case Constant.Points_Checkin :    //签到积分获取
						modelName = "签到积分获取|";
						int checkinDay=0;
						try{
							//获取签到天数
							checkinDay=jsonObject.get("checkin_day").getAsInt();
						}catch(Exception e){
							e.printStackTrace();
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查支付获取积分接口包含参数checkin_day。");
						}
						switch (checkinDay) {
						//签到天数
						case 1 :
							score=scoreConfigRule.getScoreValue1();	
							break;
						case 2 :
							score=scoreConfigRule.getScoreValue2();	
							break;
						case 3 :
							score=scoreConfigRule.getScoreValue3();	
							break;
						case 4 :
							score=scoreConfigRule.getScoreValue4();
							break;
						case 5 :
							score=scoreConfigRule.getScoreValue5();	
							break;
						case 6 :
							score=scoreConfigRule.getScoreValue6();	
							break;
						case 7 :
							score=scoreConfigRule.getScoreValue7();	
							int minScore = scoreConfigRule.getMinScoreValue();
							int maxScore=scoreConfigRule.getMaxScoreValue();
							getRandomNum(minScore,maxScore);
							break;
						default:
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "请输入正确的天数，7天为一个周期。");
						}
						entity.setScoreAction("签到积分获取");
						noralScore="{'name':'普通积分','value':'"+score+"'}";
						//生日特权叠加
						if(isBirthday&&birthdayPrivilege!=null&&birthdayPrivilege.getCheckIn()==1){
							if(birthdayPrivilege.getDoubleSet()!=null&&birthdayPrivilege.getDoubleSet().doubleValue()>1){
								score=Math.round(score*birthdayPrivilege.getDoubleSet().doubleValue());
								noralScore+="{'name':'生日特权','value:'积分翻倍"+birthdayPrivilege.getDoubleSet()+"'}";
							}else{
								score=score+birthdayPrivilege.getExtraAdd().longValue();
								noralScore+="{'name':'生日特权','value:'额外获得"+birthdayPrivilege.getExtraAdd().longValue()+"'}";
							}
						}
						entity.setScoreAction("评论积分获取");
						sb.append("'value':'"+score+"','detail':["+noralScore+"]");
						break;
					case  Constant.Points_Share :     //分享积分获取
					log.error("-=-=-=-=-=-正在分享积分获取=-=-=-=-=-=-=-=");
						modelName = "分享积分获取|";
						//分享次数
						int shareCount=0;
						try{
							//分享次数
							shareCount=jsonObject.get("share_count").getAsInt();
							log.error("=============分享次数==============="+shareCount);
						}catch(Exception e){
							e.printStackTrace();
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查支付获取积分接口包含参数share_count。");
						}
						//奖励积分
						int value =scoreConfigRule.getScoreValue1();
						log.error("-+-+-+-+-+-+-+-+奖励积分==============="+value);
						//获取单日奖励上限
						int maxValue =scoreConfigRule.getMaxScoreValue();
						log.error("<<<<<<<<<<<<<<<<<单日奖励上限<<<<<<<<<<<<"+maxValue);
//						if((shareCount*value<maxValue ||shareCount*value==maxValue)&&(shareCount<3&&shareCount>0)){
						if((shareCount*value <= maxValue)){
							score = value;
						log.error("################获得积分################"+score);
						}else{
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分获取失败，每日分享积分上限为"+maxValue+",最大分享次数为2");
						}
						sb.append("'value':'"+score+"'");
						entity.setScoreAction("分享积分获取");
						break;
					case  Constant.Points_MInfo:   //个人信息积分获取
						log.error("++++++++++++++正在修改个人信息+++++++++++++++++++++");
						int isFinishInfoTask=member.getIsFinishInfoTask();
						if(isFinishInfoTask==0){
							modelName = "个人信息修改积分获取|";
							//会员信息维度
							 JsonArray memberDimensions = null;
							 
							try{
								//会员信息维度
								 memberDimensions = jsonObject.get("Minfo_dimension").getAsJsonArray();
								 log.error("=========修改内容=====json===="+memberDimensions);
							}catch(Exception e){
								return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查支付获取积分接口包含参数Minfo_dimension。");
							}
							
							//获取配置的扩展项
						   String  extensing =scoreConfigRule.getExtensing();
						   String[] extensingOptions = extensing.split("\\|");
						   
						   ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(extensingOptions));
						   Iterator<JsonElement> it = memberDimensions.iterator();
						   while(it.hasNext()){
							   JsonElement option= it.next();
							   JsonObject infoOption= option.getAsJsonObject();
							   String infoCode = infoOption.get("code").getAsString();
							   if(arrayList.contains(infoCode)){
								   arrayList.remove(infoCode);
							   }
						   }
						   log.error("=======后台配置=====arrayList===="+arrayList);
						   if(arrayList.size()==0){
							   score=scoreConfigRule.getScoreValue1();
						   log.error("=======获得的积分=====score===="+score);	   
						   }else{
							   return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "获取积分失败！请检查是否满足积分获取条件。");    
						   }
							entity.setScoreAction("个人信息完善积分获取");
						   sb.append("'value':'"+score+"'");
						}else{
							  return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "获取积分失败！单个用户只能通过修改个人信息获取一次。");    
						}
						member.setIsFinishInfoTask(1);
					   break;
					case Constant.Points_Auth:  //实名认证积分获取
						modelName = "实名认证积分获取|";
						score=scoreConfigRule.getScoreValue1();	 
						entity.setScoreAction("实名认证积分获取");
						 sb.append("'value':'"+score+"'");
						break;
					case Constant.Points_Invitee: //邀请注册积分获取
						log.error("#############正在邀请好友注册###########");	
						modelName = "邀请注册积分获取|";
						int inviteeCount=0;
						try{
							inviteeCount=jsonObject.get("invitee_count").getAsInt();
							log.error("++++++++++++邀请好友注册json+++++++++"+inviteeCount);	
						}catch(Exception e){
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查支付获取积分接口包含参数invitee_count。");
						}
							//奖励积分
							int value1 =scoreConfigRule.getScoreValue1();
							//获取单日奖励上限
							int maxValue1 =scoreConfigRule.getMaxScoreValue();
							log.error("++++++++++++邀请好友注册++单日奖励上限+++++++"+maxValue1);
							if(inviteeCount*value1<maxValue1||inviteeCount*value1==maxValue1){
								score=value1;
						   log.error("++++++++++++邀请好友注册++获得score+++++++"+score);
							}else{
								return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分获取失败，每日邀请注册积分上限为"+maxValue1);
							}
							sb.append("'value':'"+score+"'");
							entity.setScoreAction("邀请注册积分获取");
							break;
					case Constant.Points_Bind:   //第三方账户积分获取
						modelName = "第三方账户积分获取|";
						//会员信息维度
						 JsonArray bindOptions = null;
						 boolean isExist=false;
						 
					 try{
						 bindOptions = jsonObject.get("bind_options").getAsJsonArray();
						}catch(Exception e){
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "校验出错！请检查支付获取积分接口包含参数bind_options。");
						}
						 
						//获取配置的扩展项
					   String  bExtensing =scoreConfigRule.getExtensing();
					   String[] bindExtentOpts = bExtensing.split("\\|");
					   ArrayList<String> optsList = new ArrayList<String>(Arrays.asList(bindExtentOpts));
					   Iterator<JsonElement> bindIt = bindOptions.iterator();
					   while(bindIt.hasNext()){
						   JsonElement option= bindIt.next();
						   JsonObject infoOption= option.getAsJsonObject();
						   String infoCode = infoOption.get("code").getAsString();
						   if(optsList.contains(infoCode)){
//							   optsList.remove(infoCode);
							   isExist=true;
							   break;
						   }
					   }
					   
					   if(isExist){
						   score=scoreConfigRule.getScoreValue1();	  
					   }else{
						   return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "获取积分失败！请检查是否满足积分获取条件。");  
					   }
					   sb.append("'value':'"+score+"'");
					   entity.setScoreAction("第三方账户绑定积分获取");
					   break;
					case Constant.Points_register://注册获取积分
						
						modelName = "注册获取积分|";		
						score=scoreConfigRule.getScoreValue1();	
						entity.setScoreAction("注册获取积分");
						sb.append("'value':'"+score+"'");																		
						break;   
					default:
						System.out.println("参数 \"operateType\" 校验出错");
						return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "参数 \"operateType\" 校验出错。");
					}
					sb.append("}");
					if(!busiType.equals(Constant.Points_Pay)){
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
					}else{
						member.setDisableScore(new BigDecimal(score).add(member.getDisableScore()));
						phiMemberService.saveOrUpdatePhiMember(member);
						PhiScoreFlow psf=phiScoreFlowService.findPhiScoreFlowByCondition(member.getId(),entity.getOrderCode() );
						if(psf!=null){
							return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分获取失败,该订单已获取过积分，请勿重复请求");
						}
					}
					
					//签到、商城支付、评论、完善个人资料、分享 积分发生变化，给商城推送会员信息
					phiMemberService.pullMemberInfoToChenShang(member,modelName);
					
					entity.setCreateTime(date);
					entity.setMemberId(member.getId());
					entity.setOrderCode(orderCode);
					entity.setScoreType("gain");
					entity.setSourcePlatform("phicomm_mall");
					entity.setScore(new BigDecimal(score));
					entity.setTaskId(pstc.getId());
					entity.setTaskName(pstc.getTaskName());
					try{
						phiScoreFlowService.savePhiScoreFlow(entity);
					}catch(Exception e){
						e.printStackTrace();
						return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "积分获取失败");
					}
				}else{
					return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "不符合积分规则设置的时间段");
				}
				
			}
			
		}else{
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "该积分规则已关闭");
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
	
	public static void main(String[] arg){
	BigDecimal ss=new BigDecimal("20");
	BigDecimal s1=BigDecimal.valueOf(2.6396);
	if("".length()>0){
		System.out.println(1);
	}else{
		System.out.println(0);
	}
	System.out.println(ss);
	System.out.println(s1.setScale(0,BigDecimal.ROUND_HALF_UP));
	}
	
}
