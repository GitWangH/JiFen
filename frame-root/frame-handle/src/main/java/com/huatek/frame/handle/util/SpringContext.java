package com.huatek.frame.handle.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.core.ApplicationContextFacade;
import org.apache.catalina.core.StandardContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.huatek.frame.core.util.EhcacheManager;

public class SpringContext implements ServletContextListener {

	 private static final String SESSION_CACHE = "sessionCache";
	private static WebApplicationContext springContext;
	 private static Manager manager=null;
	 public static void setSpringContext(WebApplicationContext springContext) {
        SpringContext.springContext = springContext;
    }

    //public static  String APPLCATION_CONTEXT = "";
	 public void contextInitialized(ServletContextEvent event) {
	        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	        //APPLCATION_CONTEXT = event.getServletContext().getContextPath();
	        try{
	        	Class<?> noPluggabilityServletContext = Class.forName("org.apache.catalina.core.StandardContext$NoPluggabilityServletContext");
	        	Field sc = noPluggabilityServletContext.getDeclaredField("sc");
	        	sc.setAccessible(true);
	        	ApplicationContextFacade appFaceContext = (ApplicationContextFacade)sc.get(event.getServletContext());
	        	Field appContextField = ApplicationContextFacade.class.getDeclaredField("context");
	        	appContextField.setAccessible(true);
	        	org.apache.catalina.core.ApplicationContext  appContext = (org.apache.catalina.core.ApplicationContext) 
	    				appContextField.get(appFaceContext);
	    		Field contextField = org.apache.catalina.core.ApplicationContext.class.getDeclaredField("context");
	    			contextField.setAccessible(true);
	    		StandardContext context = (StandardContext)contextField.get(appContext);
	    		manager = context.getManager();
	         }catch(Exception e){
	        	 e.printStackTrace();
	         }
	    }


	    public void contextDestroyed(ServletContextEvent event) {
	    	springContext = null;
	    	//APPLCATION_CONTEXT = null;
	    }

	    public static ApplicationContext getApplicationContext() {
	        return springContext;
	    }

	    public static Object getBean(String beanName){
	    	return springContext.getBean(beanName);
	    }
	    
	    public static HttpSession getSession(String sessionId){
	    	if(manager == null){
	    		 return null;
	    	}
	    	try{
	    		HttpSession session = (HttpSession)EhcacheManager.getCache(sessionId, SESSION_CACHE);
		    	if(session!=null ){
		    		return session;
		    	}
	    		if(manager.findSession(sessionId) == null){
		    		return null;
		    	}
		    	session = manager.findSession(sessionId).getSession();
		    	EhcacheManager.putCache(sessionId, session, SESSION_CACHE);
		    	return session;
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		return null;
	    	}
	    } 
	    public static List<HttpSession> findSessions(){
	    	List<HttpSession> sessions=new ArrayList<HttpSession>();
	    	if(manager!=null){
	    		for(Session session : manager.findSessions()){
	    			if(session.isValid()){
	    				sessions.add(session.getSession());
	    			}
	    			
	    		}
	    	}
	    	return sessions;
	    }
}

