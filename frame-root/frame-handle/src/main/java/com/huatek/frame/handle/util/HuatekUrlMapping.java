package com.huatek.frame.handle.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

/***
 * 本类将嵌入spring的映射处理类中，协助该类直接从Url路径获取到
 * 执行的映射.
 * 加载类的时候，如果发现映射路径中包含有大括号，把大括号以及其中内容统一替换成
 * 下划线。如果映射路径最初开始的下划线右方不是大括号的，也需要统一替换成大括号，
 * 降低数据匹配的复杂度。如果映射没有标明是post或是get,直接生成两个key.
 * 如果一个key已经存在，打出警告信息，让开发人员修改映射信息.
 * 
 * 请求映射时，先直接获取映射是否能够得到。不能得到判断是否有数字，如果有数字
 * 从数字的地方直接替换成下划线。没有数字，从右开始替换。
 * 
 * 
 * @author winner pan.
 *
 */
public class HuatekUrlMapping {
	private static final Logger logger = Logger.getLogger(HuatekUrlMapping.class);
	/**
	 * 存放映射数据.
	 */
	public static final Map<String, List<String>> huatekMappingMap = 
			new ConcurrentHashMap<String, List<String>>();
	/**
	 *把spring的映射信息按照自己的方案保存一个备份.
	 */
	public static void registerMap(RequestMappingInfo info){
		/**
		 * 减少复杂度，不支持一个方法对应有多个映射的处理.
		 * 如果存在这个现象，只能走spring 自己的映射方式.
		 */
		if(info.getPatternsCondition()==null || info.getPatternsCondition().getPatterns().size()==0){
			return;
		}
		
		String path = info.getPatternsCondition().getPatterns().iterator().next();
		if(path.indexOf("{")<0){
			//路径中不包含变量的，采用原有的处理方式，不做处理.
			return;
		}
		
		String[] pathElements = path.split("/");
		if(pathElements.length<=3){
			logger.warn("映射路径不合理,必须是/api/model/actionName/XX，路径信息："+info);
			return;
		}
		boolean isReplace = false;
		StringBuffer keyBuffer = new StringBuffer(pathElements[0]).append("/").
				append(pathElements[1]).append("/").append(pathElements[2]);
		for(int i=3;i<pathElements.length;i++){
			if(StringUtils.isEmpty(pathElements[i])){
				continue;
			}
			if(isReplace || pathElements[i].indexOf("{")>=0){
				isReplace = true;
				pathElements[i] = "_";
			}
			keyBuffer.append("/").append(pathElements[i] );
		}
		String key = keyBuffer.append("/").toString();
		RequestMethodsRequestCondition methodCondition = info.getMethodsCondition();
		if(methodCondition==null || methodCondition.getMethods().size()==0){
			putElement(key+"get", path);
			putElement(key+"post", path);
		}else{
			Set<RequestMethod> reqMethodSet = methodCondition.getMethods();
			for(RequestMethod reqMethod : reqMethodSet){
				putElement(key+reqMethod.toString().toLowerCase(), path);
			}
		}
	}
	
	private static synchronized void putElement(String key, String path){
		List<String> infoList = huatekMappingMap.get(key);
		if(infoList==null){
			infoList = new ArrayList<String>();
			huatekMappingMap.put(key, infoList);
		}else{
			logger.warn("映射路径不合理,已经存在类似的路径信息，会影响客户访问的路由性能，路径信息："+path);
		}
		infoList.add(path);
	}
	
	public static String getRequestMappingInfo(String url, String reqMethod){
		String[] pathElements = url.split("/");
		if(pathElements.length<=3){
			return null;
		}
		for(int i=pathElements.length-1;i>=3;i--){
			pathElements[i] = "_";
			StringBuffer keyBuffer = new StringBuffer(pathElements[0]).append("/").append(pathElements[1]).
					append("/").append(pathElements[2]);
			boolean isReplace = false;
			for(int k=3;k<pathElements.length;k++){
				if(k<i && (isReplace||isNumeric(pathElements[k]))){
					isReplace = true;
					pathElements[k] = "_";
				}
				keyBuffer.append("/").append(pathElements[k]);
			}
			List<String> infoList = huatekMappingMap.get(keyBuffer.append("/").append(reqMethod).toString());
			if(infoList==null){
				continue;
			}
			if(infoList.size()==1){
				if(logger.isDebugEnabled()){
					logger.debug("Restful请求成功使用Huatek映射路径："+infoList.get(0));
				}
				return infoList.get(0);
			}else{
				//目前暂时对这种路径不做处理.
				return null;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	* @Title: isNumeric 
	* @Description: 判断字符串是否为数字 
	* @createDate: 2016年8月3日 下午4:57:58
	* @param   
	* @return  boolean    
	* @throws
	 */
	public static boolean isNumeric(String str){
	    for (int i = str.length(); --i>=0; ){  
    	     if (!Character.isDigit(str.charAt(i))){
    	         return false;
    	     }
	    }
	    return true;
	}
}
