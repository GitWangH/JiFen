package com.huatek.busi.api.base;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.BusiUrlConstants;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.util.MD5Util;
import com.huatek.frame.core.util.PhicommCloudUtil;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMemberVO;
import com.huatek.frame.handle.util.MemcacheManager;
import com.huatek.frame.session.util.SessionKey;


@RestController
@RequestMapping(value =BusiUrlConstants.BUSIVALIDATEVMCTOKEN_API)
public class BusiValidateVMCToken {

    private static final Logger log = LoggerFactory .getLogger(BusiValidateVMCToken.class);
   
    
    
    @RequestMapping(value = "/doNotFilter/validate",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<TokenMessage> validateToken(String access_token,String timestamp,String sign,HttpServletRequest request)  {
    	TokenMessage tokenMessage = null;
    	if(access_token!=null&&timestamp!=null&&sign!=null){
    		String decode_token="";
    		try {
    			decode_token=java.net.URLDecoder.decode(access_token,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		String realtoken = MD5Util.getSignature(sign, decode_token, timestamp);	
    		if(realtoken!=null){
    			PhicommCloudUtil pcu=new PhicommCloudUtil();
    			CloudMemberVO cloudMemberVO = pcu.getMemeberInfo(realtoken);
    			String status = cloudMemberVO.getToken_status();
    			if(status.equals("0")){
					//获取云账户信息
					CloudMember cloudMember = cloudMemberVO.getData();
					request.getSession().setAttribute(SessionKey.currentMember, cloudMember);
					String token=MD5Util.getSignature(realtoken);
					tokenMessage=new TokenMessage(token,"200","token获取正常");
					MemcacheManager.putFrontEndMemCache(token, cloudMember);
					ThreadLocalClient.get().setCloudMember(cloudMember);
					ThreadLocalClient.remove();
				}else{
					tokenMessage=new TokenMessage("-1","301","用户状态异常");
				}
    		}else{
    			tokenMessage=new TokenMessage("-1","302","验签未通过");
    		}
    	}else{
    		tokenMessage=new TokenMessage("-1","300","所有参数不能为空");
    	}
        return new ResponseEntity<>(tokenMessage, HttpStatus.OK);
    }
    
    public class TokenMessage{
    	private String token;
    	private String status;
    	private String desc;
    	public TokenMessage(String token,String status,String desc){
    		this.token=token;
    		this.status=status;
    		this.desc=desc;
    		
    	}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
    	
    }
    
    
}
