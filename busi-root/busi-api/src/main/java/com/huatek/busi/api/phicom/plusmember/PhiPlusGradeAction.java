package com.huatek.busi.api.phicom.plusmember;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.member.PhiMemberDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusGradeDto;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.plusmember.PhiPlusGradeService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSGRADE_API)
public class PhiPlusGradeAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusGradeAction.class);
    @Autowired
    private PhiMemberService phiMemberService;
    @Autowired
    private PhiPlusGradeService phiPlusGradeService;

    
    
    
    /** 
     * @Title: createPhiPlusGradeDto 
     * @Description: Plus会员等级价格配置
     * @param    
     * @return   ResponseEntity<ResponseMessage>    
     */ 
     @RequestMapping(value = "/showGradeForOrder", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<String> plusGradeWithOrder(HttpServletRequest request) {
    	 List<PhiPlusGradeDto> phiPlus = phiPlusGradeService.getAllPhiPlusGradeDto();
    	 JsonObject jsonObject = new JsonObject();
    	 if(phiPlus!=null){
    		 PhiPlusGradeDto plusGrade=phiPlus.get(0);
        	 jsonObject.addProperty("orderPrice",plusGrade.getRechargeMoney());
        	 jsonObject.addProperty("goodsName",plusGrade.getPlusGrade());
        	 jsonObject.addProperty("plusCode",plusGrade.getPlusCode());
        	 CloudMember cm=(CloudMember) request.getSession().getAttribute(SessionKey.currentMember);
        	 PhiMemberDto member = phiMemberService.getPhiMemberVOByUId(Integer.valueOf(cm.getUid()));
        	 jsonObject.addProperty("memberId",member.getId()); 
    	 }else{
    		 jsonObject.addProperty("status","没有查询到会员plus购买信息");
    	 }
    	
         return new ResponseEntity<>(new Gson().toJson(jsonObject), HttpStatus.OK);
     }
    
    /** 
    * @Title: getAllPhiPlusGrade 
    * @Description:  翻页查询PhiPlusGrade信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusGradeDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusGradeDto>> getAllPhiPlusGrade(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPlusGrade of param " + queryPage.getQueryInfo());
        DataPage<PhiPlusGradeDto> phiPlusGradePages = phiPlusGradeService.getAllPhiPlusGradePage(queryPage);
        log.debug("get phiPlusGrade size @" + phiPlusGradePages.getContent().size());
        return new ResponseEntity<>(phiPlusGradePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPlusGradeDto 
    * @Description: 添加PhiPlusGrade 
    * @param    phiPlusGradeDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusGradeDto(@RequestBody PhiPlusGradeDto phiPlusGradeDto) {
        phiPlusGradeService.savePhiPlusGradeDto(phiPlusGradeDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusGrade创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPlusGradeDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPlusGradeDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusGradeDto> getPhiPlusGradeDto(@PathVariable("id") Long id) {
    	PhiPlusGradeDto phiPlusGradeDto = phiPlusGradeService.getPhiPlusGradeDtoById(id);
        return new ResponseEntity<>(phiPlusGradeDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPlusGrade 
    * @Description:修改PhiPlusGrade信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPlusGradeDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusGrade(@PathVariable("id") Long id, @RequestBody PhiPlusGradeDto phiPlusGradeDto) {
        phiPlusGradeService.updatePhiPlusGrade(id, phiPlusGradeDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPlusGradeById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusGradeById(@PathVariable("id") Long id) {
        phiPlusGradeService.deletePhiPlusGrade(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
