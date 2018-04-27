package com.huatek.frame.handle.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
  * @ClassName: AbstractConfigurationUtil
  * @Description: 配置文件工具父类
  * @author: Arno
  * @Email : Arno_Fu@huatek.com
  * @date: 2016年9月5日 下午6:04:21
  * @version: 1.0
  */

public abstract class AbstractConfigurationUtil {

	/** @Fields conf :MySQL数据库配置信息 */ 
	protected Properties conf = new Properties();

	/** 
	* @Title: getConfigValue 
	* @Description: 获取配置信息 
	* @createDate: 2016年9月5日 下午6:05:39
	* @param key
	* @return  String  
	*/ 
	public String getConfigValue(String key) {
		if (StringUtils.isEmpty(key)) {
			return StringUtils.EMPTY;
		}

		if (conf.isEmpty()) {
			try {
				readConfigurationFile();
			} catch (Exception e) {
				return StringUtils.EMPTY;
			}
		}

		return conf.getProperty(key, StringUtils.EMPTY).trim();
	}

	/** 
	* @Title: readConfigurationFile 
	* @Description:  读取配置文件 
	* @createDate: 2016年9月5日 下午6:05:47
	* @throws Exception   
	* @return  void  
	*/ 
	protected void readConfigurationFile() throws Exception {
		InputStream inputStream = null;
		ClassPathResource resource = new ClassPathResource(
				getConfurationFilePath());
		inputStream = resource.getInputStream();
		conf.load(inputStream);

		if (inputStream != null) {
			inputStream.close();
		}
	}

	/** 
	* @Title: getConfigurationKeys 
	* @Description: 返回所有配置信息键值 
	* @createDate: 2016年9月5日 下午6:05:54
	* @return  String[]  
	*/ 
	public String[] getConfigurationKeys() {
		if (conf.isEmpty()) {
			try {
				readConfigurationFile();
			} catch (Exception e) {
				return new String[0];
			}
		}

		return conf.keySet().toArray(new String[conf.size()]);
	}

	/** 
	* @Title: getConfurationFilePath 
	* @Description: 获取配置文件路径 
	* @createDate: 2016年9月5日 下午6:06:03
	* @return  String  
	*/ 
	protected abstract String getConfurationFilePath();


}
