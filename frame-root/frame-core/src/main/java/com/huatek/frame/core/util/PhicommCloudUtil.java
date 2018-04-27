package com.huatek.frame.core.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 
* @ClassName: FeiYunCloudUtil 
* @Description: 斐讯云对接
* @author eden_sun
* @date Jan 20, 2018 3:04:00 PM 
*
 */
public class PhicommCloudUtil {
	private final static String PHICOMMURL = PropertyUtil.getConfigValue("PHICOMMURL")+CommonConstants.ACCOUNTURL;
	public CloudMemberVO getMemeberInfo(String token){
		Map<String,String> map=new HashMap<String,String>();
//		 "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMjMxMjUzIiwiY29kZSI6ImZlaXh1bioxMjMuU0hfMjc5MTUwMyIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJpc3MiOiJQaGljb21tIiwibmJmIjoxNTE2NDE4NDAwLCJleHAiOjE1MTY0ODMyMDAsInJlZnJlc2hUaW1lIjoiMjAxOC0wMS0yMCAxNzoyMDowMCJ9.JM0bzlFVTFmNFQTuiC94WmRUYp5A91SDfhgdhyP6Jyc"
		map.put("Authorization",token);
		String response = "";
			try {
				response = HttpsUtils.httpsGet(PHICOMMURL, map);
			   return	new Gson().fromJson(response, CloudMemberVO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new CloudMemberVO();
	}
	
	public RefreshTokenVO refreshToken(String refreshToken){
		AuthorizationVo authorizationVo = getAuthorizationCode();
		String url=PropertyUtil.getConfigValue("PHICOMMURL")+CommonConstants.TOKEN+"?authorizationcode="+authorizationVo.getAuthorizationcode()+"&grant_type=refresh_token"; 
		Map<String,String> map=new HashMap<String,String>();
		map.put("Authorization",refreshToken);
		String response = "";
			try {
				response = HttpsUtils.httpsGet(url, map);
			   return	new Gson().fromJson(response, RefreshTokenVO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new RefreshTokenVO();
	}
	
	public AuthorizationVo getAuthorizationCode(){
      String url=PropertyUtil.getConfigValue("PHICOMMURL")+CommonConstants.AUTHORIZATIONURL+"?client_id="+CommonConstants.CLIENT_ID+"&client_secret="+CommonConstants.CLIENT_SECRET+"&response_type=code&scope="+PhiCommApiScope.read; 
		String response = "";
			try {
				response = HttpsUtils.httpsGet(url, null);
				 return	new Gson().fromJson(response, AuthorizationVo.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new AuthorizationVo();
	}
	
	public class CloudMemberVO {
		private String token_status;
		private CloudMember data;
		private String error;
		public String getToken_status() {
			return token_status;
		}
		public void setToken_status(String token_status) {
			this.token_status = token_status;
		}
		public CloudMember getData() {
			return data;
		}
		public void setData(CloudMember data) {
			this.data = data;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
	}
	/**{
		"token_status": "0",
		"data": {
			"birthday": "1910-9-7",
			"img": "https://portraitsymtest.phicomm.com/pic/avatar/uvblotuTohjRuRAV5fbrnAjO5AYHuArRqurJtCfDoe4NmTaVwwjw2wNGswRIsAfwtTFGy0eN2hbLyS8w3gRxs7==",
			"address": "",
			"sex": "1",
			"phonenumber": "17621375637",
			"weight": "",
			"register_time": "2017-11-29 16:05:29.0",
			"realname": "jackma1233333",
			"zipcode": "",
			"uid": "1231253",
			"zone": "东城区",
			"nickname": "谢谢你的2222222",
			"job": "",
			"age": "",
			"height": ""
		},
		"error": "0"
	}**/
	
	public class CloudMember implements java.io.Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String birthday;
		private String img;
		private String sex;
		private String phonenumber;
		private String weight;
		private String register_time;
		private String realname;
		private String zipcode;
		private String uid;
		private String zone;
		private String nickname;
		private String job;
		private String age;
		private String height;
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		public String getWeight() {
			return weight;
		}
		public void setWeight(String weight) {
			this.weight = weight;
		}
		public String getRegister_time() {
			return register_time;
		}
		public void setRegister_time(String register_time) {
			this.register_time = register_time;
		}
		public String getRealname() {
			return realname;
		}
		public void setRealname(String realname) {
			this.realname = realname;
		}
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getZone() {
			return zone;
		}
		public void setZone(String zone) {
			this.zone = zone;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getHeight() {
			return height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
		
	}
	
	/**
	 * 
	 * 
	 * {"authorizationcode":"feixun*123.SH_2791503","error":"0"}
	* @ClassName: AuthorizationVo 
	* @Description: TODO(这里用一句话描述这个类的作用) 
	* @author eden_sun
	* @date Jan 20, 2018 4:13:22 PM 
	*
	 */
	public class AuthorizationVo{
		private String authorizationcode;
		private String error;
		public String getAuthorizationcode() {
			return authorizationcode;
		}
		public void setAuthorizationcode(String authorizationcode) {
			this.authorizationcode = authorizationcode;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
	}
	
	public class RefreshTokenVO{
		private String  scope;
		private String  token_type;
		private String  uid;
		private String  access_token;
		private String  access_token_expire;
		private String  error;
		private String  token_status;
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public String getToken_type() {
			return token_type;
		}
		public void setToken_type(String token_type) {
			this.token_type = token_type;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getAccess_token() {
			return access_token;
		}
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		public String getAccess_token_expire() {
			return access_token_expire;
		}
		public void setAccess_token_expire(String access_token_expire) {
			this.access_token_expire = access_token_expire;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public String getToken_status() {
			return token_status;
		}
		public void setToken_status(String token_status) {
			this.token_status = token_status;
		}

	}
	
	
	
	public enum PhiCommApiScope {
		read,write
	}
}
