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
import com.huatek.busi.dto.quality.BusiQualityRoutingInspectionDto;
import com.huatek.busi.service.quality.BusiQualityRoutingInspectionService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYROUTINGINSPECTION_API)
public class BusiQualityRoutingInspectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityRoutingInspectionAction.class);

    @Autowired
    private BusiQualityRoutingInspectionService busiQualityRoutingInspectionService;
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiQualityRoutingInspection 
    * @Description:  翻页查询BusiQualityRoutingInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityRoutingInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityRoutingInspectionDto>> getAllBusiQualityRoutingInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
//    	if(!UserUtil.getUser().getFromApp()){
//    		queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
//    	}
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityRoutingInspectionDto> busiQualityRoutingInspectionPages = busiQualityRoutingInspectionService.getAllBusiQualityRoutingInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualityRoutingInspectionPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityRoutingInspectionDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityRoutingInspectionDto> busiQualityRoutingInspectionDtos = busiQualityRoutingInspectionService.getBusiQualityRoutingInspectionDtoByIds(ids);
        return new ResponseEntity<>(busiQualityRoutingInspectionDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityRoutingInspectionDto 
    * @Description: 添加BusiQualityRoutingInspection 
    * @param    busiQualityRoutingInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityRoutingInspectionDto(@RequestBody BusiQualityRoutingInspectionDto busiQualityRoutingInspectionDto) throws Exception {
        busiQualityRoutingInspectionService.saveBusiQualityRoutingInspectionDto(busiQualityRoutingInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityRoutingInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityRoutingInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityRoutingInspectionDto> getBusiQualityRoutingInspectionDto(@PathVariable("id") Long id) {
    	BusiQualityRoutingInspectionDto busiQualityRoutingInspectionDto = busiQualityRoutingInspectionService.getBusiQualityRoutingInspectionDtoById(id);
        return new ResponseEntity<>(busiQualityRoutingInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityRoutingInspection 
    * @Description:修改BusiQualityRoutingInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityRoutingInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityRoutingInspection(@PathVariable("id") Long id, @RequestBody BusiQualityRoutingInspectionDto busiQualityRoutingInspectionDto) throws Exception {
        busiQualityRoutingInspectionService.updateBusiQualityRoutingInspection(id, busiQualityRoutingInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityRoutingInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityRoutingInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualityRoutingInspectionService.deleteBusiQualityRoutingInspection(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * @Title: saveBusiQualityRawMaterialRectification 
     * @Description:新增整改单信息  
     * @param ids
     * @param rectification
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "/rectificationAdd/{ids}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> saveBusiQualityRoutingInspectionRectification(@PathVariable("ids") Long[] ids) throws Exception {
      busiQualityRoutingInspectionService.rectificateReport(ids);
         return new ResponseEntity<>(ResponseMessage.success("下发整改单成功"), HttpStatus.OK);
     }
     
     /**
      * @Title: getRawMaterialByReCode 
      * @Description:通过整改编号获取对应的质量巡检数据
      * @param inspectionCode 整改编号
      * @return
      * @throws Exception
      */
      @RequestMapping(value = "/getRoutingInspectionByReCode/{inspectionCode}", method = RequestMethod.GET)
      @ResponseBody
      public ResponseEntity<List<BusiQualityRoutingInspectionDto>> getRoutingInspectionByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
    	  	List<BusiQualityRoutingInspectionDto> list = busiQualityRoutingInspectionService.getRoutingInspectionByReCode(inspectionCode);
          return new ResponseEntity<List<BusiQualityRoutingInspectionDto>>(list, HttpStatus.OK);
      }
      
      /**
       * @Title: getRoutingByRectificationId 
       * @Description:通过整改单id获取对应的质量巡检数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
      @RequestMapping(value = "/getRoutingByRectificationId/{rid}", method = RequestMethod.GET)
      @ResponseBody
      public ResponseEntity<BusiQualityRoutingInspectionDto> getRoutingByRectificationId(@PathVariable("rid") Long rid) throws Exception {
    	  BusiQualityRoutingInspectionDto dto = busiQualityRoutingInspectionService.getRoutingByRectificationId(rid);
    	  return new ResponseEntity<BusiQualityRoutingInspectionDto>(dto, HttpStatus.OK);
      }
      
      
}
