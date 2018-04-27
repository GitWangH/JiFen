package com.huatek.busi.api.contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.project.port.BusiProjectManagementOfBidSectionPortDto;
import com.huatek.busi.service.project.BusiProjectManagementOfBidSectionService;
import com.huatek.frame.authority.service.OperationService;

/**
 * @ClassName: BusiContractRestfulApiAction
 * @Description: 合同管理API控制器Action
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-31 15:30:35
 * @version: 1.0
 * @memuInfo [合同管理]
 * ------------------------------------------------------------------
 * 修改历史 
 * 序号       版本号          	    修改日期       			 修改人           	        修改原因 
 *  1	  1.0	2017-10-31 15:30:35	  mickey_meng	 Create New Class
 *  2
 *  ...
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_CONTRACT_RESTFUL_API)
public class BusiContractRestfulApiAction {

    @Autowired
    private OperationService oprationService;//日志记录服务类
    
    @Autowired
    private BusiProjectManagementOfBidSectionService busiProjectManagementOfBidSectionService;

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
