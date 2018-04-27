package com.huatek.frame.core.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.huatek.frame.core.util.ExpressUtils.ExpressVO;

/**
 * 
* @ClassName: WanJiaJinFuUtil 
* @Description: 万家金服卡券发放
* @author eden_sun
* @date Jan 23, 2018 10:47:16 AM 
*
 */
public class WanJiaJinFuUtil {
	private final static String REG_URL =PropertyUtil.getConfigValue("REG_URL");
	private final static String EXCHANGE_URL =PropertyUtil.getConfigValue("EXCHANGE_URL");
	
	
	/**
	 * 
	 * @author eden  
	 * @date Jan 23, 2018 1:56:38 PM
	 * @desc 注册用户
	 * @param: @param phone-电话号码
	 * @param: @return  
	 * @return: WanJiaJinFuRegVO      
	 * @throws
	 */
	public WanJiaJinFuRegVO regCustomer(String phone){
		String url=REG_URL;
		Map<String,String> map=new HashMap<String,String>();
		map.put("service_name", CommonConstants.REG_SERVICE_NAME);
		map.put("phone",phone);
		map.put("recommendSource",CommonConstants.REG_RECOMMENDSOURCE);
		map.put("godType","1");
		map.put("recommend",CommonConstants.REG_RECOMMEND);
	    String resp = null;
	      try {
	          resp = HttpClientUtil.requestPost(url, map).getResponseContent();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  
		return new Gson().fromJson(resp, WanJiaJinFuRegVO.class);
	}
	
	/**
	 * 
	 * @author eden  
	 * @date Jan 23, 2018 1:55:56 PM
	 * @desc 兑换优惠券接口
	 * @param: mobile-电话号码； presentId-优惠券编码
	 * @return: WanJiaJinFuExchangeVO      
	 * @throws
	 */
	public WanJiaJinFuExchangeVO exchangePresent(String mobile,String presentId,String presentNum){
		
		String url=EXCHANGE_URL;
		Map<String,String> map=new HashMap<String,String>();
		map.put("service_name", CommonConstants.EXCHANGE_SERVICE_NAME);
		map.put("mobile",mobile);
		map.put("presentId",presentId);
		map.put("presentNum",presentNum);
	    String resp = null;
	      try {
	          resp = HttpClientUtil.requestPost(url, map).getResponseContent();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	    WanJiaJinFuExchangeVO  exchangeVO= new Gson().fromJson(resp, WanJiaJinFuExchangeVO.class);
	      if(exchangeVO.getResult().equals("5000")){
	    	  WanJiaJinFuRegVO regVO = regCustomer(mobile);
	    	  if(regVO.getResult().equals("000000")){
	    		  resp = HttpClientUtil.requestPost(url, map).getResponseContent();  
	    	  }
	      }else{
	    	  return exchangeVO;
	      }
	      
		return new Gson().fromJson(resp, WanJiaJinFuExchangeVO.class);
	}

	public class WanJiaJinFuExchangeVO{
		/**返回状态
		 *Rescode：
		 *rescode=0000查询成功 
		 *rescode=1000业务出错  
		 *rescode=2000系统繁忙
		 *rescode=3000网络或服务器异常
		 *rescode=4000请求参数出错*/
		private String result;
		
		/**消息描述
		 *K码发送失败
		 *k码已过发行期
		 *k码已超发行量
		 *K码发送失败
		 * */
		private String resmsgdesc;
		
		//服务名称mbm_exchangePresent_req
		private String service_name;
		
		/**消息描述
		 *1、接口调用成功
		 *2、接口调用失败*/
		private String resultdesc;

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getResmsgdesc() {
			return resmsgdesc;
		}

		public void setResmsgdesc(String resmsgdesc) {
			this.resmsgdesc = resmsgdesc;
		}

		public String getService_name() {
			return service_name;
		}

		public void setService_name(String service_name) {
			this.service_name = service_name;
		}

		public String getResultdesc() {
			return resultdesc;
		}

		public void setResultdesc(String resultdesc) {
			this.resultdesc = resultdesc;
		}
	}
	
	
	public class WanJiaJinFuRegVO{
		
		/*	返回消息码, 如下：
		000000	成功
		100001	失败
		100000	其他错误
		100099	参数错误
		100098	参数为空
		200002 服务未找到
		203015 推荐人不存在*/
		private String result;
		//用户认证令牌
		private String tokenId;
		//用户信息
		private GodInfo god;
		//god.regCustmer
		private String service_name;
		//消息描述
		private String resultdesc;
		
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}

		public String getTokenId() {
			return tokenId;
		}
		public void setTokenId(String tokenId) {
			this.tokenId = tokenId;
		}
		public GodInfo getGod() {
			return god;
		}
		public void setGod(GodInfo god) {
			this.god = god;
		}
		public String getService_name() {
			return service_name;
		}
		public void setService_name(String service_name) {
			this.service_name = service_name;
		}
		public String getResultdesc() {
			return resultdesc;
		}
		public void setResultdesc(String resultdesc) {
			this.resultdesc = resultdesc;
		}
	}
	
	public class GodInfo{
		//证件类型
		private String idType;
		//创建时间
		private String createTime;
		//手机号码
		private String phone;
		
		private String recommendGodUrl;
		private String rdmStr;
		//用户名
		private String aliases;
		private String recommendTypeName;
		//用户类型 0 普通用户,1 为 VIP用户
		private String userType;
		//用户ID
		private String id;
		//邮箱验证标志(1为验证通过，0为未验证)
		private String isValidateEmail;
		//银行卡验证标志(1为验证通过，0为未验证)
		private String isValidateBank;
		//证件验证标志(1为验证通过，0为未验证)
		private String isIDNumber;
		//用户类型(投资人=1,借款人=2,担保公司=3,平台=4,体验标=5)
		private String godType;
		//证件ID
		private String idNumber;
		private String updateTime;
		//用户状态（0 代表正常用户,1为注销用户,2为冻结用户）
		private String status;
		//是否自动投标标志
		private String isAutomaticBid;
		//手机验证标志(1为验证通过，0为未验证)
		private String isValidatePhone;
		//weiXinId
		private String weiXinId;
		private String recommendGodUrl2;
		//头像图片地址
		private String picture;
		private String reCompany;
		//推荐时间
		private String recommendTime;
		private String riskAssessTypeName;
		private String riskAssessScore;
		//用户邮箱
		private String email;
		//推荐人Id
		private String recommendGodId;
		//推荐类型ID
		private String recommendTypeId;
		private String riskAssessDate;
		//真实姓名
		private String fullName;
		//逻辑删除标识1正常0删除
		private String isValid;
		public String getIdType() {
			return idType;
		}
		public void setIdType(String idType) {
			this.idType = idType;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getRecommendGodUrl() {
			return recommendGodUrl;
		}
		public void setRecommendGodUrl(String recommendGodUrl) {
			this.recommendGodUrl = recommendGodUrl;
		}
		public String getRdmStr() {
			return rdmStr;
		}
		public void setRdmStr(String rdmStr) {
			this.rdmStr = rdmStr;
		}
		public String getAliases() {
			return aliases;
		}
		public void setAliases(String aliases) {
			this.aliases = aliases;
		}
		public String getRecommendTypeName() {
			return recommendTypeName;
		}
		public void setRecommendTypeName(String recommendTypeName) {
			this.recommendTypeName = recommendTypeName;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getIsValidateEmail() {
			return isValidateEmail;
		}
		public void setIsValidateEmail(String isValidateEmail) {
			this.isValidateEmail = isValidateEmail;
		}
		public String getIsValidateBank() {
			return isValidateBank;
		}
		public void setIsValidateBank(String isValidateBank) {
			this.isValidateBank = isValidateBank;
		}
		public String getIsIDNumber() {
			return isIDNumber;
		}
		public void setIsIDNumber(String isIDNumber) {
			this.isIDNumber = isIDNumber;
		}
		public String getGodType() {
			return godType;
		}
		public void setGodType(String godType) {
			this.godType = godType;
		}
		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getIsAutomaticBid() {
			return isAutomaticBid;
		}
		public void setIsAutomaticBid(String isAutomaticBid) {
			this.isAutomaticBid = isAutomaticBid;
		}
		public String getIsValidatePhone() {
			return isValidatePhone;
		}
		public void setIsValidatePhone(String isValidatePhone) {
			this.isValidatePhone = isValidatePhone;
		}
		public String getWeiXinId() {
			return weiXinId;
		}
		public void setWeiXinId(String weiXinId) {
			this.weiXinId = weiXinId;
		}
		public String getRecommendGodUrl2() {
			return recommendGodUrl2;
		}
		public void setRecommendGodUrl2(String recommendGodUrl2) {
			this.recommendGodUrl2 = recommendGodUrl2;
		}
		public String getPicture() {
			return picture;
		}
		public void setPicture(String picture) {
			this.picture = picture;
		}
		public String getReCompany() {
			return reCompany;
		}
		public void setReCompany(String reCompany) {
			this.reCompany = reCompany;
		}
		public String getRecommendTime() {
			return recommendTime;
		}
		public void setRecommendTime(String recommendTime) {
			this.recommendTime = recommendTime;
		}
		public String getRiskAssessTypeName() {
			return riskAssessTypeName;
		}
		public void setRiskAssessTypeName(String riskAssessTypeName) {
			this.riskAssessTypeName = riskAssessTypeName;
		}
		public String getRiskAssessScore() {
			return riskAssessScore;
		}
		public void setRiskAssessScore(String riskAssessScore) {
			this.riskAssessScore = riskAssessScore;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRecommendGodId() {
			return recommendGodId;
		}
		public void setRecommendGodId(String recommendGodId) {
			this.recommendGodId = recommendGodId;
		}
		public String getRecommendTypeId() {
			return recommendTypeId;
		}
		public void setRecommendTypeId(String recommendTypeId) {
			this.recommendTypeId = recommendTypeId;
		}
		public String getRiskAssessDate() {
			return riskAssessDate;
		}
		public void setRiskAssessDate(String riskAssessDate) {
			this.riskAssessDate = riskAssessDate;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getIsValid() {
			return isValid;
		}
		public void setIsValid(String isValid) {
			this.isValid = isValid;
		}
		
	}
	
}
