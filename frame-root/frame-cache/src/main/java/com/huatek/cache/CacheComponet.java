package com.huatek.cache;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.huatek.cache.annotation.CacheClass;
import com.huatek.cache.annotation.CacheFlush;
import com.huatek.cache.annotation.CacheMethod;
import com.huatek.cache.model.CacheDataWrap;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.util.PropertyUtil;
import com.huatek.rpc.client.service.MasterNodeService;

/**
 * 拦截缓存.
 * 
 * @author Winner pan
 */
//@Aspect
@Component
public class CacheComponet {
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private RpcProxy rpcProxy;
	
	private static final Logger logger = Logger.getLogger(CacheComponet.class);
	/***
	 * 方法参数名获取.
	 */
	private static LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

	@Pointcut("execution(* com.huatek..*.*Impl.*(..))")
	public void cachedPointcut() {

	}

	// 定义切面

	@Around("cachedPointcut()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {
	
		Signature signature = call.getSignature();
		Class<?> factor = null;
		Class<?>[] factors = null;
		String expressId = null;
		/***
		 * 实体类名作为缓存数据.
		 */
		CacheClass factorClass = null;
		if (call.getTarget().getClass().isAnnotationPresent(CacheClass.class)) {
			factorClass = call.getTarget().getClass()
					.getAnnotation(CacheClass.class);
			factor = factorClass.factor();
		}
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		String className = call.getTarget().getClass().getName();
		Boolean isUseCache = false;

		if (method.isAnnotationPresent(CacheClass.class)) {
			factorClass = method.getAnnotation(CacheClass.class);
			factor = factorClass.factor();
			isUseCache = true;
		}
		if (method.isAnnotationPresent(CacheMethod.class)) {
			CacheMethod cacheMethod = method.getAnnotation(CacheMethod.class);
			factors = cacheMethod.factors();
			factor = cacheMethod.factor();
			expressId = cacheMethod.id();
			isUseCache = true;
		}
		if (!isUseCache && !method.isAnnotationPresent(CacheFlush.class)) {
			return call.proceed();
		}
		String key = this.getKey(className, method, call.getArgs());
		if (isUseCache) {
			Object result = null;
			CacheDataWrap cacheDataWrap = null;
			/***
			 * 读取缓存，读到缓存直接返回. 读不到缓存执行处理，把结果存放到缓存中，给下次执行时使用.
			 */
			if (!factors[0].equals(String.class)) {
				cacheDataWrap = cacheManager.getCache(factors, key);
				if (cacheDataWrap == null) {
					result = call.proceed();
					cacheDataWrap = new CacheDataWrap(result);
					cacheManager.putCache(factors, key, cacheDataWrap);
				}
			} else if (!expressId.equals("-1")) {
				boolean isSuffix = expressId.startsWith("@return");
				if (isSuffix) {
					cacheDataWrap = cacheManager.getSuffixCache(factor, key);
					if (cacheDataWrap == null) {
						result = call.proceed();
						expressId = "#" + expressId.substring(1);
						String id = this.getExpressValue(expressId, "return", result);
						if(id==null){
							id = "NULL";
						}
						cacheDataWrap = new CacheDataWrap(result);
						cacheManager.putSuffixCache(factor, id, key, cacheDataWrap);
					}

				} else {
					String id = getIdValue(call, expressId);
					if(id==null){
						id = "NULL";
					}
					cacheDataWrap = cacheManager.getCache(factor, id, key);
					if (cacheDataWrap == null) {
						result = call.proceed();
						cacheDataWrap = new CacheDataWrap(result);
						cacheManager.putCache(factor, id, key, cacheDataWrap);
					}
				}
			} else {
				cacheDataWrap = cacheManager.getCache(factor, key);
				if (cacheDataWrap == null) {
					result = call.proceed();
					cacheDataWrap = new CacheDataWrap(result);
					cacheManager.putCache(factor, key, cacheDataWrap);
				}
			}
			return cacheDataWrap.getValue();

		}
		/***
		 * 清空缓存.
		 */
		CacheFlush cacheFlush = method.getAnnotation(CacheFlush.class);
		String systemCode = PropertyUtil.getAppConfigValue("systemType");
		
		factors= cacheFlush.factor();
		expressId = cacheFlush.id();
		String[] factorNames = new String[factors.length];
		for(int i=0; i<factors.length ; i++){
			factorNames[i] = factors[i].getName();
		}
		MasterNodeService masterNodeService = this.rpcProxy.create(MasterNodeService.class);
		FlushNodesCache flushNodesCache = this.rpcProxy.createNotice(FlushNodesCache.class, systemCode);
		if(expressId!=null && !expressId.equals("-1")){
			String id = getIdValue(call, expressId);
			if(!factors[0].equals(String.class)){
				long[] factorVersion = masterNodeService.getFactorNextVersion(factorNames[0]);
				flushNodesCache.clearIdCache(factorNames, id, factorVersion);
			}
		}else{
			if(!factors[0].equals(String.class)){
				long[] version = masterNodeService.getNextVersion(factorNames[0]);
				flushNodesCache.clearNoIdCache(factorNames, version);
			}
		}
		return call.proceed();

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
		return java.net.URLEncoder.encode(sb.append(")").toString(),"UTF8");
	}

	private String getIdValue(ProceedingJoinPoint call, String id) {
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
	}

}
