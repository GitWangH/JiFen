package com.huatek.busi.api.measure;
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
import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateDto;
import com.huatek.busi.service.measure.BusiMeasureMiddlePayCertificateService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIMEASUREMIDDLEPAYCERTIFICATE_API)
public class BusiMeasureMiddlePayCertificateAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiMeasureMiddlePayCertificateAction.class);

    @Autowired
    private BusiMeasureMiddlePayCertificateService busiMeasureMiddlePayCertificateService;

    
    /** 
    * @Title: getAllBusiMeasureMiddlePayCertificate 
    * @Description:  翻页查询BusiMeasureMiddlePayCertificate信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureMiddlePayCertificateDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureMiddlePayCertificateDto>> getAllBusiMeasureMiddlePayCertificate(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all busiMeasureMiddlePayCertificate of param " + queryPage.getQueryInfo());
        DataPage<BusiMeasureMiddlePayCertificateDto> busiMeasureMiddlePayCertificatePages = busiMeasureMiddlePayCertificateService.getAllBusiMeasureMiddlePayCertificatePage(queryPage);
        log.debug("get busiMeasureMiddlePayCertificate size @" + busiMeasureMiddlePayCertificatePages.getContent().size());
        return new ResponseEntity<>(busiMeasureMiddlePayCertificatePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureMiddlePayCertificateDto 
    * @Description: 添加BusiMeasureMiddlePayCertificate 
    * @param    busiMeasureMiddlePayCertificateDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureMiddlePayCertificateDto(@RequestBody BusiMeasureMiddlePayCertificateDto busiMeasureMiddlePayCertificateDto) throws Exception {
        busiMeasureMiddlePayCertificateService.saveBusiMeasureMiddlePayCertificateDto(busiMeasureMiddlePayCertificateDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiMeasureMiddlePayCertificate创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureMiddlePayCertificateDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureMiddlePayCertificateDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureMiddlePayCertificateDto> getBusiMeasureMiddlePayCertificateDto(@PathVariable("id") Long id) {
    	BusiMeasureMiddlePayCertificateDto busiMeasureMiddlePayCertificateDto = busiMeasureMiddlePayCertificateService.getBusiMeasureMiddlePayCertificateDtoById(id);
        return new ResponseEntity<>(busiMeasureMiddlePayCertificateDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureMiddlePayCertificate 
    * @Description:修改BusiMeasureMiddlePayCertificate信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureMiddlePayCertificateDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureMiddlePayCertificate(@PathVariable("id") Long id, @RequestBody BusiMeasureMiddlePayCertificateDto busiMeasureMiddlePayCertificateDto) throws Exception {
        busiMeasureMiddlePayCertificateService.updateBusiMeasureMiddlePayCertificate(id, busiMeasureMiddlePayCertificateDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureMiddlePayCertificateById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureMiddlePayCertificateById(@PathVariable("id") Long id) throws Exception {
        busiMeasureMiddlePayCertificateService.deleteBusiMeasureMiddlePayCertificate(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
