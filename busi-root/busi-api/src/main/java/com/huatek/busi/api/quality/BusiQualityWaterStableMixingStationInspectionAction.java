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
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualityWaterStableMixingStationInspectionDto;
import com.huatek.busi.service.quality.BusiQualityWaterStableMixingStationInspectionService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYWATERSTABLEMIXINGSTATIONINSPECTION_API)
public class BusiQualityWaterStableMixingStationInspectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityWaterStableMixingStationInspectionAction.class);

    @Autowired
    private BusiQualityWaterStableMixingStationInspectionService busiQualityWaterStableMixingStationInspectionService;
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiQualityWaterStableMixingStationInspection 
    * @Description:  翻页查询BusiQualityWaterStableMixingStationInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityWaterStableMixingStationInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityWaterStableMixingStationInspectionDto>> getAllBusiQualityWaterStableMixingStationInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityWaterStableMixingStationInspectionDto> busiQualityWaterStableMixingStationInspectionPages = busiQualityWaterStableMixingStationInspectionService.getAllBusiQualityWaterStableMixingStationInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualityWaterStableMixingStationInspectionPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityWaterStableMixingStationInspectionDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityWaterStableMixingStationInspectionDto> busiQualityWaterStableMixingStationInspectionDtos = busiQualityWaterStableMixingStationInspectionService.getBusiQualityWaterStableMixingStationInspectionDtoByIds(ids);
        return new ResponseEntity<>(busiQualityWaterStableMixingStationInspectionDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityWaterStableMixingStationInspectionDto 
    * @Description: 添加BusiQualityWaterStableMixingStationInspection 
    * @param    busiQualityWaterStableMixingStationInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityWaterStableMixingStationInspectionDto(@RequestBody BusiQualityWaterStableMixingStationInspectionDto busiQualityWaterStableMixingStationInspectionDto) throws Exception {
        busiQualityWaterStableMixingStationInspectionService.saveBusiQualityWaterStableMixingStationInspectionDto(busiQualityWaterStableMixingStationInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityWaterStableMixingStationInspection创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityWaterStableMixingStationInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityWaterStableMixingStationInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityWaterStableMixingStationInspectionDto> getBusiQualityWaterStableMixingStationInspectionDto(@PathVariable("id") Long id) {
    	BusiQualityWaterStableMixingStationInspectionDto busiQualityWaterStableMixingStationInspectionDto = busiQualityWaterStableMixingStationInspectionService.getBusiQualityWaterStableMixingStationInspectionDtoById(id);
        return new ResponseEntity<>(busiQualityWaterStableMixingStationInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityWaterStableMixingStationInspection 
    * @Description:修改BusiQualityWaterStableMixingStationInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityWaterStableMixingStationInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityWaterStableMixingStationInspection(@PathVariable("id") Long id, @RequestBody BusiQualityWaterStableMixingStationInspectionDto busiQualityWaterStableMixingStationInspectionDto) throws Exception {
        busiQualityWaterStableMixingStationInspectionService.updateBusiQualityWaterStableMixingStationInspection(id, busiQualityWaterStableMixingStationInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityWaterStableMixingStationInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityWaterStableMixingStationInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualityWaterStableMixingStationInspectionService.deleteBusiQualityWaterStableMixingStationInspection(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * @Title: saveBusiQualityCementMixingStationRectification 
     * @Description:新增整改单信息  
     * @param ids
     * @param rectification
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "/rectificationAdd/{ids}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> saveBusiQualityWaterStableMixingStationRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
    	 busiQualityWaterStableMixingStationInspectionService.generateBusiQualityRectificate(rectification, ids);
   	     oprationService.logOperation("新增水稳拌合站检测整改单["+rectification.getRectificationCode()+"]");
         return new ResponseEntity<>(ResponseMessage.success("创建整改单成功"), HttpStatus.OK);
     }
     
     /**
      * @Title: saveBusiQualityRawMaterialQuickProcess 
      * @Description:新增快捷处理信息  
      * @param rectification
      * @return
      * @throws Exception
      */
      @RequestMapping(value = "/quickProcessAdd/{ids}/{orgId}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<ResponseMessage> saveBusiQualityWaterStableMixingStationQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
   	   dto.setOrgId(orgId.toString());
   	   busiQualityWaterStableMixingStationInspectionService.generateBusiQualityQuickProcess(dto, ids);
       oprationService.logOperation("新增水稳拌合站检测快捷处理["+dto.getId()+"]");
          return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
      }
      
      /**
       * @Title: getRawMaterialByReCode 
       * @Description:通过整改编号获取对应的水稳检测数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/getWaterStableMixingStationByReCode/{inspectionCode}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<List<BusiQualityWaterStableMixingStationInspectionDto>> getWaterStableMixingStationByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
     	  	List<BusiQualityWaterStableMixingStationInspectionDto> list = busiQualityWaterStableMixingStationInspectionService.getBusiQualityWaterStableMixingStationByReCode(inspectionCode);
           return new ResponseEntity<List<BusiQualityWaterStableMixingStationInspectionDto>>(list, HttpStatus.OK);
       }
}
