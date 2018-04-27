package com.huatek.busi.api.measure;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateTemplateDto;
import com.huatek.busi.service.measure.BusiMeasureMiddlePayCertificateTemplateService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIMEASUREMIDDLEPAYCERTIFICATETEMPLATE_API)
public class BusiMeasureMiddlePayCertificateTemplateAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiMeasureMiddlePayCertificateTemplateAction.class);

    @Autowired
    private BusiMeasureMiddlePayCertificateTemplateService busiMeasureMiddlePayCertificateTemplateService;

    
    /** 
    * @Title: getAllBusiMeasureMiddlePayCertificateTemplate 
    * @Description:  翻页查询BusiMeasureMiddlePayCertificateTemplate信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureMiddlePayCertificateTemplateDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureMiddlePayCertificateTemplateDto>> getAllBusiMeasureMiddlePayCertificateTemplate(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiMeasureMiddlePayCertificateTemplateDto> busiMeasureMiddlePayCertificateTemplatePages = busiMeasureMiddlePayCertificateTemplateService.getAllBusiMeasureMiddlePayCertificateTemplatePage(queryPage);
        return new ResponseEntity<>(busiMeasureMiddlePayCertificateTemplatePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureMiddlePayCertificateTemplateDto 
    * @Description: 添加BusiMeasureMiddlePayCertificateTemplate 
    * @param    busiMeasureMiddlePayCertificateTemplateDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureMiddlePayCertificateTemplateDto(@RequestBody BusiMeasureMiddlePayCertificateTemplateDto busiMeasureMiddlePayCertificateTemplateDto) throws Exception {
        busiMeasureMiddlePayCertificateTemplateService.saveBusiMeasureMiddlePayCertificateTemplateDto(busiMeasureMiddlePayCertificateTemplateDto);
        return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureMiddlePayCertificateTemplateDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureMiddlePayCertificateTemplateDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureMiddlePayCertificateTemplateDto> getBusiMeasureMiddlePayCertificateTemplateDto(@PathVariable("id") Long id) {
    	BusiMeasureMiddlePayCertificateTemplateDto busiMeasureMiddlePayCertificateTemplateDto = busiMeasureMiddlePayCertificateTemplateService.getBusiMeasureMiddlePayCertificateTemplateDtoById(id);
        return new ResponseEntity<>(busiMeasureMiddlePayCertificateTemplateDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureMiddlePayCertificateTemplate 
    * @Description:修改BusiMeasureMiddlePayCertificateTemplate信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureMiddlePayCertificateTemplateDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureMiddlePayCertificateTemplate(@PathVariable("id") Long id, @RequestBody BusiMeasureMiddlePayCertificateTemplateDto busiMeasureMiddlePayCertificateTemplateDto) throws Exception {
        busiMeasureMiddlePayCertificateTemplateService.updateBusiMeasureMiddlePayCertificateTemplate(id, busiMeasureMiddlePayCertificateTemplateDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureMiddlePayCertificateTemplateById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureMiddlePayCertificateTemplateById(@PathVariable("id") Long id) throws Exception {
        busiMeasureMiddlePayCertificateTemplateService.deleteBusiMeasureMiddlePayCertificateTemplate(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    @RequestMapping(value = "/queryAll/{orgId}")
    @ResponseBody
    public ResponseEntity<List<BusiMeasureMiddlePayCertificateTemplateDto>> _getAllBusiMeasureMiddlePayCertificateTemplateByOrgId(@PathVariable("orgId") Long orgId ) { 	
        List<BusiMeasureMiddlePayCertificateTemplateDto> busiMeasureMiddlePayCertificateTemplate = busiMeasureMiddlePayCertificateTemplateService.getAllBusiMeasureMiddlePayCertificateTemplateDto(orgId);
        return new ResponseEntity<>(busiMeasureMiddlePayCertificateTemplate, HttpStatus.OK);
       
    }
}
