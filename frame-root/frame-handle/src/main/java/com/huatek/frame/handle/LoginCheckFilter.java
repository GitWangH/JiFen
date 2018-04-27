package com.huatek.frame.handle;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huatek.frame.authority.util.ClientInfoBean;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.util.MD5Util;
import com.huatek.frame.core.util.PhicommCloudUtil;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMemberVO;
import com.huatek.frame.handle.util.MemcacheManager;
import com.huatek.frame.session.data.RoleInfo;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;


public class LoginCheckFilter implements Filter {
//	private Logger log = LoggerFactory
//			.getLogger(LoginCheckFilter.class);
	private static final Gson GSON = new GsonBuilder().setDateFormat(
			"yyyy-MM-dd HH:mm:ss").create();
	protected ApplicationContext applicationContext;
	private CloudMember user;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse hResponse = (HttpServletResponse) response;
		hResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		HttpServletRequest hRequest = (HttpServletRequest) request;
		String methed = hRequest.getMethod();
		
		/***
		 * 获取菜单Id的Cookie.
		 */
		Long menuId = hRequest.getHeader("menuId")==null?null:Long.valueOf(hRequest.getHeader("menuId"));
		String path = hRequest.getRequestURI();
		
		
		
		
//		log.debug("request path: "+path);
		if (path.indexOf("/api/app/login") > 0 ||path.indexOf("/api/") < 0 || path.indexOf("/api/ping") > 0 || path.indexOf("/pda/service") > 0 || path.indexOf("/api/public") > 0||path.indexOf("/api/v1") > 0 ) {
			UserInfo user = (UserInfo) hRequest.getSession().getAttribute(
					SessionKey.currentUser);
			if(user!=null){
				ThreadLocalClient.get().setOperator(user);
				
			}
			chain.doFilter(request, response);
			ThreadLocalClient.remove();
			return;
		}
		// 公共方法不判断用户是否有权限
		if (path.indexOf("doNotFilter") > 0) {
			chain.doFilter(request, response);
			return;
		}
		
//		
//		 String frontEnd=hRequest.getParameter("frontEnd");
//			//判断是否为前端
//			if(frontEnd!=null&&frontEnd.equals("1")){
//				String sessionId=hRequest.getHeader("jsessionId");
//				if(sessionId!=null){
//					CloudMember member = MemcacheManager.getFrontEndMemCacheInfo(sessionId);
//					if(member!=null){
//						ThreadLocalClient.get().setCloudMember(member);
//						chain.doFilter(request, response);
//						ThreadLocalClient.remove();
//					}else{
//						String jsonString = GSON.toJson(ResponseMessage.danger("MemCache中未找到匹配信息"));
//						hResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//						hResponse.getWriter().print(jsonString);
//						return;
//					}
//				}else{
//					String jsonString = GSON.toJson(ResponseMessage.danger("前端sessionId为空"));
//					hResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//					hResponse.getWriter().print(jsonString);
//					return;
//				}
//			}
//		String frontEndtest=hRequest.getParameter("frontEndtest");
		
		 String frontEnd=hRequest.getParameter("frontEnd");
		 System.out.println(path);
//		 if(frontEndtest!=null&&frontEndtest.equals("1")){
			//判断是否为前端
			if(frontEnd!=null&&frontEnd.equals("1")){
				String access_token=hRequest.getHeader("accesstoken");
				String timestamp=hRequest.getHeader("timestamp");
				String sign=hRequest.getHeader("sign");
//				log.error("--------------------------------------------------");
//				log.error(access_token);
//				log.error(timestamp);
//				log.error(sign);
//				String access_token1 = hRequest.getHeader("access_token");
				//如果header中取不到，则从参数列表中获取
				if(StringUtils.isEmpty(access_token)){
					access_token = hRequest.getParameter("accesstoken");
					if(StringUtils.isEmpty(access_token)){
						access_token = hRequest.getParameter("access_token");//辰商传的参数名为 access_token
					}
				}
				if(StringUtils.isEmpty(timestamp)){
					timestamp = hRequest.getParameter("timestamp");
				}
				if(StringUtils.isEmpty(sign)){
					sign = hRequest.getParameter("sign");
				}
				
				if(access_token!=null&&timestamp!=null&&sign!=null){
					access_token=java.net.URLDecoder.decode(access_token,"UTF-8");
//					log.error("----------------------222222222222222222222----------------------------");
//					log.error(access_token);
					String realtoken = MD5Util.getSignature(sign, access_token, timestamp);	
//					log.error("--------------------------------------------------");
//					log.error(realtoken);
					//验签成功
					if(realtoken!=null&&!realtoken.equals("")){
						CloudMember member = MemcacheManager.getFrontEndMemCacheInfo(MD5Util.encodeMD5(realtoken));
						if(member!=null){
//							log.error("--------------------------------------------------");
//							log.error("222222222222222222222222222222222");
							hRequest.getSession().setAttribute(SessionKey.currentMember, member);
							ThreadLocalClient.get().setCloudMember(member);
							chain.doFilter(request, response);
							ThreadLocalClient.remove();
							return;
						}else{
//							log.error("--------------------------------------------------");
//							log.error("1111111111111111111111");
			    			PhicommCloudUtil pcu=new PhicommCloudUtil();
			    			CloudMemberVO cloudMemberVO = pcu.getMemeberInfo(realtoken);
			    			String status = cloudMemberVO.getToken_status();
			    			TokenMessage tokenMessage;
//			    			log.error("--------------------------------------------------");
//							log.error(status);
							if(status.equals("0")){
								//获取云账户信息
								CloudMember cloudMember = cloudMemberVO.getData();
								hRequest.getSession().setAttribute(SessionKey.currentMember, cloudMember);
								String token=MD5Util.encodeMD5(realtoken);
								MemcacheManager.putFrontEndMemCache(token, cloudMember);
								ThreadLocalClient.get().setCloudMember(cloudMember);
								chain.doFilter(request, response);
								ThreadLocalClient.remove();
								return;
							}else{
								tokenMessage=new TokenMessage("-1","301","用户状态异常");
								String jsonString = GSON.toJson(tokenMessage);
								hResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
								hResponse.getWriter().print(jsonString);
								return;
							}
							
						}
					}else{
						TokenMessage tokenMessage=new TokenMessage("-1","302","验签失败");
						String jsonString = GSON.toJson(tokenMessage);
						hResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						hResponse.getWriter().print(jsonString);
						return;
					}
				}else{
					TokenMessage tokenMessage=new TokenMessage("-1","303","请求头参数不全");
					String jsonString = GSON.toJson(tokenMessage);
					hResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					hResponse.getWriter().print(jsonString);
					return;
				}
			}
		
//		 }
		
