package com.huatek.busi.api.phicom.alipay;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.appAlipay.config.AlipayConfig;
import com.huatek.busi.appAlipay.util.AlipayNotify;
import com.huatek.busi.appAlipay.util.AlipaySubmit;
import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusGradeDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusMemberOrderDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.impl.phicom.coupons.PhiTimingPushCouponsDetailTaskService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.member.PhiPlusMemberOrderService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.plusmember.PhiPlusGradeService;
import com.huatek.busi.service.phicom.support.OpenPlusMemberService;
import com.huatek.busi.wxpay.WchatPayDto;


/**
* 支付宝
* @ClassName: AliPayAction  
* @Description: TODO  
* @author martin_ju
* @e_mail martin_ju@huatek.com
* @date 2018年1月22日  
 */
@RestController
@RequestMapping("/api/ping/ali/")
public class AliPayAction {

	private static final Logger log = LoggerFactory
			.getLogger(AliPayAction.class);
	@Autowired
	private PhiMemberService phiMemberService;
	
	@Autowired
	private PhiOrderService phiOrderService;
	
	@Autowired
	private PhiPlusMemberOrderService phiPlusMemberOrderService;
	
	@Autowired
	private PhiPlusGradeService phiPlusGradeService;
	
	@Autowired
	private OpenPlusMemberService openPlusMemberService;
	
	@Autowired
	private PhiTimingPushCouponsDetailTaskService phiTimingPushCouponsDetailTaskService;
	
