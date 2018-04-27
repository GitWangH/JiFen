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
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDetailDto;
import com.huatek.busi.service.phicom.coupons.PhiThirdPartyCouponsDetailService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHITHIRDPARTYCOUPONSDETAIL_API)
public class PhiThirdPartyCouponsDetailAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiThirdPartyCouponsDetailAction.class);

    @Autowired
    private PhiThirdPartyCouponsDetailService phiThirdPartyCouponsDetailService;

    
    /** 
    * @Title: getAllPhiThirdPartyCouponsDetail 
    * @Description:  翻页查询PhiThirdPartyCouponsDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiThirdPartyCouponsDetailDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiThirdPartyCouponsDetailDto>> getAllPhiThirdPartyCouponsDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all phiThirdPartyCouponsDetail of param " + queryPage.getQueryInfo());
        DataPage<PhiThirdPartyCouponsDetailDto> phiThirdPartyCouponsDetailPages = phiThirdPartyCouponsDetailService.getAllPhiThirdPartyCouponsDetailPage(queryPage);
        log.debug("get phiThirdPartyCouponsDetail size @" + phiThirdPartyCouponsDetailPages.getContent().size());
        return new ResponseEntity<>(phiThirdPartyCouponsDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiThirdPartyCouponsDetailDto 
    * @Description: 添加PhiThirdPartyCouponsDetail 
    * @param    phiThirdPartyCouponsDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiThirdPartyCouponsDetailDto(@RequestBody PhiThirdPartyCouponsDetailDto phiThirdPartyCouponsDetailDto) throws Exception {
        phiThirdPartyCouponsDetailService.savePhiThirdPartyCouponsDetailDto(phiThirdPartyCouponsDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiThirdPartyCouponsDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiThirdPartyCouponsDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiThirdPartyCouponsDetailDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiThirdPartyCouponsDetailDto> getPhiThirdPartyCouponsDetailDto(@PathVariable("id") Long id) {
    	PhiThirdPartyCouponsDetailDto phiThirdPartyCouponsDetailDto = phiThirdPartyCouponsDetailService.getPhiThirdPartyCouponsDetailDtoById(id);
        return new ResponseEntity<>(phiThirdPartyCouponsDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiThirdPartyCouponsDetail 
    * @Description:修改PhiThirdPartyCouponsDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiThirdPartyCouponsDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiThirdPartyCouponsDetail(@PathVariable("id") Long id, @RequestBody PhiThirdPartyCouponsDetailDto phiThirdPartyCouponsDetailDto) throws Exception {
        phiThirdPartyCouponsDetailService.updatePhiThirdPartyCouponsDetail(id, phiThirdPartyCouponsDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiThirdPartyCouponsDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiThirdPartyCouponsDetailById(@PathVariable("id") Long id) throws Exception {
        phiThirdPartyCouponsDetailService.deletePhiThirdPartyCouponsDetail(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: getCouponsDetailByCouponsId 
     * @Description:通过第三方优惠券id获取优惠券明细信息 
     * @createDate: 2016年4月25日 下午1:49:25
     * @param    cpnsId
     * @param    queryPage
     * @return   ResponseEntity<DataPage<PhiThirdPartyCouponsDetailDto>>    
     */ 
     @RequestMapping(value = "/getCouponsDetailByCouponsId/{cpnsId}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<DataPage<PhiThirdPartyCouponsDetailDto>> getCouponsDetailByWayId(@PathVariable("cpnsId") String cpnsId,@RequestBody QueryPage queryPage) {
    	 DataPage<PhiThirdPartyCouponsDetailDto> phiThirdPartyCouponsDetailDtoPages = phiThirdPartyCouponsDetailService.getThirdPartyPhiCouponsDetailById(cpnsId,queryPage);
         return new ResponseEntity<DataPage<PhiThirdPartyCouponsDetailDto>>(phiThirdPartyCouponsDetailDtoPages, HttpStatus.OK);
     }
}
