package com.huatek.busi.api.phicom.member;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.phicom.member.PhiMemberDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.member.PhiMemberAddress;
import com.huatek.busi.model.phicom.score.PhiScoreConfigRule;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.busi.service.phicom.member.PhiMemberAddressService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.busi.service.phicom.score.ScoreTaskService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.cmd.category.util.JsonUtil;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.core.util.TimeStampUtil;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value =BusiUrlConstants.BUSIJFMEMBER_API)
public class PhiMemberAction {

    private static final Logger log = LoggerFactory .getLogger(PhiMemberAction.class);

    @Autowired
    private PhiMemberService phiMemberService;
    @Autowired
    private PhiOrderService phiOrderService;
    
    @Autowired
	private CmdFileMangerService cmdFileMangerService;
    @Autowired
	ScoreTaskService scoreTaskService;
    
	@Autowired
	PhiScoreFlowService phiScoreFlowService;
	
	@Autowired
	private InterfaceApiService interfaceApiService;//第三方接口Api服务类
	
	 @Autowired
	 private PhiMemberAddressService phiMemberAddressService;
    /** 
    * @Title: getAllPhiMember 
    * @Description:  翻页查询PhiMember信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiMemberDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiMemberDto>> getAllPhiMember(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiMember of param " + queryPage.getQueryInfo());
        DataPage<PhiMemberDto> phiMemberPages = phiMemberService.getAllPhiMemberPage(queryPage);
        log.debug("get phiMember size @" + phiMemberPages.getContent().size());
        return new ResponseEntity<>(phiMemberPages, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/queryPlusMember", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiMemberDto> getAllPlusPhiMember(HttpSession session) {
    	CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
        String uid=member.getUid();
        PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
        Long memberId =  phiMember.getId();
    	PhiMemberDto phiMemberDto = phiMemberService.getPlusPhiMemberDtoById(memberId);
    	int plusYears = 0;
    	/** @Fields plusOpenType :plus会员开通类型：firstOpen:首次开通；renewOpen:续费开通*/ 
    	String plusOpenType = phiMember.getPlusOpenType();
    	if("firstOpen".equals(plusOpenType)){
    		//首次开通按天展示
    		plusYears = phiMemberService.getPhiMemberPlusYear(memberId);
    	}else if("renewOpen".equals(plusOpenType)){
    		//续费按年展示
    		plusYears = phiMemberService.getPhiMemberPlusYear(memberId);
		}
    	if(phiMemberDto == null){
    		phiMemberDto = BeanCopy.getInstance().convert(phiMember, PhiMemberDto.class);
    	}
        return new ResponseEntity<PhiMemberDto>(phiMemberDto, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/memberScoreInfo")
    @ResponseBody
    public ResponseEntity<String> getMemberScoreInfo(HttpServletRequest request)  {
//    	CloudMember user = (CloudMember)request.getSession().getAttribute(SessionKey.currentMember);
//        PhiMemberDto phiMember = phiMemberService.getPhiMemberVOByUId(Integer.valueOf(user.getUid()));
    	Cookie[] cookie = request.getCookies();
		if(cookie!=null){
			for (Cookie cookie2 : cookie) {
				String jsession=cookie2.getName();
				System.out.println(jsession);
				if(jsession.equals("")){
				
				}
			}
		}
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/checkIn",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ResponseMessage> getAllPhiMember(HttpSession session)  { 	
    	CloudMember cloudmember = (CloudMember) session.getAttribute(SessionKey.currentMember);
        String uid=cloudmember.getUid();
        PhiMember member = phiMemberService.GetMemberPhiMemberByUid(Integer.valueOf(uid));
//        Long memberId =  phiMember.getId();
    	PhiScoreTaskConfig scoreTask = scoreTaskService.findPhiScoreTaskConfigByCondition(Constant.Points_Checkin);
    	Map<String,String> map=new HashMap<String,String>();
    	int score=0;
    	int requestTime=TimeStampUtil.getIntTimeStamp(new Date());
    	int checkIn_now=0;
//    	PhiMember member = phiMemberService.findPhiMemberById(memberId);
    	//判断是否为连续签到
	    Date nowDate = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(nowDate);  
	    calendar.add(Calendar.DAY_OF_MONTH, -1);//+1今天的时间加一天  （当前日期的前一天）
	    Date lastdate = calendar.getTime();
	    String nowDateString = sdf.format(lastdate);
	    String lastCheckString =sdf.format(new Date());
	    if(member.getLastCheckTime()!=null){
	    	lastCheckString=sdf.format(member.getLastCheckTime());
	    }
	   
    	 //判断用户今日是否签到
	     String requestString = sdf.format(nowDate);
   	
   		if(scoreTask.getTaskSwitch().equals("on")){
    		Set<PhiScoreConfigRule> taskRule = scoreTask.getScoreConfigRule();
     		Iterator<PhiScoreConfigRule> phiIterator = taskRule.iterator();
    		while(phiIterator.hasNext()){
    			PhiScoreConfigRule scoreConfigRule=phiIterator.next();
    			int startTime=0;
    			int endTime=0;
    			if(null!=scoreConfigRule.getStartTime()&&!"".equals(scoreConfigRule.getStartTime())){
    				startTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getStartTime());
    			}
    			if(null!=scoreConfigRule.getEndTime()&&!"".equals(scoreConfigRule.getEndTime())){
    				endTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getEndTime());
    			}
				int ruleType= scoreConfigRule.getRuleTimeType();
				//判断获取积分的时间是否符合任务规则项时间范围，ruleType为1永久，2为时间设置
				if(((requestTime>startTime&&requestTime<endTime)&&ruleType==2)||ruleType==1){
					int checkInDay =0;
					PhiScoreFlow entity=new PhiScoreFlow();
					if(null != member.getCheckInDay()){
						checkInDay = member.getCheckInDay();
					}
		    	     
		    	    checkIn_now=checkInDay+1;
		    	    //判断是否是第二次签到
		    	    if(requestString.equals(lastCheckString)&&(null!=member.getLastCheckTime())){
		    	    	//如果是第二次签到（签到天数为当前天）
		    	    	checkIn_now = checkInDay;
		    	    }else{
		    	    	//否则先判断是否是连续签到
		    	    	//是否连续签到
			    	    if(nowDateString.equals(lastCheckString)){
			    	    	 checkIn_now=checkInDay+1;
			    	    }else{
			    	    	checkIn_now = 1;
			    	    }
		    	    }
		    	    switch (checkIn_now) {
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
						break;
					default:
						checkIn_now=1;
						score=scoreConfigRule.getScoreValue1();	
					}
		    	    if(requestString.equals(lastCheckString)&&(null!=member.getLastCheckTime())&&checkIn_now==checkInDay){
		    	   		 map.put("isSignup", "true");//已签到设当前状态为已签到返给前台
		    	   		
		    	   	 }else{
		    	   		member.setIsDraw("no");//是否抽奖（yes:已抽奖，no:未抽奖）
		    	   		if(null==member.getEnableScore()||"".equals(member.getEnableScore())){
		    	   			member.setEnableScore(new BigDecimal(score));
		    	   		}else{
		    	   			member.setEnableScore(new BigDecimal(score).add(member.getEnableScore()));
		    	   		}
		    	   		//总积分相加
		    	   		if(null==member.getAllScore()||"".equals(member.getAllScore())){
		    	   			member.setAllScore(new BigDecimal(score));
		    	   		}else{
		    	   			member.setAllScore(new BigDecimal(score).add(member.getAllScore()));
		    	   		}
			    	    member.setCheckInDay(checkIn_now);
			    	    member.setLastCheckTime(new Date());//保存签到日期
			    	    phiMemberService.saveOrUpdatePhiMember(member);
			    		entity.setScoreAction("签到积分获取");
			    		entity.setTaskName("签到");
			    	    entity.setCreateTime(new Date());
						entity.setMemberId(member.getId());
						entity.setOrderCode("");
						entity.setScoreType("gain");
						entity.setSourcePlatform("phicomm_scoremall");
						entity.setScore(new BigDecimal(score));
						phiScoreFlowService.savePhiScoreFlow(entity);
						map.put("isSignup", "true");//已签到设当前状态为已签到返给前台
						//积分兑换 ，积分发生变化，给商城推送会员信息
						phiMemberService.pullMemberInfoToChenShang(member,"签到获取积分|");
		    	   	 }
			    		 
				}else {
			      return new ResponseEntity<>(ResponseMessage.warning(("签到失败")), HttpStatus.BAD_REQUEST);
				}
				
    		}
    	}else{
    		 return new ResponseEntity<>(ResponseMessage.warning(("签到功能尚未开启")), HttpStatus.OK);
    	}
   	 
