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
import com.huatek.busi.dto.quality.BusiQualityAsphaltMixingPlantInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityUniversalPressMachineParentDto;
import com.huatek.busi.service.quality.BusiQualityAsphaltMixingPlantInspectionService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYASPHALTMIXINGPLANTINSPECTION_API)
public class BusiQualityAsphaltMixingPlantInspectionAction {

    private static final Logger log = LoggerFactory.getLogger(BusiQualityAsphaltMixingPlantInspectionAction.class);

    @Autowired
    private BusiQualityAsphaltMixingPlantInspectionService busiQualityAsphaltMixingPlantInspectionService;
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiQualityAsphaltMixingPlantInspection 
    * @Description:  翻页查询BusiQualityAsphaltMixingPlantInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityAsphaltMixingPlantInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityAsphaltMixingPlantInspectionDto>> getAllBusiQualityAsphaltMixingPlantInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityAsphaltMixingPlantInspectionDto> busiQualityAsphaltMixingPlantInspectionPages = busiQualityAsphaltMixingPlantInspectionService.getAllBusiQualityAsphaltMixingPlantInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualityAsphaltMixingPlantInspectionPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityAsphaltMixingPlantInspectionDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityAsphaltMixingPlantInspectionDto> busiQualityAsphaltMixingPlantInspectionDtos = busiQualityAsphaltMixingPlantInspectionService.getBusiQualityAsphaltMixingPlantInspectionDtoByIds(ids);
        return new ResponseEntity<>(busiQualityAsphaltMixingPlantInspectionDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityAsphaltMixingPlantInspectionDto 
    * @Description: 添加BusiQualityAsphaltMixingPlantInspection 
    * @param    busiQualityAsphaltMixingPlantInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityAsphaltMixingPlantInspectionDto(@RequestBody BusiQualityAsphaltMixingPlantInspectionDto busiQualityAsphaltMixingPlantInspectionDto) throws Exception {
        busiQualityAsphaltMixingPlantInspectionService.saveBusiQualityAsphaltMixingPlantInspectionDto(busiQualityAsphaltMixingPlantInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityAsphaltMixingPlantInspection创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityAsphaltMixingPlantInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityAsphaltMixingPlantInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityAsphaltMixingPlantInspectionDto> getBusiQualityAsphaltMixingPlantInspectionDto(@PathVariable("id") Long id) {
    	BusiQualityAsphaltMixingPlantInspectionDto busiQualityAsphaltMixingPlantInspectionDto = busiQualityAsphaltMixingPlantInspectionService.getBusiQualityAsphaltMixingPlantInspectionDtoById(id);
        return new ResponseEntity<>(busiQualityAsphaltMixingPlantInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityAsphaltMixingPlantInspection 
    * @Description:修改BusiQualityAsphaltMixingPlantInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityAsphaltMixingPlantInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityAsphaltMixingPlantInspection(@PathVariable("id") Long id, @RequestBody BusiQualityAsphaltMixingPlantInspectionDto busiQualityAsphaltMixingPlantInspectionDto) throws Exception {
        busiQualityAsphaltMixingPlantInspectionService.updateBusiQualityAsphaltMixingPlantInspection(id, busiQualityAsphaltMixingPlantInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityAsphaltMixingPlantInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityAsphaltMixingPlantInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualityAsphaltMixingPlantInspectionService.deleteBusiQualityAsphaltMixingPlantInspection(id);
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
     public ResponseEntity<ResponseMessage> saveBusiQualityAsphaltMixingPlantRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
    	 busiQualityAsphaltMixingPlantInspectionService.generateBusiQualityRectificate(rectification, ids);
   	     oprationService.logOperation("新增沥青拌合站检测整改单["+rectification.getRectificationCode()+"]");
         return new ResponseEntity<>(ResponseMessage.success("创建整改单成功"), HttpStatus.OK);
     }
     
     /**
      * @Title: saveBusiQualityAsphaltMixingPlantQuickProcess 
      * @Description:新增快捷处理信息  
      * @param rectification
      * @return
      * @throws Exception
      */
      @RequestMapping(value = "/quickProcessAdd/{ids}/{orgId}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<ResponseMessage> saveBusiQualityAsphaltMixingPlantQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
   	   dto.setOrgId(orgId.toString());
   	   busiQualityAsphaltMixingPlantInspectionService.generateBusiQualityQuickProcess(dto, ids);
       oprationService.logOperation("新增沥青拌合站检测快捷处理["+dto.getId()+"]");
       return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
      }
      
      /**
       * @Title: getRawMaterialByReCode 
       * @Description:通过整改编号获取对应的砂浆数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/getAsphaltMixingPlantByReCode/{inspectionCode}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<List<BusiQualityAsphaltMixingPlantInspectionDto>> getPrestressedTensionMainByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
     	  	List<BusiQualityAsphaltMixingPlantInspectionDto> list = busiQualityAsphaltMixingPlantInspectionService.getBusiQualityAsphaltMixingPlantByReCode(inspectionCode);
           return new ResponseEntity<List<BusiQualityAsphaltMixingPlantInspectionDto>>(list, HttpStatus.OK);
       }
}
