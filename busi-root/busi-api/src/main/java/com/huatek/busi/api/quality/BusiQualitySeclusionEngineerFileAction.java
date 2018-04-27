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
import com.huatek.busi.dto.quality.BusiQualitySeclusionEngineerFileDto;
import com.huatek.busi.service.quality.BusiQualitySeclusionEngineerFileService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 隐蔽工程上传文件action
 * @author rocky_weie
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_QUALITY_SECLUSION_ENGINEER_API)
public class BusiQualitySeclusionEngineerFileAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualitySeclusionEngineerFileAction.class);

    @Autowired
    private BusiQualitySeclusionEngineerFileService busiQualitySeclusionEngineerFileService;

    
    /** 
    * @Title: getAllBusiQualitySeclusionEngineerFile 
    * @Description:  翻页查询BusiQualitySeclusionEngineerFile信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualitySeclusionEngineerFileDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualitySeclusionEngineerFileDto>> getAllBusiQualitySeclusionEngineerFile(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all busiQualitySeclusionEngineerFile of param " + queryPage.getQueryInfo());
//        queryPage.setSqlCondition(" tendersBranch.busiFwOrg.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        DataPage<BusiQualitySeclusionEngineerFileDto> busiQualitySeclusionEngineerFilePages = busiQualitySeclusionEngineerFileService.getAllBusiQualitySeclusionEngineerFilePage(queryPage);
        log.debug("get busiQualitySeclusionEngineerFile size @" + busiQualitySeclusionEngineerFilePages.getContent().size());
        return new ResponseEntity<>(busiQualitySeclusionEngineerFilePages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualitySeclusionEngineerFileDto 
    * @Description: 添加BusiQualitySeclusionEngineerFile 
    * @param    busiQualitySeclusionEngineerFileDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualitySeclusionEngineerFileDto(@RequestBody BusiQualitySeclusionEngineerFileDto busiQualitySeclusionEngineerFileDto) throws Exception {
        busiQualitySeclusionEngineerFileService.saveBusiQualitySeclusionEngineerFileDto(busiQualitySeclusionEngineerFileDto);
        return new ResponseEntity<>(ResponseMessage.success("BusiQualitySeclusionEngineerFile创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualitySeclusionEngineerFileDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualitySeclusionEngineerFileDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualitySeclusionEngineerFileDto> getBusiQualitySeclusionEngineerFileDto(@PathVariable("id") Long id) {
    	BusiQualitySeclusionEngineerFileDto busiQualitySeclusionEngineerFileDto = busiQualitySeclusionEngineerFileService.getBusiQualitySeclusionEngineerFileDtoById(id);
        return new ResponseEntity<>(busiQualitySeclusionEngineerFileDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualitySeclusionEngineerFile 
    * @Description:修改BusiQualitySeclusionEngineerFile信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualitySeclusionEngineerFileDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualitySeclusionEngineerFile(@PathVariable("id") Long id, @RequestBody BusiQualitySeclusionEngineerFileDto busiQualitySeclusionEngineerFileDto) throws Exception {
        busiQualitySeclusionEngineerFileService.updateBusiQualitySeclusionEngineerFile(id, busiQualitySeclusionEngineerFileDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualitySeclusionEngineerFileById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualitySeclusionEngineerFileById(@PathVariable("id") Long id) throws Exception {
        busiQualitySeclusionEngineerFileService.deleteBusiQualitySeclusionEngineerFile(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title:  
     * @Description: 根据分部分项编号id获取隐蔽工程附件
     * @param   tId 分部分项编号id
     * @return  ResponseEntity<ResponseMessage>    
     * @throws  Exception
     */
     @RequestMapping(value = "/getSeclEngByTendersId/{tId}", method = RequestMethod.GET)
     @ResponseBody
     public ResponseEntity<List<BusiQualitySeclusionEngineerFileDto>> getSeclEngByTendersId(@PathVariable("tId") Long tId) throws Exception {
    	 List<BusiQualitySeclusionEngineerFileDto> list = busiQualitySeclusionEngineerFileService.getSeclEngListByTendersId(tId);
         return new ResponseEntity<>(list, HttpStatus.OK);
     }
}
