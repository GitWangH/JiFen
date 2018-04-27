package com.huatek.file.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.huatek.frame.authority.util.ClientInfoBean;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.dao.ThreadLocalSession;
import com.huatek.frame.handle.util.MemcacheManager;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;

public class RequestFilter implements Filter {
	Logger logger = Logger.getLogger(RequestFilter.class);
	private String allowOrigin = null;

	private String allowMethods = null;

	@Override
	public void destroy() {
		this.allowOrigin = null;
		this.allowMethods = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
			throws IOException, ServletException {
		
		
		HttpServletRequest hRequest = (HttpServletRequest) req;
		Long menuId = hRequest.getHeader("menuId")==null?null:Long.valueOf(hRequest.getHeader("menuId"));
		String sessionKey="sessionId";
		Enumeration<String> e = hRequest.getHeaderNames();
		while(e.hasMoreElements()){
			 String  key = e.nextElement();
			 if(key.equalsIgnoreCase("sessionId")){
				 sessionKey=key;
			 }
		}
		if(StringUtils.isNoneBlank(hRequest.getHeader(sessionKey))){
			hRequest.getSession().setAttribute(SessionKey.currentMenuId, menuId);
			UserInfo user = MemcacheManager.getMemCacheInfo(hRequest.getHeader(sessionKey));
			ClientInfoBean client = new ClientInfoBean();
			client.setOperator(user);
			client.setMenuId(menuId);
			ThreadLocalClient.put(client);
			
		}else if (hRequest.getSession().getAttribute(SessionKey.currentUser) != null) {
			UserInfo user = (UserInfo) hRequest.getSession().getAttribute( SessionKey.currentUser);
			ClientInfoBean client = new ClientInfoBean();
			client.setOperator(user);
			client.setMenuId(menuId);;
			ThreadLocalClient.put(client);
		}
		

		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", allowOrigin);
		response.setHeader("Access-Control-Allow-Methods", allowMethods);
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try{
			chain.doFilter(req, res);
		}finally{
			ThreadLocalClient.remove();
			/*if(ThreadLocalSession.get()!=null && ThreadLocalSession.get().size()>0){
				int size = ThreadLocalSession.remove();
				logger.info("release database connnection "+size);
			}*/
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		this.allowOrigin = config.getInitParameter("allowOrigin");
		this.allowMethods = config.getInitParameter("allowMethods");
	}

}