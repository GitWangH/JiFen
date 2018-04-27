package com.huatek.frame.core.dao;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.huatek.frame.core.ExecuteCache;
import com.huatek.frame.core.util.EhcacheManager;

/**
 * 拦截缓存.
 * 
 * @author Winner pan
 */
@Aspect
@Component
public class ExecuteCacheAop {

	
	private static final Logger logger = Logger.getLogger(ExecuteCacheAop.class);
	/***
	 * 方法参数名获取.
	 */
	//private static LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

	@Pointcut("execution(* com.huatek..*.*Impl.*(..))")
	public void cachedPointcut() {

	}

	// 定义切面

	@Around("cachedPointcut()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {
	
		Signature signature = call.getSignature();
		String cacheName = null;
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method.isAnnotationPresent(ExecuteCache.class)) {
			cacheName = method.getAnnotation(ExecuteCache.class).value();

		}
		if(cacheName==null){
			return call.proceed();
		}
		String className = call.getTarget().getClass().getName();
		String key = this.getKey(className, method, call.getArgs());
		Object result = EhcacheManager.getCache(key, cacheName);
		if(result==null){
			result = call.proceed();
			EhcacheManager.putCache(key, result, cacheName);
		}
		return result;

	}

	/**
	 * 组装key值
	 * 
	 * @param method
	 * @param args
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private String getKey(String className, Method method, Object[] args) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer(className);
		String methodName = method.getName();
		sb.append(".").append(methodName).append("(");
		if (args != null && args.length > 0) {
			boolean isFirst = true;
			for (Object arg : args) {
				if(arg == null){
					arg = "Null";
				}
				if (isFirst) {
					sb.append(arg);
					isFirst = false;
				} else {
					sb.append(",").append(arg);
				}

			}
		}
		return sb.append(")").toString();
	}

	/*private String getIdValue(ProceedingJoinPoint call, String id) {
		if (!id.startsWith("#")) {
			return id;
		}
		int length = id.indexOf(".");
		String paramName = null;
		if(length>0){
			paramName = id.substring(1, length);
		}else{
			paramName = id.substring(1);
		}
		
		MethodSignature methodSignature = (MethodSignature) call.getSignature();
		Method method = methodSignature.getMethod();
		int position = 0;
		boolean configParamOk = false;
		for (String param : discoverer.getParameterNames(method)) {
			if (param.equals(paramName)) {
				configParamOk = true;
				break;
			} else {
				position++;
			}
		}
		if (!configParamOk) {
			throw new RuntimeException("类:" + call.getTarget().getClass().getName() + "方法:"
					+ method.getName() + "注解配置的参数名:" + id + "不存在");
		}
		Object inputParam = call.getArgs()[position];
		if(inputParam == null){
			return null;
		}
		return getExpressValue(id, paramName, inputParam);
	}

	private String getExpressValue(String id, String paramName,
			Object inputParam) {
		if(inputParam == null){
			return null;
		}
		ExpressionParser expressionParser = new SpelExpressionParser();
		Expression expression = expressionParser.parseExpression(id);
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable(paramName, inputParam);
		Object value = expression.getValue(context);
		return value==null?null:value.toString();
	}*/

}
