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
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.service.quality.BusiQualityMortarService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYMORTAR_API)
public class BusiQualityMortarAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityMortarAction.class);

    @Autowired
    private BusiQualityMortarService busiQualityMortarService;
    @Autowired
    private OperationService oprationService;//日志记录服务类

    
    /** 
    * @Title: getAllBusiQualityMortar 
    * @Description:  翻页查询BusiQualityMortar信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityMortarDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityMortarDto>> getAllBusiQualityMortar(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	DataPage<BusiQualityMortarDto> busiQualityMortarPages = busiQualityMortarService.getAllBusiQualityMortarPage(queryPage);
        return new ResponseEntity<>(busiQualityMortarPages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryList/{ids}")
    @ResponseBody
    public ResponseEntity<List<BusiQualityMortarDto>> queryList(@PathVariable("ids") Long[] ids) throws JsonParseException, JsonMappingException, IOException { 	
        List<BusiQualityMortarDto> busiQualityMortarDtos = busiQualityMortarService.getBusiQualityMortarDtoByIds(ids);
        return new ResponseEntity<>(busiQualityMortarDtos, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiQualityMortarDto 
    * @Description: 添加BusiQualityMortar 
    * @param    busiQualityMortarDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityMortarDto(@RequestBody BusiQualityMortarDto busiQualityMortarDto) throws Exception {
        busiQualityMortarService.saveBusiQualityMortarDto(busiQualityMortarDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualityMortar创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityMortarDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityMortarDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityMortarDto> getBusiQualityMortarDto(@PathVariable("id") Long id) {
    	BusiQualityMortarDto busiQualityMortarDto = busiQualityMortarService.getBusiQualityMortarDtoById(id);
        return new ResponseEntity<>(busiQualityMortarDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityMortar 
    * @Description:修改BusiQualityMortar信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityMortarDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityMortar(@PathVariable("id") Long id, @RequestBody BusiQualityMortarDto busiQualityMortarDto) throws Exception {
        busiQualityMortarService.updateBusiQualityMortar(id, busiQualityMortarDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityMortarById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityMortarById(@PathVariable("id") Long id) throws Exception {
        busiQualityMortarService.deleteBusiQualityMortar(id);
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
     public ResponseEntity<ResponseMessage> saveBusiQualityMortarRectification(@PathVariable("ids") Long[] ids, @RequestBody BusiQualityRectificationDto rectification) throws Exception {
      busiQualityMortarService.generateBusiQualityRectificate(rectification, ids);
   	  oprationService.logOperation("新增砂浆检测整改单["+rectification.getRectificationCode()+"]");
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
      public ResponseEntity<ResponseMessage> saveBusiQualityMortarQuickProcess(@PathVariable("ids") Long[] ids,@PathVariable("orgId") Long orgId,@RequestBody BusiQualityQuickProcessingDto dto) throws Exception {
   	   dto.setOrgId(orgId.toString());
      	  busiQualityMortarService.generateBusiQualityQuickProcess(dto, ids);
    	  oprationService.logOperation("新增砂浆检测快捷处理["+dto.getId()+"]");
          return new ResponseEntity<>(ResponseMessage.success("快捷处理成功"), HttpStatus.OK);
      }
      
      /**
       * @Title: getRawMaterialByReCode 
       * @Description:通过整改编号获取对应的砂浆数据
       * @param inspectionCode 整改编号
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/getMortarByReCode/{inspectionCode}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<List<BusiQualityMortarDto>> getMortarByReCode(@PathVariable("inspectionCode") String inspectionCode) throws Exception {
     	  	List<BusiQualityMortarDto> list = busiQualityMortarService.getBusiQualityMortarByReCode(inspectionCode);
           return new ResponseEntity<List<BusiQualityMortarDto>>(list, HttpStatus.OK);
       }
}
