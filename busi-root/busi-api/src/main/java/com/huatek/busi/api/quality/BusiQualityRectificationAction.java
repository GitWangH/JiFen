package com.huatek.busi.api.quality;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.service.quality.BusiQualityRawMaterialInspectionService;
import com.huatek.busi.service.quality.BusiQualityRectificationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYRECTIFICATION_API)
public class BusiQualityRectificationAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityRectificationAction.class);

    @Autowired
    private BusiQualityRectificationService busiQualityRectificationService;
    @Autowired
    private BusiQualityRawMaterialInspectionService busiQualityRawMaterialInspectionService;
    
    /** 
    * @Title: getAllBusiQualityRectification 
    * @Description:  翻页查询BusiQualityRectification信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityRectificationDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<?>> getAllBusiQualityRectification(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
	   log.debug("get all busiQualityRectification of param " + queryPage.getQueryInfo());
       DataPage<BusiQualityRectificationDto> busiQualityRectificationPages = busiQualityRectificationService.getAllBusiQualityRectificationPage(queryPage);
       log.debug("get busiQualityRectification size @" + busiQualityRectificationPages.getContent().size());
       return new ResponseEntity<>(busiQualityRectificationPages, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityRectificationDto 
    * @Description: 添加BusiQualityRectification 
    * @param    busiQualityRectificationDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityRectificationDto(@RequestBody BusiQualityRectificationDto busiQualityRectificationDto) throws Exception {
        busiQualityRectificationService.saveBusiQualityRectificationDto(busiQualityRectificationDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityRectification创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityRectificationDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityRectificationDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityRectificationDto> getBusiQualityRectificationDto(@PathVariable("id") Long id) {
    	BusiQualityRectificationDto busiQualityRectificationDto = busiQualityRectificationService.getBusiQualityRectificationDtoById(id);
        return new ResponseEntity<>(busiQualityRectificationDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityRectification 
    * @Description:修改BusiQualityRectification信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityRectificationDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityRectification(@PathVariable("id") Long id, @RequestBody BusiQualityRectificationDto busiQualityRectificationDto) throws Exception {
        busiQualityRectificationService.updateBusiQualityRectification(id, busiQualityRectificationDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityRectificationById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityRectificationById(@PathVariable("id") Long id) throws Exception {
        busiQualityRectificationService.deleteBusiQualityRectification(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: getBusiQualityRectificationDto 
     * @Description: 获取需要整改单通过整改单编号
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    rectificationCode 整改单编号
     * @return   ResponseEntity<BusiQualityRectificationDto>    
     * @throws 
     */ 
     @RequestMapping(value = "/queryRectification/{rectificationCode}", method = RequestMethod.GET)
     @ResponseBody
     public ResponseEntity<BusiQualityRectificationDto> getBusiQualityRectificationDtoByRectificationCode(@PathVariable("rectificationCode") String rectificationCode) {
     	BusiQualityRectificationDto busiQualityRectificationDto = busiQualityRectificationService.getBusiQualityRectificationDtoByCode(rectificationCode);
     	return new ResponseEntity<>(busiQualityRectificationDto, HttpStatus.OK);
     }
}
