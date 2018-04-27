package com.huatek.busi.api.phicom.member;
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
import com.huatek.busi.dto.phicom.member.PhiVirtualUserDto;
import com.huatek.busi.service.phicom.member.PhiVirtualUserService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIVIRTUALUSER_API)
public class PhiVirtualUserAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiVirtualUserAction.class);

    @Autowired
    private PhiVirtualUserService phiVirtualUserService;

    
    /** 
    * @Title: getAllPhiVirtualUser 
    * @Description:  翻页查询PhiVirtualUser信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiVirtualUserDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiVirtualUserDto>> getAllPhiVirtualUser(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiVirtualUser of param " + queryPage.getQueryInfo());
        DataPage<PhiVirtualUserDto> phiVirtualUserPages = phiVirtualUserService.getAllPhiVirtualUserPage(queryPage);
        log.debug("get phiVirtualUser size @" + phiVirtualUserPages.getContent().size());
        return new ResponseEntity<>(phiVirtualUserPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiVirtualUserDto 
    * @Description: 添加PhiVirtualUser 
    * @param    phiVirtualUserDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiVirtualUserDto(@RequestBody PhiVirtualUserDto phiVirtualUserDto) {
        phiVirtualUserService.savePhiVirtualUserDto(phiVirtualUserDto);
        return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiVirtualUserDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiVirtualUserDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiVirtualUserDto> getPhiVirtualUserDto(@PathVariable("id") Long id) {
    	PhiVirtualUserDto phiVirtualUserDto = phiVirtualUserService.getPhiVirtualUserDtoById(id);
        return new ResponseEntity<>(phiVirtualUserDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiVirtualUser 
    * @Description:修改PhiVirtualUser信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiVirtualUserDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiVirtualUser(@PathVariable("id") Long id, @RequestBody PhiVirtualUserDto phiVirtualUserDto) {
        phiVirtualUserService.updatePhiVirtualUser(id, phiVirtualUserDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiVirtualUserById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiVirtualUserById(@PathVariable("id") Long id) {
        phiVirtualUserService.deletePhiVirtualUser(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
