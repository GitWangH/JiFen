package com.huatek.busi.api.phicom.game;
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
import com.huatek.busi.dto.phicom.game.PhiGameDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.game.PhiGameService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController		
@RequestMapping(value = BusiUrlConstants.PHIGAME_API)
public class PhiGameAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiGameAction.class);

    @Autowired
    private PhiGameService phiGameService;
    
    @Autowired
    private PhiMemberService phiMemberService;

    
    /** 
    * @Title: getAllPhiGame 
    * @Description:  翻页查询PhiGame信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiGameDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiGameDto>> getAllPhiGame(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiGame of param " + queryPage.getQueryInfo());
        DataPage<PhiGameDto> phiGamePages = phiGameService.getAllPhiGamePage(queryPage);
        log.debug("get phiGame size @" + phiGamePages.getContent().size());
        return new ResponseEntity<>(phiGamePages, HttpStatus.OK);
       
    }
    
    /** 
     * @Title: getAllPhiGame 
     * @Description:  翻页查询PhiGame信息.
     * @param   queryPage
     * @return  ResponseEntity<DataPage<PhiGameDto>>    
     */
     @RequestMapping(value = "/queryByType/{type}")
     @ResponseBody
     public ResponseEntity<PhiGameDto> getAllPhiGameByTypeForApp(@PathVariable("type") String type,HttpSession session)  { 
    	 CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
         String uid=member.getUid();
         PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
         Long memberId =  phiMember.getId();
    	// Long memberId =  Long.valueOf(210);
         //log.debug("get all phiGame of param " + queryPage.getQueryInfo());       
         PhiGameDto phiGameDto = phiGameService.getPhiGameDtoByTypeForApp(type ,memberId);
         phiGameDto.setEnableScore(phiMember.getEnableScore() == null?"0":phiMember.getEnableScore().toString());
         //log.debug("get phiGame size @" + phiGamePages.getContent().size());
         return new ResponseEntity<>(phiGameDto, HttpStatus.OK);      
     }
    
     
     
     /** 
      * @Title: getAllPhiGame 
      * @Description:  翻页查询PhiGame信息.
      * @param   queryPage
      * @return  ResponseEntity<DataPage<PhiGameDto>>    
      */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/playGame/{type}/{position}",method = RequestMethod.POST)
    @ResponseBody
      public ResponseEntity<ResponseMessage> PlayTheGameForApp(@PathVariable("type") String type,@PathVariable("position") String position,HttpSession session)  {                 
    	  CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
          String uid=member.getUid();
          PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
          Long memberId =  phiMember.getId();
    	 // Long memberId =  Long.valueOf(210);
          ResponseEntity<ResponseMessage> responseMessage =null;
          if(type.equals("D")){
        	   responseMessage= (ResponseEntity<ResponseMessage>) phiGameService.playGamesDOnceForApp(memberId, type, position);
          }
          if(type.equals("J")){
        	  
        	   responseMessage= (ResponseEntity<ResponseMessage>) phiGameService.playGamesJOnceForApp(memberId, type, position);
		  }
    	   
          return responseMessage;      
      }
    
 
     
    
    
     /** 
    * @Title: createPhiGameDto 
    * @Description: 添加PhiGame 
    * @param    phiGameDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiGameDto(@RequestBody PhiGameDto phiGameDto) {
        phiGameService.savePhiGameDto(phiGameDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiGame创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiGameDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiGameDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiGameDto> getPhiGameDto(@PathVariable("id") Long id) {
    	PhiGameDto phiGameDto = phiGameService.getPhiGameDtoById(id);
        return new ResponseEntity<>(phiGameDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiGame 
    * @Description:修改PhiGame信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiGameDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiGame(@PathVariable("id") Long id, @RequestBody PhiGameDto phiGameDto) {
        phiGameService.updatePhiGame(id, phiGameDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiGameById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiGameById(@PathVariable("id") Long id) {
        phiGameService.deletePhiGame(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: editPhiScoreTaskConfig 
     * @Description:修改PhiScoreTaskConfig信息 
     * @createDate: 2016年4月25日 下午1:49:25
     * @param    id
     * @param    phiScoreTaskConfigDto
     * @return   ResponseEntity<ResponseMessage>    
     */ 
     @RequestMapping(value = "/switch/{id}/{taskSwitch}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> editPhiScoreswitch(@PathVariable("id") Long id,@PathVariable("taskSwitch") String  taskSwitch) {
    	 phiGameService.editPhiScoreswitch(id,taskSwitch);
         return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
     }
    
}
