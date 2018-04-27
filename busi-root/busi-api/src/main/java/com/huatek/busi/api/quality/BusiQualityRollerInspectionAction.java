package com.huatek.busi.api.quality;
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
import com.huatek.busi.dto.quality.BusiQualityRollerInspectionDto;
import com.huatek.busi.service.quality.BusiQualityRollerInspectionService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYROLLERINSPECTION_API)
public class BusiQualityRollerInspectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityRollerInspectionAction.class);

    @Autowired
    private BusiQualityRollerInspectionService busiQualityRollerInspectionService;

    
    /** 
    * @Title: getAllBusiQualityRollerInspection 
    * @Description:  翻页查询BusiQualityRollerInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityRollerInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityRollerInspectionDto>> getAllBusiQualityRollerInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiQualityRollerInspectionDto> busiQualityRollerInspectionPages = busiQualityRollerInspectionService.getAllBusiQualityRollerInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualityRollerInspectionPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityRollerInspectionDto 
    * @Description: 添加BusiQualityRollerInspection 
    * @param    busiQualityRollerInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityRollerInspectionDto(@RequestBody BusiQualityRollerInspectionDto busiQualityRollerInspectionDto) throws Exception {
        busiQualityRollerInspectionService.saveBusiQualityRollerInspectionDto(busiQualityRollerInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityRollerInspection创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityRollerInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityRollerInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityRollerInspectionDto> getBusiQualityRollerInspectionDto(@PathVariable("id") Long id) {
    	BusiQualityRollerInspectionDto busiQualityRollerInspectionDto = busiQualityRollerInspectionService.getBusiQualityRollerInspectionDtoById(id);
        return new ResponseEntity<>(busiQualityRollerInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityRollerInspection 
    * @Description:修改BusiQualityRollerInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityRollerInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityRollerInspection(@PathVariable("id") Long id, @RequestBody BusiQualityRollerInspectionDto busiQualityRollerInspectionDto) throws Exception {
        busiQualityRollerInspectionService.updateBusiQualityRollerInspection(id, busiQualityRollerInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityRollerInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityRollerInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualityRollerInspectionService.deleteBusiQualityRollerInspection(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
