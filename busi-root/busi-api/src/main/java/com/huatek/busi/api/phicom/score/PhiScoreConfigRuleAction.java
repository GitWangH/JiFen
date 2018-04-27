package com.huatek.busi.api.phicom.score;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import com.huatek.busi.dto.phicom.score.PhiScoreConfigRuleDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.score.PhiScoreConfigRuleService;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.cmd.category.util.JsonUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value = BusiUrlConstants.PHISCORECONFIGRULE_API)
public class PhiScoreConfigRuleAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiScoreConfigRuleAction.class);

    @Autowired
    private PhiScoreConfigRuleService phiScoreConfigRuleService;
    
    @Autowired
    private PhiMemberService phiMemberService;
    
    @Autowired
	PhiScoreFlowService phiScoreFlowService;
    /** 
    * @Title: getAllPhiScoreConfigRule 
    * @Description:  翻页查询PhiScoreConfigRule信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiScoreConfigRuleDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiScoreConfigRuleDto>> getAllPhiScoreConfigRule(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiScoreConfigRule of param " + queryPage.getQueryInfo());
        DataPage<PhiScoreConfigRuleDto> phiScoreConfigRulePages = phiScoreConfigRuleService.getAllPhiScoreConfigRulePage(queryPage);
        log.debug("get phiScoreConfigRule size @" + phiScoreConfigRulePages.getContent().size());
        return new ResponseEntity<>(phiScoreConfigRulePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiScoreConfigRuleDto 
    * @Description: 添加PhiScoreConfigRule 
    * @param    phiScoreConfigRuleDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiScoreConfigRuleDto(@RequestBody PhiScoreConfigRuleDto phiScoreConfigRuleDto) {
        phiScoreConfigRuleService.savePhiScoreConfigRuleDto(phiScoreConfigRuleDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiScoreConfigRule创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiScoreConfigRuleDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiScoreConfigRuleDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiScoreConfigRuleDto> getPhiScoreConfigRuleDto(@PathVariable("id") Long id) {
    	PhiScoreConfigRuleDto phiScoreConfigRuleDto = phiScoreConfigRuleService.getPhiScoreConfigRuleDtoById(id);
        return new ResponseEntity<>(phiScoreConfigRuleDto, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/findConfigRule", method = RequestMethod.POST)
    @ResponseBody
    public PhiScoreConfigRuleDto getPhiScoreConfigRuleForApp() {    	
    	PhiScoreConfigRuleDto phiScoreConfigRuleDto = phiScoreConfigRuleService.getConfigRuleDtoForApp();
        return phiScoreConfigRuleDto;
    }
    
    //抽奖
    @RequestMapping(value = "/Luckdraw", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> getPhiLuckdrawForApp(HttpSession session) { 
    	CloudMember cloudmember = (CloudMember) session.getAttribute(SessionKey.currentMember);
        String uid=cloudmember.getUid();
        PhiMember member = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
        Long memberId =  member.getId();
    	PhiScoreConfigRuleDto phiScoreConfigRuleDto = phiScoreConfigRuleService.getConfigRuleDtoForApp();
    	  int min = Integer.parseInt(phiScoreConfigRuleDto.getMinScoreValue());
    	  int max = Integer.parseInt(phiScoreConfigRuleDto.getMaxScoreValue()); 
    	  int score =0;
             if(7==member.getCheckInDay() && member.getIsDraw().equals("no")){
            	score = getRandomNum(min,max);//得到随机的积分
            	PhiScoreFlow entity=new PhiScoreFlow();//积分流水
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
 	    	    //抽奖状态置为已抽奖
 	    	    member.setIsDraw("yes");
 	    	    phiMemberService.saveOrUpdatePhiMember(member);
 	    	    //存储积分流水
 	    		entity.setScoreAction("签到积分获取");
 	    		entity.setTaskName("签到");
 	    	    entity.setCreateTime(new Date());
 				entity.setMemberId(member.getId());
 				entity.setOrderCode("");
 				entity.setScoreType("gain");
 				entity.setSourcePlatform("phicomm_scoremall");
 				entity.setScore(new BigDecimal(score));
 				phiScoreFlowService.savePhiScoreFlow(entity);
 				//积分兑换 ，积分发生变化，给商城推送会员信息
				phiMemberService.pullMemberInfoToChenShang(member,"签到获取积分|");
             }
				Map<String,String> map=new HashMap<String,String>();
		    	map.put("score", String.valueOf(score));
         return new ResponseEntity<>(ResponseMessage.success(JsonUtil.map2json(map)), HttpStatus.OK);
    }

    /** 
    * @Title: editPhiScoreConfigRule 
    * @Description:修改PhiScoreConfigRule信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiScoreConfigRuleDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiScoreConfigRule(@PathVariable("id") Long id, @RequestBody PhiScoreConfigRuleDto phiScoreConfigRuleDto) {
        phiScoreConfigRuleService.updatePhiScoreConfigRule(id, phiScoreConfigRuleDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiScoreConfigRuleById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiScoreConfigRuleById(@PathVariable("id") Long id) {
        phiScoreConfigRuleService.deletePhiScoreConfigRule(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    public static int getRandomNum(int min,int max){  
	     Random rdm = new Random();  
	     return rdm.nextInt(max-min+1)+min;  
	} 
}
