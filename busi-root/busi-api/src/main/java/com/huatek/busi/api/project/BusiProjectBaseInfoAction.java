package com.huatek.busi.api.project;
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
import com.huatek.busi.dto.project.BusiProjectBaseInfoDto;
import com.huatek.busi.service.base.BusiBaseInitService;
import com.huatek.busi.service.project.BusiProjectBaseInfoService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
/**
 * 项目基本信息
 * @author eli_cui
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_PROJECT_BASE_INFO_API)
public class BusiProjectBaseInfoAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiProjectBaseInfoAction.class);

    @Autowired
    private BusiProjectBaseInfoService busiProjectBaseInfoService;
    
    @Autowired
    private BusiBaseInitService s;

    
    /** 
    * @Title: getAllBusiProjectBaseInfo 
    * @Description:  翻页查询BusiProjectBaseInfo信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiProjectBaseInfoDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiProjectBaseInfoDto>> getAllBusiProjectBaseInfo(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiProjectBaseInfoDto> busiProjectBaseInfoPages = busiProjectBaseInfoService.getAllBusiProjectBaseInfoPage(queryPage);
        return new ResponseEntity<>(busiProjectBaseInfoPages, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiProjectBaseInfoDto 
    * @Description: 添加BusiProjectBaseInfo 
    * @param    busiProjectBaseInfoDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiProjectBaseInfoDto(@RequestBody BusiProjectBaseInfoDto busiProjectBaseInfoDto) throws Exception {
        busiProjectBaseInfoService.saveBusiProjectBaseInfoDto(busiProjectBaseInfoDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiProjectBaseInfo创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiProjectBaseInfoDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiProjectBaseInfoDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiProjectBaseInfoDto> getBusiProjectBaseInfoDto(@PathVariable("id") Long id) {
    	BusiProjectBaseInfoDto busiProjectBaseInfoDto = busiProjectBaseInfoService.getBusiProjectBaseInfoDtoById(id);
        return new ResponseEntity<>(busiProjectBaseInfoDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiProjectBaseInfo 
    * @Description:修改BusiProjectBaseInfo信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiProjectBaseInfoDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiProjectBaseInfo(@PathVariable("id") Long id, @RequestBody BusiProjectBaseInfoDto busiProjectBaseInfoDto) throws Exception {
        busiProjectBaseInfoService.updateBusiProjectBaseInfo(id, busiProjectBaseInfoDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiProjectBaseInfoById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiProjectBaseInfoById(@PathVariable("id") Long id) throws Exception {
        busiProjectBaseInfoService.deleteBusiProjectBaseInfo(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
//    @RequestMapping(value = "/test", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<ResponseMessage> aaat() throws Exception {
//    	s.initBaseData(99L, 99L);
//        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
//    }
   
}
