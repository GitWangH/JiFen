package com.huatek.busi.api.phicom.plusmember;
import java.io.IOException;
import java.util.List;

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
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDto;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSRIGHT_API)
public class PhiPlusRightAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusRightAction.class);

    @Autowired
    private PhiPlusRightService phiPlusRightService;

    
    /** 
    * @Title: getAllPhiPlusRight 
    * @Description:  翻页查询PhiPlusRight信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusRightDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusRightDto>> getAllPhiPlusRight(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPlusRight of param " + queryPage.getQueryInfo());
        DataPage<PhiPlusRightDto> phiPlusRightPages = phiPlusRightService.getAllPhiPlusRightPage(queryPage);
        log.debug("get phiPlusRight size @" + phiPlusRightPages.getContent().size());
        return new ResponseEntity<>(phiPlusRightPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPlusRightDto 
    * @Description: 添加PhiPlusRight 
    * @param    phiPlusRightDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusRightDto(@RequestBody PhiPlusRightDto phiPlusRightDto) {
        phiPlusRightService.savePhiPlusRightDto(phiPlusRightDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusRight创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPlusRightDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPlusRightDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusRightDto> getPhiPlusRightDto(@PathVariable("id") Long id) {
    	PhiPlusRightDto phiPlusRightDto = phiPlusRightService.getPhiPlusRightDtoById(id);
        return new ResponseEntity<>(phiPlusRightDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPlusRight 
    * @Description:修改PhiPlusRight信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPlusRightDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusRight(@PathVariable("id") Long id, @RequestBody PhiPlusRightDto phiPlusRightDto) {
        phiPlusRightService.updatePhiPlusRight(id, phiPlusRightDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPlusRightById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusRightById(@PathVariable("id") Long id) {
        phiPlusRightService.deletePhiPlusRight(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
