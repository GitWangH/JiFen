package com.huatek.busi.api.project;
import java.io.IOException;
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
import com.huatek.busi.dto.project.BusiRemoteMonitorDto;
import com.huatek.busi.service.project.BusiRemoteMonitorService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_REMOTE_MONITOR_API)
public class BusiRemoteMonitorAction {

    private static final Logger log = LoggerFactory
            .getLogger(BusiRemoteMonitorAction.class);

    @Autowired
    private BusiRemoteMonitorService busiRemoteMonitorService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllBusiRemoteMonitor 
    * @Description:  翻页查询BusiRemoteMonitor信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiRemoteMonitorDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiRemoteMonitorDto>> getAllBusiRemoteMonitor(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        if(null == UserUtil.getUser().getTenantId()){
        	queryPage.setSqlCondition(" tenantId is null and tenders.level3 = "+UserUtil.getUser().getCurrProId());
        }else {
        	queryPage.setSqlCondition(" tenders.level3 = "+UserUtil.getUser().getCurrProId());
        }
    	DataPage<BusiRemoteMonitorDto> busiRemoteMonitorPages = busiRemoteMonitorService.getAllBusiRemoteMonitorPage(queryPage);
        return new ResponseEntity<>(busiRemoteMonitorPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createBusiRemoteMonitorDto 
    * @Description: 添加BusiRemoteMonitor 
    * @param    busiRemoteMonitorDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiRemoteMonitorDto(@RequestBody BusiRemoteMonitorDto busiRemoteMonitorDto) throws Exception {
        busiRemoteMonitorService.saveBusiRemoteMonitorDto(busiRemoteMonitorDto);
        operationService.logOperation("创建【BusiRemoteMonitor】");
        return new ResponseEntity<>(ResponseMessage.success("远程监控配置成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiRemoteMonitorDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiRemoteMonitorDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiRemoteMonitorDto> getBusiRemoteMonitorDto(@PathVariable("id") Long id) {
    	BusiRemoteMonitorDto busiRemoteMonitorDto = busiRemoteMonitorService.getBusiRemoteMonitorDtoById(id);
        return new ResponseEntity<>(busiRemoteMonitorDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiRemoteMonitor 
    * @Description:修改BusiRemoteMonitor信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiRemoteMonitorDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiRemoteMonitor(@PathVariable("id") Long id, @RequestBody BusiRemoteMonitorDto busiRemoteMonitorDto) throws Exception {
        busiRemoteMonitorService.updateBusiRemoteMonitor(id, busiRemoteMonitorDto);
        operationService.logOperation("修改【BusiRemoteMonitor("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiRemoteMonitorById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiRemoteMonitorById(@PathVariable("id") Long id) throws Exception {
        busiRemoteMonitorService.deleteBusiRemoteMonitor(id);
        operationService.logOperation("删除【BusiRemoteMonitor("+id+")】");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 
    * @Title: getAllUserRemoteMonitor 
    * @Description: 根据监控类型获取用户监控数据 
    * @createDate: 2017年11月16日 下午4:16:17
    * @param   
    * @return  ResponseEntity<ResponseMessage> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/getAllUserRemoteMonitor/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BusiRemoteMonitorDto>> getAllUserRemoteMonitor(@PathVariable("type") String type) throws Exception {
    	List<BusiRemoteMonitorDto> list = busiRemoteMonitorService.getAllUserRemoteMonitorByMotitorType(type, UserUtil.getUser().getOrgId(), UserUtil.getUser().getCurrProId());
    	if(null != list && !list.isEmpty()){
    		for(BusiRemoteMonitorDto dto : list){
    			if(StringUtils.isNotBlank(dto.getTitle())){
    				dto.setTendersName(dto.getTendersName()+"  "+dto.getTitle());
    			}
    		}
    	}
    	return new ResponseEntity<List<BusiRemoteMonitorDto>>(list, HttpStatus.OK);
    }
}
