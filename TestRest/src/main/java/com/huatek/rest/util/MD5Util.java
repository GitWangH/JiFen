package com.huatek.rest.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MD5Util {
	public static String getSignature(String secret,Map<String,String> params){
		if (params == null)
			return null;
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, String> treeMap = new TreeMap<String, String>();
		treeMap.putAll(params);

		StringBuilder basestring = new StringBuilder();
		basestring.append(secret);
		Iterator<String> iter = treeMap.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			basestring.append(name).append(params.get(name));
		}
		basestring.append(secret);

//		System.out.println(basestring.toString());
		// 使用MD5对待签名串求签
		byte[] bytes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
		}catch (UnsupportedEncodingException e) {
		} catch (GeneralSecurityException ex) {
			
		}

		// 将MD5输出的二进制结果转换为小写的十六进制
		StringBuilder sign = new StringBuilder();
		if(bytes!=null){
			for (int i = 0; i<bytes.length; i++) {
				String hex = Integer.toHexString(bytes[i] & 0xFF);
				if (hex.length() == 1) {
					sign.append("0");
				}
				sign.append(hex);
			}
		}
		return sign.toString().toUpperCase();
	}

}
