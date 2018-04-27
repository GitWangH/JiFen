package com.huatek.busi.api.quality;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.huatek.busi.dto.quality.BusiQualityConstructionInspectionDto;
import com.huatek.busi.service.quality.BusiQualityConstructionInspectionService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 施工报检action
 * @author rocky_wei
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYCONSTRUCTIONINSPECTION_API)
public class BusiQualityConstructionInspectionAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityConstructionInspectionAction.class);

    @Autowired
    private BusiQualityConstructionInspectionService busiQualityConstructionInspectionService;

    
    /** 
    * @Title: getAllBusiQualityConstructionInspection 
    * @Description:  翻页查询BusiQualityConstructionInspection信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityConstructionInspectionDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityConstructionInspectionDto>> getAllBusiQualityConstructionInspection(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
//    	if(!UserUtil.getUser().getFromApp()){
//    		queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
//    	}
		queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
    	
    	DataPage<BusiQualityConstructionInspectionDto> busiQualityConstructionInspectionPages = busiQualityConstructionInspectionService.getAllBusiQualityConstructionInspectionPage(queryPage);
        return new ResponseEntity<>(busiQualityConstructionInspectionPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityConstructionInspectionDto 
    * @Description: 添加BusiQualityConstructionInspection 
    * @param    busiQualityConstructionInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityConstructionInspectionDto(@RequestBody BusiQualityConstructionInspectionDto busiQualityConstructionInspectionDto) throws Exception {
        busiQualityConstructionInspectionService.saveBusiQualityConstructionInspectionDto(busiQualityConstructionInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("报检添加成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityConstructionInspectionDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityConstructionInspectionDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityConstructionInspectionDto> getBusiQualityConstructionInspectionDto(@PathVariable("id") Long id) {
    	BusiQualityConstructionInspectionDto busiQualityConstructionInspectionDto = busiQualityConstructionInspectionService.getBusiQualityConstructionInspectionDtoById(id);
        return new ResponseEntity<>(busiQualityConstructionInspectionDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityConstructionInspection 
    * @Description:修改BusiQualityConstructionInspection信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityConstructionInspectionDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityConstructionInspection(@PathVariable("id") Long id, @RequestBody BusiQualityConstructionInspectionDto busiQualityConstructionInspectionDto) throws Exception {
        busiQualityConstructionInspectionService.updateBusiQualityConstructionInspection(id, busiQualityConstructionInspectionDto);
        return new ResponseEntity<>(ResponseMessage.success("报检修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityConstructionInspectionById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityConstructionInspectionById(@PathVariable("id") Long id) throws Exception {
        busiQualityConstructionInspectionService.deleteBusiQualityConstructionInspection(id);
        return new ResponseEntity<>(ResponseMessage.success("报检删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: busiQualityConstructionInspectionFlow 
     * @Description: 施工报检流程申请. 
     * @param   id
     * @return  ResponseEntity<ResponseMessage>    
     * @throws  Exception
     */
    @RequestMapping(value = "/flow/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> busiQualityConstructionInspectionFlow(@PathVariable("id") Long id) throws Exception {
    	busiQualityConstructionInspectionService.reportQualityConstructionInspection(id);
    	return new ResponseEntity<>(ResponseMessage.success("流程发起成功"), HttpStatus.OK);
    }
    
    /**
     * 施工报检审批
     * @param taskId
     * @param busiId
     * @param map
     * @return
     */
    @RequestMapping(value = "/conInspectApprove/{taskId}/{busiId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> conInspectApprove(@PathVariable("taskId") String taskId,@PathVariable("busiId") Long busiId,@RequestBody Map<String,Object> map) {
		
    	busiQualityConstructionInspectionService.constructionInspectionApprove(taskId, busiId, map);
		return new ResponseEntity<ResponseMessage>(ResponseMessage.success("审批成功!") ,HttpStatus.OK);
	} 
    
    /**
     * 通过整改编号查询施工报检信息
     * @param inspectionCode
     * @return
     */
    @RequestMapping(value = "/getConInspectionByInsCode/{inspectionCode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BusiQualityConstructionInspectionDto>> conInspectApprove(@PathVariable("inspectionCode") String inspectionCode) {
    	return new ResponseEntity<List<BusiQualityConstructionInspectionDto>>(busiQualityConstructionInspectionService.getConInspectionByInsCode(inspectionCode) ,HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/getConInspectionByInsCodeData/{inspectionCode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityConstructionInspectionDto> conInspectApproveData(@PathVariable("inspectionCode") String inspectionCode) {
    	List<BusiQualityConstructionInspectionDto> list = busiQualityConstructionInspectionService.getConInspectionByInsCode(inspectionCode);
    	BusiQualityConstructionInspectionDto dto = null;
    	if(null != list && !list.isEmpty()){
    		dto = list.get(0);
    	}
    	return new ResponseEntity<BusiQualityConstructionInspectionDto>( dto,HttpStatus.OK);
    } 
}
