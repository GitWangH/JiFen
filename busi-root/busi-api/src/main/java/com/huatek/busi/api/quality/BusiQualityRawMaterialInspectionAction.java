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
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.service.quality.BusiQualityRawMaterialInspectionService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_QUALITY_RAW_MATERIAL_INSPECTION_API)
public class BusiQualityRawMaterialInspectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityRawMaterialInspectionAction.class);

    @Autowired
    private BusiQualityRawMaterialInspectionService busiQualityRawMaterialInspectionService;
    @Autowired
    private OperationService oprationService;//日志记录服务类
    
    /** 
    * @Title: getAllBusiQualityRawMaterialInspection 
    * @Description:  翻页查询BusiQualityRawMaterialInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityRawMaterialInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityRawMaterialInspectionDto>> getAllBusiQualityRawMaterialInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityRawMaterialInspectionDto> busiQualityRawMaterialInspectionPages = busiQualityRawMaterialInspectionService.getAllBusiQualityRawMaterialInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualityRawMaterialInspectionPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityRawMaterialInspectionDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityRawMaterialInspectionDto> busiQualityRawMaterialInspectionDtos = busiQualityRawMaterialInspectionService.getBusiQualityRawMaterialInspectionDtoByIds(ids);
        return new ResponseEntity<>(busiQualityRawMaterialInspectionDtos, HttpStatus.OK);
    }
    
    /**
     * 原材料查询整改单信息
     * @param id
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/query/{id}")
    @ResponseBody
    public ResponseEntity<BusiQualityRawMaterialInspectionDto> getAllBusiQualityRawMaterialInspection(@PathVariable("id") Long id) throws JsonParseException, JsonMappingException, IOException { 	
    	BusiQualityRawMaterialInspectionDto busiQualityRawMaterialInspectionDto = busiQualityRawMaterialInspectionService.getBusiQualityRawMaterialInspectionDtoById(id);
    	return new ResponseEntity<>(busiQualityRawMaterialInspectionDto, HttpStatus.OK);
    	
    }
    
     /** 
    * @Title: createBusiQualityRawMaterialInspectionDto 
    * @Description: 添加BusiQualityRawMaterialInspection 
    * @param    busiQualityRawMaterialInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityRawMaterialInspectionDto(@RequestBody BusiQualityRawMaterialInspectionDto busiQualityRawMaterialInspectionDto) throws Exception {
        busiQualityRawMaterialInspectionService.saveBusiQualityRawMaterialInspectionDto(busiQualityRawMaterialInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityRawMaterialInspection创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityRawMaterialInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityRawMaterialInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityRawMaterialInspectionDto> getBusiQualityRawMaterialInspectionDto(@PathVariable("id") Long id) {
    	BusiQualityRawMaterialInspectionDto busiQualityRawMaterialInspectionDto = busiQualityRawMaterialInspectionService.getBusiQualityRawMaterialInspectionDtoById(id);
        return new ResponseEntity<>(busiQualityRawMaterialInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityRawMaterialInspection 
    * @Description:修改BusiQualityRawMaterialInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityRawMaterialInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityRawMaterialInspection(@PathVariable("id") Long id, @RequestBody BusiQualityRawMaterialInspectionDto busiQualityRawMaterialInspectionDto) throws Exception {
        busiQualityRawMaterialInspectionService.updateBusiQualityRawMaterialInspection(id, busiQualityRawMaterialInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityRawMaterialInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityRawMaterialInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualityRawMaterialInspectionService.deleteBusiQualityRawMaterialInspection(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: editBusiQualityRawMaterialInspection 
     * @Description:修改BusiQualityRawMaterialInspection信息 
     * @createDate: 2016年4月25日 下午1:49:25
     * @param    id
     * @param    busiQualityRawMaterialInspectionDto
     * @return   ResponseEntity<ResponseMessage>    
     * @throws 
     */ 
     @RequestMapping(value = "/hasDisposeDeatil/{id}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<List<BusiQualityRawMaterialInspectionDto>> hasDisposeDeatil(@PathVariable("id") Long id, @RequestBody BusiQualityRawMaterialInspectionDto busiQualityRawMaterialInspectionDto) throws Exception {
    	 BusiQualityRawMaterialInspectionDto dto = busiQualityRawMaterialInspectionService.getBusiQualityRawMaterialInspectionDtoById(id);
    	 List<BusiQualityRawMaterialInspectionDto> BusiQualityRawMaterialInspectionDtos= busiQualityRawMaterialInspectionService.getAllRawMaterialInspectionByInspectionTypeAndInspectionId(dto.getInspectionType(), dto.getInspectionId());
    	 return new ResponseEntity<>(BusiQualityRawMaterialInspectionDtos, HttpStatus.OK);
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
      public ResponseEntity<ResponseMessage> saveBusiQualityRawMaterialRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
    	  busiQualityRawMaterialInspectionService.generateBusiQualityRectificate(rectification, ids);
    	  oprationService.logOperation("新增原材料检测整改单["+rectification.getRectificationCode()+"]");
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
       public ResponseEntity<ResponseMessage> saveBusiQualityRawMaterialQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
    	   dto.setOrgId(orgId.toString());
    	   busiQualityRawMaterialInspectionService.generateBusiQualityQuickProcess(dto, ids);
     	  oprationService.logOperation("新增原材料检测快捷处理["+dto.getId()+"]");
           return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
       }
       
       /**
        * @Title: getRawMaterialByReCode 
        * @Description:通过整改编号获取对应的原材料数据
        * @param inspectionCode 整改编号
        * @return
        * @throws Exception
        */
        @RequestMapping(value = "/getRawMaterialByReCode/{inspectionCode}", method = RequestMethod.GET)
        @ResponseBody
        public ResponseEntity<List<BusiQualityRawMaterialInspectionDto>> getRawMaterialByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
      	  	List<BusiQualityRawMaterialInspectionDto> list = busiQualityRawMaterialInspectionService.getRawMaterialByReCode(inspectionCode);
            return new ResponseEntity<List<BusiQualityRawMaterialInspectionDto>>(list, HttpStatus.OK);
        }
	
}
