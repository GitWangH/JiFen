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
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerExceedDto;
import com.huatek.busi.service.quality.BusiQualitySpreaderRollerExceedService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYSPREADERROLLEREXCEED_API)
public class BusiQualitySpreaderRollerExceedAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualitySpreaderRollerExceedAction.class);

    @Autowired
    private BusiQualitySpreaderRollerExceedService busiQualitySpreaderRollerExceedService;

    
    /** 
    * @Title: getAllBusiQualitySpreaderRollerExceed 
    * @Description:  翻页查询BusiQualitySpreaderRollerExceed信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualitySpreaderRollerExceedDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualitySpreaderRollerExceedDto>> getAllBusiQualitySpreaderRollerExceed(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiQualitySpreaderRollerExceedDto> busiQualitySpreaderRollerExceedPages = busiQualitySpreaderRollerExceedService.getAllBusiQualitySpreaderRollerExceedPage(queryPage);
        return new ResponseEntity<>(busiQualitySpreaderRollerExceedPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualitySpreaderRollerExceedDto 
    * @Description: 添加BusiQualitySpreaderRollerExceed 
    * @param    busiQualitySpreaderRollerExceedDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualitySpreaderRollerExceedDto(@RequestBody BusiQualitySpreaderRollerExceedDto busiQualitySpreaderRollerExceedDto) throws Exception {
        busiQualitySpreaderRollerExceedService.saveBusiQualitySpreaderRollerExceedDto(busiQualitySpreaderRollerExceedDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualitySpreaderRollerExceed创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualitySpreaderRollerExceedDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualitySpreaderRollerExceedDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualitySpreaderRollerExceedDto> getBusiQualitySpreaderRollerExceedDto(@PathVariable("id") Long id) {
    	BusiQualitySpreaderRollerExceedDto busiQualitySpreaderRollerExceedDto = busiQualitySpreaderRollerExceedService.getBusiQualitySpreaderRollerExceedDtoById(id);
        return new ResponseEntity<>(busiQualitySpreaderRollerExceedDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualitySpreaderRollerExceed 
    * @Description:修改BusiQualitySpreaderRollerExceed信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualitySpreaderRollerExceedDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualitySpreaderRollerExceed(@PathVariable("id") Long id, @RequestBody BusiQualitySpreaderRollerExceedDto busiQualitySpreaderRollerExceedDto) throws Exception {
        busiQualitySpreaderRollerExceedService.updateBusiQualitySpreaderRollerExceed(id, busiQualitySpreaderRollerExceedDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualitySpreaderRollerExceedById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualitySpreaderRollerExceedById(@PathVariable("id") Long id) throws Exception {
        busiQualitySpreaderRollerExceedService.deleteBusiQualitySpreaderRollerExceed(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
