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
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDetailsDto;
import com.huatek.busi.service.phicom.plusmember.PhiPlusRightDetailsService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPLUSRIGHTDETAILS_API)
public class PhiPlusRightDetailsAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPlusRightDetailsAction.class);

    @Autowired
    private PhiPlusRightDetailsService phiPlusRightDetailsService;

    
    /** 
    * @Title: getAllPhiPlusRightDetails 
    * @Description:  翻页查询PhiPlusRightDetails信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPlusRightDetailsDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPlusRightDetailsDto>> getAllPhiPlusRightDetails(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPlusRightDetails of param " + queryPage.getQueryInfo());
        DataPage<PhiPlusRightDetailsDto> phiPlusRightDetailsPages = phiPlusRightDetailsService.getAllPhiPlusRightDetailsPage(queryPage);
        log.debug("get phiPlusRightDetails size @" + phiPlusRightDetailsPages.getContent().size());
        return new ResponseEntity<>(phiPlusRightDetailsPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPlusRightDetailsDto 
    * @Description: 添加PhiPlusRightDetails 
    * @param    phiPlusRightDetailsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPlusRightDetailsDto(@RequestBody PhiPlusRightDetailsDto phiPlusRightDetailsDto) {
        phiPlusRightDetailsService.savePhiPlusRightDetailsDto(phiPlusRightDetailsDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPlusRightDetails创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPlusRightDetailsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPlusRightDetailsDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPlusRightDetailsDto> getPhiPlusRightDetailsDto(@PathVariable("id") Long id) {
    	PhiPlusRightDetailsDto phiPlusRightDetailsDto = phiPlusRightDetailsService.getPhiPlusRightDetailsDtoById(id);
        return new ResponseEntity<>(phiPlusRightDetailsDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPlusRightDetails 
    * @Description:修改PhiPlusRightDetails信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPlusRightDetailsDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPlusRightDetails(@PathVariable("id") Long id, @RequestBody PhiPlusRightDetailsDto phiPlusRightDetailsDto) {
        phiPlusRightDetailsService.updatePhiPlusRightDetails(id, phiPlusRightDetailsDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPlusRightDetailsById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPlusRightDetailsById(@PathVariable("id") Long id) {
        phiPlusRightDetailsService.deletePhiPlusRightDetails(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
