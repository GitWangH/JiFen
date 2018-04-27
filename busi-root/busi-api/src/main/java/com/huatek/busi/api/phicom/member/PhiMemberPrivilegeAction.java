package com.huatek.busi.api.phicom.member;
import java.io.IOException;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.member.PhiMemberPrivilegeDto;
import com.huatek.busi.service.phicom.member.PhiMemberPrivilegeService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIMEMBERPRIVILEGE_API)
public class PhiMemberPrivilegeAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiMemberPrivilegeAction.class);

    @Autowired
    private PhiMemberPrivilegeService phiMemberPrivilegeService;

    
    /** 
    * @Title: getAllPhiMemberPrivilege 
    * @Description:  翻页查询PhiMemberPrivilege信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiMemberPrivilegeDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiMemberPrivilegeDto>> getAllPhiMemberPrivilege(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all phiMemberPrivilege of param " + queryPage.getQueryInfo());
        DataPage<PhiMemberPrivilegeDto> phiMemberPrivilegePages = phiMemberPrivilegeService.getAllPhiMemberPrivilegePage(queryPage);
        log.debug("get phiMemberPrivilege size @" + phiMemberPrivilegePages.getContent().size());
        return new ResponseEntity<>(phiMemberPrivilegePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiMemberPrivilegeDto 
    * @Description: 添加PhiMemberPrivilege 
    * @param    phiMemberPrivilegeDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiMemberPrivilegeDto(@RequestBody PhiMemberPrivilegeDto phiMemberPrivilegeDto) throws Exception {
        phiMemberPrivilegeService.savePhiMemberPrivilegeDto(phiMemberPrivilegeDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiMemberPrivilege创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiMemberPrivilegeDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiMemberPrivilegeDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiMemberPrivilegeDto> getPhiMemberPrivilegeDto(@PathVariable("id") Long id) {
    	PhiMemberPrivilegeDto phiMemberPrivilegeDto = phiMemberPrivilegeService.getPhiMemberPrivilegeDtoById(id);
        return new ResponseEntity<>(phiMemberPrivilegeDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiMemberPrivilege 
    * @Description:修改PhiMemberPrivilege信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiMemberPrivilegeDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiMemberPrivilege(@PathVariable("id") Long id, @RequestBody PhiMemberPrivilegeDto phiMemberPrivilegeDto) throws Exception {
        phiMemberPrivilegeService.updatePhiMemberPrivilege(id, phiMemberPrivilegeDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiMemberPrivilegeById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiMemberPrivilegeById(@PathVariable("id") Long id) throws Exception {
        phiMemberPrivilegeService.deletePhiMemberPrivilege(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * @Title: editPhiMemberPrivilegeState 
     * @Description: 修改会员特权权限状态（开启/关闭）信息.
     * @param id
     * @param state
     * @return ResponseEntity<ResponseMessage>
     */
     @RequestMapping(value = "/editState/{id}/{state}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> editPhiMemberPrivilegeState(@PathVariable("id") Long id,@PathVariable("state") Integer state) {
    	 phiMemberPrivilegeService.editPhiMemberPrivilegeState(id,state);
         return new ResponseEntity<>(ResponseMessage.success("设置成功"), HttpStatus.OK);
     }
}
