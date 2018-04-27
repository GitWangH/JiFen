package com.huatek.busi.service.impl.phicom.game;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.game.PhiGameConfigDao;
import com.huatek.busi.dao.phicom.game.PhiGameDao;
import com.huatek.busi.dao.phicom.game.PhiGameUserDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dto.phicom.game.PhiGameConfigDto;
import com.huatek.busi.dto.phicom.game.PhiGameDto;
import com.huatek.busi.model.phicom.game.PhiGame;
import com.huatek.busi.model.phicom.game.PhiGameConfig;
import com.huatek.busi.model.phicom.game.PhiGameUser;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.service.phicom.game.PhiGameService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("phiGameServiceImpl")
@Transactional
public class PhiGameServiceImpl implements PhiGameService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiGameServiceImpl.class);
	
	@Autowired
	PhiGameDao phiGameDao;
	@Autowired
	PhiGameConfigDao phiGameConfigDao;
	@Autowired
	PhiMemberDao phiMemberDao;
	@Autowired
	PhiGameUserDao phiGameUserDao;
	@Autowired
	PhiScoreFlowService phiScoreFlowService;
	@Autowired
    private PhiMemberService phiMemberService;
	@Override
	public void savePhiGameDto(PhiGameDto entityDto)  {
		log.debug("save phiGameDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiGame entity = BeanCopy.getInstance().convert(entityDto, PhiGame.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiGameDao.persistentPhiGame(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiGameDto getPhiGameDtoById(Long id) {
		log.debug("get phiGame by id@" + id);
		PhiGame entity = phiGameDao.findPhiGameById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiGameDto entityDto = BeanCopy.getInstance().convert(entity, PhiGameDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiGame(Long id, PhiGameDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiGame phiGame = phiGameDao.findPhiGameById(id);
		/**@Fields type : 类型(bigWheel大转盘  ninePlace九宫格)*/
		String type = null;
		if(phiGame != null){
			type = phiGame.getType();
			//List<PhiGameConfig> phiGameConfigs = phiGame.getPhiGameConfigs();
			List<PhiGameConfigDto> phiGameConfigDtos = entityDto.getPhiGameConfigs();
			List<PhiGameConfig> phiGameConfigs = BeanCopy.getInstance().convertList(phiGameConfigDtos, PhiGameConfig.class);
			if(phiGameConfigs != null && phiGameConfigs.size() > 0){
				for (int i = 0; i < phiGameConfigs.size(); i++) {
					phiGameConfigDao.merge(phiGameConfigs.get(i));
				}
			}
			phiGame.setPhiGameConfigs(phiGameConfigs);
		}
		//前台展示名称
		phiGame.setGameName(entityDto.getGameName());
		//单日抽奖上限
		phiGame.setDrawMax(Integer.parseInt(entityDto.getDrawMax()));
		//单日免费次数
		phiGame.setFreeTimesDay(new BigDecimal(entityDto.getFreeTimesDay()));
		//积分配置单次消费积分
		phiGame.setOneTimeScore(new BigDecimal(entityDto.getOneTimeScore()));
		//游戏是否开启
		phiGame.setTaskSwitch(entityDto.getTaskSwitch());
		//游戏规则
		phiGame.setRemark(entityDto.getRemark());
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		//操作人
		phiGame.setOperationPerson(userInfo.getUserName());
		Date currDate = new Date(); 
		//操作时间
		phiGame.setLastOperationTime(currDate);
		phiGameDao.merge(phiGame);
	}
	
	
	
	@Override
	public void deletePhiGame(Long id) {
		log.debug("delete phiGame by id@" + id);
		beforeRemove(id);
		PhiGame entity = phiGameDao.findPhiGameById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiGameDao.deletePhiGame(entity);
	}
	
	@Override
	public DataPage<PhiGameDto> getAllPhiGamePage(QueryPage queryPage) {
		DataPage<PhiGame> dataPage = phiGameDao.getAllPhiGame(queryPage);
		DataPage<PhiGameDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiGameDto.class);
		if(null!=datPageDto){
			for(int i=0;i<datPageDto.getContent().size();i++){
				PhiGameDto entity = datPageDto.getContent().get(i);
				if(null!=entity){					
					datPageDto.getContent().get(i).setCode(i+1);		   
				}
			}
		}
		return datPageDto;
	}
	
	@Override
	public List<PhiGameDto> getAllPhiGameDto() {
		List<PhiGame> entityList = phiGameDao.findAllPhiGame();
		List<PhiGameDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiGameDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(Long id) {

	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    phiGameDto
	* @param    phiGame
	* @return  void    
	* @
	*/
	private void beforeSave(PhiGameDto entityDto, PhiGame entity) {

	}


	@Override
	public void editPhiScoreswitch(Long id, String taskSwitch) {
			/*获取要修改的实体*/
		PhiGame entity = phiGameDao.findPhiGameById(id);
		entity.setTaskSwitch(taskSwitch);
		phiGameDao.saveOrUpdatePhiGame(entity);
		
	}


	@Override
	public PhiGameDto getPhiGameDtoByTypeForApp(String type,Long memberId) {
		  String gametype = "";
		  int  drawTime = 0;
		  PhiMember member = phiMemberDao.findPhiMemberById(memberId);
		  
		  Date sysDate= new Date();
		  String phoneNumber = member.getTel();
		  //大转盘游戏参与人员
		  List<PhiGameUser> wheeluserList = phiGameUserDao.findWheelGameUsersBymemberTel(phoneNumber);
		  if(wheeluserList != null && wheeluserList.size()>0){
			 // List<PhiGameUser> wheelUsers = 
			  //从参与用户信息中获得该用户最后一次玩游戏的时间
			  Date lastplayDate =  wheeluserList.get(0).getCreateTime();
			  Calendar opcalendar = Calendar.getInstance();
			  Calendar syscalendar = Calendar.getInstance();
			  syscalendar.setTime(sysDate);
			  opcalendar.setTime(lastplayDate);
			  //如果系统时间与当前时间相等
			  if(syscalendar.get(Calendar.DATE) > opcalendar.get(Calendar.DATE)){
				  drawTime = 0;  
				  member.setDrawTimesD(drawTime);
			  }
		  }else {
			  drawTime = 0;  
			  member.setDrawTimesD(drawTime);
		}
		  List<PhiGameUser> placeuserList = phiGameUserDao.findPlacesGameUsersBymemberTel(phoneNumber);
		  if(placeuserList != null && placeuserList.size()>0){
				 // List<PhiGameUser> wheelUsers = 
				  //从参与用户信息中获得该用户最后一次玩游戏的时间
				  Date lastplayDate =  placeuserList.get(0).getCreateTime();
				  Calendar opcalendar = Calendar.getInstance();
				  Calendar syscalendar = Calendar.getInstance();
				  syscalendar.setTime(sysDate);
				  opcalendar.setTime(lastplayDate);
				  //如果系统时间与当前时间相等
				  if(syscalendar.get(Calendar.DATE) > opcalendar.get(Calendar.DATE)){
					  drawTime = 0;  
					  member.setDrawTimesJ(drawTime);
				  }
			}else {
				drawTime = 0;  
				member.setDrawTimesJ(drawTime);
			}
		   if(type.equals("D")){
	       //大转盘	
               gametype = "bigWheel";
               drawTime =  member.getDrawTimesD();
		   }
		   if(type.equals("J")){
		   //九宫格
			   gametype = "ninePlace";
			   drawTime = member.getDrawTimesJ();
		   }
	       PhiGame phiGame  = phiGameDao.findPhiGameByType(gametype);
	      
	       //通过游戏的id找到该游戏的配置
	       List<PhiGameConfig> phiGameConfigs = phiGameConfigDao.findAllPhiGameConfigByGameId(phiGame.getId());
	       List<PhiGameConfigDto> phiGameConfigDtos = BeanCopy.getInstance().convertList(phiGameConfigs, PhiGameConfigDto.class);
	       //将配置加入到游戏的dto中;
	       PhiGameDto phiGameDto = BeanCopy.getInstance().convert(phiGame, PhiGameDto.class);
	       BigDecimal memberScore =  member.getEnableScore();
	       BigDecimal useScore = phiGame.getOneTimeScore();
	       BigDecimal bigDecimal = new BigDecimal(0);
			if(null != memberScore && null != useScore){
				if(memberScore.subtract(useScore).compareTo(bigDecimal)<0){
					phiGameDto.setIsEncough(2);//2不能玩
				}else{
					phiGameDto.setIsEncough(1);//1积分足能玩
				}
			}else{
				phiGameDto.setIsEncough(2);
			}
	       phiGameDto.setDrawTimes(drawTime);
	       phiGameDto.setPhiGameConfigs(phiGameConfigDtos);
		   return phiGameDto;
	}


	@SuppressWarnings("unused")
	@Override
	public Object playGamesDOnceForApp(Long memberId, String type, String location) {
		   String gametype = "";
		   String gameName = "";
		   BigDecimal bigDecimal  = new BigDecimal(0);
		   if(type.equals("D")){
	       //大转盘	
              gametype = "bigWheel";
              gameName = "大转盘";
		   }		   	   		  		    
	       PhiGame phiGame  = phiGameDao.findPhiGameByType(gametype);
	       //找到玩游戏的会员
		   PhiMember member = phiMemberDao.findPhiMemberById(memberId);
		   
		   /**
		    * 通过count记录会员玩游戏的次数，如果最后一次玩游戏的时间和当前系统时间是同一天
		    * 则给count加1;
		    */		   
		   int count = member.getDrawTimesD();
		   Date sysDate = new Date();
		   String phoneNumber = member.getTel();
		   PhiGameUser gameUser = new PhiGameUser();
		   //1,查看游戏是否关闭
		   String isOpen = phiGame.getTaskSwitch(); 
		   if(isOpen.equals("on")){//open
			   
			   
			   //大转盘游戏参与人员
			   List<PhiGameUser> wheeluserList = phiGameUserDao.findWheelGameUsersBymemberTel(phoneNumber);
			   if(wheeluserList != null && wheeluserList.size()>0){
				  //从参与用户信息中获得该用户最后一次玩游戏的时间
				  Date lastplayDate =  wheeluserList.get(0).getCreateTime();
				  Calendar opcalendar = Calendar.getInstance();
				  Calendar syscalendar = Calendar.getInstance();
				  syscalendar.setTime(sysDate);
				  opcalendar.setTime(lastplayDate);
				  //如果系统时间与当前时间相等
				  if(syscalendar.get(Calendar.DATE) == opcalendar.get(Calendar.DATE)){
					  count +=1;
				  	}else{
				    	count = 0;
				    }
				  member.setDrawTimesD(count);//保存游戏的次数；
				  //gameUser.setCreateTime(sysDate);
				  phiGameDao.persistentPhiGame(phiGame);	
			   }else {
				  count +=1;
				  member.setDrawTimesD(count);//保存游戏的次数；
				  //gameUser.setCreateTime(sysDate);
				  phiGameDao.persistentPhiGame(phiGame);
			   }
			   log.error("++++++++getcount++++++++++++++"+member.getDrawTimesD());
			    //2,消耗了多少积分   
				BigDecimal useScore = new BigDecimal(0);	
				 //单日抽奖上限
				int drawMax =phiGame.getDrawMax();
				//免费抽奖次数
				BigDecimal FreeTimes = phiGame.getFreeTimesDay();
				BigDecimal Bigcount = new BigDecimal(count);
				//每次玩游戏消耗多少积分
				BigDecimal oneTimeScore = phiGame.getOneTimeScore();
				BigDecimal drawCount = new BigDecimal(count);           
				int x= drawCount.compareTo(FreeTimes);										
		        int g= drawCount.compareTo(BigDecimal.valueOf(drawMax));
		        log.error("=========抽奖次数drawCount============="+drawCount);
		        log.error("=========免费抽奖次数FreeTimes============="+FreeTimes);	        
		        //当天玩游戏的次数小于游戏上限；
		        if(g == -1 || g == 0){
		        	if(x == -1 || x== 0){		        		
						useScore = new BigDecimal(0);//消耗的积分为零		
					}else{
						//useScore = oneTimeScore.multiply(count);//单次消耗积分和玩游戏的次数相乘；						
						useScore = oneTimeScore;
					}
		        }else{
		        return new ResponseEntity<>(ResponseMessage.warning(("已超出游戏上限次数，明天再来玩吧")), HttpStatus.BAD_REQUEST);
		        }
				BigDecimal memberScore =  member.getEnableScore();
				if(null != memberScore && null != useScore){
					if(memberScore.subtract(useScore).compareTo(bigDecimal)<0){
						 return new ResponseEntity<>(ResponseMessage.warning(("积分不足")), HttpStatus.BAD_REQUEST);
					}
				}else{
					return new ResponseEntity<>(ResponseMessage.warning(("积分不足")), HttpStatus.BAD_REQUEST);
				}
			 //3,获取了多少积分   
	      /*  PhiGameUser phiGameUser = phiGameUserDao.findPhiGameUserById(memberId);*/
	        BigDecimal getScore = new BigDecimal(0);
    	   //没参与过游戏的会员(每次都新建一个参与游戏的用户)
    	   PhiGameUser phiGameUser1 = new PhiGameUser();
    	   List<PhiGameConfig> phiGameConfigs = phiGameConfigDao.findAllPhiGameConfigByGameId(phiGame.getId());	           
		   for(PhiGameConfig phiGameConfig :phiGameConfigs){
			  if(location.endsWith(phiGameConfig.getLocation())){
				    getScore= phiGameConfig.getScore(); 			   
			    }				   
		     }
		   //6,保存参与游戏的会员信息
	       phiGameUser1.setCreateTime(new Date());
	       phiGameUser1.setGameId(phiGame.getId());
	       phiGameUser1.setGameType(gameName);
           phiGameUser1.setMemberGrade(member.getPhiMembergrade().getMemberGrade());
           //获取的积分
           phiGameUser1.setGetscore(getScore);
           //消耗的积分
           phiGameUser1.setScore(useScore);
           phiGameUser1.setTel(member.getTel());
           phiGameUser1.setUsrName(member.getUserName());		
           phiGameUserDao.persistentPhiGameUser(phiGameUser1);		               		    			   		   
			  //4,更新会员的积分
			BigDecimal memScore = getScore.subtract(useScore);//
			BigDecimal enableScore = member.getEnableScore();
			if(enableScore == null){
				enableScore = memScore;
			}else {
			    enableScore = member.getEnableScore().add(memScore);
			}	
			int ss = enableScore.intValue();
			if(ss<0){
				enableScore = new BigDecimal(0);
			}
			member.setEnableScore(enableScore);//可有积分
			member.setAllScore(enableScore);//经验积分
			log.error("+oooooooooooooogetcount2ooooooooooooo"+member.getDrawTimesD());
			phiMemberDao.persistentPhiMember(member);			
			  //5,保存积分流水
			//获取积分						
				PhiScoreFlow entity=new PhiScoreFlow();
				entity.setScoreAction(gameName);
				entity.setTaskName("游戏");
				entity.setCreateTime(new Date());
				entity.setScoreType("gain");
				entity.setMemberId(member.getId());
				entity.setOrderCode("");
				entity.setSourcePlatform("phicomm_scoremall");
				entity.setScore(getScore);
				 log.error("=============getScore==============="+getScore);
				phiScoreFlowService.savePhiScoreFlow(entity);
		
			//消耗积分
					
				PhiScoreFlow entity1=new PhiScoreFlow();
				entity1.setScoreAction(gameName);
				entity1.setTaskName("游戏");
				entity1.setCreateTime(new Date());
				entity1.setMemberId(member.getId());
				entity1.setOrderCode("");
				entity1.setScoreType("consume");
				entity1.setSourcePlatform("phicomm_scoremall");
				entity1.setScore(useScore);
				 log.error("=============useScore==============="+useScore);
				phiScoreFlowService.savePhiScoreFlow(entity1);
			
			//积分变化，同步给辰商
			phiMemberService.pullMemberInfoToChenShang(member,"玩游戏获取/消耗积分");
		   }else{
			    return new ResponseEntity<>(ResponseMessage.warning(("游戏已经关闭")), HttpStatus.BAD_REQUEST);
		   }
		   
		
     return new ResponseEntity<>(ResponseMessage.success(("成功玩了一次"+gameName+"游戏")), HttpStatus.OK);
		
		
	}
	

	@Override
	public Object playGamesJOnceForApp(Long memberId, String type, String location) {
		   String gametype = "";
		   String gameName = "";
		   BigDecimal bigDecimal = new BigDecimal(0);
		   if(type.equals("J")){
		   //九宫格
			   gametype = "ninePlace";
			   gameName = "九宫格";
		   }		   		  		    
	       PhiGame phiGame  = phiGameDao.findPhiGameByType(gametype);
	       //找到玩游戏的会员
		   PhiMember member = phiMemberDao.findPhiMemberById(memberId);
		   /**
		    * 通过count记录会员玩游戏的次数，如果最后一次玩游戏的时间和当前系统时间是同一天
		    * 则给count加1;
		    */		   
		   int count = member.getDrawTimesJ();
		   String phoneNumber = member.getTel();
		   Date sysDate = new Date();
		   String isOpen = phiGame.getTaskSwitch(); 
		   //1,查看游戏是否关闭
		   if(isOpen.equals("on")){//open
			   List<PhiGameUser> placeuserList = phiGameUserDao.findPlacesGameUsersBymemberTel(phoneNumber);
				  if(placeuserList != null && placeuserList.size()>0){
						 // List<PhiGameUser> wheelUsers = 
						  //从参与用户信息中获得该用户最后一次玩游戏的时间
						  Date lastplayDate =  placeuserList.get(0).getCreateTime();
						  Calendar opcalendar = Calendar.getInstance();
						  Calendar syscalendar = Calendar.getInstance();
						  syscalendar.setTime(sysDate);
						  opcalendar.setTime(lastplayDate);
						  //如果系统时间与当前时间相等
						  if(syscalendar.get(Calendar.DATE)==opcalendar.get(Calendar.DATE)){
						    	count +=1;
						    }else{
						    	count = 0;
						    }
						  member.setDrawTimesJ(count);//保存游戏的次数；		    
						    //phiGame.setLastOperationTime(sysDate);//将系统时间记录为最后一次玩游戏的时间
						  phiGameDao.persistentPhiGame(phiGame);
					}else {
						count +=1;
						 member.setDrawTimesJ(count);//保存游戏的次数；
						  //gameUser.setCreateTime(sysDate);
					   	 phiGameDao.persistentPhiGame(phiGame);
					} 
		   
		    //2,消耗了多少积分   
			BigDecimal useScore = new BigDecimal(0);	
			 //单日抽奖上限
			int drawMax =phiGame.getDrawMax();
			BigDecimal FreeTimes =phiGame.getFreeTimesDay();//免费次数
			BigDecimal Bigcount = new BigDecimal(count);
			log.error("==========抽奖次数count======="+count);
			log.error("==ccccccccccccccccc免费抽奖次数FreeTimes1ccccccccccccccccccccc=="+FreeTimes);
			BigDecimal oneTimeScore = phiGame.getOneTimeScore();//每次玩游戏消耗多少积分
			BigDecimal drawCount = new BigDecimal(count);           
			int x= drawCount.compareTo(FreeTimes);										
	        int g= drawCount.compareTo(BigDecimal.valueOf(drawMax));
	        //当天玩游戏的次数小于游戏上限；
	       // log.error("=========抽奖次数drawCount============="+drawCount);
	      //  log.error("=========免费抽奖次数FreeTimes============="+FreeTimes);	    
	        if(g == -1 || g == 0){
	        	if(x == -1 || x== 0){	        		
					useScore = new BigDecimal(0);//消耗的积分为零
				}else{
					//useScore = oneTimeScore.multiply(count);//单次消耗积分和玩游戏的次数相乘；
					useScore = oneTimeScore;
				}
	        }else{
	        return new ResponseEntity<>(ResponseMessage.warning(("已超出游戏上限次数，明天再来玩吧")), HttpStatus.BAD_REQUEST);
	        }
	        BigDecimal memberScore =  member.getEnableScore();
			if(null != memberScore && null != useScore){
				if(memberScore.subtract(useScore).compareTo(bigDecimal)<0){
					 return new ResponseEntity<>(ResponseMessage.warning(("积分不足")), HttpStatus.BAD_REQUEST);
				}
			}else{
				return new ResponseEntity<>(ResponseMessage.warning(("积分不足")), HttpStatus.BAD_REQUEST);
			}
			 //3,获取了多少积分   
	       // PhiGameUser phiGameUser = phiGameUserDao.findPhiGameUserById(memberId);
	        BigDecimal getScore = new BigDecimal(0);       
	        	   //没参与过游戏的会员(每次都新建一个参与游戏的用户)
	        	   PhiGameUser phiGameUser1 = new PhiGameUser();
	        	   List<PhiGameConfig> phiGameConfigs = phiGameConfigDao.findAllPhiGameConfigByGameId(phiGame.getId());	           
    			   for(PhiGameConfig phiGameConfig :phiGameConfigs){
    				  if(location.endsWith(phiGameConfig.getLocation())){
    					    getScore= phiGameConfig.getScore(); 			   
    				    }				   
    			     }
    			   //6,保存参与游戏的会员信息
 			       phiGameUser1.setCreateTime(new Date());
 			       phiGameUser1.setGameId(phiGame.getId());
 			       phiGameUser1.setGameType(gameName);
 		           phiGameUser1.setMemberGrade(member.getPhiMembergrade().getMemberGrade());
 		           //获取的积分
 		           phiGameUser1.setGetscore(getScore);
 		           //消耗的积分
 	               phiGameUser1.setScore(useScore);
 	               phiGameUser1.setTel(member.getTel());
 	               phiGameUser1.setUsrName(member.getUserName());		
 	               phiGameUserDao.persistentPhiGameUser(phiGameUser1);		
	        
	    /*    }	*/		      	      		    			   		   
			  //4,更新会员的积分
			BigDecimal memScore = getScore.subtract(useScore);//
			BigDecimal enableScore = member.getEnableScore();			
			if(enableScore == null){				 
				 enableScore = memScore; 
			}else{
				 enableScore = member.getEnableScore().add(memScore); 
			}
			int ss = enableScore.intValue();
			if(ss<0){
				enableScore = new BigDecimal(0);
			}
			member.setEnableScore(enableScore);//可有积分
			member.setAllScore(enableScore);//经验积分
			phiMemberDao.persistentPhiMember(member);			
			  //5,保存积分流水
			//获取积分
				
				PhiScoreFlow entity=new PhiScoreFlow();
				entity.setScoreAction(gameName);
				entity.setTaskName("游戏");
				entity.setCreateTime(new Date());
				entity.setMemberId(member.getId());
				entity.setOrderCode("");
				entity.setScoreType("gain");
				entity.setSourcePlatform("phicomm_scoremall");
				entity.setScore(getScore);
	          log.error("=============getScore==============="+getScore);
				phiScoreFlowService.savePhiScoreFlow(entity);
		
			//消耗积分
					
				PhiScoreFlow entity1=new PhiScoreFlow();
				entity1.setScoreAction(gameName);
				entity1.setTaskName("游戏");
				entity1.setCreateTime(new Date());
				entity1.setMemberId(member.getId());
				entity1.setOrderCode("");
				entity1.setScoreType("consume");
				entity1.setSourcePlatform("phicomm_scoremall");
				entity1.setScore(useScore);
			 log.error("=============useScore==============="+useScore);
				phiScoreFlowService.savePhiScoreFlow(entity1);
		
			//积分变化同步给辰商；
			phiMemberService.pullMemberInfoToChenShang(member,"玩游戏获取/消耗积分");	
		   }else{
			    return new ResponseEntity<>(ResponseMessage.warning(("游戏已经关闭")), HttpStatus.BAD_REQUEST);
		   }
		   
		
     return new ResponseEntity<>(ResponseMessage.success(("成功玩了一次"+gameName+"游戏")), HttpStatus.OK);
		
		
	}
	  
	
	
	
	
}