	/**
	 * PC接口
	 * @param dto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/aliPay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> buildRequest(@RequestBody WchatPayDto dto) {
		Map<String, String> returnMap = new HashMap<String, String>();
		String out_trade_no=dto.getOrderNo();
		String price="";
		if(out_trade_no.contains("PLUS")){//plus会员
			PhiPlusMemberOrderDto dtoOrder=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
			if(dtoOrder!=null){
				if("1".equals(dtoOrder.getIsPay())){//订单号已支付成功
					returnMap.put("status", "0");
					returnMap.put("msg", "订单号已支付成功");
					return returnMap;
				}
			}
			PhiPlusGradeDto dto1=phiPlusGradeService.findPhiPlusGradeByPlusCode("plus_399");
			if(dto1!=null){
				price=dto1.getRechargeMoney();
			}else{
				returnMap.put("status", "0");
				returnMap.put("msg", "PLUS会员数据不存在");
				return returnMap;
			}
		}else{
			PhiOrderDto orderDto=phiOrderService.findPhiOrderinfoByOrderNo(out_trade_no);
			if(orderDto!=null){
				if("1".equals(orderDto.getIsclose())){
					returnMap.put("status", "0");
					returnMap.put("msg", "订单号已支付成功");
					return returnMap;
				}else{
					price=String.valueOf(orderDto.getMoney());
				}
			}else{
				returnMap.put("status", "0");
				returnMap.put("msg", "订单号不存在");
				return returnMap;
			}
		}
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", dto.getOrderNo());
		sParaTemp.put("subject", dto.getGoodsName());
		sParaTemp.put("total_fee", price);
		//sParaTemp.put("total_fee", "0.01");
		sParaTemp.put("body", "斐讯积分商城");
		String url=AlipaySubmit.buildRequest(sParaTemp);
		//Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("status", "1");
		returnMap.put("url", url);
		//String out_trade_no=dto.getOrderNo();
		if(out_trade_no.contains("PLUS")){//plus会员
			PhiPlusMemberOrderDto dtoOrder=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
			if(dtoOrder==null){
				PhiPlusMemberOrderDto orderDto= new PhiPlusMemberOrderDto();
				orderDto.setCreateTime(new Date());
				orderDto.setIsPay("0");
				orderDto.setOrderNo(out_trade_no);
				orderDto.setPayMoney(price);
				orderDto.setMemberId(dto.getMemberId());
				orderDto.setPlusCode(dto.getPlusCode());
				orderDto.setCount(1);//默认1
				//支付平台
				orderDto.setPlatForm("1");
				phiPlusMemberOrderService.saveOrUpdatePhiPlusMemberOrder(orderDto);
			}
		}else{
			//设置
			//phiOrderService.setPhiOrderStatusDtoByOrderNo(out_trade_no);
		}
		return returnMap;
	}	
	
	/**
	 * 通知
	 * @param dto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/aliNotice", method = RequestMethod.POST)
	public void aliNoticeAPP(HttpServletResponse response,HttpServletRequest request) throws Exception {
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get (name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
			: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		//商户订单号
		String out_trade_no ="";
		try {
			out_trade_no=new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//支付宝交易号
		String trade_no ="";
		try {
			trade_no= new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//交易状态
		String trade_status="";
		try {
			trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String total_fee="";
		try {
			total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(AlipayNotify.verify(params)){//验证成功
			if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
				if(out_trade_no.contains("PLUS")){//plus会员
					PhiPlusMemberOrderDto orderDto=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
					if(orderDto!=null){
						if("0".equals(orderDto.getIsPay())){//订单号已支付
							orderDto.setIsPay("1");
							orderDto.setPayTime(new Date());
							orderDto.setTransactionId(trade_no);
							orderDto.setPayType("2");
							orderDto.setPayMoney(total_fee);
							orderDto.setCount(1);//默认1
							phiPlusMemberOrderService.saveOrUpdatePhiPlusMemberOrder(orderDto);
//						    phiMemberService.OpenPhimember(orderDto.getMemberId(), orderDto.getPlusCode());
							openPlusMemberService.openPlusMember(orderDto.getMemberId(), orderDto.getPlusCode());//重构的新方法
						}else{
							log.info("PLUS会员已支付");
						}
					}
				}else{//积分+现金  购物
					PhiOrderDto orderDto=phiOrderService.findPhiOrderinfoByOrderNo(out_trade_no);
					if(orderDto!=null){
						if("1".equals(orderDto.getIsclose())){
							log.info("订单号已支付");
						}else{
							phiOrderService.editOrderAfterPayByOrderNo(out_trade_no,"2",trade_no);
						}
					}
				}
				PrintWriter out = response.getWriter();
				out.write("success");
				out.flush();
				out.close();
				System.out.println("---------success---"+out_trade_no);
				System.out.println("---------success---"+trade_no);
			}else{
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.flush();
				out.close();
			}
		}else{
			PrintWriter out = response.getWriter();
			out.write("fail");
			out.flush();
			out.close();
		}
	}
	
	/**
	 * APP接口
	 * @param dto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/aliAppPay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> builBAPPRequest(@RequestBody WchatPayDto dto) {
		Map<String, String> returnMap = new HashMap<String, String>();
		String out_trade_no=dto.getOrderNo();
		String price="";
		log.error("member_id："+dto.getMemberId()+"  out_trade_no:"+dto.getOrderNo());
		if(out_trade_no.contains("PLUS")){//plus会员
			log.error("PLUS[member_id："+dto.getMemberId()+"  out_trade_no:"+dto.getOrderNo()+"]");
			/********************先判断用户是否已开通plus，如果已开通，则提示已开通 Start*************************/
			if(null != dto.getMemberId()){
				PhiMember phiMember = phiMemberService.findPhiMemberById(dto.getMemberId());
				if(null != phiMember && "1".equals(phiMember.getIsplusMember())){//已开通plus会员
					log.error("member_id："+dto.getMemberId()+"  out_trade_no:"+dto.getOrderNo() +"已开通plus会员");
					returnMap.put("status", "0");
					returnMap.put("msg", "已开通plus会员");
					return returnMap;
				}
			}
			/********************先判断用户是否已开通plus，如果已开通，则提示已开通  End*************************/
			
