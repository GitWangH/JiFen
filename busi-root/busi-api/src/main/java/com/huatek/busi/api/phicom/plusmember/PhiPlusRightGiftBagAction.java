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
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDto;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightGiftBagService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSRIGHTGIFTBAG_API)
public class PhiPlusRightGiftBagAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusRightGiftBagAction.class);

    @Autowired
    private PhiPlusRightGiftBagService phiPlusRightGiftBagService;

    
    /** 
    * @Title: getAllPhiPlusRightGiftBag 
    * @Description:  翻页查询PhiPlusRightGiftBag信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusRightGiftBagDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusRightGiftBagDto>> getAllPhiPlusRightGiftBag(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPlusRightGiftBag of param " + queryPage.getQueryInfo());
        DataPage<PhiPlusRightGiftBagDto> phiPlusRightGiftBagPages = phiPlusRightGiftBagService.getAllPhiPlusRightGiftBagPage(queryPage);
        log.debug("get phiPlusRightGiftBag size @" + phiPlusRightGiftBagPages.getContent().size());
        return new ResponseEntity<>(phiPlusRightGiftBagPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPlusRightGiftBagDto 
    * @Description: 添加PhiPlusRightGiftBag 
    * @param    phiPlusRightGiftBagDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusRightGiftBagDto(@RequestBody PhiPlusRightGiftBagDto phiPlusRightGiftBagDto) {
        phiPlusRightGiftBagService.savePhiPlusRightGiftBagDto(phiPlusRightGiftBagDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusRightGiftBag创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPlusRightGiftBagDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPlusRightGiftBagDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusRightGiftBagDto> getPhiPlusRightGiftBagDto(@PathVariable("id") Long id) {
    	PhiPlusRightGiftBagDto phiPlusRightGiftBagDto = phiPlusRightGiftBagService.getPhiPlusRightGiftBagDtoById(id);
        return new ResponseEntity<>(phiPlusRightGiftBagDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPlusRightGiftBag 
    * @Description:修改PhiPlusRightGiftBag信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPlusRightGiftBagDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusRightGiftBag(@PathVariable("id") Long id, @RequestBody PhiPlusRightGiftBagDto phiPlusRightGiftBagDto) {
        phiPlusRightGiftBagService.updatePhiPlusRightGiftBag(id, phiPlusRightGiftBagDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPlusRightGiftBagById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusRightGiftBagById(@PathVariable("id") Long id) {
        phiPlusRightGiftBagService.deletePhiPlusRightGiftBag(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
