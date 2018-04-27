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
import com.huatek.busi.dto.quality.BusiQualityPrestressedTensionMainDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.dto.quality.BusiQualitySpreaderRollerSpreaderParentDto;
import com.huatek.busi.service.quality.BusiQualitySpreaderRollerSpreaderParentService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYSPREADERROLLERSPREADERPARENT_API)
public class BusiQualitySpreaderRollerSpreaderParentAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualitySpreaderRollerSpreaderParentAction.class);

    @Autowired
    private BusiQualitySpreaderRollerSpreaderParentService busiQualitySpreaderRollerSpreaderParentService;
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiQualitySpreaderRollerSpreaderParent 
    * @Description:  翻页查询BusiQualitySpreaderRollerSpreaderParent信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualitySpreaderRollerSpreaderParentDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualitySpreaderRollerSpreaderParentDto>> getAllBusiQualitySpreaderRollerSpreaderParent(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualitySpreaderRollerSpreaderParentDto> busiQualitySpreaderRollerSpreaderParentPages = busiQualitySpreaderRollerSpreaderParentService.getAllBusiQualitySpreaderRollerSpreaderParentPage(queryPage);
        return new ResponseEntity<>(busiQualitySpreaderRollerSpreaderParentPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualitySpreaderRollerSpreaderParentDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualitySpreaderRollerSpreaderParentDto> busiQualitySpreaderRollerSpreaderParentDtos = busiQualitySpreaderRollerSpreaderParentService.getBusiQualitySpreaderRollerSpreaderParentDtoByIds(ids);
        return new ResponseEntity<>(busiQualitySpreaderRollerSpreaderParentDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualitySpreaderRollerSpreaderParentDto 
    * @Description: 添加BusiQualitySpreaderRollerSpreaderParent 
    * @param    busiQualitySpreaderRollerSpreaderParentDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualitySpreaderRollerSpreaderParentDto(@RequestBody BusiQualitySpreaderRollerSpreaderParentDto busiQualitySpreaderRollerSpreaderParentDto) throws Exception {
        busiQualitySpreaderRollerSpreaderParentService.saveBusiQualitySpreaderRollerSpreaderParentDto(busiQualitySpreaderRollerSpreaderParentDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualitySpreaderRollerSpreaderParent创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualitySpreaderRollerSpreaderParentDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualitySpreaderRollerSpreaderParentDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualitySpreaderRollerSpreaderParentDto> getBusiQualitySpreaderRollerSpreaderParentDto(@PathVariable("id") Long id) {
    	BusiQualitySpreaderRollerSpreaderParentDto busiQualitySpreaderRollerSpreaderParentDto = busiQualitySpreaderRollerSpreaderParentService.getBusiQualitySpreaderRollerSpreaderParentDtoById(id);
        return new ResponseEntity<>(busiQualitySpreaderRollerSpreaderParentDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualitySpreaderRollerSpreaderParent 
    * @Description:修改BusiQualitySpreaderRollerSpreaderParent信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualitySpreaderRollerSpreaderParentDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualitySpreaderRollerSpreaderParent(@PathVariable("id") Long id, @RequestBody BusiQualitySpreaderRollerSpreaderParentDto busiQualitySpreaderRollerSpreaderParentDto) throws Exception {
        busiQualitySpreaderRollerSpreaderParentService.updateBusiQualitySpreaderRollerSpreaderParent(id, busiQualitySpreaderRollerSpreaderParentDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualitySpreaderRollerSpreaderParentById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualitySpreaderRollerSpreaderParentById(@PathVariable("id") Long id) throws Exception {
        busiQualitySpreaderRollerSpreaderParentService.deleteBusiQualitySpreaderRollerSpreaderParent(id);
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
     public ResponseEntity<ResponseMessage> saveBusiQualitySpreaderRollerSpreaderRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
    	 busiQualitySpreaderRollerSpreaderParentService.generateBusiQualityRectificate(rectification, ids);
   	     oprationService.logOperation("新增铺摊压路检测整改单["+rectification.getRectificationCode()+"]");
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
      public ResponseEntity<ResponseMessage> saveBusiQualitySpreaderRollerSpreaderQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
   	   dto.setOrgId(orgId.toString());
   	   busiQualitySpreaderRollerSpreaderParentService.generateBusiQualityQuickProcess(dto, ids);
       oprationService.logOperation("新增铺摊压路检测快捷处理["+dto.getId()+"]");
       return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
      }
      
      /**
       * @Title: getRawMaterialByReCode 
       * @Description:通过整改编号获取对应的砂浆数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/getSpreaderRollerSpreaderByReCode/{inspectionCode}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<List<BusiQualitySpreaderRollerSpreaderParentDto>> getSpreaderRollerSpreaderByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
     	  	List<BusiQualitySpreaderRollerSpreaderParentDto> list = busiQualitySpreaderRollerSpreaderParentService.getBusiQualitySpreaderRollerSpreaderByReCode(inspectionCode);
           return new ResponseEntity<List<BusiQualitySpreaderRollerSpreaderParentDto>>(list, HttpStatus.OK);
       }
}


