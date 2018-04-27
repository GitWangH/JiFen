package com.huatek.frame.core.util;

import java.net.URISyntaxException;
import java.net.URL;

public class PropertyUtil {
	/**
	 * 
	 * @param key
	 *            读取配置的key
	 * @return
	 */
	public static String getConfigValue(String key) {
		// BEGIN: modified by kevin on 2014-12-02 for 解决路径中包含特殊字符的情况，如%20
		String path = CommonConstants.EMPTY_STRING;
		try {
			path = PropertyUtil.class.getResource(FileConstants.CONFIG_FILE_PATH).toURI()
					.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// END: modified by kevin on 2014-12-02 for 解决路径中包含特殊字符的情况，如%20
		// //System.out.println("conifg path:"+path);
		// String configFile =
		// "F://kuyu/应用系统/ESB/4. 源码/src/src/resource/config/sc/config.properties";
		// String configFile = path + "resource/config/sc/config.properties";
		return FileUtil.readValue(path, key);
	}

	public static String getAppConfigValue(String key) {
		// BEGIN: modified by kevin on 2014-12-02 for 解决路径中包含特殊字符的情况，如%20
		String path = CommonConstants.EMPTY_STRING;
		try {
			URL url = PropertyUtil.class
					.getResource(FileConstants.CONFIG_APP_FILE_PATH);
			if(url == null){
				return null;
			}
			path = url.toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return FileUtil.readValue(path, key);
	}

}