		//解决sessionId的大小写敏感
		String sessionKey="sessionId";
		Enumeration<String> e = hRequest.getHeaderNames();
		while(e.hasMoreElements()){
			 String  key = e.nextElement();
			 if(key.equalsIgnoreCase("sessionId")){
				 sessionKey=key;
			 }
		}
		if(methed.equals("OPTIONS")){
			UserInfo user = new UserInfo();
			user.setAcctName("admin");
			user.setCurrProId(new Long(1));
			user.setFromApp(true);
			user.setId(new Long(1));
			user.setOrgId(new Long(1));
			user.setOrgName("系统");
			user.setOrgShortName("系统");
			user.setOrgType("1");	
			user.setUserName("系统管理员");
			RoleInfo[] roleCollectionInfos = new RoleInfo[1];
			RoleInfo role = new RoleInfo();
			role.setId(new Long(1));
			role.setCode("R_0001");
			role.setName("系统管理员");
			roleCollectionInfos[0] = role;
			user.setRoleInfos(roleCollectionInfos);
			keepThreadUser(user, menuId);
			chain.doFilter(request, response);
			ThreadLocalClient.remove();
		}else if(StringUtils.isNoneBlank(hRequest.getHeader(sessionKey))){
//			log.error("--------------------------------------------");
//			log.error(request.getParameter("sessionId"));
//			log.error("+++++++++++++++++++++++++++++++++++++++++++++");
			if(hRequest.getHeader(sessionKey) != null && hRequest.getHeader(sessionKey).equals("app")){
				UserInfo user = new UserInfo();
				user.setAcctName("admin");
				user.setCurrProId(new Long(1));
				user.setFromApp(true);
				user.setId(new Long(1));
				user.setOrgId(new Long(1));
				user.setOrgName("系统");
				user.setOrgShortName("系统");
				user.setOrgType("1");	
				user.setUserName("系统管理员");
				RoleInfo[] roleCollectionInfos = new RoleInfo[1];
				RoleInfo role = new RoleInfo();
				role.setId(new Long(1));
				role.setCode("R_0001");
				role.setName("系统管理员");
				roleCollectionInfos[0] = role;
				user.setRoleInfos(roleCollectionInfos);
				keepThreadUser(user, menuId);
				chain.doFilter(request, response);
				ThreadLocalClient.remove();
			}else {
				hRequest.getSession().setAttribute(SessionKey.currentMenuId, menuId);
				UserInfo user = MemcacheManager.getMemCacheInfo(hRequest.getHeader(sessionKey));
				keepThreadUser(user, menuId);
				chain.doFilter(request, response);
				ThreadLocalClient.remove();
			}
		}else if (hRequest.getSession().getAttribute(SessionKey.currentUser) != null) {
			UserInfo user = (UserInfo) hRequest.getSession().getAttribute(
					SessionKey.currentUser);
			//保存SessionId以供RPC时调用.
			hRequest.getSession().setAttribute(SessionKey.currentMenuId, menuId);
			

			path = path.substring(path.indexOf("/api/") + 4);
			/***
			 * URL路径必须唯一标识. /module/action/
			 */
			int position = path.indexOf("/", 1);
			position = path.indexOf("/", position + 1);
			if (position < 0) {
				position = path.length();
			}
			path = path.substring(0, position);

			// 判断用户是否有权限
				keepThreadUser(user, menuId);
				chain.doFilter(request, response);
				ThreadLocalClient.remove();
				


		} else {
			// 打印错误信息.
			String jsonString = GSON.toJson(ResponseMessage.danger("用户尚未登录"));
			hResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			hResponse.getWriter().print(jsonString);
		}
	}

	private boolean isAdmin(Long userId) {
		return userId == 1;
	}

	@Override
	public void destroy() {

	}
	

   public static String getIpAddress(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getRemoteAddr(); 
        } 
        return ip; 
    } 
	
	private void keepThreadUser(UserInfo user,Long menuId) {
		ClientInfoBean client = new ClientInfoBean();
		client.setOperator(user);
		if(menuId!=null){
			client.setMenuId(menuId);
		}
		
		ThreadLocalClient.put(client);
	}


	/**
	 * 判断客户端请求是否是Ajax请求.
	 *
	 * @param request
	 *            servlet内置对象.
	 * @return 返回是或否
	 */
	public static boolean isAjaxRequest(final HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return header != null && "XMLHttpRequest".equals(header);
	}
}