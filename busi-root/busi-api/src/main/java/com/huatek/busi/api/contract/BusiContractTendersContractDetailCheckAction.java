package com.huatek.busi.api.contract;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractTendersContractDetailDto;
import com.huatek.busi.service.contract.BusiContractTendersContractDetailCheckService;
import com.huatek.busi.service.contract.BusiContractTendersContractDetailService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractAction
 * @Description: 标段合同表 (施工合同)复核清单后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-01 15:12:35
 * @version: 1.0
 * @memuInfo [合同管理] - [项目合同] -[合同清单复核管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-11-01 15:12:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_TENDERS_CONTRACT_DETAIL_CHECK_API)
public class BusiContractTendersContractDetailCheckAction {

    @Autowired
    private BusiContractTendersContractDetailService busiContractTendersContractDetailService;
    
    @Autowired
    private BusiContractTendersContractDetailCheckService busiContractTendersContractDetailCheckService;
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
    public ResponseEntity<List<BusiContractTendersContractDetailDto>> _getAllTopLevelBusiContractTendersContractDetail(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
    	List<BusiContractTendersContractDetailDto> topLevelBusiContractTendersContractDetailList = busiContractTendersContractDetailCheckService.getAllTopLevelBusiContractTendersContractDetailDto(queryPage);
        return new ResponseEntity<>(topLevelBusiContractTendersContractDetailList, HttpStatus.OK);
    }
    
    /**
     * 根据父节点查询子节点数据
     * @param parentUUID
     * @return
     */
    @RequestMapping(value = "/queryChildNodes/{parentUUID}")
    @ResponseBody
    public ResponseEntity<List<BusiContractTendersContractDetailDto>> _getChildBusiContractTendersContractDetailByParentUUID(@PathVariable("parentUUID") String parentUUID) { 	
       List<BusiContractTendersContractDetailDto> childBusiContractTendersContractDetailList = busiContractTendersContractDetailService.getChildBusiContractTendersContractDetailDtoByParentUUID(parentUUID);
       return new ResponseEntity<>(childBusiContractTendersContractDetailList, HttpStatus.OK);
    }
    
    /** 
     * @Title: saveTreeGridData 
     * @Description: 保存合同复核清单列表数据
     * @param    busiContractTendersContractDetailDtoList
     * @return   ResponseEntity<ResponseMessage>    
     * @throws   Exception
     */ 
     @RequestMapping(value = "/saveData/{orgId}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<List<TreeGridAddIdAndUUIDDto>> saveTreeGridData(@PathVariable("orgId") Long orgId, @RequestBody List<BusiContractTendersContractDetailDto> busiContractTendersContractDetailDtoList) throws Exception {
         operationService.logOperation("保存合同复核清单列表数据】");
         return new ResponseEntity<>(busiContractTendersContractDetailService.saveTreeGridData(orgId,busiContractTendersContractDetailDtoList,"check_detail"), HttpStatus.CREATED);
     }
     
     /**
      * @Title: applyBusiContractTendersContractDetail 
      * @Description: 合同清单流程申请
      * @param   id 合同ID
      * @return  ResponseEntity<ResponseMessage>    
      * @throws
      */
  	  @RequestMapping(value = "/apply/{orgId}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<ResponseMessage> applyBusiContractTendersContractDetailCheck(@PathVariable("orgId") Long orgId) throws Exception {
  		busiContractTendersContractDetailCheckService.applyBusiContractTendersContractDetailCheckByOrgId(orgId);;//申请
  		return new ResponseEntity<>(ResponseMessage.success("申请成功"), HttpStatus.OK);
      }
  	 
  	 /** 
      * @Title: getBusiContractTendersContractDto 
      * @Description: 获取需要修改 标段合同表 (施工合同) 信息
      * @createDate: 2016年4月25日 下午1:49:40
      * @param    id
      * @return   ResponseEntity<BusiContractTendersContractDto>    
      * @throws 
      */ 
      @RequestMapping(value = "/getTendersFlowInfo/{orgId}", method = RequestMethod.GET)
      @ResponseBody
      public ResponseEntity<BusiContractTendersContractDetailDto> getTendersFlowInfo(@PathVariable("orgId") Long orgId) {
    	  BusiContractTendersContractDetailDto busiContractTendersContractDetailDto = busiContractTendersContractDetailService.getBusiContractTendersContractDetailDtoByOrgId(orgId);
    	  if(null != busiContractTendersContractDetailDto){
    		  if(StringUtils.isNotEmpty(busiContractTendersContractDetailDto.getCheckApprovalStatus())){
        		  if(busiContractTendersContractDetailDto.getCheckApprovalStatus().equals(Constant.FLOW_STATUS_UNAPPROVED)){
            		  busiContractTendersContractDetailDto.setApprovalStatusName("未审批");
            	  }else if(busiContractTendersContractDetailDto.getCheckApprovalStatus().equals(Constant.FLOW_STATUS_INAPPROVAL)){
            		  busiContractTendersContractDetailDto.setApprovalStatusName("审批中");
            	  }else if(busiContractTendersContractDetailDto.getCheckApprovalStatus().equals(Constant.FLOW_STATUS_REBUT)){
            		  busiContractTendersContractDetailDto.setApprovalStatusName("已驳回");
            	  }else if(busiContractTendersContractDetailDto.getCheckApprovalStatus().equals(Constant.FLOW_STATUS_PASSED)){
            		  busiContractTendersContractDetailDto.setApprovalStatusName("已通过");
            	  }
        	  }else{
        		  busiContractTendersContractDetailDto.setApprovalStatusName("未申请");
        	  }
    	  }else{
    		  busiContractTendersContractDetailDto = new BusiContractTendersContractDetailDto();
    		  busiContractTendersContractDetailDto.setApprovalStatusName("未申请");
      	  } 
    	  return new ResponseEntity<>(busiContractTendersContractDetailDto, HttpStatus.OK);
      }
     
}
