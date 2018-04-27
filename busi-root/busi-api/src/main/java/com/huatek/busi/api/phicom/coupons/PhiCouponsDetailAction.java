package com.huatek.busi.api.phicom.coupons;
import java.util.Date;
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

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.dto.phicom.coupons.RepairCoupons;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.coupons.PhiCouponsDetailService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.dto.ExceptionLogDto;
import com.huatek.frame.util.DateUtil;

@RestController
@RequestMapping(value = BusiUrlConstants.PHICOUPONSDETAIL_API)
public class PhiCouponsDetailAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiCouponsDetailAction.class);

    @Autowired
    private PhiCouponsDetailService phiCouponsDetailService;
    
    @Autowired
	private PhiMemberService phiMemberService; 
    
    @Autowired
	private InterfaceApiService interfaceApiService;//第三方接口Api服务类

    
    /** 
    * @Title: getAllPhiCouponsDetail 
    * @Description:  翻页查询PhiCouponsDetail信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiCouponsDetailDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiCouponsDetailDto>> getAllPhiCouponsDetail(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiCouponsDetail of param " + queryPage.getQueryInfo());
        DataPage<PhiCouponsDetailDto> phiCouponsDetailPages = phiCouponsDetailService.getAllPhiCouponsDetailPage(queryPage);
        log.debug("get phiCouponsDetail size @" + phiCouponsDetailPages.getContent().size());
        return new ResponseEntity<>(phiCouponsDetailPages, HttpStatus.OK);
       
    }
    


    


    @RequestMapping(value = "/queryDetail/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DataPage<PhiCouponsDetailDto>> getPhiCouponsDetailByFatherId(@PathVariable("id") Long id,@RequestBody QueryPage queryPage)  { 	
    	
    	queryPage.setSqlCondition(" coupWayId= "+id);
        DataPage<PhiCouponsDetailDto> phiCouponsDetailPages = phiCouponsDetailService.getPhiCouponsDetailDtoByfatharId(queryPage);
        log.debug("get phiCouponsDetail size @" + phiCouponsDetailPages.getContent().size());
        return new ResponseEntity<>(phiCouponsDetailPages, HttpStatus.OK);
       
    }

     /** 
    * @Title: createPhiCouponsDetailDto 
    * @Description: 添加PhiCouponsDetail 
    * @param    phiCouponsDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiCouponsDetailDto(@RequestBody PhiCouponsDetailDto phiCouponsDetailDto) {
        phiCouponsDetailService.savePhiCouponsDetailDto(phiCouponsDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiCouponsDetail创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiCouponsDetailDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiCouponsDetailDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiCouponsDetailDto> getPhiCouponsDetailDto(@PathVariable("id") Long id) {
    	PhiCouponsDetailDto phiCouponsDetailDto = phiCouponsDetailService.getPhiCouponsDetailDtoById(id);
        return new ResponseEntity<>(phiCouponsDetailDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiCouponsDetail 
    * @Description:修改PhiCouponsDetail信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiCouponsDetailDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiCouponsDetail(@PathVariable("id") Long id, @RequestBody PhiCouponsDetailDto phiCouponsDetailDto) {
        phiCouponsDetailService.updatePhiCouponsDetail(id, phiCouponsDetailDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiCouponsDetailById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiCouponsDetailById(@PathVariable("id") Long id) {
        phiCouponsDetailService.deletePhiCouponsDetail(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /** 
     * @Title: getCouponsDetailByWayId 
     * @Description:通过优惠券方案id获取优惠券明细信息 
     * @createDate: 2016年4月25日 下午1:49:25
     * @param    wayId
     * @param    queryPage
     * @return   ResponseEntity<DataPage<PhiCouponsDetailDto>>    
     */ 
     @RequestMapping(value = "/getCouponsDetailByWayId/{wayId}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<DataPage<PhiCouponsDetailDto>> getCouponsDetailByWayId(@PathVariable("wayId") Long wayId,@RequestBody QueryPage queryPage) {
    	 DataPage<PhiCouponsDetailDto> phiCouponsDetailDtoPages = phiCouponsDetailService.getPhiCouponsDetailById(wayId,queryPage);
         return new ResponseEntity<DataPage<PhiCouponsDetailDto>>(phiCouponsDetailDtoPages, HttpStatus.OK);
     }
     
     
     /**
      * 手动补推优惠劵
      * @param telNumber
      * @param coupWayId
      * @return
      */
    @RequestMapping(value = "/repairCoupons/{telNumber}/{coupWayId}")
 	@ResponseBody
 	public ResponseEntity<String> repairCoupons(@PathVariable("telNumber") String telNumber,@PathVariable("coupWayId") String coupWayId) {
    		PhiMember phiMember = phiMemberService.findPhiMemberByTelNumber(telNumber.trim());
    		if(null != phiMember && "1".equals(phiMember.getIsplusMember())){
 	   		log.error("手动推送优惠劵(uid="+phiMember.getUId()+"|tel="+telNumber+"|member_id="+phiMember.getId()+")" + DateUtil.timeFormat.format(new Date()));
 	        try {
 	        	PhiCouponsDetail couponsDetial = new PhiCouponsDetail();
 	        	couponsDetial.setMemberId(phiMember);
 	        	couponsDetial.setCoupWayId(Long.valueOf(coupWayId.trim()));
 	        	/********************** 2、 绑定优惠劵 & 推送已绑定的优惠劵  *********************************/
 		        interfaceApiService.pushAndBindingSupplyPhiCouponsToChengShang(couponsDetial);
 		        return new ResponseEntity<String>("补优惠劵成功!", HttpStatus.OK);
 	        }catch (Exception e) {
 	        	ExceptionLogDto exceptionLogDto = new ExceptionLogDto();
 				exceptionLogDto.setEcptMessage(phiMember.getTel() + "开通plus会员绑定优惠劵失败(PhiMemberServiceImpl_862)!");
 				exceptionLogDto.setEcptModule("PLUS会员开通绑定优惠劵");
 				exceptionLogDto.setCreateTime(DateUtil.timeFormat.format(new Date()));
 				exceptionLogDto.setAcctName(phiMember.getTel());
 				exceptionLogDto.setEcptCode("open_plus");
 				exceptionLogDto.setEcptStack(e.toString());
// 				exceptionLogService.saveExceptionLog(exceptionLogDto);
 				return new ResponseEntity<String>("补优惠劵失败:异常信息" + e.getStackTrace(), HttpStatus.BAD_REQUEST);
 	        }
    		}else{
    			return new ResponseEntity<String>("补优惠劵失败:账号 "+telNumber+" 不存在或其不属于plus会员!", HttpStatus.BAD_REQUEST);
    		}
 	}
    
    /**
     * 批量手动补推优惠劵
     * @param telNumber
     * @param coupWayId
     * @return
     */
    @RequestMapping(value = "/batchRepairCoupons")
	@ResponseBody
	public ResponseEntity<String> repairCoupons(@RequestBody List<RepairCoupons> repairCouponsList) {
    	StringBuffer responseMsg = new StringBuffer("");
    	HttpStatus httpStatus = HttpStatus.OK;
    	if(repairCouponsList != null && repairCouponsList.size() > 0){
    		for(RepairCoupons repairCoupons:repairCouponsList){
    			String telNumber = repairCoupons.getTel();
    	    	String coupWayId  = repairCoupons.getCpns_code();
    			if(StringUtils.isNotEmpty(telNumber) && StringUtils.isNotEmpty(coupWayId)){
    				telNumber = telNumber.trim();
    				coupWayId = coupWayId.trim();
    			}else{
    				responseMsg.append(telNumber+"手机号或者方案ID为空;\n");
    				httpStatus = HttpStatus.BAD_REQUEST;
    				continue;
    			}
    	   		PhiMember phiMember = phiMemberService.findPhiMemberByTelNumber(telNumber.trim());
    	   		if(null != phiMember && "1".equals(phiMember.getIsplusMember())){
    		   		log.error("手动推送优惠劵(uid="+phiMember.getUId()+"|tel="+telNumber+"|member_id="+phiMember.getId()+")" + DateUtil.timeFormat.format(new Date()));
    		        try {
    		        	PhiCouponsDetail couponsDetial = new PhiCouponsDetail();
    		        	couponsDetial.setMemberId(phiMember);
    		        	couponsDetial.setCoupWayId(Long.valueOf(coupWayId.trim()));
    		        	/********************** 2、 绑定优惠劵 & 推送已绑定的优惠劵  *********************************/
    			        interfaceApiService.pushAndBindingSupplyPhiCouponsToChengShang(couponsDetial);
    		        }catch (Exception e) {
    		        	ExceptionLogDto exceptionLogDto = new ExceptionLogDto();
    					exceptionLogDto.setEcptMessage(phiMember.getTel() + "开通plus会员绑定优惠劵失败(PhiMemberServiceImpl_862)!");
    					exceptionLogDto.setEcptModule("PLUS会员开通绑定优惠劵");
    					exceptionLogDto.setCreateTime(DateUtil.timeFormat.format(new Date()));
    					exceptionLogDto.setAcctName(phiMember.getTel());
    					exceptionLogDto.setEcptCode("open_plus");
    					exceptionLogDto.setEcptStack(e.toString());
//    					exceptionLogService.saveExceptionLog(exceptionLogDto);
    					responseMsg.append(telNumber+"补优惠劵失败;\n");
    					httpStatus = HttpStatus.BAD_REQUEST;
    		        }
    	   		}else{
    	   			responseMsg.append(telNumber+"补优惠劵失败:原因："+telNumber+"不存在或其不属于plus会员;\n");
					httpStatus = HttpStatus.BAD_REQUEST;
    	   		}
    		}
    		if(StringUtils.isEmpty(responseMsg.toString())){
    			responseMsg.append("补优惠劵成功!");
    			httpStatus = HttpStatus.OK;
    		}
    	}
    	return new ResponseEntity<String>(responseMsg.toString(), httpStatus);
	}
}
