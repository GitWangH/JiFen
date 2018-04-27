package com.huatek.frame.api;
import java.io.IOException;
import java.util.HashMap;
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
import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.FwAccountRoleService;
import com.huatek.frame.service.dto.FwAccountRoleDto;
import com.huatek.frame.service.dto.FwRoleDto;

@RestController
@RequestMapping(value = FrameUrlConstants.FWACCOUNTROLE_API)
public class FwAccountRoleAction {

    private static final Logger log = LoggerFactory
            .getLogger(FwAccountRoleAction.class);

    @Autowired
    private FwAccountRoleService fwAccountRoleService;
    @Autowired
    private OperationService operationService;
    
    /** 
    * @Title: getAllFwAccountRole 
    * @Description:  翻页查询FwAccountRole信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<FwAccountRoleDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwAccountRoleDto>> _getAllFwAccountRole(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        DataPage<FwAccountRoleDto> fwAccountRolePages = fwAccountRoleService.getAllFwAccountRolePage(queryPage);
        return new ResponseEntity<>(fwAccountRolePages, HttpStatus.OK);
       
    }
    
    @RequestMapping(value = "/queryRoleAcct")
    @ResponseBody
    public ResponseEntity<DataPage<FwAccountRoleDto>> _getRoleAcct(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        
//    	String queryCondition = " ";
//    	queryPage.setSqlCondition(queryCondition);
    	
    	DataPage<FwAccountRoleDto> fwAccountPages = fwAccountRoleService.RoleAccts(queryPage);
        
        return new ResponseEntity<>(fwAccountPages, HttpStatus.OK);
       
    }
    
    /**
     * 
    * @Title: loadAllRoleWithAcct 
    * @Description: 获取用户已有角色 
    * @createDate: 2017年11月4日 下午3:26:16
    * @param   
    * @return  ResponseEntity<Map<String,Object>> 
    * @author cloud_liu   
    * @throws
     */
    @RequestMapping(value = "/assign/loadAllRoleWithAcct/{acctId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> loadAllRoleWithAcct(@PathVariable("acctId") Long acctId) {
    	List<FwRoleDto> roleList =fwAccountRoleService.getAccountRoleByAcct(acctId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", roleList);
		return new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
	}
    
}
