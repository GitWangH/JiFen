package com.huatek.frame.handle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class ActionLogger {
	private ThreadLocal<Long> actionExecutePoint = new ThreadLocal<Long>();
	private static final Logger logger = Logger.getLogger(ActionLogger.class);

	/**
	 * 在方法开始前纪录
	 * 
	 * @param jp
	 */
	@Before ("execution(* com.huatek..*.*dao..*.*(..)) or execution(* com.huatek..*.service..*.*(..))")
	public void logInfo(JoinPoint jp) {
		// String className = jp.getThis().toString();
		// String methodName = jp.getSignature().getName(); //获得方法名
		/***
		 * 记录当前时间.
		 */
		//if (logger.isDebugEnabled()) {
			actionExecutePoint.set(System.currentTimeMillis());
		//}
	}

	/**
	 * 在方法结束后纪录
	 * 
	 * @param jp
	 */
	@After ("execution(* com.huatek..*.*dao..*.*(..)) or execution(* com.huatek..*.service..*.*(..))")
	public void logInfoAfter(JoinPoint jp) {
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName(); // 获得方法名
		long userTime = System.currentTimeMillis() - actionExecutePoint.get() ;
		if (userTime  > 1000 ) {
			ActionLogger.logger.warn( className + "方法" + methodName
					+ " 执行超过一秒钟!——"+userTime);
		} else if (userTime > 500 && logger.isInfoEnabled()) {
			ActionLogger.logger.info(className + "方法" + methodName
					+ " 执行超过半秒钟!——"+userTime);
		} else if (userTime > 100 && logger.isDebugEnabled()) {
			ActionLogger.logger.debug(className + "方法" + methodName
					+ " 执行超过1/5秒钟!——"+userTime);
		}
	
	}
}
