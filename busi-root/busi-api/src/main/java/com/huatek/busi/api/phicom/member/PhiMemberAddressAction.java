package com.huatek.busi.api.phicom.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.StaleObjectStateException;
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
import com.huatek.busi.dto.phicom.member.PhiMemberAddressDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.member.PhiMemberAddressService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIMEMBERADDRESS_API)
public class PhiMemberAddressAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiMemberAddressAction.class);

    @Autowired
    private PhiMemberAddressService phiMemberAddressService;
    
    @Autowired
    private PhiMemberService phiMemberService;
    
    /** 
    * @Title: getAllPhiMemberAddress 
    * @Description:  翻页查询PhiMemberAddress信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiMemberAddressDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiMemberAddressDto>> getAllPhiMemberAddress(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiMemberAddress of param " + queryPage.getQueryInfo());
        DataPage<PhiMemberAddressDto> phiMemberAddressPages = phiMemberAddressService.getAllPhiMemberAddressPage(queryPage);
        log.debug("get phiMemberAddress size @" + phiMemberAddressPages.getContent().size());
        return new ResponseEntity<>(phiMemberAddressPages, HttpStatus.OK);
       
    }

    /** 
    * @Title: getAllPhiMemberAddress 
    * @Description:  翻页查询PhiMemberAddress信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiMemberAddressDto>>    
    */
    @RequestMapping(value = "/queryMemberAddress")
    @ResponseBody
    public ResponseEntity<DataPage<PhiMemberAddressDto>> getAllPhiMemberAddressForApp(@RequestBody QueryPage queryPage,HttpSession session)  { 	
         
    	log.debug("get all phiMemberAddress of param " + queryPage.getQueryInfo());
    	   CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
           String uid=member.getUid();
          PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
           Long memberId =  phiMember.getId();
           queryPage.setSqlCondition("memberId="+memberId);
        DataPage<PhiMemberAddressDto> phiMemberAddressPages = phiMemberAddressService.getAllPhiMemberAddressPage(queryPage);
        log.debug("get phiMemberAddress size @" + phiMemberAddressPages.getContent().size());
        return new ResponseEntity<>(phiMemberAddressPages, HttpStatus.OK);
       
    }
	/*  @RequestMapping(value = "/queryMemberAddress/{memberId}")
	  @ResponseBody
	  public ResponseEntity<DataPage<PhiMemberAddressDto>> getAllPhiMemberAddressForApp(@RequestBody QueryPage queryPage,@PathVariable("memberId") Long memberId)  { 	
	       
	  	log.debug("get all phiMemberAddress of param " + queryPage.getQueryInfo());
	         queryPage.setSqlCondition("memberId="+memberId);
	      DataPage<PhiMemberAddressDto> phiMemberAddressPages = phiMemberAddressService.getAllPhiMemberAddressPage(queryPage);
	      log.debug("get phiMemberAddress size @" + phiMemberAddressPages.getContent().size());
	      return new ResponseEntity<>(phiMemberAddressPages, HttpStatus.OK);
	     
	  }*/
    
     /** 
    * @Title: createPhiMemberAddressDto 
    * @Description: 添加PhiMemberAddress 
    * @param    phiMemberAddressDto
    * @return   ResponseEntity<ResponseMessage>    
    */
    
    
    @RequestMapping(value = "/addMemberAddress", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiMemberAddressDtoForApp(@RequestBody PhiMemberAddressDto phiMemberAddressDto,HttpSession session) {
    	 CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
         String uid=member.getUid();
         PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
         int UId= Integer.valueOf(uid);
         Long memberId =  phiMember.getId();
         List<PhiMemberAddressDto> addressDtos = phiMemberAddressService.getPhiMemberAddressDtoByMemberId(UId);
         try{
        	 boolean addAddress = phiMemberAddressService.saveMemberAddressDtoForApp(phiMemberAddressDto,memberId,UId);
        	 if(addAddress){
        		 return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
        	 }else {
        		 return new ResponseEntity<>(ResponseMessage.warning("新增地址不得超过10个！"), HttpStatus.OK);
			}
         }catch(StaleObjectStateException e){
			 return new ResponseEntity<>(ResponseMessage.success("地址信息异常，请稍后操作！"), HttpStatus.BAD_REQUEST);
         }
    }
    /** 
    * @Title: getPhiMemberAddressDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiMemberAddressDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiMemberAddressDto> getPhiMemberAddressDto(@PathVariable("id") Long id) {
    	PhiMemberAddressDto phiMemberAddressDto = phiMemberAddressService.getPhiMemberAddressDtoById(id);
        return new ResponseEntity<>(phiMemberAddressDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiMemberAddress 
    * @Description:修改PhiMemberAddress信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiMemberAddressDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiMemberAddress(@PathVariable("id") Long id, @RequestBody PhiMemberAddressDto phiMemberAddressDto) {
        phiMemberAddressService.updatePhiMemberAddress(id, phiMemberAddressDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiMemberAddressById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiMemberAddressById(@PathVariable("id") Long id) {
        phiMemberAddressService.deletePhiMemberAddress(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
