package com.huatek.busi.api.quality;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.huatek.busi.dto.quality.BusiQualityMonthlyReportManagementDto;
import com.huatek.busi.service.quality.BusiQualityMonthlyReportManagementService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

/**
 * 月报管理
 * @author rocky_wei
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSIQUALITYREPORTMAG_API)
public class BusiQualityMonthlyReportManagementAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiQualityMonthlyReportManagementAction.class);

    @Autowired
    private BusiQualityMonthlyReportManagementService busiQualityMonthlyReportManagementService;

    
    /** 
    * @Title: getAllBusiQualityMonthlyReportManagement 
    * @Description:  翻页查询BusiQualityMonthlyReportManagement信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiQualityMonthlyReportManagementDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiQualityMonthlyReportManagementDto>> getAllBusiQualityMonthlyReportManagement(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all busiQualityMonthlyReportManagement of param " + queryPage.getQueryInfo());
//        String startWriteReportTime = "";
//        String endWriteReportTime = "";
//        StringBuffer buffer = new StringBuffer();
//        List<QueryParam> list =new ArrayList<QueryParam>();
//        for(QueryParam queryParam : queryPage.getQueryParamList()){
//        	if("writeReportTime".equals(queryParam.getField())){
//        		startWriteReportTime = queryParam.getValue();
//        		continue;
//        	}
//        	if("writeReportTime_".equals(queryParam.getField())){
//        		endWriteReportTime = queryParam.getValue();
//        		continue;
//        	}
//        	list.add(queryParam);
//        }
//        if(StringUtils.isNotBlank(startWriteReportTime)){
//        	buffer.append(" writeReportTime >= '"+startWriteReportTime+"'");
//        }
//        if(StringUtils.isNotBlank(endWriteReportTime)){
//        	if(StringUtils.isNotBlank(startWriteReportTime)){
//        		buffer.append(" and ");
//        	}
//        	buffer.append(" writeReportTime <= '"+endWriteReportTime+"'");
//        }
//        queryPage.setSqlCondition(buffer.toString());
//        queryPage.setQueryParamList(list);
        queryPage.setSqlCondition(" org.level3 ="+UserUtil.getUser().getCurrProId()+" ");
        DataPage<BusiQualityMonthlyReportManagementDto> busiQualityMonthlyReportManagementPages = busiQualityMonthlyReportManagementService.getAllBusiQualityMonthlyReportManagementPage(queryPage);
        log.debug("get busiQualityMonthlyReportManagement size @" + busiQualityMonthlyReportManagementPages.getContent().size());
        return new ResponseEntity<>(busiQualityMonthlyReportManagementPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiQualityMonthlyReportManagementDto 
    * @Description: 添加BusiQualityMonthlyReportManagement 
    * @param    busiQualityMonthlyReportManagementDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiQualityMonthlyReportManagementDto(@RequestBody BusiQualityMonthlyReportManagementDto busiQualityMonthlyReportManagementDto) throws Exception {
        busiQualityMonthlyReportManagementService.saveBusiQualityMonthlyReportManagementDto(busiQualityMonthlyReportManagementDto);
        return new ResponseEntity<>(ResponseMessage.success("创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiQualityMonthlyReportManagementDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiQualityMonthlyReportManagementDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityMonthlyReportManagementDto> getBusiQualityMonthlyReportManagementDto(@PathVariable("id") Long id) {
    	BusiQualityMonthlyReportManagementDto busiQualityMonthlyReportManagementDto = busiQualityMonthlyReportManagementService.getBusiQualityMonthlyReportManagementDtoById(id);
        return new ResponseEntity<>(busiQualityMonthlyReportManagementDto, HttpStatus.OK);
    }
    
    /** 
     * @Title: getBusiQualityMonthlyReportManagementDto 
     * @Description: 获取审批信息
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    id
     * @return   ResponseEntity<BusiQualityMonthlyReportManagementDto>    
     * @throws 
     */ 
    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiQualityMonthlyReportManagementDto> getBusiQualityMonthlyReportApprove(@PathVariable("id") Long id) {
    	BusiQualityMonthlyReportManagementDto busiQualityMonthlyReportManagementDto = busiQualityMonthlyReportManagementService.getBusiQualityMonthlyReportManagementDtoById(id);
    	return new ResponseEntity<>(busiQualityMonthlyReportManagementDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiQualityMonthlyReportManagement 
    * @Description:修改BusiQualityMonthlyReportManagement信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiQualityMonthlyReportManagementDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiQualityMonthlyReportManagement(@PathVariable("id") Long id, @RequestBody BusiQualityMonthlyReportManagementDto busiQualityMonthlyReportManagementDto) throws Exception {
        busiQualityMonthlyReportManagementService.updateBusiQualityMonthlyReportManagement(id, busiQualityMonthlyReportManagementDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiQualityMonthlyReportManagementById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiQualityMonthlyReportManagementById(@PathVariable("id") Long id) throws Exception {
        busiQualityMonthlyReportManagementService.deleteBusiQualityMonthlyReportManagement(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: flow 
     * @Description: 根据ID删除MdmLineBaseInfo信息. 
     * @param   id
     * @return  ResponseEntity<ResponseMessage>    
     * @throws  Exception
     */
    @RequestMapping(value = "/flow/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessage> monthReportFlowStart(@PathVariable("id") Long id) throws Exception {
    	busiQualityMonthlyReportManagementService.monthReportFlowStart(id);
    	return new ResponseEntity<>(ResponseMessage.success("上报成功"), HttpStatus.OK);
    }
}
