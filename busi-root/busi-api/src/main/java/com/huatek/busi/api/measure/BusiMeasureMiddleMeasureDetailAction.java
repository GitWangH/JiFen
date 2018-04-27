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
import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDetailDto;
import com.huatek.busi.service.measure.BusiMeasureMiddleMeasureDetailService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiMeasureMiddleMeasureDetailAction
 * @Description: 中间计量明细后台控制器Action
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
@RequestMapping(value = BusiUrlConstants.BUSI_MEASURE_MIDDLE_MEASURE_DETAIL_API)
public class BusiMeasureMiddleMeasureDetailAction {

    @Autowired
    private BusiMeasureMiddleMeasureDetailService busiMeasureMiddleMeasureDetailService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiMeasureMiddleMeasureDetail 
    * @Description:  翻页查询BusiMeasureMiddleMeasureDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureMiddleMeasureDetailDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureMiddleMeasureDetailDto>> getAllBusiMeasureMiddleMeasureDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiMeasureMiddleMeasureDetailDto> busiMeasureMiddleMeasureDetailPages = busiMeasureMiddleMeasureDetailService.getAllBusiMeasureMiddleMeasureDetailPage(queryPage);
        return new ResponseEntity<>(busiMeasureMiddleMeasureDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureMiddleMeasureDetailDto 
    * @Description: 添加BusiMeasureMiddleMeasureDetail 
    * @param    busiMeasureMiddleMeasureDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureMiddleMeasureDetailDto(@RequestBody BusiMeasureMiddleMeasureDetailDto busiMeasureMiddleMeasureDetailDto) throws Exception {
        busiMeasureMiddleMeasureDetailService.saveBusiMeasureMiddleMeasureDetailDto(busiMeasureMiddleMeasureDetailDto);
        operationService.logOperation("创建【BusiMeasureMiddleMeasureDetail】");
        return new ResponseEntity<>(ResponseMessage.success("BusiMeasureMiddleMeasureDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureMiddleMeasureDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureMiddleMeasureDetailDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureMiddleMeasureDetailDto> getBusiMeasureMiddleMeasureDetailDto(@PathVariable("id") Long id) {
    	BusiMeasureMiddleMeasureDetailDto busiMeasureMiddleMeasureDetailDto = busiMeasureMiddleMeasureDetailService.getBusiMeasureMiddleMeasureDetailDtoById(id);
        return new ResponseEntity<>(busiMeasureMiddleMeasureDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureMiddleMeasureDetail 
    * @Description:修改BusiMeasureMiddleMeasureDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureMiddleMeasureDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureMiddleMeasureDetail(@PathVariable("id") Long id, @RequestBody BusiMeasureMiddleMeasureDetailDto busiMeasureMiddleMeasureDetailDto) throws Exception {
        busiMeasureMiddleMeasureDetailService.updateBusiMeasureMiddleMeasureDetail(id, busiMeasureMiddleMeasureDetailDto);
        operationService.logOperation("修改【BusiMeasureMiddleMeasureDetail("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureMiddleMeasureDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureMiddleMeasureDetailById(@PathVariable("id") Long id) throws Exception {
        busiMeasureMiddleMeasureDetailService.deleteBusiMeasureMiddleMeasureDetail(id);
        operationService.logOperation("删除【BusiMeasureMiddleMeasureDetail("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
