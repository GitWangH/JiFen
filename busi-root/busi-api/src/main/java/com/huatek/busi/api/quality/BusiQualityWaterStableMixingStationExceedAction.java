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
import com.huatek.busi.dto.quality.BusiQualityWaterStableMixingStationExceedDto;
import com.huatek.busi.service.quality.BusiQualityWaterStableMixingStationExceedService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYWATERSTABLEMIXINGSTATIONEXCEED_API)
public class BusiQualityWaterStableMixingStationExceedAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityWaterStableMixingStationExceedAction.class);

    @Autowired
    private BusiQualityWaterStableMixingStationExceedService busiQualityWaterStableMixingStationExceedService;

    
    /** 
    * @Title: getAllBusiQualityWaterStableMixingStationExceed 
    * @Description:  翻页查询BusiQualityWaterStableMixingStationExceed信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityWaterStableMixingStationExceedDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityWaterStableMixingStationExceedDto>> getAllBusiQualityWaterStableMixingStationExceed(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	DataPage<BusiQualityWaterStableMixingStationExceedDto> busiQualityWaterStableMixingStationExceedPages = busiQualityWaterStableMixingStationExceedService.getAllBusiQualityWaterStableMixingStationExceedPage(queryPage);
        return new ResponseEntity<>(busiQualityWaterStableMixingStationExceedPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityWaterStableMixingStationExceedDto 
    * @Description: 添加BusiQualityWaterStableMixingStationExceed 
    * @param    busiQualityWaterStableMixingStationExceedDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityWaterStableMixingStationExceedDto(@RequestBody BusiQualityWaterStableMixingStationExceedDto busiQualityWaterStableMixingStationExceedDto) throws Exception {
        busiQualityWaterStableMixingStationExceedService.saveBusiQualityWaterStableMixingStationExceedDto(busiQualityWaterStableMixingStationExceedDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityWaterStableMixingStationExceed创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityWaterStableMixingStationExceedDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityWaterStableMixingStationExceedDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityWaterStableMixingStationExceedDto> getBusiQualityWaterStableMixingStationExceedDto(@PathVariable("id") Long id) {
    	BusiQualityWaterStableMixingStationExceedDto busiQualityWaterStableMixingStationExceedDto = busiQualityWaterStableMixingStationExceedService.getBusiQualityWaterStableMixingStationExceedDtoById(id);
        return new ResponseEntity<>(busiQualityWaterStableMixingStationExceedDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityWaterStableMixingStationExceed 
    * @Description:修改BusiQualityWaterStableMixingStationExceed信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityWaterStableMixingStationExceedDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityWaterStableMixingStationExceed(@PathVariable("id") Long id, @RequestBody BusiQualityWaterStableMixingStationExceedDto busiQualityWaterStableMixingStationExceedDto) throws Exception {
        busiQualityWaterStableMixingStationExceedService.updateBusiQualityWaterStableMixingStationExceed(id, busiQualityWaterStableMixingStationExceedDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityWaterStableMixingStationExceedById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityWaterStableMixingStationExceedById(@PathVariable("id") Long id) throws Exception {
        busiQualityWaterStableMixingStationExceedService.deleteBusiQualityWaterStableMixingStationExceed(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
