package com.huatek.busi.api.phicom.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusGradeDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusMemberOrderDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.member.PhiPlusMemberOrderService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.plusmember.PhiPlusGradeService;
import com.huatek.busi.service.phicom.support.OpenPlusMemberService;
import com.huatek.busi.wxpay.HttpUtil;
import com.huatek.busi.wxpay.PayCommonUtil;
import com.huatek.busi.wxpay.PayConfigUtil;
import com.huatek.busi.wxpay.WchatPayDto;
import com.huatek.busi.wxpay.XMLUtil;

/**
 * 微信支付
 * 
 * @ClassName: WchatPayAction
 * @Description: TODO
 * @author martin_ju
 * @e_mail martin_ju@huatek.com
 * @date 2018年1月22日
 *
 */
@RestController
@RequestMapping("/api/ping/wchat/")
public class WchatPayAction {

	private static final Logger log = LoggerFactory
			.getLogger(WchatPayAction.class);
	
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
	
	/**
	 * 微信支付返回二维码code
	 */
	@RequestMapping(value = "/wchatPay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> weixin_pay(@RequestBody WchatPayDto dto)
			throws Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		String out_trade_no=dto.getOrderNo();
		String price="";
		if(out_trade_no.contains("PLUS")){//plus会员
			PhiPlusMemberOrderDto dtoOrder=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
			if(dtoOrder!=null){
				if("1".equals(dtoOrder.getIsPay())){//订单号已支付成功
					returnMap.put("msg", "订单号已支付成功");
					returnMap.put("type", "ERROR");
					return returnMap;
				}
			}
			PhiPlusGradeDto dto1=phiPlusGradeService.findPhiPlusGradeByPlusCode("plus_399");
			if(dto1!=null){
				price=dto1.getRechargeMoney();
			}else{
				returnMap.put("type", "ERROR");
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
		//Map<String, String> returnMap = new HashMap<String, String>();
		// 账号信息
		String appid = PayConfigUtil.APP_ID; // appid
		String mch_id = PayConfigUtil.MCH_ID; // 商业号
		String key = PayConfigUtil.API_KEY; // key

		String currTime = PayCommonUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = PayCommonUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;

		String order_price =dto.getOrderPrice(); // 价格 注意：价格的单位是分
		//String order_price ="1"; 
		String body = "斐讯积分商城--"+dto.getGoodsName(); // 商品名称
		//String out_trade_no =dto.getOrderNo(); // 订单号

		// 获取发起电脑 ip
		String spbill_create_ip = PayConfigUtil.CREATE_IP;
		// 回调接口
		String notify_url = PayConfigUtil.NOTIFY_URL;
		String trade_type = "NATIVE";

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", PayCommonUtil.getMoney(price));
		//packageParams.put("total_fee", order_price);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
		packageParams.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		System.out.println(requestXML);
		String resXml = HttpUtil
				.postData(PayConfigUtil.UFDODER_URL, requestXML);
		Map map = XMLUtil.doXMLParse(resXml);
		String return_code = (String) map.get("return_code");
		String result_code = (String) map.get("result_code");
		if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
			String urlCode = (String) map.get("code_url");
			String prepay_id = (String) map.get("prepay_id");
			returnMap.put("urlCode", urlCode);
			returnMap.put("msg", "成功");
			returnMap.put("prepay_id", prepay_id);
			returnMap.put("type", "SUCCESS");
			returnMap.put("orderNo", out_trade_no);
			log.error(resXml);
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
		} else {
			returnMap.put("msg", "失败");
			returnMap.put("type", "ERROR");
		}
		System.out.println(return_code + "----------------------------");
		System.out.println(result_code + "----------------------------");
		return returnMap;
	}

	/**
	 * 回调通知
	 */
	@RequestMapping(value = "/wchatNotice", method = RequestMethod.POST)
	public void weixin_notify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		// 账号信息
		String key = PayConfigUtil.API_KEY; // key

		log.info("{}", packageParams);
		// 判断签名是否正确
		String resXml = "";
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				// ////////执行自己的业务逻辑////////////////
				String mch_id = (String) packageParams.get("mch_id");
				String openid = (String) packageParams.get("openid");
				String is_subscribe = (String) packageParams
						.get("is_subscribe");
				String out_trade_no = (String) packageParams
						.get("out_trade_no");
				String transaction_id = (String) packageParams
						.get("transaction_id");
				String total_fee = (String) packageParams.get("total_fee");

				log.info("mch_id:" + mch_id);
				log.info("openid:" + openid);
				log.info("is_subscribe:" + is_subscribe);
				log.info("out_trade_no:" + out_trade_no);
				log.info("total_fee:" + total_fee);
				log.info("transaction_id:" + transaction_id);
				// ////////执行自己的业务逻辑////////////////
				if(out_trade_no.contains("PLUS")){//plus会员
					PhiPlusMemberOrderDto orderDto=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
					if(orderDto!=null){
						if("0".equals(orderDto.getIsPay())){//订单号已支付
							orderDto.setIsPay("1");
							orderDto.setPayTime(new Date());
							orderDto.setTransactionId(transaction_id);
							orderDto.setPayType("1");
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
							phiOrderService.editOrderAfterPayByOrderNo(out_trade_no,"1",transaction_id);
						}
					}
				}
				log.info("支付成功");
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>"
						+ "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				log.info("支付失败,错误信息：" + packageParams.get("err_code"));
				resXml = "<xml>"
						+ "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>"
						+ "</xml> ";
			}
			PrintWriter out = response.getWriter();
			out.write(resXml);
			out.flush();
			out.close();
		} else {
			log.info("通知签名验证失败");
		}
	}

	/**
	 * 查询订单状态
	 */
	@RequestMapping(value = "/wchatGetOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> wchatQueryOrder(@RequestBody WchatPayDto dto)
			throws Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		String out_trade_no=dto.getOrderNo();
		if(out_trade_no.contains("PLUS")){//plus会员
			PhiPlusMemberOrderDto orderDto=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
			if(orderDto!=null){
				if("1".equals(orderDto.getIsPay())){
					returnMap.put("msg", "成功");
					returnMap.put("type", "SUCCESS");
				}else{
					returnMap.put("msg", "失败");
					returnMap.put("type", "ERROR");
				}
			}else{
				returnMap.put("msg", "失败");
				returnMap.put("type", "ERROR");
			}
		}else{
			PhiOrderDto orderDto=phiOrderService.findPhiOrderinfoByOrderNo(out_trade_no);
			if(orderDto!=null){
				if("1".equals(orderDto.getIsclose())){
					returnMap.put("msg", "成功");
					returnMap.put("type", "SUCCESS");
				}else{
					returnMap.put("msg", "失败");
					returnMap.put("type", "ERROR");
				}
			}else{
				returnMap.put("msg", "失败");
				returnMap.put("type", "ERROR");
			}
		}
		return returnMap;
	}

	/**
	 * 微信APP支付
	 */
	@RequestMapping(value = "/wchatAppPay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> weixin_app_pay(@RequestBody WchatPayDto dto)
			throws Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		String out_trade_no=dto.getOrderNo();
		String price="";
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
					returnMap.put("msg", "订单号已支付成功");
					returnMap.put("type", "ERROR");
					return returnMap;
				}
			}
			PhiPlusGradeDto dto1=phiPlusGradeService.findPhiPlusGradeByPlusCode("plus_399");
			if(dto1!=null){
				price=dto1.getRechargeMoney();
			}else{
				returnMap.put("type", "ERROR");
				returnMap.put("msg", "PLUS会员数据不存在");
				return returnMap;
			}
		}else{
			PhiOrderDto orderDto=phiOrderService.findPhiOrderinfoByOrderNo(out_trade_no);
			if(orderDto!=null){
				if("1".equals(orderDto.getIsclose())){
					returnMap.put("type", "ERROR");
					returnMap.put("msg", "订单号已支付成功");
					return returnMap;
				}else{
					price=String.valueOf(orderDto.getMoney());
				}
			}else{
				returnMap.put("type", "ERROR");
				returnMap.put("msg", "订单号不存在");
				return returnMap;
			}
		}
		//Map<String, String> returnMap = new HashMap<String, String>();
		
		// 账号信息
		String appid = PayConfigUtil.APP_SHARE_ID; // appid
		String mch_id = PayConfigUtil.APP_MCH_ID; // 商业号
		String key = PayConfigUtil.APP_API_KEY; // key

		String currTime = PayCommonUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = PayCommonUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;

		String order_price = dto.getOrderPrice(); // 价格 注意：价格的单位是分
		//String order_price ="1"; 
		String body = "斐讯积分商城"; // 商品名称
		//String out_trade_no = dto.getOrderNo(); // 订单号

		// 获取发起电脑 ip
		String spbill_create_ip = PayConfigUtil.CREATE_IP;
		// 回调接口
		String notify_url = PayConfigUtil.APP_NOTIFY_URL;
		String trade_type = "APP";

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("device_info", "WEB");
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", PayCommonUtil.getMoney(price));
		//packageParams.put("total_fee", order_price);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
		packageParams.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		System.out.println(requestXML);
		String resXml = HttpUtil
				.postData(PayConfigUtil.UFDODER_URL, requestXML);
		Map map = XMLUtil.doXMLParse(resXml);
		String return_code = (String) map.get("return_code");
		String result_code = (String) map.get("result_code");
		if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
			String prepay_id = (String) map.get("prepay_id");
			SortedMap<Object, Object> packageParams1 = new TreeMap<Object, Object>();
			String timeStamp = String.valueOf(new Date().getTime() / 1000);
			returnMap.put("appid", appid);
			returnMap.put("partnerid",mch_id );
			returnMap.put("prepayid",prepay_id );
			returnMap.put("package", "Sign=WXPay");
			returnMap.put("noncestr",nonce_str );
