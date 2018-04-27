package com.huatek.busi.api.quality;
import java.io.IOException;
import java.util.List;

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
import com.huatek.busi.dto.quality.BusiQualityRectificationDetailDto;
import com.huatek.busi.service.quality.BusiQualityRectificationDetailService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYRECTIFICATIONDETAIL_API)
public class BusiQualityRectificationDetailAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityRectificationDetailAction.class);

    @Autowired
    private BusiQualityRectificationDetailService busiQualityRectificationDetailService;

    
    /** 
    * @Title: getAllBusiQualityRectificationDetail 
    * @Description:  翻页查询BusiQualityRectificationDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityRectificationDetailDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityRectificationDetailDto>> getAllBusiQualityRectificationDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all busiQualityRectificationDetail of param " + queryPage.getQueryInfo());
        DataPage<BusiQualityRectificationDetailDto> busiQualityRectificationDetailPages = busiQualityRectificationDetailService.getAllBusiQualityRectificationDetailPage(queryPage);
        log.debug("get busiQualityRectificationDetail size @" + busiQualityRectificationDetailPages.getContent().size());
        return new ResponseEntity<>(busiQualityRectificationDetailPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityRectificationDetailDto 
    * @Description: 添加BusiQualityRectificationDetail 
    * @param    taskId 流程节点id
    * @param    rectificationId 整改单id
    * @param    busiQualityRectificationDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add/{taskId}/{rectificationId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityRectificationDetailDto(@PathVariable("taskId") String taskId,
    		@PathVariable("rectificationId") String rectificationId,@RequestBody BusiQualityRectificationDetailDto busiQualityRectificationDetailDto) throws Exception {
//    	busiQualityRectificationDetailDto.setId(null);
    	busiQualityRectificationDetailDto.setTaskId(taskId);
    	busiQualityRectificationDetailDto.setRectificationId(rectificationId);
    	busiQualityRectificationDetailService.saveBusiQualityRectificationDetailDto(busiQualityRectificationDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("审批成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityRectificationDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityRectificationDetailDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityRectificationDetailDto> getBusiQualityRectificationDetailDto(@PathVariable("id") Long id) {
    	BusiQualityRectificationDetailDto busiQualityRectificationDetailDto = busiQualityRectificationDetailService.getBusiQualityRectificationDetailDtoById(id);
        return new ResponseEntity<>(busiQualityRectificationDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityRectificationDetail 
    * @Description:修改BusiQualityRectificationDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityRectificationDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityRectificationDetail(@PathVariable("id") Long id, @RequestBody BusiQualityRectificationDetailDto busiQualityRectificationDetailDto) throws Exception {
        busiQualityRectificationDetailService.updateBusiQualityRectificationDetail(id, busiQualityRectificationDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityRectificationDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityRectificationDetailById(@PathVariable("id") Long id) throws Exception {
        busiQualityRectificationDetailService.deleteBusiQualityRectificationDetail(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: getBusiQualityRectificationDetailDto 
     * @Description: 获取需要修改 MdmLineBaseInfo信息
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    rid 整改单编号id
     * @return   ResponseEntity<BusiQualityRectificationDetailDto>    
     * @throws 
     */ 
     @RequestMapping(value = "/findDetailRecordByRectId/{rid}", method = RequestMethod.GET)
     @ResponseBody
     public ResponseEntity<List<BusiQualityRectificationDetailDto>> findDetailRecordByRectId(@PathVariable("rid") Long rid) {
     	List<BusiQualityRectificationDetailDto> busiQualityRectificationDetailDtos = busiQualityRectificationDetailService.getBusiQualityDetailRecordByRectId(rid);
     	return new ResponseEntity<>(busiQualityRectificationDetailDtos, HttpStatus.OK);
     }
}