			PhiPlusMemberOrderDto dtoOrder=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
			if(dtoOrder!=null){
				if("1".equals(dtoOrder.getIsPay())){//订单号已支付成功
					returnMap.put("status", "0");
					returnMap.put("msg", "订单号已支付成功");
					return returnMap;
				}
			}
			PhiPlusGradeDto dto1=phiPlusGradeService.findPhiPlusGradeByPlusCode("plus_399");
			if(dto1!=null){
				price=dto1.getRechargeMoney();
			}else{
				returnMap.put("status", "0");
				returnMap.put("msg", "PLUS会员数据不存在");
				return returnMap;
			}
		}else{
			PhiOrderDto orderDto=phiOrderService.findPhiOrderinfoByOrderNo(out_trade_no);
			if(orderDto!=null){
				if("1".equals(orderDto.getIsclose())){
					returnMap.put("status", "0");
					returnMap.put("msg", "订单号已支付成功");
					return returnMap;
				}else{
					price=String.valueOf(orderDto.getMoney());
				}
			}else{
				returnMap.put("status", "0");
				returnMap.put("msg", "订单号不存在");
				return returnMap;
			}
		}
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.app_service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.app_return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", dto.getOrderNo());
		sParaTemp.put("subject", dto.getGoodsName());
		sParaTemp.put("total_fee", price);
		//sParaTemp.put("total_fee", "0.01");
		sParaTemp.put("body", "斐讯积分商城");
		String url=AlipaySubmit.buildRequest(sParaTemp);
		//Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("status", "1");
		returnMap.put("url", url);
		//String out_trade_no=dto.getOrderNo();
		if(out_trade_no.contains("PLUS")){//plus会员
			PhiPlusMemberOrderDto dtoOrder=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
			if(dtoOrder==null){
				PhiPlusMemberOrderDto orderDto= new PhiPlusMemberOrderDto();
				orderDto.setCreateTime(new Date());
				orderDto.setIsPay("0");
				orderDto.setOrderNo(out_trade_no);
				orderDto.setPayMoney(price);
				orderDto.setMemberId(dto.getMemberId());
				orderDto.setPlusCode(dto.getPlusCode());
				//支付平台
				orderDto.setPlatForm(dto.getPlatForm());
				phiPlusMemberOrderService.saveOrUpdatePhiPlusMemberOrder(orderDto);
			}
		}else{
			//设置
			//phiOrderService.setPhiOrderStatusDtoByOrderNo(out_trade_no);
		}
		return returnMap;
	}

	@RequestMapping(value = "/aliPayTest")
	@ResponseBody
	public Map<String,String> builB1Request() {
		WchatPayDto dto = new WchatPayDto();
		dto.setOrderNo("PLUS18994567845689785412");
		dto.setOrderPrice("0.01");
		dto.setMemberId(new Long(11208));
		dto.setPlusCode("plus_399");
		dto.setGoodsName("积分商城");
		Map<String,String> map=buildRequest(dto);
		return map;
	}
	
	@RequestMapping(value = "/aliPayPlusTest/{memberId}")
	@ResponseBody
	public void aliPayPlusTest(@PathVariable("memberId") String memberId) {
		openPlusMemberService.openPlusMember(new Long(memberId), "plus_399");//重构的新方法
	}
	
	@RequestMapping(value = "/timingPushCoupons")
	@ResponseBody
	public void TimingPushCoupons() {
		phiTimingPushCouponsDetailTaskService.pushTimingPushCouponsDetailByManual();
	}
	

	public static void main(String[] args) {
		
	AliPayAction a= new AliPayAction();
		WchatPayDto dto = new WchatPayDto();
		dto.setOrderNo("PLUS1516684565648974");
		dto.setOrderPrice("0.01");
		dto.setMemberId(new Long(11208));
		dto.setPlusCode("plus_399");
		dto.setGoodsName("积分商城");
		Map<String,String> map=a.buildRequest(dto);
		System.out.print(map);
	}
}
