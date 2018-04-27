package com.huatek.busi.api.phicom.order;
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
import com.huatek.busi.dto.phicom.order.PhiPayInfoDto;
import com.huatek.busi.service.phicom.order.PhiPayInfoService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIORDERP_API)
public class PhiPayInfoAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiPayInfoAction.class);

    @Autowired
    private PhiPayInfoService phiPayInfoService;

    
    /** 
    * @Title: getAllPhiPayInfo 
    * @Description:  翻页查询PhiPayInfo信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiPayInfoDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiPayInfoDto>> getAllPhiPayInfo(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiPayInfo of param " + queryPage.getQueryInfo());
        DataPage<PhiPayInfoDto> phiPayInfoPages = phiPayInfoService.getAllPhiPayInfoPage(queryPage);
        log.debug("get phiPayInfo size @" + phiPayInfoPages.getContent().size());
        return new ResponseEntity<>(phiPayInfoPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiPayInfoDto 
    * @Description: 添加PhiPayInfo 
    * @param    phiPayInfoDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiPayInfoDto(@RequestBody PhiPayInfoDto phiPayInfoDto) {
        phiPayInfoService.savePhiPayInfoDto(phiPayInfoDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiPayInfo创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiPayInfoDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiPayInfoDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiPayInfoDto> getPhiPayInfoDto(@PathVariable("id") Long id) {
    	PhiPayInfoDto phiPayInfoDto = phiPayInfoService.getPhiPayInfoDtoById(id);
        return new ResponseEntity<>(phiPayInfoDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiPayInfo 
    * @Description:修改PhiPayInfo信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiPayInfoDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiPayInfo(@PathVariable("id") Long id, @RequestBody PhiPayInfoDto phiPayInfoDto) {
        phiPayInfoService.updatePhiPayInfo(id, phiPayInfoDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiPayInfoById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiPayInfoById(@PathVariable("id") Long id) {
        phiPayInfoService.deletePhiPayInfo(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
