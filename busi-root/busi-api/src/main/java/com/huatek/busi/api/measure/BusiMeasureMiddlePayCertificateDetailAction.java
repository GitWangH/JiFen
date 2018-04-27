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
import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateDetailDto;
import com.huatek.busi.service.measure.BusiMeasureMiddlePayCertificateDetailService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIMEASUREMIDDLEPAYCERTIFICATEDETAIL_API)
public class BusiMeasureMiddlePayCertificateDetailAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiMeasureMiddlePayCertificateDetailAction.class);

    @Autowired
    private BusiMeasureMiddlePayCertificateDetailService busiMeasureMiddlePayCertificateDetailService;

    
    /** 
    * @Title: getAllBusiMeasureMiddlePayCertificateDetail 
    * @Description:  翻页查询BusiMeasureMiddlePayCertificateDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureMiddlePayCertificateDetailDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureMiddlePayCertificateDetailDto>> getAllBusiMeasureMiddlePayCertificateDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all busiMeasureMiddlePayCertificateDetail of param " + queryPage.getQueryInfo());
        DataPage<BusiMeasureMiddlePayCertificateDetailDto> busiMeasureMiddlePayCertificateDetailPages = busiMeasureMiddlePayCertificateDetailService.getAllBusiMeasureMiddlePayCertificateDetailPage(queryPage);
        log.debug("get busiMeasureMiddlePayCertificateDetail size @" + busiMeasureMiddlePayCertificateDetailPages.getContent().size());
        return new ResponseEntity<>(busiMeasureMiddlePayCertificateDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureMiddlePayCertificateDetailDto 
    * @Description: 添加BusiMeasureMiddlePayCertificateDetail 
    * @param    busiMeasureMiddlePayCertificateDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureMiddlePayCertificateDetailDto(@RequestBody BusiMeasureMiddlePayCertificateDetailDto busiMeasureMiddlePayCertificateDetailDto) throws Exception {
        busiMeasureMiddlePayCertificateDetailService.saveBusiMeasureMiddlePayCertificateDetailDto(busiMeasureMiddlePayCertificateDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiMeasureMiddlePayCertificateDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureMiddlePayCertificateDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureMiddlePayCertificateDetailDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureMiddlePayCertificateDetailDto> getBusiMeasureMiddlePayCertificateDetailDto(@PathVariable("id") Long id) {
    	BusiMeasureMiddlePayCertificateDetailDto busiMeasureMiddlePayCertificateDetailDto = busiMeasureMiddlePayCertificateDetailService.getBusiMeasureMiddlePayCertificateDetailDtoById(id);
        return new ResponseEntity<>(busiMeasureMiddlePayCertificateDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureMiddlePayCertificateDetail 
    * @Description:修改BusiMeasureMiddlePayCertificateDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureMiddlePayCertificateDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureMiddlePayCertificateDetail(@PathVariable("id") Long id, @RequestBody BusiMeasureMiddlePayCertificateDetailDto busiMeasureMiddlePayCertificateDetailDto) throws Exception {
        busiMeasureMiddlePayCertificateDetailService.updateBusiMeasureMiddlePayCertificateDetail(id, busiMeasureMiddlePayCertificateDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureMiddlePayCertificateDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureMiddlePayCertificateDetailById(@PathVariable("id") Long id) throws Exception {
        busiMeasureMiddlePayCertificateDetailService.deleteBusiMeasureMiddlePayCertificateDetail(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