//			returnMap.put("sign",sign);
			returnMap.put("timestamp", timeStamp);
			
//			packageParams1.put("appid", "wxad30bea38e0cd2a1");
//			packageParams1.put("partnerid","1497336112" );
//			packageParams1.put("prepayid","wx20180227142126f1470880bb0276608255" );
//			packageParams1.put("package", "Sign=WXPay");
//			packageParams1.put("noncestr","D6EBE89733D5C3EC47EDC3ABAEED604C" );
//			packageParams1.put("timestamp","1519712489");
			packageParams1.put("appid", appid);
			packageParams1.put("partnerid",mch_id );
			packageParams1.put("prepayid",prepay_id );
			packageParams1.put("package", "Sign=WXPay");
			packageParams1.put("noncestr",nonce_str );
//			packageParams1.put("sign",sign);
			packageParams1.put("timestamp", timeStamp);
			String sign2=PayCommonUtil.createSign("UTF-8", packageParams1, key);
			returnMap.put("sign",sign2);
			returnMap.put("msg", "成功");
			returnMap.put("type", "SUCCESS");
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
			log.error(resXml);
		} else {
			returnMap.put("msg", "失败");
			returnMap.put("type", "ERROR");
		}
		return returnMap;
	}

	/**
	 *  微信APP支付
	 */
	@RequestMapping(value = "/wchatAppNotice", method = RequestMethod.POST)
	public void wchatAppNotice(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		        // 读取参数
				InputStream inputStream;
				StringBuffer sb = new StringBuffer();
				inputStream = request.getInputStream();
				String s;
				BufferedReader in = new BufferedReader(new InputStreamReader(
						inputStream, "UTF-8"));
				while ((s = in.readLine()) != null) {
					sb.append(s);
				}
				in.close();
				inputStream.close();
				// 解析xml成map
				Map<String, String> m = new HashMap<String, String>();
				m = XMLUtil.doXMLParse(sb.toString());

				// 过滤空 设置 TreeMap
				SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
				Iterator it = m.keySet().iterator();
				while (it.hasNext()) {
					String parameter = (String) it.next();
					String parameterValue = m.get(parameter);

					String v = "";
					if (null != parameterValue) {
						v = parameterValue.trim();
					}
					packageParams.put(parameter, v);
				}
				String out_trade_no = (String) packageParams.get("out_trade_no");
				String transaction_id = (String) packageParams.get("transaction_id");
				// 账号信息
				String key = PayConfigUtil.APP_API_KEY; // key
				log.info("{}", packageParams);
				log.error("callbak.....");
				// 判断签名是否正确
				if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
					// ------------------------------
					// 处理业务开始
					// ------------------------------
					String resXml = "";
					if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
						log.error("callbak.....success");
						// 这里是支付成功
						if(out_trade_no.contains("PLUS")){//plus会员
							log.error("callbak.....success....plus");
							PhiPlusMemberOrderDto orderDto=phiPlusMemberOrderService.getphiPlusMemberOrder(out_trade_no);
							if(orderDto!=null){
								if("0".equals(orderDto.getIsPay())){//订单号已支付
									orderDto.setIsPay("1");
									orderDto.setPayTime(new Date());
									orderDto.setTransactionId(transaction_id);
									orderDto.setPayType("1");
									phiPlusMemberOrderService.saveOrUpdatePhiPlusMemberOrder(orderDto);
//								    phiMemberService.OpenPhimember(orderDto.getMemberId(), orderDto.getPlusCode());
									openPlusMemberService.openPlusMember(orderDto.getMemberId(), orderDto.getPlusCode());//重构的新方法
								}else{
									log.info("PLUS会员已支付");
								}
							}
						}else{//积分+现金  购物
							log.error("callbak.....success...plus...++++");
							PhiOrderDto orderDto=phiOrderService.findPhiOrderinfoByOrderNo(out_trade_no);
							if(orderDto!=null){
								if("1".equals(orderDto.getIsclose())){
									log.info("订单号已支付");
								}else{
									phiOrderService.editOrderAfterPayByOrderNo(out_trade_no,"1",transaction_id);
								}
							}
						}
						
						log.info("支付成功");
						// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
						resXml = "<xml>"
								+ "<return_code><![CDATA[SUCCESS]]></return_code>"
								+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

					} else {
						log.error("callbak.....fail");
						log.info("支付失败,错误信息：" + packageParams.get("err_code"));
						resXml = "<xml>"
								+ "<return_code><![CDATA[FAIL]]></return_code>"
								+ "<return_msg><![CDATA[报文为空]]></return_msg>"
								+ "</xml> ";
					}
					// ------------------------------
					// 处理业务完毕
					// ------------------------------
					PrintWriter out = response.getWriter();
					out.write(resXml);
					out.flush();
					out.close();
				} else {  
					log.error("callbak.....sign....fail");
					log.info("通知签名验证失败");
				}

	}
	
	@RequestMapping(value = "/wchatPayTest")
	@ResponseBody
	public Map<String, String> weixin_test_pay()
			throws Exception {
		WchatPayDto dto=new WchatPayDto();
		dto.setOrderNo("PLUS589686454252556");
		dto.setOrderPrice("1");
		dto.setMemberId(new Long(5));
		dto.setPlusCode("plus_399");
		Map<String,String> map=weixin_pay(dto);
		return map;
	}
	
	public static void main(String[] args) {
	    WchatPayAction action =new WchatPayAction();
		WchatPayDto dto=new WchatPayDto();
		try {
			dto.setOrderNo("PLUS2017081515254588898877378");
			dto.setOrderPrice("1");
			dto.setMemberId(new Long(5));
			dto.setPlusCode("plus_399");
			Map<String,String> map=action.weixin_pay(dto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
