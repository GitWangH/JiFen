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
import com.huatek.busi.dto.quality.BusiQualityUniversalMachineDto;
import com.huatek.busi.service.quality.BusiQualityUniversalMachineService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_QUALITY_UNIVERSAL_MACHINE_API)
public class BusiQualityUniversalMachineAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityUniversalMachineAction.class);

    @Autowired
    private BusiQualityUniversalMachineService busiQualityUniversalMachineService;

    
    /** 
    * @Title: getAllBusiQualityUniversalMachine 
    * @Description:  翻页查询BusiQualityUniversalMachine信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityUniversalMachineDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityUniversalMachineDto>> getAllBusiQualityUniversalMachine(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiQualityUniversalMachineDto> busiQualityUniversalMachinePages = busiQualityUniversalMachineService.getAllBusiQualityUniversalMachinePage(queryPage);
        return new ResponseEntity<>(busiQualityUniversalMachinePages, HttpStatus.OK);
       
    }
    
    
    /** 
    * @Title: getBusiQualityUniversalMachineDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityUniversalMachineDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityUniversalMachineDto> getBusiQualityUniversalMachineDto(@PathVariable("id") Long id) {
    	BusiQualityUniversalMachineDto busiQualityUniversalMachineDto = busiQualityUniversalMachineService.getBusiQualityUniversalMachineDtoById(id);
        return new ResponseEntity<>(busiQualityUniversalMachineDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityUniversalMachine 
    * @Description:修改BusiQualityUniversalMachine信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityUniversalMachineDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityUniversalMachine(@PathVariable("id") Long id, @RequestBody BusiQualityUniversalMachineDto busiQualityUniversalMachineDto) throws Exception {
        busiQualityUniversalMachineService.updateBusiQualityUniversalMachine(id, busiQualityUniversalMachineDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityUniversalMachineById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityUniversalMachineById(@PathVariable("id") Long id) throws Exception {
        busiQualityUniversalMachineService.deleteBusiQualityUniversalMachine(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
