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
import com.huatek.busi.dto.quality.BusiQualityMortarDto;
import com.huatek.busi.dto.quality.BusiQualityPrestressedTensionMainDto;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.service.quality.BusiQualityPrestressedTensionMainService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYPRESTRESSEDTENSIONMAIN_API)
public class BusiQualityPrestressedTensionMainAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityPrestressedTensionMainAction.class);

    @Autowired
    private BusiQualityPrestressedTensionMainService busiQualityPrestressedTensionMainService;
    @Autowired
    private OperationService oprationService;//日志记录服务类


    
    /** 
    * @Title: getAllBusiQualityPrestressedTensionMain 
    * @Description:  翻页查询BusiQualityPrestressedTensionMain信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityPrestressedTensionMainDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityPrestressedTensionMainDto>> getAllBusiQualityPrestressedTensionMain(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityPrestressedTensionMainDto> busiQualityPrestressedTensionMainPages = busiQualityPrestressedTensionMainService.getAllBusiQualityPrestressedTensionMainPage(queryPage);
        return new ResponseEntity<>(busiQualityPrestressedTensionMainPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityPrestressedTensionMainDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityPrestressedTensionMainDto> prestressedTensionMainDtos = busiQualityPrestressedTensionMainService.getBusiQualityPrestressedTensionMainDtoByIds(ids);
        return new ResponseEntity<>(prestressedTensionMainDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityPrestressedTensionMainDto 
    * @Description: 添加BusiQualityPrestressedTensionMain 
    * @param    busiQualityPrestressedTensionMainDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityPrestressedTensionMainDto(@RequestBody BusiQualityPrestressedTensionMainDto busiQualityPrestressedTensionMainDto) throws Exception {
        busiQualityPrestressedTensionMainService.saveBusiQualityPrestressedTensionMainDto(busiQualityPrestressedTensionMainDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityPrestressedTensionMain创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityPrestressedTensionMainDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityPrestressedTensionMainDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityPrestressedTensionMainDto> getBusiQualityPrestressedTensionMainDto(@PathVariable("id") Long id) {
    	BusiQualityPrestressedTensionMainDto busiQualityPrestressedTensionMainDto = busiQualityPrestressedTensionMainService.getBusiQualityPrestressedTensionMainDtoById(id);
        return new ResponseEntity<>(busiQualityPrestressedTensionMainDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityPrestressedTensionMain 
    * @Description:修改BusiQualityPrestressedTensionMain信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityPrestressedTensionMainDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityPrestressedTensionMain(@PathVariable("id") Long id, @RequestBody BusiQualityPrestressedTensionMainDto busiQualityPrestressedTensionMainDto) throws Exception {
        busiQualityPrestressedTensionMainService.updateBusiQualityPrestressedTensionMain(id, busiQualityPrestressedTensionMainDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityPrestressedTensionMainById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityPrestressedTensionMainById(@PathVariable("id") Long id) throws Exception {
        busiQualityPrestressedTensionMainService.deleteBusiQualityPrestressedTensionMain(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * @Title: saveBusiQualityMortarRectification 
     * @Description:新增整改单信息  
     * @param ids
     * @param rectification
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "/rectificationAdd/{ids}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> saveBusiQualityPrestressedTensionMainRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
    	 busiQualityPrestressedTensionMainService.generateBusiQualityRectificate(rectification, ids);
   	     oprationService.logOperation("新增预应力张拉检测整改单["+rectification.getRectificationCode()+"]");
         return new ResponseEntity<>(ResponseMessage.success("创建整改单成功"), HttpStatus.OK);
     }
     
     /**
      * @Title: saveBusiQualityMortarQuickProcess 
      * @Description:新增快捷处理信息  
      * @param rectification
      * @return
      * @throws Exception
      */
      @RequestMapping(value = "/quickProcessAdd/{ids}/{orgId}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<ResponseMessage> saveBusiQualityPrestressedTensionMainQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
   	      dto.setOrgId(orgId.toString());
   	      busiQualityPrestressedTensionMainService.generateBusiQualityQuickProcess(dto, ids);
    	  oprationService.logOperation("新增预应力张拉检测快捷处理["+dto.getId()+"]");
          return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
      }
      
      /**
       * @Title: getRawMaterialByReCode 
       * @Description:通过整改编号获取对应的预应张拉力砂浆数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/getPrestressedTensionMainByReCode/{inspectionCode}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<List<BusiQualityPrestressedTensionMainDto>> getPrestressedTensionMainByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
     	  	List<BusiQualityPrestressedTensionMainDto> list = busiQualityPrestressedTensionMainService.getBusiQualityPrestressedTensionMainByReCode(inspectionCode);
           return new ResponseEntity<List<BusiQualityPrestressedTensionMainDto>>(list, HttpStatus.OK);
       }
}
