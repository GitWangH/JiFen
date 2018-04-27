package com.huatek.busi.api.phicom.coupons;
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
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDto;
import com.huatek.busi.service.phicom.coupons.PhiThirdPartyCouponsService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHITHIRDPARTYCOUPONS_API)
public class PhiThirdPartyCouponsAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiThirdPartyCouponsAction.class);

    @Autowired
    private PhiThirdPartyCouponsService phiThirdPartyCouponsService;

    
    /** 
    * @Title: getAllPhiThirdPartyCoupons 
    * @Description:  翻页查询PhiThirdPartyCoupons信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiThirdPartyCouponsDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiThirdPartyCouponsDto>> getAllPhiThirdPartyCoupons(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all phiThirdPartyCoupons of param " + queryPage.getQueryInfo());
        DataPage<PhiThirdPartyCouponsDto> phiThirdPartyCouponsPages = phiThirdPartyCouponsService.getAllPhiThirdPartyCouponsPage(queryPage);
        log.debug("get phiThirdPartyCoupons size @" + phiThirdPartyCouponsPages.getContent().size());
        return new ResponseEntity<>(phiThirdPartyCouponsPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiThirdPartyCouponsDto 
    * @Description: 添加PhiThirdPartyCoupons 
    * @param    phiThirdPartyCouponsDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiThirdPartyCouponsDto(@RequestBody PhiThirdPartyCouponsDto phiThirdPartyCouponsDto) throws Exception {
        phiThirdPartyCouponsService.savePhiThirdPartyCouponsDto(phiThirdPartyCouponsDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiThirdPartyCoupons创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiThirdPartyCouponsDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiThirdPartyCouponsDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiThirdPartyCouponsDto> getPhiThirdPartyCouponsDto(@PathVariable("id") Long id) {
    	PhiThirdPartyCouponsDto phiThirdPartyCouponsDto = phiThirdPartyCouponsService.getPhiThirdPartyCouponsDtoById(id);
        return new ResponseEntity<>(phiThirdPartyCouponsDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiThirdPartyCoupons 
    * @Description:修改PhiThirdPartyCoupons信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiThirdPartyCouponsDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiThirdPartyCoupons(@PathVariable("id") Long id, @RequestBody PhiThirdPartyCouponsDto phiThirdPartyCouponsDto) throws Exception {
        phiThirdPartyCouponsService.updatePhiThirdPartyCoupons(id, phiThirdPartyCouponsDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiThirdPartyCouponsById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiThirdPartyCouponsById(@PathVariable("id") Long id) throws Exception {
        phiThirdPartyCouponsService.deletePhiThirdPartyCoupons(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
