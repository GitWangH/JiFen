package com.huatek.busi.api.measure;
import java.io.IOException;

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
import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDto;
import com.huatek.busi.service.measure.BusiMeasureMiddleMeasureService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiMeasureMiddleMeasureAction
 * @Description: 中间计量后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 11:05:35
 * @version: 1.0
 * @memuInfo [计量支付] - [中间计量]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-12-05 11:05:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_MEASURE_MIDDLE_MEASURE_API)
public class BusiMeasureMiddleMeasureAction {

    @Autowired
    private BusiMeasureMiddleMeasureService busiMeasureMiddleMeasureService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiMeasureMiddleMeasure 
    * @Description:  翻页查询BusiMeasureMiddleMeasure信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureMiddleMeasureDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureMiddleMeasureDto>> getAllBusiMeasureMiddleMeasure(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiMeasureMiddleMeasureDto> busiMeasureMiddleMeasurePages = busiMeasureMiddleMeasureService.getAllBusiMeasureMiddleMeasurePage(queryPage);
        return new ResponseEntity<>(busiMeasureMiddleMeasurePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureMiddleMeasureDto 
    * @Description: 添加BusiMeasureMiddleMeasure 
    * @param    busiMeasureMiddleMeasureDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureMiddleMeasureDto(@RequestBody BusiMeasureMiddleMeasureDto busiMeasureMiddleMeasureDto) throws Exception {
        busiMeasureMiddleMeasureService.saveBusiMeasureMiddleMeasureDto(busiMeasureMiddleMeasureDto);
        operationService.logOperation("创建【BusiMeasureMiddleMeasure】");
        return new ResponseEntity<>(ResponseMessage.success("BusiMeasureMiddleMeasure创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureMiddleMeasureDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureMiddleMeasureDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureMiddleMeasureDto> getBusiMeasureMiddleMeasureDto(@PathVariable("id") Long id) {
    	BusiMeasureMiddleMeasureDto busiMeasureMiddleMeasureDto = busiMeasureMiddleMeasureService.getBusiMeasureMiddleMeasureDtoById(id);
        return new ResponseEntity<>(busiMeasureMiddleMeasureDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureMiddleMeasure 
    * @Description:修改BusiMeasureMiddleMeasure信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureMiddleMeasureDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureMiddleMeasure(@PathVariable("id") Long id, @RequestBody BusiMeasureMiddleMeasureDto busiMeasureMiddleMeasureDto) throws Exception {
        busiMeasureMiddleMeasureService.updateBusiMeasureMiddleMeasure(id, busiMeasureMiddleMeasureDto);
        operationService.logOperation("修改【BusiMeasureMiddleMeasure("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureMiddleMeasureById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureMiddleMeasureById(@PathVariable("id") Long id) throws Exception {
        busiMeasureMiddleMeasureService.deleteBusiMeasureMiddleMeasure(id);
        operationService.logOperation("删除【BusiMeasureMiddleMeasure("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
