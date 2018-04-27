package com.huatek.frame.core.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MD5Util {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
			.toCharArray();
	private static MessageDigest _MDINST = null;
	private static char HEXDIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static MessageDigest getMdInst() {
		if (_MDINST == null) {
			try {
				_MDINST = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return _MDINST;
	}

	public static String getSignature(String secret, Map<String, String> params) {
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

		System.out.println(basestring.toString());
		// 使用MD5对待签名串求签
		byte[] bytes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		} catch (GeneralSecurityException ex) {

		}

		// 将MD5输出的二进制结果转换为小写的十六进制
		StringBuilder sign = new StringBuilder();
		if (bytes != null) {
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(bytes[i] & 0xFF);
				if (hex.length() == 1) {
					sign.append("0");
				}
				sign.append(hex);
			}
		}
		return sign.toString().toUpperCase();
	}

	public static String getSignature(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			// System.out.println("MD5(" + sourceStr + ",32) = " + result);
			// System.out.println("MD5(" + sourceStr + ",16) = "
			// + buf.toString().substring(8, 24));
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * md5编码
	 *
	 * @param input
	 * @return
	 */
	public static String encodeMD5(String input) {
		try {
			byte[] btInput = input.getBytes();
			// 使用指定的字节更新摘要
			getMdInst().update(btInput);
			// 获得密文
			byte[] md = getMdInst().digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : md) {
				str[k++] = HEXDIGITS[byte0 >>> 4 & 0xf];
				str[k++] = HEXDIGITS[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getSignature(String sign, String access_token,
			String timestamp) {
		String validSign = MD5Util.getSignature(MD5Util
				.getSignature(access_token)
				+ MD5Util.getSignature(timestamp));
		String base64 = "";
		String realtoken = "";
		if (sign.equals(validSign)) {
			base64 = decryptBASE64(access_token);
			realtoken = base64.substring(4, base64.length() - 6);
			return realtoken;
		}else{
			return null;
		}
	}

	public static String encryptBASE64(String data) {

		Encoder encoder = Base64.getEncoder();
		String encode = null;
		try {
			encode = encoder.encodeToString(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encode;
	}

	public static String decryptBASE64(String data) {
		Decoder decoder = Base64.getDecoder();
		byte[] buffer = decoder.decode(data);
		try {
			return new String(buffer, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public static void main(String[] args) throws Exception {
		String ss="eHh4eGV5SjBlWEFpT2lKS1YxUWlMQ0poYkdjaU9pSklVekkxTmlKOS5leUoxYVdRaU9pSXhNak14T1RrMElpd2lZMjlrWlNJNkltWmxhWGgxYmlveE1qTXVVMGhmTWpjNU1UVXdNeUlzSW5SNWNHVWlPaUpoWTJObGMzTmZkRzlyWlc0aUxDSnBjM01pT2lKUWFHbGpiMjF0SWl3aWJtSm1Jam94TlRFM01ETXhOakEyTENKbGVIQWlPakUxTVRjd09UWTBNRFlzSW5KbFpuSmxjMmhVYVcxbElqb2lNakF4T0Mwd01TMHlOeUF4T1RvME1Eb3dOaUo5LnhaZFlLOU9oQWZUTUthaWt2cVlSTHlfd0poVUpzN2dUbjhvVGdxcVBiWUV4eHh4eHg=";
		 System.out.println(encodeMD5(ss));	
	}

}
