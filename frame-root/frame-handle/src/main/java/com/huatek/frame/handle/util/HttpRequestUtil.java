package com.huatek.frame.handle.util;

import javax.servlet.http.HttpServletRequest;
public class HttpRequestUtil {

	public static String getCallPath(HttpServletRequest request){
		String path = request.getRequestURI();
		int position = 0;
		if(path.indexOf("/api/")<0){
			path = path.substring(path.indexOf(request.getContextPath())+request.getContextPath().length());
			position = path.indexOf("/",1);
		}else{
			path = path.substring(path.indexOf("/api/")+4);
			position = path.indexOf("/",1);
			position = path.indexOf("/",position+1);
		}
		/***
		 * URL路径必须唯一标识.
		 * /module/action/
		 */
		if(position<0){
			position = path.length();
		}
		return path.substring(0, position);
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
}
