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
import com.huatek.busi.dto.quality.BusiQualitySpreaderInspectionDto;
import com.huatek.busi.service.quality.BusiQualitySpreaderInspectionService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYSPREADERINSPECTION_API)
public class BusiQualitySpreaderInspectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualitySpreaderInspectionAction.class);

    @Autowired
    private BusiQualitySpreaderInspectionService busiQualitySpreaderInspectionService;

    
    /** 
    * @Title: getAllBusiQualitySpreaderInspection 
    * @Description:  翻页查询BusiQualitySpreaderInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualitySpreaderInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualitySpreaderInspectionDto>> getAllBusiQualitySpreaderInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiQualitySpreaderInspectionDto> busiQualitySpreaderInspectionPages = busiQualitySpreaderInspectionService.getAllBusiQualitySpreaderInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualitySpreaderInspectionPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualitySpreaderInspectionDto 
    * @Description: 添加BusiQualitySpreaderInspection 
    * @param    busiQualitySpreaderInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualitySpreaderInspectionDto(@RequestBody BusiQualitySpreaderInspectionDto busiQualitySpreaderInspectionDto) throws Exception {
        busiQualitySpreaderInspectionService.saveBusiQualitySpreaderInspectionDto(busiQualitySpreaderInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualitySpreaderInspection创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualitySpreaderInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualitySpreaderInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualitySpreaderInspectionDto> getBusiQualitySpreaderInspectionDto(@PathVariable("id") Long id) {
    	BusiQualitySpreaderInspectionDto busiQualitySpreaderInspectionDto = busiQualitySpreaderInspectionService.getBusiQualitySpreaderInspectionDtoById(id);
        return new ResponseEntity<>(busiQualitySpreaderInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualitySpreaderInspection 
    * @Description:修改BusiQualitySpreaderInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualitySpreaderInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualitySpreaderInspection(@PathVariable("id") Long id, @RequestBody BusiQualitySpreaderInspectionDto busiQualitySpreaderInspectionDto) throws Exception {
        busiQualitySpreaderInspectionService.updateBusiQualitySpreaderInspection(id, busiQualitySpreaderInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualitySpreaderInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualitySpreaderInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualitySpreaderInspectionService.deleteBusiQualitySpreaderInspection(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
