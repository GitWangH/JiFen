package com.huatek.busi.api.contract;
import java.io.IOException;

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
import com.huatek.busi.dto.contract.BusiContractTendersContractDto;
import com.huatek.busi.dto.project.port.BusiProjectManagementOfBidSectionPortDto;
import com.huatek.busi.service.contract.BusiContractTendersContractService;
import com.huatek.busi.service.project.BusiProjectManagementOfBidSectionService;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractAction
 * @Description: 标段合同表 (施工合同)后台控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 13:29:35
 * @version: 1.0
 * @memuInfo [合同管理] - [项目合同] -[施工合同管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-24 13:29:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_TENDERS_CONTRACT_API)
public class BusiContractTendersContractAction {

    private static final Logger log = LoggerFactory.getLogger(BusiContractTendersContractAction.class);

    @Autowired
    private BusiContractTendersContractService busiContractTendersContractService;//标段合同表 (施工合同)服务层接口
    
    @Autowired
    private OperationService oprationService;//日志记录服务类
    
    @Autowired
    private BusiProjectManagementOfBidSectionService busiProjectManagementOfBidSectionService;

    /** 
    * @Title: getAllBusiContractTendersContract 
    * @Description:  翻页查询 标段合同表 (施工合同) 分页信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<BusiContractTendersContractDto>>    
    * @throws  JsonParseException
    * @throws  JsonMappingException
    * @throws  IOException 
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<BusiContractTendersContractDto>> _getAllBusiContractTendersContract(@RequestBody QueryPage queryPage) throws JsonParseException, JsonMappingException, IOException { 	
        log.debug("get all busiContractTendersContract of param " + queryPage.getQueryInfo());
        DataPage<BusiContractTendersContractDto> busiContractTendersContractPages = busiContractTendersContractService.getAllBusiContractTendersContractPage(queryPage);
        log.debug("get busiContractTendersContract size @" + busiContractTendersContractPages.getContent().size());
        return new ResponseEntity<>(busiContractTendersContractPages, HttpStatus.OK);
    }
    
     /** 
    * @Title: createBusiContractTendersContractDto 
    * @Description: 新增 标段合同表 (施工合同)
    * @param    busiContractTendersContractDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws   Exception
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createBusiContractTendersContractDto(@RequestBody BusiContractTendersContractDto busiContractTendersContractDto) throws Exception {
        busiContractTendersContractService.saveBusiContractTendersContractDto(busiContractTendersContractDto);
        oprationService.logOperation("新增标段合同表 (施工合同)["+busiContractTendersContractDto.getContractCnName()+"]");
        return new ResponseEntity<>(ResponseMessage.success("施工合同创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getBusiContractTendersContractDto 
    * @Description: 获取需要修改 标段合同表 (施工合同) 信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<BusiContractTendersContractDto>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BusiContractTendersContractDto> getBusiContractTendersContractDto(@PathVariable("id") Long id) {
    	BusiContractTendersContractDto busiContractTendersContractDto = busiContractTendersContractService.getBusiContractTendersContractDtoById(id);
        return new ResponseEntity<>(busiContractTendersContractDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editBusiContractTendersContract 
    * @Description:执行修改 标段合同表 (施工合同)信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    busiContractTendersContractDto
    * @return   ResponseEntity<ResponseMessage>    
    * @throws 
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editBusiContractTendersContract(@PathVariable("id") Long id, @RequestBody BusiContractTendersContractDto busiContractTendersContractDto) throws Exception {
    	String[] ignoreTargetFields = {"creater","createrName","createTime","tenantId","isDelete","approvalStatus"};//不更新的字段
    	busiContractTendersContractService.updateBusiContractTendersContract(id, busiContractTendersContractDto, ignoreTargetFields);
    	oprationService.logOperation("修改标段合同表 (施工合同)[ID:"+id+"]");
    	return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deleteBusiContractTendersContractById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    * @throws  Exception
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteBusiContractTendersContractById(@PathVariable("id") Long id) throws Exception {
        busiContractTendersContractService.deleteBusiContractTendersContract(id);
        oprationService.logOperation("删除标段合同表 (施工合同)[ID:"+id+"]");
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * @Title: applyBusiContractTendersContract 
     * @Description: 合同流程申请
     * @param   id 合同ID
     * @return  ResponseEntity<ResponseMessage>    
     * @throws
     */
 	 @RequestMapping(value = "/apply/{id}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> applyBusiContractTendersContract(@PathVariable("id") Long id) throws Exception {
 		busiContractTendersContractService.applyBusiContractTendersContract(id);//申请
 		return new ResponseEntity<>(ResponseMessage.success("申请成功"), HttpStatus.OK);
     }
 	
 	/**
 	 * 根据机构编码获取标段信息
 	 * @param id
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "/getTendersInfoByOrgId/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getTendersInfoByOrgId(@PathVariable("orgId") Long orgId) throws Exception {
 		BusiProjectManagementOfBidSectionPortDto busiProjectManagementOfBidSectionPortDto = null;
 		if(null != orgId){
 			busiProjectManagementOfBidSectionPortDto = busiProjectManagementOfBidSectionService.getInfoForTheContractByOrgId(orgId);
 		}
		return new ResponseEntity<>(busiProjectManagementOfBidSectionPortDto, HttpStatus.OK);
    }
}
