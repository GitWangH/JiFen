package com.huatek.busi.api.contract;
import java.io.IOException;
import java.util.List;

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
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractSupervisorContractDetailDto;
import com.huatek.busi.service.contract.BusiContractSupervisorContractDetailService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractAction
 * @Description: 安全(监理)清单管理后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 20:02:35
 * @version: 1.0
 * @memuInfo [合同管理] - [项目合同] -[安全(监理)清单管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-31 20:02:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_SUPERVISOR_CONTRACT_DETAIL_API)
public class BusiContractSupervisorContractDetailAction {

    @Autowired
    private BusiContractSupervisorContractDetailService busiContractSupervisorContractDetailService;
    @Autowired
    private OperationService operationService;
    
    /**
     * 查询安全清单列表树数据
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/queryTopLevel")
    @ResponseBody
    public ResponseEntity<List<BusiContractSupervisorContractDetailDto>> _getAllTopLevelBusiContractSupervisorContractDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	List<BusiContractSupervisorContractDetailDto> topLevelBusiContractSupervisorContractDetailList = busiContractSupervisorContractDetailService.getAllTopLevelBusiContractSupervisorContractDetailDto(queryPage);
        return new ResponseEntity<>(topLevelBusiContractSupervisorContractDetailList, HttpStatus.OK);
    }
    
    /**
     * 根据父节点查询子节点数据
     * @param parentUUID
     * @return
     */
    @RequestMapping(value = "/queryChildNodes/{parentUUID}")
    @ResponseBody
    public ResponseEntity<List<BusiContractSupervisorContractDetailDto>> _getChildBusiContractSupervisorContractDetailByParentUUID(@PathVariable("parentUUID") String parentUUID) { 	
    	List<BusiContractSupervisorContractDetailDto> childBusiContractSupervisorContractDetailList = busiContractSupervisorContractDetailService.getChildBusiContractSupervisorContractDetailDtoByParentUUID(parentUUID);
       return new ResponseEntity<>(childBusiContractSupervisorContractDetailList, HttpStatus.OK);
    }
    
   /** 
    * @Title: createBusiContractSupervisorContractDetailDto 
    * @Description: 添加BusiContractSupervisorContractDetail 
    * @param    busiContractSupervisorContractDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/saveData/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TreeGridAddIdAndUUIDDto>> saveTreeGridData(@PathVariable("orgId") Long orgId, @RequestBody List<BusiContractSupervisorContractDetailDto> busiContractSupervisorContractDetailDtoList) throws Exception {
        operationService.logOperation("保存安全清单列表数据】");
        return new ResponseEntity<>(busiContractSupervisorContractDetailService.saveTreeGridData(orgId,busiContractSupervisorContractDetailDtoList), HttpStatus.CREATED);
    }
    
    /**
     * 翻页查询列表树数据(废弃)信息.
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
     @Deprecated
     @RequestMapping(value = "/query")
     @ResponseBody
     public ResponseEntity<DataPage<BusiContractSupervisorContractDetailDto>> getAllBusiContractSupervisorContractDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
         DataPage<BusiContractSupervisorContractDetailDto> busiContractSupervisorContractDetailPages = busiContractSupervisorContractDetailService.getAllBusiContractSupervisorContractDetailPage(queryPage);
         return new ResponseEntity<>(busiContractSupervisorContractDetailPages, HttpStatus.OK);
     }
    
}
