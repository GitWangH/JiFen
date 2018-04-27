package com.huatek.frame.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;


import com.huatek.frame.handle.SessionContainer;
//import com.huatek.frame.handle.SessionContainer;
import com.huatek.frame.handle.util.SpringContext;

public class ServletInitConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//System.out.println("ServletInitConfig......");
		
	/*	Dynamic dynamic = servletContext.addServlet("testAutoWired", "com.hisense.frame.config.TestAutoWiredServlet");
/		Dynamic dynamic = servletContext.addServlet("testAutoWired", new com.hisense.frame.config.TestAutoWired());
		
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("/api/test");*/
		servletContext.addListener(SessionContainer.class);
		servletContext.addListener(SpringContext.class);
		
	}

}
