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
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationInspectionService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYCEMENTMIXINGSTATIONINSPECTION_API)
public class BusiQualityCementMixingStationInspectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityCementMixingStationInspectionAction.class);

    @Autowired
    private BusiQualityCementMixingStationInspectionService busiQualityCementMixingStationInspectionService;
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiQualityCementMixingStationInspection 
    * @Description:  翻页查询BusiQualityCementMixingStationInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityCementMixingStationInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityCementMixingStationInspectionDto>> getAllBusiQualityCementMixingStationInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityCementMixingStationInspectionDto> busiQualityCementMixingStationInspectionPages = busiQualityCementMixingStationInspectionService.getAllBusiQualityCementMixingStationInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualityCementMixingStationInspectionPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityCementMixingStationInspectionDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityCementMixingStationInspectionDto> busiQualityCementMixingStationInspectionDtos = busiQualityCementMixingStationInspectionService.getBusiQualityCementMixingStationInspectionDtoByIds(ids);
        return new ResponseEntity<>(busiQualityCementMixingStationInspectionDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityCementMixingStationInspectionDto 
    * @Description: 添加BusiQualityCementMixingStationInspection 
    * @param    busiQualityCementMixingStationInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityCementMixingStationInspectionDto(@RequestBody BusiQualityCementMixingStationInspectionDto busiQualityCementMixingStationInspectionDto) throws Exception {
        busiQualityCementMixingStationInspectionService.saveBusiQualityCementMixingStationInspectionDto(busiQualityCementMixingStationInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityCementMixingStationInspection创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityCementMixingStationInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityCementMixingStationInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityCementMixingStationInspectionDto> getBusiQualityCementMixingStationInspectionDto(@PathVariable("id") Long id) {
    	BusiQualityCementMixingStationInspectionDto busiQualityCementMixingStationInspectionDto = busiQualityCementMixingStationInspectionService.getBusiQualityCementMixingStationInspectionDtoById(id);
        return new ResponseEntity<>(busiQualityCementMixingStationInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityCementMixingStationInspection 
    * @Description:修改BusiQualityCementMixingStationInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityCementMixingStationInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityCementMixingStationInspection(@PathVariable("id") Long id, @RequestBody BusiQualityCementMixingStationInspectionDto busiQualityCementMixingStationInspectionDto) throws Exception {
        busiQualityCementMixingStationInspectionService.updateBusiQualityCementMixingStationInspection(id, busiQualityCementMixingStationInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityCementMixingStationInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityCementMixingStationInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualityCementMixingStationInspectionService.deleteBusiQualityCementMixingStationInspection(id);
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
     public ResponseEntity<ResponseMessage> saveBusiQualityCementMixingStationRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
      busiQualityCementMixingStationInspectionService.generateBusiQualityRectificate(rectification, ids);
   	  oprationService.logOperation("新增水泥拌合站检测整改单["+rectification.getRectificationCode()+"]");
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
      public ResponseEntity<ResponseMessage> saveBusiQualityCementMixingStationQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
   	   dto.setOrgId(orgId.toString());
   	   busiQualityCementMixingStationInspectionService.generateBusiQualityQuickProcess(dto, ids);
       oprationService.logOperation("新增水泥拌合站检测快捷处理["+dto.getId()+"]");
          return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
      }
      
      /**
       * @Title: getRawMaterialByReCode 
       * @Description:通过整改编号获取对应的水泥拌合站检测数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/getCementMixingStationByReCode/{inspectionCode}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<List<BusiQualityCementMixingStationInspectionDto>> getCementMixingStationByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
     	  	List<BusiQualityCementMixingStationInspectionDto> list = busiQualityCementMixingStationInspectionService.getBusiQualityCementMixingStationByReCode(inspectionCode);
           return new ResponseEntity<List<BusiQualityCementMixingStationInspectionDto>>(list, HttpStatus.OK);
       }
}
