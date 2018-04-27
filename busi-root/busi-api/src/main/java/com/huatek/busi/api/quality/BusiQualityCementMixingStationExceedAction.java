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
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationExceedDto;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationExceedService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYCEMENTMIXINGSTATIONEXCEED_API)
public class BusiQualityCementMixingStationExceedAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityCementMixingStationExceedAction.class);

    @Autowired
    private BusiQualityCementMixingStationExceedService busiQualityCementMixingStationExceedService;

    
    /** 
    * @Title: getAllBusiQualityCementMixingStationExceed 
    * @Description:  翻页查询BusiQualityCementMixingStationExceed信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityCementMixingStationExceedDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityCementMixingStationExceedDto>> getAllBusiQualityCementMixingStationExceed(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiQualityCementMixingStationExceedDto> busiQualityCementMixingStationExceedPages = busiQualityCementMixingStationExceedService.getAllBusiQualityCementMixingStationExceedPage(queryPage);
        return new ResponseEntity<>(busiQualityCementMixingStationExceedPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityCementMixingStationExceedDto 
    * @Description: 添加BusiQualityCementMixingStationExceed 
    * @param    busiQualityCementMixingStationExceedDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityCementMixingStationExceedDto(@RequestBody BusiQualityCementMixingStationExceedDto busiQualityCementMixingStationExceedDto) throws Exception {
        busiQualityCementMixingStationExceedService.saveBusiQualityCementMixingStationExceedDto(busiQualityCementMixingStationExceedDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityCementMixingStationExceed创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityCementMixingStationExceedDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityCementMixingStationExceedDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityCementMixingStationExceedDto> getBusiQualityCementMixingStationExceedDto(@PathVariable("id") Long id) {
    	BusiQualityCementMixingStationExceedDto busiQualityCementMixingStationExceedDto = busiQualityCementMixingStationExceedService.getBusiQualityCementMixingStationExceedDtoById(id);
        return new ResponseEntity<>(busiQualityCementMixingStationExceedDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityCementMixingStationExceed 
    * @Description:修改BusiQualityCementMixingStationExceed信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityCementMixingStationExceedDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityCementMixingStationExceed(@PathVariable("id") Long id, @RequestBody BusiQualityCementMixingStationExceedDto busiQualityCementMixingStationExceedDto) throws Exception {
        busiQualityCementMixingStationExceedService.updateBusiQualityCementMixingStationExceed(id, busiQualityCementMixingStationExceedDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityCementMixingStationExceedById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityCementMixingStationExceedById(@PathVariable("id") Long id) throws Exception {
        busiQualityCementMixingStationExceedService.deleteBusiQualityCementMixingStationExceed(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