    	//判断是否抽奖
    	if("yes".equals(member.getIsDraw())){
    		map.put("isDraw", "true");
    	}else{
    		map.put("isDraw", "false");
    	}
    	map.put("score", String.valueOf(score));

    	map.put("checkInDay",String.valueOf(checkIn_now));
        return new ResponseEntity<>(ResponseMessage.success(JsonUtil.map2json(map)), HttpStatus.OK);
       
    }
    //测试
//    @RequestMapping(value = "/checkIn/{id}",method = {RequestMethod.GET,RequestMethod.POST})
//	  @ResponseBody
//	  public ResponseEntity<ResponseMessage> getAllPhiMember(@PathVariable("id") Long id)  { 	
//	  	PhiScoreTaskConfig scoreTask = scoreTaskService.findPhiScoreTaskConfigByCondition(Constant.Points_Checkin);
//	  	Map<String,String> map=new HashMap<String,String>();
//	  	int score=0;
//	  	int requestTime=TimeStampUtil.getIntTimeStamp(new Date());
//	  	int checkIn_now=0;
//	  	PhiMember member = phiMemberService.findPhiMemberById(id);
//	  	//判断是否为连续签到
//		    Date nowDate = new Date();
//		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		    Calendar calendar = Calendar.getInstance();  
//		    calendar.setTime(nowDate);  
//		    calendar.add(Calendar.DAY_OF_MONTH, -1);//+1今天的时间加一天  （当前日期的前一天）
//		    Date lastdate = calendar.getTime();
//		    String nowDateString = sdf.format(lastdate);
//		    String lastCheckString = sdf.format(member.getLastCheckTime());
//	  	 //判断用户今日是否签到
//		     String requestString = sdf.format(nowDate);
//	 	
//	 		if(scoreTask.getTaskSwitch().equals("on")){
//	  		Set<PhiScoreConfigRule> taskRule = scoreTask.getScoreConfigRule();
//	   		Iterator<PhiScoreConfigRule> phiIterator = taskRule.iterator();
//	  		while(phiIterator.hasNext()){
//	  			PhiScoreConfigRule scoreConfigRule=phiIterator.next();
//	  			int startTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getStartTime());
//					int endTime=TimeStampUtil.getIntTimeStamp(scoreConfigRule.getEndTime());
//					int ruleType= scoreConfigRule.getRuleTimeType();
//					//判断获取积分的时间是否符合任务规则项时间范围，ruleType为1永久，2为时间设置
//					if(((requestTime>startTime&&requestTime<endTime)&&ruleType==2)||ruleType==1){
//						PhiScoreFlow entity=new PhiScoreFlow();
//			    	    int checkInDay = member.getCheckInDay();
//			    	    checkIn_now=checkInDay+1;
//			    	    //判断是否是第二次签到
//			    	    if(requestString.equals(lastCheckString)){
//			    	    	//如果是第二次签到（签到天数为当前天）
//			    	    	checkIn_now = checkInDay;
//			    	    }else{
//			    	    	//否则先判断是否是连续签到
//			    	    	//是否连续签到
//				    	    if(nowDateString.equals(lastCheckString)){
//				    	    	 checkIn_now=checkInDay+1;
//				    	    }else{
//				    	    	checkIn_now = 1;
//				    	    }
//			    	    }
//			    	    switch (checkIn_now) {
//						//签到天数
//						case 1 :
//							score=scoreConfigRule.getScoreValue1();	
//							break;
//						case 2 :
//							score=scoreConfigRule.getScoreValue2();	
//							break;
//						case 3 :
//							score=scoreConfigRule.getScoreValue3();	
//							break;
//						case 4 :
//							score=scoreConfigRule.getScoreValue4();
//							break;
//						case 5 :
//							score=scoreConfigRule.getScoreValue5();	
//							break;
//						case 6 :
//							score=scoreConfigRule.getScoreValue6();	
//							break;
//						case 7 :
//							score=scoreConfigRule.getScoreValue7();	
//							break;
//						default:
//							checkIn_now=1;
//							score=scoreConfigRule.getScoreValue1();	
//						}
//			    	    if(requestString.equals(lastCheckString)){
//			    	   		 map.put("isSignup", "true");//已签到设当前状态为已签到返给前台
//			    	   		
//			    	   	 }else{
//			    	   		member.setIsDraw("no");//是否抽奖（yes:已抽奖，no:未抽奖）
//			    		 	member.setEnableScore(new BigDecimal(score).add(member.getEnableScore()));
//				    	    member.setAllScore(new BigDecimal(score).add(member.getAllScore())); 
//				    	    member.setCheckInDay(checkIn_now);
//				    	    member.setLastCheckTime(new Date());//保存签到日期
//				    	    phiMemberService.saveOrUpdatePhiMember(member);
//				    		entity.setScoreAction("签到积分获取");
//				    		entity.setTaskName("签到");
//				    	    entity.setCreateTime(new Date());
//							entity.setMemberId(member.getId());
//							entity.setOrderCode("");
//							entity.setScoreType("gain");
//							entity.setSourcePlatform("phicomm_scoremall");
//							entity.setScore(new BigDecimal(score));
//							phiScoreFlowService.savePhiScoreFlow(entity);
//							map.put("isSignup", "true");//已签到设当前状态为已签到返给前台
//			    	   	 }
//				    		 
//					}else {
//				      return new ResponseEntity<>(ResponseMessage.warning(("签到失败")), HttpStatus.BAD_REQUEST);
//					}
//					
//	  		}
//	  	}else{
//	  		 return new ResponseEntity<>(ResponseMessage.warning(("签到功能尚未开启")), HttpStatus.OK);
//	  	}
//	 	 
//	  	//判断是否抽奖
//	  	if("no".equals(member.getIsDraw())){
//	  		map.put("isDraw", "false");
//	  	}else{
//	  		map.put("isDraw", "true");
//	  	}
//	  	map.put("score", String.valueOf(score));
//	
//	  	map.put("checkInDay",String.valueOf(checkIn_now));
//	      return new ResponseEntity<>(ResponseMessage.success(JsonUtil.map2json(map)), HttpStatus.OK);
//	     
//	  }
	    
    
     /** 
    * @Title: createPhiMemberDto 
    * @Description: 添加PhiMember 
    * @param    phiMemberDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiMemberDto(@RequestBody PhiMemberDto phiMemberDto) {
        phiMemberService.savePhiMemberDto(phiMemberDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiMember创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiMemberDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiMemberDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiMemberDto> getPhiMemberDto(@PathVariable("id") Long id) {
    	PhiMemberDto phiMemberDto = phiMemberService.getPhiMemberDtoById(id);
        return new ResponseEntity<>(phiMemberDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiMember 
    * @Description:修改PhiMember信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiMemberDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiMember(@PathVariable("id") Long id, @RequestBody PhiMemberDto phiMemberDto) {
        phiMemberService.updatePhiMember(id, phiMemberDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }
   
    
    
    
     @RequestMapping(value = "/queryScoreDetail", method = RequestMethod.POST)
     @ResponseBody
     public PhiMemberDto editPhiMemberForApp(HttpSession session) {
    	 CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
         String uid=member.getUid();
         PhiMember phiMember = phiMemberService.GetMemberPhiMemberByUid(Integer.valueOf(uid));
         Long memberId =  phiMember.getId();
         phiMemberAddressService.getPhiMemberAddressDtoByUId(Integer.valueOf(uid));
    	 PhiMemberDto phiMemberDto  = phiMemberService.getPhiMemberDtoById(memberId);  
    	 String imgurl = phiMemberDto.getPhiMembergrade().getImage();
    	 String portraitUrl = phiMemberDto.getPortrait();
    	 List<CmdFileMangerDto> images = cmdFileMangerService.getCmdFileDtoByBusiId(imgurl);
    	 List<CmdFileMangerDto> portraitImages = cmdFileMangerService.getCmdFileDtoByBusiId(portraitUrl);
    	  if(images.size()>0){
    		phiMemberDto.setImgUrl(images.get(0).getFilePath());   	    	 	  
    	  }
    	  if(portraitImages.size()>0){
    		  phiMemberDto.setPortraitUrl(portraitImages.get(0).getFilePath());
    	  }
    	  PhiMember memberCalc= phiMemberService.calcEnableScore(phiMember);
    	  phiMemberDto.setEnableScore(memberCalc.getEnableScore());
    	  phiMemberDto.setAllScore(memberCalc.getAllScore());
    	  phiMemberDto.setDisableScore(memberCalc.getDisableScore());
    	  return phiMemberDto;
     }

     /** 
      * @Title: editPhiMember 
      * @Description:获取会员积分明细 
      * @createDate: 2016年4月25日 下午1:49:25
      * @param    id
      * @param    phiMemberDto
      * @return   ResponseEntity<ResponseMessage>    
      *//* 
      @RequestMapping(value = "/queryScoreDetail/{id}", method = RequestMethod.POST)
      @ResponseBody
      public PhiMemberDto editPhiMemberForApp(@PathVariable("id") Long id,HttpSession session) {
     	 //log.debug("get all phiMemberAddress of param " + queryPage.getQueryInfo());
   	       CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
              String uid=member.getUid();
              PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
              Long memberId =  phiMember.getId();
     	   PhiMemberDto phiMemberDto  = phiMemberService.getPhiMemberDtoById(memberId);  
     	 String imgurl = phiMemberDto.getPhiMembergrade().getImage();
     	 //String portraitUrl = phiMemberDto.getPortrait();
     	 List<CmdFileMangerDto> images = cmdFileMangerService.getCmdFileDtoByBusiId(imgurl);
     	 //List<CmdFileMangerDto> portraitImages = cmdFileMangerService.getCmdFileDtoByBusiId(portraitUrl);
     	  if(images.size()>0){
     		phiMemberDto.setImgUrl(images.get(0).getFilePath());   	    	 	  
     	  }
     	  if(portraitImages.size()>0){
     		  phiMemberDto.setPortraitUrl(portraitImages.get(0).getFilePath());
     	  }
     	  return phiMemberDto;
      }
*/
     
    /** 
    * @Title: deletePhiMemberById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiMemberById(@PathVariable("id") Long id) {
        phiMemberService.deletePhiMember(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: updatePhiProductDto 
     * @Description:拉黑会员
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    id
     * @return   ResponseEntity<PhiProductDto>    
     */ 
     @RequestMapping(value = "/setBacklist/{id}/{val}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> setBacklistPhiMemberDto(@PathVariable("id") Long id,@PathVariable("val") String status) {
    	 PhiMemberDto phiMemberDto = phiMemberService.getPhiMemberDtoById(id);
    	 phiMemberService.updateBackList(id, status,phiMemberDto);
            String msg = "";
            if(status.equals("1")){
            	msg = "该会员已被拉黑！";
            }else if(status.equals("0")){
            	msg = "该会员账号已激活！";
            }
         return new ResponseEntity<>(ResponseMessage.success(msg), HttpStatus.OK);
    }
     
      
      /** 
       * @Title: editBlackList 
       * @Description:修改PhiMember信息 
       * @createDate: 2018年1月8日 下午1:49:25
       * @param    id
       * @param    phiMemberDto
       * @return   ResponseEntity<ResponseMessage>    
       */ 
       @RequestMapping(value = "/editBlackList/{id}/{blacklist}", method = RequestMethod.POST)
       @ResponseBody
       public ResponseEntity<ResponseMessage> editMemberBlackList(@PathVariable("id") Long id,@PathVariable("blacklist") String status) {
    	   PhiMemberDto phiMemberDto = phiMemberService.getPhiMemberDtoById(id);
    	   phiMemberService.updateBackList(id, status,phiMemberDto);
           return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
       }
       
       
       /** 
        * @Title: editPhiMember 
        * @Description:获取可用积分
        * @param    id
        * @param    phiMemberDto
        * @return   ResponseEntity<ResponseMessage>    
        */ 
        @RequestMapping(value = "/queryEnableScore/{id}", method = RequestMethod.POST)
        @ResponseBody
        public BigDecimal editPhiMember(@PathVariable("id") Long id) {    
       	 PhiMemberDto phiMemberDto  = phiMemberService.getPhiMemberDtoById(id);  
       	 BigDecimal enableScore = phiMemberDto.getEnableScore();
       	  return enableScore;
        }
}
