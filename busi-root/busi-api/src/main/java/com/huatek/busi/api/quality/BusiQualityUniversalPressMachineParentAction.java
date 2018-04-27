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
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerSpreaderParentDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.service.quality.BusiQualityUniversalPressMachineParentService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYUNIVERSALPRESSMACHINEPARENT_API)
public class BusiQualityUniversalPressMachineParentAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityUniversalPressMachineParentAction.class);

    @Autowired
    private BusiQualityUniversalPressMachineParentService busiQualityUniversalPressMachineParentService;
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiQualityUniversalPressMachineParent 
    * @Description:  翻页查询BusiQualityUniversalPressMachineParent信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityUniversalPressMachineParentDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityUniversalPressMachineParentDto>> getAllBusiQualityUniversalPressMachineParent(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityUniversalPressMachineParentDto> busiQualityUniversalPressMachineParentPages = busiQualityUniversalPressMachineParentService.getAllBusiQualityUniversalPressMachineParentPage(queryPage);
        return new ResponseEntity<>(busiQualityUniversalPressMachineParentPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityUniversalPressMachineParentDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityUniversalPressMachineParentDto> busiQualityUniversalPressMachineParentDtos = busiQualityUniversalPressMachineParentService.getBusiQualityUniversalPressMachineParentDtoByIds(ids);
        return new ResponseEntity<>(busiQualityUniversalPressMachineParentDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityUniversalPressMachineParentDto 
    * @Description: 添加BusiQualityUniversalPressMachineParent 
    * @param    busiQualityUniversalPressMachineParentDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityUniversalPressMachineParentDto(@RequestBody BusiQualityUniversalPressMachineParentDto busiQualityUniversalPressMachineParentDto) throws Exception {
        busiQualityUniversalPressMachineParentService.saveBusiQualityUniversalPressMachineParentDto(busiQualityUniversalPressMachineParentDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityUniversalPressMachineParent创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityUniversalPressMachineParentDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityUniversalPressMachineParentDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityUniversalPressMachineParentDto> getBusiQualityUniversalPressMachineParentDto(@PathVariable("id") Long id) {
    	BusiQualityUniversalPressMachineParentDto busiQualityUniversalPressMachineParentDto = busiQualityUniversalPressMachineParentService.getBusiQualityUniversalPressMachineParentDtoById(id);
        return new ResponseEntity<>(busiQualityUniversalPressMachineParentDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityUniversalPressMachineParent 
    * @Description:修改BusiQualityUniversalPressMachineParent信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityUniversalPressMachineParentDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityUniversalPressMachineParent(@PathVariable("id") Long id, @RequestBody BusiQualityUniversalPressMachineParentDto busiQualityUniversalPressMachineParentDto) throws Exception {
        busiQualityUniversalPressMachineParentService.updateBusiQualityUniversalPressMachineParent(id, busiQualityUniversalPressMachineParentDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityUniversalPressMachineParentById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityUniversalPressMachineParentById(@PathVariable("id") Long id) throws Exception {
        busiQualityUniversalPressMachineParentService.deleteBusiQualityUniversalPressMachineParent(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * @Title: saveBusiQualityAsphaltMixingPlantRectification 
     * @Description:新增整改单信息  
     * @param ids
     * @param rectification
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "/rectificationAdd/{ids}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> saveBusiQualityUniversalPressMachineRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
    	 busiQualityUniversalPressMachineParentService.generateBusiQualityRectificate(rectification, ids);
   	     oprationService.logOperation("新增试验检测整改单["+rectification.getRectificationCode()+"]");
         return new ResponseEntity<>(ResponseMessage.success("创建整改单成功"), HttpStatus.OK);
     }
     
     /**
      * @Title: saveBusiQualityUniversalPressMachineQuickProcess 
      * @Description:新增快捷处理信息  
      * @param rectification
      * @return
      * @throws Exception
      */
      @RequestMapping(value = "/quickProcessAdd/{ids}/{orgId}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<ResponseMessage> saveBusiQualityUniversalPressMachineQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
   	   dto.setOrgId(orgId.toString());
   	   busiQualityUniversalPressMachineParentService.generateBusiQualityQuickProcess(dto, ids);
       oprationService.logOperation("新增试验检测快捷处理["+dto.getId()+"]");
       return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
      }
      
      /**
       * @Title: getRawMaterialByReCode 
       * @Description:通过整改编号获取对应的试验检测数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/getUniversalPressMachineByReCode/{inspectionCode}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<List<BusiQualityUniversalPressMachineParentDto>> getPrestressedTensionMainByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
     	  	List<BusiQualityUniversalPressMachineParentDto> list = busiQualityUniversalPressMachineParentService.getBusiQualityUniversalPressMachineParentByReCode(inspectionCode);
           return new ResponseEntity<List<BusiQualityUniversalPressMachineParentDto>>(list, HttpStatus.OK);
       }
}
