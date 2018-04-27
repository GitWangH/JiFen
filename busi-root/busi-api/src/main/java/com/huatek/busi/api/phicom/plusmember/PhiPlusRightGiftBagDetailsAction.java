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
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDetailsDto;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightGiftBagDetailsService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSRIGHTGIFTBAGDETAILS_API)
public class PhiPlusRightGiftBagDetailsAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusRightGiftBagDetailsAction.class);

    @Autowired
    private PhiPlusRightGiftBagDetailsService phiPlusRightGiftBagDetailsService;

    
    /** 
    * @Title: getAllPhiPlusRightGiftBagDetails 
    * @Description:  翻页查询PhiPlusRightGiftBagDetails信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusRightGiftBagDetailsDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusRightGiftBagDetailsDto>> getAllPhiPlusRightGiftBagDetails(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPlusRightGiftBagDetails of param " + queryPage.getQueryInfo());
        DataPage<PhiPlusRightGiftBagDetailsDto> phiPlusRightGiftBagDetailsPages = phiPlusRightGiftBagDetailsService.getAllPhiPlusRightGiftBagDetailsPage(queryPage);
        log.debug("get phiPlusRightGiftBagDetails size @" + phiPlusRightGiftBagDetailsPages.getContent().size());
        return new ResponseEntity<>(phiPlusRightGiftBagDetailsPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPlusRightGiftBagDetailsDto 
    * @Description: 添加PhiPlusRightGiftBagDetails 
    * @param    phiPlusRightGiftBagDetailsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusRightGiftBagDetailsDto(@RequestBody PhiPlusRightGiftBagDetailsDto phiPlusRightGiftBagDetailsDto) {
        phiPlusRightGiftBagDetailsService.savePhiPlusRightGiftBagDetailsDto(phiPlusRightGiftBagDetailsDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusRightGiftBagDetails创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPlusRightGiftBagDetailsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPlusRightGiftBagDetailsDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusRightGiftBagDetailsDto> getPhiPlusRightGiftBagDetailsDto(@PathVariable("id") Long id) {
    	PhiPlusRightGiftBagDetailsDto phiPlusRightGiftBagDetailsDto = phiPlusRightGiftBagDetailsService.getPhiPlusRightGiftBagDetailsDtoById(id);
        return new ResponseEntity<>(phiPlusRightGiftBagDetailsDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPlusRightGiftBagDetails 
    * @Description:修改PhiPlusRightGiftBagDetails信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPlusRightGiftBagDetailsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusRightGiftBagDetails(@PathVariable("id") Long id, @RequestBody PhiPlusRightGiftBagDetailsDto phiPlusRightGiftBagDetailsDto) {
        phiPlusRightGiftBagDetailsService.updatePhiPlusRightGiftBagDetails(id, phiPlusRightGiftBagDetailsDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPlusRightGiftBagDetailsById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusRightGiftBagDetailsById(@PathVariable("id") Long id) {
        phiPlusRightGiftBagDetailsService.deletePhiPlusRightGiftBagDetails(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
