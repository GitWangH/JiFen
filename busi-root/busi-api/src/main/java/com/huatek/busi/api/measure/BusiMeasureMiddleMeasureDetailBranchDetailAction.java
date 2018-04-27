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
import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDetailBranchDetailDto;
import com.huatek.busi.service.measure.BusiMeasureMiddleMeasureDetailBranchDetailService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiMeasureMiddleMeasureDetailBranchDetailAction
 * @Description: 中间计量分部分项明细后台控制器Action
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
@RequestMapping(value = BusiUrlConstants.BUSI_MEASURE_MIDDLE_MEASURE_DETAIL_BRANCH_DETAIL_API)
public class BusiMeasureMiddleMeasureDetailBranchDetailAction {

    @Autowired
    private BusiMeasureMiddleMeasureDetailBranchDetailService busiMeasureMiddleMeasureDetailBranchDetailService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiMeasureMiddleMeasureDetailBranchDetail 
    * @Description:  翻页查询BusiMeasureMiddleMeasureDetailBranchDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiMeasureMiddleMeasureDetailBranchDetailDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiMeasureMiddleMeasureDetailBranchDetailDto>> getAllBusiMeasureMiddleMeasureDetailBranchDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<BusiMeasureMiddleMeasureDetailBranchDetailDto> busiMeasureMiddleMeasureDetailBranchDetailPages = busiMeasureMiddleMeasureDetailBranchDetailService.getAllBusiMeasureMiddleMeasureDetailBranchDetailPage(queryPage);
        return new ResponseEntity<>(busiMeasureMiddleMeasureDetailBranchDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiMeasureMiddleMeasureDetailBranchDetailDto 
    * @Description: 添加BusiMeasureMiddleMeasureDetailBranchDetail 
    * @param    busiMeasureMiddleMeasureDetailBranchDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiMeasureMiddleMeasureDetailBranchDetailDto(@RequestBody BusiMeasureMiddleMeasureDetailBranchDetailDto busiMeasureMiddleMeasureDetailBranchDetailDto) throws Exception {
        busiMeasureMiddleMeasureDetailBranchDetailService.saveBusiMeasureMiddleMeasureDetailBranchDetailDto(busiMeasureMiddleMeasureDetailBranchDetailDto);
        operationService.logOperation("创建【BusiMeasureMiddleMeasureDetailBranchDetail】");
        return new ResponseEntity<>(ResponseMessage.success("BusiMeasureMiddleMeasureDetailBranchDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiMeasureMiddleMeasureDetailBranchDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiMeasureMiddleMeasureDetailBranchDetailDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiMeasureMiddleMeasureDetailBranchDetailDto> getBusiMeasureMiddleMeasureDetailBranchDetailDto(@PathVariable("id") Long id) {
    	BusiMeasureMiddleMeasureDetailBranchDetailDto busiMeasureMiddleMeasureDetailBranchDetailDto = busiMeasureMiddleMeasureDetailBranchDetailService.getBusiMeasureMiddleMeasureDetailBranchDetailDtoById(id);
        return new ResponseEntity<>(busiMeasureMiddleMeasureDetailBranchDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiMeasureMiddleMeasureDetailBranchDetail 
    * @Description:修改BusiMeasureMiddleMeasureDetailBranchDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiMeasureMiddleMeasureDetailBranchDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiMeasureMiddleMeasureDetailBranchDetail(@PathVariable("id") Long id, @RequestBody BusiMeasureMiddleMeasureDetailBranchDetailDto busiMeasureMiddleMeasureDetailBranchDetailDto) throws Exception {
        busiMeasureMiddleMeasureDetailBranchDetailService.updateBusiMeasureMiddleMeasureDetailBranchDetail(id, busiMeasureMiddleMeasureDetailBranchDetailDto);
        operationService.logOperation("修改【BusiMeasureMiddleMeasureDetailBranchDetail("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiMeasureMiddleMeasureDetailBranchDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiMeasureMiddleMeasureDetailBranchDetailById(@PathVariable("id") Long id) throws Exception {
        busiMeasureMiddleMeasureDetailBranchDetailService.deleteBusiMeasureMiddleMeasureDetailBranchDetail(id);
        operationService.logOperation("删除【BusiMeasureMiddleMeasureDetailBranchDetail("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
}
