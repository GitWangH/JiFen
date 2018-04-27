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
import com.huatek.busi.dto.contract.BusiContractOtherContractDetailDto;
import com.huatek.busi.service.contract.BusiContractOtherContractDetailService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractOtherContractDetailAction
 * @Description: 其它合同清单管理后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 17:01:35
 * @version: 1.0
 * @memuInfo [合同管理] - [项目合同] -[其它合同清单管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-31 17:01:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_OTHER_CONTRACT_DETAIL_API)
public class BusiContractOtherContractDetailAction {

    @Autowired
    private BusiContractOtherContractDetailService busiContractOtherContractDetailService;
    
    @Autowired
    private OperationService operationService;//日志记录服务类
    
    /**
     * 查询顶级节点列表数据
     * @param queryPage
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/queryTopLevel")
    @ResponseBody
    public ResponseEntity<List<BusiContractOtherContractDetailDto>> _getAllTopLevelBusiContractOtherContractDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	List<BusiContractOtherContractDetailDto> topLevelBusiContractOtherContractDetailList = busiContractOtherContractDetailService.getAllTopLevelBusiContractOtherContractDetailDto(queryPage);
        return new ResponseEntity<>(topLevelBusiContractOtherContractDetailList, HttpStatus.OK);
    }
    
    /**
     * 根据父级ID查询自己节点数据
     * @param parentUUID
     * @return
     */
     @RequestMapping(value = "/queryChildNodes/{parentUUID}")
     @ResponseBody
     public ResponseEntity<List<BusiContractOtherContractDetailDto>> _getChildBusiContractOtherContractDetailDtoByParentUUID(@PathVariable("parentUUID") String parentUUID) { 	
     	List<BusiContractOtherContractDetailDto> childBusiContractOtherContractDetailList = busiContractOtherContractDetailService.getChildBusiContractOtherContractDetailDtoByParentUUID(parentUUID);
        return new ResponseEntity<>(childBusiContractOtherContractDetailList, HttpStatus.OK);
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
     public ResponseEntity<List<TreeGridAddIdAndUUIDDto>> saveTreeGridData(@PathVariable("orgId") Long orgId, @RequestBody List<BusiContractOtherContractDetailDto> busiContractOtherContractDetailDtoList) throws Exception {
         operationService.logOperation("保存其它合同清单列表数据】");
         return new ResponseEntity<>(busiContractOtherContractDetailService.saveTreeGridData(orgId, busiContractOtherContractDetailDtoList), HttpStatus.CREATED);
     }

}
