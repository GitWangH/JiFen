package com.huatek.frame.core.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger l4jLogger = Logger.getLogger(FileUtil.class);
	private static Map<String, Properties> props;

	public static String readValue(String filePath, String key) {
		try {
			if (props == null) {
				props = new HashMap<String, Properties>();
			}
			Properties prop = null;
			if (props.get(filePath) == null) {
				prop = new Properties();
				InputStream in = new BufferedInputStream(new FileInputStream(
						filePath));
				prop.load(in);
				props.put(filePath, prop);
				in.close();
			} else {
				prop = props.get(filePath);
			}
			String value = prop.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			l4jLogger.error(e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static void readProperties(String filePath) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				// String Property =
				props.getProperty(key);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			l4jLogger.error(e.getMessage());
		}
	}

	public static void writeProperties(String filePath, String parameterName,
			String parameterValue) {
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			prop.load(fis);
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			prop.store(fos, "Update '" + parameterName + "' value");
			fos.close();
			fis.close();
		} catch (IOException e) {
			l4jLogger.error(e.getMessage());
		}
	}
	
	/**
	 * 根据Key获取文件信息
	 * @param key
	 * @return
	 */
	public static String getPropertiesValue(String key){
        //
        String value = "";
        try{
            Properties properties = new Properties();
            InputStream is = FileUtil.class.getClassLoader().getResourceAsStream("upload_info.properties");
            properties.load(is);
            //获取MongDb相关参数信息
            value = properties.getProperty(key);
        }catch(Exception e){
            throw new ExceptionInInitializerError(e);
        }
        return value;
    }
	
	/**
	 * 
	 * @author eden  
	 * @date Jan 4, 2018 3:32:18 PM
	 * @desc TODO(用一句话描述本方法的作用)  
	 * @param: @param path
	 * @param: @return  
	 * @return: String      
	 * @throws
	 */
	public static String getPath(String path){
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
	}
}
