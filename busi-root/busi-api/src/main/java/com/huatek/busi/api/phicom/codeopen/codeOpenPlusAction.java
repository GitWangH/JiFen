package com.huatek.busi.api.phicom.codeopen;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusMemberOrderDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.member.PhiPlusMemberOrderService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.busi.service.phicom.support.OpenPlusMemberService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping("/api/openPlus/codeOpenAction/")
public class codeOpenPlusAction {
	
	private static final Logger log = LoggerFactory.getLogger(codeOpenPlusAction.class);
	
	@Autowired
	private InterfaceApiService interfaceApiService;//第三方接口Api服务类
	@Autowired
	private PhiPlusMemberOrderService phiPlusMemberOrderService;
	@Autowired
	private OpenPlusMemberService openPlusMemberService;
	@Autowired
	PhiMemberService phiMemberService; 

	@RequestMapping(value = "/plusCodeOpen", method = RequestMethod.POST)
	public ResponseEntity<Object> codeOpenPlus(@RequestBody PhiMember phiMember,HttpSession session) throws Exception {
		String message = "K码为空!";
		if(StringUtils.isNotEmpty(phiMember.getKeyNum())){
				String keyNum = phiMember.getKeyNum();
				CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
		         String uid = member.getUid();
				//String uid = (String) packageParams.get("uid");
				//String code = (String) packageParams.get("code");
				
				//第一步 调用辰商的接口校验卷码是否是有效的
				//String code = "123456789";
				JsonObject  json = interfaceApiService.toCheckThePlusCodeForChengShang(keyNum,uid);	
				JSONObject dataJson = JSONObject.fromObject(json.toString()); 
				String status = dataJson.getString("status");
				message = dataJson.getString("message");												
				//第二步 if(卷码是有效的)
				if("200".endsWith(status)){
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String validDateStr ="";
					String amountMoney = "";
					if(!dataJson.getJSONObject("data").isNullObject()){
						JSONObject jsonObject = dataJson.getJSONObject("data");
					     validDateStr = jsonObject.getString("validDate");
						 amountMoney = jsonObject.getString("amountMoney");
					}
					if (StringUtils.isNotEmpty(validDateStr)) {
						Date validDate = formatter.parse(validDateStr);
						if (validDate.compareTo(new Date()) >= 0) {
							//第三步，创建plusOrder保存相关信息
							PhiMember dbPhiMember = phiMemberService.findPhiMemberByUid(Integer.parseInt(uid));
							if(null != dbPhiMember){
								PhiPlusMemberOrderDto phiPlusMemberOrderDto = new PhiPlusMemberOrderDto();
								phiPlusMemberOrderDto.setIsPay("1");
								phiPlusMemberOrderDto.setPayTime(new Date());
								phiPlusMemberOrderDto.setTransactionId(keyNum);//
								phiPlusMemberOrderDto.setPayType("3");//会员码支付
								phiPlusMemberOrderDto.setOrderNo("PLUS_FX_"+System.currentTimeMillis());
								phiPlusMemberOrderDto.setPayMoney(amountMoney);
								phiPlusMemberOrderDto.setCardMoney(amountMoney);
								phiPlusMemberOrderDto.setRealMoney(new BigDecimal(amountMoney));
								phiPlusMemberOrderDto.setCount(1);//默认1
								phiPlusMemberOrderDto.setMemberId(dbPhiMember.getId());
								phiPlusMemberOrderDto.setPlusCode(keyNum);
								phiPlusMemberOrderService.savePhiPlusMemberOrderDto(phiPlusMemberOrderDto);							
								//第四步，开通plus会员
							    openPlusMemberService.openPlusMember(dbPhiMember.getId(), keyNum);//重构的新方法
							}
						}
					}
					return new ResponseEntity<>(ResponseMessage.success(message),HttpStatus.OK);
				 }else if("400".endsWith(status)){ //else(缺少用户参数)
			        log.info("缺少用户参数");
			        return new ResponseEntity<>(ResponseMessage.warning(message),HttpStatus.OK);
				}else if("500".endsWith(status)){ //else(未找到该卷码)			 
				    log.info("未找到该卷码");
				    return new ResponseEntity<>(ResponseMessage.warning(message),HttpStatus.OK);
				}
				else if("600".endsWith(status)){ //else(卷码已过期)		 
				    log.info("卷码已过期");
				    return new ResponseEntity<>(ResponseMessage.warning(message),HttpStatus.OK);
				}
				
		}
		return new ResponseEntity<>(ResponseMessage.success(message),HttpStatus.OK);
	
	}
}
