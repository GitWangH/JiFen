package com.huatek.busi.api.phicom.support;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.coupons.PhiCouponsService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.plusmember.PhiPlusAllRightService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.frame.service.ExceptionLogService;
import com.huatek.frame.service.dto.ExceptionLogDto;
import com.huatek.frame.util.DateUtil;

/**
 * 手动推送优惠劵
 * @author mickey_meng
 */
@RestController
@RequestMapping("/api/ping/Interface")
public class OpenPlusMemberAction {

    private static final Logger log = LoggerFactory.getLogger(OpenPlusMemberAction.class);

    @Autowired
	private PhiMemberService phiMemberService; 
    
    @Autowired
    private ExceptionLogService exceptionLogService;//异常日志服务类
	
	@Autowired
	private PhiCouponsService phiCouponsService;//优惠劵服务类
	
	@Autowired
	private InterfaceApiService interfaceApiService;//第三方接口Api服务类

   /**
    * SELECT * FROM phi_member m WHERE m.member_id = 2049975;
	* SELECT * FROM phi_coupons_detail cd WHERE cd.member_id = 2049975 ORDER BY cd.coup_id DESC;
	* SELECT * FROM cmd_interface_receive_message l WHERE l.request_body LIKE '%1232325%'  ORDER BY l.create_time DESC LIMIT 1000;
    * @param telNumber
    * @param coupWayId
    */
   	@RequestMapping(value = "/pushCouponsByShouDong/{telNumber}/{coupWayId}")
	@ResponseBody
	public ResponseEntity<String> aliPayPlusTest(@PathVariable("telNumber") String telNumber,@PathVariable("coupWayId") String coupWayId) {
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
//				exceptionLogService.saveExceptionLog(exceptionLogDto);
				return new ResponseEntity<String>("补优惠劵失败:异常信息" + e.getStackTrace(), HttpStatus.BAD_REQUEST);
	        }
   		}else{
   			return new ResponseEntity<String>("补优惠劵失败:账号 "+telNumber+" 不存在或其不属于plus会员!", HttpStatus.BAD_REQUEST);
   		}
	}
   	
   	@Autowired
	private PhiPlusAllRightService phiAllRightService;
   	
   	@RequestMapping(value = "/couponsAutoUptoGrant")
	@ResponseBody
	public void couponsAutoUptoGrant() {
   		log.error("++++++++++++发放每月专享礼包 -----------------");
		phiAllRightService.couponsAutoUptoGrant();	
   	}
}
